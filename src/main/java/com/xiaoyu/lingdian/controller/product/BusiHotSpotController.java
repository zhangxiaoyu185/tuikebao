package com.xiaoyu.lingdian.controller.product;

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
import com.xiaoyu.lingdian.entity.BusiHotSpot;
import com.xiaoyu.lingdian.service.product.BusiHotSpotService;
import com.xiaoyu.lingdian.vo.BusiHotSpotVO;

@Controller
@RequestMapping(value="/busiHotSpot")
public class BusiHotSpotController extends BaseController {

	/**
	* 热点配置表
	*/
	@Autowired
	private BusiHotSpotService busiHotSpotService;
	
	/**
	* 添加
	*
	* @param bshstName 热点名称
	* @param bshstIcon 热点图片
	* @return
	*/
	@RequestMapping(value="/add/busiHotSpot", method=RequestMethod.POST)
	public void addBusiHotSpot (String bshstName, String bshstIcon, HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin addBusiHotSpot");

		if (StringUtil.isEmpty(bshstName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "热点名称不能为空!"), response);
			logger.info("[BusiHotSpotController]:end addBusiHotSpot");
			return;
		}
		if (StringUtil.isEmpty(bshstIcon)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "热点图片不能为空!"), response);
			logger.info("[BusiHotSpotController]:end addBusiHotSpot");
			return;
		}
				BusiHotSpot busiHotSpot = new BusiHotSpot();
		String uuid = RandomUtil.generateString(16);
		busiHotSpot.setBshstUuid(uuid);
		busiHotSpot.setBshstName(bshstName);
		busiHotSpot.setBshstIcon(bshstIcon);
		busiHotSpot.setBshstCdate(new Date());
		busiHotSpot.setBshstUdate(new Date());

		busiHotSpotService.insertBusiHotSpot(busiHotSpot);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiHotSpotController]:end addBusiHotSpot");
	}

	/**
	* 修改
	*
	* @param bshstUuid 标识UUID
	* @param bshstName 热点名称
	* @param bshstIcon 热点图片
	* @return
	*/
	@RequestMapping(value="/update/busiHotSpot", method=RequestMethod.POST)
	public void updateBusiHotSpot (String bshstUuid, String bshstName, String bshstIcon, HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin updateBusiHotSpot");
		if (StringUtil.isEmpty(bshstUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiHotSpotController]:end updateBusiHotSpot");
			return;
		}
		if (StringUtil.isEmpty(bshstName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "热点名称不能为空!"), response);
			logger.info("[BusiHotSpotController]:end updateBusiHotSpot");
			return;
		}
		if (StringUtil.isEmpty(bshstIcon)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "热点图片不能为空!"), response);
			logger.info("[BusiHotSpotController]:end updateBusiHotSpot");
			return;
		}
		BusiHotSpot busiHotSpot = new BusiHotSpot();
		busiHotSpot.setBshstUuid(bshstUuid);
		busiHotSpot.setBshstName(bshstName);
		busiHotSpot.setBshstIcon(bshstIcon);
		busiHotSpot.setBshstUdate(new Date());

		busiHotSpotService.updateBusiHotSpot(busiHotSpot);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiHotSpotController]:end updateBusiHotSpot");
	}

	/**
	* 删除
	*
	* @param bshstUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiHotSpot (String bshstUuid, HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin deleteBusiHotSpot");
		if (StringUtil.isEmpty(bshstUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiHotSpotController]:end deleteBusiHotSpot");
			return;
		}
		BusiHotSpot busiHotSpot = new BusiHotSpot();
		busiHotSpot.setBshstUuid(bshstUuid);

		busiHotSpotService.deleteBusiHotSpot(busiHotSpot);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiHotSpotController]:end deleteBusiHotSpot");
	}

	/**
	* 批量删除
	*
	* @param bshstUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiHotSpot (String bshstUuids, HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin deleteBatchBusiHotSpot");
		if (StringUtil.isEmpty(bshstUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiHotSpotController]:end deleteBatchBusiHotSpot");
			return;
		}
		String[] uuids=bshstUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiHotSpotController]:end deleteBatchBusiHotSpot");
			return;
		}
		busiHotSpotService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiHotSpotController]:end deleteBatchBusiHotSpot");
	}

	/**
	* 获取单个
	*
	* @param bshstUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiHotSpot (String bshstUuid, HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin viewsBusiHotSpot");
		if (StringUtil.isEmpty(bshstUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiHotSpotController]:end viewsBusiHotSpot");
			return;
		}
		BusiHotSpot busiHotSpot = new BusiHotSpot();
		busiHotSpot.setBshstUuid(bshstUuid);

		busiHotSpot = busiHotSpotService.getBusiHotSpot(busiHotSpot);

		BusiHotSpotVO busiHotSpotVO = new BusiHotSpotVO();
		busiHotSpotVO.convertPOToVO(busiHotSpot);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiHotSpotVO), response);
		logger.info("[BusiHotSpotController]:end viewsBusiHotSpot");
	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiHotSpotList (HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin findBusiHotSpotList");
		List<BusiHotSpot> lists = busiHotSpotService.findBusiHotSpotList();
		List<BusiHotSpotVO> vos = new ArrayList<BusiHotSpotVO>();
		BusiHotSpotVO vo;
		for (BusiHotSpot busiHotSpot : lists) {
			vo = new BusiHotSpotVO();			vo.convertPOToVO(busiHotSpot);			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiHotSpotController]:end findBusiHotSpotList");
	}

	/**
	* 获取列表<Page>
	* 
	* @param bshstName 热点名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiHotSpotPage (String bshstName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiHotSpotController]:begin findBusiHotSpotPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiHotSpot> page = busiHotSpotService.findBusiHotSpotPage(bshstName, pageNum, pageSize);
		Page<BusiHotSpotVO> pageVO = new Page<BusiHotSpotVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiHotSpotVO> vos = new ArrayList<BusiHotSpotVO>();
		BusiHotSpotVO vo;
		for (BusiHotSpot busiHotSpot : page.getResult()) {
			vo = new BusiHotSpotVO();			vo.convertPOToVO(busiHotSpot);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiHotSpotController]:end findBusiHotSpotPage");
	}

}