var id = Common.QueryString().id;

$(function () {

    Common.info({
        url:"/api/customized/"+ id
    },function (data) {
        if(data.createId == 0){
            data.createId = "系统管理员";
        }

        $('#name').val(data.name);//名称
        $('#synopsis').val(data.desc);//描述
        $('#range').val(data.dimension);//采集维度
        $('#scale').val(data.scale);//规模
        $('#type').val(Common.typeSelect(dataTypeJson,data.serviceType));//数据类别
        $('#price').val(data.price);//金额
        $('#net').val(data.website);//网站
        $('#time').val(Common._formatedate(data.endTime));//时间
        $('#person').val(data.createId);//发布人
        $('#tel').val(data.phone);//电话
        $('#QQ').val(data.qq);//QQ
    });


});

