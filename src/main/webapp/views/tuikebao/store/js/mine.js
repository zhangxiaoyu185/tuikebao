/***********************个人中心**********************/
Views.myIndexView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myIndex',
        hasFootNav: true,
        footItemOrder:4, // hasFootNav设置true才有效   表示现在远着的是底部第几个菜单 0开始
        itemClass: 'item'
    },

    willShow: function(param, isBackPage) {
        var isLoginFlag = isLogin();
        if(!isLoginFlag) {
            return;
        }
        
        var self = this;
        var data = {crusrUuid: dataGet('userUuid')};
        ajaxTool("post", data, WEB_URL + "coreUser/views",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                  
                    JdataSave('userInfo', data.data);
                    dataSave('userUuid', data.data.crusrUuid);
                    dataSave('userCode', data.data.crusrCode);
                    self.show({userInfo: data.data, WEB_URL: WEB_URL}, isBackPage);
                    self.headPath = WEB_URL + "coreAttachment/image/get/" + data.data.crusrHead;
                    self.head = data.data.crusrHead;                   
                }
            },true
        );
    },

    didShow: function(param, isBackPage) {
        initWx();
        var self=this;
        var imgUrl=self.head;
        if(!imgUrl){
            $('#headPic').css({
                'background':'../images/mine/img-head-picture.png',
                '-webkit-background-size': '74px 74px',
                'background-size': '74px 74px'
            });
        }else{
            $('#headPic').css({
                'background':'url("'+this.headPath+'")',
                '-webkit-background-size': '74px 74px',
                'background-size': '74px 74px'
            });
        }
        
    },

    goPersonInfo: function(btn) {
        Views.accountSettingView.willShow();
    },

    goMyOrder: function(btn) {
        dataSave('myOrderIndex', "");
        dataSave('myOrderIndex', $(btn).attr('data-index'));
        Views.myOrderView.willShow();
    },

    goAfterSale: function(btn) {
        Views.myOrderAfterSaleView.willShow();
    },

    goAddressManageView: function(btn) {
        Views.receiveAddressView.willShow();
    },

    goAccountSettingView: function(btn) {
        Views.accountSettingView.willShow();
    },

    goMyMessageView: function(btn) {
        Views.myMessageView.willShow();
    },
     
    goShoppingCartView: function(btn) {
        dataSave('cartIndex', 0);
        var flag = true;
        dataSave('flag',flag);
        Views.shoppingCartView.willShow();
    },

    exitApp: function(btn) {

        if(confirm("确定要退出吗"))
          {
            dataSave('isNotLogin', "true");
            dataSave('accountName', '');
            dataSave('accountPassword', '');
            JdataSave('userInfo', '');
            dataSave('userUuid', '');
            dataSave('userCode', '');
            dataSave("crusrGrade", '');
            isLogin();
          }
          else
          {
            Views.myIndexView.willShow();
          }
        
    }
});

