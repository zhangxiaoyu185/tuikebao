function commLogin() {
    var accountName = dataGet('accountName');
    var accountPassword = dataGet('accountPassword');
    if(!accountName || !accountPassword) {
        return;
    }

    var self = this;
    var data = {
        mobile: accountName,
        password: accountPassword
    };
    var url = WEB_URL + "coreUser/store/login";

    //累计邀请
    ajaxTool("post", data, url,
        function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
            alert("error:" + data);
        },
        function success(data){
            if(!data.success || !data.data.crusrOpenidStore) {
                dataSave('isNotLogin', "true");
                dataSave('accountName', '');
                dataSave('accountPassword', '');
                JdataSave('userInfo', '');
                dataSave('userUuid', '');
                // dataSave('userCode', '');
                dataSave("crusrGrade", '');
            }else{
                JdataSave('userInfo', data.data);
                dataSave('userUuid', data.data.crusrUuid);
                dataSave('userCode', data.data.crusrCode);
                dataSave('isNotLogin', "false");
            }
        },true
    );
}

function isLogin() {
    // var isNotLogin = dataGet('isNotLogin');
    var accountName = dataGet('accountName');
    var accountPassword = dataGet('accountPassword');

    if(!accountName || !accountPassword) {
        // nextView('http://localhost:8080/tuikebao/views/page/OAuthGetCodeLogin.html');
        nextView(WEB_URL + 'views/page/OAuthGetCodeLogin.html');
        // Views.loginPageView.willShow(null, false);
        return false;
    }
    return true;
}



// function getGradeInfo() {
//     //查询用户信息
//     var data = {crusrUuid:  dataGet('userUuid')}
//     var url = WEB_URL + "coreUser/views";
//     ajaxTool("post",data,url,
//         function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
//             alert("error:请求失败");
//         },
//         function success(data){
//             if(!data.success) {
//                 alert(data.errMsg);
//             }else{
//                 dataSave("grade",data.data.crusrGrade);
//                 var crusrGrade ="grade_"+data.data.crusrGrade;
//                 dataSave("crusrGrade", crusrGrade);                 
//             }
//         },true
//     );       
// }

// var commLogin = {
//     login: function() {
//         var accountName = dataGet('accountName');
//         var accountPassword = dataGet('accountPassword');
//         if(!accountName || !accountPassword) {
//             return;
//         }
//     },

//     isLogin: function() {
//         var isLogin = dataGet('isLogin');
//         if(!isLogin || isLogin == 'false') {
//             Views.loginPageView.willShow();
//             return false;
//         }
//         return true;
//     }
// }


/***********************登录首页**********************/
Views.loginPageView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'loginPage'
    },

    willShow: function(param, isBackPage) {
         this.show(param, false);
        //this.show(param, true);
    },

    didShow: function() {
        
    },

    forgetPassword:function() {
        Views.forgetPasswordView.willShow();
    },

    register:function() {
        Views.registerView.willShow();
    },

    logIn: function() {
        var accountName = $('#accountName').val();
        var accountPassword = $('#accountPassword').val();
        var isPhoneNo = IsMobile(accountName); 
        if(!isPhoneNo) {
            alert('请输入正确的手机号！');
            return;
        }

        if(accountPassword.length<6) {
            alert('请输入6位以上的密码！');
            return;
        }



        var self = this;
        var data = {
            mobile: accountName,
            password: accountPassword,
            code: dataGet('code')
        };
        var url = WEB_URL + "coreUser/store/login";
        ajaxTool("post", data, url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                    dataSave('isNotLogin', "true");
                    dataSave('accountName', '');
                    dataSave('accountPassword', '');
                    JdataSave('userInfo', '');
                    dataSave('userUuid', '');
                    // dataSave('userCode', '');
                    dataSave("crusrGrade", '');
                }else{
                    dataSave('accountName', accountName);
                    dataSave('accountPassword',  accountPassword);

                    JdataSave('userInfo', data.data);
                    dataSave('userUuid', data.data.crusrUuid);
                    dataSave('userCode', data.data.crusrCode);
                    dataSave('isNotLogin', "false");
                     //backPage();
                    //window.history.go(-4);
                    window.history.back();
                }
            },true
        );
    } 
});

/***********************忘记密码**********************/

