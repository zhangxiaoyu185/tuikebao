package com.xiaoyu.lingdian.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xiaoyu.lingdian.entity.BusiInvitedActivity;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.invited.BusiInvitedActivityService;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.RandomUtil;

/**
 * 每周一00:01分清0用户表中的用户的活动邀请人数，生成活动期数（活动期数表插入期数）
 * @author Administrator
 *
 */
public class AutoEmptyActivityPeriod extends QuartzJobBean{
 
	private final Logger logger = LoggerFactory.getLogger("BASE_LOGGER");
	
	/**
	* 用户表
	*/
	private CoreUserService coreUserService;
	
	/**
	* 邀请活动表
	*/
	private BusiInvitedActivityService busiInvitedActivityService;
	 
	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}  
	public void setBusiInvitedActivityService(
			BusiInvitedActivityService busiInvitedActivityService) {
		this.busiInvitedActivityService = busiInvitedActivityService;
	}
  
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("AutoEmptyActivityPeriod start");
		//清空邀请人数
		coreUserService.updateToEmptyCoreUserByInvitedCount(0);
		//生成活动期数
        String periods = DateUtil.getPeriods(new Date());
        try {
            if (null != periods) {
                BusiInvitedActivity busiInvitedActivity = new BusiInvitedActivity();
                String uuid = RandomUtil.generateString(16);
                busiInvitedActivity.setBsiayUuid(uuid);
                busiInvitedActivity.setBsiayPeriods(periods);
                busiInvitedActivity.setBsiayCdate(new Date());
                busiInvitedActivityService.insertBusiInvitedActivity(busiInvitedActivity);
            }
        } catch (Exception e) {
            logger.info("生成活动期数失败!");
        }
        logger.info("AutoEmptyActivityPeriod end");
	}

}
