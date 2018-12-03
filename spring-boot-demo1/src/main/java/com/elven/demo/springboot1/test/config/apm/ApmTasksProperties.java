/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.test.config.apm;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author qiusheng.wu
 * @Filename ApmTasksProperties.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/28 12:02</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
@ConfigurationProperties(prefix = "apm", locations = "classpath: config/apm.yml")
public class ApmTasksProperties {

    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public static class Task {
        // 任务名称
        private String name;
        // 是否仅支持单任务运行
        private boolean single;
        /**
         *   INIT("00", "初始"),
             PROCSSING("01", "处理中"),
             SUCCESS("02", "处理成功"),
             FILED("03", "处理失败"),
         */
        private String status;
        // 依赖任务
        private List<Task> dependencies;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSingle() {
            return single;
        }

        public void setSingle(boolean single) {
            this.single = single;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Task> getDependencies() {
            return dependencies;
        }

        public void setDependencies(List<Task> dependencies) {
            this.dependencies = dependencies;
        }
    }
}