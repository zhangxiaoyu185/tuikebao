package com.xiaoyu.lingdian.service.server;

import com.xiaoyu.lingdian.entity.BusiServerDetail;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 服务详情页面表
*/
public interface BusiServerDetailService {

	/**
	* 添加
	* @param busiServerDetail
	* @return
	*/
	public boolean insertBusiServerDetail(BusiServerDetail busiServerDetail);

	/**
	* 修改
	* @param busiServerDetail
	* @return
	*/
	public boolean updateBusiServerDetail(BusiServerDetail busiServerDetail);

	/**
	* 删除
	* @param busiServerDetail
	* @return
	*/
	public boolean deleteBusiServerDetail(BusiServerDetail busiServerDetail);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiServerDetail
	* @return
	*/
	public BusiServerDetail getBusiServerDetail(BusiServerDetail busiServerDetail);

	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiServerDetail> findBusiServerDetailList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiServerDetail> findBusiServerDetailPage(int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}