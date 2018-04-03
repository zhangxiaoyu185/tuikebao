package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 广告位表
*/
public class BusiAdvert extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsaetUnid;

	/**
	*标识UUID
	*/
	private String bsaetUuid;

	/**
	*链接
	*/
	private String bsaetLink;

	/**
	*广告图
	*/
	private String bsaetPic;

	/**
	*顺序
	*/
	private Integer bsaetOrd;

	/**
	*状态:1启动0禁用
	*/
	private Integer bsaetStatus;

	/**
	*描述
	*/
	private String bsaetDesc;

	/**
	*创建时间
	*/
	private Date bsaetCdate;

	/**
	*更新时间
	*/
	private Date bsaetUdate;

	public void setBsaetUnid(Integer bsaetUnid) {
		this.bsaetUnid = bsaetUnid;
	}

	public Integer getBsaetUnid( ) {
		return bsaetUnid;
	}

	public void setBsaetUuid(String bsaetUuid) {
		this.bsaetUuid = bsaetUuid;
	}

	public String getBsaetUuid( ) {
		return bsaetUuid;
	}

	public void setBsaetLink(String bsaetLink) {
		this.bsaetLink = bsaetLink;
	}

	public String getBsaetLink( ) {
		return bsaetLink;
	}

	public void setBsaetPic(String bsaetPic) {
		this.bsaetPic = bsaetPic;
	}

	public String getBsaetPic( ) {
		return bsaetPic;
	}

	public void setBsaetOrd(Integer bsaetOrd) {
		this.bsaetOrd = bsaetOrd;
	}

	public Integer getBsaetOrd( ) {
		return bsaetOrd;
	}

	public void setBsaetStatus(Integer bsaetStatus) {
		this.bsaetStatus = bsaetStatus;
	}

	public Integer getBsaetStatus( ) {
		return bsaetStatus;
	}

	public void setBsaetDesc(String bsaetDesc) {
		this.bsaetDesc = bsaetDesc;
	}

	public String getBsaetDesc( ) {
		return bsaetDesc;
	}

	public void setBsaetCdate(Date bsaetCdate) {
		this.bsaetCdate = bsaetCdate;
	}

	public Date getBsaetCdate( ) {
		return bsaetCdate;
	}

	public void setBsaetUdate(Date bsaetUdate) {
		this.bsaetUdate = bsaetUdate;
	}

	public Date getBsaetUdate( ) {
		return bsaetUdate;
	}

	public BusiAdvert( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}