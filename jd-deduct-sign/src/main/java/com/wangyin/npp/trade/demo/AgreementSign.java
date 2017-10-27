package com.wangyin.npp.trade.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wangyin.npp.trade.demo.base.AgencyConfig;
import com.wangyin.npp.trade.demo.service.impl.AgencyPayImpl;
import com.wangyin.npp.trade.demo.util.AgencyContants;
import com.wangyin.npp.trade.demo.util.RequestUtil;
import com.wangyin.npp.trade.demo.util.SpringUtil;
import com.wangyin.npp.util.DateUtils;
import com.wangyin.npp.util.ResultDealUtils;
/**
 * 签约处理
 * @author wangtiezhi
 *
 */
public class AgreementSign {
	
	private static Log logger = LogFactory.getLog(AgreementSign.class);
	 /**
     * 获取网银签约跳转地址
     * @return
     */
    public String getAgreementSignUrl() {
        String url = "";
        try {

            // 参数放入map
            Map<String, String> mapStr=new HashMap<String, String>();
            mapStr.put("customer_no",Contants.customer_no); //会员号
            mapStr.put("templet_no",Contants.templet_no); //模板号 实际过程中需要进行申请及填写  
            mapStr.put("out_trade_no",System.currentTimeMillis()+"_sign"); //请求号（该请求号是业务系统生成，查询问题时必须提供，所以建议业务方请求代付前存储下来
            mapStr.put("request_datetime", DateUtils.getStringDate()); //请求时间
           
            mapStr.put("pay_tool","COLL"); //支付工具 代收
            mapStr.put("trade_source","JDJR"); //交易来源  申请分配，为了区分商户服务
//          mapStr.put("return_url","http://www.jd.com"); //回跳地址
            mapStr.put("notify_url","http://172.24.5.238:8081/apiRequest/mockUNCNotifyUrl"); //通知地址
			/**
			 * 指定用户信息签约
			 */
//            mapStr.put("bank_account_no","6222620910002880965"); //银行卡号
//            mapStr.put("bank_account_name","刘燕"); //持卡人姓名
//            mapStr.put("id_number","140108199011060841"); //身份证号
//            mapStr.put("mobile_no","13611199000"); //手机号
            // 签名
            AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
            AgencyPayImpl agencyPay = new AgencyPayImpl(agencyConfig);
            url=  agencyPay.getAgreementSignUrl(mapStr);
        } catch (Exception e) {
        }
        return url;
    }
    
    /**
     * 签约--协议查询接口
     * 签约订单号和协议号二选一，都可查询到协议状态
     * @return
     */
    public Map<String,String> agreementQuery() {

        Map<String, String> mapStr=new HashMap<String, String>();
        mapStr.put("customer_no",Contants.customer_no); //会员号
        mapStr.put("request_datetime", DateUtils.getStringDate()); //请求时间
        mapStr.put("out_trade_no","06ded20d76769a4289b5caa08d028b961"); //请求号（该请求号是业务系统生成，查询问题时必须提供，所以建议业务方请求代付前存储下来）
        mapStr.put("trade_source","OUT_PC"); //交易来源   OUT_PC PC端；OUT_APP 移动端
        mapStr.put("agreement_no",Contants.agreement_no); //协议号
        AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
        AgencyPayImpl agencyPay = new AgencyPayImpl(agencyConfig);
        Map<String,String> result =  agencyPay.agreementQuery(mapStr);
        logger.info("===> 协议查询接口返回的验证后数据:"+result);
        if(result==null){
            logger.info("===> 返回数据异常");
        }else{
            String agreement_status = result.get("agreement_status");
            logger.info("===> 返回协议状态："+ResultDealUtils.handleAgreementStatus(agreement_status));    
         }
       
        return result;
    }
    /**
     * 签约解约
     * 
     * @return
     */
    public Map<String,String> agreementCancel() {

        Map<String, String> mapStr=new HashMap<String, String>();
        mapStr.put("customer_no",Contants.customer_no); //会员号
        mapStr.put("request_datetime", DateUtils.getStringDate()); //请求时间
        mapStr.put("out_trade_no",System.currentTimeMillis()+"_cancel"); //请求号（该请求号是业务系统生成，查询问题时必须提供，所以建议业务方请求代付前存储下来
        mapStr.put("trade_source","OUT_PC"); //交易来源   OUT_PC PC端；OUT_APP 移动端
        mapStr.put("agreement_no",Contants.agreement_no); //协议号
        AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
        AgencyPayImpl agencyPay = new AgencyPayImpl(agencyConfig);
        Map<String,String> result =  agencyPay.agreementCancel(mapStr);
        logger.info("===> 协议解约接口返回的验证后数据:"+result);
        if(result==null){
            logger.info("===> 返回数据异常");
        }else{
            String agreement_status = result.get("agreement_status");
            logger.info("===> 协议解约返回协议状态："+ResultDealUtils.handleAgreementStatus(agreement_status));    
         }
        return result;
    }
    
