/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.jpa;

import com.elven.demo.springboot1.test.Application;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiusheng.wu
 * @Filename JpaTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/3/16 17:58</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JpaTest {
    @Autowired
    private CardAuthOrderRepository cardAuthOrderRepository;

    @Test
    public void findByXYZTest(){

        List<CardAuthOrder> cardAuthOrders = cardAuthOrderRepository.findByAcctNo("acctNo");
        System.out.println(cardAuthOrders.size());

        cardAuthOrders = cardAuthOrderRepository.findByAcctNoAndAcctName("acctNo", "acctName");
        System.out.println(cardAuthOrders.size());

        cardAuthOrders = cardAuthOrderRepository.findByAcctNoAndAcctNameAndCertNo("acctNo", "acctName", "certNo");
        System.out.println(cardAuthOrders.size());

        List<String> acctNos = new ArrayList<String>();
        acctNos.add("1");
        acctNos.add("2");
        acctNos.add("3");
        cardAuthOrders = cardAuthOrderRepository.findByAcctNoAndAcctNameAndCertNoAndAcctNoIn("acctNo", "acctName", "certNo", acctNos);
        System.out.println(cardAuthOrders.size());

        long id = 10;
        cardAuthOrders = cardAuthOrderRepository.findByAcctNoAndAcctNameAndCertNoAndAcctNoInAndIdLessThan("acctNo", "acctName", "certNo", acctNos, id);
        System.out.println(cardAuthOrders.size());

        java.util.Date createTime = new DateTime().toDate();
        cardAuthOrders = cardAuthOrderRepository.findByAcctNoAndAcctNameAndCertNoAndAcctNoInAndIdLessThanAndCreateTimeAfter("acctNo", "acctName", "certNo", acctNos, id, createTime);
        System.out.println(cardAuthOrders.size());

        cardAuthOrders = cardAuthOrderRepository.findByAcctNoAndAcctNameAndCertNoAndAcctNoInAndIdLessThanAndCreateTimeAfterOrErrorCode("acctNo", "acctName", "certNo", acctNos, id, createTime, "errorCode");
        System.out.println(cardAuthOrders.size());


    }
}