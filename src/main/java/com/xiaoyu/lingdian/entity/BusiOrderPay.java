package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 订单支付记录表
*/
public class BusiOrderPay extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsopyUnid;

	/**
	*标识UUID
	*/
	private String bsopyUuid;

	/**
	*订单标识
	*/
	private String bsopyOrder;

	/**
	*客户端地址
	*/
	private String bsopyClientIp;

	/**
	*支付渠道
	*/
	private String bsopyPayChannel;

	/**
	*交易类型
	*/
	private String bsopyTradeType;

	/**
	*用户标识
	*/
	private String bsopyUser;

	/**
	*同步通知地址
	*/
	private String bsopyReturnUrl;

	/**
	*支付返回参数（返回用于前端页面支付参数）
	*/
	private String bsopyPayParams;

	/**
	*业务结果
	*/
	private String bsopyResultCode;

	/**
	*错误代码
	*/
	private String bsopyErrorCode;

	/**
	*错误描述
	*/
	private String bsopyErrorMsg;

	/**
	*第三方单号
	*/
	private String bsopyOutTradeNo;

	/**
	*支付结果
	*/
	private String bsopyPayResult;

	/**
	*支付时间
	*/
	private Date bsopyPayDate;

	/**
	*通知时间
	*/
	private Date bsopyNotifyDate;

	/**
	*创建时间
	*/
	private Date bsopyCdate;

	/**
	*更新时间
	*/
	private Date bsopyUdate;

	public void setBsopyUnid(Integer bsopyUnid) {
		this.bsopyUnid = bsopyUnid;
	}

	public Integer getBsopyUnid( ) {
		return bsopyUnid;
	}

	public void setBsopyUuid(String bsopyUuid) {
		this.bsopyUuid = bsopyUuid;
	}

	public String getBsopyUuid( ) {
		return bsopyUuid;
	}

	public void setBsopyOrder(String bsopyOrder) {
		this.bsopyOrder = bsopyOrder;
	}

	public String getBsopyOrder( ) {
		return bsopyOrder;
	}

	public void setBsopyClientIp(String bsopyClientIp) {
		this.bsopyClientIp = bsopyClientIp;
	}

	public String getBsopyClientIp( ) {
		return bsopyClientIp;
	}

	public void setBsopyPayChannel(String bsopyPayChannel) {
		this.bsopyPayChannel = bsopyPayChannel;
	}

	public String getBsopyPayChannel( ) {
		return bsopyPayChannel;
	}

	public void setBsopyTradeType(String bsopyTradeType) {
		this.bsopyTradeType = bsopyTradeType;
	}

	public String getBsopyTradeType( ) {
		return bsopyTradeType;
	}

	public void setBsopyUser(String bsopyUser) {
		this.bsopyUser = bsopyUser;
	}

	public String getBsopyUser( ) {
		return bsopyUser;
	}

	public void setBsopyReturnUrl(String bsopyReturnUrl) {
		this.bsopyReturnUrl = bsopyReturnUrl;
	}

	public String getBsopyReturnUrl( ) {
		return bsopyReturnUrl;
	}

	public void setBsopyPayParams(String bsopyPayParams) {
		this.bsopyPayParams = bsopyPayParams;
	}

	public String getBsopyPayParams( ) {
		return bsopyPayParams;
	}

	public void setBsopyResultCode(String bsopyResultCode) {
		this.bsopyResultCode = bsopyResultCode;
	}

	public String getBsopyResultCode( ) {
		return bsopyResultCode;
	}

	public void setBsopyErrorCode(String bsopyErrorCode) {
		this.bsopyErrorCode = bsopyErrorCode;
	}

	public String getBsopyErrorCode( ) {
		return bsopyErrorCode;
	}

	public void setBsopyErrorMsg(String bsopyErrorMsg) {
		this.bsopyErrorMsg = bsopyErrorMsg;
	}

	public String getBsopyErrorMsg( ) {
		return bsopyErrorMsg;
	}

	public void setBsopyOutTradeNo(String bsopyOutTradeNo) {
		this.bsopyOutTradeNo = bsopyOutTradeNo;
	}

	public String getBsopyOutTradeNo( ) {
		return bsopyOutTradeNo;
	}

	public void setBsopyPayResult(String bsopyPayResult) {
		this.bsopyPayResult = bsopyPayResult;
	}

	public String getBsopyPayResult( ) {
		return bsopyPayResult;
	}

	public void setBsopyPayDate(Date bsopyPayDate) {
		this.bsopyPayDate = bsopyPayDate;
	}

	public Date getBsopyPayDate( ) {
		return bsopyPayDate;
	}

	public void setBsopyNotifyDate(Date bsopyNotifyDate) {
		this.bsopyNotifyDate = bsopyNotifyDate;
	}

	public Date getBsopyNotifyDate( ) {
		return bsopyNotifyDate;
	}

	public void setBsopyCdate(Date bsopyCdate) {
		this.bsopyCdate = bsopyCdate;
	}

	public Date getBsopyCdate( ) {
		return bsopyCdate;
	}

	public void setBsopyUdate(Date bsopyUdate) {
		this.bsopyUdate = bsopyUdate;
	}

	public Date getBsopyUdate( ) {
		return bsopyUdate;
	}

	public BusiOrderPay( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}