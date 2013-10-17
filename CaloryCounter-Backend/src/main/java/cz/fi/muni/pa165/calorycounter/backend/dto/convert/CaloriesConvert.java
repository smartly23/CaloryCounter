package cz.fi.muni.pa165.calorycounter.backend.dto.convert;

import cz.fi.muni.pa165.calorycounter.backend.dto.CaloriesDto;
import cz.fi.muni.pa165.calorycounter.backend.model.Calories;

/**
 * Conversion between ActivityRecord DTO and entity back and forth.
 *
 * @author
 */
public class CaloriesConvert implements Convert<Calories, CaloriesDto> {

    @Override
    public Calories fromDtoToEntity(CaloriesDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CaloriesDto fromEntityToDto(Calories entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
