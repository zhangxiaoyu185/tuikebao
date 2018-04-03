// (function($){
	'use strict'

	var isAddEventListener = false;

	$(function() {
		FastClick.attach(document.body);
		// Views.listTestView.show();
		dataSave('alearlyWx','0');
		if (/iPhone|iPad|iPod|Macintosh/i.test(navigator.userAgent)) {
			initWx();
		}
		
		// Views.loginView.willShow({},true);
		// Views.messageCenterView.show();
		// Views.upGradeView.show();
		dataSave('isNotLogin', "true");
		dataSave('code', GetParpam('code'));
		var shareUserUuid = GetParpam('UCODE');
		if(shareUserUuid){
		    // var temp = state.split('_');
			dataSave('shareUserUuid', shareUserUuid);
		}else {
			dataSave('shareUserUuid', "");
		}

		// Views.indexView.willShow();
		if(!GetParpam('pageName')){
			Views.indexView.willShow();
			dataSave('initPage', '');
		}else{
			if(GetParpam('pageName')=='productDesc') {
				// dataSave('productUuid', GetParpam('productUuid'));
				if(GetParpam('productUuid')){
					dataSave('productUuid', GetParpam('productUuid'));
				}
				dataSave('initProude', 'true');
			}else{
				// dataSave('productUuid', '');
				dataSave('initProude', 'false');
			}
			dataSave('initPage', GetParpam('pageName'));
			Views[GetParpam('pageName')+'View'].willShow(null, true);
		}


		
		


		//Views.productDescView.show();
		// Views.productDescView.show();

		// Views.myOrderView.show();

		// Views.inviteFriendsView.show();

		// Views.testView.show({
		// 	list: ['阿道夫1', '地煞符2', '撒旦法三大3']
		// });
 
		// Views.test3View.show();
		// Views.personInfoView.show();
		// $.extend({}, Views.PanelView, {
		//     options: {
		//         tmpl: Views.initTmpl,
		//         hasFootNav: true,
		//         footItemOrder:0, // hasFootNav设置true才有效   表示现在远着的是底部第几个菜单
		// 		itemClass: 'item' //hasFootNav设置true才有效   导航条item的筛选条件
		//     },

		//     willShow: function() {

		//     },

		//     didShow: function() {

		//     }

		// }).show(Views.initData||{});
	})
// })//(jQuery); 

