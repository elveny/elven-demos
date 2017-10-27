package com.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.demo.web.constant.MerchantConstant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 创建人: 王振龙
 * 日期:  2014-8-26
 * 说明:  支付请求-交易信息构造
 */
@Controller
@RequestMapping(value = "/demo")
public class WebPayIndex {

    @Resource
    private MerchantConstant merchantConstant;

    @RequestMapping(value = "/pay/index", method = RequestMethod.GET)
    public String execute(HttpServletRequest httpServletRequest) {

        /**
         * 设置一些初始数据,也可从页面上填写
         */
        httpServletRequest.setAttribute("tradeNum", merchantConstant.getMerchantNum() + System.currentTimeMillis());
        httpServletRequest.setAttribute("merchantNum", merchantConstant.getMerchantNum());
        httpServletRequest.setAttribute("successCallbackUrl", merchantConstant.getSuccessCallbackUrl());
        httpServletRequest.setAttribute("failCallbackUrl", merchantConstant.getFailCallbackUrl());
        httpServletRequest.setAttribute("notifyUrl", merchantConstant.getNotifyUrl());
        httpServletRequest.setAttribute("serverUrl", merchantConstant.getWangyinServerPayUrl());
        httpServletRequest.setAttribute("tradeTime", new Date());
        return "payIndex";
    }

}
