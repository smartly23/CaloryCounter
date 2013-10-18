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

    @Override
    public Long create(ActivityRecordDto dto) {
        ActivityRecordConvert convert = new ActivityRecordConvert();
        ActivityRecord entity = convert.fromDtoToEntity(dto);
        if (entity.getId() != null) {
            IllegalArgumentException iaex = new IllegalArgumentException("Cannot create activity record that"
                    + " already exists. Use update instead.");
            log.error("ActivityRecordServiceImpl.create() called on existing entity", iaex);
            throw iaex;
        } else {
            ActivityRecordDao activityRecordDao = new ActivityRecordDaoImplJPA(em);
            em.getTransaction().begin();        // ak budeme robit fasadu, tak transakcie posunut az tam hore
            activityRecordDao.create(entity);
            em.getTransaction().commit();
            return entity.getId();
        }
    }

    @Override
    public ActivityRecordDto get(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ActivityRecordDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(ActivityRecordDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
