/***********************商品详情**********************/
Views.productDescView = $.extend({}, Views.PanelView, {
  options: {
    tmpl: 'productDesc',
  },

  willShow: function(param, isBackPage) {
    var self = this;
    var productUuid = dataGet('productUuid');
    var data = {
      bsprtUuid: productUuid
    };
    var url = WEB_URL + "busiProduct/views/before";
    ajaxTool("post", data, url,
      function error(XMLHttpRequest, textStatus, errorThrown, fnErr) {
        alert("error:" + data);
      },
      function success(data) {
        if (!data.success) {
          alert(data.errMsg);
          backPage();
        } else {
            // console.log(data.data);
            self.bsprtWxzvalueJson = data.data.bsprtWxzvalueJson;
            self.getTagValList(data.data, isBackPage);
        }
      }
    );
  },

  getTagValList: function(productData, isBackPage) {
    var self = this;
    var bsprtRuleParam = JSON.parse(productData.bsprtRuleParam); //规格参数JSON串
    var bsprtAttrJson = JSON.parse(productData.bsprtAttrJson); //所有属性标识JSON串
    var bsprtValueJson = JSON.parse(productData.bsprtValueJson); //所有属性值标识JSON串
    var bsprtProductTag = JSON.parse(productData.bsprtProductTag); //标签

    var bsprtProductTagNames = bsprtProductTag.tagNames.split('|');
    self.bsprtProductTagNames = bsprtProductTagNames;
    self.bsprtValueJson = bsprtValueJson;
    var attrTitle = "";
    for (var i = 0; i < bsprtAttrJson.length; i++) {
      attrTitle += bsprtAttrJson[i].name + " ";
    }
    self.attrTitle = attrTitle;

    if(dataGet('initProude')=='true') {
      isBackPage = true;
      // dataSave('initProude', 'false');
    }

    if (!bsprtProductTag.tagUuids) {
      self.show({
        prduct: productData,
        bsprtRuleParam: bsprtRuleParam,
        bsprtRuleParamContent: "",
        bsprtAttrJson: bsprtAttrJson,
        attrTitle: attrTitle,
        bsprtProductTagNames: bsprtProductTagNames,
        WEB_URL: WEB_URL
      }, isBackPage);
      self.bsprtView = productData.bsprtView;
      self.bssctDesc = productData.bsprtDesc;
      self.bssctSharePic = productData.bsprtSharePic;
      self.bssctStorePic = productData.bsprtStorePic;
      self.bsprtExpress = productData.bsprtExpress;
      self.bsprtUuid = productData.bsprtUuid;
      self.bsoimIsBom = productData.bsprtIsVirtual;
      return;
    }

    var data = {
      bststUuids: bsprtProductTag.tagUuids
    };
    var url = WEB_URL + "busiTagSet/find/by/uuids";
    ajaxTool("post", data, url,
      function error(XMLHttpRequest, textStatus, errorThrown, fnErr) {
        alert("error:" + data);
      },
      function success(data) {
        if (!data.success) {
          alert(data.errMsg);
        } else {
          console.log(data.data);
          self.show({
            prduct: productData,
            bsprtRuleParam: bsprtRuleParam,
            bsprtRuleParamContent: data.data,
            bsprtAttrJson: bsprtAttrJson,
            attrTitle: attrTitle,
            bsprtProductTagNames: bsprtProductTagNames,
            WEB_URL: WEB_URL
          }, isBackPage);
          self.bsprtView = productData.bsprtView;
          self.bssctDesc = productData.bsprtDesc;
          self.bssctSharePic = productData.bsprtSharePic;
          self.bssctStorePic = productData.bsprtStorePic;
          self.bsprtExpress = productData.bsprtExpress;
          self.bsprtUuid = productData.bsprtUuid;
          self.bsoimIsBom = productData.bsprtIsVirtual;

        }
      }
    );
  },

  didShow: function() {
    initWx();
    $('#productDesc_content').html(this.bsprtView);
    var imgLen = $('#index_slides').children('.productDesc_img').length;
    if (imgLen > 1) {
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

    //计算基本信息总体的高度
    var self = this;
    var base = window.document.getElementById('productDesc_base');
    var baseHeight = base.offsetHeight;
    self.total = baseHeight;

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
  },

  productDescType: function(btn) {
    $('.productDesc_top .selected').removeClass('selected');
    $(btn).addClass('selected');
    var type = $(btn).attr('data-type');

    if (type == "evaluate") {
      $('#productDesc_evaluate').show();
      $('#productDesc_content').hide();
      $('#productDesc_base').hide();
      $(".productDesc_shade_cotent").css("height","0")
      $(".productDesc_shade").hide();
      $(".close_attr").hide();
      $(".close_btn").hide();
      this.scrollInitRender();
    } else {
      $('#productDesc_evaluate').hide();
      $('#productDesc_content').show();
      $('#productDesc_base').show();
      $(".productDesc_shade_cotent").css("height","0")
      $(".productDesc_shade").hide();
      $(".close_attr").hide();
      $(".close_btn").hide();
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
    $('.productDesc_shade').show();
    $('.productDesc_btn_bottom').hide();
    $('body').css({
      "overflow": "hidden"
    });

    this.type = type;

    this.openTypeId = $(btn).attr('data-openTypeId');
    if (this.openTypeId == 'attribute') {
      if (type) {
        $('#sure_btn').show();
        $('#close_attr').hide();
        $('#close_btn').hide();
      } else {
        $('#close_attr').show();
        $('#close_btn').hide();
        $('#sure_btn').hide();
      }

      var attributeEl = $('#attribute .cotent_item .selected');
      var attrSelectKey = "attrVal_";
      for (var i = 0; i < attributeEl.length; i++) {
        var uuid = $(attributeEl[i]).attr('data-type');
        attrSelectKey += uuid + "|";
      }

      //$('#selectPrice').html(this.bsprtValueJson[attrSelectKey].price* parseInt($('#number_val').html()));
      //$('#store').html(this.bsprtValueJson[attrSelectKey].store);
      //this.storeNumber = this.bsprtValueJson[attrSelectKey].store;

    } else {
      $('#close_btn').show();
      $('#sure_btn').hide();
      $('#close_attr').hide();
    }
    $('#' + this.openTypeId).stop().animate({
      height: '370px'
    });
  },

  closeShade: function(btn) {
    $('.productDesc_shade').hide();
    $('.productDesc_btn_bottom').show();
    $('body').css({
      "overflow": "auto"
    });

    // if(this.openTypeId == 'attribute') {
    $('.close_attr').hide();
    // }else {
    $('.close_btn').hide();
    // }
    $('#' + this.openTypeId).stop().animate({
      height: '0'
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
      
    

    //判断是否点击最后一个选项  如果不是的话 就移除最后一个选项('selected');
    // $('.cotent').click(function(e){
    //   var cotentItem = $('.cotent .cotent_item ');
    //   var itemSelect = $(cotentItem[cotentItem.length -1]).find('.item_select ');
    //   for(var h=0; h<itemSelect.length; h++){
    //      if(e.target != itemSelect[h] ){
    //         $(itemSelect[h]).removeClass('selected');
    //     }       
    //   }
    // })


    // var t_tTitle  = $(btn).parent('.cotent_item').siblings('.t_title');
    // var attributeEl = $('#attribute .cotent_item .selected');

    //判断选中的个数 是否跟全部减一 的个数相同
    // if(attributeEl.length == t_tTitle.length -1){
    //     for(var j =0; j<wxzvalueJson.length; j++){//循环没有库存的数据
    //       for(var i=0; i<attributeEl.length; i++){//获取标题相应的UUID
    //         var key = $(t_tTitle[i]).attr('data-uuid');
    //         var keyO = $(t_tTitle[i+1]).attr('data-uuid');
    //         var type = $(attributeEl[i]).attr('data-type');
    //         var typeO = $(attributeEl[i+1]).attr('data-type');
    //         //根据获取的标题UUID 跟 属性的UUID 去查找库存的数据 中是否有  
    //         if( wxzvalueJson[j][["attr_"+key]] == type && wxzvalueJson[j][["attr_"+keyO]] == typeO){
    //           flag = true;
    //           //如果为TRUE  将库存数据最后一个的值 存入数组attr_type中
    //           if(flag){
    //             var Akey = $(t_tTitle[t_tTitle.length -1]).attr('data-uuid');
    //             attr_type[attr_type.length] = wxzvalueJson[j][["attr_"+Akey]]
    //           }
    //         }else{
    //           flag = false;
    //         }
    //       }
    //    }

       //根据上面数组添加的数据   将最后一个属性的选项禁用
      // var cotentItemTwo  = $(btn).parent().parent().find('.cotent_item');
      // var iSelsct = $(cotentItemTwo[cotentItemTwo.length -1]).children('.item_select ');      
      // for(var z =0; z<iSelsct.length; z++){
      //   for(var a =0; a<attr_type.length; a++){
      //     if($(iSelsct[z]).attr('data-type') == attr_type[a]){
      //       $(iSelsct[z]).removeClass('selected');
      //       $(iSelsct[z]).removeClass('ui_btn');
      //       $(iSelsct[z]).addClass('disable');
      //       return;
      //     }else{
      //       $(iSelsct[z]).removeClass('disable');
      //       $(iSelsct[z]).addClass('ui_btn');
      //     }
      //   }       
      // }
      // console.log("0000000000000000000");
      // console.log(attr_type);
      // console.log(wxzvalueJson);
    // }


    // if(t_tTitle.length == attributeEl.length){
    //   var attrSelectKey = "attrVal_";
    //   for (var i = 0; i < attributeEl.length; i++) {
    //     var uuid = $(attributeEl[i]).attr('data-type');
    //     attrSelectKey += uuid + "|";
    //   }

    //   $('#selectPrice').html(this.bsprtValueJson[attrSelectKey].price);
    //   $('#store').html(this.bsprtValueJson[attrSelectKey].store);
    //   this.storeNumber = this.bsprtValueJson[attrSelectKey].store;

    //   $('#number_val').html("1");     
    // }

  },

  sureBuyOrAddCat: function() {
    var Id=dataGet("userUuid");
    if(Id == null) {
      alert("您尚未登录！请先登录！")
      Views.loginPageView.willShow;
    }else if(dataGet('BuyOrAddCat') == "addToCart") {
      this.addShoppingCart();
    }else {
        this.sureBuy();
    }
    //  if (dataGet('BuyOrAddCat') == "addToCart") {
    //   this.addShoppingCart();
    // } else {
    //   this.sureBuy();
    // }

  },

  addShoppingCart: function() { //添加购物车接口
    var isLoginFlag = isLogin();
    if(!isLoginFlag) {
        return;
    }

    var self = this;
    var attributeEl = $('#attribute .cotent_item .selected');
    var attrSelectKey = "attrVal_";
    for (var i = 0; i < attributeEl.length; i++) {
      var uuid = $(attributeEl[i]).attr('data-type');
      console.log(uuid)
      attrSelectKey += uuid + "|";
    }

    var data = {
      bssctUser: dataGet('userUuid'),
      bssctProduct: dataGet('productUuid'),
      bssctQuantity: $('#number_val').html(),
      bssctAttrName: this.attrTitle,
      bssctAttrValue: attrSelectKey,
      bssctFee: $('#selectPrice').html(),
      bssctTags: '',
      bssctName: $('#productName').html(),
      bssctDesc: this.bssctDesc,
      bssctSharePic: this.bssctSharePic,
      bssctStorePic: this.bssctStorePic,
    };
    var url = WEB_URL + "busiShoppingCart/add/busiShoppingCart";

    ajaxTool("post", data, url,
      function error(XMLHttpRequest, textStatus, errorThrown, fnErr) {
        alert("error:" + data);
      },
      function success(data) {
        if (!data.success) {
          alert(data.errMsg);
        } else {
          alert('添加成功');
          console.log(data);
          self.closeShade();
        }
      }
    );
  },

  sureBuy: function(btn) {

    var selectPrice = $("#selectPrice").html();
    var store = $("#store").html();
    if(selectPrice == "" || store == ""){
      alert("请选着套餐");
      return;
    }

    isLogin();

    var attributeEl = $('#attribute .cotent_item .selected');
    var bssctAttrValueName = "";
    var bssctAttrValue = [];
    for (var i = 0; i < attributeEl.length; i++) {
      var attrVal = $(attributeEl[i]).html();
      bssctAttrValueName += attrVal + " ";
      bssctAttrValue.push($(attributeEl[i]).attr('data-type'));
    }
    var bsprtProductTagNames = this.bsprtProductTagNames;
    var data = {
      bssctUser: dataGet('userUuid'),
      bssctProduct: dataGet('productUuid'),
      bssctQuantity: $('#number_val').html(),
      bssctAttrName: this.attrTitle,
      bssctAttrValue: bssctAttrValue.join('|'),
      bssctAttrValueName: bssctAttrValueName,
      bsprtProductTagNames: bsprtProductTagNames,
      bssctFee: $('#selectPrice').html(),
      bssctName: $('#productName').html(),
      bssctDesc: this.bssctDesc,
      bssctSharePic: this.bssctSharePic,
      bssctStorePic: this.bssctStorePic,
      bsprtExpress: this.bsprtExpress,
      bsprtUuid: this.bsprtUuid,
      bsoimIsBom: this.bsoimIsBom,
    };
    JdataSave('buyProduct', data);

    var self = this;
    var data = {
      bssasUser: dataGet('userUuid')
    };
    ajaxTool("post", data, WEB_URL + "busiShippingAddress/views/my/default",
      function error(XMLHttpRequest, textStatus, errorThrown, fnErr) {
        alert("error:" + data);
      },
      function success(data) {
        if (!data.success) {
          alert(data.errMsg);
        } else {
          if (data.data) {
            JdataSave('addressBuy', data.data);
          } else {
            JdataSave('addressBuy', null);
          }

          self.closeShade();
          Views.sureOrderView.willShow();

          // self.show({orderInfo: buyProduct, address: data.data}, isBackPage);
          // self.show({shoppingInfo: data.data, WEB_URL:WEB_URL}, isBackPage);
        }
      }, true
    );
  },

  addToCart: function(btn) {
    var isLoginFlag = isLogin();
    if(!isLoginFlag) {
        return;
    }

    this.openShade(btn, 'addToCart');
    dataSave('BuyOrAddCat', 'addToCart');
  },

  addToCartAttr: function(btn) {
    
    var selectPrice = $("#selectPrice").html();
    var store = $("#store").html();
    if(selectPrice == "" || store == ""){
      alert("请选着套餐");
      return;
    }
    // this.openShade(btn, 'addToCart');
    var isLoginFlag = isLogin();
    if(!isLoginFlag) {
        return;
    }
    this.addShoppingCart();
  },

  goSure: function(btn) {
      var isLoginFlag = isLogin();
      if(!isLoginFlag) {
          return;
      }
      this.openShade(btn, 'goSure');
      dataSave('BuyOrAddCat', 'buy');
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
    var store = parseInt($('#store').html());
    if(!store){
      alert("请选着套餐");
      return;
    }
    var val = $('#number_val').html();
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
    for (var i = 0; i < attributeEl.length; i++) {
      var uuid = $(attributeEl[i]).attr('data-type');
      console.log(uuid)
      attrSelectKey += uuid + "|";
    }
    var nowPrice = Number(this.bsprtValueJson[attrSelectKey].price);
    var totalPrice = nowPrice * number;
    //$('#selectPrice').html(totalPrice);
  },

  scrollInitRender: function() {
    this.pageNum = 1;
    this.getEvaluateList("scrollInit", 1, 10);
  },

  scrollDownRender: function() {
    this.pageNum = 1;
    this.getEvaluateList("scrollDown", 1, 10);
  },

  scrollUpRender: function() {
    this.pageNum++;
    if (this.pageNum <= this.lastPageNumber) {
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
    
  shut:function(btn) {
     $('.productDesc_shade_cotent').css("height","0"); 
     $('.close_btn').hide();
     $('.productDesc_btn_bottom').show();
     $('.productDesc_shade').hide();

  }


});



/***********************购物车**********************/
Views.shoppingCartView = $.extend({}, Views.PanelView, {
  options: {
    tmpl: 'shoppingCart',
    hasFootNav: true,
    footItemOrder: 2, // hasFootNav设置true才有效   表示现在远着的是底部第几个菜单 0开始
    itemClass: 'item'
  },

  willShow: function(param, isBackPage) {
    var isLoginFlag = isLogin();
    if(!isLoginFlag) {
        return;
    }
    
    var self = this;
    var data = {
      bssctUser: dataGet('userUuid')
    };
    ajaxTool("post", data, WEB_URL + "busiShoppingCart/find/all/by/my",
      function error(XMLHttpRequest, textStatus, errorThrown, fnErr) {
        alert("error:" + data);
      },
      function success(data) {
        if (!data.success) {
          alert(data.errMsg);
        } else {
          // console.log(data);
          var invalid =[];//失效
          var sure =[];//可以
      var a =0;
      var b =0;
      if(data.data.length == 0){
      invalid =[];
      sure =[];
      }else{
          for(var x=0; x<data.data.length; x++){
            if(data.data[x].bssctStatus == 1){  
              sure[a] = x;
              a++;
            }else{
              invalid[b] = x;
              b++;
            }
          }
        }
          self.show({
            shoppingInfo: data.data,
            invalid:invalid,
            sure:sure,
            flag:dataGet('flag'),
            WEB_URL: WEB_URL
          }, isBackPage);
          self.shoppingList = data.data;
        }
      }, true
    );
  },

  didShow: function() {
    initWx();
    var num = 0;
    $(".aggregatePrice").html(num);

    if (dataGet('cartIndex') == "0") {
      $("#placeholder").hide();
      $('#footNavBar').hide();
    }

  },
  addressSelected: function(btn) {
    var degree = $(btn).parent().parent().parent().find(".selected").length;
    var money = $(btn).parent().parent().find(".money").attr('data-prices');
    var figure = $(btn).parent().parent().find(".numRegion").attr('data-info');
    var prices = money * figure;
    if ($(btn).children('.photo_btn').hasClass('selected')) {
      $(btn).children('.photo_btn').removeClass('selected');
    } else {
      $(btn).children('.photo_btn').addClass("selected");
    }

    this.countPrice();
  },

  countPrice: function() {
    var cartEllList = $('.shoppingCart_warp .selected');
    var money = 0;
    for (var i = 0; i < cartEllList.length; i++) {
      var price = Number($(cartEllList[i]).parent().parent().parent().find(".money").attr('data-prices'));
      var number = Number($(cartEllList[i]).parent().parent().parent().find(".numRegion").html());
      money += price * number;

    }
    $(".aggregatePrice").html(money);
    console.log(money);
  },

  commoditySelection: function(btn) {
    if ($(btn).hasClass('selected')) {
      $('.shoppingCart_warp .selected').removeClass('selected');
      $(btn).removeClass('selected');
    } else {
      $(btn).addClass('selected');
      $('.shoppingCart_warp .photo_btn').addClass('selected');
    }
    this.countPrice();
  },

  // goBuy: function(btn) {
  //   Views.sureOrderView.willShow();
  // },

  minPrices: function(btn) {
    var num = $(btn).parent().find(".numRegion").html(); //获取数量值
    var reveal = parseFloat(num) - 1; //数量减一
    if (reveal == 0) {
      reveal = 1;
    }
    $(btn).parent().find(".numRegion").html(reveal); //显示数量
    this.countPrice();
  },


  plusPrices: function(btn) {
    var num = $(btn).parent().find(".numRegion").html(); //获取数量值
    var reveal = parseFloat(num) + 1; //数量加一
    $(btn).parent().find(".numRegion").html(reveal); //显示数量
    var prices = $(btn).parent().parent().find(".money").attr("data-prices"); //获取单价
    this.countPrice();
  },

  delet: function(btn) {
    var self = this;
    var data = {
      bssctUuid: dataGet('userUuid')
    };
    ajaxTool("post", data, WEB_URL + "busiShoppingCart/delete/one",
      function error(XMLHttpRequest, textStatus, errorThrown, fnErr) {
        alert("error:" + data);
      },
      function success(data) {
        if (!data.success) {
          alert(data.errMsg);
        } else {
          console.log(data);
          backPage();
        }
      }, true
    );
  },

    openDetails:function(btn) {
      dataSave('productUuid', $(btn).attr('data-uuid'));
      Views.productDescView.willShow();
    },

    delet:function(btn) {
      var self = this;
      var shoppId=$(btn).attr("data-uid");
      var data = {bssctUuid:shoppId};
      ajaxTool("post", data, WEB_URL + "busiShoppingCart/delete/one",
          function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
              alert("error:" + data);
          },
          function success(data){
              if(!data.success) {
                  alert(data.errMsg);
              }else{
                  console.log(data);
                  $(btn).parent().parent("div").remove(".shoppingCart_area")
                  alert("已删除！");
              }
          },true
      );  
    },

  cartGoBuy: function() {
    var cartEllList = $('.shoppingCart_warp .selected');
    if (cartEllList.length == 0) {
      alert('请选择你要购买的物品');
      return;
    }
    var selectList = [];
    for (var i = 0; i < cartEllList.length; i++) {
      var dataIndex = Number($(cartEllList[i]).attr('data-index'));

      this.shoppingList[i].bssctQuantity = Number($(cartEllList[i]).parent().parent().parent().find(".numRegion").html());
      selectList.push(this.shoppingList[dataIndex]);
    }
    JdataSave('selectCartList', selectList);
    dataSave('totalPrice', $('.aggregatePrice').html());

    var self = this;
    var data = {
      bssasUser: dataGet('userUuid')
    };
    ajaxTool("post", data, WEB_URL + "busiShippingAddress/views/my/default",
      function error(XMLHttpRequest, textStatus, errorThrown, fnErr) {
        alert("error:" + data);
      },
      function success(data) {
        if (!data.success) {
          alert(data.errMsg);
        } else {
          if (data.data) {
            JdataSave('addressBuy', data.data);
          } else {
            JdataSave('addressBuy', null);
          }

          Views.sureOrderCartView.willShow();



          // self.show({orderInfo: buyProduct, address: data.data}, isBackPage);
          // self.show({shoppingInfo: data.data, WEB_URL:WEB_URL}, isBackPage);
        }
      }, true
    );

  },
});


/***********************确认订单购物车**********************/
Views.sureOrderCartView = $.extend({}, Views.PanelView, {
  options: {
    tmpl: 'sureOrderCart'
  },

  willShow: function(param, isBackPage) {
    // var buyProduct = JdataGet('buyProduct');
    var selectCartList = JdataGet('selectCartList');
    var addressBuy = JdataGet('addressBuy');
    var totalPrice = dataGet('totalPrice');
    this.show({
      selectCartList: selectCartList,
      addressBuy: addressBuy,
      totalPrice: totalPrice,
      WEB_URL: WEB_URL
    }, isBackPage);
  },

  didShow: function(param, isBackPage) {
    // initWx();
  },

  selectAddress: function(btn) {
    Views.addressManageView.willShow();
  },

  subtract: function(btn) {
    var numberEl = $(btn).parent().find('.number');
    var number = parseInt(numberEl.html());
    number--;
    if (number < 1) {
      return;
    }
    numberEl.html(number);
    $(btn).parent().parent().parent().find('.number_x').html(number);
    var price = $(btn).parent().parent().parent().find('.price_red span').html();
    $(btn).parent().parent().parent().find('.sum_black_val').html(number);
    $(btn).parent().parent().parent().find('.sum_price_val').html(number * price);
    var sumEls = $('.sum_price_val');
    var countPrice = 0;
    for (var i = 0; i < sumEls.length; i++) {
      countPrice += Number($(sumEls[i]).html());
    }

    var bsorrExpressValue = 0; //运费总价
    var bsorrExpressValueEl = $('.kd_type');
    for (var i = 0; i < bsorrExpressValueEl.length; i++) {
      bsorrExpressValue += Number($(bsorrExpressValueEl[i]).attr('data-express'));
    }

    $('#sumPrice').html(bsorrExpressValue + countPrice);
  },

  add: function(btn) {
    var numberEl = $(btn).parent().find('.number');
    var number = parseInt(numberEl.html());
    number++;
    numberEl.html(number);
    $(btn).parent().parent().parent().find('.number_x').html(number);
    var price = $(btn).parent().parent().parent().find('.price_red span').html();
    $(btn).parent().parent().parent().find('.sum_black_val').html(number);
    var totalPrice = Number(number) * Number(price);
    $(btn).parent().parent().parent().find('.sum_price_val').html(totalPrice.toFixed(2));

    var sumEls = $('.sum_price_val');
    var countPrice = 0;
    for (var i = 0; i < sumEls.length; i++) {
      countPrice += Number($(sumEls[i]).html());
    }

    var bsorrExpressValue = 0; //运费总价
    var bsorrExpressValueEl = $('.kd_type');
    for (var i = 0; i < bsorrExpressValueEl.length; i++) {
      bsorrExpressValue += Number($(bsorrExpressValueEl[i]).attr('data-express'));
    }

    $('#sumPrice').html(countPrice + bsorrExpressValue);
  },

  upHeadOrder: function() {
    var addressBuy = JdataGet('addressBuy');
    if (!addressBuy) {
      alert('请填写收件人信息');
      return;
    }

    var bsorrProductValue = 0; //商品总价
    var price_red_valEl = $('.sum_price_val');
    for (var i = 0; i < price_red_valEl.length; i++) {
      bsorrProductValue += Number($(price_red_valEl[i]).html());
    }

    var bsorrExpressValue = 0; //运费总价
    var bsorrExpressValueEl = $('.kd_type');
    for (var i = 0; i < bsorrExpressValueEl.length; i++) {
      bsorrExpressValue += Number($(bsorrExpressValueEl[i]).attr('data-express'));
    }

    var bsorrTotalQuantity = 0; //商品总数
    var number_xEl = $('.number_x');
    for (var i = 0; i < number_xEl.length; i++) {
      bsorrTotalQuantity += Number($(number_xEl[i]).html());
    }


    var sureOrder_item_border = $('.sureOrder_item_border');
    var selectCartList = JdataGet('selectCartList');
    var orderPayItemList = [];
    for (var i = 0; i < sureOrder_item_border.length; i++) {
      orderPayItemList.push({
        bsoimProduct: selectCartList[i].bssctUuid,
        bsoimProductName: selectCartList[i].bssctName,
        bsoimSharePic: selectCartList[i].bssctSharePic,
        bsoimStorePic: selectCartList[i].bssctStorePic,
        bsoimAttrName: selectCartList[i].bssctAttrName,
        bsoimAttrValue: selectCartList[i].bssctAttrValue,
        // bsoimTags: buyProduct.bsprtProductTagNames.join('|'),
        bsoimQuantity: $('.number_x').eq(i).html(),
        bsoimExpress: 0,
        bsoimIsBom: 0,
        bsoimValue: $('.sum_price_val').eq(i).html(),
        bsoimPrice: $('.price_red span').eq(i).html(),
        bsoimBuyerMemo: $('.leaveMessage').eq(i).val()
      });
    }

    // var shareProductUuid = dataGet('shareProductUuid');
    // var productUuid = dataGet('productUuid');
    // var shareUserUuid = "";

    // var shareUserUuid = "";
    // if(shareProductUuid == productUuid) {
    //   shareUserUuid = dataGet('shareUserUuid');
    // }

    var orderPay = {
      shareUserUuid: "",
      bsorrUser: dataGet('userUuid'),
      bsorrProductValue: bsorrProductValue,
      bsorrExpressValue: bsorrExpressValue,
      bsorrOrderValue: bsorrProductValue + bsorrExpressValue,
      bsorrActualPay: bsorrProductValue + bsorrExpressValue,
      bsorrTotalValue: bsorrProductValue + bsorrExpressValue,
      bsorrTotalQuantity: bsorrTotalQuantity,
      bsorrName: $('#receiveName').html(),
      bsorrMobile: $('#phoneNo').html(),
      bsorrProvince: addressBuy.bssasProvince,
      bsorrProvinceName: addressBuy.bssasProvinceName,
      bsorrCity: addressBuy.bssasCity,
      bsorrCityName: addressBuy.bssasCityName,
      bsorrCounty: addressBuy.bssasCounty,
      bsorrCountyName: addressBuy.bssasCountyName,
      bsorrAddress: addressBuy.bssasAddress,
      orderPayItemStr: JSON.stringify(orderPayItemList)
    }

    ajaxTool("post", orderPay, WEB_URL + "pay/get/package/go/pay",
      function error(XMLHttpRequest, textStatus, errorThrown, fnErr) {
        alert("error:" + data);
      },
      function success(data) {
        if (!data.success) {
          alert(data.errMsg);
        } else {
          // console.log(data);
          // backPage();
          goToPay(data);
        }
      }, true
    );
  }
});

/***********************确认订单**********************/
Views.sureOrderView = $.extend({}, Views.PanelView, {
  options: {
    tmpl: 'sureOrder'
  },

  willShow: function(param, isBackPage) {
    var self = this;
    var buyProduct = JdataGet('buyProduct');
    var addressBuy = JdataGet('addressBuy');
    var totalPrice = dataGet('totalPrice');
    this.price = buyProduct.bssctFee;
    self.express = Number(buyProduct.bsprtExpress);
    var bssctFee =  Number(buyProduct.bssctQuantity) * this.price + this.express;
    var quantity = Number(buyProduct.bssctQuantity) * this.price;//小计
    var whole = Number(quantity) + this.express;//总计
    this.show({
      orderInfo: buyProduct,
      bssctFee:bssctFee,
      quantity:quantity,
      whole:whole,
      addressBuy: addressBuy,
      WEB_URL: WEB_URL
    }, isBackPage);
    
  },

  didShow: function(param, isBackPage) {
    // initWx();
  },

  selectAddress: function(btn) {
    Views.addressManageView.willShow();
  },

  subtract: function(btn) {
    var numberEl = $(btn).parent().find('.number');
    var number = parseInt(numberEl.html());
    number--;
    if (number < 1) {
      return;
    }
    numberEl.html(number);
    $(btn).parent().parent().parent().find('.number_x').html(number);
    // $(btn).parent().parent().parent().find('.price_red_val').html(number*this.price);
    $(btn).parent().parent().parent().find('.sum_black_val').html(number);
    $(btn).parent().parent().parent().find('.sum_price_val').html(number * this.price);
    var total = number * this.price + this.express;
    $('#sumPrice').html(total);
  },

  add: function(btn) {
    var numberEl = $(btn).parent().find('.number');
    var number = parseInt(numberEl.html());
    number++;
    numberEl.html(number);
    $(btn).parent().parent().parent().find('.number_x').html(number);
    // $(btn).parent().parent().parent().find('.price_red_val').html(number*this.price);
    $(btn).parent().parent().parent().find('.sum_black_val').html(number);
    $(btn).parent().parent().parent().find('.sum_price_val').html(number * this.price);
    var total = number * this.price + this.express;
    $('#sumPrice').html(total);
  },

  upHeadOrder: function() {
    var addressBuy = JdataGet('addressBuy');
    if (!addressBuy) {
      alert('请填写收件人信息');
      return;
    }

    var bsorrProductValue = 0; //商品总价
    var price_red_valEl = $('.sum_price_val');
    for (var i = 0; i < price_red_valEl.length; i++) {
      bsorrProductValue += Number($(price_red_valEl[i]).html());
    }

    var bsorrExpressValue = 0; //运费总价
    var bsorrExpressValueEl = $('.kd_type');
    for (var i = 0; i < bsorrExpressValueEl.length; i++) {
      bsorrExpressValue += Number($(bsorrExpressValueEl[i]).attr('data-express'));
    }

    var bsorrTotalQuantity = 0; //商品总数
    var number_xEl = $('.number_x');
    for (var i = 0; i < number_xEl.length; i++) {
      bsorrTotalQuantity += Number($(number_xEl[i]).html());
    }

    var buyProduct = JdataGet('buyProduct');

    var shareProductUuid = dataGet('shareProductUuid');
    var productUuid = dataGet('productUuid');
    var shareUserUuid = "";
    if (shareProductUuid == productUuid) {
      shareUserUuid = dataGet('shareUserUuid');
    }

    // shareUserUuid
    var orderPayItemList = [{
      bsoimProduct: buyProduct.bsprtUuid,
      bsoimProductName: buyProduct.bssctName,
      bsoimSharePic: buyProduct.bssctSharePic,
      bsoimStorePic: buyProduct.bssctStorePic,
      bsoimAttrName: buyProduct.bssctAttrName,
      bsoimAttrValue: buyProduct.bssctAttrValue,
      bsoimTags: buyProduct.bsprtProductTagNames.join('|'),
      bsoimQuantity: $('.number_x').html(),
      bsoimExpress: buyProduct.bsprtExpress,
      bsoimIsBom: buyProduct.bsoimIsBom,
      bsoimValue: $('.sum_price_val').html(),
      bsoimPrice: $('.price_red span').html(),
      bsoimBuyerMemo: $('.leaveMessage').val(),
      shareUserUuid: shareUserUuid,
    }];


    var orderPay = {
      bsorrUser: dataGet('userUuid'),
      bsorrProductValue: bsorrProductValue,
      bsorrExpressValue: bsorrExpressValue,
      bsorrOrderValue: bsorrProductValue + bsorrExpressValue,
      bsorrActualPay: bsorrProductValue + bsorrExpressValue,
      bsorrTotalValue: bsorrProductValue + bsorrExpressValue,
      bsorrTotalQuantity: bsorrTotalQuantity,
      bsorrName: $('#receiveName').html(),
      bsorrMobile: $('#phoneNo').html(),
      bsorrProvince: addressBuy.bssasProvince,
      bsorrProvinceName: addressBuy.bssasProvinceName,
      bsorrCity: addressBuy.bssasCity,
      bsorrCityName: addressBuy.bssasCityName,
      bsorrCounty: addressBuy.bssasCounty,
      bsorrCountyName: addressBuy.bssasCountyName,
      bsorrAddress: addressBuy.bssasAddress,
      orderPayItemStr: JSON.stringify(orderPayItemList)
    }

    ajaxTool("post", orderPay, WEB_URL + "pay/get/package/go/pay",
      function error(XMLHttpRequest, textStatus, errorThrown, fnErr) {
        alert("error:" + data);
      },
      function success(data) {
        if (!data.success) {
          alert(data.errMsg);
        } else {
          console.log(data);
          // backPage();
          goToPay(data);
        }
      }, true
    );
  }
});

/***********************404**********************/

Views.page_404View = $.extend({}, Views.PanelView, {
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

Views.page_500View = $.extend({}, Views.PanelView, {
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
Views.shoppingEmptyView = $.extend({}, Views.PanelView, {
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
Views.myorderEmptyView = $.extend({}, Views.PanelView, {
  options: {
    tmpl: 'myorderEmpty',
  },

  willShow: function(param, isBackPage) {
    this.show(param, isBackPage);
  },

  didShow: function() {

  }
});