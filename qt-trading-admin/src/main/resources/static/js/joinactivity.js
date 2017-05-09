/**
 * Created by zd on 2017/4/6.
 */
$(function(){
    if(!$("#content-item").hasClass("open")){
        $("#content-item>a").trigger("click")
        $("#activity-item").trigger("click");
    }
    $("#content-item").addClass("active").siblings(".active").removeClass("active");

    //返回活动列表
    $(".returnList").click(function(){
        $(".page-content-wrapper").load("/ctactivity/activityList",{});
    });

    $('#table1').DataTable({
        "aoColumnDefs": [ { "bSortable": false, "aTargets": [] }],
        language: {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "关键词搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
    });

})
