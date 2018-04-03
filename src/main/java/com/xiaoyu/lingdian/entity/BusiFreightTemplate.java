package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 运费模板表
*/
public class BusiFreightTemplate extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsfteUnid;

	/**
	*标识UUID
	*/
	private String bsfteUuid;

	/**
	*模板名称
	*/
	private String bsfteName;

	/**
	*初始重量
	*/
	private Integer bsfteInitialWeight;

	/**
	*初始价格
	*/
	private Double bsfteInitialPrice;

	/**
	*加重
	*/
	private Integer bsfteWeight;

	/**
	*加价
	*/
	private Double bsftePrice;

	/**
	*创建时间
	*/
	private Date bsfteCdate;

	/**
	*更新时间
	*/
	private Date bsfteUdate;

	public void setBsfteUnid(Integer bsfteUnid) {
		this.bsfteUnid = bsfteUnid;
	}

	public Integer getBsfteUnid( ) {
		return bsfteUnid;
	}

	public void setBsfteUuid(String bsfteUuid) {
		this.bsfteUuid = bsfteUuid;
	}

	public String getBsfteUuid( ) {
		return bsfteUuid;
	}

	public void setBsfteName(String bsfteName) {
		this.bsfteName = bsfteName;
	}

	public String getBsfteName( ) {
		return bsfteName;
	}

	public void setBsfteInitialWeight(Integer bsfteInitialWeight) {
		this.bsfteInitialWeight = bsfteInitialWeight;
	}

	public Integer getBsfteInitialWeight( ) {
		return bsfteInitialWeight;
	}

	public void setBsfteInitialPrice(Double bsfteInitialPrice) {
		this.bsfteInitialPrice = bsfteInitialPrice;
	}

	public Double getBsfteInitialPrice( ) {
		return bsfteInitialPrice;
	}

	public void setBsfteWeight(Integer bsfteWeight) {
		this.bsfteWeight = bsfteWeight;
	}

	public Integer getBsfteWeight( ) {
		return bsfteWeight;
	}

	public void setBsftePrice(Double bsftePrice) {
		this.bsftePrice = bsftePrice;
	}

	public Double getBsftePrice( ) {
		return bsftePrice;
	}

	public void setBsfteCdate(Date bsfteCdate) {
		this.bsfteCdate = bsfteCdate;
	}

	public Date getBsfteCdate( ) {
		return bsfteCdate;
	}

	public void setBsfteUdate(Date bsfteUdate) {
		this.bsfteUdate = bsfteUdate;
	}

	public Date getBsfteUdate( ) {
		return bsfteUdate;
	}

	public BusiFreightTemplate( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}