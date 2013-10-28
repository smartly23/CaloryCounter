/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.dto.convert;

import cz.fi.muni.pa165.calorycounter.backend.dto.CaloriesDto;
import cz.fi.muni.pa165.calorycounter.backend.model.Calories;

/**
 *
 * @author Lastuvka
 */
public class CaloriesConvert {

    public static Calories fromDtoToEntity(CaloriesDto dto) {
        Calories calories = new Calories();
        calories.setId(dto.getId());
        calories.setAmount(dto.getAmount());
        calories.setWeightCat(dto.getWeightCat());
        calories.setActivity(ActivityConvert.fromDtoToEntity(dto.getActivity()));
        return calories;
    }

    public static CaloriesDto fromEntityToDto(Calories entity) {
        CaloriesDto caloriesDto = new CaloriesDto();
        caloriesDto.setId(entity.getId());
        caloriesDto.setAmount(entity.getAmount());
        caloriesDto.setWeightCat(entity.getWeightCat());
        caloriesDto.setActivity(ActivityConvert.fromEntityToDto(entity.getActivity()));
        return caloriesDto;
    }
}
