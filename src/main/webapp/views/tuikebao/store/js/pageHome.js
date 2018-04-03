/***********************首页**********************/
Views.indexView =  $.extend({}, Views.PanelView, {
 options: {
        tmpl: 'index',
        hasFootNav: true,
        footItemOrder:0, // hasFootNav设置true才有效   表示现在远着的是底部第几个菜单 0开始
        itemClass: 'item'
    },

    willShow: function(param, isBackPage) {
        var self = this;

        commLogin();

        //查询用户信息
        // var data = {crusrUuid:  dataGet('userUuid')}
        // // var data = {crusrUuid:  'AAAAA'}
        // var url = WEB_URL + "coreUser/views";
        // ajaxTool("post",data,url,
        //     function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
        //         alert("error:请求失败");
        //     },
        //     function success(data){
        //         if(!data.success) {
        //             alert(data.errMsg);
        //         }else{
        //             fl = true;
        //             self.crusrGrade ="grade_"+data.data.crusrGrade;
        //             dataSave("crusrGrade",self.crusrGrade);                
        //         }
        //     },true
        // );
        // if(dataGet('isNotLogin')=="true"&&(dataGet('code')&&dataGet('code')!='null')){
            // var param = {code: dataGet('code')};
            // var urlOne = WEB_URL + "coreUser/click/store/index";
            // ajaxTool("post",param,urlOne,
            //     function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
            //         alert("error:请求失败");
            //     },
            //     function success(data){
            //         if(!data.success) {
            //             alert(data.errMsg);
            //         }else{
            //             JdataSave('userInfo', data.data);
            //             dataSave('userUuid', data.data.crusrUuid);
            //             dataSave('userCode', data.data.crusrCode);
            //             self.getAllDesc(isBackPage);
            //             dataSave('isNotLogin', "false");
            //         }
            //     },true
            // );



            //=====================================本地浏览器测试使用
            // var data = {crusrUuid: '170322093746q3c1'};
            // ajaxTool("post", data, WEB_URL + "coreUser/views",
            //     function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
            //         alert("error:" + data);
            //     },
            //     function success(data){
            //         if(!data.success) {
            //             alert(data.errMsg);
            //         }else{
            //             JdataSave('userInfo', data.data);
            //             dataSave('userUuid', data.data.crusrUuid);
            //             dataSave('userCode', data.data.crusrCode);
            //             self.getAllDesc(isBackPage);
            //             dataSave('isNotLogin', "false");


            //         }
            //     },true
            // );
        // }else {
            // self.getAllDesc(isBackPage);
        // }
        
        self.getAllDesc(isBackPage);
    },

    getAllDesc: function(isBackPage) {
        var self = this;

        //微信关注
        var urlOne = WEB_URL + "coreWechat/views";
        data ={
            crwctUuid:"161126131423yqiZ"
        }
        ajaxTool("post",data,urlOne,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.Wechat = data.data;
                    // console.log(self.Wechat);          
                }
            },true,true
        );

        //分类
        var urlOne = WEB_URL + "busiProductCategory/find/have/child";
        ajaxTool("post",null,urlOne,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.classification = data.data;
                    //self.show({ classification: self.classification, advertising: self.classification, banner: self.banner, commodity: self.commodity ,  WEB_URL: WEB_URL},isBackPage);
                }
            },true,true
        );

        // //广告图
        // var urlTwo = WEB_URL + "busiAdvert/find/all";
        // ajaxTool("post",null,urlTwo,
        //     function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
        //         alert("error:请求失败");
        //     },
        //     function success(data){
        //         if(!data.success) {
        //             alert(data.errMsg);
        //         }else{
        //             self.advertising = data.data;
        //         }
        //     },true,true
        // );

        //banner
        var urlThree = WEB_URL + "busiBanner/find/all/by/store";
        ajaxTool("post",null,urlThree,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.banner = data.data;  
                    self.show({Wechat: self.Wechat, classification: self.classification,  banner: self.banner, WEB_URL: WEB_URL},isBackPage);

                }
            },true,true
        );

        // //商品
        // var urlFour = WEB_URL + "busiIndexProduct/find/by/cnd/before";      
        // ajaxTool("post",null,urlFour,
        //     function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
        //         alert("error:请求失败");
        //     },
        //     function success(data){
        //         if(!data.success) {
        //             alert(data.errMsg);
        //         }else{
        //             self.commodity = data.data.result;
        //             self.show({ classification: self.classification, advertising: self.classification, banner: self.banner, commodity: self.commodity , WEB_URL: WEB_URL},isBackPage);
        //             if(dataGet('goProducrDescFlag')=="true") {
        //                 Views.productDescView.willShow();
        //             }
                                        
        //         }
        //     },true,true
        // );
    },

    //商品
    scrollInitRender: function() {
        this.pageNum=1;
        this.getServiceList("scrollInit", 1, 10);



        var typeIscroll = new iScroll($('#h_wrapper')[0], {
            bounce: false,
            snap: true,
            preventDefault:false,
            momentum: false,
            hScrollbar: false,
            vScrollbar: false,
            checkDOMChanges: true,
            onBeforeScrollStart: null,//解决上下 左右不能一起滑动的冲突
            onScrollEnd: function(e) {
                $(".type_dot_border .selected").removeClass("selected");
                $(".type_dot_border .type_dot").eq(this.currPageX).addClass("selected");
            }
        });
    },

    scrollDownRender: function() {
        this.pageNum=1;
        this.getServiceList("scrollDown", 1, 10);
    },

    scrollUpRender: function() {
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            this.getServiceList("scrollUp", this.pageNum, 10);
        }else{
        	$('#pullUp-msg').html("我也是有底线的！");
        }
		
    },

    getServiceList: function(type,pageNum,pageSize) {
        var self = this;
        if(!pageNum){
            pageNum=1;
        }
        if(!pageSize){
            pageSize=10;
        }
        var data = {
            //bsprtCategory: dataGet('bspcyUuid'),
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiIndexProduct/find/by/cnd/before"; 
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                } else {
					self.lastPageNumber = data.data.lastPageNumber;
                    self.commodity = data.data.result;  				
                    if (type == "scrollInit") {                          
                        self.scrollInit('indexItem',{commodity: self.commodity ,  WEB_URL: WEB_URL}); 
                        $('#index_slides').slidesjs({
                            width: 375,
                            height: 230,
                            navigation: false,
                            play: {
                                active: false,
                                auto: true,
                                interval: 4000,
                                swap: true
                            }
                        });  

                    };
                    if (type == "scrollDown") {                         
                        self.scrollDown('indexItem',{commodity: self.commodity ,  WEB_URL: WEB_URL});
                        // $('#index_slides').slidesjs({
                        //     width: 375,
                        //     height: 230,
                        //     navigation: false,
                        //     play: {
                        //         active: false,
                        //         auto: true,
                        //         interval: 4000,
                        //         swap: true
                        //     }
                        // });                        
                    };
                    if (type == "scrollUp") {                        
                        self.scrollUp('indexItem',{commodity: self.commodity , WEB_URL: WEB_URL});                        
                    };
                }
            }
        );
    },

    didShow: function() {
        // $('#index_slides').slidesjs({
        //     width: 375,
        //     height: 230,
        //     navigation: false,
        //     play: {
        //         active: false,
        //         auto: true,
        //         interval: 4000,
        //         swap: true
        //     }
        // });

        // var typeIscroll = new iScroll($('#h_wrapper')[0], {
        //     bounce: false,
        //     snap: true,
        //     preventDefault:false,
        //     momentum: false,
        //     hScrollbar: false,
        //     vScrollbar: false,
        //     checkDOMChanges: true,
        //     onBeforeScrollStart: null,//解决上下 左右不能一起滑动的冲突
        //     onScrollEnd: function(e) {
        //         $(".type_dot_border .selected").removeClass("selected");
        //         $(".type_dot_border .type_dot").eq(this.currPageX).addClass("selected");
        //     }
        // });
    },

    goProductType: function(btn) {
        var self = this;
        var bspcyTop = $(btn).attr('data-Uuid');
        dataSave('bspcyTop', bspcyTop)
        Views.productTypeView.willShow();
    },


    goBuy: function(btn) {
        dataSave('productUuid', $(btn).attr('data-uuid'));
        Views.productDescView.willShow();
    },


    ////////微信关注/////

    followButton:function(btn){
        $('#followContent').show();

    },

    contentClose:function(){
        $('#followContent').hide();
    }
    

});

