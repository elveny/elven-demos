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
 * @Filename RabbitMqSenderTest1.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/9 17:02</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RabbitMqSenderTest1 {
    public final static String EXCHANGE_NAME = "paycore.test-exchange.wuqs";
    public final static String EXCHANGE_NAME1 = "paycore.test-exchange.wuqs1";
    public final static String EXCHANGE_NAME2 = "paycore.test-exchange-topic.wuqs";
    public final static String QUEUE_NAME = "paycore.test-queue.wuqs";
    public final static String ROUTING_KEY = "paycore.test-routing-key.wuqs";

    static ConnectionFactory factory = new ConnectionFactory();

    public static void createFactory(){
        factory.setHost("10.16.31.78");
        factory.setPort(5672);
        factory.setUsername("dev");
        factory.setPassword("dev");
        factory.setVirtualHost("dev");
    }
    @Before
    public void init(){
        createFactory();
    }

    @Test
    public void sender1() throws IOException, TimeoutException {

        Connection conn = factory.newConnection(); //获取一个链接
        //通过Channel进行通信
        Channel channel = conn.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true); //如果消费者已创建，这里可不声明

        channel.confirmSelect(); //Enables publisher acknowledgements on this channel
        channel.addConfirmListener(new ConfirmListener() {

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("[handleNack] :" + deliveryTag + "," + multiple);

            }

            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("[handleAck] :" + deliveryTag + "," + multiple);
            }
        });

        String message = "lkl-";
        //消息持久化 MessageProperties.PERSISTENT_TEXT_PLAIN
        //发送多条信息，每条消息对应routekey都不一致
        for (int i = 0; i < 10; i++) {
            channel.basicPublish(EXCHANGE_NAME, message + (i % 2), MessageProperties.PERSISTENT_TEXT_PLAIN,
                    (message + i).getBytes());
            System.out.println("[send] msg " + (message + i) + " of routingKey is " + (message + (i % 2)));
        }

    }

    @Test
    public void sender2() throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        /*接下创建channel（信道），这是绝大多数API都能用到的。为了发送消息，你必须要声明一个消息消息队列，然后向该队列里推送消息*/
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String message = "Hello World!";
        // 第一个参数就是exchange的名字。空字符串的符号指的是默认的或没有命名的exchange：消息会根据routingKey被路由到指定的消息队列中。
        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        /*声明一个幂等的队列（只有在该队列不存在时，才会被创建）。消息的上下文是一个
        字节数组，你可以指定它的编码。*/
//        channel.close();
        connection.close();

    }

    @Test
    public void sender3() throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明exchange名字以及类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

//        channel.close();
        connection.close();
    }

    @Test
    public void sender4() throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明exchange名字以及类型
        channel.exchangeDeclare(EXCHANGE_NAME1, "direct");

        String message = "Hello World!";
        String routingKey = "black";
        channel.basicPublish(EXCHANGE_NAME1, routingKey, null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

//        channel.close();
        connection.close();
    }

    @Test
    public void sender5_1() throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明exchange名字以及类型
        channel.exchangeDeclare(EXCHANGE_NAME2, "topic");

        String message = "Hello World!";
        String routingKey = "lazy.orange.y.z";
        channel.basicPublish(EXCHANGE_NAME2, routingKey, null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

//        channel.close();
        connection.close();
    }

    @Test
    public void sender5_2() throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明exchange名字以及类型
        channel.exchangeDeclare(EXCHANGE_NAME2, "topic");

        String message = "Hello World!";
        String routingKey = "x.y.rabbit";
        channel.basicPublish(EXCHANGE_NAME2, routingKey, null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

//        channel.close();
        connection.close();
    }

    @Test
    public void sender5_3() throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明exchange名字以及类型
        channel.exchangeDeclare(EXCHANGE_NAME2, "topic");

        String message = "Hello World!";
        String routingKey = "lazy.x.y";
        channel.basicPublish(EXCHANGE_NAME2, routingKey, null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

//        channel.close();
        connection.close();
    }
}