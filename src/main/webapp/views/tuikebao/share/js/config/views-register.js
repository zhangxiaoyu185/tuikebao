// ------------------------------------------------------------------------
// Views registry
//
var Views = {
	tmpl: {
		/********pageHome***********/
		index: 'views/tmpl/pageHome/index.html:首页:#f6f5f7',//#... 表示body背景颜色
		indexItem: 'views/tmpl/pageHome/indexItem.html:首页:#f6f5f7',//#... 表示body背景颜色
		productType: 'views/tmpl/pageHome/productType.html:分类:#f6f5f7',
		productTypeItem: 'views/tmpl/pageHome/productTypeItem.html:分类:#f6f5f7',
		hotList: 'views/tmpl/pageHome/hotList.html:热榜:#f6f5f7',
		hotListItem: 'views/tmpl/pageHome/hotListItem.html:热榜:#f6f5f7',
		
		/********commPage***********/
		productDesc: 'views/tmpl/commPage/productDesc.html:商品详情:#f6f5f7',
		productDescEvaluateItem: 'views/tmpl/commPage/productDescEvaluateItem.html:商品评价:#f6f5f7',
		page_404: 'views/tmpl/commPage/page_404.html:404:#f6f5f7',
		page_500: 'views/tmpl/commPage/page_500.html:500:#f6f5f7',
		shoppingEmpty: 'views/tmpl/commPage/shoppingEmpty.html:购物车(空):#f6f5f7',
		myorderEmpty: 'views/tmpl/commPage/myorderEmpty.html:我的订单(空):#f6f5f7',

		/********service************/
		serviceIndex: 'views/tmpl/service/serviceIndex.html:服务:#f6f5f7',
		serviceList: 'views/tmpl/service/serviceList.html:服务:#f6f5f7',
		serviceListItem: 'views/tmpl/service/serviceListItem.html:服务:#f6f5f7',
		serviceDesc: 'views/tmpl/service/serviceDesc.html:服务详情:#f6f5f7',
		onLine: 'views/tmpl/service/onLine.html:在线客服:#f6f5f7',
		aboutUs:'views/tmpl/service/aboutUs.html:关于我们:#f6f5f7',


		/********mine************/
		myOrder: 'views/tmpl/mine/myOrder.html:我的订单:#f6f5f7',
		cashWithdrawal:'views/tmpl/mine/cashWithdrawal.html:申请提现:#f6f5f7',
		personalCenter:'views/tmpl/mine/personalCenter.html:个人中心:#f6f5f7',
		messageCenter:'views/tmpl/mine/messageCenter.html:消息中心:#f6f5f7',
		messageCenterItem:'views/tmpl/mine/messageCenterItem.html:消息中心:#f6f5f7',
		upGrade:'views/tmpl/mine/upGrade.html:升级:#f6f5f7',
		creditsExchange:'views/tmpl/mine/creditsExchange.html:积分兑换:#f6f5f7',
		incomeDetails:'views/tmpl/mine/incomeDetails.html:收益明细:#f6f5f7',
		incomeDetailsItem:'views/tmpl/mine/incomeDetailsItem.html:收益明细:#f6f5f7',
		integralDetails:'views/tmpl/mine/integralDetails.html:积分明细:#f6f5f7',
		integralDetailsItem:'views/tmpl/mine/integralDetailsItem.html:积分明细:#f6f5f7',
		presentationRecord:'views/tmpl/mine/presentationRecord.html:提现记录:#f6f5f7',
		presentationRecordItem:'views/tmpl/mine/presentationRecordItem.html:提现记录:#f6f5f7',
		shareRecord:'views/tmpl/mine/shareRecord.html:分享记录:#f6f5f7',
		shareRecordItem:'views/tmpl/mine/shareRecordItem.html:分享记录:#f6f5f7',
		recordConversion:'views/tmpl/mine/recordConversion.html:兑换记录:#f6f5f7',
		recordConversionItem:'views/tmpl/mine/recordConversionItem.html:兑换记录:#f6f5f7',
		accountSetting: 'views/tmpl/mine/accountSetting.html:账户设置:#f6f5f7',



		/********friendRequest************/
		inviteFriends: 'views/tmpl/friendRequest/inviteFriends.html:邀请好友:#f6f5f7',
		rewardCollection: 'views/tmpl/friendRequest/rewardCollection.html:领取奖励:#f6f5f7',
		invitersList:'views/tmpl/friendRequest/invitersList.html:邀请人列表:#f6f5f7',
		invitersListItem:'views/tmpl/friendRequest/invitersListItem.html:邀请人列表:#f6f5f7',
		rankingList:'views/tmpl/friendRequest/rankingList.html:排行榜:#f6f5f7',
		collectionRules:'views/tmpl/friendRequest/collectionRules.html:领取规则:#f6f5f7', 

		/********login************/	
		loginPage: 'views/tmpl/login/loginPage.html:登录:#f6f5f7',
		forgetPassword: 'views/tmpl/login/forgetPassword.html:忘记密码:#f6f5f7',
		register: 'views/tmpl/login/register.html:注册:#f6f5f7',


		test: 'views/tmpl/test.html:页面一:#fff',
		test2: 'views/tmpl/test2.html:页面二:#fff',
		test3: 'views/tmpl/test3.html:页面3:#fff',
		listTest: 'views/tmpl/listTest.html:测试列表分页:#fff',
		listTestItem: 'views/tmpl/listTestItem.html:测试列表Item:#fff',
		upImgTest: 'views/tmpl/upImgTest.html:上传图片控件:#fff',
		
	},
	initTmpl: 'myOrder',//启动页的配置
	initData: {list: ['文艺1', '博客2', '摄影3', '电影4', '民谣5', '旅行6', '吉他7']},//启动页的初始化数据，一般不需要测试用
	errMsg: {
		NETWORK_ERROR: '网络繁忙，请稍后重试:-(',
		LOGIN_NO_USERNAME: '请输入手机号',
		LOGIN_NO_PASSWORD: '请输入密码',
		SESSION_TIMEOUT: '您的登录已过期！'
	}
};