/***********************我的订单**********************/
Views.myOrderView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myOrder'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
        $('.myOrder_type_border .selected').removeClass('selected');
        var typeIndex = parseInt(dataGet('myOrderIndex'));
        $('.myOrder_type_border .item').eq(typeIndex).addClass('selected');
        dataSave('typePage',$('.myOrder_type_border .selected').eq(0).attr('data-type'));
		
		 
    },

    

    scrollInitRender: function() {
        this.pageNum=1;
        var typePage = dataGet('typePage');
        switch(typePage)
        {
        case "myOrder_waitSend_item":
            // 代发货
            var bsoimStatus = 2;
            this.getServiceList("scrollInit", 1, 10,bsoimStatus,typePage);
            break;
        case "myOrder_waitReceive_item":
            // 待收货
            var bsoimStatus = 5;
            this.getServiceList("scrollInit", 1, 10,bsoimStatus,typePage);
            break;
        case "myOrder_waitEvaluate_item":
            // 待评价
            var bsoimStatus = 6;
            this.getServiceList("scrollInit", 1, 10,bsoimStatus,typePage);
            break;
        case "myOrder_finish_item":
            // 已完成
            var bsoimStatus = 8;
            this.getServiceList("scrollInit", 1, 10,bsoimStatus,typePage);
            break;
        case "myOrder_waitPay_item":
            // 代付款
            var bsoimStatus = 1;
            this.getServiceList("scrollInit", 1, 10,bsoimStatus,typePage);
            break;
        default:
        }
    },

    scrollDownRender: function() {
        this.pageNum=1;
        var typePage = dataGet('typePage');
        switch(typePage)
        {
        case "myOrder_waitSend_item":
            // 代发货
            var bsoimStatus = 2;
            this.getServiceList("scrollDown", 1, 10,bsoimStatus,typePage);
            break;
        case "myOrder_waitReceive_item":
            // 待收货
            var bsoimStatus = 5;
            this.getServiceList("scrollDown", 1, 10,bsoimStatus,typePage);
            break;
        case "myOrder_waitEvaluate_item":
            // 待评价
            var bsoimStatus = 6;
            this.getServiceList("scrollDown", 1, 10,bsoimStatus,typePage);
            break;
        case "myOrder_finish_item":
            // 已完成
            var bsoimStatus = 8;
            this.getServiceList("scrollDown", 1, 10,bsoimStatus,typePage);
            break;
        case "myOrder_waitPay_item":
            // 代付款
            var bsoimStatus = 1;
            this.getServiceList("scrollDown", 1, 10,bsoimStatus,typePage);
            break;
        default:
        }
    },

    scrollUpRender: function() {
		console.log(this);
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            var typePage = dataGet('typePage');
            switch(typePage)
            {
            case "myOrder_waitSend_item":
                // 代发货
                var bsoimStatus = 2;
                this.getServiceList("scrollUp",  this.pageNum, 10,bsoimStatus,typePage);
                break;
            case "myOrder_waitReceive_item":
                // 待收货
                var bsoimStatus = 5;
                this.getServiceList("scrollUp",  this.pageNum, 10,bsoimStatus,typePage);
                break;
            case "myOrder_waitEvaluate_item":
                // 待评价
                var bsoimStatus = 6;
                this.getServiceList("scrollUp",  this.pageNum, 10,bsoimStatus,typePage);
                break;
            case "myOrder_finish_item":
                // 已完成
                var bsoimStatus = 8;
                this.getServiceList("scrollUp",  this.pageNum, 10,bsoimStatus,typePage);
                break;
            case "myOrder_waitPay_item":
                // 代付款
                var bsoimStatus = 1;
                this.getServiceList("scrollUp",  this.pageNum, 10,bsoimStatus,typePage);
                break;
            default:
            }
        }else{
        	$('#pullUp-msg').html("我也是有底线的！");
        }
    },

    getServiceList: function(type,pageNum,pageSize,bsoimStatus,typePage) {
        var self = this;
        if(!pageNum){
            pageNum=1;
        }
        if(!pageSize){
            pageSize=10;
        }
        var data = {
            bsoimUser:dataGet('userUuid'),
            bsorrUser:dataGet('userUuid'),
            bsoimStatus: bsoimStatus,
            pageNum: pageNum,
            pageSize: pageSize
        };
        if( bsoimStatus == 1){
            var url = WEB_URL + "busiOrder/find/by/cnd/my/pending";
        }else{
            var url = WEB_URL + "busiOrder/item/find/by/cnd/my/status";
        }
        
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                } else {
                    self.lastPageNumber = data.data.lastPageNumber;
                    var typePage = dataGet('typePage');
                    if( bsoimStatus == 1){
                        var dataWaitPay = data.data.result;
                        JdataSave('dataWaitPay',dataWaitPay);
                    }
                    if (type == "scrollInit") {
                            self.scrollInit(typePage,{list: data.data.result, WEB_URL: WEB_URL});   	
                    };
                    if (type == "scrollDown") {
                            self.scrollInit(typePage,{list: data.data.result, WEB_URL: WEB_URL});                   
                    };
                    if (type == "scrollUp") {
                            self.scrollInit(typePage,{list: data.data.result, WEB_URL: WEB_URL});                 
                    };
                }
            }
        );
    },

 //订单详情查询
    // orderDetails: function(bsoimStatus,typePage){
    //     var self = this;
    //     var data = {
    //         // pageNum: pageNum,
    //         // pageSize: pageSize,
    //         bsoimUser:dataGet('userUuid'),
    //         bsoimStatus: bsoimStatus
    //     };
        
    //     ajaxTool("post", data, url,
    //         function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
    //             alert("error:" + data);
    //         },
    //         function success(data){
    //             if(!data.success) {
    //                 alert(data.errMsg);
    //             }else{
    //                console.log(data.data.result);
    //                     self.scrollInit(typePage,{list: data.data.result, WEB_URL: WEB_URL});
    //             }
    //         }
    //     );

    // },

    //待支付取消
    delete: function(btn){
        var self = this;
        if(confirm("确定要取消吗")){
            var bsorrUuid = $(btn).attr('data-uuid');
            var self = this;
            var data = {
                bsorrUuid:bsorrUuid,
            };
            var url = WEB_URL + "busiOrder/cancel/order";
            ajaxTool("post", data, url,
                function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                    alert("error:" + data);
                },
                function success(data){
                    if(!data.success) {
                        alert(data.errMsg);
                    }else{
                        alert(data.errMsg);
                        //Views.myOrderView.willShow();  
                        $(btn).parent().parent().remove();
                        dataSave('myOrderIndex', "0");
                        dataSave('typePage', "myOrder_waitPay_item");
                        self.scrollInitRender();               
                    }
                }
            );
        }

    },

    //提醒发货
    goRemind: function(btn){
        var bsoimUuid = $(btn).attr('data-uuid');
        var self = this;
        var data = {
            bsoimUuid:bsoimUuid,
        };
        var url = WEB_URL + "busiOrder/item/prompt/shipment";
        ajaxTool("post", data, url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    alert(data.errMsg);
                    //self.scrollInit(typePage,{list: data.data.result, WEB_URL: WEB_URL});
                }
            }
        );

    },

    //确认收货
    confirm:function(btn){
        if(confirm("是否确认收货")){
            var bsoimUuid = $(btn).attr('data-uuid');
            var self = this;
            var data = {
                bsoimUuid:bsoimUuid,
            };
            var url = WEB_URL + "busiOrder/item/recv/receipt";
            ajaxTool("post", data, url,
                function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                    alert("error:" + data);
                },
                function success(data){
                    if(!data.success) {
                        alert(data.errMsg);
                    }else{
                        // scrollInitRender();
                        alert(data.errMsg);
                        dataSave('myOrderIndex', "3");
                        dataSave('typePage', "myOrder_waitEvaluate_item");
                        self.scrollInitRender(); 
                    }
                }
            );
        }else {
            //backPage();
        }
    },

    //删除
    goDelete: function(btn){
        var bsoimUuid = $(btn).attr('data-uuid');
        var self = this;
        var data = {
            bsoimUuid:bsoimUuid,
        };
        var url = WEB_URL + "busiOrder/item/delete";
        ajaxTool("post", data, url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    // scrollInitRender();
                    alert(data.errMsg);
                    //self.scrollInit(typePage,{list: data.data.result, WEB_URL: WEB_URL});
                }
            }
        );
    },

    //立即支付
    goPay: function(btn){
        var self = this;
        var number = $(btn).attr('data-number');
        self.dataWaitPay = JdataGet('dataWaitPay');
        var url = WEB_URL + "pay/get/package/order/go/pay";
        var orderPay = {
            orderUuid: self.dataWaitPay[number].bsorrUuid,
            userUuid: self.dataWaitPay[number].bsorrUser
        }
        ajaxTool("post", orderPay, url,
          function error(XMLHttpRequest, textStatus, errorThrown, fnErr) {
            alert("error:" + data);
          },
          function success(data) {
            if (!data.success) {
              alert(data.errMsg);
            } else {
              // backPage();
              goToPay(data);
            }
          }, true
        );      
    },


    myOrderType: function(btn) {
    	$('.myOrder_type_border .selected').removeClass('selected');
    	$(btn).addClass('selected');
        var typePage = $(btn).attr('data-type');
        var typeIndex = $(btn).attr('data-index');
        dataSave('myOrderIndex', typeIndex);
        dataSave('typePage', typePage);
        this.scrollInitRender();
    },

    goDesc: function() {
        Views.myOrderDescView.willShow();
    },

    goDesc_waitPay: function(btn) {
        // var bsorrUuid = $(btn).parent().siblings('.top').children('.orderNumber').html();//订单号
        var type = $(btn).attr('data-type');
        Views.myOrderDesc_waitPayView.willShow(type);
    },

    goDesc_waitSend: function(btn) {
        var uuid = $(btn).attr('data-uuid');
        Views.myOrderDesc_waitSendView.willShow(uuid);
    },

    goDesc_waitReceive: function(btn) {
        data ={
            bsoimUuid:$(btn).attr("data-uuid")
        }
        Views.myOrderDesc_waitReceiveView.willShow(data);
    },

    goDesc_waitEvaluate: function(btn) {
        var uuid = $(btn).attr('data-uuid');
        Views.myOrderDesc_waitEvaluateView.willShow(uuid);
    },

    goDesc_finish: function(btn) {
        var uuid = $(btn).attr('data-uuid');
        Views.myOrderDesc_finishView.willShow(uuid);
    },

    goExpress: function(btn) {//物流
        //Views.expressView.willShow();
        var data = {
            bsoimUuid: $(btn).attr('data-uuid'),
            bsoimExpno: $(btn).attr('data-Expno'),
            bsoimExpressCode:$(btn).attr('data-ExpressCode'),
            bsoimOrderTime: $(btn).attr('data-OrderTime'),
            bsoimExpressName: $(btn).attr('data-ExpressName')
            //bsoimExpressName:  
        }
        Views.expressView.willShow(data);
    },

    goEvaluate: function(btn) {//评价
         var data = {
            bseveOrderItem:$(btn).attr('data-uuid'),
            bseveProduct: $(btn).attr('data-Product'),
            bseveAttrName: $(btn).attr('data-AttrName'),
        }
        Views.evaluateView.willShow(data);
    },

    goOnLine: function(btn) {
        Views.onLineView.willShow();
    },

    goPaid:function(btn) {
        var self = this;
        var userUuid = dataGet('userUuid');
        var orderUuid=$(btn).attr("data-num")
        var data = {
            userUuid: userUuid,
            orderUuid:orderUuid
        };
        var url = WEB_URL + "pay/get/package/order/go/pay";
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                } else {
                    //console.log(data);
                  
                }
            }
        );true;    
    },
});

