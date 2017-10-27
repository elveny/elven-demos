package com.wangyin.npp.trade.demo.service.impl;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wangyin.npp.trade.demo.base.AgencyConfig;
import com.wangyin.npp.trade.demo.service.AgencyPay;
import com.wangyin.npp.trade.demo.util.AgencyContants;
import com.wangyin.npp.trade.demo.util.RequestUtil;
import com.wangyin.npp.util.SignUtils;
/**
 * @author wangtiezhi
 * @since: 16-12-22 下午2:40
 * @version: 1.0.0
 */
public class AgencyPayImpl implements AgencyPay {
	private static Log logger = LogFactory.getLog(AgencyPayImpl.class);
    private RequestUtil requestUtil;
    private AgencyConfig agencyConfig; 
    public AgencyPayImpl(AgencyConfig agencyConfig){
    	this.agencyConfig = agencyConfig;
    	requestUtil = new RequestUtil(agencyConfig);
    }
	@Override
	public String getAgreementSignUrl(Map<String, String> paramMap) {
		String agreementSignUrl = null;
		try {
			if (null != paramMap && paramMap.size() > 0) {
				// 签名数据
				String sign_data = SignUtils.sign(paramMap, AgencyContants.singType, agencyConfig.getShaKey(), AgencyContants.charset);
				paramMap.put("sign_data", sign_data);
				agreementSignUrl = agencyConfig.getUrlPrefix() + "/agreement_sign";
				StringBuffer stringBuffer = new StringBuffer(agreementSignUrl).append("?sign_type=SHA-256");
				for (String key : paramMap.keySet()) {
					stringBuffer.append("&" + key + "=" + paramMap.get(key));
				}
				agreementSignUrl = stringBuffer.toString();
			}
		} catch (Exception e) {
			logger.error("===> 获取签约url发生异常 paramMap="+paramMap+e);
		}
		return agreementSignUrl;
	}

	@Override
	public  Map<String,String> agreementQuery(Map<String, String> paramMap) {
		
		Map<String,String> result = null;
		try{
		    String url = agencyConfig.getUrlPrefix()+"/agreement_query";
	        String responseText = requestUtil.tradeRequestSSL(paramMap, url, null);
	        //验证数据
	        result = requestUtil.verifySignReturnData(responseText);
	        if(result==null){
	            logger.error("===> 协议查询接口返回数据异常 (或验签失败)requestMap="+paramMap);
	        }else{
	            logger.info("===> 协议查询接口返回的验证后数据:"+result);
	        }
		}catch(Exception e) {
			logger.error("===> 协议查询接口发生异常 paramMap="+paramMap+e);
		}
        return result;
	}
	@Override
	public Map<String,String> agreementCancel(Map<String, String> paramMap) {
		Map<String,String> result = null;
		try{
			  String url = agencyConfig.getUrlPrefix()+"/agreement_cancel";
			  String responseText = requestUtil.tradeRequestSSL(paramMap,url,null);
	          //验证数据
	          result = requestUtil.verifySignReturnData(responseText);
	          if(result==null){
	        	  logger.error("===> 解约接口返回数据异常 (或验签失败)requestMap="+paramMap);
	          }else{
	              logger.info("===> 解约接口返回的验证后数据:"+result);
	          }		
          }catch(Exception e) {
  			logger.error("===> 协议解约接口发生异常 paramMap="+paramMap+e);
  		}
          return result;
	}

	@Override
	public Map<String, String> trade(Map<String, String> paramMap,String encryptType) {
		Map<String,String> result = null;
		try{
	        String url = agencyConfig.getUrlPrefix()+"/agreement_pay";
	        String  responseText = requestUtil.tradeRequestSSL(paramMap, url, encryptType);
	        //验证数据
	        result = requestUtil.verifySignReturnData(responseText);
	        if(result==null){
	            logger.error("===> 代扣交易返回数据异常(或验签失败) requestMap="+paramMap);
	        }else{
	            logger.info("===> 代扣交易接口返回的验证后数据:"+result);
	        }
		}catch(Exception e) {
			logger.error("===> 代扣交易接口发生异常 paramMap="+paramMap+e);
		}
        return result;
	}

