package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 邀请活动奖励领取表
*/
public class BusiInvitedReceive extends BaseEntity {

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
	*活动期数标识
	*/
	private String bsireActivity;

	/**
	*活动期数
	*/
	private String bsirePeriods;

	/**
	*领取人
	*/
	private String bsireUser;

	/**
	*领取积分
	*/
	private Integer bsireIntegral;

	/**
	*规则邀请人数
	*/
	private Integer bsireRuleNumber;

	/**
	*活动邀请人数
	*/
	private Integer bsireActivityNumber;

	/**
	*领取时间
	*/
	private Date bsireCdate;

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

	public void setBsireActivity(String bsireActivity) {
		this.bsireActivity = bsireActivity;
	}

	public String getBsireActivity( ) {
		return bsireActivity;
	}

	public void setBsirePeriods(String bsirePeriods) {
		this.bsirePeriods = bsirePeriods;
	}

	public String getBsirePeriods( ) {
		return bsirePeriods;
	}

	public void setBsireUser(String bsireUser) {
		this.bsireUser = bsireUser;
	}

	public String getBsireUser( ) {
		return bsireUser;
	}

	public void setBsireIntegral(Integer bsireIntegral) {
		this.bsireIntegral = bsireIntegral;
	}

	public Integer getBsireIntegral( ) {
		return bsireIntegral;
	}

	public void setBsireRuleNumber(Integer bsireRuleNumber) {
		this.bsireRuleNumber = bsireRuleNumber;
	}

	public Integer getBsireRuleNumber( ) {
		return bsireRuleNumber;
	}

	public void setBsireActivityNumber(Integer bsireActivityNumber) {
		this.bsireActivityNumber = bsireActivityNumber;
	}

	public Integer getBsireActivityNumber( ) {
		return bsireActivityNumber;
	}

	public void setBsireCdate(Date bsireCdate) {
		this.bsireCdate = bsireCdate;
	}

	public Date getBsireCdate( ) {
		return bsireCdate;
	}

	public BusiInvitedReceive( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}