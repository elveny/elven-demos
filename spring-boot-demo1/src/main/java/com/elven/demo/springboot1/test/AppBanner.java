package com.elven.demo.springboot1.test;/**
 * Created by qiusheng.wu on 2017/1/3.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * spring boot banner
 *
 * @author qiusheng.wu
 * @Created 2017/1/3.
 */
public class AppBanner implements Banner{
    private Logger logger = LoggerFactory.getLogger(AppBanner.class);

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        System.out.println("::::::::::::::::::::::::elven-demos::::::::::::::::::::::::::");
        System.out.println(":              spring-boot-demo1 start                      :");
        System.out.println(":::::::::::::::::::::::::end:::::::::::::::::::::::::::::::::");
    }
}
