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
	var str = 'bssasUuid='+encodeURIComponent(id);
	getOData(str,"busiShippingAddress/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bssasUser").text(oData.data.bssasUser || "");
			$(".bssasName").text(oData.data.bssasName || "");
			$(".bssasIdCard").text(oData.data.bssasIdCard || "");
			$(".bssasMobile").text(oData.data.bssasMobile || "");
			$(".bsarsZipCode").text(oData.data.bsarsZipCode || "");
			$(".bssasProvinceName").text(oData.data.bssasProvinceName || "");
			$(".bssasCityName").text(oData.data.bssasCityName || "");
			$(".bssasCountyName").text(oData.data.bssasCountyName || "");
			$(".bssasAddress").text(oData.data.bssasAddress || "");
			$(".bsarsDefault").text(oData.data.bsarsDefault==1?"默认":"不默认");
		} else {
			alert(data.errMsg);
		}
	}});
}
