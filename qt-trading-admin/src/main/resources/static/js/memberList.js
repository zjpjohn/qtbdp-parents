/**
 * Created by zd on 2017/5/3.
 */
$(function(){
    $(".system-item").addClass("active").siblings(".active").removeClass("active");
    $(".sub-menu li.nav-item.active").removeClass("active");
    $(".sub-menu li.nav-item.open>a").trigger("click");
    $(".jigou-item").addClass("active");
    if(!$(".system-item").hasClass("open")){
        $(".system-item>a").trigger("click");
    }

    //进入添加用户页面
    $(".addUserLink").click(function(){
        $(".page-content-wrapper").load("/user/addSysMember",{});
    });
    //进入修改页面
    $(".changeInfo").click(function(){
        var id=$(this).attr("data-id");
        $(".page-content-wrapper").load("/user/findUserById",{
            id:id
        });
    });
    //返回用户列表
    $(".returnList").click(function(){
        $(".page-content-wrapper").load("/user/userList",{});
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