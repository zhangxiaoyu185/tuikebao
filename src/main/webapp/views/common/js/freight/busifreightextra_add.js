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
	$(".bsfeaFreightTemplate").val("");
	$(".bsfeaRegion").val("");
	$(".bsfeaInitialPrice").val("");
	$(".bsfeaStepPrice").val("");
	$(".bsfeaCdate").val("");
	$(".bsfeaUdate").val("");
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
	if($.trim($(".bsfeaFreightTemplate").val()) == ""){
		alert("所属运费模板不能为空，请填写完再提交！");
		$(".bsfeaFreightTemplate").focus();
		return false;
	}
	if($.trim($(".bsfeaRegion").val()) == ""){
		alert("特殊地区不能为空，请填写完再提交！");
		$(".bsfeaRegion").focus();
		return false;
	}
	if($.trim($(".bsfeaInitialPrice").val()) == ""){
		alert("初始价格不能为空，请填写完再提交！");
		$(".bsfeaInitialPrice").focus();
		return false;
	}
	if($.trim($(".bsfeaStepPrice").val()) == ""){
		alert("加价不能为空，请填写完再提交！");
		$(".bsfeaStepPrice").focus();
		return false;
	}
	if($.trim($(".bsfeaCdate").val()) == ""){
		alert("创建时间不能为空，请填写完再提交！");
		$(".bsfeaCdate").focus();
		return false;
	}
	if($.trim($(".bsfeaUdate").val()) == ""){
		alert("更新时间不能为空，请填写完再提交！");
		$(".bsfeaUdate").focus();
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
	var bsfeaFreightTemplate = $(".bsfeaFreightTemplate").val();
	var bsfeaRegion = $(".bsfeaRegion").val();
	var bsfeaInitialPrice = $(".bsfeaInitialPrice").val();
	var bsfeaStepPrice = $(".bsfeaStepPrice").val();
	var bsfeaCdate = $(".bsfeaCdate").val();
	var bsfeaUdate = $(".bsfeaUdate").val();
	var str = 'bsfeaFreightTemplate='+encodeURIComponent(bsfeaFreightTemplate)+'&bsfeaRegion='+encodeURIComponent(bsfeaRegion)+'&bsfeaInitialPrice='+encodeURIComponent(bsfeaInitialPrice)+'&bsfeaStepPrice='+encodeURIComponent(bsfeaStepPrice)+'&bsfeaCdate='+encodeURIComponent(bsfeaCdate)+'&bsfeaUdate='+encodeURIComponent(bsfeaUdate);
	getOData(str,"busiFreightExtra/add/busiFreightExtra",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}