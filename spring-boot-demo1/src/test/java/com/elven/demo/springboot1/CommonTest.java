/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1;

import org.apache.commons.lang3.ObjectUtils;
import org.assertj.core.util.DateUtil;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author qiusheng.wu
 * @Filename CommonTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/1/20 9:57</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CommonTest {

    @Test
    public void test1(){
        String filePath = "sdfsfs.dfdf.ssss";
        String suffix = filePath.substring(filePath.lastIndexOf(".")+1);
        System.out.println(suffix);
    }

    @Test
    public void test2(){
        String string = "中国工商银行铜川分行印台支行";
        String suffix = string.substring(0, string.indexOf("行")+1);
        System.out.println(suffix);
    }

    @Test
    public void test3(){
        try{
            int r = 1 / 0;
        }
        catch (Exception e){
            System.out.println("catch");
            throw e;
        }
        finally {
            System.out.println("finally");
        }
    }

    @Test
    public void test4(){
        Assert.assertFalse(ObjectUtils.NULL == null);
        Assert.assertTrue(ObjectUtils.equals(ObjectUtils.NULL, null));
    }

    @Test
    public void test5(){


        long t1 = 1484103140L;
        Date d1 = new Date(t1);
        System.out.println(d1);
        System.out.println(DateUtil.formatAsDatetime(d1));

        DateTime dateTime = new DateTime(t1);
        System.out.println(dateTime.toString("yyyyMMddHHmmss.SSS"));

        long t2 = 1486433767L; // 2017.2.6
        Date d2 = new Date(t2);
        System.out.println(d2);

    }

    @Test
    public void test6(){

        System.out.println(new DateTime(1486433767L).toString("yyyy-MM-dd HH:mm:ss.SSS"));  // 2017.2.6
        System.out.println(new DateTime().getMillis()/1000);  // 当前
        System.out.println(new DateTime().toString("yyyy-MM-dd HH:mm:ss.SSS"));  // 当前
    }

    @Test
    public void test7(){
        System.out.println(GregorianCalendar.getInstance().getTimeInMillis());
        System.out.println(new DateTime().getMillis());
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test8(){
        String str = String.format("1111: %s, %s, %d", "222", "333", 444);
        System.out.println(str);
    }


}