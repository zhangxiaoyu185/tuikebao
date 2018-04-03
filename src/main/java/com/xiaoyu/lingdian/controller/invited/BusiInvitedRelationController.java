package com.xiaoyu.lingdian.controller.invited;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.BusiInvitedRelation;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.invited.BusiInvitedRelationService;
import com.xiaoyu.lingdian.vo.BusiInvitedRelationVO;

@Controller
@RequestMapping(value="/busiInvitedRelation")
public class BusiInvitedRelationController extends BaseController {

	/**
	* 邀请关系表
	*/
	@Autowired
	private BusiInvitedRelationService busiInvitedRelationService;
	
	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 今日邀请数
	*
	* @param bsirnInvited
	* @return
	*/
	@RequestMapping(value="/now/day/invited/count", method=RequestMethod.POST)
	public void nowDayInvitedCount (String bsirnInvited, HttpServletResponse response) {
		logger.info("[BusiInvitedRelationController]:begin nowDayInvitedCount");

		if (StringUtil.isEmpty(bsirnInvited)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邀请人不能为空!"), response);
			logger.info("[BusiInvitedRelationController]:end nowDayInvitedCount");
			return;
		}

		Page<BusiInvitedRelation> page = busiInvitedRelationService.findBusiInvitedRelationForPagesByMyNow(bsirnInvited, 1, 100);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取今日邀请数成功", page.getTotalCount()), response);
		logger.info("[BusiInvitedRelationController]:end nowDayInvitedCount");
	}
	
	/**
	* 今日邀请人列表
	*
	* @param bsirnInvited
	* @return
	*/
	@RequestMapping(value="/now/day/invited/list", method=RequestMethod.POST)
	public void nowDayInvitedList (String bsirnInvited, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiInvitedRelationController]:begin nowDayInvitedList");

