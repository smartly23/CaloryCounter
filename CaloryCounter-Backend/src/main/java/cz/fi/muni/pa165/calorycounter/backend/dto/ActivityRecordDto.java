package cz.fi.muni.pa165.calorycounter.backend.dto;

import java.util.Date;
import java.util.Objects;

/**
 * DTO for ActivityRecord entity.
 *
 * @author Martin Pasko (smartly23)
 */
public class ActivityRecordDto {

    private Long id;
    private Long activityRecordId; // null if it was first created
    private int duration; //duration unit is minute
    private Date activityDate; // na prezentacnej vrstve bude util.Date = treba konvertovat medzi DTO a DAO
    private int caloriesBurnt;
    private AuthUserDto authUser;
    private CaloriesDto calories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AuthUserDto getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUserDto authUser) {
        this.authUser = authUser;
    }

    public CaloriesDto getCalories() {
        return calories;
    }

    public void setCalories(CaloriesDto calories) {
        this.calories = calories;
    }

    public Long getActivityRecordId() {
        return activityRecordId;
    }

    public void setActivityRecordId(Long activityRecordId) {
        this.activityRecordId = activityRecordId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActivityRecordDto other = (ActivityRecordDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ActivityRecordDto{" + "duration=" + duration + ", activityDate=" + activityDate + ", caloriesBurnt="
                + caloriesBurnt + ", authUser=" + authUser + ", calories=" + calories + '}';
    }
}