/***********************分类**********************/
Views.productTypeView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'productType',
        hasFootNav: false,
        // footItemOrder:0, // hasFootNav设置true才有效   表示现在远着的是底部第几个菜单 0开始
        // itemClass: 'item'
    },

    willShow: function(param,isBackPage) {
        var self = this;     
        var data = {bspcyTop: dataGet('bspcyTop')};
        var urlOne = WEB_URL + "busiProductCategory/find/all/child/by/top";
        ajaxTool("post",data,urlOne,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{  
                    self.subClass = data.data;
                    if( data.data.length != 0){
                        dataSave('bspcyUuid',  data.data[0].bspcyUuid); 
                    }else{
                        dataSave('bspcyUuid',null); 
                    }

                    //self.show({ subClass: self.subClass, banner: self.banner, WEB_URL: WEB_URL}, isBackPage);
                                    
                }
            },true,true
        );

        //banner
        var urlTwo = WEB_URL + "busiBanner/find/all/by/store";
        ajaxTool("post",null,urlTwo,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){;
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.banner = data.data;
                    self.show({ subClass: self.subClass, banner: self.banner, WEB_URL: WEB_URL}, isBackPage);                      
                }
            },true,true
        );           
    },

    scrollInitRender: function() {
        this.pageNum=1;
        this.getServiceList("scrollInit", 1, 10);
    },

    scrollDownRender: function() {
        this.pageNum=1;
        this.getServiceList("scrollDown", 1, 10);
    },

    scrollUpRender: function() {
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            this.getServiceList("scrollUp", this.pageNum, 10);
        }else{
        	$('#pullUp-msg').html("我也是有底线的！");
        }
    },

    getServiceList: function(type,pageNum,pageSize) {
        var self = this;
        if(!pageNum){
            pageNum=1;
        }
        if(!pageSize){
            pageSize=10;
        }
        var data = {
            bsprtCategory: dataGet('bspcyUuid'),
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiProduct/find/by/category";
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
                        self.scrollInit('productTypeItem',{bsprtCategory: data.data.result, WEB_URL: WEB_URL});   
                    };
                    if (type == "scrollDown") {                         
                        self.scrollDown('productTypeItem',{bsprtCategory: data.data.result, WEB_URL: WEB_URL});                        
                    };
                    if (type == "scrollUp") {                        
                        self.scrollUp('productTypeItem',{bsprtCategory: data.data.result, WEB_URL: WEB_URL});                        
                    };
                }
            }
        );
    },

    didShow: function() {
        $('#index_slides').slidesjs({
            width: 375,
            height: 230,
            navigation: false,
            play: {
                active: false,
                auto: true,
                interval: 4000,
                swap: true
            }
        });

        var typeIscroll = new iScroll($('#type_page_wrapper')[0], {
            bounce: false,
            snap: false,
            preventDefault:false,
            momentum: false,
            hScrollbar: false,
            vScrollbar: false,
            checkDOMChanges: true,
            onBeforeScrollStart: null,//解决上下 左右不能一起滑动的冲突
        });

        $(window).off('scroll').on('scroll',function(){
            var mTop = $(window).scrollTop();
            var h = $('#index_slides').height() + 12;

            if(mTop>h){
                if(!$('#type_page_wrapper').hasClass('fixed')){
                    $('#type_page_wrapper').addClass('fixed');
                    $('.productType_placeholder').show();
                }
            }else {
                if($('#type_page_wrapper').hasClass('fixed')){
                    $('#type_page_wrapper').removeClass('fixed');
                    $('.productType_placeholder').hide();
                }
            }
        });
    },

    typeSelect: function(btn) {
        $('.productType_border .selected').removeClass('selected');
        $(btn).addClass('selected');
        var bspcyUuid = $(btn).attr('data-uuid');
        dataSave('bspcyUuid',  bspcyUuid); 
        this.scrollInitRender();
    },

    goBuy: function(btn) {
        dataSave('productUuid', $(btn).attr('data-uuid'));
        Views.productDescView.willShow();
    },

});

