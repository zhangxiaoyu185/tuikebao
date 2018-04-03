package com.xiaoyu.lingdian.service.impl.my;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiCashRecordService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiCashRecord;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 用户提现记录表
*/
@Service("busiCashRecordService")
public class BusiCashRecordServiceImpl implements BusiCashRecordService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;
	
	@Override
	public boolean insertBusiCashRecord(BusiCashRecord busiCashRecord) {
		myBatisDAO.insert(busiCashRecord);
		return true;
	}

	@Override
	public boolean updateBusiCashRecord(BusiCashRecord busiCashRecord) {
		myBatisDAO.update(busiCashRecord);
		return true;
	}

	@Override
	public boolean deleteBusiCashRecord(BusiCashRecord busiCashRecord) {
		myBatisDAO.delete(busiCashRecord);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiCashRecordByIds", hashMap);
		return true;
	}

	@Override
	public BusiCashRecord getBusiCashRecord(BusiCashRecord busiCashRecord) {
		return (BusiCashRecord) myBatisDAO.findForObject(busiCashRecord);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiCashRecord> findBusiCashRecordPage(String userName, String bscrdMobile, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiCashRecord>(1, 10, 0);
			}
			hashMap.put("list", list);
		}
		hashMap.put("bscrdMobile", bscrdMobile);
		return myBatisDAO.findForPage("findBusiCashRecordForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiCashRecord> findBusiCashRecordForPagesByMy(String bscrdExtracted, int bscrdStatus, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bscrdExtracted", bscrdExtracted);
		hashMap.put("bscrdStatus", bscrdStatus);
		return myBatisDAO.findForPage("findBusiCashRecordForPagesByMy", new PageRequest(pageNum, pageSize, hashMap));
	}

}