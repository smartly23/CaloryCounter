package cz.fi.muni.pa165.calorycounter.serviceapi.dto;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO for AuthUser entity.
 *
 * @author Martin Pasko (smartly23)
 */

// ak chceme konkretny tvar XML-ka, mozme si zadefinovat vlastnu schemu, v ktorej namespace bude rovnaky 
//ako tu @XmlRootElement(namespace = "somenamespace"), alebo je mozne definovat rovno package-namespace,
//vid http://stackoverflow.com/questions/16584555/understanding-jaxb-xmlrootelement-annotation
@XmlRootElement
public class AuthUserDto {

    private Long userId;
    private String username; 
    private String password; 
    private String name;
    private String sex;
    private int age;
    private WeightCategory weightCategory;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public WeightCategory getWeightCategory() {
        return weightCategory;
    }

    public void setWeightCategory(WeightCategory weightCategory) {
        this.weightCategory = weightCategory;
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
        return "AuthUserDto{" + "userId=" + userId + ", username=" + username + ", password=" + password
                + ", name=" + name + ", sex=" + sex + ", age=" + age + ", weightCategory=" + weightCategory + '}';
    }

}
