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
import com.xiaoyu.lingdian.entity.BusiProductCategory;
import com.xiaoyu.lingdian.service.product.BusiProductCategoryService;
import com.xiaoyu.lingdian.vo.BusiProductCategoryVO;

@Controller
@RequestMapping(value="/busiProductCategory")
public class BusiProductCategoryController extends BaseController {

	/**
	* 分类表
	*/
	@Autowired
	private BusiProductCategoryService busiProductCategoryService;
	
	/**
	* 添加(默认启动)，暂时只做两级
	*
	* @param bspcyName 分类名称
	* @param bspcyIcon 分类图标
	* @param bspcyOrd 顺序
	* @param bspcyTop 上级分类
	* @param bspcyChildNode 是否有子节点:1有0无
	* @return
	*/
	@RequestMapping(value="/add/busiProductCategory", method=RequestMethod.POST)
	public void addBusiProductCategory (String bspcyName, String bspcyIcon, Integer bspcyOrd, String bspcyTop, Integer bspcyChildNode, HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin addBusiProductCategory");

		if (StringUtil.isEmpty(bspcyName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分类名称不能为空!"), response);
			logger.info("[BusiProductCategoryController]:end addBusiProductCategory");
			return;
		}
		if (null == bspcyOrd || 0 == bspcyOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必传!"), response);
			logger.info("[BusiProductCategoryController]:end addBusiProductCategory");
			return;
		}
		if (null == bspcyChildNode) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "是否有子节点必传!"), response);
			logger.info("[BusiProductCategoryController]:end addBusiProductCategory");
			return;
		}		BusiProductCategory busiProductCategory = new BusiProductCategory();
		String uuid = RandomUtil.generateString(16);
		busiProductCategory.setBspcyUuid(uuid);
		busiProductCategory.setBspcyName(bspcyName);
		busiProductCategory.setBspcyIcon(bspcyIcon);
		busiProductCategory.setBspcyOrd(bspcyOrd);
		busiProductCategory.setBspcyStatus(1);
		busiProductCategory.setBspcyTop(bspcyTop);
		busiProductCategory.setBspcyChildNode(bspcyChildNode);
		busiProductCategory.setBspcyCdate(new Date());
		busiProductCategory.setBspcyUdate(new Date());

		busiProductCategoryService.insertBusiProductCategory(busiProductCategory);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiProductCategoryController]:end addBusiProductCategory");
	}

	/**
	* 修改
	*
	* @param bspcyUuid 标识UUID
	* @param bspcyName 分类名称
	* @param bspcyIcon 分类图标
	* @param bspcyOrd 顺序
	* @param bspcyTop 上级分类
	* @param bspcyChildNode 是否有子节点:1有0无
	* @return
	*/
	@RequestMapping(value="/update/busiProductCategory", method=RequestMethod.POST)
	public void updateBusiProductCategory (String bspcyUuid, String bspcyName, String bspcyIcon, Integer bspcyOrd, String bspcyTop, Integer bspcyChildNode, HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin updateBusiProductCategory");
		if (StringUtil.isEmpty(bspcyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProductCategoryController]:end updateBusiProductCategory");
			return;
		}
		if (StringUtil.isEmpty(bspcyName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分类名称不能为空!"), response);
			logger.info("[BusiProductCategoryController]:end updateBusiProductCategory");
			return;
		}
		if (null == bspcyOrd || 0 == bspcyOrd) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "顺序必传!"), response);
			logger.info("[BusiProductCategoryController]:end updateBusiProductCategory");
			return;
		}
		if (null == bspcyChildNode) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "是否有子节点必传!"), response);
			logger.info("[BusiProductCategoryController]:end updateBusiProductCategory");
			return;
		}
		BusiProductCategory busiProductCategory = new BusiProductCategory();
		busiProductCategory.setBspcyUuid(bspcyUuid);
		busiProductCategory.setBspcyName(bspcyName);
		busiProductCategory.setBspcyIcon(bspcyIcon);
		busiProductCategory.setBspcyOrd(bspcyOrd);
		busiProductCategory.setBspcyTop(bspcyTop);
		busiProductCategory.setBspcyChildNode(bspcyChildNode);
		busiProductCategory.setBspcyUdate(new Date());

		busiProductCategoryService.updateBusiProductCategory(busiProductCategory);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiProductCategoryController]:end updateBusiProductCategory");
	}

	/**
	* 启用
	*
	* @param bspcyUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable (String bspcyUuid, HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin enable");

		if (StringUtil.isEmpty(bspcyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProductCategoryController]:end enable");
			return;
		}
		
		BusiProductCategory busiProductCategory = new BusiProductCategory();
		busiProductCategory.setBspcyUuid(bspcyUuid);
		busiProductCategory.setBspcyStatus(1);
		busiProductCategory.setBspcyUdate(new Date());

		busiProductCategoryService.updateBusiProductCategory(busiProductCategory);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[BusiProductCategoryController]:end enable");
	}
	
	/**
	* 禁用,父结点禁用,子节点也禁用
	*
	* @param bspcyUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable (String bspcyUuid, HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin disable");

		if (StringUtil.isEmpty(bspcyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProductCategoryController]:end enable");
			return;
		}
		
		BusiProductCategory busiProductCategory = new BusiProductCategory();
		busiProductCategory.setBspcyUuid(bspcyUuid);
		busiProductCategory.setBspcyStatus(0);
		busiProductCategory.setBspcyUdate(new Date());
		busiProductCategoryService.updateBusiProductCategory(busiProductCategory);

		//子节点也禁用
		busiProductCategoryService.updateBusiProductCategoryDisableByTop(bspcyUuid);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiProductCategoryController]:end disable");
	}
	
	/**
	* 删除
	*
	* @param bspcyUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiProductCategory (String bspcyUuid, HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin deleteBusiProductCategory");
		if (StringUtil.isEmpty(bspcyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProductCategoryController]:end deleteBusiProductCategory");
			return;
		}
		BusiProductCategory busiProductCategory = new BusiProductCategory();
		busiProductCategory.setBspcyUuid(bspcyUuid);
		busiProductCategory.setBspcyStatus(2);
		busiProductCategory.setBspcyUdate(new Date());
		busiProductCategoryService.updateBusiProductCategory(busiProductCategory);

		//子节点也删除
		busiProductCategoryService.updatBatchBusiProductCategoryDeteleByTop(bspcyUuid);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiProductCategoryController]:end deleteBusiProductCategory");
	}

	/**
	* 批量删除
	*
	* @param bspcyUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiProductCategory (String bspcyUuids, HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin deleteBatchBusiProductCategory");
		if (StringUtil.isEmpty(bspcyUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiProductCategoryController]:end deleteBatchBusiProductCategory");
			return;
		}
		String[] uuids=bspcyUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			busiProductCategoryService.updateBusiProductCategoryDisableByTop(uuids[i]);
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiProductCategoryController]:end deleteBatchBusiProductCategory");
			return;
		}
		busiProductCategoryService.updatBatchBusiProductCategoryDeteleByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiProductCategoryController]:end deleteBatchBusiProductCategory");
	}

	/**
	* 获取单个商品分类
	*
	* @param bspcyUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiProductCategory (String bspcyUuid, HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin viewsBusiProductCategory");
		if (StringUtil.isEmpty(bspcyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProductCategoryController]:end viewsBusiProductCategory");
			return;
		}
		BusiProductCategory busiProductCategory = new BusiProductCategory();
		busiProductCategory.setBspcyUuid(bspcyUuid);
		busiProductCategory = busiProductCategoryService.getBusiProductCategory(busiProductCategory);
		if (null == busiProductCategory) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品分类不存在!"), response);
			logger.info("[BusiProductCategoryController]:end viewsBusiProductCategory");
			return;
		}

		BusiProductCategoryVO busiProductCategoryVO = new BusiProductCategoryVO();
		busiProductCategoryVO.convertPOToVO(busiProductCategory);
		
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
			hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
		}	
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);
		
		if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
			busiProductCategoryVO.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiProductCategoryVO), response);
		logger.info("[BusiProductCategoryController]:end viewsBusiProductCategory");
	}

	/**
	* 获取所有有子节点的启用分类list
	* 
	* @return
	*/
	@RequestMapping(value="/find/have/child", method=RequestMethod.POST)
	public void findBusiProductCategoryForListsHaveChild (HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin findBusiProductCategoryForListsHaveChild");

		List<BusiProductCategory> lists = busiProductCategoryService.findBusiProductCategoryForListsHaveChild();
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		for (BusiProductCategory busiProductCategory : lists) {
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
			}	
		}
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);
	
		List<BusiProductCategoryVO> vos = new ArrayList<BusiProductCategoryVO>();
		BusiProductCategoryVO vo;
		for (BusiProductCategory busiProductCategory : lists) {
			vo = new BusiProductCategoryVO();
			vo.convertPOToVO(busiProductCategory);
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				vo.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
			}
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiProductCategoryController]:end findBusiProductCategoryForListsHaveChild");
	}
	
	/**
	* 根据父结点获取启用的分类子节点列表list
	* 
	* @param bspcyTop
	* @return
	*/
	@RequestMapping(value="/find/all/child/by/top", method=RequestMethod.POST)
	public void findBusiProductCategoryForListsByTop (String bspcyTop, HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin findBusiProductCategoryForListsByTop");
		if (StringUtil.isEmpty(bspcyTop)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "父结点不能为空!"), response);
			logger.info("[BusiProductCategoryController]:end findBusiProductCategoryForListsByTop");
			return;
		}

		List<BusiProductCategory> lists = busiProductCategoryService.findBusiProductCategoryForListsByTop(bspcyTop);
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		for (BusiProductCategory busiProductCategory : lists) {
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
			}	
		}
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);
	
		List<BusiProductCategoryVO> vos = new ArrayList<BusiProductCategoryVO>();
		BusiProductCategoryVO vo;
		for (BusiProductCategory busiProductCategory : lists) {
			vo = new BusiProductCategoryVO();
			vo.convertPOToVO(busiProductCategory);
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				vo.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
			}
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiProductCategoryController]:end findBusiProductCategoryForListsByTop");
	}

	/**
	* 商品用获取所有启用的子分类列表list
	* 
	* @return
	*/
	@RequestMapping(value="/find/all/child/by/product", method=RequestMethod.POST)
	public void findBusiProductCategoryForListsByChild (HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin findBusiProductCategoryForListsByChild");

		List<BusiProductCategory> lists = busiProductCategoryService.findBusiProductCategoryForListsByChild();
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		for (BusiProductCategory busiProductCategory : lists) {
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
			}	
		}
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);
	
		List<BusiProductCategoryVO> vos = new ArrayList<BusiProductCategoryVO>();
		BusiProductCategoryVO vo;
		for (BusiProductCategory busiProductCategory : lists) {
			vo = new BusiProductCategoryVO();
			vo.convertPOToVO(busiProductCategory);
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				vo.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
			}
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiProductCategoryController]:end findBusiProductCategoryForListsByChild");
	}

	/**
	* 商品用获取所有启用的分类列表list,返回变成树
	* 
	* @return
	*/
	@RequestMapping(value="/find/all/by/product", method=RequestMethod.POST)
	public void findBusiProductCategoryForListsAll (HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin findBusiProductCategoryForListsAll");
		List<BusiProductCategory> lists = busiProductCategoryService.findBusiProductCategoryForListsAll();
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		for (BusiProductCategory busiProductCategory : lists) {
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
			}	
		}
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);
	
		List<BusiProductCategoryVO> vos = new ArrayList<BusiProductCategoryVO>();
		BusiProductCategoryVO vo;
		for (BusiProductCategory busiProductCategory : lists) {
			vo = new BusiProductCategoryVO();			vo.convertPOToVO(busiProductCategory);
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				vo.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
			}			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiProductCategoryController]:end findBusiProductCategoryForListsAll");
	}

	/**
	* 后台根据分类名称获取所有分类page
	* 
	* @param bspcyTop
	* @param bspcyName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiProductCategoryPage (String bspcyTop, String bspcyName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiProductCategoryController]:begin findBusiProductCategoryPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiProductCategory> page = busiProductCategoryService.findBusiProductCategoryPage(bspcyTop, bspcyName, pageNum, pageSize);
		Page<BusiProductCategoryVO> pageVO = new Page<BusiProductCategoryVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashTopCategoryUuids = new HashSet<String>();
		for (BusiProductCategory busiProductCategory : page.getResult()) {
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				hashTopCategoryUuids.add(busiProductCategory.getBspcyTop());
			}	
		}
		List<String> topCategoryUuids = new ArrayList<>(hashTopCategoryUuids);
		Map<String, BusiProductCategory> topCategoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(topCategoryUuids);
		
		List<BusiProductCategoryVO> vos = new ArrayList<BusiProductCategoryVO>();
		BusiProductCategoryVO vo;
		for (BusiProductCategory busiProductCategory : page.getResult()) {
			vo = new BusiProductCategoryVO();			vo.convertPOToVO(busiProductCategory);
			if (!StringUtil.isEmpty(busiProductCategory.getBspcyTop())) {
				vo.setBspcyTopName(topCategoryMap.get(busiProductCategory.getBspcyTop())==null?null:topCategoryMap.get(busiProductCategory.getBspcyTop()).getBspcyName());
			}
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiProductCategoryController]:end findBusiProductCategoryPage");
	}

}