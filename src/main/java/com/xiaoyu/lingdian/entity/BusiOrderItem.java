package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 商城订单明细表
*/
public class BusiOrderItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsoimUnid;

	/**
	*标识UUID
	*/
	private String bsoimUuid;

	/**
	*所属用户
	*/
	private String bsoimUser;

	/**
	*订单标识
	*/
	private String bsoimOrder;

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
	*明细订单重量
	*/
	private Double bsoimWeight;

	/**
	*明细金额
	*/
	private Double bsoimValue;

	/**
	*商品单价
	*/
	private Double bsoimPrice;

	/**
	*退款金额
	*/
	private Double bsoimRefund;

	/**
	*买家退款原因（暂未使用）
	*/
	private String bsoimBrefundReason;

	/**
	*客服备注退款原因（退款和不予退款时使用）
	*/
	private String bsoimCrefundReason;

	/**
	*退款状态:1未发起2退款处理中（转售后）3不予退款4退款
	*/
	private Integer bsoimRefundStatus;

	/**
	*明细订单状态:1支付中2支付成功3支付失败4已取消（未支付成功前）5卖家已发货6已收货7已评价8交易完成9售后处理10已删除（已完成后）
	*/
	private Integer bsoimStatus;

	/**
	*下单时间(冗余)
	*/
	private Date bsoimOrderTime;

	/**
	*支付时间(冗余)
	*/
	private Date bsoimPayTime;

	/**
	*发货时间
	*/
	private Date bsoimShippingTime;

	/**
	*确认收货时间
	*/
	private Date bsoimRecvTime;

	/**
	*取消订单时间
	*/
	private Date bsoimCancelTime;

	/**
	*发起售后时间
	*/
	private Date bsoimAftsaleTime;

	/**
	*评价时间
	*/
	private Date bsoimEvaluateTime;

	/**
	*交易完成时间
	*/
	private Date bsoimFinishTime;

	/**
	*删除订单时间
	*/
	private Date bsoimDelTime;

	/**
	*创建时间
	*/
	private Date bsoimCdate;

	/**
	*更新时间
	*/
	private Date bsoimUdate;

	/**
	*快递单号
	*/
	private String bsoimExpno;

	/**
	*快递公司编号
	*/
	private String bsoimExpressCode;

	/**
	*快递公司名称
	*/
	private String bsoimExpressName;

	/**
	*上一次查询时间
	*/
	private Date bsoimLastDate;

	/**
	*买家留言（下单时使用）
	*/
	private String bsoimBuyerMemo;

	/**
	*客服备注（转售后时使用）
	*/
	private String bsoimSellerMemo;

	public void setBsoimUnid(Integer bsoimUnid) {
		this.bsoimUnid = bsoimUnid;
	}

	public Integer getBsoimUnid( ) {
		return bsoimUnid;
	}

	public void setBsoimUuid(String bsoimUuid) {
		this.bsoimUuid = bsoimUuid;
	}

	public String getBsoimUuid( ) {
		return bsoimUuid;
	}

	public void setBsoimUser(String bsoimUser) {
		this.bsoimUser = bsoimUser;
	}

	public String getBsoimUser( ) {
		return bsoimUser;
	}

	public void setBsoimOrder(String bsoimOrder) {
		this.bsoimOrder = bsoimOrder;
	}

	public String getBsoimOrder( ) {
		return bsoimOrder;
	}

	public void setBsoimProduct(String bsoimProduct) {
		this.bsoimProduct = bsoimProduct;
	}

	public String getBsoimProduct( ) {
		return bsoimProduct;
	}

	public void setBsoimProductName(String bsoimProductName) {
		this.bsoimProductName = bsoimProductName;
	}

	public String getBsoimProductName( ) {
		return bsoimProductName;
	}

	public void setBsoimSharePic(String bsoimSharePic) {
		this.bsoimSharePic = bsoimSharePic;
	}

	public String getBsoimSharePic( ) {
		return bsoimSharePic;
	}

	public void setBsoimStorePic(String bsoimStorePic) {
		this.bsoimStorePic = bsoimStorePic;
	}

	public String getBsoimStorePic( ) {
		return bsoimStorePic;
	}

	public void setBsoimAttrName(String bsoimAttrName) {
		this.bsoimAttrName = bsoimAttrName;
	}

	public String getBsoimAttrName( ) {
		return bsoimAttrName;
	}

	public void setBsoimAttrValue(String bsoimAttrValue) {
		this.bsoimAttrValue = bsoimAttrValue;
	}

	public String getBsoimAttrValue( ) {
		return bsoimAttrValue;
	}

	public void setBsoimTags(String bsoimTags) {
		this.bsoimTags = bsoimTags;
	}

	public String getBsoimTags( ) {
		return bsoimTags;
	}

	public void setBsoimQuantity(Integer bsoimQuantity) {
		this.bsoimQuantity = bsoimQuantity;
	}

	public Integer getBsoimQuantity( ) {
		return bsoimQuantity;
	}

	public void setBsoimExpress(Double bsoimExpress) {
		this.bsoimExpress = bsoimExpress;
	}

	public Double getBsoimExpress( ) {
		return bsoimExpress;
	}

	public void setBsoimIsBom(Integer bsoimIsBom) {
		this.bsoimIsBom = bsoimIsBom;
	}

	public Integer getBsoimIsBom( ) {
		return bsoimIsBom;
	}

	public void setBsoimWeight(Double bsoimWeight) {
		this.bsoimWeight = bsoimWeight;
	}

	public Double getBsoimWeight( ) {
		return bsoimWeight;
	}

	public void setBsoimValue(Double bsoimValue) {
		this.bsoimValue = bsoimValue;
	}

	public Double getBsoimValue( ) {
		return bsoimValue;
	}

	public void setBsoimPrice(Double bsoimPrice) {
		this.bsoimPrice = bsoimPrice;
	}

	public Double getBsoimPrice( ) {
		return bsoimPrice;
	}

	public void setBsoimRefund(Double bsoimRefund) {
		this.bsoimRefund = bsoimRefund;
	}

	public Double getBsoimRefund( ) {
		return bsoimRefund;
	}

	public void setBsoimBrefundReason(String bsoimBrefundReason) {
		this.bsoimBrefundReason = bsoimBrefundReason;
	}

	public String getBsoimBrefundReason( ) {
		return bsoimBrefundReason;
	}

	public void setBsoimCrefundReason(String bsoimCrefundReason) {
		this.bsoimCrefundReason = bsoimCrefundReason;
	}

	public String getBsoimCrefundReason( ) {
		return bsoimCrefundReason;
	}

	public void setBsoimRefundStatus(Integer bsoimRefundStatus) {
		this.bsoimRefundStatus = bsoimRefundStatus;
	}

	public Integer getBsoimRefundStatus( ) {
		return bsoimRefundStatus;
	}

	public void setBsoimStatus(Integer bsoimStatus) {
		this.bsoimStatus = bsoimStatus;
	}

	public Integer getBsoimStatus( ) {
		return bsoimStatus;
	}

	public void setBsoimOrderTime(Date bsoimOrderTime) {
		this.bsoimOrderTime = bsoimOrderTime;
	}

	public Date getBsoimOrderTime( ) {
		return bsoimOrderTime;
	}

	public void setBsoimPayTime(Date bsoimPayTime) {
		this.bsoimPayTime = bsoimPayTime;
	}

	public Date getBsoimPayTime( ) {
		return bsoimPayTime;
	}

	public void setBsoimShippingTime(Date bsoimShippingTime) {
		this.bsoimShippingTime = bsoimShippingTime;
	}

	public Date getBsoimShippingTime( ) {
		return bsoimShippingTime;
	}

	public void setBsoimRecvTime(Date bsoimRecvTime) {
		this.bsoimRecvTime = bsoimRecvTime;
	}

	public Date getBsoimRecvTime( ) {
		return bsoimRecvTime;
	}

	public void setBsoimCancelTime(Date bsoimCancelTime) {
		this.bsoimCancelTime = bsoimCancelTime;
	}

	public Date getBsoimCancelTime( ) {
		return bsoimCancelTime;
	}

	public void setBsoimAftsaleTime(Date bsoimAftsaleTime) {
		this.bsoimAftsaleTime = bsoimAftsaleTime;
	}

	public Date getBsoimAftsaleTime( ) {
		return bsoimAftsaleTime;
	}

	public void setBsoimEvaluateTime(Date bsoimEvaluateTime) {
		this.bsoimEvaluateTime = bsoimEvaluateTime;
	}

	public Date getBsoimEvaluateTime( ) {
		return bsoimEvaluateTime;
	}

	public void setBsoimFinishTime(Date bsoimFinishTime) {
		this.bsoimFinishTime = bsoimFinishTime;
	}

	public Date getBsoimFinishTime( ) {
		return bsoimFinishTime;
	}

	public void setBsoimDelTime(Date bsoimDelTime) {
		this.bsoimDelTime = bsoimDelTime;
	}

	public Date getBsoimDelTime( ) {
		return bsoimDelTime;
	}

	public void setBsoimCdate(Date bsoimCdate) {
		this.bsoimCdate = bsoimCdate;
	}

	public Date getBsoimCdate( ) {
		return bsoimCdate;
	}

	public void setBsoimUdate(Date bsoimUdate) {
		this.bsoimUdate = bsoimUdate;
	}

	public Date getBsoimUdate( ) {
		return bsoimUdate;
	}

	public void setBsoimExpno(String bsoimExpno) {
		this.bsoimExpno = bsoimExpno;
	}

	public String getBsoimExpno( ) {
		return bsoimExpno;
	}

	public void setBsoimExpressCode(String bsoimExpressCode) {
		this.bsoimExpressCode = bsoimExpressCode;
	}

	public String getBsoimExpressCode( ) {
		return bsoimExpressCode;
	}

	public void setBsoimExpressName(String bsoimExpressName) {
		this.bsoimExpressName = bsoimExpressName;
	}

	public String getBsoimExpressName( ) {
		return bsoimExpressName;
	}

	public void setBsoimLastDate(Date bsoimLastDate) {
		this.bsoimLastDate = bsoimLastDate;
	}

	public Date getBsoimLastDate( ) {
		return bsoimLastDate;
	}

	public void setBsoimBuyerMemo(String bsoimBuyerMemo) {
		this.bsoimBuyerMemo = bsoimBuyerMemo;
	}

	public String getBsoimBuyerMemo( ) {
		return bsoimBuyerMemo;
	}

	public void setBsoimSellerMemo(String bsoimSellerMemo) {
		this.bsoimSellerMemo = bsoimSellerMemo;
	}

	public String getBsoimSellerMemo( ) {
		return bsoimSellerMemo;
	}

	public BusiOrderItem( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}