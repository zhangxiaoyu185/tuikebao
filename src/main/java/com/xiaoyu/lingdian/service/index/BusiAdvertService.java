package com.xiaoyu.lingdian.service.index;

import com.xiaoyu.lingdian.entity.BusiAdvert;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 广告位表
*/
public interface BusiAdvertService {

	/**
	* 添加
	* @param busiAdvert
	* @return
	*/
	public boolean insertBusiAdvert(BusiAdvert busiAdvert);

	/**
	* 修改
	* @param busiAdvert
	* @return
	*/
	public boolean updateBusiAdvert(BusiAdvert busiAdvert);

	/**
	* 删除
	* @param busiAdvert
	* @return
	*/
	public boolean deleteBusiAdvert(BusiAdvert busiAdvert);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiAdvert
	* @return
	*/
	public BusiAdvert getBusiAdvert(BusiAdvert busiAdvert);

	/**
	* 查询所有启用的广告图List
	* @return List
	*/
	public List<BusiAdvert> findBusiAdvertList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiAdvert> findBusiAdvertPage(int pageNum, int pageSize);

}