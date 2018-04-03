package com.xiaoyu.lingdian.controller.my;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
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
import com.xiaoyu.lingdian.entity.BusiIntegralRecord;
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.BusiShareRecord;
import com.xiaoyu.lingdian.entity.CoreGrade;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreGradeService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiIntegralRecordService;
import com.xiaoyu.lingdian.service.my.BusiMessageCenterService;
import com.xiaoyu.lingdian.service.my.BusiShareRecordService;
import com.xiaoyu.lingdian.vo.BusiShareRecordVO;

@Controller
@RequestMapping(value="/busiShareRecord")
public class BusiShareRecordController extends BaseController {

	/**
	* 用户分享记录表
	*/
	@Autowired
	private BusiShareRecordService busiShareRecordService;
	
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
	* 用户消息中心表
	*/
	@Autowired
	private BusiMessageCenterService busiMessageCenterService;
	
	/**
	* 分享商品回调
	*
	* @param bssrdUser 所属用户
	* @param bssrdGrade 分享时等级
	* @param bssrdCommision 单个商品佣金
	* @param bssrdProduct 分享商品
	* @param bssrdSharePic 分享商品推客封面图
	* @param bssrdStorePic 分享商品商城封面图
	* @param bssrdProductName 分享商品名称
	* @param bssrdIntegral 积分收益
	* @return
	*/
	@RequestMapping(value="/add/busiShareRecord", method=RequestMethod.POST)
	public void addBusiShareRecord (String bssrdUser, String bssrdGrade, Double bssrdCommision, String bssrdProduct, String bssrdSharePic, String bssrdStorePic, String bssrdProductName, Integer bssrdIntegral, HttpServletResponse response) {
		logger.info("[BusiShareRecordController]:begin addBusiShareRecord");

		if (StringUtil.isEmpty(bssrdUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属用户必传!"), response);
			logger.info("[BusiShareRecordController]:end addBusiShareRecord");
			return;
		}
		if (StringUtil.isEmpty(bssrdGrade)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享时等级必传!"), response);
			logger.info("[BusiShareRecordController]:end addBusiShareRecord");
			return;
		}
		if (null == bssrdCommision) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "单个商品佣金必传!"), response);
			logger.info("[BusiShareRecordController]:end addBusiShareRecord");
			return;
		}
		if (StringUtil.isEmpty(bssrdProduct)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享商品标识必传!"), response);
			logger.info("[BusiShareRecordController]:end addBusiShareRecord");
			return;
		}
		if (StringUtil.isEmpty(bssrdStorePic)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享商品商城封面图必传!"), response);
			logger.info("[BusiShareRecordController]:end addBusiShareRecord");
			return;
		}
		if (StringUtil.isEmpty(bssrdSharePic)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享商品推客封面图必传!"), response);
			logger.info("[BusiShareRecordController]:end addBusiShareRecord");
			return;
		}
		if (StringUtil.isEmpty(bssrdProductName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享商品名称必传!"), response);
			logger.info("[BusiShareRecordController]:end addBusiShareRecord");
			return;
		}

		//判断是否是重复分享
		BusiShareRecord shareRecord = busiShareRecordService.getBusiShareRecordByProductAndUser(bssrdUser, bssrdProduct);
		if (null == shareRecord) {
			BusiShareRecord busiShareRecord = new BusiShareRecord();
			String uuid = RandomUtil.generateString(16);
			busiShareRecord.setBssrdUuid(uuid);
			busiShareRecord.setBssrdUser(bssrdUser);
			busiShareRecord.setBssrdGrade(bssrdGrade);
			busiShareRecord.setBssrdCommision(bssrdCommision);
			busiShareRecord.setBssrdProduct(bssrdProduct);
			busiShareRecord.setBssrdSharePic(bssrdSharePic);
			busiShareRecord.setBssrdStorePic(bssrdStorePic);
			busiShareRecord.setBssrdProductName(bssrdProductName);
			busiShareRecord.setBssrdBill("分享"+bssrdProductName+"商品");
			busiShareRecord.setBssrdIntegral(Double.valueOf(bssrdIntegral));
			busiShareRecord.setBssrdCdate(new Date());
			busiShareRecord.setBssrdUdate(new Date());
			busiShareRecordService.insertBusiShareRecord(busiShareRecord);

			//更新用户分享次数
			coreUserService.updateCoreUserByshareCount(bssrdUser, 1, bssrdIntegral, 0.0, 0);
			
			//添加积分明细
			BusiIntegralRecord busiIntegralRecord = new BusiIntegralRecord();
			busiIntegralRecord.setBsirdUuid(RandomUtil.generateString(16));
			busiIntegralRecord.setBsirdUser(bssrdUser);
			busiIntegralRecord.setBsirdType(1);		
			busiIntegralRecord.setBsirdQuota(String.valueOf(bssrdIntegral));
			busiIntegralRecord.setBsirdBill("分享"+bssrdProductName+"商品链接获得"+bssrdIntegral+"积分到账!");
			busiIntegralRecord.setBsirdCdate(new Date());
			busiIntegralRecordService.insertBusiIntegralRecord(busiIntegralRecord);

			//添加用户积分
			coreUserService.updateCoreUserByIntegral(bssrdUser, bssrdIntegral, bssrdIntegral);
			
			//添加到用户消息中心
			BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
			busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
			busiMessageCenter.setBsmecUser(bssrdUser);
			busiMessageCenter.setBsmecCdate(new Date());
			busiMessageCenter.setBsmecUdate(new Date());
			busiMessageCenter.setBsmecStatus(1);
			busiMessageCenter.setBsmecType(2);
			busiMessageCenter.setBsmecProductPic(null);
			busiMessageCenter.setBsmecContent("分享"+bssrdProductName+"商品链接获得"+bssrdIntegral+"积分到账!");
			busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "商品回调成功!"),response);
		logger.info("[BusiShareRecordController]:end addBusiShareRecord");

	}

	/**
	* 获取单个分享明细
	*
	* @param bssrdUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiShareRecord (String bssrdUuid, HttpServletResponse response) {
		logger.info("[BusiShareRecordController]:begin viewsBusiShareRecord");
		if (StringUtil.isEmpty(bssrdUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiShareRecordController]:end viewsBusiShareRecord");
			return;
		}
		BusiShareRecord busiShareRecord = new BusiShareRecord();
		busiShareRecord.setBssrdUuid(bssrdUuid);
		busiShareRecord = busiShareRecordService.getBusiShareRecord(busiShareRecord);
		if(null == busiShareRecord){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享明细不存在!"), response);
			logger.info("[BusiShareRecordController]:end viewsBusiShareRecord");
			return;
		}

		BusiShareRecordVO busiShareRecordVO = new BusiShareRecordVO();
		busiShareRecordVO.convertPOToVO(busiShareRecord);

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiShareRecord.getBssrdUser());
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
			logger.info("[BusiShareRecordController]:end viewsBusiShareRecord");
			return;
		}
		busiShareRecordVO.setBssrdUserName(coreUser.getCrusrName());

		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgdeUuid(busiShareRecord.getBssrdGrade());
		coreGrade = coreGradeService.getCoreGrade(coreGrade);
		if(null == coreGrade){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享时的等级不存在!"), response);
			logger.info("[BusiShareRecordController]:end viewsBusiShareRecord");
			return;
		}
		busiShareRecordVO.setBssrdGradeName(coreGrade.getCrgdeName());
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个分享明细成功", busiShareRecordVO), response);
		logger.info("[BusiShareRecordController]:end viewsBusiShareRecord");
	}

	/**
	* 我的分享记录<Page>
	* 
	* @param bssrdUser 所属用户
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/my/find/by/cnd", method=RequestMethod.POST)
	public void findBusiShareRecordPageByMy (String bssrdUser, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiShareRecordController]:begin findBusiShareRecordPageByMy");

		if (StringUtil.isEmpty(bssrdUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属用户不能为空!"), response);
			logger.info("[BusiShareRecordController]:end findBusiShareRecordPageByMy");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiShareRecord> page = busiShareRecordService.findBusiShareRecordForPagesByMy(bssrdUser, pageNum, pageSize);
		Page<BusiShareRecordVO> pageVO = new Page<BusiShareRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());

		HashSet<String> hashGradeUuids = new HashSet<String>();
		for (BusiShareRecord busiShareRecord : page.getResult()) {
			hashGradeUuids.add(busiShareRecord.getBssrdGrade());
		}
		List<String> gradeUuids = new ArrayList<>(hashGradeUuids);
		Map<String, CoreGrade> gradeMap = coreGradeService.findCoreGradeMapByUuidList(gradeUuids);
		
		List<BusiShareRecordVO> vos = new ArrayList<BusiShareRecordVO>();
		BusiShareRecordVO vo;
		for (BusiShareRecord busiShareRecord : page.getResult()) {
			vo = new BusiShareRecordVO();
			vo.convertPOToVO(busiShareRecord);
			vo.setBssrdGradeName(gradeMap.get(busiShareRecord.getBssrdGrade())==null?null:gradeMap.get(busiShareRecord.getBssrdGrade()).getCrgdeName());
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiShareRecordController]:end findBusiShareRecordPageByMy");
	}

	/**
	* 后台获取分享记录<Page>
	* 
	* @param userName 用户名
	* @param bssrdProductName 商品名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiShareRecordPage (String userName, String bssrdProductName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiShareRecordController]:begin findBusiShareRecordPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiShareRecord> page = busiShareRecordService.findBusiShareRecordPage(userName, bssrdProductName, pageNum, pageSize);
		Page<BusiShareRecordVO> pageVO = new Page<BusiShareRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		HashSet<String> hashGradeUuids = new HashSet<String>();
		for (BusiShareRecord busiShareRecord : page.getResult()) {
			hashUserUuids.add(busiShareRecord.getBssrdUser());
			hashGradeUuids.add(busiShareRecord.getBssrdGrade());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);

		List<String> gradeUuids = new ArrayList<>(hashGradeUuids);
		Map<String, CoreGrade> gradeMap = coreGradeService.findCoreGradeMapByUuidList(gradeUuids);
		
		List<BusiShareRecordVO> vos = new ArrayList<BusiShareRecordVO>();
		BusiShareRecordVO vo;
		for (BusiShareRecord busiShareRecord : page.getResult()) {
			vo = new BusiShareRecordVO();			vo.convertPOToVO(busiShareRecord);
			vo.setBssrdUserName(userMap.get(busiShareRecord.getBssrdUser())==null?null:userMap.get(busiShareRecord.getBssrdUser()).getCrusrName());
			vo.setBssrdGradeName(gradeMap.get(busiShareRecord.getBssrdGrade())==null?null:gradeMap.get(busiShareRecord.getBssrdGrade()).getCrgdeName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiShareRecordController]:end findBusiShareRecordPage");
	}

}