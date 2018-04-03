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
import com.xiaoyu.lingdian.entity.BusiFreightExtra;
import com.xiaoyu.lingdian.service.freight.BusiFreightExtraService;
import com.xiaoyu.lingdian.vo.BusiFreightExtraVO;

@Controller
@RequestMapping(value="/busiFreightExtra")
public class BusiFreightExtraController extends BaseController {

	/**
	* 特殊地区运费模板表
	*/
	@Autowired
	private BusiFreightExtraService busiFreightExtraService;
	
	/**
	* 添加
	*
	* @param bsfeaFreightTemplate 所属运费模板
	* @param bsfeaRegion 特殊地区
	* @param bsfeaInitialPrice 初始价格
	* @param bsfeaStepPrice 加价
	* @param bsfeaCdate 创建时间
	* @param bsfeaUdate 更新时间
	* @return
	*/
	@RequestMapping(value="/add/busiFreightExtra", method=RequestMethod.POST)
	public void addBusiFreightExtra (String bsfeaFreightTemplate, String bsfeaRegion, Double bsfeaInitialPrice, Double bsfeaStepPrice, String bsfeaCdate, String bsfeaUdate, HttpServletResponse response) {
		logger.info("[BusiFreightExtraController]:begin addBusiFreightExtra");
		BusiFreightExtra busiFreightExtra = new BusiFreightExtra();
		String uuid = RandomUtil.generateString(16);
		busiFreightExtra.setBsfeaUuid(uuid);
		busiFreightExtra.setBsfeaFreightTemplate(bsfeaFreightTemplate);
		busiFreightExtra.setBsfeaRegion(bsfeaRegion);
		busiFreightExtra.setBsfeaInitialPrice(bsfeaInitialPrice);
		busiFreightExtra.setBsfeaStepPrice(bsfeaStepPrice);
		busiFreightExtra.setBsfeaCdate(new Date());
		busiFreightExtra.setBsfeaUdate(new Date());

		busiFreightExtraService.insertBusiFreightExtra(busiFreightExtra);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiFreightExtraController]:end addBusiFreightExtra");

	}

	/**
	* 修改
	*
	* @param bsfeaUuid 标识UUID
	* @param bsfeaFreightTemplate 所属运费模板
	* @param bsfeaRegion 特殊地区
	* @param bsfeaInitialPrice 初始价格
	* @param bsfeaStepPrice 加价
	* @param bsfeaCdate 创建时间
	* @param bsfeaUdate 更新时间
	* @return
	*/
	@RequestMapping(value="/update/busiFreightExtra", method=RequestMethod.POST)
	public void updateBusiFreightExtra (String bsfeaUuid, String bsfeaFreightTemplate, String bsfeaRegion, Double bsfeaInitialPrice, Double bsfeaStepPrice, String bsfeaCdate, String bsfeaUdate, HttpServletResponse response) {
		logger.info("[BusiFreightExtraController]:begin updateBusiFreightExtra");
		if (StringUtil.isEmpty(bsfeaUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiFreightExtra busiFreightExtra = new BusiFreightExtra();
		busiFreightExtra.setBsfeaUuid(bsfeaUuid);
		busiFreightExtra.setBsfeaFreightTemplate(bsfeaFreightTemplate);
		busiFreightExtra.setBsfeaRegion(bsfeaRegion);
		busiFreightExtra.setBsfeaInitialPrice(bsfeaInitialPrice);
		busiFreightExtra.setBsfeaStepPrice(bsfeaStepPrice);
		busiFreightExtra.setBsfeaCdate(new Date());
		busiFreightExtra.setBsfeaUdate(new Date());

		busiFreightExtraService.updateBusiFreightExtra(busiFreightExtra);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiFreightExtraController]:end updateBusiFreightExtra");

	}

	/**
	* 删除
	*
	* @param bsfeaUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiFreightExtra (String bsfeaUuid, HttpServletResponse response) {
		logger.info("[BusiFreightExtraController]:begin deleteBusiFreightExtra");
		if (StringUtil.isEmpty(bsfeaUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiFreightExtra busiFreightExtra = new BusiFreightExtra();
		busiFreightExtra.setBsfeaUuid(bsfeaUuid);

		busiFreightExtraService.deleteBusiFreightExtra(busiFreightExtra);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiFreightExtraController]:end deleteBusiFreightExtra");

	}

	/**
	* 批量删除
	*
	* @param bsfeaUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiFreightExtra (String bsfeaUuids, HttpServletResponse response) {
		logger.info("[BusiFreightExtraController]:begin deleteBatchBusiFreightExtra");
		if (StringUtil.isEmpty(bsfeaUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=bsfeaUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		busiFreightExtraService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiFreightExtraController]:end deleteBatchBusiFreightExtra");

	}

	/**
	* 获取单个
	*
	* @param bsfeaUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiFreightExtra (String bsfeaUuid, HttpServletResponse response) {
		logger.info("[BusiFreightExtraController]:begin viewsBusiFreightExtra");
		if (StringUtil.isEmpty(bsfeaUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiFreightExtra busiFreightExtra = new BusiFreightExtra();
		busiFreightExtra.setBsfeaUuid(bsfeaUuid);

		busiFreightExtra = busiFreightExtraService.getBusiFreightExtra(busiFreightExtra);

		BusiFreightExtraVO busiFreightExtraVO = new BusiFreightExtraVO();
		busiFreightExtraVO.convertPOToVO(busiFreightExtra);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiFreightExtraVO), response);
		logger.info("[BusiFreightExtraController]:end viewsBusiFreightExtra");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiFreightExtraList (HttpServletResponse response) {
		logger.info("[BusiFreightExtraController]:begin findBusiFreightExtraList");
		List<BusiFreightExtra> lists = busiFreightExtraService.findBusiFreightExtraList();
		List<BusiFreightExtraVO> vos = new ArrayList<BusiFreightExtraVO>();
		BusiFreightExtraVO vo;
		for (BusiFreightExtra busiFreightExtra : lists) {
			vo = new BusiFreightExtraVO();
			vo.convertPOToVO(busiFreightExtra);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiFreightExtraController]:end findBusiFreightExtraList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiFreightExtraPage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiFreightExtraController]:begin findBusiFreightExtraPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiFreightExtra> page = busiFreightExtraService.findBusiFreightExtraPage(pageNum, pageSize);
		Page<BusiFreightExtraVO> pageVO = new Page<BusiFreightExtraVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiFreightExtraVO> vos = new ArrayList<BusiFreightExtraVO>();
		BusiFreightExtraVO vo;
		for (BusiFreightExtra busiFreightExtra : page.getResult()) {
			vo = new BusiFreightExtraVO();
			vo.convertPOToVO(busiFreightExtra);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiFreightExtraController]:end findBusiFreightExtraPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}