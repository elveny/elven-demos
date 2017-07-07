package wangyin.wepay.join.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 创建人: 王振龙
 * 日期:  2014-8-26
 * 说明:  demo入口
 */
@Controller
@RequestMapping(value = "/demo")
public class WebDemoIndex {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String execute() {
        return "demoIndex";
    }
}
