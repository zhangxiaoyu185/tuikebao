package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiInvitedRule;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 邀请奖励规则表
*/
public class BusiInvitedRuleVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bsireUuid;

	/**
	*需要邀请人数
	*/
	private Integer bsireCount;

	/**
	*可领取积分
	*/
	private Integer bsireIntegral;

	/**
	*顺序
	*/
	private Integer bsireOrd;

	public void setBsireUuid(String bsireUuid) {
		this.bsireUuid = bsireUuid;
	}

	public String getBsireUuid( ) {
		return bsireUuid;
	}

	public void setBsireCount(Integer bsireCount) {
		this.bsireCount = bsireCount;
	}

	public Integer getBsireCount( ) {
		return bsireCount;
	}

	public void setBsireIntegral(Integer bsireIntegral) {
		this.bsireIntegral = bsireIntegral;
	}

	public Integer getBsireIntegral( ) {
		return bsireIntegral;
	}

	public void setBsireOrd(Integer bsireOrd) {
		this.bsireOrd = bsireOrd;
	}

	public Integer getBsireOrd( ) {
		return bsireOrd;
	}

	public BusiInvitedRuleVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiInvitedRule po = (BusiInvitedRule) poObj;
		this.bsireUuid = po.getBsireUuid();
		this.bsireCount = po.getBsireCount();
		this.bsireIntegral = po.getBsireIntegral();
		this.bsireOrd = po.getBsireOrd();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}