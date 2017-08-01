package com.jd.jr.pay.demo.model;

import com.jd.jr.pay.gate.signature.vo.JdPayBaseResponse;

/**
 * Created by zhouzhenjiang on 2017/1/5.
 */
public class FkmPayResponse extends JdPayBaseResponse {

	private static final long serialVersionUID = 1L;

	private String tradeNum;

    private Long amount;

    private String currency;

    private String tradeTime;

    private String note;

    private Integer status;
    
    private String sign;

    public FkmPayResponse() {
    }

    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "FkmPayResponse [tradeNum=" + tradeNum + ", amount=" + amount + ", currency=" + currency + ", tradeTime="
				+ tradeTime + ", note=" + note + ", status=" + status + ", sign=" + sign + "]" + super.toString();
	}
    
}
