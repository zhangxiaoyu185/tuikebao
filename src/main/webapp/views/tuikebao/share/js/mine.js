/***********************我的订单**********************/
Views.myOrderView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myOrder'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
        
    },

    myOrderType: function(btn) {
    	$('.myOrder_type_border .selected').removeClass('selected');
    	$(btn).addClass('selected');
    }
});

/***********************申请提现**********************/
Views.cashWithdrawalView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'cashWithdrawal'
    },

    willShow: function(param, isBackPage) {
        var self = this;
        var data = {
            bspioUser: dataGet('userUuid'),
            mobileCode:dataGet('crusrMobile')
    };
        ajaxTool("post", data, WEB_URL + "busiCashRecord/go/promoterInfo",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   self.show({cashInfo: data.data}, isBackPage);
                }
            },true
        );
    },

    didShow: function() {
        this.countdown=60;
        
    },
    
    judge:function() {
        var phone = $('#phoneNo').val();
        var a=false;
        if(phone==0) {
            a=confirm("您的手机号信息尚未完善");
            Views.accountSettingView.willShow();
        }else{
             backPage();
        }
      },

    sumbitApply: function()  {
        var self=this; 

        var data = {
            bscrdAmount: $('#money').val(),
            bscrdExtracted: dataGet('userUuid'),
            bscrdMobile: $('#phoneNo').val(),
            mobileCode: $('#checkCode').val(),
            bscrdAccountType: $('#payType').val(),
            bscrdAlipayNo: $('#bscrdAlipayNo').val(),
            bscrdAlipayName: $('#bscrdAlipayName').val()
        };

        ajaxTool("post", data, WEB_URL + "busiCashRecord/add/busiCashRecord",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    //self.judge();
                    alert(data.errMsg);
             
            }else{
                    //backPage();                  
                    alert(data.errMsg);

                }
            },true
        );


    },



    sendVerificationCode:function (btn) {
        var self=this; 
        
        if(this.countdown == 60){
            var phoneNo = $('#phoneNo').val();
            var data = {
                crmceUser: dataGet('userUuid'),
                crmceMobile:phoneNo
            };
            ajaxTool("post", data, WEB_URL + "coreShortMessage/add/coreShortMessage",
                function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                    alert("error:" + data);
                },
                function success(data){
                    if(!data.success) {
                        alert(data.errMsg);
                    }else{             
                       //self.show({userInfo: data.data, WEB_URL: WEB_URL}, isBackPage);
                       
                    }
                },true
            ); 
        }

        if (this.countdown == 0) { 
            $(btn).removeAttr("disabled");
            $(btn).val("发送验证码"); 

            clearTimeout(function(){
                self.sendVerificationCode(btn);});
            this.countdown = 60;

        } else { 
            $(btn).attr("disabled",true); 
            $(btn).val("重新发送(" + this.countdown + ")"); 
            this.countdown--; 

            setTimeout(function(){
                self.sendVerificationCode(btn);
            },1000);
        }        
    },                                   
    
});

