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
        String enStr = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxDSElOQUJBTks+DQogICAgPFZFUlNJT04+MS4xLjA8L1ZFUlNJT04+DQogICAgPE1FUkNIQU5UPjIyOTA3ODU2PC9NRVJDSEFOVD4NCiAgICA8VEVSTUlOQUw+MDAwMDAwMDE8L1RFUk1JTkFMPg0KICAgIDxEQVRBPlU2dXVJM1BLZmhQdmgwbmxWbDF3YUxYSVhmMUwrYldUY3hiOWc2UUNWTXVFTlhOSFp6ZXBtOWp3ZHZ1ejh5Tmp5YWNnaERrcUVkc0NsMlVIVFM1VzEyNCs1eERQWEpDWHNjcUNUK3hpeW4yUjEwaUpZNk5nT0syNFhi\n" +
                "VXRVMk5LVmV5Vm00OGhjYXgzWktKTXJjSWZjdWt0cmpjWEVXZ0NxeVVwSFFQTFhjYWdOalVTVDdo\n" +
                "Z0s1RFlLWDNRVktxSjwvREFUQT4NCiAgICA8U0lHTj44MTc1MmUzMWQ4YmI0ZDJjNGQyMTM1NGJj\n" +
                "NmE2ODBmYzwvU0lHTj4NCjwvQ0hJTkFCQU5LPg==";
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

    @Test
    public void test1(){
        System.out.println(Base64Utils.encodeToString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes()));
    }

    @Test
    public void test2(){
        System.out.println(new String(Base64Utils.decodeFromString("Cjw/eG1sIHZlcnNpb249IjEuMCIgZW5jb2Rpbmc9IlVURi04Ij8+CjxDSElOQUJBTks+CiAgICA8VkVSU0lPTj4xLjAuMDwvVkVSU0lPTj4KICAgIDxNRVJDSEFOVD4xMTAyMTAwMDcwMDE8L01FUkNIQU5UPgogICAgPFRFUk1JTkFMPjAwMDAwMDAxPC9URVJNSU5BTD4KICAgIDxEQVRBPk5JV0h3SlZtelNCS1BTM25BNURGWlc0d1oxSUZiSS8rRlZHajN0WTV4UTNxNS9VQUM5RXdHVVFBcG8yUnQ1dnNsbkNEdFkvSDNsay9lOWFZaS9mMnZNd3ZCTnV0L3haVUJ2UFBycHZtMkZyeUhJSmpmYTF1c1NWU25PUENQUXhlNlAzVEh6ZGlQSWdHdU5UNllhM2Y1Nko5YitjVHRXa2ZFdGc4alJGT1A3SUd1RnBGUDhxYmFRWTRRTHY4Y3RiOVYzSWI5YU04bFlOSlhPN2ZSYkRoTVF2NTlWNnBsdnNSeTh2cHQ2VXFCblhldVA4STNqSE5tVDk3MXBpTDkvYThmNzQ5RksxMmMxZ2taZnZZL2VLd3c5NStmbkV1WlZ5VEdqdzRCeVpnUDZXZ0ZlanNpNWh1dzJ4Mit2aGx0UldibHdjc0t1RVo3YVdyODVMc1VEYzlwRCtucTU2K3dOUTViTWdlRXF6OExkQkt4cDVCWC9tQk1WREFHSU9mLzNjL2lHQUczUUhxN0EwMEh5UUN3eGwxYm1ZTHJCUzAzWDY5MWsxMnVrL1NHNmhMb0FHTEVsVTNsRW1PU3AvQlhRQ0lRQ3k4RkUxRjR3UlhjaHYxb3p5VmcvVU1kM3hsVmpZMHVWd0tXbElvYlR5akdET2w3NFFiV2tuOUJBQm10SklyRWJBc21PeVhXWGtvQURadU1XVU9qR1B6Y1dvTlFtdERHanc0QnlaZ1A2Vkh1R2dvakJjWC91RENvVE9sbTY5Y0VGT0Q2cDc3cEx3a2ZBL2ViT1RqSzNLeG5WaHNTMk1jYlJvaERmcFlBTEhjQmFaWUVLNDIwNVJzOGlQMUxieEVQYTlTblFkUHNxdHJOR28xRGVIc00zMXpOaGpTVSs1bHJScTlNcEc5OGlSZWR6QndWbS8xQmovbjlhei9vREVuPC9EQVRBPgogICAgPFNJR04+NDljMmM1OGE1NDU4YjY4YTgxMTMxNjU0ZmI4MjM1NDk8L1NJR04+CjwvQ0hJTkFCQU5LPg==")));
    }
}