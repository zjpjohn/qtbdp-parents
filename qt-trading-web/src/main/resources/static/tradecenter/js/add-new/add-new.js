$(function () {

//表单元素
    var type = 1,
        $typeBtn = $('.type-button > button'),//切换身份按钮
        $facilitator = $('#facilitator'),//服务商名称
        $field = $('#field'),//服务领域
        $logo = $('#logo'),//logo
        $provinceId = $('#provinceId'),//省份
        $cityId = $('#cityId'),//城市
        $phone = $('#phone'),//联系电话

    //表单个人信息
        $name = $('#name'),//姓名
        $ID = $('#ID'),//身份证号
        $IDFace = $('#ID1'),//身份证正面
        $IDreverse = $('#ID2'),//身份证反面

    //表单企业信息
        $companyName = $('#companyName'),//企业名称
        $License = $('#License'),//营业执照
        $LicenseNumber = $('#LicenseNumber');//营业执照号


    //图片显示异步上传
    fromCommon.RenderImg($(".fileLogo"),$logo,"/api/upload/img");
    fromCommon.RenderImg($(".fileID1"),$IDFace,"/api/upload/img");
    fromCommon.RenderImg($(".fileID2"),$IDreverse,"/api/upload/img");
    fromCommon.RenderImg($(".fileLicense"),$License,"/api/upload/img");


    //身份切换
    $typeBtn.on('click', function () {
        var val = $(this).val();
        $(this).addClass('select-button').siblings().removeClass('select-button');
        type = val;
        if(val == 1){
            $('.person').addClass('block').removeClass('none');
            $('.company').addClass('none').removeClass('block');
        }else if(val == 2) {
            $('.person').addClass('none').removeClass('block');
            $('.company').addClass('block').removeClass('none');
        }
    });

    ////加载省市下拉菜单
    $("#Location").citySelect({
        url:"/tradecenter/js/city.min.js",
        prov:"浙江", //省份
        city:"杭州", //城市
        // dist:"萧山区", //区县
        nodata:"none" //当子集无数据时，隐藏select
    });






    $('#submit-btn').on('click', function (e) {
        e.preventDefault();

        var data = {},

        //获取表单信息
            facilitator = $facilitator.val(),
            field = $field.val(),
            logo = $logo.attr('src'),
            province = $provinceId.val(),
            city = $cityId.val(),
            phone = $phone.val();

        if(type == 1){

        }else if(type == 2){

        }


    });





});