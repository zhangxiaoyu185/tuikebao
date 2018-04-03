/***********************商品详情**********************/
Views.productDescView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'productDesc',
    },
    shut: function(btn){
        $(btn).css("display","none");
        $('#productDesc_shareArea').css("display","none");
        $('#productDesc_shareCopy').css("display","none");
        $('.productDesc_shareArea').css("height","375px");
        $('.productDesc_shareCopy_area').css("display","none");
        $('.productDesc_shareCopy_nav').css("display","none");
        $('.productDesc_shade_cotent').css("height","0");
        $('.close_attr').hide();
        $(".close_btn").hide();
    },

    willShow: function(param, isBackPage) {
      var self = this;
      var productUuid;
      if(GetParpam('P')) {
        dataSave('productUuid', GetParpam('P'));
      }

      var data = {crsstUuid: "161126131423aaaZ"}
      ajaxTool("post", data, WEB_URL + "coreSystemSet/views",
          function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
              alert("error:" + data);
          },
          function success(data){
              if(!data.success) {
                  alert(data.errMsg);
              }else{
                  var crsstProductDetails = data.data.crsstProductDetails;
                  //var link=crsstProductDetails+"&&shareProductUuid=" + dataGet('productUuid')+"&shareUserUuid=" + dataGet('userUuid');
                  var link=crsstProductDetails+"&P=" + dataGet('productUuid')+"&S=" + dataGet('userUuid');
                  self.link = link;

                  // $('#code').html("每天捧着手机就能赚钱了就能赚钱了就能赚钱了"+link);
                  // //加载二维码
                  // var twoCode = new QRCode(document.getElementById("twoCode"), 
                  // {
                  //     width : 150,//设置二维码显示的宽高
                  //     height : 150
                  // });            
                  // twoCode.makeCode(link); //将链接传入二维码生成库
              }
          },true,true
      );

      var productUuid = dataGet('productUuid');
      var data = {bsprtUuid: productUuid};
      var url = WEB_URL + "busiProduct/views/before";
      ajaxTool("post",data,url,
          function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
              alert("error:" + data);
          },
          function success(data){
              if(!data.success) {
                  alert(data.errMsg);
                  backPage();
              } else {
                self.bsprtWxzvalueJson = data.data.bsprtWxzvalueJson;
                self.productData = data.data;
                //self.getTagValList(data.data, isBackPage);
              }
          },true,true
      ); 

      //分享文案
      //var productUuid = dataGet('productUuid');
      var url = WEB_URL + "coreShareCopy/find/all";
      ajaxTool("post",null,url,
          function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
              alert("error:" + data);
          },
          function success(data){
              if(!data.success) {
                  alert(data.errMsg);
                  backPage();
              } else {
                console.log(data);
                self.getTagValList(data.data, self.productData, isBackPage);
              }
          },true,true
      ); 

    },

    //分享记录
    record: function(){
        var self = this;
        var userInfo =JdataGet('userInfo');
        var grade = userInfo.crusrGrade;
        var data = {
            bssrdUser: dataGet('userUuid'),//用户ID
            bssrdGrade: grade,//等级
            bssrdCommision: self.shareEarnings.money,//佣金
            bssrdProduct: self.productData.bsprtUuid,//商品ID
            bssrdSharePic: self.productData.bsprtSharePic,//分享端图片
            bssrdStorePic: self.productData.bsprtStorePic,//商城端图片
            bssrdProductName: self.productData.bsprtName,//商品名称
            bssrdIntegral: self.shareEarnings.integral,//积分收益
          }
        ajaxTool("post", data, WEB_URL + "busiShareRecord/add/busiShareRecord",
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
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

    getTagValList:  function(crscyCopyData, productData, isBackPage) {
      var self = this;
      self.productData = productData;
      var bsprtRuleParam = JSON.parse(productData.bsprtRuleParam);//规格参数JSON串
      var bsprtAttrJson =  JSON.parse(productData.bsprtAttrJson);//所有属性标识JSON串
      var bsprtValueJson =  JSON.parse(productData.bsprtValueJson);//所有属性值标识JSON串
      var bsprtProductTag = JSON.parse(productData.bsprtProductTag);//标签
      var bsprtProductTagNames = bsprtProductTag.tagNames.split('|');
      self.bsprtValueJson = bsprtValueJson;
      var attrTitle = "";
      for(var i=0; i<bsprtAttrJson.length; i ++) {
        attrTitle += bsprtAttrJson[i].name + " ";
      }
      var userInfo =JdataGet('userInfo');
      var bsprtGradeShareJson = JSON.parse(productData.bsprtGradeShare); 
      if(!userInfo){
        self.shareEarnings = bsprtGradeShareJson['grade_170301174444rMbM'];
      }else{
        self.shareEarnings = bsprtGradeShareJson['grade_'+userInfo.crusrGrade];
      }

      if(!bsprtProductTag.tagUuids) {
        self.show({
          prduct: productData, 
          crscyCopy:crscyCopyData,
          bsprtRuleParam: bsprtRuleParam,
          bsprtRuleParamContent: "",
          shareEarnings: self.shareEarnings,
          bsprtAttrJson: bsprtAttrJson,
          attrTitle: attrTitle, 
          bsprtProductTagNames: bsprtProductTagNames,
          WEB_URL: WEB_URL
        }, isBackPage);
        self.bsprtView = productData.bsprtView;
        return;
      }

      var data = {bststUuids: bsprtProductTag.tagUuids};
      var url = WEB_URL + "busiTagSet/find/by/uuids";
      ajaxTool("post",data,url,
          function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
              alert("error:" + data);
          },
          function success(data){
              if(!data.success) {
                  alert(data.errMsg);
              } else {
                  self.show({
                    prduct: productData, 
                    crscyCopy:crscyCopyData,
                    bsprtRuleParam: bsprtRuleParam,
                    bsprtRuleParamContent: data.data,
                    shareEarnings: self.shareEarnings,
                    bsprtAttrJson: bsprtAttrJson,
                    attrTitle: attrTitle, 
                    bsprtProductTagNames: bsprtProductTagNames,
                    WEB_URL: WEB_URL
                  }, isBackPage);
                  self.bsprtView = productData.bsprtView;
              }
          }
      );
    },

    didShow: function() {
      initWx();
      var self = this;
      var shareCopy = $(".productDesc_custom").find('span').html();
      var recommendedName=$(".title").html();
      shareTimeline(recommendedName, self.link, '', function(){
        self.record();
      });
      shareAppMessage(recommendedName, shareCopy, self.link,  '', function(){
        self.record();
      });
      shareQQ(recommendedName, shareCopy, self.link,  '', function(){
        self.record();
      });

      var imgLen =  $('#index_slides').children('.productDesc_img').length;
      if(imgLen>1){
        $('#index_slides').slidesjs({
          width: 940,
          height: 528,
          navigation: false,
          play: {
              active: false,
              auto: true,
              interval: 4000,
              swap: true
          }
        });
      }

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
        $('#productDesc_content').html(this.bsprtView);	

        //计算基本信息总体的高度
        var self = this;
        var share = window.document.getElementById('productDesc_share_note');
        var base = window.document.getElementById('productDesc_base');
        var shareHeight = share.offsetHeight;
        var baseHeight = base.offsetHeight;
        self.total = shareHeight + baseHeight;

        //滚动条事件
        window.onscroll=function(){
            var type = $('.selected').attr('data-type');    
            // alert(self.total);
            if( type == "evaluate"){
              return;//滚动时 如果在评论页面 则退出执行
            }
            var scrollTop =document.documentElement.scrollTop||document.body.scrollTop;//获取滚动条的位置
            if( scrollTop >= self.total){
              $('.productDesc_top .selected').removeClass('selected');
              $('.desc').addClass('selected');       
            }else{
              $('.productDesc_top .selected').removeClass('selected');
              $('.base').addClass('selected');           
            }
        }


        //监听自定义文本框输入的字数
        $('#text').focus( function(){
          $('#text').keyup(function(){
            var len = $('#text').val().length;
            if(len >50){
              var num=$('#text').val().substr(0,50);
              $('#text').val(num);
            }else{
              $('#len').html(len);  
            }    
          })
        })

    },

   productDescType: function(btn) {
      $('.productDesc_top .selected').removeClass('selected');
      $(btn).addClass('selected');
      var type = $(btn).attr('data-type');
      if(type=="evaluate") {
        $('#productDesc_evaluate').show();
        $('#productDesc_content').hide();
        $('#productDesc_base').hide();
        $('.productDesc_shade').hide();
        $('.productDesc_shareArea').hide();
        $('#productDesc_shareCopy').hide();
        $('.productDesc_shareArea').css("height","375px");
        $('.productDesc_shareCopy_area').css("display","none");
        $('.productDesc_shareCopy_nav').css("display","none");
        $('.productDesc_shade_cotent').css("height","0");
        $('.close_attr').hide();
        this.scrollInitRender();
      }else{
        $('#productDesc_evaluate').hide();
        $('#productDesc_content').show();
        $('#productDesc_base').show();
        $('.productDesc_shade').hide();
        $('.productDesc_shareArea').hide();
        $('#productDesc_shareCopy').hide();
        $('.productDesc_shareArea').css("height","375px");
        $('.productDesc_shareCopy_area').css("display","none");
        $('.productDesc_shareCopy_nav').css("display","none");
        $('.productDesc_shade_cotent').css("height","0");
        $('.close_attr').hide();
      }

      //改变滚动条位置
      if( type == "base"){
        scrollTo(0, 0);     
      }

      if(  type == "desc"){
         scrollTo(0, this.total);
      }

    },

    noteClose: function(btn) {
      $('#productDesc_share_note').hide();
    },

    
    openShade: function(btn, type) {
      $('#shuts').show();
      $('.productDesc_btn_bottom').hide();
      $('body').css({"overflow":"hidden"});
      // $('#productDesc_share_note').hide();

      this.type = type;

      this.openTypeId = $(btn).attr('data-openTypeId');
      if(this.openTypeId == 'attribute') {
        if(type) {
          $('#sure_btn').show();
          $('#close_attr').hide();
          $('#close_btn').hide();
        }else {
          $('#close_attr').show();
          $('#close_btn').hide();
          $('#sure_btn').hide();
          var attributeEl = $('#attribute .cotent_item .selected');
          var attrSelectKey = "attrVal_";
          for(var i=0; i<attributeEl.length; i++) {
            var uuid = $(attributeEl[i]).attr('data-type');
            attrSelectKey += uuid + "|";
          }

          //$('#selectPrice').html(this.bsprtValueJson[attrSelectKey].price* parseInt($('#number_val').html()));
          //$('#store').html(this.bsprtValueJson[attrSelectKey].store);
          //this.storeNumber = this.bsprtValueJson[attrSelectKey].store;
        }
      }else {
        $('#close_btn').show();
        $('#sure_btn').hide();
          $('#close_attr').hide();
      }
      $('#'+this.openTypeId).stop().animate({
        height:'370px'
      });
    },


    shareOpen:function(btn){
      var self = this;
      $('.productDesc_shareArea').show();
      $('.sharingmode').hide();
      $('.productDesc_shareCopy_area').show();
      //分享图片
      var url =  WEB_URL + "busiProduct/create/share/product/pic?shareCopy="+encodeURIComponent(dataGet('shareCopy'))+"&productUuid="+dataGet('productUuid')+"&userUuid="+dataGet('userUuid');
      $('#productDesc_shareArea').children('#sharePic').attr("src",url);
      // 用户分享回调函数
      self.record();
    },


    shareLine:function(btn) {
       var self=this;
      var Id=dataGet("userUuid");
      if(Id == null || Id == "") {
        alert("您尚未登录！请先登录！")
        Views.loginPageView.willShow();
      }else {
        var data = {crusrUuid: dataGet('userUuid')};
        var url = WEB_URL + "coreGrade/judge/share/count";
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){

                if(!data.success) {
                    alert(data.errMsg);
                    //backPage();
                } else {
                  console.log(data.data);
                  // if(data.data.code == 1){
                      console.log(self.link);
                      $("#commodity").html("点击分享链接，发送到你想分享的朋友，让他与你一起赚钱!"+self.link);//将链接赋到文本框内
                      $('.sharingmode').show();
                      $('.productDesc_shareCopy').show();
                      $('#shade').show();
                      $('body').css({"overflow":"hidden"});
                  // }else{
                  //   alert(data.data.errMsg);
                  // }
                  //self.getTagValList(data.data, isBackPage);
                }
            }
        );
      }
    },



    openLine:function(btn){
      $(".productDesc_shareCopy_top").addClass('selected');
      $(".productDesc_shareCopy_top .txt").hide();
      $(".productDesc_shareCopy_top .ft").show();
      $("#nav").show();
      $(".productDesc_shareArea").css("height","242px");
      $("#edit").hide(); 
    },

    checkLine:function(btn){
      $(".productDesc_shareCopy_nav  .check").removeClass('selected');
      $(btn).find(".check").addClass('selected')
      var shareCopy = $(btn).find('span').html();
      dataSave('shareCopy',shareCopy);
      this.shareOpen();
    },

    editCopy: function(btn){
      $("#nav").hide();
      $("#edit").show(); 
      $(".productDesc_shareCopy_top .txt").show();
      $(".productDesc_shareCopy_top .ft").hide();
    },

    submitCopy:function(btn){
      var copy = $('#text').val();
      dataSave('shareCopy',copy);
      this.shareOpen();
      $('#text').val("");
    },

    shareArea:function(){
      $("#nav").hide();
      $("#edit").hide();      
    },

    closeShade: function(btn) {
      $('.productDesc_shade').hide();
      $('.productDesc_btn_bottom').show();
      $('body').css({"overflow":"auto"});
      $('#productDesc_share_note').show();
      // if(this.openTypeId == 'attribute') {
        $('.close_attr').hide();
      // }else {
        $('.close_btn').hide();
      // }
      $('#'+this.openTypeId).stop().animate({
        height:'0'
      });
    },

    
    attrSelect: function(btn) {
      if($(btn).hasClass('disable')){
            return;
          }
          var wxzvalueJson = JSON.parse(this.bsprtWxzvalueJson);

          if($(btn).hasClass('selected')) {
            $(btn).removeClass('selected');
            $(btn).parent('.cotent_item').removeClass('select').addClass('noSelect');
            $('#selectPrice').html("");
            $('#store').html("");   
          }else {
            $(btn).parent('.cotent_item').find('.item_select').removeClass('selected');
            $(btn).addClass('selected');
            $(btn).parent('.cotent_item').removeClass('noSelect').addClass('select');
          }

          var selectKey = $('.selectArea .cotent .cotent_item.select');
          var leave = $('.selectArea .cotent .cotent_item').length;

          if(selectKey.length != leave){
            $('.selectArea .cotent .cotent_item .item_select').removeClass('disable');
          }
          if(selectKey.length >= leave-1){
            for(var i=0; i<wxzvalueJson.length; i++) {
              var indexDate = wxzvalueJson[i];
              var selectedRight = 0;
              for(var j=0; j<selectKey.length; j++) {
                if(indexDate['attr_'+$(selectKey[j]).attr('data-key')] == $(selectKey[j]).find('.selected').attr('data-type')){
                  selectedRight++;
                } 
              }
              if(selectedRight >= leave-1 ) {
                // var noSelectKey = $('.selectArea .cotent .cotent_item.noSelect').attr('data-key');
                // var noSelectAttr = indexDate['attr_'+noSelectKey];
                var noSelectKey = $('.selectArea .cotent .cotent_item.noSelect');
                var noSelectAttr = indexDate['attr_'+$(noSelectKey).attr('data-key')];
                var noSelectItem = $('.selectArea .cotent .cotent_item.noSelect .item_select');
                if(noSelectKey.length==0) {
                  // noSelectAttr = indexDate['attr_'+$(btn).parent('.cotent_item').attr('data-key')];
                  noSelectItem = $(btn).parent('.cotent_item').find('.item_select');
                }
                
                for(var k=0; k<noSelectItem.length; k++) {
                  var attr = $(noSelectItem[k]).attr('data-type');
                  if(attr == noSelectAttr) {
                    $(noSelectItem[k]).addClass('disable');
                  }
                }
              }
            }
          }

          if(selectKey.length == leave){
            var attributeEl = $('#attribute .cotent_item .selected');
            var attrSelectKey = "attrVal_";
            for (var i = 0; i < attributeEl.length; i++) {
              var uuid = $(attributeEl[i]).attr('data-type');
              attrSelectKey += uuid + "|";
            }

            $('#selectPrice').html(this.bsprtValueJson[attrSelectKey].price* parseInt($('#number_val').html()));
            $('#store').html(this.bsprtValueJson[attrSelectKey].store);
            // this.storeNumber = this.bsprtValueJson[attrSelectKey].store;
          }
      // $(btn).parent('.cotent_item').find('.item_select').removeClass('selected');
      // $(btn).addClass('selected');

      // var attributeEl = $('#attribute .cotent_item .selected');
      // var attrSelectKey = "attrVal_";
      // for(var i=0; i<attributeEl.length; i++) {
      //   var uuid = $(attributeEl[i]).attr('data-type');
      //   attrSelectKey += uuid + "|";
      // }

      // $('#selectPrice').html(this.bsprtValueJson[attrSelectKey].price);
      // $('#store').html(this.bsprtValueJson[attrSelectKey].store);
      // this.storeNumber = this.bsprtValueJson[attrSelectKey].store;

      // $('#number_val').html("1");
    },

    addToCart: function(btn) {
      this.openShade(btn, 'addToCart');
    },

    goSure: function(btn) {
      this.openShade(btn, 'goSure');   
    },

    add: function(btn) {
      var val = parseInt($('#number_val').html());
      var store = parseInt($('#store').html());
      if(!store){
        alert("请选着套餐");
        return;
      }
      if(val>=store) {
        alert('购买数量不能大于库存');
      }else{
        val++;
        $('#number_val').html(val); 
      }
      this.countPrice(val);
    },

    subtract: function(btn) {
      var val = $('#number_val').html();
      var store = parseInt($('#store').html());
      if(!store){
        alert("请选着套餐");
        return;
      }
      var number =val--;
      if (number <= 0) {
        val = 0;
      }
      $('#number_val').html(val);
      this.countPrice(val);
    },

    countPrice: function(number) {
      var attributeEl = $('#attribute .cotent_item .selected');
      var attrSelectKey = "attrVal_";
      for(var i=0; i<attributeEl.length; i++) {
        var uuid = $(attributeEl[i]).attr('data-type');
        attrSelectKey += uuid + "|";
      }

      var nowPrice = Number(this.bsprtValueJson[attrSelectKey].price);
      var totalPrice =  nowPrice * number;
      $('#selectPrice').html(totalPrice);
    },     

    sureBuy: function(btn) {
      this.closeShade();
      Views.sureOrderView.show();
    },

    scrollInitRender: function() {
        this.pageNum=1;
        this.getEvaluateList("scrollInit", 1, 10);
    },

    scrollDownRender: function() {
        this.pageNum=1;
        this.getEvaluateList("scrollDown", 1, 10);
    },

    scrollUpRender: function() {
        this.pageNum++;
        if(this.pageNum<=this.lastPageNumber){
            this.getEvaluateList("scrollUp", this.pageNum, 10);
        }else{
        	$('#pullUp-msg').html("我也是有底线的！");
        }
    },

    getEvaluateList: function(type,pageNum,pageSize) {
        var self = this;
        if(!pageNum){
            pageNum=1;
        }
        if(!pageSize){
            pageSize=10;
        }
        var productUuid = dataGet('productUuid');
        var data = {
            bseveProduct: productUuid,
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiEvaluate/find/by/cnd/by/product";
        ajaxTool("post",data,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                } else {
                    self.lastPageNumber = data.data.lastPageNumber;
                    var list = data.data.result;
                    var evaluateCount = data.data.totalCount;
                    $('#evaluateCount').html(evaluateCount);
                    if (type == "scrollInit") {
                        self.scrollInit('productDescEvaluateItem',{list: list, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollDown") {
                        self.scrollDown('productDescEvaluateItem',{list: list, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollUp") {
                        self.scrollUp('productDescEvaluateItem',{list: list, WEB_URL: WEB_URL});
                    };
                }
            },true
        );
    },

    share:function(btn) {
      $(btn).parent(".productDesc_spread").find(".productDesc_shade").show();
    },

    conceal:function(btn) {
      $(btn).hide();
    },

    linkShare:function(btn) {
      $(btn).parent().parent().parent(".productDesc_shareCopy").find(".productDesc_spread .productDesc_shade").show();
    }

});

/***********************404**********************/

Views.page_404View =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'page_404',
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
        
    }
});


/***********************500**********************/

Views.page_500View =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'page_500',
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
        
    }
});

/***********************购物车(空)**********************/
Views.shoppingEmptyView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'shoppingEmpty',
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
        
    }
});

/***********************我的订单(空)**********************/
Views.myorderEmptyView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'myorderEmpty',
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
        
    }
});

