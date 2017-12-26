/**
 * msxf.com Inc.
 * Copyright (c) 2016-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security.paygw.jd;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qigang.chen
 * @Filename CertificateType.java
 * @description  证书类型
 * @Version 1.0
 * @History <br/>
 * <li>Author: qigang.chen</li>
 * <li>Date: 2016/12/27 18:36</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public enum CertificateType {

    /**
     * 文本
     */
    TEXT("01", "TEXT"),

    /**
     * 证书文件 不含私钥
     */
    DER("02", "CER/CRT"),

    /**
     * JKS秘钥库文件  .keystore
     */
    JKS("03", "JKS"),

    /**
     * PKCS12密钥库文件 .pfx .p12
     */
    PKCS12("04" , "PKCS12")
    ;


    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构造一个<code>NoteEnum</code>枚举对象
     *
     * @param code
     * @param message
     */
    private CertificateType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return Returns the code.
     */
    public String code() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String message() {
        return message;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return NoteEnum
     */
    public static CertificateType getByCode(String code) {
        for (CertificateType _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<NoteEnum>
     */
    public List<CertificateType> getAllEnum() {
        List<CertificateType> list = new ArrayList<CertificateType>();
        for (CertificateType _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     *
     * @return List<String>
     */
    public List<String> getAllEnumCode() {
        List<String> list = new ArrayList<String>();
        for (CertificateType _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

}