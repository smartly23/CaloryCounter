package cz.fi.muni.pa165.calorycounter.backend.dto;

import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import java.util.Objects;

/**
 *
 * @author Lastuvka
 */
public class CaloriesDto {

    private Long id;
    private int amount;
    private WeightCategory weightCat;
    private ActivityDto activity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public WeightCategory getWeightCat() {
        return weightCat;
    }

    public void setWeightCat(WeightCategory weightCat) {
        this.weightCat = weightCat;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public void setActivity(ActivityDto activity) {
        this.activity = activity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final CaloriesDto other = (CaloriesDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CaloriesDto{" + "id=" + id + ", amount=" + amount + ", weightCat=" + weightCat + ", activity=" + activity + '}';
    }
    
}
