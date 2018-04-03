package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiCategoryAttribute;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 产品类型属性表
*/
public class BusiCategoryAttributeVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bscaeUuid;

	/**
	*分类标识
	*/
	private String bscaeCategory;

	/**
	*分类标识名称
	*/
	private String bscaeCategoryName;
	
	/**
	*属性名称
	*/
	private String bscaeName;

	/**
	*显示顺序
	*/
	private Integer bscaeOrd;

	/**
	*创建时间
	*/
	private String bscaeCdate;

	/**
	*更新时间
	*/
	private String bscaeUdate;

	public void setBscaeUuid(String bscaeUuid) {
		this.bscaeUuid = bscaeUuid;
	}

	public String getBscaeUuid( ) {
		return bscaeUuid;
	}

	public void setBscaeCategory(String bscaeCategory) {
		this.bscaeCategory = bscaeCategory;
	}

	public String getBscaeCategory( ) {
		return bscaeCategory;
	}

	public void setBscaeName(String bscaeName) {
		this.bscaeName = bscaeName;
	}

	public String getBscaeName( ) {
		return bscaeName;
	}

	public void setBscaeOrd(Integer bscaeOrd) {
		this.bscaeOrd = bscaeOrd;
	}

	public Integer getBscaeOrd( ) {
		return bscaeOrd;
	}

	public void setBscaeCdate(String bscaeCdate) {
		this.bscaeCdate = bscaeCdate;
	}

	public String getBscaeCdate( ) {
		return bscaeCdate;
	}

	public void setBscaeUdate(String bscaeUdate) {
		this.bscaeUdate = bscaeUdate;
	}

	public String getBscaeUdate( ) {
		return bscaeUdate;
	}

	public String getBscaeCategoryName() {
		return bscaeCategoryName;
	}

	public void setBscaeCategoryName(String bscaeCategoryName) {
		this.bscaeCategoryName = bscaeCategoryName;
	}

	public BusiCategoryAttributeVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiCategoryAttribute po = (BusiCategoryAttribute) poObj;
		this.bscaeUuid = po.getBscaeUuid();
		this.bscaeCategory = po.getBscaeCategory();
		this.bscaeName = po.getBscaeName();
		this.bscaeOrd = po.getBscaeOrd();
		this.bscaeCdate = po.getBscaeCdate()!=null?DateUtil.formatDefaultDate(po.getBscaeCdate()):"";
		this.bscaeUdate = po.getBscaeUdate()!=null?DateUtil.formatDefaultDate(po.getBscaeUdate()):"";
	}

}