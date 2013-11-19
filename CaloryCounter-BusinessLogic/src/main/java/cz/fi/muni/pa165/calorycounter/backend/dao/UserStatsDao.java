/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.dao;

import cz.fi.muni.pa165.calorycounter.backend.dao.impl.UserStatsDaoImplJPA;
import java.util.List;

/**
 *
 * @author  Jan Kucera (Greld)
 */
public interface UserStatsDao {

    public List<UserStatsDaoImplJPA.UserStats> getUsersStats();
}
