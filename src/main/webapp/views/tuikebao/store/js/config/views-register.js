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
		productDescEvaluateItem: 'views/tmpl/commPage/productDescEvaluateItem.html:商品评论:#f6f5f7',
		shoppingCart: 'views/tmpl/commPage/shoppingCart.html:购物车:#f6f5f7',
		sureOrder: 'views/tmpl/commPage/sureOrder.html:确认订单:#f6f5f7',
		sureOrderCart: 'views/tmpl/commPage/sureOrderCart.html:确认订单(购物车):#f6f5f7',
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
		myIndex: 'views/tmpl/mine/myIndex.html:个人中心:#f6f5f7',
		myOrder: 'views/tmpl/mine/myOrder.html:我的订单:#f6f5f7',
		myOrder_waitPay_item: 'views/tmpl/mine/myOrder_waitPay_item.html:待付款:#f6f5f7',
		myOrder_waitSend_item: 'views/tmpl/mine/myOrder_waitSend_item.html:待发货:#f6f5f7',
		myOrder_waitReceive_item: 'views/tmpl/mine/myOrder_waitReceive_item.html:待收货:#f6f5f7',
		myOrder_waitEvaluate_item: 'views/tmpl/mine/myOrder_waitEvaluate_item.html:待评价:#f6f5f7',
		myOrder_finish_item: 'views/tmpl/mine/myOrder_finish_item.html:已完成:#f6f5f7',
		myOrderAfterSale: 'views/tmpl/mine/myOrderAfterSale.html:退款/售后:#f6f5f7',

		myOrderDesc: 'views/tmpl/mine/myOrderDesc.html:我的订单:#f6f5f7',
		myOrderDesc_waitPay: 'views/tmpl/mine/myOrderDesc_waitPay.html:待付款订单:#f6f5f7',
		myOrderDesc_waitSend: 'views/tmpl/mine/myOrderDesc_waitSend.html:待发货订单:#f6f5f7',
		myOrderDesc_waitReceive: 'views/tmpl/mine/myOrderDesc_waitReceive.html:待收货订单:#f6f5f7',
		myOrderDesc_waitEvaluate: 'views/tmpl/mine/myOrderDesc_waitEvaluate.html:待评价订单:#f6f5f7',
		myOrderDesc_finish: 'views/tmpl/mine/myOrderDesc_finish.html:已完成订单:#f6f5f7',

		express: 'views/tmpl/mine/express.html:物流详情:#f6f5f7',
		evaluate: 'views/tmpl/mine/evaluate.html:评价:#f6f5f7',
		myMessage: 'views/tmpl/mine/myMessage.html:我的消息:#f6f5f7',
		myMessageItem: 'views/tmpl/mine/myMessageItem.html:我的消息:#f6f5f7',
		addressManage: 'views/tmpl/mine/addressManage.html:管理收货地址:#f6f5f7',
		personInfo: 'views/tmpl/mine/personInfo.html:账户设置:#f6f5f7',
		receiveAddress:'views/tmpl/mine/receiveAddress.html:收货地址:#f6f5f7',
		updateAddress:'views/tmpl/mine/updateAddress.html:编辑收货地址:#f6f5f7',
		newAddress:'views/tmpl/mine/newAddress.html:新建收货地址:#f6f5f7',
		accountSetting:'views/tmpl/mine/accountSetting.html:账户设置:#f6f5f7',


		/********login************/	
		loginPage: 'views/tmpl/login/loginPage.html:登录:#f6f5f7',
		forgetPassword: 'views/tmpl/login/forgetPassword.html:忘记密码:#f6f5f7',
		register: 'views/tmpl/login/register.html:注册:#f6f5f7',


		test: 'views/tmpl/test.html:页面一:#fff',
		test2: 'views/tmpl/test2.html:页面二:#fff',
		test3: 'views/tmpl/test3.html:页面3:#fff',
		listTest: 'views/tmpl/listTest.html:测试列表分页:#fff',
		listTestItem: 'views/tmpl/listTestItem.html:测试列表Item:#fff',
		upImgTest: 'views/tmpl/upImgTest.html:上传图片控件:#fff'
	},
	initTmpl: 'shoppingCart',//启动页的配置
	initData: {list: ['文艺1', '博客2', '摄影3', '电影4', '民谣5', '旅行6', '吉他7']},//启动页的初始化数据，一般不需要测试用
	errMsg: {
		NETWORK_ERROR: '网络繁忙，请稍后重试:-(',
		LOGIN_NO_USERNAME: '请输入手机号',
		LOGIN_NO_PASSWORD: '请输入密码',
		SESSION_TIMEOUT: '您的登录已过期！'
	}
};