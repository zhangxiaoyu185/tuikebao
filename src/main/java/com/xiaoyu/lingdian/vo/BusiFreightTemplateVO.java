package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiFreightTemplate;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 运费模板表
*/
public class BusiFreightTemplateVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bsfteUuid;

	/**
	*模板名称
	*/
	private String bsfteName;

	/**
	*初始重量
	*/
	private Integer bsfteInitialWeight;

	/**
	*初始价格
	*/
	private Double bsfteInitialPrice;

	/**
	*加重
	*/
	private Integer bsfteWeight;

	/**
	*加价
	*/
	private Double bsftePrice;

	/**
	*创建时间
	*/
	private String bsfteCdate;

	/**
	*更新时间
	*/
	private String bsfteUdate;

	public void setBsfteUuid(String bsfteUuid) {
		this.bsfteUuid = bsfteUuid;
	}

	public String getBsfteUuid( ) {
		return bsfteUuid;
	}

	public void setBsfteName(String bsfteName) {
		this.bsfteName = bsfteName;
	}

	public String getBsfteName( ) {
		return bsfteName;
	}

	public void setBsfteInitialWeight(Integer bsfteInitialWeight) {
		this.bsfteInitialWeight = bsfteInitialWeight;
	}

	public Integer getBsfteInitialWeight( ) {
		return bsfteInitialWeight;
	}

	public void setBsfteInitialPrice(Double bsfteInitialPrice) {
		this.bsfteInitialPrice = bsfteInitialPrice;
	}

	public Double getBsfteInitialPrice( ) {
		return bsfteInitialPrice;
	}

	public void setBsfteWeight(Integer bsfteWeight) {
		this.bsfteWeight = bsfteWeight;
	}

	public Integer getBsfteWeight( ) {
		return bsfteWeight;
	}

	public void setBsftePrice(Double bsftePrice) {
		this.bsftePrice = bsftePrice;
	}

	public Double getBsftePrice( ) {
		return bsftePrice;
	}

	public void setBsfteCdate(String bsfteCdate) {
		this.bsfteCdate = bsfteCdate;
	}

	public String getBsfteCdate( ) {
		return bsfteCdate;
	}

	public void setBsfteUdate(String bsfteUdate) {
		this.bsfteUdate = bsfteUdate;
	}

	public String getBsfteUdate( ) {
		return bsfteUdate;
	}

	public BusiFreightTemplateVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiFreightTemplate po = (BusiFreightTemplate) poObj;
		this.bsfteUuid = po.getBsfteUuid();
		this.bsfteName = po.getBsfteName();
		this.bsfteInitialWeight = po.getBsfteInitialWeight();
		this.bsfteInitialPrice = po.getBsfteInitialPrice();
		this.bsfteWeight = po.getBsfteWeight();
		this.bsftePrice = po.getBsftePrice();
		this.bsfteCdate = po.getBsfteCdate()!=null?DateUtil.formatDefaultDate(po.getBsfteCdate()):"";
		this.bsfteUdate = po.getBsfteUdate()!=null?DateUtil.formatDefaultDate(po.getBsfteUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}