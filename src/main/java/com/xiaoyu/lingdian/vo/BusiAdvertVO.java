package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiAdvert;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 广告位表
*/
public class BusiAdvertVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bsaetUuid;

	/**
	*链接
	*/
	private String bsaetLink;

	/**
	*广告图
	*/
	private String bsaetPic;

	/**
	*顺序
	*/
	private Integer bsaetOrd;

	/**
	*状态:1启动0禁用
	*/
	private Integer bsaetStatus;

	/**
	*描述
	*/
	private String bsaetDesc;

	/**
	*创建时间
	*/
	private String bsaetCdate;

	/**
	*更新时间
	*/
	private String bsaetUdate;

	public void setBsaetUuid(String bsaetUuid) {
		this.bsaetUuid = bsaetUuid;
	}

	public String getBsaetUuid( ) {
		return bsaetUuid;
	}

	public void setBsaetLink(String bsaetLink) {
		this.bsaetLink = bsaetLink;
	}

	public String getBsaetLink( ) {
		return bsaetLink;
	}

	public void setBsaetPic(String bsaetPic) {
		this.bsaetPic = bsaetPic;
	}

	public String getBsaetPic( ) {
		return bsaetPic;
	}

	public void setBsaetOrd(Integer bsaetOrd) {
		this.bsaetOrd = bsaetOrd;
	}

	public Integer getBsaetOrd( ) {
		return bsaetOrd;
	}

	public void setBsaetStatus(Integer bsaetStatus) {
		this.bsaetStatus = bsaetStatus;
	}

	public Integer getBsaetStatus( ) {
		return bsaetStatus;
	}

	public void setBsaetDesc(String bsaetDesc) {
		this.bsaetDesc = bsaetDesc;
	}

	public String getBsaetDesc( ) {
		return bsaetDesc;
	}

	public void setBsaetCdate(String bsaetCdate) {
		this.bsaetCdate = bsaetCdate;
	}

	public String getBsaetCdate( ) {
		return bsaetCdate;
	}

	public void setBsaetUdate(String bsaetUdate) {
		this.bsaetUdate = bsaetUdate;
	}

	public String getBsaetUdate( ) {
		return bsaetUdate;
	}

	public BusiAdvertVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiAdvert po = (BusiAdvert) poObj;
		this.bsaetUuid = po.getBsaetUuid();
		this.bsaetLink = po.getBsaetLink();
		this.bsaetPic = po.getBsaetPic();
		this.bsaetOrd = po.getBsaetOrd();
		this.bsaetStatus = po.getBsaetStatus();
		this.bsaetDesc = po.getBsaetDesc();
		this.bsaetCdate = po.getBsaetCdate()!=null?DateUtil.formatDefaultDate(po.getBsaetCdate()):"";
		this.bsaetUdate = po.getBsaetUdate()!=null?DateUtil.formatDefaultDate(po.getBsaetUdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}