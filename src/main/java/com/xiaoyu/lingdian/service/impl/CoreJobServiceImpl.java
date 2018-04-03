package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreJob;

/**
* 职业表
*/
@Service("coreJobService")
public class CoreJobServiceImpl implements CoreJobService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreJob(CoreJob coreJob) {
		myBatisDAO.insert(coreJob);
		return true;
	}

	@Override
	public boolean updateCoreJob(CoreJob coreJob) {
		myBatisDAO.update(coreJob);
		return true;
	}

	@Override
	public boolean deleteCoreJob(CoreJob coreJob) {
		myBatisDAO.delete(coreJob);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchCoreJobByIds",hashMap);
		return true;
	}

	@Override
	public CoreJob getCoreJob(CoreJob coreJob) {
		return (CoreJob) myBatisDAO.findForObject(coreJob);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreJob> findCoreJobList() {
		return myBatisDAO.findForList("findCoreJobForLists", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreJob> findCoreJobPage(String crjobName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crjobName", crjobName);
		return myBatisDAO.findForPage("findCoreJobForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}