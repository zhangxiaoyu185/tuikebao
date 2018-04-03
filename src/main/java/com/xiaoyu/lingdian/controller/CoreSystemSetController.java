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
import com.xiaoyu.lingdian.entity.CoreSystemSet;
import com.xiaoyu.lingdian.service.CoreSystemSetService;
import com.xiaoyu.lingdian.vo.CoreSystemSetVO;

@Controller
@RequestMapping(value="/coreSystemSet")
public class CoreSystemSetController extends BaseController {

	/**
	* 系统设置表
	*/
	@Autowired
	private CoreSystemSetService coreSystemSetService;
	
	/**
	* 添加
	*
	* @param crsstInviteLink 邀请链接前缀
	* @param crsstProductDetails 推广商品详情前缀
	* @param crsstMessagePath 短信接口路径
	* @param crsstMessageLoginname 短信账户名
	* @param crsstMessagePwd 短信密码
	* @param crsstMessageKey 短信key
	* @param crsstMessageDomain 项目域名
	* @param crsstAttachmentDir 附件存放目录
	* @return
	*/
	@RequestMapping(value="/add/coreSystemSet", method=RequestMethod.POST)
	public void addCoreSystemSet (String crsstInviteLink, String crsstProductDetails, String crsstMessagePath, String crsstMessageLoginname, String crsstMessagePwd, String crsstMessageKey, String crsstMessageDomain, String crsstAttachmentDir, HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin addCoreSystemSet");

		if (StringUtil.isEmpty(crsstInviteLink)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邀请链接前缀不能为空!"), response);
			logger.info("[CoreSystemSetController]:end addCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstProductDetails)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "推广商品详情前缀不能为空!"), response);
			logger.info("[CoreSystemSetController]:end addCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstMessagePath)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信接口路径不能为空!"), response);
			logger.info("[CoreSystemSetController]:end addCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstMessageLoginname)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信账户名不能为空!"), response);
			logger.info("[CoreSystemSetController]:end addCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstMessagePwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信密码不能为空!"), response);
			logger.info("[CoreSystemSetController]:end addCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstMessageKey)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信key不能为空!"), response);
			logger.info("[CoreSystemSetController]:end addCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstMessageDomain)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "项目域名不能为空!"), response);
			logger.info("[CoreSystemSetController]:end addCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstAttachmentDir)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "附件存放目录不能为空!"), response);
			logger.info("[CoreSystemSetController]:end addCoreSystemSet");
			return;
		}
				CoreSystemSet coreSystemSet = new CoreSystemSet();
		String uuid = RandomUtil.generateString(16);
		coreSystemSet.setCrsstUuid(uuid);
		coreSystemSet.setCrsstInviteLink(crsstInviteLink);
		coreSystemSet.setCrsstProductDetails(crsstProductDetails);
		coreSystemSet.setCrsstMessagePath(crsstMessagePath);
		coreSystemSet.setCrsstMessageLoginname(crsstMessageLoginname);
		coreSystemSet.setCrsstMessagePwd(crsstMessagePwd);
		coreSystemSet.setCrsstMessageKey(crsstMessageKey);
		coreSystemSet.setCrsstMessageDomain(crsstMessageDomain);
		coreSystemSet.setCrsstAttachmentDir(crsstAttachmentDir);

		coreSystemSetService.insertCoreSystemSet(coreSystemSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreSystemSetController]:end addCoreSystemSet");
	}

	/**
	* 修改
	*
	* @param crsstUuid 标识UUID
	* @param crsstInviteLink 邀请链接前缀
	* @param crsstProductDetails 推广商品详情前缀
	* @param crsstMessagePath 短信接口路径
	* @param crsstMessageLoginname 短信账户名
	* @param crsstMessagePwd 短信密码
	* @param crsstMessageKey 短信key
	* @param crsstMessageDomain 项目域名
	* @param crsstAttachmentDir 附件存放目录
	* @return
	*/
	@RequestMapping(value="/update/coreSystemSet", method=RequestMethod.POST)
	public void updateCoreSystemSet (String crsstUuid, String crsstInviteLink, String crsstProductDetails, String crsstMessagePath, String crsstMessageLoginname, String crsstMessagePwd, String crsstMessageKey, String crsstMessageDomain, String crsstAttachmentDir, HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin updateCoreSystemSet");
		if (StringUtil.isEmpty(crsstUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstInviteLink)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邀请链接前缀不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstProductDetails)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "推广商品详情前缀不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstMessagePath)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信接口路径不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstMessageLoginname)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信账户名不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstMessagePwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信密码不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstMessageKey)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信key不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstMessageDomain)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "项目域名不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreSystemSet");
			return;
		}
		if (StringUtil.isEmpty(crsstAttachmentDir)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "附件存放目录不能为空!"), response);
			logger.info("[CoreSystemSetController]:end updateCoreSystemSet");
			return;
		}		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(crsstUuid);
		coreSystemSet.setCrsstInviteLink(crsstInviteLink);
		coreSystemSet.setCrsstProductDetails(crsstProductDetails);
		coreSystemSet.setCrsstMessagePath(crsstMessagePath);
		coreSystemSet.setCrsstMessageLoginname(crsstMessageLoginname);
		coreSystemSet.setCrsstMessagePwd(crsstMessagePwd);
		coreSystemSet.setCrsstMessageKey(crsstMessageKey);
		coreSystemSet.setCrsstMessageDomain(crsstMessageDomain);
		coreSystemSet.setCrsstAttachmentDir(crsstAttachmentDir);

		coreSystemSetService.updateCoreSystemSet(coreSystemSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreSystemSetController]:end updateCoreSystemSet");
	}

	/**
	* 删除
	*
	* @param crsstUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreSystemSet (String crsstUuid, HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin deleteCoreSystemSet");
		if (StringUtil.isEmpty(crsstUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreSystemSetController]:end deleteCoreSystemSet");
			return;
		}
		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(crsstUuid);

		coreSystemSetService.deleteCoreSystemSet(coreSystemSet);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreSystemSetController]:end deleteCoreSystemSet");
	}

	/**
	* 批量删除
	*
	* @param crsstUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreSystemSet (String crsstUuids, HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin deleteBatchCoreSystemSet");
		if (StringUtil.isEmpty(crsstUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[CoreSystemSetController]:end deleteBatchCoreSystemSet");
			return;
		}
		String[] uuids=crsstUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreSystemSetController]:end deleteBatchCoreSystemSet");
			return;
		}
		coreSystemSetService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreSystemSetController]:end deleteBatchCoreSystemSet");
	}

	/**
	* 获取单个
	*
	* @param crsstUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreSystemSet (String crsstUuid, HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin viewsCoreSystemSet");
		if (StringUtil.isEmpty(crsstUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreSystemSetController]:end viewsCoreSystemSet");
			return;
		}
		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(crsstUuid);

		coreSystemSet = coreSystemSetService.getCoreSystemSet(coreSystemSet);

		CoreSystemSetVO coreSystemSetVO = new CoreSystemSetVO();
		coreSystemSetVO.convertPOToVO(coreSystemSet);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreSystemSetVO), response);
		logger.info("[CoreSystemSetController]:end viewsCoreSystemSet");
	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreSystemSetList (HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin findCoreSystemSetList");
		List<CoreSystemSet> lists = coreSystemSetService.findCoreSystemSetList();
		List<CoreSystemSetVO> vos = new ArrayList<CoreSystemSetVO>();
		CoreSystemSetVO vo;
		for (CoreSystemSet coreSystemSet : lists) {
			vo = new CoreSystemSetVO();
			vo.convertPOToVO(coreSystemSet);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreSystemSetController]:end findCoreSystemSetList");
	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreSystemSetPage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreSystemSetController]:begin findCoreSystemSetPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreSystemSet> page = coreSystemSetService.findCoreSystemSetPage(pageNum, pageSize);
		Page<CoreSystemSetVO> pageVO = new Page<CoreSystemSetVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreSystemSetVO> vos = new ArrayList<CoreSystemSetVO>();
		CoreSystemSetVO vo;
		for (CoreSystemSet coreSystemSet : page.getResult()) {
			vo = new CoreSystemSetVO();
			vo.convertPOToVO(coreSystemSet);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreSystemSetController]:end findCoreSystemSetPage");
	}

}