/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.*;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Filename CertificateFactoryTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-29 下午11:08</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CertificateFactoryTest {

    @Test
    public void generateCertificateTest() throws CertificateException, FileNotFoundException {

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        Certificate certificate = certificateFactory.generateCertificate(new FileInputStream(ResourceUtils.getFile("classpath:test_demos_test1.cer")));

        System.out.println("getEncoded:"+Base64Utils.encodeToString(certificate.getEncoded()));
        System.out.println("getType:"+certificate.getType());
        System.out.println("getPublicKey().getAlgorithm():"+certificate.getPublicKey().getAlgorithm());
        System.out.println("getPublicKey().getFormat():"+certificate.getPublicKey().getFormat());
        System.out.println("getPublicKey().getEncoded():"+Base64Utils.encodeToString(certificate.getPublicKey().getEncoded()));
    }

    @Test
    public void generateCertificatesTest() throws CertificateException, FileNotFoundException {

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        Collection<Certificate> certificates = (Collection<Certificate>) certificateFactory.generateCertificates(new FileInputStream(ResourceUtils.getFile("classpath:test_demos_test1.cer")));

        System.out.println("isEmpty:"+certificates.isEmpty());
        System.out.println("size:"+certificates.size());

    }

    @Test
    public void generateCRLTest() throws CertificateException, FileNotFoundException, CRLException {

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        CRL crl = certificateFactory.generateCRL(new FileInputStream(ResourceUtils.getFile("classpath:test_demos_test1.cer")));

        System.out.println("getType:"+crl.getType());

    }

    @Test
    public void generateCertPathTest() throws CertificateException, FileNotFoundException, CRLException {

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        CertPath certPath = certificateFactory.generateCertPath(new FileInputStream(ResourceUtils.getFile("classpath:test_demos_test1.cer")));

        System.out.println("getType:"+certPath.getType());

    }

    @Test
    public void getCertPathEncodingsTest() throws CertificateException, FileNotFoundException, CRLException {

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        Iterator iterator = certificateFactory.getCertPathEncodings();

        System.out.println("hasNext:"+iterator.hasNext());

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void generateCertificateTest1() throws IOException, CertificateException {
        FileInputStream fis = new FileInputStream(ResourceUtils.getFile("classpath:test_demos_test1.cer"));
        BufferedInputStream bis = new BufferedInputStream(fis);

        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        while (bis.available() > 0) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Certificate cert = cf.generateCertificate(bis);
            System.out.println(cert.toString());
        }

    }

}