package com.xiaoyu.lingdian.service.freight;

import com.xiaoyu.lingdian.entity.BusiFreightTemplate;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 运费模板表
*/
public interface BusiFreightTemplateService {

	/**
	* 添加
	* @param busiFreightTemplate
	* @return
	*/
	public boolean insertBusiFreightTemplate(BusiFreightTemplate busiFreightTemplate);

	/**
	* 修改
	* @param busiFreightTemplate
	* @return
	*/
	public boolean updateBusiFreightTemplate(BusiFreightTemplate busiFreightTemplate);

	/**
	* 删除
	* @param busiFreightTemplate
	* @return
	*/
	public boolean deleteBusiFreightTemplate(BusiFreightTemplate busiFreightTemplate);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiFreightTemplate
	* @return
	*/
	public BusiFreightTemplate getBusiFreightTemplate(BusiFreightTemplate busiFreightTemplate);

	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiFreightTemplate> findBusiFreightTemplateList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiFreightTemplate> findBusiFreightTemplatePage(int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}