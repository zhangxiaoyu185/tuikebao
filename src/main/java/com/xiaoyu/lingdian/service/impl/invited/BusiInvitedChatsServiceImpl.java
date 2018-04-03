package com.xiaoyu.lingdian.service.impl.invited;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.invited.BusiInvitedChatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiInvitedChats;

/**
* 邀请排行榜表
*/
@Service("busiInvitedChatsService")
public class BusiInvitedChatsServiceImpl implements BusiInvitedChatsService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiInvitedChats(BusiInvitedChats busiInvitedChats) {
		myBatisDAO.insert(busiInvitedChats);
		return true;
	}

	@Override
	public boolean updateBusiInvitedChats(BusiInvitedChats busiInvitedChats) {
		myBatisDAO.update(busiInvitedChats);
		return true;
	}

	@Override
	public boolean deleteBusiInvitedChats(BusiInvitedChats busiInvitedChats) {
		myBatisDAO.delete(busiInvitedChats);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiInvitedChatsByIds",hashMap);
		return true;
	}

	@Override
	public BusiInvitedChats getBusiInvitedChats(BusiInvitedChats busiInvitedChats) {
		return (BusiInvitedChats) myBatisDAO.findForObject(busiInvitedChats);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiInvitedChats> findBusiInvitedChatsList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiInvitedChatsForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiInvitedChats> findBusiInvitedChatsPage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findBusiInvitedChatsForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@Override
	public BusiInvitedChats getBusiInvitedChatsByPeriods(String bsicsPeriods) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsicsPeriods", bsicsPeriods);
		return (BusiInvitedChats) myBatisDAO.findForObject("getBusiInvitedChatsByPeriods", hashMap);
	}

}