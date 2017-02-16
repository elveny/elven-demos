/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author qiusheng.wu
 * @Filename RabbitMqReceiverTest1.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/9 16:59</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class RabbitMqReceiverTest1 {
    ConnectionFactory factory = new ConnectionFactory();

    @Before
    public void init(){
        RabbitMqSenderTest1.createFactory();
    }

    @Test
    public void receiver1() throws IOException, TimeoutException, InterruptedException {


        Connection conn = factory.newConnection(); //获取一个链接
        //通过Channel进行通信
        Channel channel = conn.createChannel();
        int prefetchCount = 1;
        channel.basicQos(prefetchCount); //保证公平分发

        boolean durable = true;
        //声明交换机
        channel.exchangeDeclare(RabbitMqSenderTest1.EXCHANGE_NAME, "direct", durable); //按照routingKey过滤


        //声明队列
        String queueName = channel.queueDeclare("queue-01", true, true, false, null).getQueue();
        //将队列和交换机绑定
        String routingKey = "lkl-0";
        //队列可以多次绑定，绑定不同的交换机或者路由key
        channel.queueBind(queueName, RabbitMqSenderTest1.EXCHANGE_NAME, routingKey);

        //创建消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //将消费者和队列关联
        channel.basicConsume(queueName, false, consumer); // 设置为false表面手动确认消息消费

        //获取消息

        System.out.println(" Wait message ....");
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            String key = delivery.getEnvelope().getRoutingKey();

            System.out.println("  Received '" + key + "':'" + msg + "'");
            System.out.println(" Handle message");
            TimeUnit.SECONDS.sleep(3); //mock handle message
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); //确定该消息已成功消费
        }
    }

    @Test
    public void receiver2() throws IOException, InterruptedException, TimeoutException {

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        /*确保这里的队列是存在的*/
        channel.queueDeclare(RabbitMqSenderTest1.QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        /*这里用到了额外的类QueueingConsumer来缓存服务器将要推过来的消息。我们通知服务器向接收端推送消息，
　　　然后服务器将会向客户端异步推送消息，这里提供了一个可以回调的对象来缓存消息，直到我们做好准备来使用
　　  它，这个类就是QueueingConsumer*/
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(RabbitMqSenderTest1.QUEUE_NAME, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }

    }

    @Test
    public void receiver3_1() throws IOException, TimeoutException, InterruptedException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明消息路由的名称和类型
        channel.exchangeDeclare(RabbitMqSenderTest1.EXCHANGE_NAME, "fanout");
        //声明一个随机消息队列
        String queueName = channel.queueDeclare().getQueue();

        System.out.println(" [*] I'm "+queueName);

        //绑定消息队列和消息路由
        channel.queueBind(queueName, RabbitMqSenderTest1.EXCHANGE_NAME, "");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //启动一个消费者
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println(" [x] Received '" + message + "'");
        }
    }

    @Test
    public void receiver3_2() throws IOException, TimeoutException, InterruptedException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明消息路由的名称和类型
        channel.exchangeDeclare(RabbitMqSenderTest1.EXCHANGE_NAME, "fanout");
        //声明一个随机消息队列
        String queueName = channel.queueDeclare().getQueue();

        System.out.println(" [*] I'm "+queueName);

        //绑定消息队列和消息路由
        channel.queueBind(queueName, RabbitMqSenderTest1.EXCHANGE_NAME, "");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //启动一个消费者
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println(" [x] Received '" + message + "'");
        }
    }

    @Test
    public void receiver4_1() throws IOException, TimeoutException, InterruptedException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明消息路由的名称和类型
        channel.exchangeDeclare(RabbitMqSenderTest1.EXCHANGE_NAME1, "direct");
        //声明一个随机消息队列
//        String queueName = channel.queueDeclare().getQueue();
        String queueName = "paycore.test-queue.wuqs1";

        System.out.println(" [*] I'm "+queueName);

        channel.queueDeclare(queueName, false, false, false, null);

        //绑定消息队列和消息路由
        String routingKey = "black";
        channel.queueBind(queueName, RabbitMqSenderTest1.EXCHANGE_NAME1, routingKey);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //启动一个消费者
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println(" [x] Received '" + message + "'");
        }
    }

    @Test
    public void receiver4_2() throws IOException, TimeoutException, InterruptedException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明消息路由的名称和类型
        channel.exchangeDeclare(RabbitMqSenderTest1.EXCHANGE_NAME1, "direct");
        //声明一个随机消息队列
