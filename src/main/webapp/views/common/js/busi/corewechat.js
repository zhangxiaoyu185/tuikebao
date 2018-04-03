// JavaScript Document
$(function () {
	initModify();
	//提交
	$(".submitStore").on("click",function(){
		checkModifyStore();
	});
	$(".submitShare").on("click",function(){
		checkModifyShare();
	});
});
//初始化
function initModify(){
	getStoreInfo();
	getShareInfo();
}
//获取商城端详情
function getStoreInfo(){
	var str = 'crwctUuid=161126131423yqiZ';
	getOData(str,"coreWechat/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crwctAppidStore").val(oData.data.crwctAppid || "");
			$(".crwctAppsecretStore").val(oData.data.crwctAppsecret || "");
			$(".crwctPartnerStore").val(oData.data.crwctPartner || "");
			$(".crwctPartnerkeyStore").val(oData.data.crwctPartnerkey || "");
			$(".crwctRemarksStore").val(oData.data.crwctRemarks || "");
			
			upImgItem.changeShow('store_img_upload_pic', 'crwctPicStore', oData.data.crwctPic, 400, 200, '8', '8', '400X200', function(widget) {
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
			
			$(".crwctProjectNameStore").val(oData.data.crwctProjectName || "");
			$(".crwctProjectDescStore").val(oData.data.crwctProjectDesc || "");
			
			var crwctProjectLogoStore = oData.data.crwctProjectLogo;
			$(".upImg_flag_store").attr("data-uuid", crwctProjectLogoStore);
			var imgUrl =urlfile + 'coreAttachment/image/get/' + crwctProjectLogoStore ;
			$(".upImg_flag_store").css("background","url("+imgUrl+")0% 0% / 100px 100px no-repeat");
		
			//图片上传
			$('.input_filt_logo_store').on('change',function() {
				var upImg = upImgItem.init('input_filt_logo_store');
			    var formData = new FormData();//构造空对象，下面用append 方法赋值。  
			        formData.append("cratmType", $(this).parent().attr('data-cratmType'));  
			        formData.append("cratmDir",$(this).parent().attr('data-cratmDir')); 
			     	formData.append("file", $(".input_filt_logo_store")[0].files[0]);
		   
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
			                $(".upImg_flag_store").attr("data-uuid", imgUuid);
			              	var imgUrl =urlfile + 'coreAttachment/image/get/' + imgUuid;
			                $(".upImg_flag_store").css("background","url("+imgUrl+")0% 0% / 100px 100px no-repeat");
			            }
		            },           
			    });
			});
		} else {
			alert(data.errMsg);
		}
	}},true);
}
//获取分享端详情
function getShareInfo(){
	var str = 'crwctUuid=161126131423yqiI';
	getOData(str,"coreWechat/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crwctAppidShare").val(oData.data.crwctAppid || "");
			$(".crwctAppsecretShare").val(oData.data.crwctAppsecret || "");
			$(".crwctPartnerShare").val(oData.data.crwctPartner || "");
			$(".crwctPartnerkeyShare").val(oData.data.crwctPartnerkey || "");
			$(".crwctRemarksShare").val(oData.data.crwctRemarks || "");			
			
			upImgItem.changeShow('share_img_upload_pic', 'crwctPicShare', oData.data.crwctPic, 400, 200, '8', '8', '400X200', function(widget) {
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
		    
			$(".crwctProjectNameShare").val(oData.data.crwctProjectName || "");
			$(".crwctProjectDescShare").val(oData.data.crwctProjectDesc || "");
			
			var crwctProjectLogoShare = oData.data.crwctProjectLogo;
			$(".upImg_flag_share").attr("data-uuid", crwctProjectLogoShare);
			var imgUrl =urlfile + 'coreAttachment/image/get/' + crwctProjectLogoShare ;
			$(".upImg_flag_share").css("background","url("+imgUrl+")0% 0% / 100px 100px no-repeat");
		
			//图片上传
			$('.input_filt_logo_share').on('change',function() {
				var upImg = upImgItem.init('input_filt_logo_share');
			    var formData = new FormData();//构造空对象，下面用append 方法赋值。  
			        formData.append("cratmType", $(this).parent().attr('data-cratmType'));  
			        formData.append("cratmDir",$(this).parent().attr('data-cratmDir')); 
			     	formData.append("file", $(".input_filt_logo_share")[0].files[0]);
		   
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
			                $(".upImg_flag_share").attr("data-uuid", imgUuid);
			              	var imgUrl =urlfile + 'coreAttachment/image/get/' + imgUuid;
			                $(".upImg_flag_share").css("background","url("+imgUrl+")0% 0% / 100px 100px no-repeat");
			            }
		            },           
			    });
			});
		} else {
			alert(data.errMsg);
		}
	}},true);
}
//商城端检查提交
function checkModifyStore(){
	if($.trim($(".crwctAppidStore").val()) == ""){
		alert("appid不能为空，请填写完再提交！");
		$(".crwctAppidStore").focus();
		return false;
	}
	if($.trim($(".crwctAppsecretStore").val()) == ""){
		alert("appsecret不能为空，请填写完再提交！");
		$(".crwctAppsecretStore").focus();
		return false;
	}

	var r=confirm("是否确认修改？");
	if (r==true){
		var crwctUuid = "161126131423yqiZ";
		var crwctAppid = $(".crwctAppidStore").val();
		var crwctAppsecret = $(".crwctAppsecretStore").val();
		var crwctPic = $(".crwctPicStore").attr('data-uuid');
		var crwctPartner = $(".crwctPartnerStore").val();
		var crwctPartnerkey = $(".crwctPartnerkeyStore").val();
		var crwctProjectLogo = $(".crwctProjectLogoStore").attr('data-uuid');
		var crwctProjectName = $(".crwctProjectNameStore").val();
		var crwctProjectDesc = $(".crwctProjectDescStore").val();
		var crwctRemarks = $(".crwctRemarksStore").val();
		var str = 'crwctUuid='+encodeURIComponent(crwctUuid)+'&crwctAppid='+encodeURIComponent(crwctAppid)+'&crwctAppsecret='+encodeURIComponent(crwctAppsecret)
		+'&crwctPic='+encodeURIComponent(crwctPic)+'&crwctPartner='+encodeURIComponent(crwctPartner)+'&crwctPartnerkey='+encodeURIComponent(crwctPartnerkey)
		+'&crwctProjectLogo='+encodeURIComponent(crwctProjectLogo)+'&crwctProjectName='+encodeURIComponent(crwctProjectName)+'&crwctProjectDesc='+encodeURIComponent(crwctProjectDesc)
		+'&crwctRemarks='+encodeURIComponent(crwctRemarks);
		getOData(str,"coreWechat/update/coreWechat",{
			fn:function(oData){
				alert("修改成功！");
			},
			fnerr:function(oData){
				alert("修改失败！");
			}
		});
	}
}
//分享端检查提交
function checkModifyShare(){
	if($.trim($(".crwctAppidShare").val()) == ""){
		alert("appid不能为空，请填写完再提交！");
		$(".crwctAppidShare").focus();
		return false;
	}
	if($.trim($(".crwctAppsecretShare").val()) == ""){
		alert("appsecret不能为空，请填写完再提交！");
		$(".crwctAppsecretShare").focus();
		return false;
	}

	var r=confirm("是否确认修改？");
	if (r==true){
		var crwctUuid = "161126131423yqiI";
		var crwctAppid = $(".crwctAppidShare").val();
		var crwctAppsecret = $(".crwctAppsecretShare").val();
		var crwctPic = $(".crwctPicShare").attr('data-uuid');
		var crwctPartner = $(".crwctPartnerShare").val();
		var crwctPartnerkey = $(".crwctPartnerkeyShare").val();
		var crwctProjectLogo = $(".crwctProjectLogoShare").attr('data-uuid');
		var crwctProjectName = $(".crwctProjectNameShare").val();
		var crwctProjectDesc = $(".crwctProjectDescShare").val();
		var crwctRemarks = $(".crwctRemarksShare").val();
		var str = 'crwctUuid='+encodeURIComponent(crwctUuid)+'&crwctAppid='+encodeURIComponent(crwctAppid)+'&crwctAppsecret='+encodeURIComponent(crwctAppsecret)
		+'&crwctPic='+encodeURIComponent(crwctPic)+'&crwctPartner='+encodeURIComponent(crwctPartner)+'&crwctPartnerkey='+encodeURIComponent(crwctPartnerkey)
		+'&crwctProjectLogo='+encodeURIComponent(crwctProjectLogo)+'&crwctProjectName='+encodeURIComponent(crwctProjectName)+'&crwctProjectDesc='+encodeURIComponent(crwctProjectDesc)
		+'&crwctRemarks='+encodeURIComponent(crwctRemarks);
		getOData(str,"coreWechat/update/coreWechat",{
			fn:function(oData){
				alert("修改成功！");
			},
			fnerr:function(oData){
				alert("修改失败！");
			}
		});
	}
}
//切换
function store(){
	$('#storeContent').css("display","block");
	$('#shareContent').css("display","none");
	$('#store').addClass("bg_color");
	$('#share').removeClass("bg_color");
}
function share(){
	$('#shareContent').css("display","block");
	$('#storeContent').css("display","none");
	$('#share').addClass("bg_color");
	$('#store').removeClass("bg_color");
}