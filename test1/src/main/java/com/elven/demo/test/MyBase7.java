/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.test;

import org.junit.Test;

import java.util.Date;

/**
 * @Filename MyBase7.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-3-8 下午9:48</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MyBase7 {

    /**
     * # vim ~/.myBase7.ini
     * App.UserLic.FirstUseOn=1487605350
     * App.UserLic.LaunchNum=9
     */
    @Test
    public void firstUseOn(){
        long time = new Date().getTime();
        System.out.println(time/1000);
    }
}