$(document).ready(function(){
    //订单数量
    $.ajax({
        type:"GET",
        dataType:"json",
        url: "/api/trade/count" ,
        ansync:true,
        data:{
            userId:userId
        },
        xhrFields:{
            withCredentials:true
        },
        success:function(data){
            $(".allorders").html(data.pageInfo.authorizeOrder+data.pageInfo.transactionOrder);
            $(".dataorders").html(data.pageInfo.authorizeOrder);
            $(".demandorders").html(data.pageInfo.transactionOrder);
        }
    });
    //发布数量
    $.ajax({
        type:"GET",
        dataType:"json",
        url: "/api/demand/count" ,
        ansync:true,
        data:{
            userId:userId
        },
        xhrFields:{
            withCredentials:true
        },
        success:function(data){
            $(".allfabus").html(data.pageInfo.num);
            $(".crowdings").html(data.pageInfo.buyinfo);
            $(".schemes").html(data.pageInfo.sosinfo);
        }
    });

    // 概览 最新订单
    var settings = {
        url: "/api/trade/neworders",      // 请求地址
        tmpl_id: "#tmpl_neworder" ,     //  tmpl 模板元素id
        target: "#orderList1" ,       // 替换html元素id
        params: [{key:"userId",value:userId }]
    }
    pageData.products(settings) ;

    //概览 最新发布
    var settings = {
        url: "/api/demand/demandorders",
        tmpl_id: "#tmpl_newfabu" ,
        target: "#newfabuList" ,
        params: [{key:"userId",value:userId }]
    }
    pageData.products(settings) ;




    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }

    //如果是支付订单
    if(getQueryString("order")=="4"){
        $(".pereach>li:nth-child(4)").addClass("active").siblings(".active").removeClass("active");
        $(".orderpay").addClass("active").siblings(".active").removeClass("active");

    }else{
        var settings={
            url: "/api/user",            // 请求地址
            tmpl_id: "#tmpl_personals" ,     // jquery template 模板元素，如：#div_id 或 .class_name
            target: "#personaldata" ,       // 替换html元素，如：#div_id 或 .class_name
            params: [{key:"id",value:userId}],

        }
        pageData.products(settings) ;

    }
    //判断是否成为服务商
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/api/institution/exist",
        ansync: true,
        xhrFields: {
            widthCredentials: true
        },
        data: {
            userId: userId
        },
        success: function (data) {
            if (data.isExist) {
                $("#joinservice2").remove();
            }
        }
    });


});
//获取概览数据封装
function userGailan(url,id){
    $.ajax({
        type:"GET",
        dataType:"json",
        url: url ,
        ansync:true,
        data:{
            userId:userId
        },
        xhrFields:{
            withCredentials:true
        },
        success:function(data){
            console.log(data);
            $(id).html(data);
        }
    });
}


/**点击切换右侧页面*/

