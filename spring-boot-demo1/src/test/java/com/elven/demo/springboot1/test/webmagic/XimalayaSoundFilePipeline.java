/**
 * msxf.com Inc.
 * Copyright (c) 2018-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.webmagic;

import com.elven.demo.springboot1.common.util.HttpClientTool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author qiusheng.wu
 * @Filename XimalayaSoundFilePipeline.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2018/4/13 12:27</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
public class XimalayaSoundFilePipeline implements Pipeline {

    Logger logger = LoggerFactory.getLogger(XimalayaSoundFilePipeline.class);

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public XimalayaSoundFilePipeline() {
    }

    public XimalayaSoundFilePipeline(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {

        try {
            Map<String, Object> map = resultItems.get("soundObjMap");
            if(!CollectionUtils.isEmpty(map)){

                String id = String.valueOf(map.get("id"));
                String title = String.valueOf(map.get("title"));
                String album_id = String.valueOf(map.get("album_id"));
                String album_title = String.valueOf(map.get("album_title"));
                String nickname = String.valueOf(map.get("nickname"));
                String play_path = String.valueOf(map.get("play_path"));

                // 获取后缀名
                String suffix = FilenameUtils.getExtension(play_path);

                // 下载音频文件
                byte[] bytes = new HttpClientTool().getBytes(play_path);

                // 保存文件
                FileUtils.writeByteArrayToFile(new File(filePath+File.separator+nickname+File.separator+album_title+File.separator+title+"."+suffix), bytes);
            }
        } catch (IOException e) {
            logger.error("下载音频文件异常：{}", e.getMessage(), e);
        }
    }
}