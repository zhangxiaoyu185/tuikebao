package com.xiaoyu.lingdian.service.impl.my;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiShareRecordService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiShareRecord;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 用户分享记录表
*/
@Service("busiShareRecordService")
public class BusiShareRecordServiceImpl implements BusiShareRecordService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;

	@Override
	public boolean insertBusiShareRecord(BusiShareRecord busiShareRecord) {
		myBatisDAO.insert(busiShareRecord);
		return true;
	}

	@Override
	public boolean updateBusiShareRecord(BusiShareRecord busiShareRecord) {
		myBatisDAO.update(busiShareRecord);
		return true;
	}

	@Override
	public boolean deleteBusiShareRecord(BusiShareRecord busiShareRecord) {
		myBatisDAO.delete(busiShareRecord);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiShareRecordByIds",hashMap);
		return true;
	}

	@Override
	public BusiShareRecord getBusiShareRecord(BusiShareRecord busiShareRecord) {
		return (BusiShareRecord) myBatisDAO.findForObject(busiShareRecord);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiShareRecord> findBusiShareRecordForPagesByMy(String bssrdUser, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssrdUser", bssrdUser);
		return myBatisDAO.findForPage("findBusiShareRecordForPagesByMy", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiShareRecord> findBusiShareRecordPage(String userName, String bssrdProductName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiShareRecord>(1, 10, 0);
			}
			hashMap.put("list", list);
		}
		hashMap.put("bssrdProductName", bssrdProductName);
		return myBatisDAO.findForPage("findBusiShareRecordForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@Override
	public BusiShareRecord getBusiShareRecordByProductAndUser(String bssrdUser, String bssrdProduct) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssrdUser", bssrdUser);
		hashMap.put("bssrdProduct", bssrdProduct);
		return (BusiShareRecord) myBatisDAO.findForObject("getBusiShareRecordByProductAndUser", hashMap);
	}

	@Override
	public boolean updateBusiShareRecordByProductAndUser(BusiShareRecord busiShareRecord) {
		myBatisDAO.update("updateBusiShareRecordByProductAndUser", busiShareRecord);
		return true;
	}

}