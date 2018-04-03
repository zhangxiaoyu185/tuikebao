// JavaScript Document
$(function () {
	$(".cur_postion").html("当前位置： [ "+sessionStorage.getItem("pmenuname")+" ] - [ "+sessionStorage.getItem("cmenuname")+" ]");

	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>消息内容:</span>'
		+'<input type="text" class="inputStyle_condition crmecContent"/>'
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
			content: '../busi/coremessagecenter_detail.html?id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
		});
	});
	//处理
	$("body").delegate('.modifyit','click', function(){
		var r=confirm("是否确认修改？");
		if (r==true){			
			var str = 'crmecUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"coreMessageCenter/update/coreMessageCenter",{fn:function(oData){
					pagenum = parseInt($(".curpage").text());
					isNull(obj,pagenum);
					alert("处理成功！");
				}}
			);
		}
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

	//批量删除
	$("body").delegate(".delthese","click",function(){
		var ids = '';
		$("input[name='checkbox']").each(function(){
			if($(this).attr("checked")){
				ids += $(this).attr("data-id")+"|";
			}
		});
		if(ids == ""){
			alert("未选择删除对象！");
		}else{
			var r=confirm("是否确认删除所选的记录？");
			if (r==true){
				var str = 'crmecUuids='+encodeURIComponent(ids);
				getOData(str,"coreMessageCenter/delete/batch",{fn:function(oData){
						var obj = {};//查询条件对象
						searchContent(obj);
						pagenum = parseInt($(".curpage").text());
						isNull(obj,pagenum);
						alert("删除成功！");
					}}
				);
			}
		}
	});

	//删除
	$("body").delegate(".delit","click",function(){
		var r=confirm("是否确认删除？");
		if (r==true){
			var str = 'crmecUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"coreMessageCenter/delete/one",{fn:function(oData){
					var obj = {};//查询条件对象
					searchContent(obj);
					pagenum = parseInt($(".curpage").text());
					isNull(obj,pagenum);
					alert("删除成功！");
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
		$(".crmecContent").val('');
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
				{name:"消息内容",percent:"50"},
				{name:"状态:",percent:"10"},
				{name:"消息类型",percent:"15"},
				{name:"操作",percent:"20"}];
	setTableHead(aData);
	var str = '';
	if(obj.crmecContent==""){
		str = 'pageNum='+pagenum+'&pageSize=10';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=10&crmecContent='+encodeURIComponent(obj.crmecContent);
	}
	getOData(str,"coreMessageCenter/find/by/cnd",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;	
		for(var i=0;i<ln;i++){
			var crrorStatus = arrData[i].crmecStatus;//:1已处理2未处理3已删除
			var crrorStatusCH="已处理";
			if(2==crrorStatus){
				crrorStatusCH="未处理";
			}else if(3==crrorStatus){
				crrorStatusCH="已删除";
			}
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].crmecUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].crmecContent || "")+'</td>'
				+'<td>'+(crrorStatusCH)+'</td>'
				+'<td>'+(arrData[i].crmecType || "")+'</td>'
				+'<td>';
			if(arrData[i].crmecStatus==1){
				strhtml_list+='<a  class="p-edit delit" data-id="'+arrData[i].crmecUuid+'">删除</a>'
				+'</td>'
				+'</tr>';
			}else{
				strhtml_list+='<a  class="p-edit modifyit" data-id="'+arrData[i].crmecUuid+'">处理</a>'
				+'<a  class="p-edit delit" data-id="'+arrData[i].crmecUuid+'">删除</a>'
				+'</td>'
				+'</tr>';
			}				
		}		
		$(".tb-body").html(strhtml_list);
		setTableFoot(oData.data,aData.length);
	}});
}
function isNull(obj,pagenum){
	var str = '';
	if(obj.crmecContent==""){
		str = 'pageNum='+pagenum+'&pageSize=10';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=10&crmecContent='+encodeURIComponent(obj.crmecContent);
	}
	getOData(str,"coreMessageCenter/find/by/cnd",{fn:function(oData){
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
	obj.crmecContent = $(".crmecContent").val();
}
