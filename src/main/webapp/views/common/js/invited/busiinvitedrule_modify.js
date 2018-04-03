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
	$(".bsireCount").val("");
	$(".bsireIntegral").val("");
	$(".bsireOrd").val("");
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'bsireUuid='+encodeURIComponent(id);
	getOData(str,"busiInvitedRule/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsireCount").val(oData.data.bsireCount || "");
			$(".bsireIntegral").val(oData.data.bsireIntegral || "");
			$(".bsireOrd").val(oData.data.bsireOrd || "");
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".bsireCount").val()) == ""){
		alert("需要邀请人数不能为空，请填写完再提交！");
		$(".bsireCount").focus();
		return false;
	}
	if($.trim($(".bsireIntegral").val()) == ""){
		alert("可领取积分不能为空，请填写完再提交！");
		$(".bsireIntegral").focus();
		return false;
	}
	if($.trim($(".bsireOrd").val()) == ""){
		alert("顺序不能为空，请填写完再提交！");
		$(".bsireOrd").focus();
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
	var bsireUuid = getQueryString("id");
	var bsireCount = $(".bsireCount").val();
	var bsireIntegral = $(".bsireIntegral").val();
	var bsireOrd = $(".bsireOrd").val();
	var str = 'bsireUuid='+encodeURIComponent(bsireUuid)+'&bsireCount='+encodeURIComponent(bsireCount)+'&bsireIntegral='+encodeURIComponent(bsireIntegral)+'&bsireOrd='+encodeURIComponent(bsireOrd);
	getOData(str,"busiInvitedRule/update/busiInvitedRule",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}