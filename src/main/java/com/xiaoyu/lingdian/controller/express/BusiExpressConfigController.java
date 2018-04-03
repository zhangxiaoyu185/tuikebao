package com.xiaoyu.lingdian.controller.express;

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
import com.xiaoyu.lingdian.entity.BusiExpressConfig;
import com.xiaoyu.lingdian.service.express.BusiExpressConfigService;
import com.xiaoyu.lingdian.vo.BusiExpressConfigVO;

@Controller
@RequestMapping(value="/busiExpressConfig")
public class BusiExpressConfigController extends BaseController {

	/**
	* 快递鸟配置表
	*/
	@Autowired
	private BusiExpressConfigService busiExpressConfigService;
	
	/**
	* 添加
	*
	* @param bsecgKdnCode 商户标识
	* @param bsecgApiKey API_KEY
	* @return
	*/
	@RequestMapping(value="/add/busiExpressConfig", method=RequestMethod.POST)
	public void addBusiExpressConfig (String bsecgKdnCode, String bsecgApiKey, HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin addBusiExpressConfig");

		if (StringUtil.isEmpty(bsecgKdnCode)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[商户标识]不能为空!"), response);
			logger.info("[BusiExpressConfigController]:end addBusiExpressConfig");
			return;
		}
		if (StringUtil.isEmpty(bsecgApiKey)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[API_KEY]不能为空!"), response);
			logger.info("[BusiExpressConfigController]:end addBusiExpressConfig");
			return;
		}
		BusiExpressConfig busiExpressConfig = new BusiExpressConfig();
		String uuid = RandomUtil.generateString(16);
		busiExpressConfig.setBsecgUuid(uuid);
		busiExpressConfig.setBsecgKdnCode(bsecgKdnCode);
		busiExpressConfig.setBsecgApiKey(bsecgApiKey);
		busiExpressConfig.setBsecgCdate(new Date());
		busiExpressConfig.setBsecgUdate(new Date());

		busiExpressConfigService.insertBusiExpressConfig(busiExpressConfig);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiExpressConfigController]:end addBusiExpressConfig");

	}

	/**
	* 修改
	*
	* @param bsecgUuid 标识UUID
	* @param bsecgKdnCode 商户标识
	* @param bsecgApiKey API_KEY
	* @return
	*/
	@RequestMapping(value="/update/busiExpressConfig", method=RequestMethod.POST)
	public void updateBusiExpressConfig (String bsecgUuid, String bsecgKdnCode, String bsecgApiKey, HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin updateBusiExpressConfig");
		if (StringUtil.isEmpty(bsecgUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExpressConfigController]:end updateBusiExpressConfig");
			return;
		}
		if (StringUtil.isEmpty(bsecgKdnCode)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[商户标识]不能为空!"), response);
			logger.info("[BusiExpressConfigController]:end updateBusiExpressConfig");
			return;
		}
		if (StringUtil.isEmpty(bsecgApiKey)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[API_KEY]不能为空!"), response);
			logger.info("[BusiExpressConfigController]:end updateBusiExpressConfig");
			return;
		}
		BusiExpressConfig busiExpressConfig = new BusiExpressConfig();
		busiExpressConfig.setBsecgUuid(bsecgUuid);
		busiExpressConfig.setBsecgKdnCode(bsecgKdnCode);
		busiExpressConfig.setBsecgApiKey(bsecgApiKey);
		busiExpressConfig.setBsecgUdate(new Date());

		busiExpressConfigService.updateBusiExpressConfig(busiExpressConfig);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiExpressConfigController]:end updateBusiExpressConfig");

	}

	/**
	* 删除
	*
	* @param bsecgUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiExpressConfig (String bsecgUuid, HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin deleteBusiExpressConfig");
		if (StringUtil.isEmpty(bsecgUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExpressConfigController]:end deleteBusiExpressConfig");
			return;
		}
		BusiExpressConfig busiExpressConfig = new BusiExpressConfig();
		busiExpressConfig.setBsecgUuid(bsecgUuid);

		busiExpressConfigService.deleteBusiExpressConfig(busiExpressConfig);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiExpressConfigController]:end deleteBusiExpressConfig");

	}

	/**
	* 批量删除
	*
	* @param bsecgUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiExpressConfig (String bsecgUuids, HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin deleteBatchBusiExpressConfig");
		if (StringUtil.isEmpty(bsecgUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiExpressConfigController]:end deleteBatchBusiExpressConfig");
			return;
		}
		String[] uuids=bsecgUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiExpressConfigController]:end deleteBatchBusiExpressConfig");
			return;
		}
		busiExpressConfigService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiExpressConfigController]:end deleteBatchBusiExpressConfig");

	}

	/**
	* 获取单个
	*
	* @param bsecgUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiExpressConfig (String bsecgUuid, HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin viewsBusiExpressConfig");
		if (StringUtil.isEmpty(bsecgUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExpressConfigController]:end viewsBusiExpressConfig");
			return;
		}
		BusiExpressConfig busiExpressConfig = new BusiExpressConfig();
		busiExpressConfig.setBsecgUuid(bsecgUuid);

		busiExpressConfig = busiExpressConfigService.getBusiExpressConfig(busiExpressConfig);

		BusiExpressConfigVO busiExpressConfigVO = new BusiExpressConfigVO();
		busiExpressConfigVO.convertPOToVO(busiExpressConfig);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiExpressConfigVO), response);
		logger.info("[BusiExpressConfigController]:end viewsBusiExpressConfig");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiExpressConfigList (HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin findBusiExpressConfigList");
		List<BusiExpressConfig> lists = busiExpressConfigService.findBusiExpressConfigList();
		List<BusiExpressConfigVO> vos = new ArrayList<BusiExpressConfigVO>();
		BusiExpressConfigVO vo;
		for (BusiExpressConfig busiExpressConfig : lists) {
			vo = new BusiExpressConfigVO();
			vo.convertPOToVO(busiExpressConfig);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiExpressConfigController]:end findBusiExpressConfigList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiExpressConfigPage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiExpressConfigController]:begin findBusiExpressConfigPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiExpressConfig> page = busiExpressConfigService.findBusiExpressConfigPage(pageNum, pageSize);
		Page<BusiExpressConfigVO> pageVO = new Page<BusiExpressConfigVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiExpressConfigVO> vos = new ArrayList<BusiExpressConfigVO>();
		BusiExpressConfigVO vo;
		for (BusiExpressConfig busiExpressConfig : page.getResult()) {
			vo = new BusiExpressConfigVO();
			vo.convertPOToVO(busiExpressConfig);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiExpressConfigController]:end findBusiExpressConfigPage");

	}

}