package com.xiaoyu.lingdian.controller.product;

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
import com.xiaoyu.lingdian.entity.BusiIndexProduct;
import com.xiaoyu.lingdian.entity.BusiProduct;
import com.xiaoyu.lingdian.service.product.BusiIndexProductService;
import com.xiaoyu.lingdian.service.product.BusiProductService;
import com.xiaoyu.lingdian.vo.BusiIndexProductVO;

@Controller
@RequestMapping(value="/busiIndexProduct")
public class BusiIndexProductController extends BaseController {

	/**
	* 首页商品表
	*/
	@Autowired
	private BusiIndexProductService busiIndexProductService;

	/**
	* 商品表
	*/
	@Autowired
	private BusiProductService busiProductService;
	
	/**
	* 添加(默认0禁用)
	*
	* @param bsiptProduct 商品标识
	* @param bsiptOrd 顺序
	* @return
	*/
	@RequestMapping(value="/add/busiIndexProduct", method=RequestMethod.POST)
	public void addBusiIndexProduct (String bsiptProduct, Integer bsiptOrd, HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin addBusiIndexProduct");

		if (StringUtil.isEmpty(bsiptProduct)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品标识不能为空!"), response);
			logger.info("[BusiIndexProductController]:end addBusiIndexProduct");
			return;
		}
		if (null == bsiptOrd || 0 == bsiptOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必填!"), response);
			logger.info("[BusiIndexProductController]:end addBusiIndexProduct");
			return;
		}
				BusiIndexProduct busiIndexProduct = new BusiIndexProduct();
		String uuid = RandomUtil.generateString(16);
		busiIndexProduct.setBsiptUuid(uuid);
		busiIndexProduct.setBsiptProduct(bsiptProduct);
		busiIndexProduct.setBsiptOrd(bsiptOrd);
		busiIndexProduct.setBsiptStatus(0);
		busiIndexProduct.setBsiptCdate(new Date());
		busiIndexProduct.setBsiptUdate(new Date());

		busiIndexProductService.insertBusiIndexProduct(busiIndexProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiIndexProductController]:end addBusiIndexProduct");
	}

	/**
	* 修改
	*
	* @param bsiptUuid 标识UUID
	* @param bsiptProduct 商品标识
	* @param bsiptOrd 顺序
	* @return
	*/
	@RequestMapping(value="/update/busiIndexProduct", method=RequestMethod.POST)
	public void updateBusiIndexProduct (String bsiptUuid, String bsiptProduct, Integer bsiptOrd, HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin updateBusiIndexProduct");
		if (StringUtil.isEmpty(bsiptUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiIndexProductController]:end updateBusiIndexProduct");
			return;
		}
		if (StringUtil.isEmpty(bsiptProduct)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品标识不能为空!"), response);
			logger.info("[BusiIndexProductController]:end updateBusiIndexProduct");
			return;
		}
		if (null == bsiptOrd || 0 == bsiptOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必填!"), response);
			logger.info("[BusiIndexProductController]:end updateBusiIndexProduct");
			return;
		}
		BusiIndexProduct busiIndexProduct = new BusiIndexProduct();
		busiIndexProduct.setBsiptUuid(bsiptUuid);
		busiIndexProduct.setBsiptProduct(bsiptProduct);
		busiIndexProduct.setBsiptOrd(bsiptOrd);
		busiIndexProduct.setBsiptUdate(new Date());

		busiIndexProductService.updateBusiIndexProduct(busiIndexProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiIndexProductController]:end updateBusiIndexProduct");
	}

