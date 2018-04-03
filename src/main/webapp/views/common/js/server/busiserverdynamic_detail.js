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
	var str = 'bssdcUuid='+encodeURIComponent(id);
	getOData(str,"busiServerDynamic/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bssdcType").text(oData.data.bssdcTypeName || "");
			$(".bssdcTitle").text(oData.data.bssdcTitle || "");
			$(".bssdcContent").text(oData.data.bssdcContent || "");
			$(".bssdcDesc").text(oData.data.bssdcDesc || "");
			$(".bssdcCount").text(oData.data.bssdcCount || 0);
			$(".bssdcPdate").text(oData.data.bssdcPdate || "");
			$(".bssdcCdate").text(oData.data.bssdcCdate || "");
			$(".bssdcUdate").text(oData.data.bssdcUdate || "");
			$(".bssdcStatus").text(oData.data.bssdcStatus==0?"草稿":"已发布");
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.bssdcPic,function(realWidth,realHeight){
				var width = 702;
				var height = 320;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".bssdcPic_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".bssdcPic_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".bssdcPic_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bssdcPic);
			});
		} else {
			alert(data.errMsg);
		}
	}});
}
