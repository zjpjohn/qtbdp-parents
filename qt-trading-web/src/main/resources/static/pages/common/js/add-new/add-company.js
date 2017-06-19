$(function () {

//表单元素
    var $logo = $('#logo'),//logo
        $phone = $('#phone'),//联系电话

        //表单企业信息
        $companyName = $('#companyName'),//企业名称
        $License = $('#License'),//营业执照
        $LicenseNumber = $('#LicenseNumber');//营业执照号


    //图片显示异步上传
    fromCommon.RenderImg($(".fileLogo"),$logo,"/api/upload/img",$('#logoContent'));
    fromCommon.RenderImg($(".fileLicense"),$License,"/api/upload/img",$('#licenseContent'));



    ////加载省市下拉菜单
    $("#Location").citySelect({
        url:"/tradecenter/js/city.min.js",
        prov:"浙江", //省份
        city:"杭州", //城市
        // dist:"萧山区", //区县
        nodata:"none" //当子集无数据时，隐藏select
    });


    var options = {
        _form: "#fromCompany",
        _rules: {
            name: {
                required: true,//服务商名称
                rangelength: [5, 50]//长度为8-50之间
            },
            designation: {
                required: true//服务领域
            },
            "companyInfoModel.phone": {
                required: true,//联系电话
                isMobile:true
            },
            "companyInfoModel.companyName":{
                required: true//企业名称
            },
            "companyInfoModel.licenseNumber":{
                required: true//营业执照号
            },
            "companyInfoModel.companyLogo":{
                required: true
            },
            logo:{
                required: true
            }
        },
        _messages: {
            name: {
                required: "请输入服务商名称",
                rangelength: "字符长度为5-50之间"//长度为8-50之间
            },
            designation: {
                required: "请输入服务领域"
            },
            "companyInfoModel.phone": {
                required: "请输入联系电话"
            },
            "companyInfoModel.companyName":{
                required: "请输入企业名称"//姓名
            },
            "companyInfoModel.licenseNumber":{
                required: "请输入营业执照注册号"//身份证
            },
            "companyInfoModel.companyLogo":{
                required: "请上传营业执照照片"
            },
            logo:{
                required: "请上传服务商logo"
            }
        }
    };

    FormValidationMd.init(options,function () {

    });



});