package com.data.java.crawler.service.impl;

import com.data.java.crawler.dto.EastMoneyBlogDTO;
import com.data.java.crawler.service.EastMoneyBlogService;
import com.data.java.crawler.utils.SendMailUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 1.每天上午9点,下午13点定时获取东方财富的热门博主文章
 * 2.并将消息发送到邮箱上
 */
@Service
public class EastMoneyBlogServiceImpl implements EastMoneyBlogService {
    private String url = "http://blog.eastmoney.com/hot_1.html";//连接地址

    public void done(){
        //调接口
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/72.0.3626.121 Chrome/72.0.3626.121 Safari/537.36")
                    .referrer("http://blog.eastmoney.com/hot_1.html")
                    .header("Accept", "*/*")
                    .get();
        }catch (Exception e){
            e.printStackTrace();
        }

        //解析文档,获取博客名称,博客链接,作者
        List<EastMoneyBlogDTO> eastMoneyBlogDTOS = new LinkedList<EastMoneyBlogDTO>();

        if (document!=null) {
            Element body = document.body();
            Elements divs = body.select("div#main1");
            Element div = divs.get(1);
            Elements uls = div.select("ul");
            for (Element ul : uls) {
                String title = ul.select("li.w45").select("a").html();
                String href = ul.select("li.w45").select("a").attr("href");
                String author = ul.select("li.w15").select("span").html();
                if (title != null&&!title.equals("") && href != null && author != null) {
                    EastMoneyBlogDTO eastMoneyBlogDTO = new EastMoneyBlogDTO();
                    eastMoneyBlogDTO.setAuthor(author);
                    eastMoneyBlogDTO.setHref(href);
                    eastMoneyBlogDTO.setTitle(title);
                    eastMoneyBlogDTOS.add(eastMoneyBlogDTO);
                }
            }
        }

        //发送邮件到tanpei@yeah.net
        //组装邮件信息
        String content = "";
        int i = 1;
        for (EastMoneyBlogDTO eastMoneyBlogDTO:eastMoneyBlogDTOS){
            String div = "<div>"+
                    "<a href='"+eastMoneyBlogDTO.getHref()+"'>"+eastMoneyBlogDTO.getTitle()+"</a>"+
                    "<span>"+eastMoneyBlogDTO.getAuthor()+"</span>"
                    +"</div>";
            content = content+div;
            i++;
            if (i==20) break;
        }
        content = "<div>"+content+"</div>";
        String subject = "热门博主文章20条";
        SendMailUtils.send(subject,content);
    }
}
