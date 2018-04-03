package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 等级配置表
*/
public class CoreGrade extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crgdeUnid;

	/**
	*标识UUID
	*/
	private String crgdeUuid;

	/**
	*等级名称
	*/
	private String crgdeName;

	/**
	*等级图标
	*/
	private String crgdeIcon;

	/**
	*等级顺序
	*/
	private Integer crgdeOrd;

	/**
	*所需积分
	*/
	private Integer crgdeIntegral;

	/**
	*每天分享次数
	*/
	private Integer crgdeDayShares;

	public void setCrgdeUnid(Integer crgdeUnid) {
		this.crgdeUnid = crgdeUnid;
	}

	public Integer getCrgdeUnid( ) {
		return crgdeUnid;
	}

	public void setCrgdeUuid(String crgdeUuid) {
		this.crgdeUuid = crgdeUuid;
	}

	public String getCrgdeUuid( ) {
		return crgdeUuid;
	}

	public void setCrgdeName(String crgdeName) {
		this.crgdeName = crgdeName;
	}

	public String getCrgdeName( ) {
		return crgdeName;
	}

	public void setCrgdeIcon(String crgdeIcon) {
		this.crgdeIcon = crgdeIcon;
	}

	public String getCrgdeIcon( ) {
		return crgdeIcon;
	}

	public void setCrgdeOrd(Integer crgdeOrd) {
		this.crgdeOrd = crgdeOrd;
	}

	public Integer getCrgdeOrd( ) {
		return crgdeOrd;
	}

	public void setCrgdeIntegral(Integer crgdeIntegral) {
		this.crgdeIntegral = crgdeIntegral;
	}

	public Integer getCrgdeIntegral( ) {
		return crgdeIntegral;
	}

	public void setCrgdeDayShares(Integer crgdeDayShares) {
		this.crgdeDayShares = crgdeDayShares;
	}

	public Integer getCrgdeDayShares( ) {
		return crgdeDayShares;
	}

	public CoreGrade( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}