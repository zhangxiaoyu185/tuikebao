package com.xiaoyu.lingdian.controller;

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
import com.xiaoyu.lingdian.entity.CoreSystemConfig;
import com.xiaoyu.lingdian.service.CoreSystemConfigService;
import com.xiaoyu.lingdian.vo.CoreSystemConfigVO;

@Controller
@RequestMapping(value="/coreSystemConfig")
public class CoreSystemConfigController extends BaseController {

	/**
	* 系统配置表
	*/
	@Autowired
	private CoreSystemConfigService coreSystemConfigService;

	/**
	* 添加
	*
	* @param crscgKey 配置KEY
	* @param crscgValue 配置值
	* @param crscgDesc 配置说明
	* @return
	*/
	@RequestMapping(value="/add/coreSystemConfig", method=RequestMethod.POST)
	public void addCoreSystemConfig (String crscgKey, String crscgValue, String crscgDesc, HttpServletResponse response) {
		logger.info("[CoreSystemConfigController]:begin addCoreSystemConfig");

		if (StringUtil.isEmpty(crscgKey)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "配置KEY不能为空!"), response);
			logger.info("[CoreSystemConfigController]:end addCoreSystemConfig");
			return;
		}
		if (StringUtil.isEmpty(crscgValue)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "配置值不能为空!"), response);
			logger.info("[CoreSystemConfigController]:end addCoreSystemConfig");
			return;
		}
				CoreSystemConfig coreSystemConfig = new CoreSystemConfig();
		coreSystemConfig.setCrscgKey(crscgKey);
		
		//判断这个公众号中这个KEY是否已存在
		CoreSystemConfig systemConfig = coreSystemConfigService.getCoreSystemConfigByKey(coreSystemConfig);
		if (null != systemConfig) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "KEY已存在!"),response);
			logger.info("[CoreSystemConfigController]:end addCoreSystemConfig");
			return;
		}

		String uuid = RandomUtil.generateString(16);
		coreSystemConfig.setCrscgUuid(uuid);	
		coreSystemConfig.setCrscgValue(crscgValue);
		coreSystemConfig.setCrscgDesc(crscgDesc);

		coreSystemConfigService.insertCoreSystemConfig(coreSystemConfig);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreSystemConfigController]:end addCoreSystemConfig");

	}

	/**
	* 修改
	*
	* @param crscgUuid 标识UUID
	* @param crscgValue 配置值
	* @param crscgDesc 配置说明
	* @return
	*/
	@RequestMapping(value="/update/coreSystemConfig", method=RequestMethod.POST)
	public void updateCoreSystemConfig (String crscgUuid, String crscgValue, String crscgDesc, HttpServletResponse response) {
		logger.info("[CoreSystemConfigController]:begin updateCoreSystemConfig");
		if (StringUtil.isEmpty(crscgUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreSystemConfigController]:end updateCoreSystemConfig");
			return;
		}
		if (StringUtil.isEmpty(crscgValue)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "配置值不能为空!"), response);
			logger.info("[CoreSystemConfigController]:end updateCoreSystemConfig");
			return;
		}
		CoreSystemConfig coreSystemConfig = new CoreSystemConfig();
		coreSystemConfig.setCrscgUuid(crscgUuid);
		coreSystemConfig.setCrscgValue(crscgValue);
		coreSystemConfig.setCrscgDesc(crscgDesc);

		coreSystemConfigService.updateCoreSystemConfig(coreSystemConfig);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreSystemConfigController]:end updateCoreSystemConfig");

	}

	/**
	* 删除
	*
	* @param crscgUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreSystemConfig (String crscgUuid, HttpServletResponse response) {
		logger.info("[CoreSystemConfigController]:begin deleteCoreSystemConfig");
		if (StringUtil.isEmpty(crscgUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreSystemConfigController]:end deleteCoreSystemConfig");
			return;
		}
		CoreSystemConfig coreSystemConfig = new CoreSystemConfig();
		coreSystemConfig.setCrscgUuid(crscgUuid);

		coreSystemConfigService.deleteCoreSystemConfig(coreSystemConfig);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreSystemConfigController]:end deleteCoreSystemConfig");

	}

	/**
	* 批量删除
	*
	* @param crscgUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreSystemConfig (String crscgUuids, HttpServletResponse response) {
		logger.info("[CoreSystemConfigController]:begin deleteBatchCoreSystemConfig");
		if (StringUtil.isEmpty(crscgUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[CoreSystemConfigController]:end deleteBatchCoreSystemConfig");
			return;
		}
		String[] uuids=crscgUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreSystemConfigController]:end deleteBatchCoreSystemConfig");
			return;
		}
		coreSystemConfigService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreSystemConfigController]:end deleteBatchCoreSystemConfig");

	}

	/**
	* 获取单个
	*
	* @param crscgUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreSystemConfig (String crscgUuid, HttpServletResponse response) {
		logger.info("[CoreSystemConfigController]:begin viewsCoreSystemConfig");
		if (StringUtil.isEmpty(crscgUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreSystemConfigController]:end viewsCoreSystemConfig");
			return;
		}
		CoreSystemConfig coreSystemConfig = new CoreSystemConfig();
		coreSystemConfig.setCrscgUuid(crscgUuid);

		coreSystemConfig = coreSystemConfigService.getCoreSystemConfig(coreSystemConfig);
		if (null == coreSystemConfig) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "详情不存在!"), response);
			logger.info("[CoreSystemConfigController]:end viewsCoreSystemConfig");
			return;
		}
		
		CoreSystemConfigVO coreSystemConfigVO = new CoreSystemConfigVO();
		coreSystemConfigVO.convertPOToVO(coreSystemConfig);
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreSystemConfigVO), response);
		logger.info("[CoreSystemConfigController]:end viewsCoreSystemConfig");

	}

	/**
	* 获取配置值
	*
	* @param crscgKey
	* @return
	*/
	@RequestMapping(value="/views/by/key", method=RequestMethod.POST)
	public void viewsCoreSystemConfigByKey (String crscgKey, HttpServletResponse response) {
		logger.info("[CoreSystemConfigController]:begin viewsCoreSystemConfigByKey");
		
		if (StringUtil.isEmpty(crscgKey)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "KEY不能为空!"), response);
			logger.info("[CoreSystemConfigController]:end viewsCoreSystemConfigByKey");
			return;
		}

		CoreSystemConfig coreSystemConfig = new CoreSystemConfig();
		coreSystemConfig.setCrscgKey(crscgKey);

		coreSystemConfig = coreSystemConfigService.getCoreSystemConfigByKey(coreSystemConfig);

		CoreSystemConfigVO coreSystemConfigVO = new CoreSystemConfigVO();
		coreSystemConfigVO.convertPOToVO(coreSystemConfig);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取配置值成功", coreSystemConfigVO), response);
		logger.info("[CoreSystemConfigController]:end viewsCoreSystemConfigByKey");

	}
	
	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreSystemConfigList (HttpServletResponse response) {
		logger.info("[CoreSystemConfigController]:begin findCoreSystemConfigList");
		List<CoreSystemConfig> lists = coreSystemConfigService.findCoreSystemConfigList();
		List<CoreSystemConfigVO> vos = new ArrayList<CoreSystemConfigVO>();
		CoreSystemConfigVO vo;
		for (CoreSystemConfig coreSystemConfig : lists) {
			vo = new CoreSystemConfigVO();			vo.convertPOToVO(coreSystemConfig);			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreSystemConfigController]:end findCoreSystemConfigList");

	}

	/**
	* 获取分页列表<Page>
	* 
	* @param crscgKey
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreSystemConfigPage (String crscgKey, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreSystemConfigController]:begin findCoreSystemConfigPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}

		Page<CoreSystemConfig> page = coreSystemConfigService.findCoreSystemConfigPage(crscgKey, pageNum, pageSize);
		Page<CoreSystemConfigVO> pageVO = new Page<CoreSystemConfigVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreSystemConfigVO> vos = new ArrayList<CoreSystemConfigVO>();
		CoreSystemConfigVO vo;
		for (CoreSystemConfig coreSystemConfig : page.getResult()) {
			vo = new CoreSystemConfigVO();			vo.convertPOToVO(coreSystemConfig);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreSystemConfigController]:end findCoreSystemConfigPage");

	}

}