package com.wangyin.npp.trade.demo.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	 private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-base.xml");
	    public static Object getBean(String beanName){
	        return applicationContext.getBean(beanName);
	    }
}
