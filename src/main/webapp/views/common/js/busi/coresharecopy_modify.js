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
	$(".crscyCopy").val("");
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'crscyUuid='+encodeURIComponent(id);
	getOData(str,"coreShareCopy/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crscyCopy").val(oData.data.crscyCopy || "");
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".crscyCopy").val()) == ""){
		alert("文案不能为空，请填写完再提交！");
		$(".crscyCopy").focus();
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
	var crscyUuid = getQueryString("id");
	var crscyCopy = $(".crscyCopy").val();
	var str = 'crscyUuid='+encodeURIComponent(crscyUuid)+'&crscyCopy='+encodeURIComponent(crscyCopy);
	getOData(str,"coreShareCopy/update/coreShareCopy",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}