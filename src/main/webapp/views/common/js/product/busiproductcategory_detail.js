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
	var str = 'bspcyUuid='+encodeURIComponent(id);
	getOData(str,"busiProductCategory/views",{fn:function(oData){
		console.log(oData);
		if(oData.code == 1) {
			$(".bspcyName").text(oData.data.bspcyName || "");	
			if(oData.data.bspcyIcon&&  oData.data.bspcyIcon!="undefined") {
				var imgPath = urlfile+"/coreAttachment/image/get/"+oData.data.bspcyIcon;
				var imgHtml = '<img class="bspcyIcon" alt="" src="'+imgPath+'">';
				$("#bspcyIcon").html(imgHtml);       
			}       
			$(".bspcyOrd").text(oData.data.bspcyOrd || "");
			$(".bspcyStatus").text(oData.data.bspcyStatus==1?"启用":"禁用");
			$(".bspcyTop").text(oData.data.bspcyTopName || "");
			$(".bspcyChildNode").text(oData.data.bspcyChildNode==1?"有":"无");
			$(".bspcyCdate").text(oData.data.bspcyCdate || "");
			$(".bspcyUdate").text(oData.data.bspcyUdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
