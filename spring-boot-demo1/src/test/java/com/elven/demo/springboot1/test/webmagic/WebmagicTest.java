/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.webmagic;

import com.elven.demo.springboot1.common.util.HttpClientTool;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.example.BaiduBaikePageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

import java.io.File;
import java.io.IOException;

/**
 * @author qiusheng.wu
 * @Filename WebmagicTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/1/20 18:10</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class WebmagicTest {

    @Test
    public void test1(){
        Spider.create(new GithubRepoPageProcessor())
                //从https://github.com/code4craft开始抓
                .addUrl("https://github.com/code4craft")
                //设置Scheduler，使用Redis来管理URL队列
//                .setScheduler(new RedisScheduler("localhost"))
                //设置Pipeline，将结果以json方式保存到文件
                .addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
                //开启5个线程同时执行
                .thread(5)
                //启动爬虫
                .run();
    }

    @Test
    public void test2(){
        Spider.create(new BaiduBaikePageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://baike.baidu.com/item/%E8%B1%B9%E7%BA%B9%E9%B2%A8")
                .addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }

    @Test
    public void test3(){
        Spider spider = Spider.create(new BaiduBaikePageProcessor());
        ResultItems result = spider.get("http://baike.baidu.com/item/%E8%B1%B9%E7%BA%B9%E9%B2%A8");
        String name = result.get("name");
        System.out.println(name);
    }


    @Test
    public void test4() throws IOException {
        byte[] bytes = new HttpClientTool().getBytes("http://audio.xmcdn.com/group19/M08/E2/F6/wKgJK1fOO8bR5RS5ABRKJftFjRQ867.m4a");

        FileUtils.writeByteArrayToFile(new File("E:\\temp\\wKgJK1fOO8bR5RS5ABRKJftFjRQ867.m4a"), bytes);
    }
}