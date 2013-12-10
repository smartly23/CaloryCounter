/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.serviceapi.dto;

/**
 *
 * @author Jan Kucera (Greld)
 */
public class UserStatsDto {

    // abych mohl zvyraznit aktualniho usera
    private long userId;
    private String nameOfUser;
    private int sumBurntCalories;
    private int sumDuration;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
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
        return "UserStatsDto{" + "nameOfUser=" + nameOfUser + ", sumBurntCalories=" + sumBurntCalories + ", sumDuration=" + sumDuration + '}';
    }

}