/***********************个人中心**********************/
Views.personalCenterView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'personalCenter',
        hasFootNav: true,
        footItemOrder:4, // hasFootNav设置true才有效   表示现在选择的是底部第几个菜单 0开始
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
                   self.crusrHead = data.data.crusrHead;
                   //dataSave('crusrHead',data.data.crusrHead);
                   JdataSave('userInfo', data.data);
                   dataSave('userUuid', data.data.crusrUuid);
                   dataSave('userCode', data.data.crusrCode);                  
                   self.show({userInfo: data.data, WEB_URL: WEB_URL}, isBackPage);
                }
            },true
        );

    },

    didShow: function() {
    	//var pic = dataGet('crusrHead');
        var pic = this.crusrHead;  
        if(!pic) {
            $("#headPic").attr('src','./images/mine/img-head-picture.png')
        }
    },

    toAccountSetting: function(btn) {
        Views.accountSettingView.willShow();
    },

    toUpGradeView: function(btn) {
         Views.upGradeView.willShow($(btn).attr('data-uuid'));
    },

    toCreditsExchange: function()  {
        Views.creditsExchangeView.willShow();
    },

    toIncomeDetails: function()  {
        Views.incomeDetailsView.willShow();
    },

    toIntegralDetails: function()  {
        Views.integralDetailsView.willShow();
    },

    toPresentationRecord: function()  {
        Views.presentationRecordView.willShow();
    },
    
    toShareRecord: function()  {
        Views.shareRecordView.willShow();
    },

    toMessageCenter: function()  {
        Views.messageCenterView.willShow();
    },

    toCashWithdrawal: function()  {
        Views.cashWithdrawalView.willShow();
    },
    
    toRecordConversion: function()  {
        Views.recordConversionView.willShow();
    },

    shareRecord: function(btn)  {//分享记录
        $('#shareRecord span').removeClass('personalCenter_change');
        $(btn).addClass('personalCenter_change');

        var userInfo = JdataGet('userInfo');
        var type = $(btn).attr('data-type');
        if(type == "now"){
            $('#shareNumber').html(userInfo.crusrNowShare);
            $('#shareMoney').html(userInfo.crusrNowAddincome);
            $('#shareFinish').html(userInfo.crusrNowFinish);
            $('#shareIntegral').html(userInfo.crusrNowAddintegral);
        }
        if(type == "yesterday"){
            $('#shareNumber').html(userInfo.crusrYestShare);
            $('#shareMoney').html(userInfo.crusrYestAddincome);
            $('#shareFinish').html(userInfo.crusrYestFinish);
            $('#shareIntegral').html(userInfo.crusrYestAddintegral);
        }
        if(type == "month"){
            $('#shareNumber').html(userInfo.crusrMonthShare);
            $('#shareMoney').html(userInfo.crusrMonthAddincome);
            $('#shareFinish').html(userInfo.crusrMonthFinish);
            $('#shareIntegral').html(userInfo.crusrMonthAddintegral);
        }
    },

    moneyRecord: function(btn)  {//提现记录
        $('#moneyRecord span').removeClass('personalCenter_change');
        $(btn).addClass('personalCenter_change');

        var userInfo = JdataGet('userInfo');
        var type = $(btn).attr('data-type');
        if(type == "now"){
            $('#moneyNumber').html(userInfo.crusrNowCashcount);
            $('#moneySum').html(userInfo.crusrNowCashfee);
        }
        if(type == "yesterday"){
            $('#moneyNumber').html(userInfo.crusrYestCashcount);
            $('#moneySum').html(userInfo.crusrYestCashfee);
        }
        if(type == "month"){
            $('#moneyNumber').html(userInfo.crusrMonthCashcount);
            $('#moneySum').html(userInfo.crusrMonthCashfee);
        }
    },

    swopRecord: function(btn)  {//兑换记录
        $('#swopRecord span').removeClass('personalCenter_change');
        $(btn).addClass('personalCenter_change');

        var userInfo = JdataGet('userInfo');
        var type = $(btn).attr('data-type');
        if(type == "now"){
            $('#swopNumber').html(userInfo.crusrNowExchcount);
            $('#swopSum').html(userInfo.crusrNowExchintegral);
        }
        if(type == "yesterday"){
            $('#swopNumber').html(userInfo.crusrYestExchcount);
            $('#swopSum').html(userInfo.crusrYestExchintegral);
        }
        if(type == "month"){
            $('#swopNumber').html(userInfo.crusrMonthExchcount);
            $('#swopSum').html(userInfo.crusrMonthExchintegral);
        }
    },
    
    clickBtn:function(btn) {//签到事件
        var self = this;
        var data={bssrdUser:dataGet('userUuid')}
        var url = WEB_URL + "busiSignRecord/sign";
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{ 
                    if( data.errMsg == "签到成功!"){
                        var addIntegral = parseInt(data.data.addIntegral);
                        $(".depressed").html("+"+addIntegral);
                        $(".depressed").show();
                        var integral = $(".rg .personalCenter_change").html();  
                        $(".rg .personalCenter_change").html(parseInt(integral)+addIntegral) 
                        alert(data.errMsg); 
                    }  
                }
            }
        );
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
            Views.personalCenterView.willShow();
          }
        
    }
});

