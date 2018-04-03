package com.xiaoyu.lingdian.controller.product;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.BusiCategoryAttribute;
import com.xiaoyu.lingdian.entity.BusiCategoryValue;
import com.xiaoyu.lingdian.entity.BusiProductCategory;
import com.xiaoyu.lingdian.service.product.BusiCategoryAttributeService;
import com.xiaoyu.lingdian.service.product.BusiCategoryValueService;
import com.xiaoyu.lingdian.service.product.BusiProductCategoryService;
import com.xiaoyu.lingdian.vo.BusiCategoryAttributeVO;
import com.xiaoyu.lingdian.vo.BusiCategoryValueVO;

@Controller
@RequestMapping(value="/busiCategoryAttribute")
public class BusiCategoryAttributeController extends BaseController {

	/**
	* 产品类型属性表
	*/
	@Autowired
	private BusiCategoryAttributeService busiCategoryAttributeService;
	
	/**
	* 产品类型属性值表
	*/
	@Autowired
	private BusiCategoryValueService busiCategoryValueService;

	/**
	* 分类表
	*/
	@Autowired
	private BusiProductCategoryService busiProductCategoryService;

	/**
	* 添加属性
	*
	* @param bscaeCategory 分类标识
	* @param bscaeName 属性名称
	* @return
	*/
	@RequestMapping(value="/add/busiCategoryAttribute", method=RequestMethod.POST)
	public void addBusiCategoryAttribute (String bscaeCategory, String bscaeName, HttpServletResponse response) {
		logger.info("[BusiCategoryAttributeController]:begin addBusiCategoryAttribute");
		if (StringUtil.isEmpty(bscaeCategory)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分类标识不能为空!"), response);
			logger.info("[BusiCategoryAttributeController]:end addBusiCategoryAttribute");
			return;
		}
		if (StringUtil.isEmpty(bscaeName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "属性名称不能为空!"), response);
			logger.info("[BusiCategoryAttributeController]:end addBusiCategoryAttribute");
			return;
		}

		int maxOrd = busiCategoryAttributeService.getBusiCategoryAttributeMaxOrd();
				BusiCategoryAttribute busiCategoryAttribute = new BusiCategoryAttribute();
		String uuid = RandomUtil.generateString(16);
		busiCategoryAttribute.setBscaeUuid(uuid);
		busiCategoryAttribute.setBscaeCategory(bscaeCategory);
		busiCategoryAttribute.setBscaeName(bscaeName);
		busiCategoryAttribute.setBscaeOrd(maxOrd + 1);
		busiCategoryAttribute.setBscaeCdate(new Date());
		busiCategoryAttribute.setBscaeUdate(new Date());

