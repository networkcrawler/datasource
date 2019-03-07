package com.data.java.crawler.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.CORBA.portable.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

/**
 * 该类是
 * 
 * @author admin
 *
 */
public class CrawlerTask implements Runnable {
	private static Logger LOGGER = LoggerFactory.getLogger(CrawlerTask.class);

	// 爬取的url
	private String url;
	// 文件保存路径
	private String savepath;
	// 等待爬取的url
	private List<String> allwaiturl;
	// 爬取过的url
	private Set<String> alloverurl;
	// 记录所有url的深度进行爬取判断
	private Map<String, Integer> allurldepth;
	// 空闲线程数
	private Integer count;
	// 定义reference结构
	private Map<String, String> referrers;
	//锁
	private Lock lock;

	public CrawlerTask(String url, String savepath, List<String> allwaiturl, Set<String> alloverurl,
			Map<String, Integer> allurldepth, Integer count, Map<String, String> referrers,Lock lock) {
		super();
		this.url = url;
		this.savepath = savepath;
		this.allwaiturl = allwaiturl;
		this.alloverurl = alloverurl;
		this.allurldepth = allurldepth;
		this.count = count;
		this.referrers = referrers;
		this.lock = lock;
	}



	@Override
	public void run() {
		Document document = null;
		PrintWriter outputStream = null;
		System.out.println("当前执行："+Thread.currentThread().getName()+" 爬取线程处理爬取链接："+url+"\n\t");
		try {
			//获取上一级链接
			String referrer = referrers.get(url);
			//获取网页文档
			Response response = null;
			if(referrer!=null) {
				response = Jsoup.connect(url)
						.referrer(referrer)
						.header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
						.execute();
			}else {
				response = Jsoup.connect(url)
						.header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
						.execute();
			}
			
			//获取相应信息
			//String contentType = response.contentType();
			document = response.parse();
			Integer status = response.statusCode();

			if(status!=null&&status==200) {
				//获取网页文档中所有链接
				Elements links = document.select("a[href]");
				
				//定义获取新链接计数器
				try {
					lock.lock();
					for(Element link:links) {
						String nextUrl = link.attr("abs:href");
						
						//将获取的链接加入待爬取集合，并记录深度
						allwaiturl.add(nextUrl);
						referrers.put(nextUrl, url);
						allurldepth.put(nextUrl, allurldepth.get(url)==null?0:allurldepth.get(url)+1);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					lock.unlock();
				}
				
				//将文档保存起来
				File file = new File(savepath);
				if(!file.exists()) {
					file.mkdirs();
				}
				
				outputStream = new PrintWriter(file+File.separator+System.currentTimeMillis()+".txt");
				outputStream.println(response.body());
				
				try {
					lock.lock();
					//记录已爬取过的url
					alloverurl.add(url);
					//线程计数加1
					count++;
					System.out.println(url+"网页爬取完成......"+"\n\t");
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					lock.unlock();
				}
				System.out.println(count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(outputStream!=null) {
				try {
					outputStream.close();
				}catch (Exception e) {
					outputStream = null;
				}
			}
		}
	}
}
