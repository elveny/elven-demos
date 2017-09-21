package com.chinabank.motopay.service;

import com.chinabank.motopay.util.DES;
import com.chinabank.motopay.util.FTL;
import com.chinabank.motopay.util.MD5;
import com.chinabank.motopay.util.XmlMsg;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.util.HashMap;
import java.util.Map;

public class MotoPayService {
	
	private MotoPayProvider provider = new MotoPayProvider();
	
	/**
	 * 发起交易请求
	 * @param tradeType
	 * @return 返回交易结果 
	 */
	public String trade(String tradeType){
		String respStr = "";
		try{
			String data = this.getDataElmtXML(tradeType);
			System.out.println("data........"+data);
			//将请求参数XML中的data元素用DES加密,DES密钥是在商户在网银在线后台设置的
			String dataDES = DES.encrypt(data, Context.getValue("des"), Context.getValue("charset"));
			//计算数据签名version+merchant+terminal+data元素，MD5密钥是在商户在网银在线后台设置，签名是为了验证请求的合法性
			String signMD5 = MD5.md5(Context.getValue("version")
					+Context.getValue("merchant")
					+Context.getValue("terminal")
					+dataDES, Context.getValue("md5"));
			String reqXML = this.getReqXML(dataDES, signMD5);
			System.out.println("reqXML........"+reqXML);
			//最后将xml用base64加密
			String reqParams = Base64.encode(reqXML.getBytes());
			System.out.println("reqParams........"+reqParams);
			//发送请求到网银在线快捷支付地址
			respStr = provider.process(Context.getValue("charset"), reqParams);
		}catch(Exception e){
			e.printStackTrace();
		}
		return respStr;
	}
	/**
	 * 处理交易结果
	 * @param respStr
	 */
	public void operate(String respStr){
		try {
			//数据格式是resp=XML数据
//			respStr="";
//			respStr= URLDecoder.decode(respStr);
			String temResp = respStr.substring(respStr.indexOf("=") + 1);
			System.out.println("temResp........"+temResp );
			//快捷支付数据先base64解码
			temResp = new String(Base64.decode(temResp));
			System.out.println("temResp,,,,,,,,,"+temResp);
			
			//解析xml中的数据
			Map<String, String> respParams= XmlMsg.parse(temResp);
			System.out.println("respParams,,,,,,,,,"+respParams);
//			System.out.println("respParams,,,,,,,,,"+respParams);
//			System.out.println("版本号|商户号|终端号|交易数据|数据签名"
//					+respParams.get("chinabank.version")+"|"+respParams.get("chinabank.merchant")+"|"+respParams.get("chinabank.terminal")+"|"
//					+respParams.get("chinabank.data")+"|"+respParams.get("chinabank.sign"));
			//验证数据签名的合法性。版本号+商户号+终端号+交易数据使用md5加密和数据签名比较，md5密钥在网银在线商户后台设置
			if(!MD5.verify(respParams.get("chinabank.version")
					+respParams.get("chinabank.merchant")+respParams.get("chinabank.terminal")
					+respParams.get("chinabank.data"), Context.getValue("md5"), respParams.get("chinabank.sign"))){
				return;
			}
			//使用DES解密data交易数据,des密钥网银在线商户后台设置
			String dataDES = DES.decrypt(respParams.get("chinabank.data"), Context.getValue("des"), respParams.get("xml.charset"));
	//		System.out.println("dataDES,,,,,,,,,"+dataDES);
			Map<String, String> dataParams= XmlMsg.parse(dataDES);
			System.out.println("交易类型："+dataParams.get("data.trade.type"));
			System.out.println("交易号："+dataParams.get("data.trade.id"));
			System.out.println("原交易跟踪号："+dataParams.get("data.trade.oid"));
			System.out.println("交易金额："+dataParams.get("data.trade.amount"));
			System.out.println("交易货币："+dataParams.get("data.trade.currency"));
			System.out.println("交易日期："+dataParams.get("data.trade.date"));
			System.out.println("交易时间："+dataParams.get("data.trade.time"));
			System.out.println("交易备注："+dataParams.get("data.trade.note"));
			System.out.println("交易状态："+dataParams.get("data.trade.status"));
			System.out.println("跳转URL："+dataParams.get("data.trade.url"));
			System.out.println("交易返回码："+dataParams.get("data.return.code"));
			System.out.println("交易返回码描述："+dataParams.get("data.return.desc"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		MotoPayService service = new MotoPayService();
		String respStr = service.trade("V");
	//	String respStr="";
		service.operate(respStr);
	}
	/**
	 * 此方法为了方便，把data元素中的所有子元素都返回了
	 * 网银在线快捷支付会根据交易类型选择传入参数
	 * @return
	 */
	public String getDataElmtXML(String tradeType){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("charset", Context.getValue("charset"));
		if("V".equals(tradeType)){
			//签约
			params.put("card_bank", Context.getValue("card_bank"));
			params.put("card_type", Context.getValue("card_type"));
			params.put("card_no", Context.getValue("card_no"));
			params.put("card_exp", Context.getValue("card_exp"));
			params.put("card_cvv2", Context.getValue("card_cvv2"));
			params.put("card_name", Context.getValue("card_name"));
			params.put("card_idtype", Context.getValue("card_idtype"));
			params.put("card_idno", Context.getValue("card_idno"));
			params.put("card_phone", Context.getValue("card_phone"));
			params.put("trade_type", tradeType);
			params.put("trade_id",  Context.getValue("trade_id"));
			params.put("trade_amount", Context.getValue("trade_amount"));
			params.put("trade_currency", Context.getValue("trade_currency"));
			params.put("trade_notice", Context.getValue("trade_notice"));
			params.put("trade_note", Context.getValue("trade_note"));
			params.put("trade_limittime", Context.getValue("trade_limittime"));
		}else if("S".equals(tradeType)){
			//消费
			params.put("card_bank", Context.getValue("card_bank"));
			params.put("card_type", Context.getValue("card_type"));
			params.put("card_no", Context.getValue("card_no"));
			params.put("card_exp", Context.getValue("card_exp"));
			params.put("card_cvv2", Context.getValue("card_cvv2"));
			params.put("card_name", Context.getValue("card_name"));
			params.put("card_idtype", Context.getValue("card_idtype"));
			params.put("card_idno", Context.getValue("card_idno"));
			params.put("card_phone", Context.getValue("card_phone"));
			params.put("trade_type", tradeType);
		//	params.put("trade_id", System.currentTimeMillis());
			params.put("trade_id", Context.getValue("trade_id"));
			params.put("trade_amount", Context.getValue("trade_amount"));
			params.put("trade_currency", Context.getValue("trade_currency"));
			params.put("trade_date", Context.getValue("trade_date"));
			params.put("trade_time", Context.getValue("trade_time"));
		//	params.put("trade_notice", Context.getValue("trade_notice"));
			params.put("trade_code", Context.getValue("trade_code"));
		}else if("R".equals(tradeType)){
			//退款
			params.put("trade_type", tradeType);
		//	params.put("trade_id",  System.currentTimeMillis());
			params.put("trade_id", Context.getValue("trade_id"));
			params.put("trade_oid",  Context.getValue("trade_oid"));
			params.put("trade_amount", Context.getValue("trade_amount"));
			params.put("trade_currency", Context.getValue("trade_currency"));
			params.put("trade_date", Context.getValue("trade_date"));
			params.put("trade_time", Context.getValue("trade_time"));
			params.put("trade_notice", Context.getValue("trade_notice"));
		}else if("Q".equals(tradeType)){
			//查询
			params.put("trade_type", tradeType);
			params.put("trade_id", Context.getValue("trade_id"));
		}else{
			return "";
		}
		return FTL.doString(params,  "/xml/", "http_des_req_data.xml");
	}
	public String getReqXML(String dataElmtDES, String signMD5){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("charset", Context.getValue("charset"));
		params.put("version", Context.getValue("version"));
		params.put("merchant", Context.getValue("merchant"));
		params.put("terminal", Context.getValue("terminal"));
		params.put("data", dataElmtDES);
		params.put("sign", signMD5);
		return FTL.doString(params, "/xml/", "http_des_req.xml");
	}

}
