package com.wangyin.npp.trade.demo;

import java.util.HashMap;
import java.util.Map;

import com.wangyin.npp.trade.demo.base.AgencyConfig;
import com.wangyin.npp.trade.demo.service.impl.AgencyPayImpl;
import com.wangyin.npp.trade.demo.util.SpringUtil;
import com.wangyin.npp.util.CodeConst;
/**
 *  退款处理
 * @author wangtiezhi
 *
 */
public class RefundTrade {
	 /**
     *   退款交易查询
     * 
     */
  
    public void query(){
    	try {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("MERCHANT", Contants.merchant_no);// 根据业务不一样商户号不一样
        dataMap.put("VERSION", "1.0.0");// 固定值 无需修改 
        dataMap.put("TYPE", "Q");// 固定值 无需修改
        dataMap.put("TRADE", "1482845481447Refund");// 退款交易流水号，唯一
        AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
        AgencyPayImpl agencyPay = new AgencyPayImpl(agencyConfig);
        //验证数据
        Map<String,String> mapRes = agencyPay.RefundQuery(dataMap);

        refundTradeHandle(mapRes);
    } catch (Exception e) {
    }

    }
   /**
    *   退款
    * 
    */
 
    public void refundTrade() {
    	try {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("VERSION", "1.0.0");// 固定值无需修改
        dataMap.put("MERCHANT", Contants.merchant_no);// 根据业务不一样商户号不一样
        dataMap.put("TYPE", "R");// 固定值无需修改
        dataMap.put("TRADE", System.currentTimeMillis()+"Refund");//退款交易流水号，唯一
        dataMap.put("ORDER", "1482912867228_coll");// 消费时的out_trade_no值，原交易流水号
        dataMap.put("AMOUNT", "1");//退款金额 单位 分
        dataMap.put("CURRENCY", "CNY");// 无需修改  固定值 CNY 人民币
        dataMap.put("DATETIME", "2016-08-09 12:16:56 0");//交易时间  格式 ：yyyy-MM-dd HH:mm:ss S 
        dataMap.put("NOTE", "test");// 备注
        dataMap.put("NOTICE", "http://XXX.XXX.XXX");// 异步通知地址
        
        AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
        AgencyPayImpl agencyPay = new AgencyPayImpl(agencyConfig);
        //验证数据
        Map<String,String> mapRes = agencyPay.RefundTrade(dataMap);
        refundTradeHandle(mapRes);
    	 } catch (Exception e) {
         }
    }
    
    private void refundTradeHandle( Map<String,String> mapRes){
    	if(mapRes==null){
            System.out.println("签名不正确");
        }else{
        	if("0000000".equals(mapRes.get("CODE"))){
	            String trade_status = mapRes.get("STATUS");
	            if(CodeConst.REFUND_TRADE_FINI.equals(trade_status)){
	                System.out.println("退款交易受理成功");//查询时代表处理完成
	                //TODO 成功后业务逻辑
	            }else if(CodeConst.REFUND_TRADE_CLOS.equals(trade_status)){
	                System.out.println("退款交易关闭，交易失败");
	                //TODO 失败后业务逻辑
	            }else if(CodeConst.REFUND_TRADE_WPAR.equals(trade_status)){
	                System.out.println("退款交易等待支付结果，处理中");//需查询交易获取结果或等待通知结果
	                //TODO 处理中业务逻辑
	            }
        	} else{
            	System.out.println("===> 未知异常：" + mapRes.get("CODE") + "  " + mapRes.get("DESC"));
            }
        }
    }
}
