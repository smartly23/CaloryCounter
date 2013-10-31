/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.dto;

import java.util.List;

/**
 * This is used for various filtering of user stats based on his activities.
 *
 * @author Zdenek Lastuvka
 */
public class UserActivityRecordsDto {

    private String nameOfUser;
    private List<ActivityRecordDto> activityRecords;

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public List<ActivityRecordDto> getActivityRecords() {
        return activityRecords;
    }

    public void setActivityRecords(List<ActivityRecordDto> activityRecords) {
        this.activityRecords = activityRecords;
    }
}
