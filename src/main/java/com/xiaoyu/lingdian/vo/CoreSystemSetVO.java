package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreSystemSet;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 系统设置表
*/
public class CoreSystemSetVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crsstUuid;

	/**
	*邀请链接前缀
	*/
	private String crsstInviteLink;

	/**
	*推广商品详情前缀
	*/
	private String crsstProductDetails;

	/**
	*短信接口路径
	*/
	private String crsstMessagePath;

	/**
	*短信账户名
	*/
	private String crsstMessageLoginname;

	/**
	*短信密码
	*/
	private String crsstMessagePwd;

	/**
	*短信key
	*/
	private String crsstMessageKey;
	
	/**
	*项目域名
	*/
	private String crsstMessageDomain;

	/**
	*附件存放目录
	*/
	private String crsstAttachmentDir;

	public void setCrsstUuid(String crsstUuid) {
		this.crsstUuid = crsstUuid;
	}

	public String getCrsstUuid( ) {
		return crsstUuid;
	}

	public void setCrsstInviteLink(String crsstInviteLink) {
		this.crsstInviteLink = crsstInviteLink;
	}

	public String getCrsstInviteLink( ) {
		return crsstInviteLink;
	}

	public void setCrsstProductDetails(String crsstProductDetails) {
		this.crsstProductDetails = crsstProductDetails;
	}

	public String getCrsstProductDetails( ) {
		return crsstProductDetails;
	}

	public void setCrsstMessagePath(String crsstMessagePath) {
		this.crsstMessagePath = crsstMessagePath;
	}

	public String getCrsstMessagePath( ) {
		return crsstMessagePath;
	}

	public void setCrsstMessageLoginname(String crsstMessageLoginname) {
		this.crsstMessageLoginname = crsstMessageLoginname;
	}

	public String getCrsstMessageLoginname( ) {
		return crsstMessageLoginname;
	}

	public void setCrsstMessagePwd(String crsstMessagePwd) {
		this.crsstMessagePwd = crsstMessagePwd;
	}

	public String getCrsstMessagePwd( ) {
		return crsstMessagePwd;
	}

	public String getCrsstMessageKey() {
		return crsstMessageKey;
	}

	public void setCrsstMessageKey(String crsstMessageKey) {
		this.crsstMessageKey = crsstMessageKey;
	}

	public void setCrsstMessageDomain(String crsstMessageDomain) {
		this.crsstMessageDomain = crsstMessageDomain;
	}

	public String getCrsstMessageDomain( ) {
		return crsstMessageDomain;
	}

	public void setCrsstAttachmentDir(String crsstAttachmentDir) {
		this.crsstAttachmentDir = crsstAttachmentDir;
	}

	public String getCrsstAttachmentDir( ) {
		return crsstAttachmentDir;
	}

	public CoreSystemSetVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreSystemSet po = (CoreSystemSet) poObj;
		this.crsstUuid = po.getCrsstUuid();
		this.crsstInviteLink = po.getCrsstInviteLink();
		this.crsstProductDetails = po.getCrsstProductDetails();
		this.crsstMessagePath = po.getCrsstMessagePath();
		this.crsstMessageLoginname = po.getCrsstMessageLoginname();
		this.crsstMessagePwd = po.getCrsstMessagePwd();
		this.crsstMessageKey = po.getCrsstMessageKey();
		this.crsstMessageDomain = po.getCrsstMessageDomain();
		this.crsstAttachmentDir = po.getCrsstAttachmentDir();
	}

}