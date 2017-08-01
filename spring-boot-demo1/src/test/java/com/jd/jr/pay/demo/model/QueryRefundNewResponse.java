package com.jd.jr.pay.demo.model;

import java.util.List;

import com.jd.jr.pay.gate.signature.vo.JdPayBaseResponse;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class QueryRefundNewResponse extends JdPayBaseResponse {

	private static final long serialVersionUID = 7202174286884566369L;

	@XStreamAlias("refList")
	private List<RefundInfo> refList;

	/**
	 * @return the refList
	 */
	public List<RefundInfo> getRefList() {
		return refList;
	}

	/**
	 * @param refList
	 *            the refList to set
	 */
	public void setRefList(List<RefundInfo> refList) {
		this.refList = refList;
	}

	/**
	 * @Title: toString
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @throws
	 * @author mythling
	 * @Date 2016年9月27日 上午10:50:57
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryRefundNewResponse [refList=");
		builder.append(refList);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
