package com.elven.demo.springboot1.test;

import com.elven.demo.springboot1.common.util.HttpClientTool;
import com.elven.demo.springboot1.common.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by qiusheng.wu on 2016/12/27.
 */
@RestController("testController")
@RequestMapping("/spirngbootdemo1/test")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "httpClientTool")
    private HttpClientTool httpClientTool;

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    @RequestMapping("hello/{name}")
    public String hello(@PathVariable("name") String name){
        if (name == null || "".equals(name)){
            name = "world";
        }

        return String.format("hello %s !!!", name);
    }

    @RequestMapping("hello1")
    public String hello1(@RequestBody String reqMsg){

        return String.format("你输入的是“%s”", reqMsg);
    }

    @RequestMapping("helloUser")
    public User helloUser(){

        User user = new User();
        user.setId(1);
        user.setName("test");
        user.setAge(11);
        return user;
    }

    @RequestMapping("helloUser1")
    public User helloUser1(User user){
        System.out.println(user);
//        user = new User();
//        user.setId(1);
//        user.setName("test");
//        user.setAge(11);
        return user;
    }

    @RequestMapping("redis/set/{key}/{value}")
    public boolean redisSetTest(@PathVariable String key, @PathVariable String value){
        logger.info("开始设置redis String值{},{}", key, value);

        boolean flag = redisUtil.set(key, value);

        logger.info("设置redis String值结束,flag={}");

        return true;
    }

    @RequestMapping("redis/get/{key}")
    public Object getRedisStringValue(@PathVariable String key){

        Object obj = redisUtil.get(key);
        logger.info(":::::::::::::::<{}>:::::::",obj);
        return obj;
    }

    @RequestMapping("redis/test/write")
    public void redisWriteTest(){
        User user = new User();
        user.setId(1);
        user.setName("test");
        user.setAge(11);

        boolean flag = redisUtil.set("PAYCORE_TEST_WUQS_0002", user);

        logger.info(":::::::::::::::<{}>:::::::",flag);
    }

    @RequestMapping("redis/test/read")
    public Object redisReadTest(){

        Object obj = redisUtil.get("123456");
        logger.info(":::::::::::::::<{}>:::::::",obj);
        return obj;
    }

    @RequestMapping("httpClientTest")
    public String httpClientTest(@PathVariable("name") String name){
        String rsp = "";
        try {
            rsp = httpClientTool.get("http://gitlab.msxf.com/");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rsp;
    }
}
