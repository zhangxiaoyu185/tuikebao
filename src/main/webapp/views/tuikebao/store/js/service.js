/***********************服务index**********************/
Views.serviceIndexView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'serviceIndex',
        hasFootNav: true,
        footItemOrder:3, // hasFootNav设置true才有效   表示现在远着的是底部第几个菜单 0开始
        itemClass: 'item'
    },

    willShow: function(param, isBackPage) {
        var self = this;
        var url = WEB_URL + "busiServerType/store/find/all";
        ajaxTool("post",null,url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.listData = data.data; 
                    self.show({list: self.listData, WEB_URL: WEB_URL}, isBackPage);
                }
            },true
        );
    },

    didShow: function() {
    },

    goList: function(btn) {
        var list = this.listData;
        var index = parseInt($(btn).attr('data-index'));
        if(list[index].bssteIslist==0) {
            window.location.href = list[index].bssteDetails;
        }else {
            var bssteUuid = list[index].bssteUuid;
            Views.serviceListView.willShow(bssteUuid);
        } 
    },

    goOnline: function(btn) {
        Views.onLineView.willShow();
    }
});

/***********************服务List**********************/
Views.serviceListView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'serviceList',
        hasFootNav: true,
        footItemOrder:3, // hasFootNav设置true才有效   表示现在远着的是底部第几个菜单 0开始
        itemClass: 'item'
    },

    willShow: function(param, isBackPage) {
        dataSave('bssteUuid', param);
        this.show(param, isBackPage);
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
            bssdcType: dataGet('bssteUuid'),
            pageNum: pageNum,
            pageSize: pageSize
        };
        var url = WEB_URL + "busiServerDynamic/find/by/cnd/before";
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
                        self.scrollInit('serviceListItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollDown") {
                        self.scrollDown('serviceListItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                    if (type == "scrollUp") {
                        self.scrollUp('serviceListItem',{list: data.data.result, WEB_URL: WEB_URL});
                    };
                }
            },true
        );
    },

    goDesc:function(btn) {
        var bssteUuid = $(btn).attr('data-bssteUuid');
        Views.serviceDescView.willShow(bssteUuid);
    }  
});

/***********************服务Desc**********************/
Views.serviceDescView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'serviceDesc'
    },

    willShow: function(param, isBackPage) {
        var self = this;
        var uuid = param;
        var data = {bssdcUuid: uuid};
        var url = WEB_URL + "busiServerDynamic/index/views";
        ajaxTool("post", data, url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:请求失败");
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    self.listData = data.data;
                    self.show({serviceDesc:data.data}, isBackPage);
                    self.serviceContent = data.data.bssdcContent;
                }
            },true
        );
    },

    didShow: function() {
      $('#serviceContent').html(this.serviceContent);
    }
    
});

/***********************在线客服**********************/
Views.onLineView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'onLine'
    },

    willShow: function(param, isBackPage) {
        this.show(param, isBackPage);
    },

    didShow: function() {
      
    }
    
});



