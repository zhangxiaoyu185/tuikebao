// JavaScript Document
$(function () {
	$(".cur_postion").html("当前位置： [ "+sessionStorage.getItem("pmenuname")+" ] - [ "+sessionStorage.getItem("cmenuname")+" ]");

	//查询条件
	var strhtml_searchContent = '<div class="inline-block margin">'
		+'<span>标题:</span>'
		+'<input type="text" class="inputStyle_condition crcseTitle"/>'
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
			content: '../order/busievaluate_detail.html?id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
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
			content: '../order/busievaluate_add.html?timestamp='+(new Date()).valueOf()
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
			content: '../order/busievaluate_modify.html?id='+$(this).attr("data-id")+'&timestamp='+(new Date()).valueOf()
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
				var str = 'bseveUuids='+encodeURIComponent(ids);
				getOData(str,"busiEvaluate/delete/batch",{fn:function(oData){
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
			var str = 'bseveUuid='+encodeURIComponent($(this).attr("data-id"));
			getOData(str,"busiEvaluate/delete/one",{fn:function(oData){
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
		/*var imgurl = $(this).attr("data-imageurl");
		if(imgurl != "#"){
			var that = this;
			$("<img/>").attr("src", imgurl).load(function() {

				var width = 150;
				var realWidth = this.width;
				var realHeight = this.height;
				var height = realHeight/realWidth*width;
				if(realWidth>=width){
					var bannerimg = '<img id="bannerimg" src="'+imgurl+'" width="'+width+'px" height="'+height+'px" />';
				}
				else{//如果小于浏览器的宽度按照原尺寸显示
					$("#bannerimg").css("width",realWidth+'px').css("height",realHeight+'px');
					var bannerimg = '<img id="bannerimg" src="'+imgurl+'" width="'+realWidth+'px" height="'+realHeight+'px" />';
				}

				layer.tips(bannerimg, that ,{
					tips: 3
				});
			});
		}*/
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
		$(".crcseTitle").val('');
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
				{name:"订单明细标识",percent:"10"},
				{name:"商品标识",percent:"10"},
				{name:"选择商品属性组合名称（颜色：绿色尺寸：L）",percent:"10"},
				{name:"评价人",percent:"10"},
				{name:"评价人姓名",percent:"10"},
				{name:"评价人头像",percent:"10"},
				{name:"评价内容",percent:"10"},
				{name:"评价等级:1好评2中评3差评",percent:"10"},
				{name:"状态:1启动0禁用",percent:"10"},
				{name:"评价图1",percent:"10"},
				{name:"评价图2",percent:"10"},
				{name:"评价图3",percent:"10"},
				{name:"评价图4",percent:"10"},
				{name:"评价图5",percent:"10"},
				{name:"创建时间",percent:"10"},
				{name:"操作",percent:"10"}];
	setTableHead(aData);
	var str = '';
	if(obj.crcseTitle==""){
		str = 'pageNum='+pagenum+'&pageSize=10';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=10&crcseTitle='+encodeURIComponent(obj.crcseTitle);
	}
	getOData(str,"busiEvaluate/find/by/cnd",{fn:function(oData){
		var strhtml_list = "";
		var arrData = oData.data.result;
		var ln = arrData.length;
		for(var i=0;i<ln;i++){
			strhtml_list += '<tr class="trHighLight">'
				+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].bseveUuid+'"/>'+'</td>'
				+'<td>'+(arrData[i].bseveOrderItem || "")+'</td>'
				+'<td>'+(arrData[i].bseveProduct || "")+'</td>'
				+'<td>'+(arrData[i].bseveAttrName || "")+'</td>'
				+'<td>'+(arrData[i].bseveUser || "")+'</td>'
				+'<td>'+(arrData[i].bseveName || "")+'</td>'
				+'<td>'+(arrData[i].bseveHead || "")+'</td>'
				+'<td>'+(arrData[i].bseveContent || "")+'</td>'
				+'<td>'+(arrData[i].bseveGrade || "")+'</td>'
				+'<td>'+(arrData[i].bseveStatus || "")+'</td>'
				+'<td>'+(arrData[i].bsevePic1 || "")+'</td>'
				+'<td>'+(arrData[i].bsevePic2 || "")+'</td>'
				+'<td>'+(arrData[i].bsevePic3 || "")+'</td>'
				+'<td>'+(arrData[i].bsevePic4 || "")+'</td>'
				+'<td>'+(arrData[i].bsevePic5 || "")+'</td>'
				+'<td>'+(arrData[i].bseveCdate || "")+'</td>'
				+'<td>'
				+'<a  class="p-edit detailit" data-id="'+arrData[i].bseveUuid+'">查看</a>'
				+'<a  class="p-edit modifyit" data-id="'+arrData[i].bseveUuid+'">修改</a>'
				+'<a  class="p-edit delit" data-id="'+arrData[i].bseveUuid+'">删除</a>'
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
	if(obj.crcseTitle==""){
		str = 'pageNum='+pagenum+'&pageSize=10';
	}else{
		str = 'pageNum='+pagenum+'&pageSize=10&crcseTitle='+encodeURIComponent(obj.crcseTitle);
	}
	getOData(str,"busiEvaluate/find/by/cnd",{fn:function(oData){
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
	obj.crcseTitle = $(".crcseTitle").val();
}
