$(function () {


    //数据类别
    fromCommon.typeSelect($('#typeA'),"/api/type/findRootNode",undefined,'请选择数据类别');
    $('#typeA').on("change",function () {
        var val = $(this).val();
        fromCommon.typeSelect($('#typeB'),"/api/type/"+val,val,'请选择数据类别');
    });

    $('#bargaining').on('click',function () {
       if($(this).is(':checked') == true){
            $(this).val("1");
       }else {
           $(this).val("0");
       }
    });


    $('#endDate').datetimepicker({
        lang:'ch',
        format:'Y-m-d',
        timepicker:false,
        yearStart:1990,
        yearEnd:2017,
        todayButton:true
    });
    $.datetimepicker.setLocale('ch');



    var options = {
        _form: "#fromData",
        _rules: {
            name: {
                required: true,
                rangelength: [5, 50]
            },
            desc: {
                required: true
            },
            dimension: {
                required: true
            },
            scale:{
                digits: true,//必须输入整数
                required: true
            },
            typeId: {
                required: true
            },
            price:{
                required: true,
                digits: true
            },
            endTime:{
                required: true
            },
            website:{
                required: true,
                url:true
            },
            phone:{
                required: true,
                isMobile:true
            },
            qq:{
                required: true

            }
        },
        _messages: {
            name: {
                required: "请输入定制数据名称",
                rangelength: "字符长度为5-50之间"//长度为8-50之间
            },
            desc: {
                required: "请输入定制描述"
            },
            dimension:{
                required: "请输入数据维度"
            },
            scale:{
                required: "请输入数据规模",
                digits: "请输入整数"//必须输入整数
            },
            typeId: {
                required: "请选择数据类别"
            },
            price:{
                required: "请输入悬赏金额",
                digits: "请输入整数"//必须输入整数
            },
            endTime:{
                required: "请选择截止时间"
            },
            website:{
                required: "请填写参考网站",
                url:"网站格式不正确"
            },
            phone:{
                required: "请输入手机号"
            },
            qq:{
                required: "请输入QQ号"

            }
        }
    };

    FormValidationMd.init(options,function (data) {
        //跳转到个人中心我发布的定制
        if(data.result.success) {
            setTimeout(function () {
                location.href = "/usercenter/customized/0";
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











