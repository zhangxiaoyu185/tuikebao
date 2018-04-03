package com.xiaoyu.lingdian.service.impl.order;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.order.BusiOrderPayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiOrderPay;

/**
* 订单支付记录表
*/
@Service("busiOrderPayService")
public class BusiOrderPayServiceImpl implements BusiOrderPayService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiOrderPay(BusiOrderPay busiOrderPay) {
		myBatisDAO.insert(busiOrderPay);
		return true;
	}

	@Override
	public boolean updateBusiOrderPay(BusiOrderPay busiOrderPay) {
		myBatisDAO.update(busiOrderPay);
		return true;
	}

	@Override
	public boolean deleteBusiOrderPay(BusiOrderPay busiOrderPay) {
		myBatisDAO.delete(busiOrderPay);
		return true;
	}

	private static final String DELETEBATCH_BUSIORDERPAY_BY_IDS="deleteBatchBusiOrderPayByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_BUSIORDERPAY_BY_IDS,hashMap);
		return true;
	}

	@Override
	public BusiOrderPay getBusiOrderPay(BusiOrderPay busiOrderPay) {
		return (BusiOrderPay) myBatisDAO.findForObject(busiOrderPay);
	}

	private static final String FIND_BUSIORDERPAY_FOR_LISTS="findBusiOrderPayForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiOrderPay> findBusiOrderPayList() {
		return myBatisDAO.findForList(FIND_BUSIORDERPAY_FOR_LISTS, null);
	}

	private static final String FIND_BUSIORDERPAY_FOR_PAGES="findBusiOrderPayForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiOrderPay> findBusiOrderPayPage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage(FIND_BUSIORDERPAY_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}