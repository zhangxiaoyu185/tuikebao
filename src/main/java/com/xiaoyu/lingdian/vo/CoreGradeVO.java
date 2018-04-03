package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreGrade;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 等级配置表
*/
public class CoreGradeVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crgdeUuid;

	/**
	*等级名称
	*/
	private String crgdeName;

	/**
	*等级图标
	*/
	private String crgdeIcon;

	/**
	*等级顺序
	*/
	private Integer crgdeOrd;

	/**
	*所需积分
	*/
	private Integer crgdeIntegral;

	/**
	*每天分享次数
	*/
	private Integer crgdeDayShares;

	public void setCrgdeUuid(String crgdeUuid) {
		this.crgdeUuid = crgdeUuid;
	}

	public String getCrgdeUuid( ) {
		return crgdeUuid;
	}

	public void setCrgdeName(String crgdeName) {
		this.crgdeName = crgdeName;
	}

	public String getCrgdeName( ) {
		return crgdeName;
	}

	public void setCrgdeIcon(String crgdeIcon) {
		this.crgdeIcon = crgdeIcon;
	}

	public String getCrgdeIcon( ) {
		return crgdeIcon;
	}

	public void setCrgdeOrd(Integer crgdeOrd) {
		this.crgdeOrd = crgdeOrd;
	}

	public Integer getCrgdeOrd( ) {
		return crgdeOrd;
	}

	public void setCrgdeIntegral(Integer crgdeIntegral) {
		this.crgdeIntegral = crgdeIntegral;
	}

	public Integer getCrgdeIntegral( ) {
		return crgdeIntegral;
	}

	public void setCrgdeDayShares(Integer crgdeDayShares) {
		this.crgdeDayShares = crgdeDayShares;
	}

	public Integer getCrgdeDayShares( ) {
		return crgdeDayShares;
	}

	public CoreGradeVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreGrade po = (CoreGrade) poObj;
		this.crgdeUuid = po.getCrgdeUuid();
		this.crgdeName = po.getCrgdeName();
		this.crgdeIcon = po.getCrgdeIcon();
		this.crgdeOrd = po.getCrgdeOrd();
		this.crgdeIntegral = po.getCrgdeIntegral();
		this.crgdeDayShares = po.getCrgdeDayShares();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}