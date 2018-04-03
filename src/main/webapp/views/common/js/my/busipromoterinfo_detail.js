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
	var str = 'bspioUuid='+encodeURIComponent(id);
	getOData(str,"busiPromoterInfo/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bspioUser").text(oData.data.bspioUserName || "");
			$(".bspioAccountName").text(oData.data.bspioAccountName || "");
			$(".bspioAccountNo").text(oData.data.bspioAccountNo || "");
			$(".bspioBankName").text(oData.data.bspioBankName || "");
			$(".bspioBankSite").text(oData.data.bspioBankSite || "");
			$(".bspioAlipayNo").text(oData.data.bspioAlipayNo || "");
			$(".bspioAlipayName").text(oData.data.bspioAlipayName || "");
			$(".bspioUdate").text(oData.data.bspioUdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
