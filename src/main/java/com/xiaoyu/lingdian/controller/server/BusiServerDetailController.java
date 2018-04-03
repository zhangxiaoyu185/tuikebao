package com.xiaoyu.lingdian.controller.server;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.xiaoyu.lingdian.tool.DateUtil;
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
import com.xiaoyu.lingdian.entity.BusiServerDetail;
import com.xiaoyu.lingdian.service.server.BusiServerDetailService;
import com.xiaoyu.lingdian.vo.BusiServerDetailVO;

@Controller
@RequestMapping(value="/busiServerDetail")
public class BusiServerDetailController extends BaseController {

	/**
	* 服务详情页面表
	*/
	@Autowired
	private BusiServerDetailService busiServerDetailService;
	
	/**
	* 添加
	*
	* @param bssdlTitle Title内容
	* @param bssdlSeo SEO关键字
	* @param bssdlHtml 页面html内容
	* @param bssdlUrl 生成的URL路径
	* @param bssdlCdate 创建日期
	* @param bssdlUdate 修改日期
	* @param bssdlStatus 状态:1启用,0禁用
	* @return
	*/
	@RequestMapping(value="/add/busiServerDetail", method=RequestMethod.POST)
	public void addBusiServerDetail (String bssdlTitle, String bssdlSeo, String bssdlHtml, String bssdlUrl, String bssdlCdate, String bssdlUdate, Integer bssdlStatus, HttpServletResponse response) {
		logger.info("[BusiServerDetailController]:begin addBusiServerDetail");
		BusiServerDetail busiServerDetail = new BusiServerDetail();
		String uuid = RandomUtil.generateString(16);
		busiServerDetail.setBssdlUuid(uuid);
		busiServerDetail.setBssdlTitle(bssdlTitle);
		busiServerDetail.setBssdlSeo(bssdlSeo);
		busiServerDetail.setBssdlHtml(bssdlHtml);
		busiServerDetail.setBssdlUrl(bssdlUrl);
		busiServerDetail.setBssdlCdate(new Date());
		busiServerDetail.setBssdlUdate(new Date());
		busiServerDetail.setBssdlStatus(bssdlStatus);

		busiServerDetailService.insertBusiServerDetail(busiServerDetail);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiServerDetailController]:end addBusiServerDetail");

	}

	/**
	* 修改
	*
	* @param bssdlUuid 标识UUID
	* @param bssdlTitle Title内容
	* @param bssdlSeo SEO关键字
	* @param bssdlHtml 页面html内容
	* @param bssdlUrl 生成的URL路径
	* @param bssdlCdate 创建日期
	* @param bssdlUdate 修改日期
	* @param bssdlStatus 状态:1启用,0禁用
	* @return
	*/
	@RequestMapping(value="/update/busiServerDetail", method=RequestMethod.POST)
	public void updateBusiServerDetail (String bssdlUuid, String bssdlTitle, String bssdlSeo, String bssdlHtml, String bssdlUrl, String bssdlCdate, String bssdlUdate, Integer bssdlStatus, HttpServletResponse response) {
		logger.info("[BusiServerDetailController]:begin updateBusiServerDetail");
		if (StringUtil.isEmpty(bssdlUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiServerDetail busiServerDetail = new BusiServerDetail();
		busiServerDetail.setBssdlUuid(bssdlUuid);
		busiServerDetail.setBssdlTitle(bssdlTitle);
		busiServerDetail.setBssdlSeo(bssdlSeo);
		busiServerDetail.setBssdlHtml(bssdlHtml);
		busiServerDetail.setBssdlUrl(bssdlUrl);
		busiServerDetail.setBssdlCdate(new Date());
		busiServerDetail.setBssdlUdate(new Date());
		busiServerDetail.setBssdlStatus(bssdlStatus);

		busiServerDetailService.updateBusiServerDetail(busiServerDetail);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiServerDetailController]:end updateBusiServerDetail");

	}

	/**
	* 删除
	*
	* @param bssdlUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiServerDetail (String bssdlUuid, HttpServletResponse response) {
		logger.info("[BusiServerDetailController]:begin deleteBusiServerDetail");
		if (StringUtil.isEmpty(bssdlUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiServerDetail busiServerDetail = new BusiServerDetail();
		busiServerDetail.setBssdlUuid(bssdlUuid);

		busiServerDetailService.deleteBusiServerDetail(busiServerDetail);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiServerDetailController]:end deleteBusiServerDetail");

	}

	/**
	* 批量删除
	*
	* @param bssdlUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiServerDetail (String bssdlUuids, HttpServletResponse response) {
		logger.info("[BusiServerDetailController]:begin deleteBatchBusiServerDetail");
		if (StringUtil.isEmpty(bssdlUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=bssdlUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		busiServerDetailService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiServerDetailController]:end deleteBatchBusiServerDetail");

	}

	/**
	* 获取单个
	*
	* @param bssdlUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiServerDetail (String bssdlUuid, HttpServletResponse response) {
		logger.info("[BusiServerDetailController]:begin viewsBusiServerDetail");
		if (StringUtil.isEmpty(bssdlUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiServerDetail busiServerDetail = new BusiServerDetail();
		busiServerDetail.setBssdlUuid(bssdlUuid);

		busiServerDetail = busiServerDetailService.getBusiServerDetail(busiServerDetail);

		BusiServerDetailVO busiServerDetailVO = new BusiServerDetailVO();
		busiServerDetailVO.convertPOToVO(busiServerDetail);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiServerDetailVO), response);
		logger.info("[BusiServerDetailController]:end viewsBusiServerDetail");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiServerDetailList (HttpServletResponse response) {
		logger.info("[BusiServerDetailController]:begin findBusiServerDetailList");
		List<BusiServerDetail> lists = busiServerDetailService.findBusiServerDetailList();
		List<BusiServerDetailVO> vos = new ArrayList<BusiServerDetailVO>();
		BusiServerDetailVO vo;
		for (BusiServerDetail busiServerDetail : lists) {
			vo = new BusiServerDetailVO();
			vo.convertPOToVO(busiServerDetail);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiServerDetailController]:end findBusiServerDetailList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiServerDetailPage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiServerDetailController]:begin findBusiServerDetailPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiServerDetail> page = busiServerDetailService.findBusiServerDetailPage(pageNum, pageSize);
		Page<BusiServerDetailVO> pageVO = new Page<BusiServerDetailVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiServerDetailVO> vos = new ArrayList<BusiServerDetailVO>();
		BusiServerDetailVO vo;
		for (BusiServerDetail busiServerDetail : page.getResult()) {
			vo = new BusiServerDetailVO();
			vo.convertPOToVO(busiServerDetail);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiServerDetailController]:end findBusiServerDetailPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}