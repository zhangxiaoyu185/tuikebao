package com.xiaoyu.lingdian.controller;

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
import com.xiaoyu.lingdian.entity.CoreFunction;
import com.xiaoyu.lingdian.service.CoreFunctionService;
import com.xiaoyu.lingdian.vo.CoreFunctionVO;

@Controller
@RequestMapping(value="/coreFunction")
public class CoreFunctionController extends BaseController {

	/**
	* 功能项表
	*/
	@Autowired
	private CoreFunctionService coreFunctionService;

	/**
	* 添加
	*
	* @param crfntFunName 功能项名称
	* @param crfntResource 资源请求地址
	* @param crfntOrd 排序号
	* @param crfntFather 上级菜单
	* @return
	*/
	@RequestMapping(value="/add/coreFunction", method=RequestMethod.POST)
	public void addCoreFunction (String crfntFunName, String crfntResource, Integer crfntOrd, String crfntFather, HttpServletResponse response) {
		logger.info("[CoreFunctionController]:begin addCoreFunction");

		if (StringUtil.isEmpty(crfntFunName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "功能项名称不能为空!"), response);
			logger.info("[CoreFunctionController]:end addCoreFunction");
			return;
		}
		if (StringUtil.isEmpty(crfntFather)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "上级菜单不能为空!"), response);
			logger.info("[CoreFunctionController]:end addCoreFunction");
			return;
		}

		String uuid = RandomUtil.generateString(16);
		coreFunction.setCrfntUuid(uuid);
		coreFunction.setCrfntFunName(crfntFunName);
		coreFunction.setCrfntResource(crfntResource);
		coreFunction.setCrfntStatus(1);
		coreFunction.setCrfntCdate(new Date());
		coreFunction.setCrfntUdate(new Date());
		coreFunction.setCrfntOrd(crfntOrd == null ? 1 : crfntOrd);
		coreFunction.setCrfntFather(crfntFather);

		coreFunctionService.insertCoreFunction(coreFunction);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreFunctionController]:end addCoreFunction");

	}

	/**
	 * 单个功能菜单启用禁用
	 * 
	 * @param crfntUuid
	 * @param crfntStatus
	 * @param response
	 */
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public void enable(String crfntUuid, Integer crfntStatus, HttpServletResponse response) {
		logger.info("[CoreFunctionController]:begin enable");
		
		if (StringUtil.isEmpty(crfntUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空！"), response);
			logger.info("[CoreFunctionController]:end enable");
			return;
		}
		if (crfntStatus == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "状态不能为空！"), response);
			logger.info("[CoreFunctionController]:end enable");
			return;
		}

		CoreFunction coreFunction = new CoreFunction();
		coreFunction.setCrfntUuid(crfntUuid);
		coreFunction.setCrfntStatus(crfntStatus);
		coreFunctionService.updateCoreFunction(coreFunction);
		if (crfntStatus == 1) { //如果是状态为启用，即父菜单也启用，子菜单也启用
			coreFunction = coreFunctionService.getCoreFunction(coreFunction);
			if (coreFunction != null && coreFunction.getCrfntFather() != null) {
				//父菜单也启用
				CoreFunction fatherFunction = new CoreFunction();
				fatherFunction.setCrfntUuid(coreFunction.getCrfntFather());
				fatherFunction.setCrfntStatus(1);
				coreFunctionService.updateCoreFunction(fatherFunction);
				//子菜单也启用
				CoreFunction childFunction = new CoreFunction();
				childFunction.setCrfntFather(coreFunction.getCrfntUuid());
				childFunction.setCrfntStatus(1);
				coreFunctionService.updateCoreFunctionByFather(childFunction);
			}		
		}
		if (crfntStatus == 0) { //如果是状态为禁用，即子菜单也禁用
			coreFunction = coreFunctionService.getCoreFunction(coreFunction);
			if (coreFunction != null && coreFunction.getCrfntFather() != null) {
				CoreFunction childFunction = new CoreFunction();
				childFunction.setCrfntFather(coreFunction.getCrfntUuid());
				childFunction.setCrfntStatus(0);
				coreFunctionService.updateCoreFunctionByFather(childFunction);
			}
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, crfntStatus == 1 ? "启用成功！" : "禁用成功！"), response);
		logger.info("[CoreFunctionController]:end enable");
	}
	
	/**
	* 修改
	*
	* @param crfntUuid 标识UUID
	* @param crfntFunName 功能项名称
	* @param crfntResource 资源请求地址
	* @param crfntOrd 排序号
	* @param crfntFather 上级菜单
	* @return
	*/
	@RequestMapping(value="/update/coreFunction", method=RequestMethod.POST)
	public void updateCoreFunction (String crfntUuid, String crfntFunName, String crfntResource, Integer crfntOrd, String crfntFather, HttpServletResponse response) {
		logger.info("[CoreFunctionController]:begin updateCoreFunction");

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreFunctionController]:end updateCoreFunction");
			return;
		}
		if (StringUtil.isEmpty(crfntFunName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "功能名称不能为空!"), response);
			logger.info("[CoreFunctionController]:end updateCoreFunction");
			return;
		}
		if (StringUtil.isEmpty(crfntFather)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "上级菜单不能为空!"), response);
			logger.info("[CoreFunctionController]:end updateCoreFunction");
			return;
		}

		coreFunction.setCrfntUuid(crfntUuid);
		coreFunction.setCrfntFunName(crfntFunName);
		coreFunction.setCrfntResource(crfntResource);
		coreFunction.setCrfntUdate(new Date());
		coreFunction.setCrfntOrd(crfntOrd == null ? 1 : crfntOrd);
		coreFunction.setCrfntFather(crfntFather);

		coreFunctionService.updateCoreFunction(coreFunction);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreFunctionController]:end updateCoreFunction");

	}

	/**
	* 删除
	*
	* @param crfntUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreFunction (String crfntUuid, HttpServletResponse response) {
		logger.info("[CoreFunctionController]:begin deleteCoreFunction");

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreFunctionController]:end deleteCoreFunction");
			return;
		}

		coreFunction.setCrfntUuid(crfntUuid);

		coreFunctionService.deleteCoreFunction(coreFunction);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreFunctionController]:end deleteCoreFunction");

	}

	/**
	* 批量删除
	*
	* @param crfntUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreFunction (String crfntUuids, HttpServletResponse response) {
		logger.info("[CoreFunctionController]:begin deleteBatchCoreFunction");

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[CoreFunctionController]:end deleteBatchCoreFunction");
			return;
		}

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[CoreFunctionController]:end deleteBatchCoreFunction");
			return;
		}
		coreFunctionService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreFunctionController]:end deleteBatchCoreFunction");

	}

	/**
	* 获取单个
	*
	* @param crfntUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreFunction (String crfntUuid, HttpServletResponse response) {
		logger.info("[CoreFunctionController]:begin viewsCoreFunction");

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreFunctionController]:begin viewsCoreFunction");
			return;
		}

		coreFunction.setCrfntUuid(crfntUuid);

		coreFunction = coreFunctionService.getCoreFunction(coreFunction);
		if (coreFunction == null) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "详情不存在!"), response);
			logger.info("[CoreFunctionController]:begin viewsCoreFunction");
			return;
		}
		
		CoreFunctionVO coreFunctionVO = new CoreFunctionVO();
		coreFunctionVO.convertPOToVO(coreFunction);
		CoreFunction fatherFunction = new CoreFunction();
		if (coreFunction.getCrfntFather() != null) {
			fatherFunction.setCrfntUuid(coreFunction.getCrfntFather());
			fatherFunction = coreFunctionService.getCoreFunction(fatherFunction);
			if (fatherFunction != null) {
				coreFunctionVO.setCrfntFatherName(fatherFunction.getCrfntFunName());
			}
		}
		
		logger.info("[CoreFunctionController]:end viewsCoreFunction");

	}

	/**
	 * 主菜单和一级菜单列表
	 * @param response
	 */
	@RequestMapping(value = "/top/menu", method = RequestMethod.POST)
	public void topMenuList(HttpServletResponse response) {
		logger.info("[CoreFunctionController]:begin topMenuList");
		
		List<CoreFunction> list = coreFunctionService.findCoreFunctionFather();
		List<CoreFunctionVO> functionVOs = new ArrayList<CoreFunctionVO>();
		CoreFunctionVO functionVO;
		CoreFunction fatherFunction;
		for (CoreFunction coreFunction : list) {
			functionVO = new CoreFunctionVO();
			functionVO.convertPOToVO(coreFunction);
			if (functionVO.getCrfntFather() != null) {
				fatherFunction = new CoreFunction();
				fatherFunction.setCrfntUuid(functionVO.getCrfntFather());
				fatherFunction = coreFunctionService.getCoreFunction(fatherFunction);
				if (fatherFunction != null) {
					functionVO.setCrfntFatherName(fatherFunction.getCrfntFunName());
				}
			}
			functionVOs.add(functionVO);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取主菜单和一级菜单列表成功！", functionVOs), response);
		logger.info("[CoreFunctionController]:end topMenuList");
	}

	/**
	* 获取列表<List>
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreFunctionList (String crfntFather, HttpServletResponse response) {
		logger.info("[CoreFunctionController]:begin findCoreFunctionList");

		List<CoreFunctionVO> vos = new ArrayList<CoreFunctionVO>();
		CoreFunctionVO functionVO;
		CoreFunction fatherFunction;
		for (CoreFunction coreFunction : lists) {
			functionVO = new CoreFunctionVO();
			functionVO.convertPOToVO(coreFunction);
			if (functionVO.getCrfntFather() != null) {
				fatherFunction = new CoreFunction();
				fatherFunction.setCrfntUuid(functionVO.getCrfntFather());
				fatherFunction = coreFunctionService.getCoreFunction(fatherFunction);
				if (fatherFunction != null) {
					functionVO.setCrfntFatherName(fatherFunction.getCrfntFunName());
				}
			}
			vos.add(functionVO);
		}

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreFunctionController]:end findCoreFunctionList");

	}

	/**
	* 获取分页列表<Page>
	* 
	* @param crfntFunName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreFunctionPage (String crfntFunName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreFunctionController]:begin findCoreFunctionPage");

			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}		

		Page<CoreFunction> page = coreFunctionService.findCoreFunctionPage(crfntFunName, pageNum, pageSize);
		Page<CoreFunctionVO> pageVO = new Page<CoreFunctionVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreFunctionVO> vos = new ArrayList<CoreFunctionVO>();
		CoreFunctionVO vo;
		CoreFunction fatherFunction;
		for (CoreFunction coreFunction : page.getResult()) {
			vo = new CoreFunctionVO();
			vo.convertPOToVO(coreFunction);
			if (vo.getCrfntFather() != null) {
				fatherFunction = new CoreFunction();
				fatherFunction.setCrfntUuid(vo.getCrfntFather());
				fatherFunction = coreFunctionService.getCoreFunction(fatherFunction);
				if (fatherFunction != null) {
					vo.setCrfntFatherName(fatherFunction.getCrfntFunName());
				}
			}
			vos.add(vo);
		}
		pageVO.setResult(vos);

		logger.info("[CoreFunctionController]:end findCoreFunctionPage");

	}

}