package cz.fi.muni.pa165.calorycounter.backend.service;

import cz.fi.muni.pa165.calorycounter.backend.dao.ActivityRecordDao;
import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityRecordDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.convert.ActivityRecordConvert;
import cz.fi.muni.pa165.calorycounter.backend.model.Activity;
import cz.fi.muni.pa165.calorycounter.backend.model.ActivityRecord;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import cz.fi.muni.pa165.calorycounter.backend.model.Calories;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit Tests using Mockito to mock DAO layer and thus avoid real DB operations.
 *
 * @author Martin Pasko (smartly23)
 */
@RunWith(MockitoJUnitRunner.class) // aby sme nemuseli v SetUp pisat MockitoAnnotations.initMocks( this );
// vid http://eclipsesource.com/blogs/2011/09/29/effective-mockito-part-2/
public class ActivityRecordServiceTest {

    private static ApplicationContext context;
    @InjectMocks
    private static ActivityRecordService activityRecordService;
    @Mock
    private ActivityRecordDao activityRecordDao;
    @Mock
    private ActivityRecordConvert activityRecordConvertMock;
    @Mock
    private Calories caloriesMock;
    private AuthUser user;
    private ActivityRecordDto activityRecordDto;
    private ActivityRecord activityRecord;
    private final Long ACTIVITY_RECORD_ID = 42L;
    private final Long USER_ID = 237L;

    @BeforeClass
    public static void setUpOnce() {
        context = new ClassPathXmlApplicationContext(
                "testContext.xml");
        activityRecordService = (ActivityRecordService) context.getBean("activityRecordService");
    }

    @AfterClass
    public static void tearDownOnce() {
        activityRecordService = null;
    }

    @Before
    public void setUp() {
        Long time = new java.util.Date().getTime();

        user = new AuthUser();
        user.setId(USER_ID);
        user.setAge(35);
        user.setGender("female");
        user.setName("Edita Papeky");
        user.setWeightCat(WeightCategory._180_);
        user.setUsername("Petra");

        activityRecord = new ActivityRecord();
        activityRecord.setActivityDate(new java.sql.Date(time));
        activityRecord.setDuration(40);
        activityRecord.setCaloriesBurnt(4000);
        activityRecord.setCalories(caloriesMock);
        activityRecord.setAuthUser(user);

        activityRecordDto = new ActivityRecordDto();
        activityRecordDto.setActivityName("Strielanie zajacov.");
        activityRecordDto.setActivityDate(new java.util.Date(time));
        activityRecordDto.setCaloriesBurnt(4000);
        activityRecordDto.setDuration(40);
        activityRecordDto.setWeightCatNum(3);
        activityRecordDto.setUserId(USER_ID);
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void testCreate() {
        activityRecordDto.setActivityRecordId(null);    // must be null if new entity is to be created
        activityRecord.setId(null);     // returned after conversion from DTO

        when(activityRecordDao.create(activityRecord)).thenReturn(ACTIVITY_RECORD_ID);
        when(activityRecordConvertMock.fromDtoToEntity(activityRecordDto)).thenReturn(activityRecord);

        Long returnedId = activityRecordService.create(activityRecordDto);

        ArgumentCaptor<ActivityRecord> argument = ArgumentCaptor.forClass(ActivityRecord.class);
        verify(activityRecordDao).create(argument.capture());
        assertTrue("Service layer sent to DAO an entity with different Id than expected. Expected Id: null, "
                + "sent Id: " + argument.getValue().getId() + ".", argument.getValue().getId() == null);

        activityRecord.setId(ACTIVITY_RECORD_ID);   // set by DB after DAO create() call
        assertEquals("Returned Id and expected Id are inconsistent.", activityRecord.getId(), returnedId);
    }

    @Test
    public void testGet() {

        // oroginal test, which didn't compile because of a known Mockito bug (on line 125):
        // http://code.google.com/p/mockito/issues/detail?id=53

        /*
         ActivityRecordConvert activityRecordConvert = (ActivityRecordConvert) context.getBean("activityRecordConvert");
         activityRecord.setId(ACTIVITY_RECORD_ID);
         activityRecordDto.setActivityRecordId(ACTIVITY_RECORD_ID);
         when(activityRecordDao.get(ACTIVITY_RECORD_ID)).thenReturn(activityRecord);
         when(activityRecordConvertMock.fromEntityToDto(activityRecord)).thenReturn(activityRecordConvert.fromEntityToDto(activityRecord));
         when(caloriesMock.getWeightCat()).thenReturn(WeightCategory._180_); // activityRecord contains caloriesMock
         // stub(caloriesMock.getWeightCat()).toReturn(WeightCategory._180_);   // deprecated
         Activity activity = new Activity();
         activity.setName("Strielanie zajacov.");
         when(caloriesMock.getActivity()).thenReturn(activity);
         * */

        // new trivial test:

        activityRecord.setId(ACTIVITY_RECORD_ID);
        activityRecordDto.setActivityRecordId(ACTIVITY_RECORD_ID);
        when(activityRecordDao.get(ACTIVITY_RECORD_ID)).thenReturn(activityRecord);
        // stub(activityRecordDao.get(ACTIVITY_RECORD_ID)).toReturn(activityRecord);   // deprecated
        when(activityRecordConvertMock.fromEntityToDto(activityRecord)).thenReturn(activityRecordDto);

        ActivityRecordDto comparedDto = activityRecordService.get(activityRecord.getId());

        assertEquals("Get method does not return expected DTO", comparedDto, activityRecordDto);
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testRemove() {
    }
}
