package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 模板消息日志记录表
*/
public class CoreTemplateMessage extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crtmeUnid;

	/**
	*标识UUID
	*/
	private String crtmeUuid;

	/**
	*所属用户
	*/
	private String crtmeUser;

	/**
	*消息内容
	*/
	private String crtmeContent;

	/**
	*发送时间
	*/
	private Date crtmeTime;

	/**
	*发送状态:1成功,0失败
	*/
	private Integer crtmeStatus;

	/**
	*模板消息类型
	*/
	private Integer crtmeType;

	/**
	*模板消息标识
	*/
	private String crtmeSign;

	public void setCrtmeUnid(Integer crtmeUnid) {
		this.crtmeUnid = crtmeUnid;
	}

	public Integer getCrtmeUnid( ) {
		return crtmeUnid;
	}

	public void setCrtmeUuid(String crtmeUuid) {
		this.crtmeUuid = crtmeUuid;
	}

	public String getCrtmeUuid( ) {
		return crtmeUuid;
	}

	public void setCrtmeUser(String crtmeUser) {
		this.crtmeUser = crtmeUser;
	}

	public String getCrtmeUser( ) {
		return crtmeUser;
	}

	public void setCrtmeContent(String crtmeContent) {
		this.crtmeContent = crtmeContent;
	}

	public String getCrtmeContent( ) {
		return crtmeContent;
	}

	public void setCrtmeTime(Date crtmeTime) {
		this.crtmeTime = crtmeTime;
	}

	public Date getCrtmeTime( ) {
		return crtmeTime;
	}

	public void setCrtmeStatus(Integer crtmeStatus) {
		this.crtmeStatus = crtmeStatus;
	}

	public Integer getCrtmeStatus( ) {
		return crtmeStatus;
	}

	public void setCrtmeType(Integer crtmeType) {
		this.crtmeType = crtmeType;
	}

	public Integer getCrtmeType( ) {
		return crtmeType;
	}

	public void setCrtmeSign(String crtmeSign) {
		this.crtmeSign = crtmeSign;
	}

	public String getCrtmeSign( ) {
		return crtmeSign;
	}

	public CoreTemplateMessage( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}