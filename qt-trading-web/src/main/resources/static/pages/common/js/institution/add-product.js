$(function () {
    //表单信息
    var $dataName = $('#data_name'),
        $dataResume = $('#data_resume'),
        $ChargeMode = $('#Charge_mode'),
        $price = $('#price'),
        $marketPrice = $('#market_price'),
        $rebatePrice = $('#rebate_price'),
        $child_price = $('#child_price'),
        $commission = $('#commission_price'),
        $typeA = $('#typeA'),
        $typeB = $('#typeB'),
        $dataScale = $('#data_scale'),
        $dataType = $('#data_type>input'),
        $dataSource = $('#data_source>input'),
        $dataFile = $('#data_file'),
        $fileImg = $('.fileImg'),//上传图片
        $introduction = $('#introduction');



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
        _form: "#fromProduct",
        _rules: {
            designation: {
                required: true,
                rangelength: [5, 50]
            },
            dataProfile: {
                required: true
            },
            dataTypeSub: {
                required: true
            },
            dataScale:{
                required: true,
                digits: true
            },
            introduce:{
                required: true
            },
            fileUrl:{
                required: true
            },
            pic:{
                required: true
            }
        },
        _messages: {
            designation: {
                required: "请输入数据名称",
                rangelength: "字符长度为5-50之间"//长度为5-50之间
            },
            dataProfile: {
                required: "请输入数据简介"
            },
            dataTypeSub: {
                required: "请选择数据类别"
            },
            dataScale:{
                required: "请输入数据规模",
                digits: "请输入整数"//必须输入整数
            },
            introduce:{
                required: "请输入数据描述"
            },
            fileUrl:{
                required: "请上传数据"
            },
            pic:{
                required: "请上传图片"
            }
        }
    };

    FormValidationMd.init(options,function (data) {
        //跳转到个人中心我发布的商品
        if(data.result.success) {
            setTimeout(function () {
                location.href = "/usercenter/publich/0";
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

















