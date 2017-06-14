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
            { "data": "addtime" },
            { "data": null }
        ],
        columnDefs:[{
            targets: 6,
            render: function (data, type, row, meta) {
                var isUsed,
                    color;
                if(row.isUsed == 1){
                    isUsed = "下架";
                    color = "red";
                }else {
                    isUsed = "上架";
                    color = "blue";
                }
                return '<a href="/save?id=' + row.id + '" class="btn btn-sm green btn-outline filter-submit revise" data-value="'+ row.id +'">修改</a>' +
                    '<button class="btn btn-sm '+ color + ' btn-outline filter-cancel isUsed" value=" '+ row.id +' ">'+ isUsed + '</button>';
            }
        },
            { "orderable": false, "targets": 4 }
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
                        layer.msg("上架成功",{icon: 1});
                    }else {
                        that.removeClass('red').addClass('blue');
                        that.text("上架");
                        layer.msg("下架成功",{icon: 1});
                    }
                }
            }
        })
    });




});
