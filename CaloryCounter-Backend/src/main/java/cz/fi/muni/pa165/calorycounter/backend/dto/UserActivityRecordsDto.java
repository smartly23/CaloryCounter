/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.dto;

import java.util.List;

/**
 * This is used for various filtering of user stats based on his activities.
 *
 * @author xpasko
 */
public class UserActivityRecordsDto {

    private String nameOfUser;
    private List<ActivityRecordDto> activityRecords;
}
