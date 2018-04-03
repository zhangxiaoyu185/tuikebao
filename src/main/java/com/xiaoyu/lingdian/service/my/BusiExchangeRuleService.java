package com.xiaoyu.lingdian.service.my;

import com.xiaoyu.lingdian.entity.BusiExchangeRule;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 积分兑换话费设置表
*/
public interface BusiExchangeRuleService {

	/**
	* 添加
	* @param busiExchangeRule
	* @return
	*/
	public boolean insertBusiExchangeRule(BusiExchangeRule busiExchangeRule);

	/**
	* 修改
	* @param busiExchangeRule
	* @return
	*/
	public boolean updateBusiExchangeRule(BusiExchangeRule busiExchangeRule);

	/**
	* 删除
	* @param busiExchangeRule
	* @return
	*/
	public boolean deleteBusiExchangeRule(BusiExchangeRule busiExchangeRule);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiExchangeRule
	* @return
	*/
	public BusiExchangeRule getBusiExchangeRule(BusiExchangeRule busiExchangeRule);

	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiExchangeRule> findBusiExchangeRuleList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiExchangeRule> findBusiExchangeRulePage(int pageNum, int pageSize);

}