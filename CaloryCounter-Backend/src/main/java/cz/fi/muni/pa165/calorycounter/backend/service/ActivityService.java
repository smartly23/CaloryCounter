/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.service;

import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityDto;
import java.util.List;

/**
 *
 * @author Martin Bryndza (martin-bryndza)
 */
public interface ActivityService {

    ActivityDto get(Long activityId);

    ActivityDto get(String activityName);

    List<ActivityDto> getAll();
}