/***********************升级**********************/
Views.upGradeView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'upGrade'
    },

    willShow: function(param, isBackPage) {
        this.toUpgrade(param, isBackPage);


    },
    didShow: function(){
        this.progressBar();
        this.judgmentUpgrade();
    },

    toUpgrade:function(param){
        var self = this;
        var url = WEB_URL + "coreUser/go/upgrade";
            ajaxTool("post",{crusrUuid:param},url,
                function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                    alert("error:请求失败");
                },
                function success(data){
                    if(!data.success) {
                        alert(data.errMsg);
                    }else{
                         if(data.code==2){
                            alert('你当前已是最大等级!无需升级！')
                        }else{
                         self.show({userInfo:data.data,WEB_URL: WEB_URL});
                         dataSave('crusrIntegral',data.data.crusrIntegral);
                         dataSave('nextCrgdeIntegral',data.data.nextCrgdeIntegral);
                        }
                    }
                },true
            );
    },

    judgmentUpgrade:function(){
        var integral =parseInt(dataGet('crusrIntegral'));//你拥有的积分
        var amount =parseInt( dataGet('nextCrgdeIntegral'));//你需要达到的积分值
        if(integral<amount) {
            $(".upGrade_btn").css("background","#c0c0c0","pointer-events","none");
       }

    },

    progressBar:function() {
        var grade = dataGet('crusrIntegral');//你拥有的积分
        var needAccess = dataGet('nextCrgdeIntegral');//你需要达到的积分值
        var num=grade/needAccess;
		if(num == 0) {
            $(".bar").css("width","0%");
            $(".bar img").css("display","none");
            $(".bar span").css("position","absolute");
            $(".upGrade_rectangle").css("position","relation");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#ffcb02))");
        }else if(num <= 0.005) {
            $(".bar").css("width","2%");
            $(".bar img").css("display","none");
            $(".bar span").css("position","absolute");
            $(".upGrade_rectangle").css("position","relation");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#ffc502))");
        }else if(num <= 0.01 && num>0.005) {
            $(".bar").css("width","6%");
            $(".bar img").css("display","none");
            $(".bar span").css("position","absolute");
            $(".upGrade_rectangle").css("position","relation");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#ffc502))");
        }else if(num <= 0.05 && num>0.01) {
            $(".bar").css("width","8%");
            $(".bar img").css("display","none");
            $(".bar span").css("position","absolute");
            $(".upGrade_rectangle").css("position","relation");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#ffc502))");
        }else if(num <= 0.1 && num > 0.05) {
            $(".bar").css("width","10%");
            $(".bar span").css("position","absolute");
            $(".upGrade_rectangle").css("position","relation");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#ffc502))");
        }else if(num <= 0.2 && num >0.1) {
            $(".bar").css("width","20%");
            $(".bar span").css("position","absolute");
            $(".upGrade_rectangle").css("position","relation");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#ffa902))");
        }else if(num <= 0.3 && num >0.2) {
            $(".bar").css("width","30%");
            $(".bar span").css("position","absolute");
            $(".upGrade_rectangle").css("position","relation");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#fe9602))");
        }else if(num <= 0.4 && num >0.3) {
            $(".bar").css("width","40%");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#ff8402))");
        }else if(num <= 0.5 && num >0.4) {
            $(".bar").css("width","50%");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#ff8302))");
        }else if(num <= 0.6 && num >0.5) {
            $(".bar").css("width","60%");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#ff8002))");
        }else if(num <= 0.7 && num >0.6) {
            $(".bar").css("width","70%");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#fe7102))");
        }else if(num <= 0.8 && num >0.7) {
            $(".bar").css("width","80%");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#fe6302))");
        }else if(num <= 0.9 && num >0.8) {
            $(".bar").css("width","90%");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#ff5402))");
        }else if(num <= 1 && num >0.9) {
            $(".bar").css("width","100%");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#fe4602))");
        }else if(num >1) {
            $(".bar").css("width","100%");
            $(".bar").css("background","-webkit-gradient(linear, 0% 0%, 100% 0%, from(#ffcb02), to(#fe4602))");
        }
    },

    Upgrade:function(btn){
        var integral =parseInt(dataGet('crusrIntegral'));//你拥有的积分
        var amount =parseInt( dataGet('nextCrgdeIntegral'));//你需要达到的积分值
        if(integral<amount) {
            $(".upGrade_btn").css("background","#c0c0c0","pointer-events","none");
       }else if (integral>=amount) {            
             var self = this;
             var userUuid = dataGet('userUuid');
             var data={
                crusrUuid: userUuid,
                nextCrgdeUuid:$(btn).attr('data-nextCrgdeUuid')
             }

             var url = WEB_URL + "coreUser/upgrade";
                ajaxTool("post",data,url,
                    function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                        alert("error:请求失败");
                    },
                    function success(data){
                        if(!data.success) {
                            alert(data.errMsg);
                        }else{
                            if(data.code==2){
                                alert('你当前已是最大等级!无需升级！')
                            }else{
                             self.show({userInfo:data.data,WEB_URL: WEB_URL});
                             this.progressBar(); 
                            }
                        }
                    },true
                );
            }
        },

});

