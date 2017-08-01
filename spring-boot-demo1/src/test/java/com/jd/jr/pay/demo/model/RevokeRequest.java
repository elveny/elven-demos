package com.jd.jr.pay.demo.model;


import com.jd.jr.pay.gate.signature.vo.JdPayBaseRequest;

import java.io.Serializable;

/**
 * Created by zhouzhenjiang on 2016/12/13.
 */
public class RevokeRequest extends JdPayBaseRequest implements Serializable {

    /**
     * 交易流水号
     */
    private String tradeNum;

    /**
     * 原交易流水号
     */
    private String oTradeNum;

    /**
     * 交易金额
     */
    private Long amount;

    /**
     * 交易币种
     */
    private String currency;

    /**
     * 交易时间
     */
    private String tradeTime;

    /**
     * 交易备注
     */
    private String note;

    /**
     * 商户公钥证书
     */
    private String cert;

    private String Device;

    public RevokeRequest() {
    }


    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    public String getoTradeNum() {
        return oTradeNum;
    }

    public void setoTradeNum(String oTradeNum) {
        this.oTradeNum = oTradeNum;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }
}
