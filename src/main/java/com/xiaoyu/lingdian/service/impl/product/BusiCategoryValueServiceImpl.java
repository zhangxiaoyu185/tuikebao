package com.xiaoyu.lingdian.service.impl.product;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.service.product.BusiCategoryValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.BusiCategoryValue;

/**
* 产品类型属性值表
*/
@Service("busiCategoryValueService")
public class BusiCategoryValueServiceImpl implements BusiCategoryValueService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertBusiCategoryValue(BusiCategoryValue busiCategoryValue) {
		myBatisDAO.insert(busiCategoryValue);
		return true;
	}

	@Override
	public boolean deleteBusiCategoryValue(BusiCategoryValue busiCategoryValue) {
		myBatisDAO.delete(busiCategoryValue);
		return true;
	}

	@Override
	public BusiCategoryValue getBusiCategoryValue(BusiCategoryValue busiCategoryValue) {
		return (BusiCategoryValue) myBatisDAO.findForObject(busiCategoryValue);
	}

	@Override
	public int getBusiCategoryValueMaxOrd() {
		Integer maxOrd = (Integer) myBatisDAO.findForObject("getBusiCategoryValueMaxOrd", null);
		return maxOrd==null?0:maxOrd;
	}
	
	@Override
	public boolean deleteBusiCategoryValueByAttr(String bscveAttr) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bscveAttr", bscveAttr);
		myBatisDAO.delete("deleteBusiCategoryValueByAttr", hashMap);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiCategoryValue> findBusiCategoryValueList(String bscveAttr) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bscveAttr", bscveAttr);
		return myBatisDAO.findForList("findBusiCategoryValueForLists", hashMap);
	}

}