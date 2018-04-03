package com.xiaoyu.lingdian.service.invited;

import java.util.Date;

import com.xiaoyu.lingdian.entity.BusiInvitedRelation;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 邀请关系表
*/
public interface BusiInvitedRelationService {

	/**
	* 添加
	* @param busiInvitedRelation
	* @return
	*/
	public boolean insertBusiInvitedRelation(BusiInvitedRelation busiInvitedRelation);

	/**
	* 查询
	* @param busiInvitedRelation
	* @return
	*/
	public BusiInvitedRelation getBusiInvitedRelation(BusiInvitedRelation busiInvitedRelation);

	/**
	* 获取我的今日邀请好友page
	* @param bsirnInvited
	* @return Page
	*/
	public Page<BusiInvitedRelation> findBusiInvitedRelationForPagesByMyNow(String bsirnInvited, int pageNum, int pageSize);
	
	/**
	* 获取我的活动邀请好友page
	* @param bsirnInvited
	* @param firstDate
	* @return Page
	*/
	public Page<BusiInvitedRelation> findBusiInvitedRelationForPagesByActive(String bsirnInvited, Date firstDate, int pageNum, int pageSize);

	/**
	* 获取我的累计邀请好友page
	* @param bsirnInvited
	* @return Page
	*/
	public Page<BusiInvitedRelation> findBusiInvitedRelationForPagesByMyTotal(String bsirnInvited, int pageNum, int pageSize);

	/**
	* 后台查询所有Page
	* @param userName
	* @return Page
	*/
	public Page<BusiInvitedRelation> findBusiInvitedRelationForPages(String userName, int pageNum, int pageSize);

}