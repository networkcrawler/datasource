package com.data.java.crawler;

import java.util.ArrayList;
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

import com.data.java.crawler.task.CrawlerTask;
import com.data.java.crawler.utils.PropertyUtil;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * 启动类,程序的入口
 * @author admin
 *
 */
public class Main {
	public static void main(String[] args) {
		try {
			//创建scheduler
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			//定义一个Trigger
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
					.startNow()
					.withSchedule(cronSchedule("0 40 23 ? * *"))//每天23:40执行
					.build();
			//定义一个jobdetail
			JobDetail job = newJob(EastMoneyBlogQuartz.class)
					.withIdentity("job1","group1"
					)
					.usingJobData("name","quartz")
					.build();
			//加入这个调度
			scheduler.scheduleJob(job,trigger);

			//启动调度器
			scheduler.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

/**
 * 查询热门博主博文工具job
 */
class EastMoneyBlogQuartz implements Job{
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		EastMoneyBlogTask eastMoneyBlogTask = new EastMoneyBlogTask();
		eastMoneyBlogTask.done();
	}
}