		if (StringUtil.isEmpty(bsirnInvited)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邀请人不能为空!"), response);
			logger.info("[BusiInvitedRelationController]:end nowDayInvitedList");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiInvitedRelation> page = busiInvitedRelationService.findBusiInvitedRelationForPagesByMyNow(bsirnInvited, pageNum, pageSize);
		Page<BusiInvitedRelationVO> pageVO = new Page<BusiInvitedRelationVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiInvitedRelation busiInvitedRelation : page.getResult()) {
			hashUserUuids.add(busiInvitedRelation.getBsirnInvited());
			hashUserUuids.add(busiInvitedRelation.getBsirnBeInvited());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiInvitedRelationVO> vos = new ArrayList<BusiInvitedRelationVO>();
		BusiInvitedRelationVO vo;
		for (BusiInvitedRelation busiInvitedRelation : page.getResult()) {
			vo = new BusiInvitedRelationVO();
			vo.convertPOToVO(busiInvitedRelation);
			vo.setBsirnBeInvitedName(userMap.get(busiInvitedRelation.getBsirnBeInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnBeInvited()).getCrusrName());
			vo.setBsirnBeInvitedHead(userMap.get(busiInvitedRelation.getBsirnBeInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnBeInvited()).getCrusrHead());
			vo.setBsirnInvitedName(userMap.get(busiInvitedRelation.getBsirnInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnInvited()).getCrusrName());
			vo.setBsirnInvitedHead(userMap.get(busiInvitedRelation.getBsirnInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnInvited()).getCrusrHead());
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取今日邀请人列表成功", pageVO), response);
		logger.info("[BusiInvitedRelationController]:end nowDayInvitedList");
	}
	
	/**
	* 活动邀请人列表
	*
	* @param bsirnInvited
	* @return
	*/
	@RequestMapping(value="/active/invited/list", method=RequestMethod.POST)
	public void activeInvitedList (String bsirnInvited, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiInvitedRelationController]:begin activeInvitedList");

		if (StringUtil.isEmpty(bsirnInvited)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邀请人不能为空!"), response);
			logger.info("[BusiInvitedRelationController]:end activeInvitedList");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiInvitedRelation> page = busiInvitedRelationService.findBusiInvitedRelationForPagesByActive(bsirnInvited, DateUtil.getFirstDayOfWeek(new Date()), pageNum, pageSize);
		Page<BusiInvitedRelationVO> pageVO = new Page<BusiInvitedRelationVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiInvitedRelation busiInvitedRelation : page.getResult()) {
			hashUserUuids.add(busiInvitedRelation.getBsirnInvited());
			hashUserUuids.add(busiInvitedRelation.getBsirnBeInvited());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiInvitedRelationVO> vos = new ArrayList<BusiInvitedRelationVO>();
		BusiInvitedRelationVO vo;
		for (BusiInvitedRelation busiInvitedRelation : page.getResult()) {
			vo = new BusiInvitedRelationVO();
			vo.convertPOToVO(busiInvitedRelation);
			vo.setBsirnBeInvitedName(userMap.get(busiInvitedRelation.getBsirnBeInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnBeInvited()).getCrusrName());
			vo.setBsirnBeInvitedHead(userMap.get(busiInvitedRelation.getBsirnBeInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnBeInvited()).getCrusrHead());
			vo.setBsirnInvitedName(userMap.get(busiInvitedRelation.getBsirnInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnInvited()).getCrusrName());
			vo.setBsirnInvitedHead(userMap.get(busiInvitedRelation.getBsirnInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnInvited()).getCrusrHead());
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取活动邀请人列表成功", pageVO), response);
		logger.info("[BusiInvitedRelationController]:end activeInvitedList");
	}
	
	/**
	* 累计邀请人列表
	*
	* @param bsirnInvited
	* @return
	*/
	@RequestMapping(value="/total/day/invited/list", method=RequestMethod.POST)
	public void totalDayInvitedList (String bsirnInvited, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiInvitedRelationController]:begin totalDayInvitedList");

		if (StringUtil.isEmpty(bsirnInvited)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邀请人不能为空!"), response);
			logger.info("[BusiInvitedRelationController]:end totalDayInvitedList");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiInvitedRelation> page = busiInvitedRelationService.findBusiInvitedRelationForPagesByMyTotal(bsirnInvited, pageNum, pageSize);
		Page<BusiInvitedRelationVO> pageVO = new Page<BusiInvitedRelationVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiInvitedRelation busiInvitedRelation : page.getResult()) {
			hashUserUuids.add(busiInvitedRelation.getBsirnInvited());
			hashUserUuids.add(busiInvitedRelation.getBsirnBeInvited());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiInvitedRelationVO> vos = new ArrayList<BusiInvitedRelationVO>();
		BusiInvitedRelationVO vo;
		for (BusiInvitedRelation busiInvitedRelation : page.getResult()) {
			vo = new BusiInvitedRelationVO();
			vo.convertPOToVO(busiInvitedRelation);
			vo.setBsirnBeInvitedName(userMap.get(busiInvitedRelation.getBsirnBeInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnBeInvited()).getCrusrName());
			vo.setBsirnBeInvitedHead(userMap.get(busiInvitedRelation.getBsirnBeInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnBeInvited()).getCrusrHead());
			vo.setBsirnInvitedName(userMap.get(busiInvitedRelation.getBsirnInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnInvited()).getCrusrName());
			vo.setBsirnInvitedHead(userMap.get(busiInvitedRelation.getBsirnInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnInvited()).getCrusrHead());
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取累计邀请人列表成功", pageVO), response);
		logger.info("[BusiInvitedRelationController]:end totalDayInvitedList");
	}

	/**
	* 获取单个邀请好友关系
	*
	* @param bsirnUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiInvitedRelation (String bsirnUuid, HttpServletResponse response) {
		logger.info("[BusiInvitedRelationController]:begin viewsBusiInvitedRelation");
		if (StringUtil.isEmpty(bsirnUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiInvitedRelationController]:end viewsBusiInvitedRelation");
			return;
		}
		BusiInvitedRelation busiInvitedRelation = new BusiInvitedRelation();
		busiInvitedRelation.setBsirnUuid(bsirnUuid);
		busiInvitedRelation = busiInvitedRelationService.getBusiInvitedRelation(busiInvitedRelation);
		if(null == busiInvitedRelation){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邀请好友关系不存在!"), response);
			logger.info("[BusiInvitedRelationController]:end viewsBusiInvitedRelation");
			return;
		}

		BusiInvitedRelationVO busiInvitedRelationVO = new BusiInvitedRelationVO();
		busiInvitedRelationVO.convertPOToVO(busiInvitedRelation);

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiInvitedRelation.getBsirnInvited());
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邀请人不存在!"), response);
			logger.info("[BusiInvitedRelationController]:end viewsBusiInvitedRelation");
			return;
		}
		busiInvitedRelationVO.setBsirnInvitedName(coreUser.getCrusrName());
		busiInvitedRelationVO.setBsirnInvitedHead(coreUser.getCrusrHead());
		
		CoreUser user = new CoreUser();
		user.setCrusrUuid(busiInvitedRelation.getBsirnBeInvited());
		user = coreUserService.getCoreUser(user);
		if(null == user){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "被邀请人不存在!"), response);
			logger.info("[BusiInvitedRelationController]:end viewsBusiInvitedRelation");
			return;
		}
		busiInvitedRelationVO.setBsirnBeInvitedName(user.getCrusrName());
		busiInvitedRelationVO.setBsirnBeInvitedHead(user.getCrusrHead());
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个邀请好友关系成功", busiInvitedRelationVO), response);
		logger.info("[BusiInvitedRelationController]:end viewsBusiInvitedRelation");
	}

	/**
	* 后台获取邀请好友关系列表<Page>
	* 
	* @param userName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiInvitedRelationPage (String userName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiInvitedRelationController]:begin findBusiInvitedRelationPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiInvitedRelation> page = busiInvitedRelationService.findBusiInvitedRelationForPages(userName, pageNum, pageSize);
		Page<BusiInvitedRelationVO> pageVO = new Page<BusiInvitedRelationVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiInvitedRelation busiInvitedRelation : page.getResult()) {
			hashUserUuids.add(busiInvitedRelation.getBsirnInvited());
			hashUserUuids.add(busiInvitedRelation.getBsirnBeInvited());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);

		List<BusiInvitedRelationVO> vos = new ArrayList<BusiInvitedRelationVO>();
		BusiInvitedRelationVO vo;
		for (BusiInvitedRelation busiInvitedRelation : page.getResult()) {
			vo = new BusiInvitedRelationVO();			vo.convertPOToVO(busiInvitedRelation);
			vo.setBsirnBeInvitedName(userMap.get(busiInvitedRelation.getBsirnBeInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnBeInvited()).getCrusrName());
			vo.setBsirnBeInvitedHead(userMap.get(busiInvitedRelation.getBsirnBeInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnBeInvited()).getCrusrHead());
			vo.setBsirnInvitedName(userMap.get(busiInvitedRelation.getBsirnInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnInvited()).getCrusrName());
			vo.setBsirnInvitedHead(userMap.get(busiInvitedRelation.getBsirnInvited())==null?null:userMap.get(busiInvitedRelation.getBsirnInvited()).getCrusrHead());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiInvitedRelationController]:end findBusiInvitedRelationPage");
	}

}