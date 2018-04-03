package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 邀请关系表
*/
public class BusiInvitedRelation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsirnUnid;

	/**
	*标识UUID
	*/
	private String bsirnUuid;

	/**
	*邀请人
	*/
	private String bsirnInvited;

	/**
	*被邀请人
	*/
	private String bsirnBeInvited;

	/**
	*所用邀请码
	*/
	private String bsirnCode;

	/**
	*邀请时间
	*/
	private Date bsirnIdate;

	public void setBsirnUnid(Integer bsirnUnid) {
		this.bsirnUnid = bsirnUnid;
	}

	public Integer getBsirnUnid( ) {
		return bsirnUnid;
	}

	public void setBsirnUuid(String bsirnUuid) {
		this.bsirnUuid = bsirnUuid;
	}

	public String getBsirnUuid( ) {
		return bsirnUuid;
	}

	public void setBsirnInvited(String bsirnInvited) {
		this.bsirnInvited = bsirnInvited;
	}

	public String getBsirnInvited( ) {
		return bsirnInvited;
	}

	public void setBsirnBeInvited(String bsirnBeInvited) {
		this.bsirnBeInvited = bsirnBeInvited;
	}

	public String getBsirnBeInvited( ) {
		return bsirnBeInvited;
	}

	public void setBsirnCode(String bsirnCode) {
		this.bsirnCode = bsirnCode;
	}

	public String getBsirnCode( ) {
		return bsirnCode;
	}

	public void setBsirnIdate(Date bsirnIdate) {
		this.bsirnIdate = bsirnIdate;
	}

	public Date getBsirnIdate( ) {
		return bsirnIdate;
	}

	public BusiInvitedRelation( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}