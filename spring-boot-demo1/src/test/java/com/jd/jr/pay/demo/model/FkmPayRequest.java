package com.jd.jr.pay.demo.model;

import java.io.Serializable;
import java.util.Map;

import com.jd.jr.pay.gate.signature.vo.JdPayBaseRequest;

public class FkmPayRequest  extends JdPayBaseRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
     * 付款码
     */
    private String token;

    /**
     * 设备号
     */
    private String device;

    /**
     * 交易流水号
     */
    private String tradeNum;

    /**
     * 交易名称
     */
    private String tradeName;

    /**
     * 交易描述
     */
    private String tradeDesc;

    /**
     * 交易时间
     */
    private String tradeTime;

    /**
     * 交易金额
     */
    private Long amount;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 业务类型
     */
    private String industryCategory;

    /**
     * 货币种类
     */
    private String currency;

    /**
     * 商户备注
     */
    private String note;

    /**
     * 异步通知页面地址
     */
    private String notifyUrl;

    /**
     * 订单商品数量
     */
    private String orderGoodsNum;

    /**
     * 厂商编码
    */
    private String vendorId;

    /**
     * 支付商户名
     */
    private String payMerchant;
    
    /**
     * 商品信息列表
     */
    private String goodsInfoList;

    /**
     * 收货信息
     */
    private String receiverInfo;

    /**
     * 收货信息
     */
    private String termInfo;
    
    private String riskInfo;

    private Map<String,String> extendParam;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTradeDesc() {
		return tradeDesc;
	}

	public void setTradeDesc(String tradeDesc) {
		this.tradeDesc = tradeDesc;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getIndustryCategory() {
		return industryCategory;
	}

	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getOrderGoodsNum() {
		return orderGoodsNum;
	}

	public void setOrderGoodsNum(String orderGoodsNum) {
		this.orderGoodsNum = orderGoodsNum;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getPayMerchant() {
		return payMerchant;
	}

	public void setPayMerchant(String payMerchant) {
		this.payMerchant = payMerchant;
	}

	public String getGoodsInfoList() {
		return goodsInfoList;
	}

	public void setGoodsInfoList(String goodsInfoList) {
		this.goodsInfoList = goodsInfoList;
	}

	public String getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(String receiverInfo) {
		this.receiverInfo = receiverInfo;
	}

	public String getTermInfo() {
		return termInfo;
	}

	public void setTermInfo(String termInfo) {
		this.termInfo = termInfo;
	}

	public Map<String, String> getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(Map<String, String> extendParam) {
		this.extendParam = extendParam;
	}

	public String getRiskInfo() {
		return riskInfo;
	}

	public void setRiskInfo(String riskInfo) {
		this.riskInfo = riskInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FkmPayRequest [token=");
		builder.append(token);
		builder.append(", device=");
		builder.append(device);
		builder.append(", tradeNum=");
		builder.append(tradeNum);
		builder.append(", tradeName=");
		builder.append(tradeName);
		builder.append(", tradeDesc=");
		builder.append(tradeDesc);
		builder.append(", tradeTime=");
		builder.append(tradeTime);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", orderType=");
		builder.append(orderType);
		builder.append(", industryCategory=");
		builder.append(industryCategory);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", note=");
		builder.append(note);
		builder.append(", notifyUrl=");
		builder.append(notifyUrl);
		builder.append(", orderGoodsNum=");
		builder.append(orderGoodsNum);
		builder.append(", vendorId=");
		builder.append(vendorId);
		builder.append(", payMerchant=");
		builder.append(payMerchant);
		builder.append(", goodsInfoList=");
		builder.append(goodsInfoList);
		builder.append(", receiverInfo=");
		builder.append(receiverInfo);
		builder.append(", termInfo=");
		builder.append(termInfo);
		builder.append(", riskInfo=");
		builder.append(riskInfo);
		builder.append(", extendParam=");
		builder.append(extendParam);
		builder.append("]");
		return builder.toString();
	}

	

}
