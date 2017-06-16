/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import com.elven.demo.springboot1.test.paygw.reconciliation.ReconResult;
import com.elven.demo.springboot1.test.paygw.reconciliation.Trans;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiusheng.wu
 * @Filename PropertyUtilsTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/15 17:14</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class PropertyUtilsTest {

    @Test
    public void describe1() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        System.out.println(PropertyUtils.describe(list));
    }

    @Test
    public void describe2() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<Trans> list = new ArrayList<Trans>();
        list.add(new Trans("111", "ss", new BigDecimal("111")));
        list.add(new Trans("222", "ss", new BigDecimal("222")));
        System.out.println(PropertyUtils.describe(list));
    }

    @Test
    public void describe3() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Trans trans = new Trans("111", "ss", new BigDecimal("111"));
        System.out.println(PropertyUtils.describe(trans));
    }

    @Test
    public void describe4() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        ReconResult reconResult = new ReconResult();

        System.out.println(PropertyUtils.describe(reconResult));
    }
}