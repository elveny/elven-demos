/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename JsonTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/3/9 15:52</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class JsonTest {
    @Test
    public void test1() throws Exception {
        Map<String, String> mapValue = new HashMap<String, String>();
        System.out.println(JSON.toJSONString(mapValue));
        mapValue.put("aaaa", "bbbb");
        mapValue.put("cccc", "dddd");
        mapValue.put("eeee", "ffff");
        System.out.println(JSON.toJSONString(mapValue));
    }

    @Test
    public void test2(){
        Address address = new Address(1L, "test");
        User user = new User(1L, "name", address);

        String json = JSON.toJSONString(user);

        System.out.println(json);

        User user1 = JSON.parseObject(json, User.class);
        System.out.println(user1.getAddress().getAddr());
    }

    @Test
    public void test3(){
        String jsonStr = "{\n" +
                "    \"trans_content\": {\n" +
                "        \"trans_reqDatas\": [\n" +
                "            {\n" +
        "                \"trans_reqData\": " +
                "                   [\n" +
                "                    {\n" +
                "                        \"state\": -1,\n" +
                "                        \"to_acc_dept\": \"||工商银行\",\n" +
                "                        \"to_acc_name\": \"张宝\",\n" +
                "                        \"to_acc_no\": \"6222020111122220000\",\n" +
                "                        \"trans_batchid\": 20749813,\n" +
                "                        \"trans_endtime\": \"2017-06-21 17:03:02\",\n" +
                "                        \"trans_fee\": \"0.01\",\n" +
                "                        \"trans_money\": \"1.01\",\n" +
                "                        \"trans_no\": \"10000027620170621000012000\",\n" +
                "                        \"trans_orderid\": 16974524,\n" +
                "                        \"trans_remark\": \"\",\n" +
                "                        \"trans_starttime\": \"2017-06-21 17:02:57\",\n" +
                "                        \"trans_summary\": \"马上金融还款\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"state\": 1,\n" +
                "                        \"to_acc_dept\": \"||工商银行\",\n" +
                "                        \"to_acc_name\": \"张宝\",\n" +
                "                        \"to_acc_no\": \"6222020111122220000\",\n" +
                "                        \"trans_batchid\": 20749814,\n" +
                "                        \"trans_endtime\": \"2017-06-21 17:04:15\",\n" +
                "                        \"trans_fee\": \"0.01\",\n" +
                "                        \"trans_money\": \"1.01\",\n" +
                "                        \"trans_no\": \"10000027620170621000012001\",\n" +
                "                        \"trans_orderid\": 16974525,\n" +
                "                        \"trans_remark\": \"\",\n" +
                "                        \"trans_starttime\": \"2017-06-21 17:04:08\",\n" +
                "                        \"trans_summary\": \"马上金融还款\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"state\": 1,\n" +
                "                        \"to_acc_dept\": \"||工商银行\",\n" +
                "                        \"to_acc_name\": \"张宝\",\n" +
                "                        \"to_acc_no\": \"6222020111122220000\",\n" +
                "                        \"trans_batchid\": 20749816,\n" +
                "                        \"trans_endtime\": \"2017-06-21 17:04:31\",\n" +
                "                        \"trans_fee\": \"0.01\",\n" +
                "                        \"trans_money\": \"1.01\",\n" +
                "                        \"trans_no\": \"10000027620170621000012002\",\n" +
                "                        \"trans_orderid\": 16974527,\n" +
                "                        \"trans_remark\": \"\",\n" +
                "                        \"trans_starttime\": \"2017-06-21 17:04:27\",\n" +
                "                        \"trans_summary\": \"马上金融还款\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"state\": 2,\n" +
                "                        \"to_acc_dept\": \"||工商银行\",\n" +
                "                        \"to_acc_name\": \"张宝\",\n" +
                "                        \"to_acc_no\": \"6222020111122220000\",\n" +
                "                        \"trans_batchid\": 20749817,\n" +
                "                        \"trans_endtime\": \"2017-06-21 17:04:50\",\n" +
                "                        \"trans_fee\": \"0.01\",\n" +
                "                        \"trans_money\": \"1.01\",\n" +
                "                        \"trans_no\": \"10000027620170621000012003\",\n" +
                "                        \"trans_orderid\": 16974528,\n" +
                "                        \"trans_remark\": \"\",\n" +
                "                        \"trans_starttime\": \"2017-06-21 17:04:47\",\n" +
                "                        \"trans_summary\": \"马上金融还款\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"trans_head\": {\n" +
                "            \"return_code\": \"0000\",\n" +
                "            \"return_msg\": \"代付请求交易成功\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        JSONObject jsonObj = (JSONObject)JSON.parse(jsonStr);

//        JSONArray transReqData = jsonObj.getJSONObject("trans_content").getJSONArray("trans_reqDatas").getJSONObject(0).getJSONArray("trans_reqData");
//        JSONArray transReqData = ObjectUtils.isEmpty(jsonObj.getJSONObject("trans_content").getJSONArray("trans_reqDatas")) ? null : ObjectUtils.isEmpty(jsonObj.getJSONObject("trans_content").getJSONArray("trans_reqDatas").getJSONObject(0)) ? null : jsonObj.getJSONObject("trans_content").getJSONArray("trans_reqDatas").getJSONObject(0).getJSONArray("trans_reqData");

        JSONArray json = jsonObj.getJSONObject("trans_content").getJSONArray("trans_reqDatas").getJSONObject(0).getJSONArray("trans_reqData");

        System.out.println(json.size());

//        if(!ObjectUtils.isEmpty(transReqData)){
//            int dataSize = transReqData.size();
//
//            for (int i = 0; i < dataSize; i++) {
//                JSONObject data = transReqData.getJSONObject(i);
//                System.out.println(data.getString("trans_no"));
//            }
//        }



    }

    @Test
    public void test4(){

        String jsonStr = "{\n" +
                "    \"alipay_trade_query_response\": {\n" +
                "        \"code\": \"40004\",\n" +
                "        \"msg\": \"Business Failed\",\n" +
                "        \"sub_code\": \"ACQ.TRADE_NOT_EXIST\",\n" +
                "        \"sub_msg\": \"交易不存在\",\n" +
                "        \"buyer_pay_amount\": \"0.00\",\n" +
                "        \"invoice_amount\": \"0.00\",\n" +
                "        \"out_trade_no\": \"042017070318********0009\",\n" +
                "        \"point_amount\": \"0.00\",\n" +
                "        \"receipt_amount\": \"0.00\"\n" +
                "    },\n" +
                "    \"sign\": \"QJgsgQcvUysu1xwgyDlKHNYFNZSJFkD3m00TUBUG80pGQAZBywlqWjLgWGyQZXAAL+NbrzxHcUy+Y7DEAWLVGGF9HP5/yHE+8Q3Hq2C0M/D5QSD/8Bdp02LXzU8+s5tz/5CB3vOaATtobFXOMGzuRh3IvJsfUM8Lk7FfQD+Wh/x3hRaLiab04sBG6/FlT8H711zFXgqbo1cyFkHJ2HpqCdJf0v6Ca53NRHxB6e7aIMDOgA/ziU7lQHM76NPAf4T734Iitfuk6i8JCeSg0uiqq0KZe3fmn4rw75E64fuN1OCiEUHdneCu74DNvC9+OYQ075rV9dGIWvwtK62IsS5n1A==\"\n" +
                "}";

        Map<String, String> map = JSON.parseObject(jsonStr, new TypeReference<LinkedHashMap<String, String>>(){});

        JSONObject jsonObject = (JSONObject) JSONObject.parse(jsonStr, Feature.OrderedField);

        System.out.println(jsonObject.get("alipay_trade_query_response"));


//        Map<String, Object> alipay_trade_query_responseMap = JSON.parseObject(String.valueOf(map.get("alipay_trade_query_response")), new TypeReference<HashMap<String, Object>>() {
//        });
//        System.out.println(alipay_trade_query_responseMap.get("msg"));

    }

    @Test
    public void test5(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("now", new java.util.Date());

        System.out.println(JSON.toJSONString(map));
    }

}