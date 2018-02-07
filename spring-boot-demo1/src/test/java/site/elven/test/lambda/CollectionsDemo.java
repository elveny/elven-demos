/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.lambda;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author qiusheng.wu
 * @Filename CollectionsDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/2 17:34</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        Collections.sort(Arrays.asList("1", "2", "3"), (o1, o2) -> {return o1.hashCode() > o2.hashCode() ? 1: 0;});
    }

}