// JavaScript Document
$(function () {
	initModify();
	initShareGrade();
	initBusiTagList();
	//提交
	$(".submit").on("click",function(){
		checkModify();
	});
	
	$(".set .btn").click(function(){
        $(".set .btn").eq($(this).index()).addClass("select").siblings().removeClass('select');
		$(".plate").hide().eq($(this).index()).show();
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

	initAttrList();
	
});

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

function initCheckEventC() {
	$("input[name='fruit']:checkbox").each( function() {
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

function removeCheckAttr(uuid) {
	$('#attr'+uuid).remove();
}


function initAttrList() {
	var str = 'bscaeCategory='+encodeURIComponent(getQueryString("typeUuid"));
	getOData(str,"busiCategoryAttribute/attr/find/all",{
		fn:function(oData){
			var attrHtml = "";
			for(var i=0; i<oData.data.length; i++) {
				// console.log("//////////////////////////");
				// console.log(oData.data);
				// console.log("//////////////////////////");
				for( var j =0; j<bsprtAttrJson.length; j++){
					if( oData.data[i].bscaeUuid == bsprtAttrJson[j].uuid){
						attrHtml += '<input type="checkbox" checked name="fruit" data-attrUuid="'+oData.data[i].bscaeUuid+'"  data-attrName="'+oData.data[i].bscaeName+'"><span>'+oData.data[i].bscaeName+'</span>';		
					}else{
						attrHtml += '<input type="checkbox" name="" data-attrUuid="'+oData.data[i].bscaeUuid+'"  data-attrName="'+oData.data[i].bscaeName+'"><span>'+oData.data[i].bscaeName+'</span>';
					}
				}
			}
			$('#typeCheck .much').html(attrHtml);
			initCheckEventC();
			initCheckEvent();
		},
		fnerr:function(oData){
			alert(oData.errMsg);
		}
	});
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

var attrItems;
function initValueCheckEventC() {
	
	$("input[name='fruit']:checkbox").each(  function() {
		 checkEvent(true);	 
	});
}

//var attrItems;
function initValueCheckEvent() {
	
	$('.categoryValue').off("click").on("click", function() {	
		 checkEvent(false);	 
	});
}

function checkEvent(flagcheck){
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
	 if(flagcheck){
	 	createTable(attrItemName, result, true);
	 }else{
	 	createTable(attrItemName, result, false);
	 }
		
}


function getCategoryValueList(uuid) {
	var str = 'bscveAttr='+encodeURIComponent(uuid);
	getOData(str,"busiCategoryAttribute/attr/value/find/all",{
		fn:function(oData){
			// console.log("........................................");			
			// console.log(oData);
			// console.log(JSON.parse(bsprtAttrJson));		
			// console.log("........................................");			
			var attrContent = "";
			var flag = true;
			for(var i=0; i<oData.data.length; i++) {
				attrContent += '<input class="categoryValue" data-name="'+oData.data[i].bscveValue+'" data-uuid="'+oData.data[i].bscveUuid+'" type="checkbox" name=""><span>'+oData.data[i].bscveValue+'</span>';				
			}
			// for(var i=0; i<oData.data.length; i++) {
			// 	for( var j=0; j<JSON.parse(bsprtAttrJson).length; j++){					
			// 		for( var a=0; a<JSON.parse(bsprtAttrJson)[j].data.length; a++){
			// 			if( JSON.parse(bsprtAttrJson)[j].data[a].uuid == oData.data[i].bscveUuid){		
			// 				attrContent += '<input class="categoryValue" checked data-name="'+oData.data[i].bscveValue+'" data-uuid="'+oData.data[i].bscveUuid+'" type="checkbox" name=""><span>'+oData.data[i].bscveValue+'</span>';				
			// 				flag = false;
			// 			}
			// 			if( flag ){
			// 				attrContent += '<input class="categoryValue" data-name="'+oData.data[i].bscveValue+'" data-uuid="'+oData.data[i].bscveUuid+'" type="checkbox" name=""><span>'+oData.data[i].bscveValue+'</span>';							
			// 				flag = false;
			// 			}

			// 		}
			// 	}
			// }
			$('#attrContent'+uuid).html(attrContent);	
			check();
			initValueCheckEvent();

			//initValueCheckEventC();
		},
		fnerr:function(oData){
			alert(oData.errMsg);
		}

	});
}

//将选中的  添加checked 属性
function check(){
	$('#attrSelect .attrItem').each(function(i){
		$(this).children('.color').children('input').each(function(j){
		var id = $(this).attr('data-uuid');
			for( var j=0; j<bsprtAttrJson.length; j++){					
				for( var a=0; a<bsprtAttrJson[j].data.length; a++){
					if( bsprtAttrJson[j].data[a].uuid == id){		
					$(this).attr('checked','checked');
					}
				}
			}
		});
	});
	initValueCheckEventC();
	
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


function createTable(attrItemName, resultArray, initFlag) {
	
	var tableHtml = '<div class="tr">';
	
	for(var x=0; x< attrItemName.length; x++) {
		tableHtml += '<div class="td">'+attrItemName[x]+'</div>';
	}
	tableHtml += '<div class="td">库存</div><div class="td">价格</div></div>';
		for(var i= 0; i< resultArray.length; i++) {
			tableHtml += '<div class="tr contentTr">';
			// var keyUuidS = [];
				var keyName = "attrVal_";
				for(var j=0; j<resultArray[i].length; j++) {
					tableHtml += '<div class="td uuidTd" data-uuid="'+resultArray[i][j].uuid+'">'+resultArray[i][j].name+'</div>';
					keyName += resultArray[i][j].uuid+'|';
				}
				if(initFlag){
					if(!bsprtValueJson[keyName]){
						break;
					}
					if(bsprtValueJson[keyName].store == 0 && bsprtValueJson[keyName].price == 0){
						var store = "";
						var price = "";
					}else{
						var store = bsprtValueJson[keyName].store;
						var price = bsprtValueJson[keyName].price;
					}
					tableHtml += '<input class="td store" value="'+store+'" /><input class="td price" value="'+price+'"/></div>';
				}else{
						tableHtml += '<input class="td store" value="" /><input class="td price"/></div>';
				}		
			}	
		$('#product_table').html(tableHtml);
	}


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
				hot_selectUl += '<input style="margin-left: 10px;" type="radio" name="hot" data-uuid="'+arrHotData[i].bshstUuid+'" data-icon="'+arrHotData[i].bshstIcon+'" value="'+arrHotData[i].bshstName+'"/>'+arrHotData[i].bshstName;

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
			shareGradeHtml += 	'<div style="width: 100%; height: 28px; line-height: 28px; margin-top: 5px;" >'+
									'<span style="float: left; width:90px; height:30px; text-align: center; line-height: 30px;">分享等级</span>'+									
									'<label style="float: left; width:160px; height:30px; text-align: center; line-height: 30px;">积分</label>'+
									'<label style="float: left; width:130px; height:30px; text-align: center; line-height: 30px;">收益</label>'+
								'</div>';
			for(var i=0; i<oData.data.length; i++) {
				shareGradeHtml += 	'<div class="item" data-gradeUuid="'+oData.data[i].crgdeUuid+'">'+
										'<span style="float: left">分享等级名称：'+oData.data[i].crgdeName+'</span>'+
										'<input class="integral" type="text" placeholder="请输入分享获得积分数" value="'+bsprtGradeShare["grade_"+oData.data[i].crgdeUuid].integral+'"/>'+
										'<input class="money" type="text" placeholder="请输入分享并确认收货获得单个返现收益" value="'+bsprtGradeShare["grade_"+oData.data[i].crgdeUuid].money+'"/>'+
									    '</div>';  
			}
			$('#shareGrade').html(shareGradeHtml);
		},
		fnerr:function(oData){
			alert(oData.errMsg);		
		}
	});
}

//初始化
function initModify(){
	CKEDITOR.replace( 'bsprtView' , { height: '240px', 
		width: '480px',
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
	getInfo(getQueryString("id"));
}
//获取详情
var bsprtAttrJson = "";
var bsprtGradeShare = "";
var bsprtValueJson = "";
var bsprtRuleParam = "";
function getInfo(id){
	var str = 'bsprtUuid='+encodeURIComponent(id);
	getOData(str,"busiProduct/views/back",{fn:function(oData){
		if(oData.code == 1) {
			//获取选中的属性
			bsprtAttrJson = JSON.parse(oData.data.bsprtAttrJson);
			//获取相对应的等级积分和钱
			bsprtGradeShare = JSON.parse(oData.data.bsprtGradeShare); 
			//获取相对应的库存价格attrVal_
			bsprtValueJson = JSON.parse(oData.data.bsprtValueJson); 
			//获取相对应的标签
			var bsprtProductTag = JSON.parse(oData.data.bsprtProductTag);
			//获取相对应的规格属性
			bsprtRuleParam = JSON.parse(oData.data.bsprtRuleParam);
					
//			 console.log('======================');
//			// console.log( oData);
//			 console.log(bsprtRuleParam);
//			 console.log('======================');
			$(".bsprtCategory").html(oData.data.bsprtCategoryName);
			$(".bsprtName").val(oData.data.bsprtName || "");
			$(".bsprtDesc").val(oData.data.bsprtDesc || "");
			$(".bsprtTel").val(oData.data.bsprtTel || "");
			$(".bsprtSalePrice").val(oData.data.bsprtSalePrice || "");
			$(".bsprtOrignalPrice").val(oData.data.bsprtOrignalPrice || "");
			$(".bsprtSaleCount").val(oData.data.bsprtSaleCount || 0);
			$(".bsprtAddress").val(oData.data.bsprtAddress || "");		
			$(".bsprtIsVirtual").val(oData.data.bsprtIsVirtual || 0);		
			$(".bsprtExpress").val(oData.data.bsprtExpress || 0);

			$(".bsprtProductTag .select-item").val(bsprtProductTag.tagNames || "");
			if(oData.data.bsprtHotspotName != ""){
				var hotName = oData.data.bsprtHotspotName;
			}else{
				var hotName = "无";
			}
			$(".labelHot").html("当前："+hotName);
			CKEDITOR.instances.bsprtView.setData(oData.data.bsprtView);
			// var radio=document.getElementsByName("hot");
			// alert(radio.length);
			// // for(var l=0; l<radio.length; l++){
			// // 	if($(radio[l]).attr('data-uuid') == oData.data.bsprtHotSpot){
			// // 		$(radio[l]).attr('checked',checked);

			// // 	}
			// // }
			
		   //初始化商品规格
			var cshHtml = "";
			for ( var i = 0; i < bsprtRuleParam.length; i++) {
				cshHtml +=  	'<div class="item standardContent">'+
										'<div class="flag">'+bsprtRuleParam[i].flag+'</div>'+
										'<div class="val">'+bsprtRuleParam[i].val+'</div>'+
										'<div class="deleteBtn">删除</div>'+
										'</div>';
			}
			$('#productParam').append(cshHtml);
			$('#productParam .deleteBtn').off("click").on("click",function(){
				$(this).parent().remove();
			});
			
			//推客封面图
			upImgItem.changeShow('img_upload_sharePic', 'bsprtSharePic', oData.data.bsprtSharePic, 200, 200, '13', '13', '200X200', function(widget) {
				var str = "cratmUuid=" + $(widget).attr('data-uuid');
				getOData(str,"coreAttachment/delete",{
            		fn:function(oData){
            			widget.attr('data-uuid', "");
            		},
            		fnerr:function(oData){
            			alert(oData.errMsg);
            		}
            	});
			});

			//商城封面图
			upImgItem.changeShow('img_upload_StorePic', 'bsprtStorePic', oData.data.bsprtStorePic, 750, 480, '13', '13', '750X480', function(widget) {
				var str = "cratmUuid=" + $(widget).attr('data-uuid');
				getOData(str,"coreAttachment/delete",{
            		fn:function(oData){
            			widget.attr('data-uuid', "");
            		},
            		fnerr:function(oData){
            			alert(oData.errMsg);
            		}
            	});
			});

			//主图1
			upImgItem.changeShow('img_upload_ImageUrl1', 'bsprtImageUrl1', oData.data.bsprtImageUrl1, 750, 468, '13', '13', '750X468', function(widget) {
				var str = "cratmUuid=" + $(widget).attr('data-uuid');
				getOData(str,"coreAttachment/delete",{
            		fn:function(oData){
            			widget.attr('data-uuid', "");
            		},
            		fnerr:function(oData){
            			alert(oData.errMsg);
            		}
            	});
			});

			//主图2
			upImgItem.changeShow('img_upload_ImageUrl2', 'bsprtImageUrl2', oData.data.bsprtImageUrl2, 750, 468, '13', '13', '750X468', function(widget) {
				var str = "cratmUuid=" + $(widget).attr('data-uuid');
				getOData(str,"coreAttachment/delete",{
            		fn:function(oData){
            			widget.attr('data-uuid', "");
            		},
            		fnerr:function(oData){
            			alert(oData.errMsg);
            		}
            	});
			});

			//主图3
			upImgItem.changeShow('img_upload_ImageUrl3', 'bsprtImageUrl3', oData.data.bsprtImageUrl3, 750, 468, '13', '13', '750X468', function(widget) {
				var str = "cratmUuid=" + $(widget).attr('data-uuid');
				getOData(str,"coreAttachment/delete",{
            		fn:function(oData){
            			widget.attr('data-uuid', "");
            		},
            		fnerr:function(oData){
            			alert(oData.errMsg);
            		}
            	});
			});

			//主图4
			upImgItem.changeShow('img_upload_ImageUrl4', 'bsprtImageUrl4', oData.data.bsprtImageUrl4, 750, 468, '13', '13', '750X468', function(widget) {
				var str = "cratmUuid=" + $(widget).attr('data-uuid');
				getOData(str,"coreAttachment/delete",{
            		fn:function(oData){
            			widget.attr('data-uuid', "");
            		},
            		fnerr:function(oData){
            			alert(oData.errMsg);
            		}
            	});
			});

			//主图5
			upImgItem.changeShow('img_upload_ImageUrl5', 'bsprtImageUrl5', oData.data.bsprtImageUrl5, 750, 468, '13', '13', '750X468', function(widget) {
				var str = "cratmUuid=" + $(widget).attr('data-uuid');
				getOData(str,"coreAttachment/delete",{
            		fn:function(oData){
            			widget.attr('data-uuid', "");
            		},
            		fnerr:function(oData){
            			alert(oData.errMsg);
            		}
            	});
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
		            	var str = "cratmUuid=" + widget.attr('data-uuid');
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
		} else {
			alert(data.errMsg);
		}
	}});
}



//检查提交
function checkModify(){
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

	var shareGradeItems = $('#shareGrade .item');
	for(var i=0; i<shareGradeItems.length; i++) {
		var keyName = "grade_" + $(shareGradeItems[i]).attr('data-gradeuuid');
		var integral = $(shareGradeItems[i]).find('.integral').val();
		var money = $(shareGradeItems[i]).find('.money').val();
		if(integral==null||integral=="") {
			parent.layer.close(msgObject);
			alert('分享等级积分必填');
			return;
		}
		if(money==null||money=="") {
			parent.layer.close(msgObject);
			alert('分享等级收益必填');
			return;
		}
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
			
			// console.log(".............");
			// console.log(JSON.stringify(wxzvalue));	

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
	// alert(bsprtHotSpot +"|"+bsprtHotspotName+".."+bsprtHotspotIcon);


	
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
		// if(integral==null||integral=="") {
		// 	parent.layer.close(msgObject);
		// 	alert('分享等级积分必填');
		// 	return;
		// }
		// if(money==null||money=="") {
		// 	parent.layer.close(msgObject);
		// 	alert('分享等级收益必填');
		// 	return;
		// }
		
		bsprtGradeShare[keyName] = {
			integral: integral,
			money: money,
		}
	}
	var bsprtUuid = getQueryString("id");
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
	
	var str = 'bsprtUuid='+encodeURIComponent(bsprtUuid)+'&bsprtName='+encodeURIComponent(bsprtName)+'&bsprtCategory='+
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
	getOData(str,"busiProduct/update/busiProduct",{
		fn:function(oData){
			parent.layer.close(msgObject);
			window.parent.refreshList();
			alert("修改成功！");
		},
		fnerr:function(oData){
			parent.layer.close(msgObject);
		}
	});
}