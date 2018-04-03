package com.xiaoyu.lingdian.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xiaoyu.lingdian.service.CoreUserService;

public class CashRecodeJob extends QuartzJobBean{
    
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
            logger.info("CashRecodeJob start"); 
                try {
                   //把用户表中今日提现信息赋值给昨日提现信息------把今日提现信息清0
                    coreUserService.updateCoreUserCashRecord(); 
                    //根据更新时间和状态为成功统计当月的提现次数和提现金额
                    coreUserService.updateCoreUserByCruseMonthCashfee(); 
                } catch (Exception e) {
                    logger.info("提现信息更新失败"+e.getMessage());
                }
            logger.info("CashRecodeJob end");     
    }

}
