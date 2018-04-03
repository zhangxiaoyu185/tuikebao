package com.xiaoyu.lingdian.controller.invited;

import java.util.List;
import java.util.ArrayList;
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
import com.xiaoyu.lingdian.entity.BusiInvitedRule;
import com.xiaoyu.lingdian.service.invited.BusiInvitedRuleService;
import com.xiaoyu.lingdian.vo.BusiInvitedRuleVO;

@Controller
@RequestMapping(value="/busiInvitedRule")
public class BusiInvitedRuleController extends BaseController {

	/**
	* 邀请奖励规则表
	*/
	@Autowired
	private BusiInvitedRuleService busiInvitedRuleService;
	
	/**
	* 根据用户活动邀请人数查询符合最近的领取规则 
	* @param userInvitedNused
	* @return
	*/
	@RequestMapping(value="/find/view/with/cnd", method=RequestMethod.POST)
	public void findBusiInvitedRuleViewWithCnd (Integer userInvitedNused, HttpServletResponse response) {
		logger.info("[BusiInvitedRuleController]:begin findBusiInvitedRuleViewWithCnd");
		
		if (null == userInvitedNused) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "活动邀请好友数必传!"), response);
			logger.info("[BusiInvitedRuleController]:end findBusiInvitedRuleViewWithCnd");
			return;
		}
		BusiInvitedRule busiInvitedRule = busiInvitedRuleService.findBusiInvitedRuleViewWithCnd(userInvitedNused);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取成功!", busiInvitedRule),response);
		logger.info("[BusiInvitedRuleController]:end findBusiInvitedRuleViewWithCnd");
	}
	
	/**
	* 添加
	*
	* @param bsireCount 需要邀请人数
	* @param bsireIntegral 可领取积分
	* @param bsireOrd 顺序
	* @return
	*/
	@RequestMapping(value="/add/busiInvitedRule", method=RequestMethod.POST)
	public void addBusiInvitedRule (Integer bsireCount, Integer bsireIntegral, Integer bsireOrd, HttpServletResponse response) {
		logger.info("[BusiInvitedRuleController]:begin addBusiInvitedRule");
		if (null == bsireCount || 0 == bsireCount) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "需要邀请人数必填!"), response);
			logger.info("[BusiInvitedRuleController]:end addBusiInvitedRule");
			return;
		}
		if (null == bsireIntegral || 0 == bsireIntegral) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "可领取积分必填!"), response);
			logger.info("[BusiInvitedRuleController]:end addBusiInvitedRule");
			return;
		}
		if (null == bsireOrd || 0 == bsireOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必填!"), response);
			logger.info("[BusiInvitedRuleController]:end addBusiInvitedRule");
			return;
		}
		BusiInvitedRule busiInvitedRule = new BusiInvitedRule();
		String uuid = RandomUtil.generateString(16);
		busiInvitedRule.setBsireUuid(uuid);
		busiInvitedRule.setBsireCount(bsireCount);
		busiInvitedRule.setBsireIntegral(bsireIntegral);
		busiInvitedRule.setBsireOrd(bsireOrd);

		busiInvitedRuleService.insertBusiInvitedRule(busiInvitedRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiInvitedRuleController]:end addBusiInvitedRule");
	}

	/**
	* 修改
	*
	* @param bsireUuid 标识UUID
	* @param bsireCount 需要邀请人数
	* @param bsireIntegral 可领取积分
	* @param bsireOrd 顺序
	* @return
	*/
	@RequestMapping(value="/update/busiInvitedRule", method=RequestMethod.POST)
	public void updateBusiInvitedRule (String bsireUuid, Integer bsireCount, Integer bsireIntegral, Integer bsireOrd, HttpServletResponse response) {
		logger.info("[BusiInvitedRuleController]:begin updateBusiInvitedRule");
		if (StringUtil.isEmpty(bsireUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiInvitedRuleController]:end updateBusiInvitedRule");
			return;
		}
		if (null == bsireCount || 0 == bsireCount) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "需要邀请人数必填!"), response);
			logger.info("[BusiInvitedRuleController]:end updateBusiInvitedRule");
			return;
		}
		if (null == bsireIntegral || 0 == bsireIntegral) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "可领取积分必填!"), response);
			logger.info("[BusiInvitedRuleController]:end updateBusiInvitedRule");
			return;
		}
		if (null == bsireOrd || 0 == bsireOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必填!"), response);
			logger.info("[BusiInvitedRuleController]:end updateBusiInvitedRule");
			return;
		}
		BusiInvitedRule busiInvitedRule = new BusiInvitedRule();
		busiInvitedRule.setBsireUuid(bsireUuid);
		busiInvitedRule.setBsireCount(bsireCount);
		busiInvitedRule.setBsireIntegral(bsireIntegral);
		busiInvitedRule.setBsireOrd(bsireOrd);

		busiInvitedRuleService.updateBusiInvitedRule(busiInvitedRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiInvitedRuleController]:end updateBusiInvitedRule");
	}

	/**
	* 删除
	*
	* @param bsireUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiInvitedRule (String bsireUuid, HttpServletResponse response) {
		logger.info("[BusiInvitedRuleController]:begin deleteBusiInvitedRule");
		if (StringUtil.isEmpty(bsireUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiInvitedRuleController]:end deleteBusiInvitedRule");
			return;
		}
		BusiInvitedRule busiInvitedRule = new BusiInvitedRule();
		busiInvitedRule.setBsireUuid(bsireUuid);

		busiInvitedRuleService.deleteBusiInvitedRule(busiInvitedRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiInvitedRuleController]:end deleteBusiInvitedRule");
	}

	/**
	* 批量删除
	*
	* @param bsireUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiInvitedRule (String bsireUuids, HttpServletResponse response) {
		logger.info("[BusiInvitedRuleController]:begin deleteBatchBusiInvitedRule");
		if (StringUtil.isEmpty(bsireUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiInvitedRuleController]:end deleteBatchBusiInvitedRule");
			return;
		}
		String[] uuids=bsireUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiInvitedRuleController]:end deleteBatchBusiInvitedRule");
			return;
		}
		busiInvitedRuleService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiInvitedRuleController]:end deleteBatchBusiInvitedRule");
	}

	/**
	* 获取单个邀请好友规格
	*
	* @param bsireUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiInvitedRule (String bsireUuid, HttpServletResponse response) {
		logger.info("[BusiInvitedRuleController]:begin viewsBusiInvitedRule");
		if (StringUtil.isEmpty(bsireUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiInvitedRuleController]:begin viewsBusiInvitedRule");
			return;
		}
		BusiInvitedRule busiInvitedRule = new BusiInvitedRule();
		busiInvitedRule.setBsireUuid(bsireUuid);

		busiInvitedRule = busiInvitedRuleService.getBusiInvitedRule(busiInvitedRule);

		BusiInvitedRuleVO busiInvitedRuleVO = new BusiInvitedRuleVO();
		busiInvitedRuleVO.convertPOToVO(busiInvitedRule);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiInvitedRuleVO), response);
		logger.info("[BusiInvitedRuleController]:end viewsBusiInvitedRule");
	}

	/**
	* 获取所有的邀请好友规则list
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiInvitedRuleList (HttpServletResponse response) {
		logger.info("[BusiInvitedRuleController]:begin findBusiInvitedRuleList");
		
		List<BusiInvitedRule> lists = busiInvitedRuleService.findBusiInvitedRuleList();
		List<BusiInvitedRuleVO> vos = new ArrayList<BusiInvitedRuleVO>();
		BusiInvitedRuleVO vo;
		for (BusiInvitedRule busiInvitedRule : lists) {
			vo = new BusiInvitedRuleVO();
			vo.convertPOToVO(busiInvitedRule);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiInvitedRuleController]:end findBusiInvitedRuleList");
	}

	/**
	* 获取邀请好友规格<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiInvitedRulePage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiInvitedRuleController]:begin findBusiInvitedRulePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiInvitedRule> page = busiInvitedRuleService.findBusiInvitedRulePage(pageNum, pageSize);
		Page<BusiInvitedRuleVO> pageVO = new Page<BusiInvitedRuleVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiInvitedRuleVO> vos = new ArrayList<BusiInvitedRuleVO>();
		BusiInvitedRuleVO vo;
		for (BusiInvitedRule busiInvitedRule : page.getResult()) {
			vo = new BusiInvitedRuleVO();			vo.convertPOToVO(busiInvitedRule);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiInvitedRuleController]:end findBusiInvitedRulePage");

	}

}