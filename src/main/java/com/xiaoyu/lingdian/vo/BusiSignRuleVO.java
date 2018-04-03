package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiSignRule;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 签到设置规则表
*/
public class BusiSignRuleVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bssreUuid;

	/**
	*连续天数
	*/
	private Integer bssreCount;

	/**
	*可领取积分
	*/
	private Integer bssreIntegral;

	/**
	*顺序
	*/
	private Integer bssreOrd;

	public void setBssreUuid(String bssreUuid) {
		this.bssreUuid = bssreUuid;
	}

	public String getBssreUuid( ) {
		return bssreUuid;
	}

	public void setBssreCount(Integer bssreCount) {
		this.bssreCount = bssreCount;
	}

	public Integer getBssreCount( ) {
		return bssreCount;
	}

	public void setBssreIntegral(Integer bssreIntegral) {
		this.bssreIntegral = bssreIntegral;
	}

	public Integer getBssreIntegral( ) {
		return bssreIntegral;
	}

	public void setBssreOrd(Integer bssreOrd) {
		this.bssreOrd = bssreOrd;
	}

	public Integer getBssreOrd( ) {
		return bssreOrd;
	}

	public BusiSignRuleVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiSignRule po = (BusiSignRule) poObj;
		this.bssreUuid = po.getBssreUuid();
		this.bssreCount = po.getBssreCount();
		this.bssreIntegral = po.getBssreIntegral();
		this.bssreOrd = po.getBssreOrd();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}