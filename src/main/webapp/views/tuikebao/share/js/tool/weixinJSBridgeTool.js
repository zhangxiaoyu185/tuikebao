
    // var imgUrl = "图片地址";
    // var lineLink = "http://www.baidu.com";
    // var descContent = '副标题';
    // var shareTitle = '标题';
    var appid = '';

    var url = WEB_URL + "coreWechat/views";
    var data = {crwctUuid: '161126131423yqiI'};
    ajaxTool("post",data,url,
        function error(XMLHttpRequest, textStatus, errorThrown, fnErr){
            alert("error:" + data);
        },
        function success(data){
            if(!data.success) {
                alert(data.errMsg);
            }else{
                appid = data.data.crwctAppid;
            }
        },true
    );
    // 发送给好友    
    function shareFriend(imgUrl, imgUrl, lineLink, descContent, shareTitle) {
        WeixinJSBridge.invoke('sendAppMessage',{
            "appid": appid,
            "img_url": imgUrl,
            "img_width": "200",   
            "img_height": "200",
            "link": lineLink,
            "desc": descContent,
            "title": shareTitle
        }, function(res) {
            //_report('send_msg', res.err_msg);
        })
    }
    // 分享到朋友圈
    function shareTimeline(imgUrl, lineLink, descContent, shareTitle) {
        WeixinJSBridge.invoke('shareTimeline',{
            "img_url": imgUrl,
            "img_width": "200",
            "img_height": "200",
            "link": lineLink,
            "desc": descContent,
            "title": shareTitle
        }, function(res) {
               //_report('timeline', res.err_msg);
        });
    }
    // 分享到微博
    function shareWeibo(descContent, lineLink) {
        WeixinJSBridge.invoke('shareWeibo',{
            "content": descContent,
            "url": lineLink,
        }, function(res) {
            //_report('weibo', res.err_msg);
        });
    }
    // 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
    document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
        // 发送给好友
        WeixinJSBridge.on('menu:share:appmessage', function(argv){
            shareFriend();
        });
        // 分享到朋友圈
        WeixinJSBridge.on('menu:share:timeline', function(argv){
            shareTimeline();
        });
        // 分享到微博
        WeixinJSBridge.on('menu:share:weibo', function(argv){
            shareWeibo();
        });
    }, false);

