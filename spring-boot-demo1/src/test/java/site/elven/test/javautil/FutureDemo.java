/**
 * elven.site Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package site.elven.test.javautil;

import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.concurrent.*;

/**
 * @author qiusheng.wu
 * @Filename FutureDemo.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/2/1 22:42</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("=====================================顺序进行=============================================");
        sequential();
        System.out.println("=====================================future统筹方法=============================================");
        future();
        System.out.println("=====================================guava future统筹方法=============================================");
        guavaFuture();
    }

    public static void sequential() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        // 第一步 网购厨具
        OnlineShopping thread = new OnlineShopping();
        thread.start();
        thread.join();  // 保证厨具送到
        // 第二步 去超市购买食材
        Thread.sleep(2000);  // 模拟购买食材时间
        Shicai shicai = new Shicai();
        System.out.println("第二步：食材到位");
        // 第三步 用厨具烹饪食材
        System.out.println("第三步：开始展现厨艺");
        cook(thread.chuju, shicai);

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    // 网购厨具线程
    static class OnlineShopping extends Thread {

        private Chuju chuju;

        @Override
        public void run() {
            System.out.println("第一步：下单");
            System.out.println("第一步：等待送货");
            try {
                Thread.sleep(5000);  // 模拟送货时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一步：快递送到");
            chuju = new Chuju();
        }

    }

    public static void future() throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        // 第一步 网购厨具
        Callable<Chuju> onlineShopping = new Callable<Chuju>() {

            @Override
            public Chuju call() throws Exception {
                System.out.println("第一步：下单");
                System.out.println("第一步：等待送货");
                Thread.sleep(5000);  // 模拟送货时间
                System.out.println("第一步：快递送到");
                return new Chuju();
            }

        };
        FutureTask<Chuju> task = new FutureTask<Chuju>(onlineShopping);
        new Thread(task).start();
        // 第二步 去超市购买食材
        Thread.sleep(2000);  // 模拟购买食材时间
        Shicai shicai = new Shicai();
        System.out.println("第二步：食材到位");
        // 第三步 用厨具烹饪食材
        if (!task.isDone()) {  // 联系快递员，询问是否到货
            System.out.println("第三步：厨具还没到，心情好就等着（心情不好就调用cancel方法取消订单）");
        }
        // 厨具未到，会阻塞
        Chuju chuju = task.get();
        System.out.println("第三步：厨具到位，开始展现厨艺");
        cook(chuju, shicai);

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void guavaFuture() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        //将ExecutorService装饰成ListeningExecutorService
        ListeningExecutorService service= MoreExecutors.listeningDecorator(
                Executors.newCachedThreadPool());

        ListenableFuture<Chuju> future = service.submit(() -> {
            System.out.println("第一步：下单");
            System.out.println("第一步：等待送货");
            TimeUnit.SECONDS.sleep(5);  // 模拟送货时间
            System.out.println("第一步：快递送到");
            return new Chuju();
        });

        // 第二步 去超市购买食材
        TimeUnit.SECONDS.sleep(2);  // 模拟购买食材时间
        Shicai shicai = new Shicai();
        System.out.println("第二步：食材到位");

        Futures.addCallback(future, new FutureCallback<Chuju>() {
            @Override
            public void onSuccess(@Nullable Chuju chuju) {
                // 第三步 用厨具烹饪食材
                System.out.println("第三步：厨具到位，开始展现厨艺");
                cook(chuju, shicai);
                System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        },service);

    }

    //  用厨具烹饪食材
    static void cook(Chuju chuju, Shicai shicai) {}

    // 厨具类
    static class Chuju {}

    // 食材类
    static class Shicai {}
}