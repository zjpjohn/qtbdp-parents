$(function () {
    var $tbody = $('tbody'),
        $searchBtn = $('#searchBtn'),
        searchInp = $('#searchInp');


    var settings = {
        container:"#datatable_ajax",
        url: "/api/product",
        columns: [
            { "data": "id" },
            { "data": "dataTypeProps" },
            { "data": "dataType" },
            { "data": "designation" },
            { "data": "userId" },
            { "data": "addTime" },
            { "data": null }
        ]
    };

    Common.ajaxTable(settings);


    //上下架操作
    $tbody.on('click','.isUsed',function () {
        var id = $(this).val();
            $.ajax({
                url: "/api/product/changeState",
                dataType: "json",
                type:"get",
                data:{
                    id:id
                },
                error:function () {
                    alert("错误")
                },
                success: function () {
                    alert("成功");
                }
            })
    })

    //修改操作
    $tbody.on('click','.revise',function () {
        var id = $(this).data("value");
        $.ajax({
            url: "/api/product/changeState",
            dataType: "json",
            type:"get",
            data:{
                id:id
            },
            error:function () {
                alert("错误")
            },
            success: function () {
                alert("成功");
                window.location.href = './setUp.html';
            }
        })
    });

    // 搜索
    $searchBtn.on('click',function () {
        var val = searchInp.val();

    })

});
