package com.xiaoyu.lingdian.service.my;

import com.xiaoyu.lingdian.entity.BusiPromoterInfo;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 用户提现信息表
*/
public interface BusiPromoterInfoService {

	/**
	* 添加
	* @param busiPromoterInfo
	* @return
	*/
	public boolean insertBusiPromoterInfo(BusiPromoterInfo busiPromoterInfo);

	/**
	* 修改
	* @param busiPromoterInfo
	* @return
	*/
	public boolean updateBusiPromoterInfo(BusiPromoterInfo busiPromoterInfo);

	/**
	* 删除
	* @param busiPromoterInfo
	* @return
	*/
	public boolean deleteBusiPromoterInfo(BusiPromoterInfo busiPromoterInfo);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param busiPromoterInfo
	* @return
	*/
	public BusiPromoterInfo getBusiPromoterInfo(BusiPromoterInfo busiPromoterInfo);

	/**
	 * 根据用户查询提现信息
	 * @param bspioUser
	 * @return
	 */
	public BusiPromoterInfo getBusiPromoterInfoByUser(String bspioUser);

	/**
	* 查询所有Page
	* @param userName
	* @return Page
	*/
	public Page<BusiPromoterInfo> findBusiPromoterInfoPage(String userName, int pageNum, int pageSize);

}