/***********************积分兑换**********************/
Views.creditsExchangeView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'creditsExchange'
    },

    willShow: function(param, isBackPage) {
        var self = this;
        var url = WEB_URL + "busiExchangeRule/find/all";
            ajaxTool("post",null,url,
                function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                    alert("error:请求失败");
                },
                function success(data){
                    if(!data.success) {
                        alert(data.errMsg);
                    }else{
                         self.show({integralList:data.data, WEB_URL: WEB_URL}, isBackPage); 
                    }
                },true
            );
        },

    didShow: function(){
            //numberAttribution();//查询页面加载时默认号码的归属地
            //查询页面加载后修改号码的归属地
            $('#attribution').keyup(function(){
                var number=$('#attribution').val();
                if(number.length == 11){
                     numberAttribution();
                }    
            });

            //号码归属地查询
            function numberAttribution(){                
                var exchangeNumber = $('#attribution').val();
                var self = this;
                var url = WEB_URL + "coreUser/mobile/attach";
                var data={mobileTel:exchangeNumber};
                    ajaxTool("post",data,url,
                        function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                            alert("error:请求失败");
                        },
                        function success(data){
                            if(!data.success) {
                                alert(data.errMsg);
                            }else{
                                $('#numberAttribution').html("号码归属地（"+data.data+")");
                            }
                        }
                    );
            }
    },

    confirm:function(btn){
        var bseeeUuid = self.uid;//兑换标识
        if( bseeeUuid == null){
            alert("请选择兑换金额");
            return;
        }
        var data={
            bseblUser: dataGet('userUuid'),
            bseblMobile:$('#attribution').val(),//兑换的手机号
            bseeeUuid:bseeeUuid
            }
            var url = WEB_URL + "busiExchangeBill/add/busiExchangeBill";
                ajaxTool("post",data,url,
                    function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                        alert("error:请求失败");
                    },
                    function success(data){
                        if(!data.success) {
                            alert(data.errMsg);
                        }else{
                            alert(data.errMsg);
                            backPage();
                            }
                        }
                    ); 
    },
   
    attrSelect: function(btn) {
      $(btn).parent('.creditsExchange_convert').find('.creditsExchange_bar').removeClass('selected');
      $(btn).addClass('selected');
      self.uid=$(btn).attr('data-Uuid');//获取兑换的标识
      var integral=$(btn).children('#integral').html();
      $('#wantIntegral').html(integral);
    },   
});

