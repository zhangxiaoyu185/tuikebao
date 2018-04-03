package com.xiaoyu.lingdian.service.freight;

import com.xiaoyu.lingdian.entity.BusiFreightExtra;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 特殊地区运费模板表
*/
public interface BusiFreightExtraService {

	/**
	* 添加
	* @param busiFreightExtra
	* @return
	*/
	public boolean insertBusiFreightExtra(BusiFreightExtra busiFreightExtra);

	/**
	* 修改
	* @param busiFreightExtra
	* @return
	*/
	public boolean updateBusiFreightExtra(BusiFreightExtra busiFreightExtra);

	/**
	* 删除
	* @param busiFreightExtra
	* @return
	*/
	public boolean deleteBusiFreightExtra(BusiFreightExtra busiFreightExtra);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiFreightExtra
	* @return
	*/
	public BusiFreightExtra getBusiFreightExtra(BusiFreightExtra busiFreightExtra);

	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiFreightExtra> findBusiFreightExtraList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiFreightExtra> findBusiFreightExtraPage(int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}