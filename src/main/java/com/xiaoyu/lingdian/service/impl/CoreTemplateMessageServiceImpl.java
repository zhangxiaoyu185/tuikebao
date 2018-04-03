package com.xiaoyu.lingdian.service.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.core.mybatis.page.PageRequest;
import com.xiaoyu.lingdian.service.CoreTemplateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyu.lingdian.core.mybatis.dao.MyBatisDAO;
import com.xiaoyu.lingdian.entity.CoreTemplateMessage;

/**
* 模板消息日志记录表
*/
@Service("coreTemplateMessageService")
public class CoreTemplateMessageServiceImpl implements CoreTemplateMessageService {

	@Autowired
	private MyBatisDAO myBatisDAO;

	@Override
	public boolean insertCoreTemplateMessage(CoreTemplateMessage coreTemplateMessage) {
		myBatisDAO.insert(coreTemplateMessage);
		return true;
	}

	@Override
	public boolean updateCoreTemplateMessage(CoreTemplateMessage coreTemplateMessage) {
		myBatisDAO.update(coreTemplateMessage);
		return true;
	}

	@Override
	public boolean deleteCoreTemplateMessage(CoreTemplateMessage coreTemplateMessage) {
		myBatisDAO.delete(coreTemplateMessage);
		return true;
	}

	@Override
	public boolean deleteBatchByIds(List<String> list ) {
		if (list.size() <= 0) {
			return true;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", list);
		myBatisDAO.delete("deleteBatchCoreTemplateMessageByIds",hashMap);
		return true;
	}

	@Override
	public CoreTemplateMessage getCoreTemplateMessage(CoreTemplateMessage coreTemplateMessage) {
		return (CoreTemplateMessage) myBatisDAO.findForObject(coreTemplateMessage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoreTemplateMessage> findCoreTemplateMessageList() {
		return myBatisDAO.findForList("findCoreTemplateMessageForLists", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CoreTemplateMessage> findCoreTemplateMessagePage(int pageNum, int pageSize) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		return myBatisDAO.findForPage("findCoreTemplateMessageForPages", new PageRequest(pageNum, pageSize, hashMap));
	}

}