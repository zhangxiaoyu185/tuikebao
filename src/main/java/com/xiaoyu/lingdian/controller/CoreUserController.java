package com.xiaoyu.lingdian.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyu.lingdian.tool.BaseConstant;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.xiaoyu.lingdian.tool.encrypt.MD5Util;
import com.xiaoyu.lingdian.tool.http.HttpUrlConnectionUtil;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.tool.wx.WeixinUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.BusiIntegralRecord;
import com.xiaoyu.lingdian.entity.BusiInvitedRelation;
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.CoreGrade;
import com.xiaoyu.lingdian.entity.CoreShortMessage;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.entity.CoreWechat;
import com.xiaoyu.lingdian.entity.weixin.Constant;
import com.xiaoyu.lingdian.entity.weixin.OpenidAndAccessToken;
import com.xiaoyu.lingdian.service.CoreGradeService;
import com.xiaoyu.lingdian.service.CoreShortMessageService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.CoreWechatService;
import com.xiaoyu.lingdian.service.invited.BusiInvitedRelationService;
import com.xiaoyu.lingdian.service.my.BusiIntegralRecordService;
import com.xiaoyu.lingdian.service.my.BusiMessageCenterService;
import com.xiaoyu.lingdian.vo.CoreUserVO;
import com.xiaoyu.lingdian.vo.other.UserGradeVO;

@Controller
@RequestMapping(value="/coreUser")
public class CoreUserController extends BaseController {

	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;
	
	/**
	* 等级配置表
	*/
	@Autowired
	private CoreGradeService coreGradeService;

	/**
	* 用户积分明细表
	*/
	@Autowired
	private BusiIntegralRecordService busiIntegralRecordService;

	/**
	* 邀请关系表
	*/
	@Autowired
	private BusiInvitedRelationService busiInvitedRelationService;

	/**
	* 用户消息中心表
	*/
	@Autowired
	private BusiMessageCenterService busiMessageCenterService;
	
	/**
	* 短信日志记录表
	*/
	@Autowired
	private CoreShortMessageService coreShortMessageService;

	/**
	* 公众号表
	*/
	@Autowired
	private CoreWechatService coreWechatService;
	
