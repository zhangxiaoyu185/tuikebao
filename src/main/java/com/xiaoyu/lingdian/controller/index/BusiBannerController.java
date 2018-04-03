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
import com.xiaoyu.lingdian.entity.BusiBanner;
import com.xiaoyu.lingdian.service.index.BusiBannerService;
import com.xiaoyu.lingdian.vo.BusiBannerVO;

@Controller
@RequestMapping(value="/busiBanner")
public class BusiBannerController extends BaseController {

	/**
	* 导航商品配置表
	*/
	@Autowired
	private BusiBannerService busiBannerService;
	
	/**
	* 添加(默认为启用)
	*
	* @param bsbarLink 链接
	* @param bsbarPic BANNER图
	* @param bsbarOrd 顺序
	* @param bsbarDesc 描述
	* @param bsbarShow 显示端:1分享端2商城端3都显示
	* @return
	*/
	@RequestMapping(value="/add/busiBanner", method=RequestMethod.POST)
	public void addBusiBanner (String bsbarLink, String bsbarPic, Integer bsbarOrd, String bsbarDesc, Integer bsbarShow, HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin addBusiBanner");

		if (StringUtil.isEmpty(bsbarLink)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "链接不能为空!"), response);
			logger.info("[BusiBannerController]:end addBusiBanner");
			return;
		}
		if (null == bsbarOrd || 0 == bsbarOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必传!"), response);
			logger.info("[BusiBannerController]:end addBusiBanner");
			return;
		}
		if (null == bsbarShow || bsbarShow == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择显示端!"), response);
			logger.info("[BusiBannerController]:end addBusiBanner");
			return;
		}
		BusiBanner busiBanner = new BusiBanner();
		String uuid = RandomUtil.generateString(16);
		busiBanner.setBsbarUuid(uuid);
		busiBanner.setBsbarLink(bsbarLink);
		busiBanner.setBsbarPic(bsbarPic);
		busiBanner.setBsbarOrd(bsbarOrd);
		busiBanner.setBsbarStatus(1);
		busiBanner.setBsbarDesc(bsbarDesc);
		busiBanner.setBsbarShow(bsbarShow);
		busiBanner.setBsbarCdate(new Date());
		busiBanner.setBsbarUdate(new Date());

		busiBannerService.insertBusiBanner(busiBanner);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiBannerController]:end addBusiBanner");
	}

	/**
	* 修改
	*
	* @param bsbarUuid 标识UUID
	* @param bsbarLink 链接
	* @param bsbarPic BANNER图
	* @param bsbarOrd 顺序
	* @param bsbarDesc 描述
	* @param bsbarShow 显示端:1分享端2商城端3都显示
	* @return
	*/
	@RequestMapping(value="/update/busiBanner", method=RequestMethod.POST)
	public void updateBusiBanner (String bsbarUuid, String bsbarLink, String bsbarPic, Integer bsbarOrd, String bsbarDesc, Integer bsbarShow, HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin updateBusiBanner");
		if (StringUtil.isEmpty(bsbarUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiBannerController]:end updateBusiBanner");
			return;
		}
		if (StringUtil.isEmpty(bsbarLink)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "链接不能为空!"), response);
			logger.info("[BusiBannerController]:end updateBusiBanner");
			return;
		}
		if (null == bsbarOrd || 0 == bsbarOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必传!"), response);
			logger.info("[BusiBannerController]:end updateBusiBanner");
			return;
		}
		if (null == bsbarShow || bsbarShow == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择显示端!"), response);
			logger.info("[BusiBannerController]:end addBusiBanner");
			return;
		}
		BusiBanner busiBanner = new BusiBanner();
		busiBanner.setBsbarUuid(bsbarUuid);
		busiBanner.setBsbarLink(bsbarLink);
		busiBanner.setBsbarPic(bsbarPic);
		busiBanner.setBsbarOrd(bsbarOrd);
		busiBanner.setBsbarDesc(bsbarDesc);
		busiBanner.setBsbarShow(bsbarShow);
		busiBanner.setBsbarUdate(new Date());

		busiBannerService.updateBusiBanner(busiBanner);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiBannerController]:end updateBusiBanner");
	}

	/**
	* 启用
	*
	* @param bsbarUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable (String bsbarUuid, HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin enable");

		if (StringUtil.isEmpty(bsbarUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiBannerController]:end enable");
			return;
		}
		
		BusiBanner busiBanner = new BusiBanner();
		busiBanner.setBsbarUuid(bsbarUuid);
		busiBanner.setBsbarStatus(1);
		busiBanner.setBsbarUdate(new Date());

		busiBannerService.updateBusiBanner(busiBanner);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[BusiBannerController]:end enable");
	}
	
	/**
	* 禁用
	*
	* @param bsbarUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable (String bsbarUuid, HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin disable");

		if (StringUtil.isEmpty(bsbarUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiBannerController]:end enable");
			return;
		}
		
		BusiBanner busiBanner = new BusiBanner();
		busiBanner.setBsbarUuid(bsbarUuid);
		busiBanner.setBsbarStatus(0);
		busiBanner.setBsbarUdate(new Date());

		busiBannerService.updateBusiBanner(busiBanner);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiBannerController]:end disable");
	}

	/**
	* 删除
	*
	* @param bsbarUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiBanner (String bsbarUuid, HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin deleteBusiBanner");
		if (StringUtil.isEmpty(bsbarUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiBannerController]:end deleteBusiBanner");
			return;
		}
		BusiBanner busiBanner = new BusiBanner();
		busiBanner.setBsbarUuid(bsbarUuid);

		busiBannerService.deleteBusiBanner(busiBanner);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiBannerController]:end deleteBusiBanner");
	}

	/**
	* 批量删除
	*
	* @param bsbarUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiBanner (String bsbarUuids, HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin deleteBatchBusiBanner");
		if (StringUtil.isEmpty(bsbarUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiBannerController]:end deleteBatchBusiBanner");
			return;
		}
		String[] uuids=bsbarUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiBannerController]:end deleteBatchBusiBanner");
			return;
		}
		busiBannerService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiBannerController]:end deleteBatchBusiBanner");
	}

	/**
	* 获取单个banner
	*
	* @param bsbarUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiBanner (String bsbarUuid, HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin viewsBusiBanner");
		if (StringUtil.isEmpty(bsbarUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiBannerController]:end viewsBusiBanner");
			return;
		}
		BusiBanner busiBanner = new BusiBanner();
		busiBanner.setBsbarUuid(bsbarUuid);

		busiBanner = busiBannerService.getBusiBanner(busiBanner);

		BusiBannerVO busiBannerVO = new BusiBannerVO();
		busiBannerVO.convertPOToVO(busiBanner);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiBannerVO), response);
		logger.info("[BusiBannerController]:end viewsBusiBanner");
	}

	/**
	* 获取商城端启用的banner图list
	* 
	* @return
	*/
	@RequestMapping(value="/find/all/by/store", method=RequestMethod.POST)
	public void findBusiBannerForListsByStore (HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin findBusiBannerForListsByStore");
		List<BusiBanner> lists = busiBannerService.findBusiBannerForListsByStore();
		List<BusiBannerVO> vos = new ArrayList<BusiBannerVO>();
		BusiBannerVO vo;
		for (BusiBanner busiBanner : lists) {
			vo = new BusiBannerVO();			vo.convertPOToVO(busiBanner);			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiBannerController]:end findBusiBannerForListsByStore");
	}

	/**
	* 获取分享端启用的banner图list
	* 
	* @return
	*/
	@RequestMapping(value="/find/all/by/share", method=RequestMethod.POST)
	public void findBusiBannerForListsByShare (HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin findBusiBannerForListsByShare");

		List<BusiBanner> lists = busiBannerService.findBusiBannerForListsByShare();
		List<BusiBannerVO> vos = new ArrayList<BusiBannerVO>();
		BusiBannerVO vo;
		for (BusiBanner busiBanner : lists) {
			vo = new BusiBannerVO();
			vo.convertPOToVO(busiBanner);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiBannerController]:end findBusiBannerForListsByShare");
	}
	
	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiBannerPage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiBannerController]:begin findBusiBannerPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiBanner> page = busiBannerService.findBusiBannerPage(pageNum, pageSize);
		Page<BusiBannerVO> pageVO = new Page<BusiBannerVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiBannerVO> vos = new ArrayList<BusiBannerVO>();
		BusiBannerVO vo;
		for (BusiBanner busiBanner : page.getResult()) {
			vo = new BusiBannerVO();			vo.convertPOToVO(busiBanner);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiBannerController]:end findBusiBannerPage");
	}

}