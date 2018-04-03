package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 用户积分明细表
*/
public class BusiIntegralRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsirdUnid;

	/**
	*标识UUID
	*/
	private String bsirdUuid;

	/**
	*所属用户
	*/
	private String bsirdUser;

	/**
	*积分类型:1获得2消耗
	*/
	private Integer bsirdType;

	/**
	*额度
	*/
	private String bsirdQuota;

	/**
	*事项
	*/
	private String bsirdBill;

	/**
	*发生时间
	*/
	private Date bsirdCdate;

	public void setBsirdUnid(Integer bsirdUnid) {
		this.bsirdUnid = bsirdUnid;
	}

	public Integer getBsirdUnid( ) {
		return bsirdUnid;
	}

	public void setBsirdUuid(String bsirdUuid) {
		this.bsirdUuid = bsirdUuid;
	}

	public String getBsirdUuid( ) {
		return bsirdUuid;
	}

	public void setBsirdUser(String bsirdUser) {
		this.bsirdUser = bsirdUser;
	}

	public String getBsirdUser( ) {
		return bsirdUser;
	}

	public void setBsirdType(Integer bsirdType) {
		this.bsirdType = bsirdType;
	}

	public Integer getBsirdType( ) {
		return bsirdType;
	}

	public void setBsirdQuota(String bsirdQuota) {
		this.bsirdQuota = bsirdQuota;
	}

	public String getBsirdQuota( ) {
		return bsirdQuota;
	}

	public void setBsirdBill(String bsirdBill) {
		this.bsirdBill = bsirdBill;
	}

	public String getBsirdBill( ) {
		return bsirdBill;
	}

	public void setBsirdCdate(Date bsirdCdate) {
		this.bsirdCdate = bsirdCdate;
	}

	public Date getBsirdCdate( ) {
		return bsirdCdate;
	}

	public BusiIntegralRecord( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}