/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.runoob.java8;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * @author qiusheng.wu
 * @Filename Base64Tester.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/3/1 23:01</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class Base64Tester {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 使用基本编码
        String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
        System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);

        // 解码
        byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
        System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));

        // 使用URL编码
        base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
        System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);

        // 使用MIME编码
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            stringBuilder.append(UUID.randomUUID().toString());
        }
        System.out.println("UUID原始字符串："+stringBuilder.toString());

        byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
        String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
        System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
    }
}