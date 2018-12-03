/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.test;

/**
 * @author qiusheng.wu
 * @Filename Department.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/7 14:52</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class Department {
    private User user;

    public Department() {
    }

    public Department(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}