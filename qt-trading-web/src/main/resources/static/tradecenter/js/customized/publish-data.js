$(function () {


    //数据类别
    fromCommon.typeSelect($('#typeA'),"/api/type/findRootNode",undefined,'请选择数据类别');
    $('#typeA').on("change",function () {
        var val = $(this).val();
        fromCommon.typeSelect($('#typeB'),"/api/type/"+val,val,'请选择数据类别');
    });


    var options = {
        _form: "#fromData",
        _rules: {
            name: {
                required: true,
                rangelength: [8, 50]
            },
            introduction: {
                required: true
            },
            dimension: {
                required: true
            },
            scale:{
                required: true
            },
            typeA:{
                required: true
            },
            typeId: {
                required: true
            },
            price:{
                required: true
            },
            endTime:{
                required: true
            },
            website:{
                required: true
            },
            phone:{
                required: true
            },
            qq:{
                required: true

            }
        },
        _messages: {
            name: {
                required: "请输入定制数据名称",
                rangelength: "字符长度为8-50之间"//长度为8-50之间
            },
            describe: {
                required: "请输入定制描述"
            },
            dimension:{
                required: "请输入数据维度"
            },
            scale:{
                required: "请输入数据规模"
            },
            typeA:{
                required: "请选择数据类别"
            },
            typeId: {
                required: "请选择数据类别"
            },
            price:{
                required: "请输入悬赏金额"
            },
            endTime:{
                required: "请选择截止时间"
            },
            website:{
                required: "请填写参考网站"
            },
            phone:{
                required: "请输入手机号"

            },
            qq:{
                required: "请输入QQ号"

            }
        }
    };

    FormValidationMd.init(options,function () {

    });
});











