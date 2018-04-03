package com.xiaoyu.lingdian.service.my;

import com.xiaoyu.lingdian.entity.BusiExchangeBill;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 用户积分兑换话费记录表
*/
public interface BusiExchangeBillService {

	/**
	* 添加
	* @param busiExchangeBill
	* @return
	*/
	public boolean insertBusiExchangeBill(BusiExchangeBill busiExchangeBill);

	/**
	* 修改
	* @param busiExchangeBill
	* @return
	*/
	public boolean updateBusiExchangeBill(BusiExchangeBill busiExchangeBill);

	/**
	* 删除
	* @param busiExchangeBill
	* @return
	*/
	public boolean deleteBusiExchangeBill(BusiExchangeBill busiExchangeBill);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiExchangeBill
	* @return
	*/
	public BusiExchangeBill getBusiExchangeBill(BusiExchangeBill busiExchangeBill);

	/**
	* 我的积分兑换列表page
	* @param bseblUser
	* @return Page
	*/
	public Page<BusiExchangeBill> findBusiExchangeBillPageByMy(String bseblUser, int pageNum, int pageSize);
	
	/**
	* 后台查询所有Page
	* @param userName
	* @param bseblMobile
	* @return Page
	*/
	public Page<BusiExchangeBill> findBusiExchangeBillPage(String userName, String bseblMobile, int pageNum, int pageSize);

}