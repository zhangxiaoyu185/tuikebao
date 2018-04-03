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
	var str = 'bscrdUuid='+encodeURIComponent(id);
	getOData(str,"busiCashRecord/views",{fn:function(oData){
		if(oData.code == 1) {
			var accountType="银行账户";
			if(oData.data.bscrdAccountType==2){accountType="支付宝";}
			var statusName="申请提现";
			if(oData.data.bscrdStatus==2){statusName="提现成功";}
			if(oData.data.bscrdStatus==3){statusName="提现失败";}
			$(".bscrdAmount").text(oData.data.bscrdAmount || "");
			$(".bscrdExtracted").text(oData.data.bscrdExtracted || "");
			$(".bscrdStatus").text(statusName || "");
			$(".bscrdCdate").text(oData.data.bscrdCdate || "");
			$(".bscrdUdate").text(oData.data.bscrdUdate || "");
			$(".bscrdMobile").text(oData.data.bscrdMobile || "");
			$(".bscrdAccountType").text(accountType || "");
			$(".bscrdAccountName").text(oData.data.bscrdAccountName || "");
			$(".bscrdAccountNo").text(oData.data.bscrdAccountNo || "");
			$(".bscrdBankName").text(oData.data.bscrdBankName || "");
			$(".bscrdBankSite").text(oData.data.bscrdBankSite || "");
			$(".bscrdAlipayNo").text(oData.data.bscrdAlipayNo || "");
			$(".bscrdAlipayName").text(oData.data.bscrdAlipayName || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
