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
	var str = 'crtmeUuid='+encodeURIComponent(id);
	getOData(str,"coreTemplateMessage/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crtmeUser").text(oData.data.crtmeUser || "");
			$(".crtmeMobile").text(oData.data.crtmeMobile || "");
			$(".crtmeContent").text(oData.data.crtmeContent || "");
			$(".crtmeTime").text(oData.data.crtmeTime || "");
			$(".crtmeStatus").text(oData.data.crtmeStatus || "");
			$(".crtmeType").text(oData.data.crtmeType || "");
			$(".crtmeSign").text(oData.data.crtmeSign || "");
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
