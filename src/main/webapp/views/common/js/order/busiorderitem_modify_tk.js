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
	var str = 'bsoimUuid='+encodeURIComponent(id);
	getOData(str,"busiOrder/item/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsoimRefund").val(oData.data.BSOIM_REFUND || "");
			$(".bsoimCrefundReason").val(oData.data.BSOIM_CREFUND_REASON || "");
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){	
	if($.trim($(".bsoimRefund").val()) == ""){
		alert("退款金额不能为空，请填写完再提交！");
		$(".bsoimRefund").focus();
		return false;
	}
	if($.trim($(".bsoimCrefundReason").val()) == ""){
		alert("客服备注退款原因不能为空，请填写完再提交！");
		$(".bsoimCrefundReason").focus();
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
	var bsoimUuid = getQueryString("id");
	var bsoimRefund = $(".bsoimRefund").val();
	var bsoimCrefundReason = $(".bsoimCrefundReason").val();
	var str = 'bsoimUuid='+encodeURIComponent(bsoimUuid)+'&bsoimRefund='+encodeURIComponent(bsoimRefund)+'&bsoimCrefundReason='+encodeURIComponent(bsoimCrefundReason);
	getOData(str,"busiOrder/item/back/refund",{
		fn:function(oData){
			window.parent.refreshList();
			alert("退款成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}