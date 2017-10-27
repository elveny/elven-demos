package com.wangyin.npp.trade.demo.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author wangtiezhi
 * @since: 16-12-22
 * @version: 1.0.0
 */
public interface AgencyPay {
	/**
	 * 获取签约的URL
	 */
	public String getAgreementSignUrl(Map<String, String> paramMap);
	/**
	 * 协议查询
	 */
	public Map<String,String> agreementQuery(Map<String, String> paramMap);
	/**
	 * 协议解约
	 */
	public Map<String,String> agreementCancel(Map<String, String> paramMap);
	/**
	 * 代扣支付
	 * @param paramMap
	 * @return
	 */
	public Map<String,String> trade(Map<String, String> paramMap,String encryptType);
	/**
	 * 代扣交易查询
	 * @param paramMap
	 * @return
	 */
	public Map<String,String> tradeQuery(Map<String, String> paramMap);
	/**
	 * 商户余额查询
	 * @param paramMap
	 * @return
	 */
	public Map<String,String> balanceQuery(Map<String, String> paramMap);
	/**
	 * 退款交易
	 * @param paramMap
	 * @return
	 */
	public Map<String,String> RefundTrade(Map<String, String> paramMap);
	/**
	 * 退款结果查询
	 * @param paramMap
	 * @return
	 */
	public Map<String,String> RefundQuery(Map<String, String> paramMap);
	/**
	 * 退款交易的通知mock
	 * @param response
	 * @param request
	 * @return
	 */
	public Map<String,String> refundNotifyMock(HttpServletResponse response, HttpServletRequest request);
	/**
	 * 通知结果处理mock(非退款)
	 * @param response
	 * @param request
	 * @return
	 */
	public Map<String,String> NotifyMock(HttpServletResponse response, HttpServletRequest request);
}
