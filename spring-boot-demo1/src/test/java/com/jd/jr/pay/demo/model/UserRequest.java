/**   
 * @Title: UserRequest.java 
 * @Package com.jd.jr.pay.demo.model 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author mythling   
 * @date 2017年3月2日 下午9:55:41 
 * @version V1.0   
 */
package com.jd.jr.pay.demo.model;

/**
 * @ClassName: UserRequest
 * @Description:
 * @author mythling
 * @date 2017年3月2日 下午9:55:41
 * @version V1.0
 */
public class UserRequest {

	/**
	 * 访问接口版本号
	 */
	private String version;

	/**
	 * 商户号
	 */
	private String merchant;

	/**
	 * 用户ID
	 */
	private String userId;

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRequest [version=");
		builder.append(version);
		builder.append(", merchant=");
		builder.append(merchant);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
