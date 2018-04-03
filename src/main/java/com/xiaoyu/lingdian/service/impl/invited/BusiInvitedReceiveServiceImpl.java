package com.xiaoyu.lingdian.service.impl.invited;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.invited.BusiInvitedReceiveService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiInvitedReceive;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 邀请活动奖励领取表
*/
@Service("busiInvitedReceiveService")
public class BusiInvitedReceiveServiceImpl implements BusiInvitedReceiveService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;
	
	@Override
	public boolean insertBusiInvitedReceive(BusiInvitedReceive busiInvitedReceive) {
		myBatisDAO.insert(busiInvitedReceive);
		return true;
	}

	@Override
	public boolean updateBusiInvitedReceive(BusiInvitedReceive busiInvitedReceive) {
		myBatisDAO.update(busiInvitedReceive);
		return true;
	}

	@Override
	public boolean deleteBusiInvitedReceive(BusiInvitedReceive busiInvitedReceive) {
		myBatisDAO.delete(busiInvitedReceive);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiInvitedReceiveByIds",hashMap);
		return true;
	}

	@Override
	public BusiInvitedReceive getBusiInvitedReceive(BusiInvitedReceive busiInvitedReceive) {
		return (BusiInvitedReceive) myBatisDAO.findForObject(busiInvitedReceive);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiInvitedReceive> findBusiInvitedReceiveList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiInvitedReceiveForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiInvitedReceive> findBusiInvitedReceivePage(String userName, String bsirePeriods, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiInvitedReceive>(1, 10, 0);
			}
			hashMap.put("list", list);
		}
		hashMap.put("bsirePeriods", bsirePeriods);
		return myBatisDAO.findForPage("findBusiInvitedReceiveForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}