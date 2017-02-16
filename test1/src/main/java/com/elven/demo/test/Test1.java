package com.elven.demo.test;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by qiusheng.wu on 2016/12/28.
 */

public class Test1 {
    public static void main(String[] args) {
//        System.out.println("Hello world!!!");
////        randomTest();
//        String[] arr = stringTokenizer("|", ";");
//        for (String s:arr
//             ) {
//
//        System.out.println(s);
//        }
//        StringTest();

//        dateTest();
//        listTest();

        referenceTest();
    }

    static void referenceTest(){
        User user = new User("1", "name1", 1);
        Department department = new Department(user);
        System.out.println("::::::::::::::::::修改前:::::::::::::::::::");

        System.out.println(user);

        referenceTest1(department);
        System.out.println("::::::::::::::::::修改后:::::::::::::::::::");
        System.out.println(department.getUser());
    }

    static void referenceTest1(Department department){
        User user = department.getUser();
        user = new User();
        user.setAge(2);
    }

    static void listTest(){
        List<User> users = new ArrayList<User>();
        for(int i=0; i<10; i++){
            users.add(new User(""+i, "name"+i, i));
        }
        System.out.println("::::::::::::::::::修改前:::::::::::::::::::");
        for(User user : users){
            System.out.println(user);
        }

        for(User user : users){
            user.setAge(user.getAge() * 10);
        }

        System.out.println("::::::::::::::::::修改后:::::::::::::::::::");
        for(User user : users){
            System.out.println(user);
        }
    }


    public static void dateTest(){
        long time = 1482737399L;
        Date date = new Date(time);
        SimpleDateFormat ymdhms=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(ymdhms.format(date));
    }

    public static void StringTest(){
        String str = "JAVA";
        String str1 = "JAVA";
        System.out.println(str.codePointAt(0));
        System.out.println(str.codePointBefore(1));
        System.out.println(str.codePointCount(3,3));
        System.out.println(str.getBytes(Charset.forName("UTF-8")));
        System.out.println(str.equals(str1));
    }

    public static String[] stringTokenizer(String str, String tokener) {
        if (tokener.equals( "|") || tokener.equals("*") || tokener.equals("+") || tokener.equals( "."))
            tokener = "\\" + tokener;

        return str.split(tokener, -1);
    }

    /**
     *
     */
    static void mapTest(){
        Map<String, String> resultMap = new HashMap<String, String>();
        System.out.println(resultMap.isEmpty());
    }

    static void randomTest(){
        Random random = new Random();

        double result = random.nextDouble();
        System.out.println(result);
    }


}
