package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 用户积分兑换话费记录表
*/
public class BusiExchangeBill extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bseblUnid;

	/**
	*标识UUID
	*/
	private String bseblUuid;

	/**
	*兑换用户
	*/
	private String bseblUser;

	/**
	*兑换号码
	*/
	private String bseblMobile;

	/**
	*消耗积分
	*/
	private String bseblIntegral;

	/**
	*兑换话费
	*/
	private String bseblBill;

	/**
	*状态:1已充值,0待充值
	*/
	private Integer bseblStatus;

	/**
	*兑换时间
	*/
	private Date bseblCdate;

	/**
	*更新时间
	*/
	private Date bseblUdate;

	public void setBseblUnid(Integer bseblUnid) {
		this.bseblUnid = bseblUnid;
	}

	public Integer getBseblUnid( ) {
		return bseblUnid;
	}

	public void setBseblUuid(String bseblUuid) {
		this.bseblUuid = bseblUuid;
	}

	public String getBseblUuid( ) {
		return bseblUuid;
	}

	public void setBseblUser(String bseblUser) {
		this.bseblUser = bseblUser;
	}

	public String getBseblUser( ) {
		return bseblUser;
	}

	public void setBseblIntegral(String bseblIntegral) {
		this.bseblIntegral = bseblIntegral;
	}

	public String getBseblIntegral( ) {
		return bseblIntegral;
	}

	public void setBseblBill(String bseblBill) {
		this.bseblBill = bseblBill;
	}

	public String getBseblBill( ) {
		return bseblBill;
	}

	public void setBseblStatus(Integer bseblStatus) {
		this.bseblStatus = bseblStatus;
	}

	public Integer getBseblStatus( ) {
		return bseblStatus;
	}

	public void setBseblCdate(Date bseblCdate) {
		this.bseblCdate = bseblCdate;
	}

	public Date getBseblCdate( ) {
		return bseblCdate;
	}

	public String getBseblMobile() {
		return bseblMobile;
	}

	public void setBseblMobile(String bseblMobile) {
		this.bseblMobile = bseblMobile;
	}

	public Date getBseblUdate() {
		return bseblUdate;
	}

	public void setBseblUdate(Date bseblUdate) {
		this.bseblUdate = bseblUdate;
	}

	public BusiExchangeBill( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}