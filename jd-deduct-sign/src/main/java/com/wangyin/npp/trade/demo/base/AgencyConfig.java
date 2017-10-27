package com.wangyin.npp.trade.demo.base;

/**
 * @author wangtiezhi
 * @since: 16-12-22 下午2:40
 * @version: 1.0.0
 */
public class AgencyConfig {
	/**
     * sha_Key
     */
    private String shaKey;
    /**
     * DES_密钥
     */
    private String desKey;
    /**
     * 密钥文件路径
     */
    private String keyStorePath;
	/**
	 * 商户私钥文件名称
	 */
    private String priKeyStoreName;
    /**
     * 网银公钥文件名称
     */
    private String pubKeyStoreName;
    /**
     * 密钥文件密码
     */
    private String keyStorePassWord;
    /**
     *  API 请求路径前缀
     */
    private String urlPrefix = "https://mapi.jdpay.com/npp10"; 
    /**
     * MD5 密钥
     */
    private String md5Key; 
    /**
     *  退款请求地址
     */
    private String refundUrl = "https://tmapi.jdpay.com/jd.htm"; 
    
    public String getKeyStorePassWord() {
		return keyStorePassWord;
	}
	public void setKeyStorePassWord(String keyStorePassWord) {
		this.keyStorePassWord = keyStorePassWord;
	}
	
    public String getShaKey() {
		return shaKey;
	}
	public void setShaKey(String shaKey) {
		this.shaKey = shaKey;
	}
	public void setDesKey(String desKey){
    	this.desKey = desKey;
    }
    public String getDesKey(){
    	return this.desKey;
    }
	public String getKeyStorePath() {
		return keyStorePath;
	}
	public void setKeyStorePath(String keyStorePath) {
		this.keyStorePath = keyStorePath;
	}
	public String getPriKeyStoreName() {
		return priKeyStoreName;
	}
	public void setPriKeyStoreName(String priKeyStoreName) {
		this.priKeyStoreName = priKeyStoreName;
	}
	public String getPubKeyStoreName() {
		return pubKeyStoreName;
	}
	public void setPubKeyStoreName(String pubKeyStoreName) {
		this.pubKeyStoreName = pubKeyStoreName;
	}
	public String getUrlPrefix() {
		return urlPrefix;
	}
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	public String getMd5Key() {
		return md5Key;
	}
	public void setMd5Key(String md5Key) {
		this.md5Key = md5Key;
	}
	public String getRefundUrl() {
		return refundUrl;
	}
	public void setRefundUrl(String refundUrl) {
		this.refundUrl = refundUrl;
	}
    
}

