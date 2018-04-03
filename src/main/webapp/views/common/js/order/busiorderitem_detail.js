// JavaScript Document
$(function () {
	initDetail();
});
//初始化
function initDetail(){
	getInfo(getQueryString("id"));
}
//获取详情
function getInfo(id){
	var str = 'bsorrUuid='+encodeURIComponent(id);
	getOData(str,"busiOrder/views/back",{fn:function(oData){
		if(oData.code == 1) {
			$(".bsorrUuid").text(oData.data.bsorrUuid || "");
			$(".bsorrUser").text(oData.data.bsorrUserName || "");
			$(".bsorrProductValue").text(oData.data.bsorrProductValue || 0);
			$(".bsorrExpressValue").text(oData.data.bsorrExpressValue || 0);
			$(".bsorrAdjustExpress").text(oData.data.bsorrAdjustExpress || 0);
			$(".bsorrAdjustProduct").text(oData.data.bsorrAdjustProduct || 0);
			$(".bsorrOrderValue").text(oData.data.bsorrOrderValue || 0);
			$(".bsorrActualPay").text(oData.data.bsorrActualPay || 0);
			$(".bsorrTotalValue").text(oData.data.bsorrTotalValue || 0);
			$(".bsorrRefund").text(oData.data.bsorrRefund || 0);
			$(".bsorrTradeNo").text(oData.data.bsorrTradeNo || "");
			$(".bsorrTotalQuantity").text(oData.data.bsorrTotalQuantity || 0);
			$(".bsorrTotalWeight").text(oData.data.bsorrTotalWeight || 0);
			$(".bsorrPayTime").text(oData.data.bsorrPayTime || "");
			var bsorrStatusCH = '支付中';
			if(2==oData.data.bsorrStatus) {
				bsorrStatusCH = '支付成功';
			}
			if(3==oData.data.bsorrStatus) {
				bsorrStatusCH = '支付失败';
			}
			$(".bsorrStatus").text(bsorrStatusCH || "");
			$(".bsorrCdate").text(oData.data.bsorrCdate || "");
			$(".bsorrUdate").text(oData.data.bsorrUdate || "");
			$(".bsorrName").text(oData.data.bsorrName || "");
			$(".bsorrIdCard").text(oData.data.bsorrIdCard || "");
			$(".bsorrMobile").text(oData.data.bsorrMobile || "");
			$(".bsorrZipCode").text(oData.data.bsorrZipCode || "");
			$(".bsorrProvince").text(oData.data.bsorrProvinceName || "");
			$(".bsorrCity").text(oData.data.bsorrCityName || "");
			$(".bsorrCounty").text(oData.data.bsorrCountyName || "");
			$(".bsorrAddress").text(oData.data.bsorrAddress || ""); 
			 
			var aData =[{name:"<input type='checkbox' name='checkboxAll' value='checkbox' />",percent:"5"},
						{name:"商品名称",percent:"15"},
						{name:"商品属性",percent:"15"},
						{name:"属性标识",percent:"15"},
						{name:"标签集合",percent:"15"},
						{name:"购买数量",percent:"8"},
						{name:"明细金额",percent:"8"},
						{name:"商品单价",percent:"8"}];
			setTableHead(aData);
			var strhtml_list = "";
			var arrData = oData.data.busiOrderItemList;
			var ln = oData.data.busiOrderItemList.length;
			for(var i=0;i<ln;i++){
				strhtml_list += '<tr class="trHighLight">'
					+'<td>'+'<input type="checkbox" name="checkbox" value="checkbox" data-id="'+arrData[i].bsoimUuid+'"/>'+'</td>'
					+'<td>'+(arrData[i].bsoimProductName || "")+'</td>'
					+'<td>'+(arrData[i].bsoimAttrName || "")+'</td>'
					+'<td>'+(arrData[i].bsoimAttrValue || "")+'</td>'
					+'<td>'+(arrData[i].bsoimTags || "")+'</td>' 
					+'<td>'+(arrData[i].bsoimQuantity || 0)+'</td>'
					+'<td>'+(arrData[i].bsoimValue || 0)+'</td>' 
					+'<td>'+(arrData[i].bsoimPrice || "")+'</td>'
					+'</tr>';
			}
			$(".tb-body").html(strhtml_list);  
			
		} else {
			alert(data.errMsg);
		}
	}});
}
