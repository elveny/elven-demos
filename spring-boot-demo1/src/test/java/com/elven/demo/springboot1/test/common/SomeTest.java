/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author qiusheng.wu
 * @Filename SomeTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/3/9 18:57</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SomeTest {
    @Test
    public void testStringUtils() throws Exception {
        boolean flag = StringUtils.pathEquals("222", "222");
        System.out.println(flag);
    }

    @Test
    public void testClassPathResource() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("./sample-data.csv");

        System.out.println(classPathResource.getURL());
    }

    @Test
    public void testStringUtils1(){
        boolean flag = StringUtils.hasText("xxxxx");
        System.out.println(flag);
    }

    @Test
    public void currentTimeMillis(){
        System.out.println(System.currentTimeMillis());
    }


    public static void main(String[] args) throws IOException {
        System.out.println("start");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        while (line != null) {
            System.out.println("您已输入::::"+line);
            line = reader.readLine();
        }

        System.out.println("end");
    }

    @Test
    public void cutSql(){

//        String sql = "INSERT INTO sign_trans(trans_no,trans_date,trans_date_time,sa_flag,process_status,trans_status,resp_code,resp_msg,channel_date_time,inst_code,inst_trans_code,inst_trans_id,inst_trans_date,inst_req_no,acct_no,acct_name,cert_type,cert_no,phone_no,remark) SELECT REPLACE(UUID(),'-',''),DATE_FORMAT(t.create_time, '%Y-%m-%d'),t.create_time,'S','FINISH','SUCCESS','0','交易成功',t.create_time,'tjpbc','sign','sign',DATE_FORMAT(t.create_time, '%Y-%m-%d'),t.sign_no,t.card_no,t.name,'01',t.id_card,t.cell_phone,'数据迁移' FROM t_pay_sign t WHERE t.`channel_no`='tjpbc' AND t.`sign_status`='1' AND t.`state`='1' and t.id between {0} and {1};";
        String sql = "INSERT INTO sign_trans(trans_no,trans_date,trans_date_time,sa_flag,process_status,trans_status,resp_code,resp_msg,channel_date_time,inst_code,inst_trans_code,inst_trans_id,inst_trans_date,inst_req_no,acct_no,acct_name,cert_type,cert_no,phone_no,remark) SELECT REPLACE(UUID(),'-',''),DATE_FORMAT(t.create_time, '%Y-%m-%d'),t.create_time,'S','FINISH','SUCCESS','0','交易成功',t.create_time,'tjpbc','sign','sign',DATE_FORMAT(t.create_time, '%Y-%m-%d'),t.sign_no,t.card_no,t.name,'01',t.id_card,t.cell_phone,'数据迁移' FROM t_pay_sign t WHERE t.`channel_no`='tjpbc' AND t.`sign_status`='1' AND t.`state`='1' and t.id between %d and %d;";

        int i = 5909;
        int max = 16762072;
        int step = 1000000;

        while(i <= max){
            int start = i;
            int end = i+(step-1);

            if(end > max){
                end = max;
            }

//            System.out.println(MessageFormat.format(sql, ""+start, ""+end));
//            System.out.println(String.format(sql, start, end));
            System.out.println("INSERT INTO sign_trans(trans_no,trans_date,trans_date_time,sa_flag,process_status,trans_status,resp_code,resp_msg,channel_date_time,inst_code,inst_trans_code,inst_trans_id,inst_trans_date,inst_req_no,acct_no,acct_name,cert_type,cert_no,phone_no,remark) SELECT REPLACE(UUID(),'-',''),DATE_FORMAT(t.create_time, '%Y-%m-%d'),t.create_time,'S','FINISH','SUCCESS','0','交易成功',t.create_time,'tjpbc','sign','sign',DATE_FORMAT(t.create_time, '%Y-%m-%d'),t.sign_no,t.card_no,t.name,'01',t.id_card,t.cell_phone,'数据迁移' FROM t_pay_sign t WHERE t.`channel_no`='tjpbc' AND t.`sign_status`='1' AND t.`state`='1' and t.id between "+start+" and "+end+";");
            i += step;
        }

    }

    @Test
    public void test(){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new Integer("111").toString());
    }
}