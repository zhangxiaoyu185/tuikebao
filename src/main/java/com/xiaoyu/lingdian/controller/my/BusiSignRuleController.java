package com.xiaoyu.lingdian.controller.my;

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
import com.xiaoyu.lingdian.entity.BusiSignRule;
import com.xiaoyu.lingdian.service.my.BusiSignRuleService;
import com.xiaoyu.lingdian.vo.BusiSignRuleVO;

@Controller
@RequestMapping(value="/busiSignRule")
public class BusiSignRuleController extends BaseController {

	/**
	* 签到设置规则表
	*/
	@Autowired
	private BusiSignRuleService busiSignRuleService;
	
	/**
	* 添加
	*
	* @param bssreCount 连续天数
	* @param bssreIntegral 可领取积分
	* @param bssreOrd 顺序
	* @return
	*/
	@RequestMapping(value="/add/busiSignRule", method=RequestMethod.POST)
	public void addBusiSignRule (Integer bssreCount, Integer bssreIntegral, Integer bssreOrd, HttpServletResponse response) {
		logger.info("[BusiSignRuleController]:begin addBusiSignRule");
		if (null == bssreCount || 0 == bssreCount) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "连续天数必填!"), response);
			logger.info("[BusiSignRuleController]:end addBusiSignRule");
			return;
		}
		if (null == bssreIntegral || 0 == bssreIntegral) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "可领取积分必填!"), response);
			logger.info("[BusiSignRuleController]:end addBusiSignRule");
			return;
		}
		if (null == bssreOrd || 0 == bssreOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必填!"), response);
			logger.info("[BusiSignRuleController]:end addBusiSignRule");
			return;
		}		BusiSignRule busiSignRule = new BusiSignRule();
		String uuid = RandomUtil.generateString(16);
		busiSignRule.setBssreUuid(uuid);
		busiSignRule.setBssreCount(bssreCount);
		busiSignRule.setBssreIntegral(bssreIntegral);
		busiSignRule.setBssreOrd(bssreOrd);

		busiSignRuleService.insertBusiSignRule(busiSignRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiSignRuleController]:end addBusiSignRule");
	}

	/**
	* 修改
	*
	* @param bssreUuid 标识UUID
	* @param bssreCount 连续天数
	* @param bssreIntegral 可领取积分
	* @param bssreOrd 顺序
	* @return
	*/
	@RequestMapping(value="/update/busiSignRule", method=RequestMethod.POST)
	public void updateBusiSignRule (String bssreUuid, Integer bssreCount, Integer bssreIntegral, Integer bssreOrd, HttpServletResponse response) {
		logger.info("[BusiSignRuleController]:begin updateBusiSignRule");
		if (StringUtil.isEmpty(bssreUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiSignRuleController]:end updateBusiSignRule");
			return;
		}
		if (null == bssreCount || 0 == bssreCount) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "连续天数必填!"), response);
			logger.info("[BusiSignRuleController]:end updateBusiSignRule");
			return;
		}
		if (null == bssreIntegral || 0 == bssreIntegral) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "可领取积分必填!"), response);
			logger.info("[BusiSignRuleController]:end updateBusiSignRule");
			return;
		}
		if (null == bssreOrd || 0 == bssreOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必填!"), response);
			logger.info("[BusiSignRuleController]:end updateBusiSignRule");
			return;
		}
		BusiSignRule busiSignRule = new BusiSignRule();
		busiSignRule.setBssreUuid(bssreUuid);
		busiSignRule.setBssreCount(bssreCount);
		busiSignRule.setBssreIntegral(bssreIntegral);
		busiSignRule.setBssreOrd(bssreOrd);
		busiSignRuleService.updateBusiSignRule(busiSignRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiSignRuleController]:end updateBusiSignRule");
	}

	/**
	* 删除
	*
	* @param bssreUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiSignRule (String bssreUuid, HttpServletResponse response) {
		logger.info("[BusiSignRuleController]:begin deleteBusiSignRule");
		if (StringUtil.isEmpty(bssreUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiSignRuleController]:end deleteBusiSignRule");
			return;
		}
		BusiSignRule busiSignRule = new BusiSignRule();
		busiSignRule.setBssreUuid(bssreUuid);

		busiSignRuleService.deleteBusiSignRule(busiSignRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiSignRuleController]:end deleteBusiSignRule");
	}

	/**
	* 批量删除
	*
	* @param bssreUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiSignRule (String bssreUuids, HttpServletResponse response) {
		logger.info("[BusiSignRuleController]:begin deleteBatchBusiSignRule");
		if (StringUtil.isEmpty(bssreUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiSignRuleController]:end deleteBatchBusiSignRule");
			return;
		}
		String[] uuids=bssreUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiSignRuleController]:end deleteBatchBusiSignRule");
			return;
		}
		busiSignRuleService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiSignRuleController]:end deleteBatchBusiSignRule");
	}

	/**
	* 获取单个签到设置
	*
	* @param bssreUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiSignRule (String bssreUuid, HttpServletResponse response) {
		logger.info("[BusiSignRuleController]:begin viewsBusiSignRule");
		if (StringUtil.isEmpty(bssreUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiSignRuleController]:end viewsBusiSignRule");
			return;
		}
		BusiSignRule busiSignRule = new BusiSignRule();
		busiSignRule.setBssreUuid(bssreUuid);

		busiSignRule = busiSignRuleService.getBusiSignRule(busiSignRule);

		BusiSignRuleVO busiSignRuleVO = new BusiSignRuleVO();
		busiSignRuleVO.convertPOToVO(busiSignRule);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个签到设置信息成功", busiSignRuleVO), response);
		logger.info("[BusiSignRuleController]:end viewsBusiSignRule");
	}

	/**
	* 获取所有的签到设置<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiSignRuleList (HttpServletResponse response) {
		logger.info("[BusiSignRuleController]:begin findBusiSignRuleList");
		List<BusiSignRule> lists = busiSignRuleService.findBusiSignRuleList();
		List<BusiSignRuleVO> vos = new ArrayList<BusiSignRuleVO>();
		BusiSignRuleVO vo;
		for (BusiSignRule busiSignRule : lists) {
			vo = new BusiSignRuleVO();			vo.convertPOToVO(busiSignRule);			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiSignRuleController]:end findBusiSignRuleList");
	}

	/**
	* 获取签到设置<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiSignRulePage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiSignRuleController]:begin findBusiSignRulePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiSignRule> page = busiSignRuleService.findBusiSignRulePage(pageNum, pageSize);
		Page<BusiSignRuleVO> pageVO = new Page<BusiSignRuleVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiSignRuleVO> vos = new ArrayList<BusiSignRuleVO>();
		BusiSignRuleVO vo;
		for (BusiSignRule busiSignRule : page.getResult()) {
			vo = new BusiSignRuleVO();			vo.convertPOToVO(busiSignRule);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiSignRuleController]:end findBusiSignRulePage");
	}

}