/***********************我的订单详情**********************/
Views.myOrderDescView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myOrderDesc'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
        
    },
    goEvaluate: function(btn) {//评价
         var data = {
            bseveOrderItem:$(btn).attr('data-uuid'),
            bseveProduct: $(btn).attr('data-Product'),
            bseveAttrName: $(btn).attr('data-AttrName'),
        }
        Views.evaluateView.willShow(data);
    },
     //删除
    goDelete: function(btn){
        var bsoimUuid = $(btn).attr('data-uuid');
        var self = this;
        var data = {
            bsoimUuid:bsoimUuid,
        };
        var url = WEB_URL + "busiOrder/item/delete";
        ajaxTool("post", data, url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    // scrollInitRender();
                    alert(data.errMsg);
                    //self.scrollInit(typePage,{list: data.data.result, WEB_URL: WEB_URL});
                }
            }
        );
    },
});

/***********************待付款订单**********************/
Views.myOrderDesc_waitPayView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myOrderDesc_waitPay'
    },

    willShow: function(param, isBackPage) {
        var self = this;
        self.param = param;
        self.dataWaitPay = JdataGet('dataWaitPay');
        var WaitPay = this.dataWaitPay[param];     
       self.show({List:WaitPay, WEB_URL: WEB_URL}, isBackPage);

    },

    didShow: function() {

    },

    goPay: function(btn){
        var self = this;
        var url = WEB_URL + "pay/get/package/order/go/pay";
        var data = {
            orderUuid: self.dataWaitPay[self.param].bsorrUuid,
            userUuid: self.dataWaitPay[self.param].bsorrUser
        }
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    // self.listData = data.data; 
                    //self.show({list: self.listData, WEB_URL: WEB_URL}, isBackPage);
                    goToPay(data);
                }
            },true
        );
    },

});

