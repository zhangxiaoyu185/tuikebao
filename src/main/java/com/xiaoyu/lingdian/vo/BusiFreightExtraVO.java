package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiFreightExtra;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 特殊地区运费模板表
*/
public class BusiFreightExtraVO implements BaseVO {

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
	private String bsfeaCdate;

	/**
	*更新时间
	*/
	private String bsfeaUdate;

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

	public void setBsfeaCdate(String bsfeaCdate) {
		this.bsfeaCdate = bsfeaCdate;
	}

	public String getBsfeaCdate( ) {
		return bsfeaCdate;
	}

	public void setBsfeaUdate(String bsfeaUdate) {
		this.bsfeaUdate = bsfeaUdate;
	}

	public String getBsfeaUdate( ) {
		return bsfeaUdate;
	}

	public BusiFreightExtraVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiFreightExtra po = (BusiFreightExtra) poObj;
		this.bsfeaUuid = po.getBsfeaUuid();
		this.bsfeaFreightTemplate = po.getBsfeaFreightTemplate();
		this.bsfeaRegion = po.getBsfeaRegion();
		this.bsfeaInitialPrice = po.getBsfeaInitialPrice();
		this.bsfeaStepPrice = po.getBsfeaStepPrice();
		this.bsfeaCdate = po.getBsfeaCdate()!=null?DateUtil.formatDefaultDate(po.getBsfeaCdate()):"";
		this.bsfeaUdate = po.getBsfeaUdate()!=null?DateUtil.formatDefaultDate(po.getBsfeaUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}