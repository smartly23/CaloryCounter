package cz.fi.muni.pa165.calorycounter.backend.dao.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.ActivityRecordDao;
import cz.fi.muni.pa165.calorycounter.backend.model.ActivityRecord;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JPA/Hibernate DAO implementation - for operations on the persistence layer on
 * ActivityRecord entities.
 *
 * @author Zdenek Lastuvka
 */
public class ActivityRecordDaoImplJPA implements ActivityRecordDao {

    final static Logger log = LoggerFactory.getLogger(CaloriesDaoImplJPA.class);
    private EntityManager em;

    public ActivityRecordDaoImplJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long create(ActivityRecord record) {
        if (validate(record)) {
            throw new IllegalArgumentException("Invalid record: null");
        }
        ActivityRecord createdRecord = em.merge(record);
        return createdRecord.getId();
    }

    @Override
    public ActivityRecord get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid id: null");
        } else if (em.createQuery("SELECT tbl.id FROM ActivityRecord tbl WHERE tbl.id = "
                + ":givenId", Long.class).setParameter("givenId", id).getResultList().size() < 1) {
            throw new IllegalArgumentException("Invalid id: nonexistent");
        }
        return em.createQuery("SELECT tbl FROM ActivityRecord tbl "
                + "WHERE tbl.id = :givenId", ActivityRecord.class).setParameter("givenId", id).getSingleResult();
        // nechceme vracat manazovanu entitu (return em.find(AuthUser.class, id)), treba vyuzivat CRUD metody
    }

    @Override
    public void update(ActivityRecord record) {
        if (validate(record) || record.getId() == null) {
            throw new IllegalArgumentException("Invalid record: null or with no id.");
        } else if (em.createQuery("SELECT tbl.id FROM ActivityRecord tbl WHERE tbl.id = "
                + ":givenId", Long.class).setParameter("givenId", record.getId()).getResultList().size() < 1) {
            throw new IllegalArgumentException("Invalid user: nonexistent");
        }
        em.merge(record);
    }

    @Override
    public void remove(ActivityRecord record) {
        if (validate(record) || record.getId() == null) {
            throw new IllegalArgumentException("Invalid record: null or with no id.");
        }
        ActivityRecord activityRecord = em.find(ActivityRecord.class, record.getId());
        if (activityRecord == null) {
            log.error("ActivityRecord is not in DB");
        }

        em.remove(record);
    }

    private boolean validate(ActivityRecord record) {
        return (record == null || record.getAuthUser() == null || record.getCalories() == null);
    }
}
