package com.xiaoyu.lingdian.controller.product;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.BusiProduct;
import com.xiaoyu.lingdian.entity.BusiShoppingCart;
import com.xiaoyu.lingdian.service.CoreUserService;
import com.xiaoyu.lingdian.service.product.BusiProductService;
import com.xiaoyu.lingdian.service.product.BusiShoppingCartService;
import com.xiaoyu.lingdian.vo.BusiShoppingCartVO;

@Controller
@RequestMapping(value="/busiShoppingCart")
public class BusiShoppingCartController extends BaseController {

	/**
	* 购物车表
	*/
	@Autowired
	private BusiShoppingCartService busiShoppingCartService;
	
	/**
	* 用户表
	*/
	@Autowired
	private CoreUserService coreUserService;
	
   /**
    * 商品表
    */
    @Autowired
    private BusiProductService busiProductService;
	
	/**
	* 加入购物车
	*
	* @param bssctUser 所属用户
	* @param bssctProduct 商品标识
	* @param bssctQuantity 加入数量
	* @param bssctAttrName 选择商品属性组合名称（颜色：绿色尺寸：L）
	* @param bssctAttrValue 选择商品属性组合标识(123|456)
	* @param bssctFee 加入时价格
	* @param bssctTags 标签集合（七|赠|正）
	* @param bssctName 商品名称
	* @param bssctDesc 商品描述
	* @param bssctSharePic 推客封面图
	* @param bssctStorePic 商城封面图
	* @return
	*/
	@RequestMapping(value="/add/busiShoppingCart", method=RequestMethod.POST)
	public void addBusiShoppingCart (String bssctUser, String bssctProduct, Integer bssctQuantity, String bssctAttrName, String bssctAttrValue, Double bssctFee, String bssctTags, String bssctName, String bssctDesc, String bssctSharePic, String bssctStorePic, HttpServletResponse response) {
		logger.info("[BusiShoppingCartController]:begin addBusiShoppingCart");

		if (StringUtil.isEmpty(bssctUser)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "所属用户不能为空!"), response);
			logger.info("[BusiShoppingCartController]:end addBusiShoppingCart");
			return;
		}
		if (StringUtil.isEmpty(bssctProduct)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品标识不能为空!"), response);
			logger.info("[BusiShoppingCartController]:end addBusiShoppingCart");
			return;
		}
		if (null == bssctQuantity || 0 == bssctQuantity) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "加入数量必传!"), response);
			logger.info("[BusiShoppingCartController]:end addBusiShoppingCart");
			return;
		}
		if (StringUtil.isEmpty(bssctAttrName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择商品属性组合名称!"), response);
			logger.info("[BusiShoppingCartController]:end addBusiShoppingCart");
			return;
		}
		if (StringUtil.isEmpty(bssctAttrValue)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择商品属性组合标识!"), response);
			logger.info("[BusiShoppingCartController]:end addBusiShoppingCart");
			return;
		}
		if (null == bssctFee || 0.0 == bssctFee) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "加入时价格必传!"), response);
			logger.info("[BusiShoppingCartController]:end addBusiShoppingCart");
			return;
		}
		if (StringUtil.isEmpty(bssctName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "商品名称必填!"), response);
			logger.info("[BusiShoppingCartController]:end addBusiShoppingCart");
			return;
		}
		BusiShoppingCart busiShoppingCart = new BusiShoppingCart();
		String uuid = RandomUtil.generateString(16);
		busiShoppingCart.setBssctUuid(uuid);
		busiShoppingCart.setBssctUser(bssctUser);
		busiShoppingCart.setBssctProduct(bssctProduct);
		busiShoppingCart.setBssctQuantity(bssctQuantity);
		busiShoppingCart.setBssctAttrName(bssctAttrName);
		busiShoppingCart.setBssctAttrValue(bssctAttrValue);
		busiShoppingCart.setBssctFee(bssctFee);
		busiShoppingCart.setBssctTags(bssctTags);
		busiShoppingCart.setBssctName(bssctName);
		busiShoppingCart.setBssctDesc(bssctDesc);
		busiShoppingCart.setBssctSharePic(bssctSharePic);
		busiShoppingCart.setBssctStorePic(bssctStorePic);
		busiShoppingCart.setBssctCdate(new Date());
		busiShoppingCart.setBssctUdate(new Date());
		busiShoppingCartService.insertBusiShoppingCart(busiShoppingCart);

		//更新用户的购物车数量
		coreUserService.updateCoreUserByOrderCount(bssctUser, 1, 0, 0, 0, 0, 0, 0, 0, 0);
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[BusiShoppingCartController]:end addBusiShoppingCart");
	}

	/**
	* 删除
	*
	* @param bssctUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteBusiShoppingCart (String bssctUuid, HttpServletResponse response) {
		logger.info("[BusiShoppingCartController]:begin deleteBusiShoppingCart");
		if (StringUtil.isEmpty(bssctUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiShoppingCartController]:end deleteBusiShoppingCart");
			return;
		}
		BusiShoppingCart busiShoppingCart = new BusiShoppingCart();
		busiShoppingCart.setBssctUuid(bssctUuid);
		busiShoppingCart = busiShoppingCartService.getBusiShoppingCart(busiShoppingCart);
		
		if (null != busiShoppingCart) {
			busiShoppingCartService.deleteBusiShoppingCart(busiShoppingCart);

			//更新用户的购物车数量
			coreUserService.updateCoreUserByOrderCount(busiShoppingCart.getBssctUser(), 1, 0, 0, 0, 0, 0, 0, 0, 0);
		}
				
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[BusiShoppingCartController]:end deleteBusiShoppingCart");
	}

	/**
	* 获取单个
	*
	* @param bssctUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsBusiShoppingCart (String bssctUuid, HttpServletResponse response) {
		logger.info("[BusiShoppingCartController]:begin viewsBusiShoppingCart");
		if (StringUtil.isEmpty(bssctUuid)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			logger.info("[BusiShoppingCartController]:end viewsBusiShoppingCart");
			return;
		}
		BusiShoppingCart busiShoppingCart = new BusiShoppingCart();
		busiShoppingCart.setBssctUuid(bssctUuid);

		busiShoppingCart = busiShoppingCartService.getBusiShoppingCart(busiShoppingCart);

		BusiShoppingCartVO busiShoppingCartVO = new BusiShoppingCartVO();
		busiShoppingCartVO.convertPOToVO(busiShoppingCart);
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "获取单个信息成功", busiShoppingCartVO), response);
		logger.info("[BusiShoppingCartController]:end viewsBusiShoppingCart");
	}

	/**
	* 我的购物车List
	* @param bssctUser
	* @return
	*/
	@RequestMapping(value="/find/all/by/my", method=RequestMethod.POST)
	public void findBusiShoppingCartListByMy (String bssctUser, HttpServletResponse response) {
		logger.info("[BusiShoppingCartController]:begin findBusiShoppingCartListByMy");
 		List<BusiShoppingCart> lists = busiShoppingCartService.findBusiShoppingCartList(bssctUser);
		List<BusiShoppingCartVO> vos = new ArrayList<BusiShoppingCartVO>();
		BusiShoppingCartVO vo;
		for (BusiShoppingCart busiShoppingCart : lists) { 
			vo = new BusiShoppingCartVO();
			vo.convertPOToVO(busiShoppingCart); 
			 //去查询商品表 获得商品状态
			String bssctProduct = busiShoppingCart.getBssctProduct();
            BusiProduct busiProduct = new BusiProduct();
            busiProduct.setBsprtUuid(bssctProduct); 
            busiProduct = busiProductService.getBusiProduct(busiProduct);
            if (busiProduct != null){
                vo.setBssctStatus(busiProduct.getBsprtSaleStatus());
            } 			vos.add(vo);
		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[BusiShoppingCartController]:end findBusiShoppingCartListByMy");
	}

}