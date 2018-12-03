/**
 * elven.site Inc.
 * Copyright (c) 2017-2026 All Rights Reserved.
 */
package com.elven.demo.springboot.batch1.controller;

import com.elven.demo.springboot.batch1.DemoBatchConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiusheng.wu
 * @Filename JobTestController.java
 * @description
 * @Version 1.0
 * @History <li>Author: qiusheng.wu</li>
 * <li>Date: 2017/2/22 18:55</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 */
@RestController
@RequestMapping("/spirngbootbatch1/test/job")
public class JobTestController {

    private final static Logger logger = LoggerFactory.getLogger(JobTestController.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private DemoBatchConfiguration demoBatchConfiguration;

    @Autowired
    private Step oneStep;

    @Autowired
    private Step twoStep;

    @RequestMapping("hello")
    public String hello() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Job job = demoBatchConfiguration.buildJob(oneStep, twoStep);

        JobExecution jobExecution = jobLauncher.run(job, new JobParametersBuilder().toJobParameters());

        logger.info("jobExecution.status::::"+jobExecution.getStatus().toString());

        return "SUCCESS";
    }
}