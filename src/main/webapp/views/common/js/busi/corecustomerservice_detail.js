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
	var str = 'crcseUuid='+encodeURIComponent(id);
	getOData(str,"coreCustomerService/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crcseTel").text(oData.data.crcseTel || "");
			$(".crcseQq").text(oData.data.crcseQq || "");
			$(".crcseCdate").text(oData.data.crcseCdate || "");
			$(".crcseUdate").text(oData.data.crcseUdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
