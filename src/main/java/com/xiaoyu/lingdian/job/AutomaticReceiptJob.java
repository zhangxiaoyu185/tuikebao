package com.xiaoyu.lingdian.job;

import java.util.Date;
import java.util.List;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xiaoyu.lingdian.entity.BusiMessageCenter;
import com.xiaoyu.lingdian.entity.BusiOrderItem;
import com.xiaoyu.lingdian.entity.BusiProfitRecord;
import com.xiaoyu.lingdian.entity.BusiShareReceipt;
import com.xiaoyu.lingdian.entity.BusiShareRecord;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiMessageCenterService;
import com.xiaoyu.lingdian.service.my.BusiProfitRecordService;
import com.xiaoyu.lingdian.service.my.BusiShareReceiptService;
import com.xiaoyu.lingdian.service.my.BusiShareRecordService;
import com.xiaoyu.lingdian.service.order.BusiOrderItemService;
import com.xiaoyu.lingdian.tool.RandomUtil;

/**
 * 自动确认收货
 *
 */
public class AutomaticReceiptJob extends QuartzJobBean {

	private final Logger logger = LoggerFactory.getLogger("BASE_LOGGER");

	/**
	* 商城订单明细表
	*/
	private BusiOrderItemService busiOrderItemService;

	/**
	* 用户消息中心表
	*/
	private BusiMessageCenterService busiMessageCenterService;
	
	/**
	* 用户表
	*/
	private CoreUserService coreUserService;

	/**
	* 分享商品购买收货记录表
	*/
	private BusiShareReceiptService busiShareReceiptService;
	
	/**
	* 用户分享记录表
	*/
	private BusiShareRecordService busiShareRecordService;
	
	/**
	* 用户收益明细表
	*/
	private BusiProfitRecordService busiProfitRecordService;

	public void setBusiOrderItemService(BusiOrderItemService busiOrderItemService) {
		this.busiOrderItemService = busiOrderItemService;
	}

	public void setBusiMessageCenterService(
			BusiMessageCenterService busiMessageCenterService) {
		this.busiMessageCenterService = busiMessageCenterService;
	}

	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}

	public void setBusiShareReceiptService(
			BusiShareReceiptService busiShareReceiptService) {
		this.busiShareReceiptService = busiShareReceiptService;
	}

	public void setBusiShareRecordService(
			BusiShareRecordService busiShareRecordService) {
		this.busiShareRecordService = busiShareRecordService;
	}

	public void setBusiProfitRecordService(
			BusiProfitRecordService busiProfitRecordService) {
		this.busiProfitRecordService = busiProfitRecordService;
	}

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("AutomaticReceiptJob start");
		//查询超过10天的待收货订单
		List<BusiOrderItem> orderItemList = busiOrderItemService.findBusiOrderItemForListsByOverDayDSH();
		for (BusiOrderItem orderItem : orderItemList) {
			try	{
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
				busiOrderItem.setBsoimUuid(orderItem.getBsoimUuid());
				busiOrderItem.setBsoimStatus(6);
				busiOrderItem.setBsoimRecvTime(new Date());
				busiOrderItem.setBsoimUdate(new Date());
				busiOrderItemService.updateBusiOrderItem(busiOrderItem);
				
				coreUserService.updateCoreUserByOrderCount(orderItem.getBsoimUser(), 0, 0, 0, 0, -1, 1, 0, 0, 0);
				
				//修改用户分享记录，修改用户表统计,增加收益明细记录
				BusiShareReceipt shareReceipt = busiShareReceiptService.getBusiShareReceiptByOrder(orderItem.getBsoimUuid());
				if (null != shareReceipt) {
					if(shareReceipt.getBssrtStatus() > 1) { //已确认收货过
						continue;
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
						busiShareRecordService.updateBusiShareRecord(busiShareRecord);
						
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
						busiMessageCenter.setBsmecContent(coreUser.getCrusrName()+"购买了您分享的"+orderItem.getBsoimProductName()+"商品,您获得"+shareReceipt.getBssrtBackNow()+"收益!");
						busiMessageCenterService.insertBusiMessageCenter(busiMessageCenter);
					}
				}
			}catch (Exception e) {
				logger.info("子订单"+orderItem.getBsoimUuid()+"超过10天自动确认收货失败");
			}
		}	
		logger.info("AutomaticReceiptJob end");
	}
	
}