
function shareTimeline(title, link, imgUrl, shareSuccess) {
	wx.onMenuShareTimeline({
	    title: title, // 分享标题
	    link: link, // 分享链接
	    imgUrl: imgUrl, // 分享图标
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	        shareSuccess();
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	        // alert("取消分享");
	    },
	    trigger: function() {
	    	var isLoginFlag = isLogin();
	        if(!isLoginFlag) {
	            alert('请您登录后再进行分享。如不登陆直接分享，您将不能获得收益！');
	        }
	    }
	});
}

function shareAppMessage(title, desc, link, imgUrl, shareSuccess) {
	wx.onMenuShareAppMessage({
	    title: title, // 分享标题
	    desc: desc, // 分享描述
	    link: link, // 分享链接
	    imgUrl: imgUrl, // 分享图标
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	        shareSuccess();
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    },
	    trigger: function() {
	    	var isLoginFlag = isLogin();
	        if(!isLoginFlag) {
	            alert('请您登录后再进行分享。如不登陆直接分享，您将不能获得收益！');
	        }
	    }
	});
}

function shareQQ(title, desc, link, imgUrl, shareSuccess) {
	wx.onMenuShareQQ({
	    title: title, // 分享标题
	    desc: desc, // 分享描述
	    link: link, // 分享链接
	    imgUrl: imgUrl, // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	       shareSuccess();
	    },
	    cancel: function () { 
	       // 用户取消分享后执行的回调函数
	    },
	    trigger: function() {
	    	var isLoginFlag = isLogin();
	        if(!isLoginFlag) {
	            alert('请您登录后再进行分享。如不登陆直接分享，您将不能获得收益！');
	        }
	    }
	});
}

function initWx() {
	if(dataGet('alearlyWx')=='1'&& /iPhone|iPad|iPod|Macintosh/i.test(navigator.userAgent)) {
		return;
	}
	var urlString = getUrlAllPath();
	var data = "&urlString=" + encodeURIComponent(urlString) +"&crwctUuid=161126131423yqiI";
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
						'onMenuShareTimeline',
						'onMenuShareAppMessage',
						'onMenuShareQQ'
				     ]
				});
			}
		},
		true,true
	);
}
	

wx.ready(function(){
	dataSave('alearlyWx','1');
	// if(dataGet('urlString') != "0") { //没有失败
	// 	dataSave('urlString', "1");
	// }
});

wx.error(function(res){
	alert(res.errMsg);
	dataSave('alearlyWx', "0");
});

function getUrlAllPath() {
	//return window.location.protocol + '//' + window.location.host + window.location.pathname;
	if (/iPhone|iPad|iPod|Macintosh/i.test(navigator.userAgent)) {
		//return location.href.split('?')[0];
		return location.href.split('#')[0];
	}
	if (/Android/i.test(navigator.userAgent)) {
		return location.href.split('#')[0];
	}
}

//去支付
function goToPay(data) {
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
            	wxPayFee(data);
            },
            fail: function (res) {
            	alert(res.errMsg);
            	layer.closeAll();
            }
        });    	
    }
}

function wxPayFee(data) {
	wx.chooseWXPay({
	    timestamp: data.data.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
	    nonceStr: data.data.nonceStr, // 支付签名随机串，不长于 32 位
	    package: data.data.packages, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
	    signType: data.data.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
	    paySign: data.data.paySign, // 支付签名
	    success: function (res) {
	        // 支付成功后的回调函数
	    	layer.closeAll();
            if (res.err_msg == "chooseWXPay:ok") {
                backPage();
            } else if (res.err_msg == "chooseWXPay:cancel") {
                alert("取消支付");
            } else if (res.err_msg == "chooseWXPay:fail") {
                alert("支付失败");
            }
	    }
	});
}