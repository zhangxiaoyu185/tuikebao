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
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'bststUuid='+encodeURIComponent(id);
	getOData(str,"busiTagSet/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bststName").val(oData.data.bststName || "");
			$(".bststDesc").val(oData.data.bststDesc || "");
			$(".bststIcon").val(oData.data.bststIcon || "");
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
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
	var bststUuid = getQueryString("id");
	var bststName = $(".bststName").val();
	var bststDesc = $(".bststDesc").val();
	var bststIcon = $(".bststIcon").val();
	var str = 'bststUuid='+encodeURIComponent(bststUuid)+'&bststName='+encodeURIComponent(bststName)+'&bststDesc='+encodeURIComponent(bststDesc)+'&bststIcon='+encodeURIComponent(bststIcon);
	getOData(str,"busiTagSet/update/busiTagSet",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}