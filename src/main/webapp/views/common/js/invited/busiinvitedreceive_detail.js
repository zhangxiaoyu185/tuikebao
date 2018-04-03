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
	var str = 'bsireUuid='+encodeURIComponent(id);
	getOData(str,"busiInvitedReceive/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsireActivity").text(oData.data.bsireActivity || "");
			$(".bsirePeriods").text(oData.data.bsirePeriods || "");
			$(".bsireUser").text(oData.data.bsireUserName || "");
			$(".bsireIntegral").text(oData.data.bsireIntegral || "");
			$(".bsireRuleNumber").text(oData.data.bsireRuleNumber || "");
			$(".bsireActivityNumber").text(oData.data.bsireActivityNumber || "");
			$(".bsireCdate").text(oData.data.bsireCdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
