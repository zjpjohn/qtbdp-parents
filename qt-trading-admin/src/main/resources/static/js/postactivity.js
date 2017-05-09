$(function() {
    if(!$("#content-item").hasClass("open")){
        $("#content-item>a").trigger("click")
        $("#activity-item").trigger("click");
    }
    $("#content-item").addClass("active").siblings(".active").removeClass("active");
    //返回活动列表页
    $("#returnList").click(function(){
        $(".page-content-wrapper").load("/ctactivity/activityList",{});
    });
    //验证
    //主题
    function check1(){
        var content= $.trim($(".topic").val());
        if(content==''){
            $("#topic_err").html('请输入活动主题');
            $(".topic").focus();
            return false;
        }
        if(content.length<5||content.length>25){
            $("#topic_err").html('活动主题需在5-25个字之间');
            $(".topic").focus();
            return false;
        }
        $("#topic_err").html('');
        return true;
    }
    $(".topic").blur(check1);

    //描述
    function check2(){
        var content= $.trim($(".description").val());
        if(content==''){
            $("#description_err").html('请输入活动描述');
            $(".description").focus();
            return false;
        }
        if(content.length<5||content.length>30){
            $("#description_err").html('活动描述需在5-30个字之间');
            $(".description").focus();
            return false;
        }
        $("#description_err").html('');
        return true;
    }
    $(".description").blur(check2);

    //活动开始时间
    function check3(){
        var content=$(".activityBeginDate").val();
        var end=$(".activityEndDate").val();
        if(content==''){
            $("#start_err").html("请输入活动开始时间").addClass("err_msg");
            $(".activityBeginDate").focus();
            return false;
        }
        if(end!=''){
            var time1=new Date(content).getTime();
            var time2=new Date(end).getTime();
            if(time1>time2){
                $("#start_err").html("活动开始时间必须早于活动结束时间").addClass("err_msg");
                $(".activityBeginDate").focus();
                return false;
            }
        }
        $("#start_err").html("活动开始时间必须早于活动结束时间").removeClass("err_msg");
        return true;
    }
    $(".activityBeginDate").blur(check3);

    //活动结束时间
    function check4(){
        var content1=$(".activityBeginDate").val();
        var content2=$(".activityEndDate").val();
        if(content2==''){
            $("#end_err").html("请输入活动结束时间").addClass("err_msg");
            $(".activityEndDate").focus();
            return false;
        }
        var time1=new Date(content1).getTime();
        var time2=new Date(content2).getTime();
        if(time1>time2){
            $("#end_err").html("活动结束时间必须晚于活动开始时间").addClass("err_msg");
            $(".activityEndDate").focus();
            return false;
        }
        $("#end_err").html("活动结束时间必须晚于活动开始时间").removeClass("err_msg");
        return true;
    }
    $(".activityEndDate").blur(check4);

    //活动封面
    function check5(){
        var content=$(".coverImg").attr("src");
        if(content==''){
            $("#cover_err").html("请上传活动封面").addClass("err_msg");
            return false;
        }
        $("#cover_err").html("活动封面必须为：关键内容需在中间部位显示，大小不超过1M").removeClass("err_msg");
        return true;
    }

    //选择活动内容
    $("#activityContent").change(function(){
        if($(this).val()==1){
            $(".chose_1").hide();
            $(".chose_2").show();
        }else if($(this).val()==0){
            $(".chose_1").show();
            $(".chose_2").hide();
        }
    });


    //活动banner
    function check6(){
        var content=$(".bannerPic").attr("src");
        if(content==''){
            $("#banner_err").html("请上传活动banner").addClass("err_msg");
            return false;
        }

        $("#banner_err").html("活动banner图片必须为：关键内容需在中间部位显示，大小不超过1M。").removeClass("err_msg");
        return true;
    }


    //报名方式
    function check8(){
        if($(".isOnlineApply:checked").length==0){
            $("#applyWay").html("请选择一种报名方式");
            return false;
        }
        $("#applyWay").html("");
        return true;
    }
    $(".isOnlineApply").change(check8);
    //点击报名方式切换显示
    $(".isOnlineApply").change(function(){
        if($(this).val()==1){
            //线上报名
            $(".form_1").show();
            $(".sel_2").hide();
            $("#TextArea1").val("");
        }else{
            $(".form_1").hide();
            $(".sel_2").show();
            $(".applyBeginDate").val("");
            $(".applyEndDate").val("");
        }
    });

    //报名方式-线上报名-报名时间
    function check81(){
        var type=$(".isOnlineApply:checked").val();
        if(type==1){
            //选择线上报名
            var content1=$(".applyBeginDate").val();
            var content2=$(".applyEndDate").val();
            if(content1==''||content2==''){
                $("#apply_err").html("请填写报名时间").addClass("err_msg");
                return false;
            }
            var time1=new Date(content1).getTime();
            var time2=new Date(content2).getTime();
            if(time1>time2){
                $("#apply_err").html("活动报名开始时间必须早于报名结束时间<br>活动报名结束时间必须早于活动开始时间").addClass("err_msg");
                return false;
            }
            $("#apply_err").html("活动报名开始时间必须早于报名结束时间<br>活动报名结束时间必须早于活动开始时间").removeClass("err_msg");
            return true;
        }
        $("#apply_err").html("");
        return true;
    }
    $(".applyBeginDate").blur(check81);
    $(".applyEndDate").blur(check81);

    //报名方式-按报名信息说明报名-报名时间
    function check82(){
        var type=$(".isOnlineApply:checked").val();
        if(type==0){
            //选择按报名信息说明报名
            var content=$.trim($("#TextArea1").val());
            if(content==''){
                $("#applyDescription_err").html("请填写报名信息");
                return false;
            }
            $("#applyDescription_err").html("");
            return true;
        }
        $("#applyDescription_err").html("");
        return true;
    }
    $("#TextArea1").blur(check82);




    //投票选择显示
    $(".voteType").change(function(){
        console.log($(this).val());
        if($(this).val()=="1"){
            //正反投票
            $("#isVote").val("1");
            $(".votetype1").show();
            $(".votetype2").hide();
        }else if($(this).val()==""){
            $("#isVote").val("0");
            $("#vote_tex").val("");
            $(".voteQuestion2").val("");
            $(".vote_detail_item").each(function(){
                $(this).val("");
            });
            $(".votetype1").hide();
            $(".votetype2").hide();
        }else{
            $("#isVote").val("1");
            $(".votetype1").hide();
            $(".votetype2").show();
        }
    });

    //正反投票
    function check91(){
        var type=$(".voteType:checked").val();
        //console.log(type);
        if(type=="1"){
            var content=$(".voteQuestion").val();
            if(content==''){
                $("#votetype1_err").html("请填写问题描述").addClass("err_msg");
                return false;
            }
            if(content.length<5||content.length>30){
                $("#votetype1_err").html("问题描述需在5-30个字之间").addClass("err_msg");
                return false;
            }
            $("#votetype1_err").html("问题描述需在5-30个字之间；</br>问题描述需引起对立争议，例：二线城市房价是否会继续飙升？").removeClass("err_msg");
            return true;
        }
        $("#votetype1_err").html("问题描述需在5-30个字之间；</br>问题描述需引起对立争议，例：二线城市房价是否会继续飙升？").removeClass("err_msg");
        return true;
    }
    $(".voteQuestion").blur(check91);


    //多个选项投票
    function check92(){
        var type=$(".voteType:checked").val();
        if(type=="0"){
            var pro=$(".voteQuestion2").val();
            if(pro==''){
                $("#vote_count").html("请填写问题").addClass("err_msg");
                return false;
            }
            $("#vote_count").html("多个选项投票，投票观点数需在3-6个之间。").removeClass("err_msg");
            return true;
        }
        $("#vote_count").html("多个选项投票，投票观点数需在3-6个之间。").removeClass("err_msg");
        return true;
    }
    $(".votetype2 input").blur(check92);

    //多个选项
    function check93(){
        var type=$(".voteType:checked").val();
        if(type=="0"){
            var content=$.trim($(".pro_item1").val());
            if(content==''){
                $("#vote_count").html("请填写选项").addClass("err_msg");
                return false;
            }
            $("#vote_count").html("多个选项投票，投票观点数需在3-6个之间。").removeClass("err_msg");
            return true;
        }
        $("#vote_count").html("多个选项投票，投票观点数需在3-6个之间。").removeClass("err_msg");
        return true;
    }
    $(".pro_item1").blur(check93);

    function check94(){
        var type=$(".voteType:checked").val();
        if(type=="0"){
            var content=$.trim($(".pro_item2").val());
            if(content==''){
                $("#vote_count").html("请填写选项").addClass("err_msg");
                return false;
            }
            $("#vote_count").html("多个选项投票，投票观点数需在3-6个之间。").removeClass("err_msg");
            return true;
        }
        $("#vote_count").html("多个选项投票，投票观点数需在3-6个之间。").removeClass("err_msg");
        return true;
    }
    $(".pro_item2").blur(check94);

    function check95(){
        var type=$(".voteType:checked").val();
        if(type=="0"){
            var content=$.trim($(".pro_item3").val());
            if(content==''){
                $("#vote_count").html("请填写选项").addClass("err_msg");
                return false;
            }
            $("#vote_count").html("多个选项投票，投票观点数需在3-6个之间。").removeClass("err_msg");
            return true;
        }
        $("#vote_count").html("多个选项投票，投票观点数需在3-6个之间。").removeClass("err_msg");
        return true;
    }
    $(".pro_item3").blur(check95);

    //添加
    var problemNum=3;
    $("#addBtn").click(function(){
        if(problemNum>=3&&problemNum<6){
            problemNum++;
            var html='<div class="form-group votetype2">' +
                ' <label class="control-label col-md-2">选项'+problemNum+'</label>' +
                ' <div class="col-md-8">' +
                ' <input type="text"   class="form-control vote_detail_item"   placeholder="输入选项"/>' +
                ' </div> ' +
                '<a href="javascript:;" class="btn-link col-md-2 deletePro">删除</a>' +
                ' </div>';
            $(".proList").append(html);
            $(".votetype2").show();
        }else{
            $("#vote_count").show();
        }
    });

    //上传活动详情页
    function check11(){
        if($("#pcDetailPicUrl").val()==''){
            $(".request_ul").addClass("warning_msg");
            return false;
        }
        var size=$("#pcDetailPicUrl")[0].files[0].size;
        if(size>1048576){
            $(".request_ul").addClass("warning_msg");
            return false;
        }
        $(".request_ul").removeClass("warning_msg");
        return true;
    }
    function check12(){
        if($("#appDetailPicUrl").val()==''){
            $(".request_ul").addClass("warning_msg");
            return false;
        }
        var size=$("#appDetailPicUrl")[0].files[0].size;
        if(size>1048576){
            $(".request_ul").addClass("warning_msg");
            return false;
        }
        $(".request_ul").removeClass("warning_msg");
        return true;
    }

    var commonToken = $("#csrf_token").attr("attr-token");
    console.log(commonToken);
    //上传图片到阿里云
    var jcropApi
    function uploadOss(id,aspectRatio,setSelect){
        jcropApi="";
        var fileObj=document.getElementById(id).files[0];
        // var type=document.getElementById(id).files[0].name.split(".").pop();
        var form=new FormData();
        form.append("imgFile",fileObj);
        //form.append("filetype",type);
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
                if(id=="coverUrl"||id=="uploadBannerBtn"){
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
                        if(id=="coverUrl"){
                            $("#realCoverUrl").val(cropUrl);
                            $(".coverImg").attr("src",cropUrl).show();
                        }else if(id=="uploadBannerBtn"){
                            $("#realBannerPic").val(cropUrl);
                            $(".bannerPic").attr("src",cropUrl).show();
                        }
                        layer.close(index);
                    });
                    $(".cancal").click(function(){
                        layer.close(index);
                    });
                }else if(id=="pcDetailPicUrl"){
                    $("#realPcDetailPicUrl").val(data.url);
                    $(".pcCoverImg").attr("src",data.url).show();
                }else if(id=="appDetailPicUrl"){
                    $("#realAppDetailPicUrl").val(data.url);
                    $(".appCoverImg").attr("src",data.url).show();
                }


            },
            error:function(){
                layer.alert("上传失败");
            }
        });
    }




    //上传活动封面到阿里云
    $("#coverUrl").change(function(){
        var size=$(".coverUrl")[0].files[0].size;
        if(size>1048576){
            $("#cover_err").html("图片大小不能超过1M").addClass("err_msg");
            return false;
        }
        uploadOss("coverUrl",348/188,[0,0,696,376]);
        $(this).val("");
    });
    //上传PC活动详情页到阿里云
    $("#pcDetailPicUrl").change(function(){
        uploadOss("pcDetailPicUrl");
    });
    //上传app活动详情页到阿里云
    $("#appDetailPicUrl").change(function(){
        uploadOss("appDetailPicUrl");
    });
    //上传banner到阿里云
    $("#uploadBannerBtn").change(function(){
        var size=$(".banner_img")[0].files[0].size;
        if(size>1048576){
            $("#banner_err").html("图片大小不能超过1M").addClass("err_msg");
            return false;
        }
        uploadOss("uploadBannerBtn",1200/280,[0,0,720,168]);
        $(this).val("");
    });


    //点击发布
    $("#publishBtn").click(function(){
        var type=$("#activityContent").val();
        if(type!=1&&type!=0){
            //console.log(type);
            $("#activityContentErr").html("请选择活动内容");
            return false;
        }else if(type==1){
            $("#activityContentErr").html("");
            //验证上传活动详情页
            if(check1()&&check2()&&check3()&&check4()&&check5()&&check6()&&check11()&&check12()){
                //alert("jinlaile");
                //修改为ajax提交表单
                $.ajax({
                    dataType:"json",
                    url:"/ctactivity/addActivity",
                    data:$("#publishForm").serialize(),
                    type:"post",
                    success:function(data){
                        //console.log(data);
                        if(data.success){
                            var id=data.data.id;
                            layer.msg("发布成功！",{
                                icon:1
                            },function(){
                                $(".page-content-wrapper").load("/ctactivity/goActivityDetail",{
                                    id:id
                                });
                            });
                        }
                    }
                });
            }
        }else if(type==0){
            $("#activityContentErr").html("");
            //编辑活动详情
            if(check1()&&check2()&&check3()&&check4()&&check5()&&check6()&&check8()&&check81()&&check82()&&check91()&&check92()&&check93()&&check94()&&check95()){
                //设置投票问题
                if($("#vote").is(":checked")){
                    //正反投票
                   $("#voteQuestion").val($("#vote_tex").val());
                   $("#voteDetail").val($(".true_false_type:checked").val());
                    $(".true_false_type:checked").each(function(){
                        $(this).attr("name","");
                    });
                }else if($("#multiVote").is(":checked")){
                    //多项选择投票
                    $("#voteQuestion").val($(".voteQuestion2").val());
                    //设置投票详情字符串
                    var voteStr='';
                    $(".vote_detail_item").each(function(){
                        voteStr+=$(this).val()+'|'
                    });
                    voteStr=voteStr.substring(0,voteStr.length-1);
                    $("#voteDetail").val(voteStr);
                }
                //修改为ajax提交表单
                $.ajax({
                    dataType:"json",
                    url:"/ctactivity/addActivity",
                    data:$("#publishForm").serialize(),
                    type:"post",
                    success:function(data){
                        //console.log(data);
                        if(data.success){
                            var id=data.data.id;
                            layer.msg("发布成功！",{
                                icon:1
                            },function(){
                                $(".page-content-wrapper").load("/ctactivity/goActivityDetail",{
                                    id:id
                                });
                            });
                        }
                    }
                });
            }
        }

    });




    //删除
    $(document).on("click",".deletePro",function(){
        problemNum--;
        $("#vote_count").html("");
        $(this).parent().remove();
    });

    $('.about_time').datetimepicker({
        lang:'ch',
        // format:'Y-m-d h:i',
        timepicker:true,
        yearStart:1990,
        yearEnd:2017,
        todayButton:true
    });
    $.datetimepicker.setLocale('ch');

    $("#city").citySelect({
        url:"/js/city.min.js",
        prov:'浙江', //省份
        city:'杭州', //城市
        nodata:"none" //当子集无数据时，隐藏select
    });
    var commonToken = $("#csrf_token").attr("attr-token");
    console.log("postactivity.js ======" + commonToken);
    $.ajaxSetup({
        headers:{
            'X-CSRF-TOKEN': commonToken
        }
    });
    //生成富文本编辑器

    window.um = UM.getEditor('umcontainer', {
        toolbar: [
            'bold  underline   | removeformat |',
        '| selectall cleardoc  ' ,
        '| justifyleft justifycenter justifyright justifyjustify |',
    ]


    });


    // var editor;
    // KindEditor.ready(function(K) {
    //     editor = K.create('#kContent', {
    //         allowFileManager : true,
    //         width : '400px',
    //         height:"400px",
    //         items:[
    //             'cut', 'copy', 'paste',
    //             'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
    //             'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
    //             'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
    //             'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
    //             'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
    //             'flash', 'media',  'table'
    //         ],
    //         fileManagerJson:"/fileUpload/doFileUpload",
    //         uploadJson : '/fileUpload/doFileUpload',
    //         afterUpload : function(url) {
    //             console.log(url);
    //         }
    //     });
    //
    // });

    // $(document).on("click",".ke-dialog-yes",function(){
    //     alert(123);
    //     var form=new FormData($(".ke-upload-area.ke-form")[0]);
    //     $.ajax({
    //         url:  "/fileUpload/doFileUpload",
    //         type: "post",
    //         headers:{
    //             'X-CSRF-TOKEN': commonToken
    //         },
    //         xhrFields:{withCredentials:true},//true表示允许发送cookie
    //         processData:false,//防止ajax自动序列化表单
    //         contentType:false,//上传文件必须改为flase，使用multipart/form-data
    //         data:form,
    //         success: function (data) {
    //             console.log(data);
    //         }
    //     })
    // });
});


       