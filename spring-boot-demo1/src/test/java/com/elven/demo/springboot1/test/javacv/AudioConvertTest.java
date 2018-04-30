/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.javacv;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import org.bytedeco.javacpp.avcodec;
import org.junit.Test;

import java.io.File;

/**
 * @author qiusheng.wu
 * @Filename AudioConvertTest.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/4/23 11:27</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class AudioConvertTest {

    @Test
    public void test1(){
        long start = System.nanoTime();
        AudioConvert.convert("E:\\temp\\20180415\\夏朗大梦想\\小宝贝大梦想：亲子互动讲堂\\7508870_46432383_【梦想故事汇】小雨滴-童话剧《国王爸爸》.m4a", "E:\\temp\\20180415\\夏朗大梦想\\小宝贝大梦想：亲子互动讲堂\\7508870_46432383_【梦想故事汇】小雨滴-童话剧《国王爸爸》_javacv.mp3", avcodec.AV_CODEC_ID_MP3, 8000, 64000, 2);
        long end = System.nanoTime();
        System.out.println("花费时长："+(end - start));
    }

    @Test
    public void test2(){
        AudioConvert.convert("E:\\temp\\error\\宫西达也——《千万别放弃》下集\u200B.m4a", "E:\\temp\\error\\target_javacv.mp3", avcodec.AV_CODEC_ID_MP3, 8000, 64000, 2);
    }

    public static void jave() throws EncoderException {
        long start = System.nanoTime();
        File source = new File("E:\\temp\\error\\6436989_52346986_宫西达也——《千万别放弃》下集.m4a");
        File target = new File("E:\\temp\\error\\6436989_52346986_宫西达也——《千万别放弃》下集_jave.mp3");
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

    public static void javacv8000(){
        long start = System.nanoTime();
        String inputFile = "E:\\temp\\error\\6436989_52346986_宫西达也——《千万别放弃》下集.m4a";
        String outputFile = "E:\\temp\\error\\6436989_52346986_宫西达也——《千万别放弃》下集_javacv_8000.mp3";
        AudioConvert.convert(inputFile, outputFile, avcodec.AV_CODEC_ID_MP3, 8000, 64000, 2);
        long end = System.nanoTime();
        System.out.println("8000花费时长："+(end - start));
    }

    public static void javacv44100(){
        long start = System.nanoTime();
        String inputFile = "E:\\temp\\error\\6436989_52346986_宫西达也——《千万别放弃》下集.m4a";
        String outputFile = "E:\\temp\\error\\6436989_52346986_宫西达也——《千万别放弃》下集_javacv_44100.mp3";
        AudioConvert.convert(inputFile, outputFile, avcodec.AV_CODEC_ID_MP3, 44100, 64000, 2);
        long end = System.nanoTime();
        System.out.println("44100花费时长："+(end - start));
    }

    public static void main(String[] args) throws EncoderException {

        jave();

//        javacv8000();

        javacv44100();

    }

}