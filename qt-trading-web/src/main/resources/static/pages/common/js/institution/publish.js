$(function () {
    //表单信息
    var $ChargeMode = $('#Charge_mode'),
        $price = $('#price'),

        $typeA = $('#typeA'),
        $typeB = $('#typeB'),
        $dataFile = $('#data_file'),
        $fileImg = $('.fileImg');//上传图片




    $('.dropify').dropify({
        messages: {
            'default': '拖放文件或单击',
            'replace': '拖放或单击以替换',
            'remove':  '删除',
            'error':   '错误'
        }
    });//图片预览

    //收费价格显示与否
    $ChargeMode.on('change','input',function () {
        if(this.type != 'radio'){
            return;
        }else if($(this).is(':checked') === true){
            $price.find("input").val("");
            if($(this).val() == 2){
                $price.css('display','block');
            }else {
                $price.css('display','none');
                $('#price input').val("0");
            }
        }
    });


    //数据类别
    fromCommon.typeSelect($typeA,"/api/type/findRootNode",undefined,'请选择数据类别');
    $typeA.on("change",function () {
        var val = $(this).val();
        fromCommon.typeSelect($typeB,"/api/type/"+val,val,'请选择数据类别');
    });


    // 上传文件
    $dataFile.change(function(){
        $(this).parents(".uploader").find("#data_file_path").val($(this).val());
        var val = this.files[0];
        var formData = new FormData();
        formData.append("file", val);

        $.ajax({
            url: "/api/upload/file",
            type:"post",
            contentType: false,
            processData: false,
            data:formData,
            beforeSend: function(){
                layer.msg("文件正在上传",{icon:5});
            },
            error:function () {
                layer.msg("文件上传失败",{icon:5});
            },
            success: function (ret) {
                if (ret.success == true){
                    layer.msg("文件上传成功",{icon:1});
                    $('#fileContent').val(ret.file);
                }
            }
        });
    });
    //未选择文件时显示
    $dataFile.each(function(){
        if($(this).val()==""){
            $(this).parents(".uploader").find("#data_file_path").val("未选中文件...");
        }
    });


    //图片上传
    $fileImg.on("change",function () {
        var val = this.files[0];
        var formData = new FormData();
        formData.append("img", val);
        $.ajax({
            url: "/api/upload/img",
            dataType: "json",
            type:"post",
            contentType: false,
            processData: false,
            data:formData,
            error:function () {
                layer.msg("图片上传失败",{icon:5});
            },
            success: function (ret) {
                var img = $('.dropify-render>img');
                img.attr("src",ret.img);
                $('#imgContent').val(ret.img);
            }
        });
    });


    var options = {
        _form: "#ruleupload",
        _rules: {
            name: {
                required: true,//规则名称
                rangelength: [5, 50]//长度为5-50之间
            },
            price: {
                required: true,//规则价格
                number: true,//必须输入数字
                digits: true//必须输入整数
            },
            webSite: {//采集网站
                required: true
            },
            collectionField:{
                required: true
            },
            typeId:{
                required: true
            },
            //网站类型
            large_input: "required",//采集字段
            //数据类别
            filePath: "required",//规则文件
            //封面图
            introduction: "required",//规则描述
            logo:"required"
        },
        _messages: {
            name: {
                required: "请输入规则名称",//规则名称
                rangelength: "字符长度为8-50之间"//长度为8-50之间
            },

            price: {
                required: "请输入规则价格",//规则价格
                number: "请输入数字",//必须输入数字
                digits: "请输入整数"//必须输入整数

            },
            webSite: {//采集网站
                required: "请输入采集网站"

            },
            collectionField:{
                required: "请输入采集字段"
            },
            typeId:{
                required: "请选择数据类别"
            },
            //网站类型
            large_input: "请输入采集字段，以','隔开",//采集字段
            //数据类别
            filePath: "请上传规则文件",//规则文件
            //封面图
            introduction: "请输入规则描述",//规则描述
            logo:"请上传图片"
        }
    }

    FormValidationMd.init(options,function (data) {
        //跳转到个人中心我发布的商品
        if(data.result.success) {
            setTimeout(function () {
                location.href = "/usercenter/publich/1";
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

















