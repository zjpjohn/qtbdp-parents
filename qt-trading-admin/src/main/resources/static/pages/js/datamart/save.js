$(document).ready(function(){
    //表单元素
    var subFlie,
        id = Common.QueryString().id,//修改时ID
        $form = $('#form_sample'),//form表单
        $attr = $('#attr>div'),//二级菜单下的属性
        $waresN = $('#waresName'),//产品名称
        $describe = $('#describe'),//产品描述
        $classA = $('#classA'),//一级分类
        $classB = $('#classB'),//二级分类
        $typeTmpl = $('#type_tmpl'),//分类模板
        $sourceTmpl = $('#source_tmpl'),//数据来源模板
        $chargeTmpl = $('#charge_tmpl'),//收费模板
        $waresTmpl = $('#waresType_tmpl'),//文件类型模板
        $source = $('#source'),//数据来源属性
        $charge = $('#charge'),//收费方式
        $wares = $('#waresType'),//文件类型
        $price = $('#Price'),//价格
        $marketPrice = $("#marketPrice"),//市场价
        $synopsis =$('#synopsis'),//产品简介
        $whole = $('#whole'),//整包价格
        $child = $('#child'),//子文件价格
        $scale = $('#scale'),//数据规模
        $file = $('#fileName'),//上传文件
        $fileImg = $('.fileImg');//上传图片

    // var img = $('img#an-img');
    $('#describe').trumbowyg({//文本编辑器实例化
        lang: 'zh_cn',
        btnsDef: {
            // 设置上传的3种方法，远程上传，本地上传，图片64位加密
            image: {
                dropdown: ['insertImage', 'upload'],
                ico: 'insertImage'
            }
        },
        // Redefine the button pane
        btns: ['viewHTML',
            '|', 'formatting',
            '|', 'btnGrp-semantic',
            '|', 'link',
            '|', 'image',
            '|', 'btnGrp-justify',
            '|', 'btnGrp-lists',
            '|', 'horizontalRule']
    });

    $('.dropify').dropify();//图片预览



    $file.ssi_uploader({
        url:'/api/upload/file',
        maxFileSize:6,
        onEachUpload:function (ret) {
            layer.msg("文件上传成功", {icon: 1});
            if (ret.success == true) {
                layer.msg("文件上传成功", {icon: 1});
                $file.attr("data-src", ret.file);
                $file.attr("data-size", ret.dataSize);
                if (ret.subFiles) {
                    subFlie = ret.subFiles
                }
            } else {
                subFlie = {}
            }
        },
        maxNumberOfFiles:1,
        maxFileSize:50
    });






    // $file.fileinput({
    //     language: 'zh',
    //     showUpload:false,
    //     layoutTemplates:{
    //         actionUpload:''
    //     },
    //     uploadUrl: '/api/upload/file'
    // }).on("filebatchselected", function(event, files) {
    //     $('.file-caption').html("");
    //     $(this).fileinput("upload");
    // }).on('fileerror', function(event, data, msg) {
    //     layer.msg("文件上传失败",{icon:5});
    // }).on("fileuploaded", function(event, data, previewId, index) {
    //     layer.msg("文件上传成功",{icon:1});
    //     var ret = data.response;
    //     if (ret.success == true){
    //         layer.msg("文件上传成功",{icon:1});
    //         $file.attr("data-src", ret.file);
    //         $file.attr("data-size", ret.dataSize);
    //         if(ret.subFiles){
    //             subFlie = ret.subFiles
    //         }
    //     }else {
    //         subFlie = {}
    //     }
    // });





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




    //收费价格显示与否
    $charge.on('change','input',function () {
        if(this.type != 'radio'){
            return;
        }else if($(this).is(':checked') === true){
            $price.find("input").val("");
            if($(this).val() === "2"){
                $price.css('display','block');
            }else {
                $price.css('display','none');
            }
        }
    });

    //收费价格校验
    $price.on('blur','input',function () {
        if($price.css('display') != 'none'){
            if($whole.val() == '' || $child.val() == ''){
                $('#warning').css('display','block');
                return;
            }else {
                $('#warning').css('display','none');
            }
        }
    });




    //展示一级
    Common.typeList({
        ele:$classA,
        url:"/api/dataType/findNode",
        tmpl:$typeTmpl
    });
    //通过一级展示二级
    $classA.on('change',function () {
        var id = $(this).val();
        if(id == "" || id == undefined || id == null){
            $classB.empty();
            return;
        }else {
            Common.typeList({
                ele:$classB,
                id:id,
                url:"/api/dataType/findNode",
                tmpl:$typeTmpl
            });
        }
    });





    //修改时获取元素
    var reviseWares = function () {
        if(id == undefined || id == null || id == ""){
            return
        }else {
            $.ajax({
                url: "/api/product/findProductById",
                dataType: "json",
                type:"get",
                data:{
                    id:id
                },
                error:function () {
                    layer.msg("获取信息时错误",{icon:5});
                },
                success: function (ret) {
                    data = ret.pageInfo;

                    if(ret.pageInfo){
                        var data = ret.pageInfo,
                            typePath = data.dataTypePath,
                            type = data.dataType,
                            status = data.dataStatus,
                            dataSrc = data.dataSrc;

                        Common.radioSelect({
                            ele:$('#waresType input'),
                            id:status
                        });

                        Common.radioSelect({
                            ele:$('#source input'),
                            id:dataSrc
                        });

                        if(data.price != 0){
                            $('#mode2').attr('checked','checked');
                            $('#Price').css('display','block');
                            $marketPrice.val(data.marketPrice);
                            $whole.val(data.price);
                            $child.val(data.itemPrice);
                        }else {
                            $('#Price').css('display','none');
                        }


                        Common.optionSelect({
                            ele:$classA,
                            id:typePath
                        });

                        Common.typeList({
                            ele:$classB,
                            id:typePath,
                            url:"/api/dataType/findNode",
                            tmpl:$typeTmpl,
                            CId:type
                        });



                        $synopsis.val(data.dataProfile);
                        $waresN.val(data.designation);
                        $describe.html(data.introduce);
                        $scale.val(data.dataScale);
                        $file.attr("data-src",data.fileUrl);
                        $file.attr("data-size", data.dataSize);
                        $('#tempUrl').html("上次上传文件路径："+data.fileUrl);
                        var img = document.createElement('img');
                        img.setAttribute("src",data.pic);
                        $('.dropify-render').append(img);
                        $('.dropify-preview').css("display","block");
                    }
                }
            });
        }
    };


    reviseWares();





    var FormValidationMd = function () {


        var r = function (options) {
            var _options  = options;

            jQuery.validator.addMethod("isClassB", function() {
                debugger;
                return _options.isParent == 0 || _options.isParent == 1;
            }, "请选择二级分类");

            var e = $("#form_sample"), r = $(".alert-danger", e), i = $(".alert-success", e);
            e.validate({
                errorElement: "span",
                errorClass: "help-block help-block-error",
                focusInvalid: !1,
                ignore: "",
                messages: {
                    name: {required: "名称不能为空",minlength:"名称太短"},
                    type1: {required: "请选择一级分类"},
                    type2: {required: "请选择二级分类"},
                    type:{required: "请选择产品类型"},
                    scale: {required: "请输入数据规模"},
                    describe:{required: "请填写产品描述"},
                    source: {required: "请选择数据来源"},
                    // fileName: {required: "请上传数据文件"},
                    // uploadImg: {required: "请上传图片!"},
                    chargeRadio: {required: "请选择收费方式"},
                    synopsis: {required: "请输入产品简介"},
                    size:{required: "请选择文件大小"}
                },
                rules: {
                    name: {minlength: 2, required: !0},
                    type1: {required: !0},
                    type2: {
                        // isClassB: true,
                        // required: true
                    },
                    scale: {required: !0},
                    synopsis: {required: !0},
                    chargeRadio: {required: !0},
                    type:{required: !0},
                    source: {required: !0},
                    // describe:{required: !0},
                    // fileName: {required: !0},
                    // uploadImg: {required: !0},
                    size:{required: !0}
                },
                invalidHandler: function (e, t) {
                    i.hide(), r.show(), App.scrollTo(r, -200)
                },
                errorPlacement: function (e, r) {
                    r.is(":checkbox") ? e.insertAfter(r.closest(".md-checkbox-list, .md-checkbox-inline, .checkbox-list, .checkbox-inline")) : r.is(":radio") ? e.insertAfter(r.closest(".md-radio-list, .md-radio-inline, .radio-list,.radio-inline")) : e.insertAfter(r)
                },
                highlight: function (e) {
                    $(e).closest(".form-group").addClass("has-error")
                },
                unhighlight: function (e) {
                    $(e).closest(".form-group").removeClass("has-error")
                },
                success: function (e) {
                    e.closest(".form-group").removeClass("has-error")
                },
                submitHandler: function (e) {
                    i.show(), r.hide();


                       // /获取表单信息
                       var ajaxType,
                           mes = '',
                           errorMes = '',
                           wares = $waresN.val(),
                           describe = $describe.html(),
                           classA = $classA.val(),
                           classB = $classB.val(),
                           synopsis = $synopsis.val(),
                           //文件类型信息
                           type = $('#waresType input:radio:checked ').val(),
                           //数据来源信息
                           source = $('#source input:radio:checked ').val(),

                           scale = $scale.val(),
                           file = $file.data("src"),
                           fileSize = $file.data("size"),
                           img = $('.dropify-render>img').attr("src"),
                           data = {
                               designation:wares,
                               introduce:describe,
                               dataProfile:synopsis,
                               pic:img,
                               fileUrl:file,
                               dataSize:fileSize,
                               subFiles:subFlie,
                               dataTypePath:classA,
                               dataType:classB,
                               dataScale:scale,
                               dataStatus:type,
                               dataSrc:source
                           };
                       if(id == undefined || id == null || id == ""){
                           ajaxType = "post";
                           mes = '添加数据产品成功';
                           errorMes = "添加数据包产品失败";

                       }else {
                           ajaxType = "put";
                           data.id = id;
                           mes = '修改数据包产品成功';
                           errorMes = "修改数据包产品失败";
                       }


                       if(charge != 1){
                           data.price = $whole.val();
                           data.itemPrice = $child.val();
                           data.marketPrice = $marketPrice.val();
                       }else {
                           data.price = "0";
                           data.itemPrice = "0";
                           data.marketPrice = "0";
                       }




                       $.ajax({
                           url:"/api/product",
                           type: ajaxType,
                           data: JSON.stringify(data),
                           contentType: "application/json; charset=utf-8",
                           dataType: "json",
                           error: function () {
                               layer.msg(errorMes,{icon:5});
                           },
                           success: function(result) {
                               if (result.success == true ) {
                                   layer.msg(mes, {
                                       icon: 1,
                                       time: 3000 //3秒关闭（如果不配置，默认是3秒）
                                   }, function(){
                                       //do something
                                       window.location.href = '/products';
                                   });
                               }
                           }
                       });



                }
            })
        };
        return {
            init: function (options) {
                r(options)
            }
        }
    }();

    FormValidationMd.init()






















});
