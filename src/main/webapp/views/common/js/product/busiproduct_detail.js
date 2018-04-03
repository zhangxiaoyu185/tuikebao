// JavaScript Document
$(function () {
	initDetail();
});
//初始化
function initDetail(){
	getInfo(getQueryString("id"));
}


function copyUrl2(id)
{
var Url2=document.getElementById(id);
Url2.select(); // 选择对象
document.execCommand("Copy"); // 执行浏览器复制命令
alert("已复制好，可贴粘。");
}

//获取详情
function getInfo(id){
	var str = 'bsprtUuid='+encodeURIComponent(id);
	getOData(str,"busiProduct/views/back",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsprtName").text(oData.data.bsprtName || "");
			$(".shareLink").text(productShareUrl + oData.data.bsprtUuid  || "");
			$(".storeLink").text(productStoreUrl + oData.data.bsprtUuid || "");
			$(".bsprtCategory").text(oData.data.bsprtCategoryName || "");
			$(".bsprtDesc").text(oData.data.bsprtDesc || "");
			$(".bsprtTel").text(oData.data.bsprtTel || "");
			$(".bsprtView").html(oData.data.bsprtView || "");
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

			//所有属性标识
			var attrData = jQuery.parseJSON(oData.data.bsprtAttrJson);
			var attr = "";
			for( var i =0; i<attrData.length; i++){
				for(var j =0; j<attrData[i].data.length; j++){
					attr = attr + attrData[i].name + "，" + attrData[i].data[j].name + "</br>";
					$(".bsprtAttrJson").html( attr || "");
				}
			}
			
			console.log(oData.data);
			//所有属性值标识
			var valueData = jQuery.parseJSON(oData.data.bsprtValueJson);
			var value = "";
			console.log(valueData);
			for( var key in valueData){
				var uuid = key.substring(8,key.length - 1);
					for( var x =0; x<attrData.length; x++){
						for(var j =0; j<attrData[x].data.length; j++){
							if( attrData[x].data[j].uuid == uuid){
								var name = attrData[x].data[j].name;
							}							
						}
					}
				value = value + name +"："+ "价格   " + valueData[key].price + "," + "库存   "+ valueData[key].store + "</br>";
				$(".bsprtValueJson").html( value || "");
			}

			//标签JSON串
			var productData = jQuery.parseJSON(oData.data.bsprtProductTag);
			var product = "";
			for ( var key in productData){
				product = product + key + "：" + productData[key]+"</br>";
				$(".bsprtProductTag").html(product || "");
			}

			//规格参数
			var ruleData = jQuery.parseJSON(oData.data.bsprtRuleParam);
			var ruleParam = "";
			for( var i = 0; i<ruleData.length; i++){				
				ruleParam = ruleParam + ruleData[i].flag + ruleData[i].val + "</br>";
				$(".bsprtRuleParam").html(ruleParam || "");
			}

			//商品等级分享金额
			var gradeData = jQuery.parseJSON(oData.data.bsprtGradeShare);
			var gradeShare = "";
			var grade = 0;
		    for (var key in gradeData)
		    {
		    	grade ++;
		       gradeShare = gradeShare + "等级"+grade+"：积分收入"+gradeData[key].integral+"积分，佣金收入"+gradeData[key].money +"元"+ "</br>";
		       $(".bsprtGradeShare").html( gradeShare || "");
		    }

			$(".bsprtCdate").text(oData.data.bsprtCdate || "");
			$(".bsprtUdate").text(oData.data.bsprtUdate || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
