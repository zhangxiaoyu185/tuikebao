package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.BusiExchangeRule;
import com.xiaoyu.lingdian.vo.BaseVO;

/**
* 积分兑换话费设置表
*/
public class BusiExchangeRuleVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String bseeeUuid;

	/**
	*充值话费
	*/
	private Double bseeeBill;

	/**
	*所需积分
	*/
	private Integer bseeeIntegral;

	/**
	*显示顺序
	*/
	private Integer bseeeOrd;

	public void setBseeeUuid(String bseeeUuid) {
		this.bseeeUuid = bseeeUuid;
	}

	public String getBseeeUuid( ) {
		return bseeeUuid;
	}

	public void setBseeeBill(Double bseeeBill) {
		this.bseeeBill = bseeeBill;
	}

	public Double getBseeeBill( ) {
		return bseeeBill;
	}

	public void setBseeeIntegral(Integer bseeeIntegral) {
		this.bseeeIntegral = bseeeIntegral;
	}

	public Integer getBseeeIntegral( ) {
		return bseeeIntegral;
	}

	public Integer getBseeeOrd() {
		return bseeeOrd;
	}

	public void setBseeeOrd(Integer bseeeOrd) {
		this.bseeeOrd = bseeeOrd;
	}

	public BusiExchangeRuleVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		BusiExchangeRule po = (BusiExchangeRule) poObj;
		this.bseeeUuid = po.getBseeeUuid();
		this.bseeeBill = po.getBseeeBill();
		this.bseeeIntegral = po.getBseeeIntegral();
		this.bseeeOrd = po.getBseeeOrd();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}