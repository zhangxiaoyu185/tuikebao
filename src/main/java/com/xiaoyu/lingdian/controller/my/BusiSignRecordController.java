package com.xiaoyu.lingdian.controller.my;

import java.util.Date;
import java.util.HashMap;
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
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.BusiSignRecord;
import com.xiaoyu.lingdian.entity.BusiSignRule;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiIntegralRecordService;
import com.xiaoyu.lingdian.service.my.BusiMessageCenterService;
import com.xiaoyu.lingdian.service.my.BusiSignRecordService;
import com.xiaoyu.lingdian.service.my.BusiSignRuleService;
import com.xiaoyu.lingdian.vo.BusiSignRecordVO;

@Controller
@RequestMapping(value="/busiSignRecord")
public class BusiSignRecordController extends BaseController {

	/**
	* 用户当前签到记录表
	*/
	@Autowired
	private BusiSignRecordService busiSignRecordService;
	
	/**
	 * 用户表
	 */
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 签到设置规则表
	*/
	@Autowired
	private BusiSignRuleService busiSignRuleService;

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
	* 用户签到
	*
	* @param bssrdUser 签到用户
	* @return
	*/
	@RequestMapping(value="/sign", method=RequestMethod.POST)
	public void sign (String bssrdUser, HttpServletResponse response) {
		logger.info("[BusiSignRecordController]:begin sign");

		if (StringUtil.isEmpty(bssrdUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不能为空!"), response);
			logger.info("[BusiSignRecordController]:end sign");
			return;
		}

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(bssrdUser);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
			logger.info("[BusiSignRecordController]:end sign");
			return;
		}

		
		//获得积分
		int integral = 0;
		//根据用户查询签到记录
		BusiSignRecord signRecord = busiSignRecordService.getBusiSignRecordByUser(bssrdUser);
		if (null == signRecord) { //还未有签到记录，第一次签到
		    //连续签到天数
	        int count = 1;  
			BusiSignRule busiSignRule = busiSignRuleService.getBusiSignRuleAfterCount(count);
			if (null == busiSignRule) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "没有签到规则,请联系在线客服!"), response);
				logger.info("[BusiSignRecordController]:end sign");
				return;
			}
			
			integral = busiSignRule.getBssreIntegral();
			
			//添加签到记录
			BusiSignRecord busiSignRecord = new BusiSignRecord();
			busiSignRecord.setBssrdUuid(RandomUtil.generateString(16));
			busiSignRecord.setBssrdUser(bssrdUser);
			busiSignRecord.setBssrdIntegral(integral);
			busiSignRecord.setBssrdLastDate(new Date());
			busiSignRecord.setBssrdSignDays(count);
			busiSignRecordService.insertBusiSignRecord(busiSignRecord);
			
			//更新用户积分
			coreUserService.updateCoreUserByIntegral(bssrdUser, integral, integral);
			
			//添加用户积分明细
			BusiIntegralRecord busiIntegralRecord = new BusiIntegralRecord();
			busiIntegralRecord.setBsirdUuid(RandomUtil.generateString(16));
			busiIntegralRecord.setBsirdUser(bssrdUser);
			busiIntegralRecord.setBsirdType(1);
			busiIntegralRecord.setBsirdQuota(String.valueOf(integral));
			busiIntegralRecord.setBsirdBill("连续"+count+"天签到获得"+integral+"积分");
			busiIntegralRecord.setBsirdCdate(new Date());
			busiIntegralRecordService.insertBusiIntegralRecord(busiIntegralRecord);
			
			//添加到用户消息中心
			BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
			busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
			busiMessageCenter.setBsmecUser(bssrdUser);
			busiMessageCenter.setBsmecCdate(new Date());
			busiMessageCenter.setBsmecUdate(new Date());
			busiMessageCenter.setBsmecStatus(1);
			busiMessageCenter.setBsmecType(2);
			busiMessageCenter.setBsmecProductPic(null);
			busiMessageCenter.setBsmecContent("连续"+count+"天签到获得"+integral+"积分!");
			busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		} else { //有签到记录
			//判断当天是否已签到
			Date date = new Date();		
			String dateNow = DateUtil.formatDate("yyyy-MM-dd", date);
			String dateLastSign = DateUtil.formatDate("yyyy-MM-dd", signRecord.getBssrdLastDate());
			if (dateNow.equals(dateLastSign)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "您今日已签到!"), response);
				logger.info("[BusiSignRecordController]:end sign");
				return;
			} 
			//获取连续签到天数
            int count = signRecord.getBssrdSignDays(); 
	        Date dateSign = DateUtil.dateFormatToDate("yyyy-MM-dd",dateLastSign);
	        Date dateToday = DateUtil.dateFormatToDate("yyyy-MM-dd",dateNow);
	        int allDays = DateUtil.daysDiff(dateSign, dateToday); 
			if (1 == allDays) { //连续签到
				count++;			
			}else{
			    count = 1;
			}
			BusiSignRule busiSignRule = busiSignRuleService.getBusiSignRuleAfterCount(count);
			if (null == busiSignRule) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "没有签到规则,请联系在线客服!"), response);
				logger.info("[BusiSignRecordController]:end sign");
				return;
			}
				
			integral = busiSignRule.getBssreIntegral();
				
			//更新签到记录
			BusiSignRecord busiSignRecord = new BusiSignRecord();
			busiSignRecord.setBssrdUser(bssrdUser);
			busiSignRecord.setBssrdIntegral(integral);
			busiSignRecord.setBssrdLastDate(date);
			busiSignRecord.setBssrdSignDays(count);
			busiSignRecordService.updateBusiSignRecord(busiSignRecord);
				
			//更新用户积分
			coreUserService.updateCoreUserByIntegral(bssrdUser, integral, integral);
				
			//添加用户积分明细
			BusiIntegralRecord busiIntegralRecord = new BusiIntegralRecord();
			busiIntegralRecord.setBsirdUuid(RandomUtil.generateString(16));
			busiIntegralRecord.setBsirdUser(bssrdUser);
			busiIntegralRecord.setBsirdType(1);
			busiIntegralRecord.setBsirdQuota(String.valueOf(signRecord.getBssrdIntegral()));
			busiIntegralRecord.setBsirdBill("连续"+count+"天签到获得"+integral+"积分!");
			busiIntegralRecord.setBsirdCdate(new Date());
			busiIntegralRecordService.insertBusiIntegralRecord(busiIntegralRecord);
			
			//添加到用户消息中心
			BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
			busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
			busiMessageCenter.setBsmecUser(bssrdUser);
			busiMessageCenter.setBsmecCdate(new Date());
			busiMessageCenter.setBsmecUdate(new Date());
			busiMessageCenter.setBsmecStatus(1);
			busiMessageCenter.setBsmecType(2);
			busiMessageCenter.setBsmecProductPic(null);
			busiMessageCenter.setBsmecContent("连续"+count+"天签到获得"+integral+"积分!");
			busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		}

		coreUser = coreUserService.getCoreUser(coreUser);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("addIntegral", "+"+integral);
		map.put("userIntegral", coreUser.getCrusrIntegral());

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "签到成功!", map),response);
		logger.info("[BusiSignRecordController]:end sign");
	}

	/**
	* 获取单个签到记录
	*
	* @param bssrdUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiSignRecord (String bssrdUuid, HttpServletResponse response) {
		logger.info("[BusiSignRecordController]:begin viewsBusiSignRecord");
		if (StringUtil.isEmpty(bssrdUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiSignRecordController]:end viewsBusiSignRecord");
			return;
		}
		BusiSignRecord busiSignRecord = new BusiSignRecord();
		busiSignRecord.setBssrdUuid(bssrdUuid);
		busiSignRecord = busiSignRecordService.getBusiSignRecord(busiSignRecord);
		if(null == busiSignRecord){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "单个签到记录不存在!"), response);
			logger.info("[BusiSignRecordController]:end viewsBusiSignRecord");
			return;
		}

		BusiSignRecordVO busiSignRecordVO = new BusiSignRecordVO();
		busiSignRecordVO.convertPOToVO(busiSignRecord);

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiSignRecord.getBssrdUser());
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
			logger.info("[BusiSignRecordController]:end viewsBusiSignRecord");
			return;
		}
		busiSignRecordVO.setBssrdUserName(coreUser.getCrusrName());
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个签到记录成功", busiSignRecordVO), response);
		logger.info("[BusiSignRecordController]:end viewsBusiSignRecord");
	}

	/**
	* 后台获取签到记录<Page>
	* 
	* @param userName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiSignRecordPage (String userName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiSignRecordController]:begin findBusiSignRecordPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiSignRecord> page = busiSignRecordService.findBusiSignRecordPage(userName, pageNum, pageSize);
		Page<BusiSignRecordVO> pageVO = new Page<BusiSignRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiSignRecord busiSignRecord : page.getResult()) {
			hashUserUuids.add(busiSignRecord.getBssrdUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiSignRecordVO> vos = new ArrayList<BusiSignRecordVO>();
		BusiSignRecordVO vo;
		for (BusiSignRecord busiSignRecord : page.getResult()) {
			vo = new BusiSignRecordVO();			vo.convertPOToVO(busiSignRecord);
			vo.setBssrdUserName(userMap.get(busiSignRecord.getBssrdUser())==null?null:userMap.get(busiSignRecord.getBssrdUser()).getCrusrName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiSignRecordController]:end findBusiSignRecordPage");
	}

}