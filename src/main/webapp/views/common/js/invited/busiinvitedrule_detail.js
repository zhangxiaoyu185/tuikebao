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
	getOData(str,"busiInvitedRule/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsireCount").text(oData.data.bsireCount || "");
			$(".bsireIntegral").text(oData.data.bsireIntegral || "");
			$(".bsireOrd").text(oData.data.bsireOrd || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
