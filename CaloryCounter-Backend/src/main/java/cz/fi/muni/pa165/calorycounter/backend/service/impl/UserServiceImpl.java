/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.service.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.UserDao;
import cz.fi.muni.pa165.calorycounter.backend.dao.UserStatsDao;
import cz.fi.muni.pa165.calorycounter.backend.dao.impl.UserStatsDaoImplJPA.UserStats;
import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityRecordDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.UserStatsDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.convert.ActivityRecordConvert;
import cz.fi.muni.pa165.calorycounter.backend.dto.convert.AuthUserConvert;
import cz.fi.muni.pa165.calorycounter.backend.model.ActivityRecord;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import cz.fi.muni.pa165.calorycounter.backend.service.UserService;
import cz.fi.muni.pa165.calorycounter.backend.service.common.DataAccessExceptionNonVoidTemplate;
import static cz.fi.muni.pa165.calorycounter.backend.service.impl.ActivityRecordServiceImpl.log;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User service for all operations on UserStats DTO.
 *
 * @author Jan Kucera (Greld)
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {DataAccessException.class})
public class UserServiceImpl implements UserService {
    final static Logger log = LoggerFactory.getLogger(ActivityRecordConvert.class);
    // concrete implementation injected by setter from Spring
    private UserStatsDao userStatsDao;
    private UserDao userDao;

   
    @Override
    public List<UserStatsDto> getAllUserStats() {
        return (List<UserStatsDto>) new DataAccessExceptionNonVoidTemplate(null) {
            @Override
            public List<UserStatsDto> doMethod() {
                List<UserStats> daos = userStatsDao.getUsersStats();
                List<UserStatsDto> dtos = new ArrayList<>();
                for (UserStats dao : daos) {
                    UserStatsDto dto = new UserStatsDto();
                    dto.setNameOfUser(dao.getUserName());
                    dto.setSumBurntCalories(dao.getSumBurntCalories());
                    dto.setSumDuration(dao.getSumDuration());
                }
                return dtos;
            }
        }.tryMethod();
    }


    @Override
    public AuthUserDto login(String username, String password) {
        if (username == null || password == null) {
            IllegalArgumentException iaex = new IllegalArgumentException("Invalid username or password in parameter: null");
            log.error("UserServiceImpl.login() called on null parameter: String username or String password", iaex);
            throw iaex;
        }
        return (AuthUserDto) new DataAccessExceptionNonVoidTemplate(username, password) {
            @Override
            public AuthUserDto doMethod() {
                AuthUser entity = userDao.login((String) getU(), (String) getV());
                AuthUserDto dto = AuthUserConvert.fromEntityToDto(entity);
                return dto;
            }
        }.tryMethod();
    }

    @Override
    public Long register(AuthUserDto user, String username, String password) {
        if (user == null || username == null || password == null) {
            IllegalArgumentException iaex = new IllegalArgumentException("Invalid user or username or password in parameter: null");
            log.error("UserServiceImpl.register() called on null parameter: AuthUserDto user or String username or String password", iaex);
            throw iaex;
        }
        
        AuthUserDto userDto = getByUsername(username);
        if (userDto != null){
            throw new IllegalArgumentException("Username is allready used");
        }
        
        AuthUser entity = AuthUserConvert.fromDtoToEntity(user);
        entity.setUsername(username);
        entity.setPassword(password);
        return (Long) new DataAccessExceptionNonVoidTemplate(entity) {
            @Override
            public Long doMethod() {
                Long entityId = userDao.create((AuthUser) getU());
                return entityId;
            }
        }.tryMethod();
    }
    
    @Override
    public AuthUserDto getByUsername(String username) {
        if (username == null) {
            IllegalArgumentException iaex = new IllegalArgumentException("Invalid username in parameter: null");
            log.error("UserServiceImpl.getByUsername() called on null parameter: String username", iaex);
            throw iaex;
        }
        return (AuthUserDto) new DataAccessExceptionNonVoidTemplate(username) {
            @Override
            public AuthUserDto doMethod() {
                AuthUser entity = userDao.getByUsername((String) getU());
                AuthUserDto dto = AuthUserConvert.fromEntityToDto(entity);
                return dto;
            }
        }.tryMethod();
    }

    public void setUserStatsDao(UserStatsDao userStatsDao) {
        this.userStatsDao = userStatsDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


}
