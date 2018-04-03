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
	$(".bseeeBill").val("");
	$(".bseeeIntegral").val("");
}
//检查提交
function checkAdd(){
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
	var bseeeBill = $(".bseeeBill").val();
	var bseeeIntegral = $(".bseeeIntegral").val();
	var bseeeOrd = $(".bseeeOrd").val();
	var str = 'bseeeBill='+encodeURIComponent(bseeeBill)+'&bseeeIntegral='+encodeURIComponent(bseeeIntegral)+'&bseeeOrd='+encodeURIComponent(bseeeOrd);
	getOData(str,"busiExchangeRule/add/busiExchangeRule",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}