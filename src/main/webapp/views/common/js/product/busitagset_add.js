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
	$(".bststName").val("");
	$(".bststDesc").val("");
	$(".bststIcon").val("");
}
//检查提交
function checkAdd(){
	if($.trim($(".bststName").val()) == ""){
		alert("标签名称不能为空，请填写完再提交！");
		$(".bststName").focus();
		return false;
	}
	if($.trim($(".bststDesc").val()) == ""){
		alert("标签简介不能为空，请填写完再提交！");
		$(".bststDesc").focus();
		return false;
	}
	if($.trim($(".bststIcon").val()) == ""){
		alert("标签图标字不能为空，请填写完再提交！");
		$(".bststIcon").focus();
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
	var bststName = $(".bststName").val();
	var bststDesc = $(".bststDesc").val();
	var bststIcon = $(".bststIcon").val();
	var str = 'bststName='+encodeURIComponent(bststName)+'&bststDesc='+encodeURIComponent(bststDesc)+'&bststIcon='+encodeURIComponent(bststIcon);
	getOData(str,"busiTagSet/add/busiTagSet",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}