/***********************待发货订单**********************/
Views.myOrderDesc_waitSendView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myOrderDesc_waitSend'
    },

    willShow: function(param, isBackPage) {
        //this.show(param, isBackPage);
        var self = this;
        var url = WEB_URL + "busiOrder/item/views";
        data = {
            bsoimUuid:param
        }
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.information = data.data; 
                    self.show({information: self.information, WEB_URL: WEB_URL}, isBackPage);
                    //goToPay(data);
                }
            },true
        );
    },

    goOnLine: function(btn) {
        Views.onLineView.willShow();
    },

    didShow: function() {
        
    },

    //提醒发货
    goRemind: function(btn){
        var bsoimUuid = $(btn).attr('data-uuid');
        var self = this;
        var data = {
            bsoimUuid:bsoimUuid,
        };
        var url = WEB_URL + "busiOrder/item/prompt/shipment";
        ajaxTool("post", data, url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    alert(data.errMsg);
                    //self.scrollInit(typePage,{list: data.data.result, WEB_URL: WEB_URL});
                }
            }
        );

    },

    scrollInitRender: function() {
        // this.scrollInit('listTestItem',{list:[199,299,399,499,599]});
        this.pageNum=1;
        this.getgetMessage("scrollInit", 1, 10);
    },

    scrollDownRender: function() {
        this.pageNum=1;
        this.getgetMessage("scrollDown", 1, 10);
    },

    scrollUpRender: function() {
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            this.getgetMessage("scrollUp", this.pageNum, 10);
        }else{
        	$('#pullUp-msg').html("我也是有底线的！");
        }
    },

    getgetMessage: function(type,pageNum,pageSize) {
        var self = this;
        if(!pageNum){
            pageNum=1;
        }
        if(!pageSize){
            pageSize=10;
        }

        var data = {
            pageNum: pageNum,
            pageSize: pageSize,
            bsoimUser:dataGet('userUuid'),
            bsoimStatus: 2
        };
        var url = WEB_URL + "busiOrder/item/find/by/cnd/my/status";
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                } else {
                    if (type == "scrollInit") {
                        self.scrollInit('myOrder_waitSend_item',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollDown") {
                        self.scrollDown('myOrder_waitSend_item',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollUp") {
                        self.scrollUp('myOrder_waitSend_item',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                }
            },true
        );
    },
});

/***********************待收货订单**********************/
Views.myOrderDesc_waitReceiveView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myOrderDesc_waitReceive'
    },

    willShow: function(param, isBackPage) {
        //this.show(param, isBackPage);
        var self = this;
        var url = WEB_URL + "busiOrder/item/views";
        // data = {
        //     bsorrUuid:
        // }
        ajaxTool("post",param,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.information = data.data; 
                    self.show({information: self.information, WEB_URL: WEB_URL}, isBackPage);
                    //goToPay(data);
                }
            },true
        );
    },

    //确认收货
    confirm:function(btn){
        if(confirm("是否确认收货")){
            var bsoimUuid = $(btn).attr('data-uuid');
            var self = this;
            var data = {
                bsoimUuid:bsoimUuid,
            };
            var url = WEB_URL + "busiOrder/item/recv/receipt";
            ajaxTool("post", data, url,
                function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                    alert("error:" + data);
                },
                function success(data){
                    if(!data.success) {
                        alert(data.errMsg);
                    }else{
                        // scrollInitRender();
                        alert(data.errMsg);
                        //self.scrollInit(typePage,{list: data.data.result, WEB_URL: WEB_URL});
                    }
                }
            );
        }else {
            //backPage();
        }
    },

    goExpress: function(btn) {//物流
        //Views.expressView.willShow();
        var data = {
            bsoimUuid: $(btn).attr('data-uuid'),
            bsoimExpno: $(btn).attr('data-Expno'),
            bsoimExpressCode:$(btn).attr('data-ExpressCode'),
            bsoimOrderTime: $(btn).attr('data-OrderTime'),
            bsoimExpressName: $(btn).attr('data-ExpressName')
            //bsoimExpressName:  
        }
        Views.expressView.willShow(data);
    },  

    didShow: function() {
        
    }
});

/***********************待评价订单**********************/
Views.myOrderDesc_waitEvaluateView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myOrderDesc_waitEvaluate'
    },

    willShow: function(param, isBackPage) {
       //this.show(param, isBackPage);
        var self = this;
        var url = WEB_URL + "busiOrder/item/views";
        data = {
            bsoimUuid:param
        }
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.finish = data.data; 
                    self.show({fulfil: self.finish, WEB_URL: WEB_URL}, isBackPage);
                    //goToPay(data);
                }
            },true
        );
    },

    didShow: function() {
        
    },

    goEvaluate: function(btn) {//评价
         var data = {
            bseveOrderItem:$(btn).attr('data-uuid'),
            bseveProduct: $(btn).attr('data-Product'),
            bseveAttrName: $(btn).attr('data-AttrName'),
        }
        Views.evaluateView.willShow(data);
    },
});

/***********************已完成订单**********************/
Views.myOrderDesc_finishView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myOrderDesc_finish'
    },

    willShow: function(param, isBackPage) {
        //this.show(param, isBackPage);
        var self = this;
        var url = WEB_URL + "busiOrder/item/views";
        data = {
            bsoimUuid:param
        }
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.finish = data.data; 
                    self.show({fulfil: self.finish, WEB_URL: WEB_URL}, isBackPage);
                    //goToPay(data);
                }
            },true
        );
    },

    didShow: function() {
        
    },
    //删除
    goDelete: function(btn){
        var bsoimUuid = $(btn).attr('data-uuid');
        var self = this;
        var data = {
            bsoimUuid:bsoimUuid,
        };
        var url = WEB_URL + "busiOrder/item/delete";
        ajaxTool("post", data, url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    // scrollInitRender();
                    alert(data.errMsg);
                    backPage();
                    //self.scrollInit(typePage,{list: data.data.result, WEB_URL: WEB_URL});
                }
            }
        );
    },
});

