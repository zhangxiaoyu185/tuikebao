/***********************邀请好友**********************/
Views.inviteFriendsView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'inviteFriends',
        hasFootNav: true,
        footItemOrder: 2, // hasFootNav设置true才有效   表示现在远着的是底部第几个菜单 0开始
        itemClass: 'item'
    },

    willShow: function(param, isBackPage) {
        // Views.loginPageView.willShow();

        var isLoginFlag = isLogin();
        if(!isLoginFlag) {
            return;
        }

        var self = this;
        var data = {
            crusrUuid: dataGet('userUuid')
        };

        //累计邀请
        ajaxTool("post", data, WEB_URL + "coreUser/views",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   self.totalInvitation = data.data.crusrInviteTotal;
                   self.crusrInviteNused = data.data.crusrInviteNused;
                   self.tcode = data.data.crusrInviteCode;
                   // self.show({totalInvitation : data.data});
                   self.invitationToday(isBackPage);
                }
            },true
        ); 
    },

    //今日邀请人数
    invitationToday: function(isBackPage){
        var self = this;
        var data = {
            bsirnInvited: dataGet('userUuid')
        };
        ajaxTool("post", data, WEB_URL + "busiInvitedRelation/now/day/invited/count",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.invitationTodayParam = data.data; 
                    self.show({totalInvitation : self.totalInvitation,invitationTodayParam: self.invitationTodayParam},isBackPage);
                }
            },true
        );
    },
    
    didShow: function() {
        initWx();  
        //链接前缀
        var self = this;
        var code = this.tcode;
        var data = {crsstUuid: "161126131423aaaZ"}
        ajaxTool("post", data, WEB_URL + "coreSystemSet/views",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    var crsstInviteLink = data.data.crsstInviteLink;
                    var link=crsstInviteLink+code;
                    self.link = link;
                    shareTimeline('帮你赚钱的应用', self.link,  WEB_URL + 'images/pigeonhole/img_1.png');
                    shareAppMessage('帮你赚钱的应用', "每天捧着手机就能赚钱了就能赚钱了就能赚钱了", self.link,  WEB_URL + 'images/pigeonhole/img_1.png');
                    shareQQ('帮你赚钱的应用', "每天捧着手机就能赚钱了就能赚钱了就能赚钱了", self.link,  WEB_URL + 'images/pigeonhole/img_1.png');
                    $('#code').html("每天捧着手机就能赚钱了就能赚钱了就能赚钱了"+link);
                    //加载二维码
                    var twoCode = new QRCode(document.getElementById("twoCode"), 
                    {
                        width : 150,//设置二维码显示的宽高
                        height : 150
                    });            
                    twoCode.makeCode(link); //将链接传入二维码生成库
                }
            },true
        );   
    },

    openShade:function(btn){
        $('.inviteFriends_link').hide();
        $('.inviteFriends_code').show();
    },

    shadeOpen:function(btn){
        $('.inviteFriends_code').hide();
        $('.inviteFriends_link').show();
    
    },

    toRewardCollection: function(btn) {
        dataSave('userInvitedNused', $(btn).attr('data-crusrInviteNused'));
        Views.rewardCollectionView.willShow();
    },

    toInvitersListView: function(btn) {
        Views.invitersListView.willShow();
    },

    toRankingListView: function(btn) {
        Views.rankingListView.willShow()
    },

    shareTimeline: function(btn) {
        // shareTimeline("", this.link, '每天捧着手机就能赚钱了就能赚钱了就能赚钱了', '帮你赚钱的应用');
        // shareTimeline('帮你赚钱的应用', this.link,  WEB_URL + 'images/pigeonhole/img_1.png');
    },
    
    share:function(btn) {
        // shareTimeline('帮你赚钱的应用', this.link,  WEB_URL + 'images/pigeonhole/img_1.png');
        $(btn).parent(".inviteFriends_share").find(".productDesc_shade").show();
    },

    conceal:function(btn) {
        $(btn).hide();
    },

    duplicate:function(btn) {
        var duplicate=$("#code").html();
        var clipboard = new Clipboard('.clickCopy'); 
        clipboard.on('success', function(a) {
            alert("复制成功！")
            $(btn).hide();
        });
        clipboard.on('error', function(a) {
               alert("复制失败！请手动复制")
        }); 
    },

    longCopy:function(btn) { 
        var timeOutEvent=0; 
        var longPress={longPress:function () {
            timeOutEvent = 0;
            $(".clickCopy").show();
            }} 
        $(btn).on({
            touchstart: function(e){
                timeOutEvent = setTimeout(longPress.longPress(),500);
                e.preventDefault();
            },
            touchmove: function(){
                clearTimeout(timeOutEvent);
                timeOutEvent = 0;
            },
            touchend: function(){
                clearTimeout(timeOutEvent);
                // if(timeOutEvent!=0){
                //     alert("你这是点击，不是长按");
                // }
                return false;
            }
        });

    },
    // longPress:function () {
    //         timeOutEvent = 0;
    //         $(btn).show();
    // }
     
});



