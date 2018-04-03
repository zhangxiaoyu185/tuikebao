package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 热榜表
*/
public class BusiHotList extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bshltUnid;

	/**
	*标识UUID
	*/
	private String bshltUuid;

	/**
	*商品标识
	*/
	private String bshltProduct;

	/**
	*顺序
	*/
	private Integer bshltOrd;

	/**
	*状态:1启动0禁用
	*/
	private Integer bshltStatus;

	/**
	*创建时间
	*/
	private Date bshltCdate;

	/**
	*更新时间
	*/
	private Date bshltUdate;

	public void setBshltUnid(Integer bshltUnid) {
		this.bshltUnid = bshltUnid;
	}

	public Integer getBshltUnid( ) {
		return bshltUnid;
	}

	public void setBshltUuid(String bshltUuid) {
		this.bshltUuid = bshltUuid;
	}

	public String getBshltUuid( ) {
		return bshltUuid;
	}

	public void setBshltProduct(String bshltProduct) {
		this.bshltProduct = bshltProduct;
	}

	public String getBshltProduct( ) {
		return bshltProduct;
	}

	public void setBshltOrd(Integer bshltOrd) {
		this.bshltOrd = bshltOrd;
	}

	public Integer getBshltOrd( ) {
		return bshltOrd;
	}

	public void setBshltStatus(Integer bshltStatus) {
		this.bshltStatus = bshltStatus;
	}

	public Integer getBshltStatus( ) {
		return bshltStatus;
	}

	public void setBshltCdate(Date bshltCdate) {
		this.bshltCdate = bshltCdate;
	}

	public Date getBshltCdate( ) {
		return bshltCdate;
	}

	public void setBshltUdate(Date bshltUdate) {
		this.bshltUdate = bshltUdate;
	}

	public Date getBshltUdate( ) {
		return bshltUdate;
	}

	public BusiHotList( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}