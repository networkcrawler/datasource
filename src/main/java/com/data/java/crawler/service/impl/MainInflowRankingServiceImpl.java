package com.data.java.crawler.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.java.crawler.dao.MainInflowRankingDao;
import com.data.java.crawler.dao.impl.MainInflowRankingDaoImpl;
import com.data.java.crawler.dto.MainInflowRankingDTO;
import com.data.java.crawler.service.MainInflowRankingService;
import com.data.java.crawler.utils.DateFormatUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 主力净流入排名 数据爬取
 */
@Service
public class MainInflowRankingServiceImpl implements MainInflowRankingService {
    private static Logger LOGGER = LoggerFactory.getLogger(MainInflowRankingServiceImpl.class);

    private String url = "http://data.eastmoney.com/zjlx/list.html";
    @Autowired
    private MainInflowRankingDao mainInflowRankingDao;

    public void done(){
        LOGGER.info("开始获取主力净流入排名\n\t");
        Document document = null;
        try {
            //获取文档内容
            document = Jsoup.connect(url)
            .ignoreContentType(true)
            .ignoreHttpErrors(true)
            .get();

            //将文档转化成流
            BufferedReader bufferedReader = null;
            String line = null;
            try {
                bufferedReader = new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream(document.toString().getBytes("utf8")), "utf8"
                        )
                );

                Pattern p = Pattern.compile("defjson:*");
                while ((line = bufferedReader.readLine()) != null) {
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        break;
                    }
                }
            }catch (Exception e){
                if (bufferedReader!=null) {
                    try {
                        bufferedReader.close();
                    }catch (Exception e1){
                        bufferedReader=null;
                    }
                }
            }

            //截取数据
            List<MainInflowRankingDTO> mainInflowRankingDTOList = new LinkedList<MainInflowRankingDTO>();
            List<MainInflowRankingDTO> mainInflowRankingDTOListup = new LinkedList<MainInflowRankingDTO>();

            if (line!=null){
                line = line.trim();
                String dataStr = line.substring(line.indexOf("defjson:")+8,line.lastIndexOf(","));
                Map<String,Object> dataMap = JSONObject.parseObject(dataStr, Map.class);
                JSONArray dataArray =(JSONArray) dataMap.get("data");

                for (int i=0;i<dataArray.size();i++){
                    String one = (String) dataArray.get(i);
                    String[] ones = one.split(",");
                    MainInflowRankingDTO mainInflowRankingDTO = new MainInflowRankingDTO();
                    mainInflowRankingDTO.setSymbol(ones[1]);
                    mainInflowRankingDTO.setName(ones[2]);
                    mainInflowRankingDTO.setLasttrade(ones[3]);
                    mainInflowRankingDTO.setTodayNetPrimary(ones[4]);
                    mainInflowRankingDTO.setTodayRanking(Integer.parseInt(ones[5]));
                    mainInflowRankingDTO.setTodayChg(ones[6]);
                    mainInflowRankingDTO.setFireNetPrimary(ones[7]);
                    mainInflowRankingDTO.setFireRanking(Integer.parseInt(ones[8]));
                    mainInflowRankingDTO.setFireChg(ones[9]);
                    mainInflowRankingDTO.setTenNetPrimary(ones[10]);
                    mainInflowRankingDTO.setTenRanking(Integer.parseInt(ones[11]));
                    mainInflowRankingDTO.setTenChg(ones[12]);
                    mainInflowRankingDTO.setPlateName(ones[13]);
                    mainInflowRankingDTO.setPlateSymbol(ones[14]);
                    mainInflowRankingDTO.setCreated(DateFormatUtils.getDay(null));
                    mainInflowRankingDTO.setUpdated(DateFormatUtils.getDay(null));
                    List<MainInflowRankingDTO> list = mainInflowRankingDao.findBySymbolAndCreate(ones[1]);
                    
                    if(list.size()>0) {
                    	mainInflowRankingDTOListup.add(mainInflowRankingDTO);
                    }else {
                    	mainInflowRankingDTOList.add(mainInflowRankingDTO);
                    }
                }
            }

            //将数据写入数据库mongodb
            if(mainInflowRankingDTOList.size()>0)
            	mainInflowRankingDao.insertMany(mainInflowRankingDTOList);
            if(mainInflowRankingDTOListup.size()>0)
            	mainInflowRankingDao.updateBySymbolAndCreate(mainInflowRankingDTOListup);
            LOGGER.info("获取主力净流入排名结束共"+mainInflowRankingDTOList.size()+"条数据\n\t");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
