package cz.fi.muni.pa165.calorycounter.backend.dao;

import cz.fi.muni.pa165.calorycounter.backend.dao.impl.ActivityDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.model.Activity;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Unit Tests on ActivityDaoImplJPA class using in-memory database and JPA
 * implementation to avoid mutual dependence among tested methods.
 *
 * @author Martin Pasko (smartly23)
 */
public class ActivityDaoTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    EntityManager em2;
    private ActivityDao activityDao;
    final static Logger log = LoggerFactory.getLogger(ActivityDaoTest.class);

    @BeforeClass
    public static void setUpOnce() {
        emf = Persistence.createEntityManagerFactory("TestPU");
    }

    @AfterClass
    public static void tearDownOnce() {
        if (emf != null) {
            emf.close();
        }
    }

    @Before
    public void setUp() {   // doporucuje sa per-method inicializaciu robit v setUp radsej ako v Konstruktore
        em = emf.createEntityManager();
        activityDao = new ActivityDaoImplJPA(em);
    }

    @After
    public void tearDown() {
        em.close();
        if (em2 != null && em2.isOpen()) {
            em2.close();
        }
        activityDao = null;
    }

    @Test
    public void testCreate() {
        Activity activity = new Activity();
        activity.setName("Chopping wood sloww");

        em.getTransaction().begin();
        Long activityId = activityDao.create(activity);
        em.getTransaction().commit();
        assertFalse("Activity was not created.", activityId == null);

        try {
            activityDao.create(null);
            fail("No exception thrown with null argument");
        } catch (IllegalArgumentException iae) {
        }

    }

    @Test
    public void testGet() {
        Activity activity = new Activity();
        activity.setName("Chopping wood slow");

        Long activityId;
        try {
            em.getTransaction().begin();
            em.persist(activity);
            em.getTransaction().commit();
            activityId = activity.getId();
        } catch (Exception ex) {
            throw new RuntimeException("internal integrity error", ex);
        }
        assertNotNull("internal integrity error", activityId);

        em2 = emf.createEntityManager();              // kvoli cache lvl 1
        ActivityDao activityDao2 = new ActivityDaoImplJPA(em2);
        Activity testActivity = activityDao2.get(activityId);
        assertEquals(activityId, testActivity.getId());

        try {
            activityDao2.get(activityId + 1);
            fail("Should throw exception when non-existent id is entered.");
        } catch (IllegalArgumentException iae) {
        }

    }

    @Test
    public void testUpdate() {
        Activity activity = new Activity();
        activity.setName("Chopping wood slowwww");

        Long activityId;
        try {
            em.getTransaction().begin();
            em.persist(activity);
            em.getTransaction().commit();
            activityId = activity.getId();
        } catch (Exception ex) {
            throw new RuntimeException("internal integrity error", ex);
        }
        assertNotNull("internal integrity error", activityId);

        activity.setName("Chopping wood fast");
        em.getTransaction().begin();
        activityDao.update(activity);
        em.getTransaction().commit();

        em2 = emf.createEntityManager();              // to avoid returning result from cache lvl 1
        ActivityDao activityDao2 = new ActivityDaoImplJPA(em2);
        Activity testActivity = em2.find(Activity.class, activityId);
        assertThat(testActivity.getName(), IsEqualIgnoringCase.equalToIgnoringCase(activity.getName()));

        activity.setId(activityId + 1);
        try {
            activityDao.update(activity);
            fail("Should throw exception when non-existent id is entered.");
        } catch (IllegalArgumentException iae) {
            try {
                activityDao.update(null);
                fail("Should throw exception when null argument is entered.");
            } catch (IllegalArgumentException iaex) {
            }
        }

    }

    @Test
    public void testRemove() {
        Activity activity = new Activity();
        activity.setName("Chopping wood slowww");

        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();

        em2 = emf.createEntityManager();              // to avoid returning result from cache lvl 1
        ActivityDao activityDao2 = new ActivityDaoImplJPA(em2);
        Activity testActivity = em2.find(Activity.class, activity.getId());
        // veryfying, that it is indeed in the database now:
        assertNotNull(testActivity);

        em.getTransaction().begin();
        activityDao.remove(activity.getId());
        em.getTransaction().commit();

        em2.clear();                    // preco nefunguje aj em2.flush (obalene v transakcii)?
        assertNull(em2.find(Activity.class, activity.getId()));
    }
}
