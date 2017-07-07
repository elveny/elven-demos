package wangyin.wepay.join.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wangyin.wepay.join.demo.web.constant.MerchantConstant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 创建人: 王振龙
 * 日期:  2014-8-26
 * 说明:  交易查询-查询信息构造
 */
@Controller
@RequestMapping(value = "/demo")
public class WebQueryIndex {

    @Resource
    private MerchantConstant merchantConstant;

    @RequestMapping(value = "/query/index", method = RequestMethod.GET)
    public String execute(HttpServletRequest httpServletRequest) {

        httpServletRequest.setAttribute("merchantNum", merchantConstant.getMerchantNum());
        return "queryIndex";
    }
}


