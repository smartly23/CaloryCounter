package cz.fi.muni.pa165.calorycounter.backend.service;

import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.CaloriesDto;
import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;

/**
 * User service interface for non-CRUD operations on Calories DTO.
 *
 * @author Martin Pasko (smartly23)
 */
public interface CaloriesService extends Service<CaloriesDto> {

    CaloriesDto getByActivityWeightCat(ActivityDto activity, WeightCategory weightCat);
}
