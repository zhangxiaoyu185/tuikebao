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
	$(".bseceCode").val("");
	$(".bseceName").val("");
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
	var str = 'bseceUuid='+encodeURIComponent(id);
	getOData(str,"busiExpressCode/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bseceCode").val(oData.data.bseceCode || "");
			$(".bseceName").val(oData.data.bseceName || "");
			//CKEDITOR.instances.crcseContent.setData(oData.data.crcseContent);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".bseceCode").val()) == ""){
		alert("编码不能为空，请填写完再提交！");
		$(".bseceCode").focus();
		return false;
	}
	if($.trim($(".bseceName").val()) == ""){
		alert("名称不能为空，请填写完再提交！");
		$(".bseceName").focus();
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
	var bseceUuid = getQueryString("id");
	var bseceCode = $(".bseceCode").val();
	var bseceName = $(".bseceName").val();
	var str = 'bseceUuid='+encodeURIComponent(bseceUuid)+'&bseceCode='+encodeURIComponent(bseceCode)+'&bseceName='+encodeURIComponent(bseceName);
	getOData(str,"busiExpressCode/update/busiExpressCode",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}