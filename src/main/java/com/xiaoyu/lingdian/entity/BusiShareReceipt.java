package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 分享商品购买收货记录表
*/
public class BusiShareReceipt extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bssrtUnid;

	/**
	*标识UUID
	*/
	private String bssrtUuid;

	/**
	*分享用户
	*/
	private String bssrtUser;

	/**
	*分享时用户等级
	*/
	private String bssrtGrade;

	/**
	*分享商品
	*/
	private String bssrtProduct;

	/**
	*分享商品推客封面图
	*/
	private String bssrtSharePic;

	/**
	*分享商品商城封面图
	*/
	private String bssrtStorePic;

	/**
	*分享商品名称
	*/
	private String bssrtProductName;

	/**
	*购买用户
	*/
	private String bssrtBuyUser;

	/**
	*下单子订单号
	*/
	private String bssrtOrderNo;

	/**
	*下单时间
	*/
	private Date bssrtCdate;

	/**
	*确认收货时间(以这个时间来做返现)
	*/
	private Date bssrtReceiptDate;

	/**
	*交易完成时间(暂时没作用)
	*/
	private Date bssrtFinishDate;

	/**
	*购买数量
	*/
	private Integer bssrtBuyCount;

	/**
	*购买金额
	*/
	private Double bssrtBuyFee;

	/**
	*返现收益
	*/
	private Double bssrtBackNow;

	/**
	*成交状态：1客户下单2确认收货3交易完成
	*/
	private Integer bssrtStatus;

	public void setBssrtUnid(Integer bssrtUnid) {
		this.bssrtUnid = bssrtUnid;
	}

	public Integer getBssrtUnid( ) {
		return bssrtUnid;
	}

	public void setBssrtUuid(String bssrtUuid) {
		this.bssrtUuid = bssrtUuid;
	}

	public String getBssrtUuid( ) {
		return bssrtUuid;
	}

	public void setBssrtUser(String bssrtUser) {
		this.bssrtUser = bssrtUser;
	}

	public String getBssrtUser( ) {
		return bssrtUser;
	}

	public void setBssrtGrade(String bssrtGrade) {
		this.bssrtGrade = bssrtGrade;
	}

	public String getBssrtGrade( ) {
		return bssrtGrade;
	}

	public void setBssrtProduct(String bssrtProduct) {
		this.bssrtProduct = bssrtProduct;
	}

	public String getBssrtProduct( ) {
		return bssrtProduct;
	}

	public void setBssrtSharePic(String bssrtSharePic) {
		this.bssrtSharePic = bssrtSharePic;
	}

	public String getBssrtSharePic( ) {
		return bssrtSharePic;
	}

	public void setBssrtStorePic(String bssrtStorePic) {
		this.bssrtStorePic = bssrtStorePic;
	}

	public String getBssrtStorePic( ) {
		return bssrtStorePic;
	}

	public void setBssrtProductName(String bssrtProductName) {
		this.bssrtProductName = bssrtProductName;
	}

	public String getBssrtProductName( ) {
		return bssrtProductName;
	}

	public void setBssrtBuyUser(String bssrtBuyUser) {
		this.bssrtBuyUser = bssrtBuyUser;
	}

	public String getBssrtBuyUser( ) {
		return bssrtBuyUser;
	}

	public void setBssrtOrderNo(String bssrtOrderNo) {
		this.bssrtOrderNo = bssrtOrderNo;
	}

	public String getBssrtOrderNo( ) {
		return bssrtOrderNo;
	}

	public void setBssrtCdate(Date bssrtCdate) {
		this.bssrtCdate = bssrtCdate;
	}

	public Date getBssrtCdate( ) {
		return bssrtCdate;
	}

	public void setBssrtReceiptDate(Date bssrtReceiptDate) {
		this.bssrtReceiptDate = bssrtReceiptDate;
	}

	public Date getBssrtReceiptDate( ) {
		return bssrtReceiptDate;
	}

	public void setBssrtFinishDate(Date bssrtFinishDate) {
		this.bssrtFinishDate = bssrtFinishDate;
	}

	public Date getBssrtFinishDate( ) {
		return bssrtFinishDate;
	}

	public void setBssrtBuyCount(Integer bssrtBuyCount) {
		this.bssrtBuyCount = bssrtBuyCount;
	}

	public Integer getBssrtBuyCount( ) {
		return bssrtBuyCount;
	}

	public void setBssrtBuyFee(Double bssrtBuyFee) {
		this.bssrtBuyFee = bssrtBuyFee;
	}

	public Double getBssrtBuyFee( ) {
		return bssrtBuyFee;
	}

	public void setBssrtBackNow(Double bssrtBackNow) {
		this.bssrtBackNow = bssrtBackNow;
	}

	public Double getBssrtBackNow( ) {
		return bssrtBackNow;
	}

	public void setBssrtStatus(Integer bssrtStatus) {
		this.bssrtStatus = bssrtStatus;
	}

	public Integer getBssrtStatus( ) {
		return bssrtStatus;
	}

	public BusiShareReceipt( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}