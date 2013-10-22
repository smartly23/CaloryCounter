package cz.fi.muni.pa165.calorycounter.backend.service.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.ActivityRecordDao;
import cz.fi.muni.pa165.calorycounter.backend.dao.impl.ActivityRecordDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityRecordDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.convert.ActivityRecordConvert;
import cz.fi.muni.pa165.calorycounter.backend.model.ActivityRecord;
import cz.fi.muni.pa165.calorycounter.backend.service.ActivityRecordService;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User service for all operations on ActivityRecord DTO.
 *
 * @author Martin Pasko (smartly23)
 */
public class ActivityRecordServiceImpl implements ActivityRecordService {

    final static Logger log = LoggerFactory.getLogger(ActivityRecordConvert.class);
    // injektovat zo Springu
    private EntityManager em;
    private ActivityRecordConvert convert = new ActivityRecordConvert();
    private ActivityRecordDao activityRecordDao = new ActivityRecordDaoImplJPA(em);

    @Override
    public Long create(ActivityRecordDto dto) {
        if (dto.getActivityRecordId() != null) {
            IllegalArgumentException iaex = new IllegalArgumentException("Cannot create activity record that"
                    + " already exists. Use update instead.");
            log.error("ActivityRecordServiceImpl.create() called on existing entity", iaex);
            throw iaex;
        } else {
            em.getTransaction().begin();
            ActivityRecord entity = convert.fromDtoToEntity(dto, em);
            em.getTransaction().commit();   // must commit SEPARATELY to have Activity and Calories objects' id set up

            em.getTransaction().begin();        // ak budeme robit fasadu, tak transakcie posunut az tam hore
            activityRecordDao.create(entity);
            em.getTransaction().commit();
            return entity.getId();
        }
    }

    @Override
    public ActivityRecordDto get(Long id) {
        ActivityRecord entity = activityRecordDao.get(id);
        ActivityRecordDto dto = convert.fromEntityToDto(entity);
        return dto;
    }

    @Override
    public void update(ActivityRecordDto dto) {
        if (dto.getActivityRecordId() == null) {
            IllegalArgumentException iaex = new IllegalArgumentException("Cannot update activity record that"
                    + " doesn't exist. Use create instead.");
            log.error("ActivityRecordServiceImpl.update() called on non-existent entity", iaex);
            throw iaex;
        } else {
            em.getTransaction().begin();
            ActivityRecord entity = convert.fromDtoToEntity(dto, em);   // no need for SEPARATE transaction as we are not creating
            // new Calories or Activity objects here (but we are updating the Calories object - will its table update too?)
            activityRecordDao.update(entity);
            em.getTransaction().commit();
        }
    }

    @Override
    public void remove(ActivityRecordDto dto) {
        if (dto.getActivityRecordId() == null) {
            IllegalArgumentException iaex = new IllegalArgumentException("Cannot remove activity record that"
                    + " doesn't exist.");
            log.error("ActivityRecordServiceImpl.remove() called on non-existent entity", iaex);
            throw iaex;
        } else {
            em.getTransaction().begin();
            activityRecordDao.remove(activityRecordDao.get(dto.getActivityRecordId()));
            em.getTransaction().commit();
        }
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
