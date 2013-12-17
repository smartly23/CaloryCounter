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

    private EntityManager context;

    public ActivityRecordDaoTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        context = emf.createEntityManager();
        userDao = new UserDaoImplJPA(context);
        caloriesDao = new CaloriesDaoImplJPA(context);
        activityRecordDao = new ActivityRecordDaoImplJPA(context);
        activityDao = new ActivityDaoImplJPA(context);
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

        context.getTransaction().begin();
        Long userId = userDao.create(authUser);
        context.getTransaction().commit();
        assertFalse("User was not created.", (userId == null || userId.equals(new Long(0))));

        AuthUser createdUser = userDao.get(userId);
        assertNotNull("User was not received by id", createdUser);

        activityRecord.setAuthUser(createdUser);

        Calories calories = new Calories();
        Activity activity = new Activity();
        activity.setName("Plavání");
        context.getTransaction().begin();
        Long activityId = activityDao.create(activity);
        context.getTransaction().commit();
        Activity createdActivity = activityDao.get(activityId);
        assertNotNull("Activity was not received by id", createdActivity);

        calories.setActivity(createdActivity);
        calories.setAmount(150);
        calories.setWeightCat(WeightCategory._130_);
        context.getTransaction().begin();
        Long caloriesId = caloriesDao.create(calories);
        context.getTransaction().commit();
        Calories createdCalories = caloriesDao.get(caloriesId);
        assertNotNull("Calories was not received by id", createdCalories);

        activityRecord.setCalories(createdCalories);

        activityRecord.setCaloriesBurnt(createdCalories.getAmount());

        /**
         * *** Create **
         */
        context.getTransaction().begin();
        Long actRecId = activityRecordDao.create(activityRecord);
        context.getTransaction().commit();

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
        context.getTransaction().begin();
        activityRecordDao.update(activityRecord);
        context.getTransaction().commit();
        ActivityRecord updatedActRec = activityRecordDao.get(actRecId);
        assertNotNull("ActivityRecord was not received by id after update", updatedActRec);
        assertEquals(60 * 10, updatedActRec.getDuration());

        /**
         * **** Remove ****
         */
        context.getTransaction().begin();
        activityRecordDao.remove(createdActRec.getId());
        context.getTransaction().commit();
        exception.expect(IllegalArgumentException.class);
        activityDao.get(actRecId);
        exception = ExpectedException.none();

        context.getTransaction().begin();
        userDao.remove(createdUser.getId());
        context.getTransaction().commit();
        exception.expect(IllegalArgumentException.class);
        activityDao.get(userId);
        exception = ExpectedException.none();

        context.getTransaction().begin();
        caloriesDao.remove(createdCalories.getId());
        context.getTransaction().commit();
        exception.expect(IllegalArgumentException.class);
        activityDao.get(caloriesId);
        exception = ExpectedException.none();

        context.getTransaction().begin();
        activityDao.remove(createdActivity.getId());
        context.getTransaction().commit();
        exception.expect(IllegalArgumentException.class);
        activityDao.get(activityId);
        exception = ExpectedException.none();

    }
}
