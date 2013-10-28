package cz.fi.muni.pa165.calorycounter.backend.dto.convert;

import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityDto;
import cz.fi.muni.pa165.calorycounter.backend.model.Activity;

/**
 * Conversion between ActivityRecord DTO and entity back and forth.
 *
 * @author
 */
public class ActivityConvert {

    public static Activity fromDtoToEntity(ActivityDto dto) {
        Activity activity = new Activity();
        activity.setName(dto.getActivityName());
        return activity;
    }

    public static ActivityDto fromEntityToDto(Activity entity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityName(entity.getName());
        return activityDto;
    }
}
