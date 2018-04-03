package com.xiaoyu.lingdian.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xiaoyu.lingdian.entity.BusiInvitedActivity;
import com.xiaoyu.lingdian.entity.BusiInvitedChats;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.invited.BusiInvitedActivityService;
import com.xiaoyu.lingdian.service.invited.BusiInvitedChatsService;
import com.xiaoyu.lingdian.tool.RandomUtil;

/**
 * 生成排行榜
 * @author Administrator
 *
 */
public class AutomaticGenerateCharts extends QuartzJobBean{

	private final Logger logger = LoggerFactory.getLogger("BASE_LOGGER");
	
	/**
	* 用户表
	*/
	private CoreUserService coreUserService;
	
	/**
	* 邀请排行榜表
	*/
	private BusiInvitedChatsService busiInvitedChatsService;
	
	/**
	* 邀请活动表
	*/ 
	private BusiInvitedActivityService busiInvitedActivityService;
	 
	
	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	} 
	public void setBusiInvitedChatsService(
			BusiInvitedChatsService busiInvitedChatsService) {
		this.busiInvitedChatsService = busiInvitedChatsService;
	} 
	public void setBusiInvitedActivityService(
			BusiInvitedActivityService busiInvitedActivityService) {
		this.busiInvitedActivityService = busiInvitedActivityService;
	}
 
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("AutomaticGenerateCharts start");
		//查询最新一期期数 
		BusiInvitedActivity latest = busiInvitedActivityService.getBusiInvitedActivityLatest(); 
		if (null == latest){
		    logger.info("还没有期数生成!");
            logger.info("[BusiInvitedChatsController]:end addBusiInvitedChats");
            return;
		}  
		//判断期数是否已存在 
		BusiInvitedChats invitedChats = busiInvitedChatsService.getBusiInvitedChatsByPeriods( latest.getBsiayPeriods());
        if (null != invitedChats) { 
            logger.info("排行榜中期数已存在!");
            logger.info("[BusiInvitedChatsController]:end addBusiInvitedChats");
            return;
        } 
        //查询邀请人数最多三位 是否少于3位
        List<CoreUser> ranking = coreUserService.findCoreUserListRanking();
        if (null == ranking){
            logger.info("该期排行榜为空!");
            logger.info("[BusiInvitedChatsController]:end addBusiInvitedChats");
            return;
        }   
		//插入排行榜表  
        try {
            BusiInvitedChats busiInvitedChats = new BusiInvitedChats();
            String uuid = RandomUtil.generateString(16);
            busiInvitedChats.setBsicsUuid(uuid);
            busiInvitedChats.setBsicsPeriods(latest.getBsiayPeriods());
            busiInvitedChats.setBsicsStatus(0);// 禁用
            if (ranking.size() == 3 && ranking.get(2).getCrusrInviteNused() != 0) {
                busiInvitedChats.setBsicsBronzeNumber(ranking.get(2).getCrusrInviteNused());
                busiInvitedChats.setBsicsBronzeGainer(ranking.get(2).getCrusrUuid());
            }
            if (ranking.size() <= 3 && ranking.size() >= 2 && ranking.get(1).getCrusrInviteNused() !=0) {
                busiInvitedChats.setBsicsSilverNumber(ranking.get(1).getCrusrInviteNused());
                busiInvitedChats.setBsicsSilverGainer(ranking.get(1).getCrusrUuid());
            }
            if (ranking.size() >= 1 && ranking.get(0).getCrusrInviteNused() !=0) {
                busiInvitedChats.setBsicsGoldNumber(ranking.get(0).getCrusrInviteNused());
                busiInvitedChats.setBsicsGoldGainer(ranking.get(0).getCrusrUuid());
            }
            busiInvitedChatsService.insertBusiInvitedChats(busiInvitedChats);
        } catch (Exception e) {
            logger.info("生成排行榜失败!");
        }
        logger.info("AutomaticGenerateCharts end");
    }
}
