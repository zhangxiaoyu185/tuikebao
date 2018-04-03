// (function($){
	'use strict'

	var isAddEventListener = false;

	$(function() {
		// FastClick.attach(document.body);
		//Views.receiveAddressView.show();


		// Views.productDescView.show();

		// Views.serviceIndexView.show();
// Views.personInfoView.show();
		dataSave('alearlyWx','0');
		if (/iPhone|iPad|iPod|Macintosh/i.test(navigator.userAgent)) {
			initWx();
		}

		dataSave('code', GetParpam('code'));

		dataSave('goProducrDescFlag', "false");
		dataSave('isNotLogin', "true");

		var shareProductUuid = GetParpam('productUuid');
		var shareUserUuid = GetParpam('shareUserUuid');

		//alert(window.location.href);

		if(shareProductUuid && shareUserUuid){
	    	dataSave('productUuid', shareProductUuid);
	    	dataSave('shareProductUuid', shareProductUuid);
	    	dataSave('shareUserUuid', shareUserUuid);
	    	dataSave('goProducrDescFlag', "true");			
		}else{
	    	dataSave('shareProduct', "");
	    	// dataSave('productUuid', "");
	    	dataSave('shareProductUuid', "");
	    	dataSave('shareUserUuid', "");			
		}
	// 	var state = GetParpam('state');
	// 　　if(state){
	// 	    var temp = state.split('_');
	// 	    if(temp.length==3) {
	// 	    	// dataSave('shareProduct', temp[0]);
	// 	    	dataSave('productUuid', temp[1]);
	// 	    	dataSave('shareProductUuid', temp[1]);
	// 	    	dataSave('shareUserUuid', temp[2]);
	// 	    	dataSave('goProducrDescFlag', "true");
	// 	    }else {
	// 	    	dataSave('shareProduct', "");
	// 	    	dataSave('productUuid', "");
	// 	    	dataSave('shareProductUuid', "");
	// 	    	dataSave('shareUserUuid', "");
	// 	    }
	// 　　}else {
	// 		dataSave('shareProduct', "");
	//     	dataSave('productUuid', "");
	//     	dataSave('shareProductUuid', "");
	//     	dataSave('shareUserUuid', "");
	// 	}

		if(!GetParpam('pageName')){
			Views.indexView.willShow();
		}else{
			if(GetParpam('pageName')=='productDesc') {
				if(GetParpam('productUuid')){
					dataSave('productUuid', GetParpam('productUuid'));
				}
				dataSave('initProude', 'true');
			}else{
				// dataSave('productUuid', '');
				dataSave('initProude', 'false');
			}

			Views[GetParpam('pageName')+'View'].willShow(null, true);
		}

		
		// Views.indexView.willShow();

		// Views["productDescView"].willShow();

		

		// Views.testView.show({
		// 	list: ['阿道夫1', '地煞符2', '撒旦法三大3']
		// });

//Views.receiveAddressView.show();// Views.accountSettingView.show();
	// Views.shoppingCartView.show();
 // Views.newAddressView.show();
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

