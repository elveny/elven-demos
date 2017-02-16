package com.elven.demo.springboot1.test.grid.domain;


import javax.persistence.*;

/**
 * @author: qiusheng.wu
 * @version: 1.0
 * @since 2017-01-19
 */
@Entity
@Table(name = "base_dic_area")
public  class AseDicArea    {

     	     private Long  id ;
     
        	    	private String  areaCode ;
         	    	private String  areaName ;
         	    	private String  parentAreaCode ;
         	    	private java.util.Date  timeInst ;
         	    	private java.util.Date  timeUpd ;
     

	 	   	     @Id
	     @GeneratedValue(strategy= GenerationType.IDENTITY)
	     @Column(name = "id"  )
     	     	// ""
		   public Long  getId  () {
		   						    return this.id;
							 }
	 
        	     		@Column(name = "area_code" )
    	         public String  getAreaCode  () {
				return areaCode;
			 }
	    	     		@Column(name = "area_name" )
    	         public String  getAreaName  () {
				return areaName;
			 }
	    	     		@Column(name = "parent_area_code" )
    	         public String  getParentAreaCode  () {
				return parentAreaCode;
			 }
	    	     		@Column(name = "time_inst" )
    	         public java.util.Date  getTimeInst  () {
				return timeInst;
			 }
	    	     		@Column(name = "time_upd" )
    	         public java.util.Date  getTimeUpd  () {
				return timeUpd;
			 }
	
	 		 public void setId (Long  id) {
		 						    this.id = id;
				
		 }
	 
            public void setAreaCode (String  areaCode) {
			this.areaCode = areaCode;
		 }
	        public void setAreaName (String  areaName) {
			this.areaName = areaName;
		 }
	        public void setParentAreaCode (String  parentAreaCode) {
			this.parentAreaCode = parentAreaCode;
		 }
	        public void setTimeInst (java.util.Date  timeInst) {
			this.timeInst = timeInst;
		 }
	        public void setTimeUpd (java.util.Date  timeUpd) {
			this.timeUpd = timeUpd;
		 }
	
	
}