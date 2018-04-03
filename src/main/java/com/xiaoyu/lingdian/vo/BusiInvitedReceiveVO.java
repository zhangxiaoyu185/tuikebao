package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiInvitedReceive;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 邀请活动奖励领取表
*/
public class BusiInvitedReceiveVO implements BaseVO {

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
	*领取人昵称
	*/
	private String bsireUserName;
	
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
	private String bsireCdate;

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

	public String getBsireUserName() {
		return bsireUserName;
	}

	public void setBsireUserName(String bsireUserName) {
		this.bsireUserName = bsireUserName;
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

	public void setBsireCdate(String bsireCdate) {
		this.bsireCdate = bsireCdate;
	}

	public String getBsireCdate( ) {
		return bsireCdate;
	}

	public BusiInvitedReceiveVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiInvitedReceive po = (BusiInvitedReceive) poObj;
		this.bsireUuid = po.getBsireUuid();
		this.bsireActivity = po.getBsireActivity();
		this.bsirePeriods = po.getBsirePeriods();
		this.bsireUser = po.getBsireUser();
		this.bsireIntegral = po.getBsireIntegral();
		this.bsireRuleNumber = po.getBsireRuleNumber();
		this.bsireActivityNumber = po.getBsireActivityNumber();
		this.bsireCdate = po.getBsireCdate()!=null?DateUtil.formatDefaultDate(po.getBsireCdate()):"";
	}

}