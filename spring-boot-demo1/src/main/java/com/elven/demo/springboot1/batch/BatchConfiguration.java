/**
 * msxf.com Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot1.batch;

/**
 * @author qiusheng.wu
 * @Filename BatchConfiguration.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/1/17 16:59</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
//@Configuration
//@EnableBatchProcessing
//@EnableAutoConfiguration
public class BatchConfiguration {
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(new Tasklet() {
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
//                        return null;
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Job job(Step step1) throws Exception {
//        return jobBuilderFactory.get("job1")
//                .incrementer(new RunIdIncrementer())
//                .start(step1)
//                .build();
//    }
}