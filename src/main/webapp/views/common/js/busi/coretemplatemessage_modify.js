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
	$(".crtmeUser").val("");
	$(".crtmeMobile").val("");
	$(".crtmeContent").val("");
	$(".crtmeTime").val("");
	$(".crtmeStatus").val("");
	$(".crtmeType").val("");
	$(".crtmeSign").val("");
	/*CKEDITOR.replace( 'crcseContent' , { height: '240px', 
		width: '480px',
		toolbar: [[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo', 'Bold',
						'Italic',"Format","FontSize","TextColor" ,"CodeSnippet","Table"]],
		extraPlugins: 'codesnippet',
		uiColor: '#f3f3f3'
	} );*/
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'crtmeUuid='+encodeURIComponent(id);
	getOData(str,"coreTemplateMessage/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crtmeUser").val(oData.data.crtmeUser || "");
			$(".crtmeMobile").val(oData.data.crtmeMobile || "");
			$(".crtmeContent").val(oData.data.crtmeContent || "");
			$(".crtmeTime").val(oData.data.crtmeTime || "");
			$(".crtmeStatus").val(oData.data.crtmeStatus || "");
			$(".crtmeType").val(oData.data.crtmeType || "");
			$(".crtmeSign").val(oData.data.crtmeSign || "");
			//CKEDITOR.instances.crcseContent.setData(oData.data.crcseContent);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".crtmeUser").val()) == ""){
		alert("所属用户不能为空，请填写完再提交！");
		$(".crtmeUser").focus();
		return false;
	}
	if($.trim($(".crtmeMobile").val()) == ""){
		alert("所属用户手机号不能为空，请填写完再提交！");
		$(".crtmeMobile").focus();
		return false;
	}
	if($.trim($(".crtmeContent").val()) == ""){
		alert("消息内容不能为空，请填写完再提交！");
		$(".crtmeContent").focus();
		return false;
	}
	if($.trim($(".crtmeTime").val()) == ""){
		alert("发送时间不能为空，请填写完再提交！");
		$(".crtmeTime").focus();
		return false;
	}
	if($.trim($(".crtmeStatus").val()) == ""){
		alert("发送状态:1成功,0失败不能为空，请填写完再提交！");
		$(".crtmeStatus").focus();
		return false;
	}
	if($.trim($(".crtmeType").val()) == ""){
		alert("模板消息类型不能为空，请填写完再提交！");
		$(".crtmeType").focus();
		return false;
	}
	if($.trim($(".crtmeSign").val()) == ""){
		alert("模板消息标识不能为空，请填写完再提交！");
		$(".crtmeSign").focus();
		return false;
	}
	/*if($.trim(CKEDITOR.instances.crcseContent.getData()) == ""){
		alert("内容不能为空，请填写完再提交！");
		return false;
	}*/

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
	//var crcseContent = CKEDITOR.instances.crcseContent.getData();
	var crtmeUuid = getQueryString("id");
	var crtmeUser = $(".crtmeUser").val();
	var crtmeMobile = $(".crtmeMobile").val();
	var crtmeContent = $(".crtmeContent").val();
	var crtmeTime = $(".crtmeTime").val();
	var crtmeStatus = $(".crtmeStatus").val();
	var crtmeType = $(".crtmeType").val();
	var crtmeSign = $(".crtmeSign").val();
	var str = 'crtmeUuid='+encodeURIComponent(crtmeUuid)+'&crtmeUser='+encodeURIComponent(crtmeUser)+'&crtmeMobile='+encodeURIComponent(crtmeMobile)+'&crtmeContent='+encodeURIComponent(crtmeContent)+'&crtmeTime='+encodeURIComponent(crtmeTime)+'&crtmeStatus='+encodeURIComponent(crtmeStatus)+'&crtmeType='+encodeURIComponent(crtmeType)+'&crtmeSign='+encodeURIComponent(crtmeSign);
	getOData(str,"coreTemplateMessage/update/coreTemplateMessage",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}