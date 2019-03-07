package com.data.java.crawler;

import java.io.InputStream;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.java.crawler.task.CrawlerTask;
import com.data.java.crawler.utils.PropertyUtil;

/**
 * 使用jsoup api 制作一个专门获取新闻网站的html文件的网络爬虫，并将获取的文件保存到本地文件
 * @author admin
 *
 */
public class HtmlCrawler {
	private static Logger LOGGER = LoggerFactory.getLogger(HtmlCrawler.class);
	private static Lock lock = new ReentrantLock();
    //提取的数据存放到该目录下
    private static String savepath;
    //爬取得深度
    private static Integer maxdepth;
    //创建8个线程的线程池
    private static ExecutorService executorService;
    //线程数量
    private static Integer threadNum;
 	//抓取的网页链接每个连接之间以；隔离，方便分割
 	private static String urls;
 	
 	static {
 		savepath = PropertyUtil.getProperty("savepath", "E:/crawler_out/");
 		maxdepth = Integer.parseInt(PropertyUtil.getProperty("maxdepth", "3"));
 		threadNum = Integer.parseInt(PropertyUtil.getProperty("threadNum", "8"));
 		executorService = Executors.newFixedThreadPool(threadNum);
 		urls = PropertyUtil.getProperty("urls");
 	}
	
	public static void main(String[] args) {
		
		//new CrawlerExecutor(lock, savepath, maxdepth, executorService, threadNum, urls).execute();
		
	    //等待爬取的url
	    List<String> allwaiturl = new ArrayList<>();
	    //爬取过的url
	    Set<String> alloverurl = new HashSet<>();
	    //记录所有url的深度进行爬取判断
	    Map<String,Integer> allurldepth = new HashMap<>();
	    //空闲线程计数器
	    Integer count = threadNum;
	    // 定义reference结构
	 	Map<String, String> referrers = new HashMap<String, String>();
		
		//爬取的网址为https://www.sina.com.cn
		//String strurl="https://www.sina.com.cn/";
		//获取网址
		if(urls==null) {
			LOGGER.error("未输入有效的网址！");
			return;
		}
		
		//分割单个网址
		String[] urlArray = urls.split(";");
		
		//将输入的网址放入待抓取列表，并更新深度链接深度
		for(int i=0;i<urlArray.length;i++) {
			allwaiturl.add(urlArray[i]);
			allurldepth.put(urlArray[i],1);
		}
		
		//死循环分配任务给空闲线程，抓取html网页文件
		while(true) {
			if(count>0) {
				if(allwaiturl.size()>0) {
					//从待抓取列表中获取链接，并删除
					String url = allwaiturl.get(0);
					allwaiturl.remove(0);
					
					if(url!=null&&!alloverurl.contains(url)&&allurldepth.get(url)<=maxdepth) {
						count--;//线程数减1
						executorService.execute(new CrawlerTask(url, savepath, allwaiturl, alloverurl,
								allurldepth, count, referrers,lock));
								
					}
				}
			}

			//循环退出条件
			if(count==threadNum&&allwaiturl.size()==0) {
				System.out.println("网页爬取完成，已爬取数量："+alloverurl.size()+"\t\n");
				return;
			}
		}
		
	}
}
