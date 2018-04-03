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
	var str = 'crgdeUuid='+encodeURIComponent(id);
	getOData(str,"coreGrade/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crgdeName").text(oData.data.crgdeName || "");
			$(".crgdeOrd").text(oData.data.crgdeOrd || "");
			$(".crgdeIntegral").text(oData.data.crgdeIntegral || "");
			$(".crgdeDayShares").text(oData.data.crgdeDayShares || "");
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.crgdeIcon,function(realWidth,realHeight){
				var width = 88;
				var height = 30;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".crgdeIcon_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".crgdeIcon_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".crgdeIcon_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.crgdeIcon);
			});
		} else {
			alert(data.errMsg);
		}
	}});
}
