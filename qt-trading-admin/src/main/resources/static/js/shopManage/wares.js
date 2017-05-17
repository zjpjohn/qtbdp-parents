$(function () {
    var $tbody = $('tbody');

    var productList = function () {
        
    }









    $.ajax({
        url: "/api/product",
        dataType: "json",
        type:"post",
        error:function () {
            alert("错误")
        },
        success: function () {
            alert("成功")
        }
    });
    
    // function showTable(tpml,tbody,data) {
    //     // tpml.tmpl(data).appendTo(tbody);
    //     console.log(data);
    // }
        


















})
