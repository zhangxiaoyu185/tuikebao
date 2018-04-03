package com.xiaoyu.lingdian.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;

import javax.servlet.http.HttpServletResponse;

import com.xiaoyu.lingdian.core.mybatis.page.Page;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.xiaoyu.lingdian.tool.encrypt.MD5Util;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.CoreAccount;
import com.xiaoyu.lingdian.entity.CoreFunction;
import com.xiaoyu.lingdian.entity.CoreRole;
import com.xiaoyu.lingdian.entity.weixin.Constant;
import com.xiaoyu.lingdian.service.CoreAccountService;
import com.xiaoyu.lingdian.service.CoreFunctionService;
import com.xiaoyu.lingdian.service.CoreRoleService;
import com.xiaoyu.lingdian.vo.CoreAccountVO;

@Controller
@RequestMapping(value="/coreAccount")
public class CoreAccountController extends BaseController {

	/**
	* 账户表
	*/
	@Autowired
	private CoreAccountService coreAccountService;
	
	/**
	 * 角色表
	 */
	@Autowired
	private CoreRoleService coreRoleService;
	
	/**
	 * 功能菜单表
	 */
	@Autowired
	private CoreFunctionService coreFunctionService;
	
	/**
	 * 登录
	 * 
	 * @param userName  用户名
	 * @param pwd 密码
	 * @param response
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(String userName, String pwd, HttpServletResponse response) {
		logger.info("[CoreAccountController]:begin login");
		if (StringUtil.isEmpty(userName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户名不能为空!"), response);
			logger.info("[CoreAccountController]:end login");
			return;
		}
		if (StringUtil.isEmpty(pwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码不能为空!"), response);
			logger.info("[CoreAccountController]:end login");
			return;
		}
		
		CoreAccount coreAccount = new CoreAccount();
		coreAccount.setCractName(userName);
		CoreAccount account = coreAccountService.getCoreAccountByName(coreAccount);
		if (null == account || !(MD5Util.encode(pwd, Constant.DEFAULT_CHARSET)).equalsIgnoreCase(account.getCractPassword())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户或密码错误！"), response);
			logger.info("[CoreAccountController]:end login");
			return;
		}
		
		CoreAccountVO coreAccountVO = new CoreAccountVO();
		coreAccountVO.convertPOToVO(account);
		
		List<CoreFunction> functionList = new ArrayList<CoreFunction>();
		// 返回菜单
		if (account.getCractRoles() != null) {
			String[] roles = account.getCractRoles().split("\\|");
			CoreRole coreRole;
			CoreFunction coreFunction;
			for (int i = 0; i < roles.length; i++) { // 获取角色
				// 根据角色查询功能菜单
				coreRole = new CoreRole();
				coreRole.setCrrolUuid((roles[i]));
				CoreRole role = coreRoleService.getCoreRole(coreRole);
				if (role != null && role.getCrrolFuns() != null) {
					String[] menus = role.getCrrolFuns().split("\\|");
					for (int y = 0; y < menus.length; y++) { // 获取菜单权限
						coreFunction = new CoreFunction();
						coreFunction.setCrfntUuid(menus[y]);
						CoreFunction function = coreFunctionService.getCoreFunction(coreFunction);
						if (null != function && function.getCrfntStatus() == 1 && Collections.frequency(functionList, function) < 1) {
							//去重
							functionList.add(function);
						}
					}
				}
			}
		}
		coreAccountVO.setCoreFunctions(functionList);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "登录成功!", coreAccountVO), response);
		logger.info("[CoreAccountController]:end login");
	}
	
	/**
	* 添加
	*
	* @param cractName 帐户名称
	* @param cractPassword 登录密码
	* @param cractRoles 角色集合
	* @param cractTel 联系方式
	* @param cractEmail 邮箱
	* @param cractRemarks 备注
	* @return
	*/
	@RequestMapping(value="/add/coreAccount", method=RequestMethod.POST)
	public void addCoreAccount (String cractName, String cractPassword, String cractRoles, String cractTel, String cractEmail, String cractRemarks, HttpServletResponse response) {
		logger.info("[CoreAccountController]:begin addCoreAccount");

		if (StringUtil.isEmpty(cractName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[帐户名称]不能为空!"), response);
			logger.info("[CoreAccountController]:end addCoreAccount");
			return;
		}	
		if(StringUtil.isEmpty(cractPassword)){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[账户密码]不能为空!"), response);
			logger.info("[CoreAccountController]:end addCoreAccount");
			return;
		}		
		if(StringUtil.isEmpty(cractRoles)){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[账户角色]不能为空!"), response);
			logger.info("[CoreAccountController]:end addCoreAccount");
			return;
		}
		if (!StringUtil.isEmpty(cractTel)) {
			if (!StringUtil.isPhone(cractTel)) {
				if (!StringUtil.isMobile(cractTel)) {
					writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "联系方式格式不对!"), response);
					logger.info("[CoreAccountController]:end addCoreAccount");
					return;
				}
			}
		}
		if (!StringUtil.isEmpty(cractEmail)) {
			if (!StringUtil.isEmail(cractEmail)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邮箱格式不对!"), response);
				logger.info("[CoreAccountController]:end addCoreAccount");
				return;
			}
		}
				CoreAccount coreAccount = new CoreAccount();
		coreAccount.setCractName(cractName);
		CoreAccount account =coreAccountService.getCoreAccountByName(coreAccount);
		if(account != null){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "此账户已存在!"), response);
			logger.info("[CoreAccountController]:end addCoreAccount");
			return;
		}
		
		String uuid = RandomUtil.generateString(16);
		coreAccount.setCractUuid(uuid);
		String md5Pwd = MD5Util.encode(cractPassword, Constant.DEFAULT_CHARSET);
		coreAccount.setCractPassword(md5Pwd);
		coreAccount.setCractStatus(1);
		coreAccount.setCractRoles(cractRoles);
		coreAccount.setCractCdate(new Date());
		coreAccount.setCractUdate(new Date());
		coreAccount.setCractTel(cractTel);
		coreAccount.setCractEmail(cractEmail);
		coreAccount.setCractRemarks(cractRemarks);

		coreAccountService.insertCoreAccount(coreAccount);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreAccountController]:end addCoreAccount");

	}

	/**
	 * 重置密码
	 * 
	 * @param cractUuid
	 * @param response
	 */
	@RequestMapping(value = "/reset/pwd", method = RequestMethod.POST)
	public void resetPwd(String cractUuid, HttpServletResponse response) {
		logger.info("[CoreAccountController.resetPwd]:begin resetPwd.");
		if (StringUtils.isBlank(cractUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入用户UUID！"), response);
			logger.info("[CoreAccountController]:end resetPwd");
			return;
		}
		
		CoreAccount coreAccount = new CoreAccount();
		coreAccount.setCractUuid(cractUuid);
		String newMd5PWD = MD5Util.encode("123456", Constant.DEFAULT_CHARSET);
		coreAccount.setCractPassword(newMd5PWD);
		coreAccount.setCractUdate(new Date());
		coreAccountService.updateCoreAccount(coreAccount);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "重置密码成功！"), response);
		logger.info("[CoreAccountController.resetPwd]:end resetPwd.");
	}
	
	/**
	 * 修改密码
	 * 
	 * @param cractUuid
	 * @param oldPwd
	 * @param newPwd
	 * @param confirmPwd
	 * @param response
	 */
	@RequestMapping(value = "/update/pwd", method = RequestMethod.POST)
	public void updatePwd(String cractUuid, String oldPwd, String newPwd, String confirmPwd, HttpServletResponse response) {
		logger.info("[CoreAccountController.updatePwd]:begin updatePwd.");
		if (StringUtils.isBlank(cractUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入用户UUID！"), response);
			logger.info("[CoreAccountController]:end updatePwd");
			return;
		}		
		if (StringUtils.isBlank(oldPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入旧密码！"), response);
			logger.info("[CoreAccountController]:end updatePwd");
			return;
		}		
		if (StringUtils.isBlank(newPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入新密码！"), response);
			logger.info("[CoreAccountController]:end updatePwd");
			return;
		}		
		if (StringUtils.isBlank(confirmPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入确认密码！"), response);
			logger.info("[CoreAccountController]:end updatePwd");
			return;
		}		
		if (!newPwd.equals(confirmPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码输入不一致！"), response);
			logger.info("[CoreAccountController]:end updatePwd");
			return;
		}

		CoreAccount coreAccount = new CoreAccount();
		coreAccount.setCractUuid(cractUuid);
		coreAccount = coreAccountService.getCoreAccount(coreAccount);		
		String oldMd5PWD = MD5Util.encode(oldPwd, Constant.DEFAULT_CHARSET);	
		if(coreAccount == null || !oldMd5PWD.equals(coreAccount.getCractPassword())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "原密码错误！"), response);
			logger.info("[CoreAccountController]:end updatePwd");
			return;
		}
		String newMd5PWD = MD5Util.encode(newPwd, Constant.DEFAULT_CHARSET);
		coreAccount.setCractPassword(newMd5PWD);
		coreAccount.setCractUdate(new Date());
		coreAccountService.updateCoreAccount(coreAccount);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改密码成功！"), response);
		logger.info("[CoreAccountController.updatePwd]:end updatePwd.");
	}
	
	/**
	* 修改
	*
	* @param cractUuid 标识UUID
	* @param cractRoles 角色集合
	* @param cractTel 联系方式
	* @param cractEmail 邮箱
	* @param cractRemarks 备注
	* @return
	*/
	@RequestMapping(value="/update/coreAccount", method=RequestMethod.POST)
	public void updateCoreAccount (String cractUuid, String cractRoles, String cractTel, String cractEmail, String cractRemarks, HttpServletResponse response) {
		logger.info("[CoreAccountController]:begin updateCoreAccount");
		if (StringUtil.isEmpty(cractUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreAccountController]:end updateCoreAccount");
			return;
		}
		if(StringUtil.isEmpty(cractRoles)){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[账户角色]不能为空!"), response);
			logger.info("[CoreAccountController]:end updateCoreAccount");
			return;
		}
		if (!StringUtil.isEmpty(cractTel)) {
			if (!StringUtil.isPhone(cractTel)) {
				if (!StringUtil.isMobile(cractTel)) {
					writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "联系方式格式不对!"), response);
					logger.info("[CoreAccountController]:end addCoreAccount");
					return;
				}
			}
		}
		if (!StringUtil.isEmpty(cractEmail)) {
			if (!StringUtil.isEmail(cractEmail)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "邮箱格式不对!"), response);
				logger.info("[CoreAccountController]:end addCoreAccount");
				return;
			}
		}
		CoreAccount coreAccount = new CoreAccount();
		coreAccount.setCractUuid(cractUuid);
		coreAccount.setCractRoles(cractRoles);
		coreAccount.setCractUdate(new Date());
		coreAccount.setCractTel(cractTel);
		coreAccount.setCractEmail(cractEmail);
		coreAccount.setCractRemarks(cractRemarks);

		coreAccountService.updateCoreAccount(coreAccount);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreAccountController]:end updateCoreAccount");

	}

	/**
	* 删除
	*
	* @param cractUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreAccount (String cractUuid, HttpServletResponse response) {
		logger.info("[CoreAccountController]:begin deleteCoreAccount");
		if (StringUtil.isEmpty(cractUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreAccountController]:end deleteCoreAccount");
			return;
		}
		CoreAccount coreAccount = new CoreAccount();
		coreAccount.setCractUuid(cractUuid);
		coreAccount.setCractStatus(0);

		coreAccountService.updateCoreAccount(coreAccount);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreAccountController]:end deleteCoreAccount");

	}

	/**
	* 批量删除
	*
	* @param cractUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreAccount (String cractUuids, HttpServletResponse response) {
		logger.info("[CoreAccountController]:begin deleteBatchCoreAccount");
		if (StringUtil.isEmpty(cractUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[CoreAccountController]:end deleteBatchCoreAccount");
			return;
		}
		String[] uuids = cractUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreAccountController]:end deleteBatchCoreAccount");
			return;
		}
		for (String strUuid : list) {
			CoreAccount coreAccount = new CoreAccount();
			coreAccount.setCractUuid(strUuid);
			coreAccount.setCractStatus(0);

			coreAccountService.updateCoreAccount(coreAccount);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreAccountController]:end deleteBatchCoreAccount");

	}

	/**
	* 获取单个
	*
	* @param cractUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreAccount (String cractUuid, HttpServletResponse response) {
		logger.info("[CoreAccountController]:begin viewsCoreAccount");
		if (StringUtil.isEmpty(cractUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreAccountController]:end viewsCoreAccount");
			return;
		}
		CoreAccount coreAccount = new CoreAccount();
		coreAccount.setCractUuid(cractUuid);
		coreAccount = coreAccountService.getCoreAccount(coreAccount);
		
		if(null == coreAccount){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "账户不存在!"),response);
			logger.info("[CoreAccountController]:end viewsCoreAccount");
			return;
		}
		
		CoreAccountVO coreAccountVO = new CoreAccountVO();
		coreAccountVO.convertPOToVO(coreAccount);
		String role = coreAccount.getCractRoles();
		String [] roles = role.split("\\|");
		List<String> uuids = new ArrayList<String>();
		Collections.addAll(uuids, roles);
		List<CoreRole> coreRoles = coreRoleService.findCoreRoleByUuids(uuids);
		if(coreRoles.size() > 0) {
			StringBuffer rolesName = new StringBuffer();
			for (CoreRole coreRole : coreRoles) {
				rolesName.append(coreRole.getCrrolName()+"|");
			}
			coreAccountVO.setCractRolesName(rolesName.toString());
		} else {
			coreAccountVO.setCractRolesName("");
		}
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreAccountVO), response);
		logger.info("[CoreAccountController]:end viewsCoreAccount");

	}

	/**
	* 获取分页列表<Page>
	* 
	* @param cractName 帐户名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreAccountPage (String cractName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreAccountController]:begin findCoreAccountPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}

		Page<CoreAccount> page = coreAccountService.findCoreAccountPage(cractName, pageNum, pageSize);
		Page<CoreAccountVO> pageVO = new Page<CoreAccountVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreAccountVO> vos = new ArrayList<CoreAccountVO>();
		CoreAccountVO vo;
		for (CoreAccount coreAccount : page.getResult()) {
			vo = new CoreAccountVO();			vo.convertPOToVO(coreAccount);
			String role = coreAccount.getCractRoles();
			String [] roles = role.split("\\|");
			List<String> uuids = new ArrayList<String>();
			Collections.addAll(uuids, roles);
			List<CoreRole> coreRoles = coreRoleService.findCoreRoleByUuids(uuids);
			if(coreRoles.size() > 0) {
				StringBuffer rolesName = new StringBuffer();
				for (CoreRole coreRole : coreRoles) {
					rolesName.append(coreRole.getCrrolName()+"|");
				}
				vo.setCractRolesName(rolesName.toString());
			} else {
				vo.setCractRolesName("");
			}
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreAccountController]:end findCoreAccountPage");

	}

}