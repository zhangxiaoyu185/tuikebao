// JavaScript Document
$(function () {
	initDetail();
});
//初始化
function initDetail(){
	getInfo(getQueryString("id"),getQueryString("code"),getQueryString("no"));
}
//获取详情
function getInfo(id,code,no){
	var str = 'bsoimUuid='+encodeURIComponent(id)+'&bsoimExpressCode='+encodeURIComponent(code)+'&bsoimExpno='+encodeURIComponent(no);
	console.log(code);
	console.log(no);
	console.log(id);
	getOData(str,"busiOrder/find/logistics/list",{fn:function(oData){
		if(oData.code == 1) {
			var str = ""; 
			for ( var i = 0; i < oData.data.length; i++) {
				str 	+= 	'<li class="list-li">'
								+'<label class="leftLi">物流状态：</label>'
								+'<div class="rightLi">'
								+'<label class="bsoesContent">'+oData.data[i].bsoesContent+'</label>'
								+'</div>'
								+'</li>'
								+'<li class="list-li">'
								+'<label class="leftLi">时间：</label>'
								+'<div class="rightLi">'
								+'<label class="bsoesCdate">'+oData.data[i].bsoesCdate+'</label>'
								+'</div>'
								+'</li>'
								+'</br>';
			} 
			$(".bsoesOrderItem").text(oData.data[0].bsoesOrderItem || "");
			$('.box .list').html(str); 
		} else {
			alert(data.errMsg);
		}
	}});
}
