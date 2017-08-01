package com.jd.jr.pay.demo.action;

import com.jd.jr.pay.demo.model.BasePayOrderInfo;
import com.jd.jr.pay.demo.model.CreateOrderResponse;
import com.jd.jr.pay.demo.util.CertUtil;
import com.jd.jr.pay.demo.util.HttpsClientUtil;
import com.jd.jr.pay.demo.util.PropertyUtils;
import com.jd.jr.pay.demo.util.StringEscape;
import com.jd.jr.pay.gate.signature.util.BASE64;
import com.jd.jr.pay.gate.signature.util.JdPayUtil;
import com.jd.jr.pay.gate.signature.util.SignUtil;
import com.jd.jr.pay.gate.signature.util.ThreeDesUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PayOrderAction {

	private static final Logger logger = Logger.getLogger(PayOrderAction.class);

	@RequestMapping(value = "/showPayPage.htm")
	public String showPayPage(HttpServletRequest httpServletRequest) {
		String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
		httpServletRequest.setAttribute("merchant", merchantNum);
		httpServletRequest.setAttribute("tradeTime", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		return "payStart";
	}

	@RequestMapping(value = "/clientOrder.htm")
	public String pay(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BasePayOrderInfo basePayOrderInfo = new BasePayOrderInfo();
		basePayOrderInfo.setVersion(request.getParameter("version"));
		basePayOrderInfo.setMerchant(request.getParameter("merchant"));
		basePayOrderInfo.setDevice(request.getParameter("device"));
		basePayOrderInfo.setTradeNum(request.getParameter("tradeNum"));
		basePayOrderInfo.setTradeName(request.getParameter("tradeName"));
		basePayOrderInfo.setTradeDesc(request.getParameter("tradeDesc"));
		basePayOrderInfo.setTradeTime(request.getParameter("tradeTime"));
		basePayOrderInfo.setAmount(request.getParameter("amount"));
		basePayOrderInfo.setCurrency(request.getParameter("currency"));
		basePayOrderInfo.setNote(request.getParameter("note"));
		basePayOrderInfo.setCallbackUrl(request.getParameter("callbackUrl"));
		basePayOrderInfo.setNotifyUrl(request.getParameter("notifyUrl"));
		basePayOrderInfo.setIp(request.getParameter("ip"));
		basePayOrderInfo.setUserType(request.getParameter("userType"));
		basePayOrderInfo.setUserId(request.getParameter("userId"));
		basePayOrderInfo.setExpireTime(request.getParameter("expireTime"));
		basePayOrderInfo.setOrderType(request.getParameter("orderType"));
		basePayOrderInfo.setIndustryCategoryCode(request.getParameter("industryCategoryCode"));
		basePayOrderInfo.setSpecCardNo(request.getParameter("specCardNo"));
		basePayOrderInfo.setSpecId(request.getParameter("specId"));
		basePayOrderInfo.setSpecName(request.getParameter("specName"));
		basePayOrderInfo.setPayChannel(request.getParameter("payChannel"));

		basePayOrderInfo.setVendorId(request.getParameter("vendorId"));
		basePayOrderInfo.setGoodsInfo(request.getParameter("goodsInfo"));
		basePayOrderInfo.setOrderGoodsNum(request.getParameter("orderGoodsNum"));
		basePayOrderInfo.setTermInfo(request.getParameter("termInfo"));
		basePayOrderInfo.setReceiverInfo(request.getParameter("receiverInfo"));
		basePayOrderInfo.setRiskInfo(request.getParameter("riskInfo"));
		filterCharProcess(basePayOrderInfo);

		String oriUrl = request.getParameter("saveUrl");
		String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
		String desKey = PropertyUtils.getProperty("wepay.merchant.desKey");
		String cert = CertUtil.getCert();
		// 有证书则证书验证模式、无则配置模式
		if (cert != null && !cert.equals("")) {
			basePayOrderInfo.setCert(cert);
		}

		List<String> unSignedKeyList = new ArrayList<String>();
		unSignedKeyList.add("sign");

		basePayOrderInfo.setSign(SignUtil.signRemoveSelectedKeys(basePayOrderInfo, priKey, unSignedKeyList));

		byte[] key = BASE64.decode(desKey);

		if (StringUtils.isNotBlank(basePayOrderInfo.getDevice())) {
			basePayOrderInfo.setDevice(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getDevice()));
		}
		basePayOrderInfo.setTradeNum(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getTradeNum()));
		if (StringUtils.isNotBlank(basePayOrderInfo.getTradeName())) {
			basePayOrderInfo.setTradeName(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getTradeName()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getTradeDesc())) {
			basePayOrderInfo.setTradeDesc(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getTradeDesc()));
		}
		basePayOrderInfo.setTradeTime(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getTradeTime()));
		basePayOrderInfo.setAmount(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getAmount()));
		basePayOrderInfo.setCurrency(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getCurrency()));
		if (StringUtils.isNotBlank(basePayOrderInfo.getNote())) {
			basePayOrderInfo.setNote(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getNote()));
		}
		basePayOrderInfo.setCallbackUrl(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getCallbackUrl()));
		basePayOrderInfo.setNotifyUrl(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getNotifyUrl()));
		basePayOrderInfo.setIp(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getIp()));
		if (StringUtils.isNotBlank(basePayOrderInfo.getUserType())) {
			basePayOrderInfo.setUserType(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getUserType()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getUserId())) {
			basePayOrderInfo.setUserId(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getUserId()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getExpireTime())) {
			basePayOrderInfo.setExpireTime(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getExpireTime()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getOrderType())) {
			basePayOrderInfo.setOrderType(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getOrderType()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getIndustryCategoryCode())) {
			basePayOrderInfo
					.setIndustryCategoryCode(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getIndustryCategoryCode()));
		}

		if (StringUtils.isNotBlank(basePayOrderInfo.getSpecCardNo())) {
			basePayOrderInfo.setSpecCardNo(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getSpecCardNo()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getSpecId())) {
			basePayOrderInfo.setSpecId(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getSpecId()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getSpecName())) {
			basePayOrderInfo.setSpecName(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getSpecName()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getPayChannel())) {
			basePayOrderInfo.setPayChannel(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getPayChannel()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getVendorId())) {
			basePayOrderInfo.setVendorId(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getVendorId()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getGoodsInfo())) {
			basePayOrderInfo.setGoodsInfo(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getGoodsInfo()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getOrderGoodsNum())) {
			basePayOrderInfo.setOrderGoodsNum(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getOrderGoodsNum()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getTermInfo())) {
			basePayOrderInfo.setTermInfo(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getTermInfo()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getReceiverInfo())) {
			basePayOrderInfo.setReceiverInfo(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getReceiverInfo()));
		}
        if(StringUtils.isNotBlank(basePayOrderInfo.getRiskInfo())){
        	basePayOrderInfo.setRiskInfo(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getRiskInfo()));
        }
		
		if (StringUtils.isNotBlank(basePayOrderInfo.getCert())) {
			basePayOrderInfo.setCert(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getCert()));
		}

		request.setAttribute("payOrderInfo", basePayOrderInfo);
		request.setAttribute("payUrl", oriUrl);

		return "autoSubmit";
	}

	/**
	 * 
	 * @Title: filterCharProcess
	 * @Description: 特殊字符处理
	 * @param: @param basePayOrderInfo
	 * @return: void
	 * @throws
	 * @author mythling
	 * @Date 2016年8月6日 下午3:55:17
	 */
	private void filterCharProcess(BasePayOrderInfo basePayOrderInfo) {
		basePayOrderInfo.setVersion(doFilterCharProcess(basePayOrderInfo.getVersion()));
		basePayOrderInfo.setMerchant(doFilterCharProcess(basePayOrderInfo.getMerchant()));
		basePayOrderInfo.setDevice(doFilterCharProcess(basePayOrderInfo.getDevice()));
		basePayOrderInfo.setTradeNum(doFilterCharProcess(basePayOrderInfo.getTradeNum()));
		basePayOrderInfo.setTradeName(doFilterCharProcess(basePayOrderInfo.getTradeName()));
		basePayOrderInfo.setTradeDesc(doFilterCharProcess(basePayOrderInfo.getTradeDesc()));
		basePayOrderInfo.setTradeTime(doFilterCharProcess(basePayOrderInfo.getTradeTime()));
		basePayOrderInfo.setAmount(doFilterCharProcess(basePayOrderInfo.getAmount()));
		basePayOrderInfo.setCurrency(doFilterCharProcess(basePayOrderInfo.getCurrency()));
		basePayOrderInfo.setNote(doFilterCharProcess(basePayOrderInfo.getNote()));
		basePayOrderInfo.setCallbackUrl(doFilterCharProcess(basePayOrderInfo.getCallbackUrl()));
		basePayOrderInfo.setNotifyUrl(doFilterCharProcess(basePayOrderInfo.getNotifyUrl()));
		basePayOrderInfo.setIp(doFilterCharProcess(basePayOrderInfo.getIp()));
		basePayOrderInfo.setUserType(doFilterCharProcess(basePayOrderInfo.getUserType()));
		basePayOrderInfo.setUserId(doFilterCharProcess(basePayOrderInfo.getUserId()));
		basePayOrderInfo.setExpireTime(doFilterCharProcess(basePayOrderInfo.getExpireTime()));
		basePayOrderInfo.setOrderType(doFilterCharProcess(basePayOrderInfo.getOrderType()));
		basePayOrderInfo.setIndustryCategoryCode(doFilterCharProcess(basePayOrderInfo.getIndustryCategoryCode()));
		basePayOrderInfo.setSpecCardNo(doFilterCharProcess(basePayOrderInfo.getSpecCardNo()));
		basePayOrderInfo.setSpecId(doFilterCharProcess(basePayOrderInfo.getSpecId()));
		basePayOrderInfo.setSpecName(doFilterCharProcess(basePayOrderInfo.getSpecName()));

		basePayOrderInfo.setVendorId(doFilterCharProcess(basePayOrderInfo.getVendorId()));
		basePayOrderInfo.setGoodsInfo(doFilterCharProcess(basePayOrderInfo.getGoodsInfo()));
		basePayOrderInfo.setOrderGoodsNum(doFilterCharProcess(basePayOrderInfo.getOrderGoodsNum()));
		basePayOrderInfo.setTermInfo(doFilterCharProcess(basePayOrderInfo.getTermInfo()));
		basePayOrderInfo.setReceiverInfo(doFilterCharProcess(basePayOrderInfo.getReceiverInfo()));

	}

	/**
	 * 
	 * @Title: doFilterCharProcess
	 * @Description: 执行特殊字符处理
	 * @param: @param param
	 * @param: @return
	 * @return: String
	 * @throws
	 * @author mythling
	 * @Date 2016年8月6日 下午3:54:58
	 */
	private String doFilterCharProcess(String param) {
		if (param == null || param.equals("")) {
			return param;
		} else {
			return StringEscape.htmlSecurityEscape(param);
		}
	}

	/**
	 * 
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/showCreateOrder.htm")
	public String showCreateOrder(HttpServletRequest httpServletRequest) {
		String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
		httpServletRequest.setAttribute("merchant", merchantNum);
		httpServletRequest.setAttribute("tradeTime", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		return "showCreateOrder";
	}

	@RequestMapping(value = "/createOrder.htm")
	public String unifiedOrder(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BasePayOrderInfo basePayOrderInfo = new BasePayOrderInfo();

		String oriUrl = PropertyUtils.getProperty("wepay.server.uniorder.url");
		String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
		String desKey = PropertyUtils.getProperty("wepay.merchant.desKey");
		String pubKey = PropertyUtils.getProperty("wepay.jd.rsaPublicKey");

		basePayOrderInfo.setVersion(request.getParameter("version"));
		basePayOrderInfo.setMerchant(request.getParameter("merchant"));
		basePayOrderInfo.setDevice(request.getParameter("device"));
		basePayOrderInfo.setTradeNum(request.getParameter("tradeNum"));
		basePayOrderInfo.setTradeName(request.getParameter("tradeName"));
		basePayOrderInfo.setTradeDesc(request.getParameter("tradeDesc"));
		basePayOrderInfo.setTradeTime(request.getParameter("tradeTime"));
		basePayOrderInfo.setAmount(request.getParameter("amount"));
		basePayOrderInfo.setCurrency(request.getParameter("currency"));
		basePayOrderInfo.setNote(request.getParameter("note"));
		basePayOrderInfo.setNotifyUrl(request.getParameter("notifyUrl"));
		basePayOrderInfo.setCallbackUrl(request.getParameter("callbackUrl"));
		basePayOrderInfo.setIp(request.getParameter("ip"));
		basePayOrderInfo.setUserType(request.getParameter("userType"));
		basePayOrderInfo.setUserId(request.getParameter("userId"));
		basePayOrderInfo.setExpireTime(request.getParameter("expireTime"));
		basePayOrderInfo.setOrderType(request.getParameter("orderType"));
		basePayOrderInfo.setIndustryCategoryCode(request.getParameter("industryCategoryCode"));
		basePayOrderInfo.setSpecCardNo(request.getParameter("specCardNo"));
		basePayOrderInfo.setSpecId(request.getParameter("specId"));
		basePayOrderInfo.setSpecName(request.getParameter("specName"));

		basePayOrderInfo.setVendorId(request.getParameter("vendorId"));
		basePayOrderInfo.setGoodsInfo(request.getParameter("goodsInfo"));
		basePayOrderInfo.setOrderGoodsNum(request.getParameter("orderGoodsNum"));
		basePayOrderInfo.setTermInfo(request.getParameter("termInfo"));
		basePayOrderInfo.setReceiverInfo(request.getParameter("receiverInfo"));
		basePayOrderInfo.setTradeType(request.getParameter("tradeType"));
		basePayOrderInfo.setPayMerchant(request.getParameter("payMerchant"));
		basePayOrderInfo.setRiskInfo(request.getParameter("riskInfo"));

		String cert = CertUtil.getCert();
		// 有证书则证书验证模式、无则配置模式
		if (cert != null && !cert.equals("")) {
			basePayOrderInfo.setCert(cert);
		}

		String tradeXml = JdPayUtil.genReqXml(basePayOrderInfo, priKey, desKey);
		logger.info("request xml:" + tradeXml);
		String resultJsonData = HttpsClientUtil.sendRequest(oriUrl, tradeXml, "application/xml");
		logger.info("resultJsonData:" + resultJsonData);

		CreateOrderResponse createOrderResponse = JdPayUtil.parseResp(pubKey, desKey, resultJsonData, CreateOrderResponse.class);
		logger.info(createOrderResponse);
        if("000000".equals(createOrderResponse.getResult().getCode())){
    		request.setAttribute("resultData", createOrderResponse);
        }else{
    		request.setAttribute("errorMsg", createOrderResponse.getResult().getCode()+","+createOrderResponse.getResult().getDesc());
        }

		return "createOrderResult";
	}

}
