package com.xiaoyu.lingdian.service.index;

import com.xiaoyu.lingdian.entity.BusiHotList;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 热榜表
*/
public interface BusiHotListService {

	/**
	* 添加
	* @param busiHotList
	* @return
	*/
	public boolean insertBusiHotList(BusiHotList busiHotList);

	/**
	* 修改
	* @param busiHotList
	* @return
	*/
	public boolean updateBusiHotList(BusiHotList busiHotList);

	/**
	* 删除
	* @param busiHotList
	* @return
	*/
	public boolean deleteBusiHotList(BusiHotList busiHotList);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiHotList
	* @return
	*/
	public BusiHotList getBusiHotList(BusiHotList busiHotList);

	/**
	* 前台获取启动的所有热榜page
	* @return Page
	*/
	public Page<BusiHotList> findBusiHotListPageBefore(int pageNum, int pageSize);

	/**
	* 后台根据商品名称查询所有热榜page
	* @param productName
	* @return Page
	*/
	public Page<BusiHotList> findBusiHotListPage(String productName, int pageNum, int pageSize);

}