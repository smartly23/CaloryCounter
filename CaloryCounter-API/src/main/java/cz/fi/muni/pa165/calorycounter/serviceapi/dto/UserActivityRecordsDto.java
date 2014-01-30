/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.serviceapi.dto;

import java.util.Collections;
import java.util.Date;
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
        return Collections.unmodifiableList(activityRecords);
    }

    public void setActivityRecords(List<ActivityRecordDto> activityRecords) {
        this.activityRecords = activityRecords;
    }

    public int getSumOfCalories() {
        int result = 0;
        for (ActivityRecordDto record : activityRecords) {
            result += record.getCaloriesBurnt();
        }
        return result;
    }

    public long getBurntPerDay() {
        Date firstDay = new Date();
        firstDay.setTime(System.currentTimeMillis());
        int sum = 0;
        for (ActivityRecordDto record : activityRecords) {
            if (record.getActivityDate().before(firstDay)) {
                firstDay = record.getActivityDate();
            }
            sum += record.getCaloriesBurnt();
        }
        if (sum <= 0) {
            return 0;
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(firstDay.getTime());
        System.out.println(sum);
        System.out.println((System.currentTimeMillis() - firstDay.getTime()) / (24 * 60 * 60 * 1000d));
        double days = (System.currentTimeMillis() - firstDay.getTime()) / (24 * 60 * 60 * 1000d);
        return Math.round(sum / days);
    }
}
