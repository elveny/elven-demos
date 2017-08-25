/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.common;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.DateUtil;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * @author qiusheng.wu
 * @Filename CommonTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/1/20 9:57</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class CommonTest {

    @Test
    public void test1(){
        String filePath = "sdfsfs.dfdf.ssss";
        String suffix = filePath.substring(filePath.lastIndexOf(".")+1);
        System.out.println(suffix);
    }

    @Test
    public void test2(){
        String string = "中国工商银行铜川分行印台支行";
        String suffix = string.substring(0, string.indexOf("行")+1);
        System.out.println(suffix);
    }

    @Test
    public void test3(){
        try{
            int r = 1 / 0;
        }
        catch (Exception e){
            System.out.println("catch");
            throw e;
        }
        finally {
            System.out.println("finally");
        }
    }

    @Test
    public void test4(){
        Assert.assertFalse(ObjectUtils.NULL == null);
        Assert.assertTrue(ObjectUtils.equals(ObjectUtils.NULL, null));
    }

    @Test
    public void test5(){


        long t1 = 1484103140L;
        Date d1 = new Date(t1);
        System.out.println(d1);
        System.out.println(DateUtil.formatAsDatetime(d1));

        DateTime dateTime = new DateTime(t1);
        System.out.println(dateTime.toString("yyyyMMddHHmmss.SSS"));

        long t2 = 1486433767L; // 2017.2.6
        Date d2 = new Date(t2);
        System.out.println(d2);

    }

    @Test
    public void test6(){

        System.out.println(new DateTime(1486433767L).toString("yyyy-MM-dd HH:mm:ss.SSS"));  // 2017.2.6
        System.out.println(new DateTime().getMillis()/1000);  // 当前
        System.out.println(new DateTime().toString("yyyy-MM-dd HH:mm:ss.SSS"));  // 当前
        System.out.println(new DateTime().toString("yyyyMMddHHmmssSSS"));  // 当前
    }

    @Test
    public void test7(){
        System.out.println(GregorianCalendar.getInstance().getTimeInMillis());
        System.out.println(new DateTime().getMillis());
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test8(){
        String str = String.format("1111: %s, %s, %d", "222", "333", 444);
        System.out.println(str);
    }


    @Test
    public void test9(){

        String filename = "20170413.txt";

        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);

        String extension = FilenameUtils.getExtension(filename);
        System.out.println(extension);

        String randomFilename = uuid.replace("-", "").concat(".").concat(extension);
        System.out.println(randomFilename);
    }


    @Test
    public void test10() throws IOException {
        long freeSpaceKb = FileSystemUtils.freeSpaceKb();
        System.out.println(freeSpaceKb/1024L/1024L);

        freeSpaceKb = FileSystemUtils.freeSpaceKb("D:\\");
        System.out.println(freeSpaceKb);

        freeSpaceKb = FileSystemUtils.freeSpaceKb("D:\\", 10*1000);
        System.out.println(freeSpaceKb);

    }

    @Test
    public void mapTest(){
        Map<String, Object> map = new HashMap<String, Object>();
    }

    @Test
    public void StringTest(){
        String str = "1234567890";

        System.out.println(str.substring(0,2));
    }

    @Test
    public void cutInstRespMsgTest(){
        String instRespMsg = "1234567890123456789012345678901234567890123456789012345678901234567890";

        System.out.println(cutInstRespMsg(instRespMsg));
        System.out.println(instRespMsg);
    }

    /**
     * 截取渠道返回描述
     * @Description 当instRespMsg长度大于64，则截取64个字
     * @Params instRespMsg 原字符串
     * @Return 截取后的字符串
     * @Exceptions
     */
    private String cutInstRespMsg(String instRespMsg){
        String cutMsg = instRespMsg;

        if(StringUtils.isNotBlank(instRespMsg) && StringUtils.length(instRespMsg) > 64){
            cutMsg = instRespMsg.substring(0, 64);
        }

        return cutMsg;
    }

    @Test
    public void test11(){
        String str1 = "MIIHUAIBAzCCBwoGCSqGSIb3DQEHAaCCBvsEggb3MIIG8zCCA0gGCSqGSIb3DQEHAaCCAzkEggM1MIIDMTCCAy0GCyqGSIb3DQEMCgECoIICsjCCAq4wKAYKKoZIhvcNAQwBAzAaBBTPd3QceU0/VUkq9ESUvAqVycNXzgICBAAEggKAyuhMDq64L/1M2PtG7NQe1jfY57eQHYLPTwb4vgWoTnTiKOr4FD8pBo6X3lGmAxki/qro3/Nd0Cy1fGAmzWjfKSP9MxPmpu2Gn8zrIeU6taWOyFhQke7v3jJbwyAP/rmH40jd0M6HprOoAfu012mHPA7Ghu6KGTNkiPu1KgyZ4gjcpVE+WUVW6OnsYIajSZlb2Db7v4X+aGi4F0XB62QBReirRj05Ffvzu5oep5wivVt1GQS/QEQFNb4d4qkrxVsQ55tbTi0MsA2tfEOqFeeRArlidrpdpVuCC8VLhtIgnqjIH2fN63GnRhl65ZC+u0ApXqFHpAc857eBrrkOiMmI7lXtUGZFOl+897xa92J0Y3NhP0AjS+UyZHpYEHDfH7AIvQYRC6Yl3lmeXN7rvI5MGlos46LA3yZMhCUGJ3h/P7oVSxATs8HXdqK+ebIu//mA6C6BtVTwgfS8obyyVLWvsikYt69kGGKjMNM7o2fyEZdRPkPnDDLq1/GOgf8UtX8a9KP3GhT16UkRvRlfhxeZNcTQxl9UDnzjKk8xeR0sy4Q06KXqBktQNOk680jpAPfZzorXGG2wSDOjoCwhZcyD5RfAvEv8B0TM/LII4n8ndCIMrzFmu2Ui0jlO2QnemLnXCHqA3cw+Eq8PDzxa0/v1XX0vv4t+HRZxyhdn7naZuydp+1UU8WYxlonupOAINUYGt/Mi6BAG4iuT8ZbUXvx1erunTJiqJztU2avmj0OH/I7HPMNfvjpzVtPMCrd5O55eX+0mATqpnhbRJG23HIfrFzcR4KG1kB6fYU3hMQC4ZItCgAwPfO+9jUnUszjJ9CycSvUk15lr7/8qwSSQ+zEebjFoMEMGCSqGSIb3DQEJFDE2HjQAYgBmAGsAZQB5AF8AMQAwADAAMAAwADAAMQA3ADgAQABAADEAMAAwADAAMAAwADkAMQA2MCEGCSqGSIb3DQEJFTEUBBJUaW1lIDE0MzE5MzgxMzI3MTgwggOjBgkqhkiG9w0BBwagggOUMIIDkAIBADCCA4kGCSqGSIb3DQEHATAoBgoqhkiG9w0BDAEGMBoEFEx7dWRIjbkVhgu/20nALEVUTb62AgIEAICCA1D4fw+I7LnOMdq4HApVLzGnR5WoyUXB0DuHoVddCHoIqYgpCioRCdu8gkamhYAItyeMApOxHQ77j1dSDLQafNBBRKe+Ba0HQto/IqQlnC8G0e8qDp13Pi1zI+I1gLE4tePEH92hsDAG62WuDrIzdJ+kiayQdGpcQv6fb8nxfKpGna3FO8zyfPPF25HQkxRtxbDWwi1Ji2gk0JyefZb/vKhIiLUqGTReHMyqfn9DbqXtmoQAjjQQ5hOXpy6IzixBqNqzd/oS/LHmd7NRSlGAEsxfmwOJw6bsv8OpR/kdssG8K5KwtGskvWMFFkH1h6qVCCKnIcjIstwy5LZRuanxQe0050MB5Q3xEDcYYQfBVIdIuaH+DUWtEp/7hk5fDFGoqhmgu0FJdU9IsaCr4rTxfqeVyo1aVnx/l7soE6NPVS9cgDmLCZEEDZiSFtokKcEig08uxVIb/z+8VfLcJ2jOAknXbuFHAk3+pLBh3M+vqhhoZKHQ+NUqPjdmT2JzH5Wmtj0rDVHDEtnUBy9fkXl3f8Rzpc+MZyNwY6YWfEHWm7eeIadHnPD+8flk7qHn/fOVzZ2mrLcFeZoEB3pvYxP4olV10D9boCm3ZPWQI06uwWgVbuDg3wedosv8T1RrnukthmL9RI8IQ3NKmGWeqK/+SBRMnbOw224yYa3hkrAhm4ObJEc4JhWdk8/vgLQbf2V7lt80swELamVGNdGLEWldW7HeyHUvmBzvQXFp0aYJY2/smv5nrne7Xd2NBZyLA6ScZmIRa504Ay3fpK3MC+/eIvQHsY0ZMJAiB7Z0hn3Gg6CRL1cX8y7nkKIw95U77IVvMQS97ZR4zC4/e3E2+020kwJA3Svs382UjxwVFGqjw2lVcf4v+/e9ZrJ2l0BiYB6xYmWjY4fDuEQWOqRATR8s5BY6Gg7DFT3dIpm2NyQR6K3nY2c967TNpVmlf8GytWEcw60JYdk4BsxrCkhtvQ4kiptsdGhb47JO6OzbI+sm4WOhcLm1Qt5BD/B10bq7crDUZNN6W1AnE18fmDb0RGwgDvmHFKniVsKf0ddeBgJZouE5Z3QTwSmF9lApmnYzJOpJ1Wjo5dX/2Dz+Tb18uAz2iDKhd+DZ5UgACh4wdfgdzFVfZzA9MCEwCQYFKw4DAhoFAAQURv3VXhd+IxWmWrtJqVpFlZ8pDTgEFOq+DKRyx+59qBIfpOjsH6iS78IvAgIEAA==";
        String str2 = "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";
        System.out.println(str1.equals(str2));
        System.out.println(cutInstRespMsg(str2).length());
    }

    @Test
    public void test12(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("weight", null);
        Integer nowWeight = Integer.parseInt(map.get("weight").toString());
        System.out.println(nowWeight);

    }

    @Test
    public void test13(){
        // 测试打字：替换掉idea的jre之后再没有搜狗输入法卡的问题了。
        int i = NumberUtils.toInt(null, 1);
        System.out.println(i);

        i = NumberUtils.toInt("1111.00", 1);
        System.out.println(i);

        i = NumberUtils.toInt("1111", 1);
        System.out.println(i);

        i = NumberUtils.toInt("SS.``", 1);
        System.out.println(i);


        i = NumberUtils.toInt("1111", 1);
        System.out.println(i);
    }


    @Test
    public void test14(){
        String source = "1111";
        String value = "11111,2222,3333";

        System.out.println(in(source, value));
        System.out.println(notIn(source, value));
    }

    @Test
    public void test15(){
        Object source = 1111;
        Object value = new Double("1111");

        System.out.println(equals(source, value));
        System.out.println(notEquals(source, value));
    }

    boolean in(Object sourceObj, Object valueObj) {
        if (sourceObj instanceof String) {
            if (valueObj instanceof String) {
                String[] values = ((String) valueObj).split(",");
                if (null != values) {
                    for (String str : values) {
                        if (StringUtils.equals(str, (String) sourceObj)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean notIn(Object sourceObj, Object valueObj) {
        return !in(sourceObj, valueObj);
//        if (sourceObj instanceof String) {
//            if (valueObj instanceof String) {
//                String[] values = ((String) valueObj).split(",");
//                if (null != values) {
//                    for (String str : values) {
//                        if (StringUtils.equals(str, (String) sourceObj)) {
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
//        return true;
    }

    boolean equals(Object sourceObj, Object valueObj) {
        if (sourceObj instanceof String) {
            if (valueObj instanceof String) {
                return StringUtils.equals((String) sourceObj, (String) valueObj);
            }
            return false;
        }
        if (sourceObj instanceof Boolean) {
            return ((Boolean) sourceObj).booleanValue() == Boolean.parseBoolean(valueObj
                    .toString());
        }
        Double source = NumberUtil.getDoubleFrom(sourceObj);
        Double value = NumberUtil.getDoubleFrom(valueObj);
        if (Double.isNaN(source) || Double.isNaN(value)) {
            return false;
        }
        return source.doubleValue() == value.doubleValue();
    }

    boolean notEquals(Object sourceObj, Object valueObj) {
        if (sourceObj instanceof String) {
            if (valueObj instanceof String) {
                return !StringUtils.equals((String) sourceObj, (String) valueObj);
            }
            return true;
        }
        if (sourceObj instanceof Boolean) {
            return ((Boolean) sourceObj).booleanValue() != Boolean.parseBoolean(valueObj
                    .toString());
        }
        Double source = NumberUtil.getDoubleFrom(sourceObj);
        Double value = NumberUtil.getDoubleFrom(valueObj);
        if (Double.isNaN(source) || Double.isNaN(value)) {
            return false;
        }
        return source.doubleValue() != value.doubleValue();
    }

    @Test
    public void test16(){
        char str = (char) (10 + 55);
        System.out.println(str);
    }

    @Test
    public void test17(){
        String batchDeductStr = "c7ad128411734978ad7e3cb19d12243a|20170818|alic|0|2088022370145622|张宝|01|320301198502169142|0.01|批扣测试|T110E5|重庆市|市辖区|0500|1f346tfd7694gdfgjoerg72384|ccs|0302|11223344000004|1|13906321789|5\n" +
                "c3b9118ba09a4c0aa9e591992d375e60|20170818|alic|0|2088022370145622|张宝|01|320301198502169142|0.01|批扣测试|T110E5|重庆市|市辖区|0500|1f346tfd7694gdfgjoerg72384|ccs|0302|11223344000004|1|13906321789|5";
        System.out.println(batchDeductStr.getBytes().length);

    }

}