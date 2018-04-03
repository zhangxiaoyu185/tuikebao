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
	$(".bssdlTitle").val("");
	$(".bssdlSeo").val("");
	$(".bssdlHtml").val("");
	$(".bssdlUrl").val("");
	$(".bssdlCdate").val("");
	$(".bssdlUdate").val("");
	$(".bssdlStatus").val("");
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
	var str = 'bssdlUuid='+encodeURIComponent(id);
	getOData(str,"busiServerDetail/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bssdlTitle").val(oData.data.bssdlTitle || "");
			$(".bssdlSeo").val(oData.data.bssdlSeo || "");
			$(".bssdlHtml").val(oData.data.bssdlHtml || "");
			$(".bssdlUrl").val(oData.data.bssdlUrl || "");
			$(".bssdlCdate").val(oData.data.bssdlCdate || "");
			$(".bssdlUdate").val(oData.data.bssdlUdate || "");
			$(".bssdlStatus").val(oData.data.bssdlStatus || "");
			//CKEDITOR.instances.crcseContent.setData(oData.data.crcseContent);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".bssdlTitle").val()) == ""){
		alert("Title内容不能为空，请填写完再提交！");
		$(".bssdlTitle").focus();
		return false;
	}
	if($.trim($(".bssdlSeo").val()) == ""){
		alert("SEO关键字不能为空，请填写完再提交！");
		$(".bssdlSeo").focus();
		return false;
	}
	if($.trim($(".bssdlHtml").val()) == ""){
		alert("页面html内容不能为空，请填写完再提交！");
		$(".bssdlHtml").focus();
		return false;
	}
	if($.trim($(".bssdlUrl").val()) == ""){
		alert("生成的URL路径不能为空，请填写完再提交！");
		$(".bssdlUrl").focus();
		return false;
	}
	if($.trim($(".bssdlCdate").val()) == ""){
		alert("创建日期不能为空，请填写完再提交！");
		$(".bssdlCdate").focus();
		return false;
	}
	if($.trim($(".bssdlUdate").val()) == ""){
		alert("修改日期不能为空，请填写完再提交！");
		$(".bssdlUdate").focus();
		return false;
	}
	if($.trim($(".bssdlStatus").val()) == ""){
		alert("状态:1启用,0禁用不能为空，请填写完再提交！");
		$(".bssdlStatus").focus();
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
	var bssdlUuid = getQueryString("id");
	var bssdlTitle = $(".bssdlTitle").val();
	var bssdlSeo = $(".bssdlSeo").val();
	var bssdlHtml = $(".bssdlHtml").val();
	var bssdlUrl = $(".bssdlUrl").val();
	var bssdlCdate = $(".bssdlCdate").val();
	var bssdlUdate = $(".bssdlUdate").val();
	var bssdlStatus = $(".bssdlStatus").val();
	var str = 'bssdlUuid='+encodeURIComponent(bssdlUuid)+'&bssdlTitle='+encodeURIComponent(bssdlTitle)+'&bssdlSeo='+encodeURIComponent(bssdlSeo)+'&bssdlHtml='+encodeURIComponent(bssdlHtml)+'&bssdlUrl='+encodeURIComponent(bssdlUrl)+'&bssdlCdate='+encodeURIComponent(bssdlCdate)+'&bssdlUdate='+encodeURIComponent(bssdlUdate)+'&bssdlStatus='+encodeURIComponent(bssdlStatus);
	getOData(str,"busiServerDetail/update/busiServerDetail",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}