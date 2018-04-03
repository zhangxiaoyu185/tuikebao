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
	var str = 'bshstUuid='+encodeURIComponent(id);
	getOData(str,"busiHotSpot/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bshstName").text(oData.data.bshstName || "");
			$(".bshstCdate").text(oData.data.bshstCdate || "");
			$(".bshstUdate").text(oData.data.bshstUdate || "");
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.bshstIcon,function(realWidth,realHeight){
				var width = 88;
				var height = 30;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".bshstIcon_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".bshstIcon_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".bshstIcon_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bshstIcon);
			});
		} else {
			alert(data.errMsg);
		}
	}});
}
