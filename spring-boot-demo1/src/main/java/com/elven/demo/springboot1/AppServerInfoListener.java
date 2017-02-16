package com.elven.demo.springboot1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author qiusheng.wu
 * @Created 2017/1/3.
 */
public class AppServerInfoListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    private Logger logger = LoggerFactory.getLogger(AppServerInfoListener.class);
    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        System.setProperty("PORT", String.valueOf(event.getEmbeddedServletContainer().getPort()));
        String host = "";
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("获取端口号失败:", e);
        }
        System.setProperty("IP", host);
        System.setProperty("START_TIME", ""+new Date());
    }
}
