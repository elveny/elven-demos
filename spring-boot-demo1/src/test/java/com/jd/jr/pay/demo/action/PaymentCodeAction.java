package com.jd.jr.pay.demo.action;

import com.jd.jr.pay.demo.model.FkmPayRequest;
import com.jd.jr.pay.demo.model.FkmPayResponse;
import com.jd.jr.pay.demo.util.HttpsClientUtil;
import com.jd.jr.pay.demo.util.PropertyUtils;
import com.jd.jr.pay.gate.signature.util.JdPayUtil;
import com.jd.jr.pay.gate.signature.vo.Result;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class PaymentCodeAction extends BaseAction {

	@RequestMapping(value = "/paymentCodeIndex.htm")
	public String showPayPage(HttpServletRequest httpServletRequest) {
		String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
		httpServletRequest.setAttribute("merchant", merchantNum);
		httpServletRequest.setAttribute("tradeTime", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		return "paymentCodePay";
	}

	@RequestMapping(value = "/paymentCodePay.htm", method = RequestMethod.POST)
	public String paymentCodePay(FkmPayRequest fkmPayRequest, HttpServletRequest request,
			HttpServletResponse response) {
		String deskey = PropertyUtils.getProperty("wepay.merchant.desKey");
		String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
		String pubKey = PropertyUtils.getProperty("wepay.jd.rsaPublicKey");

		System.out.println(fkmPayRequest);
		FkmPayResponse revoekRes = new FkmPayResponse() ;
		try {
			String tradeXml = JdPayUtil.genReqXml(fkmPayRequest, priKey, deskey);
			System.out.println("tradeXml:" + tradeXml);
			String saveUrl = PropertyUtils.getProperty("wepay.server.fkmPay.url");

			String resultJsonData = HttpsClientUtil.sendRequest(saveUrl, tradeXml, "application/xml");

			System.out.println("resultJsonData:" + resultJsonData);

			revoekRes = JdPayUtil.parseResp(pubKey, deskey, resultJsonData, FkmPayResponse.class);
			System.out.println("paymentCodePayResponse:" + revoekRes);

			request.setAttribute("resultData", revoekRes);

		} catch (Exception e) {
			Result result = new Result();
			result.setCode("");
			result.setDesc(e.getMessage());
			revoekRes.setResult(result);
			request.setAttribute("resultData", revoekRes);
			e.printStackTrace();
			return "paymentCodePayResult";
		}

		return "paymentCodePayResult";
	}
}
