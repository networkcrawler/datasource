package com.data.java.crawler.service.quartz;

import com.data.java.crawler.dao.MainInflowRankingDao;
import com.data.java.crawler.service.*;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * quartz任务类,实现的方法会定期执行,
 * 注意:这个是在springboot2.0后的版本才支持,之前用的1.5版本没有效果,不执行任务,也不出错
 */
public class SchedulerJob extends QuartzJobBean {
    @Autowired
    private CapitalFlowsTodayService capitalFlowsTodayService;
    @Autowired
    private CompanyRatingService companyRatingService;
    @Autowired
    private EastMoneyFundRealService eastMoneyFundRealService;
    @Autowired
    private IndividualReportService individualReportService;
    @Autowired
    private MainInflowRankingService mainInflowRankingService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            capitalFlowsTodayService.done();
            companyRatingService.done();
            eastMoneyFundRealService.done();
            individualReportService.done();
            mainInflowRankingService.done();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
