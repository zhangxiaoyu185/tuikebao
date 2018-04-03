package com.xiaoyu.lingdian.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xiaoyu.lingdian.service.CoreUserService;

public class IntegralExchangeRecordJob extends QuartzJobBean{
    
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
            logger.info("IntegralExchangeRecordJob start"); 
                try {
                   //先把用户表中今日兑换信息赋值给昨日兑换信息----把今日兑换信息清0 
                    coreUserService.updateCoreUserExchangeRecord(); 
                   //再去BUSI_EXCHANGE_BILL-用户积分兑换话费记录表，根据更新时间和状态为已充值统计当月的兑换次数和兑换金额
                    coreUserService.updateCoreUserByCruseMonthExchintegral(); 
                } catch (Exception e) {
                    logger.info("兑换信息更新失败"+e.getMessage());
                }
            logger.info("IntegralExchangeRecordJob end");     
    }

}
