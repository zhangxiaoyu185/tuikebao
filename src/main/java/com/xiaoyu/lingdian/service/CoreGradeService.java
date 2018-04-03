package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreGrade;

import java.util.List;
import java.util.Map;

import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 等级配置表
*/
public interface CoreGradeService {

	/**
	* 添加
	* @param coreGrade
	* @return
	*/
	public boolean insertCoreGrade(CoreGrade coreGrade);

	/**
	* 修改
	* @param coreGrade
	* @return
	*/
	public boolean updateCoreGrade(CoreGrade coreGrade);

	/**
	* 删除
	* @param coreGrade
	* @return
	*/
	public boolean deleteCoreGrade(CoreGrade coreGrade);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreGrade
	* @return
	*/
	public CoreGrade getCoreGrade(CoreGrade coreGrade);

	/**
	* 根据当前等级查询下一个等级
	* @param crgdeOrd
	* @return
	*/
	public CoreGrade getCoreGradeAfterGrade(int crgdeOrd);

	/**
	* 根据等级名查询
	* @param coreGrade
	* @return
	*/
	public CoreGrade getCoreGradeByName(CoreGrade coreGrade);
	
	/**
	 * 根据等级UUID集合查询等级map
	 * @param list
	 * @return
	 */
	public Map<String, CoreGrade> findCoreGradeMapByUuidList(List<String> list);
	
	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreGrade> findCoreGradeList();

	/**
	* 查询所有Page
	* @param crgdeName
	* @return Page
	*/
	public Page<CoreGrade> findCoreGradePage(String crgdeName, int pageNum, int pageSize);

}