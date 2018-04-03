// JavaScript Document
$(function () {
	initAdd();
	initShareGrade();
	initBusiTagList();
	//提交
	$(".submit").on("click",function(){
		checkAdd();
	});
	
	var upImg = upImgItem.init('input_filt');
    $('.input_filt').on('change',function() {
        upImg.show(this,
            {url: urlfile + "coreAttachment/stream/upload", width: $(this).parent().attr('data-width'), height: $(this).parent().attr('data-height'), 
            param:{cratmType: $(this).parent().attr('data-cratmType'),cratmDir:$(this).parent().attr('data-cratmDir')}},
            function(returnImagData, ajaxResult, widget) {//图片数据， 上传图片后返回的ajax数据
            	if(ajaxResult.success) {         		
            		widget.attr('data-uuid',ajaxResult.data);
            	}else {
            		alert(ajaxResult.errMsg);
            	}          	
            },function(widget){
            	var str = "cratmUuid=" + widget.attr('data-uuid');;
            	getOData(str,"coreAttachment/delete",{
            		fn:function(oData){
            			widget.attr('data-uuid', "");
            		},
            		fnerr:function(oData){
            			alert(oData.errMsg);
            		}
            	});
            }
        );
    });
	
	$(".set .btn").click(function(){
        $(".set .btn").eq($(this).index()).addClass("select").siblings().removeClass('select');
		$(".plate").hide().eq($(this).index()).show();
    });
	
	//下拉选择框
	$(".bsprtCategory").unbind().on('click',function(){
		var thisinput=$(this);
		var thisul=$(this).parent().find("#selectTree");
		if(thisul.css("display")=="none"){
			thisul.fadeIn("100");
			thisul.hover(function(){},function(){
				thisul.fadeOut("100");
			});
		}
		else{
			thisul.fadeOut("fast");
		}
	});
	
	$('.shop div').on("click",function(){
		$('.shop .selected').removeClass('selected');
		$(this).addClass('selected');
		var selectType = $(this).attr('data-type');
		if('productDescribe'==selectType) {
			$('#productDescribe').show();
			$('#productParam').hide();
			$('#shareGrade').hide();
		}
		if('productParam'==selectType) {
			$('#productDescribe').hide();
			$('#productParam').show();
			$('#shareGrade').hide();
		}
		
		if('shareGrade'==selectType) {
			$('#productDescribe').hide();
			$('#shareGrade').show();
			$('#productParam').hide();
		}
		
	});
	
	$('#paramAdd').on("click",function(){
		var productFlag = $('#productFlag').val();
		var productVal = $('#productVal').val();
		if(productFlag.trim()=="") {
			alert('规格属性名称不能干为空');
			return;
		}
		if(productVal.trim()=="") {
			alert('规格属性值不能干为空');
			return;
		}
		var addHtml = 	'<div class="item standardContent">'+
							'<div class="flag">'+productFlag+'</div>'+
							'<div class="val">'+productVal+'</div>'+
							'<div class="deleteBtn">删除</div>';
						'</div>';
		$('#productParam').append(addHtml);
		$('#productFlag').val('');
		$('#productVal').val('');
		
		$('#productParam .deleteBtn').off("click").on("click",function(){
			$(this).parent().remove();
		});
	});
	
	//下拉选择框
	$(".select-item").unbind().on('click',function(){
		var thisinput=$(this);
		var thisul=$(this).parent().find("ul");
		if(thisul.css("display")=="none"){
			if(thisul.height()>150){thisul.css({height:"150"+"px","overflow-y":"scroll" });};
			thisul.fadeIn("100");
			thisul.hover(function(){},function(){thisul.fadeOut("100");});
			thisul.find(".searchit").focus();
			thisul.find('.searchit').on('keyup',function(){
				var searchText=$(this).val();
				thisul.find("li").each(function(){
					if($(this).text().indexOf(searchText)>-1){
						$(this).show();
					}
					else{
						$(this).hide();
					}
				});
			});
			thisul.find("li").unbind().click(function(){
				var ids = "";
				var str_val = "";
				if($(this).find('input').is(':checked')){
					$(this).find("input").attr("checked",'');
					$(this).find("input").removeAttr("checked");
				}
				else{
					$(this).find("input").attr("checked",'checked');
				}
				$(this).parent().find(":checkbox").each(function(i){
				    if($(this).attr("checked")) {
				    	ids += $(this).parent("li").attr("data-id")+"|";
						str_val += $(this).parent("li").text()+"|";
				   }
				});
				thisinput.val(str_val);
				
				thisinput.attr("data-ids",ids);
				thisinput.css('color','#000');
				//thisul.fadeOut("100");
			}).hover(function(){$(this).addClass("select-hover");},		
			function(){$(this).removeClass("select-hover");});
		}
		else{
			thisul.fadeOut("fast");
		}
	});
	
	
//	initCheckEvent();
	
	initAttrList();
});

