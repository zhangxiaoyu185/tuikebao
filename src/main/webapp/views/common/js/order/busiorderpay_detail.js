// JavaScript Document
$(function () {
	initDetail();
});
//初始化
function initDetail(){
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'bsopyUuid='+encodeURIComponent(id);
	getOData(str,"busiOrderPay/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsopyOrder").text(oData.data.bsopyOrder || "");
			$(".bsopyClientIp").text(oData.data.bsopyClientIp || "");
			$(".bsopyPayChannel").text(oData.data.bsopyPayChannel || "");
			$(".bsopyTradeType").text(oData.data.bsopyTradeType || "");
			$(".bsopyUser").text(oData.data.bsopyUser || "");
			$(".bsopyReturnUrl").text(oData.data.bsopyReturnUrl || "");
			$(".bsopyPayParams").text(oData.data.bsopyPayParams || "");
			$(".bsopyResultCode").text(oData.data.bsopyResultCode || "");
			$(".bsopyErrorCode").text(oData.data.bsopyErrorCode || "");
			$(".bsopyErrorMsg").text(oData.data.bsopyErrorMsg || "");
			$(".bsopyOutTradeNo").text(oData.data.bsopyOutTradeNo || "");
			$(".bsopyPayResult").text(oData.data.bsopyPayResult || "");
			$(".bsopyPayDate").text(oData.data.bsopyPayDate || "");
			$(".bsopyNotifyDate").text(oData.data.bsopyNotifyDate || "");
			$(".bsopyCdate").text(oData.data.bsopyCdate || "");
			$(".bsopyUdate").text(oData.data.bsopyUdate || "");
			/*var imgUrl="/"+oData.data.list[0].cratmDir+"/"+oData.data.list[0].cratmFileName;
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/thumb/"+oData.data.list[0].cratmUuid,function(realWidth,realHeight){
				var width = 0;
				var height = 200;
				//如果真实的宽度大于浏览器的宽度就按照200显示
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".preimg").attr("src",urlfile+"/coreAttachment/image/get/thumb/"+oData.data.list[0].cratmUuid);
				$(".preimg").attr("data-filename",imgUrl);
			});*/
		} else {
			alert(data.errMsg);
		}
	}});
}
