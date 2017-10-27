package com.wangyin.npp.trade.demo;

import java.util.HashMap;
import java.util.Map;

import com.wangyin.npp.trade.demo.base.AgencyConfig;
import com.wangyin.npp.trade.demo.service.impl.AgencyPayImpl;
import com.wangyin.npp.trade.demo.util.AgencyContants;
import com.wangyin.npp.trade.demo.util.SpringUtil;
import com.wangyin.npp.util.CodeConst;
import com.wangyin.npp.util.DateUtils;
/**
 *  代扣交易处理
 * @author wangtiezhi
 *
 */
public class TradeWithAgreement {

	/**
	 * 代扣交易
	 * @return
	 */
	   public  Map<String,String> trade(){
	    	System.out.println("===> 代收交易请求:");
	        Map<String, String> mapStr=new HashMap<String, String>();
	        mapStr.put("customer_no",Contants.customer_no); //会员号
	        mapStr.put("merchant_no",Contants.merchant_no); //商户号
	        mapStr.put("request_datetime", DateUtils.getStringDate()); //请求时间
	        mapStr.put("out_trade_no",System.currentTimeMillis()+"_coll"); //请求号（该请求号是业务系统生成，查询问题时必须提供，所以建议业务方请求交易前存储下来）
	        mapStr.put("agreement_no", Contants.agreement_no); //签约产生的协议号
	        mapStr.put("trade_subject","银行卡代收");//交易摘要 必填
	        mapStr.put("trade_amount","1");// 交易金额 单位为分 默认是人民币
	        mapStr.put("pay_tool","COLL"); //支付工具 代收 必填 固定值
	        mapStr.put("notify_url","http://XXX.XXX.XXX"); //异步通知地址
            AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
	        AgencyPayImpl agencyPay = new AgencyPayImpl(agencyConfig);
	        Map<String,String> result =  agencyPay.trade(mapStr,AgencyContants.encryptType_RSA);
	        tradeResultHandle(result);
	        return  result;
	   }
	/**
	 *  交易查询
	 * @return
	 */
	   public  Map<String,String> query(){
		   System.out.println("===> 交易结果查询开始:");
	        Map<String, String> mapStr=new HashMap<String, String>();
	        mapStr.put("customer_no",Contants.customer_no); //会员号
	        mapStr.put("merchant_no",Contants.merchant_no); //商户号
	        mapStr.put("request_datetime", DateUtils.getStringDate()); //请求时间
	        mapStr.put("out_trade_no", ""); //请求号（该请求号是业务系统生成，查询问题时必须提供，所以建议业务方请求交易前存储下来
	        mapStr.put("trade_source","PC_OUT"); //交易来源   OUT_PC PC端；OUT_APP 移动端
	        mapStr.put("trade_type","T_GEN"); //收单交易
            AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
	        AgencyPayImpl agencyPay = new AgencyPayImpl(agencyConfig);
	        Map<String,String> result =  agencyPay.tradeQuery(mapStr);
	        tradeResultHandle(result);
	        return result;
	   }
	   
	   
	   private void tradeResultHandle(Map<String, String> map){
		   if(map==null){
               System.out.println("===> 交易查询返回数据异常");
           }else{
           	if("0000".equals(map.get("response_code"))||"CURRENT_DEAL_NOT_ALLOWED".equals(map.get("response_code"))){//CURRENT_DEAL_NOT_ALLOWED 交易请求流水号重复
	                String trade_status = map.get("trade_status");
	                if(CodeConst.TRADE_FINI.equals(trade_status)){
	                    System.out.println("交易成功");
	                    //TODO 成功后业务逻辑
	                }else if(CodeConst.TRADE_CLOS.equals(trade_status)){
	                    System.out.println("交易关闭，交易失败");
	                    //TODO 失败后业务逻辑
	                }else if(CodeConst.TRADE_WPAR.equals(trade_status)||CodeConst.TRADE_BUID.equals(trade_status)|| CodeConst.TRADE_ACSU.equals(trade_status)){
	                    System.out.println("等待支付结果，处理中");//需查询交易获取结果或等待通知结果
	                    //TODO 处理中业务逻辑
	                }else if(CodeConst.TRADE_REFU.equals(trade_status)){
	                    System.out.println("交易已经退款");
	                    //TODO 处理中业务逻辑
	                }
           	}else{
                   System.out.println("===> 未知异常：" + map.get("response_code") + "  " + map.get("response_message"));
               }
           }
	   }
}