function initBusiTagList() {
	
	getOData(null,"busiTagSet/find/all",{
		fn:function(oData){
			//console.log(oData);
			var strhtml_selectUl = '';
//			strhtml_selectUl += '<input type="text" style="width:400px;" class="searchit"></input>';
			var arrData = oData.data;
			var ln = arrData.length;
			for(var i=0;i<ln;i++){
				strhtml_selectUl += '<li data-id="'+arrData[i].bststUuid+'"><input type="checkbox" name="checkbox"/>'+arrData[i].bststName+'</li>';
			}
			$('.bsprtProductTag').find('.select-ul').html(strhtml_selectUl);
		},
		fnerr:function(oData){
			alert(oData.errMsg);		
		}
	});

	//获取热点图标列表
	getOData(null,"busiHotSpot/find/all",{
		fn:function(oData){
			//console.log(oData);
			var hot_selectUl = '';
//			strhtml_selectUl += '<input type="text" style="width:400px;" class="searchit"></input>';
			var arrHotData = oData.data;
			var ln = arrHotData.length;
			for(var i=0;i<ln;i++){
				//hot_selectUl += '<li data-id="'+arrHotData[i].bshstUuid+'" data-icon="'+arrHotData[i].bshstIcon+'" class="hot"><input type="checkbox" name="hot"/>'+arrHotData[i].bshstName+'</li>';
				hot_selectUl += '<input style="margin-left: 15px;" type="radio" name="hot" data-uuid="'+arrHotData[i].bshstUuid+'" data-icon="'+arrHotData[i].bshstIcon+'" value="'+arrHotData[i].bshstName+'"/>'+arrHotData[i].bshstName;

			}
			$('.bshstHot').html(hot_selectUl);
		},
		fnerr:function(oData){
			alert(oData.errMsg);		
		}
	});
}

function initShareGrade() {

	getOData(null,"coreGrade/find/all",{
		fn:function(oData){
			//console.log(oData);
			var shareGradeHtml = "";
			for(var i=0; i<oData.data.length; i++) {
				shareGradeHtml += 	'<div class="item" data-gradeUuid="'+oData.data[i].crgdeUuid+'">'+
										'<span style="float: left">分享等级名称：'+oData.data[i].crgdeName+'</span>'+
										'<input class="integral" type="text" placeholder="请输入分享获得积分数"/>'+
										'<input class="money" type="text" placeholder="请输入分享并确认收货获得单个返现收益"/>'+
									'</div>';
			}
			$('#shareGrade').html(shareGradeHtml);
		},
		fnerr:function(oData){
			alert(oData.errMsg);		
		}
	});
}

function initCheckEvent() {
	$('#typeCheck .much input').off("click").on("click", function() {
		if(this.checked) {
//            addCheckAttr($(this).attr('data-attrUuid'), $(this).attr('data-attrName'));
            var flag = true;
            var checkEl = this;
            while(flag) {
            	checkEl =$(checkEl).prev().prev();
            	if(!checkEl.is('input[type="checkbox"]')) {
            		flag = false;
            		addCheckAttr($(this).attr('data-attrUuid'), $(this).attr('data-attrName'), null);
            	}
            	
            	if(checkEl.is('input[type="checkbox"]')&&checkEl[0].checked) {
            		flag = false;
            		addCheckAttr($(this).attr('data-attrUuid'), $(this).attr('data-attrName'), checkEl.attr('data-attrUuid'));
            	}
            }
            
	    } else {
	    	removeCheckAttr($(this).attr('data-attrUuid'));
	    }
	});

}

