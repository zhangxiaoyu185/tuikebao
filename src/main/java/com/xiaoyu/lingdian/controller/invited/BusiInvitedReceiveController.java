package com.xiaoyu.lingdian.controller.invited;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.BusiIntegralRecord;
import com.xiaoyu.lingdian.entity.BusiInvitedActivity;
import com.xiaoyu.lingdian.entity.BusiInvitedReceive;
import com.xiaoyu.lingdian.entity.BusiInvitedRule;
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.invited.BusiInvitedActivityService;
import com.xiaoyu.lingdian.service.invited.BusiInvitedReceiveService;
import com.xiaoyu.lingdian.service.invited.BusiInvitedRuleService;
import com.xiaoyu.lingdian.service.my.BusiIntegralRecordService;
import com.xiaoyu.lingdian.service.my.BusiMessageCenterService;
import com.xiaoyu.lingdian.vo.BusiInvitedReceiveVO;

@Controller
@RequestMapping(value="/busiInvitedReceive")
public class BusiInvitedReceiveController extends BaseController {

	/**
	* 邀请活动奖励领取表
	*/
	@Autowired
	private BusiInvitedReceiveService busiInvitedReceiveService;
	
	/**
	* 邀请活动表
	*/
	@Autowired
	private BusiInvitedActivityService busiInvitedActivityService;
	
	/**
	* 邀请奖励规则表
	*/
	@Autowired
	private BusiInvitedRuleService busiInvitedRuleService;
	
	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 用户积分明细表
	*/
	@Autowired
	private BusiIntegralRecordService busiIntegralRecordService;
	
