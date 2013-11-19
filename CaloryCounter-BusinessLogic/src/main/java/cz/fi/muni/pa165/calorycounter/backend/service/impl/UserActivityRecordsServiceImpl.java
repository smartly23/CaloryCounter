/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.service.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.ActivityRecordDao;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.UserActivityRecordsDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.convert.ActivityRecordConvert;
import cz.fi.muni.pa165.calorycounter.backend.dto.convert.AuthUserConvert;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import cz.fi.muni.pa165.calorycounter.serviceapi.UserActivityRecordsService;
import org.springframework.dao.RecoverableDataAccessException;

/**
 *
 * @author Zdenek Lastuvka
 */
public class UserActivityRecordsServiceImpl implements UserActivityRecordsService {

    ActivityRecordConvert activityRecordConvert = new ActivityRecordConvert();
    private ActivityRecordDao activityRecordDao;

    public void setActivityRecordDao(ActivityRecordDao activityRecordDao) {
        this.activityRecordDao = activityRecordDao;
    }

    @Override
    public UserActivityRecordsDto getAllActivityRecords(AuthUserDto authUserDto) {
        if (authUserDto == null) {
            throw new IllegalArgumentException("authUserDto is null");
        }
        AuthUser authUser = AuthUserConvert.fromDtoToEntity(authUserDto);
        if (authUser.getName() == null) {
            throw new IllegalArgumentException("authUser's name is null.");
        }
        UserActivityRecordsDto uard = new UserActivityRecordsDto();
        uard.setNameOfUser(authUser.getName());
        try {
            uard.setActivityRecords(activityRecordConvert.fromEntityToDto(activityRecordDao.getAllActivityRecordsByUser(authUser)));
        } catch (Exception ex) {
            throw new RecoverableDataAccessException("Operation 'create' failed." + ex.getMessage(), ex);
        }
        return uard;
    }
}