package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiServerDetail;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 服务详情页面表
*/
public class BusiServerDetailVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bssdlUuid;

	/**
	*Title内容
	*/
	private String bssdlTitle;

	/**
	*SEO关键字
	*/
	private String bssdlSeo;

	/**
	*页面html内容
	*/
	private String bssdlHtml;

	/**
	*生成的URL路径
	*/
	private String bssdlUrl;

	/**
	*创建日期
	*/
	private String bssdlCdate;

	/**
	*修改日期
	*/
	private String bssdlUdate;

	/**
	*状态:1启用,0禁用
	*/
	private Integer bssdlStatus;

	public void setBssdlUuid(String bssdlUuid) {
		this.bssdlUuid = bssdlUuid;
	}

	public String getBssdlUuid( ) {
		return bssdlUuid;
	}

	public void setBssdlTitle(String bssdlTitle) {
		this.bssdlTitle = bssdlTitle;
	}

	public String getBssdlTitle( ) {
		return bssdlTitle;
	}

	public void setBssdlSeo(String bssdlSeo) {
		this.bssdlSeo = bssdlSeo;
	}

	public String getBssdlSeo( ) {
		return bssdlSeo;
	}

	public void setBssdlHtml(String bssdlHtml) {
		this.bssdlHtml = bssdlHtml;
	}

	public String getBssdlHtml( ) {
		return bssdlHtml;
	}

	public void setBssdlUrl(String bssdlUrl) {
		this.bssdlUrl = bssdlUrl;
	}

	public String getBssdlUrl( ) {
		return bssdlUrl;
	}

	public void setBssdlCdate(String bssdlCdate) {
		this.bssdlCdate = bssdlCdate;
	}

	public String getBssdlCdate( ) {
		return bssdlCdate;
	}

	public void setBssdlUdate(String bssdlUdate) {
		this.bssdlUdate = bssdlUdate;
	}

	public String getBssdlUdate( ) {
		return bssdlUdate;
	}

	public void setBssdlStatus(Integer bssdlStatus) {
		this.bssdlStatus = bssdlStatus;
	}

	public Integer getBssdlStatus( ) {
		return bssdlStatus;
	}

	public BusiServerDetailVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiServerDetail po = (BusiServerDetail) poObj;
		this.bssdlUuid = po.getBssdlUuid();
		this.bssdlTitle = po.getBssdlTitle();
		this.bssdlSeo = po.getBssdlSeo();
		this.bssdlHtml = po.getBssdlHtml();
		this.bssdlUrl = po.getBssdlUrl();
		this.bssdlCdate = po.getBssdlCdate()!=null?DateUtil.formatDefaultDate(po.getBssdlCdate()):"";
		this.bssdlUdate = po.getBssdlUdate()!=null?DateUtil.formatDefaultDate(po.getBssdlUdate()):"";
		this.bssdlStatus = po.getBssdlStatus();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}