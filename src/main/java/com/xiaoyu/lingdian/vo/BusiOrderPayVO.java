package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiOrderPay;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 订单支付记录表
*/
public class BusiOrderPayVO implements BaseVO {

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
	private String bsopyPayDate;

	/**
	*通知时间
	*/
	private String bsopyNotifyDate;

	/**
	*创建时间
	*/
	private String bsopyCdate;

	/**
	*更新时间
	*/
	private String bsopyUdate;

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

	public void setBsopyPayDate(String bsopyPayDate) {
		this.bsopyPayDate = bsopyPayDate;
	}

	public String getBsopyPayDate( ) {
		return bsopyPayDate;
	}

	public void setBsopyNotifyDate(String bsopyNotifyDate) {
		this.bsopyNotifyDate = bsopyNotifyDate;
	}

	public String getBsopyNotifyDate( ) {
		return bsopyNotifyDate;
	}

	public void setBsopyCdate(String bsopyCdate) {
		this.bsopyCdate = bsopyCdate;
	}

	public String getBsopyCdate( ) {
		return bsopyCdate;
	}

	public void setBsopyUdate(String bsopyUdate) {
		this.bsopyUdate = bsopyUdate;
	}

	public String getBsopyUdate( ) {
		return bsopyUdate;
	}

	public BusiOrderPayVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiOrderPay po = (BusiOrderPay) poObj;
		this.bsopyUuid = po.getBsopyUuid();
		this.bsopyOrder = po.getBsopyOrder();
		this.bsopyClientIp = po.getBsopyClientIp();
		this.bsopyPayChannel = po.getBsopyPayChannel();
		this.bsopyTradeType = po.getBsopyTradeType();
		this.bsopyUser = po.getBsopyUser();
		this.bsopyReturnUrl = po.getBsopyReturnUrl();
		this.bsopyPayParams = po.getBsopyPayParams();
		this.bsopyResultCode = po.getBsopyResultCode();
		this.bsopyErrorCode = po.getBsopyErrorCode();
		this.bsopyErrorMsg = po.getBsopyErrorMsg();
		this.bsopyOutTradeNo = po.getBsopyOutTradeNo();
		this.bsopyPayResult = po.getBsopyPayResult();
		this.bsopyPayDate = po.getBsopyPayDate()!=null?DateUtil.formatDefaultDate(po.getBsopyPayDate()):"";
		this.bsopyNotifyDate = po.getBsopyNotifyDate()!=null?DateUtil.formatDefaultDate(po.getBsopyNotifyDate()):"";
		this.bsopyCdate = po.getBsopyCdate()!=null?DateUtil.formatDefaultDate(po.getBsopyCdate()):"";
		this.bsopyUdate = po.getBsopyUdate()!=null?DateUtil.formatDefaultDate(po.getBsopyUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}