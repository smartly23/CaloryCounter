/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.service.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.UserStatsDao;
import cz.fi.muni.pa165.calorycounter.backend.dao.impl.UserStatsDaoImplJPA.UserStats;
import cz.fi.muni.pa165.calorycounter.backend.dto.UserStatsDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.convert.ActivityRecordConvert;
import cz.fi.muni.pa165.calorycounter.backend.service.UserStatsService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User service for all operations on UserStats DTO.
 *
 * @author Jan Kucera (Greld)
 */
@Service
@Transactional(readOnly = true)
public class UserStatsServiceImpl implements UserStatsService {
    final static Logger log = LoggerFactory.getLogger(ActivityRecordConvert.class);
    // concrete implementation injected by setter from Spring
    private UserStatsDao userStatsDao;

   
    @Override
    public List<UserStatsDto> getAll() {
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


    public void setUserStatsDao(UserStatsDao userStatsDao) {
        this.userStatsDao = userStatsDao;
    }



}
