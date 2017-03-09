/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.test.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename TestService.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/3/9 11:37</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class TestService {
    private Map<String, Integer> packageMap = new HashMap<String, Integer>();

    public synchronized boolean updatePackageCount(String assetPackageNo) throws InterruptedException {

//        synchronized (assetPackageNo){
            Integer count = packageMap.get(assetPackageNo);
            if(count == null){
                count = 0;
            }
            Thread.sleep(1000);
            count++;
            packageMap.put(assetPackageNo, count);
//        }

        return true;
    }

    public int getPackageCount(String assetPackageNo){
        Integer count = packageMap.get(assetPackageNo);
        if(count == null){
            count = 0;
        }
        return count;
    }
}