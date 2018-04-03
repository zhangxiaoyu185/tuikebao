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
	var str = 'bsprdUuid='+encodeURIComponent(id);
	getOData(str,"busiProfitRecord/views",{fn:function(oData){
		if(oData.code == 1) {
			var bsprdTypeName = "";
			if(oData.data.bsprdType==1) {bsprdTypeName="提现";}
			if(oData.data.bsprdType==2) {bsprdTypeName="返现";}
			$(".bsprdUser").text(oData.data.bsprdUser || "");
			$(".bsprdType").text(bsprdTypeName);
			$(".bsprdQuota").text(oData.data.bsprdQuota || "");
			$(".bsprdBill").text(oData.data.bsprdBill || "");
			$(".bsprdCdate").text(oData.data.bsprdCdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
