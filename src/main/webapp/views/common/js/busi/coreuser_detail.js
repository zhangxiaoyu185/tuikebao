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
	var str = 'crusrUuid='+encodeURIComponent(id);
	getOData(str,"coreUser/views",{fn:function(oData){
		if(oData.code == 1) {
			$(".crusrName").text(oData.data.crusrName || "");
			$(".crusrCode").text(oData.data.crusrCode || "");
			$(".crusrHead").text(oData.data.crusrHead || "");
			getImageWidthHeight(urlfile+"/coreAttachment/image/get/"+oData.data.crusrHead,function(realWidth,realHeight){
				var width = 150;
				var height = 150;
				if(realHeight>=height){
					width = realWidth/realHeight*height;
					$(".crusrHead_preimg").css("width",width).css("height",height);
				}else{//如果小于浏览器的宽度按照原尺寸显示}
					$(".crusrHead_preimg").css("width",realWidth+'px').css("height",realHeight+'px');
				}
				$(".crusrHead_preimg").attr("src",urlfile+"/coreAttachment/image/get/"+oData.data.crusrHead);
			});
			$(".crusrHeight").text(oData.data.crusrHeight || "");
			$(".crusrWeight").text(oData.data.crusrWeight || "");
			$(".crusrAge").text(oData.data.crusrAge || "");
			$(".crusrProvince").text(oData.data.crusrProvinceName || "");
			$(".crusrCity").text(oData.data.crusrCityName || "");
			$(".crusrDistrict").text(oData.data.crusrDistrictName || "");
			$(".crusrAddress").text(oData.data.crusrAddress || "");
			$(".crusrEmail").text(oData.data.crusrEmail || "");
			if(1 == oData.data.crusrEmailVerify) {
				$(".crusrEmailVerify").text("验证");
			}
			if(0 == oData.data.crusrEmailVerify) {
				$(".crusrEmailVerify").text("未验证");
			}
			$(".crusrMobile").text(oData.data.crusrMobile || "");
			if(1 == oData.data.crusrMobileVerify) {
				$(".crusrMobileVerify").text("验证");
			}
			if(0 == oData.data.crusrMobileVerify) {
				$(".crusrMobileVerify").text("未验证");
			}
			$(".crusrJob").text(oData.data.crusrJobName || "");
			$(".crusrGrade").text(oData.data.crusrGradeName || "");
			if(1 == oData.data.crusrStatus) {
				$(".crusrStatus").text("启用");
			}
			if(0 == oData.data.crusrStatus) {
				$(".crusrStatus").text("禁用");
			}
			$(".crusrCdate").text(oData.data.crusrCdate || "");
			$(".crusrUdate").text(oData.data.crusrUdate || "");
			$(".crusrBirthday").text(oData.data.crusrBirthday || "");
			if(2 == oData.data.crusrGender) {
				$(".crusrGender").text("女");
			}
			if(1 == oData.data.crusrGender) {
				$(".crusrGender").text("男");
			}
			if(0 == oData.data.crusrGender) {
				$(".crusrGender").text("未知");
			}
			$(".crusrQq").text(oData.data.crusrQq || "");
			$(".crusrSignature").text(oData.data.crusrSignature || "");
			$(".crusrInfo").text(oData.data.crusrInfo || "");
			$(".crusrRemarks").text(oData.data.crusrRemarks || "");
			$(".crusrOpenidStore").text(oData.data.crusrOpenidStore || "");
			$(".crusrOpenidShare").text(oData.data.crusrOpenidShare || "");
			$(".crusrWxNickname").text(oData.data.crusrWxNickname || "");
			if(2 == oData.data.crusrWxSex) {
				$(".crusrWxSex").text("女");
			}
			if(1 == oData.data.crusrWxSex) {
				$(".crusrWxSex").text("男");
			}
			if(0 == oData.data.crusrWxSex) {
				$(".crusrWxSex").text("未知");
			}
			$(".crusrWxCity").text(oData.data.crusrWxCity || "");
			$(".crusrWxCountry").text(oData.data.crusrWxCountry || "");
			$(".crusrWxProvince").text(oData.data.crusrWxProvince || "");
			$(".crusrHead_preimg").attr("src",oData.data.crusrWxHeadimgurl);
			$(".crusrWxSubscribe").text(oData.data.crusrWxSubscribe || "");
			$(".crusrIncome").text(oData.data.crusrIncome || "");
			$(".crusrFrozenIncome").text(oData.data.crusrFrozenIncome || "");
			$(".crusrAvailableIncome").text(oData.data.crusrAvailableIncome || "");
			$(".crusrTotalIncome").text(oData.data.crusrTotalIncome || "");
			$(".crusrIntegral").text(oData.data.crusrIntegral || "");
			$(".crusrTotalIntegral").text(oData.data.crusrTotalIntegral || "");
			$(".crusrInviter").text(oData.data.crusrInviterName || "");
			$(".crusrLastTime").text(oData.data.crusrLastTime || "");
			$(".crusrShoppingCart").text(oData.data.crusrShoppingCart || "");
			$(".crusrOrder").text(oData.data.crusrOrder || "");
			$(".crusrPendingPay").text(oData.data.crusrPendingPay || "");
			$(".crusrPendingShip").text(oData.data.crusrPendingShip || "");
			$(".crusrPendingRecv").text(oData.data.crusrPendingRecv || "");
			$(".crusrPendingEval").text(oData.data.crusrPendingEval || "");
			$(".crusrRefund").text(oData.data.crusrRefund || "");
			$(".crusrFinished").text(oData.data.crusrFinished || "");
			$(".crusrCancelled").text(oData.data.crusrCancelled || "");
			$(".crusrShareIndex").text(oData.data.crusrShareIndex || "");
			$(".crusrNowShares").text(oData.data.crusrNowShares || "");
			$(".crusrInviteCode").text(oData.data.crusrInviteCode || "");
			$(".crusrInviteTotal").text(oData.data.crusrInviteTotal || "");
			$(".crusrInviteNused").text(oData.data.crusrInviteNused || "");
			$(".crusrNowShare").text(oData.data.crusrNowShare || "");
			$(".crusrNowAddintegral").text(oData.data.crusrNowAddintegral || "");
			$(".crusrNowFinish").text(oData.data.crusrNowFinish || "");
			$(".crusrNowAddincome").text(oData.data.crusrNowAddincome || "");
			$(".crusrYestShare").text(oData.data.crusrYestShare || "");
			$(".crusrYestAddintegral").text(oData.data.crusrYestAddintegral || "");
			$(".crusrYestFinish").text(oData.data.crusrYestFinish || "");
			$(".crusrYestAddincome").text(oData.data.crusrYestAddincome || "");
			$(".crusrMonthShare").text(oData.data.crusrMonthShare || "");
			$(".crusrMonthAddintegral").text(oData.data.crusrMonthAddintegral || "");
			$(".crusrMonthFinish").text(oData.data.crusrMonthFinish || "");
			$(".crusrMonthAddincome").text(oData.data.crusrMonthAddincome || "");
			$(".crusrNowCashcount").text(oData.data.crusrNowCashcount || "");
			$(".crusrNowCashfee").text(oData.data.crusrNowCashfee || "");
			$(".crusrYestCashcount").text(oData.data.crusrYestCashcount || "");
			$(".crusrYestCashfee").text(oData.data.crusrYestCashfee || "");
			$(".crusrMonthCashcount").text(oData.data.crusrMonthCashcount || "");
			$(".crusrMonthCashfee").text(oData.data.crusrMonthCashfee || "");
			$(".crusrNowExchcount").text(oData.data.crusrNowExchcount || "");
			$(".crusrNowExchintegral").text(oData.data.crusrNowExchintegral || "");
			$(".crusrYestExchcount").text(oData.data.crusrYestExchcount || "");
			$(".crusrYestExchintegral").text(oData.data.crusrYestExchintegral || "");
			$(".crusrMonthExchcount").text(oData.data.crusrMonthExchcount || "");
			$(".crusrMonthExchintegral").text(oData.data.crusrMonthExchintegral || "");
		} else {
			alert(data.errMsg);
		}
	}});
}
