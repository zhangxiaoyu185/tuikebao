package com.xiaoyu.lingdian.controller.server;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
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
import com.xiaoyu.lingdian.entity.BusiServerDynamic;
import com.xiaoyu.lingdian.entity.BusiServerType;
import com.xiaoyu.lingdian.service.server.BusiServerDynamicService;
import com.xiaoyu.lingdian.service.server.BusiServerTypeService;
import com.xiaoyu.lingdian.vo.BusiServerDynamicVO;

@Controller
@RequestMapping(value="/busiServerDynamic")
public class BusiServerDynamicController extends BaseController {

	/**
	* 服务动态表
	*/
	@Autowired
	private BusiServerDynamicService busiServerDynamicService;
	
	/**
	* 服务动态表
	*/
	@Autowired
	private BusiServerTypeService busiServerTypeService;
	
	/**
	* 添加(刚添加的动态默认都是草稿)
	*
	* @param bssdcType 服务类别
	* @param bssdcTitle 标题
	* @param bssdcPic 封面图
	* @param bssdcContent 内容
	* @param bssdcDesc 描述
	* @return
	*/
	@RequestMapping(value="/add/busiServerDynamic", method=RequestMethod.POST)
	public void addBusiServerDynamic (String bssdcType, String bssdcTitle, String bssdcPic, String bssdcContent, String bssdcDesc, HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin addBusiServerDynamic");
		if (StringUtil.isEmpty(bssdcType)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "服务类别不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end addBusiServerDynamic");
			return;
		}
		if (StringUtil.isEmpty(bssdcTitle)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标题]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end addBusiServerDynamic");
			return;
		}
		if (StringUtil.isEmpty(bssdcPic)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[封面图]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end addBusiServerDynamic");
			return;
		}
		if (StringUtil.isEmpty(bssdcContent)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[内容]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end addBusiServerDynamic");
			return;
		}

		//判断标题是否重复
		BusiServerDynamic namic = busiServerDynamicService.getBusiServerDynamicByTitle(bssdcType, bssdcTitle);
		if(null != namic){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "标题已存在!"), response);
			logger.info("[BusiServerDynamicController]:end addBusiServerDynamic");
			return;
		}
				
		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		String uuid = RandomUtil.generateString(16);
		busiServerDynamic.setBssdcUuid(uuid);
		busiServerDynamic.setBssdcType(bssdcType);
		busiServerDynamic.setBssdcTitle(bssdcTitle);
		busiServerDynamic.setBssdcPic(bssdcPic);
		busiServerDynamic.setBssdcContent(bssdcContent);
		busiServerDynamic.setBssdcDesc(bssdcDesc);
		busiServerDynamic.setBssdcCdate(new Date());
		busiServerDynamic.setBssdcUdate(new Date());
		busiServerDynamic.setBssdcStatus(0);

		busiServerDynamicService.insertBusiServerDynamic(busiServerDynamic);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiServerDynamicController]:end addBusiServerDynamic");

	}

	/**
	* 修改
	*
	* @param bssdcUuid 标识UUID
	* @param bssdcType 服务类别
	* @param bssdcTitle 标题
	* @param bssdcPic 封面图
	* @param bssdcContent 内容
	* @param bssdcDesc 描述
	* @return
	*/
	@RequestMapping(value="/update/busiServerDynamic", method=RequestMethod.POST)
	public void updateBusiServerDynamic (String bssdcUuid, String bssdcType, String bssdcTitle, String bssdcPic, String bssdcContent, String bssdcDesc, HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin updateBusiServerDynamic");
		if (StringUtil.isEmpty(bssdcUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
			return;
		}
		if (StringUtil.isEmpty(bssdcType)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "服务类别不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
			return;
		}
		if (StringUtil.isEmpty(bssdcTitle)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标题]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
			return;
		}
		if (StringUtil.isEmpty(bssdcPic)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[封面图]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
			return;
		}
		if (StringUtil.isEmpty(bssdcContent)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[内容]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
			return;
		}

		//判断标题是否重复
		BusiServerDynamic namic = busiServerDynamicService.getBusiServerDynamicByTitle(bssdcType, bssdcTitle);
		if(null != namic && !namic.getBssdcUuid().equals(bssdcUuid)){
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "标题已存在!"), response);
			logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
			return;
		}

		//判断是否已发布
		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);
		busiServerDynamic = busiServerDynamicService.getBusiServerDynamic(busiServerDynamic);
		if (null == busiServerDynamic) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该服务动态不存在!"), response);
			logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
			return;
		}	
		if (busiServerDynamic.getBssdcStatus() == 1) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "已发布状态的服务动态不能修改!"), response);
			logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");
			return;
		}
				
		busiServerDynamic.setBssdcType(bssdcType);
		busiServerDynamic.setBssdcTitle(bssdcTitle);
		busiServerDynamic.setBssdcPic(bssdcPic);
		busiServerDynamic.setBssdcContent(bssdcContent);
		busiServerDynamic.setBssdcDesc(bssdcDesc);
		busiServerDynamic.setBssdcUdate(new Date());

		busiServerDynamicService.updateBusiServerDynamic(busiServerDynamic);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiServerDynamicController]:end updateBusiServerDynamic");

	}

	/**
	 * 发布
	 *
	 * @param bssdcUuid 标识UUID
	 * @return
	 */
	@RequestMapping(value="/publish", method=RequestMethod.POST)
	public void publish (String bssdcUuid, HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin publish");

		if (StringUtil.isEmpty(bssdcUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:begin publish");
			return;
		}

		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);
		busiServerDynamic.setBssdcStatus(1);
		busiServerDynamic.setBssdcPdate(new Date());
		busiServerDynamic.setBssdcUdate(new Date());

		busiServerDynamicService.updateBusiServerDynamic(busiServerDynamic);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "发布成功!"),response);
		logger.info("[BusiServerDynamicController]:end publish");

	}

	/**
	 * 草稿
	 *
	 * @param bssdcUuid 标识UUID
	 * @return
	 */
	@RequestMapping(value="/disable", method=RequestMethod.POST)
	public void disable (String bssdcUuid, HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin disable");

		if (StringUtil.isEmpty(bssdcUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:begin disable");
			return;
		}

		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);
		busiServerDynamic.setBssdcStatus(0);
		busiServerDynamic.setBssdcUdate(new Date());

		busiServerDynamicService.updateBusiServerDynamic(busiServerDynamic);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "禁用成功!"),response);
		logger.info("[BusiServerDynamicController]:end disable");

	}

	/**
	* 删除
	*
	* @param bssdcUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiServerDynamic (String bssdcUuid, HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin deleteBusiServerDynamic");
		if (StringUtil.isEmpty(bssdcUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end deleteBusiServerDynamic");
			return;
		}

		//判断已发布的不能删除
		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);
		busiServerDynamic = busiServerDynamicService.getBusiServerDynamic(busiServerDynamic);
		if (null != busiServerDynamic && 0 == busiServerDynamic.getBssdcStatus()) {
			busiServerDynamicService.deleteBusiServerDynamic(busiServerDynamic);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiServerDynamicController]:end deleteBusiServerDynamic");

	}

	/**
	* 批量删除
	*
	* @param bssdcUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiServerDynamic (String bssdcUuids, HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin deleteBatchBusiServerDynamic");
		if (StringUtil.isEmpty(bssdcUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end deleteBatchBusiServerDynamic");
			return;
		}
		String[] uuids=bssdcUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		BusiServerDynamic busiServerDynamic;
		for (int i = 0; i < uuids.length; i++) {
			//判断已发布的不能删除
			busiServerDynamic = new BusiServerDynamic();
			busiServerDynamic.setBssdcUuid(uuids[i]);
			busiServerDynamic = busiServerDynamicService.getBusiServerDynamic(busiServerDynamic);
			if (null != busiServerDynamic && 0 == busiServerDynamic.getBssdcStatus()) {
				list.add(uuids[i]);
			}
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiServerDynamicController]:end deleteBatchBusiServerDynamic");
			return;
		}

		busiServerDynamicService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiServerDynamicController]:end deleteBatchBusiServerDynamic");
	}

	/**
	* 后台获取单个
	*
	* @param bssdcUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiServerDynamic (String bssdcUuid, HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin viewsBusiServerDynamic");
		if (StringUtil.isEmpty(bssdcUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end viewsBusiServerDynamic");
			return;
		}
		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);

		busiServerDynamic = busiServerDynamicService.getBusiServerDynamic(busiServerDynamic);
		if (null == busiServerDynamic) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该服务动态不存在!"), response);
			logger.info("[BusiServerDynamicController]:end viewsBusiServerDynamic");
			return;
		}

		BusiServerDynamicVO busiServerDynamicVO = new BusiServerDynamicVO();
		busiServerDynamicVO.convertPOToVO(busiServerDynamic);
		
		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(busiServerDynamic.getBssdcType());
		busiServerType = busiServerTypeService.getBusiServerType(busiServerType);
		if (null != busiServerType) {
			busiServerDynamicVO.setBssdcTypeName(busiServerType.getBssteName());
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiServerDynamicVO), response);
		logger.info("[BusiServerDynamicController]:end viewsBusiServerDynamic");
	}

	/**
	* 前台获取单个(查看数+1)
	*
	* @param bssdcUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/index/views", method=RequestMethod.POST)
	public void viewsIndex (String bssdcUuid, HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin viewsIndex");

		if (StringUtil.isEmpty(bssdcUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end viewsIndex");
			return;
		}

		BusiServerDynamic busiServerDynamic = new BusiServerDynamic();
		busiServerDynamic.setBssdcUuid(bssdcUuid);
		busiServerDynamic = busiServerDynamicService.getBusiServerDynamic(busiServerDynamic);
		if (null == busiServerDynamic || busiServerDynamic.getBssdcStatus() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该服务动态不存在!"), response);
			logger.info("[BusiServerDynamicController]:end viewsIndex");
			return;
		}

		//更新数+1
		busiServerDynamicService.updateBusiServerDynamicCount(bssdcUuid);
		
		BusiServerDynamicVO busiServerDynamicVO = new BusiServerDynamicVO();
		busiServerDynamicVO.convertPOToVO(busiServerDynamic);
		
		BusiServerType busiServerType = new BusiServerType();
		busiServerType.setBssteUuid(busiServerDynamic.getBssdcType());
		busiServerType = busiServerTypeService.getBusiServerType(busiServerType);
		if (null != busiServerType) {
			busiServerDynamicVO.setBssdcTypeName(busiServerType.getBssteName());
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "前台获取单个信息成功", busiServerDynamicVO), response);
		logger.info("[BusiServerDynamicController]:end viewsIndex");
	}

	/**
	* 后台按标题获取列表<Page>
	* 
	* @param bssdcTitle 标题
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiServerDynamicPage (String bssdcTitle, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin findBusiServerDynamicPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiServerDynamic> page = busiServerDynamicService.findBusiServerDynamicForPages(bssdcTitle, pageNum, pageSize);
		Page<BusiServerDynamicVO> pageVO = new Page<BusiServerDynamicVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiServerDynamicVO> vos = new ArrayList<BusiServerDynamicVO>();
		
		HashSet<String> hashSet = new HashSet<String>();
		for (BusiServerDynamic busiServerDynamic : page.getResult()) {
			hashSet.add(busiServerDynamic.getBssdcType());
		}
		List<String> typeUuids = new ArrayList<String>(hashSet);
		Map<String, BusiServerType> map = busiServerTypeService.findBusiServerTypeMapByUuidList(typeUuids);
		
		BusiServerDynamicVO vo;
		for (BusiServerDynamic busiServerDynamic : page.getResult()) {
			vo = new BusiServerDynamicVO();
			vo.convertPOToVO(busiServerDynamic);
			vo.setBssdcTypeName(map.get(busiServerDynamic.getBssdcType()) == null ? null : map.get(busiServerDynamic.getBssdcType()).getBssteName());
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiServerDynamicController]:end findBusiServerDynamicPage");
	}

	/**
	* 前台分页列表
	* 
	* @param bssdcType 类别
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd/before", method=RequestMethod.POST)
	public void findBusiDynamicForPagesByBefore (String bssdcType, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiServerDynamicController]:begin findBusiDynamicForPagesByBefore");
		if (StringUtil.isEmpty(bssdcType)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "类别标识不能为空!"), response);
			logger.info("[BusiServerDynamicController]:end findBusiDynamicForPagesByBefore");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiServerDynamic> page = busiServerDynamicService.findBusiServerDynamicForPagesByBefore(bssdcType, pageNum, pageSize);
		Page<BusiServerDynamicVO> pageVO = new Page<BusiServerDynamicVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiServerDynamicVO> vos = new ArrayList<BusiServerDynamicVO>();

		BusiServerDynamicVO vo;
		for (BusiServerDynamic busiServerDynamic : page.getResult()) {
			vo = new BusiServerDynamicVO();
			vo.convertPOToVO(busiServerDynamic);
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiServerDynamicController]:end findBusiDynamicForPagesByBefore");
	}

}