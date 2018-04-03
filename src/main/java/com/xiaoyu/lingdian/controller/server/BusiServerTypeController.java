package com.xiaoyu.lingdian.controller.server;

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
import com.xiaoyu.lingdian.entity.BusiServerType;
import com.xiaoyu.lingdian.service.server.BusiServerTypeService;
import com.xiaoyu.lingdian.vo.BusiServerTypeVO;

@Controller
@RequestMapping(value="/busiServerType")
public class BusiServerTypeController extends BaseController {

	/**
	* 服务类别表
	*/
	@Autowired
	private BusiServerTypeService busiServerTypeService;

	/**
	* 添加(默认启用)
	*
	* @param bssteName 类别名称
	* @param bssteIcon 图标
	* @param bssteIslist 是否有列表:1有0无
	* @param bssteShow 显示端:1分享端2商城端3都显示
	* @param bssteDetails 详情URL(不是列表用)
	* @return
	*/
	@RequestMapping(value="/add/busiServerType", method=RequestMethod.POST)
	public void addBusiServerType (String bssteName, String bssteIcon, Integer bssteIslist, Integer bssteShow, String bssteDetails, HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin addBusiServerType");

		if (StringUtil.isEmpty(bssteName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "类别名称不能为空!"), response);
			logger.info("[BusiServerTypeController]:end addBusiServerType");
			return;
		}
		if (StringUtil.isEmpty(bssteIcon)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "图标不能为空!"), response);
			logger.info("[BusiServerTypeController]:end addBusiServerType");
			return;
		}
		if (bssteIslist == 0) {
			if (StringUtil.isEmpty(bssteDetails)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "无列表时详情URL必填!"), response);
				logger.info("[BusiServerTypeController]:end addBusiServerType");
				return;
			}
		}
		if (null == bssteShow || bssteShow == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择显示端!"), response);
			logger.info("[BusiServerTypeController]:end addBusiServerType");
			return;
		}
		BusiServerType type = busiServerTypeService.getBusiServerTypeByName(bssteName);		if (null != type) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该类别已存在!"), response);
			logger.info("[BusiServerTypeController]:end addBusiServerType");
			return;
		}

		BusiServerType busiServerType = new BusiServerType();
		String uuid = RandomUtil.generateString(16);
		busiServerType.setBssteUuid(uuid);
		busiServerType.setBssteName(bssteName);
		busiServerType.setBssteIcon(bssteIcon);
		busiServerType.setBssteIslist(bssteIslist);
		busiServerType.setBssteShow(bssteShow);
		busiServerType.setBssteDetails(bssteDetails);
		busiServerType.setBssteCdate(new Date());
		busiServerType.setBssteUdate(new Date());
		busiServerType.setBssteStatus(1);

		busiServerTypeService.insertBusiServerType(busiServerType);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiServerTypeController]:end addBusiServerType");

	}

	/**
	* 修改
	*
	* @param bssteUuid 标识UUID
	* @param bssteName 类别名称
	* @param bssteIcon 图标
	* @param bssteIslist 是否有列表:1有0无
	* @param bssteShow 显示端:1分享端2商城端3都显示
	* @param bssteDetails 详情URL(不是列表用)
	* @return
	*/
	@RequestMapping(value="/update/busiServerType", method=RequestMethod.POST)
	public void updateBusiServerType (String bssteUuid, String bssteName, String bssteIcon, Integer bssteIslist, Integer bssteShow, String bssteDetails, HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin updateBusiServerType");
		if (StringUtil.isEmpty(bssteUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiServerTypeController]:end updateBusiServerType");
			return;
		}
		if (StringUtil.isEmpty(bssteName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "类别名称不能为空!"), response);
			logger.info("[BusiServerTypeController]:end updateBusiServerType");
			return;
		}
		if (StringUtil.isEmpty(bssteIcon)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "图标不能为空!"), response);
			logger.info("[BusiServerTypeController]:end updateBusiServerType");
			return;
		}
		if (bssteIslist == 0) {
			if (StringUtil.isEmpty(bssteDetails)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "无列表时详情URL必填!"), response);
				logger.info("[BusiServerTypeController]:end updateBusiServerType");
				return;
			}
		}
		if (null == bssteShow || bssteShow == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择显示端!"), response);
			logger.info("[BusiServerTypeController]:end updateBusiServerType");
			return;
		}

		BusiServerType type = busiServerTypeService.getBusiServerTypeByName(bssteName);
		if (null != type && !bssteUuid.equals(type.getBssteUuid())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该类别已存在!"), response);
			logger.info("[BusiServerTypeController]:end updateBusiServerType");
			return;
		}
				BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(bssteUuid);
		busiServerType.setBssteName(bssteName);
		busiServerType.setBssteIcon(bssteIcon);
		busiServerType.setBssteIslist(bssteIslist);
		busiServerType.setBssteShow(bssteShow);
		busiServerType.setBssteDetails(bssteDetails);
		busiServerType.setBssteUdate(new Date());

		busiServerTypeService.updateBusiServerType(busiServerType);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiServerTypeController]:end updateBusiServerType");

	}

	/**
	* 启用
	*
	* @param bssteUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable (String bssteUuid, HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin enable");

		if (StringUtil.isEmpty(bssteUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiServerTypeController]:end enable");
			return;
		}
		
		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(bssteUuid);
		busiServerType.setBssteStatus(1);
		busiServerType.setBssteUdate(new Date());

		busiServerTypeService.updateBusiServerType(busiServerType);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[BusiServerTypeController]:end enable");

	}
	
	/**
	* 禁用
	*
	* @param bssteUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable (String bssteUuid, HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin disable");

		if (StringUtil.isEmpty(bssteUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiServerTypeController]:end disable");
			return;
		}
		
		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(bssteUuid);
		busiServerType.setBssteStatus(0);
		busiServerType.setBssteUdate(new Date());

		busiServerTypeService.updateBusiServerType(busiServerType);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiServerTypeController]:end disable");

	}
	
	/**
	* 删除
	*
	* @param bssteUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiServerType (String bssteUuid, HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin deleteBusiServerType");
		if (StringUtil.isEmpty(bssteUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiServerTypeController]:end deleteBusiServerType");
			return;
		}
		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(bssteUuid);

		busiServerTypeService.deleteBusiServerType(busiServerType);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiServerTypeController]:end deleteBusiServerType");

	}

	/**
	* 批量删除
	*
	* @param bssteUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiServerType (String bssteUuids, HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin deleteBatchBusiServerType");
		if (StringUtil.isEmpty(bssteUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiServerTypeController]:end deleteBatchBusiServerType");
			return;
		}
		String[] uuids=bssteUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiServerTypeController]:end deleteBatchBusiServerType");
			return;
		}
		busiServerTypeService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiServerTypeController]:end deleteBatchBusiServerType");

	}

	/**
	* 获取单个
	*
	* @param bssteUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiServerType (String bssteUuid, HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin viewsBusiServerType");
		if (StringUtil.isEmpty(bssteUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiServerTypeController]:end viewsBusiServerType");
			return;
		}
		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(bssteUuid);

		busiServerType = busiServerTypeService.getBusiServerType(busiServerType);

		BusiServerTypeVO busiServerTypeVO = new BusiServerTypeVO();
		busiServerTypeVO.convertPOToVO(busiServerType);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiServerTypeVO), response);
		logger.info("[BusiServerTypeController]:end viewsBusiServerType");

	}

	/**
	* 商城端获取启动的服务列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/store/find/all", method=RequestMethod.POST)
	public void findBusiServerTypeForListsByStore (HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin findBusiServerTypeForListsByStore");
		List<BusiServerType> lists = busiServerTypeService.findBusiServerTypeForListsByStore();
		List<BusiServerTypeVO> vos = new ArrayList<BusiServerTypeVO>();
		BusiServerTypeVO vo;
		for (BusiServerType busiServerType : lists) {
			vo = new BusiServerTypeVO();
			vo.convertPOToVO(busiServerType);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiServerTypeController]:end findBusiServerTypeForListsByStore");

	}

	/**
	* 分享端获取启动的服务列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/share/find/all", method=RequestMethod.POST)
	public void findBusiServerTypeForListsByShare (HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin findBusiServerTypeForListsByShare");

		List<BusiServerType> lists = busiServerTypeService.findBusiServerTypeForListsByStore();
		List<BusiServerTypeVO> vos = new ArrayList<BusiServerTypeVO>();
		BusiServerTypeVO vo;
		for (BusiServerType busiServerType : lists) {
			vo = new BusiServerTypeVO();

			vo.convertPOToVO(busiServerType);

			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiServerTypeController]:end findBusiServerTypeForListsByShare");

	}

	/**
	* 获取所有启用的类别list<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiServerTypeForListsAll (HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin findBusiServerTypeForListsAll");

		List<BusiServerType> lists = busiServerTypeService.findBusiServerTypeForListsAll();
		List<BusiServerTypeVO> vos = new ArrayList<BusiServerTypeVO>();
		BusiServerTypeVO vo;
		for (BusiServerType busiServerType : lists) {
			vo = new BusiServerTypeVO();

			vo.convertPOToVO(busiServerType);

			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiServerTypeController]:end findBusiServerTypeForListsAll");

	}
	
	/**
	* 获取列表<Page>
	* 
	* @param bssteName 服务类别名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiServerTypePage (String bssteName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiServerTypeController]:begin findBusiServerTypePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiServerType> page = busiServerTypeService.findBusiServerTypePage(bssteName, pageNum, pageSize);
		Page<BusiServerTypeVO> pageVO = new Page<BusiServerTypeVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiServerTypeVO> vos = new ArrayList<BusiServerTypeVO>();
		BusiServerTypeVO vo;
		for (BusiServerType busiServerType : page.getResult()) {
			vo = new BusiServerTypeVO();
			vo.convertPOToVO(busiServerType);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiServerTypeController]:end findBusiServerTypePage");

	}

}