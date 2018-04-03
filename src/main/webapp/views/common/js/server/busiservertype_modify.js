// JavaScript Document
$(function () {
	getInfo(getQueryString("id"));
	//提交
	$(".submit").on("click",function(){
		checkModify();
	});
});
//获取详情
function getInfo(id){
	var str = 'bssteUuid='+encodeURIComponent(id);
	getOData(str,"busiServerType/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bssteName").val(oData.data.bssteName || "");
			$(".bssteDetails").val(oData.data.bssteDetails || "");
			$(".bssteShow").val(oData.data.bssteShow || "");
			
			if(oData.data.bssteIslist == 1) {
				$("input[name='hasList']").eq(0).attr('checked','checked');
				$("#descUrl").css('display','none');
			} else {
				$("input[name='hasList']").eq(1).attr('checked','checked');
				$("#descUrl").css('display','block');
			}

			upImgItem.changeShow('img_upload_pic', 'bssteIcon', oData.data.bssteIcon, 52, 52, '9', '9', '52X52', function(widget) {
				var str = "data-uuid=" + $(widget).attr('data-uuid');
				getOData(str,"coreAttachment/delete",{
            		fn:function(oData){
            			widget.attr('data-uuid', "");
            		},
            		fnerr:function(oData){
            			alert(oData.errMsg);
            		}
            	});
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
		            	var str = "cratmUuid=" + widget.attr('data-uuid');
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
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".bssteName").val()) == ""){
		alert("类别名称不能为空，请填写完再提交！");
		$(".bssteName").focus();
		return false;
	}
	if($.trim($(".bssteIcon").attr('data-uuid')) == ""){
		alert("图标不能为空，请填写完再提交！");
		return false;
	}
	if($("input[name='hasList']:checked").val() == 0) {
		if($.trim($(".bssteDetails").val()) == ""){
			alert("无列表时详情URL不能为空，请填写完再提交！");
			$(".bssteDetails").focus();
			return false;
		}
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
	var bssteUuid = getQueryString("id");
	var bssteName = $(".bssteName").val();
	var bssteIcon = $('.bssteIcon').eq(0).attr('data-uuid');
	var bssteIslist = $("input[name='hasList']:checked").val();
	var bssteDetails = $(".bssteDetails").val();
	var bssteShow = $(".bssteShow").val();
	var str = 'bssteUuid='+encodeURIComponent(bssteUuid)+'&bssteName='+encodeURIComponent(bssteName)+'&bssteIcon='+encodeURIComponent(bssteIcon)+'&bssteIslist='+encodeURIComponent(bssteIslist)+'&bssteDetails='+encodeURIComponent(bssteDetails)+'&bssteShow='+bssteShow;
	getOData(str,"busiServerType/update/busiServerType",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
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
