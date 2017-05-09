$(document).ready(function() {
    $(".oldPassword").focus();
    function checkOldPwd(){
        var content=$(".oldPassword").val();
        if(content==''){
            $("#oldPwd").html("请输入原来的密码");
            $(".oldPassword").focus();
            return false;
        }
        $("#oldPwd").html("");
        return true;
    }
    function checkOldPwd(){
        var content=$(".oldPassword").val();
        if(content==''){
            $("#oldPwd").html("请输入原来的密码");
            $(".oldPassword").focus();
            return false;
        }
        $("#oldPwd").html("");
        return true;
    }
    function checkOldPwd(){
        var content=$(".oldPassword").val();
        if(content==''){
            $("#oldPwd").html("请输入原来的密码");
            $(".oldPassword").focus();
            return false;
        }
        $("#oldPwd").html("");
        return true;
    }


    //点击保存
    $("#saveBtn").click(function(){
        var oldPassword=$(".oldPassword").val();
        var newPassword=$(".newPassword").val();
        var confirmNewPassword=$(".confirmNewPassword").val();

        if(oldPassword==''){
            $("#oldPwd").html("请输入原来的密码");
            $(".oldPassword").focus();
            return ;
        }else{
            $("#oldPwd").html("");
        }
        if(newPassword==''){
            $("#newPwd").html("请输入新密码");
            $(".newPassword").focus();
            return ;
        }else{
            $("#newPwd").html("");
        }
        if(confirmNewPassword==''){
            $("#newPwd2").html("请再次输入新密码");
            $(".confirmNewPassword").focus();
            return ;
        }else{
            $("#newPwd2").html("");
        }
        if(oldPassword!=confirmNewPassword){
            $("#newPwd2").html("两次输入密码不一样");
            $(".confirmNewPassword").focus();
            return ;
        }else{
            $("#newPwd2").html("");
        }

        $.ajax({
            dataType:"json",
            url:"/user/modifyPwd",
            data:$("#modifyPwdForm").serialize(),
            type:"post",
            success:function(data){
                console.log(data);
                /*if(data.success){
                 var id=data.data.id;
                 layer.msg("发布成功！",{
                 icon:1
                 },function(){
                 $(".page-content-wrapper").load("/ctactivity/goActivityDetail",{
                 id:id
                 });
                 });
                 }*/
            },
            error:function(){
                layer.msg("修改密码失败",{icon:5})
            }
        });
    });
});