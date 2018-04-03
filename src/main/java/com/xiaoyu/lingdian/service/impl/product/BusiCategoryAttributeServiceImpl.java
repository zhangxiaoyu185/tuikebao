package com.xiaoyu.lingdian.service.impl.product;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.product.BusiCategoryAttributeService;
import com.xiaoyu.lingdian.service.product.BusiProductCategoryService;
import com.xiaoyu.lingdian.tool.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiCategoryAttribute;
import com.xiaoyu.lingdian.entity.BusiProductCategory;

/**
* 产品类型属性表
*/
@Service("busiCategoryAttributeService")
public class BusiCategoryAttributeServiceImpl implements BusiCategoryAttributeService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	/**
	* 分类表
	*/
	@Autowired
	private BusiProductCategoryService busiProductCategoryService;
	
	@Override
	public boolean insertBusiCategoryAttribute(BusiCategoryAttribute busiCategoryAttribute) {
		myBatisDAO.insert(busiCategoryAttribute);
		return true;
	}

	@Override
	public boolean deleteBusiCategoryAttribute(BusiCategoryAttribute busiCategoryAttribute) {
		myBatisDAO.delete(busiCategoryAttribute);
		return true;
	}

	@Override
	public BusiCategoryAttribute getBusiCategoryAttribute(BusiCategoryAttribute busiCategoryAttribute) {
		return (BusiCategoryAttribute) myBatisDAO.findForObject(busiCategoryAttribute);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiCategoryAttribute> findBusiCategoryAttributeList(String bscaeCategory) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bscaeCategory", bscaeCategory);
		return myBatisDAO.findForList("findBusiCategoryAttributeForLists", hashMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BusiCategoryAttribute> findBusiCategoryAttributePage(String categoryName, String bscaeName, int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();	
		if (!StringUtil.isEmpty(categoryName)) {
			List<BusiProductCategory> categoryList = busiProductCategoryService.findBusiProductCategoryList(categoryName);
			List<String> list = new ArrayList<>();
			for (BusiProductCategory busiProductCategory : categoryList) {
				list.add(busiProductCategory.getBspcyUuid());
			}
			if(list.size() <= 0) {
				return new Page<BusiCategoryAttribute>(1, 10, 0);
			}
			hashMap.put("list", list);
		}

		hashMap.put("bscaeName", bscaeName);
		return myBatisDAO.findForPage("findBusiCategoryAttributeForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

	@Override
	public int getBusiCategoryAttributeMaxOrd() {
		Integer maxOrd = (Integer) myBatisDAO.findForObject("getBusiCategoryAttributeMaxOrd", null);
		return maxOrd==null?0:maxOrd;
	}

}