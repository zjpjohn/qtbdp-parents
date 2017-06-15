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


    fromCommon.inputBind($name,$('#realNameContent'));
    fromCommon.inputBind($ID,$('#idNumberContent'));
    fromCommon.inputBind($phone,$('#phoneContent'));


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
            phone: {
                required: true,//联系电话
                number:true
            },
            realName:{
                required: true//姓名
            },
            idNumber:{
                required: true//身份证
            }
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
                required: "请输入联系电话",
                number:"请填写正确的电话号码"
            },
            realName:{
                required: "请输入姓名"//姓名
            },
            idNumber:{
                required: "请输入身份证号"//身份证
            }
        }
    };

    FormValidationMd.init(options,function () {

    });



});