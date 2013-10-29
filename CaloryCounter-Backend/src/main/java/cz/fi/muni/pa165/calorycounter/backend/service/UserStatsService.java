/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.service;

import cz.fi.muni.pa165.calorycounter.backend.dto.UserStatsDto;
import java.util.List;

/**
 *
 * @author xpasko
 */
public interface UserStatsService {
    /** Returns all users with their stats
     * 
     * @return All users with their stats
     */
    public List<UserStatsDto> getAll();
}
