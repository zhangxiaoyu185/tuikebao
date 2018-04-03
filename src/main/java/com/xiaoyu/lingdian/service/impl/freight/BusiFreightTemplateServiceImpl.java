package com.xiaoyu.lingdian.service.impl.freight;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.freight.BusiFreightTemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiFreightTemplate;

/**
* 运费模板表
*/
@Service("busiFreightTemplateService")
public class BusiFreightTemplateServiceImpl implements BusiFreightTemplateService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiFreightTemplate(BusiFreightTemplate busiFreightTemplate) {
		myBatisDAO.insert(busiFreightTemplate);
		return true;
	}

	@Override
	public boolean updateBusiFreightTemplate(BusiFreightTemplate busiFreightTemplate) {
		myBatisDAO.update(busiFreightTemplate);
		return true;
	}

	@Override
	public boolean deleteBusiFreightTemplate(BusiFreightTemplate busiFreightTemplate) {
		myBatisDAO.delete(busiFreightTemplate);
		return true;
	}

	private static final String DELETEBATCH_BUSIFREIGHTTEMPLATE_BY_IDS="deleteBatchBusiFreightTemplateByIds";

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete(DELETEBATCH_BUSIFREIGHTTEMPLATE_BY_IDS,hashMap);
		return true;
	}

	@Override
	public BusiFreightTemplate getBusiFreightTemplate(BusiFreightTemplate busiFreightTemplate) {
		return (BusiFreightTemplate) myBatisDAO.findForObject(busiFreightTemplate);
	}

	private static final String FIND_BUSIFREIGHTTEMPLATE_FOR_LISTS="findBusiFreightTemplateForLists";

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiFreightTemplate> findBusiFreightTemplateList() {
		return myBatisDAO.findForList(FIND_BUSIFREIGHTTEMPLATE_FOR_LISTS, null);
	}

	private static final String FIND_BUSIFREIGHTTEMPLATE_FOR_PAGES="findBusiFreightTemplateForPages";

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiFreightTemplate> findBusiFreightTemplatePage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage(FIND_BUSIFREIGHTTEMPLATE_FOR_PAGES, new PageRequest(pageNum, pageSize, hashMap));
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}