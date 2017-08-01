package com.jd.jr.pay.demo.action;

import com.jd.jr.pay.demo.model.UserRelationResponse;
import com.jd.jr.pay.demo.model.UserRequest;
import com.jd.jr.pay.demo.util.HttpsClientUtil;
import com.jd.jr.pay.demo.util.PropertyUtils;
import com.jd.jr.pay.gate.signature.util.JdPayUtil;
import com.jd.jr.pay.gate.signature.vo.JdPayBaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: RefundOrderAction
 * @Description: 发起退款
 * @author mythling
 * @date 2016年4月17日 下午4:02:50
 * @version V1.0
 */
@Controller
public class UserAction {

	@RequestMapping(value = "/showCancelUserRelation.htm")
	public String queryIndex(HttpServletRequest httpServletRequest) {
		String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
		httpServletRequest.setAttribute("merchantNum", merchantNum);
		return "showCancelUserRelation";
	}

	@RequestMapping(value = "/cancelUserRelation.htm", method = RequestMethod.POST)
	public String cancelUserRelation(UserRequest userRequest, HttpServletRequest httpServletRequest) {

		System.out.println("userRequest:" + userRequest);
		String deskey = PropertyUtils.getProperty("wepay.merchant.desKey");
		String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
		String pubKey = PropertyUtils.getProperty("wepay.jd.rsaPublicKey");

		try {
			String tradeXml = JdPayUtil.genReqXml(userRequest, priKey, deskey);
			System.out.println("tradeXml:" + tradeXml);
			String cancelUserRelationUrl = PropertyUtils.getProperty("wepay.server.cancelUserRelation.url");

			String resultJsonData = HttpsClientUtil.sendRequest(cancelUserRelationUrl, tradeXml, "application/xml");

			System.out.println("resultJsonData:" + resultJsonData);

			JdPayBaseResponse paymentTradeResponse = JdPayUtil.parseResp(pubKey, deskey, resultJsonData, JdPayBaseResponse.class);

			httpServletRequest.setAttribute("resultData", paymentTradeResponse);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

		return "cancelUserRelationResult";
	}
	
	
	@RequestMapping(value = "/showGetUserRelation.htm")
	public String showGetUserRelation(HttpServletRequest httpServletRequest) {
		String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
		httpServletRequest.setAttribute("merchantNum", merchantNum);
		return "showGetUserRelation";
	}
	
	@RequestMapping(value = "/getUserRelation.htm", method = RequestMethod.POST)
	public String getUserRelation(UserRequest userRequest, HttpServletRequest httpServletRequest) {

		System.out.println("userRequest:" + userRequest);
		String deskey = PropertyUtils.getProperty("wepay.merchant.desKey");
		String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
		String pubKey = PropertyUtils.getProperty("wepay.jd.rsaPublicKey");

		try {
			String tradeXml = JdPayUtil.genReqXml(userRequest, priKey, deskey);
			System.out.println("tradeXml:" + tradeXml);
			String cancelUserRelationUrl = PropertyUtils.getProperty("wepay.server.getUserRelation.url");

			String resultJsonData = HttpsClientUtil.sendRequest(cancelUserRelationUrl, tradeXml, "application/xml");

			System.out.println("resultJsonData:" + resultJsonData);

			UserRelationResponse paymentTradeResponse = JdPayUtil.parseResp(pubKey, deskey, resultJsonData, UserRelationResponse.class);

			httpServletRequest.setAttribute("resultData", paymentTradeResponse);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

		return "getUserRelationResult";
	}
}
