package com.wqs.quartz.listener;

import com.wqs.quartz.job.SimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * @author: wangqisong
 * @date: 2020/5/8
 * @Description: 监听应用启动，启动定时任务
 */
public class AppStartListener implements ApplicationListener<ContextStartedEvent> {

    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.printf("%s %n",">>>>>定时任务系统开始起动<<<<<");
        SimpleJob simpleJob =(SimpleJob) event.getApplicationContext().getBean("simpleJob");

        JobDetail jobDetail = JobBuilder.newJob().ofType(SimpleJob.class).build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .startNow().forJob(jobDetail)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().
                        withIntervalInSeconds(2))
                .build();

        try {
            Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
            defaultScheduler.start();
            defaultScheduler.scheduleJob(jobDetail, trigger) ;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        System.out.printf("%s %n",">>>>>定时任务系统起动成功<<<<<");
    }
}
