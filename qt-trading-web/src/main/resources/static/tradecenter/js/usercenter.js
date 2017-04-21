$(document).ready(function(){
    // 概览 最新订单
    var settings = {
        url: "/api/usercenter/neworders",      // 请求地址
        tmpl_id: "#tmpl_neworder" ,     //  tmpl 模板元素id
        target: "#orderList1" ,       // 替换html元素id
        // pager_id: "#pageTool",           // 分页
        params: [{key:"userId",value:1 }]
    }
    pageData.products(settings) ;
});

/**点击切换右侧页面*/
$(".pereach>li,.filter_btn>a,#moreOrder").unbind().click(function(){
    $(this).addClass("active").siblings(".active").removeClass("active");
    var centerleft=$(this).attr("data-class");
    $("."+centerleft+"2").addClass("active").siblings(".active").removeClass("active");
    switch (centerleft) {
        case "overview":
            // 概览 最新订单
            var settings = {
                url: "/api/usercenter/neworders",      // 请求地址
                tmpl_id: "#tmpl_neworder" ,     //  tmpl 模板元素id
                target: "#orderList1" ,       // 替换html元素id
                // pager_id: "#pageTool",           // 分页
                params: [{key:"userId",value:1 }]
            }
            pageData.products(settings) ;
            break;
        case "myorder":
            //我的订单数据订单
            $(".pereach>li:nth-child(4)").addClass("active").siblings().removeClass("active");
            $(".order_filter>a:first-child").addClass("active").siblings().removeClass("active");
            var settings = {
                url: "/api/usercenter/orders",
                tmpl_id: "#tmpl_dataorder" ,
                target: "#orderList2" ,
                pager_id: "#orderPage2",
                params: [{key:"productId",value:1 }],
                size: 10
            }
            pageData.products(settings) ;
            break;
        case "demandorder":
            //我的订单需求订单
            var settings = {
                url: "/api/usercenter/demandorders",
                tmpl_id: "#tmpl_demandorder" ,
                target: "#orderList3" ,
                pager_id: "#demandPage",
                params: [{key:"productId",value:1 }],
                size: 10
            }
            pageData.products(settings) ;
            break;
        case "myrelease": //我的发布 数据众包
            //页面请求参数
            var settings={
                url: "/api/demand/buyInfos",            // 请求地址
                tmpl_id: "#tmpl_release" ,     // jquery template 模板元素，如：#div_id 或 .class_name
                target: "#replace_crow" ,       // 替换html元素，如：#div_id 或 .class_name
                pager_id: "#crowdPage",           // 分页html元素标签
                params: [{key:"userId",value:1}],
                size: 10
            }
            // 初始化数据
            pageData.products(settings) ;

            break;
        case "schemes": //我的发布  方案召集
            //页面请求参数
            var settings={
                url: "/api/demand/sosInfos",            // 请求地址
                tmpl_id: "#tmpl_scheme" ,     // jquery template 模板元素，如：#div_id 或 .class_name
                target: "#tmpl_project" ,       // 替换html元素，如：#div_id 或 .class_name
                pager_id: "#schemePage",           // 分页html元素标签
                params: [{key:"userId",value:1}],
                size: 10
            }
            // 初始化数据

            pageData.products(settings) ;
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

//我的发布 右侧点击数据众包、召集方案 切换
$(".filter_btn>a").click(function(){
    $(this).addClass("active").siblings(".active").removeClass("active");
    var  switcherdata=$(this).attr("data-id");
    $(switcherdata).addClass("active").siblings(".active").removeClass("active");

});

