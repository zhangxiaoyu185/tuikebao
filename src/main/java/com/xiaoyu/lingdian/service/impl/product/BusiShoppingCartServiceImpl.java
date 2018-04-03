package com.xiaoyu.lingdian.service.impl.product;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.service.product.BusiShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiShoppingCart;

/**
* 购物车表
*/
@Service("busiShoppingCartService")
public class BusiShoppingCartServiceImpl implements BusiShoppingCartService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiShoppingCart(BusiShoppingCart busiShoppingCart) {
		myBatisDAO.insert(busiShoppingCart);
		return true;
	}

	@Override
	public boolean updateBusiShoppingCart(BusiShoppingCart busiShoppingCart) {
		myBatisDAO.update(busiShoppingCart);
		return true;
	}

	@Override
	public boolean deleteBusiShoppingCart(BusiShoppingCart busiShoppingCart) {
		myBatisDAO.delete(busiShoppingCart);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiShoppingCartByIds",hashMap);
		return true;
	}

	@Override
	public BusiShoppingCart getBusiShoppingCart(BusiShoppingCart busiShoppingCart) {
		return (BusiShoppingCart) myBatisDAO.findForObject(busiShoppingCart);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiShoppingCart> findBusiShoppingCartList(String bssctUser) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssctUser", bssctUser);
		return myBatisDAO.findForList("findBusiShoppingCartForLists", hashMap);
	}

}