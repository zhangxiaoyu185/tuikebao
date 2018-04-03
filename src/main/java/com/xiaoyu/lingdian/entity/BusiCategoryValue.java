package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 产品类型属性值表
*/
public class BusiCategoryValue extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bscveUnid;

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
	private Date bscveCdate;

	/**
	*更新时间
	*/
	private Date bscveUdate;

	public void setBscveUnid(Integer bscveUnid) {
		this.bscveUnid = bscveUnid;
	}

	public Integer getBscveUnid( ) {
		return bscveUnid;
	}

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

	public void setBscveCdate(Date bscveCdate) {
		this.bscveCdate = bscveCdate;
	}

	public Date getBscveCdate( ) {
		return bscveCdate;
	}

	public void setBscveUdate(Date bscveUdate) {
		this.bscveUdate = bscveUdate;
	}

	public Date getBscveUdate( ) {
		return bscveUdate;
	}

	public BusiCategoryValue( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}