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
import com.xiaoyu.lingdian.entity.CoreJob;
import com.xiaoyu.lingdian.service.CoreJobService;
import com.xiaoyu.lingdian.vo.CoreJobVO;

@Controller
@RequestMapping(value="/coreJob")
public class CoreJobController extends BaseController {

	/**
	* 职业表
	*/
	@Autowired
	private CoreJobService coreJobService;
	
	/**
	* 添加
	*
	* @param crjobName 职业
	* @return
	*/
	@RequestMapping(value="/add/coreJob", method=RequestMethod.POST)
	public void addCoreJob (String crjobName, HttpServletResponse response) {
		logger.info("[CoreJobController]:begin addCoreJob");

		if (StringUtil.isEmpty(crjobName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "职位不能为空!"), response);
			logger.info("[CoreJobController]:end addCoreJob");
			return;
		}
		CoreJob coreJob = new CoreJob();
		String uuid = RandomUtil.generateString(16);
		coreJob.setCrjobUuid(uuid);
		coreJob.setCrjobName(crjobName);

		coreJobService.insertCoreJob(coreJob);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreJobController]:end addCoreJob");

	}

	/**
	* 修改
	*
	* @param crjobUuid 标识UUID
	* @param crjobName 职业
	* @return
	*/
	@RequestMapping(value="/update/coreJob", method=RequestMethod.POST)
	public void updateCoreJob (String crjobUuid, String crjobName, HttpServletResponse response) {
		logger.info("[CoreJobController]:begin updateCoreJob");
		if (StringUtil.isEmpty(crjobUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreJobController]:end updateCoreJob");
			return;
		}
		if (StringUtil.isEmpty(crjobName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "职位不能为空!"), response);
			logger.info("[CoreJobController]:end updateCoreJob");
			return;
		}
		CoreJob coreJob = new CoreJob();
		coreJob.setCrjobUuid(crjobUuid);
		coreJob.setCrjobName(crjobName);

		coreJobService.updateCoreJob(coreJob);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreJobController]:end updateCoreJob");

	}

	/**
	* 删除
	*
	* @param crjobUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreJob (String crjobUuid, HttpServletResponse response) {
		logger.info("[CoreJobController]:begin deleteCoreJob");
		if (StringUtil.isEmpty(crjobUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreJobController]:end deleteCoreJob");
			return;
		}
		CoreJob coreJob = new CoreJob();
		coreJob.setCrjobUuid(crjobUuid);

		coreJobService.deleteCoreJob(coreJob);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreJobController]:end deleteCoreJob");

	}

	/**
	* 批量删除
	*
	* @param crjobUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreJob (String crjobUuids, HttpServletResponse response) {
		logger.info("[CoreJobController]:begin deleteBatchCoreJob");
		if (StringUtil.isEmpty(crjobUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[CoreJobController]:end deleteBatchCoreJob");
			return;
		}
		String[] uuids=crjobUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreJobController]:end deleteBatchCoreJob");
			return;
		}
		coreJobService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreJobController]:end deleteBatchCoreJob");

	}

	/**
	* 获取单个
	*
	* @param crjobUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreJob (String crjobUuid, HttpServletResponse response) {
		logger.info("[CoreJobController]:begin viewsCoreJob");
		if (StringUtil.isEmpty(crjobUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreJobController]:end viewsCoreJob");
			return;
		}
		CoreJob coreJob = new CoreJob();
		coreJob.setCrjobUuid(crjobUuid);

		coreJob = coreJobService.getCoreJob(coreJob);

		CoreJobVO coreJobVO = new CoreJobVO();
		coreJobVO.convertPOToVO(coreJob);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreJobVO), response);
		logger.info("[CoreJobController]:end viewsCoreJob");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreJobList (HttpServletResponse response) {
		logger.info("[CoreJobController]:begin findCoreJobList");
		List<CoreJob> lists = coreJobService.findCoreJobList();
		List<CoreJobVO> vos = new ArrayList<CoreJobVO>();
		CoreJobVO vo;
		for (CoreJob coreJob : lists) {
			vo = new CoreJobVO();
			vo.convertPOToVO(coreJob);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreJobController]:end findCoreJobList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param crjobName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreJobPage (String crjobName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreJobController]:begin findCoreJobPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreJob> page = coreJobService.findCoreJobPage(crjobName, pageNum, pageSize);
		Page<CoreJobVO> pageVO = new Page<CoreJobVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreJobVO> vos = new ArrayList<CoreJobVO>();
		CoreJobVO vo;
		for (CoreJob coreJob : page.getResult()) {
			vo = new CoreJobVO();
			vo.convertPOToVO(coreJob);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreJobController]:end findCoreJobPage");

	}

}