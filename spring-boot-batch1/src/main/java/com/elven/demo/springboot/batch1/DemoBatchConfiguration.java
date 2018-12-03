/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot.batch1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author qiusheng.wu
 * @Filename BatchConfiguration.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/22 18:19</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
@EnableBatchProcessing
@Configuration
@Component
public class DemoBatchConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(DemoBatchConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step oneStep(){
        return stepBuilderFactory.get("OneStep").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                logger.info("执行OneStep");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step twoStep(){
        return stepBuilderFactory.get("TwoStep").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                logger.info("执行TwoStep");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    public Job buildJob(Step oneStep, Step twoStep){
        return jobBuilderFactory.get("DemoJob"+System.currentTimeMillis()).start(oneStep).next(twoStep).build();
    }

}