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
	var str = 'bsorrUuid='+encodeURIComponent(id);
	getOData(str,"busiOrder/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsorrUuid").text(oData.data.bsorrUuid || "");
			$(".bsorrUser").text(oData.data.bsorrUserName || "");
			$(".bsorrProductValue").text(oData.data.bsorrProductValue || 0);
			$(".bsorrExpressValue").text(oData.data.bsorrExpressValue || 0);
			$(".bsorrAdjustExpress").text(oData.data.bsorrAdjustExpress || 0);
			$(".bsorrAdjustProduct").text(oData.data.bsorrAdjustProduct || 0);
			$(".bsorrOrderValue").text(oData.data.bsorrOrderValue || 0);
			$(".bsorrActualPay").text(oData.data.bsorrActualPay || 0);
			$(".bsorrTotalValue").text(oData.data.bsorrTotalValue || 0);
			$(".bsorrRefund").text(oData.data.bsorrRefund || 0);
			$(".bsorrTradeNo").text(oData.data.bsorrTradeNo || "");
			$(".bsorrTotalQuantity").text(oData.data.bsorrTotalQuantity || 0);
			$(".bsorrTotalWeight").text(oData.data.bsorrTotalWeight || 0);
			$(".bsorrPayTime").text(oData.data.bsorrPayTime || "");
			var bsorrStatusCH = '支付中';
			if(2==oData.data.bsorrStatus) {
				bsorrStatusCH = '支付成功';
			}
			if(3==oData.data.bsorrStatus) {
				bsorrStatusCH = '支付失败';
			}
			$(".bsorrStatus").text(bsorrStatusCH || "");
			$(".bsorrCdate").text(oData.data.bsorrCdate || "");
			$(".bsorrUdate").text(oData.data.bsorrUdate || "");
			$(".bsorrName").text(oData.data.bsorrName || "");
			$(".bsorrIdCard").text(oData.data.bsorrIdCard || "");
			$(".bsorrMobile").text(oData.data.bsorrMobile || "");
			$(".bsorrZipCode").text(oData.data.bsorrZipCode || "");
			$(".bsorrProvince").text(oData.data.bsorrProvinceName || "");
			$(".bsorrCity").text(oData.data.bsorrCityName || "");
			$(".bsorrCounty").text(oData.data.bsorrCountyName || "");
			$(".bsorrAddress").text(oData.data.bsorrAddress || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
