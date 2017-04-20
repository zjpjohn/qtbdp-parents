/**点击切换右侧页面*/
$(".pereach>li").unbind().click(function(){
    $(this).addClass("active").siblings(".active").removeClass("active");
    var centerleft=$(this).attr("data-class");
    $("."+centerleft+"2").addClass("active").siblings(".active").removeClass("active");
    var settings;
    switch (centerleft) {
        case "overview":
            // 页面请求参数
            var settings = {
                url: "/api/usercenter/neworders",      // 请求地址
                tmpl_id: "#tmpl_order" ,     //  tmpl 模板元素id
                target: "#order_list1" ,       // 替换html元素id
               // pager_id: "#pageTool",           // 分页
                params: [{key:"userId",value:[1] }]
            }
            pageData.products(settings) ;
            break;
        case "myorder":
            break;
        case "myrelease":
            break;
    }

});

//点击我的账户  余额明细、积分明细
$(".convertye>a").click(function(){
    $(this).addClass("active").siblings(".active").removeClass("active");
    var convertye=$(this).attr("data-index");
    $("."+convertye+"2").addClass("active").siblings(".active").removeClass("active");
});


//个人信息 前端验证
function check1(){
    var regphone =/^1[34578]\d{9}$/;
    var result= regphone.test($.trim($("#phoneNUM").val()));
    if($.trim($("#phoneNUM").val())==""){
        $("#phoneNUM").next(".error").css("display","block");
        return false;
    }else if($.trim($("#phoneNUM").val()).length>11||result==false){
        $("#phoneNUM").next(".error").html("请输入正确的手机号码");
        return false;
    }else{
        $("#phoneNUM").next(".error").html("");
        return true;
    }
}
$("#phoneNUM").blur(function(){
    check1();
});

function check2(){
    /*<![CDATA[*/
    var emalnum =/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
    var result= emalnum.test($.trim($("#person_emal").val()));
    console.log(result);
    if($.trim($("#person_emal").val())==""){
        $("#person_emal").next(".error").css("display","block");
        return false;
    }else if(result==false){
        $("#person_emal").next(".error").html("请输入正确的邮箱");
        return false;
    }else{
        $("#person_emal").next(".error").html("");
        return true;
    }
    /*]]>*/
}
$("#person_emal").blur(function(){
    check2();
});