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
	$(".bsfteName").val("");
	$(".bsfteInitialWeight").val("");
	$(".bsfteInitialPrice").val("");
	$(".bsfteWeight").val("");
	$(".bsftePrice").val("");
	$(".bsfteCdate").val("");
	$(".bsfteUdate").val("");
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
	var str = 'bsfteUuid='+encodeURIComponent(id);
	getOData(str,"busiFreightTemplate/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsfteName").val(oData.data.bsfteName || "");
			$(".bsfteInitialWeight").val(oData.data.bsfteInitialWeight || "");
			$(".bsfteInitialPrice").val(oData.data.bsfteInitialPrice || "");
			$(".bsfteWeight").val(oData.data.bsfteWeight || "");
			$(".bsftePrice").val(oData.data.bsftePrice || "");
			$(".bsfteCdate").val(oData.data.bsfteCdate || "");
			$(".bsfteUdate").val(oData.data.bsfteUdate || "");
			//CKEDITOR.instances.crcseContent.setData(oData.data.crcseContent);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".bsfteName").val()) == ""){
		alert("模板名称不能为空，请填写完再提交！");
		$(".bsfteName").focus();
		return false;
	}
	if($.trim($(".bsfteInitialWeight").val()) == ""){
		alert("初始重量不能为空，请填写完再提交！");
		$(".bsfteInitialWeight").focus();
		return false;
	}
	if($.trim($(".bsfteInitialPrice").val()) == ""){
		alert("初始价格不能为空，请填写完再提交！");
		$(".bsfteInitialPrice").focus();
		return false;
	}
	if($.trim($(".bsfteWeight").val()) == ""){
		alert("加重不能为空，请填写完再提交！");
		$(".bsfteWeight").focus();
		return false;
	}
	if($.trim($(".bsftePrice").val()) == ""){
		alert("加价不能为空，请填写完再提交！");
		$(".bsftePrice").focus();
		return false;
	}
	if($.trim($(".bsfteCdate").val()) == ""){
		alert("创建时间不能为空，请填写完再提交！");
		$(".bsfteCdate").focus();
		return false;
	}
	if($.trim($(".bsfteUdate").val()) == ""){
		alert("更新时间不能为空，请填写完再提交！");
		$(".bsfteUdate").focus();
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
	var bsfteUuid = getQueryString("id");
	var bsfteName = $(".bsfteName").val();
	var bsfteInitialWeight = $(".bsfteInitialWeight").val();
	var bsfteInitialPrice = $(".bsfteInitialPrice").val();
	var bsfteWeight = $(".bsfteWeight").val();
	var bsftePrice = $(".bsftePrice").val();
	var bsfteCdate = $(".bsfteCdate").val();
	var bsfteUdate = $(".bsfteUdate").val();
	var str = 'bsfteUuid='+encodeURIComponent(bsfteUuid)+'&bsfteName='+encodeURIComponent(bsfteName)+'&bsfteInitialWeight='+encodeURIComponent(bsfteInitialWeight)+'&bsfteInitialPrice='+encodeURIComponent(bsfteInitialPrice)+'&bsfteWeight='+encodeURIComponent(bsfteWeight)+'&bsftePrice='+encodeURIComponent(bsftePrice)+'&bsfteCdate='+encodeURIComponent(bsfteCdate)+'&bsfteUdate='+encodeURIComponent(bsfteUdate);
	getOData(str,"busiFreightTemplate/update/busiFreightTemplate",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}