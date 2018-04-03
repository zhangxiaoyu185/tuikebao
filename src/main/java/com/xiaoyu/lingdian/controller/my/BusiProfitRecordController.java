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
import com.xiaoyu.lingdian.entity.BusiProfitRecord;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiProfitRecordService;
import com.xiaoyu.lingdian.vo.BusiProfitRecordVO;

@Controller
@RequestMapping(value="/busiProfitRecord")
public class BusiProfitRecordController extends BaseController {

	/**
	* 用户收益明细表
	*/
	@Autowired
	private BusiProfitRecordService busiProfitRecordService;
	
	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 获取单个收益明细
	*
	* @param bsprdUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiProfitRecord (String bsprdUuid, HttpServletResponse response) {
		logger.info("[BusiProfitRecordController]:begin viewsBusiProfitRecord");
		if (StringUtil.isEmpty(bsprdUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProfitRecordController]:end viewsBusiProfitRecord");
			return;
		}
		BusiProfitRecord busiProfitRecord = new BusiProfitRecord();
		busiProfitRecord.setBsprdUuid(bsprdUuid);
		busiProfitRecord = busiProfitRecordService.getBusiProfitRecord(busiProfitRecord);
		if(null == busiProfitRecord){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "收益明细不存在!"), response);
			logger.info("[BusiProfitRecordController]:end viewsBusiProfitRecord");
			return;
		}

		BusiProfitRecordVO busiProfitRecordVO = new BusiProfitRecordVO();
		busiProfitRecordVO.convertPOToVO(busiProfitRecord);

		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiProfitRecord.getBsprdUser());
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
			logger.info("[BusiProfitRecordController]:end viewsBusiProfitRecord");
			return;
		}
		busiProfitRecordVO.setBsprdUserName(coreUser.getCrusrName());
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取收益明细成功", busiProfitRecordVO), response);
		logger.info("[BusiProfitRecordController]:end viewsBusiProfitRecord");
	}

	/**
	* 我的收益明细<Page>
	* 
	* @param bsprdUser
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/my/find/by/cnd", method=RequestMethod.POST)
	public void findBusiProfitRecordForPagesByMy (String bsprdUser, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiProfitRecordController]:begin findBusiProfitRecordForPagesByMy");

		if (StringUtil.isEmpty(bsprdUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属用户不能为空!"), response);
			logger.info("[BusiProfitRecordController]:end findBusiProfitRecordForPagesByMy");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiProfitRecord> page = busiProfitRecordService.findBusiProfitRecordForPagesByMy(bsprdUser, pageNum, pageSize);
		Page<BusiProfitRecordVO> pageVO = new Page<BusiProfitRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		List<BusiProfitRecordVO> vos = new ArrayList<BusiProfitRecordVO>();
		BusiProfitRecordVO vo;
		for (BusiProfitRecord busiProfitRecord : page.getResult()) {
			vo = new BusiProfitRecordVO();
			vo.convertPOToVO(busiProfitRecord);
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiProfitRecordController]:end findBusiProfitRecordForPagesByMy");
	}

	/**
	* 后台获取收益明细<Page>
	* 
	* @param userName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiProfitRecordPage (String userName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiProfitRecordController]:begin findBusiProfitRecordPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiProfitRecord> page = busiProfitRecordService.findBusiProfitRecordPage(userName, pageNum, pageSize);
		Page<BusiProfitRecordVO> pageVO = new Page<BusiProfitRecordVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiProfitRecord busiProfitRecord : page.getResult()) {
			hashUserUuids.add(busiProfitRecord.getBsprdUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);

		List<BusiProfitRecordVO> vos = new ArrayList<BusiProfitRecordVO>();
		BusiProfitRecordVO vo;
		for (BusiProfitRecord busiProfitRecord : page.getResult()) {
			vo = new BusiProfitRecordVO();			vo.convertPOToVO(busiProfitRecord);
			vo.setBsprdUserName(userMap.get(busiProfitRecord.getBsprdUser())==null?null:userMap.get(busiProfitRecord.getBsprdUser()).getCrusrName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiProfitRecordController]:end findBusiProfitRecordPage");
	}

}