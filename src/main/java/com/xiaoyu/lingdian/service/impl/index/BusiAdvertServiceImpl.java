package com.xiaoyu.lingdian.service.impl.index;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.index.BusiAdvertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiAdvert;

/**
* 广告位表
*/
@Service("busiAdvertService")
public class BusiAdvertServiceImpl implements BusiAdvertService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiAdvert(BusiAdvert busiAdvert) {
		myBatisDAO.insert(busiAdvert);
		return true;
	}

	@Override
	public boolean updateBusiAdvert(BusiAdvert busiAdvert) {
		myBatisDAO.update(busiAdvert);
		return true;
	}

	@Override
	public boolean deleteBusiAdvert(BusiAdvert busiAdvert) {
		myBatisDAO.delete(busiAdvert);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiAdvertByIds",hashMap);
		return true;
	}

	@Override
	public BusiAdvert getBusiAdvert(BusiAdvert busiAdvert) {
		return (BusiAdvert) myBatisDAO.findForObject(busiAdvert);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiAdvert> findBusiAdvertList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findBusiAdvertForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiAdvert> findBusiAdvertPage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findBusiAdvertForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}