/***********************我的售后**********************/
Views.myOrderAfterSaleView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myOrderAfterSale'
    },
   
    willShow: function(param, isBackPage) {
        // this.show(param, isBackPage);
        var self = this;
        var url = WEB_URL + "busiOrder/item/find/by/cnd/my/status";
        var data = {
            bsoimUser:dataGet('userUuid'),
            bsoimStatus:9

        }
        console.log(data);
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.drawback = data.data.result; 
                    self.show({service: self.drawback, WEB_URL: WEB_URL}, isBackPage);
                    console.log(data);
                    //goToPay(data);
                }
            },true
        );
        
    },
  
    didShow: function() {
        
    },
    goOnLine: function(btn) {
        Views.onLineView.willShow();
    },

});

/***********************物流详情**********************/
Views.expressView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'express'
    },

    willShow: function(param, isBackPage) {
        //this.show(param, isBackPage);
        var self = this;
        // data ={
        //     bsoimExpno:"12313213213213",
        //     bsoimExpressCode:"AXD",
        //     bsoimUuid:"170418200124si9C"
        // }
        var url = WEB_URL + "busiOrder/find/logistics/list";
        ajaxTool("post",param,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{                    
                    self.information = data.data; 
                    self.show({information: self.information, list: param , WEB_URL: WEB_URL}, isBackPage);
                    //goToPay(data);
                }
            },true
        );
    },

    didShow: function(param, isBackPage) {
        var uuid = $('#Uuid').attr();
        backPage();
    }
    
});

/***********************评价**********************/
Views.evaluateView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'evaluate'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
        var self = this;
        self.data = param;
    },

    didShow: function(param, isBackPage) {
         $('#img_head_pic').css({
            'background':'url("'+this.headPath+'")',
            '-webkit-background-size': '100px 100px',
            'background-size': '100px 100px'
        });

        var upHead = Views.upHead.init('uploadphoto');
        $('#img_head_pic').on('change',function() {
            layer.open({shadeClose: false,type: 2});
            upHead.show(
                {url: WEB_URL + "coreAttachment/stream/upload",param:{cratmType:1, cratmDir:"taoketui/head", fileName:"head.png"}, width: 150,
        height: 150},
                function(returnImagData, ajaxResult) {//图片数据， 上传图片后返回的ajax数据
                    $('#img_head_pic').css({
                        'background':'url("'+returnImagData+'")',
                        '-webkit-background-size': '100px 100px',
                        'background-size': '100px 100px'
                    });
                    $('#img_head_pic').attr('data-uuid', ajaxResult.data);
                    Views.upHead.hide();  
                }
            );
        })       
    },

    goRelease: function(btn){
        this.data.bseveGrade = $('.selected').attr('data-type');
        this.data.bseveContent = $('.evaluate_textarea').val();
        this.data.bseveUser = dataGet('userUuid');
        this.data.bsevePic1 = $('#img_head_pic').attr('data-uuid');
        //少获取图片

        var url = WEB_URL + "busiEvaluate/add/busiEvaluate";
        ajaxTool("post",this.data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                } else {
                    alert(data.errMsg);
                    history.go(-1);
                    //self.show({list:data.data}, isBackPage);
                    //self.addressList = data.data;
                }
            },true
        );
    },

    evaluateSelect: function(btn) {
        $('.evaluate_top .selected').removeClass('selected');
        $(btn).addClass('selected');
    }
});

/***********************我的消息**********************/
Views.myMessageView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myMessage'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },
    
    scrollInitRender: function() {
        // this.scrollInit('listTestItem',{list:[199,299,399,499,599]});
        this.pageNum=1;
        this.getgetMessage("scrollInit", 1, 10);
    },

    scrollDownRender: function() {
        this.pageNum=1;
        this.getgetMessage("scrollDown", 1, 10);
    },

    scrollUpRender: function() {
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            this.getgetMessage("scrollUp", this.pageNum, 10);
        }else{
        	$('#pullUp-msg').html("我也是有底线的！");
        }
    },

    getgetMessage: function(type,pageNum,pageSize) {
        var self = this;
        if(!pageNum){
            pageNum=1;
        }
        if(!pageSize){
            pageSize=10;
        }

        var data = {
            pageNum: pageNum,
            pageSize: pageSize,
            bsmecUser: dataGet('userUuid')
        };
        var url = WEB_URL + "busiMessageCenter/store/find/by/cnd";
        
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                } else {
                    self.lastPageNumber = data.data.lastPageNumber;
                    if (type == "scrollInit") {
                        self.scrollInit('myMessageItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollDown") {
                        self.scrollDown('myMessageItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollUp") {
                        self.scrollUp('myMessageItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                }
            },true
        );
    },

    didShow: function(param, isBackPage) {
        
    }
});

/***********************管理收货地址**********************/
Views.addressManageView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'addressManage'
    },

    willShow: function(param, isBackPage) {
        var self = this;
        var data = {bssasUser: dataGet('userUuid')};
        var url = WEB_URL + "busiShippingAddress/find/my/shipping/address";
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                } else {
                    self.show({list:data.data}, isBackPage);
                    self.addressList = data.data;
                }
            },true
        );

        
   },

    didShow: function(param, isBackPage) {
        
    },

    addressSelected: function(btn) {
        $('.addressManage .selected').removeClass('selected');
        $(btn).addClass('selected');
        var index = parseInt($(btn).attr('data-index'));
        var addressList = this.addressList;
        JdataSave('addressBuy', addressList[index]);
        backPage();
    },

    addAddress: function(btn) {
        Views.newAddressView.willShow();
    }
});

/***********************收货地址**********************/

