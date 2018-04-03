package com.xiaoyu.lingdian.service.order;

import com.xiaoyu.lingdian.entity.BusiOrder;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 商城订单表
*/
public interface BusiOrderService {

	/**
	* 添加
	* @param busiOrder
	* @return
	*/
	public boolean insertBusiOrder(BusiOrder busiOrder);

	/**
	* 修改
	* @param busiOrder
	* @return
	*/
	public boolean updateBusiOrder(BusiOrder busiOrder);

	/**
	* 修改订单的退款金额
	* @param busiOrder
	* @return
	*/
	public boolean updateBusiOrderByRefund(BusiOrder busiOrder);
	
	/**
	* 客服调整订单价格
	* @param busiOrder
	* @return
	*/
	public boolean updateBusiOrderByAdjust(BusiOrder busiOrder);
	
	/**
	* 查询
	* @param busiOrder
	* @return
	*/
	public BusiOrder getBusiOrder(BusiOrder busiOrder);

	/**
	* 查询所有Page
	* @param bsorrUuid
	* @param bsorrUser
	* @return Page
	*/
	public Page<BusiOrder> findBusiOrderPageByMyPendingOrOrder(String bsorrUuid, String bsorrUser, int pageNum, int pageSize);

}