/***********************领取奖励**********************/
Views.rewardCollectionView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'rewardCollection'
    },

    willShow: function(param, isBackPage) {
        var data = {userUuid:dataGet('userUuid')}
        var self = this;
//获取最新一期的期数
        var urlOne = WEB_URL + "busiInvitedActivity/find/latest";
        ajaxTool("post",null,urlOne,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.date = data.data;
                    dataSave('bsiayPeriods',data.data.bsiayPeriods);
                    dataSave("bsiayUuid",data.data.bsiayUuid);         
                }
            },true,true
        );  
//获取当前用户的活动邀请人数
        var num = {crusrUuid: dataGet('userUuid')}
        var urlTwo = WEB_URL + "coreUser/views";
        ajaxTool("post",num,urlTwo,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.number = data.data;                             
                }
            },true,true
        );
//根据用户活动邀请人数查询符合最近的领取规则
        //var rule = {userInvitedNused:dataGet('userInvitedNused')}
        var rule = {userInvitedNused:this.number.crusrInviteTotal}
        var urlThree = WEB_URL + "busiInvitedRule/find/view/with/cnd";
        ajaxTool("post",rule,urlThree,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    if(data.data){
                        self.rule = data.data;
                        dataSave("bsireUuid",data.data.bsireUuid);
                    }else{
                        self.rule ="";
                    }                 
                                       
                };
            },true,true
        );       


//活动邀请人列表
        var request = {bsirnInvited:dataGet('userUuid')};
        var urlFour = WEB_URL + "busiInvitedRelation/active/invited/list";
        ajaxTool("post",request,urlFour,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.banner = data.data;
                    self.show({ date: self.date, number: self.number, rule: self.rule, banner: self.banner, WEB_URL: WEB_URL}, isBackPage);                   
                }
            },true,true
        ); 
        this.judge();
        
    },      
      
    didShow: function(param, isBackPage) {
        this.designation();
        if(this.rule == ""){
            $("#gain").attr("style" ,"background-color:#c0c0c0; pointer-events:none");
        }else{
            this.dateNum();
        }
        
    },

    designation:function() {
        var possessNum = $("#invitationNumber").attr("data-designation");
        var designation = $(".bronze").html();
        var level = 0; 
        if(possessNum>=0 && possessNum<=5) {
            designation="普通战士";
            level = 6-possessNum;
            $("#level").html("升级到青铜还需要邀请"+level+"人");
            $(".bronze").html(designation);

        }else if(possessNum>5 && possessNum<=30) {
            designation="青铜战士";
            level = 31-possessNum;
            $("#level").html("升级到白银还需要邀请"+level+"人");
            $(".bronze").html(designation);
            $(".rewardCollection_nav").css("background-color","#549688");
        }else if(possessNum>30 && possessNum<=100) {
            designation="白银战士";
            level = 101-possessNum;
            $("#level").html("升级到黄金还需要邀请"+level+"人");
            $(".bronze").html(designation);
            $(".imperialCrown").css("background-color","#c0c0c0");
            $(".rewardCollection_nav").css("background-color","#c0c0c0");
        }else{
            designation="黄金战士";
            level = "您已经是最高级别无需升级!";
            $("#level").html(level);
            $(".bronze").html(designation);
             $(".imperialCrown").css("background-color","#e6c143");
            $(".rewardCollection_nav").css("background-color","#e6c143");
        };
    },


    openReward:function(btn) {
        Views.collectionRulesView.willShow()
    },

    dateNum:function() {
        
        var myDate = new Date();    
        var dateNum =myDate.getDay();<!-- 可以获取当前星期几  -->
        if(dateNum>= 1 && dateNum<= 5) {
            $("#gain").attr("style" ,"background-color:#c0c0c0; pointer-events:none");
        }
    },


    judge:function() {//先判断当天是否是周六或者周日 不是就灰掉领取按钮，是就根据用户标识和期数判断是否已领取过奖励
        var self = this;
        var weeks = {
            userUuid:dataGet('userUuid'),
            bsireActivity:dataGet('bsiayUuid'),
        }
        var urlFive = WEB_URL + "busiInvitedReceive/judge/busiInvitedReceive";
        ajaxTool("post",weeks,urlFive,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    // alert(data.errMsg);
                }else{
                    self.week = data.code;
                    if(self.week==1) {
                    $("#gain").html("已领取");
                    $("#gain").attr("style" ,"background-color:#c0c0c0;pointer-events:none");
                }else if(self.week==2){
                    $("#gain").html("领&nbsp;&nbsp;&nbsp;&nbsp;取");
                }
                    // self.show({week: data.data, WEB_URL: WEB_URL}, isBackPage);

                }
            },true
        ); 
    },
    receive:function(btn) {
        var self = this;
        var periodNum = dataGet('bsiayPeriods');
        var data= {
            userUuid:dataGet('userUuid'),
            bsireUuid:dataGet('bsireUuid'),
            bsireActivity:dataGet('bsiayUuid'),
            bsiayPeriods:periodNum
           
        }
     //领取奖励
        var urlSix= WEB_URL + "busiInvitedReceive/go/reward";
        ajaxTool("post",data, urlSix,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.reward = data.data;    
                    // self.show({reward: self.reward, WEB_URL: WEB_URL}, true);  
                    alert("领取成功！");
                    $("#gain").attr("style" ,"background-color:#c0c0c0;pointer-events:none");
                    $("#gain").html("已领取");
                     // var number=data.data;
                     // dataSave('userInvitedNused', number);
                 // $('#number').html(number);
                     //  self.renderList(number, true);//返回刷新赋值
                    
                    }
                 },true
              );
    }, 


    

   
    
});

