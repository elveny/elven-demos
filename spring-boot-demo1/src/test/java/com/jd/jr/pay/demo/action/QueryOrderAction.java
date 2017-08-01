package com.jd.jr.pay.demo.action;

import com.jd.jr.pay.demo.model.*;
import com.jd.jr.pay.demo.util.CertUtil;
import com.jd.jr.pay.demo.util.HttpsClientUtil;
import com.jd.jr.pay.demo.util.PropertyUtils;
import com.jd.jr.pay.gate.signature.util.JdPayUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: QueryOrderAction
 * @Description: 交易查询-消费、退款
 * @author mythling
 * @date 2016年4月28日 上午10:41:09
 * @version V1.0
 */
@Controller
public class QueryOrderAction {

	@RequestMapping(value = "/queryIndex.htm")
	public String queryIndex(HttpServletRequest httpServletRequest) {
		String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
		httpServletRequest.setAttribute("merchantNum", merchantNum);
		return "queryIndex";
	}

	@RequestMapping(value = "/queryOrder.htm", method = RequestMethod.POST)
	public String paySign(TradeQueryReqDto tradeQueryReqDto, HttpServletRequest httpServletRequest) {
		String tradeType = tradeQueryReqDto.getTradeType();

		String deskey = PropertyUtils.getProperty("wepay.merchant.desKey");
		String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
		String pubKey = PropertyUtils.getProperty("wepay.jd.rsaPublicKey");
		String cert = CertUtil.getCert();

		TradeQueryInner queryTradeDTO = new TradeQueryInner();
		queryTradeDTO.setVersion(tradeQueryReqDto.getVersion());
		queryTradeDTO.setMerchant(tradeQueryReqDto.getMerchantNum());
		queryTradeDTO.setTradeNum(tradeQueryReqDto.getTradeNum());
		queryTradeDTO.setoTradeNum(tradeQueryReqDto.getoTradeNum());
		queryTradeDTO.setTradeType(tradeType); // 0:消费 1：退款
		// 有证书则证书验证模式、无则配置模式
		if (cert != null && !cert.equals("")) {
			queryTradeDTO.setCert(cert);
		}

		String rsPage = "queryResult";
		try {
			String xml = JdPayUtil.genReqXml(queryTradeDTO, priKey, deskey);
			System.out.println("query xml:" + xml);
			String queryUrl = PropertyUtils.getProperty("wepay.server.query.url");
			String rs = HttpsClientUtil.sendRequest(queryUrl, xml, "application/xml");
			System.out.println("result:" + rs);

			if (tradeType.equals("0")) {// 解析消费报文
				PaymentTradeResponse paymentTradeResponse = JdPayUtil.parseResp(pubKey, deskey, rs, PaymentTradeResponse.class);
				System.out.println("PaymentTradeResponse:" + paymentTradeResponse);
				httpServletRequest.setAttribute("presp", paymentTradeResponse);
				rsPage = "queryResult";
			} else { // 解析退款报文
				QueryRefundResponse queryRefundResponse = JdPayUtil.parseResp(pubKey, deskey, rs, QueryRefundResponse.class);
				System.out.println("QueryRefundResponse:" + queryRefundResponse);
				httpServletRequest.setAttribute("rresp", queryRefundResponse);
				rsPage = "queryFefundResult";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsPage;
	}

	@RequestMapping(value = "/queryRefundIndex.htm")
	public String queryRefundIndex(HttpServletRequest httpServletRequest) {
		String merchantNum = PropertyUtils.getProperty("wepay.merchant.num");
		httpServletRequest.setAttribute("merchantNum", merchantNum);
		return "queryRefundIndex";
	}

	/**
	 * 
	 * @Title: queryRefund
	 * @Description: 根据正单交易号查退款记录
	 * @param: @param tradeQueryReqDto
	 * @param: @param httpServletRequest
	 * @param: @return
	 * @return: String
	 * @throws
	 * @author mythling
	 * @Date 2016年9月27日 上午10:39:39
	 */
	@RequestMapping(value = "/queryRefund.htm", method = RequestMethod.POST)
	public String queryRefund(TradeQueryReqDto tradeQueryReqDto, HttpServletRequest httpServletRequest) {
		String tradeType = tradeQueryReqDto.getTradeType();

		String deskey = PropertyUtils.getProperty("wepay.merchant.desKey");
		String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
		String pubKey = PropertyUtils.getProperty("wepay.jd.rsaPublicKey");
		String cert = CertUtil.getCert();

		TradeQueryInner queryTradeDTO = new TradeQueryInner();
		queryTradeDTO.setVersion(tradeQueryReqDto.getVersion());
		queryTradeDTO.setMerchant(tradeQueryReqDto.getMerchantNum());
		// queryTradeDTO.setTradeNum(tradeQueryReqDto.getTradeNum());
		queryTradeDTO.setoTradeNum(tradeQueryReqDto.getoTradeNum());
		queryTradeDTO.setTradeType(tradeType); // 0:消费 1：退款
		// 有证书则证书验证模式、无则配置模式
		if (cert != null && !cert.equals("")) {
			queryTradeDTO.setCert(cert);
		}

		String rsPage = "queryResult";
		try {
			String xml = JdPayUtil.genReqXml(queryTradeDTO, priKey, deskey);
			System.out.println("query xml:" + xml);
			String queryUrl = PropertyUtils.getProperty("wepay.server.query.refund.url");
			String rs = HttpsClientUtil.sendRequest(queryUrl, xml, "application/xml");
			System.out.println("result:" + rs);

			QueryRefundNewResponse queryRefundResponse = JdPayUtil.parseResp(pubKey, deskey, rs, QueryRefundNewResponse.class);
			System.out.println("QueryRefundResponse:" + queryRefundResponse);

			httpServletRequest.setAttribute("rresp", queryRefundResponse);
			rsPage = "queryNewFefundResult";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsPage;
	}

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		TradeQueryInner queryTradeDTO = new TradeQueryInner();
		queryTradeDTO.setVersion("V2.0");
		queryTradeDTO.setMerchant("22318136");
		queryTradeDTO.setoTradeNum("147488103411");
		queryTradeDTO.setTradeType("1"); // 0:消费 1：退款

		String priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL60FMvs2u2xikCbANWohQQI+llOnFrobMh+Tzkn/sGVyFNVBBmyT8ej+6a6o28b2VGlMP+oOGRSjrJuqy7pwMJqMvWPxSmmMjTqbi/FYmJNJfYmcEHrf8Jwn5PFJN2bCQdsiXJfiMquvJTiSDDV9m43NCXccp/wHXr0UQ05OAYlAgMBAAECgYEAhBrNeUKXmibtxblah6eYlWX+vtT0/QibKvxMtyRclw/CWO/Aymg6WerfzezmgHaDQcq0ObX3co+6KCL/1Jy7GP/Hk32BgfFpbp90PtQXGjVp03wUobJUBlGFfIxQjnIPUMT145z7aYN0u+ycz17IhA6K3M0QSn39VaOxpp37XcECQQDp6Xfj5dZ1TPcnPMRnSbARwo6fluMmCSRKffO032UOThZkE8un5nD5VhI3KCEllhB6LiIeG35CR5yf++lBUcbRAkEA0LYZnUu8WeNaHwAlKshvquiPzk3xugjum3Gld3wrY6neMSP1F84pbGumpIMnUuglWtKaWPD5anC8sAlF6qMHFQJAFAif8Q/lT0SZQm4M8D+6abr9FiQJLl/IEO06qzoa4J/FgSrE3Yt6D5DUnI6+UAbLQHulBmkaZjjV7EnaD3MekQJAJHOJabVugex5MuzdkOlMx3aylv959lnVAoUItyOSmGd0jPSQu8Wf6nWqtxTI62vsCj66Akqj5Pknmz8jXOV4OQJBANtNmkZH79AQl3heWHnFsr6pPyiZwVopHphzifjddHu3Mvu8/nwQvgXGRu+2vXeUGGhVRlw9W8YYRfNEFiQ+L3o=";
		String deskey = "Z8KMT8cT4z5ruu89znxFhRP4DdDBqLUH";
		String queryUrl = "https://paygate.jd.com/service/queryRefund";
		String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+tBTL7NrtsYpAmwDVqIUECPpZTpxa6GzIfk85J/7BlchTVQQZsk/Ho/umuqNvG9lRpTD/qDhkUo6ybqsu6cDCajL1j8UppjI06m4vxWJiTSX2JnBB63/CcJ+TxSTdmwkHbIlyX4jKrryU4kgw1fZuNzQl3HKf8B169FENOTgGJQIDAQAB";

		String xml = JdPayUtil.genReqXml(queryTradeDTO, priKey, deskey);
		System.out.println("query xml:" + xml);
	   //String rs = HttpClientUtil.sendRequest(queryUrl, xml, "application/xml");
	    String rs = HttpsClientUtil.sendRequest(queryUrl, xml, "application/xml");

		System.out.println("result:" + rs);

		QueryRefundNewResponse queryRefundResponse = JdPayUtil.parseResp(pubKey, deskey, rs, QueryRefundNewResponse.class);
		System.out.println(queryRefundResponse);

	}

}
