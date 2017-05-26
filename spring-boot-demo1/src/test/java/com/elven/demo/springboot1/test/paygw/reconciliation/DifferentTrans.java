/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.paygw.reconciliation;

/**
 * @author qiusheng.wu
 * @Filename DifferentTrans.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/25 11:30</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class DifferentTrans {
    private Trans left;
    private Trans right;

    public DifferentTrans() {
    }

    public DifferentTrans(Trans left, Trans right) {
        this.left = left;
        this.right = right;
    }

    public Trans getLeft() {
        return left;
    }

    public void setLeft(Trans left) {
        this.left = left;
    }

    public Trans getRight() {
        return right;
    }

    public void setRight(Trans right) {
        this.right = right;
    }
}