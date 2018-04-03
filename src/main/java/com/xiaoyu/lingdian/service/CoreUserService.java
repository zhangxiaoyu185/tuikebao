package com.xiaoyu.lingdian.service;

import java.util.List;
import java.util.Map;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 用户表
*/
public interface CoreUserService {

	/**
	* 添加
	* @param coreUser
	* @return
	*/
	public boolean insertCoreUser(CoreUser coreUser);

	/**
	* 修改
	* @param coreUser
	* @return
	*/
	public boolean updateCoreUser(CoreUser coreUser);

	/**
	* 修改冻结收益、可用收益、获得总收益
	* @param crusrUuid
	* @param crusrFrozenIncome
	* @param crusrAvailableIncome
	* @param crusrTotalIncome
	* @return
	*/
	public boolean updateCoreUserByIncome(String crusrUuid, double crusrFrozenIncome, double crusrAvailableIncome, double crusrTotalIncome);
	
	/**
	* 修改总邀请人数和活动邀请人数
	* @param crusrUuid
	* @param crusrInviteTotal
	* @param crusrInviteNused
	* @return
	*/
	public boolean updateCoreUserByInvitedCount(String crusrUuid, int crusrInviteTotal, int crusrInviteNused);
	
	/**
	* 清空活动邀请人数
	* @param crusrInviteNused
	* @return
	*/
	public boolean updateToEmptyCoreUserByInvitedCount(int crusrInviteNused);
	
	/**
	* 修改当前积分，获得总积分
	* @param crusrUuid
	* @param crusrIntegral
	* @param crusrTotalIntegral
	* @return
	*/
	public boolean updateCoreUserByIntegral(String crusrUuid, int crusrIntegral, int crusrTotalIntegral);
	
	/**
	* 修改购物车和订单数量
	* @param crusrUuid
	* @param crusrShoppingCart
	* @param crusrOrder
	* @param crusrPendingPay
	* @param crusrPendingShip
	* @param crusrPendingRecv
	* @param crusrPendingEval
	* @param crusrRefund
	* @param crusrFinished
	* @param crusrCancelled
	* @return
	*/
	public boolean updateCoreUserByOrderCount(String crusrUuid, int crusrShoppingCart, int crusrOrder, 
			int crusrPendingPay, int crusrPendingShip, int crusrPendingRecv, int crusrPendingEval, 
			int crusrRefund, int crusrFinished, int crusrCancelled);
	
	/**
	* 修改分享记录
	* @param crusrUuid
	* @param shareCount
	* @param integralCount
	* @param addincome
	* @param finishCount
	* @return
	*/
	public boolean updateCoreUserByshareCount(String crusrUuid, int shareCount, int integralCount, double addincome, int finishCount);
	
	/**
	* 修改提现记录
	* @param crusrUuid
	* @param cashcount
	* @param cashfee
	* @return
	*/
	public boolean updateCoreUserByCashCount(String crusrUuid, int cashcount, double cashfee);
	
	/**
	* 修改兑换记录
	* @param crusrUuid
	* @param exchcount
	* @param exchintegral
	* @return
	*/
	public boolean updateCoreUserByExchCount(String crusrUuid, int exchcount, int exchintegral);
	
	/**
	* 根据手机号修改
	* @param coreUser
	* @return
	*/
	public boolean updateCoreUserByMobile(CoreUser coreUser);
	
	/**
	* 批量软删除
	* @param list
	* @return boolean
	*/
	public boolean updateBatchCoreUserByIds(List<String> list);

	/**
	* 根据UUID查询
	* @param coreUser
	* @return
	*/
	public CoreUser getCoreUser(CoreUser coreUser);
	
	/**
	* 根据邀请码查询
	* @param crusrInviteCode
	* @return
	*/
	public CoreUser getCoreUserByInviteCode(String crusrInviteCode);
	
	/**
	* 根据手机号查询
	* @param crusrMobile
	* @return
	*/
	public CoreUser getCoreUserByMobile(String crusrMobile);
	
	/**
	* 根据账户名称查询
	* @param crusrName
	* @return
	*/
	public CoreUser getCoreUserByName(String crusrName);

	/**
	* 查询用户Mapper
	* @param list
	* @return List
	*/
	public Map<String, CoreUser> findCoreUserMapByUuidList(List<String> list);

	/**
	* 查询所有List
	* @param crusrName
	* @param list
	* @return List
	*/
	public List<CoreUser> findCoreUserList(String crusrName, List<String> list);

	/**
	* 查询所有Page
	* @param crusrName
	* @param crusrMobile
	* @param crusrInviteCode
	* @param pageNum
	* @param pageSize
	* @return Page
	*/
	public Page<CoreUser> findCoreUserPage(String crusrName, String crusrMobile, String crusrInviteCode, int pageNum, int pageSize);
	
	/**
	 * 查询活动邀请人数最多的三位
	 */
	public List<CoreUser> findCoreUserListRanking();
	
	/**
	 * 把用户表中今日分享信息赋值给昨日分享信息
	 */
	public boolean updateCoreUserShareRecord();
	  
	/**
	 * 根据确认收货时间统计当月的完成笔数和返现收益(状态大于1客户下单)
	 */
	public boolean updateCoreUserByCruseMonthAddincome();
	
	/**
	 * 根据分享时间统计当月的分享数和积分收益
	 */
	public boolean updateCoreUserByCruseMonthShareIntegral();
 
   /**
     * 把用户表中今日提现信息赋值给昨日提现信息
     */
    public boolean updateCoreUserCashRecord();
    
    /**
     * 根据更新时间和状态为成功统计当月的提现次数和提现金额
     */
    public boolean updateCoreUserByCruseMonthCashfee();
    
    /**
     * 把用户表中今日积分兑换信息赋值给昨日兑换信息
     */
    public boolean updateCoreUserExchangeRecord();
    
    /**
     * 根据更新时间和状态为已充值统计当月的兑换次数和兑换金额
     */
    public boolean updateCoreUserByCruseMonthExchintegral();
    
    

}