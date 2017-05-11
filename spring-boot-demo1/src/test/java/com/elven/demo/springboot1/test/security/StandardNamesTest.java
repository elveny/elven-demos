/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security;

import org.junit.Test;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * @Filename StandardNamesTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-5-11 下午11:04</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class StandardNamesTest {

    @Test
    public void getInstance() throws NoSuchAlgorithmException, CertificateException {
        AlgorithmParameterGenerator.getInstance("DiffieHellman");

        AlgorithmParameters.getInstance("AES");

        CertificateFactory.getInstance("X.509");

        CertPathBuilder.getInstance("PKIX");

    }
}