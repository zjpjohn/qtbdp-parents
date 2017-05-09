/**
 * Created by zd on 2017/4/25.
 */
$(function(){
    $(".system-item").addClass("active").siblings(".active").removeClass("active");
    $(".jigou-item").trigger("click");
    //返回用户列表
    $(".returnList").click(function(){
        $(".page-content-wrapper").load("/user/userList",{});
    });

    $("#publishForm").validate({
        rules:{
            no:"required",
            name:"required",
            loginName:"required",
            newPassword:{
                required: true,
                minlength:6
            },
            newPassword2:{
                required: true,
                minlength:6,
                equalTo: "#newPassword"
            },
            "role.id":"required"
        },
        messages:{
            no:"请输入工号",
            name:"请输入姓名",
            loginName:"请输入登录名",
            newPassword:{
                required:"请输入密码",
                minlength:"密码长度必须大于6位"
            },
            newPassword2:{
                required:"请输入确认密码",
                minlength:"密码长度必须大于6位",
                equalTo:"两次密码输入不一致"
            },
            "role.id":"请选择用户角色"
        },
        errorPlacement: function (error, element) { //指定错误信息位置
            if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                var eid = element.attr('name'); //获取元素的name属性
                error.appendTo(element.parent().parent().parent().parent()); //将错误信息添加当前元素的父结点后面
            } else {
                error.insertAfter(element);
            }
        },
        submitHandler:function(form){
            form.submit();
        }
    })



})
