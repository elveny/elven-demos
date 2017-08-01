package com.jd.jr.pay.demo.model;

import java.io.Serializable;


public class TradeQueryInner implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1626380259485072656L;
	private String version="";
    private String merchant="";
    private String tradeNum="";
    private String oTradeNum="";
    private String tradeType="";
    private  String sign="";
    private  String cert="";
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getoTradeNum() {
		return oTradeNum;
	}
	public void setoTradeNum(String oTradeNum) {
		this.oTradeNum = oTradeNum;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}

}
