package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreAttachment;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 业务附件表
*/
public class CoreAttachmentVO implements BaseVO {

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
	private String cratmCdate;

	/**
	*宽度
	*/
	private Integer cratmWidth;

	/**
	*高度
	*/
	private Integer cratmHeight;

	/**
	*1用户头像2淘品标签3淘品二维码(150)4签名二维码(300)5求签背景图6求签二维码(120)7奥运二维码(160)8不干胶广告图999非图片
	*/
	private Integer cratmType;

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

	public void setCratmCdate(String cratmCdate) {
		this.cratmCdate = cratmCdate;
	}

	public String getCratmCdate( ) {
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

	public CoreAttachmentVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreAttachment po = (CoreAttachment) poObj;
		this.cratmUuid = po.getCratmUuid();
		this.cratmBusUuid = po.getCratmBusUuid();
		this.cratmFileName = po.getCratmFileName();
		this.cratmDir = po.getCratmDir();
		this.cratmExtension = po.getCratmExtension();
		this.cratmCdate = po.getCratmCdate()!=null?DateUtil.formatDefaultDate(po.getCratmCdate()):"";
		this.cratmWidth = po.getCratmWidth();
		this.cratmHeight = po.getCratmHeight();
		this.cratmType = po.getCratmType();
	}

}