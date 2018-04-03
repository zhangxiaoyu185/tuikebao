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
	var str = 'crgdeUuid='+encodeURIComponent(id);
	getOData(str,"coreGrade/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crgdeName").val(oData.data.crgdeName || "");
			$(".crgdeOrd").val(oData.data.crgdeOrd || 0);
			$(".crgdeIntegral").val(oData.data.crgdeIntegral || 0);
			$(".crgdeDayShares").val(oData.data.crgdeDayShares || 0);
			
			upImgItem.changeShow('img_upload_pic', 'crgdeIcon', oData.data.crgdeIcon, 88, 30, '2', '2', '30X88', function(widget) {
				var str = "cratmUuid=" + $(widget).attr('data-uuid');
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
	if($.trim($(".crgdeIcon").attr('data-uuid')) == ""){
		alert("等级图标不能为空，请填写完再提交！");
		return false;
	}
	if($.trim($(".crgdeOrd").val()) == ""){
		alert("等级顺序不能为空，请填写完再提交！");
		$(".crgdeOrd").focus();
		return false;
	}
	if($.trim($(".crgdeIntegral").val()) == ""){
		alert("所需积分不能为空，请填写完再提交！");
		$(".crgdeIntegral").focus();
		return false;
	}
	if($.trim($(".crgdeDayShares").val()) == ""){
		alert("每天分享次数不能为空，请填写完再提交！");
		$(".crgdeDayShares").focus();
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
	var crgdeUuid = getQueryString("id");
	var crgdeIcon = $(".crgdeIcon").attr('data-uuid');
	var crgdeOrd = $(".crgdeOrd").val();
	var crgdeIntegral = $(".crgdeIntegral").val();
	var crgdeDayShares = $(".crgdeDayShares").val();
	var str = 'crgdeUuid='+encodeURIComponent(crgdeUuid)+'&crgdeIcon='+encodeURIComponent(crgdeIcon)+'&crgdeOrd='+encodeURIComponent(crgdeOrd)+'&crgdeIntegral='+encodeURIComponent(crgdeIntegral)+'&crgdeDayShares='+encodeURIComponent(crgdeDayShares);
	getOData(str,"coreGrade/update/coreGrade",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}