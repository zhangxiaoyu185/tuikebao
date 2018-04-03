// JavaScript Document
$(function () {
	$(".cur_postion").html("当前位置： [ "+sessionStorage.getItem("pmenuname")+" ] - [ "+sessionStorage.getItem("cmenuname")+" ]");

	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>上级分类:</span>'
		+'<select class="inputStyle_condition bspcyTop">'
		+'</select>'
		+'<span>分类名称:</span>'
		+'<input type="text" class="inputStyle_condition bspcyName"/>'
		+'</select>'
		+'</div>';
	$(".searchContent").html(strhtml_searchContent);
	
	getTypeList();
	
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
			content: '../product/busiproductcategory_detail.html?id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
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
			content: '../product/busiproductcategory_add.html?timestamp='+(new Date()).valueOf()
		});
	});
	//修改
	$("body").delegate('.modifyit','click', function(){
		layer.open({
			type: 2,
			title: '修改',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [widthLayer , heightLayer],
			content: '../product/busiproductcategory_modify.html?id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
		});
	});
	
	//启用
	$("body").delegate(".start","click",function(){
		var r=confirm("是否确认启用？");
		if (r==true){
			var str = 'bspcyUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"busiProductCategory/enable",{fn:function(oData){
				refreshList();
				}}
			);
		}
	});
	
	//禁用
	$("body").delegate(".stop","click",function(){
		var r=confirm("是否确认启用？");
		if (r==true){
			var str = 'bspcyUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"busiProductCategory/disable",{fn:function(oData){
				refreshList();
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
				var str = 'bspcyUuids='+encodeURIComponent(ids);
				getOData(str,"busiProductCategory/delete/batch",{fn:function(oData){
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
			var str = 'bspcyUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"busiProductCategory/delete/one",{fn:function(oData){
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
		$(".bspcyTop").val('');
		$(".bspcyName").val('');
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

function getTypeList() {
	getOData(null,"busiProductCategory/find/have/child",{
		fn:function(oData){
			var typeList =  '<option value ="">无</option>';
			for(var i=0; i<oData.data.length; i++) {
				typeList += '<option value ="'+oData.data[i].bspcyUuid+'">'+oData.data[i].bspcyName+'</option>';
			}
			$('.bspcyTop').html(typeList);
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});	
}

function showList(obj,pagenum){
	var aData =[{name:"<input type='checkbox' name='checkboxAll' value='checkbox' />",percent:"5"},
				{name:"分类名称",percent:"15"},
				{name:"顺序",percent:"15"},
				{name:"状态",percent:"15"},
				{name:"上级分类",percent:"15"},
				{name:"是否有子节点",percent:"15"},
				{name:"操作",percent:"20"}];
	setTableHead(aData);
	var str = 'pageNum='+pagenum+'&pageSize=10&bspcyName='+encodeURIComponent(obj.bspcyName||'')+'&bspcyTop='+encodeURIComponent(obj.bspcyTop||'');
	getOData(str,"busiProductCategory/find/by/cnd",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			var statusFlagHtml = ""; 
			if(arrData[i].bspcyStatus==0) {
				statusFlagHtml = '<a  class="p-edit start" data-id="'+arrData[i].bspcyUuid+'">启用</a>';
			}else {
				statusFlagHtml = '<a  class="p-edit stop" data-id="'+arrData[i].bspcyUuid+'">禁用</a>';
			}		
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].bspcyUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].bspcyName || "")+'</td>'
				+'<td>'+(arrData[i].bspcyOrd || "")+'</td>'
				+'<td>'+(arrData[i].bspcyStatus==1?"启用":"禁用")+'</td>'
				+'<td>'+(arrData[i].bspcyTopName || "")+'</td>'
				+'<td>'+(arrData[i].bspcyChildNode==1?"有":"无")+'</td>'
				+'<td>'
				+'<a  class="p-edit detailit" data-id="'+arrData[i].bspcyUuid+'">查看</a>'
				+'<a  class="p-edit modifyit" data-id="'+arrData[i].bspcyUuid+'">修改</a>'
				+statusFlagHtml
				+'<a  class="p-edit delit" data-id="'+arrData[i].bspcyUuid+'">删除</a>'
				+'</td>'
				+'</tr>';
		}
		$(".tb-body").html(strhtml_list);
		setTableFoot(oData.data,aData.length);
		}}
	);
}
function isNull(obj,pagenum){
	var str = 'pageNum='+pagenum+'&pageSize=10&bspcyName='+encodeURIComponent(obj.bspcyName||'')+'&bspcyTop='+encodeURIComponent(obj.bspcyTop||'');
	getOData(str,"busiProductCategory/find/by/cnd",{fn:function(oData){
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
	obj.bspcyTop = $(".bspcyTop").val();
	obj.bspcyName = $(".bspcyName").val();
}
