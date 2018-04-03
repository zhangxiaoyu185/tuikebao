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
	getOData(str,"busiSignRecord/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bssrdUser").text(oData.data.bssrdUserName || "");
			$(".bssrdIntegral").text(oData.data.bssrdIntegral || "");
			$(".bssrdLastDate").text(oData.data.bssrdLastDate || "");
			$(".bssrdSignDays").text(oData.data.bssrdSignDays || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