/***********************积分兑换**********************/
Views.creditsExchangeView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'creditsExchange'
    },

    willShow: function(param, isBackPage) {
        var self = this;
        var url = WEB_URL + "busiExchangeRule/find/all";
            ajaxTool("post",null,url,
                function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                    alert("error:请求失败");
                },
                function success(data){
                    if(!data.success) {
                        alert(data.errMsg);
                    }else{
                         self.show({integralList:data.data, WEB_URL: WEB_URL}, isBackPage); 
                    }
                },true
            );
        },

    didShow: function(){
            //查询页面加载后修改号码的归属地
            $('#attribution').keyup(function(){
                var number=$('#attribution').val();
                if(number.length == 11){
                     numberAttribution();
                }    
            });

            //号码归属地查询
            function numberAttribution(){                
                var exchangeNumber = $('#attribution').val();
                var self = this;
                var url = WEB_URL + "coreUser/mobile/attach";
                var data={mobileTel:exchangeNumber};
                    ajaxTool("post",data,url,
                        function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                            alert("error:请求失败");
                        },
                        function success(data){
                            if(!data.success) {
                                alert(data.errMsg);
                            }else{
                                $('#numberAttribution').html("号码归属地（"+data.data+")");
                            }
                        }
                    );
            }
    },

    //确认兑换
    confirm:function(btn){
        var bseeeUuid = self.uid;//兑换标识
        if( bseeeUuid == null){
            alert("请选择兑换金额");
            return;
        }
        var data={
            bseblUser: dataGet('userUuid'),
            bseblMobile:$('#attribution').val(),//兑换的手机号
            bseeeUuid:bseeeUuid
            }
            var url = WEB_URL + "busiExchangeBill/add/busiExchangeBill";
                ajaxTool("post",data,url,
                    function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                        alert("error:请求失败");
                    },
                    function success(data){
                        if(!data.success) {
                            alert(data.errMsg);
                        }else{
                            alert(data.errMsg);
                            backPage();
                            }
                        }
                    ); 
    },
   
    attrSelect: function(btn) {
      $(btn).parent('.creditsExchange_convert').find('.creditsExchange_bar').removeClass('selected');
      $(btn).addClass('selected');
      self.uid=$(btn).attr('data-Uuid');//获取兑换的标识
      var integral=$(btn).children('#integral').html();
      $('#wantIntegral').html(integral);
    },   
});

/***********************收益服务List**********************/
Views.incomeDetailsView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'incomeDetails',
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
      this.scrollInitRender();
    },
    
    scrollInitRender: function() {
        this.pageNum=1;
        this.getIncome("scrollInit", 1, 10);
    },

    scrollDownRender: function() {
        this.pageNum=1;
        this.getIncome("scrollDown", 1, 10);
    },

    scrollUpRender: function() {
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            this.getIncome("scrollUp", this.pageNum, 10);
        }else{
        	$('#pullUp-msg').html("我也是有底线的！");
        }
    },

    getIncome: function(type,pageNum,pageSize) {
        var self = this;
        if(!pageNum){
            pageNum=1;
        }
        if(!pageSize){
            pageSize=10;
        }
       
        var data = {
            bsprdUser: dataGet('userUuid'),
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiProfitRecord/my/find/by/cnd";
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                } else {
                    
                    self.lastPageNumber = data.data.lastPageNumber;
                    // var time = data.data.result[0].bsprdCdate;
                    // var timeArray=time.split(" ");
                    if (type == "scrollInit") {
                        self.scrollInit('incomeDetailsItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollDown") {
                        self.scrollDown('incomeDetailsItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollUp") {
                        self.scrollUp('incomeDetailsItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                }
            },true
        );
    },


});

/***********************积分明细**********************/
Views.integralDetailsView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'integralDetails'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
        // dataSave('bsirdUser', "AAAAA");
    },

    didShow: function() {
    },

    scrollInitRender: function() {
        this.pageNum=1;
        this.getServiceList("scrollInit", 1, 15);
    },

    scrollDownRender: function() {
        this.pageNum=1;
        this.getServiceList("scrollDown", 1, 15);
    },

    scrollUpRender: function() {
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            this.getServiceList("scrollUp", this.pageNum, 15);
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
            pageSize=15;
        }
        var data = {
            bsirdUser: dataGet('userUuid'),
            //bsirdUser: dataGet('bsirdUser'),
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiIntegralRecord/my/find/by/cnd";
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
                        self.scrollInit('integralDetailsItem',{exchangeDetail: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollDown") {
                        self.scrollDown('integralDetailsItem',{exchangeDetail: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollUp") {
                        self.scrollUp('integralDetailsItem',{exchangeDetail: data.data.result, WEB_URL: WEB_URL});
                    };
                }
            },true
        );
    },    
});


