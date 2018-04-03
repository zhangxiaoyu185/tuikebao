package com.xiaoyu.lingdian.controller.order;

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
import com.xiaoyu.lingdian.entity.BusiOrderPay;
import com.xiaoyu.lingdian.service.order.BusiOrderPayService;
import com.xiaoyu.lingdian.vo.BusiOrderPayVO;

@Controller
@RequestMapping(value="/busiOrderPay")
public class BusiOrderPayController extends BaseController {

	/**
	* 订单支付记录表
	*/
	@Autowired
	private BusiOrderPayService busiOrderPayService;
	
	/**
	* 添加
	*
	* @param bsopyOrder 订单标识
	* @param bsopyClientIp 客户端地址
	* @param bsopyPayChannel 支付渠道
	* @param bsopyTradeType 交易类型
	* @param bsopyUser 用户标识
	* @param bsopyReturnUrl 同步通知地址
	* @param bsopyPayParams 支付返回参数（返回用于前端页面支付参数）
	* @param bsopyResultCode 业务结果
	* @param bsopyErrorCode 错误代码
	* @param bsopyErrorMsg 错误描述
	* @param bsopyOutTradeNo 第三方单号
	* @param bsopyPayResult 支付结果
	* @param bsopyPayDate 支付时间
	* @param bsopyNotifyDate 通知时间
	* @param bsopyCdate 创建时间
	* @param bsopyUdate 更新时间
	* @return
	*/
	@RequestMapping(value="/add/busiOrderPay", method=RequestMethod.POST)
	public void addBusiOrderPay (String bsopyOrder, String bsopyClientIp, String bsopyPayChannel, String bsopyTradeType, String bsopyUser, String bsopyReturnUrl, String bsopyPayParams, String bsopyResultCode, String bsopyErrorCode, String bsopyErrorMsg, String bsopyOutTradeNo, String bsopyPayResult, String bsopyPayDate, String bsopyNotifyDate, String bsopyCdate, String bsopyUdate, HttpServletResponse response) {
		logger.info("[BusiOrderPayController]:begin addBusiOrderPay");
		BusiOrderPay busiOrderPay = new BusiOrderPay();
		String uuid = RandomUtil.generateString(16);
		busiOrderPay.setBsopyUuid(uuid);
		busiOrderPay.setBsopyOrder(bsopyOrder);
		busiOrderPay.setBsopyClientIp(bsopyClientIp);
		busiOrderPay.setBsopyPayChannel(bsopyPayChannel);
		busiOrderPay.setBsopyTradeType(bsopyTradeType);
		busiOrderPay.setBsopyUser(bsopyUser);
		busiOrderPay.setBsopyReturnUrl(bsopyReturnUrl);
		busiOrderPay.setBsopyPayParams(bsopyPayParams);
		busiOrderPay.setBsopyResultCode(bsopyResultCode);
		busiOrderPay.setBsopyErrorCode(bsopyErrorCode);
		busiOrderPay.setBsopyErrorMsg(bsopyErrorMsg);
		busiOrderPay.setBsopyOutTradeNo(bsopyOutTradeNo);
		busiOrderPay.setBsopyPayResult(bsopyPayResult);
		busiOrderPay.setBsopyPayDate(DateUtil.parseDefaultDate(bsopyPayDate));
		busiOrderPay.setBsopyNotifyDate(DateUtil.parseDefaultDate(bsopyNotifyDate));
		busiOrderPay.setBsopyCdate(new Date());
		busiOrderPay.setBsopyUdate(new Date());

		busiOrderPayService.insertBusiOrderPay(busiOrderPay);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiOrderPayController]:end addBusiOrderPay");

	}

	/**
	* 修改
	*
	* @param bsopyUuid 标识UUID
	* @param bsopyOrder 订单标识
	* @param bsopyClientIp 客户端地址
	* @param bsopyPayChannel 支付渠道
	* @param bsopyTradeType 交易类型
	* @param bsopyUser 用户标识
	* @param bsopyReturnUrl 同步通知地址
	* @param bsopyPayParams 支付返回参数（返回用于前端页面支付参数）
	* @param bsopyResultCode 业务结果
	* @param bsopyErrorCode 错误代码
	* @param bsopyErrorMsg 错误描述
	* @param bsopyOutTradeNo 第三方单号
	* @param bsopyPayResult 支付结果
	* @param bsopyPayDate 支付时间
	* @param bsopyNotifyDate 通知时间
	* @param bsopyCdate 创建时间
	* @param bsopyUdate 更新时间
	* @return
	*/
	@RequestMapping(value="/update/busiOrderPay", method=RequestMethod.POST)
	public void updateBusiOrderPay (String bsopyUuid, String bsopyOrder, String bsopyClientIp, String bsopyPayChannel, String bsopyTradeType, String bsopyUser, String bsopyReturnUrl, String bsopyPayParams, String bsopyResultCode, String bsopyErrorCode, String bsopyErrorMsg, String bsopyOutTradeNo, String bsopyPayResult, String bsopyPayDate, String bsopyNotifyDate, String bsopyCdate, String bsopyUdate, HttpServletResponse response) {
		logger.info("[BusiOrderPayController]:begin updateBusiOrderPay");
		if (StringUtil.isEmpty(bsopyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiOrderPay busiOrderPay = new BusiOrderPay();
		busiOrderPay.setBsopyUuid(bsopyUuid);
		busiOrderPay.setBsopyOrder(bsopyOrder);
		busiOrderPay.setBsopyClientIp(bsopyClientIp);
		busiOrderPay.setBsopyPayChannel(bsopyPayChannel);
		busiOrderPay.setBsopyTradeType(bsopyTradeType);
		busiOrderPay.setBsopyUser(bsopyUser);
		busiOrderPay.setBsopyReturnUrl(bsopyReturnUrl);
		busiOrderPay.setBsopyPayParams(bsopyPayParams);
		busiOrderPay.setBsopyResultCode(bsopyResultCode);
		busiOrderPay.setBsopyErrorCode(bsopyErrorCode);
		busiOrderPay.setBsopyErrorMsg(bsopyErrorMsg);
		busiOrderPay.setBsopyOutTradeNo(bsopyOutTradeNo);
		busiOrderPay.setBsopyPayResult(bsopyPayResult);
		busiOrderPay.setBsopyPayDate(DateUtil.parseDefaultDate(bsopyPayDate));
		busiOrderPay.setBsopyNotifyDate(DateUtil.parseDefaultDate(bsopyNotifyDate));
		busiOrderPay.setBsopyCdate(new Date());
		busiOrderPay.setBsopyUdate(new Date());

		busiOrderPayService.updateBusiOrderPay(busiOrderPay);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiOrderPayController]:end updateBusiOrderPay");

	}

	/**
	* 删除
	*
	* @param bsopyUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiOrderPay (String bsopyUuid, HttpServletResponse response) {
		logger.info("[BusiOrderPayController]:begin deleteBusiOrderPay");
		if (StringUtil.isEmpty(bsopyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiOrderPay busiOrderPay = new BusiOrderPay();
		busiOrderPay.setBsopyUuid(bsopyUuid);

		busiOrderPayService.deleteBusiOrderPay(busiOrderPay);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiOrderPayController]:end deleteBusiOrderPay");

	}

	/**
	* 批量删除
	*
	* @param bsopyUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiOrderPay (String bsopyUuids, HttpServletResponse response) {
		logger.info("[BusiOrderPayController]:begin deleteBatchBusiOrderPay");
		if (StringUtil.isEmpty(bsopyUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}
		String[] uuids=bsopyUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		busiOrderPayService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiOrderPayController]:end deleteBatchBusiOrderPay");

	}

	/**
	* 获取单个
	*
	* @param bsopyUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiOrderPay (String bsopyUuid, HttpServletResponse response) {
		logger.info("[BusiOrderPayController]:begin viewsBusiOrderPay");
		if (StringUtil.isEmpty(bsopyUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}
		BusiOrderPay busiOrderPay = new BusiOrderPay();
		busiOrderPay.setBsopyUuid(bsopyUuid);

		busiOrderPay = busiOrderPayService.getBusiOrderPay(busiOrderPay);

		BusiOrderPayVO busiOrderPayVO = new BusiOrderPayVO();
		busiOrderPayVO.convertPOToVO(busiOrderPay);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiOrderPayVO), response);
		logger.info("[BusiOrderPayController]:end viewsBusiOrderPay");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findBusiOrderPayList (HttpServletResponse response) {
		logger.info("[BusiOrderPayController]:begin findBusiOrderPayList");
		List<BusiOrderPay> lists = busiOrderPayService.findBusiOrderPayList();
		List<BusiOrderPayVO> vos = new ArrayList<BusiOrderPayVO>();
		BusiOrderPayVO vo;
		for (BusiOrderPay busiOrderPay : lists) {
			vo = new BusiOrderPayVO();
			vo.convertPOToVO(busiOrderPay);
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiOrderPayController]:end findBusiOrderPayList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findBusiOrderPayPage (Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiOrderPayController]:begin findBusiOrderPayPage");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiOrderPay> page = busiOrderPayService.findBusiOrderPayPage(pageNum, pageSize);
		Page<BusiOrderPayVO> pageVO = new Page<BusiOrderPayVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<BusiOrderPayVO> vos = new ArrayList<BusiOrderPayVO>();
		BusiOrderPayVO vo;
		for (BusiOrderPay busiOrderPay : page.getResult()) {
			vo = new BusiOrderPayVO();
			vo.convertPOToVO(busiOrderPay);
			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiOrderPayController]:end findBusiOrderPayPage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}