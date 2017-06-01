/**
 * Created by dell on 2017/5/31.
 */
var newtimer = null;
/***关闭弹框***/
function modelClose(me){
    $(me).parent().parent().hide();
};
//弹框之间切换时
function modelSwitch(){
    $(".model").hide();
    $(".model_content").css("left",window.innerWidth/2-250);
    $(".model_content").css("top",window.innerHeight/2-230);
    clearInterval(newtimer);
    newtimer = null;
    $(".getCode").html("获取验证码").removeClass("huoqu_success");
    $(".model_form input").val("");
}
//登录弹框出现
function loginShow(){
    modelSwitch();
    $("#loginModel").show();
}
//注册弹框出现
function registerShow(){
    modelSwitch();
    $("#registerModel").show();
    console.log(33);
}
//找回密码出现
function findPwdShow(){
    modelSwitch();
    $("#findPwdModel").show();
}

/*******************登录***************/
//验证登录账号
function checkAccount(type){
    var elm=$(type+".user_account");
    var userAccount=elm.val();
    if(userAccount==""){
        elm.parent().children(".error_tip").html("请输入您的账号");
        return false;
    }
    elm.parent().children(".error_tip").html("");
    return true;
}
//验证登录密码
function checkPwd(type){
    var elm=$(type+".user_pwd");
    var userPwd=elm.val();
    if( (userPwd=="")){
        elm.parent().children(".error_tip").html("请输入密码");
        return false;
    }
    elm.parent().children(".error_tip").html("");
    return true;
}

//登录表单提交
$("#loginSubmit").unbind("click").click(function(){
    var type="#loginModel ";
    var username=$(".user_account").val();
    var password=$(".user_pwd").val();
    if( (!checkAccount(type)) || (!checkPwd(type)) ){
        return false;
    }else{
        //上传表单
        $.ajax({
            url:"http://sso.qtbigdata.com/ssoUser/login?ajax=true&sysid=msvtPqZBiqX5qeOaXUEFL5AilgBSA2KTFu0WN74T4MIHjWUMxYbTvQ%3D%3D",
            type:"POST",
            dataType:"json",
            async:true,
            data:{
                username:username,
                password:password
            },

            xhrFields:{
                withCredentials:true
            },
            success:function(data){
                //如果登录成功

                //location.reload();
                if(data.status==200){
                    $("#loginModel").hide();
                    location.href="/callback";
                }else{
                    $(".error_tip").html("请输入正确的账号及密码");
                }

            },
            error:function(data){
                console.log(data);
            }
        });

    }
});

/************注册*****************/



    //验证手机号
    function checkPhone(type){
        console.log(type);

        var elm=$(type+" .user_phone");
        var phone= elm.val();
        var regPhone = /^13[0-9]{9}$|14[0-9]{9}|17[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/;
        if(phone==""){
            elm.parent().children(".error_tip").html("请输入手机号");
            return false;
        }else if (!(regPhone.test(phone))){
            elm.parent().children(".error_tip").html("请输入正确的手机号");
            return false;
        }
        elm.parent().children(".error_tip").html("");
        return true;
    }

//获取注册手机验证码
    function getPhoneCode(type){
        var elem=$(type+".getCode");
        var phone=$(type+".user_phone").val();
        if( (!checkPhone(type)) ){
            return false;
        }else if (elem.attr("class").indexOf("huoqu_success") != -1) {
            return false;
        }else{
            $.ajax({
                type: "get",
                url: "http://sso.qtbigdata.com/ssoUser/sendSms?ajax=true&sysid=zypXpOz8bjEudhPF8bNf3YrGaoHTgKgGId6pkYs8lSHmZMdRyMWaPg==&phone="+phone,
                dataType: "json",
                success: function(data){
                    //如果成功
                    console.log(data);
                    if(data.status==200){
                        timeSecond_phone();

                    }else if(data.status==500){
                        $(".error_tip").html(data.msg);
                        clearInterval(newtimer);
                        newtimer = null;
                    }

                },
                error:function(data){
                    console.log(data);
                }
            });
        }
    }
    function timeSecond_phone(){
        var timercount = 120;
        newtimer = setInterval(function(){
            timercount--;
            $(".getCode").html(timercount+"秒后重新获取");
            $(".getCode").addClass("huoqu_success");

            if(timercount<=0){
                clearInterval(newtimer);
                newtimer = null;
                console.log(timercount);
                $(".getCode").html("重新获取验证码").removeClass("huoqu_success");
            }
        },1000);
    }

//验证注册手机验证码不为空
    function checkPhoneCode(type){
        var elm=$(type+".phone_code");
        var phoneCode= elm.val();
        if(phoneCode==""){
            elm.parent().parent().children(".error_tip").html("请输入短信验证码");
            return false;
        }
        elm.parent().parent().children(".error_tip").html("");
        return true;
    }


//验证注册密码
    function checkPwd(type){
        var elm=$(type+".user_pwd3");
        var userPwd=elm.val();
        if( (userPwd=="")){
            elm.parent().children(".error_tip").html("请输入密码");
            return false;
        }
        elm.parent().children(".error_tip").html("");
        return true;
    }
//注册验证第二次新密码
    function checkPwd2(type){

        var elm=$(type+".user_pwd3").val();
        var elm2=$(type+".user_pwd2").val();
        if( elm2==""){
            $(type+".user_pwd2").parent().children(".error_tip").html("请再次输入新密码");
            return false;
        }else if(elm2!=elm){
            $(type+".user_pwd2").parent().children(".error_tip").html("两次输入密码不一致");
            return false;
        }
        $(type+".user_pwd2").parent().children(".error_tip").html("");
        return true;
    }
//验证是否同意注册协议
    function checkAgreement(type){
        var elm=$(type+".agreement");
        var isAgree=elm[0].checked;
        if(!isAgree){
            elm.parent().parent().children(".error_tip").html("请先同意用户注册协议");
            return false;
        }else{
            elm.parent().parent().children(".error_tip").html("");
            return true;
        }
    }

function checkall(type){
    if(!checkPhone(type)){
        return false;
    }
    if(!checkPhoneCode(type)){
        return false;
    }
    if(!checkPwd(type)){
        return false;
    }
    if(!checkPwd2(type)){
        return false;
    }if(!checkAgreement(type)){
        return false;
    }

    return true;
}


//提交注册表单
$("#registerSubmit").unbind("click").click(function(){
    var type="#registerModel ";


    if( !checkall(type) ){
        console.log("aa");
        return false;
    }else{
        //上传表单
        console.log(0);
        $.ajax({
            url:"http://sso.qtbigdata.com/ssoUser/register?ajax=true&sysid=zypXpOz8bjEudhPF8bNf3YrGaoHTgKgGId6pkYs8lSHmZMdRyMWaPg==",
            type:"POST",
            dataType:"json",
            async:true,
            data:{
                phone:$("#registerModel .user_phone").val(),
                mobileValidateCode:$("#registerModel .phone_code").val(),
                password:$("#registerModel .user_pwd3").val(),
                password2:$("#registerModel .user_pwd2").val()
            },
            xhrFields:{
                withCredentials:true
            },
            success:function(data){
                //如果登录成功
                console.log(data);
                //location.reload();
                if(data.status==200){
                    layer.msg("注册成功", {
                        icon: 1,
                        time: 3000 //3秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        loginShow();

                    });

                }else if(data.status==400){
                    $(".error_tip").html(data.msg);

                }

            },
            error:function(data){
                console.log(data);
            }
        });

    }
});










