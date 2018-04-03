package com.xiaoyu.lingdian.service.my;

import com.xiaoyu.lingdian.entity.BusiShareRecord;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 用户分享记录表
*/
public interface BusiShareRecordService {

	/**
	* 添加
	* @param busiShareRecord
	* @return
	*/
	public boolean insertBusiShareRecord(BusiShareRecord busiShareRecord);

	/**
	* 修改
	* @param busiShareRecord
	* @return
	*/
	public boolean updateBusiShareRecord(BusiShareRecord busiShareRecord);

	/**
	* 根据用户名和商品修改
	* @param busiShareRecord
	* @return
	*/
	public boolean updateBusiShareRecordByProductAndUser(BusiShareRecord busiShareRecord);
	
	/**
	* 删除
	* @param busiShareRecord
	* @return
	*/
	public boolean deleteBusiShareRecord(BusiShareRecord busiShareRecord);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiShareRecord
	* @return
	*/
	public BusiShareRecord getBusiShareRecord(BusiShareRecord busiShareRecord);

	/**
	* 根据用户和产品查询分享记录
	* @param bssrdUser
	* @param bssrdProduct
	* @return
	*/
	public BusiShareRecord getBusiShareRecordByProductAndUser(String bssrdUser, String bssrdProduct);
	
	/**
	* 我的分享记录Page
	* @param bssrdUser
	* @return Page
	*/
	public Page<BusiShareRecord> findBusiShareRecordForPagesByMy(String bssrdUser, int pageNum, int pageSize);

	/**
	* 后台查询所有Page
	* @param userName
	* @param bssrdProductName
	* @return Page
	*/
	public Page<BusiShareRecord> findBusiShareRecordPage(String userName, String bssrdProductName, int pageNum, int pageSize);

}