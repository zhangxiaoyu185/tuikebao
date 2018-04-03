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
	var str = 'crmecUuid='+encodeURIComponent(id);
	getOData(str,"coreMessageCenter/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crmecContent").text(oData.data.crmecContent || "");
			$(".crmecCdate").text(oData.data.crmecCdate || "");
			var crrorStatus = oData.data.crmecStatus;//:1已处理2未处理3已删除
			var crrorStatusCH="已处理";
			if(2==crrorStatus){
				crrorStatusCH="未处理";
			}else if(3==crrorStatus){
				crrorStatusCH="已删除";
			}
			$(".crmecStatus").text(crrorStatusCH || "");
			$(".crmecType").text(oData.data.crmecType || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