		busiCategoryAttributeService.insertBusiCategoryAttribute(busiCategoryAttribute);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!", uuid),response);
		logger.info("[BusiCategoryAttributeController]:end addBusiCategoryAttribute");
	}

	/**
	* 添加属性值
	*
	* @param bscveCategory 分类标识
	* @param bscveAttr 属性标识
	* @param bscveValue 属性值
	* @return
	*/
	@RequestMapping(value="/add/busiCategoryValue", method=RequestMethod.POST)
	public void addBusiCategoryValue (String bscveCategory, String bscveAttr, String bscveValue, HttpServletResponse response) {
		logger.info("[BusiCategoryAttributeController]:begin addBusiCategoryValue");
		if (StringUtil.isEmpty(bscveCategory)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分类标识不能为空!"), response);
			logger.info("[BusiCategoryAttributeController]:end addBusiCategoryAttribute");
			return;
		}
		if (StringUtil.isEmpty(bscveAttr)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "属性标识不能为空!"), response);
			logger.info("[BusiCategoryAttributeController]:end addBusiCategoryAttribute");
			return;
		}
		if (StringUtil.isEmpty(bscveValue)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "属性值不能为空!"), response);
			logger.info("[BusiCategoryAttributeController]:end addBusiCategoryAttribute");
			return;
		}

		int maxOrd = busiCategoryValueService.getBusiCategoryValueMaxOrd();
		
		BusiCategoryValue busiCategoryValue = new BusiCategoryValue();
		String uuid = RandomUtil.generateString(16);
		busiCategoryValue.setBscveUuid(uuid);
		busiCategoryValue.setBscveCategory(bscveCategory);
		busiCategoryValue.setBscveAttr(bscveAttr);
		busiCategoryValue.setBscveValue(bscveValue);
		busiCategoryValue.setBscveOrd(maxOrd + 1);
		busiCategoryValue.setBscveCdate(new Date());
		busiCategoryValue.setBscveUdate(new Date());

		busiCategoryValueService.insertBusiCategoryValue(busiCategoryValue);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!", uuid),response);
		logger.info("[BusiCategoryAttributeController]:end addBusiCategoryValue");
	}

	/**
	* 删除属性,批量删除对应属性值
	*
	* @param bscaeUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/attr/delete/one", method=RequestMethod.POST)
	public void deleteBusiCategoryAttribute (String bscaeUuid, HttpServletResponse response) {
		logger.info("[BusiCategoryAttributeController]:begin deleteBusiCategoryAttribute");
		if (StringUtil.isEmpty(bscaeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiCategoryAttributeController]:end deleteBusiCategoryAttribute");
			return;
		}
		BusiCategoryAttribute busiCategoryAttribute = new BusiCategoryAttribute();
		busiCategoryAttribute.setBscaeUuid(bscaeUuid);

		busiCategoryAttributeService.deleteBusiCategoryAttribute(busiCategoryAttribute);

		busiCategoryValueService.deleteBusiCategoryValueByAttr(bscaeUuid);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiCategoryAttributeController]:end deleteBusiCategoryAttribute");
	}

	/**
	* 删除属性值
	*
	* @param bscveUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/attr/value/delete/one", method=RequestMethod.POST)
	public void deleteBusiCategoryValue (String bscveUuid, HttpServletResponse response) {
		logger.info("[BusiCategoryAttributeController]:begin deleteBusiCategoryValue");

		if (StringUtil.isEmpty(bscveUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiCategoryAttributeController]:end deleteBusiCategoryValue");
			return;
		}

		BusiCategoryValue busiCategoryValue = new BusiCategoryValue();
		busiCategoryValue.setBscveUuid(bscveUuid);

		busiCategoryValueService.deleteBusiCategoryValue(busiCategoryValue);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiCategoryAttributeController]:end deleteBusiCategoryValue");
	}

	/**
	* 获取单个属性
	*
	* @param bscaeUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/attr/views", method=RequestMethod.POST)
	public void viewsBusiCategoryAttribute (String bscaeUuid, HttpServletResponse response) {
		logger.info("[BusiCategoryAttributeController]:begin viewsBusiCategoryAttribute");
		if (StringUtil.isEmpty(bscaeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiCategoryAttributeController]:end viewsBusiCategoryAttribute");
			return;
		}
		BusiCategoryAttribute busiCategoryAttribute = new BusiCategoryAttribute();
		busiCategoryAttribute.setBscaeUuid(bscaeUuid);
		busiCategoryAttribute = busiCategoryAttributeService.getBusiCategoryAttribute(busiCategoryAttribute);
		if (null == busiCategoryAttribute) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "属性不存在!"), response);
			logger.info("[BusiCategoryAttributeController]:end viewsBusiCategoryAttribute");
			return;
		}

		HashSet<String> hashCategoryUuids = new HashSet<String>();
		hashCategoryUuids.add(busiCategoryAttribute.getBscaeCategory());
		List<String> categoryUuids = new ArrayList<>(hashCategoryUuids);
		Map<String, BusiProductCategory> categoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(categoryUuids);	
		
		BusiCategoryAttributeVO busiCategoryAttributeVO = new BusiCategoryAttributeVO();
		busiCategoryAttributeVO.convertPOToVO(busiCategoryAttribute);
		busiCategoryAttributeVO.setBscaeCategoryName(categoryMap.get(busiCategoryAttribute.getBscaeCategory())==null?null:categoryMap.get(busiCategoryAttribute.getBscaeCategory()).getBspcyName());
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiCategoryAttributeVO), response);
		logger.info("[BusiCategoryAttributeController]:end viewsBusiCategoryAttribute");
	}

	/**
	* 根据属性获取对应属性值list
	* @param bscveAttr
	* @return
	*/
	@RequestMapping(value="/attr/value/find/all", method=RequestMethod.POST)
	public void findBusiCategoryValueList (String bscveAttr, HttpServletResponse response) {
		logger.info("[BusiCategoryAttributeController]:begin findBusiCategoryValueList");

		List<BusiCategoryValue> lists = busiCategoryValueService.findBusiCategoryValueList(bscveAttr);
		List<BusiCategoryValueVO> vos = new ArrayList<BusiCategoryValueVO>();
		BusiCategoryValueVO vo;
		for (BusiCategoryValue busiCategoryValue : lists) {
			vo = new BusiCategoryValueVO();
			vo.convertPOToVO(busiCategoryValue);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiCategoryAttributeController]:end findBusiCategoryValueList");
	}
	
	/**
	* 根据分类标识获取属性列表<List>
	* 
	* @param bscaeCategory 分类标识
	* @return
	*/
	@RequestMapping(value="/attr/find/all", method=RequestMethod.POST)
	public void findBusiCategoryAttributeList (String bscaeCategory, HttpServletResponse response) {
		logger.info("[BusiCategoryAttributeController]:begin findBusiCategoryAttributeList");
		if (StringUtil.isEmpty(bscaeCategory)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分类标识不能为空!"), response);
			logger.info("[BusiCategoryAttributeController]:end findBusiCategoryAttributeList");
			return;
		}
		List<BusiCategoryAttribute> lists = busiCategoryAttributeService.findBusiCategoryAttributeList(bscaeCategory);
		List<BusiCategoryAttributeVO> vos = new ArrayList<BusiCategoryAttributeVO>();
		
		HashSet<String> hashCategoryUuids = new HashSet<String>();
		for (BusiCategoryAttribute busiCategoryAttribute : lists) {
			hashCategoryUuids.add(busiCategoryAttribute.getBscaeCategory());
		}
		List<String> categoryUuids = new ArrayList<>(hashCategoryUuids);
		Map<String, BusiProductCategory> categoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(categoryUuids);
		
		BusiCategoryAttributeVO vo;
		for (BusiCategoryAttribute busiCategoryAttribute : lists) {
			vo = new BusiCategoryAttributeVO();			vo.convertPOToVO(busiCategoryAttribute);
			vo.setBscaeCategoryName(categoryMap.get(busiCategoryAttribute.getBscaeCategory())==null?null:categoryMap.get(busiCategoryAttribute.getBscaeCategory()).getBspcyName());			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiCategoryAttributeController]:end findBusiCategoryAttributeList");

	}

	/**
	* 后台根据分类标识获取属性列表<Page>
	* 
	* @param categoryName 分类名称
	* @param bscaeName 分类名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiCategoryAttributePage (String categoryName, String bscaeName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiCategoryAttributeController]:begin findBusiCategoryAttributePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiCategoryAttribute> page = busiCategoryAttributeService.findBusiCategoryAttributePage(categoryName, bscaeName, pageNum, pageSize);
		Page<BusiCategoryAttributeVO> pageVO = new Page<BusiCategoryAttributeVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashCategoryUuids = new HashSet<String>();
		for (BusiCategoryAttribute busiCategoryAttribute : page.getResult()) {
			hashCategoryUuids.add(busiCategoryAttribute.getBscaeCategory());
		}
		List<String> categoryUuids = new ArrayList<>(hashCategoryUuids);
		Map<String, BusiProductCategory> categoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(categoryUuids);
		
		List<BusiCategoryAttributeVO> vos = new ArrayList<BusiCategoryAttributeVO>();
		BusiCategoryAttributeVO vo;
		for (BusiCategoryAttribute busiCategoryAttribute : page.getResult()) {
			vo = new BusiCategoryAttributeVO();			vo.convertPOToVO(busiCategoryAttribute);
			vo.setBscaeCategoryName(categoryMap.get(busiCategoryAttribute.getBscaeCategory())==null?null:categoryMap.get(busiCategoryAttribute.getBscaeCategory()).getBspcyName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiCategoryAttributeController]:end findBusiCategoryAttributePage");
	}

}