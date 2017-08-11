/**
 * msxf.com Inc.
 * Copyright (c) 2016-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author yong.zhang
 * @Filename NumberUtil.java
 * @description
 * @Version 1.0
 * @History <li>Author: yong.zhang</li>
 * <li>Date: 2016/12/27 10:11</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class NumberUtil {

     /**
       * @Description
       *
       * @Params
       * @Return
       * @Exceptions
       */
    public static Double getDoubleFrom(Object valueObj) {
        if (valueObj instanceof Number) {
            return ((Number) valueObj).doubleValue();
        }

        if ((valueObj instanceof String)) {
            return NumberUtils.toDouble((String) valueObj, Double.NaN);
        }
        return Double.NaN;
    }
}
