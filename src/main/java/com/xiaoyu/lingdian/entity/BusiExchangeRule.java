package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 积分兑换话费设置表
*/
public class BusiExchangeRule extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bseeeUnid;

	/**
	*标识UUID
	*/
	private String bseeeUuid;

	/**
	*充值话费
	*/
	private Double bseeeBill;

	/**
	*所需积分
	*/
	private Integer bseeeIntegral;

	/**
	*显示顺序
	*/
	private Integer bseeeOrd;
	
	public void setBseeeUnid(Integer bseeeUnid) {
		this.bseeeUnid = bseeeUnid;
	}

	public Integer getBseeeUnid( ) {
		return bseeeUnid;
	}

	public void setBseeeUuid(String bseeeUuid) {
		this.bseeeUuid = bseeeUuid;
	}

	public String getBseeeUuid( ) {
		return bseeeUuid;
	}

	public void setBseeeBill(Double bseeeBill) {
		this.bseeeBill = bseeeBill;
	}

	public Double getBseeeBill( ) {
		return bseeeBill;
	}

	public void setBseeeIntegral(Integer bseeeIntegral) {
		this.bseeeIntegral = bseeeIntegral;
	}

	public Integer getBseeeIntegral( ) {
		return bseeeIntegral;
	}

	public Integer getBseeeOrd() {
		return bseeeOrd;
	}

	public void setBseeeOrd(Integer bseeeOrd) {
		this.bseeeOrd = bseeeOrd;
	}

	public BusiExchangeRule( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}