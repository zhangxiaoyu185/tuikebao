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
import com.xiaoyu.lingdian.entity.BusiExchangeRule;
import com.xiaoyu.lingdian.service.my.BusiExchangeRuleService;
import com.xiaoyu.lingdian.vo.BusiExchangeRuleVO;

@Controller
@RequestMapping(value="/busiExchangeRule")
public class BusiExchangeRuleController extends BaseController {

	/**
	* 积分兑换话费设置表
	*/
	@Autowired
	private BusiExchangeRuleService busiExchangeRuleService;

	/**
	* 添加
	*
	* @param bseeeBill 充值话费
	* @param bseeeIntegral 所需积分
	* @param bseeeOrd 显示顺序
	* @return
	*/
	@RequestMapping(value="/add/busiExchangeRule", method=RequestMethod.POST)
	public void addBusiExchangeRule (Double bseeeBill, Integer bseeeIntegral, Integer bseeeOrd, HttpServletResponse response) {
		logger.info("[BusiExchangeRuleController]:begin addBusiExchangeRule");

		if (null == bseeeBill || 0.0 == bseeeBill) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "充值话费不能为空!"), response);
			logger.info("[BusiExchangeRuleController]:end addBusiExchangeRule");
			return;
		}
		if (null == bseeeIntegral || 0 == bseeeIntegral) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所需积分不能为空!"), response);
			logger.info("[BusiExchangeRuleController]:end addBusiExchangeRule");
			return;
		}
		if (null == bseeeOrd || 0 == bseeeOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "显示顺序必填!"), response);
			logger.info("[BusiExchangeRuleController]:end addBusiExchangeRule");
			return;
		}		BusiExchangeRule busiExchangeRule = new BusiExchangeRule();
		String uuid = RandomUtil.generateString(16);
		busiExchangeRule.setBseeeUuid(uuid);
		busiExchangeRule.setBseeeBill(bseeeBill);
		busiExchangeRule.setBseeeIntegral(bseeeIntegral);
		busiExchangeRule.setBseeeOrd(bseeeOrd);

		busiExchangeRuleService.insertBusiExchangeRule(busiExchangeRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiExchangeRuleController]:end addBusiExchangeRule");

	}

	/**
	* 修改
	*
	* @param bseeeUuid 标识UUID
	* @param bseeeBill 充值话费
	* @param bseeeIntegral 所需积分
	* @param bseeeOrd 显示顺序
	* @return
	*/
	@RequestMapping(value="/update/busiExchangeRule", method=RequestMethod.POST)
	public void updateBusiExchangeRule (String bseeeUuid, Double bseeeBill, Integer bseeeIntegral, Integer bseeeOrd, HttpServletResponse response) {
		logger.info("[BusiExchangeRuleController]:begin updateBusiExchangeRule");
		if (StringUtil.isEmpty(bseeeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExchangeRuleController]:end updateBusiExchangeRule");
			return;
		}
		if (null == bseeeBill || 0.0 == bseeeBill) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "充值话费不能为空!"), response);
			logger.info("[BusiExchangeRuleController]:end updateBusiExchangeRule");
			return;
		}
		if (null == bseeeIntegral || 0 == bseeeIntegral) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所需积分不能为空!"), response);
			logger.info("[BusiExchangeRuleController]:end updateBusiExchangeRule");
			return;
		}
		if (null == bseeeOrd || 0 == bseeeOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "显示顺序必填!"), response);
			logger.info("[BusiExchangeRuleController]:end updateBusiExchangeRule");
			return;
		}
		BusiExchangeRule busiExchangeRule = new BusiExchangeRule();
		busiExchangeRule.setBseeeUuid(bseeeUuid);
		busiExchangeRule.setBseeeBill(bseeeBill);
		busiExchangeRule.setBseeeIntegral(bseeeIntegral);
		busiExchangeRule.setBseeeOrd(bseeeOrd);

		busiExchangeRuleService.updateBusiExchangeRule(busiExchangeRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiExchangeRuleController]:end updateBusiExchangeRule");

	}

	/**
	* 删除
	*
	* @param bseeeUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiExchangeRule (String bseeeUuid, HttpServletResponse response) {
		logger.info("[BusiExchangeRuleController]:begin deleteBusiExchangeRule");
		if (StringUtil.isEmpty(bseeeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExchangeRuleController]:end deleteBusiExchangeRule");
			return;
		}
		BusiExchangeRule busiExchangeRule = new BusiExchangeRule();
		busiExchangeRule.setBseeeUuid(bseeeUuid);

		busiExchangeRuleService.deleteBusiExchangeRule(busiExchangeRule);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiExchangeRuleController]:end deleteBusiExchangeRule");

	}

	/**
	* 批量删除
	*
	* @param bseeeUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiExchangeRule (String bseeeUuids, HttpServletResponse response) {
		logger.info("[BusiExchangeRuleController]:begin deleteBatchBusiExchangeRule");
		if (StringUtil.isEmpty(bseeeUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiExchangeRuleController]:end deleteBatchBusiExchangeRule");
			return;
		}
		String[] uuids=bseeeUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiExchangeRuleController]:end deleteBatchBusiExchangeRule");
			return;
		}
		busiExchangeRuleService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiExchangeRuleController]:end deleteBatchBusiExchangeRule");

	}

	/**
	* 获取单个
	*
	* @param bseeeUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiExchangeRule (String bseeeUuid, HttpServletResponse response) {
		logger.info("[BusiExchangeRuleController]:begin viewsBusiExchangeRule");
		if (StringUtil.isEmpty(bseeeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExchangeRuleController]:end viewsBusiExchangeRule");
			return;
		}
		BusiExchangeRule busiExchangeRule = new BusiExchangeRule();
		busiExchangeRule.setBseeeUuid(bseeeUuid);

		busiExchangeRule = busiExchangeRuleService.getBusiExchangeRule(busiExchangeRule);

		BusiExchangeRuleVO busiExchangeRuleVO = new BusiExchangeRuleVO();
		busiExchangeRuleVO.convertPOToVO(busiExchangeRule);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiExchangeRuleVO), response);
		logger.info("[BusiExchangeRuleController]:end viewsBusiExchangeRule");

	}

	/**
	* 前端获取积分列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiExchangeRuleList (HttpServletResponse response) {
		logger.info("[BusiExchangeRuleController]:begin findBusiExchangeRuleList");
		List<BusiExchangeRule> lists = busiExchangeRuleService.findBusiExchangeRuleList();
		List<BusiExchangeRuleVO> vos = new ArrayList<BusiExchangeRuleVO>();
		BusiExchangeRuleVO vo;
		for (BusiExchangeRule busiExchangeRule : lists) {
			vo = new BusiExchangeRuleVO();			vo.convertPOToVO(busiExchangeRule);			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiExchangeRuleController]:end findBusiExchangeRuleList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiExchangeRulePage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiExchangeRuleController]:begin findBusiExchangeRulePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiExchangeRule> page = busiExchangeRuleService.findBusiExchangeRulePage(pageNum, pageSize);
		Page<BusiExchangeRuleVO> pageVO = new Page<BusiExchangeRuleVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiExchangeRuleVO> vos = new ArrayList<BusiExchangeRuleVO>();
		BusiExchangeRuleVO vo;
		for (BusiExchangeRule busiExchangeRule : page.getResult()) {
			vo = new BusiExchangeRuleVO();			vo.convertPOToVO(busiExchangeRule);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiExchangeRuleController]:end findBusiExchangeRulePage");
	}

}