package site.elven.test.java.lang;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Locale;

import static site.elven.test.util.PrintUtil.*;

public class StringTest {

    @Test
    public void test1(){
        int[] codePoints = new int[]{1,2,3,4,5,6};
        int offset = 0;
        int count = 3;
        String str = new String(codePoints, offset, count);
        println(str);
    }

    @Test
    public void test2(){
        byte ascii[] = "hello world".getBytes();
        int hibyte = 2;
        int offset = 3;
        int count = 4;
        String str = new String(ascii, hibyte, offset, count);
        println(str);
    }

    @Test
    public void test3() throws UnsupportedEncodingException {
        byte bytes[] = "hello world".getBytes();
        int offset = 1;
        int length = 3;
        String charsetName = "gbk";
        String str = new String(bytes, offset, length, charsetName);
        println(str);
    }

    @Test
    public void test4() {
        String str = "hello world";
        println(""+str.charAt(6));
        println(""+str.codePointAt(3));
        println(""+str.codePointBefore(3));
        println(""+str.codePointCount(3, 8));
        println(""+str.offsetByCodePoints(3, 8));
    }

    @Test
    public void test5() {
        String str = "hello world";
        char dst[] = new char[2];
        str.getChars(1, 3, dst, 0);
        println(new String(dst));

        byte dst1[] = new byte[3];
        str.getBytes(1, 3, dst1, 1);
        println(new String(dst1));
    }

    @Test
    public void test6() {
        String str = "hello world";
        println(""+str.contentEquals("hdhd"));
        println(""+str.compareTo("hdhd"));
        println(""+"hdhd".compareTo(str));
        println(""+str.compareTo(str));
    }

    @Test
    public void test7() {
        String str = "hello world";
        println(""+str.startsWith("h"));
        println(""+str.startsWith("he", 2));
        println(""+str.startsWith("llo", 2));
        println(""+str.endsWith("llo"));
        println(""+str.endsWith("ld"));
    }

    @Test
    public void test8() {
        String str = "hello world";
        println(""+str.hashCode());
    }

    @Test
    public void test9() {
        String str = "hello world";
        println(""+str.indexOf(2));
        println(""+str.indexOf("l"));
        println(""+str.lastIndexOf(2));
        println(""+str.lastIndexOf("l"));
    }

    @Test
    public void test10() {
        String str = "hello world";
        println(""+str.substring(2));
        println(""+str.substring(2,4));
        println(""+str.subSequence(2,4));
    }

    @Test
    public void test11() {
        String str = "hello world";
        println(""+str.concat(" elven"));
    }

    @Test
    public void test12() {
        String str = "hello world";
        println(""+str.replace("l", "s"));
        println(""+str.replaceFirst("l", "s"));
        println(""+str.replaceAll("l", "s"));
        println(""+str.matches("ll"));
        println(""+str.contains("ll"));
        println(""+ Arrays.asList(str.split("ll")));
        println(""+ Arrays.asList(str.split("l", 2)));
        println(""+ Arrays.asList(str.split("l", 3)));
        println(String.join("|", "1", "2", "3"));
        println(str.toLowerCase());
        println(str.toLowerCase(Locale.CANADA));
        println(str.toUpperCase());
        println(str.toUpperCase(Locale.CANADA));
        println(str.trim());
        println(""+ Arrays.asList(str.toCharArray()));
        println(String.valueOf(1));
        Object obj = null;
        println(String.valueOf(obj));
        println(str.intern());
    }

}
