var id = Common.QueryString().id;

$(function () {

    Common.info({
        url:"/api/institutionV2/"+ id
    },function (data) {
        // $('#identity').val();//身份
        // $('#institutionName').val();//服务商名称
        // $('#domain').val();//服务领域
        // $('#logo').attr();//logo
        // $('#name').val();//姓名
        // $('#ID').val();//身份证号
        // $('#IDImg1').attr();//身份证正面
        // $('#IDImg2').attr();//身份证反面
        // $('#location').val();//所在地
        // $('#person').val();//申请人
        // $('#tel').val();//电话
    });


});
