package com.xiaoyu.lingdian.controller.invited;

import java.util.Date;
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
import com.xiaoyu.lingdian.entity.BusiInvitedActivity;
import com.xiaoyu.lingdian.service.invited.BusiInvitedActivityService;
import com.xiaoyu.lingdian.vo.BusiInvitedActivityVO;

@Controller
@RequestMapping(value="/busiInvitedActivity")
public class BusiInvitedActivityController extends BaseController {

	/**
	* 邀请活动表
	*/
	@Autowired
	private BusiInvitedActivityService busiInvitedActivityService;
	
	/**
	* 添加
	*
	* @param bsiayPeriods 活动期数
	* @param bsiayCdate 创建时间
	* @return
	*/
	@RequestMapping(value="/add/busiInvitedActivity", method=RequestMethod.POST)
	public void addBusiInvitedActivity (String bsiayPeriods, String bsiayCdate, HttpServletResponse response) {
		logger.info("[BusiInvitedActivityController]:begin addBusiInvitedActivity");
		BusiInvitedActivity busiInvitedActivity = new BusiInvitedActivity();
		String uuid = RandomUtil.generateString(16);
		busiInvitedActivity.setBsiayUuid(uuid);
		busiInvitedActivity.setBsiayPeriods(bsiayPeriods);
		busiInvitedActivity.setBsiayCdate(new Date());

		busiInvitedActivityService.insertBusiInvitedActivity(busiInvitedActivity);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiInvitedActivityController]:end addBusiInvitedActivity");

	}

	/**
	* 修改
	*
	* @param bsiayUuid 标识UUID
	* @param bsiayPeriods 活动期数
	* @param bsiayCdate 创建时间
	* @return
	*/
	@RequestMapping(value="/update/busiInvitedActivity", method=RequestMethod.POST)
	public void updateBusiInvitedActivity (String bsiayUuid, String bsiayPeriods, String bsiayCdate, HttpServletResponse response) {
		logger.info("[BusiInvitedActivityController]:begin updateBusiInvitedActivity");
		if (StringUtil.isEmpty(bsiayUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiInvitedActivity busiInvitedActivity = new BusiInvitedActivity();
		busiInvitedActivity.setBsiayUuid(bsiayUuid);
		busiInvitedActivity.setBsiayPeriods(bsiayPeriods);
		busiInvitedActivity.setBsiayCdate(new Date());

		busiInvitedActivityService.updateBusiInvitedActivity(busiInvitedActivity);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiInvitedActivityController]:end updateBusiInvitedActivity");

	}

	/**
	* 删除
	*
	* @param bsiayUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiInvitedActivity (String bsiayUuid, HttpServletResponse response) {
		logger.info("[BusiInvitedActivityController]:begin deleteBusiInvitedActivity");
		if (StringUtil.isEmpty(bsiayUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiInvitedActivity busiInvitedActivity = new BusiInvitedActivity();
		busiInvitedActivity.setBsiayUuid(bsiayUuid);

		busiInvitedActivityService.deleteBusiInvitedActivity(busiInvitedActivity);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiInvitedActivityController]:end deleteBusiInvitedActivity");

	}

	/**
	* 批量删除
	*
	* @param bsiayUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiInvitedActivity (String bsiayUuids, HttpServletResponse response) {
		logger.info("[BusiInvitedActivityController]:begin deleteBatchBusiInvitedActivity");
		if (StringUtil.isEmpty(bsiayUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=bsiayUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		busiInvitedActivityService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiInvitedActivityController]:end deleteBatchBusiInvitedActivity");

	}

	/**
	* 获取单个
	*
	* @param bsiayUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiInvitedActivity (String bsiayUuid, HttpServletResponse response) {
		logger.info("[BusiInvitedActivityController]:begin viewsBusiInvitedActivity");
		if (StringUtil.isEmpty(bsiayUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiInvitedActivity busiInvitedActivity = new BusiInvitedActivity();
		busiInvitedActivity.setBsiayUuid(bsiayUuid);

		busiInvitedActivity = busiInvitedActivityService.getBusiInvitedActivity(busiInvitedActivity);

		BusiInvitedActivityVO busiInvitedActivityVO = new BusiInvitedActivityVO();
		busiInvitedActivityVO.convertPOToVO(busiInvitedActivity);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiInvitedActivityVO), response);
		logger.info("[BusiInvitedActivityController]:end viewsBusiInvitedActivity");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiInvitedActivityList (HttpServletResponse response) {
		logger.info("[BusiInvitedActivityController]:begin findBusiInvitedActivityList");
		List<BusiInvitedActivity> lists = busiInvitedActivityService.findBusiInvitedActivityList();
		List<BusiInvitedActivityVO> vos = new ArrayList<BusiInvitedActivityVO>();
		BusiInvitedActivityVO vo;
		for (BusiInvitedActivity busiInvitedActivity : lists) {
			vo = new BusiInvitedActivityVO();
			vo.convertPOToVO(busiInvitedActivity);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiInvitedActivityController]:end findBusiInvitedActivityList");

	}
	 

	/**
	* 获取列表<Page>
	* 
	* @param bsiayPeriods
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiInvitedActivityPage (String bsiayPeriods, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiInvitedActivityController]:begin findBusiInvitedActivityPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiInvitedActivity> page = busiInvitedActivityService.findBusiInvitedActivityPage(bsiayPeriods, pageNum, pageSize);
		Page<BusiInvitedActivityVO> pageVO = new Page<BusiInvitedActivityVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiInvitedActivityVO> vos = new ArrayList<BusiInvitedActivityVO>();
		BusiInvitedActivityVO vo;
		for (BusiInvitedActivity busiInvitedActivity : page.getResult()) {
			vo = new BusiInvitedActivityVO();			vo.convertPOToVO(busiInvitedActivity);			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiInvitedActivityController]:end findBusiInvitedActivityPage");
	}

	/** 
	 * 获取最新一期的期数 
	 */
	@RequestMapping(value="/find/latest", method=RequestMethod.POST)
	public void getBusiInvitedActivityLatest (HttpServletResponse response) {
		logger.info("[BusiInvitedActivityController]:begin getBusiInvitedActivityLatest");

		BusiInvitedActivity latest = busiInvitedActivityService.getBusiInvitedActivityLatest();
		  
		BusiInvitedActivityVO busiInvitedActivityVO = new BusiInvitedActivityVO();
		busiInvitedActivityVO.convertPOToVO(latest);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取最新一期的期数成功", busiInvitedActivityVO), response);
		logger.info("[BusiInvitedActivityController]:end getBusiInvitedActivityLatest");

	}

}