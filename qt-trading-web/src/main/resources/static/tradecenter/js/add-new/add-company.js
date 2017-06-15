$(function () {

//表单元素
    var $facilitator = $('#facilitator'),//服务商名称
        $field = $('#field'),//服务领域
        $logo = $('#logo'),//logo
        $provinceId = $('#provinceId'),//省份
        $cityId = $('#cityId'),//城市
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
                rangelength: [8, 50]//长度为8-50之间
            },
            designation: {
                required: true//服务领域
            }
            // phone: {
            //     required: true//联系电话
            // },
            // companyName:{
            //     required: true//企业名称
            // },
            // LicenseNumber:{
            //     required: true//营业执照号
            // }
        },
        _messages: {
            name: {
                required: "请输入服务商名称",
                rangelength: "字符长度为8-50之间"//长度为8-50之间
            },

            designation: {
                required: "请输入服务领域"
            },
            phone: {
                required: "请输入联系电话"
            },
            companyName:{
                required: "请输入企业名称"//姓名
            },
            LicenseNumber:{
                required: "请输入营业执照注册号"//身份证
            }
        }
    };

    FormValidationMd.init(options,function () {
        alert(11) ;
    });



});