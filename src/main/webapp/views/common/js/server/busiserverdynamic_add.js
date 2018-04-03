// JavaScript Document
$(function () {
	initAdd();
	//提交
	$(".submit").on("click",function(){
		checkAdd();
	});

	var upImg = upImgItem.init('input_filt');
    $('.input_filt').on('change',function() {
        upImg.show(this,
            {url: urlfile + "coreAttachment/stream/upload", width: $(this).parent().attr('data-width'), height: $(this).parent().attr('data-height'), 
            param:{cratmType: $(this).parent().attr('data-cratmType'),cratmDir:$(this).parent().attr('data-cratmDir')}},
            function(returnImagData, ajaxResult, widget) {//图片数据， 上传图片后返回的ajax数据
            	if(ajaxResult.success) {         		
            		widget.attr('data-uuid',ajaxResult.data);
            	}else {
            		alert(ajaxResult.errMsg);
            	}          	
            },function(widget){
            	var str = "cratmUuid=" + widget.attr('data-uuid');;
            	getOData(str,"coreAttachment/delete",{
            		fn:function(oData){
            			widget.attr('data-uuid', "");
            		},
            		fnerr:function(oData){
            			alert(oData.errMsg);
            		}
            	});
            }
        );
    });
});
//初始化
function initAdd(){
	$(".bssdcTitle").val("");
	$(".bssdcDesc").val("");
	CKEDITOR.replace( 'bssdcContent' , { height: '240px', 
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
	getServerTypeList();
}

//加载服务类别下拉框内容
function getServerTypeList(){
	var str = '';
	getOData(str,"busiServerType/find/all",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			var bssteShowCH = "都显示";
			if(arrData[i].bssteShow == 1) {
				bssteShowCH = "分享端";
			}
			if(arrData[i].bssteShow == 2) {
				bssteShowCH = "商城端";
			}
			strhtml_goodColumns += '<option value ="'+arrData[i].bssteUuid+'" >'+bssteShowCH+"/"+arrData[i].bssteName+'</option>';
		}
		$(".bssdcType").html(strhtml_goodColumns);
	}});
}

//检查提交
function checkAdd(){
	if($.trim($(".bssdcType").val()) == ""){
		alert("服务类别不能为空，请填写完再提交！");
		$(".bssdcType").focus();
		return false;
	}
	if($.trim($(".bssdcTitle").val()) == ""){
		alert("标题不能为空，请填写完再提交！");
		$(".bssdcTitle").focus();
		return false;
	}
	
	var imgUuid = $('.bssdcPic').eq(0).attr('data-uuid');

	if($.trim(imgUuid) == ""){
		alert("封面图不能为空，请填写完再提交！");
		return false;
	}
	if($.trim(CKEDITOR.instances.bssdcContent.getData()) == ""){
		alert("内容不能为空，请填写完再提交！");
		return false;
	}

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
	var bssdcType = $(".bssdcType").val();
	var bssdcTitle = $(".bssdcTitle").val();
	var bssdcPic = $(".bssdcPic").eq(0).attr('data-uuid');
	var bssdcContent = CKEDITOR.instances.bssdcContent.getData();
	var bssdcDesc = $(".bssdcDesc").val();

	var str = 'bssdcType='+encodeURIComponent(bssdcType)+'&bssdcTitle='+encodeURIComponent(bssdcTitle)+'&bssdcPic='+encodeURIComponent(bssdcPic)+'&bssdcContent='+encodeURIComponent(bssdcContent)+'&bssdcDesc='+encodeURIComponent(bssdcDesc);
	getOData(str,"busiServerDynamic/add/busiServerDynamic",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}