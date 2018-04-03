package com.xiaoyu.lingdian.controller.my;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

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
import com.xiaoyu.lingdian.entity.BusiCashRecord;
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.BusiProfitRecord;
import com.xiaoyu.lingdian.entity.BusiPromoterInfo;
import com.xiaoyu.lingdian.entity.CoreShortMessage;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreShortMessageService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiCashRecordService;
import com.xiaoyu.lingdian.service.my.BusiMessageCenterService;
import com.xiaoyu.lingdian.service.my.BusiProfitRecordService;
import com.xiaoyu.lingdian.service.my.BusiPromoterInfoService;
import com.xiaoyu.lingdian.vo.BusiCashRecordVO;
import com.xiaoyu.lingdian.vo.other.UserPromoterInfoVO;

@Controller
@RequestMapping(value="/busiCashRecord")
public class BusiCashRecordController extends BaseController {

	/**
	* 用户提现记录表
	*/
	@Autowired
	private BusiCashRecordService busiCashRecordService;
	
	/**
	* 用户提现信息表
	*/
	@Autowired
	private BusiPromoterInfoService busiPromoterInfoService;
	
	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 短信日志记录表
	*/
	@Autowired
	private CoreShortMessageService coreShortMessageService;
	
	/**
	* 用户收益明细表
	*/
	@Autowired
	private BusiProfitRecordService busiProfitRecordService;

	/**
	* 用户消息中心表
	*/
	@Autowired
	private BusiMessageCenterService busiMessageCenterService;
	
	/**
	* 去提现界面
	*
	* @param bspioUser 所属用户
	* @return
	*/
	@RequestMapping(value="/go/promoterInfo", method=RequestMethod.POST)
	public void goPromoterInfo (String bspioUser, HttpServletResponse response) {
		logger.info("[BusiPromoterInfoController]:begin goPromoterInfo");

		if (StringUtil.isEmpty(bspioUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[所属用户]不能为空!"), response);
			logger.info("[BusiCashRecordController]:end goPromoterInfo");
			return;
		}
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(bspioUser);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该用户不存在!"), response);
			logger.info("[BusiCashRecordController]:end goPromoterInfo");
			return;
		}
	
		UserPromoterInfoVO userPromoterInfoVO = new UserPromoterInfoVO();
		BusiPromoterInfo busiPromoterInfo = busiPromoterInfoService.getBusiPromoterInfoByUser(bspioUser);
		if (null != busiPromoterInfo) {
			userPromoterInfoVO.convertPOToVO(busiPromoterInfo);
		}
		userPromoterInfoVO.setAvailableFee(coreUser.getCrusrAvailableIncome());
		userPromoterInfoVO.setMobileCode(coreUser.getCrusrMobile());
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取提现信息成功", userPromoterInfoVO), response);
		logger.info("[BusiCashRecordController]:end goPromoterInfo");
	}
	
