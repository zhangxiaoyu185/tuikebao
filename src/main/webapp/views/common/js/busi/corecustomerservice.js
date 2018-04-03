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
function getInfo(id){
	var str = 'crcseUuid=161126131423aaaa';
	getOData(str,"coreCustomerService/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crcseTel").val(oData.data.crcseTel || "");
			$(".crcseQq").val(oData.data.crcseQq || "");
		} else {
			alert(data.errMsg);
		}
	}});
}

//检查提交
function checkModify(){
	if($.trim($(".crcseTel").val()) == ""){
		alert("电话号码(|隔开)不能为空，请填写完再提交！");
		$(".crcseTel").focus();
		return false;
	}
	if($.trim($(".crcseQq").val()) == ""){
		alert("QQ(|隔开)不能为空，请填写完再提交！");
		$(".crcseQq").focus();
		return false;
	}

	var r=confirm("是否确认修改？");
	if (r==true){
		Modify();
	}
}
//提交
function Modify(){
	var crcseUuid = '161126131423aaaa';
	var crcseTel = $(".crcseTel").val();
	var crcseQq = $(".crcseQq").val();
	var str = 'crcseUuid='+encodeURIComponent(crcseUuid)+'&crcseTel='+encodeURIComponent(crcseTel)+'&crcseQq='+encodeURIComponent(crcseQq);
	getOData(str,"coreCustomerService/update/coreCustomerService",{
		fn:function(oData){
			alert("修改成功！");
		},
		fnerr:function(oData){
			alert("修改失败！");
		}
	});
}