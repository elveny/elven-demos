package com.jd.jr.pay.demo.action;

import com.jd.jr.pay.demo.model.BasePayOrderInfo;
import com.jd.jr.pay.demo.util.PropertyUtils;
import com.jd.jr.pay.gate.signature.util.BASE64;
import com.jd.jr.pay.gate.signature.util.SignUtil;
import com.jd.jr.pay.gate.signature.util.ThreeDesUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 有无金额扫码支付
 * @author luanmengting
 *
 */
@Controller
public class CustomerPayAction extends BaseAction{
	
	@RequestMapping(value = "/customerPayPage.htm")
	public String showPayPage(HttpServletRequest httpServletRequest) {
		String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
		httpServletRequest.setAttribute("merchant", merchantNum);
		httpServletRequest.setAttribute("tradeTime", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		return "customerPayStart";
	}
	
	
	@RequestMapping(value = "/customerClientOrder.htm")
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
		basePayOrderInfo.setExpireTime(request.getParameter("expireTime"));
		basePayOrderInfo.setOrderType(request.getParameter("orderType"));
		basePayOrderInfo.setIndustryCategoryCode(request.getParameter("industryCategoryCode"));
		basePayOrderInfo.setTradeType(request.getParameter("tradeType"));
		basePayOrderInfo.setPayMerchant(request.getParameter("payMerchant"));
		basePayOrderInfo.setRiskInfo(request.getParameter("riskInfo"));

		filterCharProcess(basePayOrderInfo);

		String oriUrl = request.getParameter("saveUrl");
		String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
		String desKey = PropertyUtils.getProperty("wepay.merchant.desKey");

		List<String> unSignedKeyList = new ArrayList<String>();
		unSignedKeyList.add("sign");

		basePayOrderInfo.setSign(SignUtil.signRemoveSelectedKeys(basePayOrderInfo, priKey, unSignedKeyList));

		byte[] key = BASE64.decode(desKey);

		basePayOrderInfo.setTradeNum(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getTradeNum()));
		basePayOrderInfo.setTradeTime(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getTradeTime()));
		basePayOrderInfo.setAmount(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getAmount()));
		basePayOrderInfo.setCurrency(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getCurrency()));
		basePayOrderInfo.setCallbackUrl(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getCallbackUrl()));
		basePayOrderInfo.setNotifyUrl(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getNotifyUrl()));
		basePayOrderInfo.setIp(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getIp()));
		
		if (StringUtils.isNotBlank(basePayOrderInfo.getDevice())) {
			basePayOrderInfo.setDevice(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getDevice()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getTradeName())) {
			basePayOrderInfo.setTradeName(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getTradeName()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getTradeDesc())) {
			basePayOrderInfo.setTradeDesc(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getTradeDesc()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getNote())) {
			basePayOrderInfo.setNote(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getNote()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getExpireTime())) {
			basePayOrderInfo.setExpireTime(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getExpireTime()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getOrderType())) {
			basePayOrderInfo.setOrderType(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getOrderType()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getIndustryCategoryCode())) {
			basePayOrderInfo.setIndustryCategoryCode(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getIndustryCategoryCode()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getCert())) {
			basePayOrderInfo.setCert(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getCert()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getTradeType())) {
			basePayOrderInfo.setTradeType(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getTradeType()));
		}
		if (StringUtils.isNotBlank(basePayOrderInfo.getPayMerchant())) {
			basePayOrderInfo.setPayMerchant(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getPayMerchant()));
		}
		if(StringUtils.isNotBlank(basePayOrderInfo.getRiskInfo())){
	        basePayOrderInfo.setRiskInfo(ThreeDesUtil.encrypt2HexStr(key, basePayOrderInfo.getRiskInfo()));
	    }
		request.setAttribute("payOrderInfo", basePayOrderInfo);
		request.setAttribute("payUrl", oriUrl);
		return "customerAutoSubmit";
	}
	
	
	/**
	 * 特殊字符处理
	 * @param basePayOrderInfo
	 * 
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
		basePayOrderInfo.setExpireTime(doFilterCharProcess(basePayOrderInfo.getExpireTime()));
		basePayOrderInfo.setOrderType(doFilterCharProcess(basePayOrderInfo.getOrderType()));
		basePayOrderInfo.setIndustryCategoryCode(doFilterCharProcess(basePayOrderInfo.getIndustryCategoryCode()));
		basePayOrderInfo.setTradeType(doFilterCharProcess(basePayOrderInfo.getTradeType()));
		basePayOrderInfo.setPayMerchant(doFilterCharProcess(basePayOrderInfo.getPayMerchant()));
	}
	
}