	/**
	* 申请提现,判断验证码,添加提现记录(默认为已申请),冻结收益，添加提现信息，添加用户消息
	*
	* @param bscrdAmount 提取金额
	* @param bscrdExtracted 提取人
	* @param bscrdMobile 手机号
	* @param mobileCode 手机号验证码
	* @param bscrdAccountType 提现类型:1银行账户2支付宝
	* @param bscrdAccountName 银行账户名称
	* @param bscrdAccountNo 银行账号
	* @param bscrdBankName 银行开户行
	* @param bscrdBankSite 银行开户网点
	* @param bscrdAlipayNo 支付宝账户
	* @param bscrdAlipayName 支付宝姓名
	* @return
	*/
	@RequestMapping(value="/add/busiCashRecord", method=RequestMethod.POST)
	public void addBusiCashRecord (Double bscrdAmount, String bscrdExtracted, String bscrdMobile, String mobileCode, Integer bscrdAccountType, String bscrdAccountName, String bscrdAccountNo, String bscrdBankName, String bscrdBankSite, String bscrdAlipayNo, String bscrdAlipayName, HttpServletResponse response) {
		logger.info("[BusiCashRecordController]:begin addBusiCashRecord");

		if (null == bscrdAmount || 0.0 == bscrdAmount) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "提取金额不能为空!"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}
		if (StringUtil.isEmpty(bscrdExtracted)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "提取人必传!"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}
		if (StringUtil.isEmpty(bscrdMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号必传!"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}
		if (StringUtil.isEmpty(mobileCode)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号验证码必传!"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}
		if (null == bscrdAccountType) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "提现类型不能为空!"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}
		if (1 != bscrdAccountType && 2 != bscrdAccountType) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择提现类型!"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}
		if (1 == bscrdAccountType) {
			if (StringUtil.isEmpty(bscrdAccountName)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "银行账户名称必传!"), response);
				logger.info("[BusiCashRecordController]:end addBusiCashRecord");
				return;
			}
			if (StringUtil.isEmpty(bscrdAccountNo)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "银行账号必传!"), response);
				logger.info("[BusiCashRecordController]:end addBusiCashRecord");
				return;
			}
			if (StringUtil.isEmpty(bscrdBankName)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "银行开户行必传!"), response);
				logger.info("[BusiCashRecordController]:end addBusiCashRecord");
				return;
			}
			if (StringUtil.isEmpty(bscrdBankSite)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "银行开户网点必传!"), response);
				logger.info("[BusiCashRecordController]:end addBusiCashRecord");
				return;
			}
		}
		if (2 == bscrdAccountType) {
			if (StringUtil.isEmpty(bscrdAlipayNo)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "支付宝账户必传!"), response);
				logger.info("[BusiCashRecordController]:end addBusiCashRecord");
				return;
			}
			if (StringUtil.isEmpty(bscrdAlipayName)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "支付宝姓名必传!"), response);
				logger.info("[BusiCashRecordController]:end addBusiCashRecord");
				return;
			}
		}
 
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(bscrdExtracted);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该用户不存在!"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}
		if(!bscrdMobile.equals(coreUser.getCrusrMobile())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "提现申请手机号和预留手机号不符!"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}
		//判断提现金额大小
		if(coreUser.getCrusrAvailableIncome() < bscrdAmount){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "您的可用收益不足!"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}

		//判断验证码
		CoreShortMessage coreShortMessage = coreShortMessageService.getCoreShortMessageByMobile(bscrdMobile);
		if (null == coreShortMessage) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请先发送短信验证码！"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}
				
		Date date = new Date();
				
		long secondLong = DateUtil.secondDiff(date, coreShortMessage.getCrmceTimeout());
		if (secondLong < 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该短信验证码已过期,请重新发送！"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}
		if (!mobileCode.equalsIgnoreCase(coreShortMessage.getCrmceContent())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "短信验证码错误！"), response);
			logger.info("[BusiCashRecordController]:end addBusiCashRecord");
			return;
		}
		
		//添加提现记录(默认为已申请)		BusiCashRecord busiCashRecord = new BusiCashRecord();
		String uuid = RandomUtil.generateString(16);
		busiCashRecord.setBscrdUuid(uuid);
		busiCashRecord.setBscrdAmount(String.valueOf(bscrdAmount));
		busiCashRecord.setBscrdExtracted(bscrdExtracted);
		busiCashRecord.setBscrdStatus(1);
		busiCashRecord.setBscrdCdate(new Date());
		busiCashRecord.setBscrdUdate(new Date());
		busiCashRecord.setBscrdMobile(bscrdMobile);
		busiCashRecord.setBscrdAccountType(bscrdAccountType);
		busiCashRecord.setBscrdAccountName(bscrdAccountName);
		busiCashRecord.setBscrdAccountNo(bscrdAccountNo);
		busiCashRecord.setBscrdBankName(bscrdBankName);
		busiCashRecord.setBscrdBankSite(bscrdBankSite);
		busiCashRecord.setBscrdAlipayNo(bscrdAlipayNo);
		busiCashRecord.setBscrdAlipayName(bscrdAlipayName);
		busiCashRecordService.insertBusiCashRecord(busiCashRecord);
		
		//添加或更新提现信息
		BusiPromoterInfo promoterInfo = busiPromoterInfoService.getBusiPromoterInfoByUser(bscrdExtracted);
		if (null == promoterInfo) {
			BusiPromoterInfo busiPromoterInfo = new BusiPromoterInfo();
			busiPromoterInfo.setBspioUuid(RandomUtil.generateString(16));
			busiPromoterInfo.setBspioUser(bscrdExtracted);
			busiPromoterInfo.setBspioAccountName(bscrdAccountName);
			busiPromoterInfo.setBspioAccountNo(bscrdAccountNo);
			busiPromoterInfo.setBspioBankName(bscrdBankName);
			busiPromoterInfo.setBspioBankSite(bscrdBankSite);
			busiPromoterInfo.setBspioAlipayNo(bscrdAlipayNo);
			busiPromoterInfo.setBspioAlipayName(bscrdAlipayName);
			busiPromoterInfo.setBspioUdate(new Date());
			busiPromoterInfoService.insertBusiPromoterInfo(busiPromoterInfo);
		} else {
			BusiPromoterInfo busiPromoterInfo = new BusiPromoterInfo();
			busiPromoterInfo.setBspioUser(bscrdExtracted);
			busiPromoterInfo.setBspioAccountName(bscrdAccountName);
			busiPromoterInfo.setBspioAccountNo(bscrdAccountNo);
			busiPromoterInfo.setBspioBankName(bscrdBankName);
			busiPromoterInfo.setBspioBankSite(bscrdBankSite);
			busiPromoterInfo.setBspioAlipayNo(bscrdAlipayNo);
			busiPromoterInfo.setBspioAlipayName(bscrdAlipayName);
			busiPromoterInfo.setBspioUdate(new Date());
			busiPromoterInfoService.updateBusiPromoterInfo(busiPromoterInfo);
		}

		//冻结收益
		coreUserService.updateCoreUserByIncome(bscrdExtracted, bscrdAmount, -bscrdAmount, 0.0);
		
		//添加消息中心记录
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(bscrdExtracted);
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(1);
		busiMessageCenter.setBsmecProductPic(null);
		busiMessageCenter.setBsmecContent("提现申请已发起，客服会在24小时内联系您");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "申请提现成功!"),response);
		logger.info("[BusiCashRecordController]:end addBusiCashRecord");
	}

	/**
	* 审核
	*
	* @param bscrdUuid 标识UUID
	* @param bscrdStatus 状态:2交易成功3交易失败
	* @return
	*/
	@RequestMapping(value="/update/busiCashRecord", method=RequestMethod.POST)
	public void updateBusiCashRecord (String bscrdUuid, Integer bscrdStatus, HttpServletResponse response) {
		logger.info("[BusiCashRecordController]:begin updateBusiCashRecord");
		if (StringUtil.isEmpty(bscrdUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiCashRecordController]:end updateBusiCashRecord");
			return;
		}
		if (2 != bscrdStatus && 3 != bscrdStatus ) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择审核状态!"), response);
			logger.info("[BusiCashRecordController]:end updateBusiCashRecord");
			return;
		}

		BusiCashRecord busiCashRecord = new BusiCashRecord();
		busiCashRecord.setBscrdUuid(bscrdUuid);
		BusiCashRecord cashRecord = busiCashRecordService.getBusiCashRecord(busiCashRecord);
		if (null == cashRecord) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "提现记录不存在!"), response);
			logger.info("[BusiCashRecordController]:end updateBusiCashRecord");
			return;
		}
		if (1 != cashRecord.getBscrdStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "已经审核操作过!"), response);
			logger.info("[BusiCashRecordController]:end updateBusiCashRecord");
			return;
		}

		busiCashRecord.setBscrdStatus(bscrdStatus);
		busiCashRecord.setBscrdUdate(new Date());
		busiCashRecordService.updateBusiCashRecord(busiCashRecord);		
		
		if (2 == bscrdStatus) { //交易成功把冻结去除
			coreUserService.updateCoreUserByIncome(cashRecord.getBscrdExtracted(), -Double.valueOf(cashRecord.getBscrdAmount()), 0.0, 0.0);
			//添加收益明细
			BusiProfitRecord busiProfitRecord = new BusiProfitRecord();
			String uuid = RandomUtil.generateString(16);
			busiProfitRecord.setBsprdUuid(uuid);
			busiProfitRecord.setBsprdUser(cashRecord.getBscrdExtracted());
			busiProfitRecord.setBsprdType(1);
			busiProfitRecord.setBsprdQuota("-"+cashRecord.getBscrdAmount());
			busiProfitRecord.setBsprdBill("提现"+cashRecord.getBscrdAmount()+"元");
			busiProfitRecord.setBsprdCdate(new Date());
			busiProfitRecordService.insertBusiProfitRecord(busiProfitRecord);
			
			//更新用户的提现信息
			coreUserService.updateCoreUserByCashCount(cashRecord.getBscrdExtracted(), 1, Double.valueOf(cashRecord.getBscrdAmount()));
			
			//添加消息中心记录
			BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
			busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
			busiMessageCenter.setBsmecUser(cashRecord.getBscrdExtracted());
			busiMessageCenter.setBsmecCdate(new Date());
			busiMessageCenter.setBsmecUdate(new Date());
			busiMessageCenter.setBsmecStatus(1);
			busiMessageCenter.setBsmecType(1);
			busiMessageCenter.setBsmecProductPic(null);
			busiMessageCenter.setBsmecContent("提现成功,请查收!");
			busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		}
		if (3 == bscrdStatus) { //交易失败把冻结收益去除,增加可用收益
			coreUserService.updateCoreUserByIncome(cashRecord.getBscrdExtracted(), -Double.valueOf(cashRecord.getBscrdAmount()), Double.valueOf(cashRecord.getBscrdAmount()), 0.0);
		
			//添加消息中心记录
			BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
			busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
			busiMessageCenter.setBsmecUser(cashRecord.getBscrdExtracted());
			busiMessageCenter.setBsmecCdate(new Date());
			busiMessageCenter.setBsmecUdate(new Date());
			busiMessageCenter.setBsmecStatus(1);
			busiMessageCenter.setBsmecType(1);
			busiMessageCenter.setBsmecProductPic(null);
			busiMessageCenter.setBsmecContent("提现失败,您的信息填写有误!");
			busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "审核成功!"),response);
		logger.info("[BusiCashRecordController]:end updateBusiCashRecord");

	}

	/**
	* 删除
	*
	* @param bscrdUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiCashRecord (String bscrdUuid, HttpServletResponse response) {
		logger.info("[BusiCashRecordController]:begin deleteBusiCashRecord");
		if (StringUtil.isEmpty(bscrdUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiCashRecordController]:end deleteBusiCashRecord");
			return;
		}
		BusiCashRecord busiCashRecord = new BusiCashRecord();
		busiCashRecord.setBscrdUuid(bscrdUuid);

		busiCashRecordService.deleteBusiCashRecord(busiCashRecord);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiCashRecordController]:end deleteBusiCashRecord");

	}

	/**
	* 批量删除
	*
	* @param bscrdUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiCashRecord (String bscrdUuids, HttpServletResponse response) {
		logger.info("[BusiCashRecordController]:begin deleteBatchBusiCashRecord");
		if (StringUtil.isEmpty(bscrdUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiCashRecordController]:end deleteBatchBusiCashRecord");
			return;
		}
		String[] uuids=bscrdUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		busiCashRecordService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiCashRecordController]:end deleteBatchBusiCashRecord");
	}

	/**
	* 获取单个提现记录
	*
	* @param bscrdUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiCashRecord (String bscrdUuid, HttpServletResponse response) {
		logger.info("[BusiCashRecordController]:begin viewsBusiCashRecord");
		if (StringUtil.isEmpty(bscrdUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiCashRecordController]:end viewsBusiCashRecord");
			return;
		}
		BusiCashRecord busiCashRecord = new BusiCashRecord();
		busiCashRecord.setBscrdUuid(bscrdUuid);
		busiCashRecord = busiCashRecordService.getBusiCashRecord(busiCashRecord);
		if(null == busiCashRecord){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "提现记录不存在!"), response);
			logger.info("[BusiCashRecordController]:end viewsBusiCashRecord");
			return;
		}

		BusiCashRecordVO busiCashRecordVO = new BusiCashRecordVO();
		busiCashRecordVO.convertPOToVO(busiCashRecord);
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiCashRecord.getBscrdExtracted());
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "提取人不存在!"), response);
			logger.info("[BusiCashRecordController]:end viewsBusiCashRecord");
			return;
		}
		busiCashRecordVO.setBscrdExtractedName(coreUser.getCrusrName());
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个提现记录成功", busiCashRecordVO), response);
		logger.info("[BusiCashRecordController]:end viewsBusiCashRecord");

	}

	/**
	* 我的提现记录<Page>
	* 
	* @param bscrdExtracted 提取人
	* @param bscrdStatus 提现状态:1已申请2交易成功3交易失败
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/my/find/by/cnd", method=RequestMethod.POST)
	public void findBusiCashRecordPageByMy (String bscrdExtracted, Integer bscrdStatus, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiCashRecordController]:begin findBusiCashRecordPageByMy");

		if (StringUtil.isEmpty(bscrdExtracted)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "提取人不能为空!"), response);
			logger.info("[BusiCashRecordController]:end findBusiCashRecordPageByMy");
			return;
		}
		if (null == bscrdStatus || 0 == bscrdStatus) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "提现状态必传!"), response);
			logger.info("[BusiCashRecordController]:end findBusiCashRecordPageByMy");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiCashRecord> page = busiCashRecordService.findBusiCashRecordForPagesByMy(bscrdExtracted, bscrdStatus, pageNum, pageSize);
		Page<BusiCashRecordVO> pageVO = new Page<BusiCashRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		List<BusiCashRecordVO> vos = new ArrayList<BusiCashRecordVO>();
		BusiCashRecordVO vo;
		for (BusiCashRecord busiCashRecord : page.getResult()) {
			vo = new BusiCashRecordVO();
			vo.convertPOToVO(busiCashRecord);
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiCashRecordController]:end findBusiCashRecordPageByMy");

	}

	/**
	* 后台获取提现记录列表<Page>
	* 
	* @param userName
	* @param bscrdMobile
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiCashRecordPage (String userName, String bscrdMobile, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiCashRecordController]:begin findBusiCashRecordPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiCashRecord> page = busiCashRecordService.findBusiCashRecordPage(userName, bscrdMobile, pageNum, pageSize);
		Page<BusiCashRecordVO> pageVO = new Page<BusiCashRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiCashRecord busiCashRecord : page.getResult()) {
			hashUserUuids.add(busiCashRecord.getBscrdExtracted());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiCashRecordVO> vos = new ArrayList<BusiCashRecordVO>();
		BusiCashRecordVO vo;
		for (BusiCashRecord busiCashRecord : page.getResult()) {
			vo = new BusiCashRecordVO();			vo.convertPOToVO(busiCashRecord);
			vo.setBscrdExtractedName(userMap.get(busiCashRecord.getBscrdExtracted())==null?null:userMap.get(busiCashRecord.getBscrdExtracted()).getCrusrName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiCashRecordController]:end findBusiCashRecordPage");
	}

}