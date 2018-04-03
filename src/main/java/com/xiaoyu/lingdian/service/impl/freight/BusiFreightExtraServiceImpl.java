package com.xiaoyu.lingdian.service.impl.freight;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.freight.BusiFreightExtraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiFreightExtra;

/**
* 特殊地区运费模板表
*/
@Service("busiFreightExtraService")
public class BusiFreightExtraServiceImpl implements BusiFreightExtraService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiFreightExtra(BusiFreightExtra busiFreightExtra) {
		myBatisDAO.insert(busiFreightExtra);
		return true;
	}

	@Override
	public boolean updateBusiFreightExtra(BusiFreightExtra busiFreightExtra) {
		myBatisDAO.update(busiFreightExtra);
		return true;
	}

	@Override
	public boolean deleteBusiFreightExtra(BusiFreightExtra busiFreightExtra) {
		myBatisDAO.delete(busiFreightExtra);
		return true;
	}

	private static final String DELETEBATCH_BUSIFREIGHTEXTRA_BY_IDS="deleteBatchBusiFreightExtraByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_BUSIFREIGHTEXTRA_BY_IDS,hashMap);
		return true;
	}

	@Override
	public BusiFreightExtra getBusiFreightExtra(BusiFreightExtra busiFreightExtra) {
		return (BusiFreightExtra) myBatisDAO.findForObject(busiFreightExtra);
	}

	private static final String FIND_BUSIFREIGHTEXTRA_FOR_LISTS="findBusiFreightExtraForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiFreightExtra> findBusiFreightExtraList() {
		return myBatisDAO.findForList(FIND_BUSIFREIGHTEXTRA_FOR_LISTS, null);
	}

	private static final String FIND_BUSIFREIGHTEXTRA_FOR_PAGES="findBusiFreightExtraForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiFreightExtra> findBusiFreightExtraPage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage(FIND_BUSIFREIGHTEXTRA_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}