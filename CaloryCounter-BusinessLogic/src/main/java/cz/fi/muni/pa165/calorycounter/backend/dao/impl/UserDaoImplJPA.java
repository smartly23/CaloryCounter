package cz.fi.muni.pa165.calorycounter.backend.dao.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.UserDao;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JPA/Hibernate DAO implementation - for operations on the persistence layer on
 * User entities.
 *
 * @author Martin Pasko (smartly23)
 */
public class UserDaoImplJPA implements UserDao {

    final static Logger log = LoggerFactory.getLogger(UserDaoImplJPA.class);
    // injected from Spring
    @PersistenceContext
    private EntityManager em;

    public UserDaoImplJPA() {
    }
    
    // this is only for legacy compatibility with some old tests
    public UserDaoImplJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public AuthUser getByUsername(String username) {
        if (validate(username)) {
            throw new IllegalArgumentException("Invalid username: null");
        }
        TypedQuery<AuthUser> query;
        AuthUser returnedUser;
        try {
            query = em.createQuery("SELECT tbl FROM AuthUser tbl "
                    + " WHERE tbl.username = :uname", AuthUser.class);
            query.setParameter("uname", username);
            returnedUser = query.getSingleResult();     // getSingleResult hadze NoResultException
        } catch (NoResultException nrex) {
            return null;                     // need this information during registration
        }
        return returnedUser;
    }
    
    

    @Override
    public Long create(AuthUser user) {
        if (validate(user) || user.getUsername() == null) {
            throw new IllegalArgumentException("Invalid user: null or null username of user");
        }
        AuthUser createdUser = em.merge(user);     // nechceme mu vratit manazovanu entitu, t.j. aby mohol robit zmeny mimo
        // vyhradenych CRUD operacii - to nechceme
        return createdUser.getId();

    }

    @Override
    public AuthUser get(Long id) {
        if (validate(id)) {
            throw new IllegalArgumentException("Invalid id: null");
        } else if (em.createQuery("SELECT tbl.id FROM AuthUser tbl WHERE tbl.id = "
                + ":givenId", Long.class).setParameter("givenId", id).getResultList().size() < 1) {
            throw new IllegalArgumentException("Invalid id: nonexistent");
        }
        return em.createQuery("SELECT tbl FROM AuthUser tbl "
                + "WHERE tbl.id = :givenId", AuthUser.class).setParameter("givenId", id).getSingleResult();
        // nechceme vracat manazovanu entitu (return em.find(AuthUser.class, id)), treba vyuzivat CRUD metody
    }

    @Override
    public void update(AuthUser user) {
        if (validate(user) || user.getId() == null) {
            throw new IllegalArgumentException("Invalid user: null or with no id.");
        } else if (em.createQuery("SELECT tbl.id FROM AuthUser tbl WHERE tbl.id = "
                + ":givenId", Long.class).setParameter("givenId", user.getId()).getResultList().size() < 1) {
            throw new IllegalArgumentException("Invalid user: nonexistent");
        }
        em.merge(user);
    }

    @Override
    public void remove(AuthUser user) {
        if (validate(user) || user.getId() == null) {
            throw new IllegalArgumentException("Invalid user: null or with no id.");
        }
        AuthUser authUser = em.find(AuthUser.class, user.getId());
        if (authUser == null) {
            log.error("Given user" + user + "is not in DB.");
        }
        em.remove(user);                    // em.find je nutne, remove zmaze iba manazovanu entitu
        // je potrebne pri inverznej zavislosti osetrit pre-removal
    }

    private boolean validate(Object obj) {
        return obj == null;
    }

    @Override
    public AuthUser login(String username, String password) {
        if (validate(username) || validate(password)) {
            throw new IllegalArgumentException("Invalid username or password: null");
        }
        TypedQuery<AuthUser> query;
        AuthUser returnedUser;
        try {
            query = em.createQuery("SELECT tbl FROM AuthUser tbl "
                    + " WHERE tbl.username = :uname and tbl.password = :pword", AuthUser.class);
            query.setParameter("uname", username);
            query.setParameter("pword", password);
            returnedUser = query.getSingleResult();     // getSingleResult hadze NoResultException
        } catch (NoResultException nrex) {
            log.info("User with given username and password doest not exist: null is returning");
            return null;
        }
        return returnedUser;
    }

}