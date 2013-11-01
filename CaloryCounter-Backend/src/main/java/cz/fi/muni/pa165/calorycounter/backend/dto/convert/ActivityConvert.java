package cz.fi.muni.pa165.calorycounter.backend.dto.convert;

import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityDto;
import cz.fi.muni.pa165.calorycounter.backend.model.Activity;
import cz.fi.muni.pa165.calorycounter.backend.model.Calories;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Conversion between ActivityRecord DTO and entity back and forth.
 *
 * @author Martin Bryndza (martin-bryndza)
 */
public class ActivityConvert {

    public static Set<Calories> fromDtoToEntitiesSet(ActivityDto dto) {
        Set<Calories> cals = new HashSet<Calories>();
        for (WeightCategory wc : WeightCategory.values()) {
            cals.add(fromDtoToEntity(dto, wc));
        }
        return cals;
    }

    public static Calories fromDtoToEntity(ActivityDto dto, WeightCategory category) {
        Calories cal = new Calories();
        Activity activity = new Activity();
        activity.setName(dto.getActivityName());
        cal.setActivity(activity);
        cal.setAmount(dto.getCaloriesAmount(category));
        cal.setWeightCat(category);
        return cal;
    }

    public static ActivityDto fromEntitiesSetToDto(Set<Calories> entities) {
        ActivityDto dto = new ActivityDto();
        dto.setActivityName(entities.iterator().next().getActivity().getName());
        for (Calories cal : entities) {
            if (!cal.getActivity().getName().equals(dto.getActivityName())) {
                throw new IllegalArgumentException("Calories given are not of the same Activity. Expected " + dto.getActivityName() + "; Current " + cal.getActivity().getName());
            }
            dto.setCaloriesAmount(cal.getWeightCat(), cal.getAmount());
        }
        for (WeightCategory wc : WeightCategory.values()) {
            if (dto.getCaloriesAmount(wc) == null) {
                throw new IllegalArgumentException("Missing Calories with WeightCategory " + wc.toString());
            }
        }
        return dto;
    }
}
