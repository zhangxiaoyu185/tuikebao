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
	$(".bssteName").val("");
	$(".bssteDetails").val("");
}

//检查提交
function checkAdd(){
	if($.trim($(".bssteName").val()) == ""){
		alert("类别名称不能为空，请填写完再提交！");
		$(".bssteName").focus();
		return false;
	}

	var imgUuid = $('.bssteIcon').eq(0).attr('data-uuid');

	if($.trim(imgUuid) == ""){
		alert("请上传图片！");
		return false;
	}	

	if($("input[name='hasList']:checked").val() == 0) {
		if($.trim($(".bssteDetails").val()) == ""){
			alert("无列表时详情URL不能为空，请填写完再提交！");
			$(".bssteDetails").focus();
			return false;
		}
	}
	var r=confirm("是否确认增加？");
	if (r==true){
		var msgObject = parent.layer.msg('处理中，请等待……', {
			icon: 16,
			shade: 0.4,
			//time: waitImgTime //（如果不配置，默认是3秒）
		}, function(index){
			//do something
			parent.layer.close(index);
		});
		Add(msgObject);
	}
}
//提交
function Add(msgObject){
	var bssteName = $(".bssteName").val();
	var imgUuid = $('.bssteIcon').eq(0).attr('data-uuid');

	var hasList = $("input[name='hasList']:checked").val();
	var bssteDetails =  $(".bssteDetails").val();
	var bssteShow =  $(".bssteShow").val();
	
	var str = 'bssteName='+encodeURIComponent(bssteName)+'&bssteIcon='+imgUuid+'&bssteIslist='+hasList+'&bssteDetails='+bssteDetails+'&bssteShow='+bssteShow;
	getOData(str, "busiServerType/add/busiServerType",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
			parent.layer.close(msgObject);
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}

function hasList(flag) {//flag  1有  0无
	if(flag==1) {
		$('#descUrl').hide();
	}else {
		$('#descUrl').show();
	}
}
