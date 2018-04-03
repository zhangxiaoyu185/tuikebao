package com.xiaoyu.lingdian.controller.my;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import com.xiaoyu.lingdian.entity.BusiShippingAddress;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.my.BusiShippingAddressService;
import com.xiaoyu.lingdian.vo.BusiShippingAddressVO;

@Controller
@RequestMapping(value="/busiShippingAddress")
public class BusiShippingAddressController extends BaseController {

	/**
	* 收货地址表
	*/
	@Autowired
	private BusiShippingAddressService busiShippingAddressService;
	
	/**
	 * 用户表
	 */
	@Autowired
	private CoreUserService coreUserService;

	/**
	* 添加
	*
	* @param bssasUser 所属用户
	* @param bssasName 收货人姓名
	* @param bssasMobile 收货人联系方式
	* @param bsarsZipCode 邮政编码
	* @param bssasProvince 所在省
	* @param bssasProvinceName 所在省名称
	* @param bssasCity 所在市
	* @param bssasCityName 所在市名称
	* @param bssasCounty 所在区
	* @param bssasCountyName 所在区名称
	* @param bssasAddress 详细地址
	* @return
	*/
	@RequestMapping(value="/add/busiShippingAddress", method=RequestMethod.POST)
	public void addBusiShippingAddress (String bssasUser, String bssasName, String bssasMobile, String bsarsZipCode, String bssasProvince, String bssasProvinceName, String bssasCity, String bssasCityName, String bssasCounty, String bssasCountyName, String bssasAddress, HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin addBusiShippingAddress");

		if (StringUtils.isBlank(bssasUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属用户不能为空！"), response);
			logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入收货人姓名！"), response);
			logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入收货人联系方式！"), response);
			logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasProvince)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在省！"), response);
			logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasProvinceName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在省名称！"), response);
			logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasCity)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在市！"), response);
			logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasCityName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在市名称！"), response);
			logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasCounty)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在区！"), response);
			logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasCountyName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在区名称！"), response);
			logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasAddress)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入详细地址！"), response);
			logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");
			return;
		}		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		String uuid = RandomUtil.generateString(16);
		busiShippingAddress.setBssasUuid(uuid);
		busiShippingAddress.setBssasUser(bssasUser);
		busiShippingAddress.setBssasName(bssasName);
		busiShippingAddress.setBssasMobile(bssasMobile);
		busiShippingAddress.setBsarsZipCode(bsarsZipCode);
		busiShippingAddress.setBssasProvince(bssasProvince);
		busiShippingAddress.setBssasProvinceName(bssasProvinceName);
		busiShippingAddress.setBssasCity(bssasCity);
		busiShippingAddress.setBssasCityName(bssasCityName);
		busiShippingAddress.setBssasCounty(bssasCounty);
		busiShippingAddress.setBssasCountyName(bssasCountyName);
		busiShippingAddress.setBssasAddress(bssasAddress);
		busiShippingAddress.setBsarsDefault(2);

		busiShippingAddressService.insertBusiShippingAddress(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiShippingAddressController]:end addBusiShippingAddress");

	}

	/**
	* 修改
	*
	* @param bssasUuid 标识UUID
	* @param bssasName 收货人姓名
	* @param bssasMobile 收货人联系方式
	* @param bsarsZipCode 邮政编码
	* @param bssasProvince 所在省
	* @param bssasProvinceName 所在省名称
	* @param bssasCity 所在市
	* @param bssasCityName 所在市名称
	* @param bssasCounty 所在区
	* @param bssasCountyName 所在区名称
	* @param bssasAddress 详细地址
	* @return
	*/
	@RequestMapping(value="/update/busiShippingAddress", method=RequestMethod.POST)
	public void updateBusiShippingAddress (String bssasUuid, String bssasName, String bssasMobile, String bsarsZipCode, String bssasProvince, String bssasProvinceName, String bssasCity, String bssasCityName, String bssasCounty, String bssasCountyName, String bssasAddress, HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin updateBusiShippingAddress");
		if (StringUtil.isEmpty(bssasUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入收货人姓名！"), response);
			logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasMobile)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入收货人联系方式！"), response);
			logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasProvince)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在省！"), response);
			logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasProvinceName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在省名称！"), response);
			logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasCity)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在市！"), response);
			logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasCityName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在市名称！"), response);
			logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasCounty)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在区！"), response);
			logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasCountyName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所在区名称！"), response);
			logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");
			return;
		}
		if (StringUtils.isBlank(bssasAddress)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入详细地址！"), response);
			logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");
			return;
		}
		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		busiShippingAddress.setBssasUuid(bssasUuid);
		busiShippingAddress.setBssasName(bssasName);
		busiShippingAddress.setBssasMobile(bssasMobile);
		busiShippingAddress.setBsarsZipCode(bsarsZipCode);
		busiShippingAddress.setBssasProvince(bssasProvince);
		busiShippingAddress.setBssasProvinceName(bssasProvinceName);
		busiShippingAddress.setBssasCity(bssasCity);
		busiShippingAddress.setBssasCityName(bssasCityName);
		busiShippingAddress.setBssasCounty(bssasCounty);
		busiShippingAddress.setBssasCountyName(bssasCountyName);
		busiShippingAddress.setBssasAddress(bssasAddress);

		busiShippingAddressService.updateBusiShippingAddress(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiShippingAddressController]:end updateBusiShippingAddress");

	}

	/**
	 * 点击取消默认
	 * @param bssasUuid
	 * @return
	 */
	@RequestMapping(value="/update/cancel/default", method=RequestMethod.POST)
	public void updateCancelDefault (String bssasUuid, HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin updateCancelDefault");

		if (StringUtil.isEmpty(bssasUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiShippingAddressController]:end updateCancelDefault");
			return;
		}

		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		busiShippingAddress.setBssasUuid(bssasUuid);
		busiShippingAddress.setBsarsDefault(2);

		busiShippingAddressService.updateBusiShippingAddress(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "取消默认成功!"),response);
		logger.info("[BusiShippingAddressController]:end updateCancelDefault");

	}
	
	/**
	 * 点击默认
	 * @param bssasUuid
	 * @param bssasUser
	 * @return
	 */
	@RequestMapping(value="/update/default", method=RequestMethod.POST)
	public void updateDefault (String bssasUuid, String bssasUser, HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin updateDefault");

		if (StringUtil.isEmpty(bssasUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiShippingAddressController]:end updateDefault");
			return;
		}
		if (StringUtils.isBlank(bssasUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请输入所属用户！"), response);
			logger.info("[BusiShippingAddressController]:end updateDefault");
			return;
		}

		//先全部更新为不默认
		busiShippingAddressService.updateBatchBusiShippingAddressByIds(bssasUser, null);
		
		//再更新当前为默认
		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		busiShippingAddress.setBssasUuid(bssasUuid);
		busiShippingAddress.setBsarsDefault(1);

		busiShippingAddressService.updateBusiShippingAddress(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "更新成默认成功!"),response);
		logger.info("[BusiShippingAddressController]:end updateDefault");

	}
	
	/**
	* 删除
	*
	* @param bssasUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiShippingAddress (String bssasUuid, HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin deleteBusiShippingAddress");
		if (StringUtil.isEmpty(bssasUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiShippingAddressController]:end deleteBusiShippingAddress");
			return;
		}
		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		busiShippingAddress.setBssasUuid(bssasUuid);

		busiShippingAddressService.deleteBusiShippingAddress(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiShippingAddressController]:end deleteBusiShippingAddress");

	}

	/**
	* 获取单个
	*
	* @param bssasUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiShippingAddress (String bssasUuid, HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin viewsBusiShippingAddress");
		if (StringUtil.isEmpty(bssasUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiShippingAddressController]:end viewsBusiShippingAddress");
			return;
		}
		BusiShippingAddress busiShippingAddress = new BusiShippingAddress();
		busiShippingAddress.setBssasUuid(bssasUuid);

		busiShippingAddress = busiShippingAddressService.getBusiShippingAddress(busiShippingAddress);

		BusiShippingAddressVO busiShippingAddressVO = new BusiShippingAddressVO();
		busiShippingAddressVO.convertPOToVO(busiShippingAddress);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiShippingAddressVO), response);
		logger.info("[BusiShippingAddressController]:end viewsBusiShippingAddress");

	}

	/**
	* 查询我的默认收货地址
	*
	* @param bssasUser 用户标识
	* @return
	*/
	@RequestMapping(value="/views/my/default", method=RequestMethod.POST)
	public void viewsBusiShippingAddressMyDefault (String bssasUser, HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin viewsBusiShippingAddressMyDefault");

		if (StringUtil.isEmpty(bssasUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiShippingAddressController]:end viewsBusiShippingAddressMyDefault");
			return;
		}

		BusiShippingAddress busiShippingAddress = busiShippingAddressService.getBusiShippingAddressDefault(bssasUser);
		if (null == busiShippingAddress) {
			List<BusiShippingAddress> lists = busiShippingAddressService.findBusiShippingAddressForListsByMy(bssasUser);
			if (null == lists || lists.size() <= 0) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(true, 2, "没有收货地址"), response);
				logger.info("[BusiShippingAddressController]:end viewsBusiShippingAddressMyDefault");
				return;
			} else {
				busiShippingAddress = lists.get(0);
			}
		}
		BusiShippingAddressVO busiShippingAddressVO = new BusiShippingAddressVO();
		busiShippingAddressVO.convertPOToVO(busiShippingAddress);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取我的默认收货地址成功", busiShippingAddressVO), response);
		logger.info("[BusiShippingAddressController]:end viewsBusiShippingAddressMyDefault");

	}

	/**
	 * 我的收货地址
	 * 
	 * @param bssasUser 用户标识
	 * @param response
	 */
	@RequestMapping(value = "/find/my/shipping/address", method = RequestMethod.POST)
	public void findMyShippingAddress(String bssasUser, HttpServletResponse response) {
		logger.info("[BusiShippingAddressController.findMyShippingAddress]:begin findMyShippingAddress.");
		if (StringUtils.isBlank(bssasUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户标识不能为空！"), response);
			logger.info("[BusiShippingAddressController.findMyShippingAddress]:end findMyShippingAddress.");
			return;
		}
		List<BusiShippingAddress> lists = busiShippingAddressService.findBusiShippingAddressForListsByMy(bssasUser);
		List<BusiShippingAddressVO> vos = new ArrayList<BusiShippingAddressVO>();
		BusiShippingAddressVO vo;
		for (BusiShippingAddress busiShippingAddress : lists) {
			vo = new BusiShippingAddressVO();

			vo.convertPOToVO(busiShippingAddress);

			vos.add(vo);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "查询我的收货地址成功", vos), response);
		logger.info("[BusiShippingAddressController.findMyShippingAddress]:end findMyShippingAddress.");
	}

	/**
	* 后台获取所有收货地址列表<Page>
	* 
	* @param userName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiShippingAddressPage (String userName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiShippingAddressController]:begin findBusiShippingAddressPage");

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiShippingAddress> page = busiShippingAddressService.findBusiShippingAddressForPages(userName, pageNum, pageSize);
		Page<BusiShippingAddressVO> pageVO = new Page<BusiShippingAddressVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashUserUuids = new HashSet<String>();
		for (BusiShippingAddress busiShippingAddress : page.getResult()) {
			hashUserUuids.add(busiShippingAddress.getBssasUser());
		}
		List<String> userUuids = new ArrayList<>(hashUserUuids);
		Map<String, CoreUser> userMap = coreUserService.findCoreUserMapByUuidList(userUuids);
		
		List<BusiShippingAddressVO> vos = new ArrayList<BusiShippingAddressVO>();
		BusiShippingAddressVO vo;
		for (BusiShippingAddress busiShippingAddress : page.getResult()) {
			vo = new BusiShippingAddressVO();
			vo.convertPOToVO(busiShippingAddress);
			vo.setBssasUserName(userMap.get(busiShippingAddress.getBssasUser())==null?null:userMap.get(busiShippingAddress.getBssasUser()).getCrusrName());
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiShippingAddressController]:end findBusiShippingAddressPage");
	}

}