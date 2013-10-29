/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.service.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.CaloriesDao;
import cz.fi.muni.pa165.calorycounter.backend.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.fi.muni.pa165.calorycounter.backend.dto.ActivityDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.convert.ActivityConvert;

/**
 *
 * @author Lastuvka
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    private CaloriesDao caloriesDao;

    @Transactional
    @Override
    public Long create(ActivityDto dto) {
        return null;

    }

    @Transactional
    @Override
    public ActivityDto get(Long id) {
        return null;
    }

    @Transactional
    @Override
    public void update(ActivityDto dto) {
    }

    @Transactional
    @Override
    public void remove(ActivityDto dto) {
    }
}
