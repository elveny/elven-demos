package com.elven.demo.springboot1.test.test;

import java.io.Serializable;

/**
 * user
 *
 * @author qiusheng.wu
 * @Created 2017/1/4.
 */
public class User implements Serializable{
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
