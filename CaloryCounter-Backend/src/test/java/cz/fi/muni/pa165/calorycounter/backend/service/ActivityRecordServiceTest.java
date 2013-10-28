package cz.fi.muni.pa165.calorycounter.backend.service;

import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityRecordDto;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
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
import org.springframework.transaction.annotation.Propagation;

/**
 * Unit Tests using Mockito to mock DAO objects.
 *
 * @author Martin Pasko (smartly23)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ActivityRecordServiceTest {

    private static AuthUser user;
    private static ActivityRecordService activityRecordService;
    @PersistenceContext
    private EntityManager em;
    
    @BeforeClass
    public static void setUpOnce() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        activityRecordService = (ActivityRecordService) context.getBean("activityRecordService");
    }

    @AfterClass
    public static void tearDownOnce() {
    }

    @Before
    public void setUp() {
        user = new AuthUser();
        user.setAge(35);
        user.setGender("female");
        user.setName("Edita Papeky");
        user.setWeightCat(WeightCategory._180_);
        em.persist(user);
    }

    @After
    public void tearDown() {
        //if (em != null) {
        //    em.close();
        //}
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
        dto.setUserId(user.getId());
        
        // test create():
        //Long id = activityRecordService.create(dto);
        Long id = temp(dto);
        assertNotNull(id);
        System.out.println("Id is: "+id);
    }
    
    @Test
    public void testGet() {}
    
    @Test
    public void testUpdate() {}
    
    @Test
    public void testRemove() {}
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private Long temp(ActivityRecordDto dto) {
        Long id = activityRecordService.create(dto);
        return id;
    }
}
