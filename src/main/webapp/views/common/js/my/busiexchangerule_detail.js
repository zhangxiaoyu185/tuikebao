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
	var str = 'bseeeUuid='+encodeURIComponent(id);
	getOData(str,"busiExchangeRule/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bseeeBill").text(oData.data.bseeeBill || "");
			$(".bseeeIntegral").text(oData.data.bseeeIntegral || "");
			$(".bseeeOrd").text(oData.data.bseeeOrd || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
