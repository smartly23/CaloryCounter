/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.serviceapi;

import cz.fi.muni.pa165.calorycounter.serviceapi.dto.ActivityDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.WeightCategory;
import java.util.List;

/**
 *
 * @author Martin Bryndza (martin-bryndza)
 */
public interface ActivityService {

    ActivityDto get(Long activityId);

    ActivityDto get(String activityName);

    List<ActivityDto> getAll();

    List<ActivityDto> getAll(WeightCategory weightCategory);
}
