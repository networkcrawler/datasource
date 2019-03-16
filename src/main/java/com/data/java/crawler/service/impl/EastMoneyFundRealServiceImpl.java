package com.data.java.crawler.service.impl;

import java.util.LinkedList;
import java.util.List;

import com.data.java.crawler.service.EastMoneyFundRealService;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONObject;
import com.data.java.crawler.dao.EastMoneyFundRealDao;
import com.data.java.crawler.dao.impl.EastMoneyFundRealDaoImpl;
import com.data.java.crawler.dto.EastMoneyFundRealDTO;
import com.data.java.crawler.dto.EastMoneyFundRealPerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 	东方财富获取实时资金流向前100股
 * @author admin
 *
 */
@Service
public class EastMoneyFundRealServiceImpl implements EastMoneyFundRealService {
	private String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=ct&st=(BalFlowMain)&sr=-1&p=1&ps=50&js=var%20ectQJNnj={pages:(pc),date:%222014-10-22%22,data:[(x)]}&token=894050c76af8597a853f5b408b759f5d&cmd=C._AB&sty=DCFFITA&rt=51742742";
	@Autowired
	private EastMoneyFundRealDao eastMoneyFundRealDao;
	
	public void done() {
		Response resp = null;
		//发送请求
		try {
			resp = Jsoup.connect(url)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/72.0.3626.121 Chrome/72.0.3626.121 Safari/537.36")
                .referrer("http://data.eastmoney.com/zjlx/detail.html")
                .execute();
			//获取返回请求体，并解析
			String result = resp.body();
			String resultJsonStr = result.split("=")[1];
			EastMoneyFundRealDTO resultJson = JSONObject.parseObject(resultJsonStr, EastMoneyFundRealDTO.class);
			String[] datas = resultJson.getData();
			List<EastMoneyFundRealPerDTO> eastPersIn = new LinkedList<EastMoneyFundRealPerDTO>();
			List<EastMoneyFundRealPerDTO> eastPersUp = new LinkedList<EastMoneyFundRealPerDTO>();
			for(String data:datas) {
				EastMoneyFundRealPerDTO eastPer = new EastMoneyFundRealPerDTO();
				String[] menbers = data.split(",");
				eastPer.setSymbol(menbers[1]);
				eastPer.setName(menbers[2]);
				eastPer.setLastTrade(Double.parseDouble(menbers[3]));
				eastPer.setChg(Double.parseDouble(menbers[4]));
				eastPer.setInflowMain(Double.parseDouble(menbers[5]));
				eastPer.setInflowMainPer(Double.parseDouble(menbers[6]));
				eastPer.setSuperInflowMain(Double.parseDouble(menbers[7]));
				eastPer.setSuperInflowMainPer(Double.parseDouble(menbers[8]));
				eastPer.setLargeInflowMain(Double.parseDouble(menbers[9]));
				eastPer.setLargeInflowMainPer(Double.parseDouble(menbers[10]));
				eastPer.setMidInflowMain(Double.parseDouble(menbers[11]));
				eastPer.setMidInflowMainPer(Double.parseDouble(menbers[12]));
				eastPer.setSmallInflowMain(Double.parseDouble(menbers[13]));
				eastPer.setSmallInflowMainPer(Double.parseDouble(menbers[14]));
				List<EastMoneyFundRealPerDTO> ones = eastMoneyFundRealDao.findOneBySymbol(menbers[1]);
				if(ones.size()==0) {
					eastPersIn.add(eastPer);
				}else {
					eastPersUp.add(eastPer);
				}
			}
			
			if(eastPersIn.size()!=0)
				eastMoneyFundRealDao.insert(eastPersIn);
			
			if(eastPersUp.size()!=0)
				eastMoneyFundRealDao.update(eastPersUp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
