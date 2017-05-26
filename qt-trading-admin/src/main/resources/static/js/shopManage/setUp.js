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
        $fileSizeTmpl = $('#fileSize_tmpl'),//文件大小模板
        $source = $('#source'),//数据来源属性
        $charge = $('#charge'),//收费方式
        $wares = $('#waresType'),//文件类型
        $fileSize = $('#fileSize'),//文件大小
        $price = $('#Price'),//价格
        $whole = $('#whole'),//整包价格
        $child = $('#child'),//子文件价格
        $file = $('#fileName'),//上传文件
        $fileImg = $('.fileImg');//上传图片

    $('#describe').trumbowyg();//文本编辑器实例化
    $('.dropify').dropify();//图片预览


    //上传文件
    $file.change(function(){
        $(this).parents(".uploader").find(".filename").val($(this).val());
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
                    $file.attr("data-src", ret.file);
                    $file.attr("data-size", ret.dataSize);
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
    $file.each(function(){
        if($(this).val()==""){
            $(this).parents(".uploader").find(".filename").val("未选中文件...");
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


    //收费价格显示与否
    $charge.on('click','input',function () {
        if(this.type != 'radio'){
            return;
        }else if($(this).is(':checked') === true){
            if($(this).val() === "101"){
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
        url:"/api/dataType/findRootNode",
        tmpl:$typeTmpl
    });


    //通过一级展示二级
    $classA.on('change',function () {
        var id = $(this).val(),
            isParent = $(this).find("option:selected").attr("data-isParent");
        $attr.css("display","none");
        if(id == "" || id == undefined || id == null){
            $classB.empty();
            return;
        }else {
            if(isParent == 0){
                $attr.css("display","block");
                $attr.children().css("display","block");
                Common.attrList({
                    id:id,
                    attrId:$attr,
                    attrContent:[
                        {attrN:$source,index:2,idName:"source",tmpl:$sourceTmpl},
                        {attrN:$wares,index:1,idName:"TypeCheck",tmpl:$waresTmpl},
                        {attrN:$charge,index:0,idName:"mode",tmpl:$chargeTmpl}
                    ]
                });
            }else {
                Common.typeList({
                    ele:$classB,
                    id:id,
                    url:"/api/dataType/findSecondNode",
                    tmpl:$typeTmpl
                });
            }
        }
    });


    //展示数据来源，收费方式，数据类型
    $classB.on('change',function () {
        var id = $(this).val();
        if(id == ""|| id == undefined || id == null){
            $attr.css("display","none");
            return;
        }else {
            Common.attrList({
                id:id,
                attrId:$attr,
                attrContent:[
                    {attrN:$source,index:2,idName:"source",tmpl:$sourceTmpl},
                    {attrN:$wares,index:1,idName:"TypeCheck",tmpl:$waresTmpl},
                    {attrN:$charge,index:0,idName:"mode",tmpl:$chargeTmpl}
                ]
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
                    alert("错误")
                },
                success: function (ret) {
                    data = ret.pageInfo;
                    if(ret.pageInfo){
                        var data = ret.pageInfo,
                            type = data.dataTypeModel,
                            optionA = $classA.children(),
                            optionB = $classB.children();

                        Common.optionSelect({
                            ele:$classA,
                            option:optionA,
                            id:type.pid
                        });
                        Common.typeList({
                            ele:$classB,
                            id:type.pid,
                            url:"/api/dataType/findSecondNode",
                            tmpl:$typeTmpl,
                            CId:type.id,
                            selected:data.attrRelationModels,
                            price:data.price,
                            itemPrice:data.itemPrice,
                            PWhole:$whole,
                            PChild:$child,
                            attrId:$attr,
                            priceName:$price,
                            attrContent:[
                                {attrN:$source,index:2,idName:"source",tmpl:$sourceTmpl},
                                {attrN:$wares,index:1,idName:"TypeCheck",tmpl:$waresTmpl},
                                {attrN:$charge,index:0,idName:"mode",tmpl:$chargeTmpl}
                            ]
                        });

                        $waresN.val(data.designation);
                        $describe.html(data.introduce);
                        $(".filename").val(data.fileUrl);
                        $file.attr("data-src",data.fileUrl);
                        $file.attr("data-size", data.dataSize);
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





    // 表单提交
    $form.on('submit',function (e) {
        e.preventDefault(); //组织默认提交表单

        FormValidationMd.init({
            isParent:$classA.find("option:selected").attr("data-isParent"),
            v:$classB.val()
        });

       //获取表单信息
        var ajaxType,
            mes = '',
            errorMes = '',
            attrRelationModels = [],
            dataTypeProps = "",
            wares = $waresN.val(),
            describe = $describe.html(),
            classB = $classB.val(),
            //文件类型信息
            type = $('#waresType input:radio:checked ').val(),
            typeAttrId = $('#waresType input:radio:checked ').data("attrid"),
            typeName = $('#waresTypeName').text(),
            typeCName = $('#waresType input:radio:checked ').data("name"),
            // 收费方式信息
            charge = $('#charge input:radio:checked ').val(),
            chargeAttrId = $('#charge input:radio:checked ').data("attrid"),
            chargeName = $('#chargeName').text(),
            chargeCName = $('#charge input:radio:checked ').data("name"),
            //数据来源信息
            source = $('#source input:radio:checked ').val(),
            sourceAttrId = $('#source input:radio:checked ').data("attrid"),
            sourceName = $('#sourceName').text(),
            sourceCName = $('#source input:radio:checked ').data("name"),

            file = $file.data("src"),
            fileSize = $file.data("size"),
            img = $('.dropify-render>img').attr("src"),
            data = {
                designation:wares,
                introduce:describe,
                pic:img,
                fileUrl:file,
                dataSize:fileSize,
                subFiles:subFlie,
                dataType:classB
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
        }else {
            data.price = "0";
            data.itemPrice = "0";
        }



        attrRelationModels.push(Common.createAttr({
            attrName:typeName,
            valName:typeCName,
            attrId:typeAttrId,
            valId:type
        }));

        attrRelationModels.push(Common.createAttr({
            attrName:chargeName,
            valName:chargeCName,
            attrId:chargeAttrId,
            valId:charge
        }));

        attrRelationModels.push(Common.createAttr({
            attrName:sourceName,
            valName:sourceCName,
            attrId:sourceAttrId,
            valId:source

        }));



        $.each(attrRelationModels,function (i,v) {
            for(var k in v){
                dataTypeProps += v[k] + ":";
            }
            dataTypeProps = dataTypeProps.substr(0,dataTypeProps.length-1);
            dataTypeProps += ";";
        });


        data.attrRelationModels = attrRelationModels;
        data.dataTypeProps = dataTypeProps;





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
                        window.location.href = '/wares';
                    });
                }
            }
        });
    })

});
