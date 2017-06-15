$(function () {

//表单元素
    var $facilitator = $('#facilitator'),//服务商名称
        $field = $('#field'),//服务领域
        $logo = $('#logo'),//logo
        $provinceId = $('#provinceId'),//省份
        $cityId = $('#cityId'),//城市
        $phone = $('#phone'),//联系电话

    //表单个人信息
        $name = $('#name'),//姓名
        $ID = $('#ID'),//身份证号
        $IDFace = $('#ID1'),//身份证正面
        $IDreverse = $('#ID2');//身份证反面



    //图片显示异步上传
    fromCommon.RenderImg($(".fileLogo"),$logo,$('#logoContent'));
    fromCommon.RenderImg($(".fileID1"),$IDFace,$('#fullFacePhoto'));
    fromCommon.RenderImg($(".fileID2"),$IDreverse,$('#negativeSidePhoto'));



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
                rangelength: [8, 50]//长度为8-50之间
            },
            designation: {
                required: true//服务领域
            },
            // phone: {
            //     required: true//联系电话
            // },
            // realName:{
            //     required: true//姓名
            // },
            // ID:{
            //     required: true//身份证
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
            realName:{
                required: "请输入姓名"//姓名
            },
            ID:{
                required: "请输入身份证号"//身份证
            }
        }
    };

    FormValidationMd.init(options,function () {
        alert(11) ;
    });



});