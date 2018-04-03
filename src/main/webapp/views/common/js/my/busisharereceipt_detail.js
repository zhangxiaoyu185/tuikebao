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
	var str = 'bssrtUuid='+encodeURIComponent(id);
	getOData(str,"busiShareReceipt/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bssrtUser").text(oData.data.bssrtUserName || "");
			$(".bssrtGrade").text(oData.data.bssrtGradeName || "");
			$(".bssrtProduct").text(oData.data.bssrtProduct || "");
			$("#bssrtSharePic").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bssrtSharePic);
			$("#bssrtStorePic").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bssrtStorePic);
			$(".bssrtProductName").text(oData.data.bssrtProductName || "");
			$(".bssrtBuyUser").text(oData.data.bssrtBuyUserName || "");
			$(".bssrtOrderNo").text(oData.data.bssrtOrderNo || "");
			$(".bssrtCdate").text(oData.data.bssrtCdate || "");
			$(".bssrtReceiptDate").text(oData.data.bssrtReceiptDate || "");
			$(".bssrtFinishDate").text(oData.data.bssrtFinishDate || "");
			$(".bssrtBuyCount").text(oData.data.bssrtBuyCount || "");
			$(".bssrtBuyFee").text(oData.data.bssrtBuyFee || "");
			$(".bssrtBackNow").text(oData.data.bssrtBackNow || "");
			
			var statusName = "";
			if(oData.data.bssrtStatus==1) {statusName="客户下单";}
			if(oData.data.bssrtStatus==2) {statusName="确认收货";}
			if(oData.data.bssrtStatus==3) {statusName="交易完成";}
			$(".bssrtStatus").text(statusName);
		} else {
			alert(data.errMsg);
		}
	}});
}