	@Override
	public Map<String, String> tradeQuery(Map<String, String> paramMap) {
		Map<String,String> result = null;
		try{
	        String url = agencyConfig.getUrlPrefix()+"/trade_query";
	        String  responseText = requestUtil.tradeRequestSSL(paramMap, url, null);
	        //验证数据
	        result = requestUtil.verifySignReturnData(responseText);
	        if(result==null){
	            logger.error("===> 代扣交易查询返回数据异常(或验签失败) requestMap="+paramMap);
	        }else{
	            logger.info("===> 代扣交易查询接口返回的验证后数据:"+result);
	        }
		}catch(Exception e) {
			logger.error("===> 代扣交易查询接口发生异常 paramMap="+paramMap+e);
		}
        return result;
	}

	@Override
	public Map<String, String> balanceQuery(Map<String, String> paramMap) {
		Map<String,String> result = null;
		try{
		    String url = agencyConfig.getUrlPrefix()+"/account_query";
	        String  responseText = requestUtil.tradeRequestSSL(paramMap, url, null);
	        //验证数据
	        result = requestUtil.verifySignReturnData(responseText);
	        if(result==null){
	            logger.error("===> 账户余额查询返回数据异常(或验签失败) requestMap="+paramMap);
	        }else{
	            logger.info("===> 账户余额查询接口返回的验证后数据:"+result);
	        }
		}catch(Exception e) {
			logger.error("===> 账户余额查询接口发生异常 paramMap="+paramMap+e);
		}
        return result;
	}

	@Override
	public Map<String, String> RefundTrade(Map<String, String> paramMap) {
		Map<String,String> result = null;
		try{
		 String responseText =  RequestUtil.process(paramMap, AgencyContants.charset, agencyConfig.getMd5Key(), agencyConfig.getRefundUrl());// KEY,URL根据需要修改
	        //解析数据
	        result = RequestUtil.signVerifyData(responseText,agencyConfig.getMd5Key(),AgencyContants.charset);
	        if(result==null){
	            logger.error("===> 退款交易返回数据异常 requestMap="+paramMap);
	        }else{
	            logger.info("===> 退款交易接口返回的验证后数据:"+result);
	        }
		}catch(Exception e) {
			logger.error("===> 退款交易接口发生异常 paramMap="+paramMap+e);
		}
		return result;
	}

	@Override
	public Map<String, String> RefundQuery(Map<String, String> paramMap) {
		Map<String,String> result = null;
		try{
			String responseText =  RequestUtil.process(paramMap,AgencyContants.charset, agencyConfig.getMd5Key(), agencyConfig.getRefundUrl());// KEY,URL根据需要修改
	        //解析数据
	        result = RequestUtil.signVerifyData(responseText,agencyConfig.getMd5Key(),AgencyContants.charset);
	        if(result==null){
	            logger.error("===> 退款交易查询返回数据异常 (或验签失败)requestMap="+paramMap);
	        }else{
	            logger.info("===> 退款交易查询接口返回的验证后数据:"+result);
	        }
		}catch(Exception e) {
			logger.error("===> 退款交易查询接口发生异常 paramMap="+paramMap+e);
		}
		return result;
	}

	@Override
	public Map<String, String> refundNotifyMock(HttpServletResponse response, HttpServletRequest request) {
		 Map<String, String> result = null;
		try { 
			String param = RequestUtil.receiveParam(request);
			if(StringUtils.isEmpty(param)){
				throw new RuntimeException("通知结果为空");
			}
	        result = RequestUtil.signVerifyData(param,agencyConfig.getMd5Key(),AgencyContants.charset);
//	        if(null!=result){
//	        	 RequestUtil.outPutDataAsBytes(response, "success");
//	        }
			} catch (Exception e) {
				logger.error("===> 异步通知结果处理发生异常 "+e);
			}
		return result;
	}

	@Override
	public Map<String, String> NotifyMock(HttpServletResponse response, HttpServletRequest request) {
	  
        Map<String, String> result = null;
		try {
			String param = RequestUtil.receiveParam(request);
			if(StringUtils.isEmpty(param)){
				throw new RuntimeException("通知结果为空");
			}
			//map 为null则表示验签失败
			result = requestUtil.verifySingNotify(param);
//			if(null!=result){
//	        	 RequestUtil.outPutDataAsBytes(response, "success");
//	        }
		} catch (Exception e) {
			logger.error("===> 异步通知结果处理发生异常 "+e);
		}
		return result;
	}

}
