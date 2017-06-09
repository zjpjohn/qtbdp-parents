var id = Common.QueryString().id;

$(function () {

    // 获取数据详情
    Common.info({
        id:id,
        url:"/api/product/findProductById"
    },function (data) {
        $('#dataName').val(data.designation);//名称
        $('#synopsis').val(data.dataProfile);//简介
        $('#scale').val(data.dataScale);//规模
        $('#price').val(data.price);//价格
        var dataStatus = DATA_STATUS[data.dataStatus] ? DATA_STATUS[data.dataStatus] : data.dataStatus;
        $('#dataType').val(dataStatus);//数据类型
        var dataType = dataTypeJson[data.dataType] ? dataTypeJson[data.dataType] : data.dataType;
        $('#category').val(dataType);//数据类别
        var dataSrc = DATA_SRC[data.dataSrc] ? DATA_SRC[data.dataSrc] : data.dataSrc;
        $('#source').val(dataSrc);//数据来源
        $('.filename').val(data.fileUrl);//数据文件
        $('#cover').attr("src",data.pic);//数据封面
        $('#merchant').val(data.username);//服务商家
        $('#details').html(data.introduce);//数据描述
    });

    //点击下载
    $('#fileName').on('click',function () {
        Common.downloadFile("/api/upload/productFile/exist",{productId:id},"/downloadFreeProduct/"+id)
    })
});
