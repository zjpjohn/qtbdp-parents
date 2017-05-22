$(document).ready(function(){
    //表单元素
    var $form = $('#form_sample'),//form表单
        $subBtn = $('#subBtn'),//提交按钮
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
        $whole = $('#whole'),//整包价格
        $child = $('#child'),//子文件价格
        $file = $('#fileName'),//上传文件
        $fileImg = $('.fileImg');

    $('#describe').trumbowyg();//文本编辑器实例化
    $('.dropify').dropify();//图片上传实例化


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
            error:function () {
                alert("加载错误")
            },
            success: function (ret) {
               alert("成功");
               $file.attr("data-src",ret.file)
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
                alert("加载错误")
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



    //展示选择栏数据列表
    var typeList = function (opt) {
        var ele = opt.ele,
            id = opt.id,
            url = opt.url;
        ele.empty();
        $.ajax({
            url: url,
            dataType: "json",
            type:"get",
            data:{
                id:id
            },
            error:function () {
                alert("加载错误")
            },
            success: function (ret) {
                var data = ret.pageInfo;
                $typeTmpl.tmpl(data).appendTo(ele);
            }
        });
    };
    //展示一级
    typeList({
        ele:$classA,
        url:"/api/dataType/findRootNode"
    });

    //通过一级展示二级
    $classA.on('change',function () {
        var id = $(this).val();
        if(id == ""){
            return;
        }else {
            typeList({
                ele:$classB,
                id:id,
                url:"/api/dataType/findSecondNode"
            });
        }
    });

    //展示数据来源，收费方式，数据类型
    $classB.on('change',function () {
        var id = $(this).val();


        if(id == ""){
            $attr.css("display","none");
            return;
        }else {
            $attr.css("display","block");
            attrList({
                ele:$source,
                id:id,
                url:"/api/dataType/findTypeAttr",
                idName:"source",
                tmpl:$sourceTmpl,
                index:2
            });
            attrList({
                ele:$wares,
                id:id,
                url:"/api/dataType/findTypeAttr",
                idName:"TypeCheck",
                tmpl:$waresTmpl,
                index:1
            });
            attrList({
                ele:$charge,
                id:id,
                index:0,
                url:"/api/dataType/findTypeAttr",
                idName:"mode",
                tmpl:$chargeTmpl
            })
        }
    });






    var attrList = function (opt) {
        var ele = opt.ele,
            id = opt.id,
            url = opt.url,
            tmpl = opt.tmpl,
            index = opt.index,
            idName = opt.idName;
        ele.empty();
        $.ajax({
            url: url,
            dataType: "json",
            type:"get",
            data:{
                id:id
            },
            error:function () {
                alert("加载错误")
            },
            success: function (ret) {
                var data = ret.pageInfo;
                tmpl.tmpl(data[index],{
                    setId:function (i) {
                        return idName + i;
                    }
                }).appendTo(ele);
            }
        });
    };




    //表单提交
    $form.on('submit',function (e) {
        e.preventDefault(); //组织默认提交表单
       //获取表单信息
        var attrRelationModels = [],
            wares = $waresN.val(),
            describe = $describe.html(),
            classB = $classB.val(),
            type = $('#waresType input:radio:checked ').val(),
            typeAttrId = $('#waresType input:radio:checked ').data("attrid"),
            typeName = $('#waresTypeName').text(),
            typeCName = $('#waresType input:radio:checked ').data("name"),
            charge = $('#charge input:radio:checked ').val(),
            chargeAttrId = $('#charge input:radio:checked ').data("attrid"),
            chargeName = $('#chargeName').text(),
            chargeCName = $('#charge input:radio:checked ').data("name"),
            source = $('#source input:radio:checked ').val(),
            sourceAttrId = $('#source input:radio:checked ').data("attrid"),
            sourceName = $('#sourceName').text(),
            sourceCName = $('#source input:radio:checked ').data("name"),
            file = $file.data("src"),
            img = $('.dropify-render>img').attr("src"),
            data = {
                designation:wares,
                introduce:describe,
                pic:img,
                fileUrl:file,
                dataType:classB
            };

        if(charge != 1){
            data.price = $whole.val();
            data.item_price = $child.val();
        }


        attrRelationModels.push(Common.createAttr({
            attrId:typeAttrId,
            attrName:typeName,
            valId:type,
            valName:typeCName
        }));

        attrRelationModels.push(Common.createAttr({
            attrId:chargeAttrId,
            attrName:chargeName,
            valId:charge,
            valName:chargeCName
        }));

        attrRelationModels.push(Common.createAttr({
            attrId:sourceAttrId,
            attrName:sourceName,
            valId:source,
            valName:sourceCName
        }));


        data.attrRelationModels = attrRelationModels;
        JSON.stringify(data);

        $.ajax({
            url: '/api/product',
            type: "post",
            data:data,
            error: function () {
                alert("错误")
            },
            success: function() {
                alert("成功");
                // window.location.href = './wares.html';
            }
        });
    })


});
