package com.data.java.crawler.task;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.java.crawler.dto.MainInflowRankingDTO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 主力净流入排名 数据爬取
 */
public class MainInflowRankingTask {

    private String url = "http://data.eastmoney.com/zjlx/list.html";

    public void done(){
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
            if (line!=null){
                line = line.trim();
                String dataStr = line.substring(line.indexOf("defjson:")+8,line.lastIndexOf(","));
                Map<String,Object> dataMap = JSONObject.parseObject(dataStr, Map.class);
                JSONArray dataArray =(JSONArray) dataMap.get("data");

                for (int i=0;i<dataArray.size();i++){
                    String one = (String) dataArray.get(i);
                    String[] ones = one.split(",");
                    System.out.println(one);
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

                    mainInflowRankingDTOList.add(mainInflowRankingDTO);
                }
            }

            //将数据写入数据库mongodb


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        MainInflowRankingTask mainInflowRankingTask = new MainInflowRankingTask();
        mainInflowRankingTask.done();
    }
}
