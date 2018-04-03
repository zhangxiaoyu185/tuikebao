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
	var str = 'bseblUuid='+encodeURIComponent(id);
	getOData(str,"busiExchangeBill/views",{fn:function(oData){
		if(oData.code == 1) {
			var statusName = "";
			if(oData.data.bseblStatus==1) {statusName="已充值";}
			if(oData.data.bseblStatus==0) {statusName="待充值";}
			
			$(".bseblUser").text(oData.data.bseblUserName || "");
			$(".bseblIntegral").text(oData.data.bseblIntegral || "");
			$(".bseblBill").text(oData.data.bseblBill || "");
			$(".bseblMobile").text(oData.data.bseblMobile || "");
			$(".bseblStatus").text(statusName);
			$(".bseblCdate").text(oData.data.bseblCdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
