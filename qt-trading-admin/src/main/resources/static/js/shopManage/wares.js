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
                    alert("错误")
                },
                success: function (ret) {
                    if(ret.success === true){
                        alert(that.text() + "成功");
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
