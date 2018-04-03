package com.xiaoyu.lingdian.controller.express;

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
import com.xiaoyu.lingdian.entity.BusiExpressCode;
import com.xiaoyu.lingdian.service.express.BusiExpressCodeService;
import com.xiaoyu.lingdian.vo.BusiExpressCodeVO;

@Controller
@RequestMapping(value="/busiExpressCode")
public class BusiExpressCodeController extends BaseController {

	/**
	* 快递鸟公司编码配置表
	*/
	@Autowired
	private BusiExpressCodeService busiExpressCodeService;
	
	/**
	* 添加
	*
	* @param bseceCode 编码
	* @param bseceName 名称
	* @return
	*/
	@RequestMapping(value="/add/busiExpressCode", method=RequestMethod.POST)
	public void addBusiExpressCode (String bseceCode, String bseceName, HttpServletResponse response) {
		logger.info("[BusiExpressCodeController]:begin addBusiExpressCode");
		BusiExpressCode busiExpressCode = new BusiExpressCode();
		String uuid = RandomUtil.generateString(16);
		busiExpressCode.setBseceUuid(uuid);
		busiExpressCode.setBseceCode(bseceCode);
		busiExpressCode.setBseceName(bseceName);

		busiExpressCodeService.insertBusiExpressCode(busiExpressCode);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiExpressCodeController]:end addBusiExpressCode");

	}

	/**
	* 修改
	*
	* @param bseceUuid 标识UUID
	* @param bseceCode 编码
	* @param bseceName 名称
	* @return
	*/
	@RequestMapping(value="/update/busiExpressCode", method=RequestMethod.POST)
	public void updateBusiExpressCode (String bseceUuid, String bseceCode, String bseceName, HttpServletResponse response) {
		logger.info("[BusiExpressCodeController]:begin updateBusiExpressCode");
		if (StringUtil.isEmpty(bseceUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExpressCodeController]:end updateBusiExpressCode");
			return;
		}
		BusiExpressCode busiExpressCode = new BusiExpressCode();
		busiExpressCode.setBseceUuid(bseceUuid);
		busiExpressCode.setBseceCode(bseceCode);
		busiExpressCode.setBseceName(bseceName);

		busiExpressCodeService.updateBusiExpressCode(busiExpressCode);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiExpressCodeController]:end updateBusiExpressCode");

	}

	/**
	* 删除
	*
	* @param bseceUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiExpressCode (String bseceUuid, HttpServletResponse response) {
		logger.info("[BusiExpressCodeController]:begin deleteBusiExpressCode");
		if (StringUtil.isEmpty(bseceUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExpressCodeController]:end deleteBusiExpressCode");
			return;
		}
		BusiExpressCode busiExpressCode = new BusiExpressCode();
		busiExpressCode.setBseceUuid(bseceUuid);

		busiExpressCodeService.deleteBusiExpressCode(busiExpressCode);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiExpressCodeController]:end deleteBusiExpressCode");

	}

	/**
	* 批量删除
	*
	* @param bseceUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiExpressCode (String bseceUuids, HttpServletResponse response) {
		logger.info("[BusiExpressCodeController]:begin deleteBatchBusiExpressCode");
		if (StringUtil.isEmpty(bseceUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiExpressCodeController]:end deleteBatchBusiExpressCode");
			return;
		}
		String[] uuids=bseceUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		busiExpressCodeService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiExpressCodeController]:end deleteBatchBusiExpressCode");

	}

	/**
	* 获取单个
	*
	* @param bseceUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiExpressCode (String bseceUuid, HttpServletResponse response) {
		logger.info("[BusiExpressCodeController]:begin viewsBusiExpressCode");
		if (StringUtil.isEmpty(bseceUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiExpressCodeController]:end viewsBusiExpressCode");
			return;
		}
		BusiExpressCode busiExpressCode = new BusiExpressCode();
		busiExpressCode.setBseceUuid(bseceUuid);

		busiExpressCode = busiExpressCodeService.getBusiExpressCode(busiExpressCode);

		BusiExpressCodeVO busiExpressCodeVO = new BusiExpressCodeVO();
		busiExpressCodeVO.convertPOToVO(busiExpressCode);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiExpressCodeVO), response);
		logger.info("[BusiExpressCodeController]:end viewsBusiExpressCode");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiExpressCodeList (HttpServletResponse response) {
		logger.info("[BusiExpressCodeController]:begin findBusiExpressCodeList");
		List<BusiExpressCode> lists = busiExpressCodeService.findBusiExpressCodeList();
		List<BusiExpressCodeVO> vos = new ArrayList<BusiExpressCodeVO>();
		BusiExpressCodeVO vo;
		for (BusiExpressCode busiExpressCode : lists) {
			vo = new BusiExpressCodeVO();
			vo.convertPOToVO(busiExpressCode);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiExpressCodeController]:end findBusiExpressCodeList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiExpressCodePage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiExpressCodeController]:begin findBusiExpressCodePage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiExpressCode> page = busiExpressCodeService.findBusiExpressCodePage(pageNum, pageSize);
		Page<BusiExpressCodeVO> pageVO = new Page<BusiExpressCodeVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiExpressCodeVO> vos = new ArrayList<BusiExpressCodeVO>();
		BusiExpressCodeVO vo;
		for (BusiExpressCode busiExpressCode : page.getResult()) {
			vo = new BusiExpressCodeVO();
			vo.convertPOToVO(busiExpressCode);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiExpressCodeController]:end findBusiExpressCodePage");

	}

}