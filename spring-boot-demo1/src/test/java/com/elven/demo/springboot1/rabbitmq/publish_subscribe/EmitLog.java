/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.rabbitmq.publish_subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author qiusheng.wu
 * @Filename EmitLog.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/16 15:21</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class EmitLog {
    private static final String EXCHANGE_NAME = "logs1";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("10.16.31.78");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("dev");
        connectionFactory.setPassword("dev");
        connectionFactory.setVirtualHost("dev");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = getMessage(argv);

        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

    private static String getMessage(String[] strings){
        if (strings.length < 1)
            return "info: Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}