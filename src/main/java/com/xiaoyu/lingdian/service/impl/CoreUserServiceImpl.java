package com.xiaoyu.lingdian.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.core.mybatis.mapper.SimpleMapMapper;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;

/**
* 用户表
*/
@Service("coreUserService")
public class CoreUserServiceImpl implements CoreUserService {

	private static final SimpleMapMapper<String, CoreUser> USER_UUID_MAPPER = new SimpleMapMapper<String, CoreUser>() {
		@Override
		public String mapKey(CoreUser object, int rowNum) {
			return object.getCrusrUuid();
		}
	};

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreUser(CoreUser coreUser) {
		myBatisDAO.insert(coreUser);
		return true;
	}

	@Override
	public boolean updateCoreUser(CoreUser coreUser) {
		myBatisDAO.update(coreUser);
		return true;
	}

	@Override
	public boolean updateCoreUserByIncome(String crusrUuid, double crusrFrozenIncome, double crusrAvailableIncome, double crusrTotalIncome) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrUuid", crusrUuid);
		hashMap.put("crusrFrozenIncome", crusrFrozenIncome);
		hashMap.put("crusrAvailableIncome", crusrAvailableIncome);
		hashMap.put("crusrTotalIncome", crusrTotalIncome);
		myBatisDAO.update("updateCoreUserByIncome", hashMap);
		return true;
	}

	@Override
	public boolean updateCoreUserByIntegral(String crusrUuid, int crusrIntegral, int crusrTotalIntegral) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrUuid", crusrUuid);
		hashMap.put("crusrIntegral", crusrIntegral);
		hashMap.put("crusrTotalIntegral", crusrTotalIntegral);
		myBatisDAO.update("updateCoreUserByIntegral", hashMap);
		return true;
	}

	@Override
	public boolean updateCoreUserByInvitedCount(String crusrUuid, int crusrInviteTotal, int crusrInviteNused) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrUuid", crusrUuid);
		hashMap.put("crusrInviteTotal", crusrInviteTotal);
		hashMap.put("crusrInviteNused", crusrInviteNused);
		myBatisDAO.update("updateCoreUserByInvitedCount", hashMap);
		return true;
	}
	
	@Override
	public boolean updateToEmptyCoreUserByInvitedCount(int crusrInviteNused) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrInviteNused", crusrInviteNused);
		myBatisDAO.update("updateToEmptyCoreUserByInvitedCount",hashMap);
		return true;
	}

	@Override
	public boolean updateBatchCoreUserByIds(List<String> list) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.update("updateBatchCoreUserByIds",hashMap);
		return true;
	}

	@Override
	public boolean updateCoreUserByMobile(CoreUser coreUser) {
		myBatisDAO.update("updateCoreUserByMobile", coreUser);
		return true;
	}

	@Override
	public CoreUser getCoreUser(CoreUser coreUser) {
		return (CoreUser) myBatisDAO.findForObject(coreUser);
	}

	@Override
	public CoreUser getCoreUserByInviteCode(String crusrInviteCode) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrInviteCode", crusrInviteCode);
		return (CoreUser) myBatisDAO.findForObject("getCoreUserByInviteCode", hashMap);
	}
	
	@Override
	public CoreUser getCoreUserByMobile(String crusrMobile) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrMobile", crusrMobile);
		return (CoreUser) myBatisDAO.findForObject("getCoreUserByMobile", hashMap);
	}
	
	@Override
	public CoreUser getCoreUserByName(String crusrName) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrName", crusrName);
		return (CoreUser) myBatisDAO.findForObject("getCoreUserByName", hashMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, CoreUser> findCoreUserMapByUuidList(List<String> list) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		return myBatisDAO.findForMap("findCoreUserForLists", hashMap, USER_UUID_MAPPER);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CoreUser> findCoreUserList(String crusrName, List<String> list) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrName", crusrName);
		hashMap.put("list", list);
		return myBatisDAO.findForList("findCoreUserForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreUser> findCoreUserPage(String crusrName, String crusrMobile, String crusrInviteCode, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrName", crusrName);
		hashMap.put("crusrMobile", crusrMobile);
		hashMap.put("crusrInviteCode", crusrInviteCode);
		return myBatisDAO.findForPage("findCoreUserForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@Override
	public boolean updateCoreUserByOrderCount(String crusrUuid, int crusrShoppingCart, int crusrOrder, 
			int crusrPendingPay, int crusrPendingShip, int crusrPendingRecv, int crusrPendingEval, 
			int crusrRefund, int crusrFinished, int crusrCancelled) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrUuid", crusrUuid);
		hashMap.put("crusrShoppingCart", crusrShoppingCart);
		hashMap.put("crusrOrder", crusrOrder);
		hashMap.put("crusrPendingPay", crusrPendingPay);
		hashMap.put("crusrPendingShip", crusrPendingShip);
		hashMap.put("crusrPendingRecv", crusrPendingRecv);
		hashMap.put("crusrPendingEval", crusrPendingEval);
		hashMap.put("crusrRefund", crusrRefund);
		hashMap.put("crusrFinished", crusrFinished);
		hashMap.put("crusrCancelled", crusrCancelled);
		myBatisDAO.update("updateCoreUserByOrderCount", hashMap);
		return true;
	}

	@Override
	public boolean updateCoreUserByshareCount(String crusrUuid, int shareCount, int integralCount, double addincome, int finishCount) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrUuid", crusrUuid);
		hashMap.put("shareCount", shareCount);
		hashMap.put("integralCount", integralCount);
		hashMap.put("addincome", addincome);
		hashMap.put("finishCount", finishCount);
		myBatisDAO.update("updateCoreUserByshareCount", hashMap);
		return true;
	}

	@Override
	public boolean updateCoreUserByCashCount(String crusrUuid, int cashcount, double cashfee) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrUuid", crusrUuid);
		hashMap.put("cashcount", cashcount);
		hashMap.put("cashfee", cashfee);
		myBatisDAO.update("updateCoreUserByCashCount", hashMap);
		return true;
	}

	@Override
	public boolean updateCoreUserByExchCount(String crusrUuid, int exchcount, int exchintegral) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crusrUuid", crusrUuid);
		hashMap.put("exchcount", exchcount);
		hashMap.put("exchintegral", exchintegral);
		myBatisDAO.update("updateCoreUserByExchCount", hashMap);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreUser> findCoreUserListRanking() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findCoreUserListRanking", hashMap); 
	}

    @Override
    public boolean updateCoreUserShareRecord() {
        myBatisDAO.update("updateCoreUserShareRecord", null);
        return true;
    }

    @Override
    public boolean updateCoreUserByCruseMonthAddincome() {
        myBatisDAO.update("updateCoreUserByCruseMonthAddincome", null);
        return true;
    }

    @Override
    public boolean updateCoreUserByCruseMonthShareIntegral() {
        myBatisDAO.update("updateCoreUserByCruseMonthShareIntegral", null);
        return true;
    }

    @Override
    public boolean updateCoreUserCashRecord() {
        myBatisDAO.update("updateCoreUserCashRecord", null);
        return true;
    }

    @Override
    public boolean updateCoreUserByCruseMonthCashfee() {
        myBatisDAO.update("updateCoreUserByCruseMonthCashfee", null);
        return true;
    }

    @Override
    public boolean updateCoreUserExchangeRecord() {
        myBatisDAO.update("updateCoreUserExchangeRecord", null);
        return true;
    }

    @Override
    public boolean updateCoreUserByCruseMonthExchintegral() {
        myBatisDAO.update("updateCoreUserByCruseMonthExchintegral", null);
        return true;
    }

}