package com.xiaoyu.lingdian.service.impl.my;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiIntegralRecordService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiIntegralRecord;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 用户积分明细表
*/
@Service("busiIntegralRecordService")
public class BusiIntegralRecordServiceImpl implements BusiIntegralRecordService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;

	@Override
	public boolean insertBusiIntegralRecord(BusiIntegralRecord busiIntegralRecord) {
		myBatisDAO.insert(busiIntegralRecord);
		return true;
	}

	@Override
	public boolean updateBusiIntegralRecord(BusiIntegralRecord busiIntegralRecord) {
		myBatisDAO.update(busiIntegralRecord);
		return true;
	}

	@Override
	public boolean deleteBusiIntegralRecord(BusiIntegralRecord busiIntegralRecord) {
		myBatisDAO.delete(busiIntegralRecord);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiIntegralRecordByIds",hashMap);
		return true;
	}

	@Override
	public BusiIntegralRecord getBusiIntegralRecord(BusiIntegralRecord busiIntegralRecord) {
		return (BusiIntegralRecord) myBatisDAO.findForObject(busiIntegralRecord);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiIntegralRecord> findBusiIntegralRecordPageByMy(String bsirdUser, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bsirdUser", bsirdUser);
		return myBatisDAO.findForPage("findBusiIntegralRecordForPagesByMy", new PageRequest(pageNum, pageSize, hashMap));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiIntegralRecord> findBusiIntegralRecordPage(String userName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiIntegralRecord>(1, 10, 0);
			}
			hashMap.put("list", list);
		}
		return myBatisDAO.findForPage("findBusiIntegralRecordForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}