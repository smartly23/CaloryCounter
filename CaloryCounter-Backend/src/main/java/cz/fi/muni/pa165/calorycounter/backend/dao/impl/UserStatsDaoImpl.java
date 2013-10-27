/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.dao.impl;

import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import java.util.List;

/**
 *
 * @author xpasko
 */
public class UserStatsDaoImpl {

    public List<UserStats> getUsersStats() {
        // em.createTypedQuery dotaz cez "new"
        return null;
    }

    public class UserStats {

        private int sumBurntCalories;
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
