/**
 * elven.tech Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.provider;

import java.security.Provider;

/**
 * @Filename MyProvider.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 17-6-14 下午11:07</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MyProvider extends Provider {
    /**
     * Constructs a provider with the specified name, version number,
     * and information.
     *
     * @param name    the provider name.
     * @param version the provider version number.
     * @param info    a description of the provider and its services.
     */
    protected MyProvider(String name, double version, String info) {
        super(name, version, info);
    }
}