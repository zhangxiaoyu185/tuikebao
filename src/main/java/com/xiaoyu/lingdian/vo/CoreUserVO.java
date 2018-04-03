package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 用户表
*/
public class CoreUserVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crusrUuid;

	/**
	*帐户名称
	*/
	private String crusrName;

	/**
	*真实姓名
	*/
	private String crusrCode;

	/**
	*头像附件
	*/
	private String crusrHead;

	/**
	*身高
	*/
	private Double crusrHeight;

	/**
	*体重
	*/
	private Double crusrWeight;

	/**
	*年龄
	*/
	private Integer crusrAge;

	/**
	*居住省份
	*/
	private String crusrProvince;

	/**
	*居住省份名称
	*/
	private String crusrProvinceName;

	/**
	*居住城市
	*/
	private String crusrCity;

	/**
	*居住城市名称
	*/
	private String crusrCityName;

	/**
	*居住区域
	*/
	private String crusrDistrict;

	/**
	*居住区域名称
	*/
	private String crusrDistrictName;

	/**
	*详细地址
	*/
	private String crusrAddress;

	/**
	*电子邮箱
	*/
	private String crusrEmail;

	/**
	*邮箱验证:1验证0未验证
	*/
	private Integer crusrEmailVerify;

	/**
	*手机号码
	*/
	private String crusrMobile;

	/**
	*手机验证:1验证0未验证
	*/
	private Integer crusrMobileVerify;

	/**
	*职业
	*/
	private String crusrJob;

	/**
	*职业名称
	*/
	private String crusrJobName;

	/**
	*会员等级
	*/
	private String crusrGrade;

	/**
	*等级名称
	*/
	private String crusrGradeName;

	/**
	*等级图标
	*/
	private String crusrGradeIcon;

	/**
	*状态:1启用,0禁用
	*/
	private Integer crusrStatus;

	/**
	*创建日期
	*/
	private String crusrCdate;

	/**
	*修改日期
	*/
	private String crusrUdate;

	/**
	*生日
	*/
	private String crusrBirthday;

	/**
	*性别:1男,2女,0其它
	*/
	private Integer crusrGender;

	/**
	*QQ
	*/
	private String crusrQq;

	/**
	*交友宣言
	*/
	private String crusrSignature;

	/**
	*个人介绍
	*/
	private String crusrInfo;

	/**
	*备注
	*/
	private String crusrRemarks;

	/**
	*商城端授权OPENID
	*/
	private String crusrOpenidStore;

	/**
	*分享端授权OPENID
	*/
	private String crusrOpenidShare;

	/**
	*微信用户的昵称
	*/
	private String crusrWxNickname;

	/**
	*微信用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	*/
	private String crusrWxSex;

	/**
	*微信用户所在城市
	*/
	private String crusrWxCity;

	/**
	*微信用户所在国家
	*/
	private String crusrWxCountry;

	/**
	*微信用户所在省份
	*/
	private String crusrWxProvince;

	/**
	*微信用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	*/
	private String crusrWxHeadimgurl;

	/**
	*微信用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	*/
	private String crusrWxSubscribe;

	/**
	*冻结收益
	*/
	private Double crusrFrozenIncome;

	/**
	*可用收益
	*/
	private Double crusrAvailableIncome;

	/**
	*获得总收益
	*/
	private Double crusrTotalIncome;

	/**
	*当前积分
	*/
	private Integer crusrIntegral;

	/**
	*获得总积分
	*/
	private Integer crusrTotalIntegral;

	/**
	*邀请人
	*/
	private String crusrInviter;

	/**
	*邀请人姓名
	*/
	private String crusrInviterName;
	
	/**
	*类别:1未知通用
	*/
	private Integer crusrType;

	/**
	*最后登录时间
	*/
	private String crusrLastTime;

	/**
	*购物车商品数量
	*/
	private Integer crusrShoppingCart;

	/**
	*订单数量
	*/
	private Integer crusrOrder;

	/**
	*待付款订单数量
	*/
	private Integer crusrPendingPay;

	/**
	*待发货订单数量
	*/
	private Integer crusrPendingShip;

	/**
	*待收货订单数量
	*/
	private Integer crusrPendingRecv;

	/**
	*待评价订单数量
	*/
	private Integer crusrPendingEval;

	/**
	*退款售后订单数量
	*/
	private Integer crusrRefund;

	/**
	*已完成订单数量
	*/
	private Integer crusrFinished;

	/**
	*取消订单数量
	*/
	private Integer crusrCancelled;

	/**
	*是否点击过分享端首页0未点击1已点击
	*/
	private Integer crusrShareIndex;

	/**
	*邀请码
	*/
	private String crusrInviteCode;

	/**
	*累计邀请人数
	*/
	private Integer crusrInviteTotal;

	/**
	*活动邀请人数
	*/
	private Integer crusrInviteNused;

	/**
	*今日分享数
	*/
	private Integer crusrNowShare;

	/**
	*今日积分收益
	*/
	private Integer crusrNowAddintegral;

	/**
	*今日完成交易笔数
	*/
	private Integer crusrNowFinish;

	/**
	*今日返现收益
	*/
	private Double crusrNowAddincome;

	/**
	*昨日分享数
	*/
	private Integer crusrYestShare;

	/**
	*昨日积分收益
	*/
	private Integer crusrYestAddintegral;

	/**
	*昨日完成交易笔数
	*/
	private Integer crusrYestFinish;

	/**
	*昨日返现收益
	*/
	private Double crusrYestAddincome;

	/**
	*当月分享数
	*/
	private Integer crusrMonthShare;

	/**
	*当月积分收益
	*/
	private Integer crusrMonthAddintegral;

	/**
	*当月完成交易笔数
	*/
	private Integer crusrMonthFinish;

	/**
	*当月返现收益
	*/
	private Double crusrMonthAddincome;

	/**
	*今日提现次数
	*/
	private Integer crusrNowCashcount;

	/**
	*今日提现金额
	*/
	private Double crusrNowCashfee;

	/**
	*昨日提现次数
	*/
	private Integer crusrYestCashcount;

	/**
	*昨日提现金额
	*/
	private Double crusrYestCashfee;

	/**
	*当月提现次数
	*/
	private Integer crusrMonthCashcount;

	/**
	*当月提现金额
	*/
	private Double crusrMonthCashfee;

	/**
	*今日兑换次数
	*/
	private Integer crusrNowExchcount;

	/**
	*今日兑换积分
	*/
	private Integer crusrNowExchintegral;

	/**
	*昨日兑换次数
	*/
	private Integer crusrYestExchcount;

	/**
	*昨日兑换积分
	*/
	private Integer crusrYestExchintegral;

	/**
	*当月兑换次数
	*/
	private Integer crusrMonthExchcount;

	/**
	*当月兑换积分
	*/
	private Integer crusrMonthExchintegral;

	public void setCrusrUuid(String crusrUuid) {
		this.crusrUuid = crusrUuid;
	}

	public String getCrusrUuid( ) {
		return crusrUuid;
	}

	public void setCrusrName(String crusrName) {
		this.crusrName = crusrName;
	}

	public String getCrusrName( ) {
		return crusrName;
	}

	public void setCrusrCode(String crusrCode) {
		this.crusrCode = crusrCode;
	}

	public String getCrusrCode( ) {
		return crusrCode;
	}

	public void setCrusrHead(String crusrHead) {
		this.crusrHead = crusrHead;
	}

	public String getCrusrHead( ) {
		return crusrHead;
	}

	public void setCrusrHeight(Double crusrHeight) {
		this.crusrHeight = crusrHeight;
	}

	public Double getCrusrHeight( ) {
		return crusrHeight;
	}

	public void setCrusrWeight(Double crusrWeight) {
		this.crusrWeight = crusrWeight;
	}

	public Double getCrusrWeight( ) {
		return crusrWeight;
	}

	public void setCrusrAge(Integer crusrAge) {
		this.crusrAge = crusrAge;
	}

	public Integer getCrusrAge( ) {
		return crusrAge;
	}

	public void setCrusrProvince(String crusrProvince) {
		this.crusrProvince = crusrProvince;
	}

	public String getCrusrProvince( ) {
		return crusrProvince;
	}

	public void setCrusrProvinceName(String crusrProvinceName) {
		this.crusrProvinceName = crusrProvinceName;
	}

	public String getCrusrProvinceName( ) {
		return crusrProvinceName;
	}

	public void setCrusrCity(String crusrCity) {
		this.crusrCity = crusrCity;
	}

	public String getCrusrCity( ) {
		return crusrCity;
	}

	public void setCrusrCityName(String crusrCityName) {
		this.crusrCityName = crusrCityName;
	}

	public String getCrusrCityName( ) {
		return crusrCityName;
	}

	public void setCrusrDistrict(String crusrDistrict) {
		this.crusrDistrict = crusrDistrict;
	}

	public String getCrusrDistrict( ) {
		return crusrDistrict;
	}

	public void setCrusrDistrictName(String crusrDistrictName) {
		this.crusrDistrictName = crusrDistrictName;
	}

	public String getCrusrDistrictName( ) {
		return crusrDistrictName;
	}

	public void setCrusrAddress(String crusrAddress) {
		this.crusrAddress = crusrAddress;
	}

	public String getCrusrAddress( ) {
		return crusrAddress;
	}

	public void setCrusrEmail(String crusrEmail) {
		this.crusrEmail = crusrEmail;
	}

	public String getCrusrEmail( ) {
		return crusrEmail;
	}

	public void setCrusrEmailVerify(Integer crusrEmailVerify) {
		this.crusrEmailVerify = crusrEmailVerify;
	}

	public Integer getCrusrEmailVerify( ) {
		return crusrEmailVerify;
	}

	public void setCrusrMobile(String crusrMobile) {
		this.crusrMobile = crusrMobile;
	}

	public String getCrusrMobile( ) {
		return crusrMobile;
	}

	public void setCrusrMobileVerify(Integer crusrMobileVerify) {
		this.crusrMobileVerify = crusrMobileVerify;
	}

	public Integer getCrusrMobileVerify( ) {
		return crusrMobileVerify;
	}

	public void setCrusrJob(String crusrJob) {
		this.crusrJob = crusrJob;
	}

	public String getCrusrJob( ) {
		return crusrJob;
	}

	public void setCrusrJobName(String crusrJobName) {
		this.crusrJobName = crusrJobName;
	}

	public String getCrusrJobName( ) {
		return crusrJobName;
	}

	public void setCrusrGrade(String crusrGrade) {
		this.crusrGrade = crusrGrade;
	}

	public String getCrusrGrade( ) {
		return crusrGrade;
	}

	public void setCrusrGradeName(String crusrGradeName) {
		this.crusrGradeName = crusrGradeName;
	}

	public String getCrusrGradeName( ) {
		return crusrGradeName;
	}

	public void setCrusrGradeIcon(String crusrGradeIcon) {
		this.crusrGradeIcon = crusrGradeIcon;
	}

	public String getCrusrGradeIcon( ) {
		return crusrGradeIcon;
	}

	public void setCrusrStatus(Integer crusrStatus) {
		this.crusrStatus = crusrStatus;
	}

	public Integer getCrusrStatus( ) {
		return crusrStatus;
	}

	public void setCrusrCdate(String crusrCdate) {
		this.crusrCdate = crusrCdate;
	}

	public String getCrusrCdate( ) {
		return crusrCdate;
	}

	public void setCrusrUdate(String crusrUdate) {
		this.crusrUdate = crusrUdate;
	}

	public String getCrusrUdate( ) {
		return crusrUdate;
	}

	public void setCrusrBirthday(String crusrBirthday) {
		this.crusrBirthday = crusrBirthday;
	}

	public String getCrusrBirthday( ) {
		return crusrBirthday;
	}

	public void setCrusrGender(Integer crusrGender) {
		this.crusrGender = crusrGender;
	}

	public Integer getCrusrGender( ) {
		return crusrGender;
	}

	public void setCrusrQq(String crusrQq) {
		this.crusrQq = crusrQq;
	}

	public String getCrusrQq( ) {
		return crusrQq;
	}

	public void setCrusrSignature(String crusrSignature) {
		this.crusrSignature = crusrSignature;
	}

	public String getCrusrSignature( ) {
		return crusrSignature;
	}

	public void setCrusrInfo(String crusrInfo) {
		this.crusrInfo = crusrInfo;
	}

	public String getCrusrInfo( ) {
		return crusrInfo;
	}

	public void setCrusrRemarks(String crusrRemarks) {
		this.crusrRemarks = crusrRemarks;
	}

	public String getCrusrRemarks( ) {
		return crusrRemarks;
	}

	public String getCrusrOpenidStore() {
		return crusrOpenidStore;
	}

	public void setCrusrOpenidStore(String crusrOpenidStore) {
		this.crusrOpenidStore = crusrOpenidStore;
	}

	public String getCrusrOpenidShare() {
		return crusrOpenidShare;
	}

	public void setCrusrOpenidShare(String crusrOpenidShare) {
		this.crusrOpenidShare = crusrOpenidShare;
	}

	public void setCrusrWxNickname(String crusrWxNickname) {
		this.crusrWxNickname = crusrWxNickname;
	}

	public String getCrusrWxNickname( ) {
		return crusrWxNickname;
	}

	public void setCrusrWxSex(String crusrWxSex) {
		this.crusrWxSex = crusrWxSex;
	}

	public String getCrusrWxSex( ) {
		return crusrWxSex;
	}

	public void setCrusrWxCity(String crusrWxCity) {
		this.crusrWxCity = crusrWxCity;
	}

	public String getCrusrWxCity( ) {
		return crusrWxCity;
	}

	public void setCrusrWxCountry(String crusrWxCountry) {
		this.crusrWxCountry = crusrWxCountry;
	}

	public String getCrusrWxCountry( ) {
		return crusrWxCountry;
	}

	public void setCrusrWxProvince(String crusrWxProvince) {
		this.crusrWxProvince = crusrWxProvince;
	}

	public String getCrusrWxProvince( ) {
		return crusrWxProvince;
	}

	public void setCrusrWxHeadimgurl(String crusrWxHeadimgurl) {
		this.crusrWxHeadimgurl = crusrWxHeadimgurl;
	}

	public String getCrusrWxHeadimgurl( ) {
		return crusrWxHeadimgurl;
	}

	public void setCrusrWxSubscribe(String crusrWxSubscribe) {
		this.crusrWxSubscribe = crusrWxSubscribe;
	}

	public String getCrusrWxSubscribe( ) {
		return crusrWxSubscribe;
	}

	public void setCrusrFrozenIncome(Double crusrFrozenIncome) {
		this.crusrFrozenIncome = crusrFrozenIncome;
	}

	public Double getCrusrFrozenIncome( ) {
		return crusrFrozenIncome;
	}

	public void setCrusrAvailableIncome(Double crusrAvailableIncome) {
		this.crusrAvailableIncome = crusrAvailableIncome;
	}

	public Double getCrusrAvailableIncome( ) {
		return crusrAvailableIncome;
	}

	public void setCrusrTotalIncome(Double crusrTotalIncome) {
		this.crusrTotalIncome = crusrTotalIncome;
	}

	public Double getCrusrTotalIncome( ) {
		return crusrTotalIncome;
	}

	public void setCrusrIntegral(Integer crusrIntegral) {
		this.crusrIntegral = crusrIntegral;
	}

	public Integer getCrusrIntegral( ) {
		return crusrIntegral;
	}

	public void setCrusrTotalIntegral(Integer crusrTotalIntegral) {
		this.crusrTotalIntegral = crusrTotalIntegral;
	}

	public Integer getCrusrTotalIntegral( ) {
		return crusrTotalIntegral;
	}

	public void setCrusrInviter(String crusrInviter) {
		this.crusrInviter = crusrInviter;
	}

	public String getCrusrInviter( ) {
		return crusrInviter;
	}

	public void setCrusrType(Integer crusrType) {
		this.crusrType = crusrType;
	}

	public Integer getCrusrType( ) {
		return crusrType;
	}

	public void setCrusrLastTime(String crusrLastTime) {
		this.crusrLastTime = crusrLastTime;
	}

	public String getCrusrLastTime( ) {
		return crusrLastTime;
	}

	public void setCrusrShoppingCart(Integer crusrShoppingCart) {
		this.crusrShoppingCart = crusrShoppingCart;
	}

	public Integer getCrusrShoppingCart( ) {
		return crusrShoppingCart;
	}

	public void setCrusrOrder(Integer crusrOrder) {
		this.crusrOrder = crusrOrder;
	}

	public Integer getCrusrOrder( ) {
		return crusrOrder;
	}

	public void setCrusrPendingPay(Integer crusrPendingPay) {
		this.crusrPendingPay = crusrPendingPay;
	}

	public Integer getCrusrPendingPay( ) {
		return crusrPendingPay;
	}

	public void setCrusrPendingShip(Integer crusrPendingShip) {
		this.crusrPendingShip = crusrPendingShip;
	}

	public Integer getCrusrPendingShip( ) {
		return crusrPendingShip;
	}

	public void setCrusrPendingRecv(Integer crusrPendingRecv) {
		this.crusrPendingRecv = crusrPendingRecv;
	}

	public Integer getCrusrPendingRecv( ) {
		return crusrPendingRecv;
	}

	public void setCrusrPendingEval(Integer crusrPendingEval) {
		this.crusrPendingEval = crusrPendingEval;
	}

	public Integer getCrusrPendingEval( ) {
		return crusrPendingEval;
	}

	public void setCrusrRefund(Integer crusrRefund) {
		this.crusrRefund = crusrRefund;
	}

	public Integer getCrusrRefund( ) {
		return crusrRefund;
	}

	public void setCrusrFinished(Integer crusrFinished) {
		this.crusrFinished = crusrFinished;
	}

	public Integer getCrusrFinished( ) {
		return crusrFinished;
	}

	public void setCrusrCancelled(Integer crusrCancelled) {
		this.crusrCancelled = crusrCancelled;
	}

	public Integer getCrusrCancelled( ) {
		return crusrCancelled;
	}

	public void setCrusrShareIndex(Integer crusrShareIndex) {
		this.crusrShareIndex = crusrShareIndex;
	}

	public Integer getCrusrShareIndex( ) {
		return crusrShareIndex;
	}

	public void setCrusrInviteCode(String crusrInviteCode) {
		this.crusrInviteCode = crusrInviteCode;
	}

	public String getCrusrInviteCode( ) {
		return crusrInviteCode;
	}

	public void setCrusrInviteTotal(Integer crusrInviteTotal) {
		this.crusrInviteTotal = crusrInviteTotal;
	}

	public Integer getCrusrInviteTotal( ) {
		return crusrInviteTotal;
	}

	public Integer getCrusrInviteNused() {
		return crusrInviteNused;
	}

	public void setCrusrInviteNused(Integer crusrInviteNused) {
		this.crusrInviteNused = crusrInviteNused;
	}

	public void setCrusrNowShare(Integer crusrNowShare) {
		this.crusrNowShare = crusrNowShare;
	}

	public Integer getCrusrNowShare( ) {
		return crusrNowShare;
	}

	public void setCrusrNowAddintegral(Integer crusrNowAddintegral) {
		this.crusrNowAddintegral = crusrNowAddintegral;
	}

	public Integer getCrusrNowAddintegral( ) {
		return crusrNowAddintegral;
	}

	public void setCrusrNowFinish(Integer crusrNowFinish) {
		this.crusrNowFinish = crusrNowFinish;
	}

	public Integer getCrusrNowFinish( ) {
		return crusrNowFinish;
	}

	public void setCrusrNowAddincome(Double crusrNowAddincome) {
		this.crusrNowAddincome = crusrNowAddincome;
	}

	public Double getCrusrNowAddincome( ) {
		return crusrNowAddincome;
	}

	public void setCrusrYestShare(Integer crusrYestShare) {
		this.crusrYestShare = crusrYestShare;
	}

	public Integer getCrusrYestShare( ) {
		return crusrYestShare;
	}

	public void setCrusrYestAddintegral(Integer crusrYestAddintegral) {
		this.crusrYestAddintegral = crusrYestAddintegral;
	}

	public Integer getCrusrYestAddintegral( ) {
		return crusrYestAddintegral;
	}

	public void setCrusrYestFinish(Integer crusrYestFinish) {
		this.crusrYestFinish = crusrYestFinish;
	}

	public Integer getCrusrYestFinish( ) {
		return crusrYestFinish;
	}

	public void setCrusrYestAddincome(Double crusrYestAddincome) {
		this.crusrYestAddincome = crusrYestAddincome;
	}

	public Double getCrusrYestAddincome( ) {
		return crusrYestAddincome;
	}

	public void setCrusrMonthShare(Integer crusrMonthShare) {
		this.crusrMonthShare = crusrMonthShare;
	}

	public Integer getCrusrMonthShare( ) {
		return crusrMonthShare;
	}

	public void setCrusrMonthAddintegral(Integer crusrMonthAddintegral) {
		this.crusrMonthAddintegral = crusrMonthAddintegral;
	}

	public Integer getCrusrMonthAddintegral( ) {
		return crusrMonthAddintegral;
	}

	public void setCrusrMonthFinish(Integer crusrMonthFinish) {
		this.crusrMonthFinish = crusrMonthFinish;
	}

	public Integer getCrusrMonthFinish( ) {
		return crusrMonthFinish;
	}

	public void setCrusrMonthAddincome(Double crusrMonthAddincome) {
		this.crusrMonthAddincome = crusrMonthAddincome;
	}

	public Double getCrusrMonthAddincome( ) {
		return crusrMonthAddincome;
	}

	public void setCrusrNowCashcount(Integer crusrNowCashcount) {
		this.crusrNowCashcount = crusrNowCashcount;
	}

	public Integer getCrusrNowCashcount( ) {
		return crusrNowCashcount;
	}

	public void setCrusrNowCashfee(Double crusrNowCashfee) {
		this.crusrNowCashfee = crusrNowCashfee;
	}

	public Double getCrusrNowCashfee( ) {
		return crusrNowCashfee;
	}

	public void setCrusrYestCashcount(Integer crusrYestCashcount) {
		this.crusrYestCashcount = crusrYestCashcount;
	}

	public Integer getCrusrYestCashcount( ) {
		return crusrYestCashcount;
	}

	public void setCrusrYestCashfee(Double crusrYestCashfee) {
		this.crusrYestCashfee = crusrYestCashfee;
	}

	public Double getCrusrYestCashfee( ) {
		return crusrYestCashfee;
	}

	public void setCrusrMonthCashcount(Integer crusrMonthCashcount) {
		this.crusrMonthCashcount = crusrMonthCashcount;
	}

	public Integer getCrusrMonthCashcount( ) {
		return crusrMonthCashcount;
	}

	public void setCrusrMonthCashfee(Double crusrMonthCashfee) {
		this.crusrMonthCashfee = crusrMonthCashfee;
	}

	public Double getCrusrMonthCashfee( ) {
		return crusrMonthCashfee;
	}

	public void setCrusrNowExchcount(Integer crusrNowExchcount) {
		this.crusrNowExchcount = crusrNowExchcount;
	}

	public Integer getCrusrNowExchcount( ) {
		return crusrNowExchcount;
	}

	public void setCrusrNowExchintegral(Integer crusrNowExchintegral) {
		this.crusrNowExchintegral = crusrNowExchintegral;
	}

	public Integer getCrusrNowExchintegral( ) {
		return crusrNowExchintegral;
	}

	public void setCrusrYestExchcount(Integer crusrYestExchcount) {
		this.crusrYestExchcount = crusrYestExchcount;
	}

	public Integer getCrusrYestExchcount( ) {
		return crusrYestExchcount;
	}

	public void setCrusrYestExchintegral(Integer crusrYestExchintegral) {
		this.crusrYestExchintegral = crusrYestExchintegral;
	}

	public Integer getCrusrYestExchintegral( ) {
		return crusrYestExchintegral;
	}

	public void setCrusrMonthExchcount(Integer crusrMonthExchcount) {
		this.crusrMonthExchcount = crusrMonthExchcount;
	}

	public Integer getCrusrMonthExchcount( ) {
		return crusrMonthExchcount;
	}

	public void setCrusrMonthExchintegral(Integer crusrMonthExchintegral) {
		this.crusrMonthExchintegral = crusrMonthExchintegral;
	}

	public Integer getCrusrMonthExchintegral( ) {
		return crusrMonthExchintegral;
	}

	public String getCrusrInviterName() {
		return crusrInviterName;
	}

	public void setCrusrInviterName(String crusrInviterName) {
		this.crusrInviterName = crusrInviterName;
	}

	public CoreUserVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreUser po = (CoreUser) poObj;
		this.crusrUuid = po.getCrusrUuid();
		this.crusrName = po.getCrusrName();
		this.crusrCode = po.getCrusrCode();
		this.crusrHead = po.getCrusrHead();
		this.crusrHeight = po.getCrusrHeight();
		this.crusrWeight = po.getCrusrWeight();
		this.crusrAge = po.getCrusrAge();
		this.crusrProvince = po.getCrusrProvince();
		this.crusrProvinceName = po.getCrusrProvinceName();
		this.crusrCity = po.getCrusrCity();
		this.crusrCityName = po.getCrusrCityName();
		this.crusrDistrict = po.getCrusrDistrict();
		this.crusrDistrictName = po.getCrusrDistrictName();
		this.crusrAddress = po.getCrusrAddress();
		this.crusrEmail = po.getCrusrEmail();
		this.crusrEmailVerify = po.getCrusrEmailVerify();
		this.crusrMobile = po.getCrusrMobile();
		this.crusrMobileVerify = po.getCrusrMobileVerify();
		this.crusrJob = po.getCrusrJob();
		this.crusrJobName = po.getCrusrJobName();
		this.crusrGrade = po.getCrusrGrade();
		this.crusrGradeName = po.getCrusrGradeName();
		this.crusrGradeIcon = po.getCrusrGradeIcon();
		this.crusrStatus = po.getCrusrStatus();
		this.crusrCdate = po.getCrusrCdate()!=null?DateUtil.formatDefaultDate(po.getCrusrCdate()):"";
		this.crusrUdate = po.getCrusrUdate()!=null?DateUtil.formatDefaultDate(po.getCrusrUdate()):"";
		this.crusrBirthday = po.getCrusrBirthday();
		this.crusrGender = po.getCrusrGender();
		this.crusrQq = po.getCrusrQq();
		this.crusrSignature = po.getCrusrSignature();
		this.crusrInfo = po.getCrusrInfo();
		this.crusrRemarks = po.getCrusrRemarks();
		this.crusrOpenidStore = po.getCrusrOpenidStore();
		this.crusrOpenidShare = po.getCrusrOpenidShare();
		this.crusrWxNickname = po.getCrusrWxNickname();
		this.crusrWxSex = po.getCrusrWxSex();
		this.crusrWxCity = po.getCrusrWxCity();
		this.crusrWxCountry = po.getCrusrWxCountry();
		this.crusrWxProvince = po.getCrusrWxProvince();
		this.crusrWxHeadimgurl = po.getCrusrWxHeadimgurl();
		this.crusrWxSubscribe = po.getCrusrWxSubscribe();
		this.crusrFrozenIncome = po.getCrusrFrozenIncome();
		this.crusrAvailableIncome = po.getCrusrAvailableIncome();
		this.crusrTotalIncome = po.getCrusrTotalIncome();
		this.crusrIntegral = po.getCrusrIntegral();
		this.crusrTotalIntegral = po.getCrusrTotalIntegral();
		this.crusrInviter = po.getCrusrInviter();
		this.crusrType = po.getCrusrType();
		this.crusrLastTime = po.getCrusrLastTime()!=null?DateUtil.formatDefaultDate(po.getCrusrLastTime()):"";
		this.crusrShoppingCart = po.getCrusrShoppingCart();
		this.crusrOrder = po.getCrusrOrder();
		this.crusrPendingPay = po.getCrusrPendingPay();
		this.crusrPendingShip = po.getCrusrPendingShip();
		this.crusrPendingRecv = po.getCrusrPendingRecv();
		this.crusrPendingEval = po.getCrusrPendingEval();
		this.crusrRefund = po.getCrusrRefund();
		this.crusrFinished = po.getCrusrFinished();
		this.crusrCancelled = po.getCrusrCancelled();
		this.crusrShareIndex = po.getCrusrShareIndex();
		this.crusrInviteCode = po.getCrusrInviteCode();
		this.crusrInviteTotal = po.getCrusrInviteTotal();
		this.crusrInviteNused = po.getCrusrInviteNused();
		this.crusrNowShare = po.getCrusrNowShare();
		this.crusrNowAddintegral = po.getCrusrNowAddintegral();
		this.crusrNowFinish = po.getCrusrNowFinish();
		this.crusrNowAddincome = po.getCrusrNowAddincome();
		this.crusrYestShare = po.getCrusrYestShare();
		this.crusrYestAddintegral = po.getCrusrYestAddintegral();
		this.crusrYestFinish = po.getCrusrYestFinish();
		this.crusrYestAddincome = po.getCrusrYestAddincome();
		this.crusrMonthShare = po.getCrusrMonthShare();
		this.crusrMonthAddintegral = po.getCrusrMonthAddintegral();
		this.crusrMonthFinish = po.getCrusrMonthFinish();
		this.crusrMonthAddincome = po.getCrusrMonthAddincome();
		this.crusrNowCashcount = po.getCrusrNowCashcount();
		this.crusrNowCashfee = po.getCrusrNowCashfee();
		this.crusrYestCashcount = po.getCrusrYestCashcount();
		this.crusrYestCashfee = po.getCrusrYestCashfee();
		this.crusrMonthCashcount = po.getCrusrMonthCashcount();
		this.crusrMonthCashfee = po.getCrusrMonthCashfee();
		this.crusrNowExchcount = po.getCrusrNowExchcount();
		this.crusrNowExchintegral = po.getCrusrNowExchintegral();
		this.crusrYestExchcount = po.getCrusrYestExchcount();
		this.crusrYestExchintegral = po.getCrusrYestExchintegral();
		this.crusrMonthExchcount = po.getCrusrMonthExchcount();
		this.crusrMonthExchintegral = po.getCrusrMonthExchintegral();
	}

}