Views.receiveAddressView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'receiveAddress'
    },

    willShow: function(param, isBackPage) {
        var self = this;
        var url = WEB_URL + "busiShippingAddress/find/my/shipping/address";
        ajaxTool("post",{bssasUser: dataGet('userUuid')},url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.show({list: data.data}, isBackPage); 
                }
            },true
        );
        
    },

    didShow: function(param, isBackPage) {
        if($(".receiveAddress_warp").find("selected")) {
            $(".addressArea_btn.selected").parent().parent().find(".photo_btn").addClass('selected'); 
        };
    },

    receiveSelected: function(btn) {  
        $(".receiveAddress_warp .selected").removeClass('selected');
        $(btn).parent().parent().find(".photo_btn").addClass('selected');
        $(btn).addClass('selected');
        var user={
            bssasUuid:$(btn).attr('data-uuid'),
            bssasUser: dataGet('userUuid')
        }

        var self = this;
        var url = WEB_URL + "busiShippingAddress/update/default";
        ajaxTool("post",user,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    //console.log(data);
                    
                }
            },true
        );
    },

    eiditAddress: function(btn) {
         Views.updateAddressView.willShow($(btn).attr('data-uuid'));
    },

    deleteAddress: function(btn) {
        var self=this;
        var url=WEB_URL+'busiShippingAddress/delete/one';
          ajaxTool("post",{bssasUuid:$(btn).attr('data-uuid')},url,
            function error(XMLHttpRequesbssasUuidt, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.listData = data.data; 
                    $(btn).parent().parent().parent().remove();                   
                }
            },true
        );
   },

    addAddress: function(btn) {
        Views.newAddressView.willShow();
    }   
});

/***********************编辑收货地址**********************/
Views.updateAddressView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'updateAddress'
    },

    
    willShow: function(param, isBackPage) {
       
        this.getAddress(param, isBackPage);
        this.uuid = param;

    },    


    didShow: function(param, isBackPage) {
        this.ajaxAddressData();
    },

    ajaxAddressData: function() {
        var provinceFlag = false, citieFlag = false, countyFlag = false;
        var provinces, citys, countys;
        var self = this;

        ajaxTool("get", null, WEB_URL + "coreRegion/province/list/1",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   provinceFlag = true;
                   provinces = data.data;
                   self.initArea(provinceFlag, citieFlag, countyFlag, provinces, citys, countys);
                }
            },true
        );

        ajaxTool("get", null, WEB_URL + "coreRegion/city/listAllCities",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   citieFlag = true;
                   citys = data.data;
                   self.initArea(provinceFlag, citieFlag, countyFlag, provinces, citys, countys);
                }
            },true
        );

        ajaxTool("get", null, WEB_URL + "coreRegion/district/listAllCounties",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   countyFlag = true;
                   countys = data.data;
                   self.initArea(provinceFlag, citieFlag, countyFlag, provinces, citys, countys);
                }
            },true
        );
    },

    initArea: function(provinceFlag, citieFlag, countyFlag, provinces, citys, countys) {
        if(provinceFlag && citieFlag && countyFlag) {
            $('#newArea').bind('click', function () {
                var iosSelect = new IosSelect(3, 
                    [provinces, citys, countys],
                    {
                        title: '地址选择',
                        itemHeight: 35,
                        parentIdName: 'crrgnParent',
                        idName: 'crrgnUuid',
                        valueName: 'crrgnName',
                        relation: [1, 1, 0, 0, 0],
                        oneLevelId: 110000,
                        callback: function (selectOneObj, selectTwoObj, selectThreeObj) {
                            $('#newArea').attr('oneUuid', selectOneObj.crrgnuuid);
                            $('#newArea').attr('twoUuid', selectTwoObj.crrgnuuid);
                            $('#newArea').attr('threeUuid', selectThreeObj.crrgnuuid);
                            $('#newArea').val(selectOneObj.crrgnname + ' ' + selectTwoObj.crrgnname + ' ' + selectThreeObj.crrgnname);
                        }
                });
            });
        }
    },

    getAddress:function(param) {
    var self=this;
        var url=WEB_URL+'busiShippingAddress/views';
          ajaxTool("post",{bssasUuid:param},url,
            function error(XMLHttpRequesbssasUuidt, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.show({address:data.data}); 
                }
            },true
        ); 
    },

     updateAddress: function(btn) {
        var bssasName =  $('#bssasName').val();//名字
        var bssasMobile =  $('#bssasMobile').val();//手机
        var bsarsZipCode = $('#bsarsZipCode').val();//邮编
        var bssasAddress= $('#bssasAddress').val();//地址
        var provincial=$('#newArea').val();//省市区
        var arr = provincial.split(' '); //根据空格截取
        var bssasProvinceName = arr[0];
        var bssasCityName = arr[1];
        var bssasCountyName = arr[2];

        var bssasProvince=$('#newArea').attr('oneUuid');//省名称
        var bssasCity=$('#newArea').attr('twoUuid');//市名称
        var bssasCounty=$('#newArea').attr('threeUuid');//区名称
        var information={
            bssasUuid:this.uuid,
            bssasName:bssasName,
            bssasMobile:bssasMobile,
            bsarsZipCode:bsarsZipCode,
            bssasProvince:bssasProvince,
            bssasProvinceName:bssasProvinceName,
            bssasCity:bssasCity,
            bssasCityName:bssasCityName,
            bssasCounty:bssasCounty,
            bssasCountyName:bssasCountyName,
            bssasAddress:bssasAddress
        }

       var myreg = /^(((17[0-9]{1})|(13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
       if(!myreg.test(bssasMobile)) 
       { 
           alert('请输入有效的手机号码！'); 
           return; 
       } 

        var re= /^[1-9][0-9]{5}$/
        if(!re.test(bsarsZipCode)){
            alert('请输入有效的邮编！'); 
            return; 
        }

       ajaxTool("POST", information, WEB_URL + "busiShippingAddress/update/busiShippingAddress",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    alert("修改成功")
                    backPage();

                }
            },true
        );

    }

});

