package com.xiaoyu.lingdian.service.order;

import com.xiaoyu.lingdian.entity.BusiOrderItem;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 商城订单明细表
*/
public interface BusiOrderItemService {

	/**
	* 添加
	* @param busiOrderItem
	* @return
	*/
	public boolean insertBusiOrderItem(BusiOrderItem busiOrderItem);

	/**
	* 修改
	* @param busiOrderItem
	* @return
	*/
	public boolean updateBusiOrderItem(BusiOrderItem busiOrderItem);

	/**
	* 根据订单编号修改订单明细
	* @param busiOrderItem
	* @return
	*/
	public boolean updateBusiOrderItemByOrder(BusiOrderItem busiOrderItem);

	/**
	* 查询
	* @param busiOrderItem
	* @return
	*/
	public BusiOrderItem getBusiOrderItem(BusiOrderItem busiOrderItem);

	/**
	* 获取超过10天的待收货订单list
	* @return List
	*/
	public List<BusiOrderItem> findBusiOrderItemForListsByOverDayDSH();
	
	/**
	* 获取超过7天的待评价订单list
	* @return List
	*/
	public List<BusiOrderItem> findBusiOrderItemForListsByOverDayPJ();

	/**
	* 获取父订单下面的所有子订单list
	* @param bsoimOrder
	* @return List
	*/
	public List<BusiOrderItem> findBusiOrderItemForListsByOrd(String bsoimOrder);
	
	/**
	* 获取父订单下面的所有还未完成的子订单list
	* @param bsoimOrder
	* @return List
	*/
	public List<BusiOrderItem> findBusiOrderItemForListsByOrdWWC(String bsoimOrder);
	
	/**
	* 获取父订单下面的所有还未删除的子订单list
	* @param bsoimOrder
	* @return List
	*/
	public List<BusiOrderItem> findBusiOrderItemForListsByOrdWSC(String bsoimOrder);
	
	/**
	* 查询所有Page
	* @param bsoimUuid
	* @param bsoimUser
	* @param bsoimStatus
	* @return Page
	*/
	public Page<BusiOrderItem> findBusiOrderItemForPagesByMyStatusOrItem(String bsoimUuid, String bsoimUser, Integer bsoimStatus, int pageNum, int pageSize);

}