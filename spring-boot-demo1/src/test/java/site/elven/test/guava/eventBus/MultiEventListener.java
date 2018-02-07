/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.guava.eventBus;

import com.google.common.eventbus.Subscribe;

/**
 * @author qiusheng.wu
 * @Filename MultiEventListener.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 18:02</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MultiEventListener {
    @Subscribe
    public void listen(OrderEvent event){
        System.out.println("receive msg: "+event.getMessage());
    }

    @Subscribe
    public void listen(String message){
        System.out.println("receive msg: "+message);
    }
}