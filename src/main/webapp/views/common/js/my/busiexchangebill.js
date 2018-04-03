// JavaScript Document
$(function () {
	$(".cur_postion").html("当前位置： [ "+sessionStorage.getItem("pmenuname")+" ] - [ "+sessionStorage.getItem("cmenuname")+" ]");

	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>兑换用户:</span>'
		+'<input type="text" class="inputStyle_condition userName"/>'
		+'<span>兑换手机号:</span>'
		+'<input type="text" class="inputStyle_condition bseblMobile"/>'
		+'</div>';
	
	$(".searchContent").html(strhtml_searchContent);
	//是否显示查询条件
	showSearchBox(true);

	var obj = {};//查询条件对象
	searchContent(obj);
	showList(obj,1);

	//详情
	$("body").delegate('.detailit','click', function(){
		layer.open({
			type: 2,
			title: '详情',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [widthLayer , heightLayer],
			content: '../my/busiexchangebill_detail.html?id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
		});
	});
	//新增
	$('.addit').on('click', function(){
		layer.open({
			type: 2,
			title: '新增',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [widthLayer , heightLayer],
			content: '../my/busiexchangebill_add.html?timestamp='+(new Date()).valueOf()
		});
	});
	
	//全选
	$("body").delegate("input[name='checkboxAll']","click",function(){
		if($(this).attr("checked")){
			$("input[name='checkbox']").each(function(){
				$(this).attr("checked",true);
			});
		}else{
			$("input[name='checkbox']").each(function(){
				$(this).removeAttr("checked");
			});
		}
	});

	//充值
	$("body").delegate(".recharge","click",function(){
		var r=confirm("确认充值？");
		if (r==true){
			var str = 'bseblUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"busiExchangeBill/update/busiExchangeBill",{fn:function(oData){
				refreshList();
				}}
			);
		}
	});
	
	//tr高亮显示
	$("body").delegate(".trHighLight","mouseleave",function(){
		$(this).find("td").css("background-color","#fff");
	});
	//tr高亮显示并显示图
	$("body").delegate(".trHighLight","mouseenter",function(){
		$(this).find("td").css("background-color","#c1ebff");
	});
	//查询
	$("body").delegate(".searchBtn","click",function(){
		var obj = {};//查询条件对象
		searchContent(obj);
		showList(obj,1);
	});
	//重置
	$("body").delegate(".resetBtn","click",function(){
		var obj = {};//查询条件对象
		$(".userName").val('');
		$(".bseblMobile").val('');
		
		searchContent(obj);
		showList(obj,1);
	});
	//上一页
	$('.manageBox').delegate(".backpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
	});
	//下一页
	$('.manageBox').delegate(".nextpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		var pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
	});
	//首页
	$('.manageBox').delegate(".firstpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		var pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
	});
	//末页
	$('.manageBox').delegate(".lastpage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		var pagenum = parseInt($(this).attr("data-pagenum"));
		showList(obj,pagenum);
	});
	//跳转至
	$('.manageBox').delegate(".jumppage","click", function() {
		var obj = {};//查询条件对象
		searchContent(obj);
		var pagenum = parseInt($('.jumppagetext').val());
		if(pagenum>0 && pagenum <=parseInt($(this).attr("data-pagemax"))){
			showList(obj,pagenum);
		}else{
			alert("查无此页！");
		}
	});

});
function showList(obj,pagenum){
	var aData =[{name:"<input type='checkbox' name='checkboxAll' value='checkbox' />",percent:"5"},
				{name:"兑换用户",percent:"10"},
				{name:"消耗积分",percent:"10"},
				{name:"兑换话费",percent:"10"},
				{name:"兑换手机号",percent:"10"},
				{name:"状态",percent:"10"},
				{name:"兑换时间",percent:"10"},
				{name:"操作",percent:"10"}];
	setTableHead(aData);
	var str = 'pageNum='+pagenum+'&pageSize=10&userName='+encodeURIComponent(obj.userName||'')+'&bseblMobile='+encodeURIComponent(obj.bseblMobile||'');
	
	getOData(str,"busiExchangeBill/find/by/cnd",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			var statusName = "";
			var statusHtml = "";
			if(arrData[i].bseblStatus==1) {statusName="已充值";}
			if(arrData[i].bseblStatus==0) {
				statusName="待充值";
				statusHtml = '<a  class="p-edit recharge" data-id="'+arrData[i].bseblUuid+'">充值</a>';
			}
			
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].bseblUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].bseblUserName || "")+'</td>'
				+'<td>'+(arrData[i].bseblIntegral || "")+'</td>'
				+'<td>'+(arrData[i].bseblBill || "")+'</td>'
				+'<td>'+(arrData[i].bseblMobile || "")+'</td>'
				+'<td>'+statusName+'</td>'
				+'<td>'+(arrData[i].bseblCdate || "")+'</td>'
				+'<td>'
				+'<a  class="p-edit detailit" data-id="'+arrData[i].bseblUuid+'">查看</a>'
				+statusHtml
				+'</td>'
				+'</tr>';
		}
		$(".tb-body").html(strhtml_list);
		setTableFoot(oData.data,aData.length);
		}}
	);
}
function isNull(obj,pagenum){
	var str = 'pageNum='+pagenum+'&pageSize=10&userName='+encodeURIComponent(obj.userName||'')+'&bseblMobile='+encodeURIComponent(obj.bseblMobile||'');
	getOData(str,"busiExchangeBill/find/by/cnd",{fn:function(oData){
		var arrData = oData.data.result;
		var ln = arrData.length;
		if(ln == 0){
			if (oData.data.totalCount != 0 && pagenum !=1){
				showList(obj,pagenum-1);
			}else{
				showList(obj,1);
			}
		}else{
			showList(obj,pagenum);
		}
		}}
	);
}

function refreshList(){
	var obj = {};//查询条件对象
	searchContent(obj);
	showList(obj,parseInt($(".curpage").text()));
	layer.closeAll();
}

function searchContent(obj){
	obj.userName = $(".userName").val();
	obj.bseblMobile = $(".bseblMobile").val();
}
