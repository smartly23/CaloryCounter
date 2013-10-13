//package cz.fi.muni.pa165.calorycounter.backend.dao;
//
//import cz.fi.muni.pa165.calorycounter.backend.dao.impl.CaloriesDaoImplJPA;
//import cz.fi.muni.pa165.calorycounter.backend.model.Activity;
//import cz.fi.muni.pa165.calorycounter.backend.model.Calories;
//import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import static org.mockito.Mockito.*;
//
///**
// * Unit Tests on CaloriesDaoImplJPA class using in-memory database and JPA
// * implementation to avoid mutual dependence among tested methods.
// *
// * @author Zdenek Lastuvka
// */
//public class CaloriesDaoTest {
//
//    private static EntityManagerFactory emf;
//    private EntityManager em;
//    private CaloriesDao caloriesDao;
//    final static Logger log = LoggerFactory.getLogger(ActivityDaoTest.class);
//
//    public CaloriesDaoTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//        emf = Persistence.createEntityManagerFactory("PU1");
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//        if (emf != null) {
//            emf.close();
//        }
//    }
//
//    @Before
//    public void setUp() {
//        em = emf.createEntityManager();
//        caloriesDao = new CaloriesDaoImplJPA(em);
//    }
//
//    @After
//    public void tearDown() {
//        em.close();
//        caloriesDao = null;
//    }
//
//    @Test
//    public void testCreate() {
//        Activity activity = mock(Activity.class);
//
//        Calories calories = new Calories();
//        calories.setWeightCat(WeightCategory._130_);
//        calories.setAmount(150);
//        calories.setActivity(activity);
//
//        em.getTransaction().begin();
//        Long caloriesId = caloriesDao.create(calories);
//        em.getTransaction().commit();
//        assertFalse("Calories was not created.", caloriesId == null);
//
//        try {
//            caloriesDao.create(null);
//            fail("No exception thrown with null argument");
//        } catch (IllegalArgumentException iae) {
//        }
//
//    }
//
//    @Test
//    public void testGet() {
//        Activity activity = mock(Activity.class);
//
//        Calories calories = new Calories();
//        calories.setWeightCat(WeightCategory._130_);
//        calories.setAmount(150);
//        calories.setActivity(activity);
//
//        Long caloriesId;
//        try {
//            em.getTransaction().begin();
//            em.persist(calories);
//            em.getTransaction().commit();
//            caloriesId = calories.getId();
//        } catch (Exception ex) {
//            throw new RuntimeException("internal integrity error", ex);
//        }
//        assertNotNull("ID is null", caloriesId);
//        assertFalse("ID is 0", caloriesId == 0);
//
//        Calories calories2 = caloriesDao.get(caloriesId);
//        assertNotNull("Calories was not received by id", calories2);
//    }
//
//    @Test
//    public void testUpdate() {
//        Activity activity = mock(Activity.class);
//
//        Calories calories = new Calories();
//        calories.setWeightCat(WeightCategory._130_);
//        calories.setAmount(150);
//        calories.setActivity(activity);
//
//        Long caloriesId;
//        try {
//            em.getTransaction().begin();
//            em.persist(calories);
//            em.getTransaction().commit();
//            caloriesId = activity.getId();
//        } catch (Exception ex) {
//            throw new RuntimeException("internal integrity error", ex);
//        }
//        assertNotNull("internal integrity error", caloriesId);
//
//        calories.setWeightCat(WeightCategory._205_);
//        calories.setAmount(50);
//
//        em.getTransaction().begin();
//        caloriesDao.update(calories);
//        em.getTransaction().commit();
//
//        em.clear();
//
//        Calories calories2 = em.find(Calories.class, caloriesId);
//
//        assertEquals(calories2.getAmount(), calories.getAmount());
//
//        calories.setId(caloriesId + 1);
//        try {
//            em.getTransaction().begin();
//            caloriesDao.update(calories);
//            em.getTransaction().commit();
//            fail("Should throw exception when non-existent id is entered.");
//        } catch (IllegalArgumentException iae) {
//            try {
//                caloriesDao.update(null);
//                fail("Should throw exception when null argument is entered.");
//            } catch (IllegalArgumentException iaex) {
//            }
//        }
//
//    }
//
//    @Test
//    public void testRemove() {
//        Activity activity = mock(Activity.class);
//
//        Calories calories = new Calories();
//        calories.setWeightCat(WeightCategory._130_);
//        calories.setAmount(150);
//        calories.setActivity(activity);
//
//        em.getTransaction().begin();
//        em.persist(calories);
//        em.getTransaction().commit();
//
//        em.clear();
//
//        Calories calories2 = em.find(Calories.class, calories.getId());
//        // veryfying, that it is indeed in the database now:
//        assertNotNull(calories2);
//
//        em.getTransaction().begin();
//        caloriesDao.remove(calories2);
//        em.getTransaction().commit();
//
//        em.clear();
//        assertNull(em.find(Calories.class, calories.getId()));
//    }
//
//    /**
//     * Test of getByActivityWeightCat method, of class CaloriesDao.
//     */
//    @Test
//    public void testGetByActivityWeightCat() {
//        Activity activity = mock(Activity.class);
//
//        Calories calories = new Calories();
//        calories.setWeightCat(WeightCategory._130_);
//        calories.setAmount(150);
//        calories.setActivity(activity);
//
//        Long caloriesId;
//        try {
//            em.getTransaction().begin();
//            em.persist(calories);
//            em.getTransaction().commit();
//            caloriesId = calories.getId();
//        } catch (Exception ex) {
//            throw new RuntimeException("internal integrity error", ex);
//        }
//        assertNotNull("ID is null", caloriesId);
//        assertFalse("ID is 0", caloriesId == 0);
//
//        Calories calories2 = caloriesDao.getByActivityWeightCat(activity, WeightCategory._130_);
//        assertNotNull("Calories was not received by act. and WeightCategory", calories2);
//
//        assertEquals(calories, calories2);
//
//
//        try {
//            caloriesDao.getByActivityWeightCat(null, null);
//            fail("Should throw exception when null argument is entered.");
//        } catch (IllegalArgumentException iaex) {
//        }
//
//    }
//}