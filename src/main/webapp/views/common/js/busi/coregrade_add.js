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
	$(".crgdeName").val("");
	$(".crgdeOrd").val("");
	$(".crgdeIntegral").val("");
	$(".crgdeDayShares").val("");
}
//检查提交
function checkAdd(){
	if($.trim($(".crgdeName").val()) == ""){
		alert("等级名称不能为空，请填写完再提交！");
		$(".crgdeName").focus();
		return false;
	}
	if($.trim($(".crgdeIcon").attr('data-uuid')) == ""){
		alert("等级图标不能为空，请填写完再提交！");
		$(".crgdeIcon").focus();
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
	var crgdeName = $(".crgdeName").val();
	var crgdeIcon = $(".crgdeIcon").attr('data-uuid');
	var crgdeOrd = $(".crgdeOrd").val();
	var crgdeIntegral = $(".crgdeIntegral").val();
	var crgdeDayShares = $(".crgdeDayShares").val();
	var str = 'crgdeName='+encodeURIComponent(crgdeName)+'&crgdeIcon='+encodeURIComponent(crgdeIcon)+'&crgdeOrd='+encodeURIComponent(crgdeOrd)+'&crgdeIntegral='+encodeURIComponent(crgdeIntegral)+'&crgdeDayShares='+encodeURIComponent(crgdeDayShares);
	getOData(str,"coreGrade/add/coreGrade",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}