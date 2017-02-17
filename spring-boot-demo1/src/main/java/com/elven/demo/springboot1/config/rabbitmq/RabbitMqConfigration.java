/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qigang.chen
 * @Filename RabbitMqConfigration.java
 * @description
 * @Version 1.0
 * @History <li>Author: qigang.chen</li>
 * <li>Date: 2017/1/10 9:39</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
@Configuration
@EnableConfigurationProperties(RabbitProperties.class)
@EnableRabbit
public class RabbitMqConfigration {

    @Bean
    public AmqpAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageConverter messageConverter() {
        //Kroty转换器没测通
        //return new KryoMessageConvertor();
        return new Jackson2JsonMessageConverter();
    }

     /**
       * @Description 配置mq连接工厂
       *
       * @Params
       * @Return
       * @Exceptions
       */
    @Bean
    public ConnectionFactory connectionFactory(RabbitProperties rabbitProperties) throws Exception {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitProperties.getHost());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
        connectionFactory.setAddresses(rabbitProperties.getAddresses());
        return connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                               MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;

    }

     /**
       * @Description rabbitmq消息模板
       *
       * @Params
       * @Return
       * @Exceptions
       */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }



    /***
     * 配置 topic 类型exchange
     * **/
    @Bean
    public TopicExchange topicExchange(AmqpAdmin rabbitAdmin) {
        TopicExchange topicExchange = new TopicExchange(MqConstant.DEMO_EXCHANGE_TOPIC, true, false);
        rabbitAdmin.declareExchange(topicExchange);
        return topicExchange;
    }

    /***
     * 配置 fanout 类型exchange
     * **/
    @Bean
    public FanoutExchange fanoutExchange(AmqpAdmin rabbitAdmin) {
        FanoutExchange fanoutExchange = new FanoutExchange(MqConstant.DEMO_EXCHANGE_FANOUT,true,false);
        rabbitAdmin.declareExchange(fanoutExchange);
        return fanoutExchange;
    }

    /**
     * 支付核心发送过来的消息队列
     **/
    @Bean
    public Queue payCoreQueue(AmqpAdmin rabbitAdmin) {
        Queue payCoreQueue = new Queue(MqConstant.DEMO_QUEUE,true);
        rabbitAdmin.declareQueue(payCoreQueue);
        return payCoreQueue;
    }

     /**
       * @Description 申明绑定
       *
       * @Params
       * @Return
       * @Exceptions
       */
    @Bean
    public Binding bindingExchangePayCoreQueue(Queue payCoreQueue, TopicExchange exchange, AmqpAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(payCoreQueue).to(exchange).with(MqConstant.DEMO_ROUTING_KEY);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

}