// JavaScript Document
$(function () {
	initAdd();
	//提交
	$(".submit").on("click",function(){
		checkAdd();
	});
});
//初始化
function initAdd(){
	$(".bsprtName").val("");
	$(".bsprtCategory").val("");
	$(".bsprtDesc").val("");
	$(".bsprtTel").val("");
	$(".bsprtView").val("");
	$(".bsprtSalePrice").val("");
	$(".bsprtOrignalPrice").val("");
	$(".bsprtSaleCount").val("");
	$(".bsprtAddress").val("");
	$(".bsprtAvailStock").val("");
	$(".bsprtFrozenStock").val("");
	$(".bsprtStockValve").val("");
	$(".bsprtIsVirtual").val("");
	$(".bsprtVisitCount").val("");
	$(".bsprtSaleStatus").val("");
	$(".bsprtExpress").val("");
	$(".bsprtSharePic").val("");
	$(".bsprtStorePic").val("");
	$(".bsprtImageUrl1").val("");
	$(".bsprtImageUrl2").val("");
	$(".bsprtImageUrl3").val("");
	$(".bsprtImageUrl4").val("");
	$(".bsprtImageUrl5").val("");
	$(".bsprtAttrJson").val("");
	$(".bsprtValueJson").val("");
	$(".bsprtProductTag").val("");
	$(".bsprtRuleParam").val("");
	$(".bsprtGradeShare").val("");
	$(".bsprtCdate").val("");
	$(".bsprtUdate").val("");
	/*CKEDITOR.replace( 'crcseContent' , { height: '240px', 
		width: '480px',
		toolbar: [[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo', 'Bold',
						'Italic',"Format","FontSize","TextColor" ,"CodeSnippet","Table"]],
		extraPlugins: 'codesnippet',
		uiColor: '#f3f3f3'
	} );*/
}
//检查提交
function checkAdd(){
	if($.trim($(".bsprtName").val()) == ""){
		alert("商品名称不能为空，请填写完再提交！");
		$(".bsprtName").focus();
		return false;
	}
	if($.trim($(".bsprtCategory").val()) == ""){
		alert("分类不能为空，请填写完再提交！");
		$(".bsprtCategory").focus();
		return false;
	}
	if($.trim($(".bsprtDesc").val()) == ""){
		alert("商品描述不能为空，请填写完再提交！");
		$(".bsprtDesc").focus();
		return false;
	}
	if($.trim($(".bsprtTel").val()) == ""){
		alert("客服电话不能为空，请填写完再提交！");
		$(".bsprtTel").focus();
		return false;
	}
	if($.trim($(".bsprtView").val()) == ""){
		alert("详情不能为空，请填写完再提交！");
		$(".bsprtView").focus();
		return false;
	}
	if($.trim($(".bsprtSalePrice").val()) == ""){
		alert("售价范围不能为空，请填写完再提交！");
		$(".bsprtSalePrice").focus();
		return false;
	}
	if($.trim($(".bsprtOrignalPrice").val()) == ""){
		alert("原价范围不能为空，请填写完再提交！");
		$(".bsprtOrignalPrice").focus();
		return false;
	}
	if($.trim($(".bsprtSaleCount").val()) == ""){
		alert("销售数量不能为空，请填写完再提交！");
		$(".bsprtSaleCount").focus();
		return false;
	}
	if($.trim($(".bsprtAddress").val()) == ""){
		alert("地区不能为空，请填写完再提交！");
		$(".bsprtAddress").focus();
		return false;
	}
	if($.trim($(".bsprtAvailStock").val()) == ""){
		alert("总可用库存(暂时不用)不能为空，请填写完再提交！");
		$(".bsprtAvailStock").focus();
		return false;
	}
	if($.trim($(".bsprtFrozenStock").val()) == ""){
		alert("总冻结库存(暂时不用)不能为空，请填写完再提交！");
		$(".bsprtFrozenStock").focus();
		return false;
	}
	if($.trim($(".bsprtStockValve").val()) == ""){
		alert("库存警戒值(暂时不用)不能为空，请填写完再提交！");
		$(".bsprtStockValve").focus();
		return false;
	}
	if($.trim($(".bsprtIsVirtual").val()) == ""){
		alert("是否是虚拟商品:1是0否不能为空，请填写完再提交！");
		$(".bsprtIsVirtual").focus();
		return false;
	}
	if($.trim($(".bsprtVisitCount").val()) == ""){
		alert("页面访问数量不能为空，请填写完再提交！");
		$(".bsprtVisitCount").focus();
		return false;
	}
	if($.trim($(".bsprtSaleStatus").val()) == ""){
		alert("状态:1销售中2已下架不能为空，请填写完再提交！");
		$(".bsprtSaleStatus").focus();
		return false;
	}
	if($.trim($(".bsprtExpress").val()) == ""){
		alert("快递费用不能为空，请填写完再提交！");
		$(".bsprtExpress").focus();
		return false;
	}
	if($.trim($(".bsprtSharePic").val()) == ""){
		alert("推客封面图不能为空，请填写完再提交！");
		$(".bsprtSharePic").focus();
		return false;
	}
	if($.trim($(".bsprtStorePic").val()) == ""){
		alert("商城封面图不能为空，请填写完再提交！");
		$(".bsprtStorePic").focus();
		return false;
	}
	if($.trim($(".bsprtImageUrl1").val()) == ""){
		alert("主图1不能为空，请填写完再提交！");
		$(".bsprtImageUrl1").focus();
		return false;
	}
	if($.trim($(".bsprtImageUrl2").val()) == ""){
		alert("主图2不能为空，请填写完再提交！");
		$(".bsprtImageUrl2").focus();
		return false;
	}
	if($.trim($(".bsprtImageUrl3").val()) == ""){
		alert("主图3不能为空，请填写完再提交！");
		$(".bsprtImageUrl3").focus();
		return false;
	}
	if($.trim($(".bsprtImageUrl4").val()) == ""){
		alert("主图4不能为空，请填写完再提交！");
		$(".bsprtImageUrl4").focus();
		return false;
	}
	if($.trim($(".bsprtImageUrl5").val()) == ""){
		alert("主图5不能为空，请填写完再提交！");
		$(".bsprtImageUrl5").focus();
		return false;
	}
	if($.trim($(".bsprtAttrJson").val()) == ""){
		alert("所有属性标识JSON串不能为空，请填写完再提交！");
		$(".bsprtAttrJson").focus();
		return false;
	}
	if($.trim($(".bsprtValueJson").val()) == ""){
		alert("所有属性值标识JSON串不能为空，请填写完再提交！");
		$(".bsprtValueJson").focus();
		return false;
	}
	if($.trim($(".bsprtProductTag").val()) == ""){
		alert("标签JSON串不能为空，请填写完再提交！");
		$(".bsprtProductTag").focus();
		return false;
	}
	if($.trim($(".bsprtRuleParam").val()) == ""){
		alert("规格参数JSON串不能为空，请填写完再提交！");
		$(".bsprtRuleParam").focus();
		return false;
	}
	if($.trim($(".bsprtGradeShare").val()) == ""){
		alert("商品等级分享金额JSON串不能为空，请填写完再提交！");
		$(".bsprtGradeShare").focus();
		return false;
	}
	if($.trim($(".bsprtCdate").val()) == ""){
		alert("创建时间不能为空，请填写完再提交！");
		$(".bsprtCdate").focus();
		return false;
	}
	if($.trim($(".bsprtUdate").val()) == ""){
		alert("更新时间不能为空，请填写完再提交！");
		$(".bsprtUdate").focus();
		return false;
	}
	/*if($.trim(CKEDITOR.instances.crcseContent.getData()) == ""){
		alert("内容不能为空，请填写完再提交！");
		return false;
	}*/

	var r=confirm("是否确认增加？");
	if (r==true){
		var msgObject = parent.layer.msg('处理中，请等待……', {
			icon: 16,
			shade: 0.4,
			time: waitImgTime //（如果不配置，默认是3秒）
		}, function(index){
			//do something
			parent.layer.close(index);
		});
		Add(msgObject);
	}
}
//提交
function Add(msgObject){
	var bsprtName = $(".bsprtName").val();
	var bsprtCategory = $(".bsprtCategory").val();
	var bsprtDesc = $(".bsprtDesc").val();
	var bsprtTel = $(".bsprtTel").val();
	var bsprtView = $(".bsprtView").val();
	var bsprtSalePrice = $(".bsprtSalePrice").val();
	var bsprtOrignalPrice = $(".bsprtOrignalPrice").val();
	var bsprtSaleCount = $(".bsprtSaleCount").val();
	var bsprtAddress = $(".bsprtAddress").val();
	var bsprtAvailStock = $(".bsprtAvailStock").val();
	var bsprtFrozenStock = $(".bsprtFrozenStock").val();
	var bsprtStockValve = $(".bsprtStockValve").val();
	var bsprtIsVirtual = $(".bsprtIsVirtual").val();
	var bsprtVisitCount = $(".bsprtVisitCount").val();
	var bsprtSaleStatus = $(".bsprtSaleStatus").val();
	var bsprtExpress = $(".bsprtExpress").val();
	var bsprtSharePic = $(".bsprtSharePic").val();
	var bsprtStorePic = $(".bsprtStorePic").val();
	var bsprtImageUrl1 = $(".bsprtImageUrl1").val();
	var bsprtImageUrl2 = $(".bsprtImageUrl2").val();
	var bsprtImageUrl3 = $(".bsprtImageUrl3").val();
	var bsprtImageUrl4 = $(".bsprtImageUrl4").val();
	var bsprtImageUrl5 = $(".bsprtImageUrl5").val();
	var bsprtAttrJson = $(".bsprtAttrJson").val();
	var bsprtValueJson = $(".bsprtValueJson").val();
	var bsprtProductTag = $(".bsprtProductTag").val();
	var bsprtRuleParam = $(".bsprtRuleParam").val();
	var bsprtGradeShare = $(".bsprtGradeShare").val();
	var bsprtCdate = $(".bsprtCdate").val();
	var bsprtUdate = $(".bsprtUdate").val();
	var str = 'bsprtName='+encodeURIComponent(bsprtName)+'&bsprtCategory='+encodeURIComponent(bsprtCategory)+'&bsprtDesc='+encodeURIComponent(bsprtDesc)+'&bsprtTel='+encodeURIComponent(bsprtTel)+'&bsprtView='+encodeURIComponent(bsprtView)+'&bsprtSalePrice='+encodeURIComponent(bsprtSalePrice)+'&bsprtOrignalPrice='+encodeURIComponent(bsprtOrignalPrice)+'&bsprtSaleCount='+encodeURIComponent(bsprtSaleCount)+'&bsprtAddress='+encodeURIComponent(bsprtAddress)+'&bsprtAvailStock='+encodeURIComponent(bsprtAvailStock)+'&bsprtFrozenStock='+encodeURIComponent(bsprtFrozenStock)+'&bsprtStockValve='+encodeURIComponent(bsprtStockValve)+'&bsprtIsVirtual='+encodeURIComponent(bsprtIsVirtual)+'&bsprtVisitCount='+encodeURIComponent(bsprtVisitCount)+'&bsprtSaleStatus='+encodeURIComponent(bsprtSaleStatus)+'&bsprtExpress='+encodeURIComponent(bsprtExpress)+'&bsprtSharePic='+encodeURIComponent(bsprtSharePic)+'&bsprtStorePic='+encodeURIComponent(bsprtStorePic)+'&bsprtImageUrl1='+encodeURIComponent(bsprtImageUrl1)+'&bsprtImageUrl2='+encodeURIComponent(bsprtImageUrl2)+'&bsprtImageUrl3='+encodeURIComponent(bsprtImageUrl3)+'&bsprtImageUrl4='+encodeURIComponent(bsprtImageUrl4)+'&bsprtImageUrl5='+encodeURIComponent(bsprtImageUrl5)+'&bsprtAttrJson='+encodeURIComponent(bsprtAttrJson)+'&bsprtValueJson='+encodeURIComponent(bsprtValueJson)+'&bsprtProductTag='+encodeURIComponent(bsprtProductTag)+'&bsprtRuleParam='+encodeURIComponent(bsprtRuleParam)+'&bsprtGradeShare='+encodeURIComponent(bsprtGradeShare)+'&bsprtCdate='+encodeURIComponent(bsprtCdate)+'&bsprtUdate='+encodeURIComponent(bsprtUdate);
	getOData(str,"busiProduct/add/busiProduct",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}