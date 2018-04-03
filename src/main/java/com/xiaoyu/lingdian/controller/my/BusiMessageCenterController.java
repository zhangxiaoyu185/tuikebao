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
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiMessageCenterService;
import com.xiaoyu.lingdian.vo.BusiMessageCenterVO;

@Controller
@RequestMapping(value="/busiMessageCenter")
public class BusiMessageCenterController extends BaseController {

	/**
	* 用户消息中心表
	*/
	@Autowired
	private BusiMessageCenterService busiMessageCenterService;
	
	/**
	 * 用户表
	 */
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 添加(添加都是未读)
	*
	* @param bsmecUser 所属用户
	* @param bsmecType 消息类型:1提现2积分3等级4金额5订单(商城)
	* @param bsmecProductPic 分享端商品封面图,类型为5订单才需要传
	* @param bsmecContent 内容
	* @return
	*/
	@RequestMapping(value="/add/busiMessageCenter", method=RequestMethod.POST)
	public void addBusiMessageCenter (String bsmecUser, Integer bsmecType, String bsmecProductPic, String bsmecContent, HttpServletResponse response) {
		logger.info("[BusiMessageCenterController]:begin addBusiMessageCenter");

		if (StringUtil.isEmpty(bsmecUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[所属用户]不能为空!"), response);
			logger.info("[BusiMessageCenterController]:end addBusiMessageCenter");
			return;
		}
		
		if (StringUtil.isEmpty(bsmecContent)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[消息内容]不能为空!"), response);
			logger.info("[BusiMessageCenterController]:end addBusiMessageCenter");
			return;
		}
		
		if(null == bsmecType || bsmecType == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[消息类型]不能为空!"), response);
			logger.info("[BusiMessageCenterController]:end addBusiMessageCenter");
			return;
		}
			
		if(bsmecType == 5) {
			if (StringUtil.isEmpty(bsmecProductPic)) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单类型消息商品图片不能为空!"), response);
				logger.info("[BusiMessageCenterController]:end addBusiMessageCenter");
				return;
			}
		}
				BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		String uuid = RandomUtil.generateString(16);
		busiMessageCenter.setBsmecUuid(uuid);
		busiMessageCenter.setBsmecUser(bsmecUser);
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(bsmecType);
		busiMessageCenter.setBsmecProductPic(bsmecProductPic);
		busiMessageCenter.setBsmecContent(bsmecContent);

		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiMessageCenterController]:end addBusiMessageCenter");

	}

	/**
	* 获取单个用户消息
	*
	* @param bsmecUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiMessageCenter (String bsmecUuid, HttpServletResponse response) {
		logger.info("[BusiMessageCenterController]:begin viewsBusiMessageCenter");

		if (StringUtil.isEmpty(bsmecUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiMessageCenterController]:end viewsBusiMessageCenter");
			return;
		}

		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(bsmecUuid);
		busiMessageCenter = busiMessageCenterService.getBusiMessageCenter(busiMessageCenter);
		if(null == busiMessageCenter){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户消息不存在!"), response);
			logger.info("[BusiMessageCenterController]:end viewsBusiMessageCenter");
			return;
		}

		BusiMessageCenterVO busiMessageCenterVO = new BusiMessageCenterVO();
		busiMessageCenterVO.convertPOToVO(busiMessageCenter);
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(busiMessageCenter.getBsmecUser());
		coreUser = coreUserService.getCoreUser(coreUser);
		if(null == coreUser){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
			logger.info("[BusiMessageCenterController]:end viewsBusiMessageCenter");
			return;
		}
		busiMessageCenterVO.setBsmecUserName(coreUser.getCrusrName());

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个用户消息成功", busiMessageCenterVO), response);
		logger.info("[BusiMessageCenterController]:end viewsBusiMessageCenter");
	}

	/**
	* 商城-我的消息<Page>
	* @param bsmecUser 所属用户
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/store/find/by/cnd", method=RequestMethod.POST)
	public void findBusiMessageCenterPageByStore (String bsmecUser, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiMessageCenterController]:begin findBusiMessageCenterPageByStore");

		if (StringUtil.isEmpty(bsmecUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[所属用户]不能为空!"), response);
			logger.info("[BusiMessageCenterController]:end findBusiMessageCenterPageByStore");
			return;
		}		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiMessageCenter> page = busiMessageCenterService.findBusiMessageCenterPageByStore(bsmecUser, pageNum, pageSize);
		Page<BusiMessageCenterVO> pageVO = new Page<BusiMessageCenterVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiMessageCenterVO> vos = new ArrayList<BusiMessageCenterVO>();
		
		List<String> uuidList = new ArrayList<>();
		for (BusiMessageCenter busiMessageCenter : page.getResult()) {
			uuidList.add(busiMessageCenter.getBsmecUuid());	
		}
		if (uuidList.size() > 0) {
			busiMessageCenterService.updateBatchBusiMessageCenterByIds(uuidList);
		}

		BusiMessageCenterVO vo;
		for (BusiMessageCenter busiMessageCenter : page.getResult()) {
			vo = new BusiMessageCenterVO();
			vo.convertPOToVO(busiMessageCenter);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "商城-我的消息获取成功!", pageVO),response);
		logger.info("[BusiMessageCenterController]:end findBusiMessageCenterPageByStore");

	}

	/**
	* 分享端-我的消息<Page>
	* @param bsmecUser 所属用户
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/share/find/by/cnd", method=RequestMethod.POST)
	public void findBusiMessageCenterPageByShare (String bsmecUser, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiMessageCenterController]:begin findBusiMessageCenterPageByShare");

		if (StringUtil.isEmpty(bsmecUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[所属用户]不能为空!"), response);
			logger.info("[BusiMessageCenterController]:end findBusiMessageCenterPageByShare");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiMessageCenter> page = busiMessageCenterService.findBusiMessageCenterPageByShare(bsmecUser, pageNum, pageSize);
		Page<BusiMessageCenterVO> pageVO = new Page<BusiMessageCenterVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiMessageCenterVO> vos = new ArrayList<BusiMessageCenterVO>();
		
		List<String> uuidList = new ArrayList<>();
		for (BusiMessageCenter busiMessageCenter : page.getResult()) {
			uuidList.add(busiMessageCenter.getBsmecUuid());	
		}
		if (uuidList.size() > 0) {
			busiMessageCenterService.updateBatchBusiMessageCenterByIds(uuidList);
		}

		BusiMessageCenterVO vo;
		for (BusiMessageCenter busiMessageCenter : page.getResult()) {
			vo = new BusiMessageCenterVO();

			vo.convertPOToVO(busiMessageCenter);

			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "分享端-我的消息获取成功!", pageVO),response);
		logger.info("[BusiMessageCenterController]:end findBusiMessageCenterPageByShare");
	}

	/**
	* 后台获取用户消息列表<Page>
	* 
	* @param userName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiMessageCenterPage (String userName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiMessageCenterController]:begin findBusiMessageCenterPage");

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiMessageCenter> page = busiMessageCenterService.findBusiMessageCenterForPages(userName, pageNum, pageSize);
		Page<BusiMessageCenterVO> pageVO = new Page<BusiMessageCenterVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiMessageCenter busiMessageCenter : page.getResult()) {
			hashUserUuids.add(busiMessageCenter.getBsmecUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiMessageCenterVO> vos = new ArrayList<BusiMessageCenterVO>();
		BusiMessageCenterVO vo;
		for (BusiMessageCenter busiMessageCenter : page.getResult()) {
			vo = new BusiMessageCenterVO();
			vo.convertPOToVO(busiMessageCenter);
			vo.setBsmecUserName(userMap.get(busiMessageCenter.getBsmecUser())==null?null:userMap.get(busiMessageCenter.getBsmecUser()).getCrusrName());
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiMessageCenterController]:end findBusiMessageCenterPage");
	}

}