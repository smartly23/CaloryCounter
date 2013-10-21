package cz.fi.muni.pa165.calorycounter.backend.dto;

import cz.fi.muni.pa165.calorycounter.backend.model.Calories;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import java.util.Map;
import java.util.Objects;

/**
 * DTO for Activity entity.
 *
 * @author Martin Pasko (smartly23)
 */
public class ActivityDto {

    private String activityName;
    private Map<WeightCategory, Calories> weightCalories;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Map<WeightCategory, Calories> getWeightCalories() {
        return weightCalories;
    }

    public void setWeightCalories(Map<WeightCategory, Calories> weightCalories) {
        this.weightCalories = weightCalories;
    }

    @Override
    public String toString() {
        return "ActivityDto{" + "activityName=" + activityName + ", weightCalories=" + weightCalories + '}';
    }

}
