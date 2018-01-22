/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package com.jd.recon;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename TestDownload.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/1/9 15:07</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TestDownload {

    public void download(){
        String url = "https://bapi.jdpay.com/api/download.do";

        String filename = "20180112ordercreate_110210007001.zip";//具体格式请参考文档  例如：20151222ordercreate_110099385001.zip

        String path = "0001/0001";//具体格式请参考文档 例如：0001/0001
        String key = "94VVIf3KlsyP02";//该参数由商户提供给网银在线进行配置
        Map<String,String> req = new HashMap<String,String>();
        req.put("name", filename);
        req.put("path", path);
        String data = "{\"name\":\""+filename+"\",\"path\":\""+path+"\"}";
        System.out.println(data);

        try {
            data = BASE64.encode(data.getBytes());//data进行BASE64
            String md5 = MD5.md5(data, key);
            System.out.println(md5);
            Map<String,String> params = new HashMap<String,String>();
            params.put("md5", md5);
            params.put("data", data);
            params.put("owner", "110210007");//owner为 商户在我方平台注册的9位商户号 例如：110099385
            System.out.println(params);
            WebUtils.download(url, params, 5000, 5000, "d:/download/dddddd/xxxxx/yyyyy/zzzz/wwwwwww/"+filename);;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void md5Test() throws Exception {
        String data = "{\"name\":\"20180109ordercreate_110306591002.zip\",\"path\":\"0001/0001\"}";
        String key = "YLkYnpbZ5gC99xwS";

        System.out.println(MD5.md5(data, key));
    }

    public static void main(String[] args) throws Exception {
        new TestDownload().download();
//        new TestDownload().md5Test();
    }
}