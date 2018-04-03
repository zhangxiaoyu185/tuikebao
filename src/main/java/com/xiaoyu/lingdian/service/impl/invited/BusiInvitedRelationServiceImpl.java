package com.xiaoyu.lingdian.service.impl.invited;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.invited.BusiInvitedRelationService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiInvitedRelation;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 邀请关系表
*/
@Service("busiInvitedRelationService")
public class BusiInvitedRelationServiceImpl implements BusiInvitedRelationService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;

	@Override
	public boolean insertBusiInvitedRelation(BusiInvitedRelation busiInvitedRelation) {
		myBatisDAO.insert(busiInvitedRelation);
		return true;
	}

	@Override
	public BusiInvitedRelation getBusiInvitedRelation(BusiInvitedRelation busiInvitedRelation) {
		return (BusiInvitedRelation) myBatisDAO.findForObject(busiInvitedRelation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiInvitedRelation> findBusiInvitedRelationForPagesByMyNow(String bsirnInvited, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsirnInvited", bsirnInvited);
		return myBatisDAO.findForPage("findBusiInvitedRelationForPagesByMyNow", new PageRequest(pageNum, pageSize, hashMap));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiInvitedRelation> findBusiInvitedRelationForPagesByActive(String bsirnInvited, Date firstDate, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsirnInvited", bsirnInvited);
		hashMap.put("firstDate", firstDate);
		return myBatisDAO.findForPage("findBusiInvitedRelationForPagesByActive", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiInvitedRelation> findBusiInvitedRelationForPagesByMyTotal(String bsirnInvited, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsirnInvited", bsirnInvited);
		return myBatisDAO.findForPage("findBusiInvitedRelationForPagesByMyTotal", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiInvitedRelation> findBusiInvitedRelationForPages(String userName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiInvitedRelation>(1, 10, 0);
			}
			hashMap.put("list", list);
		}		
		return myBatisDAO.findForPage("findBusiInvitedRelationForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}