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
import com.xiaoyu.lingdian.entity.BusiIntegralRecord;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiIntegralRecordService;
import com.xiaoyu.lingdian.vo.BusiIntegralRecordVO;

@Controller
@RequestMapping(value="/busiIntegralRecord")
public class BusiIntegralRecordController extends BaseController {

	/**
	* 用户积分明细表
	*/
	@Autowired
	private BusiIntegralRecordService busiIntegralRecordService;

	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 获取单个积分明细
	*
	* @param bsirdUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiIntegralRecord (String bsirdUuid, HttpServletResponse response) {
		logger.info("[BusiIntegralRecordController]:begin viewsBusiIntegralRecord");
		if (StringUtil.isEmpty(bsirdUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiIntegralRecordController]:end viewsBusiIntegralRecord");
			return;
		}
		BusiIntegralRecord busiIntegralRecord = new BusiIntegralRecord();
		busiIntegralRecord.setBsirdUuid(bsirdUuid);
		busiIntegralRecord = busiIntegralRecordService.getBusiIntegralRecord(busiIntegralRecord);
		if(null == busiIntegralRecord){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "积分明细不存在!"), response);
			logger.info("[BusiIntegralRecordController]:end viewsBusiIntegralRecord");
			return;
		}

		BusiIntegralRecordVO busiIntegralRecordVO = new BusiIntegralRecordVO();
		busiIntegralRecordVO.convertPOToVO(busiIntegralRecord);

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiIntegralRecord.getBsirdUser());
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
			logger.info("[BusiIntegralRecordController]:end viewsBusiIntegralRecord");
			return;
		}
		busiIntegralRecordVO.setBsirdUserName(coreUser.getCrusrName());
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取积分明细成功", busiIntegralRecordVO), response);
		logger.info("[BusiIntegralRecordController]:end viewsBusiIntegralRecord");

	}

	/**
	* 我的积分明细page
	* 
	* @param bsirdUser 所属用户
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/my/find/by/cnd", method=RequestMethod.POST)
	public void findBusiIntegralRecordPageByMy (String bsirdUser, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiIntegralRecordController]:begin findBusiIntegralRecordPageByMy");

		if (StringUtil.isEmpty(bsirdUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属用户不能为空!"), response);
			logger.info("[BusiIntegralRecordController]:end findBusiExchangeBillPageByMy");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiIntegralRecord> page = busiIntegralRecordService.findBusiIntegralRecordPageByMy(bsirdUser, pageNum, pageSize);
		Page<BusiIntegralRecordVO> pageVO = new Page<BusiIntegralRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());

		List<BusiIntegralRecordVO> vos = new ArrayList<BusiIntegralRecordVO>();
		BusiIntegralRecordVO vo;
		for (BusiIntegralRecord busiIntegralRecord : page.getResult()) {
			vo = new BusiIntegralRecordVO();
			vo.convertPOToVO(busiIntegralRecord);
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiIntegralRecordController]:end findBusiIntegralRecordPageByMy");

	}

	/**
	* 后台获取积分明细<Page>
	* 
	* @param userName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiIntegralRecordPage (String userName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiIntegralRecordController]:begin findBusiIntegralRecordPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiIntegralRecord> page = busiIntegralRecordService.findBusiIntegralRecordPage(userName, pageNum, pageSize);
		Page<BusiIntegralRecordVO> pageVO = new Page<BusiIntegralRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiIntegralRecord busiIntegralRecord : page.getResult()) {
			hashUserUuids.add(busiIntegralRecord.getBsirdUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiIntegralRecordVO> vos = new ArrayList<BusiIntegralRecordVO>();
		BusiIntegralRecordVO vo;
		for (BusiIntegralRecord busiIntegralRecord : page.getResult()) {
			vo = new BusiIntegralRecordVO();			vo.convertPOToVO(busiIntegralRecord);
			vo.setBsirdUserName(userMap.get(busiIntegralRecord.getBsirdUser())==null?null:userMap.get(busiIntegralRecord.getBsirdUser()).getCrusrName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiIntegralRecordController]:end findBusiIntegralRecordPage");
	}

}