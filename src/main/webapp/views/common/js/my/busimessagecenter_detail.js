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
	var str = 'bsmecUuid='+encodeURIComponent(id);
	getOData(str,"busiMessageCenter/views",{fn:function(oData){
		if(oData.code == 1) {
			var statusName = "";
			if(oData.data.bsmecStatus==1) {statusName="未读";}
			if(oData.data.bsmecStatus==2) {statusName="已读";}
			var bsmecTypeName = "";
			if(oData.data.bsmecType==1) {bsmecTypeName="提现";}
			if(oData.data.bsmecType==2) {bsmecTypeName="积分";}
			if(oData.data.bsmecType==3) {bsmecTypeName="等级";}
			if(oData.data.bsmecType==4) {bsmecTypeName="金额";}
			if(oData.data.bsmecType==5) {bsmecTypeName="订单";}
			
			$(".bsmecUser").text(oData.data.bsmecUser || "");
			$(".bsmecCdate").text(oData.data.bsmecCdate || "");
			$(".bsmecStatus").text(statusName || "");
			$(".bsmecType").text(bsmecTypeName || "");
			$(".bsmecContent").text(oData.data.bsmecContent || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
