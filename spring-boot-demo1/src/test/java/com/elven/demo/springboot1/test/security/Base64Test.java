/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.security;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.util.Base64Utils;

/**
 * @author qiusheng.wu
 * @Filename Base64Test.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/5/11 15:22</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class Base64Test {

    @Test
    public void encode(){

        String enStr = Base64Utils.encodeToString("M".getBytes());
        System.out.println(enStr);

        String deStr = new String (Base64Utils.decodeFromString(enStr));
        System.out.println(deStr);
    }

    @Test
    public void decode(){
        String enStr = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMPfYiYGSqD2olvLGaB9C7hiDlJiSNwC/vw4E34+M7XPBPcqjyvciiDcaLGU7R35/qH6Nzm1Rfz6lhEzG3InWRnMSDs/AISrIjEUtlvkyDDdnzf1ehov+NVpodGY+gnnGxw64i2fUGKsROL0HVvdTTMMi3J/aX/h4PH97H4+90+LAgMBAAECgYACOsa5PBUZY/VRKiyugP3jKWqUUX4W0wnoARNnunmINkUOMzPSAf6ohRnD+7f/QVs+qMR8Ka4URR30MCq9z+jZtzbVFpV3HcF7SbHhicQtfPWqqov0laxMWWdVlWfxJYJvwJ/6LJ7bqssPZXJ9+NceXL7F8SbobXFZvSfuhDCF4QJBAPFa+KNTQIhMuGzKffsbdjpYe9NttZmZ/Z1swfY4NQZf8Y36QpqVW51z8cxzWxyNnthufvpFJdCqD9nnvSCv4sMCQQDPwer1ZU4DDsO/iqvuk5c+rSBDdxJbCxp5RTGytJoKvXqcht8lUeu5dkgk9QMKf1Z7GfhZnJp2Lj3vSNAtBYOZAkEApA4FAed97ufPWEuPtJbnFyO8D2v8S4sro80gTn/IMywWIj6g9ThezLjZ+/HRVahB97Wr4+wKlzpxidmGaeiERwJBAKpc/Pvf55nQSKpP328S7gpCU1ufT5kCwOHC4N8HA+5ctCeY3XEv/RmnKb/MfoLkKpllkWaCaZMRlk4aqkLQ67kCQHbzf87PKaqKHCOoCM5SMXC3unI6Pp3Bv2MQTacFEPQ8l00dzkqUVTYP0estYNOR7PZNh0mn7p+HbyTDJKivKiU=";
        String deStr = new String (Base64Utils.decodeFromString(enStr));
        System.out.println(deStr);

        // !!! ERROR !!! 转成String之后就乱码了，再用Base64Utils.encodeToString就跟原来不一样了。
        enStr = Base64Utils.encodeToString(deStr.getBytes());
        System.out.println(enStr);
    }

    @Test
    public void sign(){
        byte[] keyBytes =  Base64.decodeBase64("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMPfYiYGSqD2olvLGaB9C7hiDlJiSNwC/vw4E34+M7XPBPcqjyvciiDcaLGU7R35/qH6Nzm1Rfz6lhEzG3InWRnMSDs/AISrIjEUtlvkyDDdnzf1ehov+NVpodGY+gnnGxw64i2fUGKsROL0HVvdTTMMi3J/aX/h4PH97H4+90+LAgMBAAECgYACOsa5PBUZY/VRKiyugP3jKWqUUX4W0wnoARNnunmINkUOMzPSAf6ohRnD+7f/QVs+qMR8Ka4URR30MCq9z+jZtzbVFpV3HcF7SbHhicQtfPWqqov0laxMWWdVlWfxJYJvwJ/6LJ7bqssPZXJ9+NceXL7F8SbobXFZvSfuhDCF4QJBAPFa+KNTQIhMuGzKffsbdjpYe9NttZmZ/Z1swfY4NQZf8Y36QpqVW51z8cxzWxyNnthufvpFJdCqD9nnvSCv4sMCQQDPwer1ZU4DDsO/iqvuk5c+rSBDdxJbCxp5RTGytJoKvXqcht8lUeu5dkgk9QMKf1Z7GfhZnJp2Lj3vSNAtBYOZAkEApA4FAed97ufPWEuPtJbnFyO8D2v8S4sro80gTn/IMywWIj6g9ThezLjZ+/HRVahB97Wr4+wKlzpxidmGaeiERwJBAKpc/Pvf55nQSKpP328S7gpCU1ufT5kCwOHC4N8HA+5ctCeY3XEv/RmnKb/MfoLkKpllkWaCaZMRlk4aqkLQ67kCQHbzf87PKaqKHCOoCM5SMXC3unI6Pp3Bv2MQTacFEPQ8l00dzkqUVTYP0estYNOR7PZNh0mn7p+HbyTDJKivKiU=".getBytes());
        System.out.println(new String(Base64.encodeBase64(keyBytes)));

        keyBytes =  Base64Utils.decodeFromString("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMPfYiYGSqD2olvLGaB9C7hiDlJiSNwC/vw4E34+M7XPBPcqjyvciiDcaLGU7R35/qH6Nzm1Rfz6lhEzG3InWRnMSDs/AISrIjEUtlvkyDDdnzf1ehov+NVpodGY+gnnGxw64i2fUGKsROL0HVvdTTMMi3J/aX/h4PH97H4+90+LAgMBAAECgYACOsa5PBUZY/VRKiyugP3jKWqUUX4W0wnoARNnunmINkUOMzPSAf6ohRnD+7f/QVs+qMR8Ka4URR30MCq9z+jZtzbVFpV3HcF7SbHhicQtfPWqqov0laxMWWdVlWfxJYJvwJ/6LJ7bqssPZXJ9+NceXL7F8SbobXFZvSfuhDCF4QJBAPFa+KNTQIhMuGzKffsbdjpYe9NttZmZ/Z1swfY4NQZf8Y36QpqVW51z8cxzWxyNnthufvpFJdCqD9nnvSCv4sMCQQDPwer1ZU4DDsO/iqvuk5c+rSBDdxJbCxp5RTGytJoKvXqcht8lUeu5dkgk9QMKf1Z7GfhZnJp2Lj3vSNAtBYOZAkEApA4FAed97ufPWEuPtJbnFyO8D2v8S4sro80gTn/IMywWIj6g9ThezLjZ+/HRVahB97Wr4+wKlzpxidmGaeiERwJBAKpc/Pvf55nQSKpP328S7gpCU1ufT5kCwOHC4N8HA+5ctCeY3XEv/RmnKb/MfoLkKpllkWaCaZMRlk4aqkLQ67kCQHbzf87PKaqKHCOoCM5SMXC3unI6Pp3Bv2MQTacFEPQ8l00dzkqUVTYP0estYNOR7PZNh0mn7p+HbyTDJKivKiU=");
        System.out.println(new String(Base64Utils.encode(keyBytes)));
    }

    @Test
    public void sign1(){
        byte[] keyBytes =  Base64.decodeBase64("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMPfYiYGSqD2olvLGaB9C7hiDlJiSNwC/vw4E34+M7XPBPcqjyvciiDcaLGU7R35/qH6Nzm1Rfz6lhEzG3InWRnMSDs/AISrIjEUtlvkyDDdnzf1ehov+NVpodGY+gnnGxw64i2fUGKsROL0HVvdTTMMi3J/aX/h4PH97H4+90+LAgMBAAECgYACOsa5PBUZY/VRKiyugP3jKWqUUX4W0wnoARNnunmINkUOMzPSAf6ohRnD+7f/QVs+qMR8Ka4URR30MCq9z+jZtzbVFpV3HcF7SbHhicQtfPWqqov0laxMWWdVlWfxJYJvwJ/6LJ7bqssPZXJ9+NceXL7F8SbobXFZvSfuhDCF4QJBAPFa+KNTQIhMuGzKffsbdjpYe9NttZmZ/Z1swfY4NQZf8Y36QpqVW51z8cxzWxyNnthufvpFJdCqD9nnvSCv4sMCQQDPwer1ZU4DDsO/iqvuk5c+rSBDdxJbCxp5RTGytJoKvXqcht8lUeu5dkgk9QMKf1Z7GfhZnJp2Lj3vSNAtBYOZAkEApA4FAed97ufPWEuPtJbnFyO8D2v8S4sro80gTn/IMywWIj6g9ThezLjZ+/HRVahB97Wr4+wKlzpxidmGaeiERwJBAKpc/Pvf55nQSKpP328S7gpCU1ufT5kCwOHC4N8HA+5ctCeY3XEv/RmnKb/MfoLkKpllkWaCaZMRlk4aqkLQ67kCQHbzf87PKaqKHCOoCM5SMXC3unI6Pp3Bv2MQTacFEPQ8l00dzkqUVTYP0estYNOR7PZNh0mn7p+HbyTDJKivKiU=".getBytes());
        System.out.println(new String(Base64Utils.encode(keyBytes)));
    }
}