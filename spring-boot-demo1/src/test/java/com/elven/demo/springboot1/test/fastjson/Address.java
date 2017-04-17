/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.fastjson;

/**
 * @author qiusheng.wu
 * @Filename Address.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/3/23 14:49</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class Address {
    private long id;
    private String addr;

    public Address() {
    }

    public Address(long id, String addr) {
        this.id = id;
        this.addr = addr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}