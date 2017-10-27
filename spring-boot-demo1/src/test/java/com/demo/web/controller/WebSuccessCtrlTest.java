/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.demo.web.controller;

import org.junit.Test;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import com.demo.utils.RSACoder;
import com.demo.utils.SignUtil;
import com.demo.web.domain.request.TradeQueryRes;

import java.util.ArrayList;

/**
 * @author qiusheng.wu
 * @Filename WebSuccessCtrlTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/7/5 13:55</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class WebSuccessCtrlTest {

    String wyPubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKE5N2xm3NIrXON8Zj19GNtLZ8xwEQ6uDIyrS3S03UhgBJMkGl4msfq4Xuxv6XUAN7oU1XhV3/xtabr9rXto4Ke3d6WwNbxwXnK5LSgsQc1BhT5NcXHXpGBdt7P8NMez5qGieOKqHGvT0qvjyYnYA29a8Z4wzNR7vAVHp36uD5RwIDAQAB";

    @Test
    public void test1() throws Exception {
        TradeQueryRes tradeQueryRes = new TradeQueryRes();
        String token = "FE1B2A2DEF8F1AB6427A4019751CF4DA";
        tradeQueryRes.setToken(token);

        String tradeNum = "042017070512511300001009";
        tradeQueryRes.setTradeNum(tradeNum);

        String tradeAmount = "1";
        tradeQueryRes.setTradeAmount(tradeAmount);

        String tradeCurrency = "CNY";
        tradeQueryRes.setTradeCurrency(tradeCurrency);

        String tradeDate = "20170705";
        tradeQueryRes.setTradeDate(tradeDate);

        String tradeTime = "125843";
        tradeQueryRes.setTradeTime(tradeTime);
        String tradeStatus = "0";
        tradeQueryRes.setTradeStatus(tradeStatus);
        String sign  = "gtJdEpH7UOOJUqX2Q9MLJLlIdk+QOaIvEYaQSuBzLTgECCggUfHtTu+DQxzQSkUV4caJ41Rv0DAt\\nDVGpsvVhUoxtl4s5T9zmF9HZMXoZ2jnVyajde2aytAha6AyraFdeHMkAyOFPOGSvfW3fW8nX+JqO\\nh18BpdIg4+9w9lS27II=\\n";


        String strSourceData = SignUtil.signString(tradeQueryRes, new ArrayList<String>());
        System.out.println(strSourceData);
        sign = sign.replace("\\n","");
        System.out.println(sign);

        byte[] decryptBASE64Arr = new BASE64Decoder().decodeBuffer(sign);
        System.out.println(Base64Utils.encodeToString(decryptBASE64Arr));

        byte[] decryptArr = RSACoder.decryptByPublicKey(decryptBASE64Arr, wyPubKey);
    }
}