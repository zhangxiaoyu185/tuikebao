package com.xiaoyu.lingdian.controller;

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
import com.xiaoyu.lingdian.entity.CoreCustomerService;
import com.xiaoyu.lingdian.service.CoreCustomerServiceService;
import com.xiaoyu.lingdian.vo.CoreCustomerServiceVO;

@Controller
@RequestMapping(value="/coreCustomerService")
public class CoreCustomerServiceController extends BaseController {

	/**
	* 客服信息表
	*/
	@Autowired
	private CoreCustomerServiceService coreCustomerServiceService;
	
	/**
	* 添加
	*
	* @param crcseTel 电话号码(|隔开)
	* @param crcseQq QQ(|隔开)
	* @return
	*/
	@RequestMapping(value="/add/coreCustomerService", method=RequestMethod.POST)
	public void addCoreCustomerService (String crcseTel, String crcseQq, HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin addCoreCustomerService");

		if (StringUtil.isEmpty(crcseTel)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "电话号码不能为空!"), response);
			logger.info("[CoreSystemSetController]:end addCoreCustomerService");
			return;
		}
		if (StringUtil.isEmpty(crcseQq)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "QQ不能为空!"), response);
			logger.info("[CoreSystemSetController]:end addCoreCustomerService");
			return;
		}
		CoreCustomerService coreCustomerService = new CoreCustomerService();
		String uuid = RandomUtil.generateString(16);
		coreCustomerService.setCrcseUuid(uuid);
		coreCustomerService.setCrcseTel(crcseTel);
		coreCustomerService.setCrcseQq(crcseQq);
		coreCustomerService.setCrcseCdate(new Date());
		coreCustomerService.setCrcseUdate(new Date());

		coreCustomerServiceService.insertCoreCustomerService(coreCustomerService);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreCustomerServiceController]:end addCoreCustomerService");

	}

	/**
	* 修改
	*
	* @param crcseUuid 标识UUID
	* @param crcseTel 电话号码(|隔开)
	* @param crcseQq QQ(|隔开)
	* @return
	*/
	@RequestMapping(value="/update/coreCustomerService", method=RequestMethod.POST)
	public void updateCoreCustomerService (String crcseUuid, String crcseTel, String crcseQq, HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin updateCoreCustomerService");
		if (StringUtil.isEmpty(crcseUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreCustomerService");
			return;
		}
		if (StringUtil.isEmpty(crcseTel)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "电话号码不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreCustomerService");
			return;
		}
		if (StringUtil.isEmpty(crcseQq)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "QQ不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreCustomerService");
			return;
		}
		CoreCustomerService coreCustomerService = new CoreCustomerService();
		coreCustomerService.setCrcseUuid(crcseUuid);
		coreCustomerService.setCrcseTel(crcseTel);
		coreCustomerService.setCrcseQq(crcseQq);
		coreCustomerService.setCrcseUdate(new Date());

		coreCustomerServiceService.updateCoreCustomerService(coreCustomerService);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreCustomerServiceController]:end updateCoreCustomerService");

	}

	/**
	* 删除
	*
	* @param crcseUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreCustomerService (String crcseUuid, HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin deleteCoreCustomerService");
		if (StringUtil.isEmpty(crcseUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreCustomerServiceController]:end deleteCoreCustomerService");
			return;
		}
		CoreCustomerService coreCustomerService = new CoreCustomerService();
		coreCustomerService.setCrcseUuid(crcseUuid);

		coreCustomerServiceService.deleteCoreCustomerService(coreCustomerService);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreCustomerServiceController]:end deleteCoreCustomerService");

	}

	/**
	* 批量删除
	*
	* @param crcseUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreCustomerService (String crcseUuids, HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin deleteBatchCoreCustomerService");
		if (StringUtil.isEmpty(crcseUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[CoreCustomerServiceController]:end deleteBatchCoreCustomerService");
			return;
		}
		String[] uuids=crcseUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreCustomerServiceController]:end deleteBatchCoreCustomerService");
			return;
		}
		coreCustomerServiceService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreCustomerServiceController]:end deleteBatchCoreCustomerService");

	}

	/**
	* 获取单个
	*
	* @param crcseUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreCustomerService (String crcseUuid, HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin viewsCoreCustomerService");
		if (StringUtil.isEmpty(crcseUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreCustomerServiceController]:end viewsCoreCustomerService");
			return;
		}
		CoreCustomerService coreCustomerService = new CoreCustomerService();
		coreCustomerService.setCrcseUuid(crcseUuid);

		coreCustomerService = coreCustomerServiceService.getCoreCustomerService(coreCustomerService);

		CoreCustomerServiceVO coreCustomerServiceVO = new CoreCustomerServiceVO();
		coreCustomerServiceVO.convertPOToVO(coreCustomerService);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreCustomerServiceVO), response);
		logger.info("[CoreCustomerServiceController]:end viewsCoreCustomerService");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreCustomerServiceList (HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin findCoreCustomerServiceList");
		List<CoreCustomerService> lists = coreCustomerServiceService.findCoreCustomerServiceList();
		List<CoreCustomerServiceVO> vos = new ArrayList<CoreCustomerServiceVO>();
		CoreCustomerServiceVO vo;
		for (CoreCustomerService coreCustomerService : lists) {
			vo = new CoreCustomerServiceVO();
			vo.convertPOToVO(coreCustomerService);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreCustomerServiceController]:end findCoreCustomerServiceList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreCustomerServicePage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreCustomerServiceController]:begin findCoreCustomerServicePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreCustomerService> page = coreCustomerServiceService.findCoreCustomerServicePage(pageNum, pageSize);
		Page<CoreCustomerServiceVO> pageVO = new Page<CoreCustomerServiceVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreCustomerServiceVO> vos = new ArrayList<CoreCustomerServiceVO>();
		CoreCustomerServiceVO vo;
		for (CoreCustomerService coreCustomerService : page.getResult()) {
			vo = new CoreCustomerServiceVO();
			vo.convertPOToVO(coreCustomerService);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreCustomerServiceController]:end findCoreCustomerServicePage");

	}

}