// JavaScript Document
$(function () {
	initModify();
	//提交
	$(".submit").on("click",function(){
		checkModify();
	});
});
//初始化
function initModify(){
	$(".bseeeBill").val("");
	$(".bseeeIntegral").val("");
	$(".bseeeOrd").val("");
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'bseeeUuid='+encodeURIComponent(id);
	getOData(str,"busiExchangeRule/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bseeeBill").val(oData.data.bseeeBill || "");
			$(".bseeeIntegral").val(oData.data.bseeeIntegral || "");
			$(".bseeeOrd").val(oData.data.bseeeOrd || "");
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".bseeeBill").val()) == ""){
		alert("充值话费不能为空，请填写完再提交！");
		$(".bseeeBill").focus();
		return false;
	}
	if($.trim($(".bseeeIntegral").val()) == ""){
		alert("所需积分不能为空，请填写完再提交！");
		$(".bseeeIntegral").focus();
		return false;
	}
	if($.trim($(".bseeeOrd").val()) == ""){
		alert("显示顺序不能为空，请填写完再提交！");
		$(".bseeeOrd").focus();
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
	var bseeeUuid = getQueryString("id");
	var bseeeBill = $(".bseeeBill").val();
	var bseeeIntegral = $(".bseeeIntegral").val();
	var bseeeOrd = $(".bseeeOrd").val();
	
	var str = 'bseeeUuid='+encodeURIComponent(bseeeUuid)+'&bseeeBill='+encodeURIComponent(bseeeBill)+'&bseeeIntegral='+encodeURIComponent(bseeeIntegral)+'&bseeeOrd='+encodeURIComponent(bseeeOrd);
	getOData(str,"busiExchangeRule/update/busiExchangeRule",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}