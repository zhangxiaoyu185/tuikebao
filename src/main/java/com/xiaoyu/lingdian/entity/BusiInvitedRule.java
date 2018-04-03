package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 邀请奖励规则表
*/
public class BusiInvitedRule extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsireUnid;

	/**
	*标识UUID
	*/
	private String bsireUuid;

	/**
	*需要邀请人数
	*/
	private Integer bsireCount;

	/**
	*可领取积分
	*/
	private Integer bsireIntegral;

	/**
	*顺序
	*/
	private Integer bsireOrd;

	public void setBsireUnid(Integer bsireUnid) {
		this.bsireUnid = bsireUnid;
	}

	public Integer getBsireUnid( ) {
		return bsireUnid;
	}

	public void setBsireUuid(String bsireUuid) {
		this.bsireUuid = bsireUuid;
	}

	public String getBsireUuid( ) {
		return bsireUuid;
	}

	public void setBsireCount(Integer bsireCount) {
		this.bsireCount = bsireCount;
	}

	public Integer getBsireCount( ) {
		return bsireCount;
	}

	public void setBsireIntegral(Integer bsireIntegral) {
		this.bsireIntegral = bsireIntegral;
	}

	public Integer getBsireIntegral( ) {
		return bsireIntegral;
	}

	public void setBsireOrd(Integer bsireOrd) {
		this.bsireOrd = bsireOrd;
	}

	public Integer getBsireOrd( ) {
		return bsireOrd;
	}

	public BusiInvitedRule( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}