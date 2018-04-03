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
	var str = 'bssrdUuid='+encodeURIComponent(id);
	getOData(str,"busiShareRecord/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bssrdUser").text(oData.data.bssrdUserName || "");
			$(".bssrdGrade").text(oData.data.bssrdGradeName || "");
			$(".bssrdCommision").text(oData.data.bssrdCommision || "");
			$(".bssrdProduct").text(oData.data.bssrdProduct || "");
			$("#bssrdSharePic").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bssrdSharePic);
			$("#bssrdStorePic").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bssrdStorePic);
			$(".bssrdProductName").text(oData.data.bssrdProductName || "");
			$(".bssrdBill").text(oData.data.bssrdBill || "");
			$(".bssrdCdate").text(oData.data.bssrdCdate || "");
			$(".bssrdUdate").text(oData.data.bssrdUdate || "");
			$(".bssrdPaymentCount").text(oData.data.bssrdPaymentCount || "");
			$(".bssrdBackNow").text(oData.data.bssrdBackNow || "");
			$(".bssrdIntegral").text(oData.data.bssrdIntegral || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
