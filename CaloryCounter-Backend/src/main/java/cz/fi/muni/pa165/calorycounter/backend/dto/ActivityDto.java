package cz.fi.muni.pa165.calorycounter.backend.dto;

import cz.fi.muni.pa165.calorycounter.backend.model.Calories;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import java.util.Map;
import java.util.Objects;

/**
 * DTO for Activity entity.
 *
 * @author Martin Bryndza (martin-bryndza)
 */
public class ActivityDto {

    private String activityName;
    private Map<WeightCategory, Integer> weightCalories;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getCaloriesAmount(WeightCategory weightCategory) {
        return weightCalories.get(weightCategory);
    }

    public void setCaloriesAmount(WeightCategory weightCategory, Integer caloriesAmount) {
        if (caloriesAmount == null || caloriesAmount.compareTo(0) < 0) {
            throw new IllegalArgumentException("Amount of calories cannot be null or less than zaro. Is " + caloriesAmount);
        }
        this.weightCalories.put(weightCategory, caloriesAmount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ActivityDto{activityName=").append(activityName);
        sb.append(" [");
        for (WeightCategory wc : weightCalories.keySet()) {
            sb.append(wc.toString()).append(":").append(weightCalories.get(wc)).append("; ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
