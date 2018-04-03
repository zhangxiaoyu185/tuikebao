package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiExchangeBill;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 用户积分兑换话费记录表
*/
public class BusiExchangeBillVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bseblUuid;

	/**
	*兑换用户
	*/
	private String bseblUser;

	/**
	*兑换用户名称
	*/
	private String bseblUserName;

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
	private String bseblCdate;

	/**
	*更新时间
	*/
	private String bseblUdate;

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

	public String getBseblUserName() {
		return bseblUserName;
	}

	public void setBseblUserName(String bseblUserName) {
		this.bseblUserName = bseblUserName;
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

	public void setBseblCdate(String bseblCdate) {
		this.bseblCdate = bseblCdate;
	}

	public String getBseblCdate( ) {
		return bseblCdate;
	}

	public String getBseblMobile() {
		return bseblMobile;
	}

	public void setBseblMobile(String bseblMobile) {
		this.bseblMobile = bseblMobile;
	}

	public String getBseblUdate() {
		return bseblUdate;
	}

	public void setBseblUdate(String bseblUdate) {
		this.bseblUdate = bseblUdate;
	}

	public BusiExchangeBillVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiExchangeBill po = (BusiExchangeBill) poObj;
		this.bseblUuid = po.getBseblUuid();
		this.bseblUser = po.getBseblUser();
		this.bseblMobile = po.getBseblMobile();
		this.bseblIntegral = po.getBseblIntegral();
		this.bseblBill = po.getBseblBill();
		this.bseblStatus = po.getBseblStatus();
		this.bseblCdate = po.getBseblCdate()!=null?DateUtil.formatDefaultDate(po.getBseblCdate()):"";
		this.bseblUdate = po.getBseblUdate()!=null?DateUtil.formatDefaultDate(po.getBseblUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}