package com.data.java.crawler.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.java.crawler.dao.StockPoolDao;
import com.data.java.crawler.dto.StockPoolDTO;
import com.data.java.crawler.service.StockPoolService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockPoolServiceImpl implements StockPoolService {
    private static Logger logger = LoggerFactory.getLogger(StockPoolService.class);

    @Autowired
    private StockPoolDao stockPoolDao;

    @Override
    public void getStockPoolFromSina(){
         for (int i=1;i<=91;i++){
             String url = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/" +
                     "Market_Center.getHQNodeData?page="+i+"&num=40&sort=changepercent&asc=0&node=hs_a&symbol=&_s_r_a=page";
             //获取文档
             Document document = null;
             try {
                 document = Jsoup.connect(url)
                         .ignoreContentType(true)
                         .ignoreHttpErrors(true)
                         .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
                         .get();
                 Random random = new Random(10000);
                 Integer time = (int)(4000+Math.random()*10) ;

                 Thread.sleep(time);
             }catch(Exception e) {
                 logger.error("获取新浪股票池出错",e);
             }
             String resultStr = document.body().html();
             JSONArray jsonArray = JSONObject.parseArray(resultStr);
             for (int j=0;j<jsonArray.size();j++){
                 JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                 String symbol = jsonObject.getString("symbol");
                 String code = jsonObject.getString("code");
                 String name = jsonObject.getString("name");
                 try {
                     StockPoolDTO tmp = stockPoolDao.selectOne(code);
                     if (tmp!=null){
                         continue;
                     }
                 }catch (Exception e){
                     e.printStackTrace();
                 }
                 StockPoolDTO stockPoolDTO = new StockPoolDTO();
                 stockPoolDTO.setCode(code);
                 stockPoolDTO.setName(name);
                 stockPoolDTO.setSymbal(symbol);
                 stockPoolDTO.setCreated(new Date());
                 stockPoolDao.insertOne(stockPoolDTO);
             }
         }
     }
}
