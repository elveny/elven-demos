package com.wangyin.npp.trade.demo;

import com.wangyin.npp.trade.demo.base.AgencyConfig;
import com.wangyin.npp.trade.demo.service.impl.AgencyPayImpl;
import com.wangyin.npp.trade.demo.util.RequestUtil;
import com.wangyin.npp.trade.demo.util.SpringUtil;
import com.wangyin.npp.util.CodeConst;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wangtiezhi on 2016/7/13.
 */

public class NotifyMock {

    /**
     *  签约回调信息
     * @param response
     * @param request
     */
    public void signNotify(HttpServletResponse response, HttpServletRequest request) {
        AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
        AgencyPayImpl agencyPay = new AgencyPayImpl(agencyConfig);
       
        try {
            Map<String,String> map = agencyPay.NotifyMock(response, request);
            System.out.println(map);
            if(map==null){
                System.out.println("验证签名不成功");
            }else{
                String agreementStatus = map.get("agreement_status");
                if(CodeConst.AGREEMENT_STATUS_FINI.equals(agreementStatus)){
                    System.out.println("协议生效");
                }else if(CodeConst.AGREEMENT_STATUS_BUID.equals(agreementStatus)){
                    System.out.println("协议创建");
                }else if(CodeConst.AGREEMENT_STATUS_WPAR.equals(agreementStatus)){
                    System.out.println("协议待审核");
                }else if(CodeConst.AGREEMENT_STATUS_FAIL.equals(agreementStatus)){
                    System.out.println("协议失败");
                }else if(CodeConst.AGREEMENT_STATUS_CLOS.equals(agreementStatus)){
                    System.out.println("协议关闭");
                }else{
                    System.out.println("未知");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		RequestUtil.outPutDataAsBytes(response, "success");

    }

    /**
     * 交易结果通知
     * @param response
     * @param request
     */

    public void tradeNotify(HttpServletResponse response, HttpServletRequest request) {
        AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
         AgencyPayImpl agencyPay = new AgencyPayImpl(agencyConfig);
        try {
            Map<String,String> map = agencyPay.NotifyMock(response, request);
            System.out.println(map);
            if(map==null){
                System.out.println("验证签名不成功");
            }else{
                tradeCode(map);//处理返回数据
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		RequestUtil.outPutDataAsBytes(response, "success");

    }
    
    /**
     * 退款交易结果通知
     * @param response
     * @param request
     */
    public void refundNotify(HttpServletResponse response, HttpServletRequest request) {
        AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
        AgencyPayImpl agencyPay = new AgencyPayImpl(agencyConfig);
         
         Map<String,String> mapRes = agencyPay.refundNotifyMock(response, request);
		System.out.println(mapRes);
		if(mapRes==null){
		    System.out.println("验证签名不成功");
		}else{
			if("0000000".equals(mapRes.get("CODE"))){
	            String trade_status = mapRes.get("STATUS");
	            if(CodeConst.REFUND_TRADE_FINI.equals(trade_status)){
	                System.out.println("退款交易成功");
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
		RequestUtil.outPutDataAsBytes(response, "success");
    }
    /**
     * 交易结果处理demo
     * @param map
     */
    private static void tradeCode(Map<String,String> map){
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
        }else{
            System.out.println("===> 未知异常：" + map.get("response_code") + "  " + map.get("response_message"));
        }
    }



}
