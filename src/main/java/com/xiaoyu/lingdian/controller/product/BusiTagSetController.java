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
import com.xiaoyu.lingdian.entity.BusiTagSet;
import com.xiaoyu.lingdian.service.product.BusiTagSetService;
import com.xiaoyu.lingdian.vo.BusiTagSetVO;

@Controller
@RequestMapping(value="/busiTagSet")
public class BusiTagSetController extends BaseController {

	/**
	* 标签配置表
	*/
	@Autowired
	private BusiTagSetService busiTagSetService;
	
	/**
	* 添加
	*
	* @param bststName 标签名称
	* @param bststDesc 标签简介
	* @param bststIcon 标签图标字
	* @return
	*/
	@RequestMapping(value="/add/busiTagSet", method=RequestMethod.POST)
	public void addBusiTagSet (String bststName, String bststDesc, String bststIcon, HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin addBusiTagSet");
		if (StringUtil.isEmpty(bststName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "标签名称不能为空!"), response);
			logger.info("[BusiTagSetController]:end addBusiTagSet");
			return;
		}
		if (StringUtil.isEmpty(bststIcon)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "标签图标字不能为空!"), response);
			logger.info("[BusiTagSetController]:end addBusiTagSet");
			return;
		}
		BusiTagSet busiTagSet = new BusiTagSet();
		String uuid = RandomUtil.generateString(16);
		busiTagSet.setBststUuid(uuid);
		busiTagSet.setBststName(bststName);
		busiTagSet.setBststDesc(bststDesc);
		busiTagSet.setBststIcon(bststIcon);
		busiTagSet.setBststCdate(new Date());
		busiTagSet.setBststUdate(new Date());

		busiTagSetService.insertBusiTagSet(busiTagSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiTagSetController]:end addBusiTagSet");
	}

	/**
	* 修改
	*
	* @param bststUuid 标识UUID
	* @param bststName 标签名称
	* @param bststDesc 标签简介
	* @param bststIcon 标签图标字
	* @return
	*/
	@RequestMapping(value="/update/busiTagSet", method=RequestMethod.POST)
	public void updateBusiTagSet (String bststUuid, String bststName, String bststDesc, String bststIcon, HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin updateBusiTagSet");
		if (StringUtil.isEmpty(bststUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiTagSetController]:end updateBusiTagSet");
			return;
		}
		if (StringUtil.isEmpty(bststName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "标签名称不能为空!"), response);
			logger.info("[BusiTagSetController]:end updateBusiTagSet");
			return;
		}
		if (StringUtil.isEmpty(bststIcon)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "标签图标字不能为空!"), response);
			logger.info("[BusiTagSetController]:end updateBusiTagSet");
			return;
		}
		BusiTagSet busiTagSet = new BusiTagSet();
		busiTagSet.setBststUuid(bststUuid);
		busiTagSet.setBststName(bststName);
		busiTagSet.setBststDesc(bststDesc);
		busiTagSet.setBststIcon(bststIcon);
		busiTagSet.setBststUdate(new Date());

		busiTagSetService.updateBusiTagSet(busiTagSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiTagSetController]:end updateBusiTagSet");

	}

	/**
	* 删除
	*
	* @param bststUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiTagSet (String bststUuid, HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin deleteBusiTagSet");
		if (StringUtil.isEmpty(bststUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiTagSetController]:end deleteBusiTagSet");
			return;
		}
		BusiTagSet busiTagSet = new BusiTagSet();
		busiTagSet.setBststUuid(bststUuid);

		busiTagSetService.deleteBusiTagSet(busiTagSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiTagSetController]:end deleteBusiTagSet");
	}

	/**
	* 批量删除
	*
	* @param bststUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiTagSet (String bststUuids, HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin deleteBatchBusiTagSet");
		if (StringUtil.isEmpty(bststUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiTagSetController]:end deleteBatchBusiTagSet");
			return;
		}
		String[] uuids=bststUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiTagSetController]:end deleteBatchBusiTagSet");
			return;
		}
		busiTagSetService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiTagSetController]:end deleteBatchBusiTagSet");
	}

	/**
	* 获取单个
	*
	* @param bststUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiTagSet (String bststUuid, HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin viewsBusiTagSet");
		if (StringUtil.isEmpty(bststUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiTagSetController]:end viewsBusiTagSet");
			return;
		}
		BusiTagSet busiTagSet = new BusiTagSet();
		busiTagSet.setBststUuid(bststUuid);

		busiTagSet = busiTagSetService.getBusiTagSet(busiTagSet);

		BusiTagSetVO busiTagSetVO = new BusiTagSetVO();
		busiTagSetVO.convertPOToVO(busiTagSet);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiTagSetVO), response);
		logger.info("[BusiTagSetController]:end viewsBusiTagSet");
	}

	/**
	* 根据标签UUID集合查询标签列表
	*
	* @param bststUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/find/by/uuids", method=RequestMethod.POST)
	public void findBusiTagSetByUuids (String bststUuids, HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin findBusiTagSetByUuids");

		if (StringUtil.isEmpty(bststUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiTagSetController]:end findBusiTagSetByUuids");
			return;
		}

		String[] uuids=bststUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择标签集合对象！"), response);
			logger.info("[BusiTagSetController]:end findBusiTagSetByUuids");
			return;
		}
		
		List<BusiTagSet> tagList = busiTagSetService.findBusiTagSetByUuids(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取成功!", tagList),response);
		logger.info("[BusiTagSetController]:end findBusiTagSetByUuids");
	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiTagSetList (HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin findBusiTagSetList");
		List<BusiTagSet> lists = busiTagSetService.findBusiTagSetList();
		List<BusiTagSetVO> vos = new ArrayList<BusiTagSetVO>();
		BusiTagSetVO vo;
		for (BusiTagSet busiTagSet : lists) {
			vo = new BusiTagSetVO();			vo.convertPOToVO(busiTagSet);			vos.add(vo);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiTagSetController]:end findBusiTagSetList");
	}

	/**
	* 获取列表<Page>
	* 
	* @param bststName 标签名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiTagSetPage (String bststName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiTagSetController]:begin findBusiTagSetPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiTagSet> page = busiTagSetService.findBusiTagSetPage(bststName, pageNum, pageSize);
		Page<BusiTagSetVO> pageVO = new Page<BusiTagSetVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiTagSetVO> vos = new ArrayList<BusiTagSetVO>();
		BusiTagSetVO vo;
		for (BusiTagSet busiTagSet : page.getResult()) {
			vo = new BusiTagSetVO();			vo.convertPOToVO(busiTagSet);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiTagSetController]:end findBusiTagSetPage");
	}

}