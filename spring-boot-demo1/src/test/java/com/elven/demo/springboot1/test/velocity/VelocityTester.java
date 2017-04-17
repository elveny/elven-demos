/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.velocity;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;

/**
 * @author qiusheng.wu
 * @Filename VelocityTester.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/4/17 11:53</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class VelocityTester {

    @Test
    public void test1(){

        VelocityContext vContext = new VelocityContext();
        String template = "#set($umask  = \"1000\")\n" +
                "#set($merchantId = \"021045\")##商户号：需替换为生产环境值\n" +
                "#set($msxfPrivateCertCode = \"msxf_ybs_quickpay_private\")##马上消费私钥编码：需替换为生产环境值\n" +
                "#set($ybsPublicCertCode = \"ybs_quickpay_public\")##深圳银联公钥编码：需替换为生产环境值\n" +
                "#set($terminalId = \"88888888\")##终端号：固定值\n" +
                "#set($certNo=\"null\")\n" +
                "#set($reqXML=$StringUtils.join($merchantId,$msxfPrivateCertCode,$ybsPublicCertCode))\n" +
                "#set($data=$StringUtils.join($data, '-'))\n" +
                "#set($reqXML=$StringUtils.join($data, $reqXML))\n" +
                "$umask$reqXML";
        Writer writer = new StringWriter();

        String[] data = new String[]{"0000", "1111", "2222", "3333", "4444"};

        vContext.put("StringUtils", StringUtils.class);
        vContext.put("data", data);

        StringUtils.join();

        Velocity.evaluate(vContext, writer, "VelocityUtil.evaluate", template);

        System.out.println(writer.toString());

    }
}