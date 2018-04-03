package com.xiaoyu.lingdian.controller.index;

import java.util.Date;
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
import com.xiaoyu.lingdian.entity.BusiHotList;
import com.xiaoyu.lingdian.entity.BusiProduct;
import com.xiaoyu.lingdian.service.index.BusiHotListService;
import com.xiaoyu.lingdian.service.product.BusiProductService;
import com.xiaoyu.lingdian.vo.BusiHotListVO;

@Controller
@RequestMapping(value="/busiHotList")
public class BusiHotListController extends BaseController {

	/**
	* 热榜表
	*/
	@Autowired
	private BusiHotListService busiHotListService;
	
	/**
	* 商品表
	*/
	@Autowired
	private BusiProductService busiProductService;
	
	/**
	* 添加(默认0禁用)
	*
	* @param bshltProduct 商品标识
	* @param bshltOrd 顺序
	* @return
	*/
	@RequestMapping(value="/add/busiHotList", method=RequestMethod.POST)
	public void addBusiHotList (String bshltProduct, Integer bshltOrd, HttpServletResponse response) {
		logger.info("[BusiHotListController]:begin addBusiHotList");

		if (StringUtil.isEmpty(bshltProduct)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品标识不能为空!"), response);
			logger.info("[BusiHotListController]:end addBusiHotList");
			return;
		}
		if (null == bshltOrd || 0 == bshltOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必填!"), response);
			logger.info("[BusiHotListController]:end addBusiHotList");
			return;
		}
		BusiHotList busiHotList = new BusiHotList();
		String uuid = RandomUtil.generateString(16);
		busiHotList.setBshltUuid(uuid);
		busiHotList.setBshltProduct(bshltProduct);
		busiHotList.setBshltOrd(bshltOrd);
		busiHotList.setBshltStatus(0);
		busiHotList.setBshltCdate(new Date());
		busiHotList.setBshltUdate(new Date());

		busiHotListService.insertBusiHotList(busiHotList);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiHotListController]:end addBusiHotList");
	}

	/**
	* 修改
	*
	* @param bshltUuid 标识UUID
	* @param bshltProduct 商品标识
	* @param bshltOrd 顺序
	* @return
	*/
	@RequestMapping(value="/update/busiHotList", method=RequestMethod.POST)
	public void updateBusiHotList (String bshltUuid, String bshltProduct, Integer bshltOrd, HttpServletResponse response) {
		logger.info("[BusiHotListController]:begin updateBusiHotList");
		if (StringUtil.isEmpty(bshltUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiHotListController]:end updateBusiHotList");
			return;
		}
		if (StringUtil.isEmpty(bshltProduct)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品标识不能为空!"), response);
			logger.info("[BusiHotListController]:end updateBusiHotList");
			return;
		}
		if (null == bshltOrd || 0 == bshltOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必填!"), response);
			logger.info("[BusiHotListController]:end updateBusiHotList");
			return;
		}
		BusiHotList busiHotList = new BusiHotList();
		busiHotList.setBshltUuid(bshltUuid);
		busiHotList.setBshltProduct(bshltProduct);
		busiHotList.setBshltOrd(bshltOrd);
		busiHotList.setBshltUdate(new Date());

		busiHotListService.updateBusiHotList(busiHotList);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiHotListController]:end updateBusiHotList");
	}

	/**
	* 启用
	*
	* @param bshltUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable (String bshltUuid, HttpServletResponse response) {
		logger.info("[BusiHotListController]:begin enable");

		if (StringUtil.isEmpty(bshltUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiHotListController]:end enable");
			return;
		}
		
		BusiHotList busiHotList = new BusiHotList();
		busiHotList.setBshltUuid(bshltUuid);
		busiHotList.setBshltStatus(1);
		busiHotList.setBshltUdate(new Date());

		busiHotListService.updateBusiHotList(busiHotList);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[BusiHotListController]:end enable");
	}
	
	/**
	* 禁用
	*
	* @param bshltUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable (String bshltUuid, HttpServletResponse response) {
		logger.info("[BusiHotListController]:begin disable");

		if (StringUtil.isEmpty(bshltUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiHotListController]:end disable");
			return;
		}
		
		BusiHotList busiHotList = new BusiHotList();
		busiHotList.setBshltUuid(bshltUuid);
		busiHotList.setBshltStatus(0);
		busiHotList.setBshltUdate(new Date());

		busiHotListService.updateBusiHotList(busiHotList);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiHotListController]:end disable");
	}
	
	/**
	* 删除
	*
	* @param bshltUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiHotList (String bshltUuid, HttpServletResponse response) {
		logger.info("[BusiHotListController]:begin deleteBusiHotList");
		if (StringUtil.isEmpty(bshltUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiHotListController]:end deleteBusiHotList");
			return;
		}
		BusiHotList busiHotList = new BusiHotList();
		busiHotList.setBshltUuid(bshltUuid);

		busiHotListService.deleteBusiHotList(busiHotList);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiHotListController]:end deleteBusiHotList");
	}

	/**
	* 批量删除
	*
	* @param bshltUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiHotList (String bshltUuids, HttpServletResponse response) {
		logger.info("[BusiHotListController]:begin deleteBatchBusiHotList");
		if (StringUtil.isEmpty(bshltUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiHotListController]:end deleteBatchBusiHotList");
			return;
		}
		String[] uuids=bshltUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiHotListController]:end deleteBatchBusiHotList");
			return;
		}
		busiHotListService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiHotListController]:end deleteBatchBusiHotList");
	}

	/**
	* 前台获取启动的所有热榜page
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd/before", method=RequestMethod.POST)
	public void findBusiHotListPageBefore (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiHotListController]:begin findBusiHotListPageBefore");

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiHotList> page = busiHotListService.findBusiHotListPageBefore(pageNum, pageSize);
		Page<BusiHotListVO> pageVO = new Page<BusiHotListVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashProductUuids = new HashSet<String>();
		for (BusiHotList busiHotList : page.getResult()) {
			hashProductUuids.add(busiHotList.getBshltProduct());
		}
		List<String> productUuids = new ArrayList<>(hashProductUuids);
		Map<String, BusiProduct> productMap = busiProductService.findBusiProductListByUuids(productUuids);
		
		List<BusiHotListVO> vos = new ArrayList<BusiHotListVO>();
		BusiHotListVO vo;
		for (BusiHotList busiHotList : page.getResult()) {
			vo = new BusiHotListVO();
			vo.convertPOToVO(busiHotList);
			if(null != productMap.get(busiHotList.getBshltProduct())) {
				vo.convertPOToVOByProduct(productMap.get(busiHotList.getBshltProduct()));
			}
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiHotListController]:end findBusiHotListPageBefore");
	}
	
	/**
	* 后台根据商品名称查询所有热榜page
	* 
	* @param productName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd/after", method=RequestMethod.POST)
	public void findBusiHotListPage (String productName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiHotListController]:begin findBusiHotListPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiHotList> page = busiHotListService.findBusiHotListPage(productName, pageNum, pageSize);
		Page<BusiHotListVO> pageVO = new Page<BusiHotListVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashProductUuids = new HashSet<String>();
		for (BusiHotList busiHotList : page.getResult()) {
			hashProductUuids.add(busiHotList.getBshltProduct());
		}
		List<String> productUuids = new ArrayList<>(hashProductUuids);
		Map<String, BusiProduct> productMap = busiProductService.findBusiProductListByUuids(productUuids);
		
		List<BusiHotListVO> vos = new ArrayList<BusiHotListVO>();
		BusiHotListVO vo;
		for (BusiHotList busiHotList : page.getResult()) {
			vo = new BusiHotListVO();			vo.convertPOToVO(busiHotList);
			if(null != productMap.get(busiHotList.getBshltProduct())) {
				vo.convertPOToVOByProduct(productMap.get(busiHotList.getBshltProduct()));
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiHotListController]:end findBusiHotListPage");
	}

}