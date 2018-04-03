package com.xiaoyu.lingdian.controller.product;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.xiaoyu.lingdian.tool.BaseConstant;
import com.xiaoyu.lingdian.tool.MarkImageUtil;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import com.xiaoyu.lingdian.tool.qrcode.QRCodeUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.BusiHotList;
import com.xiaoyu.lingdian.entity.BusiIndexProduct;
import com.xiaoyu.lingdian.entity.BusiProduct;
import com.xiaoyu.lingdian.entity.BusiProductCategory;
import com.xiaoyu.lingdian.entity.CoreAttachment;
import com.xiaoyu.lingdian.entity.CoreShareCopy;
import com.xiaoyu.lingdian.entity.CoreSystemSet;
import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.service.CoreAttachmentService;
import com.xiaoyu.lingdian.service.CoreShareCopyService;
import com.xiaoyu.lingdian.service.CoreSystemSetService;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.index.BusiHotListService;
import com.xiaoyu.lingdian.service.product.BusiIndexProductService;
import com.xiaoyu.lingdian.service.product.BusiProductCategoryService;
import com.xiaoyu.lingdian.service.product.BusiProductService;
import com.xiaoyu.lingdian.vo.BusiProductVO;

@Controller
@RequestMapping(value="/busiProduct")
public class BusiProductController extends BaseController {

	/**
	* 商品表
	*/
	@Autowired
	private BusiProductService busiProductService;
	
	/**
	* 分类表
	*/
	@Autowired
	private BusiProductCategoryService busiProductCategoryService;

	/**
	* 系统设置表
	*/
	@Autowired
	private CoreSystemSetService coreSystemSetService;
	
	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;
	
	/**
	* 业务附件表
	*/
	@Autowired
	private CoreAttachmentService coreAttachmentService;
	
	/**
	* 系统分享文案设置表
	*/
	@Autowired
	private CoreShareCopyService coreShareCopyService;
	
   /**
    * 首页商品表
    */
    @Autowired
    private BusiIndexProductService busiIndexProductService;
    
    /**
    * 热榜表
    */
    @Autowired
    private BusiHotListService busiHotListService;

	/**
	 * 附件默认文件夹
	 */
	public static String ATTACHMENT_DIR_PATH = "C:\\file\\tuikebao\\";
	
