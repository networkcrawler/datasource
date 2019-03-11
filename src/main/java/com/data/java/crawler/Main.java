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
 * 鍚姩绫�,绋嬪簭鐨勫叆鍙�
 * @author admin
 *
 */
public class Main {
	public static void main(String[] args) {
		try {
			//鍒涘缓scheduler
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			//瀹氫箟涓�涓猅rigger
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
					.startNow()
					.withSchedule(cronSchedule("0 40 23 ? * *"))//姣忓ぉ23:40鎵ц
					.build();
			//瀹氫箟涓�涓猨obdetail
			JobDetail job = newJob(EastMoneyBlogQuartz.class)
					.withIdentity("job1","group1"
					)
					.usingJobData("name","quartz")
					.build();
			
			//鍔犲叆杩欎釜璋冨害
			scheduler.scheduleJob(job,trigger);

			//鍚姩璋冨害鍣�
			scheduler.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

/**
 * 鏌ヨ鐑棬鍗氫富鍗氭枃宸ュ叿job
 */
class EastMoneyBlogQuartz implements Job{
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		EastMoneyBlogTask eastMoneyBlogTask = new EastMoneyBlogTask();
		eastMoneyBlogTask.done();
	}
}
