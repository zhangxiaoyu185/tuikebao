package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiBanner;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 导航商品配置表
*/
public class BusiBannerVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bsbarUuid;

	/**
	*链接
	*/
	private String bsbarLink;

	/**
	*BANNER图
	*/
	private String bsbarPic;

	/**
	*顺序
	*/
	private Integer bsbarOrd;

	/**
	*状态:1启动0禁用
	*/
	private Integer bsbarStatus;

	/**
	*描述
	*/
	private String bsbarDesc;

	/**
	*显示端:1分享端2商城端3都显示
	*/
	private Integer bsbarShow;
	
	/**
	*创建时间
	*/
	private String bsbarCdate;

	/**
	*更新时间
	*/
	private String bsbarUdate;

	public void setBsbarUuid(String bsbarUuid) {
		this.bsbarUuid = bsbarUuid;
	}

	public String getBsbarUuid( ) {
		return bsbarUuid;
	}

	public void setBsbarLink(String bsbarLink) {
		this.bsbarLink = bsbarLink;
	}

	public String getBsbarLink( ) {
		return bsbarLink;
	}

	public void setBsbarPic(String bsbarPic) {
		this.bsbarPic = bsbarPic;
	}

	public String getBsbarPic( ) {
		return bsbarPic;
	}

	public void setBsbarOrd(Integer bsbarOrd) {
		this.bsbarOrd = bsbarOrd;
	}

	public Integer getBsbarOrd( ) {
		return bsbarOrd;
	}

	public void setBsbarStatus(Integer bsbarStatus) {
		this.bsbarStatus = bsbarStatus;
	}

	public Integer getBsbarStatus( ) {
		return bsbarStatus;
	}

	public void setBsbarDesc(String bsbarDesc) {
		this.bsbarDesc = bsbarDesc;
	}

	public String getBsbarDesc( ) {
		return bsbarDesc;
	}

	public Integer getBsbarShow() {
		return bsbarShow;
	}

	public void setBsbarShow(Integer bsbarShow) {
		this.bsbarShow = bsbarShow;
	}

	public void setBsbarCdate(String bsbarCdate) {
		this.bsbarCdate = bsbarCdate;
	}

	public String getBsbarCdate( ) {
		return bsbarCdate;
	}

	public void setBsbarUdate(String bsbarUdate) {
		this.bsbarUdate = bsbarUdate;
	}

	public String getBsbarUdate( ) {
		return bsbarUdate;
	}

	public BusiBannerVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiBanner po = (BusiBanner) poObj;
		this.bsbarUuid = po.getBsbarUuid();
		this.bsbarLink = po.getBsbarLink();
		this.bsbarPic = po.getBsbarPic();
		this.bsbarOrd = po.getBsbarOrd();
		this.bsbarStatus = po.getBsbarStatus();
		this.bsbarDesc = po.getBsbarDesc();
		this.bsbarShow = po.getBsbarShow();
		this.bsbarCdate = po.getBsbarCdate()!=null?DateUtil.formatDefaultDate(po.getBsbarCdate()):"";
		this.bsbarUdate = po.getBsbarUdate()!=null?DateUtil.formatDefaultDate(po.getBsbarUdate()):"";
	}

}