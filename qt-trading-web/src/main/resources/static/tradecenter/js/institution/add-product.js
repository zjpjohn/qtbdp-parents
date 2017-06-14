$(function () {
    //表单信息
    var subFlie,
        $dataName = $('#data_name'),
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
            }
        }
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
                    $dataFile.attr("data-src", ret.file);
                    $dataFile.attr("data-size", ret.dataSize);
                    if(ret.subFiles){
                        subFlie = ret.subFiles
                    }
                }else {
                    subFlie = {}
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
            }
        });
    });


    var options = {
        _form: "#fromProduct",
        _rules: {
            name: {
                required: true,
                rangelength: [8, 50]
            },
            resume: {
                required: true
            },
            typeA: {
                required: true
            },
            typeB: {
                required: true
            },
            data_scale:{
                required: true
            },
            introduction:{
                required: true
            }
        },
        _messages: {
            name: {
                required: "请输入数据名称",
                rangelength: "字符长度为8-50之间"//长度为8-50之间
            },
            resume: {
                required: "请输入数据简介"
            },
            typeA: {
                required: "请选择数据类别"
            },
            typeB: {
                required: "请选择数据类别"
            },
            data_scale:{
                required: "请输入数据规模"
            },
            introduction:{
                required: "请输入数据描述"
            }
        }
    };

    FormValidationMd.init(options,function () {
        alert(11) ;
    });
});