package com.jd.jr.pay.demo.model;

import java.io.Serializable;

public class UniOrderReqInner implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6358684210627390942L;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 商户号
	 */
	private String merchant;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 商户号
	 */
	private String merchantNo;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 商户交易号
	 */
	private String merchantOrderId;
	/**
	 * 商户业务单号
	 */
	private String merchantBizNo;
	/**
	 * pin
	 */
	private String pin;
	/**
	 * 商户用户ID
	 */
	private String merchantUserId;
	/**
	 * 商户用户类型
	 */
	private String merchantUserType;
	/**
	 * 交易类型
	 */
	private String tradeType;
	/**
	 * 交易详情
	 */
	private String tradeDetail;
	/**
	 * 异步通知URL
	 */
	private String notifyUrl;
	/**
	 * 支付成功回调地址
	 */
	private String successCallbackUrl;
	/**
	 * 支付失败回调地址
	 */
	private String errorCallbackUrl;
	/**
	 * 币种
	 */
	private String currency;
	/**
	 * 应收交易金额
	 */
	private Long amount;
	/**
	 * 交易日期
	 */
	private String tradeDate;
	/**
	 * 商户通知信息（商户传入，原封不动回传给商户）
	 */
	private String tradeNote;
	/**
	 * 交易来源
	 */
	private String tradeSource;
	/**
	 * 提交者商户号
	 */
	private String submitterMerchantNo;
	/**
	 * 设备号
	 */
	private String device;
	/**
	 * 交易名称（商户提供的订单的标题/商品名称/关键字等）
	 */
	private String tradeName;
	/**
	 * 指定卡id
	 */
	private String specCardNo;
	/**
	 * 指定身份证
	 */
	private String specId;
	/**
	 * 指定姓名
	 */
	private String specName;
	/**
	 * 交易过期时间（可选）,单位秒
	 */
	private String expireTime;

	/**
	 * 订单类型 0-实物，1-虚拟
	 */
	private String orderType;

	/**
	 * 行业分类码
	 */
	private String industryCategoryCode;
	/**
	 * 安全证书
	 */
	private String cert;

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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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

	public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public String getMerchantBizNo() {
		return merchantBizNo;
	}

	public void setMerchantBizNo(String merchantBizNo) {
		this.merchantBizNo = merchantBizNo;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getMerchantUserId() {
		return merchantUserId;
	}

	public void setMerchantUserId(String merchantUserId) {
		this.merchantUserId = merchantUserId;
	}

	public String getMerchantUserType() {
		return merchantUserType;
	}

	public void setMerchantUserType(String merchantUserType) {
		this.merchantUserType = merchantUserType;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeDetail() {
		return tradeDetail;
	}

	public void setTradeDetail(String tradeDetail) {
		this.tradeDetail = tradeDetail;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getSuccessCallbackUrl() {
		return successCallbackUrl;
	}

	public void setSuccessCallbackUrl(String successCallbackUrl) {
		this.successCallbackUrl = successCallbackUrl;
	}

	public String getErrorCallbackUrl() {
		return errorCallbackUrl;
	}

	public void setErrorCallbackUrl(String errorCallbackUrl) {
		this.errorCallbackUrl = errorCallbackUrl;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeNote() {
		return tradeNote;
	}

	public void setTradeNote(String tradeNote) {
		this.tradeNote = tradeNote;
	}

	public String getTradeSource() {
		return tradeSource;
	}

	public void setTradeSource(String tradeSource) {
		this.tradeSource = tradeSource;
	}

	public String getSubmitterMerchantNo() {
		return submitterMerchantNo;
	}

	public void setSubmitterMerchantNo(String submitterMerchantNo) {
		this.submitterMerchantNo = submitterMerchantNo;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getSpecCardNo() {
		return specCardNo;
	}

	public void setSpecCardNo(String specCardNo) {
		this.specCardNo = specCardNo;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getIndustryCategoryCode() {
		return industryCategoryCode;
	}

	public void setIndustryCategoryCode(String industryCategoryCode) {
		this.industryCategoryCode = industryCategoryCode;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

}
