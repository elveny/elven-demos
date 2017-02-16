/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1;

import com.rabbitmq.client.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author qiusheng.wu
 * @Filename RabbitMQTutorialTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/16 13:17</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RabbitMQTutorialTest {

    ConnectionFactory connectionFactory = new ConnectionFactory();

    private final static String QUEUE_NAME = "hello";

    @Before
    public void init(){
        connectionFactory.setHost("10.16.31.78");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("dev");
        connectionFactory.setPassword("dev");
        connectionFactory.setVirtualHost("dev");
    }

    @Test
    public void helloWorldSender() throws IOException, TimeoutException {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }

    @Test
    public void helloWorldReceiver() throws IOException, TimeoutException {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}