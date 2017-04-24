
//检验用户是否加盟为数据服务商
function checkIsAddInstitution(){
    var result = true;
    $.ajax({
        type:"post",
        url:"/institution/checkIsAddInstitution",
        dataType:"json",
        async:false,
        data:{},
        success:function(data){
            if(data=="1"){
                result=false;
            }
        }
    });
    return result;
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
    var currentTempPath = $("#uploadImg").val();//用户需要上传的本机路径
    if (undefined != currentTempPath && "" != currentTempPath) {
        //表单提交参数
        var options = {
            dataType: "json",
            url: "/upload/uploadPic",//上传图片路径
            type: "post",
            async:false,
            success: function(data){
                imgName=tValue;
                if(data['path']!=""){
                    imgUrl=data['path'];
                    $("#picture").val(data['path']);
                    $("#pictureImg").attr("src",data['path']);
                }
            },
            error: function(data) {
                layer.msg("图片上传失败",{icon:5});
                console.log(data);
            }
        };
        //ajax提交
        $("#formSubmit").ajaxSubmit(options);
    }
});

//点击叉叉删除上传的图片
$("#deleteA").unbind("click").click(function(){
    imgName="";
    $("#picture").val("");
    $("#pictureImg").attr("src","/tradecenter/images/qtdata.png");
});
//表单提交前验证
function checkJoin(){
    var type=$('input:radio[name="type"]:checked').val();//身份选择
    var designation = $("#designation").val();//名称
    var dataTypes = $('input:checkbox[name="dataTypes"]:checked').val();//擅长领域选择
    var abstracts=$("#abstracts").val();//您的介绍
    var picture = $("#picture").val();//图片路径

   /* if(!checkIsAddInstitution()){
        layer.msg("您已经是会员用户了",{icon:5});
        return false;
    }*/
    if(!type){
        $(".err_info").html("*请选择您的身份");
        return false;
    }
    if(designation==""){
        $(".err_info").html("*请您输入名称");
        $("#designation").addClass("err").focus();
        return false;
    }
    if(!dataTypes){
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
    /*if(picture==""){
        $(".err_info").html("*请上传图片");
        return false;
    }*/
    return true;

}

var joinsubmit={
    scheme:function(){
        $("#formSubmit").unbind("click").click(function(){
            console.log(33);
            if(!checkJoin()){
                return false;
            }
            var _data = joinsubmit._formatparam($("#joinForm").serialize()) ;
            console.log("_data："+_data);

            if($(this).attr("class").indexOf("disabled")==-1){
                var me=this;
                $.ajax({
                    dataType: "text",
                    url: "/api/institution",
                    type: "post",
                    contentType:"application/json",
                    data: _data,
                    success: function(data){
                        console.log(data);
                        alert("提交成功");
                    },
                    error:function(data){
                        console.log("提交失败");
                    }
                });
            }

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


$(function(){
    joinsubmit.scheme();
});


