package com.elven.demo.springboot1;

/**
 * Created by qiusheng.wu on 2017/1/3.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * spring boot start listener
 *
 * @author qiusheng.wu
 * @Created 2017/1/3.
 */
public class AppStartListener implements SpringApplicationRunListener {

    private Logger logger = LoggerFactory.getLogger(AppStartListener.class);

    public AppStartListener(SpringApplication application, String[] args) {
        Thread.currentThread().setName("spring-boot-demo1");
        application.setBanner(new AppBanner());
    }

    @Override
    public void started() {
        logger.info("started");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        logger.info("environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        logger.info("contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        logger.info("contextLoaded");
    }

    @Override
    public void finished(ConfigurableApplicationContext context, Throwable exception) {
        logger.info("finished");
    }
}
