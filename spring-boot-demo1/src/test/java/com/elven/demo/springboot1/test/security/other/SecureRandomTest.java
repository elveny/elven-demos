/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.other;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Filename SecureRandomTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-19 下午11:01</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SecureRandomTest {
    @Test
    public void generateSeed() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] seed = secureRandom.generateSeed(11);
        for(byte b : seed){
            System.out.println(b);
        }
    }

    @Test
    public void nextInt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(10);
        int intRandom = secureRandom.nextInt();
        System.out.println(intRandom);
    }
}