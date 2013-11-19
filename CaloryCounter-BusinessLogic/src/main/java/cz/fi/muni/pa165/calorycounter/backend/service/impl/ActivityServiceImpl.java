/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.service.impl;

import cz.fi.muni.pa165.calorycounter.serviceapi.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.ActivityDto;
import cz.fi.muni.pa165.calorycounter.backend.model.Activity;
import cz.fi.muni.pa165.calorycounter.backend.dao.ActivityDao;
import cz.fi.muni.pa165.calorycounter.backend.dao.CaloriesDao;
import cz.fi.muni.pa165.calorycounter.backend.dto.convert.ActivityConvert;
import cz.fi.muni.pa165.calorycounter.backend.model.Calories;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import cz.fi.muni.pa165.calorycounter.backend.service.common.DataAccessExceptionNonVoidTemplate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin Bryndza (martin-bryndza)
 */
@Service
@Transactional(readOnly = true)
public class ActivityServiceImpl implements ActivityService {

    final static Logger log = LoggerFactory.getLogger(ActivityService.class);
    private ActivityConvert convert = new ActivityConvert();
    private ActivityDao activityDao; //injected
    private CaloriesDao caloriesDao; //injected

    @Override
    public ActivityDto get(Long activityId) {
        if (activityId == null) {
            String msg = "ActivityId is null";
            IllegalArgumentException ex = new IllegalArgumentException(msg);
            log.error(msg, ex);
            throw ex;
        }
        return (ActivityDto) new DataAccessExceptionNonVoidTemplate(activityId) {
            @Override
            public ActivityDto doMethod() {
                Activity activity = activityDao.get((Long) getU());
                List<Calories> cals = new LinkedList<>();
                for (WeightCategory wc : WeightCategory.values()) {
                    Calories cal = caloriesDao.getByActivityWeightCat(activity, wc);
                    cals.add(cal);
                }
                ActivityDto dto = convert.fromEntitiesListToDto(cals);
                return dto;
}
        }.tryMethod();
    }

    @Override
    public ActivityDto get(String activityName) {
        if (activityName == null || activityName.isEmpty()) {
            String msg = "ActivityName is null or empty: \"" + activityName + "\"";
            IllegalArgumentException ex = new IllegalArgumentException(msg);
            log.error(msg, ex);
            throw ex;
        }
        return (ActivityDto) new DataAccessExceptionNonVoidTemplate(activityName) {
            @Override
            public ActivityDto doMethod() {
                Activity activity = activityDao.get((String) getU());
                List<Calories> cals = new LinkedList<>();
                for (WeightCategory wc : WeightCategory.values()) {
                    Calories cal = caloriesDao.getByActivityWeightCat(activity, wc);
                    cals.add(cal);
                }
                ActivityDto dto = convert.fromEntitiesListToDto(cals);
                return dto;
            }
        }.tryMethod();
    }

    @Override
    public List<ActivityDto> getAll() {
        List<ActivityDto> dtos = new LinkedList<>();
        List<Calories> cals = caloriesDao.getAll();
        Map<Activity, List<Calories>> calsByActivity = new HashMap<>();
        for (Calories cal : cals) {
            if (!calsByActivity.containsKey(cal.getActivity())) {
                calsByActivity.put(cal.getActivity(), new LinkedList<Calories>());
            }
            calsByActivity.get(cal.getActivity()).add(cal);
        }
        for (Activity act : calsByActivity.keySet()) {
            dtos.add(convert.fromEntitiesListToDto(calsByActivity.get(act)));
        }
        return dtos;
    }
}
