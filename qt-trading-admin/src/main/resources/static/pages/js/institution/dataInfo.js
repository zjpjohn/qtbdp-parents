var id = Common.QueryString().id;

$(function () {

    Common.info({
        url:"/api/product/findProductById"+ id
    },function (data) {
        // $('#dataName').val();//名称
        // $('#synopsis').val();//简介
        // $('#scale').val();//规模
        // $('#price').val();//价格
        // $('#dataType').val();//数据类型
        // $('#category').val();//数据类别
        // $('.filename').attr();//数据来源
        // $('#cover').attr();//数据封面
        // $('#merchant').val();//服务商家
        // $('#details').val();//数据描述
    });


});
