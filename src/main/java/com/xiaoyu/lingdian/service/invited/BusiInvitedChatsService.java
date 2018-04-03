package com.xiaoyu.lingdian.service.invited;

import com.xiaoyu.lingdian.entity.BusiInvitedChats;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 邀请排行榜表
*/
public interface BusiInvitedChatsService {

	/**
	* 添加
	* @param busiInvitedChats
	* @return
	*/
	public boolean insertBusiInvitedChats(BusiInvitedChats busiInvitedChats);

	/**
	* 修改
	* @param busiInvitedChats
	* @return
	*/
	public boolean updateBusiInvitedChats(BusiInvitedChats busiInvitedChats);

	/**
	* 删除
	* @param busiInvitedChats
	* @return
	*/
	public boolean deleteBusiInvitedChats(BusiInvitedChats busiInvitedChats);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiInvitedChats
	* @return
	*/
	public BusiInvitedChats getBusiInvitedChats(BusiInvitedChats busiInvitedChats);

	/**
	* 根据期号查询排行榜
	* @param bsicsPeriods
	* @return
	*/
	public BusiInvitedChats getBusiInvitedChatsByPeriods(String bsicsPeriods);

	/**
	* 获取最近三次启用的排行榜期数数据
	* @return List
	*/
	public List<BusiInvitedChats> findBusiInvitedChatsList();

	/**
	* 后台查询所有Page
	* @return Page
	*/
	public Page<BusiInvitedChats> findBusiInvitedChatsPage(int pageNum, int pageSize);

}