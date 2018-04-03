// JavaScript Document
$(function () {
	$(".cur_postion").html("当前位置： [ "+sessionStorage.getItem("pmenuname")+" ] - [ "+sessionStorage.getItem("cmenuname")+" ]");

	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>订单号:</span>'
		+'<input type="text" class="inputStyle_condition bsorrUuid"/>'
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
			content: '../order/busiorder_detail.html?id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
		});
	});
	//客服调整价格
	$("body").delegate('.modifyit','click', function(){
		layer.open({
			type: 2,
			title: '客服调整价格',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [widthLayer , heightLayer],
			content: '../order/busiorder_modify.html?id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
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
		$(".bsorrUuid").val('');
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
	            {name:"订单号",percent:"15"},
	            {name:"所属用户",percent:"15"},
				{name:"订单总价",percent:"10"},
				{name:"客服调整运费",percent:"10"},
				{name:"客服调整总价",percent:"10"},
				{name:"实付款",percent:"10"},
				{name:"状态",percent:"10"},
				{name:"操作",percent:"15"}];
	setTableHead(aData);
	var str = '';
	if(obj.bsorrUuid==""){
		str = 'pageNum='+pagenum+'&pageSize=10';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=10&bsorrUuid='+encodeURIComponent(obj.bsorrUuid);
	}
	getOData(str,"busiOrder/find/by/cnd/my/pending/back",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			var bsorrStatusCH = '支付中';
			if(2==arrData[i].bsorrStatus) {
				bsorrStatusCH = '支付成功';
			}
			if(3==arrData[i].bsorrStatus) {
				bsorrStatusCH = '支付失败';
			}
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].bsorrUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].bsorrUuid || "")+'</td>'
				+'<td>'+(arrData[i].bsorrUserName || "")+'</td>'
				+'<td>'+(arrData[i].bsorrOrderValue || 0)+'</td>'
				+'<td>'+(arrData[i].bsorrAdjustExpress || 0)+'</td>'
				+'<td>'+(arrData[i].bsorrAdjustProduct || 0)+'</td>'
				+'<td>'+(arrData[i].bsorrTotalValue || 0)+'</td>'		
				+'<td>'+bsorrStatusCH+'</td>'
				+'<td>'
				+'<a  class="p-edit detailit" data-id="'+arrData[i].bsorrUuid+'">查看</a>'
				+'<a  class="p-edit modifyit" data-id="'+arrData[i].bsorrUuid+'">调整价格</a>'
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
	if(obj.bsorrUuid==""){
		str = 'pageNum='+pagenum+'&pageSize=10';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=10&bsorrUuid='+encodeURIComponent(obj.bsorrUuid);
	}
	getOData(str,"busiOrder/find/by/cnd/my/pending/back",{fn:function(oData){
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
	obj.bsorrUuid = $(".bsorrUuid").val();
}
