package com.wqs.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author: wangqisong
 * @date: 2020/5/8
 * @Description:
 */
@Component
@Qualifier("simpleJob")
public class SimpleJob implements Job {
    
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.printf("%s %n",">>>>>simple任务被调度<<<<<");
        System.out.printf("%s %n",">>>>>simple任务被调度结束<<<<<");
    }
}
