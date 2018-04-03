package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiInvitedRelation;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 邀请关系表
*/
public class BusiInvitedRelationVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bsirnUuid;

	/**
	*邀请人
	*/
	private String bsirnInvited;

	/**
	*邀请人账号
	*/
	private String bsirnInvitedName;
	
	/**
	*邀请人头像
	*/
	private String bsirnInvitedHead;
	
	/**
	*被邀请人
	*/
	private String bsirnBeInvited;

	/**
	*被邀请人账号
	*/
	private String bsirnBeInvitedName;
	
	/**
	*被邀请人头像
	*/
	private String bsirnBeInvitedHead;

	/**
	*所用邀请码
	*/
	private String bsirnCode;

	/**
	*邀请时间
	*/
	private String bsirnIdate;

	public void setBsirnUuid(String bsirnUuid) {
		this.bsirnUuid = bsirnUuid;
	}

	public String getBsirnUuid( ) {
		return bsirnUuid;
	}

	public void setBsirnInvited(String bsirnInvited) {
		this.bsirnInvited = bsirnInvited;
	}

	public String getBsirnInvited( ) {
		return bsirnInvited;
	}

	public void setBsirnBeInvited(String bsirnBeInvited) {
		this.bsirnBeInvited = bsirnBeInvited;
	}

	public String getBsirnBeInvited( ) {
		return bsirnBeInvited;
	}

	public void setBsirnCode(String bsirnCode) {
		this.bsirnCode = bsirnCode;
	}

	public String getBsirnCode( ) {
		return bsirnCode;
	}

	public void setBsirnIdate(String bsirnIdate) {
		this.bsirnIdate = bsirnIdate;
	}

	public String getBsirnIdate( ) {
		return bsirnIdate;
	}

	public String getBsirnInvitedName() {
		return bsirnInvitedName;
	}

	public void setBsirnInvitedName(String bsirnInvitedName) {
		this.bsirnInvitedName = bsirnInvitedName;
	}

	public String getBsirnInvitedHead() {
		return bsirnInvitedHead;
	}

	public void setBsirnInvitedHead(String bsirnInvitedHead) {
		this.bsirnInvitedHead = bsirnInvitedHead;
	}

	public String getBsirnBeInvitedName() {
		return bsirnBeInvitedName;
	}

	public void setBsirnBeInvitedName(String bsirnBeInvitedName) {
		this.bsirnBeInvitedName = bsirnBeInvitedName;
	}

	public String getBsirnBeInvitedHead() {
		return bsirnBeInvitedHead;
	}

	public void setBsirnBeInvitedHead(String bsirnBeInvitedHead) {
		this.bsirnBeInvitedHead = bsirnBeInvitedHead;
	}

	public BusiInvitedRelationVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiInvitedRelation po = (BusiInvitedRelation) poObj;
		this.bsirnUuid = po.getBsirnUuid();
		this.bsirnInvited = po.getBsirnInvited();
		this.bsirnBeInvited = po.getBsirnBeInvited();
		this.bsirnCode = po.getBsirnCode();
		this.bsirnIdate = po.getBsirnIdate()!=null?DateUtil.formatDefaultDate(po.getBsirnIdate()):"";
	}

}