/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.security.Security;

/**
 * @author qiusheng.wu
 * @Filename SocketProtocols.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/4/27 9:53</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class SocketProtocols {

    static {
        Security.setProperty("jdk.tls.disabledAlgorithms", "");
    }

    public static void main(String[] args) throws IOException {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket soc = (SSLSocket) factory.createSocket();

        // Returns the names of the protocol versions which are
        // currently enabled for use on this connection.
        String[] protocols = soc.getEnabledProtocols();

        System.out.println("Enabled protocols:");
        for (String s : protocols) {
            System.out.println(s);
        }
    }
}