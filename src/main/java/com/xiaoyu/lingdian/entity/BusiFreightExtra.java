package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 特殊地区运费模板表
*/
public class BusiFreightExtra extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsfeaUnid;

	/**
	*标识UUID
	*/
	private String bsfeaUuid;

	/**
	*所属运费模板
	*/
	private String bsfeaFreightTemplate;

	/**
	*特殊地区
	*/
	private String bsfeaRegion;

	/**
	*初始价格
	*/
	private Double bsfeaInitialPrice;

	/**
	*加价
	*/
	private Double bsfeaStepPrice;

	/**
	*创建时间
	*/
	private Date bsfeaCdate;

	/**
	*更新时间
	*/
	private Date bsfeaUdate;

	public void setBsfeaUnid(Integer bsfeaUnid) {
		this.bsfeaUnid = bsfeaUnid;
	}

	public Integer getBsfeaUnid( ) {
		return bsfeaUnid;
	}

	public void setBsfeaUuid(String bsfeaUuid) {
		this.bsfeaUuid = bsfeaUuid;
	}

	public String getBsfeaUuid( ) {
		return bsfeaUuid;
	}

	public void setBsfeaFreightTemplate(String bsfeaFreightTemplate) {
		this.bsfeaFreightTemplate = bsfeaFreightTemplate;
	}

	public String getBsfeaFreightTemplate( ) {
		return bsfeaFreightTemplate;
	}

	public void setBsfeaRegion(String bsfeaRegion) {
		this.bsfeaRegion = bsfeaRegion;
	}

	public String getBsfeaRegion( ) {
		return bsfeaRegion;
	}

	public void setBsfeaInitialPrice(Double bsfeaInitialPrice) {
		this.bsfeaInitialPrice = bsfeaInitialPrice;
	}

	public Double getBsfeaInitialPrice( ) {
		return bsfeaInitialPrice;
	}

	public void setBsfeaStepPrice(Double bsfeaStepPrice) {
		this.bsfeaStepPrice = bsfeaStepPrice;
	}

	public Double getBsfeaStepPrice( ) {
		return bsfeaStepPrice;
	}

	public void setBsfeaCdate(Date bsfeaCdate) {
		this.bsfeaCdate = bsfeaCdate;
	}

	public Date getBsfeaCdate( ) {
		return bsfeaCdate;
	}

	public void setBsfeaUdate(Date bsfeaUdate) {
		this.bsfeaUdate = bsfeaUdate;
	}

	public Date getBsfeaUdate( ) {
		return bsfeaUdate;
	}

	public BusiFreightExtra( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}