$(".pereach>li,.filter_btn>a,#moreOrder,#morefabu,#perfectdatum").unbind().click(function(){
    $(this).addClass("active").siblings(".active").removeClass("active");
    var centerleft=$(this).attr("data-class");
    $("."+centerleft+"2").addClass("active").siblings(".active").removeClass("active");
    switch (centerleft) {
        case "overview":
            // 概览 最新订单
            var settings = {
                url: "/api/trade/neworders",      // 请求地址
                tmpl_id: "#tmpl_neworder" ,     //  tmpl 模板元素id
                target: "#orderList1" ,       // 替换html元素id
                // pager_id: "#pageTool",           // 分页
                params: [{key:"userId",value:userId }]
            }
            pageData.products(settings) ;
            break;
        case "myorder":
            //我的订单数据订单
            $(".pereach>li:nth-child(4)").addClass("active").siblings().removeClass("active");
            $(".order_filter>a:first-child").addClass("active").siblings().removeClass("active");
            var settings = {
                url: "/api/trade/orders",
                tmpl_id: "#tmpl_dataorder" ,
                target: "#orderList2" ,
                pager_id: "#orderPage2",
                params: [{key:"productId",value:userId }],
                size: 10
            }
            pageData.products(settings) ;
            break;
        case "demandorder":
            //我的订单需求订单
            var settings = {
                url: "/api/demand/demandorders",
                tmpl_id: "#tmpl_demandorder" ,
                target: "#orderList3" ,
                pager_id: "#demandPage",
                params: [{key:"productId",value:userId }],
                size: 10
            }
            pageData.products(settings) ;
            break;
        case "myrelease": //我的发布 数据众包
            $(".pereach>li:nth-child(5)").addClass("active").siblings().removeClass("active");
            $(".fabu_filter>a:first-child").addClass("active").siblings().removeClass("active");
            //页面请求参数
            var settings={
                url: "/api/demand/buyInfos",            // 请求地址
                tmpl_id: "#tmpl_release" ,     // jquery template 模板元素，如：#div_id 或 .class_name
                target: "#replace_crow" ,       // 替换html元素，如：#div_id 或 .class_name
                pager_id: "#crowdPage",           // 分页html元素标签
                params: [{key:"userId",value:userId}],
                size: 10
            }
            // 初始化数据
            pageData.products(settings) ;

            break;
        case "schemes": //我的发布  方案召集
            $(".pereach>li:nth-child(5)").addClass("active").siblings().removeClass("active");
            $(".fabu_filter>a:nth-child(2)").addClass("active").siblings().removeClass("active");

            //页面请求参数
            var settings={
                url: "/api/demand/sosInfos",            // 请求地址
                tmpl_id: "#tmpl_scheme" ,     // jquery template 模板元素，如：#div_id 或 .class_name
                target: "#tmpl_project" ,       // 替换html元素，如：#div_id 或 .class_name
                pager_id: "#schemePage",           // 分页html元素标签
                params: [{key:"userId",value:userId}],
                size: 10
            }
            // 初始化数据

            pageData.products(settings) ;
            break;
        case "persinfo"://个人信息
            $(".pereach>li:nth-child(2)").addClass("active").siblings().removeClass("active");

            var settings={
                url: "/api/user",            // 请求地址
                tmpl_id: "#tmpl_personals" ,     // jquery template 模板元素，如：#div_id 或 .class_name
                target: "#personaldata" ,       // 替换html元素，如：#div_id 或 .class_name
                params: [{key:"id",value:userId}],
            }
            pageData.products(settings) ;
            break;

    }

});

//点击成为数据服务商 整个li可以跳转
$(".pereach>li:nth-child(6)").click(function(){
    location.href="/institution/add";

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




//个人信息 头像修改
var headUrl="";

$(document).on("change","#upheadimg",function() {
    var form = new FormData();
    form.append("img",$("#upheadimg")[0].files[0]);

    $.ajax({
        url: "/api/upload/img",
        type: "post",
        xhrFields:{withCredentials:true},
        processData:false,
        contentType:false,
        data:form,
        success: function (data) {
            console.log(data);
            headUrl=data.img;
            console.log(headUrl);
            //$("#upheadimg").val(headUrl);
            $(".headimgs>img").attr("src",headUrl);
            $("#realHead").val(headUrl);
        }
    })
});







//信息修改 保存
var usercenter = {

    userSubmit: function () {

        $("#personaldata").on("click","#savealter",function(){

            var _data = usercenter._formatparam($("#perdetail").serialize()) ;
            console.log("_data："+_data);

            $.ajax({
                type:"put",
                dataType:"json",
                url:"/api/user"  ,
                contentType:"application/json; charset=utf-8",
                data: _data ,
                success:function(data){
                    console.log("success: "+data.success);
                    layer.confirm("修改信息成功",
                        {title:"",btn:["确定"]},
                        function(index){
                            layer.close(index);
                        });
                },
                error:function(data){

                    // console.log(data);
                }
            })
        });
    },

    _formatparam :function (param) {

        if(!param) return ;
        var _data = {} ;
        var _keys = param.split("&") ;
        _keys.forEach(function(val,index){
            var _attrs = val.split("=") ;
            _data[_attrs[0]] = _attrs[1] ;
        }) ;
        return JSON.stringify(_data);
    }
}





