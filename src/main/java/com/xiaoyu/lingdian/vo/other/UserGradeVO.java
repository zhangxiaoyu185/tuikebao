package com.xiaoyu.lingdian.vo.other;

import com.xiaoyu.lingdian.entity.CoreGrade;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 等级配置表
*/
public class UserGradeVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crusrUuid;

	/**
	*帐户名称
	*/
	private String crusrName;
	
	/**
	*头像附件
	*/
	private String crusrHead;
	
	/**
	*当前积分
	*/
	private Integer crusrIntegral;

	/**
	*下一个等级标识UUID
	*/
	private String nextCrgdeUuid;

	/**
	*下一个等级名称
	*/
	private String nextCrgdeName;

	/**
	*下一个等级图标
	*/
	private String nextCrgdeIcon;

	/**
	*下一个等级顺序
	*/
	private Integer nextCrgdeOrd;

	/**
	*下一个等级所需积分
	*/
	private Integer nextCrgdeIntegral;

	/**
	*下一个等级每天分享次数
	*/
	private Integer nextCrgdeDayShares;

	public String getNextCrgdeUuid() {
		return nextCrgdeUuid;
	}

	public void setNextCrgdeUuid(String nextCrgdeUuid) {
		this.nextCrgdeUuid = nextCrgdeUuid;
	}

	public String getNextCrgdeName() {
		return nextCrgdeName;
	}

	public void setNextCrgdeName(String nextCrgdeName) {
		this.nextCrgdeName = nextCrgdeName;
	}

	public String getNextCrgdeIcon() {
		return nextCrgdeIcon;
	}

	public void setNextCrgdeIcon(String nextCrgdeIcon) {
		this.nextCrgdeIcon = nextCrgdeIcon;
	}

	public Integer getNextCrgdeOrd() {
		return nextCrgdeOrd;
	}

	public void setNextCrgdeOrd(Integer nextCrgdeOrd) {
		this.nextCrgdeOrd = nextCrgdeOrd;
	}

	public Integer getNextCrgdeIntegral() {
		return nextCrgdeIntegral;
	}

	public void setNextCrgdeIntegral(Integer nextCrgdeIntegral) {
		this.nextCrgdeIntegral = nextCrgdeIntegral;
	}

	public Integer getNextCrgdeDayShares() {
		return nextCrgdeDayShares;
	}

	public void setNextCrgdeDayShares(Integer nextCrgdeDayShares) {
		this.nextCrgdeDayShares = nextCrgdeDayShares;
	}

	public String getCrusrUuid() {
		return crusrUuid;
	}

	public void setCrusrUuid(String crusrUuid) {
		this.crusrUuid = crusrUuid;
	}

	public String getCrusrName() {
		return crusrName;
	}

	public void setCrusrName(String crusrName) {
		this.crusrName = crusrName;
	}

	public String getCrusrHead() {
		return crusrHead;
	}

	public void setCrusrHead(String crusrHead) {
		this.crusrHead = crusrHead;
	}

	public Integer getCrusrIntegral() {
		return crusrIntegral;
	}

	public void setCrusrIntegral(Integer crusrIntegral) {
		this.crusrIntegral = crusrIntegral;
	}

	public UserGradeVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreGrade po = (CoreGrade) poObj;
		this.nextCrgdeUuid = po.getCrgdeUuid();
		this.nextCrgdeName = po.getCrgdeName();
		this.nextCrgdeIcon = po.getCrgdeIcon();
		this.nextCrgdeOrd = po.getCrgdeOrd();
		this.nextCrgdeIntegral = po.getCrgdeIntegral();
		this.nextCrgdeDayShares = po.getCrgdeDayShares();
	}

}