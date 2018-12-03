/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.guava.eventBus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author qiusheng.wu
 * @Filename DeadEventListener.java
 * @description 如果EventBus发送的消息都不是订阅者关心的称之为Dead Event。
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 18:04</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class DeadEventListener {
    boolean isDelivered=true;

    @Subscribe
    public void listen(DeadEvent event){
        isDelivered=false;
        System.out.println(event.getSource().getClass()+"  "+event.getEvent()); //source通常是EventBus
    }

    public boolean isDelivered() {
        return isDelivered;
    }

}