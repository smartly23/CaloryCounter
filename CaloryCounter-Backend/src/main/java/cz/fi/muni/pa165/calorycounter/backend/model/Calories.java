package cz.fi.muni.pa165.calorycounter.backend.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * This entity represents activity with calories for specific weigth category.
 *
 * @author Jak Kucera (Greld)
 */

@Entity
public class Calories implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int amount;
    
    @Enumerated(EnumType.STRING)
    private WeightCategory weightCat;
    
    @ManyToOne
    private Activity activity;

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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Calories other = (Calories) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Calories{ amount=" + amount + ", weightCat=" + weightCat + ", activity=" + activity + '}';
    }
    
    
    
}
