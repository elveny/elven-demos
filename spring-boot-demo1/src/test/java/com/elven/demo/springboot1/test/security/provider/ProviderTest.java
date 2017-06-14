/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.provider;

import org.junit.Test;

import java.security.Provider;
import java.util.Set;

/**
 * @Filename ProviderTest.java
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
public class ProviderTest {

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
}