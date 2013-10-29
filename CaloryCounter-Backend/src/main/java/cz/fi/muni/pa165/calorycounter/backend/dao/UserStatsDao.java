/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.dao;

import cz.fi.muni.pa165.calorycounter.backend.dao.impl.UserStatsDaoImpl;
import java.util.List;

/**
 *
 * @author xpasko
 */
public interface UserStatsDao {

    public List<UserStatsDaoImpl.UserStats> getUsersStats();
}