function combination(childsz, totalsz, count, jiaobiao, cishu) {
	var h = 0;
	for (var x = 0; x < cishu; x++) {
		for (var i = 0; i < childsz.length; i++) {
			for (var j = 0; j < count; j++) {
				totalsz[h+j][jiaobiao] = childsz[i];
			}
			h=h+count;
		}
	}	
	return totalsz;
}

var attrItems;
function initValueCheckEvent() {
	
	$('.categoryValue').off("click").on("click", function() {
		 attrItems = [];
		 
		 for(var i=0; i<$('.attrItem').length; i++) {
			 attrItems[i] = [];
			 $('.attrItem').eq(i).find('.categoryValue').each(function() {  
		            if (this.checked) {  
		            	attrItems[i].push({name:$(this).attr('data-name'), uuid: $(this).attr('data-uuid')});
		            }  
		        });
		 }

		 var result = [];
		 var total = 1;
		 
		 var tempAttrItems = [];
		 var attrItemName = [];
		 for(var i=0; i<attrItems.length; i++) {
			 if(attrItems[i].length!=0) {
				 tempAttrItems.push(attrItems[i]);
				 attrItemName.push($('.attrItem').eq(i).find('.attrValName').html());
			 }
		 }

		 for(var i=0; i<tempAttrItems.length; i++) {
			 if(tempAttrItems[i].length!=0) {
				 total = total*tempAttrItems[i].length;
			 }
		 }
		 for(var i=0; i<total; i++){
			 result[i] = [];
		 }
		 var cishu = 1;
		 itemCount = total;
		 for(var i=0; i<tempAttrItems.length; i++) {
			 itemCount = itemCount/tempAttrItems[i].length;
			 result = combination(tempAttrItems[i], result, itemCount, i, cishu);
			 cishu = cishu*tempAttrItems[i].length;
		 }
		 
//		 console.log(result);
		 
		 createTable(attrItemName, result);
		 
	});
}

function createTable(attrItemName, resultArray) {
	
	var tableHtml = '<div class="tr">';
	
	for(var i=0; i< attrItemName.length; i++) {
		tableHtml += '<div class="td">'+attrItemName[i]+'</div>';
	}
	tableHtml += '<div class="td">库存</div><div class="td">价格</div></div>';
	for(var i= 0; i< resultArray.length; i++) {
		tableHtml += '<div class="tr contentTr">';
		for(var j=0; j<resultArray[i].length; j++) {
			tableHtml += '<div class="td uuidTd" data-uuid="'+resultArray[i][j].uuid+'">'+resultArray[i][j].name+'</div>';
		}
		
		tableHtml += '<input type="number" class="td store" /><input type="number" class="td price"/></div>';
	}
	
	$('#product_table').html(tableHtml);

}

var attruuid = [];
function addCheckAttr(uuid, name, prevUuid) {
	var attrItem = '<div id="attr'+uuid+'" data-uuid="'+uuid+'" data-name="'+name+'" class="attrItem">'
						+'<div class="add"><span class="attrValName">'+name+'</span><input type="text" name="" placeholder="请输入要添加的属性"><input type="button" name="" value="添加" onclick="addCategoryValue(\''+uuid+'\')"></div>'
						+'<div id="attrContent'+uuid+'" class="color">'
						+'</div>'
					+'</div>';
	if(!prevUuid){
		$('#attrs').after(attrItem);
	}else {
		$('#attr'+prevUuid).after(attrItem);
	}
	
	attruuid[attruuid.length] = uuid;//将选中的属性UUID 存入数组attruuid
	getCategoryValueList(uuid);
}

function removeCheckAttr(uuid) {
	$('#attr'+uuid).remove();
}

