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
import com.xiaoyu.lingdian.entity.CoreMessageCenter;
import com.xiaoyu.lingdian.service.CoreMessageCenterService;
import com.xiaoyu.lingdian.vo.CoreMessageCenterVO;

@Controller
@RequestMapping(value="/coreMessageCenter")
public class CoreMessageCenterController extends BaseController {

	/**
	* 系统消息中心表
	*/
	@Autowired
	private CoreMessageCenterService coreMessageCenterService;
	
	/**
	* 添加(用户添加，默认为未处理消息)
	*
	* @param crmecContent 消息内容
	* @param crmecType 消息类型
	* @return
	*/
	@RequestMapping(value="/add/coreMessageCenter", method=RequestMethod.POST)
	public void addCoreMessageCenter (String crmecContent, Integer crmecType, HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin addCoreMessageCenter");
         if(StringUtil.isEmpty(crmecContent)){
        	 writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "消息内容必填!"),response);
     		logger.info("[CoreMessageCenterController]:end addCoreMessageCenter");
 			return;
         }
         if(null == crmecType || crmecType == 0){
        	writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "消息类型必填!"),response);
     		logger.info("[CoreMessageCenterController]:end addCoreMessageCenter");
 			return;
         }
        		CoreMessageCenter coreMessageCenter = new CoreMessageCenter();
		String uuid = RandomUtil.generateString(16);
		coreMessageCenter.setCrmecUuid(uuid);
		coreMessageCenter.setCrmecContent(crmecContent);
		coreMessageCenter.setCrmecCdate(new Date());
		coreMessageCenter.setCrmecUdate(new Date());
		coreMessageCenter.setCrmecStatus(2);
		coreMessageCenter.setCrmecType(crmecType);

		coreMessageCenterService.insertCoreMessageCenter(coreMessageCenter);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreMessageCenterController]:end addCoreMessageCenter");

	}

	/**
	* 处理
	*
	* @param crmecUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/update/coreMessageCenter", method=RequestMethod.POST)
	public void updateCoreMessageCenter (String crmecUuid, HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin updateCoreMessageCenter");
		if (StringUtil.isEmpty(crmecUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreMessageCenterController]:end updateCoreMessageCenter");
			return;
		}
				CoreMessageCenter coreMessageCenter = new CoreMessageCenter();
		coreMessageCenter.setCrmecUuid(crmecUuid);
		coreMessageCenter.setCrmecStatus(1);
		coreMessageCenter.setCrmecUdate(new Date());

		coreMessageCenterService.updateCoreMessageCenter(coreMessageCenter);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "处理成功!"),response);
		logger.info("[CoreMessageCenterController]:end updateCoreMessageCenter");
	}

	/**
	* 删除
	*
	* @param crmecUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreMessageCenter (String crmecUuid, HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin deleteCoreMessageCenter");
		if (StringUtil.isEmpty(crmecUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreMessageCenterController]:end deleteCoreMessageCenter");
			return;
		}
		CoreMessageCenter coreMessageCenter = new CoreMessageCenter();
		coreMessageCenter.setCrmecUuid(crmecUuid);
		coreMessageCenter.setCrmecStatus(3);
		coreMessageCenter.setCrmecUdate(new Date());

		coreMessageCenterService.updateCoreMessageCenter(coreMessageCenter);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreMessageCenterController]:end deleteCoreMessageCenter");

	}

	/**
	* 批量删除
	*
	* @param crmecUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreMessageCenter (String crmecUuids, HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin deleteBatchCoreMessageCenter");
				if (StringUtil.isEmpty(crmecUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[CoreMessageCenterController]:end deleteBatchCoreMessageCenter");
			return;
		}

		String[] uuids = crmecUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreMessageCenterController]:end deleteBatchCoreMessageCenter");
			return;
		}
		
		coreMessageCenterService.updateBatchCoreMessageCenterByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreMessageCenterController]:end deleteBatchCoreMessageCenter");

	}

	/**
	* 获取单个
	*
	* @param crmecUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreMessageCenter (String crmecUuid, HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin viewsCoreMessageCenter");
		if (StringUtil.isEmpty(crmecUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreMessageCenterController]:end viewsCoreMessageCenter");
			return;
		}
		CoreMessageCenter coreMessageCenter = new CoreMessageCenter();
		coreMessageCenter.setCrmecUuid(crmecUuid);

		coreMessageCenter = coreMessageCenterService.getCoreMessageCenter(coreMessageCenter);

		CoreMessageCenterVO coreMessageCenterVO = new CoreMessageCenterVO();
		coreMessageCenterVO.convertPOToVO(coreMessageCenter);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreMessageCenterVO), response);
		logger.info("[CoreMessageCenterController]:end viewsCoreMessageCenter");

	}

	/**
	* 获取所有未读消息个数
	* 
	* @return
	*/
	@RequestMapping(value="/find/count", method=RequestMethod.POST)
	public void findCoreMessageCenterCount (HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin findCoreMessageCenterCount");
		int count = coreMessageCenterService.findCoreMessageCenterCount();
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取所有未读消息个数成功!", count),response);
		logger.info("[CoreMessageCenterController]:end findCoreMessageCenterCount");

	}

	/**
	* 获取列表<Page>
	* 
	* @param crmecContent 消息内容
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreMessageCenterPage (String crmecContent, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreMessageCenterController]:begin findCoreMessageCenterPage");
        		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreMessageCenter> page = coreMessageCenterService.findCoreMessageCenterPage(crmecContent, pageNum, pageSize);
		Page<CoreMessageCenterVO> pageVO = new Page<CoreMessageCenterVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreMessageCenterVO> vos = new ArrayList<CoreMessageCenterVO>();
		CoreMessageCenterVO vo;
		for (CoreMessageCenter coreMessageCenter : page.getResult()) {
			vo = new CoreMessageCenterVO();
			vo.convertPOToVO(coreMessageCenter);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreMessageCenterController]:end findCoreMessageCenterPage");
	}

}