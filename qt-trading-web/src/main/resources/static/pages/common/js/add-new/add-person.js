$(function () {

//表单元素
    var $logo = $('#logo'),//logo
        $phone = $('#phone'),//联系电话

    //表单个人信息
        $name = $('#name'),//姓名
        $ID = $('#ID'),//身份证号
        $IDFace = $('#ID1'),//身份证正面
        $IDreverse = $('#ID2');//身份证反面



    //图片显示异步上传
    fromCommon.RenderImg($(".fileLogo"),$logo,"/api/upload/img",$('#logoContent'));
    fromCommon.RenderImg($(".fileID1"),$IDFace,"/api/upload/img",$('#fullFacePhoto'));
    fromCommon.RenderImg($(".fileID2"),$IDreverse,"/api/upload/img",$('#negativeSidePhoto'));




    ////加载省市下拉菜单
    $("#Location").citySelect({
        url:"/tradecenter/js/city.min.js",
        prov:"浙江", //省份
        city:"杭州", //城市
        // dist:"萧山区", //区县
        nodata:"none" //当子集无数据时，隐藏select
    });





    var options = {
        _form: "#fromPerson",
        _rules: {
            name: {
                required: true,//服务商名称
                rangelength: [5, 50]//长度为8-50之间
            },
            designation: {
                required: true//服务领域
            },
            "personalInfoModel.phone": {
                required: true,//联系电话
                isMobile:true
            },
            "personalInfoModel.realName":{
                required: true//姓名
            },
            "personalInfoModel.idNumber":{
                idcardno:true,
                required: true//身份证
            },
            "personalInfoModel.fullFacePhoto":{
                required: true
            },
            "personalInfoModel.negativeSidePhoto":{
                required: true
            },
            logo:{
                required: true
            },
            agreement:{
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
            "personalInfoModel.phone": {
                required: "请输入联系电话"
            },
            "personalInfoModel.realName":{
                required: "请输入姓名"//姓名
            },
            "personalInfoModel.idNumber":{
                required: "请输入身份证号"//身份证
            },
            "personalInfoModel.fullFacePhoto":{
                required: "请上传身份证正面照"
            },
            "personalInfoModel.negativeSidePhoto":{
                required: "请上传身份证反面照"
            },
            logo:{
                required: "请上传服务商logo"
            },
            agreement:{
                required: "请阅读并同意服务商入驻合作协议"
            }

        }
    };

    FormValidationMd.init(options,function (data) {

        if(data.result.success) {
            setTimeout(function () {
                location.href = "/usercenter";
            },3000)
        }else {
            LoadingData.toastr({
                _type: 'error',
                _title: '表单提交',
                _msg: '网络超时，请重试或者联系管理员'
            }) ;
        }
    });



});