// JavaScript Document
$(function () {
	initAdd();
	//提交
	$(".submit").on("click",function(){
		checkAdd();
	});
	
	//图片上传
	$('.input_filt').on('change',function() {
		// var formData = new FormData($('#img_upload_pic')[0]);//序列化表单，$("form").serialize()只能序列化数据，不能序列化文件 
	    var upImg = upImgItem.init('input_filt');
	    var formData = new FormData();//构造空对象，下面用append 方法赋值。  
	        formData.append("cratmType", $(this).parent().attr('data-cratmType'));  
	        formData.append("cratmDir",$(this).parent().attr('data-cratmDir')); 
	     	formData.append("file", $(".input_filt")[0].files[0]);
	   
		$.ajax({
	        type: 'POST',
	        data: formData,
	        url: urlfile + 'coreAttachment/upload', 
	        processData: false,
	        contentType: false,
	        async: false,
	       	error: function(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("上传失败，请检查网络后重试");
            },

	        success: function(data){
              	if(!data.success) {
                  	alert(data.errMsg);                
              	} else {
	                var len = data.data.length;
	                var uuid = data.data;
	                var imgUuid = (uuid.substring(0,len - 1));
	                $(".upImg_flag").attr("data-uuid", imgUuid);
	              	var imgUrl =urlfile + 'coreAttachment/image/get/' + imgUuid;
	                $(".upImg_flag").css("background","url("+imgUrl+")0% 0% / 100px 100px no-repeat");
	            }
            },           
	    });
	});
});
//初始化
function initAdd(){
	$(".bshstName").val("");
}
//检查提交
function checkAdd(){
	if($.trim($(".bshstName").val()) == ""){
		alert("热点名称不能为空，请填写完再提交！");
		$(".bshstName").focus();
		return false;
	}
	if($.trim($(".bshstIcon").attr('data-uuid')) == ""){
		alert("热点图片不能为空，请填写完再提交！");
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
	var bshstName = $(".bshstName").val();
	var bshstIcon = $(".bshstIcon").attr('data-uuid');
	var str = 'bshstName='+encodeURIComponent(bshstName)+'&bshstIcon='+encodeURIComponent(bshstIcon);
	getOData(str,"busiHotSpot/add/busiHotSpot",{
		fn:function(oData){
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}