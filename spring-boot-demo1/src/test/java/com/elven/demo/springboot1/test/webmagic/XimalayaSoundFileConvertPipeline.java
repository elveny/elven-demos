package com.elven.demo.springboot1.test.webmagic;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;

public class XimalayaSoundFileConvertPipeline implements Pipeline {

    Logger logger = LoggerFactory.getLogger(XimalayaSoundFileConvertPipeline.class);

    private final static String DEFAULT_CONVERT_DIR = "mp3";

    private String convertDir;

    public XimalayaSoundFileConvertPipeline() {
    }

    public XimalayaSoundFileConvertPipeline(String convertDir) {
        this.convertDir = convertDir;
    }

    public String getConvertDir() {
        return convertDir;
    }

    public void setConvertDir(String convertDir) {
        this.convertDir = convertDir;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {

        File source = resultItems.get("soundFileOfM4a");
        // 文件为空则不处理
        if(source == null){
            return;
        }

        try {
            convertDir = StringUtils.isEmpty(convertDir) ? DEFAULT_CONVERT_DIR : convertDir;

            String targetFileName = source.getName().substring(0, source.getName().lastIndexOf("."))+".mp3";

            File target = new File(source.getParent() + File.separator + convertDir + File.separator + targetFileName);
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
        } catch (Exception e){
            logger.error("音频{}格式转换异常:{}", source.getPath(), e.getMessage(), e);
        }
    }
}
