package com.xiaoyu.lingdian.vo.other;

public class OrderPayItem {

	/**
	*商品标识
	*/
	private String bsoimProduct;

	/**
	*商品名称
	*/
	private String bsoimProductName;

	/**
	*推客封面图
	*/
	private String bsoimSharePic;

	/**
	*商城封面图
	*/
	private String bsoimStorePic;

	/**
	*选择商品属性组合名称（颜色：绿色尺寸：L）
	*/
	private String bsoimAttrName;

	/**
	*选择商品属性组合标识(123|456)
	*/
	private String bsoimAttrValue;

	/**
	*标签集合（七|赠|正）
	*/
	private String bsoimTags;

	/**
	*购买数量
	*/
	private Integer bsoimQuantity;

	/**
	*快递费用
	*/
	private Double bsoimExpress;

	/**
	*是否虚拟商品:1是0否
	*/
	private Integer bsoimIsBom;
	
	/**
	*明细金额
	*/
	private Double bsoimValue;

	/**
	*商品单价
	*/
	private Double bsoimPrice;

	/**
	*买家留言
	*/
	private String bsoimBuyerMemo;

	/**
	*分享人Uuid
	*/
	private String shareUserUuid;
	
	public String getBsoimProduct() {
		return bsoimProduct;
	}

	public void setBsoimProduct(String bsoimProduct) {
		this.bsoimProduct = bsoimProduct;
	}

	public String getBsoimProductName() {
		return bsoimProductName;
	}

	public void setBsoimProductName(String bsoimProductName) {
		this.bsoimProductName = bsoimProductName;
	}

	public String getBsoimSharePic() {
		return bsoimSharePic;
	}

	public void setBsoimSharePic(String bsoimSharePic) {
		this.bsoimSharePic = bsoimSharePic;
	}

	public String getBsoimStorePic() {
		return bsoimStorePic;
	}

	public void setBsoimStorePic(String bsoimStorePic) {
		this.bsoimStorePic = bsoimStorePic;
	}

	public String getBsoimAttrName() {
		return bsoimAttrName;
	}

	public void setBsoimAttrName(String bsoimAttrName) {
		this.bsoimAttrName = bsoimAttrName;
	}

	public String getBsoimAttrValue() {
		return bsoimAttrValue;
	}

	public void setBsoimAttrValue(String bsoimAttrValue) {
		this.bsoimAttrValue = bsoimAttrValue;
	}

	public String getBsoimTags() {
		return bsoimTags;
	}

	public void setBsoimTags(String bsoimTags) {
		this.bsoimTags = bsoimTags;
	}

	public Integer getBsoimQuantity() {
		return bsoimQuantity;
	}

	public void setBsoimQuantity(Integer bsoimQuantity) {
		this.bsoimQuantity = bsoimQuantity;
	}

	public Double getBsoimExpress() {
		return bsoimExpress;
	}

	public void setBsoimExpress(Double bsoimExpress) {
		this.bsoimExpress = bsoimExpress;
	}

	public Integer getBsoimIsBom() {
		return bsoimIsBom;
	}

	public void setBsoimIsBom(Integer bsoimIsBom) {
		this.bsoimIsBom = bsoimIsBom;
	}

	public Double getBsoimValue() {
		return bsoimValue;
	}

	public void setBsoimValue(Double bsoimValue) {
		this.bsoimValue = bsoimValue;
	}

	public Double getBsoimPrice() {
		return bsoimPrice;
	}

	public void setBsoimPrice(Double bsoimPrice) {
		this.bsoimPrice = bsoimPrice;
	}

	public String getBsoimBuyerMemo() {
		return bsoimBuyerMemo;
	}

	public void setBsoimBuyerMemo(String bsoimBuyerMemo) {
		this.bsoimBuyerMemo = bsoimBuyerMemo;
	}

	public String getShareUserUuid() {
		return shareUserUuid;
	}

	public void setShareUserUuid(String shareUserUuid) {
		this.shareUserUuid = shareUserUuid;
	}

}
