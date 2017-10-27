package com.wangyin.npp.trade.demo.util;
import com.alibaba.fastjson.JSON;
import com.wangyin.npp.trade.demo.base.AgencyConfig;
import com.wangyin.npp.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class RequestUtil {
	private static Log log = LogFactory.getLog(RequestUtil.class);
	private static AgencyConfig agencyConfig;

	public  RequestUtil(AgencyConfig agencyConfig){
		this.agencyConfig = agencyConfig;
	}
	/**
	 * 请求demo(带证书加密) 外部商户https参考使用
	 * @throws Exception
	 */
	public String tradeRequestSSL(Map<String, String> paramMap,String url,String encryptType) throws Exception{
		//请求数据，http请求时将此map中的数据放入Form表单中
		Map<String,String> requestMap = enctyptData(paramMap,encryptType);
		//https请求
		WyHttpClientUtil util  = new WyHttpClientUtil();
		String content = util.postSSLUrlWithParams(url,requestMap,null);
		return content;

	}

	

	/**
	 * 加密数据  RSA或3DES
	 * @param paramMap
	 * @param encrtyptType
	 * @return
	 * @throws Exception
	 */
	private Map<String,String> enctyptData(Map<String, String> paramMap,String encrtyptType) throws Exception{
		Map<String,String> requestMap = new HashMap<String,String>();
		//签名
		String sign = EnctyptUtil.sign(paramMap, agencyConfig.getShaKey());
		if(StringUtils.isEmpty(encrtyptType)){
			paramMap.put("sign_type",AgencyContants.singType);
			paramMap.put("sign_data",sign);//设置签名数据
			requestMap = paramMap;
		}else{
			requestMap.put("sign_type",AgencyContants.singType);//签名类型
			requestMap.put("encrypt_type",encrtyptType);//加密类型
			requestMap.put("customer_no",paramMap.get("customer_no"));//提交者会员号
			requestMap.put("sign_data",sign);//设置签名数据
			if("RSA".equals(encrtyptType)){
				String rootPath = agencyConfig.getKeyStorePath();
				//数据加密
				String res= EnctyptUtil.signEnvelop(paramMap, agencyConfig.getKeyStorePassWord(), rootPath+agencyConfig.getPriKeyStoreName(), rootPath+agencyConfig.getPubKeyStoreName());
				requestMap.put("encrypt_data",res);//设置加密数据
			}else if("DES".equals(encrtyptType)||"3DES".equals(encrtyptType)){
				//DES加密秘钥先对秘钥BASE64反解密后再对数据进行加密
				String encryptionDataKey = new String(CodecUtils.encodeBase64(CodecUtils.hex2byte(agencyConfig.getDesKey()), false));
				String encryptData =EnctyptUtil.encryptDES(paramMap,encryptionDataKey);
				requestMap.put("encrypt_data",encryptData);//设置加密数据
			}
		}
		return requestMap;
	}


	/**
	 * 请求返回数据后处理
	 * @param data
	 * @return 返回验证签名通过后的数据
	 * @throws UnsupportedEncodingException
	 */
	public Map<String,String> verifySignReturnData(String data) throws UnsupportedEncodingException{
		Map<String,String> map = new HashMap<String,String>();
		if(!StringUtils.isEmpty(data)){
			try{
				map = JSON.parseObject(data,Map.class);
			}catch(Exception e){
				log.info("--->非json格式 data="+data);
				map.put("response_code", data);
				return map;
			}
			//必须签名验证，确保数据一致性
			map=EnctyptUtil.verifySign(map,agencyConfig.getShaKey());
		}else{
			map.put("response_code", CodeConst.RETURN_PARAM_NULL);
			map.put("response_message", "返回数据为空");
		}
		return map;
	}
	
	
	/**
	 *  MD5签名验证
	 * @param data
	 * @param salt
	 * @param charset
	 * @return
	 */
	public static Map<String,String> signVerifyData(String data,String salt,String charset) {
		try {
		    String[] results = data.split("&");
	        Map<String, String> responseMap = new HashMap<String, String>();
	        for (String value : results) {
	            //不要用split("="),因为value由base64生成，可能含有多个=号
	            int n = value.indexOf("=");
	            responseMap.put(value.substring(0, n), value.substring(n + 1));
	        }
	        if(null!=responseMap.get("DATA")){
	        	String response_data = new String(Base64.decodeBase64(responseMap.get("DATA")),charset);
	        	Map map = JSON.parseObject(response_data,Map.class);
	            String json = JSON.toJSONString(map);
	            log.info("refund response_data="+json);
	            //生成MD5签名
	            String MD5Data = Base64.encode(json.getBytes(charset));
	            log.info("MD5 response_sign="+responseMap.get("SIGN"));
	           
		        String validateSign = MD5.md5(MD5Data, salt, true);
		        log.info("MD5 verify_sign="+MD5.md5(MD5Data, salt, true));
				if(responseMap.get("SIGN").toUpperCase().equals(validateSign)){
		        	log.info("===>MD5 验证签名通过 " );
		        	return map;
		        }else{
		        		log.error("===>MD5验证签名不通过");
		    	}
				}
	        } catch (Exception e) {
	        	e.printStackTrace();
		}
	        	
	        return null;

	}
	

	/**
	 * 签名验证demo（通知接口接收到数据后需要验签）
	 * @param data
	 * @return 返回验证签名通过后的数据
	 * @throws UnsupportedEncodingException
	 */
	public Map<String,String> verifySingNotify(String data) throws UnsupportedEncodingException{

		String paramSplitStr[]=data.split("\\&");
		Map<String, String> mapStr = new HashMap<String, String>();
		for(int i=0;i<paramSplitStr.length;i++){
			mapStr.put(paramSplitStr[i].substring(0,paramSplitStr[i].indexOf("=")),paramSplitStr[i].substring(paramSplitStr[i].indexOf("=")+1,paramSplitStr[i].length()));
		}
		//必须签名验证，确保数据一致性
		Map<String,String> map=EnctyptUtil.verifySign(mapStr, agencyConfig.getShaKey());
		return map;

	}


	/**
	 * 从商户客户端取得交易相关信息
	 * @param request
	 * @return
	 */
	public static String receiveParam(HttpServletRequest request) {
		log.info("接收参数开始");
		BufferedReader bufferedReader = null;
		StringBuffer data = new StringBuffer("");
		try {
			request.setCharacterEncoding("UTF-8");
			bufferedReader = request.getReader();
			if (bufferedReader == null) {
				return null;
			}
			String line = "";
			while ((line = bufferedReader.readLine())!=null){
				data.append(line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			log.error("通讯异常"+ e);
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException ie) {
				log.error("通讯异常", ie);
			}
		}
		log.info("接收参数成功完成");
		String param = data.toString();
		log.info("请求报文：[" + param + "]");
		return param;
	}

	/**
	 * 响应的数据
	 * @param response
	 * @param returnData
	 */
	public static void outPutDataAsBytes(HttpServletResponse response, String returnData) {
		ServletOutputStream stream = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			stream = response.getOutputStream();
			stream.write(returnData.getBytes());
		} catch (Exception e) {
			log.error("返回报文出错", e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					log.error("outPutDataAsBytes中关闭流失败,原因:" + e);
				}
			}
		}
	}

    /**
     * 向API发送请求流程
     *
     * @param requestMap 报文主体数据
     * @param charset
     * @param key     md5加密用的key，api提供
     * @param url
     * @throws Exception
     */
    public static String process(Map<String, String> requestMap, String charset, String key, String url) throws Exception {

    	log.info("原始数据: charset: "+charset+", key: "+key+", url: "+url+", requestMap: "+JSON.toJSONString(requestMap));
    	log.info("=================1.准备http post请求数据===================");
        //1.准备请求数据
        Map<String, String> requestData = prepareRequestData(requestMap, key, charset);
        log.info("requestData: "+JSON.toJSONString(requestData));

        log.info("=================2.发送http post请求===================");
		//http请求
		WyHttpClientUtil util  = new WyHttpClientUtil();
		String content = util.postSSLUrlWithParams(url,requestData,null);

		log.info("=================3.处理http response===================");
        //3.处理http response
		log.info("response content: " + content);
		
		 return content;

    }
    
    public static String bytes2Hex(byte[] bts) {
        StringBuilder temp= new StringBuilder("");
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                temp.append("0");
            }
            temp.append(tmp);
        }
        return temp.toString();
    }



    private static Map<String, String>  prepareRequestData(Map<String, String> dataMap, String key, String charset) throws Exception {
        //将map中的数据转换成json格式的string
        String json = JSON.toJSONString(dataMap);
        String requestData =  Base64.encode(json.getBytes(charset));
        log.info("response requestData: " + requestData);
        //生成MD5签名
        String requestSign = MD5.md5(requestData, key, true);
        Map<String, String> requestMap = new HashMap<String,String>(3);
        requestMap.put("CHAR", charset);
        requestMap.put("DATA", requestData);
        requestMap.put("SIGN", requestSign);
   
        return requestMap;
    }

    
    /**
     * 处理http response
     * @param response
     * @param charset
     * @return
     * @throws IOException
     */
    private static Map<String, String> parseResponse(HttpResponse response, String charset) throws IOException {
        String responseStr = "";

        //将response转换成string
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            responseStr = EntityUtils.toString(responseEntity, charset);
        }
        log.info("parseResponse ----- responseStr: " + responseStr);

        //将responseStr转换成map
        String[] results = responseStr.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String value : results) {
            //不要用split("="),因为value由base64生成，可能含有多个=号
            int n = value.indexOf("=");
            map.put(value.substring(0, n), value.substring(n + 1));
        }
        return map;
    }

}
