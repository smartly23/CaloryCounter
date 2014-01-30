/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.serviceapi.dto;

/**
 * Statistics of users and their activities
 *
 * @author Jan Kucera (Greld)
 */
public class UserStatsDto {

    private long userId;
    private String username;
    private int sumBurntCalories;
    private int sumDuration;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSumBurntCalories() {
        return sumBurntCalories;
    }

    public void setSumBurntCalories(int sumBurntCalories) {
        this.sumBurntCalories = sumBurntCalories;
    }

    public int getSumDuration() {
        return sumDuration;
    }

    public void setSumDuration(int sumDuration) {
        this.sumDuration = sumDuration;
    }

    @Override
    public String toString() {
        return "UserStatsDto{" + "username=" + username + ", sumBurntCalories=" + sumBurntCalories + ", sumDuration=" + sumDuration + '}';
    }

}
