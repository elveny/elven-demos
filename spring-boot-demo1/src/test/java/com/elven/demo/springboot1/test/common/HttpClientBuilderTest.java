/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author qiusheng.wu
 * @Filename HttpClientBuilderTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/9/27 11:23</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */

public class HttpClientBuilderTest {

    HttpClient httpClient = new HttpClientBuilder().build();

    static Logger logger = LoggerFactory.getLogger(HttpClientBuilderTest.class);

    @Test
    public void httpclientRetry() throws IOException {
        try{
            logger.info("httpclientRetry::::start");
            String url = "http://localhost:7777/spirngbootdemo1/baofooMock/bankRefundBatchQuery?id=1";
//            String url = "http://10.16.66.107:8089/dac/SinCutServletGBK";
            HttpUriRequest httpUriRequest = new HttpGet(url);
            int connectTimeout = 3000;
            int connectionRequestTimeout = 3000;
            int socketTimeout = 6000;
            //设置通讯参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .setSocketTimeout(socketTimeout).build();
            ((HttpRequestBase) httpUriRequest).setConfig(requestConfig);

            HttpResponse httpResponse = httpClient.execute(httpUriRequest);

            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if(HttpStatus.SC_OK == statusCode){
                HttpEntity entity = httpResponse.getEntity();

                InputStream in = entity.getContent();

                String result = IOUtils.toString(in, "UTF-8");

                EntityUtils.consume(entity);

            }
        }
        catch (Throwable e){
            logger.error("httpclientRetry::::error::"+e.getMessage());
        }
        logger.info("httpclientRetry::::end");
    }

    public static void main(String[] args) {
        HttpClient httpClient = new HttpClientBuilder().build();
        for (int i = 0; i < 1000; i++) {
            logger.info("i::::"+i);
            new Thread(new HttpClientThread(""+i, httpClient)).start();
        }
    }

}

class HttpClientThread implements Runnable{

    Logger logger = LoggerFactory.getLogger(HttpClientThread.class);

    private String id;
    HttpClient httpClient;
    public HttpClientThread(String id, HttpClient httpClient) {
        this.id = id;
        this.httpClient = httpClient;
    }

    @Override
    public void run() {
        try{
            logger.info("HttpClientThread::"+id+"::start");
            String url = "http://localhost:7777/spirngbootdemo1/baofooMock/bankRefundBatchQuery?id="+id;
            HttpUriRequest httpUriRequest = new HttpGet(url);
            int connectTimeout = 1;
            int connectionRequestTimeout = 3000;
            int socketTimeout = 6000;
            //设置通讯参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .setSocketTimeout(socketTimeout).build();
            ((HttpRequestBase) httpUriRequest).setConfig(requestConfig);

            HttpResponse httpResponse = httpClient.execute(httpUriRequest);

            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if(HttpStatus.SC_OK == statusCode){
                HttpEntity entity = httpResponse.getEntity();

                InputStream in = entity.getContent();

                String result = IOUtils.toString(in, "UTF-8");

                EntityUtils.consume(entity);

                logger.info("id::"+id+", result:"+result);
            }
        }
        catch (Throwable e){
            logger.error("HttpClientThread::"+id+"::error::"+e.getMessage());
        }
        logger.info("HttpClientThread::"+id+"::end");

    }
}