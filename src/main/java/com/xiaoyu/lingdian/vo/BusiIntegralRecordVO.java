package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiIntegralRecord;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 用户积分明细表
*/
public class BusiIntegralRecordVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bsirdUuid;

	/**
	*所属用户
	*/
	private String bsirdUser;

	/**
	*所属用户名称
	*/
	private String bsirdUserName;

	/**
	*积分类型:1获得2消耗
	*/
	private Integer bsirdType;

	/**
	*额度
	*/
	private String bsirdQuota;

	/**
	*事项
	*/
	private String bsirdBill;

	/**
	*发生时间
	*/
	private String bsirdCdate;

	public void setBsirdUuid(String bsirdUuid) {
		this.bsirdUuid = bsirdUuid;
	}

	public String getBsirdUuid( ) {
		return bsirdUuid;
	}

	public void setBsirdUser(String bsirdUser) {
		this.bsirdUser = bsirdUser;
	}

	public String getBsirdUser( ) {
		return bsirdUser;
	}

	public String getBsirdUserName() {
		return bsirdUserName;
	}

	public void setBsirdUserName(String bsirdUserName) {
		this.bsirdUserName = bsirdUserName;
	}

	public void setBsirdType(Integer bsirdType) {
		this.bsirdType = bsirdType;
	}

	public Integer getBsirdType( ) {
		return bsirdType;
	}

	public void setBsirdQuota(String bsirdQuota) {
		this.bsirdQuota = bsirdQuota;
	}

	public String getBsirdQuota( ) {
		return bsirdQuota;
	}

	public void setBsirdBill(String bsirdBill) {
		this.bsirdBill = bsirdBill;
	}

	public String getBsirdBill( ) {
		return bsirdBill;
	}

	public void setBsirdCdate(String bsirdCdate) {
		this.bsirdCdate = bsirdCdate;
	}

	public String getBsirdCdate( ) {
		return bsirdCdate;
	}

	public BusiIntegralRecordVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiIntegralRecord po = (BusiIntegralRecord) poObj;
		this.bsirdUuid = po.getBsirdUuid();
		this.bsirdUser = po.getBsirdUser();
		this.bsirdType = po.getBsirdType();
		this.bsirdQuota = po.getBsirdQuota();
		this.bsirdBill = po.getBsirdBill();
		this.bsirdCdate = po.getBsirdCdate()!=null?DateUtil.formatDefaultDate(po.getBsirdCdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}