/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.apache.http.client.HttpClient;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

/**
 * @author qigang.chen
 * @Filename HttpClientBuilder.java
 * @description HttpClient构建器
 * @Version 1.0
 * @History <li>Author: qigang.chen</li>
 * <li>Date: 2017/1/4 14:40</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class HttpClientBuilder {
    private static Logger logger = LoggerFactory.getLogger(HttpClientBuilder.class);

//    private KeyStoreObj keyStore;
//    private KeyStoreObj trustStore;

    /**
     * 最大连接数
     */
    private static final int MAX_TOTAL_CONNECTIONS = 200;
    /**
     * 默认的每个路由的最大连接数
     */
    private static final int DEFAULT_MAX_PERROUTE = 100;


    public static HttpClientBuilder create() {
        HttpClientBuilder builder = new HttpClientBuilder();
        return builder;
    }

//    public HttpClientBuilder setTrustStore(KeyStoreObj trustStore) {
//        this.trustStore = trustStore;
//        return this;
//    }
//
//    public HttpClientBuilder setKeyStore(KeyStoreObj keyStore) {
//        this.keyStore = keyStore;
//        return this;
//    }

    public HttpClient build() {
        RegistryBuilder registryBuilder = RegistryBuilder.create();
        registryBuilder.register("http", PlainConnectionSocketFactory.getSocketFactory());
//        registryBuilder.register("https", buildSSLConnectionSocketFactory());
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registryBuilder.build());
        cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        cm.setDefaultMaxPerRoute(DEFAULT_MAX_PERROUTE);
        return HttpClients.custom().setConnectionManager(cm).setRetryHandler(new CustomDefaultHttpRequestRetryHandler(3, false, new HashSet())).build();
    }

//    private SSLConnectionSocketFactory buildSSLConnectionSocketFactory() {
//        SSLContext sslContext = null;
//        SSLContextBuilder builder = SSLContexts.custom();
//        if (null != keyStore) {  //服务端ssl证书
//            KeyStore keyMaterial = null;
//            try {
//                //注意测试，怕影响到其他渠道
//                keyMaterial = KeyStore.getInstance(null!=keyStore.getKeyStoreType() ? keyStore.getKeyStoreType().getValue() : KeyStore.getDefaultType());
//                String storePass = StringUtils.isNotEmpty(keyStore.getStroePass()) ? keyStore.getStroePass() : "";
//                String keyPass = StringUtils.isNotEmpty(keyStore.getKeyPass()) ? keyStore.getKeyPass() : "";
//                keyMaterial.load(new ByteArrayInputStream(keyStore.getKeyBytes()), storePass.toCharArray());
//                builder.loadKeyMaterial(keyMaterial, keyPass.toCharArray());
//            } catch (Exception e) {
//                LoggerUtil.error(logger, "获取客户端ssl证书异常，异常信息：{}", e);
//            }
//        }
//        try {
//            if (trustStore != null) {
//                //注意测试，怕影响到其他渠道
//                KeyStore trustMaterial = KeyStore.getInstance(null!=trustStore.getKeyStoreType() ? trustStore.getKeyStoreType().getValue() : KeyStore.getDefaultType());
//                String storePass = StringUtils.isNotEmpty(trustStore.getStroePass()) ? trustStore.getStroePass() : "";
//                //  String keyPass = StringUtils.isNotEmpty(trustStore.getKeyPass())?trustStore.getKeyPass():"";
//                trustMaterial.load(new ByteArrayInputStream(trustStore.getKeyBytes()), storePass.toCharArray());
//                builder.loadTrustMaterial(trustMaterial, new TrustStrategy() {
//                    public boolean isTrusted(X509Certificate[] arg0, String arg1)
//                            throws CertificateException {
//                        return true; //始终信任 ，绕过检查
//                    }
//                });
//            }
//            sslContext = builder.build();
//        } catch (Exception e) {
//            LoggerUtil.error(logger, "获取服务端ssl证书异常，异常信息：{}", e);
//        }
//
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                sslContext,
//                new String[]{"TLSv1"},
//                null,
//                new HostnameVerifier() {
//                    //验证访问地址
//                    @Override
//                    public boolean verify(String s, SSLSession sslSession) {
//                        return true;
//                    }
//                });
//        return sslsf;
//    }

//
//    public static void main(String[] args) throws Exception {
//        // KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        // keyStore.load(new FileInputStream("d:\\key\\ybs_test.jks") , "msxf_2016".toCharArray());
//        KeyStoreObj keyStoreObj = new KeyStoreObj();
//        keyStoreObj.setStroePass("msxf_2016");
//        keyStoreObj.setKeyPass("msxf_2016");
//        keyStoreObj.setKeyBytes(IOUtils.toByteArray(new FileInputStream("d:\\key\\ybs_https_test.keystore")));
//
//
//        KeyStoreObj turstStoreObj = new KeyStoreObj();
//        turstStoreObj.setStroePass("msxf_2016");
//        turstStoreObj.setKeyPass("msxf_2016");
//        turstStoreObj.setKeyBytes(IOUtils.toByteArray(new FileInputStream("d:\\key\\ybs_ca_test.keystore")));
//
//        CloseableHttpClient httpClient = (CloseableHttpClient) create().setTrustStore(turstStoreObj).setKeyStore(keyStoreObj).build();
//
//        HttpPost httpPost = new HttpPost("https://219.133.37.94:8886");
//        httpPost.addHeader("Content-Type", "text/xml;charset=utf-8");
//        StringEntity se = new StringEntity("");
//        httpPost.setEntity(se);
//        CloseableHttpResponse resp = httpClient.execute(httpPost);
//        System.out.println(resp.getStatusLine());
//        if (resp.getStatusLine().getStatusCode() == 200) {
//            HttpEntity resEntity = resp.getEntity();
//            String message = EntityUtils.toString(resEntity, "GBK");
//            System.out.println(message);
//        } else {
//            HttpEntity resEntity = resp.getEntity();
//            String message = EntityUtils.toString(resEntity, "GBK");
//            System.out.println(message);
//            System.out.println("请求失败");
//        }
//
//    }
}