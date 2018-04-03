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
    
    getTypeList();
});

function getTypeList() {
	getOData(null,"busiProductCategory/find/have/child",{
		fn:function(oData){
			var typeList =  '<option value ="">无</option>';
			for(var i=0; i<oData.data.length; i++) {
				typeList += '<option value ="'+oData.data[i].bspcyUuid+'">'+oData.data[i].bspcyName+'</option>';
			}
			$('.bspcyTop').html(typeList);
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});	
}

//初始化
function initAdd(){
	$(".bspcyName").val("");
	$(".bspcyOrd").val("");
	$(".bspcyChildNode").val("");
}
//检查提交
function checkAdd(){
	if($.trim($(".bspcyName").val()) == ""){
		alert("分类名称不能为空，请填写完再提交！");
		$(".bspcyName").focus();
		return false;
	}	
	if($.trim($(".bspcyOrd").val()) == ""){
		alert("顺序不能为空，请填写完再提交！");
		$(".bspcyOrd").focus();
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
	var bspcyName = $(".bspcyName").val();
	var bspcyIcon = $(".bspcyIcon").attr('data-uuid');
	var bspcyOrd = $(".bspcyOrd").val();
	var bspcyTop = $(".bspcyTop").val();
	var bspcyChildNode;
	if(bspcyTop){
		bspcyChildNode = 0;
	}else {
		bspcyChildNode = 1;
	}

	var str = 'bspcyName='+encodeURIComponent(bspcyName)+'&bspcyIcon='+encodeURIComponent(bspcyIcon)+'&bspcyOrd='+encodeURIComponent(bspcyOrd)+
	'&bspcyTop='+encodeURIComponent(bspcyTop)+'&bspcyChildNode='+encodeURIComponent(bspcyChildNode);
	getOData(str,"busiProductCategory/add/busiProductCategory",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}