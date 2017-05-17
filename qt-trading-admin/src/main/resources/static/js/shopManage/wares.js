$(function () {
    var $tbody = $('tbody'),
        $trTmpl = $('#tr_tmpl');

    var productList = function () {
        $.ajax({
            url: "/api/product",
            dataType: "json",
            type:"get",
            error:function () {
                alert("加载错误")
            },
            success: function (data) {
                var info = data.pageInfo;
                $trTmpl.tmpl(info.list).appendTo($tbody);
            }
        });
    };









    productList();


})
