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
import com.xiaoyu.lingdian.entity.BusiPromoterInfo;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiPromoterInfoService;
import com.xiaoyu.lingdian.vo.BusiPromoterInfoVO;

@Controller
@RequestMapping(value="/busiPromoterInfo")
public class BusiPromoterInfoController extends BaseController {

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
	* 获取单个
	*
	* @param bspioUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiPromoterInfo (String bspioUuid, HttpServletResponse response) {
		logger.info("[BusiPromoterInfoController]:begin viewsBusiPromoterInfo");
		if (StringUtil.isEmpty(bspioUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiPromoterInfoController]:end viewsBusiPromoterInfo");
			return;
		}
		BusiPromoterInfo busiPromoterInfo = new BusiPromoterInfo();
		busiPromoterInfo.setBspioUuid(bspioUuid);
		busiPromoterInfo = busiPromoterInfoService.getBusiPromoterInfo(busiPromoterInfo);
		if (null == busiPromoterInfo) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "提现信息不存在!"), response);
			logger.info("[BusiPromoterInfoController]:end viewsBusiPromoterInfo");
			return;
		}

		HashSet<String> hashUserUuids = new HashSet<String>();
		hashUserUuids.add(busiPromoterInfo.getBspioUser());
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		BusiPromoterInfoVO busiPromoterInfoVO = new BusiPromoterInfoVO();
		busiPromoterInfoVO.convertPOToVO(busiPromoterInfo);
		busiPromoterInfoVO.setBspioUserName(userMap.get(busiPromoterInfo.getBspioUser())==null?null:userMap.get(busiPromoterInfo.getBspioUser()).getCrusrName());
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiPromoterInfoVO), response);
		logger.info("[BusiPromoterInfoController]:end viewsBusiPromoterInfo");

	}

	/**
	* 后台获取用户提现信息列表<Page>
	* 
	* @param userName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiPromoterInfoPage (String userName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiPromoterInfoController]:begin findBusiPromoterInfoPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiPromoterInfo> page = busiPromoterInfoService.findBusiPromoterInfoPage(userName, pageNum, pageSize);
		Page<BusiPromoterInfoVO> pageVO = new Page<BusiPromoterInfoVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiPromoterInfo busiPromoterInfo : page.getResult()) {
			hashUserUuids.add(busiPromoterInfo.getBspioUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);	
		
		List<BusiPromoterInfoVO> vos = new ArrayList<BusiPromoterInfoVO>();
		BusiPromoterInfoVO vo;
		for (BusiPromoterInfo busiPromoterInfo : page.getResult()) {
			vo = new BusiPromoterInfoVO();			vo.convertPOToVO(busiPromoterInfo);
			vo.setBspioUserName(userMap.get(busiPromoterInfo.getBspioUser())==null?null:userMap.get(busiPromoterInfo.getBspioUser()).getCrusrName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiPromoterInfoController]:end findBusiPromoterInfoPage");
	}

}