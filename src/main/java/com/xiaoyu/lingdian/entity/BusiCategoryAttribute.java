package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 产品类型属性表
*/
public class BusiCategoryAttribute extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bscaeUnid;

	/**
	*标识UUID
	*/
	private String bscaeUuid;

	/**
	*分类标识
	*/
	private String bscaeCategory;

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
	private Date bscaeCdate;

	/**
	*更新时间
	*/
	private Date bscaeUdate;

	public void setBscaeUnid(Integer bscaeUnid) {
		this.bscaeUnid = bscaeUnid;
	}

	public Integer getBscaeUnid( ) {
		return bscaeUnid;
	}

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

	public void setBscaeCdate(Date bscaeCdate) {
		this.bscaeCdate = bscaeCdate;
	}

	public Date getBscaeCdate( ) {
		return bscaeCdate;
	}

	public void setBscaeUdate(Date bscaeUdate) {
		this.bscaeUdate = bscaeUdate;
	}

	public Date getBscaeUdate( ) {
		return bscaeUdate;
	}

	public BusiCategoryAttribute( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}