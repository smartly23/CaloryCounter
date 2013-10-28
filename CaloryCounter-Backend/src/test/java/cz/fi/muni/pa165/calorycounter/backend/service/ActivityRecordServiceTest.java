package cz.fi.muni.pa165.calorycounter.backend.service;

import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityRecordDto;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit Tests using Mockito to mock DAO objects.
 *
 * @author Martin Pasko (smartly23)
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//    "classpath:applicationContext.xml"})
public class ActivityRecordServiceTest {

    private static AuthUser user;
    //@Autowired
    private static ActivityRecordService activityRecordService;
    
    @BeforeClass
    public static void setUpOnce() {
        user = mock(AuthUser.class);
        user.setAge(35);
        user.setGender("female");
        user.setId(new Long(1));
        user.setName("Edita Papeky");
        user.setWeightCat(WeightCategory._180_);
        
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        activityRecordService = (ActivityRecordService) context.getBean("activityRecordService");
    }

    @AfterClass
    public static void tearDownOnce() {
    }

    @Before
    public void setUp() {  
    }

    @After
    public void tearDown() {
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
        Long id = activityRecordService.create(dto);
        assertNotNull(id);
        System.out.println("Id is: "+id);
    }
    
    @Test
    public void testGet() {}
    
    @Test
    public void testUpdate() {}
    
    @Test
    public void testRemove() {}
}
