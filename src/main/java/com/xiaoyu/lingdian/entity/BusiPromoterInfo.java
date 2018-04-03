package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 用户提现信息表
*/
public class BusiPromoterInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bspioUnid;

	/**
	*标识UUID
	*/
	private String bspioUuid;

	/**
	*所属用户
	*/
	private String bspioUser;

	/**
	*银行账户名称
	*/
	private String bspioAccountName;

	/**
	*银行账号
	*/
	private String bspioAccountNo;

	/**
	*银行开户行
	*/
	private String bspioBankName;

	/**
	*银行开户网点
	*/
	private String bspioBankSite;

	/**
	*支付宝账户
	*/
	private String bspioAlipayNo;

	/**
	*支付宝姓名
	*/
	private String bspioAlipayName;

	/**
	*更新时间
	*/
	private Date bspioUdate;

	public void setBspioUnid(Integer bspioUnid) {
		this.bspioUnid = bspioUnid;
	}

	public Integer getBspioUnid( ) {
		return bspioUnid;
	}

	public void setBspioUuid(String bspioUuid) {
		this.bspioUuid = bspioUuid;
	}

	public String getBspioUuid( ) {
		return bspioUuid;
	}

	public String getBspioUser() {
		return bspioUser;
	}

	public void setBspioUser(String bspioUser) {
		this.bspioUser = bspioUser;
	}

	public void setBspioAccountName(String bspioAccountName) {
		this.bspioAccountName = bspioAccountName;
	}

	public String getBspioAccountName( ) {
		return bspioAccountName;
	}

	public void setBspioAccountNo(String bspioAccountNo) {
		this.bspioAccountNo = bspioAccountNo;
	}

	public String getBspioAccountNo( ) {
		return bspioAccountNo;
	}

	public void setBspioBankName(String bspioBankName) {
		this.bspioBankName = bspioBankName;
	}

	public String getBspioBankName( ) {
		return bspioBankName;
	}

	public void setBspioBankSite(String bspioBankSite) {
		this.bspioBankSite = bspioBankSite;
	}

	public String getBspioBankSite( ) {
		return bspioBankSite;
	}

	public void setBspioAlipayNo(String bspioAlipayNo) {
		this.bspioAlipayNo = bspioAlipayNo;
	}

	public String getBspioAlipayNo( ) {
		return bspioAlipayNo;
	}

	public void setBspioAlipayName(String bspioAlipayName) {
		this.bspioAlipayName = bspioAlipayName;
	}

	public String getBspioAlipayName( ) {
		return bspioAlipayName;
	}

	public void setBspioUdate(Date bspioUdate) {
		this.bspioUdate = bspioUdate;
	}

	public Date getBspioUdate( ) {
		return bspioUdate;
	}

	public BusiPromoterInfo( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}