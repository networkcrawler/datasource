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
 * ������
 * 
 * @author admin
 *
 */
public class CrawlerTask implements Runnable {
	private static Logger LOGGER = LoggerFactory.getLogger(CrawlerTask.class);

	// ��ȡ��url
	private String url;
	// �ļ�����·��
	private String savepath;
	// �ȴ���ȡ��url
	private List<String> allwaiturl;
	// ��ȡ����url
	private Set<String> alloverurl;
	// ��¼����url����Ƚ�����ȡ�ж�
	private Map<String, Integer> allurldepth;
	// �����߳���
	private Integer count;
	// ����reference�ṹ
	private Map<String, String> referrers;
	//��
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
		System.out.println("��ǰִ�У�"+Thread.currentThread().getName()+" ��ȡ�̴߳�����ȡ���ӣ�"+url+"\n\t");
		try {
			//��ȡ��һ������
			String referrer = referrers.get(url);
			//��ȡ��ҳ�ĵ�
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
			
			//��ȡ��Ӧ��Ϣ
			//String contentType = response.contentType();
			document = response.parse();
			Integer status = response.statusCode();

			if(status!=null&&status==200) {
				//��ȡ��ҳ�ĵ�����������
				Elements links = document.select("a[href]");
				
				//�����ȡ�����Ӽ�����
				try {
					lock.lock();
					for(Element link:links) {
						String nextUrl = link.attr("abs:href");
						
						//����ȡ�����Ӽ������ȡ���ϣ�����¼���
						allwaiturl.add(nextUrl);
						referrers.put(nextUrl, url);
						allurldepth.put(nextUrl, allurldepth.get(url)==null?0:allurldepth.get(url)+1);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					lock.unlock();
				}
				
				//���ĵ���������
				File file = new File(savepath);
				if(!file.exists()) {
					file.mkdirs();
				}
				
				outputStream = new PrintWriter(file+File.separator+System.currentTimeMillis()+".txt");
				outputStream.println(response.body());
				
				try {
					lock.lock();
					//��¼����ȡ����url
					alloverurl.add(url);
					//�̼߳�����1
					count++;
					System.out.println(url+"��ҳ��ȡ���......"+"\n\t");
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
