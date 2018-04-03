package com.xiaoyu.lingdian.service.my;

import com.xiaoyu.lingdian.entity.BusiProfitRecord;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 用户收益明细表
*/
public interface BusiProfitRecordService {

	/**
	* 添加
	* @param busiProfitRecord
	* @return
	*/
	public boolean insertBusiProfitRecord(BusiProfitRecord busiProfitRecord);

	/**
	* 修改
	* @param busiProfitRecord
	* @return
	*/
	public boolean updateBusiProfitRecord(BusiProfitRecord busiProfitRecord);

	/**
	* 删除
	* @param busiProfitRecord
	* @return
	*/
	public boolean deleteBusiProfitRecord(BusiProfitRecord busiProfitRecord);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiProfitRecord
	* @return
	*/
	public BusiProfitRecord getBusiProfitRecord(BusiProfitRecord busiProfitRecord);

	/**
	* 我的收益记录page
	* @param bsprdUser
	* @return Page
	*/
	public Page<BusiProfitRecord> findBusiProfitRecordForPagesByMy(String bsprdUser, int pageNum, int pageSize);
	
	/**
	* 后台查询所有Page
	* @param userName
	* @return Page
	*/
	public Page<BusiProfitRecord> findBusiProfitRecordPage(String userName, int pageNum, int pageSize);

}