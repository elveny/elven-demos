/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import java.util.Random;

/**
 * @author qiusheng.wu
 * @Filename RandomTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/25 13:47</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RandomTest {

    @Test
    public void randomNextTest(){
        Random random = new Random();
        System.out.println(random.nextInt());
        System.out.println(random.nextInt(100));
        System.out.println(random.nextBoolean());
        System.out.println(random.nextDouble());
        System.out.println(random.nextFloat());
        System.out.println(random.nextLong());
        System.out.println(random.nextGaussian());
    }

    @Test
    public void randomUtilsNextTest(){
        Random random = new Random();
        System.out.println(RandomUtils.JVM_RANDOM);
        System.out.println(RandomUtils.nextBoolean());
        System.out.println(RandomUtils.nextBoolean(RandomUtils.JVM_RANDOM));
        System.out.println(RandomUtils.nextBoolean(random));
        System.out.println(RandomUtils.nextInt());
        System.out.println(RandomUtils.nextInt(10));
        System.out.println(RandomUtils.nextInt(random));
        System.out.println(RandomUtils.nextInt(random, 10));
        System.out.println(RandomUtils.nextDouble());
        System.out.println(RandomUtils.nextDouble(random));
        System.out.println(Math.round(RandomUtils.nextDouble(random)));



    }


}