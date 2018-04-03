package com.xiaoyu.lingdian.service.impl.server;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.server.BusiServerDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiServerDetail;

/**
* 服务详情页面表
*/
@Service("busiServerDetailService")
public class BusiServerDetailServiceImpl implements BusiServerDetailService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiServerDetail(BusiServerDetail busiServerDetail) {
		myBatisDAO.insert(busiServerDetail);
		return true;
	}

	@Override
	public boolean updateBusiServerDetail(BusiServerDetail busiServerDetail) {
		myBatisDAO.update(busiServerDetail);
		return true;
	}

	@Override
	public boolean deleteBusiServerDetail(BusiServerDetail busiServerDetail) {
		myBatisDAO.delete(busiServerDetail);
		return true;
	}

	private static final String DELETEBATCH_BUSISERVERDETAIL_BY_IDS="deleteBatchBusiServerDetailByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_BUSISERVERDETAIL_BY_IDS,hashMap);
		return true;
	}

	@Override
	public BusiServerDetail getBusiServerDetail(BusiServerDetail busiServerDetail) {
		return (BusiServerDetail) myBatisDAO.findForObject(busiServerDetail);
	}

	private static final String FIND_BUSISERVERDETAIL_FOR_LISTS="findBusiServerDetailForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiServerDetail> findBusiServerDetailList() {
		return myBatisDAO.findForList(FIND_BUSISERVERDETAIL_FOR_LISTS, null);
	}

	private static final String FIND_BUSISERVERDETAIL_FOR_PAGES="findBusiServerDetailForPages";//全局的命名空间

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiServerDetail> findBusiServerDetailPage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage(FIND_BUSISERVERDETAIL_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}