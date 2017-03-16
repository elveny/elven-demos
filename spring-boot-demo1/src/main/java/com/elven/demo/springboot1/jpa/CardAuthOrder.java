package com.elven.demo.springboot1.jpa;

import javax.persistence.*;

/**
* 银行卡鉴权订单表
* @desc	使用代码生成器生成.
* @date 	2017/03/15
*/
@Entity
@Table(name = "card_auth_order")
public class CardAuthOrder {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Long id;

    /** 支付鉴权订单号 */ 
    @Column(name = "pay_order_no" ,length = 32 ,nullable = false ,unique = true)
    private String payOrderNo ;

    /** 业务流水号: 外系统流水号 */ 
    @Column(name = "out_biz_no" ,length = 32 ,nullable = false)
    private String outBizNo ;

    /** 调用方系统 */ 
    @Column(name = "system_id" ,length = 20 ,nullable = false)
    private String systemId ;

    /** 鉴权类型，three-三要素鉴权、four-四要素鉴权，alipay-支付宝鉴权 */ 
    @Column(name = "auth_type" ,length = 8 ,nullable = false)
    private String authType ;

    /** 产品线 */ 
    @Column(name = "product_line" ,length = 8 ,nullable = false)
    private String productLine ;

    /** 产品码 */ 
    @Column(name = "product_code" ,length = 8 ,nullable = false)
    private String productCode ;

    /** 业务场景 */ 
    @Column(name = "scene_code" ,length = 8 ,nullable = false)
    private String sceneCode ;

    /** 开户机构编码：总行编码 */ 
    @Column(name = "open_inst_code" ,length = 12 ,nullable = false)
    private String openInstCode ;

    /** 开户机构名称 */ 
    @Column(name = "open_inst_name" ,length = 64)
    private String openInstName ;

    /** 账号 */ 
    @Column(name = "acct_no" ,length = 32 ,nullable = false)
    private String acctNo ;

    /** 账户名称 */ 
    @Column(name = "acct_name" ,length = 32 ,nullable = false)
    private String acctName ;

    /** 证件类型 */ 
    @Column(name = "cert_type" ,length = 2 ,nullable = false)
    private String certType ;

    /** 证件号码 */ 
    @Column(name = "cert_no" ,length = 32 ,nullable = false)
    private String certNo ;

    /** 银行预留手机号 */ 
    @Column(name = "bank_phone_no" ,length = 11)
    private String bankPhoneNo ;

    /** 卡借贷标志
            01：借记卡
            02：信用卡 */ 
    @Column(name = "dc_flag" ,length = 2)
    private String dcFlag ;

    /**  */ 
    @Column(name = "valid_date" ,length = 2)
    private String validDate ;

    /**  */ 
    @Column(name = "cvv2" ,length = 3)
    private String cvv2 ;

    /** 业务日期 */ 
    @Column(name = "biz_date" ,length = 8)
    private String bizDate ;

    /** 业务状态 */ 
    @Column(name = "biz_status" ,length = 10 ,nullable = false)
    private String bizStatus ;

    /** 短信发送方：
            paycore：支付核心发送短信
            paygw：支付网关（渠道）发送短信
            如果“不需要”支付核心发送短信，则调用支付网关“鉴权-发送短信”接口。 */ 
    @Column(name = "sms_sender" ,length = 10)
    private String smsSender ;

    /** 错误码 */ 
    @Column(name = "error_code" ,length = 32)
    private String errorCode ;

    /** 错误信息 */ 
    @Column(name = "error_msg" ,length = 256)
    private String errorMsg ;

    /** 备注 */ 
    @Column(name = "remark" ,length = 256)
    private String remark ;

    /** 创建时间 */ 
	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_time" ,nullable = false)
    private java.util.Date createTime ;

