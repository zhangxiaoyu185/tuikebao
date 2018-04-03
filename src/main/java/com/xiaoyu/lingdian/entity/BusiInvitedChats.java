package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 邀请排行榜表
*/
public class BusiInvitedChats extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsicsUnid;

	/**
	*标识UUID
	*/
	private String bsicsUuid;

	/**
	*期数
	*/
	private String bsicsPeriods;

	/**
	*状态:1启动0禁用
	*/
	private Integer bsicsStatus;
	
	/**
	*金牌事项
	*/
	private String bsicsGoldMatters;

	/**
	*金牌邀请人数
	*/
	private Integer bsicsGoldNumber;

	/**
	*金牌奖励
	*/
	private String bsicsGoldReward;

	/**
	*金牌获得者
	*/
	private String bsicsGoldGainer;

	/**
	*银牌事项
	*/
	private String bsicsSilverMatters;

	/**
	*银牌邀请人数
	*/
	private Integer bsicsSilverNumber;

	/**
	*银牌奖励
	*/
	private String bsicsSilverReward;

	/**
	*银牌获得者
	*/
	private String bsicsSilverGainer;

	/**
	*铜牌事项
	*/
	private String bsicsBronzeMatters;

	/**
	*铜牌邀请人数
	*/
	private Integer bsicsBronzeNumber;

	/**
	*铜牌奖励
	*/
	private String bsicsBronzeReward;

	/**
	*铜牌获得者
	*/
	private String bsicsBronzeGainer;

	public void setBsicsUnid(Integer bsicsUnid) {
		this.bsicsUnid = bsicsUnid;
	}

	public Integer getBsicsUnid( ) {
		return bsicsUnid;
	}

	public void setBsicsUuid(String bsicsUuid) {
		this.bsicsUuid = bsicsUuid;
	}

	public String getBsicsUuid( ) {
		return bsicsUuid;
	}

	public void setBsicsPeriods(String bsicsPeriods) {
		this.bsicsPeriods = bsicsPeriods;
	}

	public String getBsicsPeriods( ) {
		return bsicsPeriods;
	}
	public Integer getBsicsStatus() {
		return bsicsStatus;
	}

	public void setBsicsStatus(Integer bsicsStatus) {
		this.bsicsStatus = bsicsStatus;
	}

	public void setBsicsGoldMatters(String bsicsGoldMatters) {
		this.bsicsGoldMatters = bsicsGoldMatters;
	}

	public String getBsicsGoldMatters( ) {
		return bsicsGoldMatters;
	}

	public void setBsicsGoldNumber(Integer bsicsGoldNumber) {
		this.bsicsGoldNumber = bsicsGoldNumber;
	}

	public Integer getBsicsGoldNumber( ) {
		return bsicsGoldNumber;
	}

	public void setBsicsGoldReward(String bsicsGoldReward) {
		this.bsicsGoldReward = bsicsGoldReward;
	}

	public String getBsicsGoldReward( ) {
		return bsicsGoldReward;
	}

	public void setBsicsGoldGainer(String bsicsGoldGainer) {
		this.bsicsGoldGainer = bsicsGoldGainer;
	}

	public String getBsicsGoldGainer( ) {
		return bsicsGoldGainer;
	}

	public void setBsicsSilverMatters(String bsicsSilverMatters) {
		this.bsicsSilverMatters = bsicsSilverMatters;
	}

	public String getBsicsSilverMatters( ) {
		return bsicsSilverMatters;
	}

	public void setBsicsSilverNumber(Integer bsicsSilverNumber) {
		this.bsicsSilverNumber = bsicsSilverNumber;
	}

	public Integer getBsicsSilverNumber( ) {
		return bsicsSilverNumber;
	}

	public void setBsicsSilverReward(String bsicsSilverReward) {
		this.bsicsSilverReward = bsicsSilverReward;
	}

	public String getBsicsSilverReward( ) {
		return bsicsSilverReward;
	}

	public void setBsicsSilverGainer(String bsicsSilverGainer) {
		this.bsicsSilverGainer = bsicsSilverGainer;
	}

	public String getBsicsSilverGainer( ) {
		return bsicsSilverGainer;
	}

	public void setBsicsBronzeMatters(String bsicsBronzeMatters) {
		this.bsicsBronzeMatters = bsicsBronzeMatters;
	}

	public String getBsicsBronzeMatters( ) {
		return bsicsBronzeMatters;
	}

	public void setBsicsBronzeNumber(Integer bsicsBronzeNumber) {
		this.bsicsBronzeNumber = bsicsBronzeNumber;
	}

	public Integer getBsicsBronzeNumber( ) {
		return bsicsBronzeNumber;
	}

	public void setBsicsBronzeReward(String bsicsBronzeReward) {
		this.bsicsBronzeReward = bsicsBronzeReward;
	}

	public String getBsicsBronzeReward( ) {
		return bsicsBronzeReward;
	}

	public void setBsicsBronzeGainer(String bsicsBronzeGainer) {
		this.bsicsBronzeGainer = bsicsBronzeGainer;
	}

	public String getBsicsBronzeGainer( ) {
		return bsicsBronzeGainer;
	}

	public BusiInvitedChats( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}