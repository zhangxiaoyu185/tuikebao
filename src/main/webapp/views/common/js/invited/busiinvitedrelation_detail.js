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
	var str = 'bsirnUuid='+encodeURIComponent(id);
	getOData(str,"busiInvitedRelation/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsirnInvited").text(oData.data.bsirnInvitedName || "");
			$(".bsirnBeInvited").text(oData.data.bsirnBeInvitedName || "");
			$(".bsirnCode").text(oData.data.bsirnCode || "");
			$(".bsirnIdate").text(oData.data.bsirnIdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
