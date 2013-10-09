package cz.fi.muni.pa165.calorycounter.backend.dao.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.ActivityRecordDao;
import cz.fi.muni.pa165.calorycounter.backend.model.ActivityRecord;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA/Hibernate DAO implementation - for operations on the persistence layer on
 * User entities.
 *
 * @author Martin Pasko (smartly23)
 */
public class ActivityRecordDaoImplJPA implements ActivityRecordDao {

    @PersistenceContext(name = "PU1")
    private EntityManager em;

    @Override
    public void create(ActivityRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Invalid record: null");
        }
        em.getTransaction().begin();
        em.merge(record);     // nechceme mu vratit manazovanu entitu, t.j. aby mohol robit zmeny mimo
        // vyhradenych CRUD operacii - to nechceme
        em.getTransaction().commit();
    }

    @Override
    public ActivityRecord get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid id: null");
        } else if (em.createQuery("SELECT tbl.id FROM ActivityRecord tbl WHERE tbl.id = "
                + ":givenId", Long.class).setParameter("givenId", id).getResultList().size() < 1) {
            throw new IllegalArgumentException("Invalid id: nonexistent");
        }
        return em.createQuery("SELECT tbl FROM ActivityRecord tbl"
                + "WHERE tbl.id = :givenId", ActivityRecord.class).setParameter("givenId", id).getSingleResult();
        // nechceme vracat manazovanu entitu (return em.find(AuthUser.class, id)), treba vyuzivat CRUD metody
    }

    @Override
    public void update(ActivityRecord record) {
        if (record == null || record.getId() == null) {
            throw new IllegalArgumentException("Invalid record: null or with no id.");
        } else if (em.createQuery("SELECT tbl.id FROM ActivityRecord tbl WHERE tbl.id = "
                + ":givenId", Long.class).setParameter("givenId", record.getId()).getResultList().size() < 1) {
            throw new IllegalArgumentException("Invalid user: nonexistent");
        }
        em.getTransaction().begin();
        em.merge(record);
        em.getTransaction().commit();
    }

    @Override
    public void remove(ActivityRecord record) {
        if (record == null || record.getId() == null) {
            throw new IllegalArgumentException("Invalid record: null or with no id.");
        }
        ActivityRecord activityRecord = em.find(ActivityRecord.class, record.getId());
        if (activityRecord == null) {
            // log: User not in DB
        }
        em.getTransaction().begin();
        em.remove(record);                    // em.find je nutne, remove zmaze iba manazovanu entitu
        em.getTransaction().commit();
        // je potrebne pri inverznej zavislosti osetrit pre-removal
    }
}
