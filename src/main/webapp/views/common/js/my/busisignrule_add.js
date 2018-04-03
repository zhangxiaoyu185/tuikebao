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
	$(".bssreCount").val("");
	$(".bssreIntegral").val("");
	$(".bssreOrd").val("");
}
//检查提交
function checkAdd(){
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
	
	var r=confirm("是否确认增加？");
	if (r==true){
		var msgObject = parent.layer.msg('处理中，请等待……', {
			icon: 16,
			shade: 0.4,
			time: waitImgTime //（如果不配置，默认是3秒）
		}, function(index){
			parent.layer.close(index);
		});
		Add(msgObject);
	}
}
//提交
function Add(msgObject){
	var bssreCount = $(".bssreCount").val();
	var bssreIntegral = $(".bssreIntegral").val();
	var bssreOrd = $(".bssreOrd").val();
	var str = 'bssreCount='+encodeURIComponent(bssreCount)+'&bssreIntegral='+encodeURIComponent(bssreIntegral)+'&bssreOrd='+encodeURIComponent(bssreOrd);
	getOData(str,"busiSignRule/add/busiSignRule",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}