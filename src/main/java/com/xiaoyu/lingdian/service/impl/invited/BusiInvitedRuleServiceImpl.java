package com.xiaoyu.lingdian.service.impl.invited;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.invited.BusiInvitedRuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiInvitedRule;

/**
* 邀请奖励规则表
*/
@Service("busiInvitedRuleService")
public class BusiInvitedRuleServiceImpl implements BusiInvitedRuleService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiInvitedRule(BusiInvitedRule busiInvitedRule) {
		myBatisDAO.insert(busiInvitedRule);
		return true;
	}

	@Override
	public boolean updateBusiInvitedRule(BusiInvitedRule busiInvitedRule) {
		myBatisDAO.update(busiInvitedRule);
		return true;
	}

	@Override
	public boolean deleteBusiInvitedRule(BusiInvitedRule busiInvitedRule) {
		myBatisDAO.delete(busiInvitedRule);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiInvitedRuleByIds", hashMap);
		return true;
	}

	@Override
	public BusiInvitedRule getBusiInvitedRule(BusiInvitedRule busiInvitedRule) {
		return (BusiInvitedRule) myBatisDAO.findForObject(busiInvitedRule);
	}

	@Override
	public BusiInvitedRule findBusiInvitedRuleViewWithCnd(int count) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("count", count);
		return (BusiInvitedRule) myBatisDAO.findForObject("findBusiInvitedRuleViewWithCnd", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiInvitedRule> findBusiInvitedRuleList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiInvitedRuleForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiInvitedRule> findBusiInvitedRulePage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findBusiInvitedRuleForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}