function addCategoryValue(attrUuid) {
	var bscveValue = $('#attr'+attrUuid).find('input').val();
	if(!bscveValue) {
		alert('属性值不能为空');
		return;
	}
	var str = 'bscveCategory='+encodeURIComponent(getQueryString("typeUuid"))+'&bscveAttr='+encodeURIComponent(attrUuid) + '&bscveValue='+encodeURIComponent(bscveValue);
	getOData(str,"busiCategoryAttribute/add/busiCategoryValue",{
		fn:function(oData){
			var attrContentItem = '<input class="categoryValue" data-name="'+bscveValue+'" data-uuid="'+oData.data+'" type="checkbox" name=""><span>'+bscveValue+'</span>';
			$('#attrContent'+attrUuid).append(attrContentItem);
			$('#attr'+attrUuid).find('input[type="text"]').val('');
			initValueCheckEvent();
		},
		fnerr:function(oData){
			alert(oData.errMsg);
		}
	});
}

function getCategoryValueList(uuid) {
	var str = 'bscveAttr='+encodeURIComponent(uuid);
	getOData(str,"busiCategoryAttribute/attr/value/find/all",{
		fn:function(oData){
			//console.log(oData);
			var attrContent = "";
			for(var i=0; i<oData.data.length; i++) {
				attrContent += '<input class="categoryValue" data-name="'+oData.data[i].bscveValue+'" data-uuid="'+oData.data[i].bscveUuid+'" type="checkbox" name=""><span>'+oData.data[i].bscveValue+'</span>';
			}
			$('#attrContent'+uuid).html(attrContent);
			initValueCheckEvent();
		},
		fnerr:function(oData){
			alert(oData.errMsg);
		}
	});
}


function initAttrList() {
	var str = 'bscaeCategory='+encodeURIComponent(getQueryString("typeUuid"));
	getOData(str,"busiCategoryAttribute/attr/find/all",{
		fn:function(oData){
			
			var attrHtml = "";
			for(var i=0; i<oData.data.length; i++) {
				attrHtml += '<input type="checkbox" name="attribute" data-attrUuid="'+oData.data[i].bscaeUuid+'"  data-attrName="'+oData.data[i].bscaeName+'"><span>'+oData.data[i].bscaeName+'</span>';
			}
			$('#typeCheck .much').html(attrHtml);
			initCheckEvent();
		},
		fnerr:function(oData){
			alert(oData.errMsg);
		}
	});
}

function addType(){
	var typeCheckAddVal = $('#typeCheckAddVal').val();
	if(typeCheckAddVal.trim()=="") {
		alert('属性类别添加不能为空');
		return;
	}
	var str = 'bscaeCategory='+encodeURIComponent(getQueryString("typeUuid"))+'&bscaeName='+encodeURIComponent(typeCheckAddVal);
	getOData(str,"busiCategoryAttribute/add/busiCategoryAttribute",{
		fn:function(oData){
			//console.log(oData);
			var addTypeHtml='<input type="checkbox" name="" data-attrUuid="'+oData.data+'"  data-attrName="'+typeCheckAddVal+'"><span>'+typeCheckAddVal+'</span>';
			$('#typeCheck .much').append(addTypeHtml);
			initCheckEvent();
		},
		fnerr:function(oData){
		}
	});
	
	$('#typeCheckAddVal').val('');
}

