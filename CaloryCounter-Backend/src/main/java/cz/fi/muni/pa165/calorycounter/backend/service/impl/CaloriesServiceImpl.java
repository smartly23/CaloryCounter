/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.service.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.CaloriesDao;
import cz.fi.muni.pa165.calorycounter.backend.dto.CaloriesDto;
import cz.fi.muni.pa165.calorycounter.backend.dto.convert.CaloriesConvert;
import cz.fi.muni.pa165.calorycounter.backend.service.CaloriesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lastuvka
 */
@Service
public class CaloriesServiceImpl implements CaloriesService {

    private CaloriesDao caloriesDao;

    @Transactional
    @Override
    public Long create(CaloriesDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("CaloriesDto is null");
        }
        if (dto.getActivity() == null) {
            throw new IllegalArgumentException("CaloriesDto-activity is null.");
        }
        caloriesDao.create(CaloriesConvert.fromDtoToEntity(dto));
        //proc Long?
        return new Long(0);
    }

    @Transactional
    @Override
    public CaloriesDto get(Long id) {
         if (id == null) {
            throw new IllegalArgumentException("id is null.");
        } 
        return CaloriesConvert.fromEntityToDto(caloriesDao.get(id));
    }

    @Transactional
    @Override
    public void update(CaloriesDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("CaloriesDto is null.");
        } 
        if (dto.getActivity() == null) {
            throw new IllegalArgumentException("CaloriesDto-activity is null.");
        }

        caloriesDao.update(CaloriesConvert.fromDtoToEntity(dto));
    }

    @Transactional
    @Override
    public void remove(CaloriesDto dto) {
         if (dto == null) {
            throw new IllegalArgumentException("CaloriesDto is null.");
        } 
        caloriesDao.remove(CaloriesConvert.fromDtoToEntity(dto));    }
}
