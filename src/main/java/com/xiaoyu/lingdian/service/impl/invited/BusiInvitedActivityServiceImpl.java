package com.xiaoyu.lingdian.service.impl.invited;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.invited.BusiInvitedActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiInvitedActivity;

/**
* 邀请活动表
*/
@Service("busiInvitedActivityService")
public class BusiInvitedActivityServiceImpl implements BusiInvitedActivityService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiInvitedActivity(BusiInvitedActivity busiInvitedActivity) {
		myBatisDAO.insert(busiInvitedActivity);
		return true;
	}

	@Override
	public boolean updateBusiInvitedActivity(BusiInvitedActivity busiInvitedActivity) {
		myBatisDAO.update(busiInvitedActivity);
		return true;
	}

	@Override
	public boolean deleteBusiInvitedActivity(BusiInvitedActivity busiInvitedActivity) {
		myBatisDAO.delete(busiInvitedActivity);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiInvitedActivityByIds",hashMap);
		return true;
	}

	@Override
	public BusiInvitedActivity getBusiInvitedActivity(BusiInvitedActivity busiInvitedActivity) {
		return (BusiInvitedActivity) myBatisDAO.findForObject(busiInvitedActivity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiInvitedActivity> findBusiInvitedActivityList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiInvitedActivityForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiInvitedActivity> findBusiInvitedActivityPage(String bsiayPeriods, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsiayPeriods", bsiayPeriods);
		return myBatisDAO.findForPage("findBusiInvitedActivityForPages", new PageRequest(pageNum, pageSize, hashMap));
	}
	 
	@Override
	public BusiInvitedActivity getBusiInvitedActivityLatest() {
		return (BusiInvitedActivity) myBatisDAO.findForObject("getBusiInvitedActivityLatest"); 
	}

}