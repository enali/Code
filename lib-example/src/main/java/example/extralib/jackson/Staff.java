package example.extralib.jackson;

import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;
import java.util.List;

/**
 * 属性的getter方法在序列化时有用.
 */
public class Staff {
    @JsonView(Views.Normal.class)
    private String name;

    @JsonView(Views.Normal.class)
    private int age;

    @JsonView(Views.Normal.class)
    private String position;

    @JsonView(Views.Manager.class)
    private BigDecimal salary;

    @JsonView(Views.Normal.class)
    private List<String> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
