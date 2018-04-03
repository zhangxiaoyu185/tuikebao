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
	getInfo();
}
//获取详情
function getInfo(){
	var str = 'bsecgUuid=170310134652gCBG';
	getOData(str,"busiExpressConfig/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsecgKdnCode").val(oData.data.bsecgKdnCode || "");
			$(".bsecgApiKey").val(oData.data.bsecgApiKey || "");
	
			//CKEDITOR.instances.crcseContent.setData(oData.data.crcseContent);
		} else {
			alert(data.errMsg);
		}
	}});
}
//检查提交
function checkModify(){
	if($.trim($(".bsecgKdnCode").val()) == ""){
		alert("商户标识不能为空，请填写完再提交！");
		$(".bsecgKdnCode").focus();
		return false;
	}
	if($.trim($(".bsecgApiKey").val()) == ""){
		alert("API_KEY不能为空，请填写完再提交！");
		$(".bsecgApiKey").focus();
		return false;
	}
	var r=confirm("是否确认修改？");
	if (r==true){
		Modify();
	}
}
//提交
function Modify(){
	
	var bsecgUuid = '170310134652gCBG';
	var bsecgKdnCode = $(".bsecgKdnCode").val();
	var bsecgApiKey = $(".bsecgApiKey").val();

	var str = 'bsecgUuid='+encodeURIComponent(bsecgUuid)+'&bsecgKdnCode='+encodeURIComponent(bsecgKdnCode)+'&bsecgApiKey='+encodeURIComponent(bsecgApiKey);
	getOData(str,"busiExpressConfig/update/busiExpressConfig",{
		fn:function(oData){	
			alert("修改成功！");
		},
		fnerr:function(oData){
			alert("修改失败！");
		}
	});
}