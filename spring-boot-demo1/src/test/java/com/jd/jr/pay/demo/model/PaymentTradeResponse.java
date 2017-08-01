package com.jd.jr.pay.demo.model;

import com.jd.jr.pay.gate.signature.vo.JdPayBaseResponse;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;


@XStreamAlias("jdpay")
public class PaymentTradeResponse extends JdPayBaseResponse{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 交易流水  数字或字母
     */
    private String tradeNum;
    /**
     * 交易类型
     */
    private Integer tradeType;
    /** 交易备注
    */
   private String note;
   /**
    * 支付总金额
    */
   private Long amount;
   /**
    * 交易返回状态  成功：2，失败，3
    */
   private String status;
    /**
     * 交易列表
     */
    private List<PayTradeVo> payList;


    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }
    
	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the payList
	 */
	public List<PayTradeVo> getPayList() {
		return payList;
	}

	/**
	 * @param payList the payList to set
	 */
	public void setPayList(List<PayTradeVo> payList) {
		this.payList = payList;
	}

	/** 
	 * @Title:        toString 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @return    
	 * @throws 
	 * @author       mythling
	 * @Date          2016年4月28日 下午3:41:03 
	 */
	@Override
	public String toString() {
		return "PaymentTradeResponse [tradeNum=" + tradeNum + ", tradeType=" + tradeType + ", note=" + note + ", amount="
				+ amount + ", status=" + status + ", payList=" + payList + "]";
	}

	
    
   

    

	
    
    
}
