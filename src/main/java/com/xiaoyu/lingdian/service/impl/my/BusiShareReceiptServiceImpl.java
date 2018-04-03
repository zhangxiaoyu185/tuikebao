package com.xiaoyu.lingdian.service.impl.my;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiShareReceiptService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiShareReceipt;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 分享商品购买收货记录表
*/
@Service("busiShareReceiptService")
public class BusiShareReceiptServiceImpl implements BusiShareReceiptService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;

	@Override
	public boolean insertBusiShareReceipt(BusiShareReceipt busiShareReceipt) {
		myBatisDAO.insert(busiShareReceipt);
		return true;
	}

	@Override
	public boolean updateBusiShareReceipt(BusiShareReceipt busiShareReceipt) {
		myBatisDAO.update(busiShareReceipt);
		return true;
	}

	@Override
	public BusiShareReceipt getBusiShareReceipt(BusiShareReceipt busiShareReceipt) {
		return (BusiShareReceipt) myBatisDAO.findForObject(busiShareReceipt);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiShareReceipt> findBusiShareReceiptPage(String userName, String bssrtProductName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiShareReceipt>(1, 10, 0);
			}
			hashMap.put("list", list);
		}
		hashMap.put("bssrtProductName", bssrtProductName);
		return myBatisDAO.findForPage("findBusiShareReceiptForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@Override
	public BusiShareReceipt getBusiShareReceiptByOrder(String bssrtOrderNo) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bssrtOrderNo", bssrtOrderNo);
		return (BusiShareReceipt) myBatisDAO.findForObject("getBusiShareReceiptByOrder", hashMap);
	}

}