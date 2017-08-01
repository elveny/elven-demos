package com.jd.jr.pay.demo.action;

import com.jd.jr.pay.demo.model.TradeResultResponse;
import com.jd.jr.pay.demo.util.PropertyUtils;
import com.jd.jr.pay.gate.signature.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * 
 * @ClassName: SuccessAction
 * @Description: 页面成功回调处理
 * @author mythling
 * @date 2016年4月28日 上午10:42:29
 * @version V1.0
 */
@Controller
public class SuccessAction {
	@RequestMapping(value = "/success.htm")
	public String success(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			System.out.println("接收页面回调成功请求开始" + "-----------------");
			TradeResultResponse tradeResultRes = new TradeResultResponse();
			String desKey = PropertyUtils.getProperty("wepay.merchant.desKey");

			byte[] key = BASE64.decode(desKey);

			String tradeNum = request.getParameter("tradeNum");
			tradeResultRes.setTradeNum(ThreeDesUtil.decrypt4HexStr(key, tradeNum));

			String tradeAmount = request.getParameter("amount");
			tradeResultRes.setAmount(ThreeDesUtil.decrypt4HexStr(key, tradeAmount));

			String tradeCurrency = request.getParameter("currency");
			tradeResultRes.setCurrency(ThreeDesUtil.decrypt4HexStr(key, tradeCurrency));

			String tradeDate = request.getParameter("tradeTime");
			tradeResultRes.setTradeTime(ThreeDesUtil.decrypt4HexStr(key, tradeDate));

			String tradeNote = request.getParameter("note");
			if (tradeNote != null && !tradeNote.equals("")) {
				tradeResultRes.setNote(ThreeDesUtil.decrypt4HexStr(key, tradeNote));
			}

			String tradeStatus = request.getParameter("status");
			tradeResultRes.setStatus(ThreeDesUtil.decrypt4HexStr(key, tradeStatus));

			String sign = request.getParameter("sign");
			System.out.println("sign:" + sign);

			String strSourceData = SignUtil.signString(tradeResultRes, new ArrayList<String>());
			System.out.println("strSourceData:" + strSourceData);
			byte[] decryptBASE64Arr = new BASE64Decoder().decodeBuffer(sign);
			String wyPubKey = PropertyUtils.getProperty("wepay.jd.rsaPublicKey");

			byte[] decryptArr = RSACoder.decryptByPublicKey(decryptBASE64Arr, wyPubKey);
			String decryptStr = new String(decryptArr, "UTF-8");
			System.out.println("decryptStr:" + decryptStr);

			String sha256SourceSignString = SHAUtil.Encrypt(strSourceData, null);
			System.out.println("sha256SourceSignString:" + sha256SourceSignString);
			System.out.println(decryptStr + "|" + sha256SourceSignString);

			if (!decryptStr.equals(sha256SourceSignString)) {

				request.setAttribute("errorMsg", "验证签名失败！");
				return "fail";
			} else {
				System.out.println("接收页面回调成功------" + tradeResultRes);
				request.setAttribute("tradeResultRes", tradeResultRes);
				return "successPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public static void main(String[] args) throws Exception {

		String strSourceData = "amount=1&currency=CNY&note=生产环境-测试商户号&status=1&tradeNum=1100258450011460274797795&tradeTime=1460275737209";

		String sha256SourceSignString = SHAUtil.Encrypt(strSourceData, null);
		System.out.println("sha256SourceSignString:" + sha256SourceSignString);
		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL60FMvs2u2xikCbANWohQQI+llOnFrobMh+Tzkn/sGVyFNVBBmyT8ej+6a6o28b2VGlMP+oOGRSjrJuqy7pwMJqMvWPxSmmMjTqbi/FYmJNJfYmcEHrf8Jwn5PFJN2bCQdsiXJfiMquvJTiSDDV9m43NCXccp/wHXr0UQ05OAYlAgMBAAECgYEAhBrNeUKXmibtxblah6eYlWX+vtT0/QibKvxMtyRclw/CWO/Aymg6WerfzezmgHaDQcq0ObX3co+6KCL/1Jy7GP/Hk32BgfFpbp90PtQXGjVp03wUobJUBlGFfIxQjnIPUMT145z7aYN0u+ycz17IhA6K3M0QSn39VaOxpp37XcECQQDp6Xfj5dZ1TPcnPMRnSbARwo6fluMmCSRKffO032UOThZkE8un5nD5VhI3KCEllhB6LiIeG35CR5yf++lBUcbRAkEA0LYZnUu8WeNaHwAlKshvquiPzk3xugjum3Gld3wrY6neMSP1F84pbGumpIMnUuglWtKaWPD5anC8sAlF6qMHFQJAFAif8Q/lT0SZQm4M8D+6abr9FiQJLl/IEO06qzoa4J/FgSrE3Yt6D5DUnI6+UAbLQHulBmkaZjjV7EnaD3MekQJAJHOJabVugex5MuzdkOlMx3aylv959lnVAoUItyOSmGd0jPSQu8Wf6nWqtxTI62vsCj66Akqj5Pknmz8jXOV4OQJBANtNmkZH79AQl3heWHnFsr6pPyiZwVopHphzifjddHu3Mvu8/nwQvgXGRu+2vXeUGGhVRlw9W8YYRfNEFiQ+L3o=";
		byte[] byteSign = RSACoder.encryptByPrivateKey(sha256SourceSignString.getBytes("UTF-8"), privateKey);
		String sign = RSACoder.encryptBASE64(byteSign);
		System.out.println("sign:" + sign);

		byte[] decryptBASE64Arr = new BASE64Decoder().decodeBuffer(sign);
		String wyPubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+tBTL7NrtsYpAmwDVqIUECPpZTpxa6GzIfk85J/7BlchTVQQZsk/Ho/umuqNvG9lRpTD/qDhkUo6ybqsu6cDCajL1j8UppjI06m4vxWJiTSX2JnBB63/CcJ+TxSTdmwkHbIlyX4jKrryU4kgw1fZuNzQl3HKf8B169FENOTgGJQIDAQAB";
		byte[] decryptArr = RSACoder.decryptByPublicKey(decryptBASE64Arr, wyPubKey);
		String decryptStr = new String(decryptArr, "UTF-8");
		System.out.println(decryptStr);
		System.out.println(decryptStr.equals(sha256SourceSignString));

		String test = "f039b7102730a11c7eadfeefb15410fa6f30bcb4bcfed2f8";
		String desKey = "OGjpKea/kuocLALfwsGKih/09M7CNHn9";
		byte[] key = BASE64.decode(desKey);

		System.out.println(ThreeDesUtil.decrypt4HexStr(key, test));

	}

}
