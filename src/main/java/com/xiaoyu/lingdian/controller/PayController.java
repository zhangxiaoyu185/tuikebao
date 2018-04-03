package com.xiaoyu.lingdian.controller;

import java.io.BufferedOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.BusiOrder;
import com.xiaoyu.lingdian.entity.BusiOrderItem;
import com.xiaoyu.lingdian.entity.BusiShareReceipt;
import com.xiaoyu.lingdian.entity.BusiShareRecord;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.entity.CoreWechat;
import com.xiaoyu.lingdian.entity.weixin.Constant;
import com.xiaoyu.lingdian.entity.weixin.PayOrder;
import com.xiaoyu.lingdian.entity.weixin.PayPackage;
import com.xiaoyu.lingdian.entity.weixin.PayResult;
import com.xiaoyu.lingdian.entity.weixin.Template;
import com.xiaoyu.lingdian.entity.weixin.TemplateData;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.CoreWechatService;
import com.xiaoyu.lingdian.service.my.BusiMessageCenterService;
import com.xiaoyu.lingdian.service.my.BusiShareReceiptService;
import com.xiaoyu.lingdian.service.my.BusiShareRecordService;
import com.xiaoyu.lingdian.service.order.BusiOrderItemService;
import com.xiaoyu.lingdian.service.order.BusiOrderService;
import com.xiaoyu.lingdian.service.product.BusiProductService;
import com.xiaoyu.lingdian.tool.BaseConstant;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.tool.wx.WeixinUtil;
import com.xiaoyu.lingdian.tool.wx.WxPayUtil;
import com.xiaoyu.lingdian.vo.other.OrderPay;
import com.xiaoyu.lingdian.vo.other.OrderPayItem;

@Controller
@RequestMapping(value="/pay")
public class PayController extends BaseController {

	/**
	 * 用户表
	 */
	@Autowired
	private CoreUserService coreUserService;		
	
	/**
	* 商品表
	*/
	@Autowired
	private BusiProductService busiProductService;
	
	/**
	* 商城订单表
	*/
	@Autowired
	private BusiOrderService busiOrderService;
	
	/**
	 * 公众号表
	 */
	@Autowired
	private CoreWechatService coreWechatService;
	
	/**
	* 商城订单明细表
	*/
	@Autowired
	private BusiOrderItemService busiOrderItemService;
	
	/**
	* 用户分享记录表
	*/
	@Autowired
	private BusiShareRecordService busiShareRecordService;
	
	/**
	* 分享商品购买收货记录表
	*/
	@Autowired
	private BusiShareReceiptService busiShareReceiptService;
	
   /**
    * 用户消息中心表
    */
    @Autowired
    private BusiMessageCenterService busiMessageCenterService;

