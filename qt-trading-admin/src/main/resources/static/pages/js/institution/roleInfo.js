var id = Common.QueryString().id;

$(function () {

    // 获取数据详情
    Common.info({
        id:id,
        url:"/api/crawlers/role/findCrawlersRoleById/"
    },function (data) {
        $('#ruleName').val(data.name);//名称
        $('#net').val(data.webSite);//采集网站
        $('#price').val(data.price);//价格
        var webType = WEB_TYPE[data.webType] ? WEB_TYPE[data.webType] : data.webType;
        $('#netType').val(webType);//网站类型
        var typeName = dataTypeJson[data.typeId] ? dataTypeJson[data.typeId] : data.typeId;
        $('#category').val(typeName);//数据类别
        $('#field').val(data.collectionField);//采集字段
        $('.filename').val(data.filePath);//文件路径
        $('#cover').attr("src",data.logo);//数据封面
        $('#merchant').val(data.username);//服务商家
        $('#details').val(data.desc);//数据描述
    });

    //点击下载
    $('#fileName').on('click',function () {
        Common.downloadFile("/api/upload/roleFile/exist",{roleId:id},"/downloadFreeRole/"+id)
    })
});
