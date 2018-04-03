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
	$(".bssreCount").val("");
	$(".bssreIntegral").val("");
	$(".bssreOrd").val("");
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'bssreUuid='+encodeURIComponent(id);
	getOData(str,"busiSignRule/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bssreCount").val(oData.data.bssreCount || "");
			$(".bssreIntegral").val(oData.data.bssreIntegral || "");
			$(".bssreOrd").val(oData.data.bssreOrd || "");
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".bssreCount").val()) == ""){
		alert("连续天数不能为空，请填写完再提交！");
		$(".bssreCount").focus();
		return false;
	}
	if($.trim($(".bssreIntegral").val()) == ""){
		alert("可领取积分不能为空，请填写完再提交！");
		$(".bssreIntegral").focus();
		return false;
	}
	if($.trim($(".bssreOrd").val()) == ""){
		alert("顺序不能为空，请填写完再提交！");
		$(".bssreOrd").focus();
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
	var bssreUuid = getQueryString("id");
	var bssreCount = $(".bssreCount").val();
	var bssreIntegral = $(".bssreIntegral").val();
	var bssreOrd = $(".bssreOrd").val();
	var str = 'bssreUuid='+encodeURIComponent(bssreUuid)+'&bssreCount='+encodeURIComponent(bssreCount)+'&bssreIntegral='+encodeURIComponent(bssreIntegral)+'&bssreOrd='+encodeURIComponent(bssreOrd);
	getOData(str,"busiSignRule/update/busiSignRule",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}