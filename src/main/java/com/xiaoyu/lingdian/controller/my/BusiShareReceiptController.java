package com.xiaoyu.lingdian.controller.my;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.BusiShareReceipt;
import com.xiaoyu.lingdian.entity.CoreGrade;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreGradeService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiShareReceiptService;
import com.xiaoyu.lingdian.vo.BusiShareReceiptVO;

@Controller
@RequestMapping(value="/busiShareReceipt")
public class BusiShareReceiptController extends BaseController {

	/**
	* 分享商品购买收货记录表
	*/
	@Autowired
	private BusiShareReceiptService busiShareReceiptService;
	
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
	* 获取单个分享商品购买收货记录
	*
	* @param bssrtUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiShareReceipt (String bssrtUuid, HttpServletResponse response) {
		logger.info("[BusiShareReceiptController]:begin viewsBusiShareReceipt");
		if (StringUtil.isEmpty(bssrtUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiShareReceiptController]:end viewsBusiShareReceipt");
			return;
		}
		BusiShareReceipt busiShareReceipt = new BusiShareReceipt();
		busiShareReceipt.setBssrtUuid(bssrtUuid);
		busiShareReceipt = busiShareReceiptService.getBusiShareReceipt(busiShareReceipt);
		if(null == busiShareReceipt){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享商品购买收货记录不存在!"), response);
			logger.info("[BusiShareReceiptController]:end viewsBusiShareReceipt");
			return;
		}

		BusiShareReceiptVO busiShareReceiptVO = new BusiShareReceiptVO();
		busiShareReceiptVO.convertPOToVO(busiShareReceipt);

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiShareReceipt.getBssrtUser());
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享人不存在!"), response);
			logger.info("[BusiShareReceiptController]:end viewsBusiShareReceipt");
			return;
		}
		busiShareReceiptVO.setBssrtUserName(coreUser.getCrusrName());

		CoreUser buyUser = new CoreUser();
		buyUser.setCrusrUuid(busiShareReceipt.getBssrtBuyUser());
		buyUser = coreUserService.getCoreUser(buyUser);
		if(null == buyUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "购买人不存在!"), response);
			logger.info("[BusiShareReceiptController]:end viewsBusiShareReceipt");
			return;
		}
		busiShareReceiptVO.setBssrtBuyUserName(buyUser.getCrusrName());

		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgdeUuid(busiShareReceipt.getBssrtGrade());
		coreGrade = coreGradeService.getCoreGrade(coreGrade);
		if(null == coreGrade){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享时的等级不存在!"), response);
			logger.info("[BusiShareReceiptController]:end viewsBusiShareReceipt");
			return;
		}
		busiShareReceiptVO.setBssrtGradeName(coreGrade.getCrgdeName());
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个分享商品购买收货记录成功", busiShareReceiptVO), response);
		logger.info("[BusiShareReceiptController]:end viewsBusiShareReceipt");
	}

	/**
	* 后台获取分享商品购买收货记录<Page>
	* 
	* @param userName
	* @param bssrtProductName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiShareReceiptPage (String userName, String bssrtProductName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiShareReceiptController]:begin findBusiShareReceiptPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiShareReceipt> page = busiShareReceiptService.findBusiShareReceiptPage(userName, bssrtProductName, pageNum, pageSize);
		Page<BusiShareReceiptVO> pageVO = new Page<BusiShareReceiptVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		HashSet<String> hashGradeUuids = new HashSet<String>();
		for (BusiShareReceipt busiShareReceipt : page.getResult()) {
			hashUserUuids.add(busiShareReceipt.getBssrtUser());
			hashUserUuids.add(busiShareReceipt.getBssrtBuyUser());
			hashGradeUuids.add(busiShareReceipt.getBssrtGrade());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);

		List<String> gradeUuids = new ArrayList<>(hashGradeUuids);
		Map<String, CoreGrade> gradeMap = coreGradeService.findCoreGradeMapByUuidList(gradeUuids);
		
		List<BusiShareReceiptVO> vos = new ArrayList<BusiShareReceiptVO>();
		BusiShareReceiptVO vo;
		for (BusiShareReceipt busiShareReceipt : page.getResult()) {
			vo = new BusiShareReceiptVO();			vo.convertPOToVO(busiShareReceipt);
			vo.setBssrtUserName(userMap.get(busiShareReceipt.getBssrtUser())==null?null:userMap.get(busiShareReceipt.getBssrtUser()).getCrusrName());
			vo.setBssrtBuyUserName(userMap.get(busiShareReceipt.getBssrtBuyUser())==null?null:userMap.get(busiShareReceipt.getBssrtBuyUser()).getCrusrName());
			vo.setBssrtGradeName(gradeMap.get(busiShareReceipt.getBssrtGrade())==null?null:gradeMap.get(busiShareReceipt.getBssrtGrade()).getCrgdeName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiShareReceiptController]:end findBusiShareReceiptPage");
	}

}