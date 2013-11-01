/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.dao.impl;

import cz.fi.muni.pa165.calorycounter.backend.dao.UserStatsDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class receives burntcalories for every user. It is a separate DAO for
 * optimalization purposes to avoid extracting whole database for single query.
 *
 * @author Jan Kucera (Greld)
 */
public class UserStatsDaoImplJPA implements UserStatsDao {

    final static Logger log = LoggerFactory.getLogger(UserDaoImplJPA.class);
    // injected from Spring
    @PersistenceContext
    private EntityManager em;

    public List<UserStats> getUsersStats() {
        // em.createTypedQuery dotaz cez "new"

        TypedQuery<UserStats> query;
        List<UserStats> returnedUsers;
        try {
            query = em.createQuery("SELECT new cz.fi.muni.pa165.calorycounter.backend.dao.impl.UserStatsDaoImpl.UserStats(u.name, SUM(ar.caloriesBurnt, SUM(ar.duration)) "
                    + " FROM AuthUser u LEFT JOIN ActivityRecord ar WHERE u.id = ar.authUser.id"
                    + " GROUP BY u.id, u.name", UserStats.class);
            returnedUsers = query.getResultList();
        } catch (NoResultException nrex) {
            throw new IllegalArgumentException("Invalid username: nonexistent");
        }
        return returnedUsers;

    }

    public static class UserStats {

        private String userName;
        private int sumBurntCalories;
        private int sumDuration;

        public UserStats(String userName, int sumBurntCalories, int sumDuration) {
            this.userName = userName;
            this.sumBurntCalories = sumBurntCalories;
            this.sumDuration = sumDuration;
        }

        public int getSumDuration() {
            return sumDuration;
        }

        public int getSumBurntCalories() {
            return sumBurntCalories;
        }

        public String getUserName() {
            return userName;
        }
    }
}
