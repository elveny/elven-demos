/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.org.apache.commons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author qiusheng.wu
 * @Filename StringUtilsTester.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/4/17 13:34</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class StringUtilsTester {

    @Test
    public void join(){
        String ss = StringUtils.join(1,2,3);
        System.out.println(ss);
    }
}