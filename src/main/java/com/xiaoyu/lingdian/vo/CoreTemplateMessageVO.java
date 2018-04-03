package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreTemplateMessage;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 模板消息日志记录表
*/
public class CoreTemplateMessageVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crtmeUuid;

	/**
	*所属用户
	*/
	private String crtmeUser;

	/**
	*所属用户手机号
	*/
	private String crtmeMobile;

	/**
	*消息内容
	*/
	private String crtmeContent;

	/**
	*发送时间
	*/
	private String crtmeTime;

	/**
	*发送状态:1成功,0失败
	*/
	private Integer crtmeStatus;

	/**
	*模板消息类型
	*/
	private Integer crtmeType;

	/**
	*模板消息标识
	*/
	private String crtmeSign;

	public void setCrtmeUuid(String crtmeUuid) {
		this.crtmeUuid = crtmeUuid;
	}

	public String getCrtmeUuid( ) {
		return crtmeUuid;
	}

	public void setCrtmeUser(String crtmeUser) {
		this.crtmeUser = crtmeUser;
	}

	public String getCrtmeUser( ) {
		return crtmeUser;
	}

	public void setCrtmeMobile(String crtmeMobile) {
		this.crtmeMobile = crtmeMobile;
	}

	public String getCrtmeMobile( ) {
		return crtmeMobile;
	}

	public void setCrtmeContent(String crtmeContent) {
		this.crtmeContent = crtmeContent;
	}

	public String getCrtmeContent( ) {
		return crtmeContent;
	}

	public void setCrtmeTime(String crtmeTime) {
		this.crtmeTime = crtmeTime;
	}

	public String getCrtmeTime( ) {
		return crtmeTime;
	}

	public void setCrtmeStatus(Integer crtmeStatus) {
		this.crtmeStatus = crtmeStatus;
	}

	public Integer getCrtmeStatus( ) {
		return crtmeStatus;
	}

	public void setCrtmeType(Integer crtmeType) {
		this.crtmeType = crtmeType;
	}

	public Integer getCrtmeType( ) {
		return crtmeType;
	}

	public void setCrtmeSign(String crtmeSign) {
		this.crtmeSign = crtmeSign;
	}

	public String getCrtmeSign( ) {
		return crtmeSign;
	}

	public CoreTemplateMessageVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreTemplateMessage po = (CoreTemplateMessage) poObj;
		this.crtmeUuid = po.getCrtmeUuid();
		this.crtmeUser = po.getCrtmeUser();
		this.crtmeContent = po.getCrtmeContent();
		this.crtmeTime = po.getCrtmeTime()!=null?DateUtil.formatDefaultDate(po.getCrtmeTime()):"";
		this.crtmeStatus = po.getCrtmeStatus();
		this.crtmeType = po.getCrtmeType();
		this.crtmeSign = po.getCrtmeSign();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}