Views.forgetPasswordView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'forgetPassword'
    },

    willShow: function(param, isBackPage) {
        this.show(param, false);
    },

    didShow: function() {
        this.countdown=60;
    },

     verificationCode:function (btn) {
        var self=this;

        if(this.countdown == 60) {
            var self = this;
            var forgetPhoneNo = $('#forgetPhoneNo').val();
            var isPhoneNo = IsMobile(forgetPhoneNo); 
            if(!isPhoneNo) {
                alert('请输入正确的手机号！');
                return;
            }
            var data = {crmceMobile: forgetPhoneNo};
            var url = WEB_URL + "coreShortMessage/store/forget/send";
            ajaxTool("post", data, url,
                function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                    alert("error:" + data);
                },
                function success(data){
                    if(!data.success) {
                        alert(data.errMsg);
                    }else{
                        
                    }
                }
            ); 
        } 
        
        if (this.countdown == 0) {  
            $(btn).removeAttr("disabled");
            $(btn).html("发送验证码"); 

            clearTimeout(function(){
                self.verificationCode(btn);});
            this.countdown = 60;

        } else { 
            $(btn).attr("disabled",true); 
            $(btn).html("重新发送(" + this.countdown + ")"); 
            this.countdown--; 

            setTimeout(function(){
                self.verificationCode(btn);
            },1000);
        }  
    },

    sumbitForgetPassword: function() {
        var forgetPhoneNo = $('#forgetPhoneNo').val();
        var isPhoneNo = IsMobile(forgetPhoneNo); 
        if(!isPhoneNo) {
            alert('请输入正确的手机号！');
            return;
        }
        var password = $('#password').val();
        if(password.length<6) {
            alert('请输入6位以上的密码！');
            return;
        }
        var confirmPassword = $('#confirmPassword').val();
        if(password != confirmPassword) {
            alert('输入的两次密码不一致！');
            return;
        }
        var code = $('#code').val();
        if(!code) {
            alert('验证码不能为空！');
            return;
        }

        var data = {
            mobile: forgetPhoneNo,
            password: password,
            confirmPassword: confirmPassword,
            code: code
        };

        var url = WEB_URL + "coreUser/store/forget";

        //累计邀请
        ajaxTool("post", data, url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    window.history.go(-2);
                }
            }
        );
    }
    
});


/***********************注册**********************/

Views.registerView =  $.extend({}, Views.PanelView, {
    options: {
        tmpl: 'register'
    },

    willShow: function(param, isBackPage) {
        this.show(param, false);
    },

    didShow: function() {
        this.countdown=60;
    },


    verificationCode:function (btn) {
        var self=this; 

        if(this.countdown == 60) {
            var self = this;
            var registerPhoneNo = $('#registerPhoneNo').val();
            var isPhoneNo = IsMobile(registerPhoneNo); 
            if(!isPhoneNo) {
                alert('请输入正确的手机号！');
                return;
            }
            var data = {crmceMobile: registerPhoneNo};
            var url = WEB_URL + "coreShortMessage/store/register/send";

            //累计邀请
            ajaxTool("post", data, url,
                function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                    alert("error:" + data);
                },
                function success(data){
                    if(!data.success) {
                        alert(data.errMsg);
                    }else{
                        
                    }
                }
            ); 
        }
        
        if (this.countdown == 0) {  
            $(btn).removeAttr("disabled");
            $(btn).html("发送验证码"); 

            clearTimeout(function(){
                self.verificationCode(btn);
            });
            this.countdown = 60;

        } else { 
            $(btn).attr("disabled",true); 
            $(btn).html("重新发送(" + this.countdown + ")"); 
            this.countdown--; 

            setTimeout(function(){
                self.verificationCode(btn);
            }, 1000);
        }
    },

    toRegister: function() {
        var registerPhoneNo = $('#registerPhoneNo').val();
        var isPhoneNo = IsMobile(registerPhoneNo); 
        if(!isPhoneNo) {
            alert('请输入正确的手机号！');
            return;
        }
        var password = $('#password').val();
        if(password.length<6) {
            alert('请输入6位以上的密码！');
            return;
        }
        var confirmPassword = $('#confirmPassword').val();
        if(password != confirmPassword) {
            alert('输入的两次密码不一致！');
            return;
        }
        var code = $('#code').val();
        if(!code) {
            alert('验证码不能为空！');
            return;
        }
        var shareUcode = dataGet('shareUserUuid');


        var data = {
            mobile: registerPhoneNo,
            password: password,
            confirmPassword: confirmPassword,
            code: code,
            shareUcode: shareUcode,
        };

        var url = WEB_URL + "coreUser/store/register";
        ajaxTool("post", data, url,
            function error(XMLHttpRequest, textStatus, errorThrown,fnErr){
                alert("error:" + data);
            },
            function success(data){
                if(!data.success) {
                    alert(data.errMsg);
                }else{
                    // backPage();
                    window.history.go(-2);
                }
            }
        );
    }
    
});


