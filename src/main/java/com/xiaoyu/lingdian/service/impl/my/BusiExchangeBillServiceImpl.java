package com.xiaoyu.lingdian.service.impl.my;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiExchangeBillService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiExchangeBill;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 用户积分兑换话费记录表
*/
@Service("busiExchangeBillService")
public class BusiExchangeBillServiceImpl implements BusiExchangeBillService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;

	@Override
	public boolean insertBusiExchangeBill(BusiExchangeBill busiExchangeBill) {
		myBatisDAO.insert(busiExchangeBill);
		return true;
	}

	@Override
	public boolean updateBusiExchangeBill(BusiExchangeBill busiExchangeBill) {
		myBatisDAO.update(busiExchangeBill);
		return true;
	}

	@Override
	public boolean deleteBusiExchangeBill(BusiExchangeBill busiExchangeBill) {
		myBatisDAO.delete(busiExchangeBill);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiExchangeBillByIds", hashMap);
		return true;
	}

	@Override
	public BusiExchangeBill getBusiExchangeBill(BusiExchangeBill busiExchangeBill) {
		return (BusiExchangeBill) myBatisDAO.findForObject(busiExchangeBill);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiExchangeBill> findBusiExchangeBillPageByMy(String bseblUser, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bseblUser", bseblUser);
		return myBatisDAO.findForPage("findBusiExchangeBillForPagesByMy", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiExchangeBill> findBusiExchangeBillPage(String userName, String bseblMobile, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiExchangeBill>(1, 10, 0);
			}
			hashMap.put("list", list);
		}
		hashMap.put("bseblMobile", bseblMobile);		
		return myBatisDAO.findForPage("findBusiExchangeBillForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}