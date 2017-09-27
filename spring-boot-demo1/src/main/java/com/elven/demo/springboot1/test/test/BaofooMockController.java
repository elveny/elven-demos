/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiusheng.wu
 * @Filename BaofooMockController.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/6/22 15:59</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
@RestController("baofooMockController")
@RequestMapping("/spirngbootdemo1/baofooMock")
public class BaofooMockController {

    Logger logger = LoggerFactory.getLogger(BaofooMockController.class);

    @RequestMapping("bankRefundBatchQuery")
    public String bankRefundBatchQuery(@RequestParam(value = "id") String id){

        logger.info("请求开始...id:"+id);

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }

        String respXmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<trans_content>\n" +
                "  <trans_head>\n" +
                "    <return_code>0000</return_code>\n" +
                "    <return_msg>代付请求成功</return_msg>\n" +
                "  </trans_head>\n" +
                "  <trans_reqDatas>\n" +
                "    <trans_reqData>\n" +
                "      <trans_orderid>200014</trans_orderid>\n" +
                "      <trans_batchid>20001</trans_batchid>\n" +
                "      <trans_no>1234567A</trans_no>\n" +
                "      <trans_money>1.23</trans_money>\n" +
                "      <to_acc_name>周小忍</to_acc_name>\n" +
                "      <to_acc_no>6222601234567890</to_acc_no>\n" +
                "      <to_acc_dept>上海市|上海市|工商银行张江支行</to_acc_dept>\n" +
                "      <trans_fee>2.0</trans_fee>\n" +
                "      <state>2</state>\n" +
                "      <trans_remark>已退款</trans_remark>\n" +
                "      <trans_starttime>20141210030455</trans_starttime>\n" +
                "      <trans_endtime>20141210030755</trans_endtime>\n" +
                "    </trans_reqData>\n" +
                "    <trans_reqData>\n" +
                "      <trans_orderid>200015</trans_orderid>\n" +
                "      <trans_batchid>20001</trans_batchid>\n" +
                "      <trans_no>1234567B</trans_no>\n" +
                "      <trans_money>1.23</trans_money>\n" +
                "      <to_acc_name>周小忍</to_acc_name>\n" +
                "      <to_acc_no>6222601234567890</to_acc_no>\n" +
                "      <to_acc_dept>上海市|上海市|工商银行张江支行</to_acc_dept>\n" +
                "      <trans_fee>2.0</trans_fee>\n" +
                "      <state>2</state>\n" +
                "      <trans_remark>已退款</trans_remark>\n" +
                "      <trans_starttime>20141210030455</trans_starttime>\n" +
                "      <trans_endtime>20141210030755</trans_endtime>\n" +
                "    </trans_reqData>\n" +
                "  </trans_reqDatas>\n" +
                "</trans_content>\n";

        logger.info("结束...id:"+id);

        return respXmlStr;
    }
}