package com.xiaoyu.lingdian.controller;

import java.util.List;
import java.util.ArrayList;
import com.xiaoyu.lingdian.tool.DateUtil;
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
import com.xiaoyu.lingdian.entity.CoreTemplateMessage;
import com.xiaoyu.lingdian.service.CoreTemplateMessageService;
import com.xiaoyu.lingdian.vo.CoreTemplateMessageVO;

@Controller
@RequestMapping(value="/coreTemplateMessage")
public class CoreTemplateMessageController extends BaseController {

	/**
	* 模板消息日志记录表
	*/
	@Autowired
	private CoreTemplateMessageService coreTemplateMessageService;
	
	/**
	* 添加
	*
	* @param crtmeUser 所属用户
	* @param crtmeContent 消息内容
	* @param crtmeTime 发送时间
	* @param crtmeStatus 发送状态:1成功,0失败
	* @param crtmeType 模板消息类型
	* @param crtmeSign 模板消息标识
	* @return
	*/
	@RequestMapping(value="/add/coreTemplateMessage", method=RequestMethod.POST)
	public void addCoreTemplateMessage (String crtmeUser, String crtmeContent, String crtmeTime, Integer crtmeStatus, Integer crtmeType, String crtmeSign, HttpServletResponse response) {
		logger.info("[CoreTemplateMessageController]:begin addCoreTemplateMessage");
		CoreTemplateMessage coreTemplateMessage = new CoreTemplateMessage();
		String uuid = RandomUtil.generateString(16);
		coreTemplateMessage.setCrtmeUuid(uuid);
		coreTemplateMessage.setCrtmeUser(crtmeUser);
		coreTemplateMessage.setCrtmeContent(crtmeContent);
		coreTemplateMessage.setCrtmeTime(DateUtil.parseDefaultDate(crtmeTime));
		coreTemplateMessage.setCrtmeStatus(crtmeStatus);
		coreTemplateMessage.setCrtmeType(crtmeType);
		coreTemplateMessage.setCrtmeSign(crtmeSign);

		coreTemplateMessageService.insertCoreTemplateMessage(coreTemplateMessage);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreTemplateMessageController]:end addCoreTemplateMessage");

	}

	/**
	* 修改
	*
	* @param crtmeUuid 标识UUID
	* @param crtmeUser 所属用户
	* @param crtmeContent 消息内容
	* @param crtmeTime 发送时间
	* @param crtmeStatus 发送状态:1成功,0失败
	* @param crtmeType 模板消息类型
	* @param crtmeSign 模板消息标识
	* @return
	*/
	@RequestMapping(value="/update/coreTemplateMessage", method=RequestMethod.POST)
	public void updateCoreTemplateMessage (String crtmeUuid, String crtmeUser, String crtmeContent, String crtmeTime, Integer crtmeStatus, Integer crtmeType, String crtmeSign, HttpServletResponse response) {
		logger.info("[CoreTemplateMessageController]:begin updateCoreTemplateMessage");
		if (StringUtil.isEmpty(crtmeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreTemplateMessage coreTemplateMessage = new CoreTemplateMessage();
		coreTemplateMessage.setCrtmeUuid(crtmeUuid);
		coreTemplateMessage.setCrtmeUser(crtmeUser);
		coreTemplateMessage.setCrtmeContent(crtmeContent);
		coreTemplateMessage.setCrtmeTime(DateUtil.parseDefaultDate(crtmeTime));
		coreTemplateMessage.setCrtmeStatus(crtmeStatus);
		coreTemplateMessage.setCrtmeType(crtmeType);
		coreTemplateMessage.setCrtmeSign(crtmeSign);

		coreTemplateMessageService.updateCoreTemplateMessage(coreTemplateMessage);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreTemplateMessageController]:end updateCoreTemplateMessage");

	}

	/**
	* 删除
	*
	* @param crtmeUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreTemplateMessage (String crtmeUuid, HttpServletResponse response) {
		logger.info("[CoreTemplateMessageController]:begin deleteCoreTemplateMessage");
		if (StringUtil.isEmpty(crtmeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreTemplateMessage coreTemplateMessage = new CoreTemplateMessage();
		coreTemplateMessage.setCrtmeUuid(crtmeUuid);

		coreTemplateMessageService.deleteCoreTemplateMessage(coreTemplateMessage);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreTemplateMessageController]:end deleteCoreTemplateMessage");

	}

	/**
	* 批量删除
	*
	* @param crtmeUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreTemplateMessage (String crtmeUuids, HttpServletResponse response) {
		logger.info("[CoreTemplateMessageController]:begin deleteBatchCoreTemplateMessage");
		if (StringUtil.isEmpty(crtmeUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=crtmeUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreTemplateMessageService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreTemplateMessageController]:end deleteBatchCoreTemplateMessage");

	}

	/**
	* 获取单个
	*
	* @param crtmeUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreTemplateMessage (String crtmeUuid, HttpServletResponse response) {
		logger.info("[CoreTemplateMessageController]:begin viewsCoreTemplateMessage");
		if (StringUtil.isEmpty(crtmeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		CoreTemplateMessage coreTemplateMessage = new CoreTemplateMessage();
		coreTemplateMessage.setCrtmeUuid(crtmeUuid);

		coreTemplateMessage = coreTemplateMessageService.getCoreTemplateMessage(coreTemplateMessage);

		CoreTemplateMessageVO coreTemplateMessageVO = new CoreTemplateMessageVO();
		coreTemplateMessageVO.convertPOToVO(coreTemplateMessage);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreTemplateMessageVO), response);
		logger.info("[CoreTemplateMessageController]:end viewsCoreTemplateMessage");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreTemplateMessageList (HttpServletResponse response) {
		logger.info("[CoreTemplateMessageController]:begin findCoreTemplateMessageList");
		List<CoreTemplateMessage> lists = coreTemplateMessageService.findCoreTemplateMessageList();
		List<CoreTemplateMessageVO> vos = new ArrayList<CoreTemplateMessageVO>();
		CoreTemplateMessageVO vo;
		for (CoreTemplateMessage coreTemplateMessage : lists) {
			vo = new CoreTemplateMessageVO();
			vo.convertPOToVO(coreTemplateMessage);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreTemplateMessageController]:end findCoreTemplateMessageList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreTemplateMessagePage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreTemplateMessageController]:begin findCoreTemplateMessagePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreTemplateMessage> page = coreTemplateMessageService.findCoreTemplateMessagePage(pageNum, pageSize);
		Page<CoreTemplateMessageVO> pageVO = new Page<CoreTemplateMessageVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreTemplateMessageVO> vos = new ArrayList<CoreTemplateMessageVO>();
		CoreTemplateMessageVO vo;
		for (CoreTemplateMessage coreTemplateMessage : page.getResult()) {
			vo = new CoreTemplateMessageVO();
			vo.convertPOToVO(coreTemplateMessage);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreTemplateMessageController]:end findCoreTemplateMessagePage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}