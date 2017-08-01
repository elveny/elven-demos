package com.jd.jr.pay.demo.model;

import com.jd.jr.pay.gate.signature.vo.JdPayBaseResponse;

public class QueryRefundResponse extends JdPayBaseResponse{

	private static final long serialVersionUID = 7202174286884566369L;
	
	private String version;
	private String merchant;
	private String device;
	private String tradeNum;
	private String tradeType;
	private String oTradeNum;
	// 金额，单位分
	private Long amount;
	private String currency;
	private String tradeTime;
	private String note;
	private String status;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	/** 
	 * @Title:        toString 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @return    
	 * @throws 
	 * @author       mythling
	 * @Date          2016年4月28日 下午2:11:30 
	 */
	@Override
	public String toString() {
		return "QueryRefundResponse [version=" + version + ", merchant=" + merchant + ", device=" + device + ", tradeNum="
				+ tradeNum + ", tradeType=" + tradeType + ", oTradeNum=" + oTradeNum + ", amount=" + amount + ", currency="
				+ currency + ", tradeTime=" + tradeTime + ", note=" + note + ", status=" + status + "]";
	}
	
}
