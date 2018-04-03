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
	var str = 'bssreUuid='+encodeURIComponent(id);
	getOData(str,"busiSignRule/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bssreCount").text(oData.data.bssreCount || "");
			$(".bssreIntegral").text(oData.data.bssreIntegral || "");
			$(".bssreOrd").text(oData.data.bssreOrd || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
