package wangyin.wepay.join.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wangyin.wepay.join.demo.web.constant.MerchantConstant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 创建人: 王振龙
 * 日期:  2014-8-26
 * 说明:  退款-退款信息构造
 */
@Controller
@RequestMapping(value = "/demo")
public class WebRefundIndex {

    @Resource
    private MerchantConstant merchantConstant;

    @RequestMapping(value = "/refund/index", method = RequestMethod.GET)
    public String execute(HttpServletRequest httpServletRequest) {
        httpServletRequest.setAttribute("merchantNum", merchantConstant.getMerchantNum());
        httpServletRequest.setAttribute("nowTime", new Date());
        return "refundIndex";
    }
}

