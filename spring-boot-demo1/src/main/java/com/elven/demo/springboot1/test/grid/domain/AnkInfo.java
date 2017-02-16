package com.elven.demo.springboot1.test.grid.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;


/**
 * @author: qiusheng.wu
 * @version: 1.0
 * @since 2017-01-19
 */
@Entity
@Table(name = "bank_info")
public  class AnkInfo    {

     	     private Integer  id ;
     
        	    	private String  bankCode ;
         	    	private String  bankName ;
         	    	private String  subBankCode ;
         	    	private String  subBankName ;
         	    	private String  parentAreaCode ;
         	    	private String  areaCode ;
         	    	private String  unitedBankNo ;
         	    	private String  clearBankNo ;
         	    	private String  status ;
         	    	private String areaName;
         	    	private String sourceType;

					private java.util.Date updateTime ;

	public AnkInfo() {
	}

	public AnkInfo(String subBankName, String parentAreaCode, String areaCode, String unitedBankNo, String clearBankNo, String areaName) {
		this.subBankName = subBankName;
		this.parentAreaCode = parentAreaCode;
		this.areaCode = areaCode;
		this.unitedBankNo = unitedBankNo;
		this.clearBankNo = clearBankNo;
		this.areaName = areaName;
	}

	public AnkInfo(String bankCode, String bankName, String subBankCode, String subBankName, String parentAreaCode, String areaCode, String unitedBankNo, String clearBankNo, String status, String areaName, String sourceType, Date updateTime) {
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.subBankCode = subBankCode;
		this.subBankName = subBankName;
		this.parentAreaCode = parentAreaCode;
		this.areaCode = areaCode;
		this.unitedBankNo = unitedBankNo;
		this.clearBankNo = clearBankNo;
		this.status = status;
		this.areaName = areaName;
		this.sourceType = sourceType;
		this.updateTime = updateTime;
	}

	@Id
	     @GeneratedValue(strategy=GenerationType.IDENTITY)
	     @Column(name = "id"  )
     	     	// "主键"
		   public Integer  getId  () {
		   						    return this.id;
							 }
	 
        	     		@Column(name = "bank_code" )
    	         public String  getBankCode  () {
				return bankCode;
			 }
	    	     		@Column(name = "bank_name" )
    	         public String  getBankName  () {
				return bankName;
			 }
	    	     		@Column(name = "sub_bank_code" )
    	         public String  getSubBankCode  () {
				return subBankCode;
			 }
	    	     		@Column(name = "sub_bank_name" )
    	         public String  getSubBankName  () {
				return subBankName;
			 }
	    	     		@Column(name = "parent_area_code" )
    	         public String  getParentAreaCode  () {
				return parentAreaCode;
			 }
	    	     		@Column(name = "area_code" )
    	         public String  getAreaCode  () {
				return areaCode;
			 }
	    	     		@Column(name = "united_bank_no" )
    	         public String  getUnitedBankNo  () {
				return unitedBankNo;
			 }
	    	     		@Column(name = "clear_bank_no" )
    	         public String  getClearBankNo  () {
				return clearBankNo;
			 }
	    	     		@Column(name = "status" )
    	         public String  getStatus  () {
				return status;
			 }
	@Column(name = "area_name" )
	public String getAreaName() {
		return areaName;
	}
	@Column(name = "source_type" )
	public String getSourceType() {
		return sourceType;
	}
	/** 更新时间 */
	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name = "update_time" ,nullable = false)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setId (Integer  id) {
		 						    this.id = id;
				
		 }
	 
            public void setBankCode (String  bankCode) {
			this.bankCode = bankCode;
		 }
	        public void setBankName (String  bankName) {
			this.bankName = bankName;
		 }
	        public void setSubBankCode (String  subBankCode) {
			this.subBankCode = subBankCode;
		 }
	        public void setSubBankName (String  subBankName) {
			this.subBankName = subBankName;
		 }
	        public void setParentAreaCode (String  parentAreaCode) {
			this.parentAreaCode = parentAreaCode;
		 }
	        public void setAreaCode (String  areaCode) {
			this.areaCode = areaCode;
		 }
	        public void setUnitedBankNo (String  unitedBankNo) {
			this.unitedBankNo = unitedBankNo;
		 }
	        public void setClearBankNo (String  clearBankNo) {
			this.clearBankNo = clearBankNo;
		 }
	        public void setStatus (String  status) {
			this.status = status;
		 }

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}