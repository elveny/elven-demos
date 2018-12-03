/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.test;

import com.elven.demo.springboot1.test.common.constants.MqConstant;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author qiusheng.wu
 * @Filename RabbitMQListener.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/17 16:31</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
@Component
public class RabbitMQListener {

    Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    @RabbitListener(queues = MqConstant.DEMO_QUEUE, containerFactory = RabbitListenerAnnotationBeanPostProcessor.DEFAULT_RABBIT_LISTENER_CONTAINER_FACTORY_BEAN_NAME)
    public void processMessage(String content, Channel channel, Message messageSource) throws IOException {
        // ...
        logger.info("收到消息："+content);

        channel.basicAck(messageSource.getMessageProperties().getDeliveryTag(), false);
    }
}