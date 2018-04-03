
// function initConfig() {
// 	// var data = "&cractName="+cractName;
// 	// var url = urlfile + "coreAccount/getAccountByName";

	
// 	// ajaxTool("post",data,url,
// 	// 	function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
// 	// 		alert("error:" + data);
// 	// 	},
// 	// 	function success(data){
// 	// 		if(!data.success) {
// 	// 			alert(data.errMsg);
// 	// 		}else{
// 	// 			dataSave("cractUuid",data.data);
// 	// 			loadFont(data.data);
// 	// 			loadPic(data.data);
// 	// 		}
// 	// 	},
// 	// 	true
// 	// );


// 	// 微信配置
// 	wx.config({
// 	    debug: false, 
// 	    appId: "wxd728dc50b5aa8ee9", 
// 	    timestamp: '1469973321', 
// 	    nonceStr: '1937462683', 
// 	    signature: 'ef209fe61abda43b6591750bdd211ebc1bc7afef',
// 	    jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage'] // 功能列表，我们要使用JS-SDK的什么功能
// 	});
// }
// alert('123');
// //分享到朋友圈


// function openWxMune() {
// 	wx.showOptionMenu();
// }

// //分享给朋友
// function shareAppMessage(title) {
// 	wx.onMenuShareAppMessage({
//     title: '', // 分享标题
//     desc: '', // 分享描述
//     link: '', // 分享链接
//     imgUrl: '', // 分享图标
//     type: '', // 分享类型,music、video或link，不填默认为link
//     dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
//     success: function () { 
//         // 用户确认分享后执行的回调函数
//     },
//     cancel: function () { 
//         // 用户取消分享后执行的回调函数
//     }
// });
// }

function initWx() {
	if(dataGet('alearlyWx')=='1'&& /iPhone|iPad|iPod|Macintosh/i.test(navigator.userAgent)) {
		return;
	}
	var urlString = getUrlAllPath();
	var data = "&urlString=" + encodeURIComponent(urlString) +"&crwctUuid=161126131423yqiZ";
	var url = WEB_URL + "weixin/share/config";
	ajaxTool("post",data,url,
		function error(XMLHttpRequest, textStatus, errorThrown, fnErr){
			alert("error:" + data);
		},
		function success(data){
			if(!data.success) {
				alert(data.errMsg);
			}else{
				// 微信配置
				wx.config({
				    debug: false, 
				    appId: data.data.appid, 
				    timestamp: data.data.timestamp, 
				    nonceStr: data.data.nonceStr, 
				    signature: data.data.signature,
				    jsApiList: [
						'checkJsApi',
						'chooseWXPay',
						// 'onMenuShareTimeline',
						// 'onMenuShareAppMessage',
						// 'onMenuShareQQ',
						// 'onMenuShareWeibo', 
						// 'hideMenuItems',
						// 'showMenuItems', 
						// 'hideAllNonBaseMenuItem',
						// 'showAllNonBaseMenuItem', 
						// 'translateVoice',
						// 'startRecord', 
						// 'stopRecord', 
						// 'onRecordEnd',
						// 'playVoice', 
						// 'pauseVoice', 
						// 'stopVoice',
						// 'uploadVoice', 
						// 'downloadVoice', 
						// 'chooseImage',	
						// 'previewImage', 
						// 'uploadImage', 
						// 'downloadImage',
						// 'getNetworkType', 
						// 'openLocation', 
						// 'getLocation',
						// 'hideOptionMenu', 
						// 'showOptionMenu', 
						// 'closeWindow',
						// 'scanQRCode', 
						
						// 'openProductSpecificView', 
						// 'addCard', 
						// 'chooseCard',	
						// 'openCard' 
				     ]
				});
			}
		}, true
	);
}

wx.ready(function(){
	// if(dataGet('urlString') != "0") { //没有失败
	// 	dataSave('urlString', "1");
	// }
	dataSave('alearlyWx','1');
});

wx.error(function(res){
	alert(res.errMsg);
	dataSave('alearlyWx', "0");
});

function getUrlAllPath() {
	//return window.location.protocol + '//' + window.location.host + window.location.pathname;
	if (/iPhone|iPad|iPod|Macintosh/i.test(navigator.userAgent)) {
		// return location.href.split('?')[0];
		return location.href.split('#')[0];
	}
	if (/Android/i.test(navigator.userAgent)) {
		return location.href.split('#')[0];
	}
}



//去支付
function goToPay(data, backPageIndex) {
	var str = window.navigator.userAgent;
    var version = str.substring(8, 11);
    if (version != "5.0" && version != "6.0" ) {
        layer.closeAll();
        alert("微信浏览器系统版本过低，请将微信升级至5.0以上");
    } else {
    	wx.checkJsApi({
            jsApiList: [
                'chooseWXPay'
            ],
            success: function (res) {
            	wxPayFee(data, backPageIndex);
            },
            fail: function (res) {
            	alert(res.errMsg);
            	layer.closeAll();
            }
        });    	
    }
}

function wxPayFee(data, backPageIndex) {
	wx.chooseWXPay({
	    timestamp: data.data.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
	    nonceStr: data.data.nonceStr, // 支付签名随机串，不长于 32 位
	    package: data.data.packages, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
	    signType: data.data.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
	    paySign: data.data.paySign, // 支付签名
	    success: function (res) {
	        // 支付成功后的回调函数
	    	layer.closeAll();
            if (res.errMsg == "chooseWXPay:ok") {
            	// if(backPageIndex){
            	// 	window.history.go(backPageIndex);
            	// }else {
            	// 	window.history.go(-1);
            	// }
            	alert('支付成功');
            	nextView(WEB_URL + 'views/tuikebao/store/index.html');
            } else if (res.errMsg == "chooseWXPay:cancel") {
                alert("取消支付");
            } else if (res.errMsg == "chooseWXPay:fail") {
                alert("支付失败");
            }
	    }
	});
	/*
    WeixinJSBridge.invoke('getBrandWCPayRequest', {
                "appId" : data.data.appId, //公众号名称，由商户传入
                "timeStamp" : data.data.timeStamp, //时间戳
                "nonceStr" : data.data.nonceStr, //随机串
                "package" : data.data.packages,//统一支付接口返回的prepay_id 参数值，提交格式如：prepay_id=***
                "signType" : data.data.signType, //微信签名方式:sha1
                "paySign" : data.data.paySign //微信签名
    }, function(res) {
        layer.closeAll();
        if (res.err_msg == "get_brand_wcpay_request:ok") {
            backPage();
        } else if (res.err_msg == "get_brand_wcpay_request:cancel") {
            alert("取消支付");
        } else if (res.err_msg == "get_brand_wcpay_request:fail") {
            alert("支付失败");
        }
    });
    */
}