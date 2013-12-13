/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.dao.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.ActivityDao;
import cz.fi.muni.pa165.calorycounter.backend.model.Activity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * JPA/Hibernate DAO implementation - for operations on the persistence layer on
 * Activity entities.
 *
 * @author Martin Bryndza (martin.bryndza)
 */
@Repository
public class ActivityDaoImplJPA implements ActivityDao {

    final static Logger LOG = LoggerFactory.getLogger(UserDaoImplJPA.class);
    // injected from Spring
    @PersistenceContext
    private EntityManager em;

    public ActivityDaoImplJPA() {
    }

    // this is only for legacy compatibility with some old tests
    public ActivityDaoImplJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long create(Activity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Invalid entity (Activity): " + entity);
        }
        LOG.debug("Creating " + entity.toString());
        Activity activity = em.merge(entity);
        Long id = activity.getId();
        LOG.debug("Created " + activity.toString() + ". Assigned ID: " + id);
        return id;
    }

    @Override
    public Activity get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        } else if (em.createQuery("SELECT a.id FROM Activity a WHERE a.id = :id", Long.class).setParameter("id", id).getResultList().size() < 1) {
            throw new IllegalArgumentException("Invalid id: nonexistent");
        }
        return em.createQuery("SELECT a FROM Activity a WHERE a.id = :id", Activity.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void update(Activity entity) {
        if ((entity == null) || entity.getId() == null) {
            throw new IllegalArgumentException("Invalid entity (Activity): null or with no id.");
        } else if (em.createQuery("SELECT a.id FROM Activity a WHERE a.id = :id", Long.class).setParameter("id", entity.getId()).getResultList().size() < 1) {
            throw new IllegalArgumentException("Invalid entity (Activity): nonexistent");
        }
        em.merge(entity);
    }

    @Override
    public void remove(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid entity (Activity): null or with no id.");
        }
        Activity activity = em.find(Activity.class, id);
        if (activity == null) {
            LOG.error("Given activity with id " + id + " is not in DB.");
        }
        em.remove(activity);
    }

    @Override
    public Activity get(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is null or empty: \"" + name + "\"");
        } else if (em.createQuery("SELECT a.id FROM Activity a WHERE a.name = :name", Long.class).setParameter("name", name).getResultList().size() < 1) {
            throw new IllegalArgumentException("Invalid name: nonexistent");
        }
        return em.createQuery("SELECT a FROM Activity a WHERE a.name = :name", Activity.class).setParameter("name", name).getSingleResult();
    }
}
