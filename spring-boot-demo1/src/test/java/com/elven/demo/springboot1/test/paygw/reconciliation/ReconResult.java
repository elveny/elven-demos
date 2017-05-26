/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.paygw.reconciliation;

import java.util.List;

/**
 * @author qiusheng.wu
 * @Filename ReconResult.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/25 11:22</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class ReconResult {
    private List<Trans> leftMoreTrans;
    private List<Trans> rightMoreTrans;
    private List<DifferentTrans> differentTrans;

    public ReconResult() {
    }

    public ReconResult(List<Trans> leftMoreTrans, List<Trans> rightMoreTrans, List<DifferentTrans> differentTrans) {
        this.leftMoreTrans = leftMoreTrans;
        this.rightMoreTrans = rightMoreTrans;
        this.differentTrans = differentTrans;
    }

    public List<Trans> getLeftMoreTrans() {
        return leftMoreTrans;
    }

    public void setLeftMoreTrans(List<Trans> leftMoreTrans) {
        this.leftMoreTrans = leftMoreTrans;
    }

    public List<Trans> getRightMoreTrans() {
        return rightMoreTrans;
    }

    public void setRightMoreTrans(List<Trans> rightMoreTrans) {
        this.rightMoreTrans = rightMoreTrans;
    }

    public List<DifferentTrans> getDifferentTrans() {
        return differentTrans;
    }

    public void setDifferentTrans(List<DifferentTrans> differentTrans) {
        this.differentTrans = differentTrans;
    }
}