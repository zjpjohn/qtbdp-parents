/**
 * Created by zd  on 2017/4/6.
 */


function loadPagerData(current){
    var $this = $("#page");
    var currentPage = current;
    $(".page-content-wrapper").load("/ctactivity/activityList?t=" + new Date().getTime(),{
        currentPage:currentPage,
        itemsPerPage:10,
        status:$(".status_btn>a.active").attr("data-status"),
        isBanner:$(".banner_btns>a.active").attr("data-banner"),
        activityBeginDate:$("#beginTimeInput").val(),
        activityEndDate:$("#endTimeInput").val(),
        applyBeginDate:$("#applyTimeInput").val(),
        applyEndDate:$("#endApplyTimeInput").val(),
        isOnlineApply:$(".applyType_btns>a.active").attr("data-type")
    })
}

// 执行分页
$(function(){
    $(".table thead tr th:first-child").css("width","400px")
    $(".table thead tr th:last-child").css("width","80px")

    if(!$("#content-item").hasClass("open")){
        $("#content-item>a").trigger("click")
        $("#activity-item").trigger("click");
    }
    $("#content-item").addClass("active").siblings(".active").removeClass("active");
    //进入活动报名列表
    $(".goApplyList").click(function(){
        var id=$(this).attr("data-id");
        $(".page-content-wrapper").load("/ctActivityApply/getListApply",{
            findKey:id
        });
    });

    //返回活动列表
    $(".returnList").click(function(){
        $(".page-content-wrapper").load("/ctactivity/activityList",{});
    });

    //进入活动预览
    $(".activity_topic").click(function(){
        var id=$(this).parent().parent().attr("id");
        $(".page-content-wrapper").load("/ctactivity/goActivityDetail",{
            id:id
        });
    });

    pager("page");


    $(".status_div>a").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
        // var status=$(".status_btn>a.active").attr("data-status");
        // var banner=$(".banner_btns>a.active").attr("data-banner");
        // var beginTime=$("#beginTimeInput").val();
        // var endTime=$("#endTimeInput").val();
        // var applyTime=$("#applyTimeInput").val();
        // var eddApplyTime=$("#endApplyTimeInput").val();
        // var applyType=$(".applyType_btns>a.active").attr("data-type");
        loadPagerData(1);
    });

//点击查询按钮
    $(".search_btn").click(function(){
        if(checkTime1()&&checkTime2()){
            var status=$(".status_btn>a.active").attr("data-status");
            var banner=$(".banner_btns>a.active").attr("data-banner");
            var beginTime=$("#beginTimeInput").val();
            var endTime=$("#endTimeInput").val();
            var applyTime=$("#applyTimeInput").val();
            var eddApplyTime=$("#endApplyTimeInput").val();
            var applyType=$(".applyType_btns>a.active").attr("data-type");

            $("#activityStatus").val(status);
            $("#activityBanner").val(banner);
            $("#beginTime").val(beginTime);
            $("#eddTime").val(endTime);
            $("#applyTime").val(applyTime);
            $("#eddApplyTime").val(eddApplyTime);
            $("#applyType").val(applyType);

            $("#selectForm").submit();
        }
    });


//设为banner
    $("#table1").on("click",".setBanner",function(){
        var id=$(this).parent().parent().attr("id");
        var me=this;
        layer.confirm("设置后，该活动将出现在活动页面banner位置。是否设为banner？",{
            btn:['确定','取消']
        },function(){
            $.ajax({
                data:{
                    id:id
                },
                url:"/ctactivity/addBanner",
                dataType:"json",
                type:"post",
                success:function(data){
                    if(data.errorCode=="0040"){
                        layer.alert("超过5个banner");
                    }else if(data.errorCode=="0000"){
                        layer.msg("设置banner成功！",{
                            icon:1
                        });
                        $(me).attr("class","cancelBanner").html("取消");
                        $(me).parent().prev().children().html("已设置");
                    }
                },
                error:function(){
                    layer.alert("设置失败");
                }
            });
        },function(){
            // layer.close();
        });

    });


