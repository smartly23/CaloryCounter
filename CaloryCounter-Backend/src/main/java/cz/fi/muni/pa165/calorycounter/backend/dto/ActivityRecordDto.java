package cz.fi.muni.pa165.calorycounter.backend.dto;

import java.util.Date;
import java.util.Objects;

/**
 * DTO for ActivityRecord entity.
 *
 * @author Martin Pasko (smartly23)
 */
public class ActivityRecordDto {

    private Long activityRecordId;
    private Long userId;    // MUST be in every object
    private String activityName;
    private int weightCatNum;   // According to the ordering in the enum, starting from 1!!!
    private int duration; //duration unit is minute
    private Date activityDate; // na prezentacnej vrstve bude util.Date = treba konvertovat medzi DTO a DAO
    private int caloriesBurnt;

    public Long getActivityRecordId() {
        return activityRecordId;
    }

    public void setActivityRecordId(Long activityRecordId) {
        this.activityRecordId = activityRecordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getWeightCatNum() {
        return weightCatNum;
    }

    public void setWeightCatNum(int weightCatNum) {
        this.weightCatNum = weightCatNum;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public int getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(int caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    @Override
    public String toString() {
        return "ActivityRecordDto{" + "activityRecordId=" + activityRecordId + ", userId=" + userId
                + ", activityName=" + activityName + ", weightCatNum=" + weightCatNum + ", duration="
                + duration + ", activityDate=" + activityDate + ", caloriesBurnt=" + caloriesBurnt + '}';
    }
}
