package com.xiaoyu.lingdian.service;

import com.xiaoyu.lingdian.entity.CoreTemplateMessage;
import java.util.List;
import com.xiaoyu.lingdian.core.mybatis.page.Page;

/**
* 模板消息日志记录表
*/
public interface CoreTemplateMessageService {

	/**
	* 添加
	* @param coreTemplateMessage
	* @return
	*/
	public boolean insertCoreTemplateMessage(CoreTemplateMessage coreTemplateMessage);

	/**
	* 修改
	* @param coreTemplateMessage
	* @return
	*/
	public boolean updateCoreTemplateMessage(CoreTemplateMessage coreTemplateMessage);

	/**
	* 删除
	* @param coreTemplateMessage
	* @return
	*/
	public boolean deleteCoreTemplateMessage(CoreTemplateMessage coreTemplateMessage);

	/**
	* 批量删除
	* @param list
	* @return boolean
	*/
	public boolean deleteBatchByIds(List<String> list);

	/**
	* 查询
	* @param coreTemplateMessage
	* @return
	*/
	public CoreTemplateMessage getCoreTemplateMessage(CoreTemplateMessage coreTemplateMessage);

	/**
	* 查询所有List
	* @return List
	*/
	public List<CoreTemplateMessage> findCoreTemplateMessageList();

	/**
	* 查询所有Page
	* @return Page
	*/
	public Page<CoreTemplateMessage> findCoreTemplateMessagePage(int pageNum, int pageSize);

//<=================定制内容开始==============
//==================定制内容结束==============>

}