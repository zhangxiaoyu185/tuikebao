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
	$(".bsireCount").val("");
	$(".bsireIntegral").val("");
	$(".bsireOrd").val("");
}
//检查提交
function checkAdd(){
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
	var bsireCount = $(".bsireCount").val();
	var bsireIntegral = $(".bsireIntegral").val();
	var bsireOrd = $(".bsireOrd").val();
	var str = 'bsireCount='+encodeURIComponent(bsireCount)+'&bsireIntegral='+encodeURIComponent(bsireIntegral)+'&bsireOrd='+encodeURIComponent(bsireOrd);
	getOData(str,"busiInvitedRule/add/busiInvitedRule",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}