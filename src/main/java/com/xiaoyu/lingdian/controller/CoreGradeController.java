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
import com.xiaoyu.lingdian.entity.CoreGrade;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreGradeService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.vo.CoreGradeVO;
import com.xiaoyu.lingdian.vo.other.JudgeShareCountVO;

@Controller
@RequestMapping(value="/coreGrade")
public class CoreGradeController extends BaseController {

	/**
	* 等级配置表
	*/
	@Autowired
	private CoreGradeService coreGradeService;
	
   /**
    * 用户表
    */
    @Autowired
    private CoreUserService coreUserService;
	
	/**
	* 添加
	*
	* @param crgdeName 等级名称
	* @param crgdeIcon 等级图标
	* @param crgdeOrd 等级顺序
	* @param crgdeIntegral 所需积分
	* @param crgdeDayShares 每天分享次数
	* @return
	*/
	@RequestMapping(value="/add/coreGrade", method=RequestMethod.POST)
	public void addCoreGrade (String crgdeName, String crgdeIcon, Integer crgdeOrd, Integer crgdeIntegral, Integer crgdeDayShares, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin addCoreGrade");
		if (StringUtil.isEmpty(crgdeName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "等级名称不能为空!"), response);
			logger.info("[CoreGradeController]:end addCoreGrade");
			return;
		}
		if (crgdeOrd == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "等级顺序必填!"), response);
			logger.info("[CoreGradeController]:end addCoreGrade");
			return;
		}
		if (crgdeIntegral == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所需积分必填!"), response);
			logger.info("[CoreGradeController]:end addCoreGrade");
			return;
		}
		if (crgdeDayShares == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "每天分享次数必填!"), response);
			logger.info("[CoreGradeController]:end addCoreGrade");
			return;
		}
		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgdeName(crgdeName);
		CoreGrade newCoreGrade = coreGradeService.getCoreGradeByName(coreGrade);
		if (null != newCoreGrade) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该等级名称已存在!"), response);
			logger.info("[CoreGradeController]:end addCoreGrade");
			return;
		}

		String uuid = RandomUtil.generateString(16);
		coreGrade.setCrgdeUuid(uuid);
		coreGrade.setCrgdeIcon(crgdeIcon);
		coreGrade.setCrgdeOrd(crgdeOrd);
		coreGrade.setCrgdeIntegral(crgdeIntegral);
		coreGrade.setCrgdeDayShares(crgdeDayShares);

		coreGradeService.insertCoreGrade(coreGrade);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreGradeController]:end addCoreGrade");

	}

	/**
	* 修改
	*
	* @param crgdeUuid 标识UUID
	* @param crgdeIcon 等级图标
	* @param crgdeOrd 等级顺序
	* @param crgdeIntegral 所需积分
	* @param crgdeDayShares 每天分享次数
	* @return
	*/
	@RequestMapping(value="/update/coreGrade", method=RequestMethod.POST)
	public void updateCoreGrade (String crgdeUuid, String crgdeIcon, Integer crgdeOrd, Integer crgdeIntegral, Integer crgdeDayShares, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin updateCoreGrade");
		if (StringUtil.isEmpty(crgdeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreGradeController]:end updateCoreGrade");
			return;
		}
		if (crgdeOrd == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "等级顺序必填!"), response);
			logger.info("[CoreGradeController]:end updateCoreGrade");
			return;
		}
		if (crgdeIntegral == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所需积分必填!"), response);
			logger.info("[CoreGradeController]:end updateCoreGrade");
			return;
		}
		if (crgdeDayShares == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "每天分享次数必填!"), response);
			logger.info("[CoreGradeController]:end updateCoreGrade");
			return;
		}
		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgdeUuid(crgdeUuid);
		coreGrade.setCrgdeIcon(crgdeIcon);
		coreGrade.setCrgdeOrd(crgdeOrd);
		coreGrade.setCrgdeIntegral(crgdeIntegral);
		coreGrade.setCrgdeDayShares(crgdeDayShares);

		coreGradeService.updateCoreGrade(coreGrade);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreGradeController]:end updateCoreGrade");

	}

	/**
	* 删除
	*
	* @param crgdeUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreGrade (String crgdeUuid, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin deleteCoreGrade");
		if (StringUtil.isEmpty(crgdeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreGradeController]:end deleteCoreGrade");
			return;
		}
		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgdeUuid(crgdeUuid);

		coreGradeService.deleteCoreGrade(coreGrade);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreGradeController]:end deleteCoreGrade");

	}

	/**
	* 批量删除
	*
	* @param crgdeUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreGrade (String crgdeUuids, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin deleteBatchCoreGrade");
		if (StringUtil.isEmpty(crgdeUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[CoreGradeController]:end deleteBatchCoreGrade");
			return;
		}
		String[] uuids=crgdeUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreGradeController]:end deleteBatchCoreGrade");
			return;
		}
		coreGradeService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreGradeController]:end deleteBatchCoreGrade");

	}

	/**
	* 获取单个
	*
	* @param crgdeUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreGrade (String crgdeUuid, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin viewsCoreGrade");
		if (StringUtil.isEmpty(crgdeUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreGradeController]:end viewsCoreGrade");
			return;
		}
		CoreGrade coreGrade = new CoreGrade();
		coreGrade.setCrgdeUuid(crgdeUuid);

		coreGrade = coreGradeService.getCoreGrade(coreGrade);

		CoreGradeVO coreGradeVO = new CoreGradeVO();
		coreGradeVO.convertPOToVO(coreGrade);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreGradeVO), response);
		logger.info("[CoreGradeController]:end viewsCoreGrade");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreGradeList (HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin findCoreGradeList");
		List<CoreGrade> lists = coreGradeService.findCoreGradeList();
		List<CoreGradeVO> vos = new ArrayList<CoreGradeVO>();
		CoreGradeVO vo;
		for (CoreGrade coreGrade : lists) {
			vo = new CoreGradeVO();
			vo.convertPOToVO(coreGrade);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreGradeController]:end findCoreGradeList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param crgdeName 等级名称
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreGradePage (String crgdeName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreGradeController]:begin findCoreGradePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<CoreGrade> page = coreGradeService.findCoreGradePage(crgdeName, pageNum, pageSize);
		Page<CoreGradeVO> pageVO = new Page<CoreGradeVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreGradeVO> vos = new ArrayList<CoreGradeVO>();
		CoreGradeVO vo;
		for (CoreGrade coreGrade : page.getResult()) {
			vo = new CoreGradeVO();
			vo.convertPOToVO(coreGrade);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[CoreGradeController]:end findCoreGradePage");
	}
	
	/**
     * 获取个人信息  获取等级配置信息
     * 判断用户分享次数 
     * @param crusrUuid 标识UUID
     * @return
     */
     @RequestMapping(value="/judge/share/count", method=RequestMethod.POST)
     public void judgeCoreUserShareCount (String crusrUuid, HttpServletResponse response) {
         logger.info("[CoreGradeController]:begin judgeCoreUserShareCount");

         if (StringUtil.isEmpty(crusrUuid)) {
             writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
             logger.info("[CoreGradeController]:end judgeCoreUserShareCount");
             return;
         }

         CoreUser coreUser = new CoreUser();
         coreUser.setCrusrUuid(crusrUuid);
         coreUser = coreUserService.getCoreUser(coreUser);
         if (null == coreUser) {
             writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
             logger.info("[CoreGradeController]:end judgeCoreUserShareCount");
             return;
         }
         //查询用户等级所对应的分享配置信息
         CoreGrade coreGrade = new CoreGrade();
         if (StringUtil.isEmpty(coreUser.getCrusrGrade())) {
             writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户等级信息不存在!"), response);
             logger.info("[CoreGradeController]:end judgeCoreUserShareCount");
             return;
         }
         coreGrade.setCrgdeUuid(coreUser.getCrusrGrade());
         coreGrade = coreGradeService.getCoreGrade(coreGrade);
         
         JudgeShareCountVO judgeShareCountVO = new JudgeShareCountVO();
         judgeShareCountVO.setGradeName(coreGrade.getCrgdeName());
         judgeShareCountVO.setGradeShareCount(coreGrade.getCrgdeDayShares());
         judgeShareCountVO.setNowShareCount(coreUser.getCrusrNowShare());

         //判断当日分享人数与等级配置人数
         if (coreGrade.getCrgdeDayShares()>coreUser.getCrusrNowShare()){
             writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "当日分享次数还未满", judgeShareCountVO), response);
             logger.info("[CoreGradeController]:end judgeCoreUserShareCount");
             return;
         }

         writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "当日分享次数已满", judgeShareCountVO), response);
         logger.info("[CoreGradeController]:end judgeCoreUserShareCount");
     }

}