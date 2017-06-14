/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.redis;

import com.alibaba.fastjson.JSON;
import com.elven.demo.springboot1.test.Application;
import com.elven.demo.springboot1.test.common.util.RedisUtil;
import com.elven.demo.springboot1.test.paygw.reconciliation.Trans;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename HashMapPerformanceTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/12 14:19</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class HashMapPerformanceTest {

    private final static Logger logger = LoggerFactory.getLogger(HashMapPerformanceTest.class);

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1(){
        redisUtil.set("key1", "test1");
        logger.info(redisUtil.get("key1").toString());

        redisUtil.set("hashKey1", "key1", "test1");
        logger.info(redisUtil.get("hashKey1", "key1").toString());

        Map<String, Object> mapObj1 = new HashMap<String, Object>();
        for (int i = 0; i < 10; i++) {
            mapObj1.put("key_"+i, new Trans());
            logger.info("key_"+i);
        }
        redisUtil.set("hashKey", mapObj1);

        Map<String, Object> redisMapObj = (Map<String, Object>) redisUtil.get("hashKey");
        logger.info(redisMapObj.get("key_1").toString());

        redisUtil.set("hashKey", "key_1", new Trans());

    }

    @Test
    public void setHashMapTest(){
        Map<String, String> mapObj1 = new HashMap<String, String>();
        for (int i = 0; i < 10; i++) {
            mapObj1.put("key_"+i, "value_"+i);
        }
        redisUtil.set("hashKey", mapObj1);

        Map<String, String> redisMapObj = (Map<String, String>) redisUtil.get("hashKey");
        logger.info(JSON.toJSONString(redisMapObj, true));

        Map<String, String> mapObj2 = new HashMap<String, String>();
        for (int i = 11; i < 20; i++) {
            mapObj2.put("key_"+i, "value_"+i);
        }
        redisUtil.set("hashKey", mapObj2);

        redisMapObj = (Map<String, String>) redisUtil.get("hashKey");
        logger.info(JSON.toJSONString(redisMapObj, true));
    }

    @Test
    public void setHashMap1000000Test(){
        logger.info("setHashMap1000000Test start");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Map<String, String> mapObj1 = new HashMap<String, String>();
        for (int i = 0; i < 1000000; i++) {
            mapObj1.put("key_"+i, "value_"+i);
        }
        redisUtil.set("hashKey", mapObj1);

        Map<String, String> redisMapObj = (Map<String, String>) redisUtil.get("hashKey");
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }

    @Test
    public void setHashMap1000000TransTest(){
        logger.info("setHashMap1000000TransTest start");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Map<String, Object> mapObj1 = new HashMap<String, Object>();
        for (int i = 0; i < 1000000; i++) {
            mapObj1.put("key_"+i, new Trans());
        }
        redisUtil.set("hashKey", mapObj1);

        Map<String, String> redisMapObj = (Map<String, String>) redisUtil.get("hashKey");
        System.out.println(redisMapObj.get("key_1"));
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }

    @Test
    public void setHashMapPer1000000TransTest(){
        logger.info("setHashMapPer1000000TransTest start");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 1000000; i++) {
            redisUtil.set("hashKey", "key_"+i, new Trans());
        }

        Map<String, String> redisMapObj = (Map<String, String>) redisUtil.get("hashKey");
        System.out.println(redisMapObj);
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }
}