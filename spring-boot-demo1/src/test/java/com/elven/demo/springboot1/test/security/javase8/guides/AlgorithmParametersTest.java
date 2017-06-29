/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.spec.DSAParameterSpec;
import java.security.spec.InvalidParameterSpecException;

/**
 * @Filename AlgorithmParametersTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-29 下午10:39</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class AlgorithmParametersTest {

    @Test
    public void AlgorithmParametersTest() throws NoSuchAlgorithmException, IOException, InvalidParameterSpecException {
        AlgorithmParameters  algorithmParameters = AlgorithmParameters.getInstance("DSA");

        algorithmParameters.init("12345678".getBytes(), "RAW");

        System.out.println("getEncoded:"+ Base64Utils.encodeToString(algorithmParameters.getEncoded()));

        DSAParameterSpec dsaParameterSpec = algorithmParameters.getParameterSpec(DSAParameterSpec.class);
    }

    @Test
    public void AlgorithmParameterGeneratorTest() throws NoSuchAlgorithmException, IOException, InvalidParameterSpecException {
        AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("DSA");
        algorithmParameterGenerator.init(512);
        AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
        System.out.println("getEncoded:"+ Base64Utils.encodeToString(algorithmParameters.getEncoded()));
        System.out.println("getEncoded:"+ Base64Utils.encodeToString(algorithmParameters.getEncoded("ASN.1")));
        System.out.println("getEncoded:"+ Base64Utils.encodeToString(algorithmParameters.getEncoded("RAW")));
        System.out.println("getProvider:"+ algorithmParameters.getProvider());
        System.out.println("getAlgorithm:"+ algorithmParameters.getAlgorithm());

        DSAParameterSpec dsaParameterSpec = algorithmParameters.getParameterSpec(DSAParameterSpec.class);

        System.out.println("getP:"+ dsaParameterSpec.getP());
        System.out.println("getG:"+ dsaParameterSpec.getG());
        System.out.println("getQ    :"+ dsaParameterSpec.getQ());
    }
}