    /** 更新时间 */ 
	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "update_time" ,nullable = false)
    private java.util.Date updateTime ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** 支付鉴权订单号 */
    public void setPayOrderNo (String payOrderNo){
        this.payOrderNo = payOrderNo ;
    }

    /** 支付鉴权订单号 */
    public String getPayOrderNo (){
        return this.payOrderNo ;
    }

    /** 业务流水号: 外系统流水号 */
    public void setOutBizNo (String outBizNo){
        this.outBizNo = outBizNo ;
    }

    /** 业务流水号: 外系统流水号 */
    public String getOutBizNo (){
        return this.outBizNo ;
    }

    /** 调用方系统 */
    public void setSystemId (String systemId){
        this.systemId = systemId ;
    }

    /** 调用方系统 */
    public String getSystemId (){
        return this.systemId ;
    }

    /** 鉴权类型，three-三要素鉴权、four-四要素鉴权，alipay-支付宝鉴权 */
    public void setAuthType (String authType){
        this.authType = authType ;
    }

    /** 鉴权类型，three-三要素鉴权、four-四要素鉴权，alipay-支付宝鉴权 */
    public String getAuthType (){
        return this.authType ;
    }

    /** 产品线 */
    public void setProductLine (String productLine){
        this.productLine = productLine ;
    }

    /** 产品线 */
    public String getProductLine (){
        return this.productLine ;
    }

    /** 产品码 */
    public void setProductCode (String productCode){
        this.productCode = productCode ;
    }

    /** 产品码 */
    public String getProductCode (){
        return this.productCode ;
    }

    /** 业务场景 */
    public void setSceneCode (String sceneCode){
        this.sceneCode = sceneCode ;
    }

    /** 业务场景 */
    public String getSceneCode (){
        return this.sceneCode ;
    }

    /** 开户机构编码：总行编码 */
    public void setOpenInstCode (String openInstCode){
        this.openInstCode = openInstCode ;
    }

    /** 开户机构编码：总行编码 */
    public String getOpenInstCode (){
        return this.openInstCode ;
    }

    /** 开户机构名称 */
    public void setOpenInstName (String openInstName){
        this.openInstName = openInstName ;
    }

    /** 开户机构名称 */
    public String getOpenInstName (){
        return this.openInstName ;
    }

    /** 账号 */
    public void setAcctNo (String acctNo){
        this.acctNo = acctNo ;
    }

    /** 账号 */
    public String getAcctNo (){
        return this.acctNo ;
    }

    /** 账户名称 */
    public void setAcctName (String acctName){
        this.acctName = acctName ;
    }

    /** 账户名称 */
    public String getAcctName (){
        return this.acctName ;
    }

    /** 证件类型 */
    public void setCertType (String certType){
        this.certType = certType ;
    }

    /** 证件类型 */
    public String getCertType (){
        return this.certType ;
    }

    /** 证件号码 */
    public void setCertNo (String certNo){
        this.certNo = certNo ;
    }

    /** 证件号码 */
    public String getCertNo (){
        return this.certNo ;
    }

    /** 银行预留手机号 */
    public void setBankPhoneNo (String bankPhoneNo){
        this.bankPhoneNo = bankPhoneNo ;
    }

    /** 银行预留手机号 */
    public String getBankPhoneNo (){
        return this.bankPhoneNo ;
    }

    /** 卡借贷标志
            01：借记卡
            02：信用卡 */
    public void setDcFlag (String dcFlag){
        this.dcFlag = dcFlag ;
    }

    /** 卡借贷标志
            01：借记卡
            02：信用卡 */
    public String getDcFlag (){
        return this.dcFlag ;
    }

    /**  */
    public void setValidDate (String validDate){
        this.validDate = validDate ;
    }

    /**  */
    public String getValidDate (){
        return this.validDate ;
    }

    /**  */
    public void setCvv2 (String cvv2){
        this.cvv2 = cvv2 ;
    }

    /**  */
    public String getCvv2 (){
        return this.cvv2 ;
    }

    /** 业务日期 */
    public void setBizDate (String bizDate){
        this.bizDate = bizDate ;
    }

    /** 业务日期 */
    public String getBizDate (){
        return this.bizDate ;
    }

    /** 业务状态 */
    public void setBizStatus (String bizStatus){
        this.bizStatus = bizStatus ;
    }

    /** 业务状态 */
    public String getBizStatus (){
        return this.bizStatus ;
    }

    /** 短信发送方：
            paycore：支付核心发送短信
            paygw：支付网关（渠道）发送短信
            如果“不需要”支付核心发送短信，则调用支付网关“鉴权-发送短信”接口。 */
    public void setSmsSender (String smsSender){
        this.smsSender = smsSender ;
    }

    /** 短信发送方：
            paycore：支付核心发送短信
            paygw：支付网关（渠道）发送短信
            如果“不需要”支付核心发送短信，则调用支付网关“鉴权-发送短信”接口。 */
    public String getSmsSender (){
        return this.smsSender ;
    }

    /** 错误码 */
    public void setErrorCode (String errorCode){
        this.errorCode = errorCode ;
    }

    /** 错误码 */
    public String getErrorCode (){
        return this.errorCode ;
    }

    /** 错误信息 */
    public void setErrorMsg (String errorMsg){
        this.errorMsg = errorMsg ;
    }

    /** 错误信息 */
    public String getErrorMsg (){
        return this.errorMsg ;
    }

    /** 备注 */
    public void setRemark (String remark){
        this.remark = remark ;
    }

    /** 备注 */
    public String getRemark (){
        return this.remark ;
    }

    /** 创建时间 */
    public void setCreateTime (java.util.Date createTime){
        this.createTime = createTime ;
    }

    /** 创建时间 */
    public java.util.Date getCreateTime (){
        return this.createTime ;
    }

    /** 更新时间 */
    public void setUpdateTime (java.util.Date updateTime){
        this.updateTime = updateTime ;
    }

    /** 更新时间 */
    public java.util.Date getUpdateTime (){
        return this.updateTime ;
    }

}