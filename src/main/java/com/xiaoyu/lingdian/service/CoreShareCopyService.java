package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreShareCopy;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 系统分享文案设置表
*/
public interface CoreShareCopyService {

	/**
	* 添加
	* @param coreShareCopy
	* @return
	*/
	public boolean insertCoreShareCopy(CoreShareCopy coreShareCopy);

	/**
	* 修改
	* @param coreShareCopy
	* @return
	*/
	public boolean updateCoreShareCopy(CoreShareCopy coreShareCopy);

	/**
	* 删除
	* @param coreShareCopy
	* @return
	*/
	public boolean deleteCoreShareCopy(CoreShareCopy coreShareCopy);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreShareCopy
	* @return
	*/
	public CoreShareCopy getCoreShareCopy(CoreShareCopy coreShareCopy);

	/**
	* 查询最近的一条文案
	* @return
	*/
	public CoreShareCopy getCoreShareCopyTop();

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreShareCopy> findCoreShareCopyList();

	/**
	* 查询所有Page
	* @param crscyCopy
	* @return Page
	*/
	public Page<CoreShareCopy> findCoreShareCopyPage(String crscyCopy, int pageNum, int pageSize);

}