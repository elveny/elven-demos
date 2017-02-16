/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.rabbitmq.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * @author qiusheng.wu
 * @Filename RPCServer.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/16 16:36</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RPCServer {
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    private static int fib(int n) {
        if (n ==0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }

    public static void main(String[] argv) {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("10.16.31.78");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("dev");
        connectionFactory.setPassword("dev");
        connectionFactory.setVirtualHost("dev");

        Connection connection = null;
        try {
            connection      = connectionFactory.newConnection();
            final Channel channel = connection.createChannel();

            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);

            channel.basicQos(1);

            System.out.println(" [x] Awaiting RPC requests");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                            .Builder()
                            .correlationId(properties.getCorrelationId())
                            .build();

                    String response = "";

                    try {
                        String message = new String(body,"UTF-8");
                        int n = Integer.parseInt(message);

                        System.out.println(" [.] fib(" + message + ")");
                        response += fib(n);
                    }
                    catch (RuntimeException e){
                        System.out.println(" [.] " + e.toString());
                    }
                    finally {
                        channel.basicPublish( "", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));

                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                }
            };

            channel.basicConsume(RPC_QUEUE_NAME, false, consumer);

            //loop to prevent reaching finally block
            while(true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException _ignore) {}
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (IOException _ignore) {}
        }
    }
}