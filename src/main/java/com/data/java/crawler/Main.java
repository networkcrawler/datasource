package com.data.java.crawler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.data.java.crawler.task.EastMoneyBlogTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.java.crawler.dao.CapitalFlowsTodayDao;
import com.data.java.crawler.task.CapitalFlowsTodayTask;
import com.data.java.crawler.task.CrawlerTask;
import com.data.java.crawler.utils.PropertyUtil;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * 	爬虫启动入口
 * @author admin
 *
 */
public class Main {
	public static void main(String[] args) throws SchedulerException {
		Main mainScheduler = new Main();
        mainScheduler.schedulerJob();
	}
	
	//创建调度器
    public static Scheduler getScheduler() throws SchedulerException{
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }
    
    public static void schedulerJob() throws SchedulerException{
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
        //创建触发器 每3秒钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
                            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
                            .build();
        Scheduler scheduler = getScheduler();
        //将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);
        //调度器开始调度任务
        scheduler.start();
        
    }

}

/**
 *	任务定义
 */
class EastMoneyBlogQuartz implements Job{
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println(jobExecutionContext.getJobDetail());
		CapitalFlowsTodayTask c = new CapitalFlowsTodayTask();
		System.out.println("执行一次");
		c.done();
	}
}

class MyJob implements Job{

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(new Date()));
    }
}