    /**
     * API签约请求
     * @return
     */
    public void agreementSignRequest() {
        logger.info("agreementSignRequest 开始");
        String url = "";
        try {

            // 参数放入map
            Map<String, String> mapStr=new HashMap<String, String>();
            mapStr.put("customer_no",Contants.customer_no); //会员号
            mapStr.put("templet_no",Contants.templet_no); //模板号 实际过程中需要进行申请及填写
            mapStr.put("request_datetime", DateUtils.getStringDate()); //请求时间
            mapStr.put("out_trade_no",System.currentTimeMillis()+"_sign"); //请求号（该请求号是业务系统生成，查询问题时必须提供，所以建议业务方请求交易前存储下来
            mapStr.put("pay_tool","COLL"); // 支付工具 代收 必填 固定值
            mapStr.put("return_url","http://www.jd.com"); //同步回跳地址
            mapStr.put("notify_url","http://XXX.XXX.XXX"); //异步通知地址
//----------------------测试时需提供真实卡信息进行联调---------------------------------------------------            
            mapStr.put("payee_bank_code","CMB"); //银行简码  可参考京东提供的代扣限额表
            mapStr.put("payee_bank_fullname",""); //出款银行全称 银行 + 分行 + 支行名称
            mapStr.put("payee_card_type","DE"); //卡类型 借记卡 DE，信用卡 CR
            mapStr.put("payee_account_type","P"); //帐户类型，对私户 P，对公户 C 支付工具是代收的必填
            mapStr.put("payee_account_no",""); //银行卡卡号
            mapStr.put("payee_account_name",""); //银行卡姓名

            mapStr.put("payee_id_type","ID"); //持卡人证件类型
            mapStr.put("payee_id_no",""); //持卡人证件号  ，ID 身份证，HO 回乡证，TW 台胞证，CE 警官证，SO 士兵证，BAP 边民出入通行证，PA 护照

            mapStr.put("payee_mobile",""); //手机号
            mapStr.put("trade_subject","签约"); //交易描述

            AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
            RequestUtil requestUtil = new RequestUtil(agencyConfig);

            url = agencyConfig.getUrlPrefix()+"/agreement_request";
	        String  responseText = requestUtil.tradeRequestSSL(mapStr, url, AgencyContants.encryptType_RSA);
            Map<String,String> result = requestUtil.verifySignReturnData(responseText);

            logger.info("===> API签约请求返回数据:"+result);
            if(result==null){
                logger.info("===> 返回数据异常");
            }else{
                String agreement_status = result.get("agreement_status");
                logger.info("===> API签约请求返回协议状态："+ResultDealUtils.handleAgreementStatus(agreement_status));    
             }
        } catch (Exception e) {
            logger.error("agreementSignRequest", e);
        }
    }
    

	/**
     * API签约确认
     * @return
     */
    public void agreementSignConfirm() {
        logger.info("agreement_confirm 签约确认开始");
        String url = "";
        try {

            // 参数放入map
            Map<String, String> mapStr=new HashMap<String, String>();
            mapStr.put("customer_no",Contants.customer_no); //会员号
            mapStr.put("agreement_no",Contants.agreement_no); //协议号
            mapStr.put("request_datetime", DateUtils.getStringDate()); //请求时间
            mapStr.put("out_trade_no","linan4409090913"); //请求号(该请求号是业务系统生成，查询问题时必须提供，所以建议业务方请求代付前存储下来)
            mapStr.put("trade_verify_value","588494"); //短信验证码
            mapStr.put("verify_mode","SMS"); //校验方式  短信：SMS
            AgencyConfig  agencyConfig = (AgencyConfig)SpringUtil.getBean("agencyConfig");
            RequestUtil requestUtil = new RequestUtil(agencyConfig);

            url = agencyConfig.getUrlPrefix()+"/agreement_confirm";
	        String  responseText = requestUtil.tradeRequestSSL(mapStr, url, null);
            Map<String,String> result = requestUtil.verifySignReturnData(responseText);

            logger.info("===> 签约确认返回数据:"+result);
            if(result==null){
                logger.info("===> 返回数据异常");
            }else{
                String agreement_status = result.get("agreement_status");
                logger.info("===> 签约确认返回协议状态："+ResultDealUtils.handleAgreementStatus(agreement_status));    
             }
        } catch (Exception e) {
            logger.error("agreement_confirm", e);
        }
        
    }
    
   
}
