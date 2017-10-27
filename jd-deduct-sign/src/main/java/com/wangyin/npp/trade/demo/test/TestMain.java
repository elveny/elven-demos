/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.wangyin.npp.trade.demo.test;

import com.wangyin.npp.trade.demo.AgreementSign;
import com.wangyin.npp.trade.demo.RefundTrade;
import com.wangyin.npp.trade.demo.TradeWithAgreement;

/**
 * @author qiusheng.wu
 * @Filename TestMain.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/10/25 15:02</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TestMain {
    public static void main(String []args){

        AgreementSign agreementSign =new AgreementSign();

        agreementSign.agreementSignRequest();// 签约请求  提交参数需要加密

//		 agreementSign.agreementSignConfirm();// 签约确认  提交参数无需加密

//		 agreementSign.agreementQuery();//协议查询
//		 agreementSign.agreementCancel();//协议解约


        TradeWithAgreement tradeWithAgreement =new TradeWithAgreement();

//		 tradeWithAgreement.trade();// 交易扣款
        tradeWithAgreement.query();//交易查询

        RefundTrade refundTrade = new RefundTrade();

//		 refundTrade.refundTrade();//交易退款
//		 refundTrade.query();//退款查询

    }
}