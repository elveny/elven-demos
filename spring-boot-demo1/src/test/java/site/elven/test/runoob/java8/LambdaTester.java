/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.runoob.java8;

/**
 * @author qiusheng.wu
 * @Filename LambdaTester.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/3/1 14:37</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class LambdaTester {
    final static String salutation = "Hello! ";

    public static void main(String[] args) {
        LambdaTester tester = new LambdaTester();

        int c = 0;

        // 此处不能修改c
//        c = 1;

        // 类型声明
        LambdaTester.MathOperation addition = (int a, int b) -> a + b + c;

        // 此处不能修改c
//        c = 1;

        // 不用类型声明
        LambdaTester.MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        LambdaTester.MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句
        LambdaTester.MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        LambdaTester.GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        LambdaTester.GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        // 用括号
        LambdaTester.GreetingService greetService3 = (message) ->
                System.out.println(salutation + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
        greetService3.sayMessage("Elven");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, LambdaTester.MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
}