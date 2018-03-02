/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.runoob.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author qiusheng.wu
 * @Filename MethodReferenceTester.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/3/1 14:41</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class MethodReferenceTester {

    static class Car {
        //Supplier是jdk1.8的接口，这里和lamda一起使用了
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }

        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }
    }

    public static void main(String[] args) {
        // 构造器引用：它的语法是Class::new，或者更一般的Class<T>::new
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );

        // 静态方法引用：它的语法是Class::static_method
        cars.forEach( Car::collide );

        // 特定类的任意对象的方法引用：它的语法是Class::method
        cars.forEach( Car::repair );

        // 特定对象的方法引用：它的语法是instance::method
        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );

        // 将 System.out::println 方法作为静态方法来引用
        List<String> names = new ArrayList<>();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);

    }
}