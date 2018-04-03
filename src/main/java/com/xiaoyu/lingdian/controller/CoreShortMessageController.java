package com.xiaoyu.lingdian.controller;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.xiaoyu.lingdian.tool.BaseConstant;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.SendSMSUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.CoreShortMessage;
import com.xiaoyu.lingdian.entity.CoreSystemSet;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreShortMessageService;
import com.xiaoyu.lingdian.service.CoreSystemSetService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.vo.CoreShortMessageVO;

@Controller
@RequestMapping(value="/coreShortMessage")
public class CoreShortMessageController extends BaseController {

	/**
	* 短信日志记录表
	*/
	@Autowired
	private CoreShortMessageService coreShortMessageService;
	
	/**
	* 系统设置表
	*/
	@Autowired
	private CoreSystemSetService coreSystemSetService;

	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;

	/**
	 * 分享端注册发送短信
	 * 
	 * @param crmceMobile
	 * @param response
	 */
	@RequestMapping(value="/share/register/send", method=RequestMethod.POST)
	public void shareRegisterSendSms(String crmceMobile, HttpServletResponse response) {
		logger.info("[CoreShortMessageController]:begin shareRegisterSendSms");	
		if (StringUtil.isEmpty(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号必传!"), response);
			logger.info("[CoreShortMessageController]:end shareRegisterSendSms");
			return;
		}
		if(!StringUtil.isMobile(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreShortMessageController]:end shareRegisterSendSms");
			return;
		}
		
		CoreUser coreUser = coreUserService.getCoreUserByMobile(crmceMobile);
		if(null != coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号已存在，请直接登录！"), response);
			logger.info("[CoreShortMessageController]:end shareRegisterSendSms");
			return;
		}
		
		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(BaseConstant.SYSTEM_SET_UUID);
		coreSystemSet = coreSystemSetService.getCoreSystemSet(coreSystemSet);
		if (null == coreSystemSet) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先配置短信账户信息!"), response);
			logger.info("[CoreShortMessageController]:end shareRegisterSendSms");
			return;
		}

		//判断今日短信发送是否超过限制
		int count = coreShortMessageService.getCoreShortMessageCountByMobile(crmceMobile);
		if (count >= 5) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "今日发送短信数量已超过限制5条,请明日再操作!"), response);
			logger.info("[CoreShortMessageController]:end shareRegisterSendSms");
			return;
		}

		Date date = new Date();
		String uuid = RandomUtil.generateString(16);
		String code = RandomUtil.generateMixNum(6);
		boolean flag = false;
		try {
			flag = SendSMSUtil.sendShareRegister(coreSystemSet.getCrsstMessagePath(), coreSystemSet.getCrsstMessageKey(), crmceMobile, code, coreSystemSet.getCrsstMessageLoginname(), coreSystemSet.getCrsstMessagePwd());
		} catch (Exception e) {
			logger.info("分享端注册发送短信失败！");
		}
		if (!flag) {
			CoreShortMessage coreShortMessage = new CoreShortMessage();		
			coreShortMessage.setCrmceUuid(uuid);
			coreShortMessage.setCrmceMobile(crmceMobile);
			coreShortMessage.setCrmceContent(code);
			coreShortMessage.setCrmceTime(date);
			coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
			coreShortMessage.setCrmceStatus(0);
			coreShortMessage.setCrmceType(1);

			coreShortMessageService.insertCoreShortMessage(coreShortMessage);

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享端注册发送验证码失败!"), response);
			logger.info("[CoreShortMessageController]:end shareRegisterSendSms");
			return;
		}
		
		CoreShortMessage coreShortMessage = new CoreShortMessage();
		coreShortMessage.setCrmceUuid(uuid);
		coreShortMessage.setCrmceMobile(crmceMobile);
		coreShortMessage.setCrmceContent(code);
		coreShortMessage.setCrmceTime(date);
		coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
		coreShortMessage.setCrmceStatus(1);
		coreShortMessage.setCrmceType(1);

		coreShortMessageService.insertCoreShortMessage(coreShortMessage);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "分享端注册发送验证码成功!"),response);
		logger.info("[CoreShortMessageController]:end shareRegisterSendSms");
	}
	
	/**
	 * 商城端注册发送短信
	 * 
	 * @param crmceMobile
	 * @param response
	 */
	@RequestMapping(value="/store/register/send", method=RequestMethod.POST)
	public void storeRegisterSendSms(String crmceMobile, HttpServletResponse response) {
		logger.info("[CoreShortMessageController]:begin storeRegisterSendSms");	
		if (StringUtil.isEmpty(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号必传!"), response);
			logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
			return;
		}
		if(!StringUtil.isMobile(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
			return;
		}

		CoreUser coreUser = coreUserService.getCoreUserByMobile(crmceMobile);
		if(null != coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号已存在，请直接登录！"), response);
			logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
			return;
		}
		
		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(BaseConstant.SYSTEM_SET_UUID);
		coreSystemSet = coreSystemSetService.getCoreSystemSet(coreSystemSet);
		if (null == coreSystemSet) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先配置短信账户信息!"), response);
			logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
			return;
		}

		//判断今日短信发送是否超过限制
		int count = coreShortMessageService.getCoreShortMessageCountByMobile(crmceMobile);
		if (count >= 5) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "今日发送短信数量已超过限制5条,请明日再操作!"), response);
			logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
			return;
		}
		
		Date date = new Date();
		String uuid = RandomUtil.generateString(16);
		String code = RandomUtil.generateMixNum(6);
		boolean flag = false;
		try {
			flag = SendSMSUtil.sendStoreRegister(coreSystemSet.getCrsstMessagePath(), coreSystemSet.getCrsstMessageKey(), crmceMobile, code, coreSystemSet.getCrsstMessageLoginname(), coreSystemSet.getCrsstMessagePwd());
		} catch (Exception e) {
			logger.info("商城端注册发送短信失败！");
		}
		if (!flag) {
			CoreShortMessage coreShortMessage = new CoreShortMessage();		
			coreShortMessage.setCrmceUuid(uuid);
			coreShortMessage.setCrmceMobile(crmceMobile);
			coreShortMessage.setCrmceContent(code);
			coreShortMessage.setCrmceTime(date);
			coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
			coreShortMessage.setCrmceStatus(0);
			coreShortMessage.setCrmceType(1);

			coreShortMessageService.insertCoreShortMessage(coreShortMessage);

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商城端注册发送验证码失败!"), response);
			logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
			return;
		}
		
		CoreShortMessage coreShortMessage = new CoreShortMessage();
		coreShortMessage.setCrmceUuid(uuid);
		coreShortMessage.setCrmceMobile(crmceMobile);
		coreShortMessage.setCrmceContent(code);
		coreShortMessage.setCrmceTime(date);
		coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
		coreShortMessage.setCrmceStatus(1);
		coreShortMessage.setCrmceType(1);

		coreShortMessageService.insertCoreShortMessage(coreShortMessage);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "商城端注册发送验证码成功!"),response);
		logger.info("[CoreShortMessageController]:end storeRegisterSendSms");
	}
	
	/**
	 * 商城端忘记密码发送短信
	 * 
	 * @param crmceMobile
	 * @param response
	 */
	@RequestMapping(value="/store/forget/send", method=RequestMethod.POST)
	public void storeForgetSendSms(String crmceMobile, HttpServletResponse response) {
		logger.info("[CoreShortMessageController]:begin storeForgetSendSms");	
		if (StringUtil.isEmpty(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户手机号必传!"), response);
			logger.info("[CoreShortMessageController]:end storeForgetSendSms");
			return;
		}
		if(!StringUtil.isMobile(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreShortMessageController]:end storeForgetSendSms");
			return;
		}

		CoreUser coreUser = coreUserService.getCoreUserByMobile(crmceMobile);
		if(null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号不存在，请先注册！"), response);
			logger.info("[CoreShortMessageController]:end storeForgetSendSms");
			return;
		}
		
		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(BaseConstant.SYSTEM_SET_UUID);
		coreSystemSet = coreSystemSetService.getCoreSystemSet(coreSystemSet);
		if (null == coreSystemSet) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先配置短信账户信息!"), response);
			logger.info("[CoreShortMessageController]:end storeForgetSendSms");
			return;
		}

		//判断今日短信发送是否超过限制
		int count = coreShortMessageService.getCoreShortMessageCountByMobile(crmceMobile);
		if (count >= 5) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "今日发送短信数量已超过限制5条,请明日再操作!"), response);
			logger.info("[CoreShortMessageController]:end storeForgetSendSms");
			return;
		}
		
		Date date = new Date();
		String uuid = RandomUtil.generateString(16);
		String code = RandomUtil.generateMixNum(6);
		boolean flag = false;
		try {
			flag = SendSMSUtil.sendShareForgetPwd(coreSystemSet.getCrsstMessagePath(), coreSystemSet.getCrsstMessageKey(), crmceMobile, code, coreSystemSet.getCrsstMessageLoginname(), coreSystemSet.getCrsstMessagePwd());
		} catch (Exception e) {
			logger.info("商城端发送忘记密码短信失败！");
		}
		if (!flag) {
			CoreShortMessage coreShortMessage = new CoreShortMessage();		
			coreShortMessage.setCrmceUuid(uuid);
			coreShortMessage.setCrmceMobile(crmceMobile);
			coreShortMessage.setCrmceContent(code);
			coreShortMessage.setCrmceTime(date);
			coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
			coreShortMessage.setCrmceStatus(0);
			coreShortMessage.setCrmceType(1);

			coreShortMessageService.insertCoreShortMessage(coreShortMessage);

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商城端发送忘记密码验证码失败!"), response);
			logger.info("[CoreShortMessageController]:end storeForgetSendSms");
			return;
		}
		
		CoreShortMessage coreShortMessage = new CoreShortMessage();
		coreShortMessage.setCrmceUuid(uuid);
		coreShortMessage.setCrmceMobile(crmceMobile);
		coreShortMessage.setCrmceContent(code);
		coreShortMessage.setCrmceTime(date);
		coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
		coreShortMessage.setCrmceStatus(1);
		coreShortMessage.setCrmceType(1);

		coreShortMessageService.insertCoreShortMessage(coreShortMessage);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "商城端发送忘记密码验证码成功!"),response);
		logger.info("[CoreShortMessageController]:end storeForgetSendSms");
	}
	
	/**
	 * 分享端忘记密码发送短信
	 * 
	 * @param crmceMobile
	 * @param response
	 */
	@RequestMapping(value="/share/forget/send", method=RequestMethod.POST)
	public void shareForgetSendSms(String crmceMobile, HttpServletResponse response) {
		logger.info("[CoreShortMessageController]:begin shareForgetSendSms");	
		if (StringUtil.isEmpty(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户手机号必传!"), response);
			logger.info("[CoreShortMessageController]:end shareForgetSendSms");
			return;
		}
		if(!StringUtil.isMobile(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreShortMessageController]:end shareForgetSendSms");
			return;
		}

		CoreUser coreUser = coreUserService.getCoreUserByMobile(crmceMobile);
		if(null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号不存在，请先注册！"), response);
			logger.info("[CoreShortMessageController]:end shareForgetSendSms");
			return;
		}
		
		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(BaseConstant.SYSTEM_SET_UUID);
		coreSystemSet = coreSystemSetService.getCoreSystemSet(coreSystemSet);
		if (null == coreSystemSet) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先配置短信账户信息!"), response);
			logger.info("[CoreShortMessageController]:end shareForgetSendSms");
			return;
		}

		//判断今日短信发送是否超过限制
		int count = coreShortMessageService.getCoreShortMessageCountByMobile(crmceMobile);
		if (count >= 5) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "今日发送短信数量已超过限制5条,请明日再操作!"), response);
			logger.info("[CoreShortMessageController]:end shareForgetSendSms");
			return;
		}
		
		Date date = new Date();
		String uuid = RandomUtil.generateString(16);
		String code = RandomUtil.generateMixNum(6);
		boolean flag = false;
		try {
			flag = SendSMSUtil.sendShareForgetPwd(coreSystemSet.getCrsstMessagePath(), coreSystemSet.getCrsstMessageKey(), crmceMobile, code, coreSystemSet.getCrsstMessageLoginname(), coreSystemSet.getCrsstMessagePwd());
		} catch (Exception e) {
			logger.info("分享端发送忘记密码短信失败！");
		}
		if (!flag) {
			CoreShortMessage coreShortMessage = new CoreShortMessage();		
			coreShortMessage.setCrmceUuid(uuid);
			coreShortMessage.setCrmceMobile(crmceMobile);
			coreShortMessage.setCrmceContent(code);
			coreShortMessage.setCrmceTime(date);
			coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
			coreShortMessage.setCrmceStatus(0);
			coreShortMessage.setCrmceType(1);

			coreShortMessageService.insertCoreShortMessage(coreShortMessage);

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享端发送忘记密码验证码失败!"), response);
			logger.info("[CoreShortMessageController]:end shareForgetSendSms");
			return;
		}
		
		CoreShortMessage coreShortMessage = new CoreShortMessage();
		coreShortMessage.setCrmceUuid(uuid);
		coreShortMessage.setCrmceMobile(crmceMobile);
		coreShortMessage.setCrmceContent(code);
		coreShortMessage.setCrmceTime(date);
		coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
		coreShortMessage.setCrmceStatus(1);
		coreShortMessage.setCrmceType(1);

		coreShortMessageService.insertCoreShortMessage(coreShortMessage);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "分享端发送忘记密码验证码成功!"),response);
		logger.info("[CoreShortMessageController]:end shareForgetSendSms");
	}
	
	/**
	* 申请提现发送短信
	*
	* @param crmceUser 所属用户
	* @param crmceMobile 所属用户手机号
	* @return
	*/
	@RequestMapping(value="/add/coreShortMessage", method=RequestMethod.POST)
	public void addCoreShortMessage (String crmceUser, String crmceMobile, HttpServletResponse response) {
		logger.info("[CoreShortMessageController]:begin addCoreShortMessage");

		if (StringUtil.isEmpty(crmceUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属用户必传!"), response);
			logger.info("[CoreShortMessageController]:end addCoreShortMessage");
			return;
		}
		if (StringUtil.isEmpty(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属用户手机号必传!"), response);
			logger.info("[CoreShortMessageController]:end addCoreShortMessage");
			return;
		}
		if(!StringUtil.isMobile(crmceMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreShortMessageController]:end addCoreShortMessage");
			return;
		}

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crmceUser);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属用户不存在!"), response);
			logger.info("[CoreShortMessageController]:end addCoreShortMessage");
			return;
		}
		if (!crmceMobile.equals(coreUser.getCrusrMobile())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "发送手机号与用户手机号不匹配!"), response);
			logger.info("[CoreShortMessageController]:end addCoreShortMessage");
			return;
		}
		
		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(BaseConstant.SYSTEM_SET_UUID);
		coreSystemSet = coreSystemSetService.getCoreSystemSet(coreSystemSet);
		if (null == coreSystemSet) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先配置短信账户信息!"), response);
			logger.info("[CoreShortMessageController]:end addCoreShortMessage");
			return;
		}

		//判断今日短信发送是否超过限制
		int count = coreShortMessageService.getCoreShortMessageCountByMobile(crmceMobile);
		if (count >= 5) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "今日发送短信数量已超过限制5条,请明日再操作!"), response);
			logger.info("[CoreShortMessageController]:end addCoreShortMessage");
			return;
		}
		
		Date date = new Date();
		String uuid = RandomUtil.generateString(16);
		String code = RandomUtil.generateMixNum(6);
		boolean flag = false;
		try {
			flag = SendSMSUtil.sendCash(coreSystemSet.getCrsstMessagePath(), coreSystemSet.getCrsstMessageKey(), crmceMobile, code, coreSystemSet.getCrsstMessageLoginname(), coreSystemSet.getCrsstMessagePwd());
		} catch (Exception e) {
			logger.info("发送提现短信失败！");
		}
		if (!flag) {
			CoreShortMessage coreShortMessage = new CoreShortMessage();		
			coreShortMessage.setCrmceUuid(uuid);
			coreShortMessage.setCrmceMobile(crmceMobile);
			coreShortMessage.setCrmceContent(code);
			coreShortMessage.setCrmceTime(date);
			coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
			coreShortMessage.setCrmceStatus(0);
			coreShortMessage.setCrmceType(1);

			coreShortMessageService.insertCoreShortMessage(coreShortMessage);

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "发送提现验证码失败!"), response);
			logger.info("[CoreShortMessageController]:end addCoreShortMessage");
			return;
		}
				CoreShortMessage coreShortMessage = new CoreShortMessage();
		coreShortMessage.setCrmceUuid(uuid);
		coreShortMessage.setCrmceMobile(crmceMobile);
		coreShortMessage.setCrmceContent(code);
		coreShortMessage.setCrmceTime(date);
		coreShortMessage.setCrmceTimeout(DateUtil.addMinute(date, 10));
		coreShortMessage.setCrmceStatus(1);
		coreShortMessage.setCrmceType(1);

		coreShortMessageService.insertCoreShortMessage(coreShortMessage);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "发送提现验证码成功!"),response);
		logger.info("[CoreShortMessageController]:end addCoreShortMessage");
	}

	/**
	* 获取单个
	*
	* @param crmceUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreShortMessage (String crmceUuid, HttpServletResponse response) {
		logger.info("[CoreShortMessageController]:begin viewsCoreShortMessage");
		if (StringUtil.isEmpty(crmceUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreShortMessageController]:end viewsCoreShortMessage");
			return;
		}
		CoreShortMessage coreShortMessage = new CoreShortMessage();
		coreShortMessage.setCrmceUuid(crmceUuid);
		coreShortMessage = coreShortMessageService.getCoreShortMessage(coreShortMessage);
		if (null == coreShortMessage) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreShortMessageController]:end viewsCoreShortMessage");
			return;
		}

		CoreShortMessageVO coreShortMessageVO = new CoreShortMessageVO();
		coreShortMessageVO.convertPOToVO(coreShortMessage);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreShortMessageVO), response);
		logger.info("[CoreShortMessageController]:end viewsCoreShortMessage");

	}

	/**
	* 获取列表<Page>
	* 
	* @param crmceMobile 手机号
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreShortMessagePage (String crmceMobile, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreShortMessageController]:begin findCoreShortMessagePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreShortMessage> page = coreShortMessageService.findCoreShortMessagePage(crmceMobile, pageNum, pageSize);
		Page<CoreShortMessageVO> pageVO = new Page<CoreShortMessageVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreShortMessageVO> vos = new ArrayList<CoreShortMessageVO>();

		CoreShortMessageVO vo;
		for (CoreShortMessage coreShortMessage : page.getResult()) {
			vo = new CoreShortMessageVO();			vo.convertPOToVO(coreShortMessage);			
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreShortMessageController]:end findCoreShortMessagePage");

	}

}