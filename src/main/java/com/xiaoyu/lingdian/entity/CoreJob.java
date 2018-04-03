package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 职业表
*/
public class CoreJob extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crjobUnid;

	/**
	*标识UUID
	*/
	private String crjobUuid;

	/**
	*职业
	*/
	private String crjobName;

	public void setCrjobUnid(Integer crjobUnid) {
		this.crjobUnid = crjobUnid;
	}

	public Integer getCrjobUnid( ) {
		return crjobUnid;
	}

	public void setCrjobUuid(String crjobUuid) {
		this.crjobUuid = crjobUuid;
	}

	public String getCrjobUuid( ) {
		return crjobUuid;
	}

	public void setCrjobName(String crjobName) {
		this.crjobName = crjobName;
	}

	public String getCrjobName( ) {
		return crjobName;
	}

	public CoreJob( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}