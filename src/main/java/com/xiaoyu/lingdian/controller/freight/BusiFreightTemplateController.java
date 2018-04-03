package com.xiaoyu.lingdian.controller.freight;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
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
import com.xiaoyu.lingdian.entity.BusiFreightTemplate;
import com.xiaoyu.lingdian.service.freight.BusiFreightTemplateService;
import com.xiaoyu.lingdian.vo.BusiFreightTemplateVO;

@Controller
@RequestMapping(value="/busiFreightTemplate")
public class BusiFreightTemplateController extends BaseController {

	/**
	* 运费模板表
	*/
	@Autowired
	private BusiFreightTemplateService busiFreightTemplateService;
	
	/**
	* 添加
	*
	* @param bsfteName 模板名称
	* @param bsfteInitialWeight 初始重量
	* @param bsfteInitialPrice 初始价格
	* @param bsfteWeight 加重
	* @param bsftePrice 加价
	* @param bsfteCdate 创建时间
	* @param bsfteUdate 更新时间
	* @return
	*/
	@RequestMapping(value="/add/busiFreightTemplate", method=RequestMethod.POST)
	public void addBusiFreightTemplate (String bsfteName, Integer bsfteInitialWeight, Double bsfteInitialPrice, Integer bsfteWeight, Double bsftePrice, String bsfteCdate, String bsfteUdate, HttpServletResponse response) {
		logger.info("[BusiFreightTemplateController]:begin addBusiFreightTemplate");
		BusiFreightTemplate busiFreightTemplate = new BusiFreightTemplate();
		String uuid = RandomUtil.generateString(16);
		busiFreightTemplate.setBsfteUuid(uuid);
		busiFreightTemplate.setBsfteName(bsfteName);
		busiFreightTemplate.setBsfteInitialWeight(bsfteInitialWeight);
		busiFreightTemplate.setBsfteInitialPrice(bsfteInitialPrice);
		busiFreightTemplate.setBsfteWeight(bsfteWeight);
		busiFreightTemplate.setBsftePrice(bsftePrice);
		busiFreightTemplate.setBsfteCdate(new Date());
		busiFreightTemplate.setBsfteUdate(new Date());

		busiFreightTemplateService.insertBusiFreightTemplate(busiFreightTemplate);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiFreightTemplateController]:end addBusiFreightTemplate");

	}

	/**
	* 修改
	*
	* @param bsfteUuid 标识UUID
	* @param bsfteName 模板名称
	* @param bsfteInitialWeight 初始重量
	* @param bsfteInitialPrice 初始价格
	* @param bsfteWeight 加重
	* @param bsftePrice 加价
	* @param bsfteCdate 创建时间
	* @param bsfteUdate 更新时间
	* @return
	*/
	@RequestMapping(value="/update/busiFreightTemplate", method=RequestMethod.POST)
	public void updateBusiFreightTemplate (String bsfteUuid, String bsfteName, Integer bsfteInitialWeight, Double bsfteInitialPrice, Integer bsfteWeight, Double bsftePrice, String bsfteCdate, String bsfteUdate, HttpServletResponse response) {
		logger.info("[BusiFreightTemplateController]:begin updateBusiFreightTemplate");
		if (StringUtil.isEmpty(bsfteUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiFreightTemplate busiFreightTemplate = new BusiFreightTemplate();
		busiFreightTemplate.setBsfteUuid(bsfteUuid);
		busiFreightTemplate.setBsfteName(bsfteName);
		busiFreightTemplate.setBsfteInitialWeight(bsfteInitialWeight);
		busiFreightTemplate.setBsfteInitialPrice(bsfteInitialPrice);
		busiFreightTemplate.setBsfteWeight(bsfteWeight);
		busiFreightTemplate.setBsftePrice(bsftePrice);
		busiFreightTemplate.setBsfteCdate(new Date());
		busiFreightTemplate.setBsfteUdate(new Date());

		busiFreightTemplateService.updateBusiFreightTemplate(busiFreightTemplate);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiFreightTemplateController]:end updateBusiFreightTemplate");

	}

	/**
	* 删除
	*
	* @param bsfteUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiFreightTemplate (String bsfteUuid, HttpServletResponse response) {
		logger.info("[BusiFreightTemplateController]:begin deleteBusiFreightTemplate");
		if (StringUtil.isEmpty(bsfteUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiFreightTemplate busiFreightTemplate = new BusiFreightTemplate();
		busiFreightTemplate.setBsfteUuid(bsfteUuid);

		busiFreightTemplateService.deleteBusiFreightTemplate(busiFreightTemplate);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiFreightTemplateController]:end deleteBusiFreightTemplate");

	}

	/**
	* 批量删除
	*
	* @param bsfteUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiFreightTemplate (String bsfteUuids, HttpServletResponse response) {
		logger.info("[BusiFreightTemplateController]:begin deleteBatchBusiFreightTemplate");
		if (StringUtil.isEmpty(bsfteUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=bsfteUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		busiFreightTemplateService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiFreightTemplateController]:end deleteBatchBusiFreightTemplate");

	}

	/**
	* 获取单个
	*
	* @param bsfteUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiFreightTemplate (String bsfteUuid, HttpServletResponse response) {
		logger.info("[BusiFreightTemplateController]:begin viewsBusiFreightTemplate");
		if (StringUtil.isEmpty(bsfteUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiFreightTemplate busiFreightTemplate = new BusiFreightTemplate();
		busiFreightTemplate.setBsfteUuid(bsfteUuid);

		busiFreightTemplate = busiFreightTemplateService.getBusiFreightTemplate(busiFreightTemplate);

		BusiFreightTemplateVO busiFreightTemplateVO = new BusiFreightTemplateVO();
		busiFreightTemplateVO.convertPOToVO(busiFreightTemplate);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiFreightTemplateVO), response);
		logger.info("[BusiFreightTemplateController]:end viewsBusiFreightTemplate");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiFreightTemplateList (HttpServletResponse response) {
		logger.info("[BusiFreightTemplateController]:begin findBusiFreightTemplateList");
		List<BusiFreightTemplate> lists = busiFreightTemplateService.findBusiFreightTemplateList();
		List<BusiFreightTemplateVO> vos = new ArrayList<BusiFreightTemplateVO>();
		BusiFreightTemplateVO vo;
		for (BusiFreightTemplate busiFreightTemplate : lists) {
			vo = new BusiFreightTemplateVO();
			vo.convertPOToVO(busiFreightTemplate);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiFreightTemplateController]:end findBusiFreightTemplateList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiFreightTemplatePage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiFreightTemplateController]:begin findBusiFreightTemplatePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiFreightTemplate> page = busiFreightTemplateService.findBusiFreightTemplatePage(pageNum, pageSize);
		Page<BusiFreightTemplateVO> pageVO = new Page<BusiFreightTemplateVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiFreightTemplateVO> vos = new ArrayList<BusiFreightTemplateVO>();
		BusiFreightTemplateVO vo;
		for (BusiFreightTemplate busiFreightTemplate : page.getResult()) {
			vo = new BusiFreightTemplateVO();
			vo.convertPOToVO(busiFreightTemplate);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiFreightTemplateController]:end findBusiFreightTemplatePage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}