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
    public void encodeTest() throws EncoderException {
        Encoder encoder = new Encoder();

        System.out.println(Arrays.asList(encoder.getSupportedEncodingFormats()));
        System.out.println(Arrays.asList(encoder.getSupportedDecodingFormats()));
        System.out.println(Arrays.asList(encoder.getAudioEncoders()));
        System.out.println(Arrays.asList(encoder.getAudioDecoders()));
        System.out.println(Arrays.asList(encoder.getVideoEncoders()));
        System.out.println(Arrays.asList(encoder.getVideoDecoders()));
        System.out.println(encoder.getInfo(new File("E:\\temp\\20180415\\夏朗大梦想\\小宝贝大梦想：亲子互动讲堂\\7508870_46432383_【梦想故事汇】小雨滴-童话剧《国王爸爸》.m4a")));

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
}