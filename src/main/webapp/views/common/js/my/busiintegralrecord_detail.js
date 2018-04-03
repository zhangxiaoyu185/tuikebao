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
	var str = 'bsirdUuid='+encodeURIComponent(id);
	getOData(str,"busiIntegralRecord/views",{fn:function(oData){
		if(oData.code == 1) {
			var bsirdTypeName = "";
			if(oData.data.bsirdType==1) {bsirdTypeName="获得";}
			if(oData.data.bsirdType==2) {bsirdTypeName="消耗";}
			$(".bsirdUser").text(oData.data.bsirdUserName || "");
			$(".bsirdType").text(bsirdTypeName);
			$(".bsirdQuota").text(oData.data.bsirdQuota || "");
			$(".bsirdBill").text(oData.data.bsirdBill || "");
			$(".bsirdCdate").text(oData.data.bsirdCdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
