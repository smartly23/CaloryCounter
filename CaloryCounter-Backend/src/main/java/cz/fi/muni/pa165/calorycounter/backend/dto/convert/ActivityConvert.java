package cz.fi.muni.pa165.calorycounter.backend.dto.convert;

import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityDto;
import cz.fi.muni.pa165.calorycounter.backend.model.Activity;
import javax.persistence.EntityManager;

/**
 * Conversion between ActivityRecord DTO and entity back and forth.
 *
 * @author
 */
public class ActivityConvert implements Convert<Activity, ActivityDto> {

    @Override
    public Activity fromDtoToEntity(ActivityDto dto, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ActivityDto fromEntityToDto(Activity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
