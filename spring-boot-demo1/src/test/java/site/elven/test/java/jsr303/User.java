package site.elven.test.java.jsr303;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    @NotNull(message = "姓名不能为空")
    private String name;

    @Min(value = 0, message = "年龄必须大于0岁")
    @Max(value = 150, message = "年龄必须大于0岁")
    private int age;

    @Max(value = 300, message = "身高不能大于400cm")
    private int height;

    @Size(min = 20, max = 50, message = "自我介绍必须在20~50字之间")
    private String desc;

    public User(String name, int age, int height, String desc) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.desc = desc;
    }

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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
