package com.xiaoyu.lingdian.service.impl.order;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.order.BusiOrderItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiOrderItem;

/**
* 商城订单明细表
*/
@Service("busiOrderItemService")
public class BusiOrderItemServiceImpl implements BusiOrderItemService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiOrderItem(BusiOrderItem busiOrderItem) {
		myBatisDAO.insert(busiOrderItem);
		return true;
	}

	@Override
	public boolean updateBusiOrderItem(BusiOrderItem busiOrderItem) {
		myBatisDAO.update(busiOrderItem);
		return true;
	}

	@Override
	public boolean updateBusiOrderItemByOrder(BusiOrderItem busiOrderItem) {
		myBatisDAO.update("updateBusiOrderItemByOrder", busiOrderItem);
		return true;
	}

	@Override
	public BusiOrderItem getBusiOrderItem(BusiOrderItem busiOrderItem) {
		return (BusiOrderItem) myBatisDAO.findForObject(busiOrderItem);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiOrderItem> findBusiOrderItemForListsByOverDayDSH() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiOrderItemForListsByOverDayDSH", hashMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiOrderItem> findBusiOrderItemForListsByOverDayPJ() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiOrderItemForListsByOverDayPJ", hashMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiOrderItem> findBusiOrderItemForListsByOrd(String bsoimOrder) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsoimOrder", bsoimOrder);
		return myBatisDAO.findForList("findBusiOrderItemForListsByOrd", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiOrderItem> findBusiOrderItemForListsByOrdWWC(String bsoimOrder) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsoimOrder", bsoimOrder);
		return myBatisDAO.findForList("findBusiOrderItemForListsByOrdWWC", hashMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiOrderItem> findBusiOrderItemForListsByOrdWSC(String bsoimOrder) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsoimOrder", bsoimOrder);
		return myBatisDAO.findForList("findBusiOrderItemForListsByOrdWSC", hashMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiOrderItem> findBusiOrderItemForPagesByMyStatusOrItem(String bsoimUuid, String bsoimUser, Integer bsoimStatus, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsoimUuid", bsoimUuid);
		hashMap.put("bsoimUser", bsoimUser);
		hashMap.put("bsoimStatus", bsoimStatus);
		return myBatisDAO.findForPage("findBusiOrderItemForPagesByMyStatusOrItem", new PageRequest(pageNum, pageSize, hashMap));
	}

}