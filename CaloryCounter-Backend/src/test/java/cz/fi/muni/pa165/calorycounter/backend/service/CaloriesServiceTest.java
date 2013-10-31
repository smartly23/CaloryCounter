/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.service;

import cz.fi.muni.pa165.calorycounter.backend.dao.impl.CaloriesDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityDto;
//import cz.fi.muni.pa165.calorycounter.backend.dto.CaloriesDto;
import cz.fi.muni.pa165.calorycounter.backend.model.Activity;
import cz.fi.muni.pa165.calorycounter.backend.model.Calories;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import cz.fi.muni.pa165.calorycounter.backend.service.impl.ActivityServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Lastuvka
 */
@RunWith(MockitoJUnitRunner.class)
public class CaloriesServiceTest {
    /*
     @InjectMocks
     private ActivityService caloriesService = new ActivityServiceImpl();
     @Mock
     CaloriesDaoImplJPA caloriesDaoImplJPA;
     CaloriesDto caloriesDto;
     ActivityDto activityDto;
     Calories calories;
     Activity activity;

     public CaloriesServiceTest() {
     }

     @BeforeClass
     public static void setUpClass() {
     }

     @AfterClass
     public static void tearDownClass() {
     }

     @Before
     public void setUp() {
     activity = new Activity();
     activity.setName("Plavání");

     activityDto = new ActivityDto();
     activityDto.setActivityName("Plavání");

     caloriesDto = new CaloriesDto();
     caloriesDto.setId(1L);
     caloriesDto.setAmount(10);
     caloriesDto.setWeightCat(WeightCategory._130_);
     caloriesDto.setActivity(activityDto);

     calories = new Calories();
     calories.setId(1L);
     calories.setAmount(10);
     calories.setWeightCat(WeightCategory._130_);
     calories.setActivity(activity);
     }

     @After
     public void tearDown() {
     }
     */

    @Test
    public void testCreate() {
        /*
         caloriesService.create(caloriesDto);

         ArgumentCaptor<Calories> argument = ArgumentCaptor.forClass(Calories.class);
         Mockito.verify(caloriesDaoImplJPA).create(argument.capture());
         assertTrue("IDs are not same", calories.getId() == argument.getValue().getId());
         assertEquals("diffrent weight category", calories.getWeightCat(), argument.getValue().getWeightCat());

         try {
         caloriesService.create(null);
         fail("caloriesService.create with null dont throw exception");
         } catch (IllegalArgumentException e) {
         }
         * */
    }
    /*
     @Test
     public void testGet() {

     Mockito.stub(caloriesDaoImplJPA.get(1L)).toReturn(calories);
     CaloriesDto caloriesDto2 = caloriesService.get(1l);
     assertTrue("ID is not equals", caloriesDto2.getId() == caloriesDto.getId());
     assertEquals("diffrent weight category", caloriesDto2.getWeightCat(), caloriesDto.getWeightCat());
     try {
     caloriesService.get(null);
     fail("caloriesService.get with null dont throw exception");
     } catch (IllegalArgumentException e) {
     }
     }

     @Test
     public void testUpdate() {
     caloriesDto.setAmount(50);

     caloriesService.update(caloriesDto);

     ArgumentCaptor<Calories> argument = ArgumentCaptor.forClass(Calories.class);
     Mockito.verify(caloriesDaoImplJPA).update(argument.capture());
     assertTrue("IDs are not same", caloriesDto.getId() == argument.getValue().getId());
     assertEquals("diffrent amount category", caloriesDto.getAmount(), argument.getValue().getAmount());
     try {
     caloriesService.update(null);
     fail("caloriesService.update with null dont throw exception");
     } catch (IllegalArgumentException e) {
     }
     }

     @Test
     public void testRemove() {

     caloriesService.remove(caloriesDto);

     ArgumentCaptor<Calories> argument = ArgumentCaptor.forClass(Calories.class);
     Mockito.verify(caloriesDaoImplJPA).remove(argument.capture());
     assertTrue("IDs are not same", caloriesDto.getId() == argument.getValue().getId());
     assertEquals("diffrent amount category", caloriesDto.getAmount(), argument.getValue().getAmount());
     try {
     caloriesService.remove(null);
     fail("caloriesService.remove with null dont throw exception");
     } catch (IllegalArgumentException e) {
     }
     }

     */
}
