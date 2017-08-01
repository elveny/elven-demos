package com.jd.jr.pay.demo.model;

import java.io.Serializable;

public class UniOrderRespInner implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -391039235977444253L;
	/**
	 * 交易订单号
	 */
	private String orderId;
	/**
	 * 商户号
	 */
	private String merchantNo;
	/**
	 * 商户名称
	 */
	private String merchantName;

	/**
	 * 订单状态
	 */
	private String orderStatus;

	/**
	 * 金额
	 */
	private String amount;
	/**
	 * 过期时间，单位秒
	 */
	private Integer expireTime;

	/**
	 * 商户订单号
	 */
	private String merchantOrderId;

	/**
	 * 二维码CODE
	 */
	private String qrCode;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Integer expireTime) {
		this.expireTime = expireTime;
	}

	public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
}
