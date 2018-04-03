package com.xiaoyu.lingdian.service.invited;

import com.xiaoyu.lingdian.entity.BusiInvitedRule;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 邀请奖励规则表
*/
public interface BusiInvitedRuleService {

	/**
	* 添加
	* @param busiInvitedRule
	* @return
	*/
	public boolean insertBusiInvitedRule(BusiInvitedRule busiInvitedRule);

	/**
	* 修改
	* @param busiInvitedRule
	* @return
	*/
	public boolean updateBusiInvitedRule(BusiInvitedRule busiInvitedRule);

	/**
	* 删除
	* @param busiInvitedRule
	* @return
	*/
	public boolean deleteBusiInvitedRule(BusiInvitedRule busiInvitedRule);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiInvitedRule
	* @return
	*/
	public BusiInvitedRule getBusiInvitedRule(BusiInvitedRule busiInvitedRule);

	/**
	* 根据用户活动邀请人数查询符合最近的领取规则
	* @return BusiInvitedRule
	*/
	public BusiInvitedRule findBusiInvitedRuleViewWithCnd(int count);

	/**
	* 获取所有邀请好友规则list
	* @return List
	*/
	public List<BusiInvitedRule> findBusiInvitedRuleList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiInvitedRule> findBusiInvitedRulePage(int pageNum, int pageSize);

}