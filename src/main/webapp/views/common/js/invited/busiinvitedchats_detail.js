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
	var str = 'bsicsUuid='+encodeURIComponent(id);
	getOData(str,"busiInvitedChats/views",{fn:function(oData){
		if(oData.code == 1) {
			var bsicsStatusCH = '禁用';
			if(oData.data.bsicsStatus == 1) {
				bsicsStatusCH = '启用';
			}
			$(".bsicsPeriods").text(oData.data.bsicsPeriods || "");
			$(".bsicsStatus").text(bsicsStatusCH);
			$(".bsicsGoldMatters").text(oData.data.bsicsGoldMatters || "");
			$(".bsicsGoldNumber").text(oData.data.bsicsGoldNumber || "");
			$(".bsicsGoldReward").text(oData.data.bsicsGoldReward || "");
			$(".bsicsGoldGainer").text(oData.data.bsicsGoldGainerName || "");
			$(".bsicsSilverMatters").text(oData.data.bsicsSilverMatters || "");
			$(".bsicsSilverNumber").text(oData.data.bsicsSilverNumber || "");
			$(".bsicsSilverReward").text(oData.data.bsicsSilverReward || "");
			$(".bsicsSilverGainer").text(oData.data.bsicsSilverGainerName || "");
			$(".bsicsBronzeMatters").text(oData.data.bsicsBronzeMatters || "");
			$(".bsicsBronzeNumber").text(oData.data.bsicsBronzeNumber || "");
			$(".bsicsBronzeReward").text(oData.data.bsicsBronzeReward || "");
			$(".bsicsBronzeGainer").text(oData.data.bsicsBronzeGainerName || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
