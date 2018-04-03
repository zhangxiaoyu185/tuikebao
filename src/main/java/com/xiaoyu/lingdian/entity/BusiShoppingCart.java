package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 购物车表
*/
public class BusiShoppingCart extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bssctUnid;

	/**
	*标识UUID
	*/
	private String bssctUuid;

	/**
	*所属用户
	*/
	private String bssctUser;

	/**
	*商品标识
	*/
	private String bssctProduct;

	/**
	*加入数量
	*/
	private Integer bssctQuantity;

	/**
	*选择商品属性组合名称（颜色：绿色尺寸：L）
	*/
	private String bssctAttrName;

	/**
	*选择商品属性组合标识(123|456)
	*/
	private String bssctAttrValue;

	/**
	*加入时价格
	*/
	private Double bssctFee;

	/**
	*标签集合（七|赠|正）
	*/
	private String bssctTags;

	/**
	*商品名称
	*/
	private String bssctName;

	/**
	*商品描述
	*/
	private String bssctDesc;

	/**
	*推客封面图
	*/
	private String bssctSharePic;

	/**
	*商城封面图
	*/
	private String bssctStorePic;

	/**
	*创建时间
	*/
	private Date bssctCdate;

	/**
	*更新时间
	*/
	private Date bssctUdate;

	public void setBssctUnid(Integer bssctUnid) {
		this.bssctUnid = bssctUnid;
	}

	public Integer getBssctUnid( ) {
		return bssctUnid;
	}

	public void setBssctUuid(String bssctUuid) {
		this.bssctUuid = bssctUuid;
	}

	public String getBssctUuid( ) {
		return bssctUuid;
	}

	public void setBssctUser(String bssctUser) {
		this.bssctUser = bssctUser;
	}

	public String getBssctUser( ) {
		return bssctUser;
	}

	public void setBssctProduct(String bssctProduct) {
		this.bssctProduct = bssctProduct;
	}

	public String getBssctProduct( ) {
		return bssctProduct;
	}

	public void setBssctQuantity(Integer bssctQuantity) {
		this.bssctQuantity = bssctQuantity;
	}

	public Integer getBssctQuantity( ) {
		return bssctQuantity;
	}

	public void setBssctAttrName(String bssctAttrName) {
		this.bssctAttrName = bssctAttrName;
	}

	public String getBssctAttrName( ) {
		return bssctAttrName;
	}

	public void setBssctAttrValue(String bssctAttrValue) {
		this.bssctAttrValue = bssctAttrValue;
	}

	public String getBssctAttrValue( ) {
		return bssctAttrValue;
	}

	public void setBssctFee(Double bssctFee) {
		this.bssctFee = bssctFee;
	}

	public Double getBssctFee( ) {
		return bssctFee;
	}

	public void setBssctTags(String bssctTags) {
		this.bssctTags = bssctTags;
	}

	public String getBssctTags( ) {
		return bssctTags;
	}

	public void setBssctName(String bssctName) {
		this.bssctName = bssctName;
	}

	public String getBssctName( ) {
		return bssctName;
	}

	public void setBssctDesc(String bssctDesc) {
		this.bssctDesc = bssctDesc;
	}

	public String getBssctDesc( ) {
		return bssctDesc;
	}

	public void setBssctSharePic(String bssctSharePic) {
		this.bssctSharePic = bssctSharePic;
	}

	public String getBssctSharePic( ) {
		return bssctSharePic;
	}

	public void setBssctStorePic(String bssctStorePic) {
		this.bssctStorePic = bssctStorePic;
	}

	public String getBssctStorePic( ) {
		return bssctStorePic;
	}

	public void setBssctCdate(Date bssctCdate) {
		this.bssctCdate = bssctCdate;
	}

	public Date getBssctCdate( ) {
		return bssctCdate;
	}

	public void setBssctUdate(Date bssctUdate) {
		this.bssctUdate = bssctUdate;
	}

	public Date getBssctUdate( ) {
		return bssctUdate;
	}

	public BusiShoppingCart( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}