//初始化
function initAdd(){
	$(".bsprtName").val("");
	$(".bsprtCategory").html(getQueryString("typeName"));
	$(".bsprtDesc").val("");
	$(".bsprtTel").val("");
//	$(".bsprtView").val("");
	$(".bsprtSalePrice").val("");
	$(".bsprtOrignalPrice").val("");
	$(".bsprtSaleCount").val("");
	$(".bsprtAddress").val("");
	$(".bsprtIsVirtual").val("");
	$(".bsprtExpress").val("");
	$(".bsprtSharePic").attr("data-uuid",'');
	$(".bsprtStorePic").attr("data-uuid",'');
	$(".bsprtImageUrl1").attr("data-uuid",'');
	$(".bsprtImageUrl2").attr("data-uuid",'');
	$(".bsprtImageUrl3").attr("data-uuid",'');
	$(".bsprtImageUrl4").attr("data-uuid",'');
	$(".bsprtImageUrl5").attr("data-uuid",'');
	$(".bsprtAttrJson").val("");
	$(".bsprtValueJson").val("");
	$(".bsprtProductTag").find("input").val("");
	$(".bsprtProductTag").find(".select-item").attr("data-ids","");
	$(".bsprtRuleParam").val("");
	$(".bsprtGradeShare").val("");
	$(".bsprtCdate").val("");
	$(".bsprtUdate").val("");
	CKEDITOR.replace( 'bsprtView' , { height: '240px', 
		width: '600px',
	    margin: '0px auto',
		toolbar: [['Source','-','Preview'],
		          ['Cut','Copy','Paste','PasteText','PasteFromWord'],
		          ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
		          ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
		          ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
		           ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
		           ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		           ['Link','Unlink'],
		          ['Image','Table','HorizontalRule','Smiley'],
		           ['Styles','Format','Font','FontSize'],
		           ['TextColor','BGColor']],
		extraPlugins: 'codesnippet',
		uiColor: '#f3f3f3'
	} );
}
//检查提交
function checkAdd(){
	if($.trim($(".bsprtName").val()) == ""){
		alert("商品名称不能为空，请填写完再提交！");
		$(".bsprtName").focus();
		return false;
	}
	if($.trim($(".bsprtTel").val()) == ""){
		alert("客服电话不能为空，请填写完再提交！");
		$(".bsprtTel").focus();
		return false;
	}
	if($.trim(CKEDITOR.instances.bsprtView.getData()) == ""){
		alert("详情不能为空，请填写完再提交！");
		$(".bsprtView").focus();
		return false;
	}
	if($.trim($(".bsprtSalePrice").val()) == ""){
		alert("售价范围不能为空，请填写完再提交！");
		$(".bsprtSalePrice").focus();
		return false;
	}
	if($.trim($(".bsprtOrignalPrice").val()) == ""){
		alert("原价范围不能为空，请填写完再提交！");
		$(".bsprtOrignalPrice").focus();
		return false;
	}
	if($.trim($(".bsprtSaleCount").val()) == ""){
		alert("销售数量不能为空，请填写完再提交！");
		$(".bsprtSaleCount").focus();
		return false;
	}
	if($.trim($(".bsprtAddress").val()) == ""){
		alert("地区不能为空，请填写完再提交！");
		$(".bsprtAddress").focus();
		return false;
	}
	//积分判断
	var shareGradeItems = $('#shareGrade .item');
	var bsprtGradeShare = {};
	for(var i=0; i<shareGradeItems.length; i++) {
		var integral = $(shareGradeItems[i]).find('.integral').val();
		var money = $(shareGradeItems[i]).find('.money').val();
		if(integral==null||integral=="") {
			//parent.layer.close(msgObject);
			alert('分享等级积分必填');
			return;
		}
		if(money==null||money=="") {
			//parent.layer.close(msgObject);
			alert('分享等级收益必填');
			return;
		}
	}
	//属性判断
	var attrSelectItems = $('#attrSelect .attrItem');
	if( attrSelectItems.length == 0){
		//parent.layer.close(msgObject);
		alert("请选着属性！");
		return;
	}

	var flag = true;
	var product_tableTr = $('#product_table .contentTr');
	for(var x=0; x<product_tableTr.length; x++){
		var store = $(product_tableTr).eq(x).find('.store').val();
		var price = $(product_tableTr).eq(x).find('.price').val();
		if(store == "" && price == ""){
			flag = false;
		}else{
			break;
		}
	}
	if(flag == false){
		alert("表格数据至少要有一条数据");
		return;
	}

	// var product_tableTr = $('#product_table .contentTr');
	// for(var i=0; i<product_tableTr.length; i++) {
	// 	var store = $(product_tableTr).eq(i).find('.store').val();
	// 	var price = $(product_tableTr).eq(i).find('.price').val();
	// 	if(store==null||store=="" || price==null||price=="") {
	// 		//parent.layer.close(msgObject);
	// 		alert("价格和库存都需填写！(可填写为0)");
	// 		return;
	// 	}
	// }

	var r=confirm("是否确认增加？");
	if (r==true){
		var msgObject = parent.layer.msg('处理中，请等待……', {
			icon: 16,
			shade: 0.4,
			time: waitImgTime //（如果不配置，默认是3秒）
		}, function(index){
			//do something
			parent.layer.close(index);
		});
		saveProduct(msgObject);
	}
}
//提交
function saveProduct(msgObject){
	
	var attrSelectItems = $('#attrSelect .attrItem');
	var bsprtAttrJson = [];
	var wxzvalue = [];
	for(var i=0; i<attrSelectItems.length; i++) {
		bsprtAttrJson[i] = {
			uuid: $(attrSelectItems[i]).attr('data-uuid'),
			name: $(attrSelectItems[i]).attr('data-name'),
			data:attrItems[i]
		}
	}
	
	var product_tableTr = $('#product_table .contentTr');
	var bsprtValueJson = {};
	for(var i=0; i<product_tableTr.length; i++) {
		var bsprtWxzvalue = {};
		var product_tableTd = $(product_tableTr).eq(i).find('.uuidTd');
		var keyName = "attrVal_";
		for(var j=0; j<product_tableTd.length; j++) {
			keyName += $(product_tableTd[j]).attr('data-uuid')+"|";
		}
		//////ADD////
		var store = $(product_tableTr).eq(i).find('.store').val();
		var price = $(product_tableTr).eq(i).find('.price').val();

		if( store == "" && price == ""){
			store = 0;
			price = 0;
			for( var z = 0; z<attruuid.length; z++){		
				bsprtWxzvalue["attr_"+attruuid[z]] =  $(product_tableTd[z]).attr('data-uuid');				
			}
			wxzvalue[wxzvalue.length] = bsprtWxzvalue;	
			//console.log(JSON.stringify(wxzvalue));	
		}

		bsprtValueJson[keyName] = {
				store: store,
				price: price
		};
		/////ADD////
		// bsprtValueJson[keyName] = {
		// 		store: $(product_tableTr).eq(i).find('.store').val(),
		// 		price: $(product_tableTr).eq(i).find('.price').val()
		// };
	}

	//获取热点
	var radio=document.getElementsByName("hot");
	var bsprtHotSpot;
	var bsprtHotspotName;
	var bsprtHotspotIcon;
		for(var i=0;i<radio.length;i++){
	        if(radio[i].checked==true) {
				bsprtHotSpot = $(radio[i]).attr("data-uuid");
				bsprtHotspotName =radio[i].value;
				bsprtHotspotIcon =  $(radio[i]).attr("data-icon");
                break;
		       }else{
			       	bsprtHotSpot = "";
					bsprtHotspotName ="";
					bsprtHotspotIcon = "";
		       }
		}


	var standardItems = $('#productParam .standardContent');
	var bsprtRuleParam = [];
	for(var i=0; i<standardItems.length; i++) {
		bsprtRuleParam[i] = {
				flag: $(standardItems[i]).find('.flag').html(),
				val: $(standardItems[i]).find('.val').html()
		}
	}
	
	var shareGradeItems = $('#shareGrade .item');
	var bsprtGradeShare = {};
	for(var i=0; i<shareGradeItems.length; i++) {
		var keyName = "grade_" + $(shareGradeItems[i]).attr('data-gradeuuid');
		var integral = $(shareGradeItems[i]).find('.integral').val();
		var money = $(shareGradeItems[i]).find('.money').val();
		
		bsprtGradeShare[keyName] = {
			integral: integral,
			money: money,
		}
	}
	
	var bsprtName = $(".bsprtName").val();
	var bsprtCategory = getQueryString("typeUuid");
	var bsprtDesc = $(".bsprtDesc").val();
	var bsprtTel = $(".bsprtTel").val();
	var bsprtView = CKEDITOR.instances.bsprtView.getData();//$(".bsprtView").val();
	var bsprtSalePrice = $(".bsprtSalePrice").val();
	var bsprtOrignalPrice = $(".bsprtOrignalPrice").val();
	var bsprtSaleCount = $(".bsprtSaleCount").val();
	var bsprtAddress = $(".bsprtAddress").val();


	var bsprtIsVirtual = $(".bsprtIsVirtual").val();
	var bsprtExpress = $(".bsprtExpress").val();
	var bsprtSharePic = $(".bsprtSharePic").attr('data-uuid');
	var bsprtStorePic = $(".bsprtStorePic").attr('data-uuid');
	var bsprtImageUrl1 = $(".bsprtImageUrl1").attr('data-uuid');
	var bsprtImageUrl2 = $(".bsprtImageUrl2").attr('data-uuid');
	var bsprtImageUrl3 = $(".bsprtImageUrl3").attr('data-uuid');
	var bsprtImageUrl4 = $(".bsprtImageUrl4").attr('data-uuid');
	var bsprtImageUrl5 = $(".bsprtImageUrl5").attr('data-uuid');
	var bsprtProductTag = {
			tagUuids: $(".bsprtProductTag").find(".select-item").attr("data-ids"),
			tagNames: $(".select-item").val()
	};

	var bsprtHotSpot;
	var bsprtHotspotName;
	var bsprtHotspotIcon;
	$("input[name='hot']:checkbox").each( function() {
		if(this.checked) {
			bsprtHotSpot = $(this).parent('.hot').attr("data-id");
			bsprtHotspotName = $('.bshstHot').children('.select-item').val()
			bsprtHotspotIcon =  $(this).parent('.hot').attr("data-icon");
		}	
	})
	// var bsprtHotSpot = $(".bshstHot").find(".select-item").attr("data-ids");
	// var bsprtHotspotName = $('.bshstHot').children('.select-item').val()
	// var bsprtHotspotIcon = $(".bshstHot").find(".select-item").attr("data-ids");	
	
	//parent.layer.close(msgObject);
	
	var str = 'bsprtName='+encodeURIComponent(bsprtName)+'&bsprtCategory='+
	encodeURIComponent(bsprtCategory)+'&bsprtDesc='+
	encodeURIComponent(bsprtDesc)+'&bsprtTel='+
	encodeURIComponent(bsprtTel)+'&bsprtView='+
	encodeURIComponent(bsprtView)+'&bsprtSalePrice='+
	encodeURIComponent(bsprtSalePrice)+'&bsprtOrignalPrice='+
	encodeURIComponent(bsprtOrignalPrice)+'&bsprtSaleCount='+
	encodeURIComponent(bsprtSaleCount)+'&bsprtAddress='+
	encodeURIComponent(bsprtAddress)+'&bsprtIsVirtual='+
	encodeURIComponent(bsprtIsVirtual)+'&bsprtExpress='+
	encodeURIComponent(bsprtExpress)+'&bsprtSharePic='+
	encodeURIComponent(bsprtSharePic)+'&bsprtStorePic='+
	encodeURIComponent(bsprtStorePic)+'&bsprtImageUrl1='+
	encodeURIComponent(bsprtImageUrl1)+'&bsprtImageUrl2='+
	encodeURIComponent(bsprtImageUrl2)+'&bsprtImageUrl3='+
	encodeURIComponent(bsprtImageUrl3)+'&bsprtImageUrl4='+
	encodeURIComponent(bsprtImageUrl4)+'&bsprtImageUrl5='+
	encodeURIComponent(bsprtImageUrl5)+'&bsprtAttrJson='+
	encodeURIComponent(JSON.stringify(bsprtAttrJson))+'&bsprtValueJson='+
	encodeURIComponent(JSON.stringify(bsprtValueJson))+'&bsprtProductTag='+
	encodeURIComponent(JSON.stringify(bsprtProductTag))+'&bsprtRuleParam='+
	encodeURIComponent(JSON.stringify(bsprtRuleParam))+'&bsprtGradeShare='+
	encodeURIComponent(JSON.stringify(bsprtGradeShare))+'&bsprtWxzvalueJson='+
	encodeURIComponent(JSON.stringify(wxzvalue))+'&bsprtHotSpot='+
	encodeURIComponent(bsprtHotSpot)+'&bsprtHotspotName='+
	encodeURIComponent(bsprtHotspotName)+'&bsprtHotspotIcon='+
	encodeURIComponent(bsprtHotspotIcon);
	getOData(str,"busiProduct/add/busiProduct",{
		fn:function(oData){
			parent.layer.close(msgObject);
			window.parent.refreshList();
			alert("增加成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}
