package com.elven.demo.springboot1;

import com.elven.demo.springboot1.test.User;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * rest test
 *
 * @author qiusheng.wu
 * @Created 2017/1/4.
 */
public class RestTest {
    @Test
    public void HelloTest(){
        RestTemplate restTemplate = new RestTemplate();
        String rsp = restTemplate.postForObject("http://localhost:8080/test/hello/elven", "ssssss", String.class);
//        restTemplate.put("http://localhost:8080/test/hello1", "ssssss");
        System.out.println(rsp);
    }

    @Test
    public void Hello1Test(){
        RestTemplate restTemplate = new RestTemplate();
        String rsp = restTemplate.postForObject("http://localhost:8080/test/hello1", "ssssss", String.class);
//        restTemplate.put("http://localhost:8080/test/hello1", "ssssss");
        System.out.println(rsp);
    }

    @Test
    public void HelloUser(){
        RestTemplate restTemplate = new RestTemplate();
        String rsp = restTemplate.postForObject("http://localhost:8080/test/helloUser", "ssssss", String.class);
//        restTemplate.put("http://localhost:8080/test/hello1", "ssssss");
        System.out.println(rsp);
    }

    @Test
    public void HelloUser1(){
        RestTemplate restTemplate = new RestTemplate();
//        User user = new User();
//        user.setId(1);
//        user.setName("test");
//        user.setAge(11);
//        String rsp = restTemplate.postForObject("http://localhost:8080/test/helloUser1", user, String.class);
//        restTemplate.put("http://localhost:8080/test/hello1", "ssssss");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", 11);
        params.put("name", "ssss");
        params.put("age", 22);
//        restTemplate.getForEntity()
        ResponseEntity<String> rsp = restTemplate.getForEntity("http://localhost:8080/test/helloUser1", String.class, params);
        System.out.println(rsp.getBody());
    }

    @Test
    public void HelloUser11(){
        RestTemplate restTemplate = new RestTemplate();

        String rsp = restTemplate.getForObject("http://localhost:8080/test/helloUser1?name=xxx", String.class);
        System.out.println(rsp);
    }
}
