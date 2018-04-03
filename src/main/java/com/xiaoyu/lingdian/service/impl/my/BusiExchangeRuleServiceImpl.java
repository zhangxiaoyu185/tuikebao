package com.xiaoyu.lingdian.service.impl.my;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.my.BusiExchangeRuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiExchangeRule;

/**
* 积分兑换话费设置表
*/
@Service("busiExchangeRuleService")
public class BusiExchangeRuleServiceImpl implements BusiExchangeRuleService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiExchangeRule(BusiExchangeRule busiExchangeRule) {
		myBatisDAO.insert(busiExchangeRule);
		return true;
	}

	@Override
	public boolean updateBusiExchangeRule(BusiExchangeRule busiExchangeRule) {
		myBatisDAO.update(busiExchangeRule);
		return true;
	}

	@Override
	public boolean deleteBusiExchangeRule(BusiExchangeRule busiExchangeRule) {
		myBatisDAO.delete(busiExchangeRule);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiExchangeRuleByIds",hashMap);
		return true;
	}

	@Override
	public BusiExchangeRule getBusiExchangeRule(BusiExchangeRule busiExchangeRule) {
		return (BusiExchangeRule) myBatisDAO.findForObject(busiExchangeRule);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiExchangeRule> findBusiExchangeRuleList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiExchangeRuleForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiExchangeRule> findBusiExchangeRulePage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findBusiExchangeRuleForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}