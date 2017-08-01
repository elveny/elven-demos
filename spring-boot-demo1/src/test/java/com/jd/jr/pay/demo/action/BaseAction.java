package com.jd.jr.pay.demo.action;

import com.jd.jr.pay.demo.util.StringEscape;

public class BaseAction {
	
	/**
	 * 
	 * @Title: doFilterCharProcess
	 * @Description: 执行特殊字符处理
	 * @param: @param param
	 * @param: @return
	 * @return: String
	 * @throws
	 * @author mythling
	 * @Date 2016年8月6日 下午3:54:58
	 */
	public String doFilterCharProcess(String param) {
		if (param == null || param.equals("")) {
			return param;
		} else {
			return StringEscape.htmlSecurityEscape(param);
		}
	}

}
