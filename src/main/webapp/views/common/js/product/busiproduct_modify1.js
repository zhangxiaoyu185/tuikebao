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
	CKEDITOR.replace( 'bsprtView' , { height: '240px', 
		width: '480px',
		toolbar: [['Source','-','Preview'],
		          ['Cut','Copy','Paste','PasteText','PasteFromWord'],
		          ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
		          ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
		          ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
		           ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
		           ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		           ['Link','Unlink'],
		          ['Image','Table','HorizontalRule','Smiley'],
		           ['Styles','Format','Font','FontSize'],
		           ['TextColor','BGColor']],
		extraPlugins: 'codesnippet',
		uiColor: '#f3f3f3'
	} );
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'bsprtUuid='+encodeURIComponent(id);
	getOData(str,"busiProduct/views/back",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsprtName").val(oData.data.bsprtName || "");
			$(".bsprtDesc").val(oData.data.bsprtDesc || "");
			$(".bsprtTel").val(oData.data.bsprtTel || "");
			$(".bsprtSalePrice").val(oData.data.bsprtSalePrice || "");
			$(".bsprtOrignalPrice").val(oData.data.bsprtOrignalPrice || "");
			$(".bsprtSaleCount").val(oData.data.bsprtSaleCount || 0);
			$(".bsprtAddress").val(oData.data.bsprtAddress || "");		
			$(".bsprtIsVirtual").val(oData.data.bsprtIsVirtual || 0);		
			$(".bsprtExpress").val(oData.data.bsprtExpress || 0);
			CKEDITOR.instances.bsprtView.setData(oData.data.bsprtView);
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".bsprtName").val()) == ""){
		alert("商品名称不能为空，请填写完再提交！");
		$(".bsprtName").focus();
		return false;
	}
	if($.trim($(".bsprtSalePrice").val()) == ""){
		alert("售价范围不能为空，请填写完再提交！");
		$(".bsprtSalePrice").focus();
		return false;
	}
	if($.trim($(".bsprtSaleCount").val()) == ""){
		alert("销售数量不能为空，请填写完再提交！");
		$(".bsprtSaleCount").focus();
		return false;
	}
	if($.trim($(".bsprtIsVirtual").val()) == ""){
		alert("是否是虚拟商品不能为空，请填写完再提交！");
		$(".bsprtIsVirtual").focus();
		return false;
	}
	if($.trim($(".bsprtExpress").val()) == ""){
		alert("快递费用不能为空，请填写完再提交！");
		$(".bsprtExpress").focus();
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
	var bsprtView = CKEDITOR.instances.bsprtView.getData();
	var bsprtUuid = getQueryString("id");
	var bsprtName = $(".bsprtName").val();
	var bsprtDesc = $(".bsprtDesc").val();
	var bsprtTel = $(".bsprtTel").val();
	var bsprtSalePrice = $(".bsprtSalePrice").val();
	var bsprtOrignalPrice = $(".bsprtOrignalPrice").val();
	var bsprtSaleCount = $(".bsprtSaleCount").val();
	var bsprtAddress = $(".bsprtAddress").val();	
	var bsprtIsVirtual = $(".bsprtIsVirtual").val();
	var bsprtExpress = $(".bsprtExpress").val();
	
	var str = 'bsprtUuid='+encodeURIComponent(bsprtUuid)+'&bsprtName='+encodeURIComponent(bsprtName)+'&bsprtDesc='+encodeURIComponent(bsprtDesc)+'&bsprtTel='+encodeURIComponent(bsprtTel)+'&bsprtView='+encodeURIComponent(bsprtView)+'&bsprtSalePrice='+encodeURIComponent(bsprtSalePrice)+'&bsprtOrignalPrice='+encodeURIComponent(bsprtOrignalPrice)+'&bsprtSaleCount='+encodeURIComponent(bsprtSaleCount)+'&bsprtAddress='+encodeURIComponent(bsprtAddress)+'&bsprtIsVirtual='+encodeURIComponent(bsprtIsVirtual)+'&bsprtExpress='+encodeURIComponent(bsprtExpress);
	getOData(str,"busiProduct/update/busiProduct/basic",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改商品基本信息成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}