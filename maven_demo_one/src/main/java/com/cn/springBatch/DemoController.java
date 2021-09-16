package com.cn.springBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jason on 2019/2/28.
 */
@RestController
@RequestMapping(value = "/springBatch")
public class DemoController {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job importJob;
    public JobParameters jobParameters;

    @GetMapping(value = "/impl")
    public String impl(String fileName) throws Exception {
        String path = fileName + ".csv";
        jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("input.file.name", path)
                .toJobParameters();
        jobLauncher.run(importJob,jobParameters);
        return "ok";
    }
}
