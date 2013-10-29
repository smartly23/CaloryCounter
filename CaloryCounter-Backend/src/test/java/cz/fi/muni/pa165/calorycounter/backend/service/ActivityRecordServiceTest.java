package cz.fi.muni.pa165.calorycounter.backend.service;

import cz.fi.muni.pa165.calorycounter.backend.dao.UserDao;
import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityRecordDto;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import cz.fi.muni.pa165.calorycounter.backend.service.impl.ActivityRecordServiceImpl;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Unit Tests using Mockito to mock DAO objects.
 *
 * @author Martin Pasko (smartly23)
 */
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ActivityRecordServiceTest {

    private static AuthUser user;
    private static ActivityRecordService activityRecordService;
    private static UserDao userDao;

    @BeforeClass
    public static void setUpOnce() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        activityRecordService = (ActivityRecordService) context.getBean("activityRecordService");
        userDao = (UserDao) context.getBean("userDaoJPA");
    }

    @AfterClass
    public static void tearDownOnce() {
        activityRecordService = null;
        userDao = null;
    }

    @Before
    public void setUp() {
        user = new AuthUser();
        user.setAge(35);
        user.setGender("female");
        user.setName("Edita Papeky");
        user.setWeightCat(WeightCategory._180_);
        user.setUsername("Petra");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("userDao: " + userDao);
        System.out.println("user: " + user);
        userDao.create(user);
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void testCreate() {
        // prepare DTO:
        ActivityRecordDto dto = new ActivityRecordDto();
        dto.setActivityDate(new Date());
        dto.setActivityName("strielanie zajacov");
        dto.setDuration(40);
        dto.setCaloriesBurnt(1000);
        dto.setWeightCatNum(2);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("user id: " + user.getId());
        dto.setUserId(user.getId());

        // test create():
        Long id = activityRecordService.create(dto);
        assertNotNull(id);
        System.out.println("Id is: " + id);
    }

    @Test
    public void testGet() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testRemove() {
    }
}
