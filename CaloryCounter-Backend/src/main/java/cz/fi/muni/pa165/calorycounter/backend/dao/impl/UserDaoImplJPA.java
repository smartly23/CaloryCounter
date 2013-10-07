package cz.fi.muni.pa165.calorycounter.backend.dao.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.UserDao;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * JPA/Hibernate DAO implementation - for operations on the persistence layer on User entities.
 *
 * @author Martin Pasko (smartly23)
 */
public class UserDaoImplJPA implements UserDao {

    @PersistenceContext(name = "PU1")
    private EntityManager em;
            
    @Override
    public AuthUser getByUsername(String username) {
        if(validate(username)) {
            throw new IllegalArgumentException("Invalid username: null");
        }
        TypedQuery<AuthUser> query;
        try {
        query = em.createQuery("SELECT tbl FROM TableName tbl"
                + "WHERE tbl.username = :uname", AuthUser.class);
        query.setParameter("uname", username);
        } catch(NoResultException nrex) {
            throw new IllegalArgumentException("Invalid username: nonexistent");
        }
        return query.getSingleResult();
    }

    @Override
    public void create(AuthUser entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AuthUser get(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(AuthUser entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(AuthUser entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean validate(Object obj) {
        return !(obj == null);
    }
    
}
