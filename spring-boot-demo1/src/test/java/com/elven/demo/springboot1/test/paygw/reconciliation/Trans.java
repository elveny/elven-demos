/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.paygw.reconciliation;

import java.math.BigDecimal;

/**
 * @author qiusheng.wu
 * @Filename Trans.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/25 11:20</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class Trans {
    private String transNo;
    private String transStatus;
    private BigDecimal transAmt;

    public Trans() {
    }

    public Trans(String transNo, String transStatus, BigDecimal transAmt) {
        this.transNo = transNo;
        this.transStatus = transStatus;
        this.transAmt = transAmt;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public BigDecimal getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }
}