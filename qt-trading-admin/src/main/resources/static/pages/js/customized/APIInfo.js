var id = Common.QueryString().id;

$(function () {

    Common.info({
        url:"/api/demand/"+ id
    },function (data) {

        $('#name').val(data.apiName);//名称
        $('#type').val(data.categoryId);//所在API接口类目
        $('#industry').val(data.apiDomain);//所属行业
        $('#desc').val(data.apiDesc);//接口描述
        $('#price').val(data.apiPrice + "￥");//需求预算
        $('#field').val(data.apiField);//返回字段
        $('#Contacts').val(data.contacts);//联系人
        $('#time').val(Common._formatedate(data.createTime));//时间
        $('#phone').val(data.phone);//电话
    });

});

