package com.xiaoyu.lingdian.job;

import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xiaoyu.lingdian.entity.BusiOrder;
import com.xiaoyu.lingdian.entity.BusiOrderItem;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.order.BusiOrderItemService;
import com.xiaoyu.lingdian.service.order.BusiOrderService;

public class AutomaticEvaluationJob extends QuartzJobBean {
	
	private final Logger logger = LoggerFactory.getLogger("BASE_LOGGER");
	
	/**
	* 商城订单明细表
	*/
	private BusiOrderItemService busiOrderItemService;
	 
	/**
	* 用户表
	*/
	private CoreUserService coreUserService;
 
	/**
	* 商城订单表
	*/
	@Autowired
	private BusiOrderService busiOrderService;
	
	public void setBusiOrderItemService(BusiOrderItemService busiOrderItemService) {
		this.busiOrderItemService = busiOrderItemService;
	} 
	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}
	public void setBusiOrderService(BusiOrderService busiOrderService) {
		this.busiOrderService = busiOrderService;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("AutomaticReceiptJob start");
		//查询超过7天的待评价的订单
		List<BusiOrderItem> orderItemList = busiOrderItemService.findBusiOrderItemForListsByOverDayPJ();
		for (BusiOrderItem orderItem : orderItemList) {
			try	{
				//不需要评价信息
				Date date = new Date();
				//根据下单订单号更新确认评价时间和成交状态,更新订单明细状态
				BusiOrderItem busiOrderItem = new BusiOrderItem();
				busiOrderItem.setBsoimUuid(orderItem.getBsoimUuid());
				busiOrderItem.setBsoimStatus(7);
				busiOrderItem.setBsoimEvaluateTime(new Date());//评价时间
				busiOrderItem.setBsoimUdate(date);
				busiOrderItemService.updateBusiOrderItem(busiOrderItem);
				
				//修改购物车和订单数量
				coreUserService.updateCoreUserByOrderCount(orderItem.getBsoimUser(), 0, 0, 0, 0, 0, -1, 0, 1, 0);
				
				//判断同一个父订单下还有没有没完成的子订单
				List<BusiOrderItem> busiOrderItemList = busiOrderItemService.findBusiOrderItemForListsByOrdWWC(orderItem.getBsoimOrder());
				if (null == busiOrderItemList || busiOrderItemList.size() <= 0) { //都完成了
					//父订单状态改为完成
					BusiOrder busiOrder = new BusiOrder();
					busiOrder.setBsorrUuid(orderItem.getBsoimOrder());
					busiOrder.setBsorrStatus(5); //完成交易		
					busiOrder.setBsorrUdate(date);
					busiOrderService.updateBusiOrder(busiOrder);
				}
			}catch (Exception e) {
				logger.info("子订单"+orderItem.getBsoimUuid()+"超过7天自动评价失败");
			}
		}	
		logger.info("AutomaticEvaluationJob end");
	}

}
