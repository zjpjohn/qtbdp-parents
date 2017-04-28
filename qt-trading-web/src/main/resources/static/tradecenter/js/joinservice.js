var isService=false;
$(window).ready(function(){
    //加载省市下拉菜单
    $("#city").citySelect({
        url:"/tradecenter/js/city.min.js",
        prov:"浙江", //省份
        city:"杭州", //城市
        // dist:"萧山区", //区县
        nodata:"none" //当子集无数据时，隐藏select
    });
    checkIsService();
});




//检验用户是否加盟为数据服务商
function checkIsService() {
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
                isService = true;
                $(".is_service>i").html("您已是数据服务商啦！");
                var tipTimer = null;
                var tipLeft = 0;
                var isLeft = false;

                function tipMove() {
                    if (!isLeft) {
                        tipLeft += 1;
                        if (tipLeft == 500) {
                            isLeft = true;
                        }
                    } else {
                        tipLeft -= 1;
                        if (tipLeft == 0) {
                            isLeft = false;
                        }
                    }
                    $(".is_service>i").css("left", tipLeft);
                };
                tipTimer = setInterval(tipMove, 50);
                $(".is_service").mouseenter(function () {
                    clearInterval(tipTimer);
                    tipTimer = null;
                });
                $(".is_service").mouseleave(function () {
                    tipTimer = setInterval(tipMove, 50);
                });
            }
        }
    })
}

















//上传图片
var imgName="",tValue="";
$("#uploadImg").change(function(){
    //获取上传文件名，保存到tvalue中
    tValue=$(this).val();
    var t1 = tValue.lastIndexOf("\\");
    if(t1 >= 0  && t1 < tValue.length){
        tValue=tValue.substring(t1 + 1)
    }
    //获取文件的后缀名
    var houzui=tValue.substring(tValue.lastIndexOf("."));
    //通过文件后缀验证附件格式
    if(houzui!=".jpeg"&&houzui!=".jpg"&&houzui!=".png"&&houzui!=".gif"&&houzui!=".pdf"){
        layer.msg("上传文件格式不对,正确格式(jpeg , jpg , png , gif , pdf)",{icon:5});
        return false;
    }
    //检验上传图片的大小
    var maxSize=2*1024*1024;
    var fileSize=$(this)[0].files[0].size;
    if(fileSize>maxSize){
        layer.msg("图片大小不能超过2M",{icon:5});
        return false;
    }
    //验证图片是否重复上传
    if(imgName==tValue){
        layer.msg("请勿上传同一张图片",{icon:5});
        return false;
    }
    var imgForm = new FormData();
    imgForm.append("img", $("#uploadImg")[0].files[0]);
        $.ajax({
            url: "/api/upload/img",
            type: "post",
            xhrFields:{withCredentials:true},
            processData:false,
            contentType:false,
            data:imgForm,
            success: function (data) {
                imgName=tValue;
                if(data.img){
                    imgUrl=data.img;
                    $("#picture").val(data.img);
                    $("#pictureImg").attr("src",data.img);
                    $("#deleteA").show();
                }
            },
            error: function(data) {
                layer.msg("图片上传失败",{icon:5});
                console.log(data);
            }
        })
});

//点击叉叉删除上传的图片
$("#deleteA").unbind("click").click(function(){
    if(imgName!=""){
        imgName="";
        $("#picture").val("");
        $("#pictureImg").attr("src","/tradecenter/images/qtdata.png");
        $("#deleteA").hide();
    }
});
//表单提交前验证
function checkJoin(){
    var type=$('input:radio[name="type"]:checked').val();//身份选择
    var designation = $("#designation").val();//名称
    var serviceType=$('input:radio[name="serviceType"]:checked').val();//服务类型
    var dataType = $('input:checkbox[name="dataType"]:checked').val();//擅长领域选择
    var abstracts=$("#abstracts").val();//您的介绍
    var picture = $("#picture").val();//图片路径

    if(!type){
        $(".err_info").html("*请选择您的身份");
        return false;
    }
    if(designation==""){
        $(".err_info").html("*请您输入名称");
        $("#designation").addClass("err").focus();
        return false;
    }
    if(!serviceType){
        $(".err_info").html("*请选择服务类型");
        return false;
    }
    if(!dataType){
        $(".err_info").html("*请选择您的擅长领域");
        return false;
    }
    if(abstracts==""){
        $(".err_info").html("*请输入您的介绍");
        $("#abstracts").addClass("err").focus();
        return false;
    }else if( abstracts.length<10 ||  abstracts.length>1000){
        $(".err_info").html("*请输入10~1000字的介绍");
        $("#abstracts").addClass("err").focus();
        return false;
    }
    if(picture==""){
        $(".err_info").html("*请上传图片");
        return false;
    }
    return true;

}

var joinsubmit={
    scheme:function(){
        $("#formSubmit").unbind("click").click(function() {
            var me=$(this)
            if (!checkJoin()) {
                return false;
            } else {
                if (isService) {
                    layer.msg("您已是数据服务商啦！", {icon: 6});
                    $(this).addClass("disabled");
                    return false;
                } else {
                    var _data = joinsubmit._formatparam($("#joinForm").serializeArray());

                    if (!$(this).hasClass("disabled")) {
                        $.ajax({
                            dataType: "text",
                            url: "/api/institution",
                            type: "post",
                            contentType: "application/json",
                            data: _data,
                            success: function (data) {
                                $(me).addClass("disabled");
                                layer.msg('您已成功提交发布信息，请耐心等待审核结果', function(){
                                    history.go(-1);
                                });
                            },
                            error: function (data) {
                                console.log("提交失败");
                            }
                        });
                    }
                }
            }



        });
    },
    _formatparam :function (param) {

        if(!param) return ;
        /*var _data = {} ;
        var _keys = param.split("&") ;
        _keys.forEach(function(val,index){
            var _attrs = val.split("=") ;
            _data[_attrs[0]] = _attrs[1] ;
        }) ;
        return JSON.stringify(_data);*/

        var _data = {} ;
        param.forEach(function(val,index){
            _data[val.name] = val.value ;
        }) ;

        return JSON.stringify(_data);
    }
}


$(function(){
    joinsubmit.scheme();
    //导航选中
    nav(4);
    fabuHover(3);
});


