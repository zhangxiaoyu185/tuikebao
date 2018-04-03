package com.xiaoyu.lingdian.service.impl.index;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.index.BusiHotListService;
import com.xiaoyu.lingdian.service.product.BusiProductService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiHotList;
import com.xiaoyu.lingdian.entity.BusiProduct;

/**
* 热榜表
*/
@Service("busiHotListService")
public class BusiHotListServiceImpl implements BusiHotListService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	/**
	* 商品表
	*/
	@Autowired
	private BusiProductService busiProductService;

	@Override
	public boolean insertBusiHotList(BusiHotList busiHotList) {
		myBatisDAO.insert(busiHotList);
		return true;
	}

	@Override
	public boolean updateBusiHotList(BusiHotList busiHotList) {
		myBatisDAO.update(busiHotList);
		return true;
	}

	@Override
	public boolean deleteBusiHotList(BusiHotList busiHotList) {
		myBatisDAO.delete(busiHotList);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiHotListByIds",hashMap);
		return true;
	}

	@Override
	public BusiHotList getBusiHotList(BusiHotList busiHotList) {
		return (BusiHotList) myBatisDAO.findForObject(busiHotList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiHotList> findBusiHotListPageBefore(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findBusiHotListPageBefore", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiHotList> findBusiHotListPage(String productName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();	
		if (!StringUtil.isEmpty(productName)) {
			List<BusiProduct> productList = busiProductService.findBusiProductForPagesByProductName(productName);
			List<String> list = new ArrayList<>();
			for (BusiProduct busiProduct : productList) {
				list.add(busiProduct.getBsprtUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiHotList>(1, 10, 0);
			}
			hashMap.put("list", list);
		}

		return myBatisDAO.findForPage("findBusiHotListForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}