package wangyin.wepay.join.demo.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Decoder;
import wangyin.wepay.join.demo.utils.RSACoder;
import wangyin.wepay.join.demo.utils.SHAUtil;
import wangyin.wepay.join.demo.utils.SignUtil;
import wangyin.wepay.join.demo.web.constant.MerchantConstant;
import wangyin.wepay.join.demo.web.domain.request.TradeQueryRes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


/**
 * 说明：接收异步通知控制器
 * author:wyyusong
 * Date:14-8-25
 * Time:上午11:36
 */


@Controller
@RequestMapping(value = "/demo")
public class WebSuccessCtrl {

    @Resource
    private MerchantConstant merchantConstant;

    @Resource
    private HttpServletRequest request;

    private static final Logger logger = Logger.getLogger(WebSuccessCtrl.class);
    
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String execute(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
	        logger.info("**********接收同步通知开始。**********");
	        //获取通知原始信息
	        
	        TradeQueryRes tradeQueryRes = new TradeQueryRes();
			String token = request.getParameter("token");
			tradeQueryRes.setToken(token);
			
			String tradeNum = request.getParameter("tradeNum");
			tradeQueryRes.setTradeNum(tradeNum);		
			
			String tradeAmount = request.getParameter("tradeAmount");
			tradeQueryRes.setTradeAmount(tradeAmount);		
			
			String tradeCurrency = request.getParameter("tradeCurrency");
			tradeQueryRes.setTradeCurrency(tradeCurrency);
			
			String tradeDate = request.getParameter("tradeDate");
			tradeQueryRes.setTradeDate(tradeDate);
			
			String tradeTime = request.getParameter("tradeTime");
			tradeQueryRes.setTradeTime(tradeTime);
			String tradeStatus = request.getParameter("tradeStatus");
			tradeQueryRes.setTradeStatus(tradeStatus);
			String sign  = request.getParameter("sign");
			
			
			String strSourceData = SignUtil.signString(tradeQueryRes, new ArrayList<String>());	
			
			byte[] decryptBASE64Arr = new BASE64Decoder().decodeBuffer(sign);
			
			String wyPubKey = merchantConstant.getCommonRSAPublicKey();
	
		    byte[] decryptArr = RSACoder.decryptByPublicKey(decryptBASE64Arr, wyPubKey);
		    String decryptStr = new String(decryptArr,"UTF-8");
	
		    String sha256SourceSignString = SHAUtil.Encrypt(strSourceData, null);
		    
		    if (!decryptStr.equals(sha256SourceSignString)){
		    	request.setAttribute("errorMsg", "验证签名失败！");
		    }else{
		    	request.setAttribute("errorMsg", tradeQueryRes.getTradeNum()+":status:"+tradeStatus);
		    }		
			
		    return "queryResult";
        }
    }