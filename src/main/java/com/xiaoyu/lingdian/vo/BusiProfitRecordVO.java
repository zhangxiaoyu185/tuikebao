package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiProfitRecord;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 用户收益明细表
*/
public class BusiProfitRecordVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bsprdUuid;

	/**
	*所属用户
	*/
	private String bsprdUser;

	/**
	*所属用户
	*/
	private String bsprdUserName;

	/**
	*收益类型:1提现2返现
	*/
	private Integer bsprdType;

	/**
	*额度
	*/
	private String bsprdQuota;

	/**
	*事项
	*/
	private String bsprdBill;

	/**
	*发生时间
	*/
	private String bsprdCdate;

	public void setBsprdUuid(String bsprdUuid) {
		this.bsprdUuid = bsprdUuid;
	}

	public String getBsprdUuid( ) {
		return bsprdUuid;
	}

	public void setBsprdUser(String bsprdUser) {
		this.bsprdUser = bsprdUser;
	}

	public String getBsprdUser( ) {
		return bsprdUser;
	}

	public void setBsprdType(Integer bsprdType) {
		this.bsprdType = bsprdType;
	}

	public Integer getBsprdType( ) {
		return bsprdType;
	}

	public void setBsprdQuota(String bsprdQuota) {
		this.bsprdQuota = bsprdQuota;
	}

	public String getBsprdQuota( ) {
		return bsprdQuota;
	}

	public void setBsprdBill(String bsprdBill) {
		this.bsprdBill = bsprdBill;
	}

	public String getBsprdBill( ) {
		return bsprdBill;
	}

	public void setBsprdCdate(String bsprdCdate) {
		this.bsprdCdate = bsprdCdate;
	}

	public String getBsprdCdate( ) {
		return bsprdCdate;
	}

	public String getBsprdUserName() {
		return bsprdUserName;
	}

	public void setBsprdUserName(String bsprdUserName) {
		this.bsprdUserName = bsprdUserName;
	}

	public BusiProfitRecordVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiProfitRecord po = (BusiProfitRecord) poObj;
		this.bsprdUuid = po.getBsprdUuid();
		this.bsprdUser = po.getBsprdUser();
		this.bsprdType = po.getBsprdType();
		this.bsprdQuota = po.getBsprdQuota();
		this.bsprdBill = po.getBsprdBill();
		this.bsprdCdate = po.getBsprdCdate()!=null?DateUtil.formatDefaultDate(po.getBsprdCdate()):"";
	}

}