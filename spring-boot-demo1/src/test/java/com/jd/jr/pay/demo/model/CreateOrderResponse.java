/**   
 * @Title: CreateOrderResponse.java 
 * @Package com.jd.jr.pay.demo.model 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author mythling   
 * @date 2016年9月28日 下午6:03:40 
 * @version V1.0   
 */
package com.jd.jr.pay.demo.model;

import com.jd.jr.pay.gate.signature.vo.JdPayBaseResponse;

/**
 * @ClassName: CreateOrderResponse
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author mythling
 * @date 2016年9月28日 下午6:03:40
 * @version V1.0
 */
public class CreateOrderResponse extends JdPayBaseResponse {
	private String orderId;

	private String merchantName;

	private String amount;

	private String tradeNum;

	private String qrCode;

	private String expireTime;

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * @param merchantName
	 *            the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the tradeNum
	 */
	public String getTradeNum() {
		return tradeNum;
	}

	/**
	 * @param tradeNum
	 *            the tradeNum to set
	 */
	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}

	/**
	 * @return the qrCode
	 */
	public String getQrCode() {
		return qrCode;
	}

	/**
	 * @param qrCode
	 *            the qrCode to set
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	/**
	 * @return the expireTime
	 */
	public String getExpireTime() {
		return expireTime;
	}

	/**
	 * @param expireTime
	 *            the expireTime to set
	 */
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	/** 
	 * @Title:        toString 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @return    
	 * @throws 
	 * @author       mythling
	 * @Date          2016年9月28日 下午6:38:33 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateOrderResponse [orderId=");
		builder.append(orderId);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", tradeNum=");
		builder.append(tradeNum);
		builder.append(", qrCode=");
		builder.append(qrCode);
		builder.append(", expireTime=");
		builder.append(expireTime);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	

}
