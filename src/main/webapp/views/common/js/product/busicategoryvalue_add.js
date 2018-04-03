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
}
//检查提交
function checkAdd(){
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
	var bscveCategory = $(".bscveCategory").val();
	var bscveAttr = $(".bscveAttr").val();
	var bscveValue = $(".bscveValue").val();
	var bscveOrd = $(".bscveOrd").val();
	var bscveCdate = $(".bscveCdate").val();
	var bscveUdate = $(".bscveUdate").val();
	var str = 'bscveCategory='+encodeURIComponent(bscveCategory)+'&bscveAttr='+encodeURIComponent(bscveAttr)+'&bscveValue='+encodeURIComponent(bscveValue)+'&bscveOrd='+encodeURIComponent(bscveOrd)+'&bscveCdate='+encodeURIComponent(bscveCdate)+'&bscveUdate='+encodeURIComponent(bscveUdate);
	getOData(str,"busiCategoryValue/add/busiCategoryValue",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}