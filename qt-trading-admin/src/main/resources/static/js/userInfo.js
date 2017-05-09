$(function() {
    if(!$("#panel-item").hasClass("open")){
        $("#panel-item>a").trigger("click");
        $("#info-item").trigger("click");
    }
    $("#panel-item").addClass("active").siblings(".active").removeClass("active");

    //验证
    //头像
    function checkInfo(){
        if($("#realInfoUrl").val()==''){
            $("#portraitErr").html("请上传头像");
            return false;
        }
        $("#portraitErr").html("");
        return true;
    }
    //姓名
    function checkName(){
        var content= $.trim($(".name").val());
        if(content==''){
            $("#nameErr").html('请输入姓名');
            $(".name").focus();
            return false;
        }
        $("#nameErr").html('');
        return true;
    }
    $(".name").blur(checkName);

    //邮箱
    function checkEmail(){
        var content= $.trim($(".email").val());
        if(content==''){
            $("#emailErr").html('请输入邮箱');
            $(".email").focus();
            return false;
        }
        var reg=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/g;
        var test=reg.test(content);
        if( !test ){
            $("#emailErr").html('请输入正确的邮箱');
            $(".email").focus();
            return false;
        }
        $("#emailErr").html('');
        return true;
    }
    $(".email").blur(checkEmail);

    //电话
    function checkPhone(){
        var content=$(".phone").val();
        if(content==''){
            $("#phoneErr").html("请输入电话");
            $(".phone").focus();
            return false;
        }
        var reg = /^0\d{2,3}-?\d{7,8}$/;
        if(!reg.test(content)){
            $("#phoneErr").html("请输入正确的电话");
            $(".phone").focus();
            return false;
        }
        $("#phoneErr").html("");
        return true;
    }
    $(".phone").blur(checkPhone);

    //手机
    function checkMobile(){
        var content=$(".mobile").val();
        if(content==''){
            $("#mobileErr").html("请输入手机号");
            $(".mobile").focus();
            return false;
        }
        var reg = /^13[0-9]{9}$|14[0-9]{9}|17[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/;
        if(!(reg.test(content))){
            $("#mobileErr").html("请输入争取的手机号");
            $(".mobile").focus();
            return false;
        }
        $("#mobileErr").html("");
        return true;
    }
    $(".mobile").blur(checkMobile);

    //上传图片到阿里云
    var jcropApi;
    function uploadOss(id,aspectRatio,setSelect){
        jcropApi="";
        var fileObj=document.getElementById(id).files[0];
        // var type=document.getElementById(id).files[0].name.split(".").pop();
        var form=new FormData();
        form.append("imgFile",fileObj);
        $.ajax({
            data:form,
            dataType:"json",
            processData:false,
            contentType:false,
            headers:{'X-CSRF-TOKEN':commonToken},
            url:"/fileUpload/doFileUpload",
            type:"post",
            success:function(data){
                console.log(data.url);
                var url=data.url;
                $("#target").attr("src",data.url);
                var index=layer.open({
                    title: false,
                    type: 1,
                    closeBtn: 1,
                    skin: 'layui-layer-rim1', //封面上传探矿添加边框，文件为layer.css
                    area: ['1200px', '600px'],
                    content:'<div class="container" id="picCrop">' +
                    ' <div class="row"> <div class="span12">' +
                    ' <div class="jc-demo-box"> ' +
                    '<div class="wrap" id="fileList"> ' +
                    '<img src="'+data.url+'" id="target" alt="" /> ' +
                    '</div> <div id="preview-pane"> ' +
                    '<div class="preview-container">' +
                    '  </div> </div>' +
                    ' <div class="btngroup clearfix"> <span class="sure">确定上传</span> ' +
                    '<span class="cancal">撤销</span> </div> </div> </div> </div> </div>'
                });
                $(".jcrop-holder").find('img').attr("src", data.url);

                $('#target').Jcrop({
                    onChange: updatePreview,
                    onSelect: updatePreview,
                    aspectRatio: aspectRatio,
                    setSelect:setSelect,
                    boxWidth:590,
                },function(){
                    jcropApi = this;

                });
                function updatePreview(c) {
                    url+="?x-oss-process=image/crop,x_"+c.x+",y_"+c.y+",w_"+c.w+",h_"+c.h
                };

                $(".sure").click(function(){
                    var cropObject = jcropApi.tellSelect();
                    var x =  Math.round(cropObject.x);
                    var y =  Math.round(cropObject.y);
                    var w =  Math.round(cropObject.w);
                    var h =  Math.round(cropObject.h);
                    var cropUrl=data.url+"?x-oss-process=image/crop,x_"+x+",y_"+y+",w_"+w+",h_"+h;
                    $("#realInfoUrl").val(cropUrl);
                    $("#infoImg").attr("src",cropUrl).show();
                    $("#portraitErr").html("");
                    layer.close(index);
                });
                $(".cancal").click(function(){
                    layer.close(index);
                });
            },
            error:function(){
                layer.alert("上传失败");
            }
        });
    }

    //上传头像到阿里云
    $("#infoUrl").change(function(){
        var size=$(".coverUrl")[0].files[0].size;
        if(size>1048576){
            $("#portrait_err").html("图片大小不能超过1M");
            return false;
        }
        uploadOss("infoUrl",100/100,[0,0,200,200]);
        $(this).val("");
    });


    //点击保存
    $("#saveBtn").click(function(){
        if(checkInfo()&&checkName()&&checkEmail()&&checkPhone()&&checkMobile()){
            //ajax提交表单
            $.ajax({
                dataType:"json",
                url:"/user/info",
                data:$("#infoForm").serialize(),
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
                }
            });
        }
    });

    //删除
    $(document).on("click",".deletePro",function(){
        problemNum--;
        $("#vote_count").html("");
        $(this).parent().remove();
    });

});


