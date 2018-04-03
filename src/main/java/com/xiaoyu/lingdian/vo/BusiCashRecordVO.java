package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiCashRecord;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 用户提现记录表
*/
public class BusiCashRecordVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bscrdUuid;

	/**
	*提取金额
	*/
	private String bscrdAmount;

	/**
	*提取人
	*/
	private String bscrdExtracted;

	/**
	*提取人账号
	*/
	private String bscrdExtractedName;

	/**
	*状态:1已申请2交易成功3交易失败
	*/
	private Integer bscrdStatus;

	/**
	*创建时间
	*/
	private String bscrdCdate;

	/**
	*更新时间
	*/
	private String bscrdUdate;

	/**
	*手机号
	*/
	private String bscrdMobile;

	/**
	*提现类型:1银行账户2支付宝
	*/
	private Integer bscrdAccountType;

	/**
	*银行账户名称
	*/
	private String bscrdAccountName;

	/**
	*银行账号
	*/
	private String bscrdAccountNo;

	/**
	*银行开户行
	*/
	private String bscrdBankName;

	/**
	*银行开户网点
	*/
	private String bscrdBankSite;

	/**
	*支付宝账户
	*/
	private String bscrdAlipayNo;

	/**
	*支付宝姓名
	*/
	private String bscrdAlipayName;

	public void setBscrdUuid(String bscrdUuid) {
		this.bscrdUuid = bscrdUuid;
	}

	public String getBscrdUuid( ) {
		return bscrdUuid;
	}

	public void setBscrdAmount(String bscrdAmount) {
		this.bscrdAmount = bscrdAmount;
	}

	public String getBscrdAmount( ) {
		return bscrdAmount;
	}

	public void setBscrdExtracted(String bscrdExtracted) {
		this.bscrdExtracted = bscrdExtracted;
	}

	public String getBscrdExtracted( ) {
		return bscrdExtracted;
	}

	public String getBscrdExtractedName() {
		return bscrdExtractedName;
	}

	public void setBscrdExtractedName(String bscrdExtractedName) {
		this.bscrdExtractedName = bscrdExtractedName;
	}

	public void setBscrdStatus(Integer bscrdStatus) {
		this.bscrdStatus = bscrdStatus;
	}

	public Integer getBscrdStatus( ) {
		return bscrdStatus;
	}

	public void setBscrdCdate(String bscrdCdate) {
		this.bscrdCdate = bscrdCdate;
	}

	public String getBscrdCdate( ) {
		return bscrdCdate;
	}

	public void setBscrdUdate(String bscrdUdate) {
		this.bscrdUdate = bscrdUdate;
	}

	public String getBscrdUdate( ) {
		return bscrdUdate;
	}

	public void setBscrdMobile(String bscrdMobile) {
		this.bscrdMobile = bscrdMobile;
	}

	public String getBscrdMobile( ) {
		return bscrdMobile;
	}

	public void setBscrdAccountType(Integer bscrdAccountType) {
		this.bscrdAccountType = bscrdAccountType;
	}

	public Integer getBscrdAccountType( ) {
		return bscrdAccountType;
	}

	public void setBscrdAccountName(String bscrdAccountName) {
		this.bscrdAccountName = bscrdAccountName;
	}

	public String getBscrdAccountName( ) {
		return bscrdAccountName;
	}

	public void setBscrdAccountNo(String bscrdAccountNo) {
		this.bscrdAccountNo = bscrdAccountNo;
	}

	public String getBscrdAccountNo( ) {
		return bscrdAccountNo;
	}

	public void setBscrdBankName(String bscrdBankName) {
		this.bscrdBankName = bscrdBankName;
	}

	public String getBscrdBankName( ) {
		return bscrdBankName;
	}

	public void setBscrdBankSite(String bscrdBankSite) {
		this.bscrdBankSite = bscrdBankSite;
	}

	public String getBscrdBankSite( ) {
		return bscrdBankSite;
	}

	public void setBscrdAlipayNo(String bscrdAlipayNo) {
		this.bscrdAlipayNo = bscrdAlipayNo;
	}

	public String getBscrdAlipayNo( ) {
		return bscrdAlipayNo;
	}

	public void setBscrdAlipayName(String bscrdAlipayName) {
		this.bscrdAlipayName = bscrdAlipayName;
	}

	public String getBscrdAlipayName( ) {
		return bscrdAlipayName;
	}

	public BusiCashRecordVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiCashRecord po = (BusiCashRecord) poObj;
		this.bscrdUuid = po.getBscrdUuid();
		this.bscrdAmount = po.getBscrdAmount();
		this.bscrdExtracted = po.getBscrdExtracted();
		this.bscrdStatus = po.getBscrdStatus();
		this.bscrdCdate = po.getBscrdCdate()!=null?DateUtil.formatDefaultDate(po.getBscrdCdate()):"";
		this.bscrdUdate = po.getBscrdUdate()!=null?DateUtil.formatDefaultDate(po.getBscrdUdate()):"";
		this.bscrdMobile = po.getBscrdMobile();
		this.bscrdAccountType = po.getBscrdAccountType();
		this.bscrdAccountName = po.getBscrdAccountName();
		this.bscrdAccountNo = po.getBscrdAccountNo();
		this.bscrdBankName = po.getBscrdBankName();
		this.bscrdBankSite = po.getBscrdBankSite();
		this.bscrdAlipayNo = po.getBscrdAlipayNo();
		this.bscrdAlipayName = po.getBscrdAlipayName();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}