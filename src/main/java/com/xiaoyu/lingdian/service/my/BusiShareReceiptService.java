package com.xiaoyu.lingdian.service.my;

import com.xiaoyu.lingdian.entity.BusiShareReceipt;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 分享商品购买收货记录表
*/
public interface BusiShareReceiptService {

	/**
	* 添加
	* @param busiShareReceipt
	* @return
	*/
	public boolean insertBusiShareReceipt(BusiShareReceipt busiShareReceipt);

	/**
	* 修改
	* @param busiShareReceipt
	* @return
	*/
	public boolean updateBusiShareReceipt(BusiShareReceipt busiShareReceipt);

	/**
	* 查询
	* @param busiShareReceipt
	* @return
	*/
	public BusiShareReceipt getBusiShareReceipt(BusiShareReceipt busiShareReceipt);

	/**
	* 根据订单号查询分享购买记录
	* @param bssrtOrderNo
	* @return
	*/
	public BusiShareReceipt getBusiShareReceiptByOrder(String bssrtOrderNo);
	
	/**
	* 后台查询所有Page
	* @param userName
	* @param bssrtProductName
	* @return Page
	*/
	public Page<BusiShareReceipt> findBusiShareReceiptPage(String userName, String bssrtProductName, int pageNum, int pageSize);

}