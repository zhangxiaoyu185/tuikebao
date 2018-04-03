package com.xiaoyu.lingdian.vo.other;

public class OrderPay {

	/**
	*所属用户
	*/
	private String bsorrUser;

	/**
	*商品总价
	*/
	private Double bsorrProductValue;

	/**
	*运费总价
	*/
	private Double bsorrExpressValue;

	/**
	*订单总价
	*/
	private Double bsorrOrderValue;

	/**
	*实际支付
	*/
	private Double bsorrActualPay;

	/**
	*合计支付金额(实付款)
	*/
	private Double bsorrTotalValue;
	
	/**
	*商品总数
	*/
	private Integer bsorrTotalQuantity;
	
	/**
	*收货人姓名
	*/
	private String bsorrName;

	/**
	*收货人身份证号
	*/
	private String bsorrIdCard;

	/**
	*收货人联系方式
	*/
	private String bsorrMobile;

	/**
	*邮政编码
	*/
	private String bsorrZipCode;

	/**
	*所在省
	*/
	private String bsorrProvince;

	/**
	*所在省名称
	*/
	private String bsorrProvinceName;

	/**
	*所在市
	*/
	private String bsorrCity;

	/**
	*所在市名称
	*/
	private String bsorrCityName;

	/**
	*所在区
	*/
	private String bsorrCounty;

	/**
	*所在区名称
	*/
	private String bsorrCountyName;

	/**
	*详细地址
	*/
	private String bsorrAddress;
	
	/**
	 * 支付订单明细
	 */
	private String orderPayItemStr;

	public String getBsorrUser() {
		return bsorrUser;
	}

	public void setBsorrUser(String bsorrUser) {
		this.bsorrUser = bsorrUser;
	}

	public Double getBsorrProductValue() {
		return bsorrProductValue;
	}

	public void setBsorrProductValue(Double bsorrProductValue) {
		this.bsorrProductValue = bsorrProductValue;
	}

	public Double getBsorrExpressValue() {
		return bsorrExpressValue;
	}

	public void setBsorrExpressValue(Double bsorrExpressValue) {
		this.bsorrExpressValue = bsorrExpressValue;
	}

	public Double getBsorrOrderValue() {
		return bsorrOrderValue;
	}

	public void setBsorrOrderValue(Double bsorrOrderValue) {
		this.bsorrOrderValue = bsorrOrderValue;
	}

	public Double getBsorrActualPay() {
		return bsorrActualPay;
	}

	public void setBsorrActualPay(Double bsorrActualPay) {
		this.bsorrActualPay = bsorrActualPay;
	}

	public Double getBsorrTotalValue() {
		return bsorrTotalValue;
	}

	public void setBsorrTotalValue(Double bsorrTotalValue) {
		this.bsorrTotalValue = bsorrTotalValue;
	}

	public Integer getBsorrTotalQuantity() {
		return bsorrTotalQuantity;
	}

	public void setBsorrTotalQuantity(Integer bsorrTotalQuantity) {
		this.bsorrTotalQuantity = bsorrTotalQuantity;
	}

	public String getBsorrName() {
		return bsorrName;
	}

	public void setBsorrName(String bsorrName) {
		this.bsorrName = bsorrName;
	}

	public String getBsorrIdCard() {
		return bsorrIdCard;
	}

	public void setBsorrIdCard(String bsorrIdCard) {
		this.bsorrIdCard = bsorrIdCard;
	}

	public String getBsorrMobile() {
		return bsorrMobile;
	}

	public void setBsorrMobile(String bsorrMobile) {
		this.bsorrMobile = bsorrMobile;
	}

	public String getBsorrZipCode() {
		return bsorrZipCode;
	}

	public void setBsorrZipCode(String bsorrZipCode) {
		this.bsorrZipCode = bsorrZipCode;
	}

	public String getBsorrProvince() {
		return bsorrProvince;
	}

	public void setBsorrProvince(String bsorrProvince) {
		this.bsorrProvince = bsorrProvince;
	}

	public String getBsorrProvinceName() {
		return bsorrProvinceName;
	}

	public void setBsorrProvinceName(String bsorrProvinceName) {
		this.bsorrProvinceName = bsorrProvinceName;
	}

	public String getBsorrCity() {
		return bsorrCity;
	}

	public void setBsorrCity(String bsorrCity) {
		this.bsorrCity = bsorrCity;
	}

	public String getBsorrCityName() {
		return bsorrCityName;
	}

	public void setBsorrCityName(String bsorrCityName) {
		this.bsorrCityName = bsorrCityName;
	}

	public String getBsorrCounty() {
		return bsorrCounty;
	}

	public void setBsorrCounty(String bsorrCounty) {
		this.bsorrCounty = bsorrCounty;
	}

	public String getBsorrCountyName() {
		return bsorrCountyName;
	}

	public void setBsorrCountyName(String bsorrCountyName) {
		this.bsorrCountyName = bsorrCountyName;
	}

	public String getBsorrAddress() {
		return bsorrAddress;
	}

	public void setBsorrAddress(String bsorrAddress) {
		this.bsorrAddress = bsorrAddress;
	}

	public String getOrderPayItemStr() {
		return orderPayItemStr;
	}

	public void setOrderPayItemStr(String orderPayItemStr) {
		this.orderPayItemStr = orderPayItemStr;
	}

	public OrderPay() {
	}

}
