/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.guava.eventBus;

/**
 * @author qiusheng.wu
 * @Filename OrderEvent.java
 * @description Guava 发布-订阅模式中传递的事件,是一个普通的POJO类
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 18:00</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class OrderEvent {
    private String message;

    public OrderEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}