package com.jd.jr.pay.demo.action;

import com.jd.jr.pay.demo.model.AsynNotifyResponse;
import com.jd.jr.pay.demo.util.PropertyUtils;
import com.jd.jr.pay.gate.signature.util.JdPayUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @ClassName: AsynNotifyAction
 * @Description: 异步通知受理类
 * @author mythling
 * @date 2016年4月17日 下午3:23:41
 *
 */
@Controller
public class AsynNotifyAction {

	@Resource
	private HttpServletRequest request;

	private static final Logger logger = Logger.getLogger(AsynNotifyAction.class);

	// 处理成功跳转页
	private final String SUCCESS_PAGE_STRING = "ok";

	/**
	 * 
	 * @Title: execute
	 * @Description: 异步通知受理
	 * @param @param httpServletRequest
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/asynNotify.htm", method = RequestMethod.POST)
	public String execute(HttpServletRequest httpServletRequest) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			logger.info("异步通知原始数据:" + sb);
		} catch (IOException e) {
			logger.error("异步通知原始数据异常:" + e);
			return "fail";
		}

		String deskey = PropertyUtils.getProperty("wepay.merchant.desKey");
		String pubKey = PropertyUtils.getProperty("wepay.jd.rsaPublicKey");
		try {
			AsynNotifyResponse anRes = JdPayUtil.parseResp(pubKey, deskey, sb.toString(), AsynNotifyResponse.class);
			logger.info("异步通知解析数据:" + anRes);
			logger.info("异步通知订单号：" + anRes.getTradeNum() + ",状态：" + anRes.getStatus() + "成功!!!!");

		} catch (Exception e) {
			logger.error(e);
			return "fail";
		}
		return SUCCESS_PAGE_STRING;
	}

	/**
	 * 
	 * @Title: main
	 * @Description: 测试解析异步通知数据
	 * @param @param args
	 * @param @throws IllegalAccessException
	 * @param @throws InstantiationException 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKE5N2xm3NIrXON8Zj19GNtLZ8xwEQ6uDIyrS3S03UhgBJMkGl4msfq4Xuxv6XUAN7oU1XhV3/xtabr9rXto4Ke3d6WwNbxwXnK5LSgsQc1BhT5NcXHXpGBdt7P8NMez5qGieOKqHGvT0qvjyYnYA29a8Z4wzNR7vAVHp36uD5RwIDAQAB";
		String deskey = "ta4E/aspLA3lgFGKmNDNRYU92RkZ4w35";
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><jdpay>  <version>V2.0</version>  <merchant>110148035005</merchant>  <result>    <code>000000</code>    <desc>success</desc>  </result>  <encrypt>M2JlZTE1NTZkMTdmOGYzN2ZkN2YyY2IwNTFkMTI0MzgwYTA5ZjAxM2ZhNWI5NmUyNjVmNzg5ZmIyY2E4OWJkMmM5N2VmYWVlMDViMzJhNWQwYWVhZDI4YTE5MjRhY2QzZjAyZmMxMjcyODVhNjM4ZWE3ZDkxNjk1N2VlYTVlMTQ0MzMzNWFlODg2MzAzOTE1ZDQyNDM3NzlmZmM2NzA1NjNmNTA2MGVjZjRmOWFlZjVkZDNkZjM2NjZlYTU4Mjk1NDZjOTUxMmJkOTI4NzM0MjBjMWUyMjU2Njg5ZjNmMjNhZjU4Njg2YWJjNDEwOWEyOGU2ZjZmMzkyZDIwNDM5ZjQxMTc0NzdiYjRmMjA3MWQxMjRlYTA0YmJlMWI1OTFjZGM5YWE1YTU5MDQ5MmQ0ZWE5NDdlNTNhNTBjZGZjNWQ2M2MyNWRlN2VhNzJhOWVlODg3ZmYzZmJjNzNlMWI1YzExYzk1MWRiNjhlY2QwOGFmZWZlYmEwNTk1NmU3MDI0NDEzY2E0NjdlNjkzNDViNWEzOTY4ZGM4M2Y0ZDk1ZGI4YWRiM2FlMjlmNGMyNDRjNjIwMTBmOWUxOTUwYjQ3ODg3OGQyYzAwZWUzY2U0ZGViMDMxZGE5YjlhMTZhNWRhMjdkNTRlMWQxNjExYjUwZGVkNzZkMGZmNWQ0YTg1ODFkN2NjNTEzODYyMGQ5OGYwZDlhODJmMTE4YWRlZTM1YzgwNDAxYTcwNzY1ZDNmY2ZkNTdjNGY4NzNhMzhhZTA4OWFmMDAxMTMyYWNmZjM0NTdhZDczYjhkOTJhNGIxMzMxNzQ3ZTMwYmQ4Njg2MGRkMDEwOGM0ZmNkMzU4M2I4YjI1NDdiMjU0OTdmZjNkMTYyZTU5MzFlODNkN2ZjZmZjYjhjYzUxNjY1NGMwMjA0MDk3YTNiNDM0YzFlNDdkZDU1ODZkN2ZlMTBkNWNkNWRmOGYyOWQ0NTk2MmMxMGYzYTE1MTJhNDM5M2M5YWI0ZmQ4MmYwYjFjMzRlNDJiMGJkNDY3YWFjMGU0OGI2YTJkMmUzZDA3ZWIxNmJmM2I5NWViNzk1MzEwZTBhNDUyZjdkYTMzMjhhNWVlZjJkN2M0MjUxZWRjY2ZmNDY2OTI1NjdlMGExMThkNjkyNGNkMGYzZjllYjEwZTAzYmMzMGZmMmQ0NDBjZGVkOWJkZGRjNjdkZmJlYjZjY2ExODk1Zjc3ZjQzNDZhMDdlNmM5M2ZlOWVjZTFkY2IyMDk2ODcwODhhMzM3ZTJhYTMzNWQyMjE4ZmIwZmUyNWYyYTFiYzc3MDgxNjZmYWM3ODJjOGQwYTc1NmQ2NmFmNDhmYjEzNTIwYzhmYjIxYjgzYmY4ZGRlYTI3MTA4YjAzZDA0OWJlYjUwZmQ1YTU5OTE0MzIwMWMxOTM4OTk4OWRjYjVmMmFkNWU4OGFmZGM2YmJlZmFjYjVhNDE1NGZmYjRmNWNjYmE3YWYwNzcxMDUxODU4NTM3YzdiNzhmYWUyNmE4ZDFjZmIzMjRiN2I4ZGZlNTE2NzBlZTUyMDA1Y2Y4Mjg4MzhkOTAwMDYxNGIyNTExZTZhNDM2Yzg1NmZhODY2YWE1YzFjY2E1Y2NkNjVkYmNiY2IzYTFiZmM4OWY1NGVlOWNjMzhmM2U3MThlYTFhMGYwMTJjNzAwMGU4MzlmZDdhOTc0MDdlY2FiODVkMGVhOTc2MDg4NWU4YjJlYjM2MTAzMmQ1ZDc4N2FhY2Q5NWQ1NmIxMjkzMGMwY2EyZDhkZmFiZmI2NWI0MDEyNmJmZDRjZGNlNDgyNjg5ZmMzNzJiOThmNzdjNzE5NWJjZTZkYTY2MzYxZjY1OTdmZTM0YjhhMDYzNDQwYmM2Y2MwZTYyZjM0NzEyYmJmZDhjN2MwZTM2Zjk0NDBiYzZjYzBlNjJmMzQ3NGFmNDY5YTg5MDFjYzA4MzVjYmUyYTMzMmI2YWEwYWJhN2Y4NjVjMjg2NzNiYzA0YjlmZGRjNTFlMDNmMDkzNjhiMmJkNTVjZmFhZDM4M2MzZjA1MTA2NGMzOWUzZTQwYzczNjRjNmE3NWI4OTNmZThmMTM5ZTRkMDE2YjJkMDVmNTdmZTg0YWI2OGZkMDYyNjlmZWRmYzYyYTg0YzM0YzgxNzRkYjI2ZGFlOGExYTlmN2JjNjIzNjUxNGI5MDhmZTQyYjM0ODE1MjRlYWIwMmIzOTNlOTk0OWZlYzFmZmE4YzBkNTU5MDQwMGRiNTE0ODI0ZjBhMWQyZDAxMWExM2M1ZDUxZDhiNzRhMTg4YWY=</encrypt></jdpay>";
		AsynNotifyResponse anRes = JdPayUtil.parseResp(pubKey, deskey, xml, AsynNotifyResponse.class);
		System.out.println(anRes);
	}

}
