package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 服务详情页面表
*/
public class BusiServerDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bssdlUnid;

	/**
	*标识UUID
	*/
	private String bssdlUuid;

	/**
	*Title内容
	*/
	private String bssdlTitle;

	/**
	*SEO关键字
	*/
	private String bssdlSeo;

	/**
	*页面html内容
	*/
	private String bssdlHtml;

	/**
	*生成的URL路径
	*/
	private String bssdlUrl;

	/**
	*创建日期
	*/
	private Date bssdlCdate;

	/**
	*修改日期
	*/
	private Date bssdlUdate;

	/**
	*状态:1启用,0禁用
	*/
	private Integer bssdlStatus;

	public void setBssdlUnid(Integer bssdlUnid) {
		this.bssdlUnid = bssdlUnid;
	}

	public Integer getBssdlUnid( ) {
		return bssdlUnid;
	}

	public void setBssdlUuid(String bssdlUuid) {
		this.bssdlUuid = bssdlUuid;
	}

	public String getBssdlUuid( ) {
		return bssdlUuid;
	}

	public void setBssdlTitle(String bssdlTitle) {
		this.bssdlTitle = bssdlTitle;
	}

	public String getBssdlTitle( ) {
		return bssdlTitle;
	}

	public void setBssdlSeo(String bssdlSeo) {
		this.bssdlSeo = bssdlSeo;
	}

	public String getBssdlSeo( ) {
		return bssdlSeo;
	}

	public void setBssdlHtml(String bssdlHtml) {
		this.bssdlHtml = bssdlHtml;
	}

	public String getBssdlHtml( ) {
		return bssdlHtml;
	}

	public void setBssdlUrl(String bssdlUrl) {
		this.bssdlUrl = bssdlUrl;
	}

	public String getBssdlUrl( ) {
		return bssdlUrl;
	}

	public void setBssdlCdate(Date bssdlCdate) {
		this.bssdlCdate = bssdlCdate;
	}

	public Date getBssdlCdate( ) {
		return bssdlCdate;
	}

	public void setBssdlUdate(Date bssdlUdate) {
		this.bssdlUdate = bssdlUdate;
	}

	public Date getBssdlUdate( ) {
		return bssdlUdate;
	}

	public void setBssdlStatus(Integer bssdlStatus) {
		this.bssdlStatus = bssdlStatus;
	}

	public Integer getBssdlStatus( ) {
		return bssdlStatus;
	}

	public BusiServerDetail( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}