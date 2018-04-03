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
	var str = 'crscyUuid='+encodeURIComponent(id);
	getOData(str,"coreShareCopy/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crscyCopy").text(oData.data.crscyCopy || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
