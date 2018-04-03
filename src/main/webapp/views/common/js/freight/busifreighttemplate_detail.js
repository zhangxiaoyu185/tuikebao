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
	var str = 'bsfteUuid='+encodeURIComponent(id);
	getOData(str,"busiFreightTemplate/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsfteName").text(oData.data.bsfteName || "");
			$(".bsfteInitialWeight").text(oData.data.bsfteInitialWeight || "");
			$(".bsfteInitialPrice").text(oData.data.bsfteInitialPrice || "");
			$(".bsfteWeight").text(oData.data.bsfteWeight || "");
			$(".bsftePrice").text(oData.data.bsftePrice || "");
			$(".bsfteCdate").text(oData.data.bsfteCdate || "");
			$(".bsfteUdate").text(oData.data.bsfteUdate || "");
			/*var imgUrl="/"+oData.data.list[0].cratmDir+"/"+oData.data.list[0].cratmFileName;
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/thumb/"+oData.data.list[0].cratmUuid,function(realWidth,realHeight){
				var width = 0;
				var height = 200;
				//如果真实的宽度大于浏览器的宽度就按照200显示
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".preimg").attr("src",urlfile+"/coreAttachment/image/get/thumb/"+oData.data.list[0].cratmUuid);
				$(".preimg").attr("data-filename",imgUrl);
			});*/
		} else {
			alert(data.errMsg);
		}
	}});
}
