package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreShareCopy;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 系统分享文案设置表
*/
public class CoreShareCopyVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crscyUuid;

	/**
	*文案
	*/
	private String crscyCopy;

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

	public CoreShareCopyVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreShareCopy po = (CoreShareCopy) poObj;
		this.crscyUuid = po.getCrscyUuid();
		this.crscyCopy = po.getCrscyCopy();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}