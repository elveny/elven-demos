/**   
 * @Title: UserRelationResponse.java 
 * @Package com.jd.jr.pay.demo.model 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author mythling   
 * @date 2017年3月6日 上午11:27:38 
 * @version V1.0   
 */
package com.jd.jr.pay.demo.model;

import com.jd.jr.pay.gate.signature.vo.JdPayBaseResponse;

/**
 * @ClassName: UserRelationResponse
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author mythling
 * @date 2017年3月6日 上午11:27:38
 * @version V1.0
 */
public class UserRelationResponse extends JdPayBaseResponse {

	private String isHas;

	public String getIsHas() {
		return isHas;
	}

	public void setIsHas(String isHas) {
		this.isHas = isHas;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRelationResponse [isHas=");
		builder.append(isHas);
		builder.append(", getVersion()=");
		builder.append(getVersion());
		builder.append(", getMerchant()=");
		builder.append(getMerchant());
		builder.append(", getDevice()=");
		builder.append(getDevice());
		builder.append(", getSign()=");
		builder.append(getSign());
		builder.append(", getResult()=");
		builder.append(getResult());
		builder.append("]");
		return builder.toString();
	}

}
