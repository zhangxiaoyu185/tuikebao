package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreJob;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 职业表
*/
public class CoreJobVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crjobUuid;

	/**
	*职业
	*/
	private String crjobName;

	public void setCrjobUuid(String crjobUuid) {
		this.crjobUuid = crjobUuid;
	}

	public String getCrjobUuid( ) {
		return crjobUuid;
	}

	public void setCrjobName(String crjobName) {
		this.crjobName = crjobName;
	}

	public String getCrjobName( ) {
		return crjobName;
	}

	public CoreJobVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreJob po = (CoreJob) poObj;
		this.crjobUuid = po.getCrjobUuid();
		this.crjobName = po.getCrjobName();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}