/***********************热榜**********************/
Views.hotListView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'hotList',
        hasFootNav: true,
        footItemOrder:1, // hasFootNav设置true才有效   表示现在远着的是底部第几个菜单 0开始
        itemClass: 'item'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
        
    },

    goProductDesc: function(btn) {
        Views.productDescView.show();
    },

    scrollInitRender: function() {
        this.pageNum=1;
        this.getServiceList("scrollInit", 1, 10);
    },

    scrollDownRender: function() {
        this.pageNum=1;
        this.getServiceList("scrollDown", 1, 10);
    },

    scrollUpRender: function() {
		console.log(this);
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            this.getServiceList("scrollUp", this.pageNum, 10);
        }else{
        	$('#pullUp-msg').html("我也是有底线的！");
        }
    },

    getServiceList: function(type,pageNum,pageSize) {
        var self = this;
        if(!pageNum){
            pageNum=1;
        }
        if(!pageSize){
            pageSize=10;
        }
        var data = {
            bsprtCategory: dataGet('bspcyUuid'),
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiHotList/find/by/cnd/before";
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
                        self.scrollInit('hotListItem',{hotList: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollDown") {                         
                        self.scrollInit('hotListItem',{hotList: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollUp") {                        
                        self.scrollInit('hotListItem',{hotList: data.data.result, WEB_URL: WEB_URL});
                    };
                }
            }
        );
    },

    goBuy: function(btn) {
        dataSave('productUuid', $(btn).attr('data-uuid'));
        Views.productDescView.willShow();
    }
});