	/**
	* 启用
	*
	* @param bsiptUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable (String bsiptUuid, HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin enable");

		if (StringUtil.isEmpty(bsiptUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiIndexProductController]:end enable");
			return;
		}
		
		BusiIndexProduct busiIndexProduct = new BusiIndexProduct();
		busiIndexProduct.setBsiptUuid(bsiptUuid);
		busiIndexProduct.setBsiptStatus(1);
		busiIndexProduct.setBsiptUdate(new Date());

		busiIndexProductService.updateBusiIndexProduct(busiIndexProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[BusiIndexProductController]:end enable");
	}

	/**
	* 禁用
	*
	* @param bsiptUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable (String bsiptUuid, HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin disable");

		if (StringUtil.isEmpty(bsiptUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiIndexProductController]:end disable");
			return;
		}
		
		BusiIndexProduct busiIndexProduct = new BusiIndexProduct();
		busiIndexProduct.setBsiptUuid(bsiptUuid);
		busiIndexProduct.setBsiptStatus(0);
		busiIndexProduct.setBsiptUdate(new Date());

		busiIndexProductService.updateBusiIndexProduct(busiIndexProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiIndexProductController]:end disable");
	}

	/**
	* 删除
	*
	* @param bsiptUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiIndexProduct (String bsiptUuid, HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin deleteBusiIndexProduct");
		if (StringUtil.isEmpty(bsiptUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiIndexProductController]:end deleteBusiIndexProduct");
			return;
		}
		BusiIndexProduct busiIndexProduct = new BusiIndexProduct();
		busiIndexProduct.setBsiptUuid(bsiptUuid);

		busiIndexProductService.deleteBusiIndexProduct(busiIndexProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiIndexProductController]:end deleteBusiIndexProduct");
	}

	/**
	* 批量删除
	*
	* @param bsiptUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiIndexProduct (String bsiptUuids, HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin deleteBatchBusiIndexProduct");
		if (StringUtil.isEmpty(bsiptUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiIndexProductController]:end deleteBatchBusiIndexProduct");
			return;
		}
		String[] uuids=bsiptUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiIndexProductController]:end deleteBatchBusiIndexProduct");
			return;
		}
		busiIndexProductService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiIndexProductController]:end deleteBatchBusiIndexProduct");
	}

	/**
	* 前台获取启动的所有热榜page
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd/before", method=RequestMethod.POST)
	public void findBusiIndexProductPageBefore (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin findBusiIndexProductPageBefore");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiIndexProduct> page = busiIndexProductService.findBusiIndexProductPageBefore(pageNum, pageSize);
		Page<BusiIndexProductVO> pageVO = new Page<BusiIndexProductVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashProductUuids = new HashSet<String>();
		for (BusiIndexProduct busiIndexProduct : page.getResult()) {
			hashProductUuids.add(busiIndexProduct.getBsiptProduct());
		}
		List<String> productUuids = new ArrayList<>(hashProductUuids);
		Map<String, BusiProduct> productMap = busiProductService.findBusiProductListByUuids(productUuids);
		
		List<BusiIndexProductVO> vos = new ArrayList<BusiIndexProductVO>();
		BusiIndexProductVO vo;
		for (BusiIndexProduct busiIndexProduct : page.getResult()) {
			vo = new BusiIndexProductVO();			vo.convertPOToVO(busiIndexProduct);
			if(null != productMap.get(busiIndexProduct.getBsiptProduct())) {
				vo.convertPOToVOByProduct(productMap.get(busiIndexProduct.getBsiptProduct()));
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiIndexProductController]:end findBusiIndexProductPageBefore");
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
	public void findBusiIndexProductPage (String productName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiIndexProductController]:begin findBusiIndexProductPage");

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiIndexProduct> page = busiIndexProductService.findBusiIndexProductForPages(productName, pageNum, pageSize);
		Page<BusiIndexProductVO> pageVO = new Page<BusiIndexProductVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashProductUuids = new HashSet<String>();
		for (BusiIndexProduct busiIndexProduct : page.getResult()) {
			hashProductUuids.add(busiIndexProduct.getBsiptProduct());
		}
		List<String> productUuids = new ArrayList<>(hashProductUuids);
		Map<String, BusiProduct> productMap = busiProductService.findBusiProductListByUuids(productUuids);
		
		List<BusiIndexProductVO> vos = new ArrayList<BusiIndexProductVO>();
		BusiIndexProductVO vo;
		for (BusiIndexProduct busiIndexProduct : page.getResult()) {
			vo = new BusiIndexProductVO();
			vo.convertPOToVO(busiIndexProduct);
			if(null != productMap.get(busiIndexProduct.getBsiptProduct())) {
				vo.convertPOToVOByProduct(productMap.get(busiIndexProduct.getBsiptProduct()));
			}
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiIndexProductController]:end findBusiIndexProductPage");
	}

}