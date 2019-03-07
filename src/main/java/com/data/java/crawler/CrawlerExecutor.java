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
    //��ȡ�����ݴ�ŵ���Ŀ¼��
    private String savepath;
    //��ȡ�����
    private Integer maxdepth;
    //����8���̵߳��̳߳�
    private ExecutorService executorService;
    //�߳�����
    private Integer threadNum;
 	//ץȡ����ҳ����ÿ������֮���ԣ����룬����ָ�
 	private String urls;
	
	//�ȴ���ȡ��url
    private List<String> allwaiturl = new ArrayList<>();
    //��ȡ����url
    private Set<String> alloverurl = new HashSet<>();
    //��¼����url����Ƚ�����ȡ�ж�
    private Map<String,Integer> allurldepth = new HashMap<>();
    //�����̼߳�����
    private Integer count;
    // ����reference�ṹ
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
 		//��ȡ����ַΪhttps://www.sina.com.cn
 		//String strurl="https://www.sina.com.cn/";
 		//��ȡ��ַ
 		if(urls==null) {
 			System.out.println("δ������Ч����ַ��");
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
