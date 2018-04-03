package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.xiaoyu.lingdian.core.mybatis.mapper.SimpleMapMapper;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreGrade;

/**
* 等级配置表
*/
@Service("coreGradeService")
public class CoreGradeServiceImpl implements CoreGradeService {

	private static final SimpleMapMapper<String, CoreGrade> GRADE_UUID_MAPPER = new SimpleMapMapper<String, CoreGrade>() {
		@Override
		public String mapKey(CoreGrade object, int rowNum) {
			return object.getCrgdeUuid();
		}
	};

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreGrade(CoreGrade coreGrade) {
		myBatisDAO.insert(coreGrade);
		return true;
	}

	@Override
	public boolean updateCoreGrade(CoreGrade coreGrade) {
		myBatisDAO.update(coreGrade);
		return true;
	}

	@Override
	public boolean deleteCoreGrade(CoreGrade coreGrade) {
		myBatisDAO.delete(coreGrade);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchCoreGradeByIds",hashMap);
		return true;
	}

	@Override
	public CoreGrade getCoreGrade(CoreGrade coreGrade) {
		return (CoreGrade) myBatisDAO.findForObject(coreGrade);
	}

	@Override
	public CoreGrade getCoreGradeAfterGrade(int crgdeOrd) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgdeOrd", crgdeOrd);
		return (CoreGrade) myBatisDAO.findForObject("getCoreGradeAfterGrade", hashMap);
	}

	@Override
	public CoreGrade getCoreGradeByName(CoreGrade coreGrade) {
		return (CoreGrade) myBatisDAO.findForObject("getCoreGradeByName", coreGrade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, CoreGrade> findCoreGradeMapByUuidList(List<String> list) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		return myBatisDAO.findForMap("findCoreGradeForLists", hashMap, GRADE_UUID_MAPPER);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreGrade> findCoreGradeList() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForList("findCoreGradeForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreGrade> findCoreGradePage(String crgdeName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("crgdeName", crgdeName);
		return myBatisDAO.findForPage("findCoreGradeForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}