package com.xiaoyu.lingdian.controller.order;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
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
import com.xiaoyu.lingdian.entity.BusiEvaluate;
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.BusiOrder;
import com.xiaoyu.lingdian.entity.BusiOrderItem;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiMessageCenterService;
import com.xiaoyu.lingdian.service.order.BusiEvaluateService;
import com.xiaoyu.lingdian.service.order.BusiOrderItemService;
import com.xiaoyu.lingdian.service.order.BusiOrderService;
import com.xiaoyu.lingdian.vo.BusiEvaluateVO;

@Controller
@RequestMapping(value="/busiEvaluate")
public class BusiEvaluateController extends BaseController {

	/**
	* 评价表
	*/
	@Autowired
	private BusiEvaluateService busiEvaluateService;
	
	/**
	 * 用户表
	 */
	@Autowired
	private CoreUserService coreUserService;
	
	/**
	* 商城订单表
	*/
	@Autowired
	private BusiOrderService busiOrderService;
	
	/**
	* 商城订单明细表
	*/
	@Autowired
	private BusiOrderItemService busiOrderItemService;

	/**
	* 用户消息中心表
	*/
	@Autowired
	private BusiMessageCenterService busiMessageCenterService;
	
	/**
	* 评价（默认启用）
	*
	* @param bseveOrderItem 订单明细标识
	* @param bseveProduct 商品标识
	* @param productName 商品名称
	* @param sharePic 推客封面图
	* @param storePic 商城封面图
	* @param bseveAttrName 选择商品属性组合名称（颜色：绿色尺寸：L）
	* @param bseveUser 评价人(通过用户表去获取姓名和头像)
	* @param bseveContent 评价内容
	* @param bseveGrade 评价等级:1好评2中评3差评
	* @param bsevePic1 评价图1
	* @param bsevePic2 评价图2
	* @param bsevePic3 评价图3
	* @param bsevePic4 评价图4
	* @param bsevePic5 评价图5
	* @return
	*/
	@RequestMapping(value="/add/busiEvaluate", method=RequestMethod.POST)
	public void addBusiEvaluate (String bseveOrderItem, String bseveProduct, String productName, String sharePic, String storePic, String bseveAttrName, String bseveUser, String bseveContent, Integer bseveGrade, String bsevePic1, String bsevePic2, String bsevePic3, String bsevePic4, String bsevePic5, HttpServletResponse response) {
		logger.info("[BusiEvaluateController]:begin addBusiEvaluate");

		if (StringUtil.isEmpty(bseveOrderItem)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单标识不能为空!"), response);
			logger.info("[BusiSignRecordController]:end addBusiEvaluate");
			return;
		}
		if (StringUtil.isEmpty(bseveProduct)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品标识不能为空!"), response);
			logger.info("[BusiSignRecordController]:end addBusiEvaluate");
			return;
		}
		if (StringUtil.isEmpty(bseveAttrName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "选择的商品属性组合名称不能为空!"), response);
			logger.info("[BusiSignRecordController]:end addBusiEvaluate");
			return;
		}
		if (StringUtil.isEmpty(bseveUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "评价人不能为空!"), response);
			logger.info("[BusiSignRecordController]:end addBusiEvaluate");
			return;
		}
		if (StringUtil.isEmpty(bseveContent)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "评价内容不能为空!"), response);
			logger.info("[BusiSignRecordController]:end addBusiEvaluate");
			return;
		}
		if (null == bseveGrade) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "评价等级不能为空!"), response);
			logger.info("[BusiSignRecordController]:end addBusiEvaluate");
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(bseveUser);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "评价人不存在!"), response);
			logger.info("[BusiSignRecordController]:end addBusiEvaluate");
			return;
		}

		BusiOrderItem orderItem = new BusiOrderItem();
		orderItem.setBsoimUuid(bseveOrderItem);
		orderItem = busiOrderItemService.getBusiOrderItem(orderItem);
		if (null == orderItem) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiSignRecordController]:end addBusiEvaluate");
			return;
		}
		if (orderItem.getBsoimStatus() != 6) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单当前状态不能评价!"), response);
			logger.info("[BusiSignRecordController]:end addBusiEvaluate");
			return;
		}
		if (!orderItem.getBsoimUser().equals(bseveUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "购买人和评价人不一致!"), response);
			logger.info("[BusiSignRecordController]:end addBusiEvaluate");
			return;
		}

		Date date = new Date();
				BusiEvaluate busiEvaluate = new BusiEvaluate();
		String uuid = RandomUtil.generateString(16);
		busiEvaluate.setBseveUuid(uuid);
		busiEvaluate.setBseveOrderItem(bseveOrderItem);
		busiEvaluate.setBseveProduct(bseveProduct);
		busiEvaluate.setBseveAttrName(bseveAttrName);
		busiEvaluate.setBseveUser(bseveUser);
		busiEvaluate.setBseveName(coreUser.getCrusrName());
		busiEvaluate.setBseveHead(coreUser.getCrusrHead());
		busiEvaluate.setBseveContent(bseveContent);
		busiEvaluate.setBseveGrade(bseveGrade);
		busiEvaluate.setBseveStatus(1);
		busiEvaluate.setBsevePic1(bsevePic1);
		busiEvaluate.setBsevePic2(bsevePic2);
		busiEvaluate.setBsevePic3(bsevePic3);
		busiEvaluate.setBsevePic4(bsevePic4);
		busiEvaluate.setBsevePic5(bsevePic5);
		busiEvaluate.setBseveCdate(date);
		busiEvaluateService.insertBusiEvaluate(busiEvaluate);

		//修改用户的待评价数量等
		coreUserService.updateCoreUserByOrderCount(bseveUser, 0, 0, 0, 0, 0, -1, 0, 1, 0);
		
		//修改订单的状态为已完成
		BusiOrderItem busiOrderItem = new BusiOrderItem();
		busiOrderItem.setBsoimUuid(bseveOrderItem);
		busiOrderItem.setBsoimStatus(8);
		busiOrderItem.setBsoimEvaluateTime(date);
		busiOrderItem.setBsoimFinishTime(date);
		busiOrderItem.setBsoimUdate(date);
		busiOrderItemService.updateBusiOrderItem(busiOrderItem);
		
		//判断同一个父订单下还有没有没完成的子订单
		List<BusiOrderItem> orderItemList = busiOrderItemService.findBusiOrderItemForListsByOrdWWC(orderItem.getBsoimOrder());
		if (null == orderItemList || orderItemList.size() <= 0) { //都完成了
			//父订单状态改为完成
			BusiOrder busiOrder = new BusiOrder();
			busiOrder.setBsorrUuid(orderItem.getBsoimOrder());
			busiOrder.setBsorrStatus(5); //完成交易		
			busiOrder.setBsorrUdate(date);
			busiOrderService.updateBusiOrder(busiOrder);
		}
		
		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(bseveUser);
		busiMessageCenter.setBsmecCdate(date);
		busiMessageCenter.setBsmecUdate(date);
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(5);
		busiMessageCenter.setBsmecProductPic(sharePic);
		busiMessageCenter.setBsmecContent("感谢亲对"+productName+"商品的评价!");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "评价成功!"),response);
		logger.info("[BusiEvaluateController]:end addBusiEvaluate");
	}

	/**
	* 后台订单的评价查看
	* 
	* @return
	*/
	@RequestMapping(value="/find/view/by/order", method=RequestMethod.POST)
	public void findBusiEvaluateByOrder (String bseveOrderItem, HttpServletResponse response) {
		logger.info("[BusiEvaluateController]:begin findBusiEvaluateByOrder");
		
		if (StringUtil.isEmpty(bseveOrderItem)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "子订单标识不能为空!"), response);
			logger.info("[BusiSignRecordController]:end findBusiEvaluateByOrder");
			return;
		}
		
		BusiEvaluate busiEvaluate = new BusiEvaluate();
		busiEvaluate.setBseveOrderItem(bseveOrderItem);		busiEvaluate = busiEvaluateService.getBusiEvaluate(busiEvaluate);
		
		BusiEvaluateVO vo = new BusiEvaluateVO();		vo.convertPOToVO(busiEvaluate);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取后台订单的评价成功!", vo),response);
		logger.info("[BusiEvaluateController]:end findBusiEvaluateByOrder");
	}

	/**
	* 前台商品的评价查看<Page>
	* 
	* @param bseveProduct 商品
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd/by/product", method=RequestMethod.POST)
	public void findBusiEvaluatePage (String bseveProduct, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiEvaluateController]:begin findBusiEvaluatePage");

		if (StringUtil.isEmpty(bseveProduct)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品标识不能为空!"), response);
			logger.info("[BusiSignRecordController]:end findBusiEvaluatePage");
			return;
		}
				if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiEvaluate> page = busiEvaluateService.findBusiEvaluateForPages(bseveProduct, pageNum, pageSize);
		Page<BusiEvaluateVO> pageVO = new Page<BusiEvaluateVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiEvaluateVO> vos = new ArrayList<BusiEvaluateVO>();
		BusiEvaluateVO vo;
		for (BusiEvaluate busiEvaluate : page.getResult()) {
			vo = new BusiEvaluateVO();			vo.convertPOToVO(busiEvaluate);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiEvaluateController]:end findBusiEvaluatePage");
	}

}