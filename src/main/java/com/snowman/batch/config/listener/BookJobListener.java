package com.snowman.batch.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Snowman2014
 * @Date 2020/11/6 15:50
 * @Version 1.0
 **/
@Component(value = "bookJobListener")
@Slf4j
public class BookJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("begin job....");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("end job....success");
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            log.info("end job....failed");
        }
    }
}