/***********************新建收货地址**********************/
Views.newAddressView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'newAddress'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function(param, isBackPage) {
        this.ajaxAddressData();
    },

    ajaxAddressData: function() {
        var provinceFlag = false, citieFlag = false, countyFlag = false;
        var provinces, citys, countys;
        var self = this;

        ajaxTool("get", null, WEB_URL + "coreRegion/province/list/1",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   provinceFlag = true;
                   provinces = data.data;
                   self.initArea(provinceFlag, citieFlag, countyFlag, provinces, citys, countys);
                }
            },true
        );

        ajaxTool("get", null, WEB_URL + "coreRegion/city/listAllCities",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   citieFlag = true;
                   citys = data.data;
                   self.initArea(provinceFlag, citieFlag, countyFlag, provinces, citys, countys);
                }
            },true
        );

        ajaxTool("get", null, WEB_URL + "coreRegion/district/listAllCounties",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   countyFlag = true;
                   countys = data.data;
                   self.initArea(provinceFlag, citieFlag, countyFlag, provinces, citys, countys);
                }
            },true
        );
    },

    initArea: function(provinceFlag, citieFlag, countyFlag, provinces, citys, countys) {
        if(provinceFlag && citieFlag && countyFlag) {
            $('#newArea').bind('click', function () {
                var iosSelect = new IosSelect(3, 
                    [provinces, citys, countys],
                    {
                        title: '地址选择',
                        itemHeight: 35,
                        parentIdName: 'crrgnParent',
                        idName: 'crrgnUuid',
                        valueName: 'crrgnName',
                        relation: [1, 1, 0, 0, 0],
                        oneLevelId: 110000,
                        callback: function (selectOneObj, selectTwoObj, selectThreeObj) {
                            $('#newArea').attr('oneUuid', selectOneObj.crrgnuuid);
                            $('#newArea').attr('twoUuid', selectTwoObj.crrgnuuid);
                            $('#newArea').attr('threeUuid', selectThreeObj.crrgnuuid);
                            $('#newArea').val(selectOneObj.crrgnname + ' ' + selectTwoObj.crrgnname + ' ' + selectThreeObj.crrgnname);
                        }
                });
            });
        }
    },

        //添加收获地址
    newAddress: function(btn) {

      var bssasName =  $('#bssasName').val();//名字
      var bssasMobile =  $('#bssasMobile').val();//手机
      var bsarsZipCode = $('#bsarsZipCode').val();//邮编
      var bssasAddress= $('#bssasAddress').val();//地址


      var provincial=$('#newArea').val();//省市区
      var arr = provincial.split(' '); //根据空格截取
      var bssasProvinceName = arr[0];
      var bssasCityName = arr[1];
      var bssasCountyName = arr[2];

      var bssasProvince=$('#newArea').attr('oneUuid');//省名称
      var bssasCity=$('#newArea').attr('twoUuid');//市名称
      var bssasCounty=$('#newArea').attr('threeUuid');//区名称
                
      var information={
        bssasUser: dataGet('userUuid'),
        bssasName:bssasName,
        bssasMobile:bssasMobile,
        bsarsZipCode:bsarsZipCode,
        bssasProvince:bssasProvince,
        bssasProvinceName:bssasProvinceName,
        bssasCity:bssasCity,
        bssasCityName:bssasCityName,
        bssasCounty:bssasCounty,
        bssasCountyName:bssasCountyName,
        bssasAddress:bssasAddress
        };

        if( bssasName == ""){
            alert("请输入姓名");
            return;
        }

       var myreg = /^(((17[0-9]{1})|(13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
       if(!myreg.test(bssasMobile)) 
       { 
           alert('请输入有效的手机号码'); 
           return; 
       } 

        // var re= /^[1-9][0-9]{5}$/
        // if(!re.test(bsarsZipCode)){
        //     alert('请输入有效的邮编'); 
        //     return; 
        // }

        if( bssasAddress == ""){
            alert("请输入详细地址");
            return;
        }

        ajaxTool("POST", information, WEB_URL + "busiShippingAddress/add/busiShippingAddress",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   backPage();
                }
            },true
        );

    }
});

