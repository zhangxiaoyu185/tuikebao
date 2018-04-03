package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiHotList;
import com.xiaoyu.lingdian.entity.BusiProduct;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 热榜表
*/
public class BusiHotListVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bshltUuid;

	/**
	*商品标识
	*/
	private String bshltProduct;

	/**
	*顺序
	*/
	private Integer bshltOrd;

	/**
	*状态:1启动0禁用
	*/
	private Integer bshltStatus;

	/**
	*创建时间
	*/
	private String bshltCdate;

	/**
	*更新时间
	*/
	private String bshltUdate;

	/**
	*商品名称
	*/
	private String bsprtName;

	/**
	*分类
	*/
	private String bsprtCategory;

	/**
	*分类名称
	*/
	private String bsprtCategoryName;
	
	/**
	*商品描述
	*/
	private String bsprtDesc;
	
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
	*是否是虚拟商品:1是0否
	*/
	private Integer bsprtIsVirtual;

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

	public void setBshltUuid(String bshltUuid) {
		this.bshltUuid = bshltUuid;
	}

	public String getBshltUuid( ) {
		return bshltUuid;
	}

	public void setBshltProduct(String bshltProduct) {
		this.bshltProduct = bshltProduct;
	}

	public String getBshltProduct( ) {
		return bshltProduct;
	}

	public void setBshltOrd(Integer bshltOrd) {
		this.bshltOrd = bshltOrd;
	}

	public Integer getBshltOrd( ) {
		return bshltOrd;
	}

	public void setBshltStatus(Integer bshltStatus) {
		this.bshltStatus = bshltStatus;
	}

	public Integer getBshltStatus( ) {
		return bshltStatus;
	}

	public void setBshltCdate(String bshltCdate) {
		this.bshltCdate = bshltCdate;
	}

	public String getBshltCdate( ) {
		return bshltCdate;
	}

	public void setBshltUdate(String bshltUdate) {
		this.bshltUdate = bshltUdate;
	}

	public String getBshltUdate( ) {
		return bshltUdate;
	}

	public String getBsprtName() {
		return bsprtName;
	}

	public void setBsprtName(String bsprtName) {
		this.bsprtName = bsprtName;
	}

	public String getBsprtCategory() {
		return bsprtCategory;
	}

	public void setBsprtCategory(String bsprtCategory) {
		this.bsprtCategory = bsprtCategory;
	}

	public String getBsprtCategoryName() {
		return bsprtCategoryName;
	}

	public void setBsprtCategoryName(String bsprtCategoryName) {
		this.bsprtCategoryName = bsprtCategoryName;
	}

	public String getBsprtDesc() {
		return bsprtDesc;
	}

	public void setBsprtDesc(String bsprtDesc) {
		this.bsprtDesc = bsprtDesc;
	}

	public String getBsprtSalePrice() {
		return bsprtSalePrice;
	}

	public void setBsprtSalePrice(String bsprtSalePrice) {
		this.bsprtSalePrice = bsprtSalePrice;
	}

	public String getBsprtOrignalPrice() {
		return bsprtOrignalPrice;
	}

	public void setBsprtOrignalPrice(String bsprtOrignalPrice) {
		this.bsprtOrignalPrice = bsprtOrignalPrice;
	}

	public Integer getBsprtSaleCount() {
		return bsprtSaleCount;
	}

	public void setBsprtSaleCount(Integer bsprtSaleCount) {
		this.bsprtSaleCount = bsprtSaleCount;
	}

	public String getBsprtAddress() {
		return bsprtAddress;
	}

	public void setBsprtAddress(String bsprtAddress) {
		this.bsprtAddress = bsprtAddress;
	}

	public Integer getBsprtIsVirtual() {
		return bsprtIsVirtual;
	}

	public void setBsprtIsVirtual(Integer bsprtIsVirtual) {
		this.bsprtIsVirtual = bsprtIsVirtual;
	}

	public Integer getBsprtSaleStatus() {
		return bsprtSaleStatus;
	}

	public void setBsprtSaleStatus(Integer bsprtSaleStatus) {
		this.bsprtSaleStatus = bsprtSaleStatus;
	}

	public Integer getBsprtExpress() {
		return bsprtExpress;
	}

	public void setBsprtExpress(Integer bsprtExpress) {
		this.bsprtExpress = bsprtExpress;
	}

	public String getBsprtSharePic() {
		return bsprtSharePic;
	}

	public void setBsprtSharePic(String bsprtSharePic) {
		this.bsprtSharePic = bsprtSharePic;
	}

	public String getBsprtStorePic() {
		return bsprtStorePic;
	}

	public void setBsprtStorePic(String bsprtStorePic) {
		this.bsprtStorePic = bsprtStorePic;
	}

	public String getBsprtGradeShare() {
		return bsprtGradeShare;
	}

	public void setBsprtGradeShare(String bsprtGradeShare) {
		this.bsprtGradeShare = bsprtGradeShare;
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

	public BusiHotListVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiHotList po = (BusiHotList) poObj;
		this.bshltUuid = po.getBshltUuid();
		this.bshltProduct = po.getBshltProduct();
		this.bshltOrd = po.getBshltOrd();
		this.bshltStatus = po.getBshltStatus();
		this.bshltCdate = po.getBshltCdate()!=null?DateUtil.formatDefaultDate(po.getBshltCdate()):"";
		this.bshltUdate = po.getBshltUdate()!=null?DateUtil.formatDefaultDate(po.getBshltUdate()):"";
	}

	public void convertPOToVOByProduct(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiProduct po = (BusiProduct) poObj;
		this.bsprtName = po.getBsprtName();
		this.bsprtCategory = po.getBsprtCategory();
		this.bsprtDesc = po.getBsprtDesc();
		this.bsprtSalePrice = po.getBsprtSalePrice();
		this.bsprtOrignalPrice = po.getBsprtOrignalPrice();
		this.bsprtSaleCount = po.getBsprtSaleCount();
		this.bsprtAddress = po.getBsprtAddress();
		this.bsprtIsVirtual = po.getBsprtIsVirtual();
		this.bsprtSaleStatus = po.getBsprtSaleStatus();
		this.bsprtExpress = po.getBsprtExpress();
		this.bsprtSharePic = po.getBsprtSharePic();
		this.bsprtStorePic = po.getBsprtStorePic();
		this.bsprtGradeShare = po.getBsprtGradeShare();
		this.bsprtHotSpot = po.getBsprtHotSpot();
		this.bsprtHotspotName = po.getBsprtHotspotName();
		this.bsprtHotspotIcon = po.getBsprtHotspotIcon();
	}

}