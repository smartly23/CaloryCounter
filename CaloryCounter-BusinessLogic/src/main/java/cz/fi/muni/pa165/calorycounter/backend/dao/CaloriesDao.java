package cz.fi.muni.pa165.calorycounter.backend.dao;

import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import cz.fi.muni.pa165.calorycounter.backend.model.Calories;
import cz.fi.muni.pa165.calorycounter.backend.model.Activity;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.WeightCategory;
import java.util.List;

/**
 * DAO interface - for operations on the persistence layer on Calories entities.
 *
 * @author Jak Kucera (Greld)
 */
public interface CaloriesDao extends Dao<Calories> {

    /**
     * Finds calories for given sport activity and weight category.
     *
     * @param activity SportActivity for which we want to know number of
     * calories.
     * @param weightCat WeightCategory for which we want to know number of
     * calories.
     * @return Calories for given sport activity and weight category.
     * @throws IllegalArgumentException if parameter is null or invalid
     */
    Calories getByActivityWeightCat(Activity activity, WeightCategory weightCat);

    List<Calories> getByActivity(Activity activity);

    List<Calories> getAll();
}
