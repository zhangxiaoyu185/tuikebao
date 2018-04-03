package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiHotSpot;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 热点配置表
*/
public class BusiHotSpotVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bshstUuid;

	/**
	*热点名称
	*/
	private String bshstName;

	/**
	*热点图片
	*/
	private String bshstIcon;

	/**
	*创建时间
	*/
	private String bshstCdate;

	/**
	*更新时间
	*/
	private String bshstUdate;

	public void setBshstUuid(String bshstUuid) {
		this.bshstUuid = bshstUuid;
	}

	public String getBshstUuid( ) {
		return bshstUuid;
	}

	public void setBshstName(String bshstName) {
		this.bshstName = bshstName;
	}

	public String getBshstName( ) {
		return bshstName;
	}

	public void setBshstIcon(String bshstIcon) {
		this.bshstIcon = bshstIcon;
	}

	public String getBshstIcon( ) {
		return bshstIcon;
	}

	public void setBshstCdate(String bshstCdate) {
		this.bshstCdate = bshstCdate;
	}

	public String getBshstCdate( ) {
		return bshstCdate;
	}

	public void setBshstUdate(String bshstUdate) {
		this.bshstUdate = bshstUdate;
	}

	public String getBshstUdate( ) {
		return bshstUdate;
	}

	public BusiHotSpotVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiHotSpot po = (BusiHotSpot) poObj;
		this.bshstUuid = po.getBshstUuid();
		this.bshstName = po.getBshstName();
		this.bshstIcon = po.getBshstIcon();
		this.bshstCdate = po.getBshstCdate()!=null?DateUtil.formatDefaultDate(po.getBshstCdate()):"";
		this.bshstUdate = po.getBshstUdate()!=null?DateUtil.formatDefaultDate(po.getBshstUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}