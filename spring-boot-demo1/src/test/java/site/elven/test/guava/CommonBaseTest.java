/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.guava;

import com.google.common.base.*;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename CommonBaseTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 16:44</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CommonBaseTest {

    @Test
    public void JoinerTest(){
        String result = Joiner.on("#").join("1", "2", " ", "3", "", "4");
        System.out.println(result);

        result = Joiner.on("#").skipNulls().join("1", "2", " ", null, "3", "", "4");
        System.out.println(result);

        Object[] parts = new Object[]{"1", "2"};
        StringBuilder stringBuilder = Joiner.on("#").appendTo(new StringBuilder("0"), parts);
        System.out.println(stringBuilder);

        Joiner.MapJoiner joiner = Joiner.on("&").withKeyValueSeparator("=");
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        result = joiner.join(map);
        System.out.println(result);
    }

    @Test
    public void ObjectsTest(){
        System.out.println(Objects.equal("1", "2"));
        System.out.println(Objects.equal("", ""));
        System.out.println(Objects.equal(null, null));
        System.out.println(Objects.hashCode("1"));
        System.out.println(java.util.Objects.hashCode("1"));
        System.out.println(java.util.Objects.hash("1"));
        System.out.println("1".hashCode());

    }

    @Test
    public void SplitterTest(){
        /*
         on():指定分隔符来分割字符串
         limit():当分割的子字符串达到了limit个时则停止分割
         fixedLength():根据长度来拆分字符串
         trimResults():去掉子串中的空格
         omitEmptyStrings():去掉空的子串
         withKeyValueSeparator():要分割的字符串中key和value间的分隔符,分割后的子串中key和value间的分隔符默认是=
        */

        System.out.println(Splitter.on("#").limit(3).split("1#22#333"));
        System.out.println(Splitter.on("#").limit(3).trimResults().split("1#22#333#4444"));
        System.out.println(Splitter.on("#").limit(3).trimResults().split("1#22#   333# 4444"));
        // fixedLength():根据长度来拆分字符串
        System.out.println(Splitter.fixedLength(3).split("1 2 3"));
        System.out.println(Splitter.on(" ").omitEmptyStrings().splitToList("1  2 3"));
        System.out.println(Splitter.on(",").omitEmptyStrings().split("1,,,,2,,,3"));
        System.out.println(Splitter.on(" ").trimResults().split("1 2 3"));
        System.out.println(Splitter.on(";").withKeyValueSeparator(":").split("a:1;b:2;c:3"));//{a=1, b=2, c=3}
    }

    @Test
    public void StringsTest(){
        System.out.println(Strings.nullToEmpty(""));
        System.out.println(Strings.nullToEmpty(null));
        System.out.println(Strings.nullToEmpty("123"));
        System.out.println(Strings.padEnd("123", 10, '0'));
        System.out.println(Strings.padStart("123", 10, '0'));
        System.out.println(Strings.commonPrefix("123", "124"));
        System.out.println(Strings.commonSuffix("123", "423"));
    }

    @Test
    public void Utf8Test() throws UnsupportedEncodingException {
        System.out.println(Utf8.encodedLength("123"));
        System.out.println(Utf8.encodedLength("hello"));
        System.out.println(Utf8.encodedLength("中文"));
        System.out.println(Utf8.isWellFormed("hello".getBytes()));
        System.out.println(Utf8.isWellFormed("hello".getBytes("utf-8")));
        System.out.println(Utf8.isWellFormed("hello".getBytes(Charsets.UTF_8)));
        System.out.println(Utf8.isWellFormed("hello".getBytes(Charsets.ISO_8859_1)));
        System.out.println(Utf8.isWellFormed("hello".getBytes(Charset.forName("gbk"))));
        System.out.println(Utf8.isWellFormed("中文".getBytes(Charset.forName("gbk"))));
    }
}