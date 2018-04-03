package com.xiaoyu.lingdian.controller.order;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyu.lingdian.tool.DateUtil;
import com.xiaoyu.lingdian.tool.KDNQueryUtil;
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
import com.xiaoyu.lingdian.entity.BusiOrder;
import com.xiaoyu.lingdian.entity.BusiOrderExpress;
import com.xiaoyu.lingdian.entity.BusiOrderItem;
import com.xiaoyu.lingdian.entity.BusiProfitRecord;
import com.xiaoyu.lingdian.entity.BusiShareReceipt;
import com.xiaoyu.lingdian.entity.BusiShareRecord;
import com.xiaoyu.lingdian.entity.CoreMessageCenter;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreMessageCenterService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiMessageCenterService;
import com.xiaoyu.lingdian.service.my.BusiProfitRecordService;
import com.xiaoyu.lingdian.service.my.BusiShareReceiptService;
import com.xiaoyu.lingdian.service.my.BusiShareRecordService;
import com.xiaoyu.lingdian.service.order.BusiOrderExpressService;
import com.xiaoyu.lingdian.service.order.BusiOrderItemService;
import com.xiaoyu.lingdian.service.order.BusiOrderService;
import com.xiaoyu.lingdian.vo.BusiOrderExpressVO;
import com.xiaoyu.lingdian.vo.BusiOrderItemVO;
import com.xiaoyu.lingdian.vo.BusiOrderVO;

@Controller
@RequestMapping(value="/busiOrder")
public class BusiOrderController extends BaseController {

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
	* 系统消息中心表
	*/
	@Autowired
	private CoreMessageCenterService coreMessageCenterService;

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
	* 分享商品购买收货记录表
	*/
	@Autowired
	private BusiShareReceiptService busiShareReceiptService;
	
	/**
	* 用户分享记录表
	*/
	@Autowired
	private BusiShareRecordService busiShareRecordService;
	
	/**
	* 用户收益明细表
	*/
	@Autowired
	private BusiProfitRecordService busiProfitRecordService;
	
	/**
	* 订单明细快递物流表
	*/
	@Autowired
	private BusiOrderExpressService busiOrderExpressService;
	
	/** 
     * 父订单取消订单 
     * 
	 * @param bsorrUuid 父订单号
	 */
	@RequestMapping(value="/cancel/order", method=RequestMethod.POST)
	public void cancelOrder (String bsorrUuid, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin cancelOrder");

		if (StringUtil.isEmpty(bsorrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderController]:end cancelOrder");
			return;
		}
		
		BusiOrder order = new BusiOrder();
		order.setBsorrUuid(bsorrUuid);
		order = busiOrderService.getBusiOrder(order);
		if (null == order) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end cancelOrder");
			return;
		}
		if (1 != order.getBsorrStatus() && 3 != order.getBsorrStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当前状态下不能取消订单!"), response);
			logger.info("[BusiOrderController]:end cancelOrder");
			return;
		}	
		
		Date date = new Date();
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrUuid(bsorrUuid);
		busiOrder.setBsorrStatus(4);		
		busiOrder.setBsorrUdate(date);
		busiOrderService.updateBusiOrder(busiOrder);

		//取消子订单
		BusiOrderItem busiOrderItem = new BusiOrderItem();
		busiOrderItem.setBsoimOrder(bsorrUuid);
		busiOrderItem.setBsoimStatus(4);
		busiOrderItem.setBsoimCancelTime(date);
		busiOrderItem.setBsoimUdate(date);
		busiOrderItemService.updateBusiOrderItemByOrder(busiOrderItem);
		
