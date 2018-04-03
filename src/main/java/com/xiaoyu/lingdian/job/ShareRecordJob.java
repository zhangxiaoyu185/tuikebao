package com.xiaoyu.lingdian.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xiaoyu.lingdian.service.CoreUserService;

public class ShareRecordJob extends QuartzJobBean{
    
    private final Logger logger = LoggerFactory.getLogger("BASE_LOGGER");
    
    /**
     * 用户表
     */
    private CoreUserService coreUserService;
     
    public void setCoreUserService(CoreUserService coreUserService) {
        this.coreUserService = coreUserService;
    } 

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException { 
            logger.info("ShareRecordJob start"); 
                try {
                  //先把用户表中今日分享信息赋值给昨日分享信息----把今日分享信息清0 
                    coreUserService.updateCoreUserShareRecord();  
                    //根据分享时间统计当月的分享数和积分收益
                    coreUserService.updateCoreUserByCruseMonthShareIntegral(); 
                    //根据确认收货时间统计当月的完成笔数和返现收益(状态大于1客户下单)
                    coreUserService.updateCoreUserByCruseMonthAddincome();
                } catch (Exception e) {
                    logger.info("分享信息更新失败"+e.getMessage());
                }
            logger.info("ShareRecordJob end");     
    }

}
