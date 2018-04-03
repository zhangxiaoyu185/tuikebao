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
	getUserList();
	getInfo(getQueryString("id"));
}

//获取详情
function getInfo(id){
	var str = 'bsicsUuid='+encodeURIComponent(id);
	getOData(str,"busiInvitedChats/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsicsPeriods").val(oData.data.bsicsPeriods || "");
			$(".bsicsGoldMatters").val(oData.data.bsicsGoldMatters || "");
			$(".bsicsGoldNumber").val(oData.data.bsicsGoldNumber || "");
			$(".bsicsGoldReward").val(oData.data.bsicsGoldReward || "");
			$(".bsicsGoldGainer").val(oData.data.bsicsGoldGainer || "");
			$(".bsicsSilverMatters").val(oData.data.bsicsSilverMatters || "");
			$(".bsicsSilverNumber").val(oData.data.bsicsSilverNumber || "");
			$(".bsicsSilverReward").val(oData.data.bsicsSilverReward || "");
			$(".bsicsSilverGainer").val(oData.data.bsicsSilverGainer || "");
			$(".bsicsBronzeMatters").val(oData.data.bsicsBronzeMatters || "");
			$(".bsicsBronzeNumber").val(oData.data.bsicsBronzeNumber || "");
			$(".bsicsBronzeReward").val(oData.data.bsicsBronzeReward || "");
			$(".bsicsBronzeGainer").val(oData.data.bsicsBronzeGainer || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
//加载模块下拉框内容
function getUserList(){
	var str = '';
	getOData(str,"coreUser/find/all",{fn:function(oData){
		var strhtml_goodColumns = '';
		var arrData = oData.data;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_goodColumns += '<option value ="'+arrData[i].crusrUuid+'" >'+arrData[i].crusrName+'</option>';
		}
		$(".bsicsGoldGainer").html(strhtml_goodColumns);
		$(".bsicsSilverGainer").html(strhtml_goodColumns);
		$(".bsicsBronzeGainer").html(strhtml_goodColumns);
	}},true);
}
//检查提交
function checkModify(){
	if($.trim($(".bsicsPeriods").val()) == ""){
		alert("期数不能为空，请填写完再提交！");
		$(".bsicsPeriods").focus();
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
	var bsicsUuid = getQueryString("id");
	var bsicsPeriods = $(".bsicsPeriods").val();
	var bsicsGoldMatters = $(".bsicsGoldMatters").val();
	var bsicsGoldNumber = $(".bsicsGoldNumber").val();
	var bsicsGoldReward = $(".bsicsGoldReward").val();
	var bsicsGoldGainer = $(".bsicsGoldGainer").val();
	var bsicsSilverMatters = $(".bsicsSilverMatters").val();
	var bsicsSilverNumber = $(".bsicsSilverNumber").val();
	var bsicsSilverReward = $(".bsicsSilverReward").val();
	var bsicsSilverGainer = $(".bsicsSilverGainer").val();
	var bsicsBronzeMatters = $(".bsicsBronzeMatters").val();
	var bsicsBronzeNumber = $(".bsicsBronzeNumber").val();
	var bsicsBronzeReward = $(".bsicsBronzeReward").val();
	var bsicsBronzeGainer = $(".bsicsBronzeGainer").val();
	var str = 'bsicsUuid='+encodeURIComponent(bsicsUuid)+'&bsicsPeriods='+encodeURIComponent(bsicsPeriods)+'&bsicsGoldMatters='+encodeURIComponent(bsicsGoldMatters)+'&bsicsGoldNumber='+encodeURIComponent(bsicsGoldNumber)+'&bsicsGoldReward='+encodeURIComponent(bsicsGoldReward)+'&bsicsGoldGainer='+encodeURIComponent(bsicsGoldGainer)+'&bsicsSilverMatters='+encodeURIComponent(bsicsSilverMatters)+'&bsicsSilverNumber='+encodeURIComponent(bsicsSilverNumber)+'&bsicsSilverReward='+encodeURIComponent(bsicsSilverReward)+'&bsicsSilverGainer='+encodeURIComponent(bsicsSilverGainer)+'&bsicsBronzeMatters='+encodeURIComponent(bsicsBronzeMatters)+'&bsicsBronzeNumber='+encodeURIComponent(bsicsBronzeNumber)+'&bsicsBronzeReward='+encodeURIComponent(bsicsBronzeReward)+'&bsicsBronzeGainer='+encodeURIComponent(bsicsBronzeGainer);
	getOData(str,"busiInvitedChats/update/busiInvitedChats",{
		fn:function(oData){
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}