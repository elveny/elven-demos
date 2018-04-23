/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.javacv;

import org.bytedeco.javacv.*;

/**
 * @author qiusheng.wu
 * @Filename AudioConvert.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/4/23 11:26</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class AudioConvert {

    /**
     * 通用音频格式参数转换
     *
     * @param inputFile
     *            -导入音频文件
     * @param outputFile
     *            -导出音频文件
     * @param audioCodec
     *            -音频编码
     * @param sampleRate
     *            -音频采样率
     * @param audioBitrate
     *            -音频比特率
     */
    public static void convert(String inputFile, String outputFile, int audioCodec, int sampleRate, int audioBitrate,
                               int audioChannels) {
        Frame audioSamples = null;
        // 音频录制（输出地址，音频通道）
        FFmpegFrameRecorder recorder = null;
        //抓取器
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);

        // 开启抓取器
        if (start(grabber)) {
            recorder = new FFmpegFrameRecorder(outputFile, audioChannels);
            // 音频编/解码器
            recorder.setAudioCodec(audioCodec);
            // 音频比特率
            recorder.setAudioBitrate(audioBitrate);
            // 通道（2表示：双通道(立体声)）
            recorder.setAudioChannels(audioChannels);
            // 音频采样率
            recorder.setSampleRate(sampleRate);
            // 最高质量
            recorder.setAudioQuality(0);
            // 不可变(固定)音频比特率
            recorder.setAudioOption("crf", "0");
            recorder.setAudioOption("aq", "10");
            // 开启录制器
            if (start(recorder)) {
                try {
                    // 抓取音频
                    while ((audioSamples = grabber.grab()) != null) {
                        recorder.setTimestamp(grabber.getTimestamp());
                        recorder.record(audioSamples);
                    }

                } catch (org.bytedeco.javacv.FrameGrabber.Exception e1) {
                    System.err.println("抓取失败");
                } catch (Exception e) {
                    System.err.println("录制失败");
                }
                stop(grabber);
                stop(recorder);
            }
        }

    }

    public static boolean start(FrameGrabber grabber) {
        try {
            grabber.start();
            return true;
        } catch (org.bytedeco.javacv.FrameGrabber.Exception e2) {
            try {
                System.err.println("首次打开抓取器失败，准备重启抓取器...");
                grabber.restart();
                return true;
            } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
                try {
                    System.err.println("重启抓取器失败，正在关闭抓取器...");
                    grabber.stop();
                } catch (org.bytedeco.javacv.FrameGrabber.Exception e1) {
                    System.err.println("停止抓取器失败！");
                }
            }

        }
        return false;
    }

    public static boolean start(FrameRecorder recorder) {
        try {
            recorder.start();
            return true;
        } catch (Exception e2) {
            try {
                System.err.println("首次打开录制器失败！准备重启录制器...");
                recorder.stop();
                recorder.start();
                return true;
            } catch (Exception e) {
                try {
                    System.err.println("重启录制器失败！正在停止录制器...");
                    recorder.stop();
                } catch (Exception e1) {
                    System.err.println("关闭录制器失败！");
                }
            }
        }
        return false;
    }

    public static boolean stop(FrameGrabber grabber) {
        try {
            grabber.flush();
            grabber.stop();
            return true;
        } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
            return false;
        } finally {
            try {
                grabber.stop();
            } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
                System.err.println("关闭抓取器失败");
            }
        }
    }

    public static boolean stop(FrameRecorder recorder) {
        try {
            recorder.stop();
            recorder.release();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                recorder.stop();
            } catch (Exception e) {

            }
        }
    }
}