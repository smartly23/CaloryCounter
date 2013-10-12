/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.dao;

import cz.fi.muni.pa165.calorycounter.backend.dao.impl.UserDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito.*;

/**
 *
 * @author Martin Bryndza (martin.bryndza)
 */
public class AuthUserDaoTest {

    private UserDao authUserDao = null;
    private AuthUser luke = null;
    private AuthUser anakin = null;
    private AuthUser obiWan = null;
    private EntityManager em = null;

    public AuthUserDaoTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU1");
        em = emf.createEntityManager();
        authUserDao = new UserDaoImplJPA(em);
        prepareTestEntities();
    }

    @Test
    public void testCreate() {
        AuthUser c3po = new AuthUser();
        c3po.setName("C-3PO");
        c3po.setAge(3);
        c3po.setGender("ROBOT");
        c3po.setUsername("c3po");
        c3po.setWeightCat(WeightCategory._205_);

        em.getTransaction().begin();
        Long returnedId = authUserDao.create(c3po);
        em.getTransaction().commit();
        assertTrue("User was not created.", (returnedId != null && !returnedId.equals(new Long(0))));

        AuthUser createdUser = getUserById(returnedId);
        assertNotNull("User was not received by id", createdUser);
    }

    @Test
    public void testUpdate() {
        AuthUser user = new AuthUser();
        user.setId(anakin.getId());
        user.setName("Darth Vader");
        user.setAge(33);
        user.setWeightCat(WeightCategory._180_);
        user.setGender("MALE");
        user.setUsername("vady");

        authUserDao.update(user);

        AuthUser updatedUser = getUserById(user.getId());

        assertTrue("Name was not updated.", updatedUser.getName().equals(user.getName()));
        assertTrue("Gender was not updated.", updatedUser.getGender().equals(user.getGender()));
        assertTrue("Username was not updated.", updatedUser.getUsername().equals(user.getUsername()));
        assertTrue("Age was not updated.", updatedUser.getAge() == user.getAge());
        assertTrue("WeightCategory was not updated.", updatedUser.getWeightCat().equals(user.getWeightCat()));
    }

    @Test
    public void testGet() {
        AuthUser returnedUser = authUserDao.get(luke.getId());
        assertNotNull("User was not received by id", returnedUser);
    }

    @Test
    public void testRemove() {
        authUserDao.remove(obiWan);
        AuthUser deletedUser = getUserById(obiWan.getId());
        assertNull("User was not deleted", deletedUser);
    }

    @Test
    public void testGetByUsername() {
        AuthUser userByUsername = authUserDao.getByUsername(luke.getUsername());
        assertTrue("User was not found by username", userByUsername != null);
        assertTrue("Username of the user returned is not correct", userByUsername.getUsername().equals(luke.getUsername()));
    }

    private void prepareTestEntities() {
        luke = new AuthUser();
        luke.setName("Luke Skywalker");
        luke.setAge(30);
        luke.setGender("MALE");
        luke.setUsername("luke");
        luke.setWeightCat(WeightCategory._155_);

        anakin = new AuthUser();
        anakin.setName("Anakin Skywalker");
        anakin.setAge(10);
        anakin.setGender("MALE");
        anakin.setUsername("anakin");
        anakin.setWeightCat(WeightCategory._130_);

        obiWan = new AuthUser();
        obiWan.setName("Obi-Wan Kenobi");
        obiWan.setAge(50);
        obiWan.setGender("MALE");
        obiWan.setUsername("keny");
        obiWan.setWeightCat(WeightCategory._180_);

        em.getTransaction().begin();
        em.persist(luke);
        em.persist(anakin);
        em.persist(obiWan);
        em.getTransaction().commit();
    }

    private AuthUser getUserById(long id) {
        Query q = em.createQuery("SELECT a FROM AuthUser a WHERE a.id=:id", AuthUser.class).setParameter("id", id);
        try {
            return (AuthUser) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
}