	/**
	* 商品分享生成图
	* @return
	*/
	@RequestMapping(value="/create/share/product/pic", method=RequestMethod.GET)
	public void createShareProductPic (String shareCopy, String productUuid, String userUuid, HttpServletResponse response) {
		logger.info("[BusiProductController]:begin createShareProductPic");
		if (StringUtil.isEmpty(productUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品标识不能为空!"), response);
			logger.info("[BusiProductController]:end createShareProductPic");
			return;
		}
		if (StringUtil.isEmpty(userUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户标识不能为空!"), response);
			logger.info("[BusiProductController]:end createShareProductPic");
			return;
		}		
		if (StringUtil.isEmpty(shareCopy)) {
			//去文案表获取最新的一条文案
			CoreShareCopy coreShareCopy = coreShareCopyService.getCoreShareCopyTop();
			if (null == coreShareCopy || StringUtil.isEmpty(coreShareCopy.getCrscyCopy())) {
				writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请联系客服设置分享文案!"), response);
				logger.info("[BusiProductController]:end createShareProductPic");
				return;
			}
			shareCopy = coreShareCopy.getCrscyCopy();
		}
		if (shareCopy.length() > 28) {
			logger.info("分享文案超过28个字,生成图片有瑕疵");
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分享文案不能超过28个字!"), response);
			logger.info("[BusiProductController]:end createShareProductPic");
			return;
		}
		
		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(productUuid);
		busiProduct = busiProductService.getBusiProduct(busiProduct);
		if (null == busiProduct) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品不存在!"), response);
			logger.info("[BusiProductController]:end createShareProductPic");
			return;
		}
		String productDesc = busiProduct.getBsprtName();
		if (busiProduct.getBsprtName().length() > 36) {
			productDesc = busiProduct.getBsprtName().substring(0, 36);
		}
		
		CoreUser coreUser = new CoreUser();
		coreUser.setCrusrUuid(userUuid);
		coreUser = coreUserService.getCoreUser(coreUser);
		if (null == coreUser) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "用户不存在!"), response);
			logger.info("[BusiProductController]:end createShareProductPic");
			return;
		}
		
		//根据分享商品的分享链接生成分享的二维码
		CoreSystemSet coreSystemSet = new CoreSystemSet();
		coreSystemSet.setCrsstUuid(BaseConstant.SYSTEM_SET_UUID);
		coreSystemSet = coreSystemSetService.getCoreSystemSet(coreSystemSet);
		if (null == coreSystemSet) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "缺少系统设置,请联系客服!"), response);
			logger.info("[BusiProductController]:end createShareProductPic");
			return;
		}
		String baseDir = ATTACHMENT_DIR_PATH;
		if (!StringUtil.isEmpty(coreSystemSet.getCrsstAttachmentDir())) {
			baseDir = coreSystemSet.getCrsstAttachmentDir();
		}
		String codePicPath = baseDir+"QR_CODE\\"+busiProduct.getBsprtCategory()+"\\"+RandomUtil.generateString(20)+".png";
		String codeUrl = coreSystemSet.getCrsstProductDetails()+"P=" + productUuid+"&S=" + userUuid;
		QRCodeUtil.createQRCode(codeUrl, codePicPath, 130, null, 0);
		
		String headPath = Thread.currentThread().getContextClassLoader()
				.getResource("\\basic\\baseHead.png").getPath();
		if (!StringUtil.isEmpty(coreUser.getCrusrHead())) {
			CoreAttachment coreAttachment = new CoreAttachment();
			coreAttachment.setCratmUuid(coreUser.getCrusrHead());
			coreAttachment = coreAttachmentService.getCoreAttachment(coreAttachment);
			if (coreAttachment != null) {
				headPath = baseDir + coreAttachment.getCratmDir() + '/' + coreAttachment.getCratmFileName();
			}
		}
		
		String productPath = "";
		if (!StringUtil.isEmpty(busiProduct.getBsprtStorePic())) {
			CoreAttachment coreAttachment = new CoreAttachment();
			coreAttachment.setCratmUuid(busiProduct.getBsprtStorePic());
			coreAttachment = coreAttachmentService.getCoreAttachment(coreAttachment);
			if (coreAttachment != null) {
				productPath = baseDir + coreAttachment.getCratmDir() + '/' + coreAttachment.getCratmFileName();
			}
		}
		
		//获取
		byte[] bytes = new MarkImageUtil().outPic(headPath, codePicPath, productPath, shareCopy, productDesc);
		writePicStream(response, bytes);
		logger.info("[BusiProductController]:end createShareProductPic");
	}

	/**
	* 添加(默认为2已下架)
	*
	* @param bsprtName 商品名称
	* @param bsprtCategory 分类
	* @param bsprtDesc 商品描述
	* @param bsprtTel 客服电话
	* @param bsprtView 详情
	* @param bsprtSalePrice 售价范围
	* @param bsprtOrignalPrice 原价范围
	* @param bsprtSaleCount 销售数量
	* @param bsprtAddress 地区
	* @param bsprtIsVirtual 是否是虚拟商品:1是0否
	* @param bsprtExpress 快递费用
	* @param bsprtSharePic 推客封面图
	* @param bsprtStorePic 商城封面图
	* @param bsprtImageUrl1 主图1
	* @param bsprtImageUrl2 主图2
	* @param bsprtImageUrl3 主图3
	* @param bsprtImageUrl4 主图4
	* @param bsprtImageUrl5 主图5
	* @param bsprtAttrJson 所有属性标识JSON串
	* @param bsprtValueJson 所有属性值标识JSON串
	* @param bsprtWxzvalueJson 未选择属性值标识JSON串
	* @param bsprtProductTag 标签JSON串
	* @param bsprtRuleParam 规格参数JSON串
	* @param bsprtGradeShare 商品等级分享金额JSON串
	* @param bsprtHotSpot 商品热点标记
	* @param bsprtHotspotName 商品热点名称
	* @param bsprtHotspotIcon 商品热点图标
	* @return
	*/
	@RequestMapping(value="/add/busiProduct", method=RequestMethod.POST)
	public void addBusiProduct (String bsprtName, String bsprtCategory, String bsprtDesc, String bsprtTel, String bsprtView, String bsprtSalePrice, 
			String bsprtOrignalPrice, Integer bsprtSaleCount, String bsprtAddress, Integer bsprtIsVirtual, Integer bsprtExpress, String bsprtSharePic, 
			String bsprtStorePic, String bsprtImageUrl1, String bsprtImageUrl2, String bsprtImageUrl3, String bsprtImageUrl4, String bsprtImageUrl5, 
			String bsprtAttrJson, String bsprtValueJson, String bsprtWxzvalueJson, String bsprtProductTag, String bsprtRuleParam, String bsprtGradeShare, String bsprtHotSpot, 
			String bsprtHotspotName, String bsprtHotspotIcon, HttpServletResponse response) {
		logger.info("[BusiProductController]:begin addBusiProduct");

		if (StringUtil.isEmpty(bsprtName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品名称不能为空!"), response);
			logger.info("[BusiProductController]:end addBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtCategory)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分类不能为空!"), response);
			logger.info("[BusiProductController]:end addBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtSalePrice)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "售价范围不能为空!"), response);
			logger.info("[BusiProductController]:end addBusiProduct");
			return;
		}
		if (null == bsprtSaleCount) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "销售数量不能为空!"), response);
			logger.info("[BusiProductController]:end addBusiProduct");
			return;
		}
		if (null == bsprtIsVirtual) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择是否是虚拟商品!"), response);
			logger.info("[BusiProductController]:end addBusiProduct");
			return;
		}
		if (null == bsprtExpress) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "快递费用不能为空!"), response);
			logger.info("[BusiProductController]:end addBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtImageUrl1)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品主图1不能为空!"), response);
			logger.info("[BusiProductController]:end addBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtAttrJson)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所有属性标识JSON串不能为空!"), response);
			logger.info("[BusiProductController]:end addBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtValueJson)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所有属性值标识JSON串不能为空!"), response);
			logger.info("[BusiProductController]:end addBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtGradeShare)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品等级分享金额JSON串不能为空!"), response);
			logger.info("[BusiProductController]:end addBusiProduct");
			return;
		}
		BusiProduct busiProduct = new BusiProduct();
		String uuid = RandomUtil.generateString(16);
		busiProduct.setBsprtUuid(uuid);
		busiProduct.setBsprtName(bsprtName);
		busiProduct.setBsprtCategory(bsprtCategory);
		busiProduct.setBsprtDesc(bsprtDesc);
		busiProduct.setBsprtTel(bsprtTel);
		busiProduct.setBsprtView(bsprtView);
		busiProduct.setBsprtSalePrice(bsprtSalePrice);
		busiProduct.setBsprtOrignalPrice(bsprtOrignalPrice);
		busiProduct.setBsprtSaleCount(bsprtSaleCount);
		busiProduct.setBsprtAddress(bsprtAddress);
		busiProduct.setBsprtIsVirtual(bsprtIsVirtual);
		busiProduct.setBsprtVisitCount(0);
		busiProduct.setBsprtSaleStatus(2);
		busiProduct.setBsprtExpress(bsprtExpress);
		busiProduct.setBsprtSharePic(bsprtSharePic);
		busiProduct.setBsprtStorePic(bsprtStorePic);
		busiProduct.setBsprtImageUrl1(bsprtImageUrl1);
		busiProduct.setBsprtImageUrl2(bsprtImageUrl2);
		busiProduct.setBsprtImageUrl3(bsprtImageUrl3);
		busiProduct.setBsprtImageUrl4(bsprtImageUrl4);
		busiProduct.setBsprtImageUrl5(bsprtImageUrl5);
		busiProduct.setBsprtAttrJson(bsprtAttrJson);
		busiProduct.setBsprtValueJson(bsprtValueJson);
		busiProduct.setBsprtWxzvalueJson(bsprtWxzvalueJson);
		busiProduct.setBsprtProductTag(bsprtProductTag);
		busiProduct.setBsprtRuleParam(bsprtRuleParam);
		busiProduct.setBsprtGradeShare(bsprtGradeShare);
		busiProduct.setBsprtHotSpot(bsprtHotSpot);
		busiProduct.setBsprtHotspotIcon(bsprtHotspotIcon);
		busiProduct.setBsprtHotspotName(bsprtHotspotName);
		busiProduct.setBsprtCdate(new Date());
		busiProduct.setBsprtUdate(new Date());
		
		busiProductService.insertBusiProduct(busiProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiProductController]:end addBusiProduct");
	}
	
	/**
	* 修改
	*
	* @param bsprtUuid 标识UUID
	* @param bsprtName 商品名称
	* @param bsprtDesc 商品描述
	* @param bsprtTel 客服电话
	* @param bsprtView 详情
	* @param bsprtSalePrice 售价范围
	* @param bsprtOrignalPrice 原价范围
	* @param bsprtSaleCount 销售数量
	* @param bsprtAddress 地区
	* @param bsprtIsVirtual 是否是虚拟商品:1是0否
	* @param bsprtExpress 快递费用
	* @param bsprtSharePic 推客封面图
	* @param bsprtStorePic 商城封面图
	* @param bsprtImageUrl1 主图1
	* @param bsprtImageUrl2 主图2
	* @param bsprtImageUrl3 主图3
	* @param bsprtImageUrl4 主图4
	* @param bsprtImageUrl5 主图5
	* @param bsprtAttrJson 所有属性标识JSON串
	* @param bsprtValueJson 所有属性值标识JSON串
	* @param bsprtWxzvalueJson 未选择属性值标识JSON串
	* @param bsprtProductTag 标签JSON串
	* @param bsprtRuleParam 规格参数JSON串
	* @param bsprtGradeShare 商品等级分享金额JSON串
	* @param bsprtHotSpot 商品热点标记
	* @param bsprtHotspotName 商品热点名称
	* @param bsprtHotspotIcon 商品热点图标
	* @return
	*/
	@RequestMapping(value="/update/busiProduct", method=RequestMethod.POST)
	public void updateBusiProduct (String bsprtUuid, String bsprtName, String bsprtDesc, String bsprtTel, String bsprtView, String bsprtSalePrice, 
			String bsprtOrignalPrice, Integer bsprtSaleCount, String bsprtAddress, Integer bsprtIsVirtual, Integer bsprtExpress, String bsprtSharePic, 
			String bsprtStorePic, String bsprtImageUrl1, String bsprtImageUrl2, String bsprtImageUrl3, String bsprtImageUrl4, String bsprtImageUrl5, 
			String bsprtAttrJson, String bsprtValueJson, String bsprtWxzvalueJson, String bsprtProductTag, String bsprtRuleParam, String bsprtGradeShare, String bsprtHotSpot, 
			String bsprtHotspotName, String bsprtHotspotIcon, HttpServletResponse response) {
		logger.info("[BusiProductController]:begin updateBusiProduct");
		if (StringUtil.isEmpty(bsprtUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProductController]:end updateBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品名称不能为空!"), response);
			logger.info("[BusiProductController]:end updateBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtSalePrice)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "售价范围不能为空!"), response);
			logger.info("[BusiProductController]:end updateBusiProduct");
			return;
		}
		if (null == bsprtSaleCount) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "销售数量不能为空!"), response);
			logger.info("[BusiProductController]:end updateBusiProduct");
			return;
		}
		if (null == bsprtIsVirtual) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择是否是虚拟商品!"), response);
			logger.info("[BusiProductController]:end updateBusiProduct");
			return;
		}
		if (null == bsprtExpress) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "快递费用不能为空!"), response);
			logger.info("[BusiProductController]:end updateBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtImageUrl1)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品主图1不能为空!"), response);
			logger.info("[BusiProductController]:end updateBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtAttrJson)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所有属性标识JSON串不能为空!"), response);
			logger.info("[BusiProductController]:end updateBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtValueJson)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所有属性值标识JSON串不能为空!"), response);
			logger.info("[BusiProductController]:end updateBusiProduct");
			return;
		}
		if (StringUtil.isEmpty(bsprtGradeShare)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品等级分享金额JSON串不能为空!"), response);
			logger.info("[BusiProductController]:end updateBusiProduct");
			return;
		}
		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct.setBsprtName(bsprtName);
		busiProduct.setBsprtDesc(bsprtDesc);
		busiProduct.setBsprtTel(bsprtTel);
		busiProduct.setBsprtView(bsprtView);
		busiProduct.setBsprtSalePrice(bsprtSalePrice);
		busiProduct.setBsprtOrignalPrice(bsprtOrignalPrice);
		busiProduct.setBsprtSaleCount(bsprtSaleCount);
		busiProduct.setBsprtAddress(bsprtAddress);
		busiProduct.setBsprtIsVirtual(bsprtIsVirtual);
		busiProduct.setBsprtExpress(bsprtExpress);
		busiProduct.setBsprtSharePic(bsprtSharePic);
		busiProduct.setBsprtStorePic(bsprtStorePic);
		busiProduct.setBsprtImageUrl1(bsprtImageUrl1);
		busiProduct.setBsprtImageUrl2(bsprtImageUrl2);
		busiProduct.setBsprtImageUrl3(bsprtImageUrl3);
		busiProduct.setBsprtImageUrl4(bsprtImageUrl4);
		busiProduct.setBsprtImageUrl5(bsprtImageUrl5);
		busiProduct.setBsprtAttrJson(bsprtAttrJson);
		busiProduct.setBsprtValueJson(bsprtValueJson);
		busiProduct.setBsprtWxzvalueJson(bsprtWxzvalueJson);
		busiProduct.setBsprtProductTag(bsprtProductTag);
		busiProduct.setBsprtRuleParam(bsprtRuleParam);
		busiProduct.setBsprtGradeShare(bsprtGradeShare);
		busiProduct.setBsprtHotSpot(bsprtHotSpot);
		busiProduct.setBsprtHotspotIcon(bsprtHotspotIcon);
		busiProduct.setBsprtHotspotName(bsprtHotspotName);
		busiProduct.setBsprtUdate(new Date());

		busiProductService.updateBusiProduct(busiProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[BusiProductController]:end updateBusiProduct");
	}

	/**
	* 改为销售
	*
	* @param bsprtUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/enable", method=RequestMethod.POST)
	public void enable (String bsprtUuid, HttpServletResponse response) {
		logger.info("[BusiProductController]:begin enable");

		if (StringUtil.isEmpty(bsprtUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProductController]:end enable");
			return;
		}
		
		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct.setBsprtSaleStatus(1);
		busiProduct.setBsprtUdate(new Date());

		busiProductService.updateBusiProduct(busiProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "改为销售成功!"),response);
		logger.info("[BusiProductController]:end enable");
	}
	
	/**
	* 改为下架
	*
	* @param bsprtUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/shelf", method=RequestMethod.POST)
	public void shelf (String bsprtUuid, HttpServletResponse response) {
		logger.info("[BusiProductController]:begin shelf");

		if (StringUtil.isEmpty(bsprtUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProductController]:end shelf");
			return;
		}
		
		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct.setBsprtSaleStatus(2);
		busiProduct.setBsprtUdate(new Date());

		busiProductService.updateBusiProduct(busiProduct);
		
		//当商品下架时，若配置表中有该商品，则同时删除配置表（热榜表，首页商品表）的该商品(1)
		BusiHotList busiHotList = new BusiHotList(); 
        busiHotList.setBshltProduct(bsprtUuid);
        busiHotListService.deleteBusiHotList(busiHotList);

        BusiIndexProduct busiIndexProduct = new BusiIndexProduct(); 
        busiIndexProduct.setBsiptProduct(bsprtUuid);
        busiIndexProductService.deleteBusiIndexProduct(busiIndexProduct);
        
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "改为下架成功!"),response);
		logger.info("[BusiProductController]:end shelf");
	}
	
	/**
	* 改为删除
	*
	* @param bsprtUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete (String bsprtUuid, HttpServletResponse response) {
		logger.info("[BusiProductController]:begin delete");

		if (StringUtil.isEmpty(bsprtUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProductController]:end delete");
			return;
		}
		
		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct.setBsprtSaleStatus(3);
		busiProduct.setBsprtUdate(new Date());

		busiProductService.updateBusiProduct(busiProduct);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "改为删除成功!"),response);
		logger.info("[BusiProductController]:end delete");
	}
	
	/**
	* 批量删除
	*
	* @param bsprtUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchBusiProduct (String bsprtUuids, HttpServletResponse response) {
		logger.info("[BusiProductController]:begin deleteBatchBusiProduct");
		if (StringUtil.isEmpty(bsprtUuids)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			logger.info("[BusiProductController]:end deleteBatchBusiProduct");
			return;
		}
		String[] uuids=bsprtUuids.split("\\|");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			logger.info("[BusiProductController]:end deleteBatchBusiProduct");
			return;
		}
		busiProductService.updateBatchBusiProductDeteleByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[BusiProductController]:end deleteBatchBusiProduct");
	}

	/**
	* 前台获取单个商品
	*
	* @param bsprtUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views/before", method=RequestMethod.POST)
	public void viewsBusiProductBefore (String bsprtUuid, HttpServletResponse response) {
		logger.info("[BusiProductController]:begin viewsBusiProductBefore");

		if (StringUtil.isEmpty(bsprtUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProductController]:end viewsBusiProductBefore");
			return;
		}

		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct = busiProductService.getBusiProduct(busiProduct);
		if (null == busiProduct  || 1 != busiProduct.getBsprtSaleStatus()) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品不存在或已下架!"), response);
			logger.info("[BusiProductController]:end viewsBusiProductBefore");
			return;
		}

		BusiProductVO busiProductVO = new BusiProductVO();
		busiProductVO.convertPOToVO(busiProduct);
		
		busiProductService.updateBusiProductVisitCount(bsprtUuid);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "前台获取单个信息成功", busiProductVO), response);
		logger.info("[BusiProductController]:end viewsBusiProductBefore");
	}
	
	/**
	* 后台获取单个商品
	*
	* @param bsprtUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views/back", method=RequestMethod.POST)
	public void viewsBusiProductByback (String bsprtUuid, HttpServletResponse response) {
		logger.info("[BusiProductController]:begin viewsBusiProductByback");
		if (StringUtil.isEmpty(bsprtUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiProductController]:end viewsBusiProductByback");
			return;
		}
		BusiProduct busiProduct = new BusiProduct();
		busiProduct.setBsprtUuid(bsprtUuid);
		busiProduct = busiProductService.getBusiProduct(busiProduct);
		if (null == busiProduct) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品不存在!"), response);
			logger.info("[BusiProductController]:end viewsBusiProductByback");
			return;
		}

		HashSet<String> hashCategoryUuids = new HashSet<String>();
		hashCategoryUuids.add(busiProduct.getBsprtCategory());
		List<String> categoryUuids = new ArrayList<>(hashCategoryUuids);
		Map<String, BusiProductCategory> categoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(categoryUuids);

		BusiProductVO busiProductVO = new BusiProductVO();
		busiProductVO.convertPOToVO(busiProduct);
		busiProductVO.setBsprtCategoryName(categoryMap.get(busiProduct.getBsprtCategory())==null?null:categoryMap.get(busiProduct.getBsprtCategory()).getBspcyName());
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "后台获取单个信息成功", busiProductVO), response);
		logger.info("[BusiProductController]:end viewsBusiProductByback");
	}

	/**
	* 获取所有销售中的商品list
	* 
	* @return
	*/
	@RequestMapping(value="/find/all/by/sale", method=RequestMethod.POST)
	public void findBusiProductForListsBySale (HttpServletResponse response) {
		logger.info("[BusiProductController]:begin findBusiProductForListsBySale");
		List<BusiProduct> lists = busiProductService.findBusiProductForListsBySale();
		List<BusiProductVO> vos = new ArrayList<BusiProductVO>();
		
		HashSet<String> hashCategoryUuids = new HashSet<String>();
		for (BusiProduct busiProduct : lists) {
			hashCategoryUuids.add(busiProduct.getBsprtCategory());
		}
		List<String> categoryUuids = new ArrayList<>(hashCategoryUuids);
		Map<String, BusiProductCategory> categoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(categoryUuids);

		BusiProductVO vo;
		for (BusiProduct busiProduct : lists) {
			vo = new BusiProductVO();			vo.convertPOToVO(busiProduct);
			vo.setBsprtCategoryName(categoryMap.get(busiProduct.getBsprtCategory())==null?null:categoryMap.get(busiProduct.getBsprtCategory()).getBspcyName());
			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiProductController]:end findBusiProductForListsBySale");
	}

	/**
	* 获取商品分类下的所有销售中的商品Page
	* 
	* @param bsprtCategory
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/category", method=RequestMethod.POST)
	public void findBusiProductForPagesByCategory (String bsprtCategory, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiProductController]:begin findBusiProductForPagesByCategory");

		if (StringUtil.isEmpty(bsprtCategory)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分类标识不能为空!"), response);
			logger.info("[BusiProductController]:end findBusiProductForPagesByCategory");
			return;
		}
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiProduct> page = busiProductService.findBusiProductForPagesByCategory(bsprtCategory, pageNum, pageSize);
		Page<BusiProductVO> pageVO = new Page<BusiProductVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashCategoryUuids = new HashSet<String>();
		for (BusiProduct busiProduct : page.getResult()) {
			hashCategoryUuids.add(busiProduct.getBsprtCategory());
		}
		List<String> categoryUuids = new ArrayList<>(hashCategoryUuids);
		Map<String, BusiProductCategory> categoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(categoryUuids);

		List<BusiProductVO> vos = new ArrayList<BusiProductVO>();
		BusiProductVO vo;
		for (BusiProduct busiProduct : page.getResult()) {
			vo = new BusiProductVO();
			vo.convertPOToVO(busiProduct);
			vo.setBsprtCategoryName(categoryMap.get(busiProduct.getBsprtCategory())==null?null:categoryMap.get(busiProduct.getBsprtCategory()).getBspcyName());
			vos.add(vo);
		}
		pageVO.setResult(vos);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiProductController]:end findBusiProductForPagesByCategory");
	}

	/**
	* 根据商品分类标识或商品名称获取所有未删除的page
	* 
	* @param bsprtCategory
	* @param bsprtName
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/back/find/by/cnd", method=RequestMethod.POST)
	public void findBusiProductPageByBack (String bsprtCategory, String bsprtName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[BusiProductController]:begin findBusiProductPageByBack");

		if (StringUtil.isEmpty(bsprtCategory)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "分类标识不能为空!"), response);
			logger.info("[BusiProductController]:end findBusiProductPageByBack");
			return;
		}		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		Page<BusiProduct> page = busiProductService.findBusiProductPage(bsprtCategory, bsprtName, pageNum, pageSize);
		Page<BusiProductVO> pageVO = new Page<BusiProductVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		
		HashSet<String> hashCategoryUuids = new HashSet<String>();
		for (BusiProduct busiProduct : page.getResult()) {
			hashCategoryUuids.add(busiProduct.getBsprtCategory());
		}
		List<String> categoryUuids = new ArrayList<>(hashCategoryUuids);
		Map<String, BusiProductCategory> categoryMap = busiProductCategoryService.findBusiProductCategoryListByUuids(categoryUuids);

		List<BusiProductVO> vos = new ArrayList<BusiProductVO>();
		BusiProductVO vo;
		for (BusiProduct busiProduct : page.getResult()) {
			vo = new BusiProductVO();			vo.convertPOToVO(busiProduct);
			vo.setBsprtCategoryName(categoryMap.get(busiProduct.getBsprtCategory())==null?null:categoryMap.get(busiProduct.getBsprtCategory()).getBspcyName());			vos.add(vo);
		}
		pageVO.setResult(vos);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "page列表获取成功!", pageVO),response);
		logger.info("[BusiProductController]:end findBusiProductPageByBack");
	}

}