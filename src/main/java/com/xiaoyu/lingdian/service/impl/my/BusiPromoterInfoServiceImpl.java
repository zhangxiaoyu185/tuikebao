package com.xiaoyu.lingdian.service.impl.my;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiPromoterInfoService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiPromoterInfo;
import com.xiaoyu.lingdian.entity.CoreUser;

/**
* 用户提现信息表
*/
@Service("busiPromoterInfoService")
public class BusiPromoterInfoServiceImpl implements BusiPromoterInfoService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Autowired
	private CoreUserService coreUserService;

	@Override
	public boolean insertBusiPromoterInfo(BusiPromoterInfo busiPromoterInfo) {
		myBatisDAO.insert(busiPromoterInfo);
		return true;
	}

	@Override
	public boolean updateBusiPromoterInfo(BusiPromoterInfo busiPromoterInfo) {
		myBatisDAO.update(busiPromoterInfo);
		return true;
	}

	@Override
	public boolean deleteBusiPromoterInfo(BusiPromoterInfo busiPromoterInfo) {
		myBatisDAO.delete(busiPromoterInfo);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchBusiPromoterInfoByIds",hashMap);
		return true;
	}

	@Override
	public BusiPromoterInfo getBusiPromoterInfo(BusiPromoterInfo busiPromoterInfo) {
		return (BusiPromoterInfo) myBatisDAO.findForObject(busiPromoterInfo);
	}

	@Override
	public BusiPromoterInfo getBusiPromoterInfoByUser(String bspioUser) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bspioUser", bspioUser);
		return (BusiPromoterInfo) myBatisDAO.findForObject("getBusiPromoterInfoByUser", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiPromoterInfo> findBusiPromoterInfoPage(String userName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userName)) {
			List<CoreUser> userList = coreUserService.findCoreUserList(userName, null);
			List<String> list = new ArrayList<>();
			for (CoreUser coreUser : userList) {
				list.add(coreUser.getCrusrUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiPromoterInfo>(1, 10, 0);
			}
			hashMap.put("list", list);
		}
		return myBatisDAO.findForPage("findBusiPromoterInfoForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}