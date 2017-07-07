package wangyin.wepay.join.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wangyin.wepay.join.demo.utils.DESUtil;
import wangyin.wepay.join.demo.utils.JsonUtil;
import wangyin.wepay.join.demo.utils.SignUtil;
import wangyin.wepay.join.demo.web.constant.MerchantConstant;
import wangyin.wepay.join.demo.web.domain.request.PaySignEntity;
import wangyin.wepay.join.demo.web.domain.request.WebPayReqDto;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 创建人: 王振龙
 * 日期:  2014-8-26
 * 说明:  模拟支付-商户签名
 */
@Controller
@RequestMapping(value = "/demo")
public class WebPaySign {

    @Resource
    private MerchantConstant merchantConstant;

    /**
     * @param webPayReqDto
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/pay/sign", method = RequestMethod.POST)
    public String paySign(WebPayReqDto webPayReqDto, HttpServletRequest httpServletRequest) {
        PaySignEntity wePayMerchantSignReqDTO = new PaySignEntity();

        wePayMerchantSignReqDTO.setVersion(webPayReqDto.getVersion());
        wePayMerchantSignReqDTO.setToken(webPayReqDto.getToken());
        wePayMerchantSignReqDTO.setMerchantNum(webPayReqDto.getMerchantNum());
        wePayMerchantSignReqDTO.setTradeNum(webPayReqDto.getTradeNum());
        wePayMerchantSignReqDTO.setTradeTime(webPayReqDto.getTradeTime());
        wePayMerchantSignReqDTO.setTradeName(webPayReqDto.getTradeName());
        wePayMerchantSignReqDTO.setCurrency(webPayReqDto.getCurrency());
        wePayMerchantSignReqDTO.setMerchantRemark(webPayReqDto.getMerchantRemark());
        wePayMerchantSignReqDTO.setTradeAmount(webPayReqDto.getTradeAmount());
        wePayMerchantSignReqDTO.setTradeDescription(webPayReqDto.getTradeDescription());
        wePayMerchantSignReqDTO.setSuccessCallbackUrl(webPayReqDto.getSuccessCallbackUrl());
        wePayMerchantSignReqDTO.setFailCallbackUrl(webPayReqDto.getFailCallbackUrl());
        wePayMerchantSignReqDTO.setNotifyUrl(webPayReqDto.getNotifyUrl());
        setSpecifyInfo(webPayReqDto,wePayMerchantSignReqDTO);
        
        /**
         * 商户签名
         */
//        String signStr = SignUtil.sign(wePayMerchantSignReqDTO, merchantConstant.getPayRSAPrivateKey());
        String signStr = SignUtil.sign4SelectedKeys(wePayMerchantSignReqDTO, merchantConstant.getPayRSAPrivateKey(),getSignList(wePayMerchantSignReqDTO));
        System.out.println("================:"+getSignList(wePayMerchantSignReqDTO));
        webPayReqDto.setMerchantSign(signStr);
        System.out.println("----------------"+signStr);
        if ("1.0".equals(webPayReqDto.getVersion())) {
            //敏感信息未加密
        } else if ("2.0".equals(webPayReqDto.getVersion())) {
            //敏感信息加密
            try {
                //获取商户 DESkey
                String desKey = merchantConstant.getMerchantDESKey();
                //对敏感信息进行 DES加密
                webPayReqDto.setMerchantRemark(DESUtil.encrypt(webPayReqDto.getMerchantRemark(), desKey, "UTF-8"));
                webPayReqDto.setTradeNum(DESUtil.encrypt(webPayReqDto.getTradeNum(), desKey, "UTF-8"));
                webPayReqDto.setTradeName(DESUtil.encrypt(webPayReqDto.getTradeName(), desKey, "UTF-8"));
                webPayReqDto.setTradeDescription(DESUtil.encrypt(webPayReqDto.getTradeDescription(), desKey, "UTF-8"));
                webPayReqDto.setTradeTime(DESUtil.encrypt(webPayReqDto.getTradeTime(), desKey, "UTF-8"));
                webPayReqDto.setTradeAmount(DESUtil.encrypt(webPayReqDto.getTradeAmount(), desKey, "UTF-8"));
                webPayReqDto.setCurrency(DESUtil.encrypt(webPayReqDto.getCurrency(), desKey, "UTF-8"));
                webPayReqDto.setNotifyUrl(DESUtil.encrypt(webPayReqDto.getNotifyUrl(), desKey, "UTF-8"));
                webPayReqDto.setSuccessCallbackUrl(DESUtil.encrypt(webPayReqDto.getSuccessCallbackUrl(), desKey, "UTF-8"));
                webPayReqDto.setFailCallbackUrl(DESUtil.encrypt(webPayReqDto.getFailCallbackUrl(), desKey, "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("3.0".equals(webPayReqDto.getVersion())||"3.0.5".equals(webPayReqDto.getVersion())) {
        	// 拼接specifyInfoJson
        	
        	//敏感信息加密
            try {
                //获取商户 DESkey
                String desKey = merchantConstant.getMerchantDESKey();
                //对敏感信息进行 DES加密
                webPayReqDto.setMerchantRemark(DESUtil.encrypt(webPayReqDto.getMerchantRemark(), desKey, "UTF-8"));
                webPayReqDto.setTradeNum(DESUtil.encrypt(webPayReqDto.getTradeNum(), desKey, "UTF-8"));
                webPayReqDto.setTradeName(DESUtil.encrypt(webPayReqDto.getTradeName(), desKey, "UTF-8"));
                webPayReqDto.setTradeDescription(DESUtil.encrypt(webPayReqDto.getTradeDescription(), desKey, "UTF-8"));
                webPayReqDto.setTradeTime(DESUtil.encrypt(webPayReqDto.getTradeTime(), desKey, "UTF-8"));
                webPayReqDto.setTradeAmount(DESUtil.encrypt(webPayReqDto.getTradeAmount(), desKey, "UTF-8"));
                webPayReqDto.setCurrency(DESUtil.encrypt(webPayReqDto.getCurrency(), desKey, "UTF-8"));
                webPayReqDto.setNotifyUrl(DESUtil.encrypt(webPayReqDto.getNotifyUrl(), desKey, "UTF-8"));
                webPayReqDto.setSuccessCallbackUrl(DESUtil.encrypt(webPayReqDto.getSuccessCallbackUrl(), desKey, "UTF-8"));
                webPayReqDto.setFailCallbackUrl(DESUtil.encrypt(webPayReqDto.getFailCallbackUrl(), desKey, "UTF-8"));
//                webPayReqDto.setJdPin(DESUtil.encrypt(webPayReqDto.getJdPin(), desKey, "UTF-8"));
//                webPayReqDto.setMerchantOrderNum(DESUtil.encrypt(webPayReqDto.getMerchantOrderNum(), desKey, "UTF-8"));
//                webPayReqDto.setMerchantUserId(DESUtil.encrypt(webPayReqDto.getMerchantUserId(), desKey, "UTF-8"));
//                webPayReqDto.setTradeDetail(DESUtil.encrypt(webPayReqDto.getTradeDetail(), desKey, "UTF-8"));
                if(isNotBlank(wePayMerchantSignReqDTO.getSpecifyInfoJson())){
                	webPayReqDto.setSpecifyInfoJson(DESUtil.encrypt(wePayMerchantSignReqDTO.getSpecifyInfoJson(), desKey, "UTF-8"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        httpServletRequest.setAttribute("tradeAmount", wePayMerchantSignReqDTO.getTradeAmount());
        httpServletRequest.setAttribute("tradeName", wePayMerchantSignReqDTO.getTradeName());

        httpServletRequest.setAttribute("serverUrl", webPayReqDto.getServerUrl());
        httpServletRequest.setAttribute("tradeInfo", webPayReqDto);
        return "paySubmit";
    }
    
    private List<String> getSignList(PaySignEntity wePayMerchantSignReqDTO){
        List<String> signedKeyList = new ArrayList<String>();
        //固定验签字段
        signedKeyList.add("currency");
 		signedKeyList.add("merchantNum");
 		signedKeyList.add("merchantRemark");
 		signedKeyList.add("tradeAmount");
 		signedKeyList.add("tradeDescription");
 		signedKeyList.add("tradeName");
 		signedKeyList.add("tradeTime");
 		signedKeyList.add("tradeNum");
 		signedKeyList.add("notifyUrl");
 		signedKeyList.add("successCallbackUrl");
 		signedKeyList.add("failCallbackUrl");
 		/* ======可选验签字段======== */
		if (isNotBlank(wePayMerchantSignReqDTO.getSpecifyInfoJson())) {
			signedKeyList.add("specifyInfoJson");
		}
		return signedKeyList;
         
    }
    
	private void setSpecifyInfo(WebPayReqDto webPayReqDto,
			PaySignEntity wePayMerchantSignReqDTO) {
		if (isNotBlank(webPayReqDto.getSpecBankCardNo())
				|| isNotBlank(webPayReqDto.getSpecIdCard())
				|| isNotBlank(webPayReqDto.getSpecName())) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("specBankCardNo", webPayReqDto.getSpecBankCardNo());
			map.put("specIdCard", webPayReqDto.getSpecIdCard());
			map.put("specName", webPayReqDto.getSpecName());
			String specifyJson = JsonUtil.write2JsonStr(map);
			wePayMerchantSignReqDTO.setSpecifyInfoJson(specifyJson);
		}
	}
    
	private boolean isNotBlank(String str) {
		if (str != null && !str.trim().equals("")) {
			return true;
		}
		return false;
	}

}