//        String queueName = channel.queueDeclare().getQueue();
        String queueName = "paycore.test-queue.wuqs2";

        channel.queueDeclare(queueName, false, false, false, null);
        System.out.println(" [*] I'm "+queueName);

        //绑定消息队列和消息路由
        String routingKey = "green";
        channel.queueBind(queueName, RabbitMqSenderTest1.EXCHANGE_NAME1, routingKey);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //启动一个消费者
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println(" [x] Received '" + message + "'");
        }
    }

    @Test
    public void receiver5_1() throws IOException, TimeoutException, InterruptedException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明消息路由的名称和类型
        channel.exchangeDeclare(RabbitMqSenderTest1.EXCHANGE_NAME2, "topic");
        //声明一个随机消息队列
//        String queueName = channel.queueDeclare().getQueue();
        String queueName = "paycore.test-queue.wuqs5_1";

        channel.queueDeclare(queueName, false, false, false, null);
        System.out.println(" [*] I'm "+queueName);

        //绑定消息队列和消息路由
        String routingKey = "lazy";
        System.out.println(" [*] my routing key is "+routingKey);
        channel.queueBind(queueName, RabbitMqSenderTest1.EXCHANGE_NAME2, routingKey);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //启动一个消费者
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println(" [x] Received '" + message + "'");
        }
    }

    @Test
    public void receiver5_2() throws IOException, TimeoutException, InterruptedException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明消息路由的名称和类型
        channel.exchangeDeclare(RabbitMqSenderTest1.EXCHANGE_NAME2, "topic");
        //声明一个随机消息队列
//        String queueName = channel.queueDeclare().getQueue();
        String queueName = "paycore.test-queue.wuqs5_2";

        channel.queueDeclare(queueName, false, false, false, null);
        System.out.println(" [*] I'm "+queueName);

        //绑定消息队列和消息路由
        String routingKey = "*.*.rabbit";
        channel.queueBind(queueName, RabbitMqSenderTest1.EXCHANGE_NAME2, routingKey);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //启动一个消费者
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println(" [x] Received '" + message + "'");
        }
    }

    @Test
    public void receiver5_3() throws IOException, TimeoutException, InterruptedException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明消息路由的名称和类型
        channel.exchangeDeclare(RabbitMqSenderTest1.EXCHANGE_NAME2, "topic");
        //声明一个随机消息队列
//        String queueName = channel.queueDeclare().getQueue();
        String queueName = "paycore.test-queue.wuqs5_3";

        channel.queueDeclare(queueName, false, false, false, null);
        System.out.println(" [*] I'm "+queueName);

        //绑定消息队列和消息路由
        String routingKey = "lazy.#";
        System.out.println(" [*] my routing key is "+routingKey);
        channel.queueBind(queueName, RabbitMqSenderTest1.EXCHANGE_NAME2, routingKey);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //启动一个消费者
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println(" [x] Received '" + message + "'");
        }
    }

    @Test
    public void receiver5_4() throws IOException, TimeoutException, InterruptedException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明消息路由的名称和类型
        channel.exchangeDeclare(RabbitMqSenderTest1.EXCHANGE_NAME2, "topic");
        //声明一个随机消息队列
//        String queueName = channel.queueDeclare().getQueue();
        String queueName = "paycore.test-queue.wuqs5_4";

        channel.queueDeclare(queueName, false, false, false, null);
        System.out.println(" [*] I'm "+queueName);

        //绑定消息队列和消息路由
        String routingKey = "lazy.rabbit";
        System.out.println(" [*] my routing key is "+routingKey);
        channel.queueBind(queueName, RabbitMqSenderTest1.EXCHANGE_NAME2, routingKey);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //启动一个消费者
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println(" [x] Received '" + message + "'");
        }
    }

}