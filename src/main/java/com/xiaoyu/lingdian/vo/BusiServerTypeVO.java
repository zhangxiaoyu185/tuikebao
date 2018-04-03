package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiServerType;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 服务类别表
*/
public class BusiServerTypeVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bssteUuid;

	/**
	*类别名称
	*/
	private String bssteName;

	/**
	*图标
	*/
	private String bssteIcon;

	/**
	*是否有列表:1有0无
	*/
	private Integer bssteIslist;

	/**
	*显示端:1分享端2商城端3都显示
	*/
	private Integer bssteShow;
	
	/**
	*详情URL(不是列表用)
	*/
	private String bssteDetails;

	/**
	*创建日期
	*/
	private String bssteCdate;

	/**
	*修改日期
	*/
	private String bssteUdate;

	/**
	*状态:1启用,0禁用
	*/
	private Integer bssteStatus;

	public void setBssteUuid(String bssteUuid) {
		this.bssteUuid = bssteUuid;
	}

	public String getBssteUuid( ) {
		return bssteUuid;
	}

	public void setBssteName(String bssteName) {
		this.bssteName = bssteName;
	}

	public String getBssteName( ) {
		return bssteName;
	}

	public void setBssteIcon(String bssteIcon) {
		this.bssteIcon = bssteIcon;
	}

	public String getBssteIcon( ) {
		return bssteIcon;
	}

	public void setBssteIslist(Integer bssteIslist) {
		this.bssteIslist = bssteIslist;
	}

	public Integer getBssteIslist( ) {
		return bssteIslist;
	}

	public void setBssteDetails(String bssteDetails) {
		this.bssteDetails = bssteDetails;
	}

	public String getBssteDetails( ) {
		return bssteDetails;
	}

	public void setBssteCdate(String bssteCdate) {
		this.bssteCdate = bssteCdate;
	}

	public String getBssteCdate( ) {
		return bssteCdate;
	}

	public void setBssteUdate(String bssteUdate) {
		this.bssteUdate = bssteUdate;
	}

	public String getBssteUdate( ) {
		return bssteUdate;
	}

	public void setBssteStatus(Integer bssteStatus) {
		this.bssteStatus = bssteStatus;
	}

	public Integer getBssteStatus( ) {
		return bssteStatus;
	}

	public Integer getBssteShow() {
		return bssteShow;
	}

	public void setBssteShow(Integer bssteShow) {
		this.bssteShow = bssteShow;
	}

	public BusiServerTypeVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiServerType po = (BusiServerType) poObj;
		this.bssteUuid = po.getBssteUuid();
		this.bssteName = po.getBssteName();
		this.bssteIcon = po.getBssteIcon();
		this.bssteIslist = po.getBssteIslist();
		this.bssteShow = po.getBssteShow();
		this.bssteDetails = po.getBssteDetails();
		this.bssteCdate = po.getBssteCdate()!=null?DateUtil.formatDefaultDate(po.getBssteCdate()):"";
		this.bssteUdate = po.getBssteUdate()!=null?DateUtil.formatDefaultDate(po.getBssteUdate()):"";
		this.bssteStatus = po.getBssteStatus();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}