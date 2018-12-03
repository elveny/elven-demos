/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.runoob.java8;

/**
 * @author qiusheng.wu
 * @Filename InterfaceDefaultMethodTester.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/3/1 17:14</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class InterfaceDefaultMethodTester {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.print();
        Car1.blowHorn();
    }

}

interface Vehicle {
    default void print(){
        System.out.println("我是一辆车!");
    }

    // 静态方法
    static void blowHorn(){
        System.out.println("按喇叭!!!");
    }

}

interface FourWheeler {
    default void print(){
        System.out.println("我是一辆四轮车!");
    }

    // 静态方法
    static void blowHorn(){
        System.out.println("四轮车按喇叭!!!");
    }

}

class Car implements Vehicle, FourWheeler {
    // 这个方法必须实现，否则编译不通过，因为Vehicle, FourWheeler分别都有方法print。
    public void print(){
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        FourWheeler.blowHorn();
        System.out.println("我是一辆汽车!");
    }

}

class Car1 implements Vehicle, FourWheeler {
    public void print(){
        Vehicle.super.print();
    }

    static void blowHorn() {
        Vehicle.blowHorn();
        System.out.println("汽车按喇叭!");
    }
}

