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
//验证各系统的地址
var sysid="msvtPqZBiqX5qeOaXUEFL5AilgBSA2KTFu0WN74T4MIHjWUMxYbTvQ%3D%3D";
//登录表单提交
$("#loginSubmit").unbind("click").click(function(sysid){
    console.log(sysid);
    var type="#loginModel";
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
                $("#loginModel").hide();
                //location.reload();

                //location.href="/callback";

            },
            error:function(data){
                console.log(data);
            }
        });

    }
});



















