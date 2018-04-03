// JavaScript Document
$(function () {
	getInfo(getQueryString("id"));
	//提交
	$(".submit").on("click",function(){
		checkModify();
	});
});

//获取详情
function getInfo(id){
	var str = 'bsorrUuid='+encodeURIComponent(id);
	getOData(str,"busiOrder/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsorrUuid").text(oData.data.bsorrUuid || "");
			$(".bsorrUser").text(oData.data.bsorrUserName || "");
			$(".bsorrProductValue").text(oData.data.bsorrProductValue || 0);
			$(".bsorrExpressValue").text(oData.data.bsorrExpressValue || 0);
			$(".bsorrOrderValue").text(oData.data.bsorrOrderValue || 0);
			$(".bsorrActualPay").text(oData.data.bsorrActualPay || 0);
			$(".bsorrTotalValue").text(oData.data.bsorrTotalValue || 0);
			$(".bsorrRefund").text(oData.data.bsorrRefund || 0);
			$(".bsorrTradeNo").text(oData.data.bsorrTradeNo || "");
			$(".bsorrTotalQuantity").text(oData.data.bsorrTotalQuantity || 0);
			$(".bsorrTotalWeight").text(oData.data.bsorrTotalWeight || 0);
			$(".bsorrPayTime").text(oData.data.bsorrPayTime || "");
			var bsorrStatusCH = '支付中';
			if(2==oData.data.bsorrStatus) {
				bsorrStatusCH = '支付成功';
			}
			if(3==oData.data.bsorrStatus) {
				bsorrStatusCH = '支付失败';
			}
			$(".bsorrStatus").text(bsorrStatusCH || "");
			$(".bsorrCdate").text(oData.data.bsorrCdate || "");
			$(".bsorrUdate").text(oData.data.bsorrUdate || "");
			$(".bsorrName").text(oData.data.bsorrName || "");
			$(".bsorrIdCard").text(oData.data.bsorrIdCard || "");
			$(".bsorrMobile").text(oData.data.bsorrMobile || "");
			$(".bsorrZipCode").text(oData.data.bsorrZipCode || "");
			$(".bsorrProvince").text(oData.data.bsorrProvinceName || "");
			$(".bsorrCity").text(oData.data.bsorrCityName || "");
			$(".bsorrCounty").text(oData.data.bsorrCountyName || "");
			$(".bsorrAddress").text(oData.data.bsorrAddress || "");
			
			$(".bsorrAdjustExpress").val(oData.data.bsorrAdjustExpress || "");
			$(".bsorrAdjustProduct").val(oData.data.bsorrAdjustProduct || "");
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".bsorrAdjustExpress").val()) == ""){
		alert("客服调整运费不能为空，请填写完再提交！");
		$(".bsorrAdjustExpress").focus();
		return false;
	}
	if($.trim($(".bsorrAdjustProduct").val()) == ""){
		alert("客服调整总价不能为空，请填写完再提交！");
		$(".bsorrAdjustProduct").focus();
		return false;
	}

	var r=confirm("是否确认修改？");
	if (r==true){
		var msgObject = parent.layer.msg('处理中，请等待……', {
			icon: 16,
			shade: 0.4,
			time: waitImgTime //（如果不配置，默认是3秒）
		}, function(index){
			//do something
			parent.layer.close(index);
		});
		Modify(msgObject);
	}
}
//提交
function Modify(msgObject){
	var bsorrUuid = getQueryString("id");	
	var bsorrAdjustExpress = $(".bsorrAdjustExpress").val();
	var bsorrAdjustProduct = $(".bsorrAdjustProduct").val();
	var str = 'bsorrUuid='+encodeURIComponent(bsorrUuid)+'&bsorrAdjustExpress='+encodeURIComponent(bsorrAdjustExpress)+'&bsorrAdjustProduct='+encodeURIComponent(bsorrAdjustProduct);
	getOData(str,"busiOrder/customer/adjust",{
		fn:function(oData){
			window.parent.refreshList();
			alert("客服调整价格成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}