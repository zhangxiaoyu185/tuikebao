package com.xiaoyu.lingdian.service.my;

import com.xiaoyu.lingdian.entity.BusiCashRecord;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 用户提现记录表
*/
public interface BusiCashRecordService {

	/**
	* 添加
	* @param busiCashRecord
	* @return
	*/
	public boolean insertBusiCashRecord(BusiCashRecord busiCashRecord);

	/**
	* 修改
	* @param busiCashRecord
	* @return
	*/
	public boolean updateBusiCashRecord(BusiCashRecord busiCashRecord);

	/**
	* 删除
	* @param busiCashRecord
	* @return
	*/
	public boolean deleteBusiCashRecord(BusiCashRecord busiCashRecord);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiCashRecord
	* @return
	*/
	public BusiCashRecord getBusiCashRecord(BusiCashRecord busiCashRecord);

	/**
	* 获取我的提现记录page
	* @param bscrdExtracted
	* @param bscrdStatus
	* @return Page
	*/
	public Page<BusiCashRecord> findBusiCashRecordForPagesByMy(String bscrdExtracted, int bscrdStatus, int pageNum, int pageSize);

	/**
	* 后台查询所有Page
	* @param userName
	* @param bscrdMobile
	* @return Page
	*/
	public Page<BusiCashRecord> findBusiCashRecordPage(String userName, String bscrdMobile, int pageNum, int pageSize);

}