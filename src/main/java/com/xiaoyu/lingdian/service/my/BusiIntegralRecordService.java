package com.xiaoyu.lingdian.service.my;

import com.xiaoyu.lingdian.entity.BusiIntegralRecord;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 用户积分明细表
*/
public interface BusiIntegralRecordService {

	/**
	* 添加
	* @param busiIntegralRecord
	* @return
	*/
	public boolean insertBusiIntegralRecord(BusiIntegralRecord busiIntegralRecord);

	/**
	* 修改
	* @param busiIntegralRecord
	* @return
	*/
	public boolean updateBusiIntegralRecord(BusiIntegralRecord busiIntegralRecord);

	/**
	* 删除
	* @param busiIntegralRecord
	* @return
	*/
	public boolean deleteBusiIntegralRecord(BusiIntegralRecord busiIntegralRecord);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiIntegralRecord
	* @return
	*/
	public BusiIntegralRecord getBusiIntegralRecord(BusiIntegralRecord busiIntegralRecord);

	/**
	* 我的积分明细列表page
	* @param bsirdUser
	* @return Page
	*/
	public Page<BusiIntegralRecord> findBusiIntegralRecordPageByMy(String bsirdUser, int pageNum, int pageSize);

	/**
	* 后台查询所有Page
	* @param userName
	* @return Page
	*/
	public Page<BusiIntegralRecord> findBusiIntegralRecordPage(String userName, int pageNum, int pageSize);

}