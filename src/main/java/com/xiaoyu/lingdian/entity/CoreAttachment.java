package com.xiaoyu.lingdian.entity;

import com.xiaoyu.lingdian.entity.BaseEntity;
import java.util.Date;

/**
* 业务附件表
*/
public class CoreAttachment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	*标识UNID
	*/
	private Integer cratmUnid;

	/**
	*标识UUID
	*/
	private String cratmUuid;

	/**
	*业务实体UUID
	*/
	private String cratmBusUuid;

	/**
	*名称
	*/
	private String cratmFileName;

	/**
	*目录,例a/b
	*/
	private String cratmDir;

	/**
	*文件扩展名
	*/
	private String cratmExtension;

	/**
	*创建日期
	*/
	private Date cratmCdate;

	/**
	*宽度
	*/
	private Integer cratmWidth;

	/**
	*高度
	*/
	private Integer cratmHeight;

	/**
	*类型:1头像2等级图3商品主图4商城端商品封面图5分享端商品封面图6评价图片7商品标签图8公众号二维码9服务类别图10服务动态封面图11导航商品封面图12广告图13商品类别图标14logo15其他999非图片
	*/
	private Integer cratmType;

	public void setCratmUnid(Integer cratmUnid) {
		this.cratmUnid = cratmUnid;
	}

	public Integer getCratmUnid( ) {
		return cratmUnid;
	}

	public void setCratmUuid(String cratmUuid) {
		this.cratmUuid = cratmUuid;
	}

	public String getCratmUuid( ) {
		return cratmUuid;
	}

	public void setCratmBusUuid(String cratmBusUuid) {
		this.cratmBusUuid = cratmBusUuid;
	}

	public String getCratmBusUuid( ) {
		return cratmBusUuid;
	}

	public void setCratmFileName(String cratmFileName) {
		this.cratmFileName = cratmFileName;
	}

	public String getCratmFileName( ) {
		return cratmFileName;
	}

	public void setCratmDir(String cratmDir) {
		this.cratmDir = cratmDir;
	}

	public String getCratmDir( ) {
		return cratmDir;
	}

	public void setCratmExtension(String cratmExtension) {
		this.cratmExtension = cratmExtension;
	}

	public String getCratmExtension( ) {
		return cratmExtension;
	}

	public void setCratmCdate(Date cratmCdate) {
		this.cratmCdate = cratmCdate;
	}

	public Date getCratmCdate( ) {
		return cratmCdate;
	}

	public void setCratmWidth(Integer cratmWidth) {
		this.cratmWidth = cratmWidth;
	}

	public Integer getCratmWidth( ) {
		return cratmWidth;
	}

	public void setCratmHeight(Integer cratmHeight) {
		this.cratmHeight = cratmHeight;
	}

	public Integer getCratmHeight( ) {
		return cratmHeight;
	}

	public void setCratmType(Integer cratmType) {
		this.cratmType = cratmType;
	}

	public Integer getCratmType( ) {
		return cratmType;
	}

	public CoreAttachment( ) { 
	}

}