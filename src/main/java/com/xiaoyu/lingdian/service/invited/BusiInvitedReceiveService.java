package com.xiaoyu.lingdian.service.invited;

import com.xiaoyu.lingdian.entity.BusiInvitedReceive;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 邀请活动奖励领取表
*/
public interface BusiInvitedReceiveService {

	/**
	* 添加
	* @param busiInvitedReceive
	* @return
	*/
	public boolean insertBusiInvitedReceive(BusiInvitedReceive busiInvitedReceive);

	/**
	* 修改
	* @param busiInvitedReceive
	* @return
	*/
	public boolean updateBusiInvitedReceive(BusiInvitedReceive busiInvitedReceive);

	/**
	* 删除
	* @param busiInvitedReceive
	* @return
	*/
	public boolean deleteBusiInvitedReceive(BusiInvitedReceive busiInvitedReceive);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiInvitedReceive
	* @return
	*/
	public BusiInvitedReceive getBusiInvitedReceive(BusiInvitedReceive busiInvitedReceive);

	/**
	* 查询所有List
	* @return List
	*/
	public List<BusiInvitedReceive> findBusiInvitedReceiveList();

	/**
	* 查询所有Page
	* @param userName
	* @param bsirePeriods
	* @return Page
	*/
	public Page<BusiInvitedReceive> findBusiInvitedReceivePage(String userName, String bsirePeriods, int pageNum, int pageSize);

}