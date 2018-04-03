package com.xiaoyu.lingdian.controller.index;

import java.util.Date;
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
import com.xiaoyu.lingdian.entity.BusiAdvert;
import com.xiaoyu.lingdian.service.index.BusiAdvertService;
import com.xiaoyu.lingdian.vo.BusiAdvertVO;

@Controller
@RequestMapping(value="/busiAdvert")
public class BusiAdvertController extends BaseController {

	/**
	* 广告位表
	*/
	@Autowired
	private BusiAdvertService busiAdvertService;
	
	/**
	* 添加(默认为启用)
	*
	* @param bsaetLink 链接
	* @param bsaetPic 广告图
	* @param bsaetOrd 顺序
	* @param bsaetDesc 描述
	* @return
	*/
	@RequestMapping(value="/add/busiAdvert", method=RequestMethod.POST)
	public void addBusiAdvert (String bsaetLink, String bsaetPic, Integer bsaetOrd, String bsaetDesc, HttpServletResponse response) {
		logger.info("[BusiAdvertController]:begin addBusiAdvert");

		if (StringUtil.isEmpty(bsaetLink)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "链接不能为空!"), response);
			logger.info("[BusiProductCategoryController]:end addBusiAdvert");
			return;
		}
		if (null == bsaetOrd || 0 == bsaetOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必传!"), response);
			logger.info("[BusiAdvertController]:end addBusiAdvert");
			return;
		}		BusiAdvert busiAdvert = new BusiAdvert();
		String uuid = RandomUtil.generateString(16);
		busiAdvert.setBsaetUuid(uuid);
		busiAdvert.setBsaetLink(bsaetLink);
		busiAdvert.setBsaetPic(bsaetPic);
		busiAdvert.setBsaetOrd(bsaetOrd);
		busiAdvert.setBsaetStatus(1);
		busiAdvert.setBsaetDesc(bsaetDesc);
		busiAdvert.setBsaetCdate(new Date());
		busiAdvert.setBsaetUdate(new Date());

		busiAdvertService.insertBusiAdvert(busiAdvert);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiAdvertController]:end addBusiAdvert");
	}

	/**
	* 修改
	*
	* @param bsaetUuid 标识UUID
	* @param bsaetLink 链接
	* @param bsaetPic 广告图
	* @param bsaetOrd 顺序
	* @param bsaetDesc 描述
	* @return
	*/
	@RequestMapping(value="/update/busiAdvert", method=RequestMethod.POST)
	public void updateBusiAdvert (String bsaetUuid, String bsaetLink, String bsaetPic, Integer bsaetOrd, String bsaetDesc, HttpServletResponse response) {
		logger.info("[BusiAdvertController]:begin updateBusiAdvert");
		if (StringUtil.isEmpty(bsaetUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiAdvertController]:end updateBusiAdvert");
			return;
		}
		if (StringUtil.isEmpty(bsaetLink)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "链接不能为空!"), response);
			logger.info("[BusiProductCategoryController]:end updateBusiAdvert");
			return;
		}
		if (null == bsaetOrd || 0 == bsaetOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必传!"), response);
			logger.info("[BusiAdvertController]:end updateBusiAdvert");
			return;
		}
		BusiAdvert busiAdvert = new BusiAdvert();
		busiAdvert.setBsaetUuid(bsaetUuid);
		busiAdvert.setBsaetLink(bsaetLink);
		busiAdvert.setBsaetPic(bsaetPic);
		busiAdvert.setBsaetOrd(bsaetOrd);
		busiAdvert.setBsaetDesc(bsaetDesc);
		busiAdvert.setBsaetUdate(new Date());

		busiAdvertService.updateBusiAdvert(busiAdvert);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiAdvertController]:end updateBusiAdvert");
	}

	/**
	* 启用
	*
	* @param bsaetUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable (String bsaetUuid, HttpServletResponse response) {
		logger.info("[BusiAdvertController]:begin enable");

		if (StringUtil.isEmpty(bsaetUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiAdvertController]:end enable");
			return;
		}
		
		BusiAdvert busiAdvert = new BusiAdvert();
		busiAdvert.setBsaetUuid(bsaetUuid);
		busiAdvert.setBsaetStatus(1);
		busiAdvert.setBsaetUdate(new Date());

		busiAdvertService.updateBusiAdvert(busiAdvert);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[BusiAdvertController]:end enable");
	}
	
	/**
	* 禁用
	*
	* @param bsaetUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable (String bsaetUuid, HttpServletResponse response) {
		logger.info("[BusiAdvertController]:begin disable");

		if (StringUtil.isEmpty(bsaetUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiAdvertController]:end enable");
			return;
		}
		
		BusiAdvert busiAdvert = new BusiAdvert();
		busiAdvert.setBsaetUuid(bsaetUuid);
		busiAdvert.setBsaetStatus(0);
		busiAdvert.setBsaetUdate(new Date());

		busiAdvertService.updateBusiAdvert(busiAdvert);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiAdvertController]:end disable");
	}
	
	/**
	* 删除
	*
	* @param bsaetUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiAdvert (String bsaetUuid, HttpServletResponse response) {
		logger.info("[BusiAdvertController]:begin deleteBusiAdvert");
		if (StringUtil.isEmpty(bsaetUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiAdvertController]:end deleteBusiAdvert");
			return;
		}
		BusiAdvert busiAdvert = new BusiAdvert();
		busiAdvert.setBsaetUuid(bsaetUuid);

		busiAdvertService.deleteBusiAdvert(busiAdvert);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiAdvertController]:end deleteBusiAdvert");
	}

	/**
	* 批量删除
	*
	* @param bsaetUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiAdvert (String bsaetUuids, HttpServletResponse response) {
		logger.info("[BusiAdvertController]:begin deleteBatchBusiAdvert");
		if (StringUtil.isEmpty(bsaetUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiAdvertController]:end deleteBatchBusiAdvert");
			return;
		}
		String[] uuids=bsaetUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiAdvertController]:end deleteBatchBusiAdvert");
			return;
		}
		busiAdvertService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiAdvertController]:end deleteBatchBusiAdvert");
	}

	/**
	* 获取单个广告图
	*
	* @param bsaetUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiAdvert (String bsaetUuid, HttpServletResponse response) {
		logger.info("[BusiAdvertController]:begin viewsBusiAdvert");
		if (StringUtil.isEmpty(bsaetUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiAdvertController]:end viewsBusiAdvert");
			return;
		}
		BusiAdvert busiAdvert = new BusiAdvert();
		busiAdvert.setBsaetUuid(bsaetUuid);

		busiAdvert = busiAdvertService.getBusiAdvert(busiAdvert);

		BusiAdvertVO busiAdvertVO = new BusiAdvertVO();
		busiAdvertVO.convertPOToVO(busiAdvert);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiAdvertVO), response);
		logger.info("[BusiAdvertController]:end viewsBusiAdvert");
	}

	/**
	* 获取所有启用的广告图列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiAdvertList (HttpServletResponse response) {
		logger.info("[BusiAdvertController]:begin findBusiAdvertList");
		List<BusiAdvert> lists = busiAdvertService.findBusiAdvertList();
		List<BusiAdvertVO> vos = new ArrayList<BusiAdvertVO>();
		BusiAdvertVO vo;
		for (BusiAdvert busiAdvert : lists) {
			vo = new BusiAdvertVO();			vo.convertPOToVO(busiAdvert);			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiAdvertController]:end findBusiAdvertList");
	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiAdvertPage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiAdvertController]:begin findBusiAdvertPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiAdvert> page = busiAdvertService.findBusiAdvertPage(pageNum, pageSize);
		Page<BusiAdvertVO> pageVO = new Page<BusiAdvertVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiAdvertVO> vos = new ArrayList<BusiAdvertVO>();
		BusiAdvertVO vo;
		for (BusiAdvert busiAdvert : page.getResult()) {
			vo = new BusiAdvertVO();			vo.convertPOToVO(busiAdvert);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiAdvertController]:end findBusiAdvertPage");
	}

}