	/**
	 * 商品去支付获取请求预支付id报文
	 * 
	 * @param shareUserUuid 分享人Uuid
	 * @param orderPay 订单标识
	 * @param response
	 */
	@RequestMapping(value = "/get/package/go/pay", method = RequestMethod.POST)
	public void getPackageGoPay(OrderPay orderPay, HttpServletResponse response) {
		logger.info("[PayController]:begin getPackageGoPay.");
		if (null == orderPay) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (StringUtil.isEmpty(orderPay.getBsorrUser())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户标识必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (StringUtil.isEmpty(orderPay.getBsorrProvince()) || StringUtil.isEmpty(orderPay.getBsorrProvinceName())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "省份必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (StringUtil.isEmpty(orderPay.getBsorrCity()) || StringUtil.isEmpty(orderPay.getBsorrCityName())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "城市必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (StringUtil.isEmpty(orderPay.getBsorrCounty()) || StringUtil.isEmpty(orderPay.getBsorrCountyName())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "乡镇必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (StringUtil.isEmpty(orderPay.getBsorrAddress())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "详细地址必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (StringUtil.isEmpty(orderPay.getBsorrMobile())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "手机号必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (null == orderPay.getBsorrActualPay() || 0 == orderPay.getBsorrActualPay()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "实际支付必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (null == orderPay.getBsorrExpressValue()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "运费总价必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (null == orderPay.getBsorrOrderValue() || 0 == orderPay.getBsorrOrderValue()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单总价必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (null == orderPay.getBsorrProductValue() || 0 == orderPay.getBsorrProductValue()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品总价必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (null == orderPay.getBsorrTotalQuantity() || 0 == orderPay.getBsorrTotalQuantity()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品总数必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (null == orderPay.getBsorrTotalValue() || 0 == orderPay.getBsorrTotalValue()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "合计支付金额必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		if (StringUtil.isEmpty(orderPay.getOrderPayItemStr())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品明细必传！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		
		List<OrderPayItem> orderPayItemList = JSON.parseArray(orderPay.getOrderPayItemStr(), OrderPayItem.class);
		if (null == orderPayItemList || orderPayItemList.size() <= 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品明细解析错误！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}

		CoreWechat coreWechat = new CoreWechat();
		coreWechat.setCrwctUuid(BaseConstant.STORE_WE_CHAT_UUID);
		coreWechat = coreWechatService.getCoreWechat(coreWechat);
		if (null == coreWechat) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "微信未配置！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(orderPay.getBsorrUser());
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在！"), response);
			logger.info("[PayController]:end getPackageGoPay.");
			return;
		}
		
		//添加商品订单为支付中
		BusiOrder busiOrder = new BusiOrder();
		String uuid = RandomUtil.generateString(16);
		busiOrder.setBsorrUuid(uuid);
		busiOrder.setBsorrUser(orderPay.getBsorrUser());
		busiOrder.setBsorrProductValue(orderPay.getBsorrProductValue());
		busiOrder.setBsorrExpressValue(orderPay.getBsorrExpressValue());
		busiOrder.setBsorrOrderValue(orderPay.getBsorrOrderValue());
		busiOrder.setBsorrActualPay(orderPay.getBsorrActualPay());
		busiOrder.setBsorrTotalValue(orderPay.getBsorrTotalValue());
		busiOrder.setBsorrTradeNo(uuid);
		busiOrder.setBsorrTotalQuantity(orderPay.getBsorrTotalQuantity());
		busiOrder.setBsorrStatus(1);
		busiOrder.setBsorrCdate(new Date());
		busiOrder.setBsorrUdate(new Date());
		busiOrder.setBsorrName(orderPay.getBsorrName());
		busiOrder.setBsorrIdCard(orderPay.getBsorrIdCard());
		busiOrder.setBsorrMobile(orderPay.getBsorrMobile());
		busiOrder.setBsorrZipCode(orderPay.getBsorrZipCode());
		busiOrder.setBsorrProvince(orderPay.getBsorrProvince());
		busiOrder.setBsorrProvinceName(orderPay.getBsorrProvinceName());
		busiOrder.setBsorrCity(orderPay.getBsorrCity());
		busiOrder.setBsorrCityName(orderPay.getBsorrCityName());
		busiOrder.setBsorrCounty(orderPay.getBsorrCounty());
		busiOrder.setBsorrCountyName(orderPay.getBsorrCountyName());
		busiOrder.setBsorrAddress(orderPay.getBsorrAddress());
		busiOrderService.insertBusiOrder(busiOrder);
		
		//添加商品订单明细为支付中
		for (OrderPayItem orderPayItem : orderPayItemList) {
			BusiOrderItem busiOrderItem = new BusiOrderItem();
			String itemUuid = RandomUtil.generateString(16);
			busiOrderItem.setBsoimUuid(itemUuid);
			busiOrderItem.setBsoimUser(orderPay.getBsorrUser());
			busiOrderItem.setBsoimOrder(uuid);
			busiOrderItem.setBsoimProduct(orderPayItem.getBsoimProduct());
			busiOrderItem.setBsoimProductName(orderPayItem.getBsoimProductName());
			busiOrderItem.setBsoimSharePic(orderPayItem.getBsoimSharePic());
			busiOrderItem.setBsoimStorePic(orderPayItem.getBsoimStorePic());
			busiOrderItem.setBsoimAttrName(orderPayItem.getBsoimAttrName());
			busiOrderItem.setBsoimAttrValue(orderPayItem.getBsoimAttrValue());
			busiOrderItem.setBsoimTags(orderPayItem.getBsoimTags());
			busiOrderItem.setBsoimQuantity(orderPayItem.getBsoimQuantity());
			busiOrderItem.setBsoimExpress(orderPayItem.getBsoimExpress());
			busiOrderItem.setBsoimIsBom(orderPayItem.getBsoimIsBom());
			busiOrderItem.setBsoimValue(orderPayItem.getBsoimValue());
			busiOrderItem.setBsoimPrice(orderPayItem.getBsoimPrice());
			busiOrderItem.setBsoimStatus(1);
			busiOrderItem.setBsoimCdate(new Date());
			busiOrderItem.setBsoimUdate(new Date());
			busiOrderItem.setBsoimOrderTime(new Date());
			busiOrderItem.setBsoimBuyerMemo(orderPayItem.getBsoimBuyerMemo());
			busiOrderItemService.insertBusiOrderItem(busiOrderItem);
		
			logger.info("shareUserUuid:" + orderPayItem.getShareUserUuid());
			
			if (!StringUtil.isEmpty(orderPayItem.getShareUserUuid())) { //分享人不为空，
				BusiShareRecord shareRecord = busiShareRecordService.getBusiShareRecordByProductAndUser(orderPayItem.getShareUserUuid(), orderPayItem.getBsoimProduct());	
				if (null != shareRecord) {
				    //插入分享商品购买收货记录表
					BusiShareReceipt busiShareReceipt = new BusiShareReceipt();
					busiShareReceipt.setBssrtUuid(RandomUtil.generateString(16));
					busiShareReceipt.setBssrtUser(orderPayItem.getShareUserUuid());
					busiShareReceipt.setBssrtGrade(shareRecord.getBssrdGrade());
					busiShareReceipt.setBssrtProduct(shareRecord.getBssrdProduct());
					busiShareReceipt.setBssrtSharePic(shareRecord.getBssrdSharePic());
					busiShareReceipt.setBssrtStorePic(shareRecord.getBssrdStorePic());
					busiShareReceipt.setBssrtProductName(shareRecord.getBssrdProductName());
					busiShareReceipt.setBssrtBuyUser(orderPay.getBsorrUser());
					busiShareReceipt.setBssrtOrderNo(itemUuid);
					busiShareReceipt.setBssrtCdate(new Date());		
					busiShareReceipt.setBssrtBuyCount(busiOrderItem.getBsoimQuantity());
					busiShareReceipt.setBssrtBuyFee(busiOrderItem.getBsoimValue());
					busiShareReceipt.setBssrtBackNow(shareRecord.getBssrdCommision() * busiOrderItem.getBsoimQuantity());
					busiShareReceipt.setBssrtStatus(1);
					busiShareReceiptService.insertBusiShareReceipt(busiShareReceipt);
					//插入用户消息中心,提示分享商品已有人已经购买
		            BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		            busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		            busiMessageCenter.setBsmecUser(orderPayItem.getShareUserUuid());
		            busiMessageCenter.setBsmecCdate(new Date());
		            busiMessageCenter.setBsmecUdate(new Date());
		            busiMessageCenter.setBsmecStatus(1);
		            busiMessageCenter.setBsmecType(6);//6 分享
		            busiMessageCenter.setBsmecProductPic(null);
		            busiMessageCenter.setBsmecContent("你分享的商品已经有人购买了!");
		            busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
				}
			}
		}
		
		coreUserService.updateCoreUserByOrderCount(orderPay.getBsorrUser(), 0, 1, 1, 0, 0, 0, 0, 0, 0);
		
		PayOrder payOrder = new PayOrder(coreWechat.getCrwctAppid(), coreWechat.getCrwctAppsecret(), orderPayItemList.get(0).getBsoimProductName(),
				coreUser.getCrusrOpenidStore(), orderPayItemList.get(0).getBsoimProductName(), orderPay.getBsorrTotalValue(), uuid,
				coreWechat.getCrwctNotifyurl(), coreWechat.getCrwctPartner(), coreWechat.getCrwctPartnerkey(), Constant.DEFAULT_CHARSET);
		
		WxPayUtil wxPayUtil = new WxPayUtil();
		PayPackage finaPackage = wxPayUtil.getPackage(payOrder);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取package成功", finaPackage), response);
		logger.info("[PayController]:end getPackageGoPay.");
	}

	/**
	 * 订单去支付获取请求预支付id报文
	 * 
	 * @param orderUuid 订单标识
	 * @param userUuid 支付人标识
	 * @param response
	 */
	@RequestMapping(value = "/get/package/order/go/pay", method = RequestMethod.POST)
	public void getPackageOrderGoPay(String orderUuid, String userUuid, HttpServletResponse response) {
		logger.info("[PayController]:begin getPackageOrderGoPay.");
		if (StringUtil.isEmpty(orderUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单标识必传！"), response);
			logger.info("[PayController]:end getPackageOrderGoPay.");
			return;
		}
		if (StringUtil.isEmpty(userUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "支付人标识必传！"), response);
			logger.info("[PayController]:end getPackageOrderGoPay.");
			return;
		}
		
		CoreWechat coreWechat = new CoreWechat();
		coreWechat.setCrwctUuid(BaseConstant.STORE_WE_CHAT_UUID);
		coreWechat = coreWechatService.getCoreWechat(coreWechat);
		if (null == coreWechat) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "微信未配置！"), response);
			logger.info("[PayController]:end getPackageOrderGoPay.");
			return;
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(userUuid);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在！"), response);
			logger.info("[PayController]:end getPackageOrderGoPay.");
			return;
		}
		
		//更新商品订单为支付中
		BusiOrder busiOrderOld = new BusiOrder();
		busiOrderOld.setBsorrUuid(orderUuid);
		busiOrderOld = busiOrderService.getBusiOrder(busiOrderOld);
		if (null == busiOrderOld) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在！"), response);
			logger.info("[PayController]:end getPackageOrderGoPay.");
			return;
		}

		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrUuid(orderUuid);
		busiOrder.setBsorrStatus(1);
		busiOrder.setBsorrUdate(new Date());
		busiOrderService.updateBusiOrder(busiOrder);

		///更新子订单为支付中
		BusiOrderItem busiOrderItem = new BusiOrderItem();
		busiOrderItem.setBsoimOrder(orderUuid);
		busiOrderItem.setBsoimStatus(1);
		busiOrderItem.setBsoimUdate(new Date());
		busiOrderItemService.updateBusiOrderItemByOrder(busiOrderItem);	

		PayOrder payOrder = new PayOrder(coreWechat.getCrwctAppid(), coreWechat.getCrwctAppsecret(), "订单"+orderUuid+"支付成功",
				coreUser.getCrusrOpenidStore(), "订单"+orderUuid+"支付", busiOrderOld.getBsorrTotalValue(), orderUuid+"-"+RandomUtil.generateString(3),
				coreWechat.getCrwctNotifyurl(), coreWechat.getCrwctPartner(), coreWechat.getCrwctPartnerkey(), Constant.DEFAULT_CHARSET);
		
		WxPayUtil wxPayUtil = new WxPayUtil();
		PayPackage finaPackage = wxPayUtil.getPackage(payOrder);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取package成功", finaPackage), response);
		logger.info("[PayController]:end getPackageOrderGoPay.");
	}

	/**
	 * 回调函数
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/notify", method = RequestMethod.POST)	
	public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("微信支付回调数据开始");
		String inputLine;
		String notityXml = "";
		String resXml = "";
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("接收到的报文：" + notityXml);
		PayResult wpr = WxPayUtil.parseXmlToList(notityXml);
		if (StringUtil.isEmpty(wpr.getOutTradeNo())) {
			return;
		}
		String outTradeNo = wpr.getOutTradeNo();
		if (wpr.getOutTradeNo().length() > 16) {
			outTradeNo = wpr.getOutTradeNo().substring(0, 16);
		}
		//查找订单
		BusiOrder order = new BusiOrder();
		order.setBsorrUuid(outTradeNo);
		order = busiOrderService.getBusiOrder(order);
		if (null == order) {
			return;
		}
		if (order.getBsorrStatus() != 1 && order.getBsorrStatus() != 3) { //不是在支付环节,防止重复回调
			return;
		}
		if ("SUCCESS".equals(wpr.getResultCode())) { // 支付成功
			String accessToken = coreWechatService.getAccessToken(BaseConstant.STORE_WE_CHAT_UUID);
			Template t = new Template();
			t.setUrl("");
			t.setTouser(wpr.getOpenid());
			t.setTopcolor("#000000");
			t.setTemplate_id("eNBknEE7zvcVdGN6uwYse0GvBItCjKo7azg3k6Vcp_A");
			Map<String, TemplateData> map = new HashMap<String, TemplateData>();
			TemplateData nameData = new TemplateData();
			nameData.setColor("#000000");
			nameData.setValue(wpr.getAttach());
			map.put("name", nameData);
			TemplateData remarkData = new TemplateData();
			remarkData.setColor("#000000");
			remarkData.setValue("我们将尽快为您打包发货!");
			map.put("remark", remarkData);
			t.setData(map);
			String jsonStr = JSONObject.fromObject(t).toString(); // 此处你应该代入自己的template
			WeixinUtil.sendTemplateMsg(jsonStr, accessToken);
			
			coreUserService.updateCoreUserByOrderCount(order.getBsorrUser(), 0, 0, -1, 1, 0, 0, 0, 0, 0);
			
			//更新订单为支付成功
			BusiOrder busiOrder = new BusiOrder();
			busiOrder.setBsorrUuid(outTradeNo);
			busiOrder.setBsorrStatus(2); //支付成功		
			busiOrder.setBsorrUdate(new Date());
			busiOrder.setBsorrPayTime(new Date());
			busiOrderService.updateBusiOrder(busiOrder);
			
			//更新子订单
			BusiOrderItem busiOrderItem = new BusiOrderItem();
			busiOrderItem.setBsoimOrder(outTradeNo);
			busiOrderItem.setBsoimStatus(2);
			busiOrderItem.setBsoimUdate(new Date());
			busiOrderItem.setBsoimPayTime(new Date());
			busiOrderItemService.updateBusiOrderItemByOrder(busiOrderItem);
			resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml> ";
		} else {
			//商品订单记录为支付失败
			BusiOrder busiOrder = new BusiOrder();
			busiOrder.setBsorrUuid(outTradeNo);
			busiOrder.setBsorrStatus(3); //支付失败		
			busiOrder.setBsorrUdate(new Date());
			busiOrderService.updateBusiOrder(busiOrder);
			
			//更新子订单
			BusiOrderItem busiOrderItem = new BusiOrderItem();
			busiOrderItem.setBsoimOrder(outTradeNo);
			busiOrderItem.setBsoimStatus(3);
			busiOrderItem.setBsoimUdate(new Date());
			busiOrderItemService.updateBusiOrderItemByOrder(busiOrderItem);
			resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[fail]]></return_msg></xml> ";
		}
		logger.info("微信支付回调数据结束");
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(resXml.getBytes());
		out.flush();
		out.close();
		return;
	}

}