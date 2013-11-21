package cz.fi.muni.pa165.calorycounter.backend.dao;

import cz.fi.muni.pa165.calorycounter.backend.dao.impl.ActivityDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.dao.impl.ActivityRecordDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.dao.impl.CaloriesDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.dao.impl.UserDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.model.Activity;
import cz.fi.muni.pa165.calorycounter.backend.model.ActivityRecord;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import cz.fi.muni.pa165.calorycounter.backend.model.Calories;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.WeightCategory;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Jan Kucera (Greld)
 */
public class ActivityRecordDaoTest {

    private EntityManager em;

    public ActivityRecordDaoTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        userDao = new UserDaoImplJPA(em);
        caloriesDao = new CaloriesDaoImplJPA(em);
        activityRecordDao = new ActivityRecordDaoImplJPA(em);
        activityDao = new ActivityDaoImplJPA(em);
    }
    private ActivityRecordDao activityRecordDao;
    private UserDao userDao;
    private CaloriesDao caloriesDao;
    private ActivityDao activityDao;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Test of CRUD methods
     */
    @Test
    public void testCRUD() {

        ActivityRecord activityRecord = new ActivityRecord();
        activityRecord.setDuration(60 * 20);
        activityRecord.setCaloriesBurnt(500);
        activityRecord.setActivityDate(new Date(System.currentTimeMillis()));

        AuthUser authUser = new AuthUser();
        authUser.setUsername("Greld2486");
        authUser.setGender("Male");
        authUser.setName("Jan Novák");
        authUser.setWeightCat(WeightCategory._130_);

        em.getTransaction().begin();
        Long userId = userDao.create(authUser);
        em.getTransaction().commit();
        assertFalse("User was not created.", (userId == null || userId.equals(new Long(0))));

        AuthUser createdUser = userDao.get(userId);
        assertNotNull("User was not received by id", createdUser);


        activityRecord.setAuthUser(createdUser);

        Calories calories = new Calories();
        Activity activity = new Activity();
        activity.setName("Plavání");
        em.getTransaction().begin();
        Long activityId = activityDao.create(activity);
        em.getTransaction().commit();
        Activity createdActivity = activityDao.get(activityId);
        assertNotNull("Activity was not received by id", createdActivity);

        calories.setActivity(createdActivity);
        calories.setAmount(150);
        calories.setWeightCat(WeightCategory._130_);
        em.getTransaction().begin();
        Long caloriesId = caloriesDao.create(calories);
        em.getTransaction().commit();
        Calories createdCalories = caloriesDao.get(caloriesId);
        assertNotNull("Calories was not received by id", createdCalories);

        activityRecord.setCalories(createdCalories);

        activityRecord.setCaloriesBurnt(createdCalories.getAmount());


        /**
         * *** Create **
         */
        em.getTransaction().begin();
        Long actRecId = activityRecordDao.create(activityRecord);
        em.getTransaction().commit();

        assertNotNull("ID is null", actRecId);
        assertFalse("ID is 0", actRecId == 0);


        /**
         * *** Get **
         */
        ActivityRecord createdActRec = activityRecordDao.get(actRecId);
        assertNotNull("ActivityRecord was not received by id", createdActRec);


        /**
         * ** Update ***
         */
        activityRecord.setId(actRecId);
        activityRecord.setDuration(60 * 10);
        em.getTransaction().begin();
        activityRecordDao.update(activityRecord);
        em.getTransaction().commit();
        ActivityRecord updatedActRec = activityRecordDao.get(actRecId);
        assertNotNull("ActivityRecord was not received by id after update", updatedActRec);
        assertEquals(60 * 10, updatedActRec.getDuration());


        /**
         * **** Remove  ****
         */
        em.getTransaction().begin();
        activityRecordDao.remove(createdActRec);
        em.getTransaction().commit();
        exception.expect(IllegalArgumentException.class);
        activityDao.get(actRecId);
        exception = ExpectedException.none();

        em.getTransaction().begin();
        userDao.remove(createdUser);
        em.getTransaction().commit();
        exception.expect(IllegalArgumentException.class);
        activityDao.get(userId);
        exception = ExpectedException.none();

        em.getTransaction().begin();
        caloriesDao.remove(createdCalories);
        em.getTransaction().commit();
        exception.expect(IllegalArgumentException.class);
        activityDao.get(caloriesId);
        exception = ExpectedException.none();

        em.getTransaction().begin();
        activityDao.remove(createdActivity);
        em.getTransaction().commit();
        exception.expect(IllegalArgumentException.class);
        activityDao.get(activityId);
        exception = ExpectedException.none();


    }
}
