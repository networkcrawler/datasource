package com.data.java.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.data.java.crawler.task.CrawlerTask;

public class CrawlerExecutor {
	private Lock lock = new ReentrantLock();
    //提取的数据存放到该目录下
    private String savepath;
    //爬取得深度
    private Integer maxdepth;
    //创建8个线程的线程池
    private ExecutorService executorService;
    //线程数量
    private Integer threadNum;
 	//抓取的网页链接每个连接之间以；隔离，方便分割
 	private String urls;
	
	//等待爬取的url
    private List<String> allwaiturl = new ArrayList<>();
    //爬取过的url
    private Set<String> alloverurl = new HashSet<>();
    //记录所有url的深度进行爬取判断
    private Map<String,Integer> allurldepth = new HashMap<>();
    //空闲线程计数器
    private Integer count;
    // 定义reference结构
    private Map<String, String> referrers = new HashMap<String, String>();
	public CrawlerExecutor(Lock lock, String savepath, Integer maxdepth, ExecutorService executorService,
			Integer threadNum, String urls) {
		super();
		this.lock = lock;
		this.savepath = savepath;
		this.maxdepth = maxdepth;
		this.executorService = executorService;
		this.threadNum = threadNum;
		this.urls = urls;
	}
	
 	public void execute() {
 		count = threadNum;
 		//爬取的网址为https://www.sina.com.cn
 		//String strurl="https://www.sina.com.cn/";
 		//获取网址
 		if(urls==null) {
 			System.out.println("未输入有效的网址！");
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
