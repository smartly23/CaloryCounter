package cz.fi.muni.pa165.calorycounter.backend.dto;

import cz.fi.muni.pa165.calorycounter.backend.model.WeightCategory;
import java.util.List;
import java.util.Objects;

/**
 * DTO for AuthUser entity.
 *
 * @author Martin Pasko (smartly23)
 */
public class AuthUserDto {

    private Long id;
    private Long userId; // null if first created
    private String name;
    private String gender;
    private int age;
    private String username;
    private WeightCategory weightCat;
    private List<ActivityRecordDto> records;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public WeightCategory getWeightCat() {
        return weightCat;
    }

    public void setWeightCat(WeightCategory weightCat) {
        this.weightCat = weightCat;
    }

    public List<ActivityRecordDto> getRecords() {
        return records;
    }

    public void setRecords(List<ActivityRecordDto> records) {
        this.records = records;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AuthUserDto{" + "name=" + name + ", gender=" + gender + ", age=" + age + ", username="
                + username + ", weightCat=" + weightCat + '}';
    }
}
