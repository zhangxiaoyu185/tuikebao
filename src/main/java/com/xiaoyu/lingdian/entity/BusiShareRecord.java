package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 用户分享记录表
*/
public class BusiShareRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bssrdUnid;

	/**
	*标识UUID
	*/
	private String bssrdUuid;

	/**
	*所属用户
	*/
	private String bssrdUser;

	/**
	*分享时等级
	*/
	private String bssrdGrade;

	/**
	*单个商品佣金
	*/
	private Double bssrdCommision;

	/**
	*分享商品
	*/
	private String bssrdProduct;

	/**
	*分享商品推客封面图
	*/
	private String bssrdSharePic;

	/**
	*分享商品商城封面图
	*/
	private String bssrdStorePic;

	/**
	*分享商品名称
	*/
	private String bssrdProductName;

	/**
	*事项
	*/
	private String bssrdBill;

	/**
	*分享时间
	*/
	private Date bssrdCdate;

	/**
	*更新时间
	*/
	private Date bssrdUdate;

	/**
	*完成笔数
	*/
	private Integer bssrdPaymentCount;

	/**
	*返现收益
	*/
	private Double bssrdBackNow;

	/**
	*积分收益
	*/
	private Double bssrdIntegral;

	public void setBssrdUnid(Integer bssrdUnid) {
		this.bssrdUnid = bssrdUnid;
	}

	public Integer getBssrdUnid( ) {
		return bssrdUnid;
	}

	public void setBssrdUuid(String bssrdUuid) {
		this.bssrdUuid = bssrdUuid;
	}

	public String getBssrdUuid( ) {
		return bssrdUuid;
	}

	public void setBssrdUser(String bssrdUser) {
		this.bssrdUser = bssrdUser;
	}

	public String getBssrdUser( ) {
		return bssrdUser;
	}

	public void setBssrdGrade(String bssrdGrade) {
		this.bssrdGrade = bssrdGrade;
	}

	public String getBssrdGrade( ) {
		return bssrdGrade;
	}

	public void setBssrdCommision(Double bssrdCommision) {
		this.bssrdCommision = bssrdCommision;
	}

	public Double getBssrdCommision( ) {
		return bssrdCommision;
	}

	public void setBssrdProduct(String bssrdProduct) {
		this.bssrdProduct = bssrdProduct;
	}

	public String getBssrdProduct( ) {
		return bssrdProduct;
	}

	public void setBssrdSharePic(String bssrdSharePic) {
		this.bssrdSharePic = bssrdSharePic;
	}

	public String getBssrdSharePic( ) {
		return bssrdSharePic;
	}

	public void setBssrdStorePic(String bssrdStorePic) {
		this.bssrdStorePic = bssrdStorePic;
	}

	public String getBssrdStorePic( ) {
		return bssrdStorePic;
	}

	public void setBssrdProductName(String bssrdProductName) {
		this.bssrdProductName = bssrdProductName;
	}

	public String getBssrdProductName( ) {
		return bssrdProductName;
	}

	public void setBssrdBill(String bssrdBill) {
		this.bssrdBill = bssrdBill;
	}

	public String getBssrdBill( ) {
		return bssrdBill;
	}

	public void setBssrdCdate(Date bssrdCdate) {
		this.bssrdCdate = bssrdCdate;
	}

	public Date getBssrdCdate( ) {
		return bssrdCdate;
	}

	public void setBssrdUdate(Date bssrdUdate) {
		this.bssrdUdate = bssrdUdate;
	}

	public Date getBssrdUdate( ) {
		return bssrdUdate;
	}

	public void setBssrdPaymentCount(Integer bssrdPaymentCount) {
		this.bssrdPaymentCount = bssrdPaymentCount;
	}

	public Integer getBssrdPaymentCount( ) {
		return bssrdPaymentCount;
	}

	public void setBssrdBackNow(Double bssrdBackNow) {
		this.bssrdBackNow = bssrdBackNow;
	}

	public Double getBssrdBackNow( ) {
		return bssrdBackNow;
	}

	public void setBssrdIntegral(Double bssrdIntegral) {
		this.bssrdIntegral = bssrdIntegral;
	}

	public Double getBssrdIntegral( ) {
		return bssrdIntegral;
	}

	public BusiShareRecord( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}