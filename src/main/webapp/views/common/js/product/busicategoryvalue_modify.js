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
	$(".bscveCategory").val("");
	$(".bscveAttr").val("");
	$(".bscveValue").val("");
	$(".bscveOrd").val("");
	$(".bscveCdate").val("");
	$(".bscveUdate").val("");
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
	var str = 'bscveUuid='+encodeURIComponent(id);
	getOData(str,"busiCategoryValue/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bscveCategory").val(oData.data.bscveCategory || "");
			$(".bscveAttr").val(oData.data.bscveAttr || "");
			$(".bscveValue").val(oData.data.bscveValue || "");
			$(".bscveOrd").val(oData.data.bscveOrd || "");
			$(".bscveCdate").val(oData.data.bscveCdate || "");
			$(".bscveUdate").val(oData.data.bscveUdate || "");
			//CKEDITOR.instances.crcseContent.setData(oData.data.crcseContent);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".bscveCategory").val()) == ""){
		alert("分类标识不能为空，请填写完再提交！");
		$(".bscveCategory").focus();
		return false;
	}
	if($.trim($(".bscveAttr").val()) == ""){
		alert("属性标识不能为空，请填写完再提交！");
		$(".bscveAttr").focus();
		return false;
	}
	if($.trim($(".bscveValue").val()) == ""){
		alert("属性值不能为空，请填写完再提交！");
		$(".bscveValue").focus();
		return false;
	}
	if($.trim($(".bscveOrd").val()) == ""){
		alert("显示顺序不能为空，请填写完再提交！");
		$(".bscveOrd").focus();
		return false;
	}
	if($.trim($(".bscveCdate").val()) == ""){
		alert("创建时间不能为空，请填写完再提交！");
		$(".bscveCdate").focus();
		return false;
	}
	if($.trim($(".bscveUdate").val()) == ""){
		alert("更新时间不能为空，请填写完再提交！");
		$(".bscveUdate").focus();
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
	var bscveUuid = getQueryString("id");
	var bscveCategory = $(".bscveCategory").val();
	var bscveAttr = $(".bscveAttr").val();
	var bscveValue = $(".bscveValue").val();
	var bscveOrd = $(".bscveOrd").val();
	var bscveCdate = $(".bscveCdate").val();
	var bscveUdate = $(".bscveUdate").val();
	var str = 'bscveUuid='+encodeURIComponent(bscveUuid)+'&bscveCategory='+encodeURIComponent(bscveCategory)+'&bscveAttr='+encodeURIComponent(bscveAttr)+'&bscveValue='+encodeURIComponent(bscveValue)+'&bscveOrd='+encodeURIComponent(bscveOrd)+'&bscveCdate='+encodeURIComponent(bscveCdate)+'&bscveUdate='+encodeURIComponent(bscveUdate);
	getOData(str,"busiCategoryValue/update/busiCategoryValue",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}