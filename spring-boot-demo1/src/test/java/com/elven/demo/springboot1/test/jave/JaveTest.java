/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.jave;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

/**
 * http://www.sauronsoftware.it/projects/jave/manual.php
 * @author qiusheng.wu
 * @Filename JaveTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/4/20 16:54</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class JaveTest {

    @Test
    public void supportedList() throws EncoderException {
        Encoder encoder = new Encoder();

        System.out.println(Arrays.asList(encoder.getSupportedEncodingFormats()));
        System.out.println(Arrays.asList(encoder.getSupportedDecodingFormats()));
        System.out.println(Arrays.asList(encoder.getAudioEncoders()));
        System.out.println(Arrays.asList(encoder.getAudioDecoders()));
        System.out.println(Arrays.asList(encoder.getVideoEncoders()));
        System.out.println(Arrays.asList(encoder.getVideoDecoders()));
        System.out.println(encoder.getInfo(new File("E:\\temp\\20180415\\夏朗大梦想\\小宝贝大梦想：亲子互动讲堂\\7508870_46432383_【梦想故事汇】小雨滴-童话剧《国王爸爸》_jave.mp3")));
        System.out.println(encoder.getInfo(new File("E:\\temp\\20180415\\夏朗大梦想\\小宝贝大梦想：亲子互动讲堂\\7508870_46432383_【梦想故事汇】小雨滴-童话剧《国王爸爸》_javacv.mp3")));
    }

    @Test
    public void encodeTest() throws EncoderException {

        Encoder encoder = new Encoder();
        AudioAttributes audioAttributes = new AudioAttributes();
        audioAttributes.setCodec("mp3");
        audioAttributes.setBitRate(new Integer(64000));

        EncodingAttributes encodingAttributes = new EncodingAttributes();
        encodingAttributes.setAudioAttributes(audioAttributes);

        encoder.encode(
                new File("E:\\temp\\20180415\\夏朗大梦想\\小宝贝大梦想：亲子互动讲堂\\7508870_46432383_【梦想故事汇】小雨滴-童话剧《国王爸爸》.m4a"),
                new File("E:\\temp\\20180415\\夏朗大梦想\\小宝贝大梦想：亲子互动讲堂\\7508870_46432383_【梦想故事汇】小雨滴-童话剧《国王爸爸》.mp3"),
                encodingAttributes);
    }

    @Test
    public void test1() throws EncoderException {
        File source = new File("E:\\temp\\error\\6436989_52346986_宫西达也——《千万别放弃》下集\u200B.m4a");
        File target = new File("E:\\temp\\error\\target_jave.mp3");
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(64000));
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(44100));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(source, target, attrs);
    }

    @Test
    public void test2() throws EncoderException {
        File source = new File("E:\\temp\\20180421\\5182923_20942586_001.什么时候出现了恐龙.m4a");

        System.out.println(source.getParent());
        System.out.println(source.getPath());
        System.out.println(source.getName());

        String targetFileName = source.getName().substring(0, source.getName().lastIndexOf("."))+".mp3";
        System.out.println(targetFileName);
    }

    @Test
    public void test3() throws EncoderException {
        long start = System.nanoTime();
        File source = new File("E:\\temp\\20180415\\夏朗大梦想\\小宝贝大梦想：亲子互动讲堂\\7508870_46432383_【梦想故事汇】小雨滴-童话剧《国王爸爸》.m4a");
        File target = new File("E:\\temp\\20180415\\夏朗大梦想\\小宝贝大梦想：亲子互动讲堂\\7508870_46432383_【梦想故事汇】小雨滴-童话剧《国王爸爸》_jave.mp3");
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(64000));
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(44100));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(source, target, attrs);
        long end = System.nanoTime();
        System.out.println("花费时长："+(end - start));
    }


}