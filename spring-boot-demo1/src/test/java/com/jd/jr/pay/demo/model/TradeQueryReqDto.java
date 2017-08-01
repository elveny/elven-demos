package com.jd.jr.pay.demo.model;

import java.io.Serializable;

/**
 * 交易查询
 * </p>
 */
public class TradeQueryReqDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7573802052381870368L;

	/**
     * 访问接口版本号
     */
    public String version;

    /**
     * 交易流水号
     */
    private String tradeNum;
    private String oTradeNum="";
    /**
     * 商户号
     */
    private String merchantNum;
    /**
     * 商户签名
     */
    private String merchantSign;
    
    private String tradeType;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public String getMerchantSign() {
        return merchantSign;
    }

    public void setMerchantSign(String merchantSign) {
        this.merchantSign = merchantSign;
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
}
