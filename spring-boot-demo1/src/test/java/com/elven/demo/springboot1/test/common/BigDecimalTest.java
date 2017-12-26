/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename BigDecimalTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/14 14:04</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class BigDecimalTest {

    /**
     * 元-分换算进制
     */
    private static final BigDecimal YUAN_FEN_RADIX = new BigDecimal("100");

    public Long yuanToFen(BigDecimal amount) {
        return amount.multiply(YUAN_FEN_RADIX).longValue();
    }

    public static Long yuanToFen(String amount) {
        return new BigDecimal(amount).multiply(YUAN_FEN_RADIX).longValue();
    }


    @Test
    public void test(){
        System.out.println(yuanToFen("35"));
    }

    @Test
    public void test1() throws InvocationTargetException, IllegalAccessException {
        BigDecimalObj bigDecimalObj = new BigDecimalObj();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("amt", "");
        org.apache.commons.beanutils.BeanUtils.populate(bigDecimalObj, map);
    }

    @Test
    public void test2(){
        System.out.println(new BigDecimal(Double.valueOf(1)/100d));
    }

}

class BigDecimalObj{
    private BigDecimal amt;
    private Integer num;

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}