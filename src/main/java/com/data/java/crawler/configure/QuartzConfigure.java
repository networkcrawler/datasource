package com.data.java.crawler.configure;

import com.data.java.crawler.service.quartz.SchedulerJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * quartz������
 */
@Configuration
public class QuartzConfigure {
    // ʹ��jobDetail��װjob
    /**@Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(SchedulerJob.class).withIdentity("myJob").storeDurably().build();
    }

    // ��jobDetailע�ᵽtrigger��ȥ
    @Bean
    public Trigger myJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1).repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }*/

    // ʹ��jobDetail��װjob
    @Bean
    public JobDetail myCronJobDetail() {
        return JobBuilder.newJob(SchedulerJob.class).withIdentity("SchedulerJob").storeDurably().build();
    }

    // ��jobDetailע�ᵽCron���ʽ��trigger��ȥ
    @Bean
    public Trigger CronJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(myCronJobDetail())
                .withIdentity("myCronJobTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
