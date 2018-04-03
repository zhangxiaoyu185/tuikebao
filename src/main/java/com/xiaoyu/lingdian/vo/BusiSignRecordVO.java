package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiSignRecord;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 用户当前签到记录表
*/
public class BusiSignRecordVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bssrdUuid;

	/**
	*签到用户
	*/
	private String bssrdUser;

	/**
	*签到用户账户
	*/
	private String bssrdUserName;

	/**
	*所得积分
	*/
	private Integer bssrdIntegral;

	/**
	*最后签到时间
	*/
	private String bssrdLastDate;

	/**
	*连续签到天数
	*/
	private Integer bssrdSignDays;

	public void setBssrdUuid(String bssrdUuid) {
		this.bssrdUuid = bssrdUuid;
	}

	public String getBssrdUuid( ) {
		return bssrdUuid;
	}

	public void setBssrdUser(String bssrdUser) {
		this.bssrdUser = bssrdUser;
	}

	public String getBssrdUser( ) {
		return bssrdUser;
	}

	public void setBssrdIntegral(Integer bssrdIntegral) {
		this.bssrdIntegral = bssrdIntegral;
	}

	public Integer getBssrdIntegral( ) {
		return bssrdIntegral;
	}

	public void setBssrdLastDate(String bssrdLastDate) {
		this.bssrdLastDate = bssrdLastDate;
	}

	public String getBssrdLastDate( ) {
		return bssrdLastDate;
	}

	public void setBssrdSignDays(Integer bssrdSignDays) {
		this.bssrdSignDays = bssrdSignDays;
	}

	public Integer getBssrdSignDays( ) {
		return bssrdSignDays;
	}

	public String getBssrdUserName() {
		return bssrdUserName;
	}

	public void setBssrdUserName(String bssrdUserName) {
		this.bssrdUserName = bssrdUserName;
	}

	public BusiSignRecordVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiSignRecord po = (BusiSignRecord) poObj;
		this.bssrdUuid = po.getBssrdUuid();
		this.bssrdUser = po.getBssrdUser();
		this.bssrdIntegral = po.getBssrdIntegral();
		this.bssrdLastDate = po.getBssrdLastDate()!=null?DateUtil.formatDefaultDate(po.getBssrdLastDate()):"";
		this.bssrdSignDays = po.getBssrdSignDays();
	}

}