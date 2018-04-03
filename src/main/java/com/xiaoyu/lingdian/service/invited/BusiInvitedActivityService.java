package com.xiaoyu.lingdian.service.invited;

import com.xiaoyu.lingdian.entity.BusiInvitedActivity;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 邀请活动表
*/
public interface BusiInvitedActivityService {

	/**
	* 添加
	* @param busiInvitedActivity
	* @return
	*/
	public boolean insertBusiInvitedActivity(BusiInvitedActivity busiInvitedActivity);

	/**
	* 修改
	* @param busiInvitedActivity
	* @return
	*/
	public boolean updateBusiInvitedActivity(BusiInvitedActivity busiInvitedActivity);

	/**
	* 删除
	* @param busiInvitedActivity
	* @return
	*/
	public boolean deleteBusiInvitedActivity(BusiInvitedActivity busiInvitedActivity);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiInvitedActivity
	* @return
	*/
	public BusiInvitedActivity getBusiInvitedActivity(BusiInvitedActivity busiInvitedActivity);
	
	/**
	* 查询表中最新一期的期数
	* @param busiInvitedActivity
	* @return
	*/
	public BusiInvitedActivity getBusiInvitedActivityLatest();

	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiInvitedActivity> findBusiInvitedActivityList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<BusiInvitedActivity> findBusiInvitedActivityPage(String bsiayPeriods, int pageNum, int pageSize);

}