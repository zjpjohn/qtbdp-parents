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
            { "data": "username" },
            { "data": "addTime" },
            { "data": null }
        ]
    };

    Common.ajaxTable(settings);


    //上下架操作
    $tbody.on('click','.isUsed',function () {
        var that = $(this);
        var id = $(this).val();
            $.ajax({
                url: "/api/product/changeState",
                dataType: "json",
                type:"get",
                data:{
                    id:id
                },
                error:function () {
                    layer.msg("修改产品上下架状态失败",{icon: 5});
                },
                success: function (ret) {
                    if(ret.success === true){
                        if(that.text() == "上架"){
                            that.removeClass('blue').addClass('red');
                            that.text("下架");
                        }else {
                            that.removeClass('red').addClass('blue');
                            that.text("上架");
                        }
                    }
                }
            })
    });




});