//取消设置banner
    $("#table1").on("click",".cancelBanner",function(){
        var me=this;
        layer.confirm('取消后，该活动将不会出现在活动页banner，是否取消？',{
            btn:['确定','取消']
        },function(index){
            var id=$(me).parent().parent().attr("id");

            $.ajax({
                data:{
                    id:id
                },
                url:"/ctactivity/cancelBanner",
                dataType:"json",
                type:"post",
                success:function(data){
                    if(data.errorCode=="0041"){
                        layer.alert("少于1个banner");
                    }else if(data.errorCode=="0000"){
                        layer.msg("取消banner成功！",{
                            icon:1
                        });
                        $(me).attr("class","setBanner").html("推荐");
                        $(me).parent().prev().children().html("未设置");
                    }
                },
                error:function(){
                    layer.alert("设置失败");
                }
            });
        },function(index){
            layer.close(index);
        });

    });

//删除活动
    $(document).on("click",".deleteBtn",function(){
        var me=this;
        layer.confirm("确定要删除吗？",{
            btn:['确定','取消']
        },function(){
            var id=$(me).parent().parent().attr("id");
            $.ajax({
                data:{
                    id:id
                },
                url:"/ctactivity/deleteActivity",
                dataType:"json",
                type:"post",
                success:function(data){
                    layer.msg('删除成功', {icon: 1});
                    if(data.errorCode=="0000"){
                        $(me).parent().parent().remove();
                    }
                },
                error:function(){
                    layer.alert("删除失败");
                }
            });
        },function(){
            layer.close();
        })

    });




//时间判断
    function checkTime1(){
        var time1=$("#beginTimeInput").val();
        var time2=$("#endTimeInput").val();
        if(time1!=""&&time2!=""){
            if(time1>time2){
                layer.msg("活动开始时间必须小于结束时间！",{
                    icon:2
                });
                return false;
            }
        }
        return true;
    }
    $("#beginTimeInput,#endTimeInput").change(checkTime1);

    function checkTime2(){
        var time1=$("#applyTimeInput").val();
        var time2=$("#endApplyTimeInput").val();
        if(time1!=""&&time2!=""){
            if(time1>time2){
                layer.msg("活动报名开始时间必须小于报名结束时间！",{
                    icon:2
                });
                return false;
            }
        }
        return true;
    }
    $("#applyTimeInput,#endApplyTimeInput").change(checkTime2);





//页面记载给a添加样式
    $(function(){
        var status=$("#activityStatus").val();
        $(".status_btn>a:eq("+status+")").addClass("active").siblings().removeClass("active");
        var isBanner=$("#activityBanner").val();
        if(isBanner==''){
            $(".banner_btns>a:eq(0)").addClass("active").siblings().removeClass("active");
        }else if(isBanner=="1"){
            $(".banner_btns>a:eq(1)").addClass("active").siblings().removeClass("active");
        }
        var applyType=$("#applyType").val();

        if(applyType==''){
            $(".applyType_btns>a:eq(0)").addClass("active").siblings().removeClass("active");
        }else if(applyType=='1'){
            $(".applyType_btns>a:eq(1)").addClass("active").siblings().removeClass("active");
        }else if(applyType=='0'){
            $(".applyType_btns>a:eq(2)").addClass("active").siblings().removeClass("active");
        }

        myBrowser();

    })


    function myBrowser(){
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串

        if (userAgent.indexOf("Chrome") == -1){
            //判断不是谷歌浏览器
            $(".table thead tr th:first-child").css("padding","10px 110px")
        }

    }

    $(".about_time").datetimepicker({
        lang:'ch',
        // format:'Y-m-d',
        timepicker:true,
        yearStart:1990,
        yearEnd:2027,
        todayButton:true
    });
    $.datetimepicker.setLocale('ch');
})





