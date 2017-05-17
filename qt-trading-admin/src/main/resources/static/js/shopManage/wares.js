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
            success: function (ret) {
                var data = ret.pageInfo;
                $trTmpl.tmpl(data).appendTo($tbody);
            }
        });
    };

    $tbody.on('click','.isUsed',function () {
        var tr = $(this).parent().parent(),
            id = tr.children('.id').text();
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
                    productList();
                }
            })
    });

    $tbody.on('click','.revise',function () {
        var tr = $(this).parent().parent(),
            id = tr.children('.id').text();
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

    productList();



});
