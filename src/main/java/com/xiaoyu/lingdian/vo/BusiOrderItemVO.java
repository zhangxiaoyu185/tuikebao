package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiOrderItem;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 商城订单明细表
*/
public class BusiOrderItemVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bsoimUuid;

	/**
	*所属用户
	*/
	private String bsoimUser;

	/**
	*所属用户名称
	*/
	private String bsoimUserName;
	
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
	private String bsoimOrderTime;

	/**
	*支付时间(冗余)
	*/
	private String bsoimPayTime;

	/**
	*发货时间
	*/
	private String bsoimShippingTime;

	/**
	*确认收货时间
	*/
	private String bsoimRecvTime;

	/**
	*取消订单时间
	*/
	private String bsoimCancelTime;

	/**
	*发起售后时间
	*/
	private String bsoimAftsaleTime;

	/**
	*评价时间
	*/
	private String bsoimEvaluateTime;

	/**
	*交易完成时间
	*/
	private String bsoimFinishTime;

	/**
	*删除订单时间
	*/
	private String bsoimDelTime;

	/**
	*创建时间
	*/
	private String bsoimCdate;

	/**
	*更新时间
	*/
	private String bsoimUdate;

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
	private String bsoimLastDate;

	/**
	*买家留言（下单时使用）
	*/
	private String bsoimBuyerMemo;

	/**
	*客服备注（转售后时使用）
	*/
	private String bsoimSellerMemo;
	
	
   /**
    *交易号
    */
    private String bsorrTradeNo;
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

	public void setBsoimOrderTime(String bsoimOrderTime) {
		this.bsoimOrderTime = bsoimOrderTime;
	}

	public String getBsoimOrderTime( ) {
		return bsoimOrderTime;
	}

	public void setBsoimPayTime(String bsoimPayTime) {
		this.bsoimPayTime = bsoimPayTime;
	}

	public String getBsoimPayTime( ) {
		return bsoimPayTime;
	}

	public void setBsoimShippingTime(String bsoimShippingTime) {
		this.bsoimShippingTime = bsoimShippingTime;
	}

	public String getBsoimShippingTime( ) {
		return bsoimShippingTime;
	}

	public void setBsoimRecvTime(String bsoimRecvTime) {
		this.bsoimRecvTime = bsoimRecvTime;
	}

	public String getBsoimRecvTime( ) {
		return bsoimRecvTime;
	}

	public void setBsoimCancelTime(String bsoimCancelTime) {
		this.bsoimCancelTime = bsoimCancelTime;
	}

	public String getBsoimCancelTime( ) {
		return bsoimCancelTime;
	}

	public void setBsoimAftsaleTime(String bsoimAftsaleTime) {
		this.bsoimAftsaleTime = bsoimAftsaleTime;
	}

	public String getBsoimAftsaleTime( ) {
		return bsoimAftsaleTime;
	}

	public void setBsoimEvaluateTime(String bsoimEvaluateTime) {
		this.bsoimEvaluateTime = bsoimEvaluateTime;
	}

	public String getBsoimEvaluateTime( ) {
		return bsoimEvaluateTime;
	}

	public void setBsoimFinishTime(String bsoimFinishTime) {
		this.bsoimFinishTime = bsoimFinishTime;
	}

	public String getBsoimFinishTime( ) {
		return bsoimFinishTime;
	}

	public void setBsoimDelTime(String bsoimDelTime) {
		this.bsoimDelTime = bsoimDelTime;
	}

	public String getBsoimDelTime( ) {
		return bsoimDelTime;
	}

	public void setBsoimCdate(String bsoimCdate) {
		this.bsoimCdate = bsoimCdate;
	}

	public String getBsoimCdate( ) {
		return bsoimCdate;
	}

	public void setBsoimUdate(String bsoimUdate) {
		this.bsoimUdate = bsoimUdate;
	}

	public String getBsoimUdate( ) {
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

	public void setBsoimLastDate(String bsoimLastDate) {
		this.bsoimLastDate = bsoimLastDate;
	}

	public String getBsoimLastDate( ) {
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

	public String getBsoimUserName() {
		return bsoimUserName;
	}

	public void setBsoimUserName(String bsoimUserName) {
		this.bsoimUserName = bsoimUserName;
	}
	 
	public String getBsorrTradeNo() {
        return bsorrTradeNo;
    }

    public void setBsorrTradeNo(String bsorrTradeNo) {
        this.bsorrTradeNo = bsorrTradeNo;
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

    public BusiOrderItemVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiOrderItem po = (BusiOrderItem) poObj;
		this.bsoimUuid = po.getBsoimUuid();
		this.bsoimUser = po.getBsoimUser();
		this.bsoimOrder = po.getBsoimOrder();
		this.bsoimProduct = po.getBsoimProduct();
		this.bsoimProductName = po.getBsoimProductName();
		this.bsoimSharePic = po.getBsoimSharePic();
		this.bsoimStorePic = po.getBsoimStorePic();
		this.bsoimAttrName = po.getBsoimAttrName();
		this.bsoimAttrValue = po.getBsoimAttrValue();
		this.bsoimTags = po.getBsoimTags();
		this.bsoimQuantity = po.getBsoimQuantity();
		this.bsoimExpress = po.getBsoimExpress();
		this.bsoimIsBom = po.getBsoimIsBom();
		this.bsoimWeight = po.getBsoimWeight();
		this.bsoimValue = po.getBsoimValue();
		this.bsoimPrice = po.getBsoimPrice();
		this.bsoimRefund = po.getBsoimRefund();
		this.bsoimBrefundReason = po.getBsoimBrefundReason();
		this.bsoimCrefundReason = po.getBsoimCrefundReason();
		this.bsoimRefundStatus = po.getBsoimRefundStatus();
		this.bsoimStatus = po.getBsoimStatus();
		this.bsoimOrderTime = po.getBsoimOrderTime()!=null?DateUtil.formatDefaultDate(po.getBsoimOrderTime()):"";
		this.bsoimPayTime = po.getBsoimPayTime()!=null?DateUtil.formatDefaultDate(po.getBsoimPayTime()):"";
		this.bsoimShippingTime = po.getBsoimShippingTime()!=null?DateUtil.formatDefaultDate(po.getBsoimShippingTime()):"";
		this.bsoimRecvTime = po.getBsoimRecvTime()!=null?DateUtil.formatDefaultDate(po.getBsoimRecvTime()):"";
		this.bsoimCancelTime = po.getBsoimCancelTime()!=null?DateUtil.formatDefaultDate(po.getBsoimCancelTime()):"";
		this.bsoimAftsaleTime = po.getBsoimAftsaleTime()!=null?DateUtil.formatDefaultDate(po.getBsoimAftsaleTime()):"";
		this.bsoimEvaluateTime = po.getBsoimEvaluateTime()!=null?DateUtil.formatDefaultDate(po.getBsoimEvaluateTime()):"";
		this.bsoimFinishTime = po.getBsoimFinishTime()!=null?DateUtil.formatDefaultDate(po.getBsoimFinishTime()):"";
		this.bsoimDelTime = po.getBsoimDelTime()!=null?DateUtil.formatDefaultDate(po.getBsoimDelTime()):"";
		this.bsoimCdate = po.getBsoimCdate()!=null?DateUtil.formatDefaultDate(po.getBsoimCdate()):"";
		this.bsoimUdate = po.getBsoimUdate()!=null?DateUtil.formatDefaultDate(po.getBsoimUdate()):"";
		this.bsoimExpno = po.getBsoimExpno();
		this.bsoimExpressCode = po.getBsoimExpressCode();
		this.bsoimExpressName = po.getBsoimExpressName();
		this.bsoimLastDate = po.getBsoimLastDate()!=null?DateUtil.formatDefaultDate(po.getBsoimLastDate()):"";
		this.bsoimBuyerMemo = po.getBsoimBuyerMemo();
		this.bsoimSellerMemo = po.getBsoimSellerMemo();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}