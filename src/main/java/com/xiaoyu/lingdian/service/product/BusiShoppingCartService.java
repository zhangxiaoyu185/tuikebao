package com.xiaoyu.lingdian.service.product;

import com.xiaoyu.lingdian.entity.BusiShoppingCart;
import java.util.List;

/**
* 购物车表
*/
public interface BusiShoppingCartService {

	/**
	* 添加
	* @param busiShoppingCart
	* @return
	*/
	public boolean insertBusiShoppingCart(BusiShoppingCart busiShoppingCart);

	/**
	* 修改
	* @param busiShoppingCart
	* @return
	*/
	public boolean updateBusiShoppingCart(BusiShoppingCart busiShoppingCart);

	/**
	* 删除
	* @param busiShoppingCart
	* @return
	*/
	public boolean deleteBusiShoppingCart(BusiShoppingCart busiShoppingCart);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiShoppingCart
	* @return
	*/
	public BusiShoppingCart getBusiShoppingCart(BusiShoppingCart busiShoppingCart);

	/**
	* 我的购物车List
	* @param bssctUser
	* @return List
	*/
	public List<BusiShoppingCart> findBusiShoppingCartList(String bssctUser);

}