package cz.fi.muni.pa165.calorycounter.backend.dto.convert;

import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityRecordDto;
import cz.fi.muni.pa165.calorycounter.backend.model.ActivityRecord;
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
    public ActivityRecord fromDtoToEntity(ActivityRecordDto dto) {
        ActivityRecord entity = new ActivityRecord();
        AuthUserConvert userConvert = new AuthUserConvert();
        CaloriesConvert caloriesConvert = new CaloriesConvert();

        entity.setAuthUser(userConvert.fromDtoToEntity(dto.getAuthUser()));
        entity.setCalories(caloriesConvert.fromDtoToEntity(dto.getCalories()));
        entity.setActivityDate(new java.sql.Date(dto.getActivityDate().getTime()));     // util.Date to sql.Date
        entity.setCaloriesBurnt(dto.getCaloriesBurnt());
        entity.setDuration(dto.getDuration());
        if (dto.getActivityRecordId() == null) {
            return entity;
        } else {
            entity.setId(dto.getActivityRecordId());
            return entity;
        }
    }

    @Override
    public ActivityRecordDto fromEntityToDto(ActivityRecord entity) {
        ActivityRecordDto dto = new ActivityRecordDto();
        AuthUserConvert userConvert = new AuthUserConvert();
        CaloriesConvert caloriesConvert = new CaloriesConvert();

        dto.setAuthUser(userConvert.fromEntityToDto(entity.getAuthUser()));
        dto.setCalories(caloriesConvert.fromEntityToDto(entity.getCalories()));
        dto.setActivityDate(new java.util.Date(entity.getActivityDate().getTime()));    // sql.Date to util.Date
        dto.setCaloriesBurnt(entity.getCaloriesBurnt());
        dto.setDuration(entity.getDuration());
        if (entity.getId() == null) {
            log.warn("Entity " + entity + "converted to DTO has no id!");
            return dto;
        } else {
            dto.setActivityRecordId(entity.getId());
            return dto;
        }
    }
}