/***********************提现记录**********************/
Views.presentationRecordView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'presentationRecord'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
        
    },

    presentationType: function(btn) {
        $('#topSelect .selected').removeClass('selected');
        $(btn).addClass('selected');
        this.scrollDownRender();
    },

    getRecord:function() {

    },

    scrollInitRender: function() {
        this.pageNum=1;
        this.getPresentationList("scrollInit", 1, 10);
    },

    scrollDownRender: function() {
        this.pageNum=1;
        this.getPresentationList("scrollDown", 1, 10);
    },

    scrollUpRender: function() {
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            this.getPresentationList("scrollUp", this.pageNum, 10);
        }else{
        	$('#pullUp-msg').html("我也是有底线的！");
        }
    },

    getPresentationList: function(type,pageNum,pageSize) {
        var self = this;
        if(!pageNum){
            pageNum=1;
        }
        if(!pageSize){
            pageSize=10;
        }

        var bscrdStatus = $('#topSelect .selected').attr('data-type');
        var data = {
            bscrdExtracted: dataGet('userUuid'),
            bscrdStatus: bscrdStatus,
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiCashRecord/my/find/by/cnd";
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
                        self.scrollInit('presentationRecordItem',{list: data.data.result});
                    };
                    if (type == "scrollDown") {
                        self.scrollDown('presentationRecordItem',{list: data.data.result});
                    };
                    if (type == "scrollUp") {
                        self.scrollUp('presentationRecordItem',{list: data.data.result});
                    };
                }
            },true
        );
    },
    
});

/***********************分享记录**********************/
Views.shareRecordView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'shareRecord'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
        // dataSave('bssrdUser', "AAAAA");
    },

    didShow: function() {
        this.scrollInitRender();      
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
            bssrdUser: dataGet('userUuid'),
            //bssrdUser: dataGet('bssrdUser'),
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiShareRecord/my/find/by/cnd";
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
                        self.scrollInit('shareRecordItem',{shareRecordList: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollDown") {
                        self.scrollDown('shareRecordItem',{shareRecordList: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollUp") {
                        self.scrollUp('shareRecordItem',{shareRecordList: data.data.result, WEB_URL: WEB_URL});
                    };
                }
            },true
        );
    },
    
    
});

/***********************兑换记录**********************/
Views.recordConversionView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'recordConversion'
    },

    willShow: function(param, isBackPage) {
        // dataSave('bseblUser', "AAAAA");
        this.show(param, isBackPage);
    },

    didShow: function() {  

    },

    scrollInitRender: function() {
        this.pageNum=1;
        this.getServiceList("scrollInit", 1, 15);
    },

    scrollDownRender: function() {
        this.pageNum=1;
        this.getServiceList("scrollDown", 1, 15);
    },

    scrollUpRender: function() {
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            this.getServiceList("scrollUp", this.pageNum, 15);
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
            pageSize=15;
        }
        var data = {
            bseblUser: dataGet('userUuid'),
            //bseblUser: dataGet('bseblUser'),
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiExchangeBill/my/find/by/cnd";
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
                        self.scrollInit('recordConversionItem',{exchangeList: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollDown") {
                        self.scrollDown('recordConversionItem',{exchangeList: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollUp") {
                        self.scrollUp('recordConversionItem',{exchangeList: data.data.result, WEB_URL: WEB_URL});
                    };
                }
            },true
        );
    },   
});


/***********************消息中心**********************/
Views.messageCenterView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'messageCenter'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
        
    },

    scrollInitRender: function() {
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
            bsmecUser: dataGet('userUuid'),
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiMessageCenter/share/find/by/cnd";

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
                        self.scrollInit('messageCenterItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollDown") {
                        self.scrollDown('messageCenterItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollUp") {
                        self.scrollUp('messageCenterItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
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

        var pic = $("#img_head_pic").attr('src');
        
        if(pic == "") {
            $("#headPic").attr('background','url(./images/mine/img-head-picture.png)')
        }
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
                   dataSave('crusrMobile',param.crusrMobile);
                }
            },true
        );
    }

});
