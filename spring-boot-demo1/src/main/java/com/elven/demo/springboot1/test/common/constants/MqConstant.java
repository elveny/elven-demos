/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common.constants;

/**
 * @author qiusheng.wu
 * @Filename MqConstant.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/17 10:32</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public interface MqConstant {
    String DEMO_EXCHANGE_TOPIC = "DEMO-EXCHANGE-TOPIC";
    String DEMO_EXCHANGE_FANOUT = "DEMO-EXCHANGE-FANOUT";

    String DEMO_QUEUE = "DEMO-QUEUE";
    String DEMO_ROUTING_KEY = "DEMO-ROUTING-KEY";
    String DEMO_BINDING_KEY = "DEMO-ROUTING-KEY";
}