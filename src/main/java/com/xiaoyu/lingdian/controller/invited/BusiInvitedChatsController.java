package com.xiaoyu.lingdian.controller.invited;

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
import com.xiaoyu.lingdian.entity.BusiInvitedChats;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.invited.BusiInvitedChatsService;
import com.xiaoyu.lingdian.vo.BusiInvitedChatsVO;

@Controller
@RequestMapping(value="/busiInvitedChats")
public class BusiInvitedChatsController extends BaseController {

	/**
	* 邀请排行榜表
	*/
	@Autowired
	private BusiInvitedChatsService busiInvitedChatsService;
	
	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 添加
	*
	* @param bsicsPeriods 期数
	* @param bsicsGoldMatters 金牌事项
	* @param bsicsGoldNumber 金牌邀请人数
	* @param bsicsGoldReward 金牌奖励
	* @param bsicsGoldGainer 金牌获得者
	* @param bsicsSilverMatters 银牌事项
	* @param bsicsSilverNumber 银牌邀请人数
	* @param bsicsSilverReward 银牌奖励
	* @param bsicsSilverGainer 银牌获得者
	* @param bsicsBronzeMatters 铜牌事项
	* @param bsicsBronzeNumber 铜牌邀请人数
	* @param bsicsBronzeReward 铜牌奖励
	* @param bsicsBronzeGainer 铜牌获得者
	* @return
	*/
	@RequestMapping(value="/add/busiInvitedChats", method=RequestMethod.POST)
	public void addBusiInvitedChats (String bsicsPeriods, String bsicsGoldMatters, Integer bsicsGoldNumber, String bsicsGoldReward, String bsicsGoldGainer, String bsicsSilverMatters, Integer bsicsSilverNumber, String bsicsSilverReward, String bsicsSilverGainer, String bsicsBronzeMatters, Integer bsicsBronzeNumber, String bsicsBronzeReward, String bsicsBronzeGainer, HttpServletResponse response) {
		logger.info("[BusiInvitedChatsController]:begin addBusiInvitedChats");

		if (StringUtil.isEmpty(bsicsPeriods)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "期数不能为空!"), response);
			logger.info("[BusiInvitedChatsController]:end addBusiInvitedChats");
			return;
		}

		CoreUser coreUser = new CoreUser();
		if (!StringUtil.isEmpty(bsicsGoldGainer)) {
			coreUser = new CoreUser();
			coreUser.setCrusrUuid(bsicsGoldGainer);
			coreUser = coreUserService.getCoreUser(coreUser);
			if(null == coreUser){
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "金牌获得者不存在!"), response);
				logger.info("[BusiInvitedChatsController]:end addBusiInvitedChats");
				return;
			}
		}
		if (!StringUtil.isEmpty(bsicsSilverGainer)) {	
			coreUser = new CoreUser();
			coreUser.setCrusrUuid(bsicsSilverGainer);
			coreUser = coreUserService.getCoreUser(coreUser);
			if(null == coreUser){
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "银牌获得者不存在!"), response);
				logger.info("[BusiInvitedChatsController]:end addBusiInvitedChats");
				return;
			}
		}
		if (!StringUtil.isEmpty(bsicsBronzeGainer)) {
			coreUser = new CoreUser();
			coreUser.setCrusrUuid(bsicsBronzeGainer);
			coreUser = coreUserService.getCoreUser(coreUser);
			if(null == coreUser){
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "铜牌获得者不存在!"), response);
				logger.info("[BusiInvitedChatsController]:end addBusiInvitedChats");
				return;
			}
		}
	
		//判断期数是否已存在
		BusiInvitedChats invitedChats = busiInvitedChatsService.getBusiInvitedChatsByPeriods(bsicsPeriods);
		if (null != invitedChats) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "期数已存在!"), response);
			logger.info("[BusiInvitedChatsController]:end addBusiInvitedChats");
			return;
		}
		BusiInvitedChats busiInvitedChats = new BusiInvitedChats();
		String uuid = RandomUtil.generateString(16);
		busiInvitedChats.setBsicsUuid(uuid);
		busiInvitedChats.setBsicsPeriods(bsicsPeriods);
		busiInvitedChats.setBsicsGoldMatters(bsicsGoldMatters);
		busiInvitedChats.setBsicsGoldNumber(bsicsGoldNumber);
		busiInvitedChats.setBsicsGoldReward(bsicsGoldReward);
		busiInvitedChats.setBsicsGoldGainer(bsicsGoldGainer);
		busiInvitedChats.setBsicsSilverMatters(bsicsSilverMatters);
		busiInvitedChats.setBsicsSilverNumber(bsicsSilverNumber);
		busiInvitedChats.setBsicsSilverReward(bsicsSilverReward);
		busiInvitedChats.setBsicsSilverGainer(bsicsSilverGainer);
		busiInvitedChats.setBsicsBronzeMatters(bsicsBronzeMatters);
		busiInvitedChats.setBsicsBronzeNumber(bsicsBronzeNumber);
		busiInvitedChats.setBsicsBronzeReward(bsicsBronzeReward);
		busiInvitedChats.setBsicsBronzeGainer(bsicsBronzeGainer);
		busiInvitedChats.setBsicsStatus(0);
		
		busiInvitedChatsService.insertBusiInvitedChats(busiInvitedChats);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiInvitedChatsController]:end addBusiInvitedChats");
	}

	/**
	* 修改
	*
	* @param bsicsUuid 标识UUID
	* @param bsicsGoldMatters 金牌事项
	* @param bsicsGoldNumber 金牌邀请人数
	* @param bsicsGoldReward 金牌奖励
	* @param bsicsGoldGainer 金牌获得者
	* @param bsicsSilverMatters 银牌事项
	* @param bsicsSilverNumber 银牌邀请人数
	* @param bsicsSilverReward 银牌奖励
	* @param bsicsSilverGainer 银牌获得者
	* @param bsicsBronzeMatters 铜牌事项
	* @param bsicsBronzeNumber 铜牌邀请人数
	* @param bsicsBronzeReward 铜牌奖励
	* @param bsicsBronzeGainer 铜牌获得者
	* @return
	*/
	@RequestMapping(value="/update/busiInvitedChats", method=RequestMethod.POST)
	public void updateBusiInvitedChats (String bsicsUuid, String bsicsGoldMatters, Integer bsicsGoldNumber, String bsicsGoldReward, String bsicsGoldGainer, String bsicsSilverMatters, Integer bsicsSilverNumber, String bsicsSilverReward, String bsicsSilverGainer, String bsicsBronzeMatters, Integer bsicsBronzeNumber, String bsicsBronzeReward, String bsicsBronzeGainer, HttpServletResponse response) {
		logger.info("[BusiInvitedChatsController]:begin updateBusiInvitedChats");
		if (StringUtil.isEmpty(bsicsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiInvitedChatsController]:end updateBusiInvitedChats");
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		if (!StringUtil.isEmpty(bsicsGoldGainer)) {
			coreUser = new CoreUser();
			coreUser.setCrusrUuid(bsicsGoldGainer);
			coreUser = coreUserService.getCoreUser(coreUser);
			if(null == coreUser){
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "金牌获得者不存在!"), response);
				logger.info("[BusiInvitedChatsController]:end updateBusiInvitedChats");
				return;
			}
		}
		if (!StringUtil.isEmpty(bsicsSilverGainer)) {	
			coreUser = new CoreUser();
			coreUser.setCrusrUuid(bsicsSilverGainer);
			coreUser = coreUserService.getCoreUser(coreUser);
			if(null == coreUser){
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "银牌获得者不存在!"), response);
				logger.info("[BusiInvitedChatsController]:end updateBusiInvitedChats");
				return;
			}
		}
		if (!StringUtil.isEmpty(bsicsBronzeGainer)) {
			coreUser = new CoreUser();
			coreUser.setCrusrUuid(bsicsBronzeGainer);
			coreUser = coreUserService.getCoreUser(coreUser);
			if(null == coreUser){
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "铜牌获得者不存在!"), response);
				logger.info("[BusiInvitedChatsController]:end updateBusiInvitedChats");
				return;
			}
		}
				BusiInvitedChats busiInvitedChats = new BusiInvitedChats();
		busiInvitedChats.setBsicsUuid(bsicsUuid);
		busiInvitedChats.setBsicsGoldMatters(bsicsGoldMatters);
		busiInvitedChats.setBsicsGoldNumber(bsicsGoldNumber);
		busiInvitedChats.setBsicsGoldReward(bsicsGoldReward);
		busiInvitedChats.setBsicsGoldGainer(bsicsGoldGainer);
		busiInvitedChats.setBsicsSilverMatters(bsicsSilverMatters);
		busiInvitedChats.setBsicsSilverNumber(bsicsSilverNumber);
		busiInvitedChats.setBsicsSilverReward(bsicsSilverReward);
		busiInvitedChats.setBsicsSilverGainer(bsicsSilverGainer);
		busiInvitedChats.setBsicsBronzeMatters(bsicsBronzeMatters);
		busiInvitedChats.setBsicsBronzeNumber(bsicsBronzeNumber);
		busiInvitedChats.setBsicsBronzeReward(bsicsBronzeReward);
		busiInvitedChats.setBsicsBronzeGainer(bsicsBronzeGainer);

		busiInvitedChatsService.updateBusiInvitedChats(busiInvitedChats);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiInvitedChatsController]:end updateBusiInvitedChats");
	}

	/**
	* 启用
	*
	* @param bsicsUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable (String bsicsUuid, HttpServletResponse response) {
		logger.info("[BusiInvitedChatsController]:begin enable");

		if (StringUtil.isEmpty(bsicsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiInvitedChatsController]:end enable");
			return;
		}
		
		BusiInvitedChats busiInvitedChats = new BusiInvitedChats();
		busiInvitedChats.setBsicsUuid(bsicsUuid);
		busiInvitedChats.setBsicsStatus(1);

		busiInvitedChatsService.updateBusiInvitedChats(busiInvitedChats);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[BusiInvitedChatsController]:end enable");
	}
	
	/**
	* 禁用
	*
	* @param bsicsUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable (String bsicsUuid, HttpServletResponse response) {
		logger.info("[BusiInvitedChatsController]:begin disable");

		if (StringUtil.isEmpty(bsicsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiInvitedChatsController]:end disable");
			return;
		}
		
		BusiInvitedChats busiInvitedChats = new BusiInvitedChats();
		busiInvitedChats.setBsicsUuid(bsicsUuid);
		busiInvitedChats.setBsicsStatus(0);

		busiInvitedChatsService.updateBusiInvitedChats(busiInvitedChats);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiInvitedChatsController]:end disable");
	}
	
	/**
	* 删除
	*
	* @param bsicsUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiInvitedChats (String bsicsUuid, HttpServletResponse response) {
		logger.info("[BusiInvitedChatsController]:begin deleteBusiInvitedChats");
		if (StringUtil.isEmpty(bsicsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiInvitedChatsController]:end deleteBusiInvitedChats");
			return;
		}
		BusiInvitedChats busiInvitedChats = new BusiInvitedChats();
		busiInvitedChats.setBsicsUuid(bsicsUuid);

		busiInvitedChatsService.deleteBusiInvitedChats(busiInvitedChats);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiInvitedChatsController]:end deleteBusiInvitedChats");
	}

	/**
	* 批量删除
	*
	* @param bsicsUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiInvitedChats (String bsicsUuids, HttpServletResponse response) {
		logger.info("[BusiInvitedChatsController]:begin deleteBatchBusiInvitedChats");
		if (StringUtil.isEmpty(bsicsUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiInvitedChatsController]:end deleteBatchBusiInvitedChats");
			return;
		}
		String[] uuids=bsicsUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiInvitedChatsController]:end deleteBatchBusiInvitedChats");
			return;
		}
		busiInvitedChatsService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiInvitedChatsController]:end deleteBatchBusiInvitedChats");
	}

	/**
	* 获取单个排行榜
	*
	* @param bsicsUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiInvitedChats (String bsicsUuid, HttpServletResponse response) {
		logger.info("[BusiInvitedChatsController]:begin viewsBusiInvitedChats");
		if (StringUtil.isEmpty(bsicsUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiInvitedChatsController]:end viewsBusiInvitedChats");
			return;
		}
		BusiInvitedChats busiInvitedChats = new BusiInvitedChats();
		busiInvitedChats.setBsicsUuid(bsicsUuid);
		busiInvitedChats = busiInvitedChatsService.getBusiInvitedChats(busiInvitedChats);
		if(null == busiInvitedChats){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "排行榜不存在!"), response);
			logger.info("[BusiInvitedChatsController]:end viewsBusiInvitedChats");
			return;
		}

		HashSet<String> hashUserUuids = new HashSet<String>();
		hashUserUuids.add(busiInvitedChats.getBsicsSilverGainer());
		hashUserUuids.add(busiInvitedChats.getBsicsGoldGainer());
		hashUserUuids.add(busiInvitedChats.getBsicsBronzeGainer());
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		BusiInvitedChatsVO busiInvitedChatsVO = new BusiInvitedChatsVO();
		busiInvitedChatsVO.convertPOToVO(busiInvitedChats);
		busiInvitedChatsVO.setBsicsBronzeGainerName(userMap.get(busiInvitedChats.getBsicsBronzeGainer())==null?null:userMap.get(busiInvitedChats.getBsicsBronzeGainer()).getCrusrName());
		busiInvitedChatsVO.setBsicsBronzeGainerHead(userMap.get(busiInvitedChats.getBsicsBronzeGainer())==null?null:userMap.get(busiInvitedChats.getBsicsBronzeGainer()).getCrusrHead());
		busiInvitedChatsVO.setBsicsGoldGainerName(userMap.get(busiInvitedChats.getBsicsGoldGainer())==null?null:userMap.get(busiInvitedChats.getBsicsGoldGainer()).getCrusrName());
		busiInvitedChatsVO.setBsicsGoldGainerHead(userMap.get(busiInvitedChats.getBsicsGoldGainer())==null?null:userMap.get(busiInvitedChats.getBsicsGoldGainer()).getCrusrHead());
		busiInvitedChatsVO.setBsicsSilverGainerName(userMap.get(busiInvitedChats.getBsicsSilverGainer())==null?null:userMap.get(busiInvitedChats.getBsicsSilverGainer()).getCrusrName());
		busiInvitedChatsVO.setBsicsSilverGainerHead(userMap.get(busiInvitedChats.getBsicsSilverGainer())==null?null:userMap.get(busiInvitedChats.getBsicsSilverGainer()).getCrusrHead());
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个单个排行榜信息成功", busiInvitedChatsVO), response);
		logger.info("[BusiInvitedChatsController]:end viewsBusiInvitedChats");
	}

	/**
	* 获取最近三次排行榜期数数据<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiInvitedChatsList (HttpServletResponse response) {
		logger.info("[BusiInvitedChatsController]:begin findBusiInvitedChatsList");
		List<BusiInvitedChats> lists = busiInvitedChatsService.findBusiInvitedChatsList();
		List<BusiInvitedChatsVO> vos = new ArrayList<BusiInvitedChatsVO>();
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiInvitedChats busiInvitedChats : lists) {
			hashUserUuids.add(busiInvitedChats.getBsicsSilverGainer());
			hashUserUuids.add(busiInvitedChats.getBsicsGoldGainer());
			hashUserUuids.add(busiInvitedChats.getBsicsBronzeGainer());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		BusiInvitedChatsVO vo;
		for (BusiInvitedChats busiInvitedChats : lists) {
			vo = new BusiInvitedChatsVO();			vo.convertPOToVO(busiInvitedChats);
			vo.setBsicsBronzeGainerName(userMap.get(busiInvitedChats.getBsicsBronzeGainer())==null?null:userMap.get(busiInvitedChats.getBsicsBronzeGainer()).getCrusrName());
			vo.setBsicsBronzeGainerHead(userMap.get(busiInvitedChats.getBsicsBronzeGainer())==null?null:userMap.get(busiInvitedChats.getBsicsBronzeGainer()).getCrusrHead());
			vo.setBsicsGoldGainerName(userMap.get(busiInvitedChats.getBsicsGoldGainer())==null?null:userMap.get(busiInvitedChats.getBsicsGoldGainer()).getCrusrName());
			vo.setBsicsGoldGainerHead(userMap.get(busiInvitedChats.getBsicsGoldGainer())==null?null:userMap.get(busiInvitedChats.getBsicsGoldGainer()).getCrusrHead());
			vo.setBsicsSilverGainerName(userMap.get(busiInvitedChats.getBsicsSilverGainer())==null?null:userMap.get(busiInvitedChats.getBsicsSilverGainer()).getCrusrName());
			vo.setBsicsSilverGainerHead(userMap.get(busiInvitedChats.getBsicsSilverGainer())==null?null:userMap.get(busiInvitedChats.getBsicsSilverGainer()).getCrusrHead());			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiInvitedChatsController]:end findBusiInvitedChatsList");
	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiInvitedChatsPage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiInvitedChatsController]:begin findBusiInvitedChatsPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiInvitedChats> page = busiInvitedChatsService.findBusiInvitedChatsPage(pageNum, pageSize);
		Page<BusiInvitedChatsVO> pageVO = new Page<BusiInvitedChatsVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiInvitedChats busiInvitedChats : page.getResult()) {
			hashUserUuids.add(busiInvitedChats.getBsicsSilverGainer());
			hashUserUuids.add(busiInvitedChats.getBsicsGoldGainer());
			hashUserUuids.add(busiInvitedChats.getBsicsBronzeGainer());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiInvitedChatsVO> vos = new ArrayList<BusiInvitedChatsVO>();
		BusiInvitedChatsVO vo;
		for (BusiInvitedChats busiInvitedChats : page.getResult()) {
			vo = new BusiInvitedChatsVO();			vo.convertPOToVO(busiInvitedChats);
			vo.setBsicsBronzeGainerName(userMap.get(busiInvitedChats.getBsicsBronzeGainer())==null?null:userMap.get(busiInvitedChats.getBsicsBronzeGainer()).getCrusrName());
			vo.setBsicsBronzeGainerHead(userMap.get(busiInvitedChats.getBsicsBronzeGainer())==null?null:userMap.get(busiInvitedChats.getBsicsBronzeGainer()).getCrusrHead());
			vo.setBsicsGoldGainerName(userMap.get(busiInvitedChats.getBsicsGoldGainer())==null?null:userMap.get(busiInvitedChats.getBsicsGoldGainer()).getCrusrName());
			vo.setBsicsGoldGainerHead(userMap.get(busiInvitedChats.getBsicsGoldGainer())==null?null:userMap.get(busiInvitedChats.getBsicsGoldGainer()).getCrusrHead());			vo.setBsicsSilverGainerName(userMap.get(busiInvitedChats.getBsicsSilverGainer())==null?null:userMap.get(busiInvitedChats.getBsicsSilverGainer()).getCrusrName());
			vo.setBsicsSilverGainerHead(userMap.get(busiInvitedChats.getBsicsSilverGainer())==null?null:userMap.get(busiInvitedChats.getBsicsSilverGainer()).getCrusrHead());
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiInvitedChatsController]:end findBusiInvitedChatsPage");
	}

}