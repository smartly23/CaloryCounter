/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.dao.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.UserStatsDao;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import java.util.List;

/**
 * This class receives burntcalories for every user. It is a separate DAO for
 * optimalization purposes to avoid extracting whole database for single query.
 *
 * @author xpasko
 */
public class UserStatsDaoImpl implements UserStatsDao {

    public List<UserStats> getUsersStats() {
        // em.createTypedQuery dotaz cez "new"
        return null;
    }

    public class UserStats {

        private int sumBurntCalories;
        // add duration
        private AuthUser user;

        private UserStats(int sumBurntCalories, AuthUser user) {
            this.sumBurntCalories = sumBurntCalories;
            this.user = user;
        }

        public int getSumBurntCalories() {
            return sumBurntCalories;
        }

        public AuthUser getUser() {
            return user;
        }
    }
}
