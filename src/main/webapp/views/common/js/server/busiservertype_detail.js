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
	var str = 'bssteUuid='+encodeURIComponent(id);
	getOData(str,"busiServerType/views",{fn:function(oData){
		if(oData.code == 1) {
			var bssteShowCH = "都显示";
			if(oData.data.bssteShow == 1) {
				bssteShowCH = "分享端";
			}
			if(oData.data.bssteShow == 2) {
				bssteShowCH = "商城端";
			}
			$(".bssteName").text(oData.data.bssteName || "");
			$(".bssteIslist").text(oData.data.bssteIslist==0?"无":"有");
			$(".bssteShow").text(bssteShowCH || "");
			$(".bssteDetails").text(oData.data.bssteDetails || "");
			$(".bssteCdate").text(oData.data.bssteCdate || "");
			$(".bssteUdate").text(oData.data.bssteUdate || "");
			$(".bssteStatus").text(oData.data.bssteStatus==0?"禁用":"启用");
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.bssteIcon,function(realWidth,realHeight){
				var width = 52;
				var height = 52;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".bssteIcon_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".bssteIcon_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".bssteIcon_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bssteIcon);
			});
		} else {
			alert(data.errMsg);
		}
	}});
}
