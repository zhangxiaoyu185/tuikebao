package com.xiaoyu.lingdian.controller;

import java.util.List;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.CoreRegion;
import com.xiaoyu.lingdian.service.CoreRegionService;
import com.xiaoyu.lingdian.vo.CoreRegionVO;

@Controller
@RequestMapping(value="/coreRegion")
public class CoreRegionController extends BaseController {

	/**
	* 区域表
	*/
	@Autowired
	private CoreRegionService coreRegionService;

	/**
	* 获取单个区域
	*
	* @param crrgnUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreRegion (String crrgnUuid, HttpServletResponse response) {
		logger.info("[CoreRegionController]:begin viewsCoreRegion");		if (StringUtil.isEmpty(crrgnUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[CoreRegionController]:end viewsCoreRegion");
			return;
		}
		CoreRegion coreRegion = new CoreRegion();
		coreRegion.setCrrgnUuid(crrgnUuid);
		coreRegion = coreRegionService.getCoreRegion(coreRegion);

		CoreRegionVO coreRegionVO = new CoreRegionVO();
		coreRegionVO.convertPOToVO(coreRegion);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", coreRegionVO), response);
		logger.info("[CoreRegionController]:end viewsCoreRegion");

	}

	/**
	 * 获取国家
	 * @param response
	 */
	@RequestMapping(value = "/country/list", method = RequestMethod.GET)
	public void listCountry(HttpServletResponse response) {
		logger.info("[CoreRegionController]:begin listCountry");
		List<CoreRegion> areas = this.coreRegionService.findCoreRegionByParentAndTypeAndName(null, 1, null);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取国家成功", areas), response);
		logger.info("[CoreRegionController]:end listCountry");
	}
	
	/**
	 * 获取省份
	 * 
	 * @param countryId 中国传‘1’
	 * @param response
	 */
	@RequestMapping(value = "/province/list/{countryId}", method = RequestMethod.GET)
	public void listProvinces(@PathVariable("countryId") String countryId, HttpServletResponse response) {
		logger.info("[CoreRegionController]:begin listProvinces");
		List<CoreRegion> areas = this.coreRegionService.findCoreRegionByParentAndTypeAndName(null, 2, countryId);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取省份成功", areas), response);
		logger.info("[CoreRegionController]:end listProvinces");
	}

	/**
	 * 获取城市
	 * 
	 * @param provinceId
	 * @param response
	 */
	@RequestMapping(value = "/city/list/{provinceId}", method = RequestMethod.GET)
	public void listCities(@PathVariable("provinceId") String provinceId, HttpServletResponse response) {		
		logger.info("[CoreRegionController]:begin listCities");
		List<CoreRegion> areas = this.coreRegionService.findCoreRegionByParentAndTypeAndName(null, 3, provinceId);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取城市成功", areas), response);
		logger.info("[CoreRegionController]:end listCities");
	}
	
	/**
	 * 获取全部城市
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/city/listAllCities", method = RequestMethod.GET)
	public void listAllCities(HttpServletResponse response) {		
		logger.info("[CoreRegionController]:begin listAllCities");
		List<CoreRegion> areas = this.coreRegionService.findCoreRegionByType(3);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取全部城市成功", areas), response);
		logger.info("[CoreRegionController]:end listAllCities");
	}
	
	/**
	 * 获取区县
	 * 
	 * @param cityId
	 * @param response
	 */
	@RequestMapping(value = "/district/list/{cityId}", method = RequestMethod.GET)
	public void listCounties (@PathVariable("cityId") String cityId, HttpServletResponse response) {
		logger.info("[CoreRegionController]:begin listCounties");
		List<CoreRegion> areas = this.coreRegionService.findCoreRegionByParentAndTypeAndName(null, 4, cityId);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取县/县级市/区成功", areas), response);
		logger.info("[CoreRegionController]:end listCounties");
	}
	
	/**
	 * 获取全部区县
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/district/listAllCounties", method = RequestMethod.GET)
	public void listAllCounties (HttpServletResponse response) {
		logger.info("[CoreRegionController]:begin listAllCounties");
		List<CoreRegion> areas = this.coreRegionService.findCoreRegionByType(4);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取全部县/县级市/区成功", areas), response);
		logger.info("[CoreRegionController]:end listAllCounties");
	}

}