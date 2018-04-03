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
}
//检查提交
function checkAdd(){
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
	var crtmeUser = $(".crtmeUser").val();
	var crtmeMobile = $(".crtmeMobile").val();
	var crtmeContent = $(".crtmeContent").val();
	var crtmeTime = $(".crtmeTime").val();
	var crtmeStatus = $(".crtmeStatus").val();
	var crtmeType = $(".crtmeType").val();
	var crtmeSign = $(".crtmeSign").val();
	var str = 'crtmeUser='+encodeURIComponent(crtmeUser)+'&crtmeMobile='+encodeURIComponent(crtmeMobile)+'&crtmeContent='+encodeURIComponent(crtmeContent)+'&crtmeTime='+encodeURIComponent(crtmeTime)+'&crtmeStatus='+encodeURIComponent(crtmeStatus)+'&crtmeType='+encodeURIComponent(crtmeType)+'&crtmeSign='+encodeURIComponent(crtmeSign);
	getOData(str,"coreTemplateMessage/add/coreTemplateMessage",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}