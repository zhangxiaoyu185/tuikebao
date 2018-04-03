package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreJob;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 职业表
*/
public interface CoreJobService {

	/**
	* 添加
	* @param coreJob
	* @return
	*/
	public boolean insertCoreJob(CoreJob coreJob);

	/**
	* 修改
	* @param coreJob
	* @return
	*/
	public boolean updateCoreJob(CoreJob coreJob);

	/**
	* 删除
	* @param coreJob
	* @return
	*/
	public boolean deleteCoreJob(CoreJob coreJob);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreJob
	* @return
	*/
	public CoreJob getCoreJob(CoreJob coreJob);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreJob> findCoreJobList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreJob> findCoreJobPage(String crjobName, int pageNum, int pageSize);

}