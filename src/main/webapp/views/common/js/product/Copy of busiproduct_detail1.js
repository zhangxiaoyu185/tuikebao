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
	var str = 'bsprtUuid='+encodeURIComponent(id);
	getOData(str,"busiProduct/views/back",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsprtName").text(oData.data.bsprtName || "");
			$(".bsprtCategory").text(oData.data.bsprtCategoryName || "");
			$(".bsprtDesc").text(oData.data.bsprtDesc || "");
			$(".bsprtTel").text(oData.data.bsprtTel || "");
			$(".bsprtView").text(oData.data.bsprtView || "");
			$(".bsprtSalePrice").text(oData.data.bsprtSalePrice || "");
			$(".bsprtOrignalPrice").text(oData.data.bsprtOrignalPrice || "");
			$(".bsprtSaleCount").text(oData.data.bsprtSaleCount || 0);
			$(".bsprtAddress").text(oData.data.bsprtAddress || "");
			$(".bsprtIsVirtual").text(oData.data.bsprtIsVirtual==1?'是':'否');
			$(".bsprtVisitCount").text(oData.data.bsprtVisitCount || 0);
			var bsprtSaleStatusCH = "销售中";
			if(2 == oData.data.bsprtSaleStatus) {
				bsprtSaleStatusCH = "已下架";
			}
			if(3 == oData.data.bsprtSaleStatus) {
				bsprtSaleStatusCH = "已删除";
			}
			$(".bsprtSaleStatus").text(bsprtSaleStatusCH || "");
			$(".bsprtExpress").text(oData.data.bsprtExpress || 0);
			
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.bsprtSharePic,function(realWidth,realHeight){
				var width = 200;
				var height = 200;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".bsprtSharePic_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".bsprtSharePic_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".bsprtSharePic_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bsprtSharePic);
			});
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.bsprtStorePic,function(realWidth,realHeight){
				var width = 750;
				var height = 480;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".bsprtStorePic_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".bsprtStorePic_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".bsprtStorePic_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bsprtStorePic);
			});
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.bsprtImageUrl1,function(realWidth,realHeight){
				var width = 750;
				var height = 468;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".bsprtImageUrl1_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".bsprtImageUrl1_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".bsprtImageUrl1_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bsprtImageUrl1);
			});
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.bsprtImageUrl2,function(realWidth,realHeight){
				var width = 750;
				var height = 468;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".bsprtImageUrl2_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".bsprtImageUrl2_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".bsprtImageUrl2_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bsprtImageUrl2);
			});
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.bsprtImageUrl3,function(realWidth,realHeight){
				var width = 750;
				var height = 468;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".bsprtImageUrl3_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".bsprtImageUrl3_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".bsprtImageUrl3_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bsprtImageUrl3);
			});
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.bsprtImageUrl4,function(realWidth,realHeight){
				var width = 750;
				var height = 468;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".bsprtImageUrl4_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".bsprtImageUrl4_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".bsprtImageUrl4_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bsprtImageUrl4);
			});
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.bsprtImageUrl5,function(realWidth,realHeight){
				var width = 750;
				var height = 468;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".bsprtImageUrl5_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".bsprtImageUrl5_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".bsprtImageUrl5_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.bsprtImageUrl5);
			});
			$(".bsprtAttrJson").text(oData.data.bsprtAttrJson || "");
			$(".bsprtValueJson").text(oData.data.bsprtValueJson || "");
			$(".bsprtProductTag").text(oData.data.bsprtProductTag || "");
			$(".bsprtRuleParam").text(oData.data.bsprtRuleParam || "");
			$(".bsprtGradeShare").text(oData.data.bsprtGradeShare || "");
			$(".bsprtCdate").text(oData.data.bsprtCdate || "");
			$(".bsprtUdate").text(oData.data.bsprtUdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
