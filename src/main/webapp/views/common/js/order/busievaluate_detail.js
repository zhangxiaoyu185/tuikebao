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
	var str = 'bseveOrderItem='+encodeURIComponent(id);
	getOData(str,"busiEvaluate/find/view/by/order",{fn:function(oData){
		if(oData.code == 1) {
			$(".bseveOrderItem").text(oData.data.bseveOrderItem || "");
			$(".bseveProduct").text(oData.data.bseveProduct || "");
			$(".bseveAttrName").text(oData.data.bseveAttrName || "");
			$(".bseveUser").text(oData.data.bseveUser || "");
			$(".bseveName").text(oData.data.bseveName || "");
			$(".bseveHead").text(oData.data.bseveHead || "");
			$(".bseveContent").text(oData.data.bseveContent || "");
			$(".bseveGrade").text(oData.data.bseveGrade || "");
			$(".bseveStatus").text(oData.data.bseveStatus || "");
			$(".bsevePic1").text(oData.data.bsevePic1 || "");
			$(".bsevePic2").text(oData.data.bsevePic2 || "");
			$(".bsevePic3").text(oData.data.bsevePic3 || "");
			$(".bsevePic4").text(oData.data.bsevePic4 || "");
			$(".bsevePic5").text(oData.data.bsevePic5 || "");
			$(".bseveCdate").text(oData.data.bseveCdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
