package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 商品表
*/
public class BusiProduct extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer bsprtUnid;

	/**
	*标识UUID
	*/
	private String bsprtUuid;

	/**
	*商品名称
	*/
	private String bsprtName;

	/**
	*分类
	*/
	private String bsprtCategory;

	/**
	*商品描述
	*/
	private String bsprtDesc;

	/**
	*客服电话
	*/
	private String bsprtTel;

	/**
	*详情
	*/
	private String bsprtView;

	/**
	*售价范围
	*/
	private String bsprtSalePrice;

	/**
	*原价范围
	*/
	private String bsprtOrignalPrice;

	/**
	*销售数量
	*/
	private Integer bsprtSaleCount;

	/**
	*地区
	*/
	private String bsprtAddress;

	/**
	*总可用库存(暂时不用)
	*/
	private Integer bsprtAvailStock;

	/**
	*总冻结库存(暂时不用)
	*/
	private Integer bsprtFrozenStock;

	/**
	*库存警戒值(暂时不用)
	*/
	private Integer bsprtStockValve;

	/**
	*是否是虚拟商品:1是0否
	*/
	private Integer bsprtIsVirtual;

	/**
	*页面访问数量
	*/
	private Integer bsprtVisitCount;

	/**
	*状态:1销售中2已下架3已删除
	*/
	private Integer bsprtSaleStatus;

	/**
	*快递费用
	*/
	private Integer bsprtExpress;

	/**
	*推客封面图
	*/
	private String bsprtSharePic;

	/**
	*商城封面图
	*/
	private String bsprtStorePic;

	/**
	*主图1
	*/
	private String bsprtImageUrl1;

	/**
	*主图2
	*/
	private String bsprtImageUrl2;

	/**
	*主图3
	*/
	private String bsprtImageUrl3;

	/**
	*主图4
	*/
	private String bsprtImageUrl4;

	/**
	*主图5
	*/
	private String bsprtImageUrl5;

	/**
	*所有属性标识JSON串
	*/
	private String bsprtAttrJson;

	/**
	*所有属性值标识JSON串
	*/
	private String bsprtValueJson;

	/**
	*未选择属性值标识JSON串
	*/
	private String bsprtWxzvalueJson;
	
	/**
	*标签JSON串
	*/
	private String bsprtProductTag;

	/**
	*规格参数JSON串
	*/
	private String bsprtRuleParam;

	/**
	*商品等级分享金额JSON串
	*/
	private String bsprtGradeShare;

	/**
	*商品热点标记
	*/
	private String bsprtHotSpot;
	
	/**
	*商品热点名称
	*/
	private String bsprtHotspotName;
	
	/**
	*商品热点图标
	*/
	private String bsprtHotspotIcon;
	
	/**
	*创建时间
	*/
	private Date bsprtCdate;

	/**
	*更新时间
	*/
	private Date bsprtUdate;

	public void setBsprtUnid(Integer bsprtUnid) {
		this.bsprtUnid = bsprtUnid;
	}

	public Integer getBsprtUnid( ) {
		return bsprtUnid;
	}

	public void setBsprtUuid(String bsprtUuid) {
		this.bsprtUuid = bsprtUuid;
	}

	public String getBsprtUuid( ) {
		return bsprtUuid;
	}

	public void setBsprtName(String bsprtName) {
		this.bsprtName = bsprtName;
	}

	public String getBsprtName( ) {
		return bsprtName;
	}

	public void setBsprtCategory(String bsprtCategory) {
		this.bsprtCategory = bsprtCategory;
	}

	public String getBsprtCategory( ) {
		return bsprtCategory;
	}

	public void setBsprtDesc(String bsprtDesc) {
		this.bsprtDesc = bsprtDesc;
	}

	public String getBsprtDesc( ) {
		return bsprtDesc;
	}

	public void setBsprtTel(String bsprtTel) {
		this.bsprtTel = bsprtTel;
	}

	public String getBsprtTel( ) {
		return bsprtTel;
	}

	public void setBsprtView(String bsprtView) {
		this.bsprtView = bsprtView;
	}

	public String getBsprtView( ) {
		return bsprtView;
	}

	public void setBsprtSalePrice(String bsprtSalePrice) {
		this.bsprtSalePrice = bsprtSalePrice;
	}

	public String getBsprtSalePrice( ) {
		return bsprtSalePrice;
	}

	public void setBsprtOrignalPrice(String bsprtOrignalPrice) {
		this.bsprtOrignalPrice = bsprtOrignalPrice;
	}

	public String getBsprtOrignalPrice( ) {
		return bsprtOrignalPrice;
	}

	public void setBsprtSaleCount(Integer bsprtSaleCount) {
		this.bsprtSaleCount = bsprtSaleCount;
	}

	public Integer getBsprtSaleCount( ) {
		return bsprtSaleCount;
	}

	public void setBsprtAddress(String bsprtAddress) {
		this.bsprtAddress = bsprtAddress;
	}

	public String getBsprtAddress( ) {
		return bsprtAddress;
	}

	public void setBsprtAvailStock(Integer bsprtAvailStock) {
		this.bsprtAvailStock = bsprtAvailStock;
	}

	public Integer getBsprtAvailStock( ) {
		return bsprtAvailStock;
	}

	public void setBsprtFrozenStock(Integer bsprtFrozenStock) {
		this.bsprtFrozenStock = bsprtFrozenStock;
	}

	public Integer getBsprtFrozenStock( ) {
		return bsprtFrozenStock;
	}

	public void setBsprtStockValve(Integer bsprtStockValve) {
		this.bsprtStockValve = bsprtStockValve;
	}

	public Integer getBsprtStockValve( ) {
		return bsprtStockValve;
	}

	public void setBsprtIsVirtual(Integer bsprtIsVirtual) {
		this.bsprtIsVirtual = bsprtIsVirtual;
	}

	public Integer getBsprtIsVirtual( ) {
		return bsprtIsVirtual;
	}

	public void setBsprtVisitCount(Integer bsprtVisitCount) {
		this.bsprtVisitCount = bsprtVisitCount;
	}

	public Integer getBsprtVisitCount( ) {
		return bsprtVisitCount;
	}

	public void setBsprtSaleStatus(Integer bsprtSaleStatus) {
		this.bsprtSaleStatus = bsprtSaleStatus;
	}

	public Integer getBsprtSaleStatus( ) {
		return bsprtSaleStatus;
	}

	public void setBsprtExpress(Integer bsprtExpress) {
		this.bsprtExpress = bsprtExpress;
	}

	public Integer getBsprtExpress( ) {
		return bsprtExpress;
	}

	public void setBsprtSharePic(String bsprtSharePic) {
		this.bsprtSharePic = bsprtSharePic;
	}

	public String getBsprtSharePic( ) {
		return bsprtSharePic;
	}

	public void setBsprtStorePic(String bsprtStorePic) {
		this.bsprtStorePic = bsprtStorePic;
	}

	public String getBsprtStorePic( ) {
		return bsprtStorePic;
	}

	public void setBsprtImageUrl1(String bsprtImageUrl1) {
		this.bsprtImageUrl1 = bsprtImageUrl1;
	}

	public String getBsprtImageUrl1( ) {
		return bsprtImageUrl1;
	}

	public void setBsprtImageUrl2(String bsprtImageUrl2) {
		this.bsprtImageUrl2 = bsprtImageUrl2;
	}

	public String getBsprtImageUrl2( ) {
		return bsprtImageUrl2;
	}

	public void setBsprtImageUrl3(String bsprtImageUrl3) {
		this.bsprtImageUrl3 = bsprtImageUrl3;
	}

	public String getBsprtImageUrl3( ) {
		return bsprtImageUrl3;
	}

	public void setBsprtImageUrl4(String bsprtImageUrl4) {
		this.bsprtImageUrl4 = bsprtImageUrl4;
	}

	public String getBsprtImageUrl4( ) {
		return bsprtImageUrl4;
	}

	public void setBsprtImageUrl5(String bsprtImageUrl5) {
		this.bsprtImageUrl5 = bsprtImageUrl5;
	}

	public String getBsprtImageUrl5( ) {
		return bsprtImageUrl5;
	}

	public void setBsprtAttrJson(String bsprtAttrJson) {
		this.bsprtAttrJson = bsprtAttrJson;
	}

	public String getBsprtAttrJson( ) {
		return bsprtAttrJson;
	}

	public void setBsprtValueJson(String bsprtValueJson) {
		this.bsprtValueJson = bsprtValueJson;
	}

	public String getBsprtValueJson( ) {
		return bsprtValueJson;
	}

	public String getBsprtWxzvalueJson() {
		return bsprtWxzvalueJson;
	}

	public void setBsprtWxzvalueJson(String bsprtWxzvalueJson) {
		this.bsprtWxzvalueJson = bsprtWxzvalueJson;
	}

	public void setBsprtProductTag(String bsprtProductTag) {
		this.bsprtProductTag = bsprtProductTag;
	}

	public String getBsprtProductTag( ) {
		return bsprtProductTag;
	}

	public void setBsprtRuleParam(String bsprtRuleParam) {
		this.bsprtRuleParam = bsprtRuleParam;
	}

	public String getBsprtRuleParam( ) {
		return bsprtRuleParam;
	}

	public void setBsprtGradeShare(String bsprtGradeShare) {
		this.bsprtGradeShare = bsprtGradeShare;
	}

	public String getBsprtGradeShare( ) {
		return bsprtGradeShare;
	}

	public String getBsprtHotSpot() {
		return bsprtHotSpot;
	}

	public void setBsprtHotSpot(String bsprtHotSpot) {
		this.bsprtHotSpot = bsprtHotSpot;
	}

	public String getBsprtHotspotName() {
		return bsprtHotspotName;
	}

	public void setBsprtHotspotName(String bsprtHotspotName) {
		this.bsprtHotspotName = bsprtHotspotName;
	}

	public String getBsprtHotspotIcon() {
		return bsprtHotspotIcon;
	}

	public void setBsprtHotspotIcon(String bsprtHotspotIcon) {
		this.bsprtHotspotIcon = bsprtHotspotIcon;
	}

	public void setBsprtCdate(Date bsprtCdate) {
		this.bsprtCdate = bsprtCdate;
	}

	public Date getBsprtCdate( ) {
		return bsprtCdate;
	}

	public void setBsprtUdate(Date bsprtUdate) {
		this.bsprtUdate = bsprtUdate;
	}

	public Date getBsprtUdate( ) {
		return bsprtUdate;
	}

	public BusiProduct( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}