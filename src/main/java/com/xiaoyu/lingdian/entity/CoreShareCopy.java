package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;

/**
* 系统分享文案设置表
*/
public class CoreShareCopy extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer crscyUnid;

	/**
	*标识UUID
	*/
	private String crscyUuid;

	/**
	*文案
	*/
	private String crscyCopy;

	public void setCrscyUnid(Integer crscyUnid) {
		this.crscyUnid = crscyUnid;
	}

	public Integer getCrscyUnid( ) {
		return crscyUnid;
	}

	public void setCrscyUuid(String crscyUuid) {
		this.crscyUuid = crscyUuid;
	}

	public String getCrscyUuid( ) {
		return crscyUuid;
	}

	public void setCrscyCopy(String crscyCopy) {
		this.crscyCopy = crscyCopy;
	}

	public String getCrscyCopy( ) {
		return crscyCopy;
	}

	public CoreShareCopy( ) { 
	}

//<=================定制内容开始==============
//==================定制内容结束==============>

}