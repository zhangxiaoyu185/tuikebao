package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiInvitedActivity;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 邀请活动表
*/
public class BusiInvitedActivityVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bsiayUuid;

	/**
	*活动期数
	*/
	private String bsiayPeriods;

	/**
	*创建时间
	*/
	private String bsiayCdate;

	public void setBsiayUuid(String bsiayUuid) {
		this.bsiayUuid = bsiayUuid;
	}

	public String getBsiayUuid( ) {
		return bsiayUuid;
	}

	public void setBsiayPeriods(String bsiayPeriods) {
		this.bsiayPeriods = bsiayPeriods;
	}

	public String getBsiayPeriods( ) {
		return bsiayPeriods;
	}

	public void setBsiayCdate(String bsiayCdate) {
		this.bsiayCdate = bsiayCdate;
	}

	public String getBsiayCdate( ) {
		return bsiayCdate;
	}

	public BusiInvitedActivityVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiInvitedActivity po = (BusiInvitedActivity) poObj;
		this.bsiayUuid = po.getBsiayUuid();
		this.bsiayPeriods = po.getBsiayPeriods();
		this.bsiayCdate = po.getBsiayCdate()!=null?DateUtil.formatDefaultDate(po.getBsiayCdate()):"";
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}