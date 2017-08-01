package com.jd.jr.pay.demo.model;

import com.jd.jr.pay.gate.signature.vo.JdPayBaseResponse;

import java.io.Serializable;

/**
 * Created by zhouzhenjiang on 2016/12/13.
 */
public class RevokeResponse extends JdPayBaseResponse implements Serializable {

    private  String tradeNum;

    private String tradeType;

    private String oTradeNum;

    private Long amount;

    private String currency;

    private String tradeTime;

    private String note;

    private Integer status;

    public RevokeResponse() {
    }

    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
