// JavaScript Document
var selectTree;
$(function () {
	selectTree = null;
	$(".cur_postion").html("当前位置： [ "+sessionStorage.getItem("pmenuname")+" ] - [ "+sessionStorage.getItem("cmenuname")+" ]");

	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>商品名称:</span>'
		+'<input type="text" class="inputStyle_condition bsprtName"/>'
		+'</div>';
	$(".searchContent").html(strhtml_searchContent);
	//是否显示查询条件
	showSearchBox(true);	

	//详情
	$("body").delegate('.detailit','click', function(){
		var width = $(document.body).width();
		var height = $('.rightContent').height() + 50 + 44;
		layer.open({
			type: 2,
			title: '详情',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [width+'px' , height+'px'],
			content: '../product/busiproduct_detail.html?id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
		});
	});
	//新增
	$('.addit').on('click', function(){
		var width = $(document.body).width();
		var height = $('.rightContent').height() + 50 + 44;
		layer.open({
			type: 2,
			title: '新增',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [width+'px' , height+'px'],
			content: '../product/busiproduct_add.html?typeUuid='+ selectTree.bspcyUuid+'&typeName='+selectTree.bspcyTopName+'&timestamp='+(new Date()).valueOf()
		});
	});
	//修改
	$("body").delegate('.modifyit','click', function(){
		var width = $(document.body).width();
		var height = $('.rightContent').height() + 50 + 44;
		layer.open({
			type: 2,
			title: '修改',
			scrollbar: false,
			maxmin: true,
			shadeClose: false, //点击遮罩关闭层
			area : [width+'px' , height+'px'],
			content: '../product/busiproduct_modify.html?typeUuid='+ selectTree.bspcyUuid+'&id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
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
				var str = 'bsprtUuids='+encodeURIComponent(ids);
				getOData(str,"busiProduct/delete/batch",{fn:function(oData){
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

	//上架
	$("body").delegate(".putaway","click",function(){
		var r=confirm("是否确认上架？");
		if (r==true){
			var str = 'bsprtUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"busiProduct/enable",{fn:function(oData){
					refreshList();
				}}
			);
		}
	});
	
	//下架
	$("body").delegate(".soldOut","click",function(){
		var r=confirm("是否确认下架？");
		if (r==true){
			var str = 'bsprtUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"busiProduct/shelf",{fn:function(oData){
					refreshList();
				}}
			);
		}
	});

	//删除
	$("body").delegate(".delit","click",function(){
		var r=confirm("是否确认删除？");
		if (r==true){
			var str = 'bsprtUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"busiProduct/delete",{fn:function(oData){
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
		$(".bsprtName").val('');
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
	
	getTypeTree();

});

function getTypeTree() {
	getOData(null,"busiProductCategory/find/all/by/product",{fn:function(oData){
		$.fn.zTree.init($("#selectTree"), setting, oData.data);		
	}});
}

var setting = {  
    data: {  
        simpleData: {  
            enable: true,//是否使用简单数据模式，默认为false,如果设置为 true，请务必设置 setting.data.simpleData 内的其他参数: idKey / pIdKey / rootPId，并且让数据满足父子关系。  
            idKey:"bspcyUuid",//节点数据中保存唯一标识的属性名称，默认值id，开启简单数据模式时有效  
            pIdKey:"bspcyTop",//节点数据中保存其父节点唯一标识的属性名称。默认值pId，开启简单数据模式时有效  
            rootPId:0//用于修正根节点父节点数据，即 pIdKey 指定的属性值,默认值为null，开启简单数据模式时有效  
        },
        key: {
			name: "bspcyName"
		}
    },

	callback: {
		onClick: treeClick
	}
}; 

function treeClick(e, treeId, treeNode) {
	if(treeNode.level==0) {
		alert('请具体到子节点');
		return;
	}
	
	selectTree = treeNode;
	$('.rightContent').show();
	$('.rightTop').show();
	
	var obj = {};//查询条件对象
	searchContent(obj);
	showList(obj,1);
}

function showList(obj,pagenum){
	var aData =[{name:"<input type='checkbox' name='checkboxAll' value='checkbox' />",percent:"5"},
				{name:"商品名称",percent:"15"},
				{name:"分类",percent:"10"},
				{name:"客服电话",percent:"10"},
				{name:"售价范围",percent:"10"},
				{name:"销售数量",percent:"10"},
				{name:"状态",percent:"10"},
				{name:"快递费用",percent:"10"},
				{name:"操作",percent:"20"}];
	setTableHead(aData);
	var str = '';
	if(obj.bsprtName==""){
		str = 'pageNum='+pagenum+'&pageSize=10'+'&bsprtCategory='+selectTree.bspcyUuid;
	}else{
		str = 'pageNum='+pagenum+'&pageSize=10&bsprtName='+encodeURIComponent(obj.bsprtName)+'&bsprtCategory='+selectTree.bspcyUuid;
	}
	getOData(str,"busiProduct/back/find/by/cnd",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			var statusName = "";
			var statusOperate = "";
			if(arrData[i].bsprtSaleStatus == 1) {
				statusName = "销售中";
				statusOperate = '<a  class="p-edit soldOut" data-id="'+arrData[i].bsprtUuid+'">下架</a>';
			}
			if(arrData[i].bsprtSaleStatus == 2) {
				statusName = "已下架";
				statusOperate = '<a  class="p-edit putaway" data-id="'+arrData[i].bsprtUuid+'">上架</a>';
			}
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].bsprtUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].bsprtName || "")+'</td>'
				+'<td>'+(arrData[i].bsprtCategoryName || "")+'</td>'
				+'<td>'+(arrData[i].bsprtTel || "")+'</td>'
				+'<td>'+(arrData[i].bsprtSalePrice || "")+'</td>'
				+'<td>'+(arrData[i].bsprtSaleCount || "")+'</td>'
				+'<td>'+statusName+'</td>'
				+'<td>'+(arrData[i].bsprtExpress || "")+'</td>'
				+'<td>'
				+'<a  class="p-edit detailit" data-id="'+arrData[i].bsprtUuid+'">查看</a>'
				+'<a  class="p-edit modifyit" data-id="'+arrData[i].bsprtUuid+'">修改</a>'
				+statusOperate
				+'<a  class="p-edit delit" data-id="'+arrData[i].bsprtUuid+'">删除</a>'
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
	if(obj.bsprtName==""){
		str = 'pageNum='+pagenum+'&pageSize=10'+'&bsprtCategory='+selectTree.bspcyUuid;
	}else{
		str = 'pageNum='+pagenum+'&pageSize=10&bsprtName='+encodeURIComponent(obj.bsprtName)+'&bsprtCategory='+selectTree.bspcyUuid;
	}
	getOData(str,"busiProduct/back/find/by/cnd",{fn:function(oData){
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
	obj.bsprtName = $(".bsprtName").val();
}
