package com.jd.jr.pay.demo.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CertUtil {
	private static Logger logger = Logger.getLogger(CertUtil.class);

	/**
	 * 效率：以文件名为Key缓存证书
	 */
	private static final ConcurrentMap<String, String> certs = new ConcurrentHashMap<String, String>();

	public static String getCert() {
		String certPath = PropertyUtils.getProperty("jdpay.cert.path");
		if (certPath == null || certPath.equals("")) {
			return null;
		}
		String cert = certs.get(certPath);

		if (cert == null || cert.equals("")) {
			byte[] strBuffer = null;
			int flen = 0;
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(certPath);
			if (is != null) {
				try {
					flen = (int) is.available();
					strBuffer = new byte[flen];
					is.read(strBuffer, 0, flen);
					cert = Base64.encode(strBuffer);
					certs.put(certPath, cert);
				} catch (FileNotFoundException e) {
					logger.error("cert not find,", e);
				} catch (IOException e) {
					logger.error("cert not find,", e);
				}
			}
		}
		return cert;
	}

	public static void main(String[] args) {
		System.out.println(certs.get(null));
	}
}