/***********************邀请人列表List**********************/
Views.invitersListView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'invitersList'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
        
    },
    
    scrollInitRender: function() {
        this.pageNum=1;
        this.invitationList("scrollInit", 1, 10);
    },

    scrollDownRender: function() {
        this.pageNum=1;
        this.invitationList("scrollDown", 1, 10);
    },

    scrollUpRender: function() {
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            this.invitationList("scrollUp", this.pageNum, 10);
        }else{
        	$('#pullUp-msg').html("我也是有底线的！");
        }
    },

    invitationList: function(type,pageNum,pageSize) {
        var self = this;
        if(!pageNum){
            pageNum=1;
        }
        if(!pageSize){
            pageSize=10;
        }
       
        var data = {
            bsirnInvited: dataGet('userUuid'),
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiInvitedRelation/total/day/invited/list";
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                } else {
                    
                    self.lastPageNumber = data.data.lastPageNumber;
                    // var time = data.data.result[0].bsirnIdate;
                    // var timeArray=time.split(" ");
                    if (type == "scrollInit") {
                        self.scrollInit('invitersListItem',{list: data.data.result,WEB_URL: WEB_URL});
                    };
                    if (type == "scrollDown") {
                        self.scrollDown('invitersListItem',{list: data.data.result,WEB_URL: WEB_URL});
                    };
                    if (type == "scrollUp") {
                        self.scrollUp('invitersListItem',{list: data.data.result,WEB_URL: WEB_URL});
                    };
                }
            },true
        );
    },
});

/***********************排行榜**********************/
Views.rankingListView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'rankingList'
    },

    willShow: function(param, isBackPage) {
        var self = this;
        ajaxTool("post", null, WEB_URL + "busiInvitedChats/find/all",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   self.show({rankingList : data.data,WEB_URL: WEB_URL},isBackPage);
                }
            },true
        );
    },

    didShow: function() {
        $('.rankingList_nav').eq(0).addClass('selected');
        $('.rankingInformation').eq(0).css("display","block");
    },
    
    attrSelect: function(btn) {
        $(btn).parent('.rankingList_top').find(".selected").removeClass('selected');
        $(btn).addClass('selected');
        var index=$(btn).index();
        $('.rankingInformation').eq(index).css("display","block");
        $('.rankingInformation').eq(index).siblings('.rankingInformation').css("display","none");
    },    
});


/***********************领取规则**********************/
Views.collectionRulesView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'collectionRules'
    },

    willShow: function(param, isBackPage) {
        var self = this;
        ajaxTool("post", null, WEB_URL + "busiInvitedRule/find/all",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                   self.show({rule : data.data,WEB_URL: WEB_URL},isBackPage);
                }
            },true
        );
    },

    didShow: function() {
        
    },
    
    attrSelect: function(btn) {
      $(btn).parent('.rankingList_top').find(".selected").removeClass('selected');
      $(btn).addClass('selected');
    },
    
});