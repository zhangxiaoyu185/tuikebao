// JavaScript Document
$(function () {
	$(".cur_postion").html("当前位置： [ "+sessionStorage.getItem("pmenuname")+" ] - [ "+sessionStorage.getItem("cmenuname")+" ]");

	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>子订单号:</span>'
		+'<input type="text" class="inputStyle_condition bsoimUuid"/>'
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
			content: '../order/busiorderitem_detail.html?id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
		});
	});
	//查看物流
	$("body").delegate('.express','click', function(){
		layer.open({
			type: 2,
			title: '详情',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [widthLayer , heightLayer],
			content: '../order/busiorderexpress_detail.html?id='+$(this).attr("data-id")+'&code='+$(this).attr("data-code")+'&no='+$(this).attr("data-no")+'&timestamp='+(new Date()).valueOf()
		});
	});
	//转为售后
	$("body").delegate('.afterit','click', function(){
		layer.open({
			type: 2,
			title: '转为售后',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [widthLayer , heightLayer],
			content: '../order/busiorderitem_modify_zwsh.html?id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
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
		$(".bsoimUuid").val('');
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
	            {name:"所属用户",percent:"15"},
				{name:"父订单号",percent:"12"},
				{name:"子订单号",percent:"12"},
				{name:"商品名称",percent:"15"},
				{name:"商品属性",percent:"15"},
				{name:"购买数量",percent:"8"},
				{name:"明细金额",percent:"8"},
				{name:"操作",percent:"10"}];
	setTableHead(aData);
	var str = '';
	if(obj.bsoimUuid==""){
		str = 'pageNum='+pagenum+'&pageSize=10&bsoimStatus=5';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=10&bsoimStatus=5&bsoimUuid='+encodeURIComponent(obj.bsoimUuid);
	}
	getOData(str,"busiOrder/item/find/by/cnd/my/status/back",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].bsoimUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].bsoimUserName || "")+'</td>'
				+'<td>'+(arrData[i].bsoimOrder || "")+'</td>'
				+'<td>'+(arrData[i].bsoimUuid || "")+'</td>'
				+'<td>'+(arrData[i].bsoimProductName || "")+'</td>'
				+'<td>'+(arrData[i].bsoimAttrName || "")+'</td>'
				+'<td>'+(arrData[i].bsoimQuantity || 0)+'</td>'
				+'<td>'+(arrData[i].bsoimValue || 0)+'</td>'
				+'<td>'
				+'<a  class="p-edit detailit" data-id="'+arrData[i].bsoimOrder+'">查看</a>'
				+'<a  class="p-edit express" data-id="'+arrData[i].bsoimUuid+'"  data-code="'+arrData[i].bsoimExpressCode+'"   data-no="'+arrData[i].bsoimExpno+'">查看物流</a>'
				+'<a  class="p-edit afterit" data-id="'+arrData[i].bsoimUuid+'">转为售后</a>'
				+'</td>'
				+'</tr>';
		}
		$(".tb-body").html(strhtml_list);
		setTableFoot(oData.data,aData.length);
		}}
	);
}
function isNull(obj,pagenum){
	var str = '';
	if(obj.bsoimUuid==""){
		str = 'pageNum='+pagenum+'&pageSize=10&bsoimStatus=5';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=10&bsoimStatus=5&bsoimUuid='+encodeURIComponent(obj.bsoimUuid);
	}
	getOData(str,"busiOrder/item/find/by/cnd/my/status/back",{fn:function(oData){
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
	obj.bsoimUuid = $(".bsoimUuid").val();
}
