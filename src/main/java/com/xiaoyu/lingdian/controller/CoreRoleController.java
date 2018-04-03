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
import com.xiaoyu.lingdian.entity.CoreFunction;
import com.xiaoyu.lingdian.entity.CoreRole;
import com.xiaoyu.lingdian.service.CoreFunctionService;
import com.xiaoyu.lingdian.service.CoreRoleService;
import com.xiaoyu.lingdian.vo.CoreRoleVO;

@Controller
@RequestMapping(value="/coreRole")
public class CoreRoleController extends BaseController {

	/**
	* 角色表
	*/
	@Autowired
	private CoreRoleService coreRoleService;

	/**
	* 功能项表
	*/
	@Autowired
	private CoreFunctionService coreFunctionService;

	/**
	* 添加(功能菜单用"|"隔开)
	*
	* @param crrolName 角色名称
	* @param crrolFuns 功能菜单集合
	* @param crrolDesc 角色描述
	* @return
	*/
	@RequestMapping(value="/add/coreRole", method=RequestMethod.POST)
	public void addCoreRole (String crrolName, String crrolFuns, String crrolDesc, HttpServletResponse response) {
		logger.info("[CoreRoleController]:begin addCoreRole");

		if (StringUtil.isEmpty(crrolName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "角色名称不能为空!"), response);
			logger.info("[CoreRoleController]:end addCoreRole");
			return;
		}
		if (StringUtil.isEmpty(crrolFuns)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "功能菜单集合不能为空!"), response);
			logger.info("[CoreRoleController]:end addCoreRole");
			return;
		}
				CoreRole coreRole = new CoreRole();
		String uuid = RandomUtil.generateString(16);
		coreRole.setCrrolUuid(uuid);
		coreRole.setCrrolName(crrolName);
		coreRole.setCrrolFuns(crrolFuns);
		coreRole.setCrrolDesc(crrolDesc);

		coreRoleService.insertCoreRole(coreRole);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreRoleController]:end addCoreRole");

	}

	/**
	* 修改(功能菜单用"|"隔开)
	*
	* @param crrolUuid 标识UUID
	* @param crrolName 角色名称
	* @param crrolFuns 功能菜单集合
	* @param crrolDesc 角色描述
	* @return
	*/
	@RequestMapping(value="/update/coreRole", method=RequestMethod.POST)
	public void updateCoreRole (String crrolUuid, String crrolName, String crrolFuns, String crrolDesc, HttpServletResponse response) {
		logger.info("[CoreRoleController]:begin updateCoreRole");
		if (StringUtil.isEmpty(crrolUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreRoleController]:end updateCoreRole");
			return;
		}
		if (StringUtil.isEmpty(crrolName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "角色名称不能为空!"), response);
			logger.info("[CoreRoleController]:end updateCoreRole");
			return;
		}
		if (StringUtil.isEmpty(crrolFuns)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "功能菜单集合不能为空!"), response);
			logger.info("[CoreRoleController]:end updateCoreRole");
			return;
		}
		CoreRole coreRole = new CoreRole();
		coreRole.setCrrolUuid(crrolUuid);
		coreRole.setCrrolName(crrolName);
		coreRole.setCrrolFuns(crrolFuns);
		coreRole.setCrrolDesc(crrolDesc);

		coreRoleService.updateCoreRole(coreRole);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreRoleController]:end updateCoreRole");

	}

	/**
	* 删除
	*
	* @param crrolUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreRole (String crrolUuid, HttpServletResponse response) {
		logger.info("[CoreRoleController]:begin deleteCoreRole");
		if (StringUtil.isEmpty(crrolUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreRoleController]:end deleteCoreRole");
			return;
		}
		CoreRole coreRole = new CoreRole();
		coreRole.setCrrolUuid(crrolUuid);

		coreRoleService.deleteCoreRole(coreRole);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreRoleController]:end deleteCoreRole");

	}

	/**
	* 批量删除
	*
	* @param crrolUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreRole (String crrolUuids, HttpServletResponse response) {
		logger.info("[CoreRoleController]:begin deleteBatchCoreRole");
		if (StringUtil.isEmpty(crrolUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[CoreRoleController]:end deleteBatchCoreRole");
			return;
		}
		String[] uuids=crrolUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreRoleController]:end deleteBatchCoreRole");
			return;
		}
		coreRoleService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreRoleController]:end deleteBatchCoreRole");

	}

	/**
	* 获取单个
	*
	* @param crrolUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreRole (String crrolUuid, HttpServletResponse response) {
		logger.info("[CoreRoleController]:begin viewsCoreRole");
		if (StringUtil.isEmpty(crrolUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreRoleController]:end viewsCoreRole");
			return;
		}
		CoreRole coreRole = new CoreRole();
		coreRole.setCrrolUuid(crrolUuid);

		coreRole = coreRoleService.getCoreRole(coreRole);
		if (coreRole == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "详情不存在!"), response);
			logger.info("[CoreRoleController]:begin viewsCoreRole");
			return;
		}
		
		CoreRoleVO coreRoleVO = new CoreRoleVO();
		coreRoleVO.convertPOToVO(coreRole);
		//获取权限
		List<String> roleList = new ArrayList<>();
		String[] func = coreRole.getCrrolFuns().split("\\|");
		for (int i = 0; i < func.length; i++) {
			roleList.add(func[i]);
		}
		String functionNames = "";
		List<CoreFunction> coreFunctions = coreFunctionService.findCoreFunctionsByIds(roleList);
		for (CoreFunction coreFunction : coreFunctions) {
			functionNames += coreFunction.getCrfntFunName() + "|";
		}
		coreRoleVO.setCrrolFunsName(functionNames);		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreRoleVO), response);
		logger.info("[CoreRoleController]:end viewsCoreRole");

	}

	/**
	* 获取列表<List>
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreRoleList (HttpServletResponse response) {
		logger.info("[CoreRoleController]:begin findCoreRoleList");
		List<CoreRole> lists = coreRoleService.findCoreRoleList();
		List<CoreRoleVO> vos = new ArrayList<CoreRoleVO>();
		CoreRoleVO vo;
		for (CoreRole coreRole : lists) {
			vo = new CoreRoleVO();			vo.convertPOToVO(coreRole);
			//获取权限
			List<String> roleList = new ArrayList<>();
			String[] func = coreRole.getCrrolFuns().split("\\|");
			for (int i = 0; i < func.length; i++) {
				roleList.add(func[i]);
			}
			String functionNames = "";
			List<CoreFunction> coreFunctions = coreFunctionService.findCoreFunctionsByIds(roleList);
			for (CoreFunction coreFunction : coreFunctions) {
				functionNames += coreFunction.getCrfntFunName() + "|";
			}
			vo.setCrrolFunsName(functionNames);			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreRoleController]:end findCoreRoleList");

	}

	/**
	* 获取分页列表<Page>
	* 
	* @param crrolName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreRolePage (String crrolName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreRoleController]:begin findCoreRolePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}

		Page<CoreRole> page = coreRoleService.findCoreRolePage(crrolName, pageNum, pageSize);
		Page<CoreRoleVO> pageVO = new Page<CoreRoleVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreRoleVO> vos = new ArrayList<CoreRoleVO>();
		CoreRoleVO vo;
		for (CoreRole coreRole : page.getResult()) {
			vo = new CoreRoleVO();			vo.convertPOToVO(coreRole);
			//获取权限
			List<String> roleList = new ArrayList<>();
			String[] func = coreRole.getCrrolFuns().split("\\|");
			for (int i = 0; i < func.length; i++) {
				roleList.add(func[i]);
			}
			String functionNames = "";
			List<CoreFunction> coreFunctions = coreFunctionService.findCoreFunctionsByIds(roleList);
			for (CoreFunction coreFunction : coreFunctions) {
				functionNames += coreFunction.getCrfntFunName() + "|";
			}
			vo.setCrrolFunsName(functionNames);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreRoleController]:end findCoreRolePage");

	}

}