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
	var str = 'bststUuid='+encodeURIComponent(id);
	getOData(str,"busiTagSet/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bststName").text(oData.data.bststName || "");
			$(".bststDesc").text(oData.data.bststDesc || "");
			$(".bststIcon").text(oData.data.bststIcon || "");
			$(".bststCdate").text(oData.data.bststCdate || "");
			$(".bststUdate").text(oData.data.bststUdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
