package cz.fi.muni.pa165.calorycounter.serviceapi.dto;

import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import java.util.Objects;

/**
 * DTO for AuthUser entity.
 *
 * @author Martin Pasko (smartly23)
 */
public class AuthUserDto {

    private Long userId;
    private String name;
    private String sex;
    private int age;
    private WeightCategory weightCatNum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public WeightCategory getWeightCatNum() {
        return weightCatNum;
    }

    public void setWeightCatNum(WeightCategory weightCatNum) {
        this.weightCatNum = weightCatNum;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.userId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthUserDto other = (AuthUserDto) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AuthUserDto{" + "userId=" + userId + ", name=" + name + ", sex=" + sex + ", age="
                + age + ", weightCatNum=" + weightCatNum + '}';
    }

}
