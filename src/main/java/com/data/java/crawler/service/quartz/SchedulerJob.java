package com.data.java.crawler.service.quartz;

import com.data.java.crawler.dao.MainInflowRankingDao;
import com.data.java.crawler.service.*;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * quartz������,ʵ�ֵķ����ᶨ��ִ��,
 * ע��:�������springboot2.0��İ汾��֧��,֮ǰ�õ�1.5�汾û��Ч��,��ִ������,Ҳ������
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
