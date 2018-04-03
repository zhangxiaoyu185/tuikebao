// JavaScript Document
$(function () {
	getExpressCodeList();
	getInfo(getQueryString("id"));
	//提交
	$(".submit").on("click",function(){
		checkModify();
	});
});
//获取详情
function getInfo(id){
	var str = 'bsoimUuid='+encodeURIComponent(id);
	getOData(str,"busiOrder/item/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsoimExpno").val(oData.data.bsoimExpno || "");
			$(".bsoimExpressCode").val(oData.data.bsoimExpressCode || "");
		} else {
			alert(data.errMsg);
		}
	}},true);
}
//加载 快递鸟公司编码配置下拉框
function getExpressCodeList(){
	var str = '';
	getOData(str,"busiExpressCode/find/all",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_goodColumns += '<option value ="'+arrData[i].bseceCode+'" >'+arrData[i].bseceName+'</option>';
		}
		$(".bsoimExpressCode").html(strhtml_goodColumns);
	}},true);
}
//检查提交
function checkModify(){
	if($.trim($(".bsoimExpno").val()) == ""){
		alert("快递单号不能为空，请填写完再提交！");
		$(".bsoimExpno").focus();
		return false;
	}
	if($.trim($(".bsoimExpressCode").val()) == ""){
		alert("快递公司不能为空，请填写完再提交！");
		$(".bsoimExpressCode").focus();
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
	var bsoimUuid = getQueryString("id");
	var bsoimExpno = $(".bsoimExpno").val();
	var bsoimExpressCode = $(".bsoimExpressCode").val();
	var bsoimExpressName = $(".bsoimExpressCode").find("option:selected").text();
	var str = 'bsoimUuid='+encodeURIComponent(bsoimUuid)+'&bsoimExpno='+encodeURIComponent(bsoimExpno)+'&bsoimExpressCode='+encodeURIComponent(bsoimExpressCode)+'&bsoimExpressName='+encodeURIComponent(bsoimExpressName);
	getOData(str,"busiOrder/item/back/shipment",{
		fn:function(oData){
			window.parent.refreshList();
			alert("发货成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}