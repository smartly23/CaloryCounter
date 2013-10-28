package cz.fi.muni.pa165.calorycounter.backend.service.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.ActivityRecordDao;
import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityRecordDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.convert.ActivityRecordConvert;
import cz.fi.muni.pa165.calorycounter.backend.model.ActivityRecord;
import cz.fi.muni.pa165.calorycounter.backend.service.ActivityRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * User service for all operations on ActivityRecord DTO.
 *
 * @author Martin Pasko (smartly23)
 */
@Service
@Transactional(readOnly = true)
public class ActivityRecordServiceImpl implements ActivityRecordService {

    final static Logger log = LoggerFactory.getLogger(ActivityRecordConvert.class);
    private ActivityRecordConvert convert = new ActivityRecordConvert();
    // concrete implementation injected from Spring
    private ActivityRecordDao activityRecordDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {DataAccessException.class})
    // to Propagation.REQUIRED je tam aj defaultne, ak nechceme nastavit inu tak to tam neni nutne
    // rollbackFor - automaticky sa spusti rollback pre vsetky RuntimeException, ale NIE pre checked Ex-s
    // DataAccessException je runtime, teda to tam neni nutne deklarovat
    public Long create(ActivityRecordDto dto) {
        if (dto.getActivityRecordId() != null) {
            IllegalArgumentException iaex = new IllegalArgumentException("Cannot create activity record that"
                    + " already exists. Use update instead.");
            log.error("ActivityRecordServiceImpl.create() called on existing entity", iaex);
            throw iaex;
        } else {
            ActivityRecord entity = convert.fromDtoToEntity(dto);

            activityRecordDao.create(entity);
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
    @Transactional(readOnly = false)
    public void update(ActivityRecordDto dto) {
        if (dto.getActivityRecordId() == null) {
            IllegalArgumentException iaex = new IllegalArgumentException("Cannot update activity record that"
                    + " doesn't exist. Use create instead.");
            log.error("ActivityRecordServiceImpl.update() called on non-existent entity", iaex);
            throw iaex;
        } else {
            ActivityRecord entity = convert.fromDtoToEntity(dto);
            activityRecordDao.update(entity);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(ActivityRecordDto dto) {
        if (dto.getActivityRecordId() == null) {
            IllegalArgumentException iaex = new IllegalArgumentException("Cannot remove activity record that"
                    + " doesn't exist.");
            log.error("ActivityRecordServiceImpl.remove() called on non-existent entity", iaex);
            throw iaex;
        } else {
            activityRecordDao.remove(activityRecordDao.get(dto.getActivityRecordId()));
        }
    }

}
