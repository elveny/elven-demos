package com.jd.jr.pay.demo.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jd.jr.pay.demo.model.RefundResponse;
import com.jd.jr.pay.demo.model.TradeRefundReqDto;
import com.jd.jr.pay.demo.util.CertUtil;
import com.jd.jr.pay.demo.util.HttpsClientUtil;
import com.jd.jr.pay.demo.util.PropertyUtils;
import com.jd.jr.pay.gate.signature.util.JdPayUtil;

/**
 * 
* @ClassName: RefundOrderAction 
* @Description: 发起退款 
* @author  mythling
* @date 2016年4月17日 下午4:02:50 
* @version V1.0
 */
@Controller
public class RefundOrderAction {

	@RequestMapping(value = "/refundIndex.htm")
	public String queryIndex(HttpServletRequest httpServletRequest) {
		String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
		httpServletRequest.setAttribute("merchantNum", merchantNum);
		httpServletRequest.setAttribute("nowTime", new Date());
		return "refundIndex";
	}

	@RequestMapping(value = "/refundOrder.htm", method = RequestMethod.POST)
	public String paySign(TradeRefundReqDto tradeRefundReqDto, HttpServletRequest httpServletRequest) {

		System.out.println("TradeRefundReqDto:" + tradeRefundReqDto);
		String deskey = PropertyUtils.getProperty("wepay.merchant.desKey");
		String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
		String pubKey = PropertyUtils.getProperty("wepay.jd.rsaPublicKey");
		String cert = CertUtil.getCert();
		// 有证书则证书验证模式、无则配置模式
		if(cert != null && !cert.equals("")){
			tradeRefundReqDto.setCert(cert);
		}
		
		try {
			String tradeXml = JdPayUtil.genReqXml(tradeRefundReqDto, priKey, deskey);
			System.out.println("tradeXml:" + tradeXml);
			String refundUrl = PropertyUtils.getProperty("wepay.server.refund.url");

			String resultJsonData = HttpsClientUtil.sendRequest(refundUrl, tradeXml, "application/xml");

			System.out.println("resultJsonData:" + resultJsonData);

			RefundResponse refundResponse = JdPayUtil.parseResp(pubKey, deskey, resultJsonData, RefundResponse.class);
			System.out.println("refundResponse:" + refundResponse);
			
			httpServletRequest.setAttribute("resultData", refundResponse);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

		return "refundResult";
	}

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		TradeRefundReqDto tradeRefundReqDto = new TradeRefundReqDto();
		tradeRefundReqDto.setVersion("V2.0");
		tradeRefundReqDto.setMerchant("110025845001");
		tradeRefundReqDto.setTradeNum("10222011603304");
		tradeRefundReqDto.setoTradeNum("102220116033023221231111");
		tradeRefundReqDto.setAmount(1);
		tradeRefundReqDto.setCurrency("CNY");
		tradeRefundReqDto.setTradeTime("20160101103015");
		tradeRefundReqDto.setNotifyUrl("http://www.test.com/notify.htm");

		String priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKr1/Rq8bmSbYHcnJB4nLAhUr3bjSZVdkNAzXstCzqIqO2/gz4F7s+lBuRnS4VuzBOWM/iNtUSrPEk5/PS2gKkQ/gkM7TJKGoBqYnnY55GcoGJ1iESBsbBS6y3SMayR2rMVx7K4bJM0XZtLVs49cwDxFvrpeEopVmbh/ytPb6ZHNAgMBAAECgYAqVHwV+aAP0xUAi7V6rdJOQcGsWzyl+iPYGVAYXfNR3ckXu39FnT5/u+E8DqX0EZBd0ho+5VtakSkEPKcGPAkasgE5oqA9PScYux98DdntT/7QH5g2zF4I/t7FNREhvB+o6eyW6RiuLhbQIsd5ZHzblL61GV/Ziq5xkGm3V79TNQJBAPVGtacNKUksgfFRbqkZKD8Jn5MLxzV3e3ZzatzXbheldOgsHQGyTbFAvQAyO5QvTuLfvs52DjiwiIYsMNHxYcsCQQCyb35vsj99d0ot6mTkZGX1KCsYfDc9SMpZH/PKhHG1kgivJktPgL06v6Wj6Tn5Z7wPdTSzdJ6nhUXj9F5TjgfHAkEAlDDAl8mP5DIL4G0+2tPkSRHbY9B974PdCFf7fKp8TbUdyRi9/cRoxGtExdWTwZhDpTdh5QPW29Fs6wPQC0g3uQJAOKq536UfDqxsrWpMAlB0JMhN72e7b0YHpQhFtyIarowjH+SsdCIxdk/YvdZap/8utUYGp5s4q4DiskMwFZ78jwJBAKtEtHoCqId+lLw1tj3r1rX9uVfnQTDTw4hID7JJ/8jjzCNcKp5epomidQ4z7BDJ046pStwOJKi51XhqmHx7kD0=";
		String deskey = "OGjpKea/kuocLALfwsGKih/09M7CNHn9";
		String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+tBTL7NrtsYpAmwDVqIUECPpZTpxa6GzIfk85J/7BlchTVQQZsk/Ho/umuqNvG9lRpTD/qDhkUo6ybqsu6cDCajL1j8UppjI06m4vxWJiTSX2JnBB63/CcJ+TxSTdmwkHbIlyX4jKrryU4kgw1fZuNzQl3HKf8B169FENOTgGJQIDAQAB";
		String tradeXml = JdPayUtil.genReqXml(tradeRefundReqDto, priKey, deskey);
		System.out.println(tradeXml);

		String refundUrl = "https://paygate.jd.com:8003/service/refund";

		String resultJsonData = HttpsClientUtil.sendRequest(refundUrl, tradeXml, "application/xml");

		RefundResponse refundResponse = JdPayUtil.parseResp(pubKey, deskey, resultJsonData, RefundResponse.class);
		System.out.println("refundResponse:" + refundResponse);

	}
}
