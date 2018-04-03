package com.xiaoyu.lingdian.service.impl.order;

import java.util.Map;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.order.BusiOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiOrder;

/**
* 商城订单表
*/
@Service("busiOrderService")
public class BusiOrderServiceImpl implements BusiOrderService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiOrder(BusiOrder busiOrder) {
		myBatisDAO.insert(busiOrder);
		return true;
	}

	@Override
	public boolean updateBusiOrder(BusiOrder busiOrder) {
		myBatisDAO.update(busiOrder);
		return true;
	}

	@Override
	public boolean updateBusiOrderByRefund(BusiOrder busiOrder) {
		myBatisDAO.update("updateBusiOrderByRefund", busiOrder);
		return true;
	}
	
	@Override
	public boolean updateBusiOrderByAdjust(BusiOrder busiOrder) {
		myBatisDAO.update("updateBusiOrderByAdjust", busiOrder);
		return true;
	}

	@Override
	public BusiOrder getBusiOrder(BusiOrder busiOrder) {
		return (BusiOrder) myBatisDAO.findForObject(busiOrder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiOrder> findBusiOrderPageByMyPendingOrOrder(String bsorrUuid, String bsorrUser, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsorrUuid", bsorrUuid);
		hashMap.put("bsorrUser", bsorrUser);
		return myBatisDAO.findForPage("findBusiOrderPageByMyPendingOrOrder", new PageRequest(pageNum, pageSize, hashMap));
	}

}