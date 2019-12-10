/**
 * elven.site Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package site.elven.test.java.common;

import org.junit.Test;

/**
 * @Filename CommonTest1.java
 *
 * @description
 *
 * @Version 1.0
 *
 * @author elven
 * @History
 *
 * <li>Author: elven</li>
 * <li>Date: 2019/10/22 23:22</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CommonTest1 {

    @Test
    public void test1(){

        System.out.println(hashCode("fdafdsa"));

    }

    private int hashCode(Object key){
        int h;
        if(key == null){
            return 0;
        }
        else {
            h = key.hashCode();
            System.out.println("h = " + h);
            int rightShift = (h >>> 16);
            System.out.println("rightShift = " + rightShift);
            return h ^ rightShift;
        }
    }
}