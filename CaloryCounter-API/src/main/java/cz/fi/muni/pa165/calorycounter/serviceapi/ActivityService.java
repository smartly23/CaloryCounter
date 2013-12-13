/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.serviceapi;

import cz.fi.muni.pa165.calorycounter.serviceapi.dto.ActivityDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.WeightCategory;
import java.util.List;

/**
 * User service interface for operations on Activity DTO.
 *
 * @author Martin Bryndza (martin-bryndza)
 */
public interface ActivityService {

    /**
     * Find activity by id
     *
     * @param activityId
     * @return ActivityDto
     */
    ActivityDto get(Long activityId);

    /**
     * Find activity by name of activity
     *
     * @param activityName
     * @return ActivityDto
     */
    ActivityDto get(String activityName);

    /**
     * Find all activities with number of calories burnt per hour
     *
     * @return list of ActivityDto
     */
    List<ActivityDto> getAll();

    /**
     * Find all activities
     *
     * @param weightCategory with number of calories burnt per hour by users in
     * given weight category
     * @return list of ActivityDto
     */
    List<ActivityDto> getAll(WeightCategory weightCategory);
}
