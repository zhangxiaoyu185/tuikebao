package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreShareCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreShareCopy;

/**
* 系统分享文案设置表
*/
@Service("coreShareCopyService")
public class CoreShareCopyServiceImpl implements CoreShareCopyService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreShareCopy(CoreShareCopy coreShareCopy) {
		myBatisDAO.insert(coreShareCopy);
		return true;
	}

	@Override
	public boolean updateCoreShareCopy(CoreShareCopy coreShareCopy) {
		myBatisDAO.update(coreShareCopy);
		return true;
	}

	@Override
	public boolean deleteCoreShareCopy(CoreShareCopy coreShareCopy) {
		myBatisDAO.delete(coreShareCopy);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchCoreShareCopyByIds",hashMap);
		return true;
	}

	@Override
	public CoreShareCopy getCoreShareCopy(CoreShareCopy coreShareCopy) {
		return (CoreShareCopy) myBatisDAO.findForObject(coreShareCopy);
	}

	@Override
	public CoreShareCopy getCoreShareCopyTop() {
		return (CoreShareCopy) myBatisDAO.findForObject("getCoreShareCopyTop", null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CoreShareCopy> findCoreShareCopyList() {
		return myBatisDAO.findForList("findCoreShareCopyForLists", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreShareCopy> findCoreShareCopyPage(String crscyCopy, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crscyCopy",crscyCopy);
		return myBatisDAO.findForPage("findCoreShareCopyForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}