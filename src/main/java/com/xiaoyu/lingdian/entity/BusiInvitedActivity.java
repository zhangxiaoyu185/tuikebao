package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 邀请活动表
*/
public class BusiInvitedActivity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsiayUnid;

	/**
	*标识UUID
	*/
	private String bsiayUuid;

	/**
	*活动期数
	*/
	private String bsiayPeriods;

	/**
	*创建时间
	*/
	private Date bsiayCdate;

	public void setBsiayUnid(Integer bsiayUnid) {
		this.bsiayUnid = bsiayUnid;
	}

	public Integer getBsiayUnid( ) {
		return bsiayUnid;
	}

	public void setBsiayUuid(String bsiayUuid) {
		this.bsiayUuid = bsiayUuid;
	}

	public String getBsiayUuid( ) {
		return bsiayUuid;
	}

	public void setBsiayPeriods(String bsiayPeriods) {
		this.bsiayPeriods = bsiayPeriods;
	}

	public String getBsiayPeriods( ) {
		return bsiayPeriods;
	}

	public void setBsiayCdate(Date bsiayCdate) {
		this.bsiayCdate = bsiayCdate;
	}

	public Date getBsiayCdate( ) {
		return bsiayCdate;
	}

	public BusiInvitedActivity( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}