package cz.fi.muni.pa165.calorycounter.backend.dto.convert;

import cz.fi.muni.pa165.calorycounter.backend.dao.ActivityDao;
import cz.fi.muni.pa165.calorycounter.backend.dao.ActivityRecordDao;
import cz.fi.muni.pa165.calorycounter.backend.dao.CaloriesDao;
import cz.fi.muni.pa165.calorycounter.backend.dao.UserDao;
import cz.fi.muni.pa165.calorycounter.backend.dao.impl.ActivityDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.dao.impl.ActivityRecordDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.dao.impl.CaloriesDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.dao.impl.UserDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityRecordDto;
import cz.fi.muni.pa165.calorycounter.backend.model.Activity;
import cz.fi.muni.pa165.calorycounter.backend.model.ActivityRecord;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import cz.fi.muni.pa165.calorycounter.backend.model.Calories;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Conversion between ActivityRecord DTO and entity back and forth.
 *
 * @author Martin Pasko (smartly23)
 */
public class ActivityRecordConvert implements Convert<ActivityRecord, ActivityRecordDto> {

    final static Logger log = LoggerFactory.getLogger(ActivityRecordConvert.class);

    @Override
    public ActivityRecord fromDtoToEntity(ActivityRecordDto dto, EntityManager em) {
        ActivityRecord entity;
        ActivityRecordDao activityRecordDao = new ActivityRecordDaoImplJPA(em);

        if (dto.getActivityRecordId() != null) {
            entity = activityRecordDao.get(dto.getActivityRecordId());
            entity.getCalories().getActivity().setName(dto.getActivityName());
            for (WeightCategory cat : WeightCategory.values()) {
                if (cat.ordinal() + 1 == dto.getWeightCatNum()) {
                    entity.getCalories().setWeightCat(cat);
                } else {
                    log.warn("ActivityRecord DTO-to-DAO conversion: unknown weight category number in DTO.");
                }
            }
        } else {
            entity = new ActivityRecord();
            // create new Calories object and fill it into our entity; transaction management in the calling class
            Calories calories = new Calories();
            Activity activity = new Activity();
            activity.setName(dto.getActivityName());
            ActivityDao activityDao = new ActivityDaoImplJPA(em);
            activityDao.create(activity);
            calories.setActivity(activity);
            for (WeightCategory cat : WeightCategory.values()) {
                if (cat.ordinal() + 1 == dto.getWeightCatNum()) {
                    calories.setWeightCat(cat);
                } else {
                    log.warn("ActivityRecord DTO-to-DAO conversion: unknown weight category number in DTO.");
                }
            }
            CaloriesDao caloriesDao = new CaloriesDaoImplJPA(em);
            caloriesDao.create(calories);
            entity.setCalories(calories);
        }

        if (dto.getUserId() != null) {
            UserDao userDao = new UserDaoImplJPA(em);
            entity.setAuthUser((AuthUser) userDao.get(dto.getUserId()));
            // conversion method is not responsible for checking any data consistence
        } else {
            log.error("ActivityRecord DTO-to-DAO conversion: converting to ActivityRecord entity "
                    + "but no user id given in DTO. User id is mandatory. Exception thrown.");
            throw new IllegalArgumentException("No userId in DTO: " + dto + "userId is mandatory.");
        }
        entity.setActivityDate(new java.sql.Date(dto.getActivityDate().getTime()));     // util.Date to sql.Date
        entity.setCaloriesBurnt(dto.getCaloriesBurnt());
        entity.setDuration(dto.getDuration());

        return entity;
    }

    @Override
    public ActivityRecordDto fromEntityToDto(ActivityRecord entity) {
        ActivityRecordDto dto = new ActivityRecordDto();

        dto.setActivityDate(new java.util.Date(entity.getActivityDate().getTime()));    // sql.Date to util.Date
        dto.setCaloriesBurnt(entity.getCaloriesBurnt());
        dto.setDuration(entity.getDuration());
        dto.setWeightCatNum(entity.getCalories().getWeightCat().ordinal() + 1);
        dto.setActivityName(entity.getCalories().getActivity().getName());
        dto.setUserId(entity.getAuthUser().getId());
        if (entity.getId() == null) {
            log.error("ActivityRecord DAO-to-DTO: Entity " + entity + "to be converted to DTO has no id!");
            throw new IllegalArgumentException("Entity " + entity + "to-be-converted to DTO has no id!");

        } else {
            dto.setActivityRecordId(entity.getId());
        }
        return dto;
    }
}
