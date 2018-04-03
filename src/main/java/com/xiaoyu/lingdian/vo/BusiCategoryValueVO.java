package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiCategoryValue;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 产品类型属性值表
*/
public class BusiCategoryValueVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bscveUuid;

	/**
	*分类标识
	*/
	private String bscveCategory;

	/**
	*属性标识
	*/
	private String bscveAttr;

	/**
	*属性值
	*/
	private String bscveValue;

	/**
	*显示顺序
	*/
	private Integer bscveOrd;

	/**
	*创建时间
	*/
	private String bscveCdate;

	/**
	*更新时间
	*/
	private String bscveUdate;

	public void setBscveUuid(String bscveUuid) {
		this.bscveUuid = bscveUuid;
	}

	public String getBscveUuid( ) {
		return bscveUuid;
	}

	public void setBscveCategory(String bscveCategory) {
		this.bscveCategory = bscveCategory;
	}

	public String getBscveCategory( ) {
		return bscveCategory;
	}

	public void setBscveAttr(String bscveAttr) {
		this.bscveAttr = bscveAttr;
	}

	public String getBscveAttr( ) {
		return bscveAttr;
	}

	public void setBscveValue(String bscveValue) {
		this.bscveValue = bscveValue;
	}

	public String getBscveValue( ) {
		return bscveValue;
	}

	public void setBscveOrd(Integer bscveOrd) {
		this.bscveOrd = bscveOrd;
	}

	public Integer getBscveOrd( ) {
		return bscveOrd;
	}

	public void setBscveCdate(String bscveCdate) {
		this.bscveCdate = bscveCdate;
	}

	public String getBscveCdate( ) {
		return bscveCdate;
	}

	public void setBscveUdate(String bscveUdate) {
		this.bscveUdate = bscveUdate;
	}

	public String getBscveUdate( ) {
		return bscveUdate;
	}

	public BusiCategoryValueVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiCategoryValue po = (BusiCategoryValue) poObj;
		this.bscveUuid = po.getBscveUuid();
		this.bscveCategory = po.getBscveCategory();
		this.bscveAttr = po.getBscveAttr();
		this.bscveValue = po.getBscveValue();
		this.bscveOrd = po.getBscveOrd();
		this.bscveCdate = po.getBscveCdate()!=null?DateUtil.formatDefaultDate(po.getBscveCdate()):"";
		this.bscveUdate = po.getBscveUdate()!=null?DateUtil.formatDefaultDate(po.getBscveUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}