/***********************账户设置**********************/
Views.accountSettingView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'accountSetting'
    },

    willShow: function(param, isBackPage) {
        var userInfo = JdataGet('userInfo');
        this.show({userInfo: userInfo}, isBackPage);
        this.headPath = WEB_URL + "coreAttachment/image/get/" + userInfo.crusrHead;
    },

    didShow: function(param, isBackPage) {
        this.initDateWidget();
        this.ajaxAddressData();

        if(this.headPath == WEB_URL + "coreAttachment/image/get/" + undefined){
            $('#img_head_pic').css({
                'background':'../images/mine/img-head-picture.png',
                '-webkit-background-size': '46px 46px',
                'background-size': '46px 46px'

            });
        }else{
            $('#img_head_pic').css({
                'background':'url("'+this.headPath+'")',
                '-webkit-background-size': '46px 46px',
                'background-size': '46px 46px'
            });
        }

        var upHead = Views.upHead.init('uploadphoto');
        $('#img_head_pic').on('change',function() {
            layer.open({shadeClose: false,type: 2});
            upHead.show(
                {url: WEB_URL + "coreAttachment/stream/upload",param:{cratmType:1, cratmDir:"1", fileName:"1"}, width: 150,
        height: 150},
                function(returnImagData, ajaxResult) {//图片数据， 上传图片后返回的ajax数据
                    $('#img_head_pic').css({
                        'background':'url("'+returnImagData+'")',
                        '-webkit-background-size': '46px 46px',
                        'background-size': '46px 46px'
                    });
                    $('#img_head_pic').attr('data-uuid', ajaxResult.data);
                    Views.upHead.hide();  
                }
            );
        })
        
    },

    ajaxAddressData: function() {
        var provinceFlag = false, citieFlag = false, countyFlag = false;
        var provinces, citys, countys;
        var self = this;

        ajaxTool("get", null, WEB_URL + "coreRegion/province/list/1",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   provinceFlag = true;
                   provinces = data.data;
                   self.initArea(provinceFlag, citieFlag, countyFlag, provinces, citys, countys);
                }
            },true
        );

        ajaxTool("get", null, WEB_URL + "coreRegion/city/listAllCities",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   citieFlag = true;
                   citys = data.data;
                   self.initArea(provinceFlag, citieFlag, countyFlag, provinces, citys, countys);
                }
            },true
        );

        ajaxTool("get", null, WEB_URL + "coreRegion/district/listAllCounties",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   countyFlag = true;
                   countys = data.data;
                   self.initArea(provinceFlag, citieFlag, countyFlag, provinces, citys, countys);
                }
            },true
        );
    },

    initArea: function(provinceFlag, citieFlag, countyFlag, provinces, citys, countys) {
        
        // $('#area').val(userInfo.crusrProvinceName + ' ' + userInfo.crusrCityName + ' ' + userInfo.crusrDistrictName);
        if(provinceFlag && citieFlag && countyFlag) {
            $('#area').bind('click', function () {
                var userInfo = JdataGet('userInfo');
                var oneLevelId = userInfo.crusrProvince?userInfo.crusrProvince: 110000;
                var twoLevelId = userInfo.crusrCity?userInfo.crusrCity: '';
                var threeLevelId = userInfo.crusrDistrict?userInfo.crusrDistrict: '';
                
                var iosSelect = new IosSelect(3, 
                    [provinces, citys, countys],
                    {
                        title: '地址选择',
                        itemHeight: 35,
                        parentIdName: 'crrgnParent',
                        idName: 'crrgnUuid',
                        valueName: 'crrgnName',
                        relation: [1, 1, 0, 0, 0],
                        oneLevelId: oneLevelId,//userInfo:crusrProvince   110000,
                        twoLevelId: twoLevelId,
                        threeLevelId: threeLevelId,
                        callback: function (selectOneObj, selectTwoObj, selectThreeObj) {
                            $('#area').attr('crusrProvince', selectOneObj.crrgnuuid);
                            $('#area').attr('crusrCity', selectTwoObj.crrgnuuid);
                            $('#area').attr('crusrDistrict', selectThreeObj.crrgnuuid);
                            $('#area').attr('crusrProvinceName', selectOneObj.crrgnname);
                            $('#area').attr('crusrCityName', selectTwoObj.crrgnname);
                            $('#area').attr('crusrDistrictName', selectThreeObj.crrgnname);
                            
                            $('#area').val(selectOneObj.crrgnname + ' ' + selectTwoObj.crrgnname + ' ' + selectThreeObj.crrgnname);
                        }
                });
            });
        }
    },

    changeName: function() {
        Views.changeInfo.show({
            title:'修改昵称',
            type:'text',
            hit: '请输入新的昵称',
            content: $('#name').html()
        },function(value){
            Views.changeInfo.hide();
        },function(value){
            var returnValue = $('#result').val();
            var obj1 = returnValue.replace(/(.)(?=[^$])/g,"$1,").split(",");
                if(returnValue.length<4) {
                    alert("昵称不能少于4位数，请重新输入！");
                    $('#result').val("").focus();
                    $('#name').val("");
                    return;
                }else if (returnValue.length>11) {
                    alert("昵称不能多于11位数，请重新输入！");
                    $('#result').val("").focus();
                    $('#name').val("");
                    return;
                }else if (!isNaN(obj1[0])){
                    alert("昵称开头不能为数字，请重新输入！");
                    $('#result').val("").focus();
                    $('#name').val("");
                    return;
                }else{
                    $('#name').html(value);
                    Views.changeInfo.hide();
                }
        });
    },

    changeSexLayer: function() {
        var self = this;
        Views.changeSex.show(
            function(result) {
                $('#sex').html(result);
            }
        );
    },

    initDateWidget: function() {
        var self = this;
        var curr = new Date().getFullYear();
        var fun = function () {
            var test = $('#birth').scroller('destroy').scroller({
                preset: 'date',
                dateFormat : 'yy-mm-dd', // 日期格式 
                dateOrder : 'yymmdd', //面板中日期排列格式 
                lang: 'zh',
                onSelect:function(valueText,inst){
                    return true;
                }
            });
        }
        fun();
    },

    changePhone: function() {
        Views.changeInfo.show({
            title:'修改手机号码',
            type:'number',
            hit: '请输入新的手机号',
            content: $('#phoneNo').html()
        },function(value){
            Views.changeInfo.hide();
        },function(value){
            $('#phoneNo').html(value);
            Views.changeInfo.hide();
        });
    },

    personSave: function(btn) {
        var param = {
            crusrUuid: dataGet('userUuid'),
            crusrName: $('#name').html(),
            crusrCode: dataGet('userCode'),
            crusrHead: $('#img_head_pic').attr('data-uuid'),
            crusrProvince: $('#area').attr('crusrProvince'),
            crusrProvinceName: $('#area').attr('crusrProvinceName'),
            crusrCity: $('#area').attr('crusrCity'),
            crusrCityName: $('#area').attr('crusrCityName'),
            crusrDistrict: $('#area').attr('crusrDistrict'),
            crusrDistrictName: $('#area').attr('crusrDistrictName'),
            crusrMobile: $('#phoneNo').html(),
            crusrGender: $('#sex').html()=='男'? 1: 2,
            crusrBirthday: $('#birth').val()  
        }

        ajaxTool("post", param, WEB_URL + "coreUser/update/coreUser",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   backPage();
                }
            },true
        );
    }

});

