package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 签到设置规则表
*/
public class BusiSignRule extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bssreUnid;

	/**
	*标识UUID
	*/
	private String bssreUuid;

	/**
	*连续天数
	*/
	private Integer bssreCount;

	/**
	*可领取积分
	*/
	private Integer bssreIntegral;

	/**
	*顺序
	*/
	private Integer bssreOrd;

	public void setBssreUnid(Integer bssreUnid) {
		this.bssreUnid = bssreUnid;
	}

	public Integer getBssreUnid( ) {
		return bssreUnid;
	}

	public void setBssreUuid(String bssreUuid) {
		this.bssreUuid = bssreUuid;
	}

	public String getBssreUuid( ) {
		return bssreUuid;
	}

	public void setBssreCount(Integer bssreCount) {
		this.bssreCount = bssreCount;
	}

	public Integer getBssreCount( ) {
		return bssreCount;
	}

	public void setBssreIntegral(Integer bssreIntegral) {
		this.bssreIntegral = bssreIntegral;
	}

	public Integer getBssreIntegral( ) {
		return bssreIntegral;
	}

	public void setBssreOrd(Integer bssreOrd) {
		this.bssreOrd = bssreOrd;
	}

	public Integer getBssreOrd( ) {
		return bssreOrd;
	}

	public BusiSignRule( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}