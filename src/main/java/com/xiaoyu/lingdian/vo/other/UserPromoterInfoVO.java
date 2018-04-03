package com.xiaoyu.lingdian.vo.other;

import com.xiaoyu.lingdian.entity.BusiPromoterInfo;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 用户提现信息表
*/
public class UserPromoterInfoVO implements BaseVO {

	/**
	*账户手机号
	*/
	private String mobileCode;
	
	/**
	*账户可用收益
	*/
	private Double availableFee;

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

	public String getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	public Double getAvailableFee() {
		return availableFee;
	}

	public void setAvailableFee(Double availableFee) {
		this.availableFee = availableFee;
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

	public UserPromoterInfoVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiPromoterInfo po = (BusiPromoterInfo) poObj;
		this.bspioUser = po.getBspioUser();
		this.bspioAccountName = po.getBspioAccountName();
		this.bspioAccountNo = po.getBspioAccountNo();
		this.bspioBankName = po.getBspioBankName();
		this.bspioBankSite = po.getBspioBankSite();
		this.bspioAlipayNo = po.getBspioAlipayNo();
		this.bspioAlipayName = po.getBspioAlipayName();
	}

}