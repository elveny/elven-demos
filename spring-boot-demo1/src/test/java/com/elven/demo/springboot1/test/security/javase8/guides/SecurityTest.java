/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.javase8.guides;

import org.junit.Test;

import java.security.Provider;
import java.security.Security;
import java.util.Set;

/**
 * @Filename SecurityTest.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-1 下午11:19</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SecurityTest {

    @Test
    public void getAllProviders(){
        java.security.Provider[] providers = java.security.Security
                .getProviders();

        for(Provider provider : providers){
            System.out.println("name::::"+provider.getName()+", version:"+provider.getVersion()+", info:"+provider.getInfo());

            Set<Provider.Service> services = provider.getServices();

            for (java.security.Provider.Service service : services) {
                System.out.println("service::::"+service);
            }
        }
    }

    @Test
    public void addProvide(){
        java.security.Security.addProvider(new MyProvider("MyProvider", 0.1, "测试用"));
        java.security.Security.insertProviderAt(new MyProvider("MyProvider", 0.1, "测试用"), 1);
        getAllProviders();
    }

    @Test
    public void insertProviderAt(){
        java.security.Security.insertProviderAt(new MyProvider("MyProvider", 0.1, "测试用"), 1);
        getAllProviders();
    }

    @Test
    public void getProvider(){
        System.out.println(Security.getProvider("SUN"));
    }

    @Test
    public void removeProvider(){
        addProvide();
        Security.removeProvider("MyProvider");
        getAllProviders();

        Security.getProperty("securerandom.source");
    }

    /**
     * 获取$JAVA_HOME/jre/lib/security/java.security中配置的属性
     */
    @Test
    public void getProperty(){
        System.out.println(Security.getProperty("security.other.1"));
        System.out.println(Security.getProperty("securerandom.source"));
        System.out.println(Security.getProperty("securerandom.strongAlgorithms"));
    }
}