	/**
	* 用户消息中心表
	*/
	@Autowired
	private BusiMessageCenterService busiMessageCenterService;
	
	
	/** 
	 *  领取奖励（根据领取规则，用户标识，期数标识和期数领取，需要根据用户标识查出活动邀请人数，根据领取规格查出领取规则，判断符不符合规格再领取）
	 * @param userUuid
	 * @param bsireUuid
	 * @param bsireActivity
	 * @param bsiayPeriods
	 * @param response
	 */
	@RequestMapping(value="/go/reward", method=RequestMethod.POST)
	public void goReward (String userUuid, String bsireUuid, String bsireActivity ,String bsiayPeriods, HttpServletResponse response) {
		logger.info("[BusiInvitedReceiveController]:begin goReward");

		if (StringUtil.isEmpty(userUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "领取用户不能为空!"), response);
			logger.info("[BusiInvitedReceiveController]:end goReward");
			return;
		}
		if (StringUtil.isEmpty(bsireUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邀请奖励规格标识不能为空!"), response);
			logger.info("[BusiInvitedReceiveController]:end goReward");
			return;
		} 
		if (StringUtil.isEmpty(bsireActivity)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "领取期数标识不能为空!"), response);
			logger.info("[BusiInvitedReceiveController]:end goReward");
			return;
		} 
		if (StringUtil.isEmpty(bsiayPeriods)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "领取期数不能为空!"), response);
			logger.info("[BusiInvitedReceiveController]:end goReward");
			return;
		} 
		BusiInvitedRule busiInvitedRule = new BusiInvitedRule();
		busiInvitedRule.setBsireUuid(bsireUuid);
		busiInvitedRule= busiInvitedRuleService.getBusiInvitedRule(busiInvitedRule);
		if (null == busiInvitedRule) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邀请奖励规格标识不存在!"), response);
			logger.info("[BusiInvitedReceiveController]:end goReward");
			return;
		} 
		BusiInvitedActivity busiInvitedActivity = new BusiInvitedActivity();
		busiInvitedActivity.setBsiayUuid(bsireActivity);
		BusiInvitedActivity invitedActivity = busiInvitedActivityService.getBusiInvitedActivity(busiInvitedActivity);
		if (null == invitedActivity) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "领取期数标识不存在!"), response);
			logger.info("[BusiInvitedReceiveController]:end goReward");
			return;
		} 
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(userUuid);
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "领取人不存在!"), response);
			logger.info("[BusiInvitedReceiveController]:end goReward");
			return;
		}
		 
	   if (coreUser.getCrusrInviteNused()  < busiInvitedRule.getBsireCount()){
		   writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "您的邀请人数不足!"), response);
			logger.info("[BusiInvitedReceiveController]:end goReward");
			return;
	   }
	   BusiInvitedReceive invitedReceive = new BusiInvitedReceive();
	   invitedReceive.setBsireUser(userUuid);
	   invitedReceive.setBsireActivity(bsireActivity); 
	   invitedReceive = busiInvitedReceiveService.getBusiInvitedReceive(invitedReceive);
       if (null != invitedReceive){
           writeAjaxJSONResponse(ResultMessageBuilder.build(false, 1, "已领取奖励!"),response);
           logger.info("[BusiInvitedReceiveController]:end goReward");
           return;
       }
		//添加领取奖励表
	    BusiInvitedReceive busiInvitedReceive = new BusiInvitedReceive();
		String uuid = RandomUtil.generateString(16);
		busiInvitedReceive.setBsireUuid(uuid);
		busiInvitedReceive.setBsireActivity(bsireActivity);
		busiInvitedReceive.setBsirePeriods(bsiayPeriods);
		busiInvitedReceive.setBsireUser(userUuid);
		busiInvitedReceive.setBsireIntegral(busiInvitedRule.getBsireIntegral());
		busiInvitedReceive.setBsireRuleNumber(busiInvitedRule.getBsireCount());
		busiInvitedReceive.setBsireActivityNumber(coreUser.getCrusrInviteNused());
		busiInvitedReceive.setBsireCdate(new Date()); 
		busiInvitedReceiveService.insertBusiInvitedReceive(busiInvitedReceive);
	   
		//增加用户积分
		coreUserService.updateCoreUserByIntegral(userUuid, busiInvitedRule.getBsireIntegral(), busiInvitedRule.getBsireIntegral());
		
		//添加到积分记录
		BusiIntegralRecord busiIntegralRecord = new BusiIntegralRecord();
		String bsirdUuid = RandomUtil.generateString(16);
		busiIntegralRecord.setBsirdUuid(bsirdUuid);
		busiIntegralRecord.setBsirdUser(userUuid);
		busiIntegralRecord.setBsirdType(1);
		busiIntegralRecord.setBsirdQuota(busiInvitedRule.getBsireIntegral()+"");
		busiIntegralRecord.setBsirdBill(bsiayPeriods+"期邀请好友活动中,邀请"+coreUser.getCrusrInviteNused()+"人,领取积分"+busiInvitedRule.getBsireIntegral());
		busiIntegralRecord.setBsirdCdate(new Date());
		busiIntegralRecordService.insertBusiIntegralRecord(busiIntegralRecord);

		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(userUuid);
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(3);
		busiMessageCenter.setBsmecProductPic(null);
		busiMessageCenter.setBsmecContent(bsiayPeriods+"期邀请好友活动中,邀请"+coreUser.getCrusrInviteNused()+"人,领取积分"+busiInvitedRule.getBsireIntegral());
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "领取奖励成功!", coreUser.getCrusrInviteNused()),response);
		logger.info("[BusiInvitedReceiveController]:end goReward");
	}
	
	/** 
	 * .根据用户标识和期数判断是否已领取过奖励 bsiayUuid--------->bsireActivity
	 */
	@RequestMapping(value="judge/busiInvitedReceive",method=RequestMethod.POST)
	public void judgeWhetherToReceive(String userUuid,String bsireActivity,HttpServletResponse response){
		logger.info("[BusiInvitedReceiveController]:begin judgeWhetherToReceive");
		Calendar cal = Calendar.getInstance();
		if(DateUtil.getDayOfWeek(cal)<=5){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "周一至周五不在领取时间内!"), response);
			logger.info("[BusiInvitedReceiveController]:end goReward");
			return;
		}
		if (StringUtil.isEmpty(userUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "领取用户必传!"), response);
			logger.info("[BusiInvitedReceiveController]:end goReward");
			return;
		}
		if (StringUtil.isEmpty(bsireActivity)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "领取期数标示必传!"), response);
			logger.info("[BusiInvitedReceiveController]:end goReward");
			return;
		}
		BusiInvitedReceive busiInvitedReceive = new BusiInvitedReceive();
		busiInvitedReceive.setBsireUser(userUuid);
		busiInvitedReceive.setBsireActivity(bsireActivity); 
		busiInvitedReceive = busiInvitedReceiveService.getBusiInvitedReceive(busiInvitedReceive);
		if (null != busiInvitedReceive){
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "已领取奖励!",busiInvitedReceive),response);
			logger.info("[BusiInvitedReceiveController]:end judgeWhetherToReceive");
			return;
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 2, "还未领取奖励!"),response);
		logger.info("[BusiInvitedReceiveController]:end judgeWhetherToReceive");
	}
	 
	/**
	* 添加
	*
	* @param bsireActivity 活动期数标识
	* @param bsirePeriods 活动期数
	* @param bsireUser 领取人
	* @param bsireIntegral 领取积分
	* @param bsireRuleNumber 规则邀请人数
	* @param bsireActivityNumber 活动邀请人数
	* @param bsireCdate 领取时间
	* @return
	*/
	@RequestMapping(value="/add/busiInvitedReceive", method=RequestMethod.POST)
	public void addBusiInvitedReceive (String bsireActivity, String bsirePeriods, String bsireUser, Integer bsireIntegral, Integer bsireRuleNumber, Integer bsireActivityNumber, String bsireCdate, HttpServletResponse response) {
		logger.info("[BusiInvitedReceiveController]:begin addBusiInvitedReceive");
		BusiInvitedReceive busiInvitedReceive = new BusiInvitedReceive();
		String uuid = RandomUtil.generateString(16);
		busiInvitedReceive.setBsireUuid(uuid);
		busiInvitedReceive.setBsireActivity(bsireActivity);
		busiInvitedReceive.setBsirePeriods(bsirePeriods);
		busiInvitedReceive.setBsireUser(bsireUser);
		busiInvitedReceive.setBsireIntegral(bsireIntegral);
		busiInvitedReceive.setBsireRuleNumber(bsireRuleNumber);
		busiInvitedReceive.setBsireActivityNumber(bsireActivityNumber);
		busiInvitedReceive.setBsireCdate(new Date());

		busiInvitedReceiveService.insertBusiInvitedReceive(busiInvitedReceive);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiInvitedReceiveController]:end addBusiInvitedReceive");

	}

	/**
	* 修改
	*
	* @param bsireUuid 标识UUID
	* @param bsireActivity 活动期数标识
	* @param bsirePeriods 活动期数
	* @param bsireUser 领取人
	* @param bsireIntegral 领取积分
	* @param bsireRuleNumber 规则邀请人数
	* @param bsireActivityNumber 活动邀请人数
	* @param bsireCdate 领取时间
	* @return
	*/
	@RequestMapping(value="/update/busiInvitedReceive", method=RequestMethod.POST)
	public void updateBusiInvitedReceive (String bsireUuid, String bsireActivity, String bsirePeriods, String bsireUser, Integer bsireIntegral, Integer bsireRuleNumber, Integer bsireActivityNumber, String bsireCdate, HttpServletResponse response) {
		logger.info("[BusiInvitedReceiveController]:begin updateBusiInvitedReceive");
		if (StringUtil.isEmpty(bsireUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiInvitedReceive busiInvitedReceive = new BusiInvitedReceive();
		busiInvitedReceive.setBsireUuid(bsireUuid);
		busiInvitedReceive.setBsireActivity(bsireActivity);
		busiInvitedReceive.setBsirePeriods(bsirePeriods);
		busiInvitedReceive.setBsireUser(bsireUser);
		busiInvitedReceive.setBsireIntegral(bsireIntegral);
		busiInvitedReceive.setBsireRuleNumber(bsireRuleNumber);
		busiInvitedReceive.setBsireActivityNumber(bsireActivityNumber);
		busiInvitedReceive.setBsireCdate(new Date());

		busiInvitedReceiveService.updateBusiInvitedReceive(busiInvitedReceive);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiInvitedReceiveController]:end updateBusiInvitedReceive");

	}

	/**
	* 删除
	*
	* @param bsireUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiInvitedReceive (String bsireUuid, HttpServletResponse response) {
		logger.info("[BusiInvitedReceiveController]:begin deleteBusiInvitedReceive");
		if (StringUtil.isEmpty(bsireUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiInvitedReceive busiInvitedReceive = new BusiInvitedReceive();
		busiInvitedReceive.setBsireUuid(bsireUuid);

		busiInvitedReceiveService.deleteBusiInvitedReceive(busiInvitedReceive);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiInvitedReceiveController]:end deleteBusiInvitedReceive");

	}

	/**
	* 批量删除
	*
	* @param bsireUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiInvitedReceive (String bsireUuids, HttpServletResponse response) {
		logger.info("[BusiInvitedReceiveController]:begin deleteBatchBusiInvitedReceive");
		if (StringUtil.isEmpty(bsireUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=bsireUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		busiInvitedReceiveService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiInvitedReceiveController]:end deleteBatchBusiInvitedReceive");

	}

	/**
	* 获取单个
	*
	* @param bsireUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiInvitedReceive (String bsireUuid, HttpServletResponse response) {
		logger.info("[BusiInvitedReceiveController]:begin viewsBusiInvitedReceive");
		if (StringUtil.isEmpty(bsireUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiInvitedReceive busiInvitedReceive = new BusiInvitedReceive();
		busiInvitedReceive.setBsireUuid(bsireUuid);
		busiInvitedReceive = busiInvitedReceiveService.getBusiInvitedReceive(busiInvitedReceive);
		if(null == busiInvitedReceive){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "领取记录不存在!"), response);
			logger.info("[BusiInvitedReceiveController]:end viewsBusiInvitedReceive");
			return;
		}
		
		BusiInvitedReceiveVO busiInvitedReceiveVO = new BusiInvitedReceiveVO();
		busiInvitedReceiveVO.convertPOToVO(busiInvitedReceive);

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiInvitedReceive.getBsireUser());
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "领取人不存在!"), response);
			logger.info("[BusiInvitedReceiveController]:end viewsBusiInvitedReceive");
			return;
		}
		busiInvitedReceiveVO.setBsireUserName(coreUser.getCrusrName());
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiInvitedReceiveVO), response);
		logger.info("[BusiInvitedReceiveController]:end viewsBusiInvitedReceive");
	}

	/**
	* 获取列表<Page>
	* @param userName
	* @param bsirePeriods
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiInvitedReceivePage (String userName, String bsirePeriods, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiInvitedReceiveController]:begin findBusiInvitedReceivePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiInvitedReceive> page = busiInvitedReceiveService.findBusiInvitedReceivePage(userName, bsirePeriods, pageNum, pageSize);
		Page<BusiInvitedReceiveVO> pageVO = new Page<BusiInvitedReceiveVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiInvitedReceive busiInvitedReceive : page.getResult()) {
			hashUserUuids.add(busiInvitedReceive.getBsireUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiInvitedReceiveVO> vos = new ArrayList<BusiInvitedReceiveVO>();
		BusiInvitedReceiveVO vo;
		for (BusiInvitedReceive busiInvitedReceive : page.getResult()) {
			vo = new BusiInvitedReceiveVO();			vo.convertPOToVO(busiInvitedReceive);
			vo.setBsireUserName(userMap.get(busiInvitedReceive.getBsireUser())==null?null:userMap.get(busiInvitedReceive.getBsireUser()).getCrusrName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiInvitedReceiveController]:end findBusiInvitedReceivePage");
	}

}