	/**
	* 获取号码归属地
	*
	* @param mobileTel 手机号码
	* @return
	*/
	@RequestMapping(value="/mobile/attach", method=RequestMethod.POST)
	public void getMobileAttach (String mobileTel, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin getMobileAttach");

		if (StringUtil.isEmpty(mobileTel)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号码不能为空!"), response);
			logger.info("[CoreUserController]:end getMobileAttach");
			return;
		}
		if (!StringUtil.isMobile(mobileTel)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号码不符合规范!"), response);
			logger.info("[CoreUserController]:end getMobileAttach");
			return;
		}
		
		String requestUrl = "http://sj.apidata.cn/?mobile=" + mobileTel;
		String message = "号段不存在";
		String strReturn = HttpUrlConnectionUtil.sendGet(requestUrl, null);
		if (!StringUtil.isEmpty(strReturn)) {
			JSONObject jsonObject = (JSONObject) JSONObject.parse(strReturn);
			if (jsonObject.getInteger("status") == 1) {
				JSONObject addressObject = (JSONObject) jsonObject.get("data");
				message = addressObject.getString("province") + addressObject.getString("city") + addressObject.getString("isp");
			}
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取号码归属地成功", message),response);
		logger.info("[CoreUserController]:end getMobileAttach");
	}
	
	/**
	 * 商城端忘记密码
	 * 
	 * @param mobile
	 * @param password
	 * @param confirmPassword
	 * @param code 验证码
	 * @param response
	 */
	@RequestMapping(value="/store/forget", method=RequestMethod.POST)
	public void storeForget(String mobile, String password, String confirmPassword, String code, HttpServletResponse response) {
		logger.info("[CoreUserController.storeForget]:begin storeForget");
		if (StringUtils.isBlank(code)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入验证码！"), response);
			logger.info("[CoreUserController.storeForget]:end storeForget");
			return;
		}
		if (StringUtils.isBlank(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入手机号！"), response);
			logger.info("[CoreUserController.storeForget]:end storeForget");
			return;
		}
		if(!StringUtil.isMobile(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreUserController.storeForget]:end storeForget");
			return;
		}	
		if (StringUtils.isBlank(password)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入密码！"), response);
			logger.info("[CoreUserController.storeForget]:end storeForget");
			return;
		}
		if (StringUtils.isBlank(confirmPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入确认密码！"), response);
			logger.info("[CoreUserController.storeForget]:end storeForget");
			return;
		}
		if (!password.equals(confirmPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码与确认密码不相同！"), response);
			logger.info("[CoreUserController.storeForget]:end storeForget");
			return;
		}
		
		if(coreUserService.getCoreUserByMobile(mobile) == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号未注册,请直接注册！"), response);
			logger.info("[CoreUserController.storeForget]:end storeForget");
			return;
		}
		
		//判断验证码
		CoreShortMessage coreShortMessage = coreShortMessageService.getCoreShortMessageByMobile(mobile);
		if (null == coreShortMessage) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先发送短信验证码！"), response);
			logger.info("[CoreUserController.storeForget]:end storeForget");
			return;
		}
		
		Date date = new Date();
		
		long secondLong = DateUtil.secondDiff(date, coreShortMessage.getCrmceTimeout());
		if (secondLong < 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该短信验证码已过期,请重新发送！"), response);
			logger.info("[CoreUserController.storeForget]:end storeForget");
			return;
		}
		if (!code.equalsIgnoreCase(coreShortMessage.getCrmceContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信验证码错误！"), response);
			logger.info("[CoreUserController.storeForget]:end storeForget");
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrMobile(mobile);	
		String md5PWD = MD5Util.encode(password, Constant.DEFAULT_CHARSET);
		coreUser.setCrusrPassword(md5PWD);
		coreUser.setCrusrUdate(date);
		coreUserService.updateCoreUserByMobile(coreUser);		
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "忘记密码修改成功！"), response);
		logger.info("[CoreUserController.storeForget]:end storeForget");
	}	

	/**
	 * 商城端注册
	 * 
	 * @param mobile
	 * @param password
	 * @param confirmPassword
	 * @param code 验证码
	 * @param response
	 */
	@RequestMapping(value="/store/register", method=RequestMethod.POST)
	public void storeRegister(String mobile, String password, String confirmPassword, String code, HttpServletResponse response) {
		logger.info("[CoreUserController.storeRegister]:begin storeRegister");
		if (StringUtils.isBlank(code)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入验证码！"), response);
			logger.info("[CoreUserController.storeRegister]:end storeRegister");
			return;
		}
		if (StringUtils.isBlank(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入手机号！"), response);
			logger.info("[CoreUserController.storeRegister]:end storeRegister");
			return;
		}
		if(!StringUtil.isMobile(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreUserController.storeRegister]:end storeRegister");
			return;
		}	
		if (StringUtils.isBlank(password)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入密码！"), response);
			logger.info("[CoreUserController.storeRegister]:end storeRegister");
			return;
		}
		if (StringUtils.isBlank(confirmPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入确认密码！"), response);
			logger.info("[CoreUserController.storeRegister]:end storeRegister");
			return;
		}
		if (!password.equals(confirmPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码与确认密码不相同！"), response);
			logger.info("[CoreUserController.storeRegister]:end storeRegister");
			return;
		}			
		if(coreUserService.getCoreUserByMobile(mobile) != null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号已存在，请直接登录！"), response);
			logger.info("[CoreUserController.storeRegister]:end storeRegister");
			return;
		}
		
		//判断验证码
		CoreShortMessage coreShortMessage = coreShortMessageService.getCoreShortMessageByMobile(mobile);
		if (null == coreShortMessage) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先发送短信验证码！"), response);
			logger.info("[CoreUserController.storeRegister]:end storeRegister");
			return;
		}
		
		Date date = new Date();
		
		long secondLong = DateUtil.secondDiff(date, coreShortMessage.getCrmceTimeout());
		if (secondLong < 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该短信验证码已过期,请重新发送！"), response);
			logger.info("[CoreUserController.storeRegister]:end storeRegister");
			return;
		}
		if (!code.equalsIgnoreCase(coreShortMessage.getCrmceContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信验证码错误！"), response);
			logger.info("[CoreUserController.storeRegister]:end storeRegister");
			return;
		}

		List<CoreGrade> gradeList = coreGradeService.findCoreGradeList();
		if (null == gradeList || null == gradeList.get(0)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "等级未设置,请联系后台管理员!"),response);
			logger.info("[CoreUserController.storeRegister]:end storeRegister");
			return;
		}

		CoreUser coreUser = new CoreUser();
		String uuid = RandomUtil.generateString(16);
		coreUser.setCrusrUuid(uuid);
		coreUser.setCrusrCdate(date);
		coreUser.setCrusrUdate(date);
		coreUser.setCrusrLastTime(date);
		coreUser.setCrusrName(mobile);
		coreUser.setCrusrMobile(mobile);
		coreUser.setCrusrMobileVerify(1);
		coreUser.setCrusrCode(mobile);
		String md5PWD = MD5Util.encode(password, Constant.DEFAULT_CHARSET);
		coreUser.setCrusrPassword(md5PWD);
		coreUser.setCrusrGrade(gradeList.get(0).getCrgdeUuid());
		coreUser.setCrusrGradeIcon(gradeList.get(0).getCrgdeIcon());
		coreUser.setCrusrGradeName(gradeList.get(0).getCrgdeName());
		coreUser.setCrusrType(1);
		coreUser.setCrusrStatus(1);
		coreUser.setCrusrShareIndex(0);
		coreUser.setCrusrInviteCode(RandomUtil.generateMixString(17));
		coreUser.setCrusrGender(1);		
		coreUserService.insertCoreUser(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "注册成功！", coreUser), response);
		logger.info("[CoreUserController.storeRegister]:end storeRegister");
	}
	
	/**
	 * 商城端登录
	 * 
	 * @param mobile
	 * @param password
	 * @param code 授权code值
	 * @param response
	 */
	@RequestMapping(value = "/store/login", method = RequestMethod.POST)
	public void storeLogin(String mobile, String password, String code, HttpServletResponse response) {
		logger.info("[CoreUserController.storeLogin]:begin storeLogin.");
		if (StringUtils.isBlank(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入手机号！"), response);
			logger.info("[CoreUserController.storeLogin]:end storeLogin");
			return;
		}
		if(!StringUtil.isMobile(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreUserController.storeLogin]:end storeLogin");
			return;
		}	
		if (StringUtils.isBlank(password)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入密码！"), response);
			logger.info("[CoreUserController.storeLogin]:end storeLogin");
			return;
		}
		
		CoreUser coreUser = coreUserService.getCoreUserByMobile(mobile);

		String md5PWD = MD5Util.encode(password, Constant.DEFAULT_CHARSET);
		if(coreUser == null || !md5PWD.equalsIgnoreCase(coreUser.getCrusrPassword())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户名或密码错误！"), response);
			logger.info("[CoreUserController.storeLogin]:end storeLogin");
			return;
		}
		
		if (!StringUtils.isBlank(code)) { //每次登陆都更新商城端openid
			//if (coreUser.getCrusrOpenidStore() == null || ("").equals(coreUser.getCrusrOpenidStore())) {			
				CoreWechat coreWechat = new CoreWechat();
				coreWechat.setCrwctUuid(BaseConstant.STORE_WE_CHAT_UUID);
				coreWechat = coreWechatService.getCoreWechat(coreWechat);
				if (null == coreWechat) {
					writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "公众号不存在!"),response);
					logger.info("[CoreUserController.storeLogin]:end storeLogin");
					return;
				}

				OpenidAndAccessToken openidAndAccessToken = WeixinUtil.getOpenIdAndToken(coreWechat.getCrwctAppid(), coreWechat.getCrwctAppsecret(), null, code);
				if (null == openidAndAccessToken || null == openidAndAccessToken.getAccess_token() || null == openidAndAccessToken.getOpenid()) {
					writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "通过code获取网页授权access_token失败,请重试!"),response);
					logger.info("[CoreUserController.storeLogin]:end storeLogin");
					return;
				}
				
				coreUser.setCrusrOpenidStore(openidAndAccessToken.getOpenid());
			//}
		}
		
		//更新最后登录时间
		Date now = new Date();
		coreUser.setCrusrUdate(now);
		coreUser.setCrusrLastTime(now);
		coreUserService.updateCoreUserByMobile(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "登录成功", coreUser), response);
		logger.info("[CoreUserController.storeLogin]:end storeLogin.");
	}
	
	/**
	 * 分享端忘记密码
	 * 
	 * @param mobile
	 * @param password
	 * @param confirmPassword
	 * @param code 验证码
	 * @param response
	 */
	@RequestMapping(value="/share/forget", method=RequestMethod.POST)
	public void shareForget(String mobile, String password, String confirmPassword, String code, HttpServletResponse response) {
		logger.info("[CoreUserController.shareForget]:begin shareForget");
		if (StringUtils.isBlank(code)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入验证码！"), response);
			logger.info("[CoreUserController.shareForget]:end shareForget");
			return;
		}
		if (StringUtils.isBlank(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入手机号！"), response);
			logger.info("[CoreUserController.shareForget]:end shareForget");
			return;
		}
		if(!StringUtil.isMobile(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreUserController.shareForget]:end shareForget");
			return;
		}	
		if (StringUtils.isBlank(password)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入密码！"), response);
			logger.info("[CoreUserController.shareForget]:end shareForget");
			return;
		}
		if (StringUtils.isBlank(confirmPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入确认密码！"), response);
			logger.info("[CoreUserController.shareForget]:end shareForget");
			return;
		}
		if (!password.equals(confirmPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码与确认密码不相同！"), response);
			logger.info("[CoreUserController.shareForget]:end shareForget");
			return;
		}
		
		if(coreUserService.getCoreUserByMobile(mobile) == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号未注册,请直接注册！"), response);
			logger.info("[CoreUserController.shareForget]:end shareForget");
			return;
		}
		
		//判断验证码
		CoreShortMessage coreShortMessage = coreShortMessageService.getCoreShortMessageByMobile(mobile);
		if (null == coreShortMessage) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先发送短信验证码！"), response);
			logger.info("[CoreUserController.shareForget]:end shareForget");
			return;
		}
		
		Date date = new Date();
		
		long secondLong = DateUtil.secondDiff(date, coreShortMessage.getCrmceTimeout());
		if (secondLong < 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该短信验证码已过期,请重新发送！"), response);
			logger.info("[CoreUserController.shareForget]:end shareForget");
			return;
		}
		if (!code.equalsIgnoreCase(coreShortMessage.getCrmceContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信验证码错误！"), response);
			logger.info("[CoreUserController.shareForget]:end shareForget");
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrMobile(mobile);	
		String md5PWD = MD5Util.encode(password, Constant.DEFAULT_CHARSET);
		coreUser.setCrusrPassword(md5PWD);
		coreUser.setCrusrUdate(date);
		coreUserService.updateCoreUserByMobile(coreUser);		
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "忘记密码修改成功！"), response);
		logger.info("[CoreUserController.shareForget]:end shareForget");
	}	

	/**
	 * 分享端注册
	 * 
	 * @param mobile
	 * @param password
	 * @param confirmPassword
	 * @param code 验证码
	 * @param shareUcode 邀请人邀请码
	 * @param response
	 */
	@RequestMapping(value="/share/register", method=RequestMethod.POST)
	public void shareRegister(String mobile, String password, String confirmPassword, String code, String shareUcode, HttpServletResponse response) {
		logger.info("[CoreUserController.shareRegister]:begin shareRegister");
		if (StringUtils.isBlank(code)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入验证码！"), response);
			logger.info("[CoreUserController.shareRegister]:end shareRegister");
			return;
		}
		if (StringUtils.isBlank(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入手机号！"), response);
			logger.info("[CoreUserController.shareRegister]:end shareRegister");
			return;
		}
		if(!StringUtil.isMobile(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreUserController.shareRegister]:end shareRegister");
			return;
		}	
		if (StringUtils.isBlank(password)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入密码！"), response);
			logger.info("[CoreUserController.shareRegister]:end shareRegister");
			return;
		}
		if (StringUtils.isBlank(confirmPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入确认密码！"), response);
			logger.info("[CoreUserController.shareRegister]:end shareRegister");
			return;
		}
		if (!password.equals(confirmPassword)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码与确认密码不相同！"), response);
			logger.info("[CoreUserController.shareRegister]:end shareRegister");
			return;
		}			
		if(coreUserService.getCoreUserByMobile(mobile) != null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该手机号已存在，请直接登录！"), response);
			logger.info("[CoreUserController.shareRegister]:end shareRegister");
			return;
		}
		
		//判断验证码
		CoreShortMessage coreShortMessage = coreShortMessageService.getCoreShortMessageByMobile(mobile);
		if (null == coreShortMessage) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先发送短信验证码！"), response);
			logger.info("[CoreUserController.shareRegister]:end shareRegister");
			return;
		}
		
		Date date = new Date();
		
		long secondLong = DateUtil.secondDiff(date, coreShortMessage.getCrmceTimeout());
		if (secondLong < 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该短信验证码已过期,请重新发送！"), response);
			logger.info("[CoreUserController.shareRegister]:end shareRegister");
			return;
		}
		if (!code.equalsIgnoreCase(coreShortMessage.getCrmceContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信验证码错误！"), response);
			logger.info("[CoreUserController.shareRegister]:end shareRegister");
			return;
		}

		List<CoreGrade> gradeList = coreGradeService.findCoreGradeList();
		if (null == gradeList || null == gradeList.get(0)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "等级未设置,请联系后台管理员!"),response);
			logger.info("[CoreUserController.shareRegister]:end shareRegister");
			return;
		}

		CoreUser coreUser = new CoreUser();
		String uuid = RandomUtil.generateString(16);
		coreUser.setCrusrUuid(uuid);
		coreUser.setCrusrCdate(date);
		coreUser.setCrusrUdate(date);
		coreUser.setCrusrLastTime(date);
		coreUser.setCrusrName(mobile);
		coreUser.setCrusrMobile(mobile);
		coreUser.setCrusrMobileVerify(1);
		coreUser.setCrusrCode(mobile);
		String md5PWD = MD5Util.encode(password, Constant.DEFAULT_CHARSET);
		coreUser.setCrusrPassword(md5PWD);
		coreUser.setCrusrGrade(gradeList.get(0).getCrgdeUuid());
		coreUser.setCrusrGradeIcon(gradeList.get(0).getCrgdeIcon());
		coreUser.setCrusrGradeName(gradeList.get(0).getCrgdeName());
		coreUser.setCrusrType(1);
		coreUser.setCrusrStatus(1);
		coreUser.setCrusrShareIndex(1);
		coreUser.setCrusrInviteCode(RandomUtil.generateMixString(17));
		coreUser.setCrusrGender(1);			
		
		if (!StringUtil.isEmpty(shareUcode)) { //有邀请码，存关联关系
			//根据邀请码查询用户信息
			CoreUser inviteUser = coreUserService.getCoreUserByInviteCode(shareUcode);
			if (null != inviteUser) {
				coreUser.setCrusrInviter(inviteUser.getCrusrUuid());
				
				//更新用户的累计邀请人数和活动邀请人数
				coreUserService.updateCoreUserByInvitedCount(inviteUser.getCrusrUuid(), 1, 1);
			}
			
			BusiInvitedRelation busiInvitedRelation = new BusiInvitedRelation();
			busiInvitedRelation.setBsirnUuid(RandomUtil.generateString(16));
			busiInvitedRelation.setBsirnInvited(inviteUser.getCrusrUuid());
			busiInvitedRelation.setBsirnBeInvited(uuid);
			busiInvitedRelation.setBsirnCode(shareUcode);
			busiInvitedRelation.setBsirnIdate(new Date());
			busiInvitedRelationService.insertBusiInvitedRelation(busiInvitedRelation);
		}
		
		coreUserService.insertCoreUser(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "注册成功！", coreUser), response);
		logger.info("[CoreUserController.shareRegister]:end shareRegister");
	}
	
	/**
	 * 分享端登录
	 * 
	 * @param mobile
	 * @param password
	 * @param response
	 */
	@RequestMapping(value = "/share/login", method = RequestMethod.POST)
	public void shareLogin(String mobile, String password, HttpServletResponse response) {
		logger.info("[CoreUserController.shareLogin]:begin shareLogin.");
		if (StringUtils.isBlank(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入手机号！"), response);
			logger.info("[CoreUserController.shareLogin]:end shareLogin");
			return;
		}
		if(!StringUtil.isMobile(mobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号格式错误！"), response);
			logger.info("[CoreUserController.shareLogin]:end shareLogin");
			return;
		}	
		if (StringUtils.isBlank(password)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入密码！"), response);
			logger.info("[CoreUserController.shareLogin]:end shareLogin");
			return;
		}
		
		CoreUser coreUser = coreUserService.getCoreUserByMobile(mobile);

		String md5PWD = MD5Util.encode(password, Constant.DEFAULT_CHARSET);
		if(coreUser == null || !md5PWD.equalsIgnoreCase(coreUser.getCrusrPassword())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户名或密码错误！"), response);
			logger.info("[CoreUserController.shareLogin]:end shareLogin");
			return;
		}
		
		//更新最后登录时间
		Date now = new Date();
		coreUser.setCrusrUdate(now);
		coreUser.setCrusrLastTime(now);
		coreUserService.updateCoreUserByMobile(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "登录成功", coreUser), response);
		logger.info("[CoreUserController.shareLogin]:end shareLogin.");
	}
	
	/**
	* 启用
	*
	* @param crusrUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable (String crusrUuid, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin enable");

		if (StringUtil.isEmpty(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreUserController]:end enable");
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser.setCrusrStatus(1);
		coreUser.setCrusrUdate(new Date());

		coreUserService.updateCoreUser(coreUser);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "启用成功!"),response);
		logger.info("[CoreUserController]:end enable");
	}
	
	/**
	* 禁用
	*
	* @param crusrUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable (String crusrUuid, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin disable");

		if (StringUtil.isEmpty(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreUserController]:end disable");
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser.setCrusrStatus(0);
		coreUser.setCrusrUdate(new Date());

		coreUserService.updateCoreUser(coreUser);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[CoreUserController]:end disable");
	}
	
	/**
	 * 去升级
	 */
	@RequestMapping(value="/go/upgrade", method=RequestMethod.POST)
	public void goUpgrade (String crusrUuid, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin goUpgrade");

		if (StringUtil.isEmpty(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreUserController]:end goUpgrade");
			return;
		}
		//根据用户UUID查找当前等级和积分和账号名称和头像
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该用户不存在!"), response);
			logger.info("[CoreUserController]:end goUpgrade");
			return;
		}
		UserGradeVO vo = new UserGradeVO();
		vo.setCrusrHead(coreUser.getCrusrHead());
		vo.setCrusrName(coreUser.getCrusrName());
		vo.setCrusrIntegral(coreUser.getCrusrIntegral());
		
		//根据当前等级的序号查询下一个等级信息
		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgdeUuid(coreUser.getCrusrGrade());
		coreGrade = coreGradeService.getCoreGrade(coreGrade);
		if (null == coreGrade) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当前用户等级不存在!"), response);
			logger.info("[CoreUserController]:end goUpgrade");
			return;
		}
		CoreGrade nextGrade = coreGradeService.getCoreGradeAfterGrade(coreGrade.getCrgdeOrd());
		if (null == nextGrade) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 2, "你当前已是最大等级!"), response);
			logger.info("[CoreUserController]:end goUpgrade");
			return;
		}
		vo.convertPOToVO(nextGrade);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "去升级获取信息成功!", vo),response);
		logger.info("[CoreUserController]:end goUpgrade");
	}
	
	/**
	 * 升级
	 */
	@RequestMapping(value="/upgrade", method=RequestMethod.POST)
	public void upgrade (String crusrUuid, String nextCrgdeUuid, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin upgrade");

		if (StringUtil.isEmpty(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreUserController]:end upgrade");
			return;
		}
		if (StringUtil.isEmpty(nextCrgdeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[升级的等级标识]不能为空!"), response);
			logger.info("[CoreUserController]:end upgrade");
			return;
		}
		//查询等级兑换需要的积分
		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgdeUuid(nextCrgdeUuid);
		coreGrade = coreGradeService.getCoreGrade(coreGrade);
		if (null == coreGrade) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "升级的等级不存在!"), response);
			logger.info("[CoreUserController]:end upgrade");
			return;
		}
		//查询当前用户的积分信息
		CoreUser user = new CoreUser();
		user.setCrusrUuid(crusrUuid);
		user = coreUserService.getCoreUser(user);
		if (null == user) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该用户不存在!"), response);
			logger.info("[CoreUserController]:end upgrade");
			return;
		}
		//更新当前用户的积分和等级信息
		if (user.getCrusrIntegral() < coreGrade.getCrgdeIntegral()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "你当前的积分不够!"), response);
			logger.info("[CoreUserController]:end upgrade");
			return;
		}
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser.setCrusrIntegral(user.getCrusrIntegral() - coreGrade.getCrgdeIntegral());
		coreUser.setCrusrGrade(nextCrgdeUuid);
		coreUser.setCrusrGradeIcon(coreGrade.getCrgdeIcon());
		coreUser.setCrusrGradeName(coreGrade.getCrgdeName());
		coreUser.setCrusrUdate(new Date());
		coreUserService.updateCoreUser(coreUser);

		//添加用户积分明细
		BusiIntegralRecord busiIntegralRecord = new BusiIntegralRecord();
		busiIntegralRecord.setBsirdUuid(RandomUtil.generateString(16));
		busiIntegralRecord.setBsirdUser(crusrUuid);
		busiIntegralRecord.setBsirdType(2);
		busiIntegralRecord.setBsirdQuota("-"+String.valueOf(coreGrade.getCrgdeIntegral()));
		busiIntegralRecord.setBsirdBill("兑换等级"+coreGrade.getCrgdeName()+"消耗"+coreGrade.getCrgdeIntegral()+"积分!");
		busiIntegralRecord.setBsirdCdate(new Date());
		busiIntegralRecordService.insertBusiIntegralRecord(busiIntegralRecord);

		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(crusrUuid);
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(3);
		busiMessageCenter.setBsmecProductPic(null);
		busiMessageCenter.setBsmecContent("恭喜你，等级升级至"+coreGrade.getCrgdeName()+"，消耗"+coreGrade.getCrgdeIntegral()+"积分!");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		
		UserGradeVO vo = new UserGradeVO();
		vo.setCrusrHead(user.getCrusrHead());
		vo.setCrusrName(user.getCrusrName());
		vo.setCrusrIntegral(coreUser.getCrusrIntegral());
		CoreGrade nextGrade = coreGradeService.getCoreGradeAfterGrade(coreGrade.getCrgdeOrd());
		if (null == nextGrade) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 2, "你当前已是最大等级!"),response);
			logger.info("[CoreUserController]:end upgrade");
			return;
		}
		vo.convertPOToVO(nextGrade);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "升级成功!", vo),response);
		logger.info("[CoreUserController]:end upgrade");
	}

	/**
	 * 重置密码
	 * 
	 * @param crusrUuid
	 * @param response
	 */
	@RequestMapping(value = "/reset/pwd", method = RequestMethod.POST)
	public void resetPwd(String crusrUuid, HttpServletResponse response) {
		logger.info("[CoreUserController.resetPwd]:begin resetPwd.");
		if (StringUtils.isBlank(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户UUID必传！"), response);
			logger.info("[CoreUserController]:end resetPwd");
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		String newMd5PWD = MD5Util.encode("123456", Constant.DEFAULT_CHARSET);
		coreUser.setCrusrPassword(newMd5PWD);
		coreUser.setCrusrUdate(new Date());
		coreUserService.updateCoreUser(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "重置密码成功！"), response);
		logger.info("[CoreUserController.resetPwd]:end resetPwd.");
	}
	
	/**
	 * 修改密码
	 * 
	 * @param crusrUuid
	 * @param oldPwd
	 * @param newPwd
	 * @param confirmPwd
	 * @param response
	 */
	@RequestMapping(value = "/update/pwd", method = RequestMethod.POST)
	public void updatePwd(String crusrUuid, String oldPwd, String newPwd, String confirmPwd, HttpServletResponse response) {
		logger.info("[CoreUserController.updatePwd]:begin updatePwd.");
		if (StringUtils.isBlank(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户UUID必传！"), response);
			logger.info("[CoreUserController]:end updatePwd");
			return;
		}		
		if (StringUtils.isBlank(oldPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入旧密码！"), response);
			logger.info("[CoreUserController]:end updatePwd");
			return;
		}		
		if (StringUtils.isBlank(newPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入新密码！"), response);
			logger.info("[CoreUserController]:end updatePwd");
			return;
		}		
		if (StringUtils.isBlank(confirmPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入确认密码！"), response);
			logger.info("[CoreUserController]:end updatePwd");
			return;
		}		
		if (!newPwd.equals(confirmPwd)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "密码输入不一致！"), response);
			logger.info("[CoreUserController]:end updatePwd");
			return;
		}

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser = coreUserService.getCoreUser(coreUser);		
		String oldMd5PWD = MD5Util.encode(oldPwd, Constant.DEFAULT_CHARSET);	
		if(coreUser == null || !oldMd5PWD.equals(coreUser.getCrusrPassword())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "原密码错误！"), response);
			logger.info("[CoreUserController]:end updatePwd");
			return;
		}
		String newMd5PWD = MD5Util.encode(newPwd, Constant.DEFAULT_CHARSET);
		coreUser.setCrusrPassword(newMd5PWD);
		coreUser.setCrusrUdate(new Date());
		coreUserService.updateCoreUser(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改密码成功！"), response);
		logger.info("[CoreUserController.updatePwd]:end updatePwd.");
	}

	/**
	* 修改个人信息
	*
	* @param crusrUuid 标识UUID
	* @param crusrName 帐户名称
	* @param crusrCode 真实姓名
	* @param crusrHead 头像附件
	* @param crusrProvince 居住省份
	* @param crusrProvinceName 居住省份名称
	* @param crusrCity 居住城市
	* @param crusrCityName 居住城市名称
	* @param crusrDistrict 居住区域
	* @param crusrDistrictName 居住区域名称
	* @param crusrAddress 详细地址
	* @param crusrEmail 电子邮箱
	* @param crusrMobile 手机号码
	* @param crusrBirthday 生日
	* @param crusrGender 性别:1男,2女,0其它
	* @param crusrRemarks 备注
	* @return
	*/
	@RequestMapping(value="/update/coreUser", method=RequestMethod.POST)
	public void updateCoreUser (String crusrUuid, String crusrName, String crusrCode, String crusrHead, String crusrProvince, 
			String crusrProvinceName, String crusrCity, String crusrCityName, String crusrDistrict, String crusrDistrictName, 
			String crusrAddress, String crusrEmail, String crusrMobile, String crusrBirthday, Integer crusrGender, String crusrRemarks, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin updateCoreUser");
		if (StringUtil.isEmpty(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreUserController]:end updateCoreUser");
			return;
		}
		if (StringUtil.isEmpty(crusrName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[账户名称]不能为空!"), response);
			logger.info("[CoreUserController]:end updateCoreUser");
			return;
		}
		if (StringUtil.isEmpty(crusrMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[手机号码]不能为空!"), response);
			logger.info("[CoreUserController]:end updateCoreUser");
			return;
		}
		if (!StringUtil.isPhone(crusrMobile)) {
			if (!StringUtil.isMobile(crusrMobile)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号码格式不对!"), response);
				logger.info("[CoreUserController]:end updateCoreUser");
				return;
			}
		}
		if (!StringUtil.isEmpty(crusrEmail)) {
			if (!StringUtil.isEmail(crusrEmail)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "电子邮箱格式不对!"), response);
				logger.info("[CoreUserController]:end updateCoreUser");
				return;
			}
		}
		if (null == crusrGender) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "性别必传!"), response);
			logger.info("[CoreUserController]:end updateCoreUser");
			return;
		}
		CoreUser user = coreUserService.getCoreUserByName(crusrName);
		if (null != user && !user.getCrusrName().equals(crusrName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该账户名称已存在!"), response);
			logger.info("[CoreUserController]:end updateCoreUser");
			return;
		}		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser.setCrusrName(crusrName);
		coreUser.setCrusrCode(crusrCode);
		coreUser.setCrusrHead(crusrHead);
		coreUser.setCrusrProvince(crusrProvince);
		coreUser.setCrusrProvinceName(crusrProvinceName);
		coreUser.setCrusrCity(crusrCity);
		coreUser.setCrusrCityName(crusrCityName);
		coreUser.setCrusrDistrict(crusrDistrict);
		coreUser.setCrusrDistrictName(crusrDistrictName);
		coreUser.setCrusrAddress(crusrAddress);
		coreUser.setCrusrEmail(crusrEmail);
		coreUser.setCrusrMobile(crusrMobile);
		coreUser.setCrusrCdate(new Date());
		coreUser.setCrusrUdate(new Date());
		coreUser.setCrusrBirthday(crusrBirthday);
		coreUser.setCrusrGender(crusrGender);
		coreUser.setCrusrRemarks(crusrRemarks);

		coreUserService.updateCoreUser(coreUser);

		coreUser = coreUserService.getCoreUser(coreUser);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改个人信息成功!", coreUser),response);
		logger.info("[CoreUserController]:end updateCoreUser");
	}

	/**
	* 获取个人信息
	*
	* @param crusrUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreUser (String crusrUuid, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin viewsCoreUser");
		if (StringUtil.isEmpty(crusrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreUserController]:end viewsCoreUser");
			return;
		}
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(crusrUuid);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
			logger.info("[CoreUserController]:end viewsCoreUser");
			return;
		}
		CoreUserVO coreUserVO = new CoreUserVO();
		coreUserVO.convertPOToVO(coreUser);
		if (!StringUtil.isEmpty(coreUser.getCrusrInviter())) {
			CoreUser user = new CoreUser();
			user.setCrusrUuid(coreUser.getCrusrInviter());
			user = coreUserService.getCoreUser(user);
			if (null != user) {
				coreUserVO.setCrusrInviterName(user.getCrusrName());
			}
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取个人信息成功", coreUserVO), response);
		logger.info("[CoreUserController]:end viewsCoreUser");
	}

	/**
	* 获取列表<List>
	* @param crusrName
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreUserList (String crusrName, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin findCoreUserList");
		List<CoreUser> lists = coreUserService.findCoreUserList(crusrName, null);
		List<CoreUserVO> vos = new ArrayList<CoreUserVO>();
		CoreUserVO vo;
		for (CoreUser coreUser : lists) {
			vo = new CoreUserVO();			vo.convertPOToVO(coreUser);			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreUserController]:end findCoreUserList");
	}

	/**
	* 获取列表<Page>
	* 
	* @param crusrName
	* @param crusrMobile
	* @param crusrInviteCode
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreUserPage (String crusrName, String crusrMobile, String crusrInviteCode, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreUserController]:begin findCoreUserPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreUser> page = coreUserService.findCoreUserPage(crusrName, crusrMobile, crusrInviteCode, pageNum, pageSize);
		Page<CoreUserVO> pageVO = new Page<CoreUserVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (CoreUser coreUser : page.getResult()) {
			if (!StringUtil.isEmpty(coreUser.getCrusrInviter())) {
				hashUserUuids.add(coreUser.getCrusrInviter());
			}
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);	
		
		List<CoreUserVO> vos = new ArrayList<CoreUserVO>();
		CoreUserVO vo;
		for (CoreUser coreUser : page.getResult()) {
			vo = new CoreUserVO();			vo.convertPOToVO(coreUser);
			if (!StringUtil.isEmpty(coreUser.getCrusrInviter())) {
				vo.setCrusrInviterName(userMap.get(coreUser.getCrusrInviter())==null?null:userMap.get(coreUser.getCrusrInviter()).getCrusrName());
			}			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreUserController]:end findCoreUserPage");
	}

}