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
	var str = 'bsaetUuid='+encodeURIComponent(id);
	getOData(str,"busiAdvert/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsaetLink").text(oData.data.bsaetLink || "");
			$(".bsaetOrd").text(oData.data.bsaetOrd || "");
			$(".bsaetStatus").text(oData.data.bsaetStatus==0?"禁用":"启用");
			$(".bsaetDesc").text(oData.data.bsaetDesc || "");
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.bsaetPic,function(realWidth,realHeight){
				var width = 750;
				var height = 200;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".bsaetPic_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".bsaetPic_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".bsaetPic_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bsaetPic);
			});
		} else {
			alert(data.errMsg);
		}
	}});
}