		coreUserService.updateCoreUserByOrderCount(order.getBsorrUser(), 0, -1, -1, 0, 0, 0, 0, 0, 1);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "取消订单成功!"),response);
		logger.info("[BusiOrderController]:end cancelOrder");
	}

	/**
	 * 子订单提醒发货
	 * 
	 * @param bsoimUuid 子订单号
	 */
	@RequestMapping(value="/item/prompt/shipment", method=RequestMethod.POST)
	public void itemPromptShipment (String bsoimUuid, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin itemPromptShipment");

		if (StringUtil.isEmpty(bsoimUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderController]:end itemPromptShipment");
			return;
		}
		
		BusiOrderItem orderItem = new BusiOrderItem();
		orderItem.setBsoimUuid(bsoimUuid);
		orderItem = busiOrderItemService.getBusiOrderItem(orderItem);
		if (null == orderItem) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end itemPromptShipment");
			return;
		}
		if (2 != orderItem.getBsoimStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当前状态下不能提醒发货!"), response);
			logger.info("[BusiOrderController]:end itemPromptShipment");
			return;
		}

		//发送提箱消息
		CoreMessageCenter coreMessageCenter = new CoreMessageCenter();
		String uuid = RandomUtil.generateString(16);
		coreMessageCenter.setCrmecUuid(uuid);
		coreMessageCenter.setCrmecContent("订单号"+orderItem.getBsoimOrder()+orderItem.getBsoimProductName()+"提醒发货");
		coreMessageCenter.setCrmecCdate(new Date());
		coreMessageCenter.setCrmecUdate(new Date());
		coreMessageCenter.setCrmecStatus(2);
		coreMessageCenter.setCrmecType(1);
		coreMessageCenterService.insertCoreMessageCenter(coreMessageCenter);
		
		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(orderItem.getBsoimUser());
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(5);
		busiMessageCenter.setBsmecProductPic(orderItem.getBsoimSharePic());
		busiMessageCenter.setBsmecContent("您购买的"+orderItem.getBsoimProductName()+"商品提醒发货成功!");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "发送消息提醒成功!"),response);
		logger.info("[BusiOrderController]:end itemPromptShipment");
	}

	/**
	 * 查看物流信息<List>
	 * 
	 * @param bsoimUuid 子订单号
	 * @param bsoimExpressCode 物流单号
	 * @param bsoimExpno 物流公司编码
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/find/logistics/list", method = RequestMethod.POST)
	public void findLogisticsList(String bsoimUuid, String bsoimExpressCode,String bsoimExpno, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin findLogisticsList");
		if (StringUtil.isEmpty(bsoimUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "子订单号不能为空!"),response);
			logger.info("[BusiOrderController]:end findLogisticsList");
			return;
		}
		if (StringUtil.isEmpty(bsoimExpressCode)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "快递公司编号不能为空!"),response);
			logger.info("[BusiOrderController]:end findLogisticsList");
			return;
		}
		if (StringUtil.isEmpty(bsoimExpno)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "快递单号不能为空!"),response);
			logger.info("[BusiOrderController]:end findLogisticsList");
			return;
		}

		// 根据子订单号查询子订单
		BusiOrderItem orderItem = new BusiOrderItem();
		orderItem.setBsoimUuid(bsoimUuid);
		orderItem = busiOrderItemService.getBusiOrderItem(orderItem);
		if (null == orderItem) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end findLogisticsList");
			return;
		} 
		if (orderItem.getBsoimStatus() < 5 || StringUtil.isEmpty(orderItem.getBsoimExpno()) || StringUtil.isEmpty(orderItem.getBsoimExpressCode())) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单发货信息不存在!"),response);
			logger.info("[BusiOrderController]:end findLogisticsList");
			return;
		}
		 
		List<BusiOrderExpressVO> listExpressVO = new ArrayList<BusiOrderExpressVO>();
		BusiOrderExpressVO expressVO;
		
		// 初始化物流信息对象并插入订单ID
		BusiOrderExpress busiOrderExpress = new BusiOrderExpress();
		busiOrderExpress.setBsoesOrderItem(bsoimUuid);
				
		if (orderItem.getBsoimStatus() > 6) { //如果状态为确认收货后，直接查询数据库
    			List<BusiOrderExpress> listsbyOrderId = busiOrderExpressService.findBusiOrderExpressForListsbyOrderId(busiOrderExpress);// 查询单个id的物流
    			if (null == listsbyOrderId || listsbyOrderId.size()==0){
                     writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "物流信息已过期!"),response);
                     logger.info("[BusiOrderController]:end findLogisticsList");
                     return;
                 } 
    			for (BusiOrderExpress express : listsbyOrderId) {
    			    expressVO = new BusiOrderExpressVO();
    			    expressVO.convertPOToVO(express);
    			    listExpressVO.add(expressVO); 
                }  
    			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1,"物流信息查看成功!",listExpressVO), response);
    			logger.info("[BusiOrderController]:end findLogisticsList");
    			return;
		}
		  
		String result = "";
		KDNQueryUtil kiaoApi = new KDNQueryUtil();

		if (null == orderItem.getBsoimLastDate()) { // 第一次查询 
    		    //更新商品订单物流上次查询时间为现在
    		    BusiOrderItem busiOrderItem = new BusiOrderItem();
    		    busiOrderItem.setBsoimUuid(bsoimUuid);
    		    busiOrderItem.setBsoimLastDate(new Date());
    		    busiOrderItemService.updateBusiOrderItem(busiOrderItem); 
    			//去物流公司查询
    		    JSONArray jsonArray = null;
    			try {
    				result = kiaoApi.getOrderTracesByJson(bsoimExpressCode, bsoimExpno);
    				if (null != result) {
    				    JSONObject parseObject = JSON.parseObject(result);
    	                if (parseObject.getBoolean("Success")) {
    	                     jsonArray = parseObject.getJSONArray("Traces");
    	                }else{
    	                    writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "不支持该快递公司查询，或者快递公司编码错误!"),response);
    	                    logger.info("[BusiOrderController]:end findLogisticsList");
    	                    return;
    	                } 
    				}else{
    				    writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"),response);
    	                logger.info("[BusiOrderController]:end findLogisticsList");
    	                return;
    				} 
    			} catch (Exception e) {
    				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"),response);
    				logger.info("[BusiOrderController]:end findLogisticsList"+e.getMessage());
    				return;
    			}
    			// 查询到数据解析存储到物流表，返回数据  
    			if (null == jsonArray || jsonArray.size()==0){  
    			    writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"),response);
    	            logger.info("[BusiOrderController]:end findLogisticsList");
    	            return;
    			} 
    			// 1、插入数据库
                for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {//循环访问数据库待改
                    JSONObject job = (JSONObject) iterator.next(); 
                    if(null != job && job.getString("AcceptStation")!=null && job.getDate("AcceptTime") !=null){ 
                        BusiOrderExpress busiOrderExpressL = new BusiOrderExpress();
                        busiOrderExpressL.setBsoesUuid(RandomUtil.generateString(16));
                        busiOrderExpressL.setBsoesOrderItem(bsoimUuid);
                        busiOrderExpressL.setBsoesContent(job.getString("AcceptStation"));
                        busiOrderExpressL.setBsoesCdate(job.getDate("AcceptTime"));
                        busiOrderExpressService.insertBusiOrderExpress(busiOrderExpressL);
                    }
                } 
    			//2、查插入的数据库数据
    			List<BusiOrderExpress> listsbyOrderId = busiOrderExpressService.findBusiOrderExpressForListsbyOrderId(busiOrderExpress);
    			if (null == listsbyOrderId || listsbyOrderId.size()==0){
                    writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"),response);
                    logger.info("[BusiOrderController]:end findLogisticsList");
                    return;
                } 
    		    for (BusiOrderExpress express : listsbyOrderId) {
                    expressVO = new BusiOrderExpressVO();
                    expressVO.convertPOToVO(express);
                    listExpressVO.add(expressVO);
                } 
                writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1,"查看物流信息成功!", listExpressVO), response);
                logger.info("[BusiOrderController]:end findLogisticsList");
                return;  
		}
		
		// 30分钟内直接查询数据库
		Date data = new Date();
		long minutes = DateUtil.minuteDiff(orderItem.getBsoimLastDate(), data);
		if (minutes <= 30) { 
    			List<BusiOrderExpress> listsbyOrderId = busiOrderExpressService.findBusiOrderExpressForListsbyOrderId(busiOrderExpress);// 查询单个id的物流
    			if (null == listsbyOrderId || listsbyOrderId.size()==0){
    			    writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"),response);
                    logger.info("[BusiOrderController]:end findLogisticsList");
                    return;
    			}
    			for (BusiOrderExpress express : listsbyOrderId) {
                    expressVO = new BusiOrderExpressVO();
                    expressVO.convertPOToVO(express);
                    listExpressVO.add(expressVO);
                } 
    			writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1,"查看物流信息成功!",listExpressVO), response);
    			logger.info("[BusiOrderController]:end findLogisticsList");
    			return;
		}
		
		// 超过30分钟，重新去物流公司查
		if (minutes > 30) {   
    		   //更新商品订单物流上次查询时间为现在
                BusiOrderItem busiOrderItem = new BusiOrderItem();
                busiOrderItem.setBsoimUuid(bsoimUuid);
                busiOrderItem.setBsoimLastDate(new Date());
                busiOrderItemService.updateBusiOrderItem(busiOrderItem); 
        		// 去物流公司查询
                JSONArray jsonArray = null;
                try {
                    result = kiaoApi.getOrderTracesByJson(bsoimExpressCode, bsoimExpno);
                    if (null != result) {
                        JSONObject parseObject = JSON.parseObject(result);
                        if (parseObject.getBoolean("Success")) {
                             jsonArray = parseObject.getJSONArray("Traces");
                        }else{
                            writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "不支持该快递公司查询，或者快递公司编码错误!"),response);
                            logger.info("[BusiOrderController]:end findLogisticsList");
                            return;
                        } 
                    }else{
                        writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"),response);
                        logger.info("[BusiOrderController]:end findLogisticsList");
                        return;
                    } 
                } catch (Exception e) {
                    writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"),response);
                    logger.info("[BusiOrderController]:end findLogisticsList"+e.getMessage());
                    return;
                }
            
        		//查询到数据就del原表中数据，解析insert查询到的数据返回数据 
                if (null == jsonArray || jsonArray.size()==0){  
                    writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"),response);
                    logger.info("[BusiOrderController]:end findLogisticsList");
                    return;
                }  
        		//1、 删除原有信息
        		busiOrderExpressService.deleteBusiOrderExpress(busiOrderExpress);
        		//2、插入数据库 
                for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {//循环访问数据库待改
                    JSONObject job = (JSONObject) iterator.next(); 
                    if(null != job && job.getString("AcceptStation")!=null && job.getDate("AcceptTime") !=null){ 
                        BusiOrderExpress busiOrderExpressL = new BusiOrderExpress();
                        busiOrderExpressL.setBsoesUuid(RandomUtil.generateString(16));
                        busiOrderExpressL.setBsoesOrderItem(bsoimUuid);
                        busiOrderExpressL.setBsoesContent(job.getString("AcceptStation"));
                        busiOrderExpressL.setBsoesCdate(job.getDate("AcceptTime"));
                        busiOrderExpressService.insertBusiOrderExpress(busiOrderExpressL);
                    }
                }     
        		//3、查询数据库返回的信息
        		List<BusiOrderExpress> listExpressById = busiOrderExpressService.findBusiOrderExpressForListsbyOrderId(busiOrderExpress);
        		if (null == listExpressById || listExpressById.size()==0){
                        writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "暂无物流信息!"),response);
                        logger.info("[BusiOrderController]:end findLogisticsList");
                        return;
                }
        		for (BusiOrderExpress express : listExpressById) {
                    expressVO = new BusiOrderExpressVO();
                    expressVO.convertPOToVO(express);
                    listExpressVO.add(expressVO);
                } 
        		writeAjaxJSONResponse(ResultMessageBuilder.build(true,1,"查看物流信息成功!", listExpressVO), response);
        		logger.info("[BusiOrderController]:end findLogisticsList");
        		return;
	    } 
	}	

	/**
	 * 前端查询我的各个状态的子订单
	 * 
	 * @param bsoimUser 用户标识
	 * @param bsoimStatus 订单状态号
	 * @param pageNum
	 * @param pageSize 
	 */
	@RequestMapping(value="/item/find/by/cnd/my/status", method=RequestMethod.POST)
	public void findBusiOrderItemForPagesByMyStatus (String bsoimUser, Integer bsoimStatus, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin findBusiOrderItemForPagesByMyStatus");

		if (StringUtil.isEmpty(bsoimUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户标识不能为空!"), response);
			logger.info("[BusiOrderController]:end findBusiOrderItemForPagesByMyStatus");
			return;
		}
		if (null == bsoimStatus) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "状态不能为空!"), response);
			logger.info("[BusiOrderController]:end findBusiOrderItemForPagesByMyStatus");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiOrderItem> page = busiOrderItemService.findBusiOrderItemForPagesByMyStatusOrItem(null, bsoimUser, bsoimStatus, pageNum, pageSize);
		Page<BusiOrderItemVO> pageVO = new Page<BusiOrderItemVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiOrderItemVO> vos = new ArrayList<BusiOrderItemVO>();
		BusiOrderItemVO vo;
		for (BusiOrderItem busiOrderItem : page.getResult()) {
			vo = new BusiOrderItemVO();
			vo.convertPOToVO(busiOrderItem);
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiOrderController]:end findBusiOrderItemForPagesByMyStatus");
	}

	/**
	 * 后台根据子订单号查询各个状态的子订单
	 * 
	 * @param bsoimUuid 子订单号
	 * @param bsoimStatus 订单状态
	 * @param pageNum
	 * @param pageSize 
	 */
	@RequestMapping(value="/item/find/by/cnd/my/status/back", method=RequestMethod.POST)
	public void findBusiOrderItemForPagesByMyStatusBack (String bsoimUuid, Integer bsoimStatus, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin findBusiOrderItemForPagesByMyStatusBack");
		if (null == bsoimStatus) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "状态不能为空!"), response);
			logger.info("[BusiOrderController]:end findBusiOrderItemForPagesByMyStatusBack");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiOrderItem> page = busiOrderItemService.findBusiOrderItemForPagesByMyStatusOrItem(bsoimUuid, null, bsoimStatus, pageNum, pageSize);
		Page<BusiOrderItemVO> pageVO = new Page<BusiOrderItemVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiOrderItem busiOrderItem : page.getResult()) {
			hashUserUuids.add(busiOrderItem.getBsoimUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiOrderItemVO> vos = new ArrayList<BusiOrderItemVO>();
		BusiOrderItemVO vo;
		for (BusiOrderItem busiOrderItem : page.getResult()) {
			vo = new BusiOrderItemVO();
			vo.convertPOToVO(busiOrderItem);
			vo.setBsoimUserName(userMap.get(busiOrderItem.getBsoimUser())==null?null:userMap.get(busiOrderItem.getBsoimUser()).getCrusrName());		
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiOrderController]:end findBusiOrderItemForPagesByMyStatusBack");
	}
	
	/**
	 * 前端查询我的待支付父订单
	 * 
	 * @param bsorrUser 用户标识
	 */
	@RequestMapping(value="/find/by/cnd/my/pending", method=RequestMethod.POST)
	public void findBusiOrderPageByMyPending (String bsorrUser, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin findBusiOrderPageByMyPending");

		if (StringUtil.isEmpty(bsorrUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户标识不能为空!"), response);
			logger.info("[BusiOrderController]:end findBusiOrderPageByMyPending");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiOrder> page = busiOrderService.findBusiOrderPageByMyPendingOrOrder(null, bsorrUser, pageNum, pageSize);
		Page<BusiOrderVO> pageVO = new Page<BusiOrderVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiOrderVO> vos = new ArrayList<BusiOrderVO>();
		BusiOrderVO vo;
		for (BusiOrder busiOrder : page.getResult()) {
			vo = new BusiOrderVO();
			vo.convertPOToVO(busiOrder);
			vo.setBusiOrderItemList(busiOrderItemService.findBusiOrderItemForListsByOrd(busiOrder.getBsorrUuid()));
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiOrderController]:end findBusiOrderPageByMyPending");
	}

	/**
	 * 后台根据订单号查询待支付父订单
	 * 
	 * @param bsorrUuid 父订单号
	 * @param pageNum
	 * @param pageSize 
	 */
	@RequestMapping(value="/find/by/cnd/my/pending/back", method=RequestMethod.POST)
	public void findBusiOrderPageByMyPendingBack (String bsorrUuid, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin findBusiOrderPageByMyPendingBack");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiOrder> page = busiOrderService.findBusiOrderPageByMyPendingOrOrder(bsorrUuid, null, pageNum, pageSize);
		Page<BusiOrderVO> pageVO = new Page<BusiOrderVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiOrder busiOrder : page.getResult()) {
			hashUserUuids.add(busiOrder.getBsorrUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);

		List<BusiOrderVO> vos = new ArrayList<BusiOrderVO>();
		BusiOrderVO vo;
		for (BusiOrder busiOrder : page.getResult()) {
			vo = new BusiOrderVO();
			vo.convertPOToVO(busiOrder);
			vo.setBusiOrderItemList(busiOrderItemService.findBusiOrderItemForListsByOrd(busiOrder.getBsorrUuid()));
			vo.setBsorrUserName(userMap.get(busiOrder.getBsorrUser())==null?null:userMap.get(busiOrder.getBsorrUser()).getCrusrName());		
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiOrderController]:end findBusiOrderPageByMyPendingBack");
	}
	
	/**
	* 客服调整父订单价格(改变实际支付和合计支付金额),bsorrStatus1支付中和3支付失败才能修改
	*  
	* @param bsorrUuid 父订单标识
	* @param bsorrAdjustExpress 客服调整运费,带正负
	* @param bsorrAdjustProduct 客服调整总价,带正负
	* @return
	*/
	@RequestMapping(value="/customer/adjust", method=RequestMethod.POST)
	public void customerAdjust (String bsorrUuid, Double bsorrAdjustExpress, Double bsorrAdjustProduct, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin customerAdjust");

		if (StringUtil.isEmpty(bsorrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderController]:end customerAdjust");
			return;
		}
		if (null == bsorrAdjustExpress) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "调整的运费必传,可为0!"), response);
			logger.info("[BusiOrderController]:end customerAdjust");
			return;
		}
		if (null == bsorrAdjustProduct) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "调整的商品价格必传,可为0!"), response);
			logger.info("[BusiOrderController]:end customerAdjust");
			return;
		}
		
		BusiOrder order = new BusiOrder();
		order.setBsorrUuid(bsorrUuid);
		order = busiOrderService.getBusiOrder(order);
		if (null == order) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end customerAdjust");
			return;
		}
		if (1 != order.getBsorrStatus() && 3 != order.getBsorrStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当前状态下不能调整!"), response);
			logger.info("[BusiOrderController]:end customerAdjust");
			return;
		}	
		
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrUuid(bsorrUuid);	
		busiOrder.setBsorrAdjustExpress(bsorrAdjustExpress);
		busiOrder.setBsorrAdjustProduct(bsorrAdjustProduct);
		busiOrderService.updateBusiOrderByAdjust(busiOrder);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "客服调整价格成功!"),response);
		logger.info("[BusiOrderController]:end customerAdjust");
	}

	/**
	 * 子订单后台发货，必填快递单号
	 * 
	 * @param bsoimUuid 子订单号
	 * @param bsoimExpno 快递单号
	 * @param bsoimExpressCode 快递公司编号
	 * @param bsoimExpressName 快递公司名称
	 */
	@RequestMapping(value="/item/back/shipment", method=RequestMethod.POST)
	public void itemBackShipment (String bsoimUuid, String bsoimExpno, String bsoimExpressCode, String bsoimExpressName, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin itemBackShipment");

		if (StringUtil.isEmpty(bsoimUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderController]:end itemBackShipment");
			return;
		}
		if (StringUtil.isEmpty(bsoimExpno)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "快递单号必填!"), response);
			logger.info("[BusiOrderController]:end itemBackShipment");
			return;
		}
		if (StringUtil.isEmpty(bsoimExpressCode)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "快递公司编号必填!"), response);
			logger.info("[BusiOrderController]:end itemBackShipment");
			return;
		}

		BusiOrderItem orderItem = new BusiOrderItem();
		orderItem.setBsoimUuid(bsoimUuid);
		orderItem = busiOrderItemService.getBusiOrderItem(orderItem);
		if (null == orderItem) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end itemBackShipment");
			return;
		}
		if (2 != orderItem.getBsoimStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当前状态下不能发货!"), response);
			logger.info("[BusiOrderController]:end itemBackShipment");
			return;
		}

		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(orderItem.getBsoimUser());
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(5);
		busiMessageCenter.setBsmecProductPic(orderItem.getBsoimSharePic());
		busiMessageCenter.setBsmecContent("亲,您购买的"+orderItem.getBsoimProductName()+"商品发货了，请关注商品的物流信息");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		
		//更新订单明细状态
		BusiOrderItem busiOrderItem = new BusiOrderItem();
		busiOrderItem.setBsoimUuid(bsoimUuid);
		busiOrderItem.setBsoimStatus(5);
		busiOrderItem.setBsoimShippingTime(new Date());
		busiOrderItem.setBsoimUdate(new Date());
		busiOrderItem.setBsoimExpno(bsoimExpno);
		busiOrderItem.setBsoimExpressCode(bsoimExpressCode);
		busiOrderItem.setBsoimExpressName(bsoimExpressName);
		
		busiOrderItemService.updateBusiOrderItem(busiOrderItem);
		
		coreUserService.updateCoreUserByOrderCount(orderItem.getBsoimUser(), 0, 0, 0, -1, 1, 0, 0, 0, 0);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "后台发货成功!"),response);
		logger.info("[BusiOrderController]:end itemBackShipment");
	}
	
	/**
	 * 子订单用户确认收货
	 * 
	 * @param bsoimUuid 子订单号
	 */
	@RequestMapping(value="/item/recv/receipt", method=RequestMethod.POST)
	public void itemRecvReceipt (String bsoimUuid, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin itemRecvReceipt");
		if (StringUtil.isEmpty(bsoimUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderController]:end itemRecvReceipt");
			return;
		}

		BusiOrderItem orderItem = new BusiOrderItem();
		orderItem.setBsoimUuid(bsoimUuid);
		orderItem = busiOrderItemService.getBusiOrderItem(orderItem);
		if (null == orderItem) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end itemRecvReceipt");
			return;
		}
		
		if (orderItem.getBsoimStatus() != 5) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当前状态不能确认收货!"),response);
			logger.info("[BusiOrderController]:end itemRecvReceipt");
			return;
		}
		
		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(orderItem.getBsoimUser());
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(5);
		busiMessageCenter.setBsmecProductPic(orderItem.getBsoimSharePic());
		busiMessageCenter.setBsmecContent("亲,您购买的"+orderItem.getBsoimProductName()+"商品已确认收货!");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		
		//根据下单订单号更新确认收货时间和成交状态,更新订单明细状态
		BusiOrderItem busiOrderItem = new BusiOrderItem();
		busiOrderItem.setBsoimUuid(bsoimUuid);
		busiOrderItem.setBsoimStatus(6);
		busiOrderItem.setBsoimRecvTime(new Date());
		busiOrderItem.setBsoimUdate(new Date());
		busiOrderItemService.updateBusiOrderItem(busiOrderItem);
		
		coreUserService.updateCoreUserByOrderCount(orderItem.getBsoimUser(), 0, 0, 0, 0, -1, 1, 0, 0, 0);
		
		//修改用户分享记录，修改用户表统计,增加收益明细记录
		BusiShareReceipt shareReceipt = busiShareReceiptService.getBusiShareReceiptByOrder(bsoimUuid);
		if (null != shareReceipt) {
			if(shareReceipt.getBssrtStatus() > 1) { //已确认收货过
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "确认收货成功!"),response);
				logger.info("[BusiOrderController]:end itemRecvReceipt");
				return;
			}
			CoreUser coreUser = new CoreUser();
			coreUser.setCrusrUuid(shareReceipt.getBssrtBuyUser());
			coreUser = coreUserService.getCoreUser(coreUser);
			if (null != coreUser) {
				shareReceipt.setBssrtReceiptDate(new Date());
				shareReceipt.setBssrtStatus(2);
				busiShareReceiptService.updateBusiShareReceipt(shareReceipt);
				
				BusiShareRecord busiShareRecord = new BusiShareRecord();
				busiShareRecord.setBssrdUser(shareReceipt.getBssrtUser());
				busiShareRecord.setBssrdProduct(orderItem.getBsoimProduct());
				busiShareRecord.setBssrdBackNow(shareReceipt.getBssrtBackNow());
				busiShareRecordService.updateBusiShareRecordByProductAndUser(busiShareRecord);
				
				coreUserService.updateCoreUserByshareCount(shareReceipt.getBssrtUser(), 0, 0, shareReceipt.getBssrtBackNow(), 1);
				
				//添加收益明细
				BusiProfitRecord busiProfitRecord = new BusiProfitRecord();
				busiProfitRecord.setBsprdUuid(RandomUtil.generateString(16));
				busiProfitRecord.setBsprdUser(shareReceipt.getBssrtUser());
				busiProfitRecord.setBsprdType(2);
				busiProfitRecord.setBsprdQuota(shareReceipt.getBssrtBackNow()+"");
				busiProfitRecord.setBsprdBill("分享"+busiOrderItem.getBsoimProductName()+"商品用户确认收货返现"+shareReceipt.getBssrtBackNow()+"元");
				busiProfitRecord.setBsprdCdate(new Date());
				busiProfitRecordService.insertBusiProfitRecord(busiProfitRecord);
				
				coreUserService.updateCoreUserByIncome(shareReceipt.getBssrtUser(), 0.0, shareReceipt.getBssrtBackNow(), shareReceipt.getBssrtBackNow());
				
				//添加到用户消息中心
				busiMessageCenter = new BusiMessageCenter();
				busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
				busiMessageCenter.setBsmecUser(shareReceipt.getBssrtUser());
				busiMessageCenter.setBsmecCdate(new Date());
				busiMessageCenter.setBsmecUdate(new Date());
				busiMessageCenter.setBsmecStatus(1);
				busiMessageCenter.setBsmecType(4);
				busiMessageCenter.setBsmecProductPic(null);
				busiMessageCenter.setBsmecContent(coreUser.getCrusrName()+"购买了您分享的"+orderItem.getBsoimProductName()+"商品,您获得"+shareReceipt.getBssrtBackNow()+"元收益!");
				busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
			}
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "确认收货成功!"),response);
		logger.info("[BusiOrderController]:end itemRecvReceipt");
	}

	/**
	 * 子订单客服转为售后
	 * 
	 * @param bsoimUuid 子订单号
	 * @param bsoimSellerMemo 客服备注
	 */
	@RequestMapping(value="/item/after/sale", method=RequestMethod.POST)
	public void itemAfterSale (String bsoimUuid, String bsoimSellerMemo, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin itemAfterSale");
		if (StringUtil.isEmpty(bsoimUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderController]:end itemAfterSale");
			return;
		}		
		if (StringUtil.isEmpty(bsoimSellerMemo)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[转为售后原因]不能为空!"), response);
			logger.info("[BusiOrderController]:end itemAfterSale");
			return;
		}

		BusiOrderItem orderItem = new BusiOrderItem();
		orderItem.setBsoimUuid(bsoimUuid);
		orderItem = busiOrderItemService.getBusiOrderItem(orderItem);
		if (null == orderItem) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end itemAfterSale");
			return;
		}		
		if (orderItem.getBsoimStatus() != 2 && orderItem.getBsoimStatus() != 5) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该状态不能发起售后!"), response);
			logger.info("[BusiOrderController]:end itemAfterSale");
			return;
		}
		
		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(orderItem.getBsoimUser());
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(5);
		busiMessageCenter.setBsmecProductPic(orderItem.getBsoimSharePic());
		busiMessageCenter.setBsmecContent("亲,您购买的"+orderItem.getBsoimProductName()+"商品已申请售后处理!");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		
		//更新订单明细状态
		BusiOrderItem busiOrderItem = new BusiOrderItem();
		busiOrderItem.setBsoimUuid(bsoimUuid);
		busiOrderItem.setBsoimSellerMemo(bsoimSellerMemo);
		busiOrderItem.setBsoimRefundStatus(2);
		busiOrderItem.setBsoimStatus(9);
		busiOrderItem.setBsoimAftsaleTime(new Date());
		busiOrderItem.setBsoimUdate(new Date());
		busiOrderItemService.updateBusiOrderItem(busiOrderItem);
		
		//判断从哪个状态发起的售后
		if (orderItem.getBsoimStatus() == 2) {
			coreUserService.updateCoreUserByOrderCount(orderItem.getBsoimUser(), 0, 0, 0, -1, 0, 0, 1, 0, 0);
		}
		if (orderItem.getBsoimStatus() == 5) {
			coreUserService.updateCoreUserByOrderCount(orderItem.getBsoimUser(), 0, 0, 0, 0, -1, 0, 1, 0, 0);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "申请售后成功!"),response);
		logger.info("[BusiOrderController]:end itemAfterSale");
	}
	
	/**
	 * 子订单客服售后退款 
	 * 
	 * @param bsoimUuid 子订单号
	 * @param bsoimRefund 退款金额
	 * @param bsoimCrefundReason 客服备注退款原因（退款和不予退款时使用）
	 */
	@RequestMapping(value="/item/back/refund",method=RequestMethod.POST)
	public void itemBackRefund(String bsoimUuid, String bsoimCrefundReason, Double bsoimRefund, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin itemBackRefund");
		if (StringUtil.isEmpty(bsoimUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderController]:end itemBackRefund");
			return;
		}		
		if (StringUtil.isEmpty(bsoimCrefundReason)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[客服备注退款原因]不能为空!"), response);
			logger.info("[BusiOrderController]:end itemBackRefund");
			return;
		}	
		if (null == bsoimRefund || 0 == bsoimRefund) { 
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[退款金额]必填且不可为0!"), response);
			logger.info("[BusiOrderController]:end itemBackRefund");
			return;
		}
		  
		BusiOrderItem orderItem = new BusiOrderItem();
		orderItem.setBsoimUuid(bsoimUuid);
		orderItem = busiOrderItemService.getBusiOrderItem(orderItem);
		if (null == orderItem) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end itemBackRefund");
			return;
		}
		if(orderItem.getBsoimStatus() != 9) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该状态下不能发起退款!"), response);
			logger.info("[BusiOrderController]:end itemBackRefund");
			return;
		}
		if (bsoimRefund > orderItem.getBsoimValue()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "退款金额大于商品金额,不符合退款要求!"), response);
			logger.info("[BusiOrderController]:end itemBackRefund");
			return;
		}

		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(orderItem.getBsoimUser());
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(5);
		busiMessageCenter.setBsmecProductPic(orderItem.getBsoimSharePic());
		busiMessageCenter.setBsmecContent("亲,您购买的"+orderItem.getBsoimProductName()+"商品申请售后已处理,退款"+bsoimRefund+"元,请查看!");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
				
		//更新订单明细状态
		BusiOrderItem busiOrderItem = new BusiOrderItem();
		busiOrderItem.setBsoimUuid(bsoimUuid);
		busiOrderItem.setBsoimRefund(bsoimRefund);
		busiOrderItem.setBsoimRefundStatus(4);
		busiOrderItem.setBsoimCrefundReason(bsoimCrefundReason);
		busiOrderItem.setBsoimStatus(8);
		busiOrderItem.setBsoimFinishTime(new Date());
		busiOrderItem.setBsoimUdate(new Date());
		busiOrderItemService.updateBusiOrderItem(busiOrderItem);
		
		//修改父订单的退款金额
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrRefund(bsoimRefund);
		busiOrder.setBsorrUuid(orderItem.getBsoimOrder());
		busiOrderService.updateBusiOrderByRefund(busiOrder);
		
		//判断同一个父订单下还有没有没完成的子订单
		List<BusiOrderItem> orderItemList = busiOrderItemService.findBusiOrderItemForListsByOrdWWC(orderItem.getBsoimOrder());
		if (null == orderItemList || orderItemList.size() <= 0) { //都完成了
			//父订单状态改为完成
			busiOrder = new BusiOrder();
			busiOrder.setBsorrUuid(orderItem.getBsoimOrder());
			busiOrder.setBsorrStatus(5); //完成交易		
			busiOrder.setBsorrUdate(new Date());
			busiOrderService.updateBusiOrder(busiOrder);
		}
		
		coreUserService.updateCoreUserByOrderCount(orderItem.getBsoimUser(), 0, 0, 0, 0, 0, 0, -1, 1, 0);
		//后台售后订单处理，确认后 售后订单数量-1，已完成+1。申请售后  分享失效。
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "售后已处理,交易成功!"),response);
		logger.info("[BusiOrderController]:end itemBackRefund");		
	}
	
	/**
	 * 子订单客服售后直接改为完成,不予退款 
	 * 
	 * @param bsoimUuid 子订单号
	 * @param bsoimCrefundReason 客服备注退款原因（退款和不予退款时使用）
	 */
	@RequestMapping(value="/item/back/finish",method=RequestMethod.POST)
	public void itemBackFinish(String bsoimUuid, String bsoimCrefundReason, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin itemBackFinish");
		if (StringUtil.isEmpty(bsoimUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderController]:end itemBackFinish");
			return;
		}		
		if (StringUtil.isEmpty(bsoimCrefundReason)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[客服备注]不能为空!"), response);
			logger.info("[BusiOrderController]:end itemBackFinish");
			return;
		}
		  
		BusiOrderItem orderItem = new BusiOrderItem();
		orderItem.setBsoimUuid(bsoimUuid);
		orderItem = busiOrderItemService.getBusiOrderItem(orderItem);
		if (null == orderItem) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end itemBackFinish");
			return;
		}
		if(orderItem.getBsoimStatus() != 9) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该状态下不能操作不予退款!"), response);
			logger.info("[BusiOrderController]:end itemBackFinish");
			return;
		}

		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(orderItem.getBsoimUser());
		busiMessageCenter.setBsmecCdate(new Date());
		busiMessageCenter.setBsmecUdate(new Date());
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(5);
		busiMessageCenter.setBsmecProductPic(orderItem.getBsoimSharePic());
		busiMessageCenter.setBsmecContent("亲,您购买的"+orderItem.getBsoimProductName()+"商品申请售后已处理,协商一致不予退款");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
				
		//更新订单明细状态
		BusiOrderItem busiOrderItem = new BusiOrderItem();
		busiOrderItem.setBsoimUuid(bsoimUuid);
		busiOrderItem.setBsoimRefundStatus(3);
		busiOrderItem.setBsoimCrefundReason(bsoimCrefundReason);
		busiOrderItem.setBsoimStatus(8);
		busiOrderItem.setBsoimFinishTime(new Date());
		busiOrderItem.setBsoimUdate(new Date());
		busiOrderItemService.updateBusiOrderItem(busiOrderItem);
		
		//判断同一个父订单下还有没有没完成的子订单
		List<BusiOrderItem> orderItemList = busiOrderItemService.findBusiOrderItemForListsByOrdWWC(orderItem.getBsoimOrder());
		if (null == orderItemList || orderItemList.size() <= 0) { //都完成了
			//父订单状态改为完成
			BusiOrder busiOrder = new BusiOrder();
			busiOrder.setBsorrUuid(orderItem.getBsoimOrder());
			busiOrder.setBsorrStatus(5); //完成交易		
			busiOrder.setBsorrUdate(new Date());
			busiOrderService.updateBusiOrder(busiOrder);
		}
		
		coreUserService.updateCoreUserByOrderCount(orderItem.getBsoimUser(), 0, 0, 0, 0, 0, 0, -1, 1, 0);
		//后台售后订单处理，确认后 售后订单数量-1，已完成+1。申请售后  分享失效。
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "售后处理不予退款成功!"),response);
		logger.info("[BusiOrderController]:end itemBackFinish");		
	}
	
	/**
	 * 子订单删除订单
	 * 
	 * @param bsoimUuid 子订单号
	 */
	@RequestMapping(value="/item/delete", method=RequestMethod.POST)
	public void itemDetele (String bsoimUuid, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin itemDetele");
		if (StringUtil.isEmpty(bsoimUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderController]:end itemDetele");
			return;
		}

		BusiOrderItem orderItem = new BusiOrderItem();
		orderItem.setBsoimUuid(bsoimUuid);
		orderItem = busiOrderItemService.getBusiOrderItem(orderItem);
		if (null == orderItem) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "订单不存在!"), response);
			logger.info("[BusiOrderController]:end itemDetele");
			return;
		}
		if (8 != orderItem.getBsoimStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当前状态下不能删除订单!"), response);
			logger.info("[BusiOrderController]:end itemDetele");
			return;
		}
		
		Date date = new Date();
		
		//添加到用户消息中心
		BusiMessageCenter busiMessageCenter = new BusiMessageCenter();
		busiMessageCenter.setBsmecUuid(RandomUtil.generateString(16));
		busiMessageCenter.setBsmecUser(orderItem.getBsoimUser());
		busiMessageCenter.setBsmecCdate(date);
		busiMessageCenter.setBsmecUdate(date);
		busiMessageCenter.setBsmecStatus(1);
		busiMessageCenter.setBsmecType(5);
		busiMessageCenter.setBsmecProductPic(orderItem.getBsoimSharePic());
		busiMessageCenter.setBsmecContent("亲,您购买的"+orderItem.getBsoimProductName()+"商品订单已删除!");
		busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
		
		//更新订单明细状态
		BusiOrderItem busiOrderItem = new BusiOrderItem();
		busiOrderItem.setBsoimUuid(bsoimUuid);
		busiOrderItem.setBsoimStatus(10);
		busiOrderItem.setBsoimDelTime(date);
		busiOrderItem.setBsoimUdate(date);
		busiOrderItemService.updateBusiOrderItem(busiOrderItem);
		
		//判断同一个父订单下还有没有没删除的子订单
		List<BusiOrderItem> orderItemList = busiOrderItemService.findBusiOrderItemForListsByOrdWSC(orderItem.getBsoimOrder());
		if (null == orderItemList || orderItemList.size() <= 0) { //都删除了
			//父订单状态改为已删除
			BusiOrder busiOrder = new BusiOrder();
			busiOrder.setBsorrUuid(orderItem.getBsoimOrder());
			busiOrder.setBsorrStatus(6); //完成交易		
			busiOrder.setBsorrUdate(date);
			busiOrderService.updateBusiOrder(busiOrder);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除订单成功!"),response);
		logger.info("[BusiOrderController]:end itemDetele");
	}

	/**
	* 前台订单获取单个 
	* 
	* @param bsorrUuid 父订单号
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiOrder (String bsorrUuid, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin viewsBusiOrder");

		if (StringUtil.isEmpty(bsorrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderController]:end viewsBusiOrder");
			return;
		}

		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrUuid(bsorrUuid);

		busiOrder = busiOrderService.getBusiOrder(busiOrder);

		BusiOrderVO busiOrderVO = new BusiOrderVO();
		busiOrderVO.convertPOToVO(busiOrder);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiOrderVO), response);
		logger.info("[BusiOrderController]:end viewsBusiOrder");
	}

	/**
	* 后台订单获取明细 
	* 
	* @param bsorrUuid 父订单号
	* @return
	*/
	@RequestMapping(value="/views/back", method=RequestMethod.POST)
	public void viewsBusiOrderBack (String bsorrUuid, HttpServletResponse response) {
		logger.info("[BusiOrderController]:begin viewsBusiOrderBack");

		if (StringUtil.isEmpty(bsorrUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderController]:end viewsBusiOrderBack");
			return;
		}

		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrUuid(bsorrUuid);

		busiOrder = busiOrderService.getBusiOrder(busiOrder);

		BusiOrderVO busiOrderVO = new BusiOrderVO();
		busiOrderVO.convertPOToVO(busiOrder);
		busiOrderVO.setBusiOrderItemList(busiOrderItemService.findBusiOrderItemForListsByOrd(busiOrder.getBsorrUuid()));
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "后台订单获取明细成功", busiOrderVO), response);
		logger.info("[BusiOrderController]:end viewsBusiOrderBack");
	}
	
	/**
	* 前台明细获取单个
	*
	* @param bsoimUuid 子订单号
	* @return
	*/
	@RequestMapping(value="/item/views", method=RequestMethod.POST)
	public void viewsBusiOrderItem (String bsoimUuid, HttpServletResponse response) {
		logger.info("[BusiOrderItemController]:begin viewsBusiOrderItem");

		if (StringUtil.isEmpty(bsoimUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderItemController]:end viewsBusiOrderItem");
			return;
		}

		BusiOrderItem busiOrderItem = new BusiOrderItem();
		busiOrderItem.setBsoimUuid(bsoimUuid);

		busiOrderItem = busiOrderItemService.getBusiOrderItem(busiOrderItem);
		
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrUuid(busiOrderItem.getBsoimOrder());
		busiOrder = busiOrderService.getBusiOrder(busiOrder);
		 
		BusiOrderItemVO busiOrderItemVO = new BusiOrderItemVO();
		busiOrderItemVO.convertPOToVO(busiOrderItem);
		busiOrderItemVO.setBsorrAddress(busiOrder.getBsorrAddress());
		busiOrderItemVO.setBsorrCity(busiOrder.getBsorrCity());
		busiOrderItemVO.setBsorrCityName(busiOrder.getBsorrCityName());
		busiOrderItemVO.setBsorrCounty(busiOrder.getBsorrCounty());
		busiOrderItemVO.setBsorrCountyName(busiOrder.getBsorrCountyName());
		busiOrderItemVO.setBsorrIdCard(busiOrder.getBsorrIdCard());
		busiOrderItemVO.setBsorrMobile(busiOrder.getBsorrMobile());
		busiOrderItemVO.setBsorrName(busiOrder.getBsorrName());
		busiOrderItemVO.setBsorrProvince(busiOrder.getBsorrProvince());
		busiOrderItemVO.setBsorrProvinceName(busiOrder.getBsorrProvinceName());
		busiOrderItemVO.setBsorrTradeNo(busiOrder.getBsorrTradeNo()); 
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiOrderItemVO), response);
		logger.info("[BusiOrderItemController]:end viewsBusiOrderItem");
	}

	/**
	* 后台明细信息查看
	*
	* @param bsoimUuid 子订单号
	* @return
	*/
	@RequestMapping(value="/item/views/back", method=RequestMethod.POST)
	public void viewsBusiOrderItemBack (String bsoimUuid, HttpServletResponse response) {
		logger.info("[BusiOrderItemController]:begin viewsBusiOrderItemBack");

		if (StringUtil.isEmpty(bsoimUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiOrderItemController]:end viewsBusiOrderItemBack");
			return;
		}

		BusiOrderItem busiOrderItem = new BusiOrderItem();
		busiOrderItem.setBsoimUuid(bsoimUuid);
		busiOrderItem = busiOrderItemService.getBusiOrderItem(busiOrderItem);
		if (null == busiOrderItem) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "明细信息不存在!"), response);
			logger.info("[BusiOrderItemController]:end viewsBusiOrderItemBack");
			return;
		}
		
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setBsorrUuid(busiOrderItem.getBsoimOrder());
		busiOrder = busiOrderService.getBusiOrder(busiOrder);
		BusiOrderVO busiOrderVO = new BusiOrderVO();
		busiOrderVO.convertPOToVO(busiOrder);
		busiOrderVO.setBusiOrderItemList(busiOrderItemService.findBusiOrderItemForListsByOrd(busiOrder.getBsorrUuid()));

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "后台明细信息查看成功", busiOrderVO), response);
		logger.info("[BusiOrderItemController]:end viewsBusiOrderItemBack");
	}

}