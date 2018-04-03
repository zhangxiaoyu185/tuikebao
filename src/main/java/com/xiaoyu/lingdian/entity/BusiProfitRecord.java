package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 用户收益明细表
*/
public class BusiProfitRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsprdUnid;

	/**
	*标识UUID
	*/
	private String bsprdUuid;

	/**
	*所属用户
	*/
	private String bsprdUser;

	/**
	*收益类型:1提现2返现
	*/
	private Integer bsprdType;

	/**
	*额度
	*/
	private String bsprdQuota;

	/**
	*事项
	*/
	private String bsprdBill;

	/**
	*发生时间
	*/
	private Date bsprdCdate;

	public void setBsprdUnid(Integer bsprdUnid) {
		this.bsprdUnid = bsprdUnid;
	}

	public Integer getBsprdUnid( ) {
		return bsprdUnid;
	}

	public void setBsprdUuid(String bsprdUuid) {
		this.bsprdUuid = bsprdUuid;
	}

	public String getBsprdUuid( ) {
		return bsprdUuid;
	}

	public void setBsprdUser(String bsprdUser) {
		this.bsprdUser = bsprdUser;
	}

	public String getBsprdUser( ) {
		return bsprdUser;
	}

	public void setBsprdType(Integer bsprdType) {
		this.bsprdType = bsprdType;
	}

	public Integer getBsprdType( ) {
		return bsprdType;
	}

	public void setBsprdQuota(String bsprdQuota) {
		this.bsprdQuota = bsprdQuota;
	}

	public String getBsprdQuota( ) {
		return bsprdQuota;
	}

	public void setBsprdBill(String bsprdBill) {
		this.bsprdBill = bsprdBill;
	}

	public String getBsprdBill( ) {
		return bsprdBill;
	}

	public void setBsprdCdate(Date bsprdCdate) {
		this.bsprdCdate = bsprdCdate;
	}

	public Date getBsprdCdate( ) {
		return bsprdCdate;
	}

	public BusiProfitRecord( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}