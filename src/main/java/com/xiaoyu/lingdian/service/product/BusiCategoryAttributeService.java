package com.xiaoyu.lingdian.service.product;

import com.xiaoyu.lingdian.entity.BusiCategoryAttribute;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 产品类型属性表
*/
public interface BusiCategoryAttributeService {

	/**
	* 添加
	* @param busiCategoryAttribute
	* @return
	*/
	public boolean insertBusiCategoryAttribute(BusiCategoryAttribute busiCategoryAttribute);

	/**
	* 删除
	* @param busiCategoryAttribute
	* @return
	*/
	public boolean deleteBusiCategoryAttribute(BusiCategoryAttribute busiCategoryAttribute);

	/**
	* 查询
	* @param busiCategoryAttribute
	* @return
	*/
	public BusiCategoryAttribute getBusiCategoryAttribute(BusiCategoryAttribute busiCategoryAttribute);

	/**
	* 查询最大排序
	* @return
	*/
	public int getBusiCategoryAttributeMaxOrd();
	
	/**
	* 根据分类标识获取属性列表List
	* @param bscaeCategory
	* @return List
	*/
	public List<BusiCategoryAttribute> findBusiCategoryAttributeList(String bscaeCategory);

	/**
	* 后台根据分类标识获取属性列表Page
	* @param categoryName
	* @param bscaeName
	* @return Page
	*/
	public Page<BusiCategoryAttribute> findBusiCategoryAttributePage(String categoryName, String bscaeName, int pageNum, int pageSize);

}