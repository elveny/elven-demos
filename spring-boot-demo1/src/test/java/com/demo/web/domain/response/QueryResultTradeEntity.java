package com.demo.web.domain.response;

import java.io.Serializable;

/**
 * Created by wywangzhenlong on 14-8-9.
 */
public class QueryResultTradeEntity implements Serializable {

    /**
     * 交易流水号
     */
    private String tradeNum;

    /**
     * 交易金额
     */
    private int tradeAmount;

    /**
     * 交易币种
     */
    private String tradeCurrency;

    /**
     * 交易日期(yyyyMMdd)
     */
    private String tradeDate;

    /**
     * 交易时间(HHmmss)
     */
    private String tradeTime;

    /**
     * 交易备注
     */
    private String tradeNote;

    /**
     * 交易状态  成功：0 ,退款：3 ,部分退款：4 ,处理中：6,失败：7
     */
    private String tradeStatus;
    
    /** 银行卡号*/
    private String cardNo;
    
    /** 持卡人手机号. */
    private String cardHolderMobile;
    
    /** 持卡人姓名. */
    private String cardHolderName;
    
    /** 持卡人证件号. */
    private String cardHolderId;
    /**实际支付金额*/
    private String payAmount; 
    /**银行编码*/
    private String bankCode;
    /**银行卡类型*/
    private String cardType;
    
    

    public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardHolderMobile() {
		return cardHolderMobile;
	}

	public void setCardHolderMobile(String cardHolderMobile) {
		this.cardHolderMobile = cardHolderMobile;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardHolderId() {
		return cardHolderId;
	}

	public void setCardHolderId(String cardHolderId) {
		this.cardHolderId = cardHolderId;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    public int getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(int tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getTradeCurrency() {
        return tradeCurrency;
    }

    public void setTradeCurrency(String tradeCurrency) {
        this.tradeCurrency = tradeCurrency;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTradeNote() {
        return tradeNote;
    }

    public void setTradeNote(String tradeNote) {
        this.tradeNote = tradeNote;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
}
