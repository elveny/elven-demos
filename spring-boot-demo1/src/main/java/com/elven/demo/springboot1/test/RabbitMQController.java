/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test;

import com.elven.demo.springboot1.common.constants.MqConstant;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiusheng.wu
 * @Filename RabbitMQController.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/17 15:09</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
@RestController("rabbitMQController")
@RequestMapping("/spirngbootdemo1/rabbitMQ")
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin rabbitAdmin;

    @RequestMapping("send/{content}")
    public String sendMessage(@PathVariable("content") String content){

        rabbitTemplate.convertAndSend(MqConstant.DEMO_EXCHANGE_TOPIC, MqConstant.DEMO_ROUTING_KEY, content);

        return "success";
    }

    @RequestMapping("deleteQueue/{queueName}")
    public String deleteQueue(@PathVariable("queueName") String queueName){

        boolean deleteFlag = rabbitAdmin.deleteQueue(queueName);

        return "success?"+deleteFlag;
    }

    @RequestMapping("deleteExchange/{exchangeName}")
    public String deleteExchange(@PathVariable("exchangeName") String exchangeName){

        boolean deleteFlag = rabbitAdmin.deleteExchange(exchangeName);

        return "success?"+deleteFlag;
    }
}