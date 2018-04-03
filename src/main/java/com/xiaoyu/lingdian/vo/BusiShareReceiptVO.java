package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiShareReceipt;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 分享商品购买收货记录表
*/
public class BusiShareReceiptVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bssrtUuid;

	/**
	*分享用户
	*/
	private String bssrtUser;

	/**
	*分享用户账号
	*/
	private String bssrtUserName;
	
	/**
	*分享时用户等级
	*/
	private String bssrtGrade;
	
	/**
	*分享时用户等级名称
	*/
	private String bssrtGradeName;

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
	*购买用户名称
	*/
	private String bssrtBuyUserName;

	/**
	*下单子订单号
	*/
	private String bssrtOrderNo;

	/**
	*下单时间
	*/
	private String bssrtCdate;

	/**
	*确认收货时间(以这个时间来做返现)
	*/
	private String bssrtReceiptDate;

	/**
	*交易完成时间(暂时没作用)
	*/
	private String bssrtFinishDate;

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

	public void setBssrtCdate(String bssrtCdate) {
		this.bssrtCdate = bssrtCdate;
	}

	public String getBssrtCdate( ) {
		return bssrtCdate;
	}

	public void setBssrtReceiptDate(String bssrtReceiptDate) {
		this.bssrtReceiptDate = bssrtReceiptDate;
	}

	public String getBssrtReceiptDate( ) {
		return bssrtReceiptDate;
	}

	public void setBssrtFinishDate(String bssrtFinishDate) {
		this.bssrtFinishDate = bssrtFinishDate;
	}

	public String getBssrtFinishDate( ) {
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

	public String getBssrtUserName() {
		return bssrtUserName;
	}

	public void setBssrtUserName(String bssrtUserName) {
		this.bssrtUserName = bssrtUserName;
	}

	public String getBssrtGradeName() {
		return bssrtGradeName;
	}

	public void setBssrtGradeName(String bssrtGradeName) {
		this.bssrtGradeName = bssrtGradeName;
	}

	public String getBssrtBuyUserName() {
		return bssrtBuyUserName;
	}

	public void setBssrtBuyUserName(String bssrtBuyUserName) {
		this.bssrtBuyUserName = bssrtBuyUserName;
	}

	public BusiShareReceiptVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiShareReceipt po = (BusiShareReceipt) poObj;
		this.bssrtUuid = po.getBssrtUuid();
		this.bssrtUser = po.getBssrtUser();
		this.bssrtGrade = po.getBssrtGrade();
		this.bssrtProduct = po.getBssrtProduct();
		this.bssrtSharePic = po.getBssrtSharePic();
		this.bssrtStorePic = po.getBssrtStorePic();
		this.bssrtProductName = po.getBssrtProductName();
		this.bssrtBuyUser = po.getBssrtBuyUser();
		this.bssrtOrderNo = po.getBssrtOrderNo();
		this.bssrtCdate = po.getBssrtCdate()!=null?DateUtil.formatDefaultDate(po.getBssrtCdate()):"";
		this.bssrtReceiptDate = po.getBssrtReceiptDate()!=null?DateUtil.formatDefaultDate(po.getBssrtReceiptDate()):"";
		this.bssrtFinishDate = po.getBssrtFinishDate()!=null?DateUtil.formatDefaultDate(po.getBssrtFinishDate()):"";
		this.bssrtBuyCount = po.getBssrtBuyCount();
		this.bssrtBuyFee = po.getBssrtBuyFee();
		this.bssrtBackNow = po.getBssrtBackNow();
		this.bssrtStatus = po.getBssrtStatus();
	}

}