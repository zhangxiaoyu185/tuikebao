package com.xiaoyu.lingdian.enums;

public enum FreightType {

	ST("申通"),
	YZ("邮政"),
	EMS("EMS"),
	SF("顺丰");

	private String description;

	FreightType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
