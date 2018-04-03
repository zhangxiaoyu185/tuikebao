package com.xiaoyu.lingdian.service.product;

import com.xiaoyu.lingdian.entity.BusiCategoryValue;
import java.util.List;

/**
* 产品类型属性值表
*/
public interface BusiCategoryValueService {

	/**
	* 添加
	* @param busiCategoryValue
	* @return
	*/
	public boolean insertBusiCategoryValue(BusiCategoryValue busiCategoryValue);

	/**
	* 删除
	* @param busiCategoryValue
	* @return
	*/
	public boolean deleteBusiCategoryValue(BusiCategoryValue busiCategoryValue);

	/**
	* 根据属性删除对应属性值
	* @param bscveAttr
	* @return
	*/
	public boolean deleteBusiCategoryValueByAttr(String bscveAttr);

	/**
	* 查询
	* @param busiCategoryValue
	* @return
	*/
	public BusiCategoryValue getBusiCategoryValue(BusiCategoryValue busiCategoryValue);

	/**
	* 查询最大排序
	* @return
	*/
	public int getBusiCategoryValueMaxOrd();
	
	/**
	* 根据属性获取对应属性值list
	* @param bscveAttr
	* @return List
	*/
	public List<BusiCategoryValue> findBusiCategoryValueList(String bscveAttr);

}