package com.xiaoyu.lingdian.controller.my;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
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
import com.xiaoyu.lingdian.entity.BusiExchangeBill;
import com.xiaoyu.lingdian.entity.BusiExchangeRule;
import com.xiaoyu.lingdian.entity.BusiIntegralRecord;
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiExchangeBillService;
import com.xiaoyu.lingdian.service.my.BusiExchangeRuleService;
import com.xiaoyu.lingdian.service.my.BusiIntegralRecordService;
import com.xiaoyu.lingdian.service.my.BusiMessageCenterService;
import com.xiaoyu.lingdian.vo.BusiExchangeBillVO;

@Controller
@RequestMapping(value="/busiExchangeBill")
public class BusiExchangeBillController extends BaseController {

	/**
	* 用户积分兑换话费记录表
	*/
	@Autowired
	private BusiExchangeBillService busiExchangeBillService;
	
	/**
	* 积分兑换话费设置表
	*/
	@Autowired
	private BusiExchangeRuleService busiExchangeRuleService;
	
	/**
	* 用户积分明细表
	*/
	@Autowired
	private BusiIntegralRecordService busiIntegralRecordService;

	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 用户消息中心表
	*/
	@Autowired
	private BusiMessageCenterService busiMessageCenterService;
	
	/**
	* 确认兑换(默认0待充值),去积分兑换话费表中判断获取，扣除相应积分
	*
	* @param bseblUser 兑换用户
	* @param bseblMobile 兑换手机号
	* @param bseeeUuid 所选积分兑换话费设置标识
	* @return
	*/
	@RequestMapping(value="/add/busiExchangeBill", method=RequestMethod.POST)
	public void addBusiExchangeBill (String bseblUser, String bseblMobile, String bseeeUuid, HttpServletResponse response) {
		logger.info("[BusiExchangeBillController]:begin addBusiExchangeBill");

		if (StringUtil.isEmpty(bseeeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExchangeBillController]:end addBusiExchangeBill");
			return;
		}
		BusiExchangeRule busiExchangeRule = new BusiExchangeRule();
		busiExchangeRule.setBseeeUuid(bseeeUuid);
		busiExchangeRule = busiExchangeRuleService.getBusiExchangeRule(busiExchangeRule);
		if (null == busiExchangeRule) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "积分兑换话费设置不存在!"), response);
			logger.info("[BusiExchangeBillController]:end addBusiExchangeBill");
			return;
		}
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(bseblUser);
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "兑换人不存在!"), response);
			logger.info("[BusiExchangeBillController]:end addBusiExchangeBill");
			return;
		}
		if(coreUser.getCrusrIntegral() < busiExchangeRule.getBseeeIntegral()){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "您的积分不足!"), response);
			logger.info("[BusiExchangeBillController]:end addBusiExchangeBill");
			return;
		}
		if (!StringUtil.isMobile(bseblMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号码不符合规范!"), response);
			logger.info("[BusiExchangeBillController]:end addBusiExchangeBill");
			return;
		}		BusiExchangeBill busiExchangeBill = new BusiExchangeBill();
		String uuid = RandomUtil.generateString(16);
		busiExchangeBill.setBseblUuid(uuid);
		busiExchangeBill.setBseblUser(bseblUser);
		busiExchangeBill.setBseblMobile(bseblMobile);
		busiExchangeBill.setBseblIntegral(busiExchangeRule.getBseeeIntegral().toString());
		busiExchangeBill.setBseblBill(busiExchangeRule.getBseeeBill().toString());
		busiExchangeBill.setBseblStatus(0);
		busiExchangeBill.setBseblCdate(new Date());
		busiExchangeBill.setBseblUdate(new Date());
		busiExchangeBillService.insertBusiExchangeBill(busiExchangeBill);

		//扣除用户积分
		coreUserService.updateCoreUserByIntegral(bseblUser, -busiExchangeRule.getBseeeIntegral(), 0);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "确认兑换成功!"),response);
		logger.info("[BusiExchangeBillController]:end addBusiExchangeBill");
	}

	/**
	* 后台审核充值，状态改为1已充值，添加用户积分明细记录
	*
	* @param bseblUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/update/busiExchangeBill", method=RequestMethod.POST)
	public void updateBusiExchangeBill (String bseblUuid, HttpServletResponse response) {
		logger.info("[BusiExchangeBillController]:begin updateBusiExchangeBill");
		if (StringUtil.isEmpty(bseblUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExchangeBillController]:end updateBusiExchangeBill");
			return;
		}
		BusiExchangeBill busiExchangeBill = new BusiExchangeBill();
		busiExchangeBill.setBseblUuid(bseblUuid);
		BusiExchangeBill exchangeBill = busiExchangeBillService.getBusiExchangeBill(busiExchangeBill);
		if (null == exchangeBill) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "积分兑换记录不存在!"), response);
			logger.info("[BusiExchangeBillController]:end updateBusiExchangeBill");
			return;
		}
		if (0 != exchangeBill.getBseblStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "已经审核操作过!"), response);
			logger.info("[BusiExchangeBillController]:end updateBusiExchangeBill");
			return;
		}
		busiExchangeBill.setBseblStatus(1);
		busiExchangeBill.setBseblUdate(new Date());
		busiExchangeBillService.updateBusiExchangeBill(busiExchangeBill);

		BusiIntegralRecord busiIntegralRecord = new BusiIntegralRecord();
		String uuid = RandomUtil.generateString(16);
		busiIntegralRecord.setBsirdUuid(uuid);
		busiIntegralRecord.setBsirdUser(exchangeBill.getBseblUser());
		busiIntegralRecord.setBsirdType(2);
		busiIntegralRecord.setBsirdQuota("-"+exchangeBill.getBseblIntegral());
		busiIntegralRecord.setBsirdBill("手机话费充值"+exchangeBill.getBseblBill()+"消耗"+exchangeBill.getBseblIntegral()+"积分!");
		busiIntegralRecord.setBsirdCdate(new Date());
		busiIntegralRecordService.insertBusiIntegralRecord(busiIntegralRecord);
		
		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(exchangeBill.getBseblUser());
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(2);
		busiMessageCenter.setBsmecProductPic(null);
		busiMessageCenter.setBsmecContent("手机话费充值"+exchangeBill.getBseblBill()+"消耗"+exchangeBill.getBseblIntegral()+"积分!");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);

		//更新用户的兑换记录
		coreUserService.updateCoreUserByExchCount(busiExchangeBill.getBseblUser(), 1, Integer.valueOf(busiExchangeBill.getBseblIntegral()));
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "审核成功!"),response);
		logger.info("[BusiExchangeBillController]:end updateBusiExchangeBill");
	}

	/**
	* 删除
	*
	* @param bseblUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiExchangeBill (String bseblUuid, HttpServletResponse response) {
		logger.info("[BusiExchangeBillController]:begin deleteBusiExchangeBill");
		if (StringUtil.isEmpty(bseblUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExchangeBillController]:end deleteBusiExchangeBill");
			return;
		}
		BusiExchangeBill busiExchangeBill = new BusiExchangeBill();
		busiExchangeBill.setBseblUuid(bseblUuid);

		busiExchangeBillService.deleteBusiExchangeBill(busiExchangeBill);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiExchangeBillController]:end deleteBusiExchangeBill");
	}

	/**
	* 批量删除
	*
	* @param bseblUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiExchangeBill (String bseblUuids, HttpServletResponse response) {
		logger.info("[BusiExchangeBillController]:begin deleteBatchBusiExchangeBill");
		if (StringUtil.isEmpty(bseblUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiExchangeBillController]:end deleteBatchBusiExchangeBill");
			return;
		}
		String[] uuids=bseblUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiExchangeBillController]:end deleteBatchBusiExchangeBill");
			return;
		}
		busiExchangeBillService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiExchangeBillController]:end deleteBatchBusiExchangeBill");

	}

	/**
	* 获取单个积分兑换记录
	*
	* @param bseblUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiExchangeBill (String bseblUuid, HttpServletResponse response) {
		logger.info("[BusiExchangeBillController]:begin viewsBusiExchangeBill");
		if (StringUtil.isEmpty(bseblUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExchangeBillController]:end viewsBusiExchangeBill");
			return;
		}
		BusiExchangeBill busiExchangeBill = new BusiExchangeBill();
		busiExchangeBill.setBseblUuid(bseblUuid);
		busiExchangeBill = busiExchangeBillService.getBusiExchangeBill(busiExchangeBill);
		if(null == busiExchangeBill){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "积分兑换记录不存在!"), response);
			logger.info("[BusiExchangeBillController]:end viewsBusiExchangeBill");
			return;
		}

		BusiExchangeBillVO busiExchangeBillVO = new BusiExchangeBillVO();
		busiExchangeBillVO.convertPOToVO(busiExchangeBill);

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiExchangeBill.getBseblUser());
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "兑换人不存在!"), response);
			logger.info("[BusiExchangeBillController]:end viewsBusiExchangeBill");
			return;
		}
		busiExchangeBillVO.setBseblUserName(coreUser.getCrusrName());
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个积分兑换记录成功", busiExchangeBillVO), response);
		logger.info("[BusiExchangeBillController]:end viewsBusiExchangeBill");

	}

	/**
	* 我的积分兑换列表page
	* 
	* @param bseblUser 所属用户
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/my/find/by/cnd", method=RequestMethod.POST)
	public void findBusiExchangeBillPageByMy (String bseblUser, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiExchangeBillController]:begin findBusiExchangeBillPageByMy");

		if (StringUtil.isEmpty(bseblUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属用户不能为空!"), response);
			logger.info("[BusiExchangeBillController]:end findBusiExchangeBillPageByMy");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiExchangeBill> page = busiExchangeBillService.findBusiExchangeBillPageByMy(bseblUser, pageNum, pageSize);
		Page<BusiExchangeBillVO> pageVO = new Page<BusiExchangeBillVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		List<BusiExchangeBillVO> vos = new ArrayList<BusiExchangeBillVO>();
		BusiExchangeBillVO vo;
		for (BusiExchangeBill busiExchangeBill : page.getResult()) {
			vo = new BusiExchangeBillVO();
			vo.convertPOToVO(busiExchangeBill);
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiExchangeBillController]:end findBusiExchangeBillPageByMy");

	}

	/**
	* 后台获取积分兑换列表<Page>
	* 
	* @param userName
	* @param bseblMobile
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiExchangeBillPage (String userName, String bseblMobile, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiExchangeBillController]:begin findBusiExchangeBillPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiExchangeBill> page = busiExchangeBillService.findBusiExchangeBillPage(userName, bseblMobile, pageNum, pageSize);
		Page<BusiExchangeBillVO> pageVO = new Page<BusiExchangeBillVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiExchangeBill busiExchangeBill : page.getResult()) {
			hashUserUuids.add(busiExchangeBill.getBseblUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);

		List<BusiExchangeBillVO> vos = new ArrayList<BusiExchangeBillVO>();
		BusiExchangeBillVO vo;
		for (BusiExchangeBill busiExchangeBill : page.getResult()) {
			vo = new BusiExchangeBillVO();			vo.convertPOToVO(busiExchangeBill);
			vo.setBseblUserName(userMap.get(busiExchangeBill.getBseblUser())==null?null:userMap.get(busiExchangeBill.getBseblUser()).getCrusrName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiExchangeBillController]:end findBusiExchangeBillPage");
	}

}