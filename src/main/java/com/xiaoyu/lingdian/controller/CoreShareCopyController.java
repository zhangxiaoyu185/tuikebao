package com.xiaoyu.lingdian.controller;

import java.util.List;
import java.util.ArrayList;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.CoreShareCopy;
import com.xiaoyu.lingdian.service.CoreShareCopyService;
import com.xiaoyu.lingdian.vo.CoreShareCopyVO;

@Controller
@RequestMapping(value="/coreShareCopy")
public class CoreShareCopyController extends BaseController {

	/**
	* 系统分享文案设置表
	*/
	@Autowired
	private CoreShareCopyService coreShareCopyService;
	
	/**
	* 添加
	*
	* @param crscyCopy 文案
	* @return
	*/
	@RequestMapping(value="/add/coreShareCopy", method=RequestMethod.POST)
	public void addCoreShareCopy (String crscyCopy, HttpServletResponse response) {
		logger.info("[CoreShareCopyController]:begin addCoreShareCopy");
		CoreShareCopy coreShareCopy = new CoreShareCopy();
		String uuid = RandomUtil.generateString(16);
		coreShareCopy.setCrscyUuid(uuid);
		coreShareCopy.setCrscyCopy(crscyCopy);

		coreShareCopyService.insertCoreShareCopy(coreShareCopy);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreShareCopyController]:end addCoreShareCopy");

	}

	/**
	* 修改
	*
	* @param crscyUuid 标识UUID
	* @param crscyCopy 文案
	* @return
	*/
	@RequestMapping(value="/update/coreShareCopy", method=RequestMethod.POST)
	public void updateCoreShareCopy (String crscyUuid, String crscyCopy, HttpServletResponse response) {
		logger.info("[CoreShareCopyController]:begin updateCoreShareCopy");
		if (StringUtil.isEmpty(crscyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreShareCopyController]:end updateCoreShareCopy");
			return;
		}
		CoreShareCopy coreShareCopy = new CoreShareCopy();
		coreShareCopy.setCrscyUuid(crscyUuid);
		coreShareCopy.setCrscyCopy(crscyCopy);

		coreShareCopyService.updateCoreShareCopy(coreShareCopy);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreShareCopyController]:end updateCoreShareCopy");

	}

	/**
	* 删除
	*
	* @param crscyUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreShareCopy (String crscyUuid, HttpServletResponse response) {
		logger.info("[CoreShareCopyController]:begin deleteCoreShareCopy");
		if (StringUtil.isEmpty(crscyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreShareCopyController]:end deleteCoreShareCopy");
			return;
		}
		CoreShareCopy coreShareCopy = new CoreShareCopy();
		coreShareCopy.setCrscyUuid(crscyUuid);

		coreShareCopyService.deleteCoreShareCopy(coreShareCopy);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreShareCopyController]:end deleteCoreShareCopy");

	}

	/**
	* 批量删除
	*
	* @param crscyUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreShareCopy (String crscyUuids, HttpServletResponse response) {
		logger.info("[CoreShareCopyController]:begin deleteBatchCoreShareCopy");
		if (StringUtil.isEmpty(crscyUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[CoreShareCopyController]:end deleteBatchCoreShareCopy");
			return;
		}
		String[] uuids=crscyUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreShareCopyController]:end deleteBatchCoreShareCopy");
			return;
		}
		coreShareCopyService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreShareCopyController]:end deleteBatchCoreShareCopy");

	}

	/**
	* 获取单个
	*
	* @param crscyUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreShareCopy (String crscyUuid, HttpServletResponse response) {
		logger.info("[CoreShareCopyController]:begin viewsCoreShareCopy");
		if (StringUtil.isEmpty(crscyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreShareCopyController]:end viewsCoreShareCopy");
			return;
		}
		CoreShareCopy coreShareCopy = new CoreShareCopy();
		coreShareCopy.setCrscyUuid(crscyUuid);

		coreShareCopy = coreShareCopyService.getCoreShareCopy(coreShareCopy);

		CoreShareCopyVO coreShareCopyVO = new CoreShareCopyVO();
		coreShareCopyVO.convertPOToVO(coreShareCopy);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreShareCopyVO), response);
		logger.info("[CoreShareCopyController]:end viewsCoreShareCopy");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreShareCopyList (HttpServletResponse response) {
		logger.info("[CoreShareCopyController]:begin findCoreShareCopyList");
		List<CoreShareCopy> lists = coreShareCopyService.findCoreShareCopyList();
		List<CoreShareCopyVO> vos = new ArrayList<CoreShareCopyVO>();
		CoreShareCopyVO vo;
		for (CoreShareCopy coreShareCopy : lists) {
			vo = new CoreShareCopyVO();
			vo.convertPOToVO(coreShareCopy);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreShareCopyController]:end findCoreShareCopyList");

	}

	/**
	* 获取列表<Page>
	* @param crscyCopy 文案
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreShareCopyPage (String crscyCopy, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreShareCopyController]:begin findCoreShareCopyPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreShareCopy> page = coreShareCopyService.findCoreShareCopyPage(crscyCopy, pageNum, pageSize);
		Page<CoreShareCopyVO> pageVO = new Page<CoreShareCopyVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreShareCopyVO> vos = new ArrayList<CoreShareCopyVO>();
		CoreShareCopyVO vo;
		for (CoreShareCopy coreShareCopy : page.getResult()) {
			vo = new CoreShareCopyVO();
			vo.convertPOToVO(coreShareCopy);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreShareCopyController]:end findCoreShareCopyPage");

	}

}