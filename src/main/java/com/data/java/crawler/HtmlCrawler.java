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
 * ʹ��jsoup api ����һ��ר�Ż�ȡ������վ��html�ļ����������棬������ȡ���ļ����浽�����ļ�
 * @author admin
 *
 */
public class HtmlCrawler {
	private static Logger LOGGER = LoggerFactory.getLogger(HtmlCrawler.class);
	private static Lock lock = new ReentrantLock();
    //��ȡ�����ݴ�ŵ���Ŀ¼��
    private static String savepath;
    //��ȡ�����
    private static Integer maxdepth;
    //����8���̵߳��̳߳�
    private static ExecutorService executorService;
    //�߳�����
    private static Integer threadNum;
 	//ץȡ����ҳ����ÿ������֮���ԣ����룬����ָ�
 	private static String urls;
 	
 	static {
 		savepath = PropertyUtil.getProperty("savepath", "E:/crawler_out/");
 		maxdepth = Integer.parseInt(PropertyUtil.getProperty("maxdepth", "3"));
 		threadNum = Integer.parseInt(PropertyUtil.getProperty("threadNum", "8"));
 		executorService = Executors.newFixedThreadPool(threadNum);
 		urls = PropertyUtil.getProperty("urls");
 	}

	//�ȴ���ȡ��url
	volatile static List<String> allwaiturl = new ArrayList<String>();
	//��ȡ����url
	volatile static Set<String> alloverurl = new HashSet<String>();
	//��¼����url����Ƚ�����ȡ�ж�
	volatile static Map<String,Integer> allurldepth = new HashMap<String,Integer>();
	//�����̼߳�����
	volatile static Integer count = threadNum;
	// ����reference�ṹ
	volatile static Map<String, String> referrers = new HashMap<String, String>();
	
	public static void main(String[] args) {
		
		//new CrawlerExecutor(lock, savepath, maxdepth, executorService, threadNum, urls).execute();
		

		
		//��ȡ����ַΪhttps://www.sina.com.cn
		//String strurl="https://www.sina.com.cn/";
		//��ȡ��ַ
		if(urls==null) {
			LOGGER.error("δ������Ч����ַ��");
			return;
		}
		
		//�ָ����ַ
		String[] urlArray = urls.split(";");
		
		//���������ַ�����ץȡ�б�����������������
		for(int i=0;i<urlArray.length;i++) {
			allwaiturl.add(urlArray[i]);
			allurldepth.put(urlArray[i],1);
		}
		
		//��ѭ����������������̣߳�ץȡhtml��ҳ�ļ�
		while(true) {
			if(count>0) {
				if(allwaiturl.size()>0) {
					//�Ӵ�ץȡ�б��л�ȡ���ӣ���ɾ��
					String url = allwaiturl.get(0);
					allwaiturl.remove(0);
					
					if(url!=null&&!alloverurl.contains(url)&&allurldepth.get(url)<=maxdepth) {
						count--;//�߳�����1
						executorService.execute(new CrawlerTask(url, savepath, allwaiturl, alloverurl,
								allurldepth, count, referrers,lock));
								
					}
				}
			}

			//ѭ���˳�����
			if(count==threadNum&&allwaiturl.size()==0) {
				System.out.println("��ҳ��ȡ��ɣ�����ȡ������"+alloverurl.size()+"\t\n");
				return;
			}
		}
		
	}
}
